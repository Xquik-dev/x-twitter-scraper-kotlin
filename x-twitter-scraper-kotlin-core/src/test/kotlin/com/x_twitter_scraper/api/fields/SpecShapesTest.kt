// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.fields

import com.x_twitter_scraper.api.core.jsonMapper
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

internal class SpecShapesTest {

    private fun operations(spec: String): List<SpecOperation> =
        SpecShapes(jsonMapper().readTree(spec)).operations()

    @Test
    fun readsQueryParamsWithTheirFlagsAndEnums() {
        val operation =
            operations(
                    """
                    {"servers": [{"url": "https://xquik.com/api/v1"}], "paths": {"/x/spaces/search": {"get": {
                      "parameters": [
                        {"name": "q", "in": "query", "required": true, "schema": {"type": "string"}},
                        {"name": "queryType", "in": "query", "schema": {"type": "string", "enum": ["Top", "Live"]}},
                        {"name": "id", "in": "path", "required": true, "schema": {"type": "string"}}
                      ],
                      "responses": {"200": {"description": "ok"}}
                    }}}}
                    """
                )
                .single()

        assertThat(operation.method).isEqualTo("GET")
        assertThat(operation.server).isEqualTo("/api/v1")
        assertThat(operation.path).isEqualTo("/x/spaces/search")
        assertThat(operation.query)
            .isEqualTo(
                Shape.Fields(
                    mapOf(
                        "q" to Prop(Shape.Leaf(), true, null),
                        "queryType" to Prop(Shape.Leaf(setOf("Top", "Live")), false, null),
                    )
                )
            )
        assertThat(operation.body(null)).isNull()
        assertThat(operation.response).isNull()
    }

    @Test
    fun countsARequiredListInAnAllOfPartWithoutProperties() {
        val response =
            responseOf(
                """
                {"allOf": [
                  {"type": "object", "properties": {"api_key": {"type": "string"}, "note": {"type": "string"}}},
                  {"required": ["api_key"]}
                ]}
                """
            )

        assertThat(response)
            .isEqualTo(
                Shape.Fields(
                    mapOf(
                        "api_key" to Prop(Shape.Leaf(), true, false),
                        "note" to Prop(Shape.Leaf(), false, false),
                    )
                )
            )
    }

    @Test
    fun intersectsTheItemsOfAnAllOfOfArrays() {
        val response =
            responseOf(
                """
                {"type": "object", "properties": {"tweets": {"allOf": [
                  {"type": "array", "items": {"type": "object", "properties": {"id": {"type": "string"}}}},
                  {"type": "array", "items": {"type": "object", "properties": {"skipped": {"type": "boolean"}}}}
                ]}}}
                """
            )

        assertThat(merge(response!!))
            .isEqualTo(
                Shape.Fields(
                    mapOf(
                        "tweets" to
                            Prop(
                                Shape.ListOf(
                                    Shape.Fields(
                                        mapOf(
                                            "id" to Prop(Shape.Leaf(), false, false),
                                            "skipped" to Prop(Shape.Leaf(), false, false),
                                        )
                                    )
                                ),
                                false,
                                false,
                            )
                    )
                )
            )
    }

    @Test
    fun mergesSeveralSuccessBodiesAndRequiresOnlyWhatEveryVariantRequires() {
        val operation =
            operations(
                    """
                    {"paths": {"/jobs/{id}": {"get": {"responses": {
                      "200": {"content": {"application/json": {"schema": {"type": "object", "required": ["id", "state"], "properties": {"id": {"type": "string"}, "state": {"type": "string", "enum": ["done"]}}}}}},
                      "202": {"content": {"application/json": {"schema": {"type": "object", "required": ["id"], "properties": {"id": {"type": "string"}, "state": {"type": "string", "enum": ["running"]}}}}}}
                    }}}}}
                    """
                )
                .single()

        assertThat(merge(operation.response!!))
            .isEqualTo(
                Shape.Fields(
                    mapOf(
                        "id" to Prop(Shape.Leaf(), true, false),
                        "state" to Prop(Shape.Leaf(setOf("done", "running")), false, false),
                    )
                )
            )
    }

