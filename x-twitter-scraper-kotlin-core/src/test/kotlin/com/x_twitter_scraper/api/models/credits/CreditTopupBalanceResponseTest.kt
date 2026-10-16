// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.credits

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CreditTopupBalanceResponseTest {

    @Test
    fun create() {
        val creditTopupBalanceResponse =
            CreditTopupBalanceResponse.builder().redirectUrl("redirect_url").url("url").build()

        assertThat(creditTopupBalanceResponse.redirectUrl()).isEqualTo("redirect_url")
        assertThat(creditTopupBalanceResponse.url()).isEqualTo("url")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val creditTopupBalanceResponse =
            CreditTopupBalanceResponse.builder().redirectUrl("redirect_url").url("url").build()

        val roundtrippedCreditTopupBalanceResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(creditTopupBalanceResponse),
                jacksonTypeRef<CreditTopupBalanceResponse>(),
            )

        assertThat(roundtrippedCreditTopupBalanceResponse).isEqualTo(creditTopupBalanceResponse)
    }
}
