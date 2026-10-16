// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.account

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AccountRetrieveResponseTest {

    @Test
    fun create() {
        val accountRetrieveResponse =
            AccountRetrieveResponse.builder()
                .monitorBilling(
                    AccountRetrieveResponse.MonitorBilling.builder()
                        .activeDailyEstimate("activeDailyEstimate")
                        .activeHourlyBurn("activeHourlyBurn")
                        .creditsPerActiveMonitorDay("creditsPerActiveMonitorDay")
                        .creditsPerActiveMonitorHour("creditsPerActiveMonitorHour")
                        .eventsIncluded(true)
                        .instantCheckIntervalSeconds(0L)
                        .unlimitedSlots(true)
                        .build()
                )
                .monitorsAllowed(0L)
                .monitorsUsed(0L)
                .plan(AccountRetrieveResponse.Plan.ACTIVE)
                .creditInfo(
                    AccountRetrieveResponse.CreditInfo.builder()
                        .autoTopupAmountDollars(0.0)
                        .autoTopupEnabled(true)
                        .autoTopupThreshold("autoTopupThreshold")
                        .balance("balance")
                        .lifetimePurchased("lifetimePurchased")
                        .lifetimeUsed("lifetimeUsed")
                        .autoTopupStopped(false)
                        .build()
                )
                .subscription(
                    AccountRetrieveResponse.Subscription.builder()
                        .status(AccountRetrieveResponse.Subscription.Status.ACTIVE)
                        .currentPeriodEnd(OffsetDateTime.parse("2026-10-01T00:00:00.000Z"))
                        .currentPeriodStart(OffsetDateTime.parse("2026-09-01T00:00:00.000Z"))
                        .tier(AccountRetrieveResponse.Subscription.Tier.PRO)
                        .build()
                )
                .xUsername("xUsername")
                .build()

        assertThat(accountRetrieveResponse.monitorBilling())
            .isEqualTo(
                AccountRetrieveResponse.MonitorBilling.builder()
                    .activeDailyEstimate("activeDailyEstimate")
                    .activeHourlyBurn("activeHourlyBurn")
                    .creditsPerActiveMonitorDay("creditsPerActiveMonitorDay")
                    .creditsPerActiveMonitorHour("creditsPerActiveMonitorHour")
                    .eventsIncluded(true)
                    .instantCheckIntervalSeconds(0L)
                    .unlimitedSlots(true)
                    .build()
            )
        assertThat(accountRetrieveResponse.monitorsAllowed()).isEqualTo(0L)
        assertThat(accountRetrieveResponse.monitorsUsed()).isEqualTo(0L)
        assertThat(accountRetrieveResponse.plan()).isEqualTo(AccountRetrieveResponse.Plan.ACTIVE)
        assertThat(accountRetrieveResponse.creditInfo())
            .isEqualTo(
                AccountRetrieveResponse.CreditInfo.builder()
                    .autoTopupAmountDollars(0.0)
                    .autoTopupEnabled(true)
                    .autoTopupThreshold("autoTopupThreshold")
                    .balance("balance")
                    .lifetimePurchased("lifetimePurchased")
                    .lifetimeUsed("lifetimeUsed")
                    .autoTopupStopped(false)
                    .build()
            )
        assertThat(accountRetrieveResponse.subscription())
            .isEqualTo(
                AccountRetrieveResponse.Subscription.builder()
                    .status(AccountRetrieveResponse.Subscription.Status.ACTIVE)
                    .currentPeriodEnd(OffsetDateTime.parse("2026-10-01T00:00:00.000Z"))
                    .currentPeriodStart(OffsetDateTime.parse("2026-09-01T00:00:00.000Z"))
                    .tier(AccountRetrieveResponse.Subscription.Tier.PRO)
                    .build()
            )
        assertThat(accountRetrieveResponse.xUsername()).isEqualTo("xUsername")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val accountRetrieveResponse =
            AccountRetrieveResponse.builder()
                .monitorBilling(
                    AccountRetrieveResponse.MonitorBilling.builder()
                        .activeDailyEstimate("activeDailyEstimate")
                        .activeHourlyBurn("activeHourlyBurn")
                        .creditsPerActiveMonitorDay("creditsPerActiveMonitorDay")
                        .creditsPerActiveMonitorHour("creditsPerActiveMonitorHour")
                        .eventsIncluded(true)
                        .instantCheckIntervalSeconds(0L)
                        .unlimitedSlots(true)
                        .build()
                )
                .monitorsAllowed(0L)
                .monitorsUsed(0L)
                .plan(AccountRetrieveResponse.Plan.ACTIVE)
                .creditInfo(
                    AccountRetrieveResponse.CreditInfo.builder()
                        .autoTopupAmountDollars(0.0)
                        .autoTopupEnabled(true)
                        .autoTopupThreshold("autoTopupThreshold")
                        .balance("balance")
                        .lifetimePurchased("lifetimePurchased")
                        .lifetimeUsed("lifetimeUsed")
                        .autoTopupStopped(false)
                        .build()
                )
                .subscription(
                    AccountRetrieveResponse.Subscription.builder()
                        .status(AccountRetrieveResponse.Subscription.Status.ACTIVE)
                        .currentPeriodEnd(OffsetDateTime.parse("2026-10-01T00:00:00.000Z"))
                        .currentPeriodStart(OffsetDateTime.parse("2026-09-01T00:00:00.000Z"))
                        .tier(AccountRetrieveResponse.Subscription.Tier.PRO)
                        .build()
                )
                .xUsername("xUsername")
                .build()

        val roundtrippedAccountRetrieveResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(accountRetrieveResponse),
                jacksonTypeRef<AccountRetrieveResponse>(),
            )

        assertThat(roundtrippedAccountRetrieveResponse).isEqualTo(accountRetrieveResponse)
    }
}
