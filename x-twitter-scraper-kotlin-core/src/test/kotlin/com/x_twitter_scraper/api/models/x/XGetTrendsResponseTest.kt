// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class XGetTrendsResponseTest {

    @Test
    fun create() {
        val xGetTrendsResponse =
            XGetTrendsResponse.builder()
                .count(0L)
                .addTrend(
                    XGetTrendsResponse.Trend.builder()
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
                .capturedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .location(
                    XGetTrendsResponse.Location.builder()
                        .country("country")
                        .countryCode("countryCode")
                        .name("name")
                        .parentWoeid(0L)
                        .placeType("placeType")
                        .placeTypeCode(0L)
                        .url("url")
                        .woeid(0L)
                        .build()
                )
                .requestedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        assertThat(xGetTrendsResponse.count()).isEqualTo(0L)
        assertThat(xGetTrendsResponse.trends())
            .containsExactly(
                XGetTrendsResponse.Trend.builder()
                    .name("name")
                    .description("description")
                    .promotedContent("promotedContent")
                    .query("query")
                    .rank(0L)
                    .tweetVolume(0L)
                    .url("url")
                    .build()
            )
        assertThat(xGetTrendsResponse.woeid()).isEqualTo(0L)
        assertThat(xGetTrendsResponse.capturedAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(xGetTrendsResponse.location())
            .isEqualTo(
                XGetTrendsResponse.Location.builder()
                    .country("country")
                    .countryCode("countryCode")
                    .name("name")
                    .parentWoeid(0L)
                    .placeType("placeType")
                    .placeTypeCode(0L)
                    .url("url")
                    .woeid(0L)
                    .build()
            )
        assertThat(xGetTrendsResponse.requestedAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val xGetTrendsResponse =
            XGetTrendsResponse.builder()
                .count(0L)
                .addTrend(
                    XGetTrendsResponse.Trend.builder()
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
                .capturedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .location(
                    XGetTrendsResponse.Location.builder()
                        .country("country")
                        .countryCode("countryCode")
                        .name("name")
                        .parentWoeid(0L)
                        .placeType("placeType")
                        .placeTypeCode(0L)
                        .url("url")
                        .woeid(0L)
                        .build()
                )
                .requestedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        val roundtrippedXGetTrendsResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(xGetTrendsResponse),
                jacksonTypeRef<XGetTrendsResponse>(),
            )

        assertThat(roundtrippedXGetTrendsResponse).isEqualTo(xGetTrendsResponse)
    }
}
