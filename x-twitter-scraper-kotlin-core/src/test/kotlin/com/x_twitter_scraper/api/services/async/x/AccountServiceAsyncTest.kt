// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async.x

import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClientAsync
import com.x_twitter_scraper.api.models.x.accounts.AccountCreateParams
import com.x_twitter_scraper.api.models.x.accounts.AccountReauthParams
import org.junit.jupiter.api.Test

internal class AccountServiceAsyncTest {

    @Test
    suspend fun create() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val accountServiceAsync = client.x().accounts()

        val account =
            accountServiceAsync.create(
                AccountCreateParams.builder()
                    .email("account@example.invalid")
                    .password("<ACCOUNT_PASSWORD>")
                    .username("your_x_username")
                    .totpSecret("<TOTP_SECRET>")
                    .build()
            )

        kotlin.test.assertNotNull(account)
    }

    @Test
    suspend fun retrieve() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val accountServiceAsync = client.x().accounts()

        val xAccountDetail = accountServiceAsync.retrieve("id")

        kotlin.test.assertNotNull(xAccountDetail)
    }

    @Test
    suspend fun list() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val accountServiceAsync = client.x().accounts()

        val accounts = accountServiceAsync.list()

        kotlin.test.assertNotNull(accounts)
    }

    @Test
    suspend fun delete() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val accountServiceAsync = client.x().accounts()

        val account = accountServiceAsync.delete("id")

        kotlin.test.assertNotNull(account)
    }

    @Test
    suspend fun bulkRetry() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val accountServiceAsync = client.x().accounts()

        val response = accountServiceAsync.bulkRetry()

        kotlin.test.assertNotNull(response)
    }

    @Test
    suspend fun reauth() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val accountServiceAsync = client.x().accounts()

        val response =
            accountServiceAsync.reauth(
                AccountReauthParams.builder()
                    .id("id")
                    .password("<ACCOUNT_PASSWORD>")
                    .email("account@example.invalid")
                    .totpSecret("<TOTP_SECRET>")
                    .build()
            )

        kotlin.test.assertNotNull(response)
    }
}
