// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async

import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClientAsync
import com.x_twitter_scraper.api.models.styles.StyleAnalyzeParams
import com.x_twitter_scraper.api.models.styles.StyleCompareParams
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

internal class StyleServiceAsyncTest {

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun list() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val styleServiceAsync = client.styles()

        val styles = styleServiceAsync.list()

        styles.validate()
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun analyze() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val styleServiceAsync = client.styles()

        val response =
            styleServiceAsync.analyze(StyleAnalyzeParams.builder().username("username").build())

        response.validate()
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun compare() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val styleServiceAsync = client.styles()

        val response =
            styleServiceAsync.compare(
                StyleCompareParams.builder().username1("username1").username2("username2").build()
            )

        response.validate()
    }
}
