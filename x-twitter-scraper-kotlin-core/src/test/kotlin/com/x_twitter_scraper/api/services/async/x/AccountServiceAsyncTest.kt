// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async.x

import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClientAsync
import com.x_twitter_scraper.api.models.x.accounts.AccountCreateParams
import com.x_twitter_scraper.api.models.x.accounts.AccountReauthParams
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

internal class AccountServiceAsyncTest {

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun create() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val accountServiceAsync = client.x().accounts()

        val account =
            accountServiceAsync.create(
                AccountCreateParams.builder()
                    .email("email")
                    .password("password")
                    .username("username")
                    .proxyCountry("proxy_country")
                    .totpSecret("totp_secret")
                    .build()
            )

        account.validate()
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun retrieve() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val accountServiceAsync = client.x().accounts()

        val xAccountDetail = accountServiceAsync.retrieve("id")

        xAccountDetail.validate()
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun list() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val accountServiceAsync = client.x().accounts()

        val accounts = accountServiceAsync.list()

        accounts.validate()
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun delete() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val accountServiceAsync = client.x().accounts()

        val account = accountServiceAsync.delete("id")

        account.validate()
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun reauth() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val accountServiceAsync = client.x().accounts()

        val response =
            accountServiceAsync.reauth(
                AccountReauthParams.builder()
                    .id("id")
                    .password("password")
                    .totpSecret("totp_secret")
                    .build()
            )

        response.validate()
    }
}
