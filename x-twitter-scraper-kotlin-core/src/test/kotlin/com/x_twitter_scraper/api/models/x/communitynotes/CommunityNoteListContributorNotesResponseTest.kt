// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.communitynotes

import com.fasterxml.jackson.databind.node.ObjectNode
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import com.x_twitter_scraper.api.models.SearchTweet
import com.x_twitter_scraper.api.services.ExampleResponses
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CommunityNoteListContributorNotesResponseTest {

    @Test
    fun create() {
        val communityNoteListContributorNotesResponse =
            PaginatedCommunityNotes.builder()
                .hasNextPage(true)
                .nextCursor("DAACCgACGRElMJcAAA")
                .addNote(
                    PaginatedCommunityNotes.Note.builder()
                        .id("2026375778254868976")
                        .abovePcrhThreshold(true)
                        .appealStatus("NotAppealed")
                        .classification("MisinformedOrPotentiallyMisleading")
                        .contributor(
                            CommunityNotesContributor.builder()
                                .alias("ardent-beach-friarbird")
                                .deletedNotesCount(0L)
                                .isApiContributor(true)
                                .isTopWriter(true)
                                .notesCount(
                                    CommunityNotesContributor.NotesCount.builder()
                                        .awaitingMoreRatings(0L)
                                        .currentlyRatedHelpful(0L)
                                        .currentlyRatedNotHelpful(0L)
                                        .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                                        .build()
                                )
                                .ratingsCount(
                                    CommunityNotesContributor.RatingsCount.builder()
                                        .awaitingMoreRatings(0L)
                                        .ratedAfterDecision(0L)
                                        .successful(
                                            CommunityNotesRatingOutcome.builder()
                                                .helpfulCount(0L)
                                                .notHelpfulCount(0L)
                                                .total(0L)
                                                .build()
                                        )
                                        .unsuccessful(
                                            CommunityNotesRatingOutcome.builder()
                                                .helpfulCount(0L)
                                                .notHelpfulCount(0L)
                                                .total(0L)
                                                .build()
                                        )
                                        .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                                        .build()
                                )
                                .build()
                        )
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .decidedBy("CoreModel (v1.1)")
                        .deleted(true)
                        .displayUrl("displayUrl")
                        .addEntity(
                            PaginatedCommunityNotes.Note.Entity.builder()
                                .fromIndex(0L)
                                .ref(
                                    PaginatedCommunityNotes.Note.Entity.Ref.builder()
                                        .screenName("screenName")
                                        .text("text")
                                        .type("TimelineUrl")
                                        .url("https://t.co/7fJbcLrO1k")
                                        .urlType("urlType")
                                        .build()
                                )
                                .toIndex(0L)
                                .build()
                        )
                        .fullyVisibleModel(true)
                        .addHelpfulTag("ImportantContext")
                        .impressionCount(0L)
                        .isApiAuthor(true)
                        .isMediaNote(true)
                        .isTranslatable(true)
                        .isUrlNote(true)
                        .language("en")
                        .mediaNoteMatchCount(0L)
                        .addNotHelpfulTag("MissingKeyPoints")
                        .post(
                            SearchTweet.builder()
                                .id("id")
                                .bookmarkCount(0L)
                                .likeCount(0L)
                                .quoteCount(0L)
                                .replyCount(0L)
                                .retweetCount(0L)
                                .text("text")
                                .viewCount(0L)
                                .build()
                        )
                        .postId("2026359315657805939")
                        .postUnavailable(
                            PaginatedCommunityNotes.Note.PostUnavailable.builder()
                                .reason("Suspended")
                                .build()
                        )
                        .ratingStatus("CurrentlyRatedHelpful")
                        .text("text")
                        .timeToHelpfulFromNoteMs(0L)
                        .timeToHelpfulFromPostMs(0L)
                        .tombstone("This note has been deleted")
                        .build()
                )
                .build()

        assertThat(communityNoteListContributorNotesResponse.hasNextPage()).isEqualTo(true)
        assertThat(communityNoteListContributorNotesResponse.nextCursor())
            .isEqualTo("DAACCgACGRElMJcAAA")
        assertThat(communityNoteListContributorNotesResponse.notes())
            .containsExactly(
                PaginatedCommunityNotes.Note.builder()
                    .id("2026375778254868976")
                    .abovePcrhThreshold(true)
                    .appealStatus("NotAppealed")
                    .classification("MisinformedOrPotentiallyMisleading")
                    .contributor(
                        CommunityNotesContributor.builder()
                            .alias("ardent-beach-friarbird")
                            .deletedNotesCount(0L)
                            .isApiContributor(true)
                            .isTopWriter(true)
                            .notesCount(
                                CommunityNotesContributor.NotesCount.builder()
                                    .awaitingMoreRatings(0L)
                                    .currentlyRatedHelpful(0L)
                                    .currentlyRatedNotHelpful(0L)
                                    .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                                    .build()
                            )
                            .ratingsCount(
                                CommunityNotesContributor.RatingsCount.builder()
                                    .awaitingMoreRatings(0L)
                                    .ratedAfterDecision(0L)
                                    .successful(
                                        CommunityNotesRatingOutcome.builder()
                                            .helpfulCount(0L)
                                            .notHelpfulCount(0L)
                                            .total(0L)
                                            .build()
                                    )
                                    .unsuccessful(
                                        CommunityNotesRatingOutcome.builder()
                                            .helpfulCount(0L)
                                            .notHelpfulCount(0L)
                                            .total(0L)
                                            .build()
                                    )
                                    .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                                    .build()
                            )
                            .build()
                    )
                    .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .decidedBy("CoreModel (v1.1)")
                    .deleted(true)
                    .displayUrl("displayUrl")
                    .addEntity(
                        PaginatedCommunityNotes.Note.Entity.builder()
                            .fromIndex(0L)
                            .ref(
                                PaginatedCommunityNotes.Note.Entity.Ref.builder()
                                    .screenName("screenName")
                                    .text("text")
                                    .type("TimelineUrl")
                                    .url("https://t.co/7fJbcLrO1k")
                                    .urlType("urlType")
                                    .build()
                            )
                            .toIndex(0L)
                            .build()
                    )
                    .fullyVisibleModel(true)
                    .addHelpfulTag("ImportantContext")
                    .impressionCount(0L)
                    .isApiAuthor(true)
                    .isMediaNote(true)
                    .isTranslatable(true)
                    .isUrlNote(true)
                    .language("en")
                    .mediaNoteMatchCount(0L)
                    .addNotHelpfulTag("MissingKeyPoints")
                    .post(
                        SearchTweet.builder()
                            .id("id")
                            .bookmarkCount(0L)
                            .likeCount(0L)
                            .quoteCount(0L)
                            .replyCount(0L)
                            .retweetCount(0L)
                            .text("text")
                            .viewCount(0L)
                            .build()
                    )
                    .postId("2026359315657805939")
                    .postUnavailable(
                        PaginatedCommunityNotes.Note.PostUnavailable.builder()
                            .reason("Suspended")
                            .build()
                    )
                    .ratingStatus("CurrentlyRatedHelpful")
                    .text("text")
                    .timeToHelpfulFromNoteMs(0L)
                    .timeToHelpfulFromPostMs(0L)
                    .tombstone("This note has been deleted")
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val communityNoteListContributorNotesResponse =
            PaginatedCommunityNotes.builder()
                .hasNextPage(true)
                .nextCursor("DAACCgACGRElMJcAAA")
                .addNote(
                    PaginatedCommunityNotes.Note.builder()
                        .id("2026375778254868976")
                        .abovePcrhThreshold(true)
                        .appealStatus("NotAppealed")
                        .classification("MisinformedOrPotentiallyMisleading")
                        .contributor(
                            CommunityNotesContributor.builder()
                                .alias("ardent-beach-friarbird")
                                .deletedNotesCount(0L)
                                .isApiContributor(true)
                                .isTopWriter(true)
                                .notesCount(
                                    CommunityNotesContributor.NotesCount.builder()
                                        .awaitingMoreRatings(0L)
                                        .currentlyRatedHelpful(0L)
                                        .currentlyRatedNotHelpful(0L)
                                        .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                                        .build()
                                )
                                .ratingsCount(
                                    CommunityNotesContributor.RatingsCount.builder()
                                        .awaitingMoreRatings(0L)
                                        .ratedAfterDecision(0L)
                                        .successful(
                                            CommunityNotesRatingOutcome.builder()
                                                .helpfulCount(0L)
                                                .notHelpfulCount(0L)
                                                .total(0L)
                                                .build()
                                        )
                                        .unsuccessful(
                                            CommunityNotesRatingOutcome.builder()
                                                .helpfulCount(0L)
                                                .notHelpfulCount(0L)
                                                .total(0L)
                                                .build()
                                        )
                                        .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                                        .build()
                                )
                                .build()
                        )
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .decidedBy("CoreModel (v1.1)")
                        .deleted(true)
                        .displayUrl("displayUrl")
                        .addEntity(
                            PaginatedCommunityNotes.Note.Entity.builder()
                                .fromIndex(0L)
                                .ref(
                                    PaginatedCommunityNotes.Note.Entity.Ref.builder()
                                        .screenName("screenName")
                                        .text("text")
                                        .type("TimelineUrl")
                                        .url("https://t.co/7fJbcLrO1k")
                                        .urlType("urlType")
                                        .build()
                                )
                                .toIndex(0L)
                                .build()
                        )
                        .fullyVisibleModel(true)
                        .addHelpfulTag("ImportantContext")
                        .impressionCount(0L)
                        .isApiAuthor(true)
                        .isMediaNote(true)
                        .isTranslatable(true)
                        .isUrlNote(true)
                        .language("en")
                        .mediaNoteMatchCount(0L)
                        .addNotHelpfulTag("MissingKeyPoints")
                        .post(
                            SearchTweet.builder()
                                .id("id")
                                .bookmarkCount(0L)
                                .likeCount(0L)
                                .quoteCount(0L)
                                .replyCount(0L)
                                .retweetCount(0L)
                                .text("text")
                                .viewCount(0L)
                                .build()
                        )
                        .postId("2026359315657805939")
                        .postUnavailable(
                            PaginatedCommunityNotes.Note.PostUnavailable.builder()
                                .reason("Suspended")
                                .build()
                        )
                        .ratingStatus("CurrentlyRatedHelpful")
                        .text("text")
                        .timeToHelpfulFromNoteMs(0L)
                        .timeToHelpfulFromPostMs(0L)
                        .tombstone("This note has been deleted")
                        .build()
                )
                .build()

        val roundtrippedCommunityNoteListContributorNotesResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(communityNoteListContributorNotesResponse),
                jacksonTypeRef<PaginatedCommunityNotes>(),
            )

        assertThat(roundtrippedCommunityNoteListContributorNotesResponse)
            .isEqualTo(communityNoteListContributorNotesResponse)
    }

    @Test
    fun readsANoteWhosePostXNoLongerShows() {
        val json =
            jsonMapper()
                .readTree(
                    ExampleResponses.read("examples/x/communityNotes/listContributorNotes.json")
                )
        (json["notes"][0] as ObjectNode).putNull("post")

        val response = jsonMapper().treeToValue(json, PaginatedCommunityNotes::class.java)

        assertThat(response.notes()[0].post()).isNull()
        assertThat(response.isValid()).isTrue()
    }
}
