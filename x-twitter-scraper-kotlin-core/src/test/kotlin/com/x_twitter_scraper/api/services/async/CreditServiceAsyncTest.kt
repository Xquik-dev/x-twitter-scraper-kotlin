// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async

import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClientAsync
import com.x_twitter_scraper.api.models.credits.CreditTopupBalanceParams
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

internal class CreditServiceAsyncTest {

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun retrieveBalance() {
        val client = XTwitterScraperOkHttpClientAsync.builder().apiKey("My API Key").build()
        val creditServiceAsync = client.credits()

        val response = creditServiceAsync.retrieveBalance()

        response.validate()
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun topupBalance() {
        val client = XTwitterScraperOkHttpClientAsync.builder().apiKey("My API Key").build()
        val creditServiceAsync = client.credits()

        val response =
            creditServiceAsync.topupBalance(
                CreditTopupBalanceParams.builder().amount(10000L).build()
            )

        response.validate()
    }
}
