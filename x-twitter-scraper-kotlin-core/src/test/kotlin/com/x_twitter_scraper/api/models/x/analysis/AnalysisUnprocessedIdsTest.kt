// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.analysis

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AnalysisUnprocessedIdsTest {

    private val page =
        """{"analysisSummary":{},"has_next_page":false,"next_cursor":"","results":[],"unanalyzed":[]"""

    private fun read(json: String): TweetAnalysisPage =
        jsonMapper().readValue(json, jacksonTypeRef<TweetAnalysisPage>())

    @Test
    fun anAnalysisPageNamesThePostsTheTimeRanOutFor() {
        val response = read("""$page,"unprocessed_ids":["2102882545427714266","draft-2"]}""")

        assertThat(response.unprocessedIds()).containsExactly("2102882545427714266", "draft-2")
    }

    @Test
    fun anAnalysisThatFinishedEveryPostHasNoUnprocessedIds() {
        assertThat(read("""$page,"unprocessed_ids":[]}""").unprocessedIds()).isEmpty()
        assertThat(read("$page}").unprocessedIds()).isNull()
    }

    @Test
    fun unprocessedIdsSurviveTheBuilderAndJson() {
        val response =
            read("$page}")
                .toBuilder()
                .unprocessedIds(listOf("2102882545427714266"))
                .addUnprocessedId("draft-2")
                .build()

        assertThat(response.unprocessedIds()).containsExactly("2102882545427714266", "draft-2")
        assertThat(response._unprocessedIds().asKnown())
            .isEqualTo(listOf("2102882545427714266", "draft-2"))
        assertThat(read(jsonMapper().writeValueAsString(response))).isEqualTo(response)
        assertThat(
                jsonMapper()
                    .readTree(jsonMapper().writeValueAsString(response))
                    .get("unprocessed_ids")
                    .map { it.asText() }
            )
            .containsExactly("2102882545427714266", "draft-2")
        assertThat(response.toString()).contains("unprocessedIds=[2102882545427714266, draft-2]")
    }
}
