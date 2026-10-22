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
        val text = checkNotNull(label.text())
        assertThat(text.text()).isEqualTo("This Post may violate X's rules against Abuse.")
        assertThat(text.rtl()).isEqualTo(false)
        val entities = checkNotNull(text.entities())
        assertThat(entities).hasSize(1)
        assertThat(entities[0].fromIndex()).isEqualTo(40L)
        assertThat(entities[0].toIndex()).isEqualTo(45L)
        val ref = checkNotNull(entities[0].ref())
        assertThat(ref.type()).isEqualTo("TimelineUrl")
        assertThat(ref.url()).isEqualTo("https://help.x.com/rules-and-policies/public-interest")
        assertThat(ref.urlType()).isEqualTo("ExternalUrl")
        val url = checkNotNull(label.url())
        assertThat(url.url()).isEqualTo("https://help.x.com/rules-and-policies/enforcement-options")
        assertThat(url.urlType()).isEqualTo("ExternalUrl")
    }
}
