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

internal class KeywordCreateResponseTest {

    @Test
    fun create() {
        val keywordCreateResponse =
            KeywordCreateResponse.builder()
                .id("id")
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .addEventType(EventType.TWEET_NEW)
                .isActive(true)
                .nextBillingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .query("query")
                .name("Launch mentions")
                .pausedAt(OffsetDateTime.parse("2025-01-15T12:00:00Z"))
                .pausedReason(KeywordCreateResponse.PausedReason.INSUFFICIENT_CREDITS)
                .build()

        assertThat(keywordCreateResponse.id()).isEqualTo("id")
        assertThat(keywordCreateResponse.createdAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(keywordCreateResponse.eventTypes()).containsExactly(EventType.TWEET_NEW)
        assertThat(keywordCreateResponse.isActive()).isEqualTo(true)
        assertThat(keywordCreateResponse.nextBillingAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(keywordCreateResponse.query()).isEqualTo("query")
        assertThat(keywordCreateResponse.name()).isEqualTo("Launch mentions")
        assertThat(keywordCreateResponse.pausedAt())
            .isEqualTo(OffsetDateTime.parse("2025-01-15T12:00:00Z"))
        assertThat(keywordCreateResponse.pausedReason())
            .isEqualTo(KeywordCreateResponse.PausedReason.INSUFFICIENT_CREDITS)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val keywordCreateResponse =
            KeywordCreateResponse.builder()
                .id("id")
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .addEventType(EventType.TWEET_NEW)
                .isActive(true)
                .nextBillingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .query("query")
                .name("Launch mentions")
                .pausedAt(OffsetDateTime.parse("2025-01-15T12:00:00Z"))
                .pausedReason(KeywordCreateResponse.PausedReason.INSUFFICIENT_CREDITS)
                .build()

        val roundtrippedKeywordCreateResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(keywordCreateResponse),
                jacksonTypeRef<KeywordCreateResponse>(),
            )

        assertThat(roundtrippedKeywordCreateResponse).isEqualTo(keywordCreateResponse)
    }

    @Test
    fun readsAdditionalFields() {
        val jsonMapper = jsonMapper()

        val keywordCreateResponse0 =
            jsonMapper.readValue(
                """{"name":"value","pausedAt":"2026-10-17T00:00:00Z","pausedReason":"value"}""",
                jacksonTypeRef<KeywordCreateResponse>(),
            )
        assertThat(keywordCreateResponse0.name()).isNotNull()
        assertThat(keywordCreateResponse0.pausedAt()).isNotNull()
        assertThat(keywordCreateResponse0.pausedReason()).isNotNull()
        assertThat(jsonMapper.readTree(jsonMapper.writeValueAsString(keywordCreateResponse0)))
            .isEqualTo(
                jsonMapper.readTree(
                    """{"name":"value","pausedAt":"2026-10-17T00:00:00Z","pausedReason":"value"}"""
                )
            )
    }

    @Test
    fun keepsAdditionalFieldsOptional() {
        val keywordCreateResponse0 =
            KeywordCreateResponse.builder()
                .id(JsonMissing.of())
                .createdAt(JsonMissing.of())
                .eventTypes(JsonMissing.of())
                .isActive(JsonMissing.of())
                .nextBillingAt(JsonMissing.of())
                .query(JsonMissing.of())
                .build()
        assertThat(keywordCreateResponse0.name()).isNull()
        assertThat(keywordCreateResponse0.pausedAt()).isNull()
        assertThat(keywordCreateResponse0.pausedReason()).isNull()
    }
}
