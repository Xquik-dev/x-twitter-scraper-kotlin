// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async.monitors

import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClientAsync
import com.x_twitter_scraper.api.models.EventType
import com.x_twitter_scraper.api.models.monitors.keywords.KeywordCreateParams
import com.x_twitter_scraper.api.models.monitors.keywords.KeywordUpdateParams
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

internal class KeywordServiceAsyncTest {

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun create() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val keywordServiceAsync = client.monitors().keywords()

        val keyword =
            keywordServiceAsync.create(
                KeywordCreateParams.builder()
                    .addEventType(EventType.TWEET_NEW)
                    .query("xquik OR \"x api\"")
                    .build()
            )

        keyword.validate()
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun retrieve() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val keywordServiceAsync = client.monitors().keywords()

        val keyword = keywordServiceAsync.retrieve("id")

        keyword.validate()
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun update() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val keywordServiceAsync = client.monitors().keywords()

        val keyword =
            keywordServiceAsync.update(
                KeywordUpdateParams.builder()
                    .id("id")
                    .addEventType(EventType.TWEET_NEW)
                    .isActive(true)
                    .build()
            )

        keyword.validate()
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun list() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val keywordServiceAsync = client.monitors().keywords()

        val keywords = keywordServiceAsync.list()

        keywords.validate()
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun deactivate() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val keywordServiceAsync = client.monitors().keywords()

        val response = keywordServiceAsync.deactivate("id")

        response.validate()
    }
}
