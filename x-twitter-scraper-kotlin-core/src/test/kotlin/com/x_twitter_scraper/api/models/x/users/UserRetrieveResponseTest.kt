// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.models.x.users

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserRetrieveResponseTest {

    @Test
    fun create() {
        val userRetrieveResponse =
            UserRetrieveResponse.builder()
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

        assertThat(userRetrieveResponse.id()).isEqualTo("id")
        assertThat(userRetrieveResponse.name()).isEqualTo("name")
        assertThat(userRetrieveResponse.username()).isEqualTo("username")
        assertThat(userRetrieveResponse.createdAt()).isEqualTo("createdAt")
        assertThat(userRetrieveResponse.description()).isEqualTo("description")
        assertThat(userRetrieveResponse.followers()).isEqualTo(0L)
        assertThat(userRetrieveResponse.following()).isEqualTo(0L)
        assertThat(userRetrieveResponse.location()).isEqualTo("location")
        assertThat(userRetrieveResponse.profilePicture()).isEqualTo("profilePicture")
        assertThat(userRetrieveResponse.statusesCount()).isEqualTo(0L)
        assertThat(userRetrieveResponse.verified()).isEqualTo(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val userRetrieveResponse =
            UserRetrieveResponse.builder()
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

        val roundtrippedUserRetrieveResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(userRetrieveResponse),
                jacksonTypeRef<UserRetrieveResponse>(),
            )

        assertThat(roundtrippedUserRetrieveResponse).isEqualTo(userRetrieveResponse)
    }
}
