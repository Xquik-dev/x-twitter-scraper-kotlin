// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async.x

import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClientAsync
import com.x_twitter_scraper.api.models.x.tweets.TweetCreateParams
import com.x_twitter_scraper.api.models.x.tweets.TweetDeleteParams
import com.x_twitter_scraper.api.models.x.tweets.TweetGetFavoritersParams
import com.x_twitter_scraper.api.models.x.tweets.TweetGetQuotesParams
import com.x_twitter_scraper.api.models.x.tweets.TweetGetRepliesParams
import com.x_twitter_scraper.api.models.x.tweets.TweetGetRetweetersParams
import com.x_twitter_scraper.api.models.x.tweets.TweetGetThreadParams
import com.x_twitter_scraper.api.models.x.tweets.TweetListParams
import com.x_twitter_scraper.api.models.x.tweets.TweetSearchParams
import java.time.LocalDate
import org.junit.jupiter.api.Test

internal class TweetServiceAsyncTest {

    @Test
    suspend fun create() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val tweetServiceAsync = client.x().tweets()

        val tweet =
            tweetServiceAsync.create(
                TweetCreateParams.builder()
                    .idempotencyKey("Idempotency-Key")
                    .account("@elonmusk")
                    .communityId("1500000000000000000")
                    .isNoteTweet(false)
                    .addMedia("https://example.com/video.mp4")
                    .replyToTweetId("1234567890")
                    .text("Just launched our new feature!")
                    .build()
            )

