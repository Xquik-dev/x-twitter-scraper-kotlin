// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.fields

import com.fasterxml.jackson.databind.JsonNode
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.jsonMapper
import java.io.File
import java.net.URI

internal data class Report(val passed: Boolean, val lines: List<String>)

internal class ApiFields(
    private val spec: JsonNode,
    private val accepted: File,
    private val sdk: () -> List<SdkOperation> = { SdkShapes().operations() },
) {

    fun run(): Report {
        val acceptedName = accepted.name
        val acceptedEntries =
            when (val parsed = readAccepted()) {
                is Accepted.Invalid ->
                    return Report(
                        false,
                        parsed.problems.map { "$acceptedName is not valid. $it" } +
                            "Fix $acceptedName as the lines above say, then rerun.",
                    )
                is Accepted.Valid -> parsed.differences
            }
        val specOperations = SpecShapes(spec).operations()
        val matched =
            sdk().mapNotNull { operation ->
                match(operation, specOperations)?.let { it to operation }
            }
        val found =
            matched
                .flatMap { (specOperation, sdkOperation) ->
                    differences(specOperation, sdkOperation).map { difference ->
                        listOf(
                                specOperation.method,
                                specOperation.server + specOperation.path,
                                difference.side.label,
                                difference.kind.label,
                                difference.path,
                            )
                            .joinToString(" ")
                    }
                }
                .toSortedSet()
        val acceptedKeys = acceptedEntries.map(::canonicalKey).toSet()
        val foundKeys = found.map(::canonicalKey).toSet()
        val unaccepted = found.filterNot { canonicalKey(it) in acceptedKeys }
        val stale =
            acceptedEntries
                .filterNot { canonicalKey(it) in foundKeys }
                .sorted()
                .map {
                    "$acceptedName lists $it, which the check no longer finds. Remove it."
                }
        if (unaccepted.isEmpty() && stale.isEmpty()) {
            val count = matched.size
            val acceptedClause =
                when (acceptedEntries.size) {
                    0 -> ""
                    1 -> ", and $acceptedName lists 1 accepted difference"
                    else -> ", and $acceptedName lists ${acceptedEntries.size} accepted differences"
                }
            val operations = if (count == 1) "1 operation" else "$count operations"
            return Report(
                true,
                listOf("The SDK's fields match the API for $operations$acceptedClause."),
            )
        }
        val nextStep =
            if (unaccepted.isNotEmpty()) {
                "Fix each field above in the SDK, or list it in $acceptedName with a reason."
            } else {
                "Update $acceptedName as the lines above say, then rerun."
            }
        return Report(false, unaccepted + stale + nextStep)
    }

    private fun match(
        operation: SdkOperation,
        specOperations: List<SpecOperation>,
    ): SpecOperation? {
        val candidates =
            specOperations
                .filter { it.method == operation.method }
                .mapNotNull { candidate ->
                    val segments = (candidate.server + candidate.path).trim('/').split('/')
                    val actualSegments = basePath + operation.pathSegments
                    if (segments.size != actualSegments.size) {
                        return@mapNotNull null
                    }
                    var literals = 0
                    for ((template, actual) in segments.zip(actualSegments)) {
                        when {
                            template.startsWith("{") && template.endsWith("}") -> {}
                            template == actual -> literals++
                            else -> return@mapNotNull null
                        }
                    }
                    candidate to literals
                }
        val best = candidates.maxOfOrNull { it.second } ?: return null
        val winners = candidates.filter { it.second == best }
        check(winners.size == 1) {
            "${operation.label} matches ${winners.joinToString(" and ") { "${it.first.method} ${it.first.path}" }} in the API spec."
        }
        return winners.single().first
    }

    private fun differences(spec: SpecOperation, sdk: SdkOperation): List<Difference> =
        compare(spec.query, sdk.query, Side.REQUEST) +
            body(spec.body(sdk.contentType), sdk.body) +
            response(spec.response, sdk.response)

    private fun body(spec: Shape?, sdk: Shape?): List<Difference> =
        when {
            spec != null && sdk != null -> compare(spec, sdk, Side.REQUEST)
            spec != null -> topLevel(spec, Side.REQUEST, Kind.MISSING)
            sdk != null -> topLevel(sdk, Side.REQUEST, Kind.EXTRA)
            else -> emptyList()
        }

    private fun response(spec: Shape?, sdk: Shape?): List<Difference> =
        when {
            spec != null && sdk != null -> compare(spec, sdk, Side.RESPONSE)
            spec != null -> topLevel(spec, Side.RESPONSE, Kind.MISSING)
            else -> emptyList()
        }

    private fun topLevel(shape: Shape, side: Side, kind: Kind): List<Difference> =
        when (val merged = merge(shape)) {
            is Shape.Fields -> merged.props.keys.map { Difference(side, kind, it) }
            else -> emptyList()
        }

    private sealed interface Accepted {
        data class Valid(val differences: Set<String>) : Accepted

        data class Invalid(val problems: List<String>) : Accepted
    }

    private fun readAccepted(): Accepted {
        if (!accepted.isFile) {
            return Accepted.Invalid(listOf("The file does not exist."))
        }
        val root =
            try {
                jsonMapper().readTree(accepted)
            } catch (e: Exception) {
                return Accepted.Invalid(listOf("It does not parse as JSON."))
            }
        if (root == null || root.isMissingNode) {
            return Accepted.Invalid(listOf("It is empty."))
        }
        if (!root.isArray) {
            return Accepted.Invalid(listOf("It must be a JSON array of entries."))
        }
        val problems = mutableListOf<String>()
        val differences = mutableListOf<String>()
        root.forEachIndexed { index, entry ->
            val number = index + 1
            if (!entry.isObject) {
                problems += "Entry $number must be an object with a reason and differences."
                return@forEachIndexed
            }
            entry
                .fieldNames()
                .asSequence()
                .filterNot { it == "reason" || it == "differences" }
                .forEach {
                    problems += "Entry $number has the unknown key $it."
                }
            val reason = entry.get("reason")
            if (reason == null || !reason.isTextual || reason.asText().isBlank()) {
                problems += "Entry $number needs a non-empty reason."
            }
            val listed = entry.get("differences")
            if (listed == null || !listed.isArray || listed.isEmpty) {
                problems += "Entry $number needs at least 1 difference."
            } else {
                listed.forEach { difference ->
                    if (difference.isTextual && KEY.matches(difference.asText())) {
                        differences += difference.asText()
                    } else {
                        problems +=
                            "Entry $number lists $difference, but each difference needs a method, a path, request or response, a kind and a field."
                    }
                }
            }
        }
        if (problems.isNotEmpty()) {
            return Accepted.Invalid(problems)
        }
        val repeated =
            differences
                .groupBy(::canonicalKey)
                .values
                .filter { it.size > 1 }
                .sortedBy { it.last() }
                .map {
                    "It lists ${it.last()} ${it.size} times. List each difference once."
                }
        return if (repeated.isEmpty()) Accepted.Valid(differences.toSet())
        else Accepted.Invalid(repeated)
    }

    private fun canonicalKey(key: String): String = PARAMETER.replace(key, "{}")

    companion object {
        private val basePath =
            URI(ClientOptions.PRODUCTION_URL).path.trim('/').split('/').filter { it.isNotEmpty() }
        private val KEY =
            Regex("^[A-Z]+ /\\S* (request|response) (missing|extra|enum|required|nullable) \\S+$")
        private val PARAMETER = Regex("\\{[^/{}]+}")
    }
}

fun main(args: Array<String>) {
    require(args.size == 3) {
        "Usage: ApiFields <spec.json> <accepted-field-differences.json> <result file>"
    }
    val (specPath, acceptedPath, resultPath) = args
    val report =
        try {
            ApiFields(jsonMapper().readTree(File(specPath)), File(acceptedPath)).run()
        } catch (e: IllegalStateException) {
            Report(
                false,
                listOf(
                    "The API fields check could not read the SDK. ${e.message}",
                    "Keep the generated patterns in that code, or teach x-twitter-scraper-kotlin-core/src/test/kotlin/com/x_twitter_scraper/api/fields to read it.",
                ),
            )
        } catch (e: IllegalArgumentException) {
            Report(
                false,
                listOf(
                    "The API fields check could not read the API spec. ${e.message}",
                    "Fix the spec, or set XQUIK_OPENAPI_SPEC to a spec the check can read.",
                ),
            )
        }
    File(resultPath)
        .writeText(
            (listOf(if (report.passed) "pass" else "fail") + report.lines).joinToString("\n") + "\n"
        )
}
