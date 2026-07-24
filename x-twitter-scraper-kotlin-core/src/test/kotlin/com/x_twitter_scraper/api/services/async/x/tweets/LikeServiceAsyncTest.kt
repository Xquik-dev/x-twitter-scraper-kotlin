// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async.x.tweets

import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClientAsync
import com.x_twitter_scraper.api.models.x.tweets.like.LikeCreateParams
import com.x_twitter_scraper.api.models.x.tweets.like.LikeDeleteParams
import org.junit.jupiter.api.Test

internal class LikeServiceAsyncTest {

    @Test
    suspend fun create() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val likeServiceAsync = client.x().tweets().like()

        val like =
            likeServiceAsync.create(
                LikeCreateParams.builder()
                    .id("id")
                    .idempotencyKey("Idempotency-Key")
                    .account("@elonmusk")
                    .build()
            )

        kotlin.test.assertNotNull(like)
    }

    @Test
    suspend fun delete() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val likeServiceAsync = client.x().tweets().like()

        val like =
            likeServiceAsync.delete(
                LikeDeleteParams.builder()
                    .id("id")
                    .idempotencyKey("Idempotency-Key")
                    .account("@elonmusk")
                    .build()
            )

        kotlin.test.assertNotNull(like)
    }
}
