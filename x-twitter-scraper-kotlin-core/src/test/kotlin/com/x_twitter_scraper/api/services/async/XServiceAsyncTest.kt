// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.services.async

import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClientAsync
import com.x_twitter_scraper.api.models.x.XAutocompleteSearchParams
import com.x_twitter_scraper.api.models.x.XGetHashflagsParams
import com.x_twitter_scraper.api.models.x.XGetHomeTimelineParams
import com.x_twitter_scraper.api.models.x.XGetNewsStoryVersionsParams
import com.x_twitter_scraper.api.models.x.XGetNotificationsParams
import com.x_twitter_scraper.api.models.x.XGetTrendLocationsParams
import com.x_twitter_scraper.api.models.x.XGetTrendsHistoryParams
import com.x_twitter_scraper.api.models.x.XGetTrendsParams
import com.x_twitter_scraper.api.models.x.XSearchPlacesParams
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

    @Test
    suspend fun autocompleteSearch() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val xServiceAsync = client.x()

        val response =
            xServiceAsync.autocompleteSearch(
                XAutocompleteSearchParams.builder()
                    .q("nasa")
                    .fields("id,text,author.username")
                    .limit(1L)
                    .omit("entities,media")
                    .types("users,topics")
                    .build()
            )

        kotlin.test.assertNotNull(response)
    }

    @Test
    suspend fun getHashflags() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val xServiceAsync = client.x()

        val response =
            xServiceAsync.getHashflags(
                XGetHashflagsParams.builder()
                    .activeOnly(true)
                    .fields("id,text,author.username")
                    .limit(1L)
                    .omit("entities,media")
                    .q("q")
                    .build()
            )

        kotlin.test.assertNotNull(response)
    }

    @Test
    suspend fun searchPlaces() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val xServiceAsync = client.x()

        val response =
            xServiceAsync.searchPlaces(
                XSearchPlacesParams.builder()
                    .q("q")
                    .fields("id,text,author.username")
                    .limit(1L)
                    .omit("entities,media")
                    .build()
            )

        kotlin.test.assertNotNull(response)
    }

    @Test
    suspend fun getTrendLocations() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val xServiceAsync = client.x()

        val response =
            xServiceAsync.getTrendLocations(
                XGetTrendLocationsParams.builder()
                    .country("country")
                    .fields("id,text,author.username")
                    .limit(1L)
                    .omit("entities,media")
                    .placeType(XGetTrendLocationsParams.PlaceType.COUNTRY)
                    .q("q")
                    .build()
            )

        kotlin.test.assertNotNull(response)
    }

    @Test
    suspend fun getTrendsHistory() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val xServiceAsync = client.x()

        val response =
            xServiceAsync.getTrendsHistory(
                XGetTrendsHistoryParams.builder()
                    .count(1L)
                    .country("country")
                    .cursor("2026-10-14T00:00:00.000Z~1791979200000")
                    .every("1h")
                    .fields("id,text,author.username")
                    .from("2026-10-13")
                    .limit(1L)
                    .location("location")
                    .omit("entities,media")
                    .to("2026-10-14")
                    .woeid(1L)
                    .build()
            )

        kotlin.test.assertNotNull(response)
    }

    @Test
    suspend fun getNewsStoryVersions() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val xServiceAsync = client.x()

        val response =
            xServiceAsync.getNewsStoryVersions(
                XGetNewsStoryVersionsParams.builder()
                    .id("2110209733533598095")
                    .fields("id,text,author.username")
                    .omit("entities,media")
                    .build()
            )

        kotlin.test.assertNotNull(response)
    }
}
