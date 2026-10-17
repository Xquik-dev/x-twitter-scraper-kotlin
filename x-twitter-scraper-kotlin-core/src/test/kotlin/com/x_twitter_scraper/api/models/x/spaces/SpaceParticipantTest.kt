package com.x_twitter_scraper.api.models.x.spaces

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.JsonValue
import com.x_twitter_scraper.api.core.jsonMapper
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SpaceParticipantTest {

    @Test
    fun create() {
        val spaceParticipant =
            SpaceParticipant.builder()
                .id("9876543210")
                .name("Solana")
                .username("elonmusk")
                .affiliatesHighlightedLabel(
                    SpaceParticipant.AffiliatesHighlightedLabel.builder()
                        .badgeUrl("badgeUrl")
                        .description("description")
                        .longDescription(
                            SpaceParticipant.AffiliatesHighlightedLabel.LongDescription.builder()
                                .putAdditionalProperty("foo", JsonValue.from("bar"))
                                .build()
                        )
                        .url("url")
                        .urlType("urlType")
                        .userLabelDisplayType("userLabelDisplayType")
                        .userLabelType("userLabelType")
                        .build()
                )
                .isBlueVerified(true)
                .isMutedByAdmin(true)
                .isMutedByGuest(true)
                .isVerified(true)
                .joinedAt(OffsetDateTime.parse("2026-03-11T17:00:52.759Z"))
                .periscopeUserId("645158")
                .profilePicture("https://pbs.twimg.com/profile_images/example_normal.jpg")
                .profilePictureOriginal("https://pbs.twimg.com/profile_images/example.jpg")
                .verifiedType("Business")
                .build()

        assertThat(spaceParticipant.id()).isEqualTo("9876543210")
        assertThat(spaceParticipant.username()).isEqualTo("elonmusk")
        assertThat(spaceParticipant.name()).isEqualTo("Solana")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val spaceParticipant =
            SpaceParticipant.builder()
                .id("9876543210")
                .name("Solana")
                .username("elonmusk")
                .affiliatesHighlightedLabel(
                    SpaceParticipant.AffiliatesHighlightedLabel.builder()
                        .badgeUrl("badgeUrl")
                        .description("description")
                        .longDescription(
                            SpaceParticipant.AffiliatesHighlightedLabel.LongDescription.builder()
                                .putAdditionalProperty("foo", JsonValue.from("bar"))
                                .build()
                        )
                        .url("url")
                        .urlType("urlType")
                        .userLabelDisplayType("userLabelDisplayType")
                        .userLabelType("userLabelType")
                        .build()
                )
                .isBlueVerified(true)
                .isMutedByAdmin(true)
                .isMutedByGuest(true)
                .isVerified(true)
                .joinedAt(OffsetDateTime.parse("2026-03-11T17:00:52.759Z"))
                .periscopeUserId("645158")
                .profilePicture("https://pbs.twimg.com/profile_images/example_normal.jpg")
                .profilePictureOriginal("https://pbs.twimg.com/profile_images/example.jpg")
                .verifiedType("Business")
                .build()
                .toBuilder()
                .putAdditionalProperty("futureField", JsonValue.from("value"))
                .build()

        val roundtrippedSpaceParticipant =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(spaceParticipant),
                jacksonTypeRef<SpaceParticipant>(),
            )

        assertThat(roundtrippedSpaceParticipant).isEqualTo(spaceParticipant)
        assertThat(roundtrippedSpaceParticipant._additionalProperties())
            .containsEntry("futureField", JsonValue.from("value"))
    }

    @Test
    fun validate() {
        val spaceParticipant =
            SpaceParticipant.builder()
                .id("9876543210")
                .name("Solana")
                .username("elonmusk")
                .affiliatesHighlightedLabel(
                    SpaceParticipant.AffiliatesHighlightedLabel.builder()
                        .badgeUrl("badgeUrl")
                        .description("description")
                        .longDescription(
                            SpaceParticipant.AffiliatesHighlightedLabel.LongDescription.builder()
                                .putAdditionalProperty("foo", JsonValue.from("bar"))
                                .build()
                        )
                        .url("url")
                        .urlType("urlType")
                        .userLabelDisplayType("userLabelDisplayType")
                        .userLabelType("userLabelType")
                        .build()
                )
                .isBlueVerified(true)
                .isMutedByAdmin(true)
                .isMutedByGuest(true)
                .isVerified(true)
                .joinedAt(OffsetDateTime.parse("2026-03-11T17:00:52.759Z"))
                .periscopeUserId("645158")
                .profilePicture("https://pbs.twimg.com/profile_images/example_normal.jpg")
                .profilePictureOriginal("https://pbs.twimg.com/profile_images/example.jpg")
                .verifiedType("Business")
                .build()

        assertThat(spaceParticipant.validate()).isSameAs(spaceParticipant)
        assertThat(spaceParticipant.isValid()).isTrue()
    }
}
