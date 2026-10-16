// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.users

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserRetrieveFollowingIdsResponseTest {

    @Test
    fun create() {
        val userRetrieveFollowingIdsResponse =
            UserRetrieveFollowingIdsResponse.builder()
                .hasNextPage(true)
                .addId("id")
                .nextCursor("next_cursor")
                .build()

        assertThat(userRetrieveFollowingIdsResponse.hasNextPage()).isEqualTo(true)
        assertThat(userRetrieveFollowingIdsResponse.ids()).containsExactly("id")
        assertThat(userRetrieveFollowingIdsResponse.nextCursor()).isEqualTo("next_cursor")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val userRetrieveFollowingIdsResponse =
            UserRetrieveFollowingIdsResponse.builder()
                .hasNextPage(true)
                .addId("id")
                .nextCursor("next_cursor")
                .build()

        val roundtrippedUserRetrieveFollowingIdsResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(userRetrieveFollowingIdsResponse),
                jacksonTypeRef<UserRetrieveFollowingIdsResponse>(),
            )

        assertThat(roundtrippedUserRetrieveFollowingIdsResponse)
            .isEqualTo(userRetrieveFollowingIdsResponse)
    }
}
