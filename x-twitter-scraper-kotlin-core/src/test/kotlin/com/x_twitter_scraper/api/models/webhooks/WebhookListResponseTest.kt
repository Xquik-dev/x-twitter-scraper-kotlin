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

internal class WebhookListResponseTest {

    @Test
    fun create() {
        val webhookListResponse =
            WebhookListResponse.builder()
                .addWebhook(
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
                .hasMore(false)
                .nextCursor("abc123")
                .build()

        assertThat(webhookListResponse.webhooks())
            .containsExactly(
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
        assertThat(webhookListResponse.hasMore()).isEqualTo(false)
        assertThat(webhookListResponse.nextCursor()).isEqualTo("abc123")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val webhookListResponse =
            WebhookListResponse.builder()
                .addWebhook(
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
                .hasMore(false)
                .nextCursor("abc123")
                .build()

        val roundtrippedWebhookListResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(webhookListResponse),
                jacksonTypeRef<WebhookListResponse>(),
            )

        assertThat(roundtrippedWebhookListResponse).isEqualTo(webhookListResponse)
    }
}
