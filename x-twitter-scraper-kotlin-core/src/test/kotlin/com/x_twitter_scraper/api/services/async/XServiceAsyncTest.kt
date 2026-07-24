// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async

import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClientAsync
import com.x_twitter_scraper.api.models.x.XGetHomeTimelineParams
import com.x_twitter_scraper.api.models.x.XGetNotificationsParams
import com.x_twitter_scraper.api.models.x.XGetTrendsParams
import org.junit.jupiter.api.Test

internal class XServiceAsyncTest {

    @Test
    suspend fun getArticle() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val xServiceAsync = client.x()

        val response = xServiceAsync.getArticle("tweetId")

        kotlin.test.assertNotNull(response)
    }

    @Test
    suspend fun getHomeTimeline() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val xServiceAsync = client.x()

        val paginatedTweets =
            xServiceAsync.getHomeTimeline(
                XGetHomeTimelineParams.builder()
                    .cursor("cursor")
                    .seenTweetIds("seenTweetIds")
                    .build()
            )

        kotlin.test.assertNotNull(paginatedTweets)
    }

    @Test
    suspend fun getNotifications() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val xServiceAsync = client.x()

        val response =
            xServiceAsync.getNotifications(
                XGetNotificationsParams.builder()
                    .cursor("cursor")
                    .type(XGetNotificationsParams.Type.ALL)
                    .build()
            )

        kotlin.test.assertNotNull(response)
    }

    @Test
    suspend fun getTrends() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val xServiceAsync = client.x()

        val response =
            xServiceAsync.getTrends(XGetTrendsParams.builder().count(1L).woeid(0L).build())

        kotlin.test.assertNotNull(response)
    }
}
