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
import com.x_twitter_scraper.api.models.x.communitynotes.CommunityNoteListContributorDeletedNotesParams
import com.x_twitter_scraper.api.models.x.communitynotes.CommunityNoteListContributorNotesParams
import com.x_twitter_scraper.api.models.x.communitynotes.CommunityNoteRetrieveContributorParams
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.ResourceLock

@WireMockTest
@ResourceLock("https://github.com/wiremock/wiremock/issues/169")
internal class XCommunityNotesExampleTest {

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
    suspend fun retrieveContributor() {
        stubFor(
            get(urlPathEqualTo("/x/community-notes/contributors/ardent-beach-friarbird"))
                .willReturn(
                    okJson(
                        ExampleResponses.read("examples/x/communityNotes/retrieveContributor.json")
                    )
                )
        )
        val params =
            CommunityNoteRetrieveContributorParams.builder().alias("ardent-beach-friarbird").build()

        val responses =
            listOf(
                client.x().communityNotes().retrieveContributor(params),
                client.async().x().communityNotes().retrieveContributor(params),
            )

        verify(
            2,
            getRequestedFor(
                urlPathEqualTo("/x/community-notes/contributors/ardent-beach-friarbird")
            ),
        )
        responses.forEach { response ->
            assertThat(response.contributor().alias()).isEqualTo("ardent-beach-friarbird")
            assertThat(response.contributor().deletedNotesCount()).isEqualTo(0L)
            assertThat(response.contributor().isApiContributor()).isEqualTo(false)
            assertThat(response.contributor().isTopWriter()).isEqualTo(false)
            assertThat(response.contributor().notesCount()!!.awaitingMoreRatings()).isEqualTo(18L)
            assertThat(response.contributor().notesCount()!!.currentlyRatedHelpful()).isEqualTo(1L)
            assertThat(response.contributor().notesCount()!!.currentlyRatedNotHelpful())
                .isEqualTo(2L)
            assertThat(response.contributor().notesCount()!!.updatedAt())
                .isEqualTo(OffsetDateTime.parse("2026-10-12T22:18:40.096Z"))
            assertThat(response.contributor().ratingsCount()!!.awaitingMoreRatings())
                .isEqualTo(703L)
            assertThat(response.contributor().ratingsCount()!!.ratedAfterDecision()).isEqualTo(154L)
            assertThat(response.contributor().ratingsCount()!!.successful()!!.helpfulCount())
                .isEqualTo(27L)
            assertThat(response.contributor().ratingsCount()!!.successful()!!.notHelpfulCount())
                .isEqualTo(22L)
            assertThat(response.contributor().ratingsCount()!!.successful()!!.total())
                .isEqualTo(49L)
            assertThat(response.contributor().ratingsCount()!!.unsuccessful()!!.helpfulCount())
                .isEqualTo(15L)
            assertThat(response.contributor().ratingsCount()!!.unsuccessful()!!.notHelpfulCount())
                .isEqualTo(4L)
            assertThat(response.contributor().ratingsCount()!!.updatedAt())
                .isEqualTo(OffsetDateTime.parse("2026-10-12T22:50:13.228Z"))
        }
    }

