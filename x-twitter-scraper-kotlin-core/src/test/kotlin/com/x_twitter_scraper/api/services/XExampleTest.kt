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
import com.x_twitter_scraper.api.models.x.XAutocompleteSearchParams
import com.x_twitter_scraper.api.models.x.XGetHashflagsParams
import com.x_twitter_scraper.api.models.x.XGetNewsStoryVersionsParams
import com.x_twitter_scraper.api.models.x.XGetTrendLocationsParams
import com.x_twitter_scraper.api.models.x.XGetTrendsHistoryParams
import com.x_twitter_scraper.api.models.x.XGetTrendsParams
import com.x_twitter_scraper.api.models.x.XSearchPlacesParams
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.ResourceLock

@WireMockTest
@ResourceLock("https://github.com/wiremock/wiremock/issues/169")
internal class XExampleTest {

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
    suspend fun autocompleteSearch() {
        stubFor(
            get(urlPathEqualTo("/x/search/autocomplete"))
                .willReturn(okJson(ExampleResponses.read("examples/SearchSuggestions.json")))
        )
        val params =
            XAutocompleteSearchParams.builder().q("nasa").limit(20L).types("users,topics").build()

        val responses =
            listOf(
                client.x().autocompleteSearch(params),
                client.async().x().autocompleteSearch(params),
            )

        verify(
            2,
            getRequestedFor(urlPathEqualTo("/x/search/autocomplete"))
                .withQueryParam("q", equalTo("nasa"))
                .withQueryParam("limit", equalTo("20"))
                .withQueryParam("types", equalTo("users,topics")),
        )
        responses.forEach { response ->
            assertThat(response.cashtags()).hasSize(0)
            assertThat(response.count()).isEqualTo(2L)
            assertThat(response.hashtags()).hasSize(0)
            assertThat(response.query()).isEqualTo("nasa")
            assertThat(response.topics()).hasSize(1)
            assertThat(response.topics()[0].topic()).isEqualTo("nasa")
            assertThat(response.topics()[0].inline()).isEqualTo(false)
            assertThat(response.topics()[0].score()).isEqualTo(0.0)
            assertThat(response.topics()[0].tokens()!!).hasSize(0)
            assertThat(response.users()).hasSize(1)
            assertThat(response.users()[0].id()).isEqualTo("11348282")
            assertThat(response.users()[0].name()).isEqualTo("NASA")
            assertThat(response.users()[0].username()).isEqualTo("NASA")
            assertThat(response.users()[0].badges()!!).hasSize(0)
            assertThat(response.users()[0].inline()).isEqualTo(false)
            assertThat(response.users()[0].isBlueVerified()).isEqualTo(true)
            assertThat(response.users()[0].isPersonaMediaGenable()).isEqualTo(false)
            assertThat(response.users()[0].isVerified()).isEqualTo(false)
            assertThat(response.users()[0].location()).isEqualTo("Pale Blue Dot")
            assertThat(response.users()[0].profilePicture())
                .isEqualTo(
                    "https://pbs.twimg.com/profile_images/1321163587679784960/0ZxKlEKB_normal.jpg"
                )
            assertThat(response.users()[0].protected()).isEqualTo(false)
            assertThat(response.users()[0].score()).isEqualTo(0.0)
            assertThat(response.users()[0].tokens()!!).hasSize(0)
            assertThat(response.users()[0].verified()).isEqualTo(true)
            assertThat(response.users()[0].verifiedType()).isEqualTo("Government")
        }
    }

