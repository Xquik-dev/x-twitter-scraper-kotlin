// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.support.tickets

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.JsonMissing
import com.x_twitter_scraper.api.core.jsonMapper
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TicketListResponseTest {

    @Test
    fun create() {
        val ticketListResponse =
            TicketListResponse.builder()
                .addTicket(
                    TicketListResponse.Ticket.builder()
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .messageCount(0L)
                        .publicId("publicId")
                        .status(TicketListResponse.Ticket.Status.OPEN)
                        .subject("subject")
                        .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .build()
                )
                .hasMore(false)
                .nextCursor("abc123")
                .build()

        assertThat(ticketListResponse.tickets())
            .containsExactly(
                TicketListResponse.Ticket.builder()
                    .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .messageCount(0L)
                    .publicId("publicId")
                    .status(TicketListResponse.Ticket.Status.OPEN)
                    .subject("subject")
                    .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .build()
            )
        assertThat(ticketListResponse.hasMore()).isEqualTo(false)
        assertThat(ticketListResponse.nextCursor()).isEqualTo("abc123")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val ticketListResponse =
            TicketListResponse.builder()
                .addTicket(
                    TicketListResponse.Ticket.builder()
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .messageCount(0L)
                        .publicId("publicId")
                        .status(TicketListResponse.Ticket.Status.OPEN)
                        .subject("subject")
                        .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .build()
                )
                .hasMore(false)
                .nextCursor("abc123")
                .build()

        val roundtrippedTicketListResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(ticketListResponse),
                jacksonTypeRef<TicketListResponse>(),
            )

        assertThat(roundtrippedTicketListResponse).isEqualTo(ticketListResponse)
    }

    @Test
    fun readsAdditionalFields() {
        val jsonMapper = jsonMapper()

        val ticketListResponse0 =
            jsonMapper.readValue(
                """{"hasMore":true,"nextCursor":"value"}""",
                jacksonTypeRef<TicketListResponse>(),
            )
        assertThat(ticketListResponse0.hasMore()).isNotNull()
        assertThat(ticketListResponse0.nextCursor()).isNotNull()
        assertThat(jsonMapper.readTree(jsonMapper.writeValueAsString(ticketListResponse0)))
            .isEqualTo(jsonMapper.readTree("""{"hasMore":true,"nextCursor":"value"}"""))
    }

    @Test
    fun keepsAdditionalFieldsOptional() {
        val ticketListResponse0 = TicketListResponse.builder().tickets(JsonMissing.of()).build()
        assertThat(ticketListResponse0.hasMore()).isNull()
        assertThat(ticketListResponse0.nextCursor()).isNull()
    }
}
