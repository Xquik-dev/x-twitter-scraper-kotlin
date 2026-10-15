// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.services

import com.github.tomakehurst.wiremock.client.WireMock.equalTo
import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor
import com.github.tomakehurst.wiremock.client.WireMock.okJson
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo
import com.github.tomakehurst.wiremock.client.WireMock.verify
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo
import com.github.tomakehurst.wiremock.junit5.WireMockTest
import com.x_twitter_scraper.api.client.XTwitterScraperClient
import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClient
import com.x_twitter_scraper.api.core.JsonValue
import com.x_twitter_scraper.api.models.x.users.UserRetrieveAffiliatesParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveArticlesParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveBatchTweetsParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveFollowerIdsParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveFollowingIdsParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveHighlightsParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveInsightsParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveListMembershipsParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveListsParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveLiveParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveSimilarParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveSubscriptionsParams
import com.x_twitter_scraper.api.models.x.users.UserTranslateBioParams
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.ResourceLock

@WireMockTest
@ResourceLock("https://github.com/wiremock/wiremock/issues/169")
internal class XUsersExampleTest {

    private lateinit var client: XTwitterScraperClient

    @BeforeEach
    fun beforeEach(wmRuntimeInfo: WireMockRuntimeInfo) {
        client =
            XTwitterScraperOkHttpClient.builder()
                .baseUrl(wmRuntimeInfo.httpBaseUrl)
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .responseValidation(true)
                .build()
    }

    @Test
    suspend fun retrieveBatchTweets() {
        stubFor(
            get(urlPathEqualTo("/x/users/batch/tweets"))
                .willReturn(
                    okJson(ExampleResponses.read("examples/x/users/retrieveBatchTweets.json"))
                )
        )
        val params =
            UserRetrieveBatchTweetsParams.builder()
                .pageSize(20L)
                .usernames("nasa,@OpenAI,x.com/verge")
                .build()

        val responses =
            listOf(
                client.x().users().retrieveBatchTweets(params),
                client.async().x().users().retrieveBatchTweets(params),
            )

        verify(
            2,
            getRequestedFor(urlPathEqualTo("/x/users/batch/tweets"))
                .withQueryParam("pageSize", equalTo("20"))
                .withQueryParam("usernames", equalTo("nasa,@OpenAI,x.com/verge")),
        )
        responses.forEach { response ->
            assertThat(response._hasNextPage()).isEqualTo(JsonValue.from(false))
            assertThat(response.nextCursor()).isEqualTo("")
            assertThat(response.tweets()).hasSize(1)
            assertThat(response.tweets()[0].id()).isEqualTo("1234567890")
            assertThat(response.tweets()[0].text()).isEqualTo("Just launched our new feature!")
            assertThat(response.tweets()[0].createdAt()).isEqualTo("2025-01-15T12:00:00Z")
            assertThat(response.tweets()[0].likeCount()).isEqualTo(42L)
            assertThat(response.tweets()[0].retweetCount()).isEqualTo(5L)
            assertThat(response.tweets()[0].replyCount()).isEqualTo(3L)
            assertThat(response.tweets()[0].quoteCount()).isEqualTo(1L)
            assertThat(response.tweets()[0].viewCount()).isEqualTo(1500L)
            assertThat(response.tweets()[0].bookmarkCount()).isEqualTo(2L)
            assertThat(response.unavailableIds()).containsExactly("1234567890")
            assertThat(response.unprocessedIds()).hasSize(0)
        }
    }

    @Test
    suspend fun retrieveAffiliates() {
        stubFor(
            get(urlPathEqualTo("/x/users/2102882545427714266/affiliates"))
                .willReturn(okJson(ExampleResponses.read("examples/PaginatedUsers.json")))
        )
        val params =
            UserRetrieveAffiliatesParams.builder()
                .id("2102882545427714266")
                .bioContains("bioContains")
                .blueVerifiedOnly(true)
                .businessEmailOnly(true)
                .build()

        val responses =
            listOf(
                client.x().users().retrieveAffiliates(params),
                client.async().x().users().retrieveAffiliates(params),
            )

        verify(
            2,
            getRequestedFor(urlPathEqualTo("/x/users/2102882545427714266/affiliates"))
                .withQueryParam("bioContains", equalTo("bioContains"))
                .withQueryParam("blueVerifiedOnly", equalTo("true"))
                .withQueryParam("businessEmailOnly", equalTo("true")),
        )
        responses.forEach { response ->
            assertPaginatedUsersExample(response)
        }
    }

