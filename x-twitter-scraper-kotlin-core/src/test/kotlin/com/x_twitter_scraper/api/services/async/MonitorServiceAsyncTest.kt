// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async

import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClientAsync
import com.x_twitter_scraper.api.models.monitors.MonitorCreateParams
import com.x_twitter_scraper.api.models.monitors.MonitorUpdateParams
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

internal class MonitorServiceAsyncTest {

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun create() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val monitorServiceAsync = client.monitors()

        val monitor =
            monitorServiceAsync.create(
                MonitorCreateParams.builder()
                    .addEventType(MonitorCreateParams.EventType.TWEET_NEW)
                    .username("username")
                    .build()
            )

        monitor.validate()
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun retrieve() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val monitorServiceAsync = client.monitors()

        val monitor = monitorServiceAsync.retrieve("id")

        monitor.validate()
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun update() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val monitorServiceAsync = client.monitors()

        val monitor =
            monitorServiceAsync.update(
                MonitorUpdateParams.builder()
                    .id("id")
                    .addEventType(MonitorUpdateParams.EventType.TWEET_NEW)
                    .isActive(true)
                    .build()
            )

        monitor.validate()
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun list() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val monitorServiceAsync = client.monitors()

        val monitors = monitorServiceAsync.list()

        monitors.validate()
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun deactivate() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val monitorServiceAsync = client.monitors()

        val response = monitorServiceAsync.deactivate("id")

        response.validate()
    }
}
