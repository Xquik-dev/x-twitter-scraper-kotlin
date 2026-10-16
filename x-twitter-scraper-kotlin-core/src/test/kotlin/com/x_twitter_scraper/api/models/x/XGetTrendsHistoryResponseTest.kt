// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class XGetTrendsHistoryResponseTest {

    @Test
    fun create() {
        val xGetTrendsHistoryResponse =
            XGetTrendsHistoryResponse.builder()
                .count(0L)
                .fromTime(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .hasNextPage(true)
                .nextCursor("next_cursor")
                .addSnapshot(
                    XGetTrendsHistoryResponse.Snapshot.builder()
                        .capturedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .count(0L)
                        .addTrend(
                            XGetTrendsHistoryResponse.Snapshot.Trend.builder()
                                .name("name")
                                .description("description")
                                .promotedContent("promotedContent")
                                .query("query")
                                .rank(0L)
                                .tweetVolume(0L)
                                .url("url")
                                .build()
                        )
                        .build()
                )
                .toTime(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .woeid(0L)
                .location(
                    XGetTrendsHistoryResponse.Location.builder()
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
                .build()

        assertThat(xGetTrendsHistoryResponse.count()).isEqualTo(0L)
        assertThat(xGetTrendsHistoryResponse.fromTime())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(xGetTrendsHistoryResponse.hasNextPage()).isEqualTo(true)
        assertThat(xGetTrendsHistoryResponse.nextCursor()).isEqualTo("next_cursor")
        assertThat(xGetTrendsHistoryResponse.snapshots())
            .containsExactly(
                XGetTrendsHistoryResponse.Snapshot.builder()
                    .capturedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .count(0L)
                    .addTrend(
                        XGetTrendsHistoryResponse.Snapshot.Trend.builder()
                            .name("name")
                            .description("description")
                            .promotedContent("promotedContent")
                            .query("query")
                            .rank(0L)
                            .tweetVolume(0L)
                            .url("url")
                            .build()
                    )
                    .build()
            )
        assertThat(xGetTrendsHistoryResponse.toTime())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(xGetTrendsHistoryResponse.woeid()).isEqualTo(0L)
        assertThat(xGetTrendsHistoryResponse.location())
            .isEqualTo(
                XGetTrendsHistoryResponse.Location.builder()
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
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val xGetTrendsHistoryResponse =
            XGetTrendsHistoryResponse.builder()
                .count(0L)
                .fromTime(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .hasNextPage(true)
                .nextCursor("next_cursor")
                .addSnapshot(
                    XGetTrendsHistoryResponse.Snapshot.builder()
                        .capturedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .count(0L)
                        .addTrend(
                            XGetTrendsHistoryResponse.Snapshot.Trend.builder()
                                .name("name")
                                .description("description")
                                .promotedContent("promotedContent")
                                .query("query")
                                .rank(0L)
                                .tweetVolume(0L)
                                .url("url")
                                .build()
                        )
                        .build()
                )
                .toTime(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .woeid(0L)
                .location(
                    XGetTrendsHistoryResponse.Location.builder()
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
                .build()

        val roundtrippedXGetTrendsHistoryResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(xGetTrendsHistoryResponse),
                jacksonTypeRef<XGetTrendsHistoryResponse>(),
            )

        assertThat(roundtrippedXGetTrendsHistoryResponse).isEqualTo(xGetTrendsHistoryResponse)
    }
}
