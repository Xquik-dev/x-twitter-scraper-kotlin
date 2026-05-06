// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async.x

import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClientAsync
import com.x_twitter_scraper.api.models.x.users.UserRetrieveBatchParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveFollowersParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveFollowersYouKnowParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveFollowingParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveLikesParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveMediaParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveMentionsParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveSearchParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveTweetsParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveVerifiedFollowersParams
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

internal class UserServiceAsyncTest {

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun retrieve() {
        val client = XTwitterScraperOkHttpClientAsync.builder().apiKey("My API Key").build()
        val userServiceAsync = client.x().users()

        val userProfile = userServiceAsync.retrieve("id")

        userProfile.validate()
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun retrieveBatch() {
        val client = XTwitterScraperOkHttpClientAsync.builder().apiKey("My API Key").build()
        val userServiceAsync = client.x().users()

        val paginatedUsers =
            userServiceAsync.retrieveBatch(UserRetrieveBatchParams.builder().ids("ids").build())

        paginatedUsers.validate()
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun retrieveFollowers() {
        val client = XTwitterScraperOkHttpClientAsync.builder().apiKey("My API Key").build()
        val userServiceAsync = client.x().users()

        val paginatedUsers =
            userServiceAsync.retrieveFollowers(
                UserRetrieveFollowersParams.builder().id("id").cursor("cursor").pageSize(0L).build()
            )

        paginatedUsers.validate()
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun retrieveFollowersYouKnow() {
        val client = XTwitterScraperOkHttpClientAsync.builder().apiKey("My API Key").build()
        val userServiceAsync = client.x().users()

        val paginatedUsers =
            userServiceAsync.retrieveFollowersYouKnow(
                UserRetrieveFollowersYouKnowParams.builder().id("id").cursor("cursor").build()
            )

        paginatedUsers.validate()
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun retrieveFollowing() {
        val client = XTwitterScraperOkHttpClientAsync.builder().apiKey("My API Key").build()
        val userServiceAsync = client.x().users()

        val paginatedUsers =
            userServiceAsync.retrieveFollowing(
                UserRetrieveFollowingParams.builder().id("id").cursor("cursor").pageSize(0L).build()
            )

        paginatedUsers.validate()
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun retrieveLikes() {
        val client = XTwitterScraperOkHttpClientAsync.builder().apiKey("My API Key").build()
        val userServiceAsync = client.x().users()

        val paginatedTweets =
            userServiceAsync.retrieveLikes(
                UserRetrieveLikesParams.builder().id("id").cursor("cursor").build()
            )

        paginatedTweets.validate()
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun retrieveMedia() {
        val client = XTwitterScraperOkHttpClientAsync.builder().apiKey("My API Key").build()
        val userServiceAsync = client.x().users()

        val paginatedTweets =
            userServiceAsync.retrieveMedia(
                UserRetrieveMediaParams.builder().id("id").cursor("cursor").build()
            )

        paginatedTweets.validate()
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun retrieveMentions() {
        val client = XTwitterScraperOkHttpClientAsync.builder().apiKey("My API Key").build()
        val userServiceAsync = client.x().users()

        val paginatedTweets =
            userServiceAsync.retrieveMentions(
                UserRetrieveMentionsParams.builder()
                    .id("id")
                    .cursor("cursor")
                    .sinceTime("sinceTime")
                    .untilTime("untilTime")
                    .build()
            )

        paginatedTweets.validate()
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun retrieveSearch() {
        val client = XTwitterScraperOkHttpClientAsync.builder().apiKey("My API Key").build()
        val userServiceAsync = client.x().users()

        val paginatedUsers =
            userServiceAsync.retrieveSearch(
                UserRetrieveSearchParams.builder().q("q").cursor("cursor").build()
            )

        paginatedUsers.validate()
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun retrieveTweets() {
        val client = XTwitterScraperOkHttpClientAsync.builder().apiKey("My API Key").build()
        val userServiceAsync = client.x().users()

        val paginatedTweets =
            userServiceAsync.retrieveTweets(
                UserRetrieveTweetsParams.builder()
                    .id("id")
                    .cursor("cursor")
                    .includeParentTweet(true)
                    .includeReplies(true)
                    .build()
            )

        paginatedTweets.validate()
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun retrieveVerifiedFollowers() {
        val client = XTwitterScraperOkHttpClientAsync.builder().apiKey("My API Key").build()
        val userServiceAsync = client.x().users()

        val paginatedUsers =
            userServiceAsync.retrieveVerifiedFollowers(
                UserRetrieveVerifiedFollowersParams.builder().id("id").cursor("cursor").build()
            )

        paginatedUsers.validate()
    }
}
