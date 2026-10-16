// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.radar

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RadarItemTest {

    @Test
    fun create() {
        val radarItem =
            RadarItem.builder()
                .id("id")
                .category(RadarItem.Category.GENERAL)
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .language("language")
                .metadata(
                    RadarItem.Metadata.builder()
                        .author("author")
                        .contentUrl("contentUrl")
                        .estimatedDownvotes(0L)
                        .estimatedUpvotes(0L)
                        .numberComments(0L)
                        .score(0L)
                        .selftext("selftext")
                        .sourceFormat(RadarItem.Metadata.SourceFormat.HTML)
                        .subreddit("subreddit")
                        .upvoteRatio(0.0)
                        .build()
                )
                .publishedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .region("region")
                .score(0.0)
                .source(RadarItem.Source.GITHUB)
                .sourceId("sourceId")
                .title("title")
                .description("description")
                .imageUrl("imageUrl")
                .url("url")
                .build()

        assertThat(radarItem.id()).isEqualTo("id")
        assertThat(radarItem.category()).isEqualTo(RadarItem.Category.GENERAL)
        assertThat(radarItem.createdAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(radarItem.language()).isEqualTo("language")
        assertThat(radarItem.metadata())
            .isEqualTo(
                RadarItem.Metadata.builder()
                    .author("author")
                    .contentUrl("contentUrl")
                    .estimatedDownvotes(0L)
                    .estimatedUpvotes(0L)
                    .numberComments(0L)
                    .score(0L)
                    .selftext("selftext")
                    .sourceFormat(RadarItem.Metadata.SourceFormat.HTML)
                    .subreddit("subreddit")
                    .upvoteRatio(0.0)
                    .build()
            )
        assertThat(radarItem.publishedAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(radarItem.region()).isEqualTo("region")
        assertThat(radarItem.score()).isEqualTo(0.0)
        assertThat(radarItem.source()).isEqualTo(RadarItem.Source.GITHUB)
        assertThat(radarItem.sourceId()).isEqualTo("sourceId")
        assertThat(radarItem.title()).isEqualTo("title")
        assertThat(radarItem.description()).isEqualTo("description")
        assertThat(radarItem.imageUrl()).isEqualTo("imageUrl")
        assertThat(radarItem.url()).isEqualTo("url")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val radarItem =
            RadarItem.builder()
                .id("id")
                .category(RadarItem.Category.GENERAL)
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .language("language")
                .metadata(
                    RadarItem.Metadata.builder()
                        .author("author")
                        .contentUrl("contentUrl")
                        .estimatedDownvotes(0L)
                        .estimatedUpvotes(0L)
                        .numberComments(0L)
                        .score(0L)
                        .selftext("selftext")
                        .sourceFormat(RadarItem.Metadata.SourceFormat.HTML)
                        .subreddit("subreddit")
                        .upvoteRatio(0.0)
                        .build()
                )
                .publishedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .region("region")
                .score(0.0)
                .source(RadarItem.Source.GITHUB)
                .sourceId("sourceId")
                .title("title")
                .description("description")
                .imageUrl("imageUrl")
                .url("url")
                .build()

        val roundtrippedRadarItem =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(radarItem),
                jacksonTypeRef<RadarItem>(),
            )

        assertThat(roundtrippedRadarItem).isEqualTo(radarItem)
    }
}