    @Test
    suspend fun retrieveArticles() {
        stubFor(
            get(urlPathEqualTo("/x/users/2102882545427714266/articles"))
                .willReturn(okJson(ExampleResponses.read("examples/PaginatedTweets.json")))
        )
        val params =
            UserRetrieveArticlesParams.builder()
                .id("2102882545427714266")
                .anyWords("anyWords")
                .blueVerifiedOnly(true)
                .cashtags("cashtags")
                .build()

        val responses =
            listOf(
                client.x().users().retrieveArticles(params),
                client.async().x().users().retrieveArticles(params),
            )

        verify(
            2,
            getRequestedFor(urlPathEqualTo("/x/users/2102882545427714266/articles"))
                .withQueryParam("anyWords", equalTo("anyWords"))
                .withQueryParam("blueVerifiedOnly", equalTo("true"))
                .withQueryParam("cashtags", equalTo("cashtags")),
        )
        responses.forEach { response ->
            assertPaginatedTweetsExample(response)
        }
    }

    @Test
    suspend fun retrieveFollowerIds() {
        stubFor(
            get(urlPathEqualTo("/x/users/2102882545427714266/follower-ids"))
                .willReturn(okJson(ExampleResponses.read("examples/PaginatedUserIds.json")))
        )
        val params =
            UserRetrieveFollowerIdsParams.builder()
                .id("2102882545427714266")
                .pageSize(5000L)
                .build()

        val responses =
            listOf(
                client.x().users().retrieveFollowerIds(params),
                client.async().x().users().retrieveFollowerIds(params),
            )

        verify(
            2,
            getRequestedFor(urlPathEqualTo("/x/users/2102882545427714266/follower-ids"))
                .withQueryParam("pageSize", equalTo("5000")),
        )
        responses.forEach { response ->
            assertThat(response.hasNextPage()).isEqualTo(true)
            assertThat(response.ids()).containsExactly("9876543210", "44196397")
            assertThat(response.nextCursor()).isEqualTo("1878079982871151535")
        }
    }

    @Test
    suspend fun retrieveFollowingIds() {
        stubFor(
            get(urlPathEqualTo("/x/users/2102882545427714266/following-ids"))
                .willReturn(okJson(ExampleResponses.read("examples/PaginatedUserIds.json")))
        )
        val params =
            UserRetrieveFollowingIdsParams.builder()
                .id("2102882545427714266")
                .pageSize(5000L)
                .build()

        val responses =
            listOf(
                client.x().users().retrieveFollowingIds(params),
                client.async().x().users().retrieveFollowingIds(params),
            )

        verify(
            2,
            getRequestedFor(urlPathEqualTo("/x/users/2102882545427714266/following-ids"))
                .withQueryParam("pageSize", equalTo("5000")),
        )
        responses.forEach { response ->
            assertThat(response.hasNextPage()).isEqualTo(true)
            assertThat(response.ids()).containsExactly("9876543210", "44196397")
            assertThat(response.nextCursor()).isEqualTo("1878079982871151535")
        }
    }

    @Test
    suspend fun retrieveHighlights() {
        stubFor(
            get(urlPathEqualTo("/x/users/2102882545427714266/highlights"))
                .willReturn(okJson(ExampleResponses.read("examples/PaginatedTweets.json")))
        )
        val params =
            UserRetrieveHighlightsParams.builder()
                .id("2102882545427714266")
                .anyWords("anyWords")
                .blueVerifiedOnly(true)
                .cashtags("cashtags")
                .build()

        val responses =
            listOf(
                client.x().users().retrieveHighlights(params),
                client.async().x().users().retrieveHighlights(params),
            )

        verify(
            2,
            getRequestedFor(urlPathEqualTo("/x/users/2102882545427714266/highlights"))
                .withQueryParam("anyWords", equalTo("anyWords"))
                .withQueryParam("blueVerifiedOnly", equalTo("true"))
                .withQueryParam("cashtags", equalTo("cashtags")),
        )
        responses.forEach { response ->
            assertPaginatedTweetsExample(response)
        }
    }

