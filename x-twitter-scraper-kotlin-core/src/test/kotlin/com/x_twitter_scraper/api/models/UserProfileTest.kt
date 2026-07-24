// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.models

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.JsonValue
import com.x_twitter_scraper.api.core.jsonMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserProfileTest {

    @Test
    fun create() {
        val userProfile =
            UserProfile.builder()
                .id("9876543210")
                .name("Elon Musk")
                .username("elonmusk")
                .automatedBy("example_user")
                .canDm(false)
                .communityRole("Member")
                .coverPicture("https://pbs.twimg.com/profile_banners/example.jpg")
                .createdAt("2009-06-02T20:12:29Z")
                .description("CEO of Tesla, SpaceX, and X")
                .favouritesCount(18000L)
                .followers(150000000L)
                .following(500L)
                .hasCustomTimelines(true)
                .isAutomated(false)
                .isBlueVerified(true)
                .isTranslator(false)
                .isVerified(true)
                .location("Austin, TX")
                .mediaCount(1200L)
                .addPinnedTweetId("1234567890")
                .possiblySensitive(false)
                .profileBio(
                    UserProfile.ProfileBio.builder()
                        .putAdditionalProperty("description", JsonValue.from("bar"))
                        .putAdditionalProperty("entities", JsonValue.from("bar"))
                        .build()
                )
                .profileBannerUrl("https://pbs.twimg.com/profile_banners/example.jpg")
                .profilePicture("https://pbs.twimg.com/profile_images/example.jpg")
                .protected(false)
                .statusesCount(35000L)
                .unavailable(false)
                .unavailableReason("suspended")
                .url("https://xquik.com")
                .verified(true)
                .verifiedType("Business")
                .viewerFollowedBy(false)
                .viewerFollowing(true)
                .addWithheldInCountry("DE")
                .build()

        assertThat(userProfile.id()).isEqualTo("9876543210")
        assertThat(userProfile.name()).isEqualTo("Elon Musk")
        assertThat(userProfile.username()).isEqualTo("elonmusk")
        assertThat(userProfile.automatedBy()).isEqualTo("example_user")
        assertThat(userProfile.canDm()).isEqualTo(false)
        assertThat(userProfile.communityRole()).isEqualTo("Member")
        assertThat(userProfile.coverPicture())
            .isEqualTo("https://pbs.twimg.com/profile_banners/example.jpg")
        assertThat(userProfile.createdAt()).isEqualTo("2009-06-02T20:12:29Z")
        assertThat(userProfile.description()).isEqualTo("CEO of Tesla, SpaceX, and X")
        assertThat(userProfile.favouritesCount()).isEqualTo(18000L)
        assertThat(userProfile.followers()).isEqualTo(150000000L)
        assertThat(userProfile.following()).isEqualTo(500L)
        assertThat(userProfile.hasCustomTimelines()).isEqualTo(true)
        assertThat(userProfile.isAutomated()).isEqualTo(false)
        assertThat(userProfile.isBlueVerified()).isEqualTo(true)
        assertThat(userProfile.isTranslator()).isEqualTo(false)
        assertThat(userProfile.isVerified()).isEqualTo(true)
        assertThat(userProfile.location()).isEqualTo("Austin, TX")
        assertThat(userProfile.mediaCount()).isEqualTo(1200L)
        assertThat(userProfile.pinnedTweetIds()).containsExactly("1234567890")
        assertThat(userProfile.possiblySensitive()).isEqualTo(false)
        assertThat(userProfile.profileBio())
            .isEqualTo(
                UserProfile.ProfileBio.builder()
                    .putAdditionalProperty("description", JsonValue.from("bar"))
                    .putAdditionalProperty("entities", JsonValue.from("bar"))
                    .build()
            )
        assertThat(userProfile.profileBannerUrl())
            .isEqualTo("https://pbs.twimg.com/profile_banners/example.jpg")
        assertThat(userProfile.profilePicture())
            .isEqualTo("https://pbs.twimg.com/profile_images/example.jpg")
        assertThat(userProfile.protected()).isEqualTo(false)
        assertThat(userProfile.statusesCount()).isEqualTo(35000L)
        assertThat(userProfile.unavailable()).isEqualTo(false)
        assertThat(userProfile.unavailableReason()).isEqualTo("suspended")
        assertThat(userProfile.url()).isEqualTo("https://xquik.com")
        assertThat(userProfile.verified()).isEqualTo(true)
        assertThat(userProfile.verifiedType()).isEqualTo("Business")
        assertThat(userProfile.viewerFollowedBy()).isEqualTo(false)
        assertThat(userProfile.viewerFollowing()).isEqualTo(true)
        assertThat(userProfile.withheldInCountries()).containsExactly("DE")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val userProfile =
            UserProfile.builder()
                .id("9876543210")
                .name("Elon Musk")
                .username("elonmusk")
                .automatedBy("example_user")
                .canDm(false)
                .communityRole("Member")
                .coverPicture("https://pbs.twimg.com/profile_banners/example.jpg")
                .createdAt("2009-06-02T20:12:29Z")
                .description("CEO of Tesla, SpaceX, and X")
                .favouritesCount(18000L)
                .followers(150000000L)
                .following(500L)
                .hasCustomTimelines(true)
                .isAutomated(false)
                .isBlueVerified(true)
                .isTranslator(false)
                .isVerified(true)
                .location("Austin, TX")
                .mediaCount(1200L)
                .addPinnedTweetId("1234567890")
                .possiblySensitive(false)
                .profileBio(
                    UserProfile.ProfileBio.builder()
                        .putAdditionalProperty("description", JsonValue.from("bar"))
                        .putAdditionalProperty("entities", JsonValue.from("bar"))
                        .build()
                )
                .profileBannerUrl("https://pbs.twimg.com/profile_banners/example.jpg")
                .profilePicture("https://pbs.twimg.com/profile_images/example.jpg")
                .protected(false)
                .statusesCount(35000L)
                .unavailable(false)
                .unavailableReason("suspended")
                .url("https://xquik.com")
                .verified(true)
                .verifiedType("Business")
                .viewerFollowedBy(false)
                .viewerFollowing(true)
                .addWithheldInCountry("DE")
                .build()

        val roundtrippedUserProfile =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(userProfile),
                jacksonTypeRef<UserProfile>(),
            )

        assertThat(roundtrippedUserProfile).isEqualTo(userProfile)
    }
}
