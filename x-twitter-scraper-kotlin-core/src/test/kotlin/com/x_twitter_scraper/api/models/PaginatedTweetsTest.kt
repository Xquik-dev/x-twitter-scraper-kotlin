// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PaginatedTweetsTest {

    @Test
    fun create() {
        val paginatedTweets =
            PaginatedTweets.builder()
                .hasNextPage(true)
                .nextCursor("next_cursor")
                .addTweet(
                    SearchTweet.builder()
                        .id("id")
                        .bookmarkCount(0L)
                        .likeCount(0L)
                        .quoteCount(0L)
                        .replyCount(0L)
                        .retweetCount(0L)
                        .text("text")
                        .viewCount(0L)
                        .build()
                )
                .filteredCount(0L)
                .readBackTo(OffsetDateTime.parse("2026-10-04T10:07:00.000Z"))
                .searchContext(
                    PaginatedTweets.SearchContext.builder()
                        .smartTag(
                            PaginatedTweets.SearchContext.SmartTag.builder()
                                .id("BTC")
                                .active(true)
                                .supportsOhlc(false)
                                .build()
                        )
                        .build()
                )
                .searchedQuery("openai gpt")
                .windowComplete(true)
                .build()

        assertThat(paginatedTweets.hasNextPage()).isEqualTo(true)
        assertThat(paginatedTweets.nextCursor()).isEqualTo("next_cursor")
        assertThat(paginatedTweets.tweets())
            .containsExactly(
                SearchTweet.builder()
                    .id("id")
                    .bookmarkCount(0L)
                    .likeCount(0L)
                    .quoteCount(0L)
                    .replyCount(0L)
                    .retweetCount(0L)
                    .text("text")
                    .viewCount(0L)
                    .build()
            )
        assertThat(paginatedTweets.filteredCount()).isEqualTo(0L)
        assertThat(paginatedTweets.readBackTo())
            .isEqualTo(OffsetDateTime.parse("2026-10-04T10:07:00.000Z"))
        assertThat(paginatedTweets.searchContext())
            .isEqualTo(
                PaginatedTweets.SearchContext.builder()
                    .smartTag(
                        PaginatedTweets.SearchContext.SmartTag.builder()
                            .id("BTC")
                            .active(true)
                            .supportsOhlc(false)
                            .build()
                    )
                    .build()
            )
        assertThat(paginatedTweets.searchedQuery()).isEqualTo("openai gpt")
        assertThat(paginatedTweets.windowComplete()).isEqualTo(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val paginatedTweets =
            PaginatedTweets.builder()
                .hasNextPage(true)
                .nextCursor("next_cursor")
                .addTweet(
                    SearchTweet.builder()
                        .id("id")
                        .bookmarkCount(0L)
                        .likeCount(0L)
                        .quoteCount(0L)
                        .replyCount(0L)
                        .retweetCount(0L)
                        .text("text")
                        .viewCount(0L)
                        .build()
                )
                .filteredCount(0L)
                .readBackTo(OffsetDateTime.parse("2026-10-04T10:07:00.000Z"))
                .searchContext(
                    PaginatedTweets.SearchContext.builder()
                        .smartTag(
                            PaginatedTweets.SearchContext.SmartTag.builder()
                                .id("BTC")
                                .active(true)
                                .supportsOhlc(false)
                                .build()
                        )
                        .build()
                )
                .searchedQuery("openai gpt")
                .windowComplete(true)
                .build()

        val roundtrippedPaginatedTweets =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(paginatedTweets),
                jacksonTypeRef<PaginatedTweets>(),
            )

        assertThat(roundtrippedPaginatedTweets).isEqualTo(paginatedTweets)
    }
}
