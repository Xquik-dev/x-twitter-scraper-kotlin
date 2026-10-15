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
import com.x_twitter_scraper.api.models.x.communities.CommunityFindParams
import com.x_twitter_scraper.api.models.x.communities.CommunityListPopularParams
import com.x_twitter_scraper.api.models.x.communities.CommunityListSuggestedParams
import com.x_twitter_scraper.api.models.x.communities.CommunityListTopicsParams
import com.x_twitter_scraper.api.models.x.communities.CommunitySearchMembersParams
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.ResourceLock

@WireMockTest
@ResourceLock("https://github.com/wiremock/wiremock/issues/169")
internal class XCommunitiesExampleTest {

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
    suspend fun find() {
        stubFor(
            get(urlPathEqualTo("/x/communities/find"))
                .willReturn(okJson(ExampleResponses.read("examples/x/communities/find.json")))
        )
        val params = CommunityFindParams.builder().q("q").pageSize(1L).build()

        val responses =
            listOf(
                client.x().communities().find(params),
                client.async().x().communities().find(params),
            )

        verify(
            2,
            getRequestedFor(urlPathEqualTo("/x/communities/find"))
                .withQueryParam("q", equalTo("q"))
                .withQueryParam("pageSize", equalTo("1")),
        )
        responses.forEach { response ->
            assertThat(response.communities()).hasSize(1)
            assertCommunityExample(response.communities()[0])
            assertThat(response.hasNextPage()).isEqualTo(true)
            assertThat(response.nextCursor()).isEqualTo("WzM1LjUxMjQ5N10=")
        }
    }

    @Test
    suspend fun listPopular() {
        stubFor(
            get(urlPathEqualTo("/x/communities/popular"))
                .willReturn(
                    okJson(ExampleResponses.read("examples/x/communities/listPopular.json"))
                )
        )
        val params =
            CommunityListPopularParams.builder()
                .pageSize(1L)
                .topic("topic")
                .topicId("topicId")
                .build()

        val responses =
            listOf(
                client.x().communities().listPopular(params),
                client.async().x().communities().listPopular(params),
            )

        verify(
            2,
            getRequestedFor(urlPathEqualTo("/x/communities/popular"))
                .withQueryParam("pageSize", equalTo("1"))
                .withQueryParam("topic", equalTo("topic"))
                .withQueryParam("topicId", equalTo("topicId")),
        )
        responses.forEach { response ->
            assertThat(response.communities()).hasSize(1)
            assertCommunityExample(response.communities()[0])
            assertThat(response.hasNextPage()).isEqualTo(true)
            assertThat(response.nextCursor()).isEqualTo("WzM1LjUxMjQ5N10=")
        }
    }

    @Test
    suspend fun listSuggested() {
        stubFor(
            get(urlPathEqualTo("/x/communities/suggested"))
                .willReturn(
                    okJson(ExampleResponses.read("examples/x/communities/listSuggested.json"))
                )
        )
        val params = CommunityListSuggestedParams.builder().limit(1L).build()

        val responses =
            listOf(
                client.x().communities().listSuggested(params),
                client.async().x().communities().listSuggested(params),
            )

        verify(
            2,
            getRequestedFor(urlPathEqualTo("/x/communities/suggested"))
                .withQueryParam("limit", equalTo("1")),
        )
        responses.forEach { response ->
            assertThat(response.communities()).hasSize(1)
            assertCommunityExample(response.communities()[0])
            assertThat(response.count()).isEqualTo(1L)
        }
    }

    @Test
    suspend fun listTopics() {
        stubFor(
            get(urlPathEqualTo("/x/communities/topics"))
                .willReturn(okJson(ExampleResponses.read("examples/x/communities/listTopics.json")))
        )
        val params = CommunityListTopicsParams.builder().limit(1L).build()

        val responses =
            listOf(
                client.x().communities().listTopics(params),
                client.async().x().communities().listTopics(params),
            )

        verify(
            2,
            getRequestedFor(urlPathEqualTo("/x/communities/topics"))
                .withQueryParam("limit", equalTo("1")),
        )
        responses.forEach { response ->
            assertThat(response.count()).isEqualTo(1L)
            assertThat(response.topics()).hasSize(1)
            assertThat(response.topics()[0].id()).isEqualTo("301")
            assertThat(response.topics()[0].name()).isEqualTo("Technology")
            assertThat(response.topics()[0].subtopics()).hasSize(2)
            assertThat(response.topics()[0].subtopics()[0].id()).isEqualTo("302")
            assertThat(response.topics()[0].subtopics()[0].name())
                .isEqualTo("Artificial Intelligence")
            assertThat(response.topics()[0].subtopics()[1].id()).isEqualTo("303")
            assertThat(response.topics()[0].subtopics()[1].name()).isEqualTo("Software")
        }
    }

    @Test
    suspend fun searchMembers() {
        stubFor(
            get(urlPathEqualTo("/x/communities/1500000000000000000/members/search"))
                .willReturn(
                    okJson(ExampleResponses.read("examples/x/communities/searchMembers.json"))
                )
        )
        val params =
            CommunitySearchMembersParams.builder()
                .id("1500000000000000000")
                .q("q")
                .bioContains("bioContains")
                .blueVerifiedOnly(true)
                .businessEmailOnly(true)
                .build()

        val responses =
            listOf(
                client.x().communities().searchMembers(params),
                client.async().x().communities().searchMembers(params),
            )

        verify(
            2,
            getRequestedFor(urlPathEqualTo("/x/communities/1500000000000000000/members/search"))
                .withQueryParam("q", equalTo("q"))
                .withQueryParam("bioContains", equalTo("bioContains"))
                .withQueryParam("blueVerifiedOnly", equalTo("true"))
                .withQueryParam("businessEmailOnly", equalTo("true")),
        )
        responses.forEach { response ->
            assertThat(response.hasNextPage()).isEqualTo(false)
            assertThat(response.nextCursor()).isEqualTo("")
            assertThat(response.users()).hasSize(1)
            assertThat(response.users()[0].id()).isEqualTo("641633")
            assertThat(response.users()[0].username()).isEqualTo("marckohlbrugge")
            assertThat(response.users()[0].name()).isEqualTo("Marc Köhlbrugge")
            assertThat(response.users()[0].communityRole()).isEqualTo("Admin")
            assertThat(response.users()[0].profilePicture())
                .isEqualTo(
                    "https://pbs.twimg.com/profile_images/1857124042433572864/iMivVaYP_normal.jpg"
                )
            assertThat(response.users()[0].isBlueVerified()).isEqualTo(true)
        }
    }
}
