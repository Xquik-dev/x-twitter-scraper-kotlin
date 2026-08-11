// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async

import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClientAsync
import com.x_twitter_scraper.api.models.EventType
import com.x_twitter_scraper.api.models.events.EventListParams
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

internal class EventServiceAsyncTest {

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun retrieve() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val eventServiceAsync = client.events()

        val eventDetail = eventServiceAsync.retrieve("id")

        eventDetail.validate()
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun list() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val eventServiceAsync = client.events()

        val events =
            eventServiceAsync.list(
                EventListParams.builder()
                    .cursor("cursor")
                    .eventType(EventType.TWEET_NEW)
                    .keywordMonitorId("keywordMonitorId")
                    .limit(1L)
                    .monitorId("monitorId")
                    .build()
            )

        events.validate()
    }
}
