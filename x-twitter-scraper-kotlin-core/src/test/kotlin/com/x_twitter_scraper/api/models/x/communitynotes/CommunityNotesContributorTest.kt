package com.x_twitter_scraper.api.models.x.communitynotes

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.JsonValue
import com.x_twitter_scraper.api.core.jsonMapper
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CommunityNotesContributorTest {

    @Test
    fun create() {
        val communityNotesContributor =
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

        assertThat(communityNotesContributor.alias()).isEqualTo("ardent-beach-friarbird")
        assertThat(communityNotesContributor.deletedNotesCount()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val communityNotesContributor =
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
                .toBuilder()
                .putAdditionalProperty("futureField", JsonValue.from("value"))
                .build()

        val roundtrippedCommunityNotesContributor =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(communityNotesContributor),
                jacksonTypeRef<CommunityNotesContributor>(),
            )

        assertThat(roundtrippedCommunityNotesContributor).isEqualTo(communityNotesContributor)
        assertThat(roundtrippedCommunityNotesContributor._additionalProperties())
            .containsEntry("futureField", JsonValue.from("value"))
    }

    @Test
    fun validate() {
        val communityNotesContributor =
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

        assertThat(communityNotesContributor.validate()).isSameAs(communityNotesContributor)
        assertThat(communityNotesContributor.isValid()).isTrue()
    }
}
