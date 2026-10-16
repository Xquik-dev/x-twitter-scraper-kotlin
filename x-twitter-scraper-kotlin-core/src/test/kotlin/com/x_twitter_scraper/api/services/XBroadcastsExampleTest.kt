// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.services

import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor
import com.github.tomakehurst.wiremock.client.WireMock.okJson
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo
import com.github.tomakehurst.wiremock.client.WireMock.verify
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo
import com.github.tomakehurst.wiremock.junit5.WireMockTest
import com.x_twitter_scraper.api.client.XTwitterScraperClient
import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClient
import com.x_twitter_scraper.api.models.x.broadcasts.BroadcastRetrieveParams
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.ResourceLock

@WireMockTest
@ResourceLock("https://github.com/wiremock/wiremock/issues/169")
internal class XBroadcastsExampleTest {

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
    suspend fun retrieve() {
        stubFor(
            get(urlPathEqualTo("/x/broadcasts/2102882545427714266"))
                .willReturn(okJson(ExampleResponses.read("examples/x/broadcasts/retrieve.json")))
        )
        val params = BroadcastRetrieveParams.builder().id("2102882545427714266").build()

        val responses =
            listOf(
                client.x().broadcasts().retrieve(params),
                client.async().x().broadcasts().retrieve(params),
            )

        verify(2, getRequestedFor(urlPathEqualTo("/x/broadcasts/2102882545427714266")))
        responses.forEach { response ->
            assertThat(response.broadcast().id()).isEqualTo("1nGeLMWbkpaKX")
            assertThat(response.broadcast().creator()!!.id()).isEqualTo("34743251")
            assertThat(response.broadcast().creator()!!.username()).isEqualTo("SpaceX")
            assertThat(response.broadcast().creator()!!.name()).isEqualTo("SpaceX")
            assertThat(response.broadcast().endedAt())
                .isEqualTo(OffsetDateTime.parse("2026-09-17T01:17:35.036Z"))
            assertThat(response.broadcast().height()).isEqualTo(2160L)
            assertThat(response.broadcast().startedAt())
                .isEqualTo(OffsetDateTime.parse("2026-09-17T00:54:56.506Z"))
            assertThat(response.broadcast().state()).isEqualTo("Ended")
            assertThat(response.broadcast().title()).isEqualTo("USSF-259 Mission")
            assertThat(response.broadcast().totalWatched()).isEqualTo(161758L)
            assertThat(response.broadcast().totalWatching()).isEqualTo(21639L)
            assertThat(response.broadcast().width()).isEqualTo(3840L)
        }
    }
}
