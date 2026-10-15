// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.users

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import com.x_twitter_scraper.api.models.SearchTweet
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserRetrieveBatchTweetsResponseTest {

    @Test
    fun create() {
        val userRetrieveBatchTweetsResponse =
            UserRetrieveBatchTweetsResponse.builder()
                .nextCursor("")
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
                .addUnavailableId("1234567890")
                .addUnprocessedId("unprocessedId")
                .build()

        assertThat(userRetrieveBatchTweetsResponse.nextCursor()).isEqualTo("")
        assertThat(userRetrieveBatchTweetsResponse.tweets())
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
        assertThat(userRetrieveBatchTweetsResponse.unavailableIds()).containsExactly("1234567890")
        assertThat(userRetrieveBatchTweetsResponse.unprocessedIds())
            .containsExactly("unprocessedId")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val userRetrieveBatchTweetsResponse =
            UserRetrieveBatchTweetsResponse.builder()
                .nextCursor("")
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
                .addUnavailableId("1234567890")
                .addUnprocessedId("unprocessedId")
                .build()

        val roundtrippedUserRetrieveBatchTweetsResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(userRetrieveBatchTweetsResponse),
                jacksonTypeRef<UserRetrieveBatchTweetsResponse>(),
            )

        assertThat(roundtrippedUserRetrieveBatchTweetsResponse)
            .isEqualTo(userRetrieveBatchTweetsResponse)
    }
}
