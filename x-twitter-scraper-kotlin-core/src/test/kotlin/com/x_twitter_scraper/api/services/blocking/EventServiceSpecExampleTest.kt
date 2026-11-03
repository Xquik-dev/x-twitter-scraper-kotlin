// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.services.blocking

import com.github.tomakehurst.wiremock.client.WireMock.equalTo
import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor
import com.github.tomakehurst.wiremock.client.WireMock.okJson
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo
import com.github.tomakehurst.wiremock.client.WireMock.verify
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo
import com.github.tomakehurst.wiremock.junit5.WireMockTest
import com.x_twitter_scraper.api.client.XTwitterScraperClient
import com.x_twitter_scraper.api.client.XTwitterScraperClientAsync
import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClient
import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClientAsync
import com.x_twitter_scraper.api.models.EventType
import com.x_twitter_scraper.api.models.events.Event
import com.x_twitter_scraper.api.models.events.EventDetail
import com.x_twitter_scraper.api.models.events.EventListParams
import java.time.OffsetDateTime
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.ResourceLock

@WireMockTest
@ResourceLock("https://github.com/wiremock/wiremock/issues/169")
internal class EventServiceSpecExampleTest {

    private lateinit var client: XTwitterScraperClient
    private lateinit var asyncClient: XTwitterScraperClientAsync

    @BeforeEach
    fun beforeEach(wmRuntimeInfo: WireMockRuntimeInfo) {
        client =
            XTwitterScraperOkHttpClient.builder()
                .baseUrl(wmRuntimeInfo.httpBaseUrl)
                .apiKey("My API Key")
                .responseValidation(true)
                .build()
        asyncClient =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(wmRuntimeInfo.httpBaseUrl)
                .apiKey("My API Key")
                .responseValidation(true)
                .build()
    }

    @Test
    fun listFiltersByPostMonitorAndParsesPostEvents() {
        stubFor(get(urlPathEqualTo("/events")).willReturn(okJson(listPostEventsExample)))
        val params =
            EventListParams.builder()
                .postMonitorId("31")
                .eventType(EventType.POST_LIKES_CHANGED)
                .build()

        val response = client.events().list(params)
        val asyncResponse = runBlocking { asyncClient.events().list(params) }

        verify(
            2,
            getRequestedFor(urlPathEqualTo("/events"))
                .withQueryParam("postMonitorId", equalTo("31"))
                .withQueryParam("eventType", equalTo("post.likes.changed")),
        )
        assertThat(asyncResponse).isEqualTo(response)
        assertThat(response.hasMore()).isTrue()
        assertThat(response.nextCursor()).isEqualTo("9001")
        assertThat(response.events()).hasSize(2)
        val likes = response.events()[0]
        assertThat(likes.id()).isEqualTo("9002")
        assertThat(likes.type()).isEqualTo(EventType.POST_LIKES_CHANGED)
        assertThat(likes.monitorId()).isEqualTo("31")
        assertThat(likes.monitorType()).isEqualTo(Event.MonitorType.POST)
        assertThat(likes.monitorName()).isEqualTo("Launch post")
        assertThat(likes.postId()).isEqualTo("2102761519985332442")
        assertThat(likes.postUrl()).isEqualTo("https://x.com/NASA/status/2102761519985332442")
        assertThat(likes.authorId()).isEqualTo("11348282")
        assertThat(likes.authorUsername()).isEqualTo("NASA")
        assertThat(likes.occurredAt()).isEqualTo(OffsetDateTime.parse("2026-10-22T11:06:00.000Z"))
        assertThat(likes.data()._additionalProperties().getValue("metric").asString())
            .isEqualTo("likes")
        assertThat(likes.data()._additionalProperties().getValue("previous").asNumber()?.toLong())
            .isEqualTo(17096L)
        assertThat(likes.data()._additionalProperties().getValue("current").asNumber()?.toLong())
            .isEqualTo(17200L)
        assertThat(likes.data()._additionalProperties().getValue("change").asNumber()?.toLong())
            .isEqualTo(104L)
        val deleted = response.events()[1]
        assertThat(deleted.type()).isEqualTo(EventType.POST_DELETED)
        assertThat(deleted.monitorType()).isEqualTo(Event.MonitorType.POST)
        assertThat(deleted.monitorName()).isNull()
        assertThat(deleted.data()._additionalProperties().getValue("text").asString())
            .isEqualTo("We cut serving costs by 20%.")
    }

    @Test
    fun retrieveParsesAPostEditEvent() {
        stubFor(get(urlPathEqualTo("/events/9003")).willReturn(okJson(getPostEventExample)))

        val response = client.events().retrieve("9003")
        val asyncResponse = runBlocking { asyncClient.events().retrieve("9003") }

        verify(2, getRequestedFor(urlPathEqualTo("/events/9003")))
        assertThat(asyncResponse).isEqualTo(response)
        assertThat(response.id()).isEqualTo("9003")
        assertThat(response.type()).isEqualTo(EventType.POST_EDITED)
        assertThat(response.monitorType()).isEqualTo(EventDetail.MonitorType.POST)
        assertThat(response.postId()).isEqualTo("2102761519985332442")
        assertThat(response.postUrl()).isEqualTo("https://x.com/NASA/status/2102761519985332442")
        assertThat(response.authorId()).isEqualTo("11348282")
        assertThat(response.authorUsername()).isEqualTo("NASA")
        assertThat(response.data()._additionalProperties().getValue("previousText").asString())
            .isEqualTo("We cut serving costs by 20%.")
        assertThat(response.data()._additionalProperties().getValue("text").asString())
            .isEqualTo("We cut serving costs by 25%.")
        assertThat(response.data()._additionalProperties().getValue("versionId").asString())
            .isEqualTo("2082577277246973001")
    }

    companion object {

        private val listPostEventsExample =
            """
            {
              "events": [
                {
                  "id": "9002",
                  "type": "post.likes.changed",
                  "monitorId": "31",
                  "monitorType": "post",
                  "monitorName": "Launch post",
                  "postId": "2102761519985332442",
                  "postUrl": "https://x.com/NASA/status/2102761519985332442",
                  "authorId": "11348282",
                  "authorUsername": "NASA",
                  "occurredAt": "2026-10-22T11:06:00.000Z",
                  "data": {
                    "metric": "likes",
                    "previous": 17096,
                    "current": 17200,
                    "change": 104
                  }
                },
                {
                  "id": "9001",
                  "type": "post.deleted",
                  "monitorId": "31",
                  "monitorType": "post",
                  "postId": "2102761519985332442",
                  "postUrl": "https://x.com/NASA/status/2102761519985332442",
                  "authorId": "11348282",
                  "authorUsername": "NASA",
                  "occurredAt": "2026-10-22T11:05:00.000Z",
                  "data": {
                    "text": "We cut serving costs by 20%."
                  }
                }
              ],
              "hasMore": true,
              "nextCursor": "9001"
            }
            """
                .trimIndent()

        private val getPostEventExample =
            """
            {
              "id": "9003",
              "type": "post.edited",
              "monitorId": "31",
              "monitorType": "post",
              "postId": "2102761519985332442",
              "postUrl": "https://x.com/NASA/status/2102761519985332442",
              "authorId": "11348282",
              "authorUsername": "NASA",
              "occurredAt": "2026-10-22T11:07:00.000Z",
              "data": {
                "previousText": "We cut serving costs by 20%.",
                "text": "We cut serving costs by 25%.",
                "versionId": "2082577277246973001"
              }
            }
            """
                .trimIndent()
    }
}
