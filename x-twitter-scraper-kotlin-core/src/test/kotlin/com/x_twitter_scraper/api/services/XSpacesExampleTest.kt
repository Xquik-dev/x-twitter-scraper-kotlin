// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.services

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
import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClient
import com.x_twitter_scraper.api.models.x.spaces.SpaceRetrieveParams
import com.x_twitter_scraper.api.models.x.spaces.SpaceRetrieveReplayParams
import com.x_twitter_scraper.api.models.x.spaces.SpaceSearchParams
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.ResourceLock

@WireMockTest
@ResourceLock("https://github.com/wiremock/wiremock/issues/169")
internal class XSpacesExampleTest {

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
            get(urlPathEqualTo("/x/spaces/2102882545427714266"))
                .willReturn(okJson(ExampleResponses.read("examples/x/spaces/retrieve.json")))
        )
        val params = SpaceRetrieveParams.builder().id("2102882545427714266").build()

        val responses =
            listOf(
                client.x().spaces().retrieve(params),
                client.async().x().spaces().retrieve(params),
            )

        verify(2, getRequestedFor(urlPathEqualTo("/x/spaces/2102882545427714266")))
        responses.forEach { response ->
            assertThat(response.space().id()).isEqualTo("1OGwblLjnQMKB")
            assertThat(response.space().admins()).hasSize(1)
            assertThat(response.space().admins()[0].id()).isEqualTo("951329744804392960")
            assertThat(response.space().admins()[0].name()).isEqualTo("Solana")
            assertThat(response.space().admins()[0].username()).isEqualTo("solana")
            assertThat(response.space().listeners()).hasSize(0)
            assertThat(response.space().speakers()).hasSize(0)
            assertThat(response.space().creator()!!.id()).isEqualTo("951329744804392960")
            assertThat(response.space().creator()!!.username()).isEqualTo("solana")
            assertThat(response.space().creator()!!.name()).isEqualTo("Solana")
            assertThat(response.space().endedAt())
                .isEqualTo(OffsetDateTime.parse("2026-03-11T17:49:29.190Z"))
            assertThat(response.space().startedAt())
                .isEqualTo(OffsetDateTime.parse("2026-03-11T17:00:26.227Z"))
            assertThat(response.space().state()).isEqualTo("Ended")
            assertThat(response.space().title()).isEqualTo("Extending the Network to the Edge")
            assertThat(response.space().totalLiveListeners()).isEqualTo(841L)
            assertThat(response.space().totalReplayWatched()).isEqualTo(609L)
        }
    }

    @Test
    suspend fun retrieveReplay() {
        stubFor(
            get(urlPathEqualTo("/x/spaces/2102882545427714266/replay"))
                .willReturn(okJson(ExampleResponses.read("examples/x/spaces/retrieveReplay.json")))
        )
        val params = SpaceRetrieveReplayParams.builder().id("2102882545427714266").build()

        val responses =
            listOf(
                client.x().spaces().retrieveReplay(params),
                client.async().x().spaces().retrieveReplay(params),
            )

        verify(2, getRequestedFor(urlPathEqualTo("/x/spaces/2102882545427714266/replay")))
        responses.forEach { response ->
            assertThat(response.replay().url())
                .isEqualTo(
                    "https://prod-fastly-us-east-1.video.pscp.tv/Transcoding/v1/hls/example/audio-space/playlist.m3u8?type=replay"
                )
            assertThat(response.replay().status()).isEqualTo("LIVE_PUBLIC")
            assertThat(response.replay().streamType()).isEqualTo("HLS")
        }
    }

    @Test
    suspend fun search() {
        stubFor(
            get(urlPathEqualTo("/x/spaces/search"))
                .willReturn(okJson(ExampleResponses.read("examples/x/spaces/search.json")))
        )
        val params =
            SpaceSearchParams.builder()
                .q("from:solana")
                .pageSize(1L)
                .queryType(SpaceSearchParams.QueryType.LATEST)
                .build()

        val responses =
            listOf(client.x().spaces().search(params), client.async().x().spaces().search(params))

        verify(
            2,
            getRequestedFor(urlPathEqualTo("/x/spaces/search"))
                .withQueryParam("q", equalTo("from:solana"))
                .withQueryParam("pageSize", equalTo("1"))
                .withQueryParam("queryType", equalTo("Latest")),
        )
        responses.forEach { response ->
            assertThat(response.hasNextPage()).isEqualTo(true)
            assertThat(response.nextCursor()).isEqualTo("DAACCgACGRElMJcAAA")
            assertThat(response.spaces()).hasSize(1)
            assertThat(response.spaces()[0].id()).isEqualTo("1OGwblLjnQMKB")
            assertThat(response.spaces()[0].admins()).hasSize(1)
            assertThat(response.spaces()[0].admins()[0].id()).isEqualTo("951329744804392960")
            assertThat(response.spaces()[0].admins()[0].name()).isEqualTo("Solana")
            assertThat(response.spaces()[0].admins()[0].username()).isEqualTo("solana")
            assertThat(response.spaces()[0].listeners()).hasSize(0)
            assertThat(response.spaces()[0].speakers()).hasSize(0)
            assertThat(response.spaces()[0].endedAt())
                .isEqualTo(OffsetDateTime.parse("2026-03-11T17:49:29.190Z"))
            assertThat(response.spaces()[0].startedAt())
                .isEqualTo(OffsetDateTime.parse("2026-03-11T17:00:26.227Z"))
            assertThat(response.spaces()[0].state()).isEqualTo("Ended")
            assertThat(response.spaces()[0].title()).isEqualTo("Extending the Network to the Edge")
            assertThat(response.spaces()[0].totalLiveListeners()).isEqualTo(841L)
            assertThat(response.spaces()[0].totalReplayWatched()).isEqualTo(609L)
        }
    }
}
