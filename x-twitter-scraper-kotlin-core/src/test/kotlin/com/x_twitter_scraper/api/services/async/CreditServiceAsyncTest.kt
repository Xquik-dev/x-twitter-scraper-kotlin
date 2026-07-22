// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async

import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClientAsync
import com.x_twitter_scraper.api.models.credits.CreditRedirectTopupCheckoutParams
import com.x_twitter_scraper.api.models.credits.CreditRetrieveTopupStatusParams
import com.x_twitter_scraper.api.models.credits.CreditTopupBalanceParams
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

internal class CreditServiceAsyncTest {

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun redirectTopupCheckout() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val creditServiceAsync = client.credits()

        creditServiceAsync.redirectTopupCheckout(
            CreditRedirectTopupCheckoutParams.builder().sessionId("session_id").build()
        )
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun retrieveBalance() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val creditServiceAsync = client.credits()

        val response = creditServiceAsync.retrieveBalance()

        response.validate()
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun retrieveTopupStatus() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val creditServiceAsync = client.credits()

        val response =
            creditServiceAsync.retrieveTopupStatus(
                CreditRetrieveTopupStatusParams.builder().sessionId("session_id").build()
            )

        response.validate()
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun topupBalance() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val creditServiceAsync = client.credits()

        val response =
            creditServiceAsync.topupBalance(
                CreditTopupBalanceParams.builder().dollars(10L).locale("en").build()
            )

        response.validate()
    }
}
