// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async.x.communities

import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClientAsync
import com.x_twitter_scraper.api.models.x.communities.tweets.TweetListByCommunityParams
import com.x_twitter_scraper.api.models.x.communities.tweets.TweetListParams
import org.junit.jupiter.api.Test

internal class TweetServiceAsyncTest {

    @Test
    suspend fun list() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val tweetServiceAsync = client.x().communities().tweets()

        val paginatedTweets =
            tweetServiceAsync.list(
                TweetListParams.builder()
                    .communityId("321669910225")
                    .q("q")
                    .cursor("cursor")
                    .pageSize(1L)
                    .queryType(TweetListParams.QueryType.LATEST)
                    .build()
            )

        kotlin.test.assertNotNull(paginatedTweets)
    }

    @Test
    suspend fun listByCommunity() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val tweetServiceAsync = client.x().communities().tweets()

        val paginatedTweets =
            tweetServiceAsync.listByCommunity(
                TweetListByCommunityParams.builder().id("id").cursor("cursor").pageSize(1L).build()
            )

        kotlin.test.assertNotNull(paginatedTweets)
    }
}
