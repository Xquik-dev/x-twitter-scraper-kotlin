package com.x_twitter_scraper.api.models.x.communitynotes

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.JsonValue
import com.x_twitter_scraper.api.core.jsonMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CommunityNotesRatingOutcomeTest {

    @Test
    fun create() {
        val communityNotesRatingOutcome =
            CommunityNotesRatingOutcome.builder()
                .helpfulCount(0L)
                .notHelpfulCount(0L)
                .total(0L)
                .build()

        assertThat(communityNotesRatingOutcome.helpfulCount()).isEqualTo(0L)
        assertThat(communityNotesRatingOutcome.notHelpfulCount()).isEqualTo(0L)
        assertThat(communityNotesRatingOutcome.total()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val communityNotesRatingOutcome =
            CommunityNotesRatingOutcome.builder()
                .helpfulCount(0L)
                .notHelpfulCount(0L)
                .total(0L)
                .build()
                .toBuilder()
                .putAdditionalProperty("futureField", JsonValue.from("value"))
                .build()

        val roundtrippedCommunityNotesRatingOutcome =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(communityNotesRatingOutcome),
                jacksonTypeRef<CommunityNotesRatingOutcome>(),
            )

        assertThat(roundtrippedCommunityNotesRatingOutcome).isEqualTo(communityNotesRatingOutcome)
        assertThat(roundtrippedCommunityNotesRatingOutcome._additionalProperties())
            .containsEntry("futureField", JsonValue.from("value"))
    }

    @Test
    fun validate() {
        val communityNotesRatingOutcome =
            CommunityNotesRatingOutcome.builder()
                .helpfulCount(0L)
                .notHelpfulCount(0L)
                .total(0L)
                .build()

        assertThat(communityNotesRatingOutcome.validate()).isSameAs(communityNotesRatingOutcome)
        assertThat(communityNotesRatingOutcome.isValid()).isTrue()
    }
}
