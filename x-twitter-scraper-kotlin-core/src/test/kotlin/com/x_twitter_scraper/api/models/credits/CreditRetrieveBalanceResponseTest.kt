// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.credits

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CreditRetrieveBalanceResponseTest {

    @Test
    fun create() {
        val creditRetrieveBalanceResponse =
            CreditRetrieveBalanceResponse.builder()
                .autoTopupAmountDollars(0.0)
                .autoTopupEnabled(true)
                .autoTopupThreshold("auto_topup_threshold")
                .balance("balance")
                .lifetimePurchased("lifetime_purchased")
                .lifetimeUsed("lifetime_used")
                .autoTopupStopped(false)
                .build()

        assertThat(creditRetrieveBalanceResponse.autoTopupAmountDollars()).isEqualTo(0.0)
        assertThat(creditRetrieveBalanceResponse.autoTopupEnabled()).isEqualTo(true)
        assertThat(creditRetrieveBalanceResponse.autoTopupThreshold())
            .isEqualTo("auto_topup_threshold")
        assertThat(creditRetrieveBalanceResponse.balance()).isEqualTo("balance")
        assertThat(creditRetrieveBalanceResponse.lifetimePurchased())
            .isEqualTo("lifetime_purchased")
        assertThat(creditRetrieveBalanceResponse.lifetimeUsed()).isEqualTo("lifetime_used")
        assertThat(creditRetrieveBalanceResponse.autoTopupStopped()).isEqualTo(false)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val creditRetrieveBalanceResponse =
            CreditRetrieveBalanceResponse.builder()
                .autoTopupAmountDollars(0.0)
                .autoTopupEnabled(true)
                .autoTopupThreshold("auto_topup_threshold")
                .balance("balance")
                .lifetimePurchased("lifetime_purchased")
                .lifetimeUsed("lifetime_used")
                .autoTopupStopped(false)
                .build()

        val roundtrippedCreditRetrieveBalanceResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(creditRetrieveBalanceResponse),
                jacksonTypeRef<CreditRetrieveBalanceResponse>(),
            )

        assertThat(roundtrippedCreditRetrieveBalanceResponse)
            .isEqualTo(creditRetrieveBalanceResponse)
    }
}
