// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async

import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClientAsync
import com.x_twitter_scraper.api.models.EventType
import com.x_twitter_scraper.api.models.webhooks.WebhookCreateParams
import com.x_twitter_scraper.api.models.webhooks.WebhookUpdateParams
import org.junit.jupiter.api.Test

internal class WebhookServiceAsyncTest {

    @Test
    suspend fun create() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val webhookServiceAsync = client.webhooks()

        val webhook =
            webhookServiceAsync.create(
                WebhookCreateParams.builder()
                    .addEventType(EventType.TWEET_NEW)
                    .addEventType(EventType.TWEET_REPLY)
                    .url("https://example.com/webhook")
                    .build()
            )

        kotlin.test.assertNotNull(webhook)
    }

    @Test
    suspend fun update() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val webhookServiceAsync = client.webhooks()

        val webhook =
            webhookServiceAsync.update(
                WebhookUpdateParams.builder()
                    .id("id")
                    .addEventType(EventType.TWEET_NEW)
                    .isActive(true)
                    .url("https://example.com/webhook")
                    .build()
            )

        kotlin.test.assertNotNull(webhook)
    }

    @Test
    suspend fun list() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val webhookServiceAsync = client.webhooks()

        val webhooks = webhookServiceAsync.list()

        kotlin.test.assertNotNull(webhooks)
    }

    @Test
    suspend fun deactivate() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val webhookServiceAsync = client.webhooks()

        val response = webhookServiceAsync.deactivate("id")

        kotlin.test.assertNotNull(response)
    }

    @Test
    suspend fun listDeliveries() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val webhookServiceAsync = client.webhooks()

        val response = webhookServiceAsync.listDeliveries("id")

        kotlin.test.assertNotNull(response)
    }

    @Test
    suspend fun resume() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val webhookServiceAsync = client.webhooks()

        val response = webhookServiceAsync.resume("id")

        kotlin.test.assertNotNull(response)
    }

    @Test
    suspend fun test() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val webhookServiceAsync = client.webhooks()

        val response = webhookServiceAsync.test("id")

        kotlin.test.assertNotNull(response)
    }
}
