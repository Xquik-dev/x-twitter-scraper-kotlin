// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.services

import com.github.tomakehurst.wiremock.client.WireMock.equalToJson
import com.github.tomakehurst.wiremock.client.WireMock.okJson
import com.github.tomakehurst.wiremock.client.WireMock.post
import com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo
import com.github.tomakehurst.wiremock.client.WireMock.verify
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo
import com.github.tomakehurst.wiremock.junit5.WireMockTest
import com.x_twitter_scraper.api.client.XTwitterScraperClient
import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClient
import com.x_twitter_scraper.api.models.webhooks.WebhookCreateTelegramLinkParams
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.ResourceLock

@WireMockTest
@ResourceLock("https://github.com/wiremock/wiremock/issues/169")
internal class WebhooksExampleTest {

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
    suspend fun createTelegramLink() {
        stubFor(
            post(urlPathEqualTo("/webhooks/telegram"))
                .willReturn(
                    okJson(ExampleResponses.read("examples/webhooks/createTelegramLink.json"))
                )
        )
        val params = WebhookCreateTelegramLinkParams.builder().build()

        val responses =
            listOf(
                client.webhooks().createTelegramLink(params),
                client.async().webhooks().createTelegramLink(params),
            )

        verify(
            2,
            postRequestedFor(urlPathEqualTo("/webhooks/telegram"))
                .withRequestBody(equalToJson("{}")),
        )
        responses.forEach { response ->
            assertThat(response.expiresAt())
                .isEqualTo(OffsetDateTime.parse("2025-01-15T12:15:00.000Z"))
            assertThat(response.url())
                .isEqualTo("https://t.me/XquikBot?start=Q2hhdExpbmtDb2RlRXhhbXBsZTAx")
        }
    }
}
