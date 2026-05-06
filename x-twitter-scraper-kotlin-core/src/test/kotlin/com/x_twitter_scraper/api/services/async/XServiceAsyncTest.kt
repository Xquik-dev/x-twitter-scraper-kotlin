// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async

import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClientAsync
import com.x_twitter_scraper.api.models.x.XGetHomeTimelineParams
import com.x_twitter_scraper.api.models.x.XGetNotificationsParams
import com.x_twitter_scraper.api.models.x.XGetTrendsParams
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

internal class XServiceAsyncTest {

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun getArticle() {
        val client = XTwitterScraperOkHttpClientAsync.builder().apiKey("My API Key").build()
        val xServiceAsync = client.x()

        val response = xServiceAsync.getArticle("tweetId")

        response.validate()
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun getHomeTimeline() {
        val client = XTwitterScraperOkHttpClientAsync.builder().apiKey("My API Key").build()
        val xServiceAsync = client.x()

        val paginatedTweets =
            xServiceAsync.getHomeTimeline(
                XGetHomeTimelineParams.builder()
                    .cursor("cursor")
                    .seenTweetIds("seenTweetIds")
                    .build()
            )

        paginatedTweets.validate()
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun getNotifications() {
        val client = XTwitterScraperOkHttpClientAsync.builder().apiKey("My API Key").build()
        val xServiceAsync = client.x()

        val response =
            xServiceAsync.getNotifications(
                XGetNotificationsParams.builder()
                    .cursor("cursor")
                    .type(XGetNotificationsParams.Type.ALL)
                    .build()
            )

        response.validate()
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun getTrends() {
        val client = XTwitterScraperOkHttpClientAsync.builder().apiKey("My API Key").build()
        val xServiceAsync = client.x()

        val response =
            xServiceAsync.getTrends(XGetTrendsParams.builder().count(1L).woeid(0L).build())

        response.validate()
    }
}
