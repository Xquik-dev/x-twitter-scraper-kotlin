// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async.x

import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClientAsync
import com.x_twitter_scraper.api.models.x.communities.CommunityCreateParams
import com.x_twitter_scraper.api.models.x.communities.CommunityDeleteParams
import com.x_twitter_scraper.api.models.x.communities.CommunityRetrieveMembersParams
import com.x_twitter_scraper.api.models.x.communities.CommunityRetrieveModeratorsParams
import com.x_twitter_scraper.api.models.x.communities.CommunityRetrieveSearchParams
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

internal class CommunityServiceAsyncTest {

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun create() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val communityServiceAsync = client.x().communities()

        val community =
            communityServiceAsync.create(
                CommunityCreateParams.builder()
                    .idempotencyKey("Idempotency-Key")
                    .account("@elonmusk")
                    .name("Example Name")
                    .description("A community for Tesla enthusiasts")
                    .build()
            )

        community.validate()
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun delete() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val communityServiceAsync = client.x().communities()

        val community =
            communityServiceAsync.delete(
                CommunityDeleteParams.builder()
                    .id("id")
                    .idempotencyKey("Idempotency-Key")
                    .account("@elonmusk")
                    .communityName("Tesla Fans")
                    .build()
            )

        community.validate()
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun retrieveInfo() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val communityServiceAsync = client.x().communities()

        val response = communityServiceAsync.retrieveInfo("id")

        response.validate()
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun retrieveMembers() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val communityServiceAsync = client.x().communities()

        val paginatedUsers =
            communityServiceAsync.retrieveMembers(
                CommunityRetrieveMembersParams.builder()
                    .id("id")
                    .bioContains("bioContains")
                    .cursor("cursor")
                    .hasLocation(true)
                    .hasWebsite(true)
                    .locationContains("locationContains")
                    .maxFollowers(0L)
                    .maxFollowing(0L)
                    .maxStatuses(0L)
                    .minAccountAgeDays(0L)
                    .minFollowers(0L)
                    .minFollowing(0L)
                    .minStatuses(0L)
                    .pageSize(20L)
                    .usernameContains("usernameContains")
                    .verifiedOnly(true)
                    .verifiedType("verifiedType")
                    .build()
            )

        paginatedUsers.validate()
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun retrieveModerators() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val communityServiceAsync = client.x().communities()

        val paginatedUsers =
            communityServiceAsync.retrieveModerators(
                CommunityRetrieveModeratorsParams.builder()
                    .id("id")
                    .bioContains("bioContains")
                    .cursor("cursor")
                    .hasLocation(true)
                    .hasWebsite(true)
                    .locationContains("locationContains")
                    .maxFollowers(0L)
                    .maxFollowing(0L)
                    .maxStatuses(0L)
                    .minAccountAgeDays(0L)
                    .minFollowers(0L)
                    .minFollowing(0L)
                    .minStatuses(0L)
                    .usernameContains("usernameContains")
                    .verifiedOnly(true)
                    .verifiedType("verifiedType")
                    .build()
            )

        paginatedUsers.validate()
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun retrieveSearch() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val communityServiceAsync = client.x().communities()

        val paginatedTweets =
            communityServiceAsync.retrieveSearch(
                CommunityRetrieveSearchParams.builder()
                    .communityId("321669910225")
                    .q("q")
                    .cursor("cursor")
                    .pageSize(1L)
                    .queryType(CommunityRetrieveSearchParams.QueryType.LATEST)
                    .build()
            )

        paginatedTweets.validate()
    }
}