    @Test
    suspend fun listContributorNotes() {
        stubFor(
            get(urlPathEqualTo("/x/community-notes/contributors/ardent-beach-friarbird/notes"))
                .willReturn(
                    okJson(
                        ExampleResponses.read("examples/x/communityNotes/listContributorNotes.json")
                    )
                )
        )
        val params =
            CommunityNoteListContributorNotesParams.builder()
                .alias("ardent-beach-friarbird")
                .pageSize(1L)
                .build()

        val responses =
            listOf(
                client.x().communityNotes().listContributorNotes(params),
                client.async().x().communityNotes().listContributorNotes(params),
            )

        verify(
            2,
            getRequestedFor(
                    urlPathEqualTo("/x/community-notes/contributors/ardent-beach-friarbird/notes")
                )
                .withQueryParam("pageSize", equalTo("1")),
        )
        responses.forEach { response ->
            assertThat(response.hasNextPage()).isEqualTo(true)
            assertThat(response.nextCursor()).isEqualTo("-1767098234260")
            assertThat(response.notes()).hasSize(1)
            assertThat(response.notes()[0].id()).isEqualTo("2026375778254868976")
            assertThat(response.notes()[0].abovePcrhThreshold()).isEqualTo(false)
            assertThat(response.notes()[0].appealStatus()).isEqualTo("NotAppealed")
            assertThat(response.notes()[0].classification())
                .isEqualTo("MisinformedOrPotentiallyMisleading")
            assertThat(response.notes()[0].contributor()!!.alias())
                .isEqualTo("ardent-beach-friarbird")
            assertThat(response.notes()[0].contributor()!!.notesCount()!!.currentlyRatedHelpful())
                .isEqualTo(1L)
            assertThat(
                    response.notes()[0].contributor()!!.notesCount()!!.currentlyRatedNotHelpful()
                )
                .isEqualTo(2L)
            assertThat(response.notes()[0].contributor()!!.ratingsCount()!!.successful()!!.total())
                .isEqualTo(49L)
            assertThat(
                    response
                        .notes()[0]
                        .contributor()!!
                        .ratingsCount()!!
                        .unsuccessful()!!
                        .helpfulCount()
                )
                .isEqualTo(15L)
            assertThat(
                    response
                        .notes()[0]
                        .contributor()!!
                        .ratingsCount()!!
                        .unsuccessful()!!
                        .notHelpfulCount()
                )
                .isEqualTo(4L)
            assertThat(response.notes()[0].createdAt())
                .isEqualTo(OffsetDateTime.parse("2026-02-24T19:16:48.435Z"))
            assertThat(response.notes()[0].decidedBy()).isEqualTo("CoreModel (v1.1)")
            assertThat(response.notes()[0].deleted()).isEqualTo(false)
            assertThat(response.notes()[0].entities()!!).hasSize(1)
            assertThat(response.notes()[0].entities()!![0].fromIndex()).isEqualTo(244L)
            assertThat(response.notes()[0].entities()!![0].ref()!!.type()).isEqualTo("TimelineUrl")
            assertThat(response.notes()[0].entities()!![0].ref()!!.url())
                .isEqualTo("https://t.co/7fJbcLrO1k")
            assertThat(response.notes()[0].entities()!![0].toIndex()).isEqualTo(340L)
            assertThat(response.notes()[0].fullyVisibleModel()).isEqualTo(true)
            assertThat(response.notes()[0].helpfulTags()!!)
                .containsExactly("ImportantContext", "Clear")
            assertThat(response.notes()[0].isApiAuthor()).isEqualTo(false)
            assertThat(response.notes()[0].isMediaNote()).isEqualTo(false)
            assertThat(response.notes()[0].isTranslatable()).isEqualTo(false)
            assertThat(response.notes()[0].isUrlNote()).isEqualTo(false)
            assertThat(response.notes()[0].language()).isEqualTo("en")
            assertThat(response.notes()[0].mediaNoteMatchCount()).isEqualTo(0L)
            assertThat(response.notes()[0].post()!!.id()).isEqualTo("2026359315657805939")
            assertThat(response.notes()[0].post()!!.text())
                .isEqualTo(
                    "Getting Global Age Assurance Right: What We Got Wrong and What’s Changing. \n\nRead the update: https://t.co/h7aRGmN7xW"
                )
            assertThat(response.notes()[0].post()!!.createdAt())
                .isEqualTo("Tue Feb 24 18:11:23 +0000 2026")
            assertThat(response.notes()[0].post()!!.retweetCount()).isEqualTo(542L)
            assertThat(response.notes()[0].post()!!.replyCount()).isEqualTo(4197L)
            assertThat(response.notes()[0].post()!!.likeCount()).isEqualTo(3990L)
            assertThat(response.notes()[0].post()!!.quoteCount()).isEqualTo(1122L)
            assertThat(response.notes()[0].post()!!.viewCount()).isEqualTo(7038435L)
            assertThat(response.notes()[0].post()!!.bookmarkCount()).isEqualTo(1472L)
            assertThat(response.notes()[0].post()!!.lang()).isEqualTo("en")
            assertThat(response.notes()[0].post()!!.url())
                .isEqualTo("https://x.com/discord/status/2026359315657805939")
            assertThat(response.notes()[0].post()!!.author()!!.id()).isEqualTo("3065618342")
            assertThat(response.notes()[0].post()!!.author()!!.username()).isEqualTo("discord")
            assertThat(response.notes()[0].post()!!.author()!!.name()).isEqualTo("Discord")
            assertThat(response.notes()[0].post()!!.author()!!.verifiedType()).isEqualTo("Business")
            assertThat(response.notes()[0].postId()).isEqualTo("2026359315657805939")
            assertThat(response.notes()[0].ratingStatus()).isEqualTo("CurrentlyRatedHelpful")
            assertThat(response.notes()[0].text())
                .isEqualTo(
                    "Since this is a wall of text article and the headline doesn't say, this quick-to-read context is necessary:\n\nDiscord is STILL rolling out Age Verification methods, just delayed to the second half of 2026 per the very own article in this post.\n\nhttps://discord.com/blog/getting-global-age-assurance-right-what-we-got-wrong-and-whats-changing"
                )
            assertThat(response.notes()[0].timeToHelpfulFromNoteMs()).isEqualTo(132890587L)
        }
    }

