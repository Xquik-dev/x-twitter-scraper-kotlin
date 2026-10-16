// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.services.async

import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClientAsync
import com.x_twitter_scraper.api.models.EventType
import com.x_twitter_scraper.api.models.events.EventListParams
import com.x_twitter_scraper.api.models.events.EventStreamParams
import kotlinx.coroutines.flow.toList
import org.junit.jupiter.api.Test

internal class EventServiceAsyncTest {

    @Test
    suspend fun retrieve() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val eventServiceAsync = client.events()

        val eventDetail = eventServiceAsync.retrieve("id")

        kotlin.test.assertNotNull(eventDetail)
    }

    @Test
    suspend fun list() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
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

        kotlin.test.assertNotNull(events)
    }

    @Test
    suspend fun stream() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val eventServiceAsync = client.events()

        val events =
            eventServiceAsync.stream(EventStreamParams.builder().lastEventId("42").build()).toList()

        kotlin.test.assertNotNull(events)
    }
}
