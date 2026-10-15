// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PaginatedUsersTest {

    @Test
    fun create() {
        val paginatedUsers =
            PaginatedUsers.builder()
                .hasNextPage(true)
                .nextCursor("next_cursor")
                .addUser(UserProfile.builder().id("id").name("name").username("username").build())
                .filteredCount(0L)
                .build()

        assertThat(paginatedUsers.hasNextPage()).isEqualTo(true)
        assertThat(paginatedUsers.nextCursor()).isEqualTo("next_cursor")
        assertThat(paginatedUsers.users())
            .containsExactly(
                UserProfile.builder().id("id").name("name").username("username").build()
            )
        assertThat(paginatedUsers.filteredCount()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val paginatedUsers =
            PaginatedUsers.builder()
                .hasNextPage(true)
                .nextCursor("next_cursor")
                .addUser(UserProfile.builder().id("id").name("name").username("username").build())
                .filteredCount(0L)
                .build()

        val roundtrippedPaginatedUsers =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(paginatedUsers),
                jacksonTypeRef<PaginatedUsers>(),
            )

        assertThat(roundtrippedPaginatedUsers).isEqualTo(paginatedUsers)
    }
}
