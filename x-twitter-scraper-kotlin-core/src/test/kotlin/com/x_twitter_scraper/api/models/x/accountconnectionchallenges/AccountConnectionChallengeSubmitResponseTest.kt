// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.accountconnectionchallenges

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AccountConnectionChallengeSubmitResponseTest {

    @Test
    fun create() {
        val accountConnectionChallengeSubmitResponse =
            AccountConnectionChallengeSubmitResponse.builder()
                .id("id")
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .health(AccountConnectionChallengeSubmitResponse.Health.HEALTHY)
                .xUserId("xUserId")
                .xUsername("xUsername")
                .expiresAt(OffsetDateTime.parse("2026-05-08T12:10:00Z"))
                .message("Enter the email verification code to continue.")
                .object_("x_account_connection_challenge")
                .username("elonmusk")
                .build()

        assertThat(accountConnectionChallengeSubmitResponse.id()).isEqualTo("id")
        assertThat(accountConnectionChallengeSubmitResponse.createdAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(accountConnectionChallengeSubmitResponse.health())
            .isEqualTo(AccountConnectionChallengeSubmitResponse.Health.HEALTHY)
        assertThat(accountConnectionChallengeSubmitResponse.xUserId()).isEqualTo("xUserId")
        assertThat(accountConnectionChallengeSubmitResponse.xUsername()).isEqualTo("xUsername")
        assertThat(accountConnectionChallengeSubmitResponse.expiresAt())
            .isEqualTo(OffsetDateTime.parse("2026-05-08T12:10:00Z"))
        assertThat(accountConnectionChallengeSubmitResponse.message())
            .isEqualTo("Enter the email verification code to continue.")
        assertThat(accountConnectionChallengeSubmitResponse.object_())
            .isEqualTo("x_account_connection_challenge")
        assertThat(accountConnectionChallengeSubmitResponse.username()).isEqualTo("elonmusk")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val accountConnectionChallengeSubmitResponse =
            AccountConnectionChallengeSubmitResponse.builder()
                .id("id")
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .health(AccountConnectionChallengeSubmitResponse.Health.HEALTHY)
                .xUserId("xUserId")
                .xUsername("xUsername")
                .expiresAt(OffsetDateTime.parse("2026-05-08T12:10:00Z"))
                .message("Enter the email verification code to continue.")
                .object_("x_account_connection_challenge")
                .username("elonmusk")
                .build()

        val roundtrippedAccountConnectionChallengeSubmitResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(accountConnectionChallengeSubmitResponse),
                jacksonTypeRef<AccountConnectionChallengeSubmitResponse>(),
            )

        assertThat(roundtrippedAccountConnectionChallengeSubmitResponse)
            .isEqualTo(accountConnectionChallengeSubmitResponse)
    }
}
