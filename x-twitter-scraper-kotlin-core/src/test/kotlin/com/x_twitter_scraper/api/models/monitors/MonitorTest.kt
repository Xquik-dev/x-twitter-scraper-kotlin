// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.monitors

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.JsonMissing
import com.x_twitter_scraper.api.core.jsonMapper
import com.x_twitter_scraper.api.models.EventType
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MonitorTest {

    @Test
    fun create() {
        val monitor =
            Monitor.builder()
                .id("id")
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .addEventType(EventType.TWEET_NEW)
                .isActive(true)
                .nextBillingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .username("username")
                .xUserId("xUserId")
                .name("name")
                .pausedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .pausedReason(Monitor.PausedReason.X_USER_NOT_FOUND)
                .build()

        assertThat(monitor.id()).isEqualTo("id")
        assertThat(monitor.createdAt()).isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(monitor.eventTypes()).containsExactly(EventType.TWEET_NEW)
        assertThat(monitor.isActive()).isEqualTo(true)
        assertThat(monitor.nextBillingAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(monitor.username()).isEqualTo("username")
        assertThat(monitor.xUserId()).isEqualTo("xUserId")
        assertThat(monitor.name()).isEqualTo("name")
        assertThat(monitor.pausedAt()).isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(monitor.pausedReason()).isEqualTo(Monitor.PausedReason.X_USER_NOT_FOUND)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val monitor =
            Monitor.builder()
                .id("id")
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .addEventType(EventType.TWEET_NEW)
                .isActive(true)
                .nextBillingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .username("username")
                .xUserId("xUserId")
                .name("name")
                .pausedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .pausedReason(Monitor.PausedReason.X_USER_NOT_FOUND)
                .build()

        val roundtrippedMonitor =
            jsonMapper.readValue(jsonMapper.writeValueAsString(monitor), jacksonTypeRef<Monitor>())

        assertThat(roundtrippedMonitor).isEqualTo(monitor)
    }

    @Test
    fun readsAdditionalFields() {
        val jsonMapper = jsonMapper()

        val monitor0 =
            jsonMapper.readValue(
                """{"name":"value"}""",
                jacksonTypeRef<Monitor>(),
            )
        assertThat(monitor0.name()).isNotNull()
        assertThat(jsonMapper.readTree(jsonMapper.writeValueAsString(monitor0)))
            .isEqualTo(jsonMapper.readTree("""{"name":"value"}"""))
    }

    @Test
    fun keepsAdditionalFieldsOptional() {
        val monitor0 =
            Monitor.builder()
                .id(JsonMissing.of())
                .createdAt(JsonMissing.of())
                .eventTypes(JsonMissing.of())
                .isActive(JsonMissing.of())
                .nextBillingAt(JsonMissing.of())
                .username(JsonMissing.of())
                .xUserId(JsonMissing.of())
                .build()
        assertThat(monitor0.name()).isNull()
    }
}