    @Test
    suspend fun retrieveInsights() {
        stubFor(
            get(urlPathEqualTo("/x/users/2102882545427714266/insights"))
                .willReturn(okJson(ExampleResponses.read("examples/x/users/retrieveInsights.json")))
        )
        val params = UserRetrieveInsightsParams.builder().id("2102882545427714266").build()

        val responses =
            listOf(
                client.x().users().retrieveInsights(params),
                client.async().x().users().retrieveInsights(params),
            )

        verify(2, getRequestedFor(urlPathEqualTo("/x/users/2102882545427714266/insights")))
        responses.forEach { response ->
            assertThat(response.insights().accountAgeDays()).isEqualTo(6858.0)
            assertThat(response.insights().averages()!!.bookmarks()).isEqualTo(209.7)
            assertThat(response.insights().averages()!!.engagements()).isEqualTo(4331.7)
            assertThat(response.insights().averages()!!.likes()).isEqualTo(3684.2)
            assertThat(response.insights().averages()!!.quotes()).isEqualTo(45.0)
            assertThat(response.insights().averages()!!.replies()).isEqualTo(142.7)
            assertThat(response.insights().averages()!!.reposts()).isEqualTo(459.8)
            assertThat(response.insights().averages()!!.views()).isEqualTo(785247.8)
            assertThat(response.insights().engagementPerView()).isEqualTo(0.5516)
            assertThat(response.insights().engagementRate()).isEqualTo(0.0047)
            assertThat(response.insights().followers()).isEqualTo(92376187.0)
            assertThat(response.insights().following()).isEqualTo(115.0)
            assertThat(response.insights().posts()).isEqualTo(74336.0)
            assertThat(response.insights().postsPerDay()).isEqualTo(1.6814)
            assertThat(response.insights().postsPrivate()).isEqualTo(false)
            assertThat(response.insights().sample().newestAt())
                .isEqualTo(OffsetDateTime.parse("2026-09-28T22:12:05.000Z"))
            assertThat(response.insights().sample().oldestAt())
                .isEqualTo(OffsetDateTime.parse("2026-09-23T13:15:28.000Z"))
            assertThat(response.insights().sample().posts()).isEqualTo(10.0)
            assertThat(response.insights().topPostId()).isEqualTo("2102761519985332442")
            assertThat(response.insights().userId()).isEqualTo("11348282")
            assertThat(response.insights().userName()).isEqualTo("NASA")
        }
    }

    @Test
    suspend fun retrieveInsightsForPrivatePosts() {
        stubFor(
            get(urlPathEqualTo("/x/users/2102882545427714266/insights"))
                .willReturn(
                    okJson(
                        ExampleResponses.read(
                            "examples/x/users/retrieveInsightsForPrivatePosts.json"
                        )
                    )
                )
        )
        val params = UserRetrieveInsightsParams.builder().id("2102882545427714266").build()

        val responses =
            listOf(
                client.x().users().retrieveInsights(params),
                client.async().x().users().retrieveInsights(params),
            )

        verify(2, getRequestedFor(urlPathEqualTo("/x/users/2102882545427714266/insights")))
        responses.forEach { response ->
            assertThat(response.insights().accountAgeDays()).isEqualTo(6858.0)
            assertThat(response.insights().averages()).isNull()
            assertThat(response.insights().engagementPerView()).isNull()
            assertThat(response.insights().engagementRate()).isNull()
            assertThat(response.insights().followers()).isEqualTo(92376187.0)
            assertThat(response.insights().following()).isEqualTo(115.0)
            assertThat(response.insights().posts()).isEqualTo(74336.0)
            assertThat(response.insights().postsPerDay()).isNull()
            assertThat(response.insights().postsPrivate()).isEqualTo(true)
            assertThat(response.insights().sample().newestAt()).isNull()
            assertThat(response.insights().sample().oldestAt()).isNull()
            assertThat(response.insights().sample().posts()).isEqualTo(0.0)
            assertThat(response.insights().topPostId()).isNull()
            assertThat(response.insights().userId()).isEqualTo("11348282")
            assertThat(response.insights().userName()).isEqualTo("NASA")
            assertThat(response.message())
                .isEqualTo("This account's posts are private, so the report has its counts only.")
        }
    }

