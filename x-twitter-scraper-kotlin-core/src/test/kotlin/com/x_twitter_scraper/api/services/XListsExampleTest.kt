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
import com.x_twitter_scraper.api.models.x.lists.ListRetrieveParams
import com.x_twitter_scraper.api.models.x.lists.ListRetrieveSearchParams
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.ResourceLock

@WireMockTest
@ResourceLock("https://github.com/wiremock/wiremock/issues/169")
internal class XListsExampleTest {

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
    suspend fun retrieve() {
        stubFor(
            get(urlPathEqualTo("/x/lists/2102882545427714266"))
                .willReturn(okJson(ExampleResponses.read("examples/x/lists/retrieve.json")))
        )
        val params = ListRetrieveParams.builder().id("2102882545427714266").build()

        val responses =
            listOf(
                client.x().lists().retrieve(params),
                client.async().x().lists().retrieve(params),
            )

        verify(
            2,
            getRequestedFor(urlPathEqualTo("/x/lists/2102882545427714266")),
        )
        responses.forEach { response ->
            assertThat(response.list().id()).isEqualTo("4143408")
            assertThat(response.list().name()).isEqualTo("NASA Centers")
            assertThat(response.list().bannerUrl())
                .isEqualTo("https://pbs.twimg.com/media/example.png")
            assertThat(response.list().createdAt())
                .isEqualTo(OffsetDateTime.parse("2009-12-02T22:41:02.000Z"))
            assertThat(response.list().description()).isEqualTo("NASA has 10 centers")
            assertThat(response.list().memberCount()).isEqualTo(11L)
            assertThat(response.list().membersContext()).isEqualTo("11 members")
            assertThat(response.list().owner()!!.id()).isEqualTo("11348282")
            assertThat(response.list().owner()!!.username()).isEqualTo("NASA")
            assertThat(response.list().owner()!!.name()).isEqualTo("NASA")
            assertThat(response.list().subscriberCount()).isEqualTo(859L)
            assertThat(response.list().url()).isEqualTo("https://x.com/i/lists/4143408")
        }
    }

    @Test
    suspend fun retrieveSearch() {
        stubFor(
            get(urlPathEqualTo("/x/lists/search"))
                .willReturn(okJson(ExampleResponses.read("examples/PaginatedLists.json")))
        )
        val params = ListRetrieveSearchParams.builder().q("q").pageSize(20L).build()

        val responses =
            listOf(
                client.x().lists().retrieveSearch(params),
                client.async().x().lists().retrieveSearch(params),
            )

        verify(
            2,
            getRequestedFor(urlPathEqualTo("/x/lists/search"))
                .withQueryParam("q", equalTo("q"))
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
}
