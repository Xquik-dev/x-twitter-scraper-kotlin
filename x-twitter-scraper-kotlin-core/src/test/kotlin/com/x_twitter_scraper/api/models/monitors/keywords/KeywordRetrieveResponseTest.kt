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

internal class KeywordRetrieveResponseTest {

    @Test
    fun create() {
        val keywordRetrieveResponse =
            KeywordRetrieveResponse.builder()
                .id("id")
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .addEventType(EventType.TWEET_NEW)
                .isActive(true)
                .nextBillingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .query("query")
                .name("Launch mentions")
                .pausedAt(OffsetDateTime.parse("2025-01-15T12:00:00Z"))
                .pausedReason(KeywordRetrieveResponse.PausedReason.INSUFFICIENT_CREDITS)
                .build()

        assertThat(keywordRetrieveResponse.id()).isEqualTo("id")
        assertThat(keywordRetrieveResponse.createdAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(keywordRetrieveResponse.eventTypes()).containsExactly(EventType.TWEET_NEW)
        assertThat(keywordRetrieveResponse.isActive()).isEqualTo(true)
        assertThat(keywordRetrieveResponse.nextBillingAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(keywordRetrieveResponse.query()).isEqualTo("query")
        assertThat(keywordRetrieveResponse.name()).isEqualTo("Launch mentions")
        assertThat(keywordRetrieveResponse.pausedAt())
            .isEqualTo(OffsetDateTime.parse("2025-01-15T12:00:00Z"))
        assertThat(keywordRetrieveResponse.pausedReason())
            .isEqualTo(KeywordRetrieveResponse.PausedReason.INSUFFICIENT_CREDITS)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val keywordRetrieveResponse =
            KeywordRetrieveResponse.builder()
                .id("id")
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .addEventType(EventType.TWEET_NEW)
                .isActive(true)
                .nextBillingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .query("query")
                .name("Launch mentions")
                .pausedAt(OffsetDateTime.parse("2025-01-15T12:00:00Z"))
                .pausedReason(KeywordRetrieveResponse.PausedReason.INSUFFICIENT_CREDITS)
                .build()

        val roundtrippedKeywordRetrieveResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(keywordRetrieveResponse),
                jacksonTypeRef<KeywordRetrieveResponse>(),
            )

        assertThat(roundtrippedKeywordRetrieveResponse).isEqualTo(keywordRetrieveResponse)
    }
}
