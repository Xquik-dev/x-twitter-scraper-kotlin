// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.guestwallets

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class GuestWalletRetrieveStatusResponseTest {

    @Test
    fun create() {
        val guestWalletRetrieveStatusResponse =
            GuestWalletRetrieveStatusResponse.builder()
                .balance("balance")
                .latestPurchase(
                    GuestWalletRetrieveStatusResponse.LatestPurchase.builder()
                        .amount(GuestWalletAmount.builder().amountMinor(0L).build())
                        .checkoutUrl("checkout_url")
                        .credits("credits")
                        .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .purchaseId("purchase_id")
                        .status(GuestWalletRetrieveStatusResponse.LatestPurchase.Status.CREATING)
                        .build()
                )
                .pollAfterSeconds(GuestWalletRetrieveStatusResponse.PollAfterSeconds._2)
                .status(GuestWalletRetrieveStatusResponse.Status.ACTIVE)
                .topUp(GuestWalletRetrieveStatusResponse.TopUp.builder().build())
                .usable(true)
                .walletId("wallet_id")
                .build()

        assertThat(guestWalletRetrieveStatusResponse.balance()).isEqualTo("balance")
        assertThat(guestWalletRetrieveStatusResponse.latestPurchase())
            .isEqualTo(
                GuestWalletRetrieveStatusResponse.LatestPurchase.builder()
                    .amount(GuestWalletAmount.builder().amountMinor(0L).build())
                    .checkoutUrl("checkout_url")
                    .credits("credits")
                    .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .purchaseId("purchase_id")
                    .status(GuestWalletRetrieveStatusResponse.LatestPurchase.Status.CREATING)
                    .build()
            )
        assertThat(guestWalletRetrieveStatusResponse.pollAfterSeconds())
            .isEqualTo(GuestWalletRetrieveStatusResponse.PollAfterSeconds._2)
        assertThat(guestWalletRetrieveStatusResponse.status())
            .isEqualTo(GuestWalletRetrieveStatusResponse.Status.ACTIVE)
        assertThat(guestWalletRetrieveStatusResponse.topUp())
            .isEqualTo(GuestWalletRetrieveStatusResponse.TopUp.builder().build())
        assertThat(guestWalletRetrieveStatusResponse.usable()).isEqualTo(true)
        assertThat(guestWalletRetrieveStatusResponse.walletId()).isEqualTo("wallet_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val guestWalletRetrieveStatusResponse =
            GuestWalletRetrieveStatusResponse.builder()
                .balance("balance")
                .latestPurchase(
                    GuestWalletRetrieveStatusResponse.LatestPurchase.builder()
                        .amount(GuestWalletAmount.builder().amountMinor(0L).build())
                        .checkoutUrl("checkout_url")
                        .credits("credits")
                        .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .purchaseId("purchase_id")
                        .status(GuestWalletRetrieveStatusResponse.LatestPurchase.Status.CREATING)
                        .build()
                )
                .pollAfterSeconds(GuestWalletRetrieveStatusResponse.PollAfterSeconds._2)
                .status(GuestWalletRetrieveStatusResponse.Status.ACTIVE)
                .topUp(GuestWalletRetrieveStatusResponse.TopUp.builder().build())
                .usable(true)
                .walletId("wallet_id")
                .build()

        val roundtrippedGuestWalletRetrieveStatusResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(guestWalletRetrieveStatusResponse),
                jacksonTypeRef<GuestWalletRetrieveStatusResponse>(),
            )

        assertThat(roundtrippedGuestWalletRetrieveStatusResponse)
            .isEqualTo(guestWalletRetrieveStatusResponse)
    }
}
