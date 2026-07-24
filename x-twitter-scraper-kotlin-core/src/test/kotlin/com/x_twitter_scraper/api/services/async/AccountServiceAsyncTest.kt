// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async

import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClientAsync
import com.x_twitter_scraper.api.models.account.AccountSetXUsernameParams
import com.x_twitter_scraper.api.models.account.AccountUpdateLocaleParams
import org.junit.jupiter.api.Test

internal class AccountServiceAsyncTest {

    @Test
    suspend fun retrieve() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val accountServiceAsync = client.account()

        val account = accountServiceAsync.retrieve()

        kotlin.test.assertNotNull(account)
    }

    @Test
    suspend fun setXUsername() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val accountServiceAsync = client.account()

        val response =
            accountServiceAsync.setXUsername(
                AccountSetXUsernameParams.builder().username("elonmusk").build()
            )

        kotlin.test.assertNotNull(response)
    }

    @Test
    suspend fun updateLocale() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val accountServiceAsync = client.account()

        val response =
            accountServiceAsync.updateLocale(
                AccountUpdateLocaleParams.builder()
                    .locale(AccountUpdateLocaleParams.Locale.EN)
                    .build()
            )

        kotlin.test.assertNotNull(response)
    }
}
