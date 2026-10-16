// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.credits

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CreditRetrieveTopupStatusResponseTest {

    @Test
    fun create() {
        val creditRetrieveTopupStatusResponse =
            CreditRetrieveTopupStatusResponse.builder()
                .status(CreditRetrieveTopupStatusResponse.Status.PAID)
                .amountDollars(0L)
                .credits("credits")
                .build()

        assertThat(creditRetrieveTopupStatusResponse.status())
            .isEqualTo(CreditRetrieveTopupStatusResponse.Status.PAID)
        assertThat(creditRetrieveTopupStatusResponse.amountDollars()).isEqualTo(0L)
        assertThat(creditRetrieveTopupStatusResponse.credits()).isEqualTo("credits")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val creditRetrieveTopupStatusResponse =
            CreditRetrieveTopupStatusResponse.builder()
                .status(CreditRetrieveTopupStatusResponse.Status.PAID)
                .amountDollars(0L)
                .credits("credits")
                .build()

        val roundtrippedCreditRetrieveTopupStatusResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(creditRetrieveTopupStatusResponse),
                jacksonTypeRef<CreditRetrieveTopupStatusResponse>(),
            )

        assertThat(roundtrippedCreditRetrieveTopupStatusResponse)
            .isEqualTo(creditRetrieveTopupStatusResponse)
    }
}
