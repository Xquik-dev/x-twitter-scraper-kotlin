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
import com.x_twitter_scraper.api.models.x.communities.media.MediaListParams
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.ResourceLock

@WireMockTest
@ResourceLock("https://github.com/wiremock/wiremock/issues/169")
internal class XCommunitiesMediaExampleTest {

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
    suspend fun list() {
        stubFor(
            get(urlPathEqualTo("/x/communities/1500000000000000000/media"))
                .willReturn(okJson(ExampleResponses.read("examples/PaginatedTweets.json")))
        )
        val params =
            MediaListParams.builder()
                .id("1500000000000000000")
                .anyWords("anyWords")
                .blueVerifiedOnly(true)
                .cashtags("cashtags")
                .build()

        val responses =
            listOf(
                client.x().communities().media().list(params),
                client.async().x().communities().media().list(params),
            )

        verify(
            2,
            getRequestedFor(urlPathEqualTo("/x/communities/1500000000000000000/media"))
                .withQueryParam("anyWords", equalTo("anyWords"))
                .withQueryParam("blueVerifiedOnly", equalTo("true"))
                .withQueryParam("cashtags", equalTo("cashtags")),
        )
        responses.forEach { response ->
            assertPaginatedTweetsExample(response)
        }
    }
}
