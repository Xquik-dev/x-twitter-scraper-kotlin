// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.users

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import com.x_twitter_scraper.api.models.UserProfile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserRetrieveBatchResponseTest {

    @Test
    fun create() {
        val userRetrieveBatchResponse =
            UserRetrieveBatchResponse.builder()
                .nextCursor("next_cursor")
                .processedCount(0L)
                .requestedCount(0L)
                .returnedCount(0L)
                .addUnavailableId("unavailableId")
                .addUnprocessedId("unprocessedId")
                .addUser(UserProfile.builder().id("id").name("name").username("username").build())
                .addFailedId("failedId")
                .filteredCount(0L)
                .build()

        assertThat(userRetrieveBatchResponse.nextCursor()).isEqualTo("next_cursor")
        assertThat(userRetrieveBatchResponse.processedCount()).isEqualTo(0L)
        assertThat(userRetrieveBatchResponse.requestedCount()).isEqualTo(0L)
        assertThat(userRetrieveBatchResponse.returnedCount()).isEqualTo(0L)
        assertThat(userRetrieveBatchResponse.unavailableIds()).containsExactly("unavailableId")
        assertThat(userRetrieveBatchResponse.unprocessedIds()).containsExactly("unprocessedId")
        assertThat(userRetrieveBatchResponse.users())
            .containsExactly(
                UserProfile.builder().id("id").name("name").username("username").build()
            )
        assertThat(userRetrieveBatchResponse.failedIds()).containsExactly("failedId")
        assertThat(userRetrieveBatchResponse.filteredCount()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val userRetrieveBatchResponse =
            UserRetrieveBatchResponse.builder()
                .nextCursor("next_cursor")
                .processedCount(0L)
                .requestedCount(0L)
                .returnedCount(0L)
                .addUnavailableId("unavailableId")
                .addUnprocessedId("unprocessedId")
                .addUser(UserProfile.builder().id("id").name("name").username("username").build())
                .addFailedId("failedId")
                .filteredCount(0L)
                .build()

        val roundtrippedUserRetrieveBatchResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(userRetrieveBatchResponse),
                jacksonTypeRef<UserRetrieveBatchResponse>(),
            )

        assertThat(roundtrippedUserRetrieveBatchResponse).isEqualTo(userRetrieveBatchResponse)
    }
}
