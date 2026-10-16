// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.services

import com.github.tomakehurst.wiremock.client.WireMock.delete
import com.github.tomakehurst.wiremock.client.WireMock.deleteRequestedFor
import com.github.tomakehurst.wiremock.client.WireMock.okJson
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo
import com.github.tomakehurst.wiremock.client.WireMock.verify
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo
import com.github.tomakehurst.wiremock.junit5.WireMockTest
import com.x_twitter_scraper.api.client.XTwitterScraperClient
import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClient
import com.x_twitter_scraper.api.core.JsonValue
import com.x_twitter_scraper.api.models.extractions.ExtractionCancelParams
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.ResourceLock

@WireMockTest
@ResourceLock("https://github.com/wiremock/wiremock/issues/169")
internal class ExtractionsExampleTest {

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
    suspend fun cancel() {
        stubFor(
            delete(urlPathEqualTo("/extractions/L3NjAbCdEfGh"))
                .willReturn(okJson(ExampleResponses.read("examples/extractions/cancel.json")))
        )
        val params = ExtractionCancelParams.builder().id("L3NjAbCdEfGh").build()

        val responses =
            listOf(client.extractions().cancel(params), client.async().extractions().cancel(params))

        verify(2, deleteRequestedFor(urlPathEqualTo("/extractions/L3NjAbCdEfGh")))
        responses.forEach { response ->
            assertThat(response.id()).isEqualTo("aB3dE6gH9jK2")
            assertThat(response._status()).isEqualTo(JsonValue.from("canceled"))
        }
    }
}
