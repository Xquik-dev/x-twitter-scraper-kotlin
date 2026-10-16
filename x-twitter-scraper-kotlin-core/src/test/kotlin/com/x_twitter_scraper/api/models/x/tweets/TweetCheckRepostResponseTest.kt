// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.tweets

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TweetCheckRepostResponseTest {

    @Test
    fun create() {
        val tweetCheckRepostResponse =
            TweetCheckRepostResponse.builder()
                .checkedPosts(0L)
                .complete(true)
                .isRetweeted(true)
                .retweetId("retweetId")
                .retweetedAt("retweetedAt")
                .tweetId("tweetId")
                .userId("userId")
                .build()

        assertThat(tweetCheckRepostResponse.checkedPosts()).isEqualTo(0L)
        assertThat(tweetCheckRepostResponse.complete()).isEqualTo(true)
        assertThat(tweetCheckRepostResponse.isRetweeted()).isEqualTo(true)
        assertThat(tweetCheckRepostResponse.retweetId()).isEqualTo("retweetId")
        assertThat(tweetCheckRepostResponse.retweetedAt()).isEqualTo("retweetedAt")
        assertThat(tweetCheckRepostResponse.tweetId()).isEqualTo("tweetId")
        assertThat(tweetCheckRepostResponse.userId()).isEqualTo("userId")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val tweetCheckRepostResponse =
            TweetCheckRepostResponse.builder()
                .checkedPosts(0L)
                .complete(true)
                .isRetweeted(true)
                .retweetId("retweetId")
                .retweetedAt("retweetedAt")
                .tweetId("tweetId")
                .userId("userId")
                .build()

        val roundtrippedTweetCheckRepostResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(tweetCheckRepostResponse),
                jacksonTypeRef<TweetCheckRepostResponse>(),
            )

        assertThat(roundtrippedTweetCheckRepostResponse).isEqualTo(tweetCheckRepostResponse)
    }
}
