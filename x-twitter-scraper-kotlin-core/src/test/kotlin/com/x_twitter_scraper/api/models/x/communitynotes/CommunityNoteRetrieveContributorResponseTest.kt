// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.communitynotes

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CommunityNoteRetrieveContributorResponseTest {

    @Test
    fun create() {
        val communityNoteRetrieveContributorResponse =
            CommunityNoteRetrieveContributorResponse.builder()
                .contributor(
                    CommunityNoteRetrieveContributorResponse.Contributor.builder()
                        .alias("alias")
                        .deletedNotesCount(0L)
                        .isApiContributor(true)
                        .isTopWriter(true)
                        .notesCount(
                            CommunityNoteRetrieveContributorResponse.Contributor.NotesCount
                                .builder()
                                .awaitingMoreRatings(0L)
                                .currentlyRatedHelpful(0L)
                                .currentlyRatedNotHelpful(0L)
                                .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                                .build()
                        )
                        .ratingsCount(
                            CommunityNoteRetrieveContributorResponse.Contributor.RatingsCount
                                .builder()
                                .awaitingMoreRatings(0L)
                                .ratedAfterDecision(0L)
                                .successful(
                                    CommunityNoteRetrieveContributorResponse.Contributor
                                        .RatingsCount
                                        .Successful
                                        .builder()
                                        .helpfulCount(0L)
                                        .notHelpfulCount(0L)
                                        .total(0L)
                                        .build()
                                )
                                .unsuccessful(
                                    CommunityNoteRetrieveContributorResponse.Contributor
                                        .RatingsCount
                                        .Unsuccessful
                                        .builder()
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
                .build()

        assertThat(communityNoteRetrieveContributorResponse.contributor())
            .isEqualTo(
                CommunityNoteRetrieveContributorResponse.Contributor.builder()
                    .alias("alias")
                    .deletedNotesCount(0L)
                    .isApiContributor(true)
                    .isTopWriter(true)
                    .notesCount(
                        CommunityNoteRetrieveContributorResponse.Contributor.NotesCount.builder()
                            .awaitingMoreRatings(0L)
                            .currentlyRatedHelpful(0L)
                            .currentlyRatedNotHelpful(0L)
                            .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                            .build()
                    )
                    .ratingsCount(
                        CommunityNoteRetrieveContributorResponse.Contributor.RatingsCount.builder()
                            .awaitingMoreRatings(0L)
                            .ratedAfterDecision(0L)
                            .successful(
                                CommunityNoteRetrieveContributorResponse.Contributor.RatingsCount
                                    .Successful
                                    .builder()
                                    .helpfulCount(0L)
                                    .notHelpfulCount(0L)
                                    .total(0L)
                                    .build()
                            )
                            .unsuccessful(
                                CommunityNoteRetrieveContributorResponse.Contributor.RatingsCount
                                    .Unsuccessful
                                    .builder()
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
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val communityNoteRetrieveContributorResponse =
            CommunityNoteRetrieveContributorResponse.builder()
                .contributor(
                    CommunityNoteRetrieveContributorResponse.Contributor.builder()
                        .alias("alias")
                        .deletedNotesCount(0L)
                        .isApiContributor(true)
                        .isTopWriter(true)
                        .notesCount(
                            CommunityNoteRetrieveContributorResponse.Contributor.NotesCount
                                .builder()
                                .awaitingMoreRatings(0L)
                                .currentlyRatedHelpful(0L)
                                .currentlyRatedNotHelpful(0L)
                                .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                                .build()
                        )
                        .ratingsCount(
                            CommunityNoteRetrieveContributorResponse.Contributor.RatingsCount
                                .builder()
                                .awaitingMoreRatings(0L)
                                .ratedAfterDecision(0L)
                                .successful(
                                    CommunityNoteRetrieveContributorResponse.Contributor
                                        .RatingsCount
                                        .Successful
                                        .builder()
                                        .helpfulCount(0L)
                                        .notHelpfulCount(0L)
                                        .total(0L)
                                        .build()
                                )
                                .unsuccessful(
                                    CommunityNoteRetrieveContributorResponse.Contributor
                                        .RatingsCount
                                        .Unsuccessful
                                        .builder()
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
                .build()

        val roundtrippedCommunityNoteRetrieveContributorResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(communityNoteRetrieveContributorResponse),
                jacksonTypeRef<CommunityNoteRetrieveContributorResponse>(),
            )

        assertThat(roundtrippedCommunityNoteRetrieveContributorResponse)
            .isEqualTo(communityNoteRetrieveContributorResponse)
    }
}
