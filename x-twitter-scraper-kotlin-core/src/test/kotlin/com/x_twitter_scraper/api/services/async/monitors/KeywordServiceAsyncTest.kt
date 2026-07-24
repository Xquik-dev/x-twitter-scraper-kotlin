// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async.monitors

import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClientAsync
import com.x_twitter_scraper.api.models.EventType
import com.x_twitter_scraper.api.models.monitors.keywords.KeywordCreateParams
import com.x_twitter_scraper.api.models.monitors.keywords.KeywordUpdateParams
import org.junit.jupiter.api.Test

internal class KeywordServiceAsyncTest {

    @Test
    suspend fun create() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val keywordServiceAsync = client.monitors().keywords()

        val keyword =
            keywordServiceAsync.create(
                KeywordCreateParams.builder()
                    .addEventType(EventType.TWEET_NEW)
                    .query("xquik OR \"x api\"")
                    .build()
            )

        kotlin.test.assertNotNull(keyword)
    }

    @Test
    suspend fun retrieve() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val keywordServiceAsync = client.monitors().keywords()

        val keyword = keywordServiceAsync.retrieve("id")

        kotlin.test.assertNotNull(keyword)
    }

    @Test
    suspend fun update() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val keywordServiceAsync = client.monitors().keywords()

        val keyword =
            keywordServiceAsync.update(
                KeywordUpdateParams.builder()
                    .id("id")
                    .addEventType(EventType.TWEET_NEW)
                    .isActive(true)
                    .build()
            )

        kotlin.test.assertNotNull(keyword)
    }

    @Test
    suspend fun list() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val keywordServiceAsync = client.monitors().keywords()

        val keywords = keywordServiceAsync.list()

        kotlin.test.assertNotNull(keywords)
    }

    @Test
    suspend fun deactivate() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val keywordServiceAsync = client.monitors().keywords()

        val response = keywordServiceAsync.deactivate("id")

        kotlin.test.assertNotNull(response)
    }
}
