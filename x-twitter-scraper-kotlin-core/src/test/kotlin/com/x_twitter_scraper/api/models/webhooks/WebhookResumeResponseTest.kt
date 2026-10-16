// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.webhooks

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import com.x_twitter_scraper.api.models.EventType
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WebhookResumeResponseTest {

    @Test
    fun create() {
        val webhookResumeResponse =
            WebhookResumeResponse.builder()
                .statusCode(0L)
                .success(true)
                .webhook(
                    Webhook.builder()
                        .id("id")
                        .consecutiveFailures(0L)
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .deliveryStatus(Webhook.DeliveryStatus.ACTIVE)
                        .eventTypes(listOf(EventType.TWEET_NEW))
                        .failureHardCap(0L)
                        .isActive(true)
                        .url("url")
                        .build()
                )
                .build()

        assertThat(webhookResumeResponse.statusCode()).isEqualTo(0L)
        assertThat(webhookResumeResponse.success()).isEqualTo(true)
        assertThat(webhookResumeResponse.webhook())
            .isEqualTo(
                Webhook.builder()
                    .id("id")
                    .consecutiveFailures(0L)
                    .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .deliveryStatus(Webhook.DeliveryStatus.ACTIVE)
                    .eventTypes(listOf(EventType.TWEET_NEW))
                    .failureHardCap(0L)
                    .isActive(true)
                    .url("url")
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val webhookResumeResponse =
            WebhookResumeResponse.builder()
                .statusCode(0L)
                .success(true)
                .webhook(
                    Webhook.builder()
                        .id("id")
                        .consecutiveFailures(0L)
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .deliveryStatus(Webhook.DeliveryStatus.ACTIVE)
                        .eventTypes(listOf(EventType.TWEET_NEW))
                        .failureHardCap(0L)
                        .isActive(true)
                        .url("url")
                        .build()
                )
                .build()

        val roundtrippedWebhookResumeResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(webhookResumeResponse),
                jacksonTypeRef<WebhookResumeResponse>(),
            )

        assertThat(roundtrippedWebhookResumeResponse).isEqualTo(webhookResumeResponse)
    }
}