    @Test
    suspend fun retrieveListMemberships() {
        stubFor(
            get(urlPathEqualTo("/x/users/2102882545427714266/list-memberships"))
                .willReturn(okJson(ExampleResponses.read("examples/PaginatedLists.json")))
        )
        val params =
            UserRetrieveListMembershipsParams.builder()
                .id("2102882545427714266")
                .pageSize(20L)
                .build()

        val responses =
            listOf(
                client.x().users().retrieveListMemberships(params),
                client.async().x().users().retrieveListMemberships(params),
            )

        verify(
            2,
            getRequestedFor(urlPathEqualTo("/x/users/2102882545427714266/list-memberships"))
                .withQueryParam("pageSize", equalTo("20")),
        )
        responses.forEach { response ->
            assertThat(response.hasNextPage()).isEqualTo(false)
            assertThat(response.lists()).hasSize(1)
            assertThat(response.lists()[0].id()).isEqualTo("4143408")
            assertThat(response.lists()[0].name()).isEqualTo("NASA Centers")
            assertThat(response.lists()[0].createdAt())
                .isEqualTo(OffsetDateTime.parse("2009-12-02T22:41:02.000Z"))
            assertThat(response.lists()[0].defaultBanner()!!.url())
                .isEqualTo("https://pbs.twimg.com/media/GkIOx3HW4AAHAs6.png")
            assertThat(response.lists()[0].defaultBanner()!!.height()).isEqualTo(375L)
            assertThat(response.lists()[0].defaultBanner()!!.salientRect()!!.height()).isEqualTo(1L)
            assertThat(response.lists()[0].defaultBanner()!!.salientRect()!!.left()).isEqualTo(562L)
            assertThat(response.lists()[0].defaultBanner()!!.salientRect()!!.top()).isEqualTo(187L)
            assertThat(response.lists()[0].defaultBanner()!!.salientRect()!!.width()).isEqualTo(1L)
            assertThat(response.lists()[0].defaultBanner()!!.width()).isEqualTo(1125L)
            assertThat(response.lists()[0].description())
                .isEqualTo("NASA has 10 centers http://www.nasa.gov/about/sites")
            assertThat(response.lists()[0].memberCount()).isEqualTo(11L)
            assertThat(response.lists()[0].membersContext()).isEqualTo("11 members")
            assertThat(response.lists()[0].owner()!!.id()).isEqualTo("11348282")
            assertThat(response.lists()[0].owner()!!.username()).isEqualTo("NASA")
            assertThat(response.lists()[0].owner()!!.name()).isEqualTo("NASA")
            assertThat(response.lists()[0].owner()!!.followers()).isEqualTo(92379503L)
            assertThat(response.lists()[0].owner()!!.following()).isEqualTo(115L)
            assertThat(response.lists()[0].owner()!!.verified()).isEqualTo(true)
            assertThat(response.lists()[0].owner()!!.isBlueVerified()).isEqualTo(true)
            assertThat(response.lists()[0].owner()!!.isVerified()).isEqualTo(true)
            assertThat(response.lists()[0].owner()!!.profilePicture())
                .isEqualTo(
                    "https://pbs.twimg.com/profile_images/1321163587679784960/0ZxKlEKB_normal.jpg"
                )
            assertThat(response.lists()[0].owner()!!.coverPicture())
                .isEqualTo("https://pbs.twimg.com/profile_banners/11348282/1788992290")
            assertThat(response.lists()[0].owner()!!.profileBannerUrl())
                .isEqualTo("https://pbs.twimg.com/profile_banners/11348282/1788992290")
            assertThat(response.lists()[0].owner()!!.description())
                .isEqualTo("Making the seemingly impossible, possible. ✨")
            assertThat(response.lists()[0].owner()!!.location()).isEqualTo("Pale Blue Dot")
            assertThat(response.lists()[0].owner()!!.createdAt())
                .isEqualTo("Wed Dec 19 20:20:32 +0000 2007")
            assertThat(response.lists()[0].owner()!!.statusesCount()).isEqualTo(74372L)
            assertThat(response.lists()[0].owner()!!.mediaCount()).isEqualTo(28176L)
            assertThat(response.lists()[0].owner()!!.protected()).isEqualTo(false)
            assertThat(response.lists()[0].owner()!!.url()).isEqualTo("http://www.nasa.gov/")
            assertThat(response.lists()[0].owner()!!.favouritesCount()).isEqualTo(17012L)
            assertThat(response.lists()[0].owner()!!.possiblySensitive()).isEqualTo(false)
            assertThat(response.lists()[0].owner()!!.verifiedType()).isEqualTo("Government")
            assertThat(response.lists()[0].owner()!!.hasGraduatedAccess()).isEqualTo(true)
            assertThat(response.lists()[0].owner()!!.parodyCommentaryFanLabel()).isEqualTo("None")
            assertThat(response.lists()[0].owner()!!.profileDescriptionLanguage()).isEqualTo("en")
            assertThat(response.lists()[0].owner()!!.profileImageShape()).isEqualTo("Square")
            assertThat(response.lists()[0].owner()!!.profileTranslatorType()).isEqualTo("regular")
            assertThat(response.lists()[0].owner()!!.superFollowEligible()).isEqualTo(false)
            assertThat(response.lists()[0].subscriberCount()).isEqualTo(859L)
            assertThat(response.lists()[0].url()).isEqualTo("https://x.com/i/lists/4143408")
            assertThat(response.nextCursor()).isEqualTo("")
        }
    }

