// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.fields

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.JsonNodeFactory
import com.fasterxml.jackson.databind.node.ObjectNode

internal data class SpecOperation(
    val method: String,
    val server: String,
    val path: String,
    val query: Shape.Fields,
    val bodies: Map<String, Shape>,
    val response: Shape?,
) {

    fun body(contentType: String?): Shape? =
        if (contentType == null) bodies.values.firstOrNull()
        else bodies[contentType.substringBefore(";").trim().lowercase()]
}

internal class SpecShapes(private val root: JsonNode) {

    fun operations(): List<SpecOperation> {
        val paths = root.path("paths")
        require(paths.isObject) { "The API spec has no paths object." }
        return paths.properties().flatMap { (path, item) ->
            METHODS.mapNotNull { method ->
                item.get(method)?.let { operation(path, item, method, it) }
            }
        }
    }

    private fun operation(
        path: String,
        item: JsonNode,
        method: String,
        op: JsonNode,
    ): SpecOperation {
        val params = linkedMapOf<Pair<String, String>, JsonNode>()
        (item.path("parameters").toList() + op.path("parameters").toList()).forEach { raw ->
            val param = deref(raw)
            params[param.path("in").asText() to param.path("name").asText()] = param
        }
        val query =
            params
                .filterKeys { it.first == "query" }
                .map { (key, param) ->
                    key.second to
                        Prop(
                            node(param.path("schema"), emptyList()).first,
                            param.path("required").asBoolean(false),
                            null,
                        )
                }
                .toMap()
        return SpecOperation(
            method.uppercase(),
            serverPath(op.get("servers") ?: item.get("servers") ?: root.get("servers")),
            path,
            Shape.Fields(query),
            requestBody(op),
            response(op),
        )
    }

    private fun serverPath(servers: JsonNode?): String {
        val url = servers?.get(0)?.path("url")?.asText().orEmpty()
        return url.replace(Regex("^[A-Za-z][A-Za-z0-9+.-]*://[^/]*"), "").trimEnd('/')
    }

    private fun requestBody(op: JsonNode): Map<String, Shape> {
        val body = op.get("requestBody")?.let(::deref) ?: return emptyMap()
        val content = body.path("content")
        return BODY_TYPES.mapNotNull { type ->
                content.get(type)?.get("schema")?.let { type to node(it, emptyList()).first }
            }
            .toMap()
    }

    private fun response(op: JsonNode): Shape? {
        val shapes =
            op.path("responses")
                .properties()
                .filter { (code, _) -> code.startsWith("2") }
                .flatMap { (_, raw) ->
                    deref(raw)
                        .path("content")
                        .properties()
                        .filter { (type, media) ->
                            val base = type.substringBefore(";").trim()
                            (base == "application/json" || base.endsWith("+json")) &&
                                media.has("schema")
                        }
                        .map { (_, media) -> node(media.path("schema"), emptyList()).first }
                }
        return when (shapes.size) {
            0 -> null
            1 -> shapes.single()
            else -> Shape.AnyOf(shapes)
        }
    }

    private fun deref(node: JsonNode): JsonNode =
        node.get("\$ref")?.let { resolve(it.asText()) } ?: node

    private fun resolve(ref: String): JsonNode {
        require(ref.startsWith("#/")) { "The API spec has a reference outside the file: $ref" }
        val target =
            ref.removePrefix("#/").split("/").fold(root) { node, part ->
                node.path(part.replace("~1", "/").replace("~0", "~"))
            }
        require(!target.isMissingNode) { "The API spec has a reference to nothing: $ref" }
        return target
    }

    fun node(schema: JsonNode, stack: List<String>): Pair<Shape, Boolean> {
        if (!schema.isObject) {
            return Shape.Unknown to false
        }
        schema.get("\$ref")?.let { ref ->
            return reference(schema, ref.asText(), stack)
        }
        var nullable = schema.path("nullable").asBoolean(false)
        val typeNode = schema.get("type")
        val types =
            when {
                typeNode == null -> null
                typeNode.isArray -> typeNode.map { it.asText() }
                else -> listOf(typeNode.asText())
            }
        if (types != null && "null" in types) {
            nullable = true
            if (types.size == 1) {
                return Shape.Unknown to true
            }
        }
        val kinds = types?.filter { it != "null" }
        schema.get("allOf")?.let { parts ->
            return allOf(schema, parts, stack) to nullable
        }
        for (key in listOf("oneOf", "anyOf")) {
            val variants = schema.get(key) ?: continue
            val (shape, variantNullable) = oneOf(schema, key, variants, stack)
            return shape to (nullable || variantNullable)
        }
        schema.get("const")?.let {
            return Shape.Leaf(setOf(scalar(it))) to nullable
        }
        schema.get("enum")?.let { values ->
            val known = values.filterNot { it.isNull }
            return Shape.Leaf(known.map(::scalar).toSet()) to
                (nullable || known.size < values.size())
        }
        val properties = schema.get("properties")
        val isObject = kinds?.contains("object") == true || properties != null
        if (isObject && properties != null && properties.size() > 0) {
            return fields(schema, properties, stack) to nullable
        }
        if (isObject) {
            return Shape.Free to nullable
        }
        if (kinds == null && schema.has("required")) {
            return Shape.Fields(emptyMap(), requiredOnly = requiredNames(schema)) to nullable
        }
        if (kinds?.contains("array") == true) {
            val items = schema.get("items")
            if (items != null && !schema.has("prefixItems")) {
                return Shape.ListOf(node(items, stack).first) to nullable
            }
        }
        return Shape.Leaf() to nullable
    }

