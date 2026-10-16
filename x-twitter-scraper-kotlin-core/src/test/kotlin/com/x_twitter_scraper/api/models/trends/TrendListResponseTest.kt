// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.trends

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TrendListResponseTest {

    @Test
    fun create() {
        val trendListResponse =
            TrendListResponse.builder()
                .total(0L)
                .addTrend(
                    TrendListResponse.Trend.builder()
                        .name("name")
                        .description("description")
                        .promotedContent("promotedContent")
                        .query("query")
                        .rank(0L)
                        .tweetVolume(0L)
                        .url("url")
                        .build()
                )
                .woeid(0L)
                .build()

        assertThat(trendListResponse.total()).isEqualTo(0L)
        assertThat(trendListResponse.trends())
            .containsExactly(
                TrendListResponse.Trend.builder()
                    .name("name")
                    .description("description")
                    .promotedContent("promotedContent")
                    .query("query")
                    .rank(0L)
                    .tweetVolume(0L)
                    .url("url")
                    .build()
            )
        assertThat(trendListResponse.woeid()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val trendListResponse =
            TrendListResponse.builder()
                .total(0L)
                .addTrend(
                    TrendListResponse.Trend.builder()
                        .name("name")
                        .description("description")
                        .promotedContent("promotedContent")
                        .query("query")
                        .rank(0L)
                        .tweetVolume(0L)
                        .url("url")
                        .build()
                )
                .woeid(0L)
                .build()

        val roundtrippedTrendListResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(trendListResponse),
                jacksonTypeRef<TrendListResponse>(),
            )

        assertThat(roundtrippedTrendListResponse).isEqualTo(trendListResponse)
    }
}
