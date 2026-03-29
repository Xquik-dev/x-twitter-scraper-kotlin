// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async

import com.github.tomakehurst.wiremock.client.WireMock.anyUrl
import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.ok
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo
import com.github.tomakehurst.wiremock.junit5.WireMockTest
import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClientAsync
import com.x_twitter_scraper.api.models.draws.DrawExportParams
import com.x_twitter_scraper.api.models.draws.DrawListParams
import com.x_twitter_scraper.api.models.draws.DrawRunParams
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.ResourceLock

@WireMockTest
@ResourceLock("https://github.com/wiremock/wiremock/issues/169")
internal class DrawServiceAsyncTest {

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun retrieve() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val drawServiceAsync = client.draws()

        val draw = drawServiceAsync.retrieve("id")

        draw.validate()
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun list() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val drawServiceAsync = client.draws()

        val draws = drawServiceAsync.list(DrawListParams.builder().after("after").limit(1L).build())

        draws.validate()
    }

    @Test
    suspend fun export(wmRuntimeInfo: WireMockRuntimeInfo) {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(wmRuntimeInfo.httpBaseUrl)
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val drawServiceAsync = client.draws()
        stubFor(get(anyUrl()).willReturn(ok().withBody("abc")))

        val response =
            drawServiceAsync.export(
                DrawExportParams.builder()
                    .id("id")
                    .format(DrawExportParams.Format.CSV)
                    .type(DrawExportParams.Type.WINNERS)
                    .build()
            )

        assertThat(response.body()).hasContent("abc")
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun run() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val drawServiceAsync = client.draws()

        val response =
            drawServiceAsync.run(
                DrawRunParams.builder()
                    .tweetUrl("https://example.com")
                    .backupCount(0L)
                    .filterAccountAgeDays(0L)
                    .filterLanguage("filterLanguage")
                    .filterMinFollowers(0L)
                    .mustFollowUsername("mustFollowUsername")
                    .mustRetweet(true)
                    .addRequiredHashtag("string")
                    .addRequiredKeyword("string")
                    .addRequiredMention("string")
                    .uniqueAuthorsOnly(true)
                    .winnerCount(0L)
                    .build()
            )

        response.validate()
    }
}
