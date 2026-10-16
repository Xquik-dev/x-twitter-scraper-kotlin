// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.accounts

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AccountReauthResponseTest {

    @Test
    fun create() {
        val accountReauthResponse =
            AccountReauthResponse.builder()
                .id("id")
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .health(AccountReauthResponse.Health.HEALTHY)
                .xUserId("xUserId")
                .xUsername("xUsername")
                .build()

        assertThat(accountReauthResponse.id()).isEqualTo("id")
        assertThat(accountReauthResponse.createdAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(accountReauthResponse.health()).isEqualTo(AccountReauthResponse.Health.HEALTHY)
        assertThat(accountReauthResponse.xUserId()).isEqualTo("xUserId")
        assertThat(accountReauthResponse.xUsername()).isEqualTo("xUsername")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val accountReauthResponse =
            AccountReauthResponse.builder()
                .id("id")
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .health(AccountReauthResponse.Health.HEALTHY)
                .xUserId("xUserId")
                .xUsername("xUsername")
                .build()

        val roundtrippedAccountReauthResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(accountReauthResponse),
                jacksonTypeRef<AccountReauthResponse>(),
            )

        assertThat(roundtrippedAccountReauthResponse).isEqualTo(accountReauthResponse)
    }
}
