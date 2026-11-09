// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.accounts

import com.x_twitter_scraper.api.core.JsonMissing
import com.x_twitter_scraper.api.core.jsonMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AccountReauthParamsTest {

    @Test
    fun create() {
        AccountReauthParams.builder()
            .id("id")
            .password("<ACCOUNT_PASSWORD>")
            .email("account@example.invalid")
            .totpSecret("<TOTP_SECRET>")
            .connectionAttemptId("xatt_0123456789abcdef0123456789abcdef")
            .build()
    }

    @Test
    fun pathParams() {
        val params = AccountReauthParams.builder().id("id").password("<ACCOUNT_PASSWORD>").build()

        assertThat(params._pathParam(0)).isEqualTo("id")
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun body() {
        val params =
            AccountReauthParams.builder()
                .id("id")
                .password("<ACCOUNT_PASSWORD>")
                .email("account@example.invalid")
                .totpSecret("<TOTP_SECRET>")
                .connectionAttemptId("xatt_0123456789abcdef0123456789abcdef")
                .build()

        val body = params._body()

        assertThat(body.password()).isEqualTo("<ACCOUNT_PASSWORD>")
        assertThat(body.email()).isEqualTo("account@example.invalid")
        assertThat(body.totpSecret()).isEqualTo("<TOTP_SECRET>")
        assertThat(body.connectionAttemptId()).isEqualTo("xatt_0123456789abcdef0123456789abcdef")
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params = AccountReauthParams.builder().id("id").password("<ACCOUNT_PASSWORD>").build()

        val body = params._body()

        assertThat(body.password()).isEqualTo("<ACCOUNT_PASSWORD>")
        assertThat(body.connectionAttemptId()).isNull()
    }

    @Test
    fun connectionAttemptId() {
        val attemptId = "xatt_0123456789abcdef0123456789abcdef"
        val params =
            AccountReauthParams.builder()
                .id("id")
                .password("<ACCOUNT_PASSWORD>")
                .connectionAttemptId(attemptId)
                .build()
        val withoutId = params.toBuilder().connectionAttemptId(JsonMissing.of()).build()
        val jsonMapper = jsonMapper()
        val json = jsonMapper.readTree(jsonMapper.writeValueAsString(params._body()))
        val jsonWithoutId = jsonMapper.readTree(jsonMapper.writeValueAsString(withoutId._body()))

        assertThat(params.connectionAttemptId()).isEqualTo(attemptId)
        assertThat(params._connectionAttemptId().asKnown()).isEqualTo(attemptId)
        assertThat(json.get("connection_attempt_id").asText()).isEqualTo(attemptId)
        assertThat(jsonWithoutId.has("connection_attempt_id")).isFalse()
        assertThat(params.toBuilder().build()).isEqualTo(params)
        assertThat(params.toBuilder().build().hashCode()).isEqualTo(params.hashCode())
        assertThat(withoutId).isNotEqualTo(params)
        assertThat(withoutId.connectionAttemptId()).isNull()
        assertThat(params._body().toString()).contains("connectionAttemptId=$attemptId")
    }
}