    @Test
    fun readsTheJsonBodyBeforeTheMultipartForm() {
        val operation =
            operations(
                    """
                    {"paths": {"/support/tickets": {"post": {
                      "requestBody": {"content": {
                        "multipart/form-data": {"schema": {"anyOf": [
                          {"type": "object", "required": ["body"], "properties": {"body": {"type": "string"}}},
                          {"type": "object", "required": ["attachments"], "properties": {"attachments": {"type": "array", "items": {"type": "string"}}}}
                        ]}},
                        "application/json": {"schema": {"type": "object", "required": ["body"], "properties": {"body": {"type": "string"}}}}
                      }},
                      "responses": {"201": {"description": "created"}}
                    }}}}
                    """
                )
                .single()

        assertThat(operation.body(null))
            .isEqualTo(Shape.Fields(mapOf("body" to Prop(Shape.Leaf(), true, false))))
    }

    @Test
    fun stopsAtACycleAndKeepsNullableFlags() {
        val response =
            responseOf(
                """
                {"${'$'}ref": "#/components/schemas/Node"}
                """,
                """
                "Node": {"type": "object", "properties": {
                  "parent": {"anyOf": [{"${'$'}ref": "#/components/schemas/Node"}, {"type": "null"}]},
                  "label": {"type": ["string", "null"]}
                }}
                """,
            )

        assertThat(response)
            .isEqualTo(
                Shape.Fields(
                    mapOf(
                        "parent" to Prop(Shape.Cycle, false, true),
                        "label" to Prop(Shape.Leaf(), false, true),
                    )
                )
            )
    }

    @Test
    fun marksAnObjectWithAdditionalPropertiesAsOpen() {
        val response =
            responseOf(
                """
                {"type": "object", "additionalProperties": true, "properties": {"id": {"type": "string"}}}
                """
            )

        assertThat(response)
            .isEqualTo(Shape.Fields(mapOf("id" to Prop(Shape.Leaf(), false, false)), open = true))
    }

    @Test
    fun failsOnAReferenceToNothing() {
        assertThatThrownBy {
                responseOf("""{"${'$'}ref": "#/components/schemas/Missing"}""")
            }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("The API spec has a reference to nothing: #/components/schemas/Missing")
    }

    @Test
    fun readsReferencedParametersAndBodiesWithSchemaSiblings() {
        val operation =
            operations(
                    """
                    {"components": {
                      "schemas": {"Thing": {"type": "object", "required": ["id"], "properties": {"id": {"type": "string"}}}},
                      "parameters": {"Limit": {"name": "limit", "in": "query", "required": true, "schema": {"type": "integer"}}},
                      "requestBodies": {"Upload": {"content": {"multipart/form-data": {"schema": {"type": "object", "properties": {"file": {"type": "string"}}}}}}}
                    }, "paths": {"/things": {"get": {
                      "parameters": [{"${'$'}ref": "#/components/parameters/Limit"}],
                      "requestBody": {"${'$'}ref": "#/components/requestBodies/Upload"},
                      "responses": {"200": {"content": {"application/json": {"schema": {
                        "${'$'}ref": "#/components/schemas/Thing", "description": "Ignored",
                        "properties": {"extra": {"type": "string"}}
                      }}}}}
                    }}}}
                    """
                )
                .single()

        assertThat(operation.query.props)
            .containsExactlyEntriesOf(mapOf("limit" to Prop(Shape.Leaf(), true, null)))
        assertThat(operation.body("multipart/form-data"))
            .isEqualTo(Shape.Fields(mapOf("file" to Prop(Shape.Leaf(), false, false))))
        assertThat(operation.response)
            .isEqualTo(
                Shape.Fields(
                    mapOf(
                        "id" to Prop(Shape.Leaf(), true, false),
                        "extra" to Prop(Shape.Leaf(), false, false),
                    )
                )
            )
    }

    @Test
    fun readsNullVariantsConstantsAndNullableEnums() {
        val response =
            responseOf(
                """
                {"type": "object", "properties": {
                  "a": {"oneOf": [{"type": "string"}, {"type": "null"}]},
                  "b": {"const": "fixed"}, "c": {"enum": ["x", null]}
                }}
                """
            )
                as Shape.Fields

        assertThat(response.props.getValue("a")).isEqualTo(Prop(Shape.Leaf(), false, true))
        assertThat(response.props.getValue("b"))
            .isEqualTo(Prop(Shape.Leaf(setOf("fixed")), false, false))
        assertThat(response.props.getValue("c"))
            .isEqualTo(Prop(Shape.Leaf(setOf("x")), false, true))
    }

    private fun responseOf(schema: String, components: String = ""): Shape? =
        operations(
                """
                {"components": {"schemas": {$components}}, "paths": {"/r": {"get": {"responses": {
                  "200": {"content": {"application/json": {"schema": $schema}}}
                }}}}}
                """
            )
            .single()
            .response
}
