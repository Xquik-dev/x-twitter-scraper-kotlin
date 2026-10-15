// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.fields

import com.x_twitter_scraper.api.core.jsonMapper
import java.nio.file.Files
import java.nio.file.Path
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

internal class ApiFieldsTest {

    @TempDir lateinit var directory: Path

    private val spec =
        jsonMapper()
            .readTree(
                """
                {"servers": [{"url": "https://xquik.com/api/v1"}], "paths": {
                  "/x/tweets/{id}": {"get": {
                    "parameters": [
                      {"name": "id", "in": "path", "required": true, "schema": {"type": "string"}},
                      {"name": "fields", "in": "query", "schema": {"type": "string"}}
                    ],
                    "responses": {"200": {"content": {"application/json": {"schema": {
                      "type": "object", "required": ["id"], "properties": {
                        "id": {"type": "string"},
                        "status": {"type": "string", "enum": ["live", "deleted"]},
                        "retweetedAt": {"type": "string"}
                      }}}}}}
                  }},
                  "/x/tweets/search": {"get": {
                    "parameters": [{"name": "q", "in": "query", "required": true, "schema": {"type": "string"}}],
                    "responses": {"200": {"content": {"application/json": {"schema": {"type": "object", "properties": {"count": {"type": "integer"}}}}}}}
                  }}
                }}
                """
            )

    private val retrieve =
        SdkOperation(
            "x().tweets().retrieve",
            "GET",
            listOf("x", "tweets", "zz-sample-a"),
            Shape.Fields(
                mapOf(
                    "fields" to Prop(Shape.Leaf(), false, null),
                    "minFaves" to Prop(Shape.Leaf(), false, null),
                )
            ),
            null,
            Shape.Fields(
                mapOf(
                    "id" to Prop(Shape.Leaf(), true, false),
                    "status" to Prop(Shape.Leaf(setOf("live")), false, null),
                )
            ),
        )

    private val search =
        SdkOperation(
            "x().tweets().search",
            "GET",
            listOf("x", "tweets", "search"),
            Shape.Fields(mapOf("q" to Prop(Shape.Leaf(), true, null))),
            null,
            Shape.Fields(mapOf("count" to Prop(Shape.Leaf(), false, null))),
        )

    private val redirect =
        SdkOperation(
            "credits().redirectTopupCheckout",
            "GET",
            listOf("credits", "topup", "redirect"),
            Shape.Fields(emptyMap()),
            null,
            null,
        )

    private fun run(accepted: String?, vararg operations: SdkOperation): Report {
        val file = directory.resolve("accepted-field-differences.json").toFile()
        if (accepted != null) {
            file.writeText(accepted)
        }
        return ApiFields(spec, file) { operations.toList() }.run()
    }

    @Test
    fun listsEachDifferenceWithItsKeyAndTheNextStep() {
        val report = run("[]", retrieve, search, redirect)

        assertThat(report.passed).isFalse()
        assertThat(report.lines)
            .containsExactly(
                "GET /api/v1/x/tweets/{id} request extra minFaves",
                "GET /api/v1/x/tweets/{id} response enum status",
                "GET /api/v1/x/tweets/{id} response missing retweetedAt",
                "Fix each field above in the SDK, or list it in accepted-field-differences.json with a reason.",
            )
    }

    @Test
    fun passesWhenEveryDifferenceIsAccepted() {
        val report =
            run(
                """
                [
                  {"reason": "Deprecated alias of minLikes.", "differences": ["GET /api/v1/x/tweets/{id} request extra minFaves"]},
                  {"reason": "The SDK keeps the old values.", "differences": [
                    "GET /api/v1/x/tweets/{id} response enum status",
                    "GET /api/v1/x/tweets/{id} response missing retweetedAt"
                  ]}
                ]
                """,
                retrieve,
                search,
            )

        assertThat(report)
            .isEqualTo(
                Report(
                    true,
                    listOf(
                        "The SDK's fields match the API for 2 operations, and accepted-field-differences.json lists 3 accepted differences."
                    ),
                )
            )
    }

