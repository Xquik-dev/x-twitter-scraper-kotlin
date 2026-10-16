// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.support.tickets

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TicketReplyResponseTest {

    @Test
    fun create() {
        val ticketReplyResponse =
            TicketReplyResponse.builder()
                .addAttachment(
                    TicketReplyResponse.Attachment.builder()
                        .publicId("publicId")
                        .status(TicketReplyResponse.Attachment.Status.PENDING)
                        .build()
                )
                .publicId("publicId")
                .build()

        assertThat(ticketReplyResponse.attachments())
            .containsExactly(
                TicketReplyResponse.Attachment.builder()
                    .publicId("publicId")
                    .status(TicketReplyResponse.Attachment.Status.PENDING)
                    .build()
            )
        assertThat(ticketReplyResponse.publicId()).isEqualTo("publicId")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val ticketReplyResponse =
            TicketReplyResponse.builder()
                .addAttachment(
                    TicketReplyResponse.Attachment.builder()
                        .publicId("publicId")
                        .status(TicketReplyResponse.Attachment.Status.PENDING)
                        .build()
                )
                .publicId("publicId")
                .build()

        val roundtrippedTicketReplyResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(ticketReplyResponse),
                jacksonTypeRef<TicketReplyResponse>(),
            )

        assertThat(roundtrippedTicketReplyResponse).isEqualTo(ticketReplyResponse)
    }
}
