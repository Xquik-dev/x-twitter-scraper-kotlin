// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async.x.users

import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClientAsync
import com.x_twitter_scraper.api.models.x.users.follow.FollowCreateParams
import com.x_twitter_scraper.api.models.x.users.follow.FollowDeleteAllParams
import org.junit.jupiter.api.Test

internal class FollowServiceAsyncTest {

    @Test
    suspend fun create() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val followServiceAsync = client.x().users().follow()

        val follow =
            followServiceAsync.create(
                FollowCreateParams.builder()
                    .id("id")
                    .idempotencyKey("Idempotency-Key")
                    .account("@elonmusk")
                    .build()
            )

        kotlin.test.assertNotNull(follow)
    }

    @Test
    suspend fun deleteAll() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val followServiceAsync = client.x().users().follow()

        val response =
            followServiceAsync.deleteAll(
                FollowDeleteAllParams.builder()
                    .id("id")
                    .idempotencyKey("Idempotency-Key")
                    .account("@elonmusk")
                    .build()
            )

        kotlin.test.assertNotNull(response)
    }
}
