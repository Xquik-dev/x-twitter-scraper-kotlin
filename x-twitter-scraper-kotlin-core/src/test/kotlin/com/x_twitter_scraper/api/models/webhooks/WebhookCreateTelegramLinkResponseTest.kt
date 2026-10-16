// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.webhooks

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WebhookCreateTelegramLinkResponseTest {

    @Test
    fun create() {
        val webhookCreateTelegramLinkResponse =
            WebhookCreateTelegramLinkResponse.builder()
                .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .url("url")
                .build()

        assertThat(webhookCreateTelegramLinkResponse.expiresAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(webhookCreateTelegramLinkResponse.url()).isEqualTo("url")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val webhookCreateTelegramLinkResponse =
            WebhookCreateTelegramLinkResponse.builder()
                .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .url("url")
                .build()

        val roundtrippedWebhookCreateTelegramLinkResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(webhookCreateTelegramLinkResponse),
                jacksonTypeRef<WebhookCreateTelegramLinkResponse>(),
            )

        assertThat(roundtrippedWebhookCreateTelegramLinkResponse)
            .isEqualTo(webhookCreateTelegramLinkResponse)
    }
}