    @Test
    suspend fun getHashflags() {
        stubFor(
            get(urlPathEqualTo("/x/hashflags"))
                .willReturn(okJson(ExampleResponses.read("examples/x/getHashflags.json")))
        )
        val params = XGetHashflagsParams.builder().activeOnly(true).limit(0L).q("q").build()

        val responses =
            listOf(
                client.x().getHashflags(params),
                client.async().x().getHashflags(params),
            )

        verify(
            2,
            getRequestedFor(urlPathEqualTo("/x/hashflags"))
                .withQueryParam("activeOnly", equalTo("true"))
                .withQueryParam("limit", equalTo("0"))
                .withQueryParam("q", equalTo("q")),
        )
        responses.forEach { response ->
            assertThat(response.count()).isEqualTo(1L)
            assertThat(response.hashflags()).hasSize(1)
            assertThat(response.hashflags()[0].animations()).hasSize(0)
            assertThat(response.hashflags()[0].assetUrl())
                .isEqualTo(
                    "https://abs.twimg.com/hashflags/BF-16692_AIon2_jp_Hashmoji_v3/BF-16692_AIon2_jp_Hashmoji_v3.png"
                )
            assertThat(response.hashflags()[0].endsAt())
                .isEqualTo(OffsetDateTime.parse("2027-01-03T14:59:00.000Z"))
            assertThat(response.hashflags()[0].hashtag()).isEqualTo("AION2")
            assertThat(response.hashflags()[0].isHashfettiEnabled()).isEqualTo(false)
            assertThat(response.hashflags()[0].startsAt())
                .isEqualTo(OffsetDateTime.parse("2026-10-02T10:00:00.000Z"))
            assertThat(response.total()).isEqualTo(1L)
        }
    }

    @Test
    suspend fun searchPlaces() {
        stubFor(
            get(urlPathEqualTo("/x/places/search"))
                .willReturn(okJson(ExampleResponses.read("examples/x/searchPlaces.json")))
        )
        val params = XSearchPlacesParams.builder().q("q").limit(0L).build()

        val responses =
            listOf(
                client.x().searchPlaces(params),
                client.async().x().searchPlaces(params),
            )

        verify(
            2,
            getRequestedFor(urlPathEqualTo("/x/places/search"))
                .withQueryParam("q", equalTo("q"))
                .withQueryParam("limit", equalTo("0")),
        )
        responses.forEach { response ->
            assertThat(response.count()).isEqualTo(1L)
            assertThat(response.places()).hasSize(1)
            assertThat(response.places()[0].boundingBox()!!.coordinates()).hasSize(1)
            assertThat(response.places()[0].boundingBox()!!.coordinates()[0]).hasSize(4)
            assertThat(response.places()[0].boundingBox()!!.coordinates()[0][0])
                .containsExactly(28.6321043, 40.8027337)
            assertThat(response.places()[0].boundingBox()!!.type()).isEqualTo("Polygon")
            assertThat(response.places()[0].centroid()!!)
                .containsExactly(28.84318392941324, 41.054196399999995)
            assertThat(response.places()[0].containedWithin()).hasSize(1)
            assertThat(response.places()[0].containedWithin()[0].boundingBox()!!.type())
                .isEqualTo("Polygon")
            assertThat(response.places()[0].containedWithin()[0].centroid()!!)
                .containsExactly(28.84318392941324, 41.054196399999995)
            assertThat(response.places()[0].containedWithin()[0].country()).isEqualTo("Turkey")
            assertThat(response.places()[0].containedWithin()[0].countryCode()).isEqualTo("TR")
            assertThat(response.places()[0].containedWithin()[0].fullName())
                .isEqualTo("İstanbul, Türkiye")
            assertThat(response.places()[0].containedWithin()[0].id()).isEqualTo("5e02a0f0d91c76d2")
            assertThat(response.places()[0].containedWithin()[0].name()).isEqualTo("İstanbul")
            assertThat(response.places()[0].containedWithin()[0].placeType()).isEqualTo("city")
            assertThat(response.places()[0].containedWithin()[0].url())
                .isEqualTo("https://api.twitter.com/1.1/geo/id/5e02a0f0d91c76d2.json")
            assertThat(response.places()[0].country()).isEqualTo("Turkey")
            assertThat(response.places()[0].countryCode()).isEqualTo("TR")
            assertThat(response.places()[0].fullName()).isEqualTo("İstanbul, Türkiye")
            assertThat(response.places()[0].id()).isEqualTo("5e02a0f0d91c76d2")
            assertThat(response.places()[0].name()).isEqualTo("İstanbul")
            assertThat(response.places()[0].placeType()).isEqualTo("city")
            assertThat(response.places()[0].url())
                .isEqualTo("https://api.twitter.com/1.1/geo/id/5e02a0f0d91c76d2.json")
            assertThat(response.total()).isEqualTo(1L)
        }
    }

