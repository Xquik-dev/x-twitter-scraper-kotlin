// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.support.tickets

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TicketCreateResponseTest {

    @Test
    fun create() {
        val ticketCreateResponse =
            TicketCreateResponse.builder()
                .addAttachment(
                    TicketCreateResponse.Attachment.builder()
                        .publicId("publicId")
                        .status(TicketCreateResponse.Attachment.Status.PENDING)
                        .build()
                )
                .publicId("publicId")
                .build()

        assertThat(ticketCreateResponse.attachments())
            .containsExactly(
                TicketCreateResponse.Attachment.builder()
                    .publicId("publicId")
                    .status(TicketCreateResponse.Attachment.Status.PENDING)
                    .build()
            )
        assertThat(ticketCreateResponse.publicId()).isEqualTo("publicId")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val ticketCreateResponse =
            TicketCreateResponse.builder()
                .addAttachment(
                    TicketCreateResponse.Attachment.builder()
                        .publicId("publicId")
                        .status(TicketCreateResponse.Attachment.Status.PENDING)
                        .build()
                )
                .publicId("publicId")
                .build()

        val roundtrippedTicketCreateResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(ticketCreateResponse),
                jacksonTypeRef<TicketCreateResponse>(),
            )

        assertThat(roundtrippedTicketCreateResponse).isEqualTo(ticketCreateResponse)
    }
}
