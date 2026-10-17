// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.webhooks

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.JsonMissing
import com.x_twitter_scraper.api.core.jsonMapper
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WebhookListDeliveriesResponseTest {

    @Test
    fun create() {
        val webhookListDeliveriesResponse =
            WebhookListDeliveriesResponse.builder()
                .addDelivery(
                    Delivery.builder()
                        .id("id")
                        .attempts(0L)
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .status("status")
                        .streamEventId("streamEventId")
                        .build()
                )
                .hasMore(false)
                .nextCursor("abc123")
                .build()

        assertThat(webhookListDeliveriesResponse.deliveries())
            .containsExactly(
                Delivery.builder()
                    .id("id")
                    .attempts(0L)
                    .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .status("status")
                    .streamEventId("streamEventId")
                    .build()
            )
        assertThat(webhookListDeliveriesResponse.hasMore()).isEqualTo(false)
        assertThat(webhookListDeliveriesResponse.nextCursor()).isEqualTo("abc123")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val webhookListDeliveriesResponse =
            WebhookListDeliveriesResponse.builder()
                .addDelivery(
                    Delivery.builder()
                        .id("id")
                        .attempts(0L)
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .status("status")
                        .streamEventId("streamEventId")
                        .build()
                )
                .hasMore(false)
                .nextCursor("abc123")
                .build()

        val roundtrippedWebhookListDeliveriesResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(webhookListDeliveriesResponse),
                jacksonTypeRef<WebhookListDeliveriesResponse>(),
            )

        assertThat(roundtrippedWebhookListDeliveriesResponse)
            .isEqualTo(webhookListDeliveriesResponse)
    }

    @Test
    fun readsAdditionalFields() {
        val jsonMapper = jsonMapper()

        val webhookListDeliveriesResponse0 =
            jsonMapper.readValue(
                """{"hasMore":true,"nextCursor":"value"}""",
                jacksonTypeRef<WebhookListDeliveriesResponse>(),
            )
        assertThat(webhookListDeliveriesResponse0.hasMore()).isNotNull()
        assertThat(webhookListDeliveriesResponse0.nextCursor()).isNotNull()
        assertThat(
                jsonMapper.readTree(jsonMapper.writeValueAsString(webhookListDeliveriesResponse0))
            )
            .isEqualTo(jsonMapper.readTree("""{"hasMore":true,"nextCursor":"value"}"""))
    }

    @Test
    fun keepsAdditionalFieldsOptional() {
        val webhookListDeliveriesResponse0 =
            WebhookListDeliveriesResponse.builder().deliveries(JsonMissing.of()).build()
        assertThat(webhookListDeliveriesResponse0.hasMore()).isNull()
        assertThat(webhookListDeliveriesResponse0.nextCursor()).isNull()
    }
}
