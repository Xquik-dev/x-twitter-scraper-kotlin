// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async.x

import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClientAsync
import org.junit.jupiter.api.Test

internal class AccountConnectionAttemptServiceAsyncTest {

    @Test
    suspend fun retrieve() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .build()
        val accountConnectionAttemptServiceAsync = client.x().accountConnectionAttempts()

        val accountConnectionAttempt =
            accountConnectionAttemptServiceAsync.retrieve("xatt_0123456789abcdef0123456789abcdef")

        accountConnectionAttempt.validate()
    }
}
