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

internal class WebhookTest {

    @Test
    fun create() {
        val webhook =
            Webhook.builder()
                .id("id")
                .consecutiveFailures(0L)
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .deliveryStatus(Webhook.DeliveryStatus.ACTIVE)
                .addEventType(EventType.TWEET_NEW)
                .failureHardCap(0L)
                .isActive(true)
                .url("url")
                .build()

        assertThat(webhook.id()).isEqualTo("id")
        assertThat(webhook.consecutiveFailures()).isEqualTo(0L)
        assertThat(webhook.createdAt()).isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(webhook.deliveryStatus()).isEqualTo(Webhook.DeliveryStatus.ACTIVE)
        assertThat(webhook.eventTypes()).containsExactly(EventType.TWEET_NEW)
        assertThat(webhook.failureHardCap()).isEqualTo(0L)
        assertThat(webhook.isActive()).isEqualTo(true)
        assertThat(webhook.url()).isEqualTo("url")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val webhook =
            Webhook.builder()
                .id("id")
                .consecutiveFailures(0L)
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .deliveryStatus(Webhook.DeliveryStatus.ACTIVE)
                .addEventType(EventType.TWEET_NEW)
                .failureHardCap(0L)
                .isActive(true)
                .url("url")
                .build()

        val roundtrippedWebhook =
            jsonMapper.readValue(jsonMapper.writeValueAsString(webhook), jacksonTypeRef<Webhook>())

        assertThat(roundtrippedWebhook).isEqualTo(webhook)
    }
}
