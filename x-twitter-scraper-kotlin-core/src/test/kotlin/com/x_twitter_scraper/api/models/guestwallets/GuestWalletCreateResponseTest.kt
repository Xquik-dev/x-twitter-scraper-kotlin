// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.guestwallets

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class GuestWalletCreateResponseTest {

    @Test
    fun create() {
        val guestWalletCreateResponse =
            GuestWalletCreateResponse.builder()
                .amount(GuestWalletAmount.builder().amountMinor(0L).build())
                .apiKey("api_key")
                .authorization(
                    GuestWalletCreateResponse.Authorization.builder()
                        .header(GuestWalletCreateResponse.Authorization.Header.AUTHORIZATION)
                        .scheme(GuestWalletCreateResponse.Authorization.Scheme.BEARER)
                        .build()
                )
                .checkoutUrl("checkout_url")
                .credits("credits")
                .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .instructions("instructions")
                .purchaseId("purchase_id")
                .status(GuestWalletCreateResponse.Status.CREATING)
                .walletId("wallet_id")
                .build()

        assertThat(guestWalletCreateResponse.amount())
            .isEqualTo(GuestWalletAmount.builder().amountMinor(0L).build())
        assertThat(guestWalletCreateResponse.apiKey()).isEqualTo("api_key")
        assertThat(guestWalletCreateResponse.authorization())
            .isEqualTo(
                GuestWalletCreateResponse.Authorization.builder()
                    .header(GuestWalletCreateResponse.Authorization.Header.AUTHORIZATION)
                    .scheme(GuestWalletCreateResponse.Authorization.Scheme.BEARER)
                    .build()
            )
        assertThat(guestWalletCreateResponse.checkoutUrl()).isEqualTo("checkout_url")
        assertThat(guestWalletCreateResponse.credits()).isEqualTo("credits")
        assertThat(guestWalletCreateResponse.expiresAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(guestWalletCreateResponse.instructions()).isEqualTo("instructions")
        assertThat(guestWalletCreateResponse.purchaseId()).isEqualTo("purchase_id")
        assertThat(guestWalletCreateResponse.status())
            .isEqualTo(GuestWalletCreateResponse.Status.CREATING)
        assertThat(guestWalletCreateResponse.walletId()).isEqualTo("wallet_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val guestWalletCreateResponse =
            GuestWalletCreateResponse.builder()
                .amount(GuestWalletAmount.builder().amountMinor(0L).build())
                .apiKey("api_key")
                .authorization(
                    GuestWalletCreateResponse.Authorization.builder()
                        .header(GuestWalletCreateResponse.Authorization.Header.AUTHORIZATION)
                        .scheme(GuestWalletCreateResponse.Authorization.Scheme.BEARER)
                        .build()
                )
                .checkoutUrl("checkout_url")
                .credits("credits")
                .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .instructions("instructions")
                .purchaseId("purchase_id")
                .status(GuestWalletCreateResponse.Status.CREATING)
                .walletId("wallet_id")
                .build()

        val roundtrippedGuestWalletCreateResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(guestWalletCreateResponse),
                jacksonTypeRef<GuestWalletCreateResponse>(),
            )

        assertThat(roundtrippedGuestWalletCreateResponse).isEqualTo(guestWalletCreateResponse)
    }
}
