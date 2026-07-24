// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async

import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClientAsync
import com.x_twitter_scraper.api.models.guestwallets.GuestWalletCreateParams
import com.x_twitter_scraper.api.models.guestwallets.GuestWalletTopupParams
import org.junit.jupiter.api.Test

internal class GuestWalletServiceAsyncTest {

    @Test
    suspend fun create() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val guestWalletServiceAsync = client.guestWallets()

        val guestWallet =
            guestWalletServiceAsync.create(
                GuestWalletCreateParams.builder()
                    .idempotencyKey("e1cb97D8-dDF3-4AaA-ad0a-49E4A0d1CfAa")
                    .amountMinor(1000L)
                    .build()
            )

        kotlin.test.assertNotNull(guestWallet)
    }

    @Test
    suspend fun retrieveStatus() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val guestWalletServiceAsync = client.guestWallets()

        val response = guestWalletServiceAsync.retrieveStatus()

        kotlin.test.assertNotNull(response)
    }

    @Test
    suspend fun topup() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val guestWalletServiceAsync = client.guestWallets()

        val response =
            guestWalletServiceAsync.topup(
                GuestWalletTopupParams.builder()
                    .idempotencyKey("e1cb97D8-dDF3-4AaA-ad0a-49E4A0d1CfAa")
                    .amountMinor(1000L)
                    .build()
            )

        kotlin.test.assertNotNull(response)
    }
}