    @Test
    suspend fun getTrendLocations() {
        stubFor(
            get(urlPathEqualTo("/x/trends/locations"))
                .willReturn(okJson(ExampleResponses.read("examples/x/getTrendLocations.json")))
        )
        val params =
            XGetTrendLocationsParams.builder()
                .country("country")
                .limit(0L)
                .placeType(XGetTrendLocationsParams.PlaceType.COUNTRY)
                .build()

        val responses =
            listOf(
                client.x().getTrendLocations(params),
                client.async().x().getTrendLocations(params),
            )

        verify(
            2,
            getRequestedFor(urlPathEqualTo("/x/trends/locations"))
                .withQueryParam("country", equalTo("country"))
                .withQueryParam("limit", equalTo("0"))
                .withQueryParam("placeType", equalTo("country")),
        )
        responses.forEach { response ->
            assertThat(response.count()).isEqualTo(1L)
            assertThat(response.locations()).hasSize(1)
            assertThat(response.locations()[0].country()).isEqualTo("Turkey")
            assertThat(response.locations()[0].countryCode()).isEqualTo("TR")
            assertThat(response.locations()[0].name()).isEqualTo("Istanbul")
            assertThat(response.locations()[0].parentWoeid()).isEqualTo(23424969L)
            assertThat(response.locations()[0].placeType()).isEqualTo("Town")
            assertThat(response.locations()[0].placeTypeCode()).isEqualTo(7L)
            assertThat(response.locations()[0].url())
                .isEqualTo("http://where.yahooapis.com/v1/place/2344116")
            assertThat(response.locations()[0].woeid()).isEqualTo(2344116L)
            assertThat(response.total()).isEqualTo(1L)
        }
    }

    @Test
    suspend fun getTrendsHistory() {
        stubFor(
            get(urlPathEqualTo("/x/trends/history"))
                .willReturn(okJson(ExampleResponses.read("examples/x/getTrendsHistory.json")))
        )
        val params =
            XGetTrendsHistoryParams.builder().count(30L).country("country").every("1h").build()

        val responses =
            listOf(
                client.x().getTrendsHistory(params),
                client.async().x().getTrendsHistory(params),
            )

        verify(
            2,
            getRequestedFor(urlPathEqualTo("/x/trends/history"))
                .withQueryParam("count", equalTo("30"))
                .withQueryParam("country", equalTo("country"))
                .withQueryParam("every", equalTo("1h")),
        )
        responses.forEach { response ->
            assertThat(response.count()).isEqualTo(1L)
            assertThat(response.fromTime())
                .isEqualTo(OffsetDateTime.parse("2026-10-13T00:00:00.000Z"))
            assertThat(response.hasNextPage()).isEqualTo(false)
            assertThat(response.nextCursor()).isEqualTo("")
            assertThat(response.snapshots()).hasSize(1)
            assertThat(response.snapshots()[0].capturedAt())
                .isEqualTo(OffsetDateTime.parse("2026-10-13T14:00:26.000Z"))
            assertThat(response.snapshots()[0].count()).isEqualTo(1L)
            assertThat(response.snapshots()[0].trends()).hasSize(1)
            assertThat(response.snapshots()[0].trends()[0].name()).isEqualTo("#AI")
            assertThat(response.snapshots()[0].trends()[0].description())
                .isEqualTo("Artificial intelligence discussions")
            assertThat(response.snapshots()[0].trends()[0].promotedContent()).isNull()
            assertThat(response.snapshots()[0].trends()[0].query()).isEqualTo("%23AI")
            assertThat(response.snapshots()[0].trends()[0].rank()).isEqualTo(1L)
            assertThat(response.snapshots()[0].trends()[0].tweetVolume()).isEqualTo(250000L)
            assertThat(response.snapshots()[0].trends()[0].url())
                .isEqualTo("https://x.com/search?q=%23AI")
            assertThat(response.toTime())
                .isEqualTo(OffsetDateTime.parse("2026-10-14T00:00:00.000Z"))
            assertThat(response.woeid()).isEqualTo(1L)
        }
    }

