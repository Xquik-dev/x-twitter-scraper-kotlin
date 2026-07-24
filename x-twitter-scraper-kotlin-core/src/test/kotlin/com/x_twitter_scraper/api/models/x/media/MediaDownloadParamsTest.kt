// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.models.x.media

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MediaDownloadParamsTest {

    @Test
    fun create() {
        MediaDownloadParams.builder()
            .tweetId("1234567890")
            .addTweetId("1234567890")
            .addTweetId("1234567891")
            .tweetInput("https://x.com/elonmusk/status/1234567890")
            .tweetUrl("https://x.com/elonmusk/status/1234567890")
            .build()
    }

    @Test
    fun body() {
        val params =
            MediaDownloadParams.builder()
                .tweetId("1234567890")
                .addTweetId("1234567890")
                .addTweetId("1234567891")
                .tweetInput("https://x.com/elonmusk/status/1234567890")
                .tweetUrl("https://x.com/elonmusk/status/1234567890")
                .build()

        val body = params._body()

        assertThat(body.tweetId()).isEqualTo("1234567890")
        assertThat(body.tweetIds()).containsExactly("1234567890", "1234567891")
        assertThat(body.tweetInput()).isEqualTo("https://x.com/elonmusk/status/1234567890")
        assertThat(body.tweetUrl()).isEqualTo("https://x.com/elonmusk/status/1234567890")
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params = MediaDownloadParams.builder().build()

        val body = params._body()
    }
}
