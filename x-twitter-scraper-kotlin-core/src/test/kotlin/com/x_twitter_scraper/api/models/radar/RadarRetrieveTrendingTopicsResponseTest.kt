// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.radar

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RadarRetrieveTrendingTopicsResponseTest {

    @Test
    fun create() {
        val radarRetrieveTrendingTopicsResponse =
            RadarRetrieveTrendingTopicsResponse.builder()
                .hasMore(true)
                .addItem(
                    RadarItem.builder()
                        .id("id")
                        .category(RadarItem.Category.GENERAL)
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .language("language")
                        .metadata(RadarItem.Metadata.builder().build())
                        .publishedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .region("region")
                        .score(0.0)
                        .source(RadarItem.Source.GITHUB)
                        .sourceId("sourceId")
                        .title("title")
                        .build()
                )
                .nextCursor("nextCursor")
                .build()

        assertThat(radarRetrieveTrendingTopicsResponse.hasMore()).isEqualTo(true)
        assertThat(radarRetrieveTrendingTopicsResponse.items())
            .containsExactly(
                RadarItem.builder()
                    .id("id")
                    .category(RadarItem.Category.GENERAL)
                    .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .language("language")
                    .metadata(RadarItem.Metadata.builder().build())
                    .publishedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .region("region")
                    .score(0.0)
                    .source(RadarItem.Source.GITHUB)
                    .sourceId("sourceId")
                    .title("title")
                    .build()
            )
        assertThat(radarRetrieveTrendingTopicsResponse.nextCursor()).isEqualTo("nextCursor")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val radarRetrieveTrendingTopicsResponse =
            RadarRetrieveTrendingTopicsResponse.builder()
                .hasMore(true)
                .addItem(
                    RadarItem.builder()
                        .id("id")
                        .category(RadarItem.Category.GENERAL)
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .language("language")
                        .metadata(RadarItem.Metadata.builder().build())
                        .publishedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .region("region")
                        .score(0.0)
                        .source(RadarItem.Source.GITHUB)
                        .sourceId("sourceId")
                        .title("title")
                        .build()
                )
                .nextCursor("nextCursor")
                .build()

        val roundtrippedRadarRetrieveTrendingTopicsResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(radarRetrieveTrendingTopicsResponse),
                jacksonTypeRef<RadarRetrieveTrendingTopicsResponse>(),
            )

        assertThat(roundtrippedRadarRetrieveTrendingTopicsResponse)
            .isEqualTo(radarRetrieveTrendingTopicsResponse)
    }
}
