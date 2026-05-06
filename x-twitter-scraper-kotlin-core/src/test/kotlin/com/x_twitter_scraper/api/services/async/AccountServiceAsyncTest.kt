// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async

import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClientAsync
import com.x_twitter_scraper.api.models.account.AccountSetXUsernameParams
import com.x_twitter_scraper.api.models.account.AccountUpdateLocaleParams
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

internal class AccountServiceAsyncTest {

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun retrieve() {
        val client = XTwitterScraperOkHttpClientAsync.builder().apiKey("My API Key").build()
        val accountServiceAsync = client.account()

        val account = accountServiceAsync.retrieve()

        account.validate()
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun setXUsername() {
        val client = XTwitterScraperOkHttpClientAsync.builder().apiKey("My API Key").build()
        val accountServiceAsync = client.account()

        val response =
            accountServiceAsync.setXUsername(
                AccountSetXUsernameParams.builder().username("elonmusk").build()
            )

        response.validate()
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun updateLocale() {
        val client = XTwitterScraperOkHttpClientAsync.builder().apiKey("My API Key").build()
        val accountServiceAsync = client.account()

        val response =
            accountServiceAsync.updateLocale(
                AccountUpdateLocaleParams.builder()
                    .locale(AccountUpdateLocaleParams.Locale.EN)
                    .build()
            )

        response.validate()
    }
}
