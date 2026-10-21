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

internal class KeywordUpdateResponseTest {

    @Test
    fun create() {
        val keywordUpdateResponse =
            KeywordUpdateResponse.builder()
                .id("id")
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .addEventType(EventType.TWEET_NEW)
                .isActive(true)
                .nextBillingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .query("query")
                .name("Launch mentions")
                .pausedAt(OffsetDateTime.parse("2025-01-15T12:00:00Z"))
                .pausedReason(KeywordUpdateResponse.PausedReason.INSUFFICIENT_CREDITS)
                .build()

        assertThat(keywordUpdateResponse.id()).isEqualTo("id")
        assertThat(keywordUpdateResponse.createdAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(keywordUpdateResponse.eventTypes()).containsExactly(EventType.TWEET_NEW)
        assertThat(keywordUpdateResponse.isActive()).isEqualTo(true)
        assertThat(keywordUpdateResponse.nextBillingAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(keywordUpdateResponse.query()).isEqualTo("query")
        assertThat(keywordUpdateResponse.name()).isEqualTo("Launch mentions")
        assertThat(keywordUpdateResponse.pausedAt())
            .isEqualTo(OffsetDateTime.parse("2025-01-15T12:00:00Z"))
        assertThat(keywordUpdateResponse.pausedReason())
            .isEqualTo(KeywordUpdateResponse.PausedReason.INSUFFICIENT_CREDITS)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val keywordUpdateResponse =
            KeywordUpdateResponse.builder()
                .id("id")
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .addEventType(EventType.TWEET_NEW)
                .isActive(true)
                .nextBillingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .query("query")
                .name("Launch mentions")
                .pausedAt(OffsetDateTime.parse("2025-01-15T12:00:00Z"))
                .pausedReason(KeywordUpdateResponse.PausedReason.INSUFFICIENT_CREDITS)
                .build()

        val roundtrippedKeywordUpdateResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(keywordUpdateResponse),
                jacksonTypeRef<KeywordUpdateResponse>(),
            )

        assertThat(roundtrippedKeywordUpdateResponse).isEqualTo(keywordUpdateResponse)
    }

    @Test
    fun readsAdditionalFields() {
        val jsonMapper = jsonMapper()

        val keywordUpdateResponse0 =
            jsonMapper.readValue(
                """{"name":"value","pausedAt":"2026-10-17T00:00:00Z","pausedReason":"value"}""",
                jacksonTypeRef<KeywordUpdateResponse>(),
            )
        assertThat(keywordUpdateResponse0.name()).isNotNull()
        assertThat(keywordUpdateResponse0.pausedAt()).isNotNull()
        assertThat(keywordUpdateResponse0.pausedReason()).isNotNull()
        assertThat(jsonMapper.readTree(jsonMapper.writeValueAsString(keywordUpdateResponse0)))
            .isEqualTo(
                jsonMapper.readTree(
                    """{"name":"value","pausedAt":"2026-10-17T00:00:00Z","pausedReason":"value"}"""
                )
            )
    }

    @Test
    fun keepsAdditionalFieldsOptional() {
        val keywordUpdateResponse0 =
            KeywordUpdateResponse.builder()
                .id(JsonMissing.of())
                .createdAt(JsonMissing.of())
                .eventTypes(JsonMissing.of())
                .isActive(JsonMissing.of())
                .nextBillingAt(JsonMissing.of())
                .query(JsonMissing.of())
                .build()
        assertThat(keywordUpdateResponse0.name()).isNull()
        assertThat(keywordUpdateResponse0.pausedAt()).isNull()
        assertThat(keywordUpdateResponse0.pausedReason()).isNull()
    }
}
