// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.guestwallets

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class GuestWalletTopupResponseTest {

    @Test
    fun create() {
        val guestWalletTopupResponse =
            GuestWalletTopupResponse.builder()
                .amount(GuestWalletAmount.builder().amountMinor(0L).build())
                .checkoutUrl("checkout_url")
                .credits("credits")
                .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .instructions("instructions")
                .purchaseId("purchase_id")
                .status(GuestWalletTopupResponse.Status.CREATING)
                .walletId("wallet_id")
                .apiKey("api_key")
                .authorization(
                    GuestWalletTopupResponse.Authorization.builder()
                        .header(GuestWalletTopupResponse.Authorization.Header.AUTHORIZATION)
                        .scheme(GuestWalletTopupResponse.Authorization.Scheme.BEARER)
                        .build()
                )
                .credentialNotice(
                    GuestWalletTopupResponse.CredentialNotice
                        .STORE_API_KEY_AND_THE_IDEMPOTENCY_KEY_SECURELY_BEFORE_SHARING_CHECKOUT_URL_NO_EMAIL_RECOVERY_IS_AVAILABLE
                )
                .build()

        assertThat(guestWalletTopupResponse.amount())
            .isEqualTo(GuestWalletAmount.builder().amountMinor(0L).build())
        assertThat(guestWalletTopupResponse.checkoutUrl()).isEqualTo("checkout_url")
        assertThat(guestWalletTopupResponse.credits()).isEqualTo("credits")
        assertThat(guestWalletTopupResponse.expiresAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(guestWalletTopupResponse.instructions()).isEqualTo("instructions")
        assertThat(guestWalletTopupResponse.purchaseId()).isEqualTo("purchase_id")
        assertThat(guestWalletTopupResponse.status())
            .isEqualTo(GuestWalletTopupResponse.Status.CREATING)
        assertThat(guestWalletTopupResponse.walletId()).isEqualTo("wallet_id")
        assertThat(guestWalletTopupResponse.apiKey()).isEqualTo("api_key")
        assertThat(guestWalletTopupResponse.authorization())
            .isEqualTo(
                GuestWalletTopupResponse.Authorization.builder()
                    .header(GuestWalletTopupResponse.Authorization.Header.AUTHORIZATION)
                    .scheme(GuestWalletTopupResponse.Authorization.Scheme.BEARER)
                    .build()
            )
        assertThat(guestWalletTopupResponse.credentialNotice())
            .isEqualTo(
                GuestWalletTopupResponse.CredentialNotice
                    .STORE_API_KEY_AND_THE_IDEMPOTENCY_KEY_SECURELY_BEFORE_SHARING_CHECKOUT_URL_NO_EMAIL_RECOVERY_IS_AVAILABLE
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val guestWalletTopupResponse =
            GuestWalletTopupResponse.builder()
                .amount(GuestWalletAmount.builder().amountMinor(0L).build())
                .checkoutUrl("checkout_url")
                .credits("credits")
                .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .instructions("instructions")
                .purchaseId("purchase_id")
                .status(GuestWalletTopupResponse.Status.CREATING)
                .walletId("wallet_id")
                .apiKey("api_key")
                .authorization(
                    GuestWalletTopupResponse.Authorization.builder()
                        .header(GuestWalletTopupResponse.Authorization.Header.AUTHORIZATION)
                        .scheme(GuestWalletTopupResponse.Authorization.Scheme.BEARER)
                        .build()
                )
                .credentialNotice(
                    GuestWalletTopupResponse.CredentialNotice
                        .STORE_API_KEY_AND_THE_IDEMPOTENCY_KEY_SECURELY_BEFORE_SHARING_CHECKOUT_URL_NO_EMAIL_RECOVERY_IS_AVAILABLE
                )
                .build()

        val roundtrippedGuestWalletTopupResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(guestWalletTopupResponse),
                jacksonTypeRef<GuestWalletTopupResponse>(),
            )

        assertThat(roundtrippedGuestWalletTopupResponse).isEqualTo(guestWalletTopupResponse)
    }
}
