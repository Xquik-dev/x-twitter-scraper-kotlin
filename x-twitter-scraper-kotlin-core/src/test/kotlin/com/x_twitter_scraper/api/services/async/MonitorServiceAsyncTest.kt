// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async

import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClientAsync
import com.x_twitter_scraper.api.models.EventType
import com.x_twitter_scraper.api.models.monitors.MonitorCreateParams
import com.x_twitter_scraper.api.models.monitors.MonitorUpdateParams
import org.junit.jupiter.api.Test

internal class MonitorServiceAsyncTest {

    @Test
    suspend fun create() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val monitorServiceAsync = client.monitors()

        val monitor =
            monitorServiceAsync.create(
                MonitorCreateParams.builder()
                    .addEventType(EventType.TWEET_NEW)
                    .addEventType(EventType.TWEET_REPLY)
                    .username("elonmusk")
                    .build()
            )

        kotlin.test.assertNotNull(monitor)
    }

    @Test
    suspend fun retrieve() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val monitorServiceAsync = client.monitors()

        val monitor = monitorServiceAsync.retrieve("id")

        kotlin.test.assertNotNull(monitor)
    }

    @Test
    suspend fun update() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val monitorServiceAsync = client.monitors()

        val monitor =
            monitorServiceAsync.update(
                MonitorUpdateParams.builder()
                    .id("id")
                    .addEventType(EventType.TWEET_NEW)
                    .isActive(true)
                    .build()
            )

        kotlin.test.assertNotNull(monitor)
    }

    @Test
    suspend fun list() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val monitorServiceAsync = client.monitors()

        val monitors = monitorServiceAsync.list()

        kotlin.test.assertNotNull(monitors)
    }

    @Test
    suspend fun deactivate() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val monitorServiceAsync = client.monitors()

        val response = monitorServiceAsync.deactivate("id")

        kotlin.test.assertNotNull(response)
    }
}
