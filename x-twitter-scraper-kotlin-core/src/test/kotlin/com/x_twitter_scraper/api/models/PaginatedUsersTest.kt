// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.JsonMissing
import com.x_twitter_scraper.api.core.jsonMapper
import com.x_twitter_scraper.api.models.x.users.UserRetrieveFollowersResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PaginatedUsersTest {

    @Test
    fun diagnosticParticipatesInEquality() {
        val diagnostic =
            UserRetrieveFollowersResponse.UserListCoverageResponse.Diagnostic.builder()
                .complete(true)
                .cursorFailureCount(0L)
                .deadlineReached(false)
                .duplicateCount(0L)
                .failedStrategyCount(0L)
                .malformedCount(0L)
                .pagesFetched(1L)
                .responseTruncated(false)
                .resultLimitReached(false)
                .returnedUsers(0L)
                .stalledStrategyCount(0L)
                .strategies(emptyList())
                .strategyCount(0L)
                .uniqueUsers(0L)
                .build()
        val paginatedUsers =
            PaginatedUsers.builder()
                .hasNextPage(false)
                .nextCursor("")
                .users(emptyList())
                .diagnostic(diagnostic)
                .build()
        val incompletePaginatedUsers =
            paginatedUsers
                .toBuilder()
                .diagnostic(diagnostic.toBuilder().complete(false).build())
                .build()
        val copy = paginatedUsers.toBuilder().build()

        assertThat(paginatedUsers).isNotEqualTo(incompletePaginatedUsers)
        assertThat(incompletePaginatedUsers).isNotEqualTo(paginatedUsers)
        assertThat(copy).isEqualTo(paginatedUsers)
        assertThat(copy.hashCode()).isEqualTo(paginatedUsers.hashCode())
        assertThat(paginatedUsers.toString()).contains("diagnostic=$diagnostic")
        assertThat(hashSetOf(paginatedUsers, incompletePaginatedUsers, copy)).hasSize(2)
        assertThat(hashMapOf(paginatedUsers to true, incompletePaginatedUsers to false))
            .hasSize(2)
            .containsEntry(copy, true)
            .containsEntry(incompletePaginatedUsers, false)
    }

    @Test
    fun scanLimit() {
        val scanLimit =
            PaginatedUsers.ScanLimit.builder()
                .limit(100000L)
                .rowsRead(100001L)
                .message("Stopped at the scan limit")
                .build()
        val paginatedUsers =
            PaginatedUsers.builder()
                .hasNextPage(false)
                .nextCursor("")
                .users(emptyList())
                .scanLimit(scanLimit)
                .build()

        paginatedUsers.validate()
        assertThat(paginatedUsers.scanLimit()).isEqualTo(scanLimit)
        assertThat(paginatedUsers.toBuilder().build()).isEqualTo(paginatedUsers)
        assertThat(paginatedUsers.toBuilder().build().hashCode())
            .isEqualTo(paginatedUsers.hashCode())
    }

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

    @Test
    fun readsAdditionalFields() {
        val jsonMapper = jsonMapper()

        val paginatedUsers0 =
            jsonMapper.readValue(
                """{"scanLimit":{}}""",
                jacksonTypeRef<PaginatedUsers>(),
            )
        assertThat(paginatedUsers0.scanLimit()).isNotNull()
        assertThat(jsonMapper.readTree(jsonMapper.writeValueAsString(paginatedUsers0)))
            .isEqualTo(jsonMapper.readTree("""{"scanLimit":{}}"""))
    }

    @Test
    fun keepsAdditionalFieldsOptional() {
        val paginatedUsers0 =
            PaginatedUsers.builder()
                .hasNextPage(JsonMissing.of())
                .nextCursor(JsonMissing.of())
                .users(JsonMissing.of())
                .build()
        assertThat(paginatedUsers0.scanLimit()).isNull()
    }
}