    @Test
    suspend fun retrieveLists() {
        stubFor(
            get(urlPathEqualTo("/x/users/2102882545427714266/lists"))
                .willReturn(okJson(ExampleResponses.read("examples/PaginatedLists.json")))
        )
        val params =
            UserRetrieveListsParams.builder().id("2102882545427714266").pageSize(20L).build()

        val responses =
            listOf(
                client.x().users().retrieveLists(params),
                client.async().x().users().retrieveLists(params),
            )

        verify(
            2,
            getRequestedFor(urlPathEqualTo("/x/users/2102882545427714266/lists"))
                .withQueryParam("pageSize", equalTo("20")),
        )
        responses.forEach { response ->
            assertThat(response.hasNextPage()).isEqualTo(false)
            assertThat(response.lists()).hasSize(1)
            assertThat(response.lists()[0].id()).isEqualTo("4143408")
            assertThat(response.lists()[0].name()).isEqualTo("NASA Centers")
            assertThat(response.lists()[0].createdAt())
                .isEqualTo(OffsetDateTime.parse("2009-12-02T22:41:02.000Z"))
            assertThat(response.lists()[0].defaultBanner()!!.url())
                .isEqualTo("https://pbs.twimg.com/media/GkIOx3HW4AAHAs6.png")
            assertThat(response.lists()[0].defaultBanner()!!.height()).isEqualTo(375L)
            assertThat(response.lists()[0].defaultBanner()!!.salientRect()!!.height()).isEqualTo(1L)
            assertThat(response.lists()[0].defaultBanner()!!.salientRect()!!.left()).isEqualTo(562L)
            assertThat(response.lists()[0].defaultBanner()!!.salientRect()!!.top()).isEqualTo(187L)
            assertThat(response.lists()[0].defaultBanner()!!.salientRect()!!.width()).isEqualTo(1L)
            assertThat(response.lists()[0].defaultBanner()!!.width()).isEqualTo(1125L)
            assertThat(response.lists()[0].description())
                .isEqualTo("NASA has 10 centers http://www.nasa.gov/about/sites")
            assertThat(response.lists()[0].memberCount()).isEqualTo(11L)
            assertThat(response.lists()[0].membersContext()).isEqualTo("11 members")
            assertThat(response.lists()[0].owner()!!.id()).isEqualTo("11348282")
            assertThat(response.lists()[0].owner()!!.username()).isEqualTo("NASA")
            assertThat(response.lists()[0].owner()!!.name()).isEqualTo("NASA")
            assertThat(response.lists()[0].owner()!!.followers()).isEqualTo(92379503L)
            assertThat(response.lists()[0].owner()!!.following()).isEqualTo(115L)
            assertThat(response.lists()[0].owner()!!.verified()).isEqualTo(true)
            assertThat(response.lists()[0].owner()!!.isBlueVerified()).isEqualTo(true)
            assertThat(response.lists()[0].owner()!!.isVerified()).isEqualTo(true)
            assertThat(response.lists()[0].owner()!!.profilePicture())
                .isEqualTo(
                    "https://pbs.twimg.com/profile_images/1321163587679784960/0ZxKlEKB_normal.jpg"
                )
            assertThat(response.lists()[0].owner()!!.coverPicture())
                .isEqualTo("https://pbs.twimg.com/profile_banners/11348282/1788992290")
            assertThat(response.lists()[0].owner()!!.profileBannerUrl())
                .isEqualTo("https://pbs.twimg.com/profile_banners/11348282/1788992290")
            assertThat(response.lists()[0].owner()!!.description())
                .isEqualTo("Making the seemingly impossible, possible. ✨")
            assertThat(response.lists()[0].owner()!!.location()).isEqualTo("Pale Blue Dot")
            assertThat(response.lists()[0].owner()!!.createdAt())
                .isEqualTo("Wed Dec 19 20:20:32 +0000 2007")
            assertThat(response.lists()[0].owner()!!.statusesCount()).isEqualTo(74372L)
            assertThat(response.lists()[0].owner()!!.mediaCount()).isEqualTo(28176L)
            assertThat(response.lists()[0].owner()!!.protected()).isEqualTo(false)
            assertThat(response.lists()[0].owner()!!.url()).isEqualTo("http://www.nasa.gov/")
            assertThat(response.lists()[0].owner()!!.favouritesCount()).isEqualTo(17012L)
            assertThat(response.lists()[0].owner()!!.possiblySensitive()).isEqualTo(false)
            assertThat(response.lists()[0].owner()!!.verifiedType()).isEqualTo("Government")
            assertThat(response.lists()[0].owner()!!.hasGraduatedAccess()).isEqualTo(true)
            assertThat(response.lists()[0].owner()!!.parodyCommentaryFanLabel()).isEqualTo("None")
            assertThat(response.lists()[0].owner()!!.profileDescriptionLanguage()).isEqualTo("en")
            assertThat(response.lists()[0].owner()!!.profileImageShape()).isEqualTo("Square")
            assertThat(response.lists()[0].owner()!!.profileTranslatorType()).isEqualTo("regular")
            assertThat(response.lists()[0].owner()!!.superFollowEligible()).isEqualTo(false)
            assertThat(response.lists()[0].subscriberCount()).isEqualTo(859L)
            assertThat(response.lists()[0].url()).isEqualTo("https://x.com/i/lists/4143408")
            assertThat(response.nextCursor()).isEqualTo("")
        }
    }

