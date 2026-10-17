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

internal class MonitorListResponseTest {

    @Test
    fun create() {
        val monitorListResponse =
            MonitorListResponse.builder()
                .addMonitor(
                    Monitor.builder()
                        .id("id")
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .eventTypes(listOf(EventType.TWEET_NEW))
                        .isActive(true)
                        .nextBillingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .username("username")
                        .xUserId("xUserId")
                        .build()
                )
                .total(0L)
                .hasMore(false)
                .nextCursor("abc123")
                .build()

        assertThat(monitorListResponse.monitors())
            .containsExactly(
                Monitor.builder()
                    .id("id")
                    .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .eventTypes(listOf(EventType.TWEET_NEW))
                    .isActive(true)
                    .nextBillingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .username("username")
                    .xUserId("xUserId")
                    .build()
            )
        assertThat(monitorListResponse.total()).isEqualTo(0L)
        assertThat(monitorListResponse.hasMore()).isEqualTo(false)
        assertThat(monitorListResponse.nextCursor()).isEqualTo("abc123")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val monitorListResponse =
            MonitorListResponse.builder()
                .addMonitor(
                    Monitor.builder()
                        .id("id")
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .eventTypes(listOf(EventType.TWEET_NEW))
                        .isActive(true)
                        .nextBillingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .username("username")
                        .xUserId("xUserId")
                        .build()
                )
                .total(0L)
                .hasMore(false)
                .nextCursor("abc123")
                .build()

        val roundtrippedMonitorListResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(monitorListResponse),
                jacksonTypeRef<MonitorListResponse>(),
            )

        assertThat(roundtrippedMonitorListResponse).isEqualTo(monitorListResponse)
    }

    @Test
    fun readsAdditionalFields() {
        val jsonMapper = jsonMapper()

        val monitorListResponse0 =
            jsonMapper.readValue(
                """{"hasMore":true,"nextCursor":"value"}""",
                jacksonTypeRef<MonitorListResponse>(),
            )
        assertThat(monitorListResponse0.hasMore()).isNotNull()
        assertThat(monitorListResponse0.nextCursor()).isNotNull()
        assertThat(jsonMapper.readTree(jsonMapper.writeValueAsString(monitorListResponse0)))
            .isEqualTo(jsonMapper.readTree("""{"hasMore":true,"nextCursor":"value"}"""))
    }

    @Test
    fun keepsAdditionalFieldsOptional() {
        val monitorListResponse0 =
            MonitorListResponse.builder().monitors(JsonMissing.of()).total(JsonMissing.of()).build()
        assertThat(monitorListResponse0.hasMore()).isNull()
        assertThat(monitorListResponse0.nextCursor()).isNull()
    }
}
