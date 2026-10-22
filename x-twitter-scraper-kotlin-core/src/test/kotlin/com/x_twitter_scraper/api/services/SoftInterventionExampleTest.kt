// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.services

import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.okJson
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo
import com.github.tomakehurst.wiremock.junit5.WireMockTest
import com.x_twitter_scraper.api.client.XTwitterScraperClient
import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClient
import com.x_twitter_scraper.api.models.SearchTweet
import com.x_twitter_scraper.api.models.SoftIntervention
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.ResourceLock

@WireMockTest
@ResourceLock("https://github.com/wiremock/wiremock/issues/169")
internal class SoftInterventionExampleTest {

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
    suspend fun listedPostsAndTheirEmbeddedPostsReturnTheLabel() {
        stubFor(
            get(urlPathEqualTo("/x/tweets/top"))
                .willReturn(
                    okJson(ExampleResponses.read("examples/x/tweets/softInterventionList.json"))
                )
        )

        val responses = listOf(client.x().tweets().listTop(), client.async().x().tweets().listTop())

        responses.forEach { response ->
            val post = response.tweets()[0]
            assertLabel(post.softIntervention())
            assertLabel(post.parentTweet()?.softIntervention())
            assertLabel(post.quotedTweet()?.softIntervention())
            assertLabel(post.retweetedTweet()?.softIntervention())
            assertThat(response.tweets()[1].softIntervention()).isNull()
        }
    }

    @Test
    suspend fun postDetailAndItsEmbeddedPostsReturnTheLabel() {
        stubFor(
            get(urlPathEqualTo("/x/tweets/2102761519985332442"))
                .willReturn(
                    okJson(ExampleResponses.read("examples/x/tweets/softInterventionDetail.json"))
                )
        )

        val responses =
            listOf(
                client.x().tweets().retrieve("2102761519985332442"),
                client.async().x().tweets().retrieve("2102761519985332442"),
            )

        responses.forEach { response ->
            val post = response.tweet()
            assertLabel(post.softIntervention())
            assertLabel(post.parentTweet()?.softIntervention())
            assertLabel(post.quotedTweet()?.softIntervention())
            assertLabel(post.retweetedTweet()?.softIntervention())
        }
    }

    private fun assertLabel(label: SoftIntervention?) {
        checkNotNull(label)
        assertThat(label.displayType()).isEqualTo("Fosnr")
        assertThat(label.engagementNudge()).isEqualTo(true)
        assertThat(label.text())
            .isEqualTo(
                SoftIntervention.Text.builder()
                    .text("This Post may violate X's rules against Abuse.")
                    .rtl(false)
                    .addEntity(
                        SearchTweet.Interstitial.Text.Entity.builder()
                            .fromIndex(40L)
                            .toIndex(45L)
                            .ref(
                                SearchTweet.Interstitial.Text.Entity.Ref.builder()
                                    .type("TimelineUrl")
                                    .url("https://help.x.com/rules-and-policies/public-interest")
                                    .urlType("ExternalUrl")
                                    .build()
                            )
                            .build()
                    )
                    .build()
            )
        val url = checkNotNull(label.url())
        assertThat(url.url()).isEqualTo("https://help.x.com/rules-and-policies/enforcement-options")
        assertThat(url.urlType()).isEqualTo("ExternalUrl")
    }
}