    @Test
    suspend fun retrieveLive() {
        stubFor(
            get(urlPathEqualTo("/x/users/2102882545427714266/live"))
                .willReturn(okJson(ExampleResponses.read("examples/x/users/retrieveLive.json")))
        )
        val params = UserRetrieveLiveParams.builder().id("2102882545427714266").build()

        val responses =
            listOf(
                client.x().users().retrieveLive(params),
                client.async().x().users().retrieveLive(params),
            )

        verify(2, getRequestedFor(urlPathEqualTo("/x/users/2102882545427714266/live")))
        responses.forEach { response ->
            assertThat(response.broadcast()!!.id()).isEqualTo("1DxLdZyVPMMxm")
            assertThat(response.broadcast()!!.endedAt())
                .isEqualTo(OffsetDateTime.parse("2026-10-08T16:38:16.770Z"))
            assertThat(response.broadcast()!!.height()).isEqualTo(2160L)
            assertThat(response.broadcast()!!.startedAt())
                .isEqualTo(OffsetDateTime.parse("2026-10-08T14:20:33.863Z"))
            assertThat(response.broadcast()!!.state()).isEqualTo("Ended")
            assertThat(response.broadcast()!!.title())
                .isEqualTo("NASA's SpaceX Crew-12 Re-Entry & Splashdown")
            assertThat(response.broadcast()!!.totalWatched()).isEqualTo(220908L)
            assertThat(response.broadcast()!!.totalWatching()).isEqualTo(119617L)
            assertThat(response.broadcast()!!.width()).isEqualTo(3840L)
            assertThat(response.live()).isEqualTo(false)
            assertThat(response.userId()).isEqualTo("11348282")
            assertThat(response.userName()).isEqualTo("NASA")
        }
    }

    @Test
    suspend fun retrieveLiveWithoutBroadcast() {
        stubFor(
            get(urlPathEqualTo("/x/users/2102882545427714266/live"))
                .willReturn(
                    okJson(
                        ExampleResponses.read("examples/x/users/retrieveLiveWithoutBroadcast.json")
                    )
                )
        )
        val params = UserRetrieveLiveParams.builder().id("2102882545427714266").build()

        val responses =
            listOf(
                client.x().users().retrieveLive(params),
                client.async().x().users().retrieveLive(params),
            )

        verify(2, getRequestedFor(urlPathEqualTo("/x/users/2102882545427714266/live")))
        responses.forEach { response ->
            assertThat(response.broadcast()).isNull()
            assertThat(response.live()).isEqualTo(false)
            assertThat(response.userId()).isEqualTo("11348282")
            assertThat(response.userName()).isEqualTo("NASA")
        }
    }