    @Test
    fun passesWithAnEmptyAcceptedList() {
        assertThat(run("[]", search))
            .isEqualTo(Report(true, listOf("The SDK's fields match the API for 1 operation.")))
    }

    @Test
    fun failsOnAnAcceptedDifferenceTheCheckNoLongerFinds() {
        val report =
            run(
                """[{"reason": "Old alias.", "differences": ["GET /api/v1/x/tweets/search request extra near"]}]""",
                search,
            )

        assertThat(report)
            .isEqualTo(
                Report(
                    false,
                    listOf(
                        "accepted-field-differences.json lists GET /api/v1/x/tweets/search request extra near, which the check no longer finds. Remove it.",
                        "Update accepted-field-differences.json as the lines above say, then rerun.",
                    ),
                )
            )
    }

    @ParameterizedTest
    @MethodSource("invalidAcceptedFiles")
    fun failsOnAnInvalidAcceptedFile(contents: String?, problems: List<String>) {
        val report = run(contents, search)

        assertThat(report)
            .isEqualTo(
                Report(
                    false,
                    problems.map { "accepted-field-differences.json is not valid. $it" } +
                        "Fix accepted-field-differences.json as the lines above say, then rerun.",
                )
            )
    }

    @Test
    fun failsWhenAnSdkOperationMatchesTwoSpecOperationsEqually() {
        val ambiguous =
            jsonMapper()
                .readTree(
                    """
                    {"paths": {"/api/v1/a/{x}": {"get": {"responses": {}}}, "/api/v1/a/{y}": {"get": {"responses": {}}}}}
                    """
                )
        val file = directory.resolve("accepted.json").toFile().apply { writeText("[]") }
        val operation =
            SdkOperation("a().get", "GET", listOf("a", "zz"), Shape.Fields(emptyMap()), null, null)

        val error = runCatching {
            ApiFields(ambiguous, file) { listOf(operation) }.run()
        }.exceptionOrNull()

        assertThat(error).isInstanceOf(IllegalStateException::class.java)
    }

    @Test
    fun mainWritesTheReportForTheWrapperScript() {
        val specFile = directory.resolve("spec.json").toFile().apply { writeText(spec.toString()) }
        val accepted = directory.resolve("accepted.json").toFile().apply { writeText("{}") }
        val result = directory.resolve("result.txt").toFile()

        main(arrayOf(specFile.path, accepted.path, result.path))

        assertThat(Files.readAllLines(result.toPath()))
            .containsExactly(
                "fail",
                "accepted.json is not valid. It must be a JSON array of entries.",
                "Fix accepted.json as the lines above say, then rerun.",
            )
    }

    companion object {

        @JvmStatic
        fun invalidAcceptedFiles(): List<Array<Any?>> =
            listOf(
                arrayOf(null, listOf("The file does not exist.")),
                arrayOf("[", listOf("It does not parse as JSON.")),
                arrayOf("", listOf("It is empty.")),
                arrayOf("{}", listOf("It must be a JSON array of entries.")),
                arrayOf("[1]", listOf("Entry 1 must be an object with a reason and differences.")),
                arrayOf(
                    """[{"reason": " ", "differences": [], "note": 1}]""",
                    listOf(
                        "Entry 1 has the unknown key note.",
                        "Entry 1 needs a non-empty reason.",
                        "Entry 1 needs at least 1 difference.",
                    ),
                ),
                arrayOf(
                    """[{"reason": "Why.", "differences": ["GET /x response odd id"]}]""",
                    listOf(
                        "Entry 1 lists \"GET /x response odd id\", but each difference needs a method, a path, request or response, a kind and a field."
                    ),
                ),
                arrayOf(
                    """[{"reason": "Why.", "differences": ["GET /x response missing id"]}, {"reason": "Again.", "differences": ["GET /x response missing id"]}]""",
                    listOf(
                        "It lists GET /x response missing id 2 times. List each difference once."
                    ),
                ),
            )
    }
}
