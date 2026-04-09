// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async.x.users

import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClientAsync
import com.x_twitter_scraper.api.models.x.users.follow.FollowCreateParams
import com.x_twitter_scraper.api.models.x.users.follow.FollowDeleteAllParams
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

internal class FollowServiceAsyncTest {

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun create() {
        val client = XTwitterScraperOkHttpClientAsync.builder().apiKey("My API Key").build()
        val followServiceAsync = client.x().users().follow()

        val follow =
            followServiceAsync.create(
                FollowCreateParams.builder().id("id").account("@elonmusk").build()
            )

        follow.validate()
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun deleteAll() {
        val client = XTwitterScraperOkHttpClientAsync.builder().apiKey("My API Key").build()
        val followServiceAsync = client.x().users().follow()

        val response =
            followServiceAsync.deleteAll(
                FollowDeleteAllParams.builder().id("id").account("@elonmusk").build()
            )

        response.validate()
    }
}