    private fun reference(
        schema: JsonNode,
        ref: String,
        stack: List<String>,
    ): Pair<Shape, Boolean> {
        if (ref in stack) {
            return Shape.Cycle to false
        }
        val target = resolve(ref)
        val siblings = schema.properties().filter { (key, _) -> key !in IGNORED_SIBLINGS }
        if (siblings.any { (key, _) -> key != "type" }) {
            val allOf = JsonNodeFactory.instance.objectNode()
            val rest = JsonNodeFactory.instance.objectNode()
            siblings.forEach { (key, value) -> rest.set<JsonNode>(key, value) }
            allOf.putArray("allOf").add(target).add(rest)
            return node(allOf, stack + ref)
        }
        val (shape, nullable) = node(target, stack + ref)
        val siblingType = schema.get("type")
        val siblingNullable =
            siblingType != null && siblingType.isArray && siblingType.any { it.asText() == "null" }
        return shape to (nullable || siblingNullable)
    }

    private fun allOf(schema: JsonNode, parts: JsonNode, stack: List<String>): Shape {
        val shapes = parts.map { node(it, stack).first }.toMutableList()
        val rest = (schema.deepCopy<ObjectNode>()).apply { remove("allOf") }
        if (rest.has("properties") || rest.has("required")) {
            shapes += node(rest, stack).first
        }
        return intersect(shapes)
    }

    private fun oneOf(
        schema: JsonNode,
        key: String,
        variants: JsonNode,
        stack: List<String>,
    ): Pair<Shape, Boolean> {
        var nullable = false
        val shapes = variants.mapNotNull { variant ->
            val isNull =
                variant.path("type").asText() == "null" ||
                    (variant.path("enum").isArray &&
                        variant.path("enum").size() == 1 &&
                        variant.path("enum")[0].isNull)
            if (isNull) {
                nullable = true
                null
            } else {
                val (shape, variantNullable) = node(variant, stack)
                nullable = nullable || variantNullable
                shape
            }
        }
        val merged =
            if (schema.has("properties")) {
                val rest = (schema.deepCopy<ObjectNode>()).apply { remove(key) }
                val base = node(rest, stack).first
                shapes.map { intersect(listOf(base, it)) }
            } else {
                shapes
            }
        return (if (merged.size == 1) merged.single() else Shape.AnyOf(merged)) to nullable
    }

    private fun fields(schema: JsonNode, properties: JsonNode, stack: List<String>): Shape.Fields {
        val required = requiredNames(schema)
        val props =
            properties.properties().associate { (name, sub) ->
                val (shape, nullable) = node(sub, stack)
                name to Prop(shape, name in required, nullable)
            }
        val additional = schema.get("additionalProperties")
        return Shape.Fields(
            props,
            open = additional != null && !(additional.isBoolean && !additional.asBoolean()),
        )
    }

    private fun requiredNames(schema: JsonNode): Set<String> =
        schema.path("required").map { it.asText() }.toSet()

    private fun scalar(node: JsonNode): String =
        if (node.isTextual) node.asText() else node.toString()

    companion object {
        private val METHODS = listOf("get", "post", "put", "patch", "delete")
        private val BODY_TYPES =
            listOf("application/json", "multipart/form-data", "application/x-www-form-urlencoded")
        private val IGNORED_SIBLINGS =
            setOf(
                "\$ref",
                "description",
                "example",
                "examples",
                "title",
                "deprecated",
                "readOnly",
                "writeOnly",
            )
    }
}

internal fun intersect(all: List<Shape>): Shape {
    val parts = all.filterNot { it == Shape.Unknown }
    if (parts.isEmpty()) {
        return Shape.Unknown
    }
    val unions = parts.filterIsInstance<Shape.AnyOf>()
    if (unions.size == 1) {
        val rest = parts.filterNot { it is Shape.AnyOf }
        return Shape.AnyOf(unions.single().variants.map { intersect(rest + it) })
    }
    if (parts.all { it is Shape.ListOf }) {
        return Shape.ListOf(intersect(parts.map { (it as Shape.ListOf).items }))
    }
    val objects = parts.filterIsInstance<Shape.Fields>()
    if (objects.isEmpty()) {
        return parts.first()
    }
    val props = linkedMapOf<String, Prop>()
    objects.forEach { part ->
        part.props.forEach { (name, prop) ->
            val old = props[name]
            props[name] =
                if (old == null) {
                    prop
                } else {
                    Prop(
                        intersect(listOf(old.shape, prop.shape)),
                        old.required == true || prop.required == true,
                        old.nullable == true && prop.nullable == true,
                    )
                }
        }
    }
    val requiredOnly = objects.flatMap { it.requiredOnly }.toSet()
    requiredOnly.forEach { name -> props[name]?.let { props[name] = it.copy(required = true) } }
    return Shape.Fields(
        props,
        open = objects.any { it.open },
        requiredOnly = requiredOnly.filterNot { it in props }.toSet(),
    )
}
