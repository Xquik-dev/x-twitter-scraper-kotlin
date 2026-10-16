// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.tweets

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TweetGetRetweeterIdsResponseTest {

    @Test
    fun create() {
        val tweetGetRetweeterIdsResponse =
            TweetGetRetweeterIdsResponse.builder()
                .hasNextPage(true)
                .addId("id")
                .nextCursor("next_cursor")
                .build()

        assertThat(tweetGetRetweeterIdsResponse.hasNextPage()).isEqualTo(true)
        assertThat(tweetGetRetweeterIdsResponse.ids()).containsExactly("id")
        assertThat(tweetGetRetweeterIdsResponse.nextCursor()).isEqualTo("next_cursor")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val tweetGetRetweeterIdsResponse =
            TweetGetRetweeterIdsResponse.builder()
                .hasNextPage(true)
                .addId("id")
                .nextCursor("next_cursor")
                .build()

        val roundtrippedTweetGetRetweeterIdsResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(tweetGetRetweeterIdsResponse),
                jacksonTypeRef<TweetGetRetweeterIdsResponse>(),
            )

        assertThat(roundtrippedTweetGetRetweeterIdsResponse).isEqualTo(tweetGetRetweeterIdsResponse)
    }
}
