// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async

import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClientAsync
import com.x_twitter_scraper.api.models.apikeys.ApiKeyCreateParams
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

internal class ApiKeyServiceAsyncTest {

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun create() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val apiKeyServiceAsync = client.apiKeys()

        val apiKey = apiKeyServiceAsync.create(ApiKeyCreateParams.builder().name("name").build())

        apiKey.validate()
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun list() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val apiKeyServiceAsync = client.apiKeys()

        val apiKeys = apiKeyServiceAsync.list()

        apiKeys.validate()
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun revoke() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val apiKeyServiceAsync = client.apiKeys()

        val response = apiKeyServiceAsync.revoke("id")

        response.validate()
    }
}
