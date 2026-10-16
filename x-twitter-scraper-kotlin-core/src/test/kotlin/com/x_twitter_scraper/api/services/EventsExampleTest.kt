// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.services

import com.github.tomakehurst.wiremock.client.WireMock.equalTo
import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor
import com.github.tomakehurst.wiremock.client.WireMock.ok
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo
import com.github.tomakehurst.wiremock.client.WireMock.verify
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo
import com.github.tomakehurst.wiremock.junit5.WireMockTest
import com.x_twitter_scraper.api.client.XTwitterScraperClient
import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClient
import com.x_twitter_scraper.api.models.EventType
import com.x_twitter_scraper.api.models.events.Event
import com.x_twitter_scraper.api.models.events.EventStreamParams
import java.time.OffsetDateTime
import kotlinx.coroutines.flow.toList
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.ResourceLock

@WireMockTest
@ResourceLock("https://github.com/wiremock/wiremock/issues/169")
internal class EventsExampleTest {

    private lateinit var client: XTwitterScraperClient

    @BeforeEach
    fun beforeEach(wmRuntimeInfo: WireMockRuntimeInfo) {
        client =
            XTwitterScraperOkHttpClient.builder()
                .baseUrl(wmRuntimeInfo.httpBaseUrl)
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .responseValidation(true)
                .build()
    }

    @Test
    suspend fun stream() {
        stubFor(
            get(urlPathEqualTo("/events/stream"))
                .willReturn(
                    ok(ExampleResponses.read("examples/events/stream.txt"))
                        .withHeader("Content-Type", "text/event-stream")
                )
        )
        val params = EventStreamParams.builder().lastEventId("41").build()

        val streams =
            listOf(
                client.events().stream(params).use { it.asSequence().toList() },
                client.async().events().stream(params).toList(),
            )

        verify(
            2,
            getRequestedFor(urlPathEqualTo("/events/stream"))
                .withHeader("Last-Event-ID", equalTo("41"))
                .withHeader("Accept", equalTo("text/event-stream")),
        )
        streams.forEach { events ->
            assertThat(events).hasSize(2)
            assertThat(events[0].id()).isEqualTo("42")
            assertThat(events[0].type()).isEqualTo(EventType.TWEET_NEW)
            assertThat(events[0].monitorId()).isEqualTo("7")
            assertThat(events[0].monitorType()).isEqualTo(Event.MonitorType.ACCOUNT)
            assertThat(events[0].occurredAt())
                .isEqualTo(OffsetDateTime.parse("2025-01-15T12:00:00.000Z"))
            assertThat(events[1].id()).isEqualTo("43")
            assertThat(events[1].type()).isEqualTo(EventType.TWEET_REPLY)
            assertThat(events[1].monitorType()).isEqualTo(Event.MonitorType.KEYWORD)
        }
    }
}
