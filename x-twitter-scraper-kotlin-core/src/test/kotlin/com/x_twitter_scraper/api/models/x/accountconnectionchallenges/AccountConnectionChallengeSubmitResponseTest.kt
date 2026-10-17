// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.accountconnectionchallenges

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.JsonMissing
import com.x_twitter_scraper.api.core.JsonValue
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
                .status(JsonValue.from("requires_email_code"))
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
        accountConnectionChallengeSubmitResponse.validate()
        assertThat(accountConnectionChallengeSubmitResponse._status())
            .isEqualTo(JsonValue.from("requires_email_code"))
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

    @Test
    fun readsAdditionalFields() {
        val jsonMapper = jsonMapper()

        val accountConnectionChallengeSubmitResponse0 =
            jsonMapper.readValue(
                """{"expiresAt":"2026-10-17T00:00:00Z","message":"value","object":"value","username":"value"}""",
                jacksonTypeRef<AccountConnectionChallengeSubmitResponse>(),
            )
        assertThat(accountConnectionChallengeSubmitResponse0.expiresAt()).isNotNull()
        assertThat(accountConnectionChallengeSubmitResponse0.message()).isNotNull()
        assertThat(accountConnectionChallengeSubmitResponse0.object_()).isNotNull()
        assertThat(accountConnectionChallengeSubmitResponse0.username()).isNotNull()
        assertThat(
                jsonMapper.readTree(
                    jsonMapper.writeValueAsString(accountConnectionChallengeSubmitResponse0)
                )
            )
            .isEqualTo(
                jsonMapper.readTree(
                    """{"expiresAt":"2026-10-17T00:00:00Z","message":"value","object":"value","username":"value"}"""
                )
            )
    }

    @Test
    fun keepsAdditionalFieldsOptional() {
        val accountConnectionChallengeSubmitResponse0 =
            AccountConnectionChallengeSubmitResponse.builder()
                .id(JsonMissing.of())
                .createdAt(JsonMissing.of())
                .health(JsonMissing.of())
                .xUserId(JsonMissing.of())
                .xUsername(JsonMissing.of())
                .build()
        assertThat(accountConnectionChallengeSubmitResponse0.expiresAt()).isNull()
        assertThat(accountConnectionChallengeSubmitResponse0.message()).isNull()
        assertThat(accountConnectionChallengeSubmitResponse0.object_()).isNull()
        assertThat(accountConnectionChallengeSubmitResponse0.username()).isNull()
    }
}