    @Test
    suspend fun getNewsStoryVersions() {
        stubFor(
            get(urlPathEqualTo("/x/news/2110209733533598095/versions"))
                .willReturn(okJson(ExampleResponses.read("examples/x/getNewsStoryVersions.json")))
        )
        val params = XGetNewsStoryVersionsParams.builder().id("2110209733533598095").build()

        val responses =
            listOf(
                client.x().getNewsStoryVersions(params),
                client.async().x().getNewsStoryVersions(params),
            )

        verify(
            2,
            getRequestedFor(urlPathEqualTo("/x/news/2110209733533598095/versions")),
        )
        responses.forEach { response ->
            assertThat(response.count()).isEqualTo(1L)
            assertThat(response.id()).isEqualTo("2110209733533598095")
            assertThat(response.versions()).hasSize(1)
            assertThat(response.versions()[0].createdAt())
                .isEqualTo(OffsetDateTime.parse("2026-10-14T03:30:42.536Z"))
            assertThat(response.versions()[0].headline())
                .isEqualTo(
                    "Elon Musk Pushes Starlink for India's Remote Areas Amid Regulatory Hurdles"
                )
            assertThat(response.versions()[0].id()).isEqualTo("2110211669641105725")
            assertThat(response.versions()[0].text())
                .isEqualTo(
                    "Musk has accused Reliance Industries' Mukesh Ambani of blocking Starlink's entry to protect local telecom giants like Jio, while India's government insists on equal treatment for all applicants, including ongoing security reviews. In Vietnam, Starlink already provides free internet to isolated spots for tourism and disaster response, highlighting its potential for hard-to-reach areas. Yet challengers note India's cheap mobile data from Jio and Airtel covers most needs, raising questions on pricing and national security as Starlink seeks final clearances."
                )
        }
    }

    @Test
    suspend fun getTrends() {
        stubFor(
            get(urlPathEqualTo("/x/trends"))
                .willReturn(okJson(ExampleResponses.read("examples/x/getTrends.json")))
        )
        val params =
            XGetTrendsParams.builder()
                .at("2026-10-13T14:00:00Z")
                .count(30L)
                .country("country")
                .build()

        val responses =
            listOf(
                client.x().getTrends(params),
                client.async().x().getTrends(params),
            )

        verify(
            2,
            getRequestedFor(urlPathEqualTo("/x/trends"))
                .withQueryParam("at", equalTo("2026-10-13T14:00:00Z"))
                .withQueryParam("count", equalTo("30"))
                .withQueryParam("country", equalTo("country")),
        )
        responses.forEach { response ->
            assertThat(response.count()).isEqualTo(30L)
            assertThat(response.trends()).hasSize(1)
            assertThat(response.trends()[0].name()).isEqualTo("#AI")
            assertThat(response.trends()[0].description())
                .isEqualTo("Artificial intelligence discussions")
            assertThat(response.trends()[0].promotedContent()).isNull()
            assertThat(response.trends()[0].query()).isEqualTo("%23AI")
            assertThat(response.trends()[0].rank()).isEqualTo(1L)
            assertThat(response.trends()[0].tweetVolume()).isEqualTo(250000L)
            assertThat(response.trends()[0].url()).isEqualTo("https://x.com/search?q=%23AI")
            assertThat(response.woeid()).isEqualTo(1L)
            assertThat(response.capturedAt())
                .isEqualTo(OffsetDateTime.parse("2026-10-13T14:00:26.000Z"))
            assertThat(response.location()!!.country()).isEqualTo("Turkey")
            assertThat(response.location()!!.countryCode()).isEqualTo("TR")
            assertThat(response.location()!!.name()).isEqualTo("Istanbul")
            assertThat(response.location()!!.parentWoeid()).isEqualTo(23424969L)
            assertThat(response.location()!!.placeType()).isEqualTo("Town")
            assertThat(response.location()!!.placeTypeCode()).isEqualTo(7L)
            assertThat(response.location()!!.url())
                .isEqualTo("http://where.yahooapis.com/v1/place/2344116")
            assertThat(response.location()!!.woeid()).isEqualTo(2344116L)
            assertThat(response.requestedAt())
                .isEqualTo(OffsetDateTime.parse("2026-10-13T14:00:00.000Z"))
        }
    }
}
