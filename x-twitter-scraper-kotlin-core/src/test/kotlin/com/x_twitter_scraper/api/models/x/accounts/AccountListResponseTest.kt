// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.accounts

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.JsonMissing
import com.x_twitter_scraper.api.core.jsonMapper
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AccountListResponseTest {

    @Test
    fun create() {
        val accountListResponse =
            AccountListResponse.builder()
                .addAccount(
                    XAccount.builder()
                        .id("id")
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .health(XAccount.Health.HEALTHY)
                        .status("status")
                        .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .xUserId("xUserId")
                        .xUsername("xUsername")
                        .build()
                )
                .hasMore(false)
                .nextCursor("MjAyNi0wMS0wMVQwMDowMDowMC4wMDBafDQy")
                .build()

        assertThat(accountListResponse.accounts())
            .containsExactly(
                XAccount.builder()
                    .id("id")
                    .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .health(XAccount.Health.HEALTHY)
                    .status("status")
                    .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .xUserId("xUserId")
                    .xUsername("xUsername")
                    .build()
            )
        assertThat(accountListResponse.hasMore()).isEqualTo(false)
        assertThat(accountListResponse.nextCursor())
            .isEqualTo("MjAyNi0wMS0wMVQwMDowMDowMC4wMDBafDQy")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val accountListResponse =
            AccountListResponse.builder()
                .addAccount(
                    XAccount.builder()
                        .id("id")
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .health(XAccount.Health.HEALTHY)
                        .status("status")
                        .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .xUserId("xUserId")
                        .xUsername("xUsername")
                        .build()
                )
                .hasMore(false)
                .nextCursor("MjAyNi0wMS0wMVQwMDowMDowMC4wMDBafDQy")
                .build()

        val roundtrippedAccountListResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(accountListResponse),
                jacksonTypeRef<AccountListResponse>(),
            )

        assertThat(roundtrippedAccountListResponse).isEqualTo(accountListResponse)
    }

    @Test
    fun readsAdditionalFields() {
        val jsonMapper = jsonMapper()

        val accountListResponse0 =
            jsonMapper.readValue(
                """{"hasMore":true,"nextCursor":"value"}""",
                jacksonTypeRef<AccountListResponse>(),
            )
        assertThat(accountListResponse0.hasMore()).isNotNull()
        assertThat(accountListResponse0.nextCursor()).isNotNull()
        assertThat(jsonMapper.readTree(jsonMapper.writeValueAsString(accountListResponse0)))
            .isEqualTo(jsonMapper.readTree("""{"hasMore":true,"nextCursor":"value"}"""))
    }

    @Test
    fun keepsAdditionalFieldsOptional() {
        val accountListResponse0 = AccountListResponse.builder().accounts(JsonMissing.of()).build()
        assertThat(accountListResponse0.hasMore()).isNull()
        assertThat(accountListResponse0.nextCursor()).isNull()
    }
}
