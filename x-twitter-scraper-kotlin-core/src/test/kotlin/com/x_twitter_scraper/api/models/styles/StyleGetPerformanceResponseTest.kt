// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.styles

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.JsonMissing
import com.x_twitter_scraper.api.core.jsonMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class StyleGetPerformanceResponseTest {

    @Test
    fun create() {
        val styleGetPerformanceResponse =
            StyleGetPerformanceResponse.builder()
                .tweetCount(0L)
                .addTweet(
                    StyleGetPerformanceResponse.Tweet.builder()
                        .id("id")
                        .text("text")
                        .bookmarkCount(42L)
                        .createdAt("createdAt")
                        .likeCount(0L)
                        .quoteCount(3L)
                        .replyCount(0L)
                        .retweetCount(0L)
                        .viewCount(0L)
                        .build()
                )
                .xUsername("xUsername")
                .build()

        assertThat(styleGetPerformanceResponse.tweetCount()).isEqualTo(0L)
        assertThat(styleGetPerformanceResponse.tweets())
            .containsExactly(
                StyleGetPerformanceResponse.Tweet.builder()
                    .id("id")
                    .text("text")
                    .bookmarkCount(42L)
                    .createdAt("createdAt")
                    .likeCount(0L)
                    .quoteCount(3L)
                    .replyCount(0L)
                    .retweetCount(0L)
                    .viewCount(0L)
                    .build()
            )
        assertThat(styleGetPerformanceResponse.xUsername()).isEqualTo("xUsername")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val styleGetPerformanceResponse =
            StyleGetPerformanceResponse.builder()
                .tweetCount(0L)
                .addTweet(
                    StyleGetPerformanceResponse.Tweet.builder()
                        .id("id")
                        .text("text")
                        .bookmarkCount(42L)
                        .createdAt("createdAt")
                        .likeCount(0L)
                        .quoteCount(3L)
                        .replyCount(0L)
                        .retweetCount(0L)
                        .viewCount(0L)
                        .build()
                )
                .xUsername("xUsername")
                .build()

        val roundtrippedStyleGetPerformanceResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(styleGetPerformanceResponse),
                jacksonTypeRef<StyleGetPerformanceResponse>(),
            )

        assertThat(roundtrippedStyleGetPerformanceResponse).isEqualTo(styleGetPerformanceResponse)
    }

    @Test
    fun readsAdditionalFields() {
        val jsonMapper = jsonMapper()

        val tweet0 =
            jsonMapper.readValue(
                """{"bookmarkCount":1,"quoteCount":1}""",
                jacksonTypeRef<StyleGetPerformanceResponse.Tweet>(),
            )
        assertThat(tweet0.bookmarkCount()).isNotNull()
        assertThat(tweet0.quoteCount()).isNotNull()
        assertThat(jsonMapper.readTree(jsonMapper.writeValueAsString(tweet0)))
            .isEqualTo(jsonMapper.readTree("""{"bookmarkCount":1,"quoteCount":1}"""))
    }

    @Test
    fun keepsAdditionalFieldsOptional() {
        val tweet0 =
            StyleGetPerformanceResponse.Tweet.builder()
                .id(JsonMissing.of())
                .text(JsonMissing.of())
                .build()
        assertThat(tweet0.bookmarkCount()).isNull()
        assertThat(tweet0.quoteCount()).isNull()
    }
}
