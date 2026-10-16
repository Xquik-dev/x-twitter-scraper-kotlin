// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.monitors.keywords

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
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
}
