// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.events

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import com.x_twitter_scraper.api.models.EventType
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class EventListResponseTest {

    @Test
    fun create() {
        val eventListResponse =
            EventListResponse.builder()
                .addEvent(
                    Event.builder()
                        .id("id")
                        .data(Event.Data.builder().build())
                        .monitorId("monitorId")
                        .monitorType(Event.MonitorType.ACCOUNT)
                        .occurredAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .type(EventType.TWEET_NEW)
                        .build()
                )
                .hasMore(true)
                .nextCursor("nextCursor")
                .build()

        assertThat(eventListResponse.events())
            .containsExactly(
                Event.builder()
                    .id("id")
                    .data(Event.Data.builder().build())
                    .monitorId("monitorId")
                    .monitorType(Event.MonitorType.ACCOUNT)
                    .occurredAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .type(EventType.TWEET_NEW)
                    .build()
            )
        assertThat(eventListResponse.hasMore()).isEqualTo(true)
        assertThat(eventListResponse.nextCursor()).isEqualTo("nextCursor")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val eventListResponse =
            EventListResponse.builder()
                .addEvent(
                    Event.builder()
                        .id("id")
                        .data(Event.Data.builder().build())
                        .monitorId("monitorId")
                        .monitorType(Event.MonitorType.ACCOUNT)
                        .occurredAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .type(EventType.TWEET_NEW)
                        .build()
                )
                .hasMore(true)
                .nextCursor("nextCursor")
                .build()

        val roundtrippedEventListResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(eventListResponse),
                jacksonTypeRef<EventListResponse>(),
            )

        assertThat(roundtrippedEventListResponse).isEqualTo(eventListResponse)
    }
}
