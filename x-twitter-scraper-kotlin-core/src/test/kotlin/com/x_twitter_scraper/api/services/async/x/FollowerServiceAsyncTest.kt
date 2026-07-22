// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async.x

import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClientAsync
import com.x_twitter_scraper.api.models.x.followers.FollowerCheckParams
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

internal class FollowerServiceAsyncTest {

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun check() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val followerServiceAsync = client.x().followers()

        val response =
            followerServiceAsync.check(
                FollowerCheckParams.builder().source("source").target("target").build()
            )

        response.validate()
    }
}
