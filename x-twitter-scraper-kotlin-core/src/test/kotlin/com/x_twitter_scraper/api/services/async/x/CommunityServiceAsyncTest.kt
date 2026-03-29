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
                    .account("account")
                    .name("name")
                    .description("description")
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
                    .account("account")
                    .communityName("community_name")
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

        communityServiceAsync.retrieveMembers(
            CommunityRetrieveMembersParams.builder().id("id").cursor("cursor").build()
        )
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

        communityServiceAsync.retrieveModerators(
            CommunityRetrieveModeratorsParams.builder().id("id").cursor("cursor").build()
        )
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

        communityServiceAsync.retrieveSearch(
            CommunityRetrieveSearchParams.builder()
                .q("q")
                .cursor("cursor")
                .queryType("queryType")
                .build()
        )
    }
}