    @Test
    suspend fun listContributorDeletedNotes() {
        stubFor(
            get(
                    urlPathEqualTo(
                        "/x/community-notes/contributors/ardent-beach-friarbird/deleted-notes"
                    )
                )
                .willReturn(
                    okJson(
                        ExampleResponses.read(
                            "examples/x/communityNotes/listContributorDeletedNotes.json"
                        )
                    )
                )
        )
        val params =
            CommunityNoteListContributorDeletedNotesParams.builder()
                .alias("ardent-beach-friarbird")
                .pageSize(1L)
                .build()

        val responses =
            listOf(
                client.x().communityNotes().listContributorDeletedNotes(params),
                client.async().x().communityNotes().listContributorDeletedNotes(params),
            )

        verify(
            2,
            getRequestedFor(
                    urlPathEqualTo(
                        "/x/community-notes/contributors/ardent-beach-friarbird/deleted-notes"
                    )
                )
                .withQueryParam("pageSize", equalTo("1")),
        )
        responses.forEach { response ->
            assertThat(response.hasNextPage()).isEqualTo(false)
            assertThat(response.nextCursor()).isEqualTo("")
            assertThat(response.notes()).hasSize(1)
            assertThat(response.notes()[0].id()).isEqualTo("2078950499982696756")
            assertThat(response.notes()[0].abovePcrhThreshold()).isEqualTo(false)
            assertThat(response.notes()[0].appealStatus()).isEqualTo("NotAppealed")
            assertThat(response.notes()[0].createdAt())
                .isEqualTo(OffsetDateTime.parse("2026-07-19T21:09:58.885Z"))
            assertThat(response.notes()[0].decidedBy()).isEqualTo("CoreModel (v1.1)")
            assertThat(response.notes()[0].deleted()).isEqualTo(true)
            assertThat(response.notes()[0].fullyVisibleModel()).isEqualTo(true)
            assertThat(response.notes()[0].isApiAuthor()).isEqualTo(false)
            assertThat(response.notes()[0].isMediaNote()).isEqualTo(false)
            assertThat(response.notes()[0].isUrlNote()).isEqualTo(false)
            assertThat(response.notes()[0].mediaNoteMatchCount()).isEqualTo(0L)
            assertThat(response.notes()[0].notHelpfulTags()!!)
                .containsExactly("MissingKeyPoints", "Incorrect")
            assertThat(response.notes()[0].ratingStatus()).isEqualTo("CurrentlyRatedNotHelpful")
            assertThat(response.notes()[0].tombstone()).isEqualTo("This note has been deleted")
        }
    }
}
