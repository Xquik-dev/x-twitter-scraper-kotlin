// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async.x.tweets

import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClientAsync
import com.x_twitter_scraper.api.models.x.tweets.retweet.RetweetCreateParams
import com.x_twitter_scraper.api.models.x.tweets.retweet.RetweetDeleteParams
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

internal class RetweetServiceAsyncTest {

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun create() {
        val client = XTwitterScraperOkHttpClientAsync.builder().apiKey("My API Key").build()
        val retweetServiceAsync = client.x().tweets().retweet()

        val retweet =
            retweetServiceAsync.create(
                RetweetCreateParams.builder().id("id").account("@elonmusk").build()
            )

        retweet.validate()
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun delete() {
        val client = XTwitterScraperOkHttpClientAsync.builder().apiKey("My API Key").build()
        val retweetServiceAsync = client.x().tweets().retweet()

        val retweet =
            retweetServiceAsync.delete(
                RetweetDeleteParams.builder().id("id").account("@elonmusk").build()
            )

        retweet.validate()
    }
}
