// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.accounts

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.JsonMissing
import com.x_twitter_scraper.api.core.JsonValue
import com.x_twitter_scraper.api.core.jsonMapper
import com.x_twitter_scraper.api.errors.XTwitterScraperInvalidDataException
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class AccountReauthResponseTest {

    @Test
    fun ofSanitizedXAccount() {
        val sanitizedXAccount =
            AccountReauthResponse.SanitizedXAccount.builder()
                .id("42")
                .createdAt(OffsetDateTime.parse("2025-01-15T12:00:00Z"))
                .health(AccountReauthResponse.SanitizedXAccount.Health.HEALTHY)
                .xUserId("9876543210")
                .xUsername("elonmusk")
                .build()

        val accountReauthResponse = AccountReauthResponse.ofSanitizedXAccount(sanitizedXAccount)

        assertThat(accountReauthResponse.sanitizedXAccount()).isEqualTo(sanitizedXAccount)
        assertThat(accountReauthResponse.xAccountConnectionAttemptPending()).isNull()
        assertThat(accountReauthResponse.xAccountConnectionChallenge()).isNull()
    }

    @Test
    fun ofSanitizedXAccountRoundtrip() {
        val jsonMapper = jsonMapper()
        val accountReauthResponse =
            AccountReauthResponse.ofSanitizedXAccount(
                AccountReauthResponse.SanitizedXAccount.builder()
                    .id("42")
                    .createdAt(OffsetDateTime.parse("2025-01-15T12:00:00Z"))
                    .health(AccountReauthResponse.SanitizedXAccount.Health.HEALTHY)
                    .xUserId("9876543210")
                    .xUsername("elonmusk")
                    .build()
            )

        val roundtrippedAccountReauthResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(accountReauthResponse),
                jacksonTypeRef<AccountReauthResponse>(),
            )

        assertThat(roundtrippedAccountReauthResponse).isEqualTo(accountReauthResponse)
    }

    @Test
    fun ofXAccountConnectionAttemptPending() {
        val xAccountConnectionAttemptPending =
            AccountReauthResponse.XAccountConnectionAttemptPending.builder()
                .id("xatt_0123456789abcdef0123456789abcdef")
                .pollAfterMs(3000L)
                .build()

        val accountReauthResponse =
            AccountReauthResponse.ofXAccountConnectionAttemptPending(
                xAccountConnectionAttemptPending
            )

        assertThat(accountReauthResponse.sanitizedXAccount()).isNull()
        assertThat(accountReauthResponse.xAccountConnectionAttemptPending())
            .isEqualTo(xAccountConnectionAttemptPending)
        assertThat(accountReauthResponse.xAccountConnectionChallenge()).isNull()
    }

    @Test
    fun ofXAccountConnectionAttemptPendingRoundtrip() {
        val jsonMapper = jsonMapper()
        val accountReauthResponse =
            AccountReauthResponse.ofXAccountConnectionAttemptPending(
                AccountReauthResponse.XAccountConnectionAttemptPending.builder()
                    .id("xatt_0123456789abcdef0123456789abcdef")
                    .pollAfterMs(3000L)
                    .build()
            )

        val roundtrippedAccountReauthResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(accountReauthResponse),
                jacksonTypeRef<AccountReauthResponse>(),
            )

        assertThat(roundtrippedAccountReauthResponse).isEqualTo(accountReauthResponse)
    }

    @Test
    fun ofXAccountConnectionChallenge() {
        val xAccountConnectionChallenge =
            AccountReauthResponse.XAccountConnectionChallenge.builder()
                .id("xch_8vGd8Y9JvH6dV0xA")
                .expiresAt(OffsetDateTime.parse("2026-05-08T12:10:00Z"))
                .message("Enter the email verification code to continue.")
                .username("elonmusk")
                .build()

        val accountReauthResponse =
            AccountReauthResponse.ofXAccountConnectionChallenge(xAccountConnectionChallenge)

        assertThat(accountReauthResponse.sanitizedXAccount()).isNull()
        assertThat(accountReauthResponse.xAccountConnectionAttemptPending()).isNull()
        assertThat(accountReauthResponse.xAccountConnectionChallenge())
            .isEqualTo(xAccountConnectionChallenge)
    }

    @Test
    fun ofXAccountConnectionChallengeRoundtrip() {
        val jsonMapper = jsonMapper()
        val accountReauthResponse =
            AccountReauthResponse.ofXAccountConnectionChallenge(
                AccountReauthResponse.XAccountConnectionChallenge.builder()
                    .id("xch_8vGd8Y9JvH6dV0xA")
                    .expiresAt(OffsetDateTime.parse("2026-05-08T12:10:00Z"))
                    .message("Enter the email verification code to continue.")
                    .username("elonmusk")
                    .build()
            )

        val roundtrippedAccountReauthResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(accountReauthResponse),
                jacksonTypeRef<AccountReauthResponse>(),
            )

        assertThat(roundtrippedAccountReauthResponse).isEqualTo(accountReauthResponse)
    }

    @Test
    fun deserializesEachReauthAnswer() {
        val jsonMapper = jsonMapper()

        val account =
            jsonMapper.readValue(
                """{"id":"42","createdAt":"2026-11-08T12:00:00Z","health":"healthy","status":"active","xUserId":"1234567890","xUsername":"your_x_username"}""",
                jacksonTypeRef<AccountReauthResponse>(),
            )
        assertThat(account.isValid()).isTrue()
        assertThat(account.sanitizedXAccount()?.id()).isEqualTo("42")
        assertThat(account.sanitizedXAccount()?.health())
            .isEqualTo(AccountReauthResponse.SanitizedXAccount.Health.HEALTHY)
        assertThat(account.xAccountConnectionAttemptPending()).isNull()
        assertThat(account.xAccountConnectionChallenge()).isNull()

        val pending =
            jsonMapper.readValue(
                """{"id":"xatt_0123456789abcdef0123456789abcdef","object":"x_account_connection_attempt","pollAfterMs":3000,"status":"pending"}""",
                jacksonTypeRef<AccountReauthResponse>(),
            )
        assertThat(pending.isValid()).isTrue()
        assertThat(pending.sanitizedXAccount()).isNull()
        assertThat(pending.xAccountConnectionAttemptPending()?.pollAfterMs()).isEqualTo(3000L)
        assertThat(pending.xAccountConnectionChallenge()).isNull()

        val challenge =
            jsonMapper.readValue(
                """{"id":"xatt_0123456789abcdef0123456789abcdef","expiresAt":"2026-11-08T12:30:00Z","message":"Enter the code X sent to your email.","object":"x_account_connection_challenge","status":"requires_email_code","username":"your_x_username"}""",
                jacksonTypeRef<AccountReauthResponse>(),
            )
        assertThat(challenge.isValid()).isTrue()
        assertThat(challenge.sanitizedXAccount()).isNull()
        assertThat(challenge.xAccountConnectionAttemptPending()).isNull()
        assertThat(challenge.xAccountConnectionChallenge()?.username()).isEqualTo("your_x_username")
    }

    enum class IncompatibleJsonShapeTestCase(val value: JsonValue) {
        BOOLEAN(JsonValue.from(false)),
        STRING(JsonValue.from("invalid")),
        INTEGER(JsonValue.from(-1)),
        FLOAT(JsonValue.from(3.14)),
        ARRAY(JsonValue.from(listOf("invalid", "array"))),
    }

    @ParameterizedTest
    @EnumSource
    fun incompatibleJsonShapeDeserializesToUnknown(testCase: IncompatibleJsonShapeTestCase) {
        val accountReauthResponse =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<AccountReauthResponse>())

        val e =
            assertThrows<XTwitterScraperInvalidDataException> { accountReauthResponse.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }

    @Test
    fun readsAdditionalFields() {
        val jsonMapper = jsonMapper()

        val xAccountConnectionChallenge0 =
            jsonMapper.readValue(
                """{"createdAt":"2026-10-17T00:00:00Z","health":"value","pollAfterMs":1,"xUserId":"value","xUsername":"value"}""",
                jacksonTypeRef<AccountReauthResponse.XAccountConnectionChallenge>(),
            )
        assertThat(xAccountConnectionChallenge0.createdAt()).isNotNull()
        assertThat(xAccountConnectionChallenge0.health()).isNotNull()
        assertThat(xAccountConnectionChallenge0.pollAfterMs()).isNotNull()
        assertThat(xAccountConnectionChallenge0.xUserId()).isNotNull()
        assertThat(xAccountConnectionChallenge0.xUsername()).isNotNull()
        assertThat(jsonMapper.readTree(jsonMapper.writeValueAsString(xAccountConnectionChallenge0)))
            .isEqualTo(
                jsonMapper.readTree(
                    """{"createdAt":"2026-10-17T00:00:00Z","health":"value","pollAfterMs":1,"xUserId":"value","xUsername":"value"}"""
                )
            )

        val xAccountConnectionAttemptPending1 =
            jsonMapper.readValue(
                """{"createdAt":"2026-10-17T00:00:00Z","expiresAt":"2026-10-17T00:00:00Z","health":"value","message":"value","username":"value","xUserId":"value","xUsername":"value"}""",
                jacksonTypeRef<AccountReauthResponse.XAccountConnectionAttemptPending>(),
            )
        assertThat(xAccountConnectionAttemptPending1.createdAt()).isNotNull()
        assertThat(xAccountConnectionAttemptPending1.expiresAt()).isNotNull()
        assertThat(xAccountConnectionAttemptPending1.health()).isNotNull()
        assertThat(xAccountConnectionAttemptPending1.message()).isNotNull()
        assertThat(xAccountConnectionAttemptPending1.username()).isNotNull()
        assertThat(xAccountConnectionAttemptPending1.xUserId()).isNotNull()
        assertThat(xAccountConnectionAttemptPending1.xUsername()).isNotNull()
        assertThat(
                jsonMapper.readTree(
                    jsonMapper.writeValueAsString(xAccountConnectionAttemptPending1)
                )
            )
            .isEqualTo(
                jsonMapper.readTree(
                    """{"createdAt":"2026-10-17T00:00:00Z","expiresAt":"2026-10-17T00:00:00Z","health":"value","message":"value","username":"value","xUserId":"value","xUsername":"value"}"""
                )
            )

        val sanitizedXAccount2 =
            jsonMapper.readValue(
                """{"expiresAt":"2026-10-17T00:00:00Z","message":"value","object":"value","pollAfterMs":1,"username":"value"}""",
                jacksonTypeRef<AccountReauthResponse.SanitizedXAccount>(),
            )
        assertThat(sanitizedXAccount2.expiresAt()).isNotNull()
        assertThat(sanitizedXAccount2.message()).isNotNull()
        assertThat(sanitizedXAccount2.object_()).isNotNull()
        assertThat(sanitizedXAccount2.pollAfterMs()).isNotNull()
        assertThat(sanitizedXAccount2.username()).isNotNull()
        assertThat(jsonMapper.readTree(jsonMapper.writeValueAsString(sanitizedXAccount2)))
            .isEqualTo(
                jsonMapper.readTree(
                    """{"expiresAt":"2026-10-17T00:00:00Z","message":"value","object":"value","pollAfterMs":1,"username":"value"}"""
                )
            )
    }

    @Test
    fun keepsAdditionalFieldsOptional() {
        val xAccountConnectionChallenge0 =
            AccountReauthResponse.XAccountConnectionChallenge.builder()
                .id(JsonMissing.of())
                .expiresAt(JsonMissing.of())
                .message(JsonMissing.of())
                .username(JsonMissing.of())
                .build()
        assertThat(xAccountConnectionChallenge0.createdAt()).isNull()
        assertThat(xAccountConnectionChallenge0.health()).isNull()
        assertThat(xAccountConnectionChallenge0.pollAfterMs()).isNull()
        assertThat(xAccountConnectionChallenge0.xUserId()).isNull()
        assertThat(xAccountConnectionChallenge0.xUsername()).isNull()
        val xAccountConnectionAttemptPending1 =
            AccountReauthResponse.XAccountConnectionAttemptPending.builder()
                .id(JsonMissing.of())
                .pollAfterMs(JsonMissing.of())
                .build()
        assertThat(xAccountConnectionAttemptPending1.createdAt()).isNull()
        assertThat(xAccountConnectionAttemptPending1.expiresAt()).isNull()
        assertThat(xAccountConnectionAttemptPending1.health()).isNull()
        assertThat(xAccountConnectionAttemptPending1.message()).isNull()
        assertThat(xAccountConnectionAttemptPending1.username()).isNull()
        assertThat(xAccountConnectionAttemptPending1.xUserId()).isNull()
        assertThat(xAccountConnectionAttemptPending1.xUsername()).isNull()
        val sanitizedXAccount2 =
            AccountReauthResponse.SanitizedXAccount.builder()
                .id(JsonMissing.of())
                .createdAt(JsonMissing.of())
                .health(JsonMissing.of())
                .xUserId(JsonMissing.of())
                .xUsername(JsonMissing.of())
                .build()
        assertThat(sanitizedXAccount2.expiresAt()).isNull()
        assertThat(sanitizedXAccount2.message()).isNull()
        assertThat(sanitizedXAccount2.object_()).isNull()
        assertThat(sanitizedXAccount2.pollAfterMs()).isNull()
        assertThat(sanitizedXAccount2.username()).isNull()
    }
}
