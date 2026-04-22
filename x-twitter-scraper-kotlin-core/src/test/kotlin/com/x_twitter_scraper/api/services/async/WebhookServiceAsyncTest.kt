// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async

import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClientAsync
import com.x_twitter_scraper.api.models.EventType
import com.x_twitter_scraper.api.models.webhooks.WebhookCreateParams
import com.x_twitter_scraper.api.models.webhooks.WebhookUpdateParams
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

internal class WebhookServiceAsyncTest {

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun create() {
        val client = XTwitterScraperOkHttpClientAsync.builder().apiKey("My API Key").build()
        val webhookServiceAsync = client.webhooks()

        val webhook =
            webhookServiceAsync.create(
                WebhookCreateParams.builder()
                    .addEventType(EventType.TWEET_NEW)
                    .addEventType(EventType.TWEET_REPLY)
                    .url("https://example.com/webhook")
                    .build()
            )

        webhook.validate()
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun update() {
        val client = XTwitterScraperOkHttpClientAsync.builder().apiKey("My API Key").build()
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

        webhook.validate()
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun list() {
        val client = XTwitterScraperOkHttpClientAsync.builder().apiKey("My API Key").build()
        val webhookServiceAsync = client.webhooks()

        val webhooks = webhookServiceAsync.list()

        webhooks.validate()
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun deactivate() {
        val client = XTwitterScraperOkHttpClientAsync.builder().apiKey("My API Key").build()
        val webhookServiceAsync = client.webhooks()

        val response = webhookServiceAsync.deactivate("id")

        response.validate()
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun listDeliveries() {
        val client = XTwitterScraperOkHttpClientAsync.builder().apiKey("My API Key").build()
        val webhookServiceAsync = client.webhooks()

        val response = webhookServiceAsync.listDeliveries("id")

        response.validate()
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun test() {
        val client = XTwitterScraperOkHttpClientAsync.builder().apiKey("My API Key").build()
        val webhookServiceAsync = client.webhooks()

        val response = webhookServiceAsync.test("id")

        response.validate()
    }
}
