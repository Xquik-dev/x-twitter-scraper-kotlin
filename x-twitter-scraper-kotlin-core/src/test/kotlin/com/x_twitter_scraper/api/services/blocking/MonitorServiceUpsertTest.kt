// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.services.blocking

import com.github.tomakehurst.wiremock.client.WireMock.aResponse
import com.github.tomakehurst.wiremock.client.WireMock.equalToJson
import com.github.tomakehurst.wiremock.client.WireMock.put
import com.github.tomakehurst.wiremock.client.WireMock.putRequestedFor
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
import com.x_twitter_scraper.api.models.monitors.MonitorUpsertParams
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.ResourceLock

@WireMockTest
@ResourceLock("https://github.com/wiremock/wiremock/issues/169")
internal class MonitorServiceUpsertTest {

    private val path = "/monitors/username/elonmusk"
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
    fun upsertSendsTheEventTypesAndLabelAndSaysItCreated() {
        stubFor(put(urlPathEqualTo(path)).willReturn(monitor(status = 201, created = true)))
        val params =
            MonitorUpsertParams.builder()
                .addEventType(EventType.TWEET_NEW)
                .addEventType(EventType.TWEET_REPLY)
                .name("Launch watch")
                .build()

        val response = client.monitors().upsert("elonmusk", params)
        val asyncResponse = runBlocking { asyncClient.monitors().upsert("elonmusk", params) }
        val rawResponse =
            client.monitors().withRawResponse().upsert("elonmusk", params).use {
                assertThat(it.statusCode()).isEqualTo(201)
                it.parse()
            }
        val asyncRawResponse = runBlocking {
            asyncClient.monitors().withRawResponse().upsert("elonmusk", params)
        }
            .use { it.parse() }

        verify(
            4,
            putRequestedFor(urlPathEqualTo(path))
                .withRequestBody(
                    equalToJson(
                        """{"eventTypes":["tweet.new","tweet.reply"],"name":"Launch watch"}"""
                    )
                ),
        )
        assertThat(asyncResponse).isEqualTo(response)
        assertThat(rawResponse).isEqualTo(response)
        assertThat(asyncRawResponse).isEqualTo(response)
        assertThat(response.created()).isTrue()
        assertThat(response.id()).isEqualTo("42")
        assertThat(response.username()).isEqualTo("elonmusk")
        assertThat(response.name()).isEqualTo("Launch watch")
    }

    @Test
    fun upsertSendsANullNameToRemoveTheLabelAndSaysItUpdated() {
        stubFor(put(urlPathEqualTo(path)).willReturn(monitor(status = 200, created = false)))

        val params =
            MonitorUpsertParams.builder().addEventType(EventType.TWEET_NEW).name(null).build()

        val response = client.monitors().upsert("elonmusk", params)

        verify(
            putRequestedFor(urlPathEqualTo(path))
                .withRequestBody(equalToJson("""{"eventTypes":["tweet.new"],"name":null}"""))
        )
        assertThat(response.created()).isFalse()
    }

    @Test
    fun upsertRequiresTheUsername() {
        val params = MonitorUpsertParams.builder().addEventType(EventType.TWEET_NEW).build()

        assertThatThrownBy { client.monitors().upsert(params) }
            .isInstanceOf(IllegalStateException::class.java)
        assertThatThrownBy { runBlocking { asyncClient.monitors().upsert(params) } }
            .isInstanceOf(IllegalStateException::class.java)
    }

    private fun monitor(status: Int, created: Boolean) =
        aResponse()
            .withStatus(status)
            .withHeader("Content-Type", "application/json")
            .withBody(
                """
                {
                  "created": $created,
                  "id": "42",
                  "username": "elonmusk",
                  "xUserId": "1234567890",
                  "name": "Launch watch",
                  "eventTypes": ["tweet.new", "tweet.reply"],
                  "isActive": true,
                  "createdAt": "2025-01-15T12:00:00Z",
                  "nextBillingAt": "2025-01-15T13:00:00Z"
                }
                """
            )
}
