// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.models.draws

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DrawRunParamsTest {

    @Test
    fun create() {
        DrawRunParams.builder()
            .tweetUrl("https://x.com/elonmusk/status/1234567890")
            .backupCount(2L)
            .filterAccountAgeDays(30L)
            .filterLanguage("en")
            .filterMinFollowers(50L)
            .mustFollowUsername("elonmusk")
            .mustRetweet(true)
            .addRequiredHashtag("#giveaway")
            .addRequiredKeyword("entered")
            .addRequiredMention("@elonmusk")
            .uniqueAuthorsOnly(true)
            .winnerCount(3L)
            .build()
    }

    @Test
    fun body() {
        val params =
            DrawRunParams.builder()
                .tweetUrl("https://x.com/elonmusk/status/1234567890")
                .backupCount(2L)
                .filterAccountAgeDays(30L)
                .filterLanguage("en")
                .filterMinFollowers(50L)
                .mustFollowUsername("elonmusk")
                .mustRetweet(true)
                .addRequiredHashtag("#giveaway")
                .addRequiredKeyword("entered")
                .addRequiredMention("@elonmusk")
                .uniqueAuthorsOnly(true)
                .winnerCount(3L)
                .build()

        val body = params._body()

        assertThat(body.tweetUrl()).isEqualTo("https://x.com/elonmusk/status/1234567890")
        assertThat(body.backupCount()).isEqualTo(2L)
        assertThat(body.filterAccountAgeDays()).isEqualTo(30L)
        assertThat(body.filterLanguage()).isEqualTo("en")
        assertThat(body.filterMinFollowers()).isEqualTo(50L)
        assertThat(body.mustFollowUsername()).isEqualTo("elonmusk")
        assertThat(body.mustRetweet()).isEqualTo(true)
        assertThat(body.requiredHashtags()).containsExactly("#giveaway")
        assertThat(body.requiredKeywords()).containsExactly("entered")
        assertThat(body.requiredMentions()).containsExactly("@elonmusk")
        assertThat(body.uniqueAuthorsOnly()).isEqualTo(true)
        assertThat(body.winnerCount()).isEqualTo(3L)
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            DrawRunParams.builder().tweetUrl("https://x.com/elonmusk/status/1234567890").build()

        val body = params._body()

        assertThat(body.tweetUrl()).isEqualTo("https://x.com/elonmusk/status/1234567890")
    }
}