        kotlin.test.assertNotNull(tweet)
    }

    @Test
    suspend fun retrieve() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val tweetServiceAsync = client.x().tweets()

        val tweet = tweetServiceAsync.retrieve("id")

        kotlin.test.assertNotNull(tweet)
    }

    @Test
    suspend fun list() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val tweetServiceAsync = client.x().tweets()

        val paginatedTweets = tweetServiceAsync.list(TweetListParams.builder().ids("ids").build())

        kotlin.test.assertNotNull(paginatedTweets)
    }

    @Test
    suspend fun delete() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val tweetServiceAsync = client.x().tweets()

        val tweet =
            tweetServiceAsync.delete(
                TweetDeleteParams.builder()
                    .id("id")
                    .idempotencyKey("Idempotency-Key")
                    .account("@elonmusk")
                    .build()
            )

        kotlin.test.assertNotNull(tweet)
    }

    @Test
    suspend fun getFavoriters() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val tweetServiceAsync = client.x().tweets()

        val paginatedUsers =
            tweetServiceAsync.getFavoriters(
                TweetGetFavoritersParams.builder().id("id").cursor("cursor").pageSize(20L).build()
            )

        kotlin.test.assertNotNull(paginatedUsers)
    }

    @Test
    suspend fun getQuotes() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val tweetServiceAsync = client.x().tweets()

        val paginatedTweets =
            tweetServiceAsync.getQuotes(
                TweetGetQuotesParams.builder()
                    .id("id")
                    .anyWords("anyWords")
                    .cashtags("cashtags")
                    .conversationId("conversationId")
                    .cursor("cursor")
                    .exactPhrase("exactPhrase")
                    .excludeWords("excludeWords")
                    .fromUser("fromUser")
                    .hashtags("hashtags")
                    .includeReplies(true)
                    .inReplyToTweetId("inReplyToTweetId")
                    .language("language")
                    .mediaType(TweetGetQuotesParams.MediaType.IMAGES)
                    .mentioning("mentioning")
                    .minFaves(0L)
                    .minQuotes(0L)
                    .minReplies(0L)
                    .minRetweets(0L)
                    .pageSize(1L)
                    .quotes(TweetGetQuotesParams.Quotes.INCLUDE)
                    .quotesOfTweetId("quotesOfTweetId")
                    .replies(TweetGetQuotesParams.Replies.INCLUDE)
                    .retweets(TweetGetQuotesParams.Retweets.INCLUDE)
                    .retweetsOfTweetId("retweetsOfTweetId")
                    .sinceDate(LocalDate.parse("2019-12-27"))
                    .sinceTime("sinceTime")
                    .toUser("toUser")
                    .untilDate(LocalDate.parse("2019-12-27"))
                    .untilTime("untilTime")
                    .url("url")
                    .verifiedOnly(true)
                    .build()
            )

        kotlin.test.assertNotNull(paginatedTweets)
    }

    @Test
    suspend fun getReplies() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val tweetServiceAsync = client.x().tweets()

        val paginatedTweets =
            tweetServiceAsync.getReplies(
                TweetGetRepliesParams.builder()
                    .id("id")
                    .anyWords("anyWords")
                    .cashtags("cashtags")
                    .conversationId("conversationId")
                    .cursor("cursor")
                    .exactPhrase("exactPhrase")
                    .excludeWords("excludeWords")
                    .fromUser("fromUser")
                    .hashtags("hashtags")
                    .inReplyToTweetId("inReplyToTweetId")
                    .language("language")
                    .mediaType(TweetGetRepliesParams.MediaType.IMAGES)
                    .mentioning("mentioning")
                    .minFaves(0L)
                    .minQuotes(0L)
                    .minReplies(0L)
                    .minRetweets(0L)
                    .pageSize(1L)
                    .quotes(TweetGetRepliesParams.Quotes.INCLUDE)
                    .quotesOfTweetId("quotesOfTweetId")
                    .replies(TweetGetRepliesParams.Replies.INCLUDE)
                    .retweets(TweetGetRepliesParams.Retweets.INCLUDE)
                    .retweetsOfTweetId("retweetsOfTweetId")
                    .sinceDate(LocalDate.parse("2019-12-27"))
                    .sinceTime("sinceTime")
                    .toUser("toUser")
                    .untilDate(LocalDate.parse("2019-12-27"))
                    .untilTime("untilTime")
                    .url("url")
                    .verifiedOnly(true)
                    .build()
            )

        kotlin.test.assertNotNull(paginatedTweets)
    }

    @Test
    suspend fun getRetweeters() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val tweetServiceAsync = client.x().tweets()

        val paginatedUsers =
            tweetServiceAsync.getRetweeters(
                TweetGetRetweetersParams.builder().id("id").cursor("cursor").pageSize(20L).build()
            )

        kotlin.test.assertNotNull(paginatedUsers)
    }

    @Test
    suspend fun getThread() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val tweetServiceAsync = client.x().tweets()

        val paginatedTweets =
            tweetServiceAsync.getThread(
                TweetGetThreadParams.builder().id("id").cursor("cursor").pageSize(1L).build()
            )

        kotlin.test.assertNotNull(paginatedTweets)
    }

    @Test
    suspend fun search() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val tweetServiceAsync = client.x().tweets()

        val paginatedTweets =
            tweetServiceAsync.search(
                TweetSearchParams.builder()
                    .q("q")
                    .advancedQuery("advancedQuery")
                    .anyWords("anyWords")
                    .boundingBox("boundingBox")
                    .cashtags("cashtags")
                    .conversationId("conversationId")
                    .cursor("cursor")
                    .exactPhrase("exactPhrase")
                    .excludeWords("excludeWords")
                    .fromUser("fromUser")
                    .hashtags("hashtags")
                    .inReplyToTweetId("inReplyToTweetId")
                    .language("language")
                    .limit(200L)
                    .listId("listId")
                    .mediaType(TweetSearchParams.MediaType.IMAGES)
                    .mentioning("mentioning")
                    .minFaves(0L)
                    .minQuotes(0L)
                    .minReplies(0L)
                    .minRetweets(0L)
                    .place("place")
                    .placeCountry("placeCountry")
                    .pointRadius("pointRadius")
                    .queryType(TweetSearchParams.QueryType.LATEST)
                    .quotes(TweetSearchParams.Quotes.INCLUDE)
                    .quotesOfTweetId("quotesOfTweetId")
                    .replies(TweetSearchParams.Replies.INCLUDE)
                    .retweets(TweetSearchParams.Retweets.INCLUDE)
                    .retweetsOfTweetId("retweetsOfTweetId")
                    .sinceDate(LocalDate.parse("2019-12-27"))
                    .sinceTime("sinceTime")
                    .toUser("toUser")
                    .untilDate(LocalDate.parse("2019-12-27"))
                    .untilTime("untilTime")
                    .url("url")
                    .verifiedOnly(true)
                    .build()
            )

        kotlin.test.assertNotNull(paginatedTweets)
    }
}