    @Test
    suspend fun retrieveSimilar() {
        stubFor(
            get(urlPathEqualTo("/x/users/2102882545427714266/similar"))
                .willReturn(okJson(ExampleResponses.read("examples/PaginatedUsers.json")))
        )
        val params =
            UserRetrieveSimilarParams.builder()
                .id("2102882545427714266")
                .bioContains("bioContains")
                .blueVerifiedOnly(true)
                .businessEmailOnly(true)
                .build()

        val responses =
            listOf(
                client.x().users().retrieveSimilar(params),
                client.async().x().users().retrieveSimilar(params),
            )

        verify(
            2,
            getRequestedFor(urlPathEqualTo("/x/users/2102882545427714266/similar"))
                .withQueryParam("bioContains", equalTo("bioContains"))
                .withQueryParam("blueVerifiedOnly", equalTo("true"))
                .withQueryParam("businessEmailOnly", equalTo("true")),
        )
        responses.forEach { response ->
            assertPaginatedUsersExample(response)
        }
    }

    @Test
    suspend fun retrieveSubscriptions() {
        stubFor(
            get(urlPathEqualTo("/x/users/2102882545427714266/subscriptions"))
                .willReturn(okJson(ExampleResponses.read("examples/PaginatedUsers.json")))
        )
        val params =
            UserRetrieveSubscriptionsParams.builder()
                .id("2102882545427714266")
                .bioContains("bioContains")
                .blueVerifiedOnly(true)
                .businessEmailOnly(true)
                .build()

        val responses =
            listOf(
                client.x().users().retrieveSubscriptions(params),
                client.async().x().users().retrieveSubscriptions(params),
            )

        verify(
            2,
            getRequestedFor(urlPathEqualTo("/x/users/2102882545427714266/subscriptions"))
                .withQueryParam("bioContains", equalTo("bioContains"))
                .withQueryParam("blueVerifiedOnly", equalTo("true"))
                .withQueryParam("businessEmailOnly", equalTo("true")),
        )
        responses.forEach { response ->
            assertPaginatedUsersExample(response)
        }
    }

    @Test
    suspend fun translateBio() {
        stubFor(
            get(urlPathEqualTo("/x/users/2102882545427714266/translation"))
                .willReturn(okJson(ExampleResponses.read("examples/x/users/translateBio.json")))
        )
        val params =
            UserTranslateBioParams.builder().id("2102882545427714266").language("es").build()

        val responses =
            listOf(
                client.x().users().translateBio(params),
                client.async().x().users().translateBio(params),
            )

        verify(
            2,
            getRequestedFor(urlPathEqualTo("/x/users/2102882545427714266/translation"))
                .withQueryParam("language", equalTo("es")),
        )
        responses.forEach { response ->
            assertThat(response.translation()!!.language()).isEqualTo("es")
            assertThat(response.translation()!!.text())
                .isEqualTo("Haciendo lo aparentemente imposible, posible. ✨")
            assertThat(response.translation()!!.userId()).isEqualTo("11348282")
            assertThat(response.translation()!!.userName()).isEqualTo("NASA")
            assertThat(response.translation()!!.sourceLanguage()).isEqualTo("en")
        }
    }

    @Test
    suspend fun translateBioWithoutBio() {
        stubFor(
            get(urlPathEqualTo("/x/users/2102882545427714266/translation"))
                .willReturn(
                    okJson(ExampleResponses.read("examples/x/users/translateBioWithoutBio.json"))
                )
        )
        val params =
            UserTranslateBioParams.builder().id("2102882545427714266").language("es").build()

        val responses =
            listOf(
                client.x().users().translateBio(params),
                client.async().x().users().translateBio(params),
            )

        verify(
            2,
            getRequestedFor(urlPathEqualTo("/x/users/2102882545427714266/translation"))
                .withQueryParam("language", equalTo("es")),
        )
        responses.forEach { response ->
            assertThat(response.translation()).isNull()
            assertThat(response.message()).isEqualTo("This profile has no bio to translate.")
        }
    }
}
