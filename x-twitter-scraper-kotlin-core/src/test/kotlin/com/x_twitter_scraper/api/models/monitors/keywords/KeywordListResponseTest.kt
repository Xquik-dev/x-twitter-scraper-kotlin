// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.monitors.keywords

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.JsonMissing
import com.x_twitter_scraper.api.core.jsonMapper
import com.x_twitter_scraper.api.models.EventType
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class KeywordListResponseTest {

    @Test
    fun create() {
        val keywordListResponse =
            KeywordListResponse.builder()
                .addMonitor(
                    KeywordListResponse.Monitor.builder()
                        .id("id")
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .addEventType(EventType.TWEET_NEW)
                        .isActive(true)
                        .nextBillingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .query("query")
                        .name("Launch mentions")
                        .pausedAt(OffsetDateTime.parse("2025-01-15T12:00:00Z"))
                        .pausedReason(KeywordListResponse.Monitor.PausedReason.INSUFFICIENT_CREDITS)
                        .build()
                )
                .total(0L)
                .hasMore(false)
                .nextCursor("abc123")
                .build()

        assertThat(keywordListResponse.monitors())
            .containsExactly(
                KeywordListResponse.Monitor.builder()
                    .id("id")
                    .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .addEventType(EventType.TWEET_NEW)
                    .isActive(true)
                    .nextBillingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .query("query")
                    .name("Launch mentions")
                    .pausedAt(OffsetDateTime.parse("2025-01-15T12:00:00Z"))
                    .pausedReason(KeywordListResponse.Monitor.PausedReason.INSUFFICIENT_CREDITS)
                    .build()
            )
        assertThat(keywordListResponse.total()).isEqualTo(0L)
        assertThat(keywordListResponse.hasMore()).isEqualTo(false)
        assertThat(keywordListResponse.nextCursor()).isEqualTo("abc123")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val keywordListResponse =
            KeywordListResponse.builder()
                .addMonitor(
                    KeywordListResponse.Monitor.builder()
                        .id("id")
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .addEventType(EventType.TWEET_NEW)
                        .isActive(true)
                        .nextBillingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .query("query")
                        .name("Launch mentions")
                        .pausedAt(OffsetDateTime.parse("2025-01-15T12:00:00Z"))
                        .pausedReason(KeywordListResponse.Monitor.PausedReason.INSUFFICIENT_CREDITS)
                        .build()
                )
                .total(0L)
                .hasMore(false)
                .nextCursor("abc123")
                .build()

        val roundtrippedKeywordListResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(keywordListResponse),
                jacksonTypeRef<KeywordListResponse>(),
            )

        assertThat(roundtrippedKeywordListResponse).isEqualTo(keywordListResponse)
    }

    @Test
    fun readsAdditionalFields() {
        val jsonMapper = jsonMapper()

        val monitor0 =
            jsonMapper.readValue(
                """{"name":"value","pausedAt":"2026-10-17T00:00:00Z","pausedReason":"value"}""",
                jacksonTypeRef<KeywordListResponse.Monitor>(),
            )
        assertThat(monitor0.name()).isNotNull()
        assertThat(monitor0.pausedAt()).isNotNull()
        assertThat(monitor0.pausedReason()).isNotNull()
        assertThat(jsonMapper.readTree(jsonMapper.writeValueAsString(monitor0)))
            .isEqualTo(
                jsonMapper.readTree(
                    """{"name":"value","pausedAt":"2026-10-17T00:00:00Z","pausedReason":"value"}"""
                )
            )

        val keywordListResponse1 =
            jsonMapper.readValue(
                """{"hasMore":true,"nextCursor":"value"}""",
                jacksonTypeRef<KeywordListResponse>(),
            )
        assertThat(keywordListResponse1.hasMore()).isNotNull()
        assertThat(keywordListResponse1.nextCursor()).isNotNull()
        assertThat(jsonMapper.readTree(jsonMapper.writeValueAsString(keywordListResponse1)))
            .isEqualTo(jsonMapper.readTree("""{"hasMore":true,"nextCursor":"value"}"""))
    }

    @Test
    fun keepsAdditionalFieldsOptional() {
        val monitor0 =
            KeywordListResponse.Monitor.builder()
                .id(JsonMissing.of())
                .createdAt(JsonMissing.of())
                .eventTypes(JsonMissing.of())
                .isActive(JsonMissing.of())
                .nextBillingAt(JsonMissing.of())
                .query(JsonMissing.of())
                .build()
        assertThat(monitor0.name()).isNull()
        assertThat(monitor0.pausedAt()).isNull()
        assertThat(monitor0.pausedReason()).isNull()
        val keywordListResponse1 =
            KeywordListResponse.builder().monitors(JsonMissing.of()).total(JsonMissing.of()).build()
        assertThat(keywordListResponse1.hasMore()).isNull()
        assertThat(keywordListResponse1.nextCursor()).isNull()
    }
}
