// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.analysis

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AnalysisFiltersTest {

    @Test
    fun everyAnalysisReadSendsTopicAndImageShows() {
        val bodies =
            listOf(
                AnalysisBrandParams.builder().topic("rust").imageShows("charts").build()._body(),
                AnalysisClassifyParams.builder().topic("rust").imageShows("charts").build()._body(),
                AnalysisMarketSignalsParams.builder()
                    .topic("rust")
                    .imageShows("charts")
                    .build()
                    ._body(),
                AnalysisNewsParams.builder().topic("rust").imageShows("charts").build()._body(),
                AnalysisSentimentParams.builder()
                    .topic("rust")
                    .imageShows("charts")
                    .build()
                    ._body(),
                AnalysisViralScoreParams.builder()
                    .topic("rust")
                    .imageShows("charts")
                    .build()
                    ._body(),
            )

        for (body in bodies) {
            val json = jsonMapper().readTree(jsonMapper().writeValueAsString(body))
            assertThat(json.get("topic").asText()).isEqualTo("rust")
            assertThat(json.get("imageShows").asText()).isEqualTo("charts")
        }
    }

    @Test
    fun analysisParamsReadTopicAndImageShowsBack() {
        val params = AnalysisBrandParams.builder().topic("rust").imageShows("charts").build()

        assertThat(params.topic()).isEqualTo("rust")
        assertThat(params.imageShows()).isEqualTo("charts")
        assertThat(params._topic().asKnown()).isEqualTo("rust")
        assertThat(params._imageShows().asKnown()).isEqualTo("charts")
        assertThat(params.toBuilder().build()).isEqualTo(params)
    }

    @Test
    fun analysisPageReadsTheTopicAndImageFilters() {
        val json =
            """{"analysisSummary":{},"has_next_page":false,"next_cursor":"","results":[],"unanalyzed":[],"topicFilter":{"topic":"rust","question":"Is this post about rust?","cutoff":0.5,"checked":4,"kept":1,"unchecked":[]},"imageFilter":{"imageShows":"charts","question":"Does a picture attached to this post show a chart?","cutoff":0.5,"checked":4,"kept":2,"unchecked":[]}}"""

        val page = jsonMapper().readValue(json, jacksonTypeRef<TweetAnalysisPage>())

        assertThat(page.topicFilter()!!.kept()).isEqualTo(1L)
        assertThat(page.imageFilter()!!.kept()).isEqualTo(2L)
        assertThat(page._topicFilter().asKnown()!!.topic()).isEqualTo("rust")
        assertThat(page._imageFilter().asKnown()!!.imageShows()).isEqualTo("charts")
        assertThat(page.toBuilder().build()).isEqualTo(page)
        assertThat(
                jsonMapper()
                    .readValue(
                        jsonMapper().writeValueAsString(page),
                        jacksonTypeRef<TweetAnalysisPage>(),
                    )
            )
            .isEqualTo(page)
    }
}
