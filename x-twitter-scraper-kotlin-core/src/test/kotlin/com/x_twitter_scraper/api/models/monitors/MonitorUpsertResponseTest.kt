// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.monitors

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import com.x_twitter_scraper.api.models.EventType
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MonitorUpsertResponseTest {

    private val response =
        MonitorUpsertResponse.builder()
            .id("42")
            .createdAt(OffsetDateTime.parse("2025-01-15T12:00:00Z"))
            .addEventType(EventType.TWEET_NEW)
            .isActive(true)
            .nextBillingAt(OffsetDateTime.parse("2025-01-15T13:00:00Z"))
            .username("elonmusk")
            .xUserId("1234567890")
            .created(false)
            .name("Launch watch")
            .pausedAt(OffsetDateTime.parse("2025-01-15T14:00:00Z"))
            .pausedReason(Monitor.PausedReason.INSUFFICIENT_CREDITS)
            .build()

    @Test
    fun create() {
        assertThat(response.created()).isEqualTo(false)
        assertThat(response.id()).isEqualTo("42")
        assertThat(response.eventTypes()).containsExactly(EventType.TWEET_NEW)
        assertThat(response.name()).isEqualTo("Launch watch")
        assertThat(response.pausedReason()).isEqualTo(Monitor.PausedReason.INSUFFICIENT_CREDITS)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()

        val roundtripped =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(response),
                jacksonTypeRef<MonitorUpsertResponse>(),
            )

        assertThat(roundtripped).isEqualTo(response)
        assertThat(roundtripped.validate()).isSameAs(roundtripped)
    }
}
