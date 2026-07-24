// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async

import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClientAsync
import com.x_twitter_scraper.api.models.styles.StyleAnalyzeParams
import com.x_twitter_scraper.api.models.styles.StyleCompareParams
import com.x_twitter_scraper.api.models.styles.StyleUpdateParams
import org.junit.jupiter.api.Test

internal class StyleServiceAsyncTest {

    @Test
    suspend fun retrieve() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val styleServiceAsync = client.styles()

        val styleProfile = styleServiceAsync.retrieve("id")

        kotlin.test.assertNotNull(styleProfile)
    }

    @Test
    suspend fun update() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val styleServiceAsync = client.styles()

        val styleProfile =
            styleServiceAsync.update(
                StyleUpdateParams.builder()
                    .id("id")
                    .label("Professional Voice")
                    .addTweet(
                        StyleUpdateParams.Tweet.builder()
                            .text("Excited to share our latest research findings.")
                            .build()
                    )
                    .build()
            )

        kotlin.test.assertNotNull(styleProfile)
    }

    @Test
    suspend fun list() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val styleServiceAsync = client.styles()

        val styles = styleServiceAsync.list()

        kotlin.test.assertNotNull(styles)
    }

    @Test
    suspend fun delete() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val styleServiceAsync = client.styles()

        styleServiceAsync.delete("id")
    }

    @Test
    suspend fun analyze() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val styleServiceAsync = client.styles()

        val styleProfile =
            styleServiceAsync.analyze(StyleAnalyzeParams.builder().username("elonmusk").build())

        kotlin.test.assertNotNull(styleProfile)
    }

    @Test
    suspend fun compare() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val styleServiceAsync = client.styles()

        val response =
            styleServiceAsync.compare(
                StyleCompareParams.builder().username1("username1").username2("username2").build()
            )

        kotlin.test.assertNotNull(response)
    }

    @Test
    suspend fun getPerformance() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val styleServiceAsync = client.styles()

        val response = styleServiceAsync.getPerformance("id")

        kotlin.test.assertNotNull(response)
    }
}
