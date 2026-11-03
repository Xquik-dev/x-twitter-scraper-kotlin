// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.fields

internal sealed interface Shape {

    data object Free : Shape

    data object Cycle : Shape

    data object Unknown : Shape

    data class Leaf(val values: Set<String>? = null) : Shape

    data class Many(val items: Shape, val container: Container = Container.LIST) : Shape

    data class Fields(
        val props: Map<String, Prop>,
        val open: Boolean = false,
        val requiredOnly: Set<String> = emptySet(),
    ) : Shape

    data class AnyOf(val variants: List<Shape>) : Shape
}

internal enum class Container(val suffix: String) {
    LIST("[]"),
    MAP("{}"),
}

internal data class Prop(val shape: Shape, val required: Boolean?, val nullable: Boolean?)

internal enum class Side(val label: String) {
    REQUEST("request"),
    RESPONSE("response"),
}

internal enum class Kind(val label: String) {
    MISSING("missing"),
    EXTRA("extra"),
    ENUM("enum"),
    REQUIRED("required"),
    NULLABLE("nullable"),
}

internal data class Difference(val side: Side, val kind: Kind, val path: String)

internal fun Shape.isOpaque(): Boolean =
    this == Shape.Free || this == Shape.Cycle || this == Shape.Unknown

internal fun merge(shape: Shape): Shape =
    when (shape) {
        is Shape.Fields ->
            shape.copy(
                props = shape.props.mapValues { (_, prop) -> prop.copy(shape = merge(prop.shape)) }
            )
        is Shape.Many -> shape.copy(items = merge(shape.items))
        is Shape.AnyOf -> mergeVariants(shape.variants.map(::merge))
        else -> shape
    }

private fun mergeVariants(all: List<Shape>): Shape {
    val variants = all.filterNot { it == Shape.Cycle || it == Shape.Unknown }.ifEmpty { all }
    if (variants.isEmpty()) {
        return Shape.Unknown
    }
    if (variants.size == 1) {
        return variants.single()
    }
    if (variants.any { it == Shape.Free }) {
        return Shape.Free
    }
    val objects = variants.filterIsInstance<Shape.Fields>()
    if (objects.isNotEmpty()) {
        val names = objects.flatMap { it.props.keys }.distinct()
        val props = names.associateWith { name ->
            val have = objects.mapNotNull { it.props[name] }
            val requireds = have.map { it.required }
            val required =
                when {
                    have.size < objects.size -> if (requireds.all { it != null }) false else null
                    requireds.any { it == null } -> null
                    else -> requireds.all { it == true }
                }
            val nullables = have.map { it.nullable }
            val nullable = if (nullables.any { it == null }) null else nullables.any { it == true }
            Prop(mergeVariants(have.map { it.shape }), required, nullable)
        }
        return Shape.Fields(props, open = objects.any { it.open })
    }
    for (container in Container.entries) {
        val same = variants.filterIsInstance<Shape.Many>().filter { it.container == container }
        if (same.isNotEmpty()) {
            return Shape.Many(mergeVariants(same.map { it.items }), container)
        }
    }
    val leaves = variants.filterIsInstance<Shape.Leaf>()
    if (leaves.isNotEmpty()) {
        val values = leaves.map { it.values }
        return Shape.Leaf(
            if (values.all { it != null }) values.flatMap { it.orEmpty() }.toSet() else null
        )
    }
    return Shape.Unknown
}

internal fun compare(spec: Shape, sdk: Shape, side: Side): List<Difference> =
    mutableListOf<Difference>().also { compareTree(merge(spec), merge(sdk), "", side, it) }

private fun compareTree(
    spec: Shape,
    sdk: Shape,
    path: String,
    side: Side,
    out: MutableList<Difference>,
) {
    if (spec.isOpaque() || sdk.isOpaque()) {
        return
    }
    when {
        spec is Shape.Fields && sdk is Shape.Fields -> {
            spec.props.forEach { (name, specProp) ->
                val here = join(path, name)
                val sdkProp = sdk.props[name]
                if (sdkProp == null) {
                    out += Difference(side, Kind.MISSING, here)
                } else {
                    compareFlags(specProp, sdkProp, here, side, out)
                    compareTree(specProp.shape, sdkProp.shape, here, side, out)
                }
            }
            if (!spec.open) {
                sdk.props.keys
                    .filterNot { it in spec.props }
                    .forEach { out += Difference(side, Kind.EXTRA, join(path, it)) }
            }
        }
        spec is Shape.Many && sdk is Shape.Many && spec.container == sdk.container ->
            compareTree(spec.items, sdk.items, path + spec.container.suffix, side, out)
        spec is Shape.Leaf && sdk is Shape.Leaf -> {
            if (spec.values != null && sdk.values != null && spec.values != sdk.values) {
                out += Difference(side, Kind.ENUM, path)
            }
        }
        path.isEmpty() && spec is Shape.Fields ->
            spec.props.keys.forEach { out += Difference(side, Kind.MISSING, it) }
        else -> out += Difference(side, Kind.MISSING, path)
    }
}

private fun compareFlags(
    spec: Prop,
    sdk: Prop,
    path: String,
    side: Side,
    out: MutableList<Difference>,
) {
    if (spec.required != null && sdk.required != null && spec.required != sdk.required) {
        out += Difference(side, Kind.REQUIRED, path)
    }
    if (spec.nullable != null && sdk.nullable != null && spec.nullable != sdk.nullable) {
        out += Difference(side, Kind.NULLABLE, path)
    }
}

private fun join(path: String, name: String): String = if (path.isEmpty()) name else "$path.$name"
