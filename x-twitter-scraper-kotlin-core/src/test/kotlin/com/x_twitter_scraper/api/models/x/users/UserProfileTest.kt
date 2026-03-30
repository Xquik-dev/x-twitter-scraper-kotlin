// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.models.x.users

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserProfileTest {

    @Test
    fun create() {
        val userProfile =
            UserProfile.builder()
                .id("id")
                .name("name")
                .username("username")
                .createdAt("createdAt")
                .description("description")
                .followers(0L)
                .following(0L)
                .location("location")
                .profilePicture("profilePicture")
                .statusesCount(0L)
                .verified(true)
                .build()

        assertThat(userProfile.id()).isEqualTo("id")
        assertThat(userProfile.name()).isEqualTo("name")
        assertThat(userProfile.username()).isEqualTo("username")
        assertThat(userProfile.createdAt()).isEqualTo("createdAt")
        assertThat(userProfile.description()).isEqualTo("description")
        assertThat(userProfile.followers()).isEqualTo(0L)
        assertThat(userProfile.following()).isEqualTo(0L)
        assertThat(userProfile.location()).isEqualTo("location")
        assertThat(userProfile.profilePicture()).isEqualTo("profilePicture")
        assertThat(userProfile.statusesCount()).isEqualTo(0L)
        assertThat(userProfile.verified()).isEqualTo(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val userProfile =
            UserProfile.builder()
                .id("id")
                .name("name")
                .username("username")
                .createdAt("createdAt")
                .description("description")
                .followers(0L)
                .following(0L)
                .location("location")
                .profilePicture("profilePicture")
                .statusesCount(0L)
                .verified(true)
                .build()

        val roundtrippedUserProfile =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(userProfile),
                jacksonTypeRef<UserProfile>(),
            )

        assertThat(roundtrippedUserProfile).isEqualTo(userProfile)
    }
}
