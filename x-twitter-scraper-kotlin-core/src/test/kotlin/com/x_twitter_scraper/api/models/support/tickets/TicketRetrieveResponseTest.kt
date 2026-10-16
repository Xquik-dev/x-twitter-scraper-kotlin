// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.support.tickets

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TicketRetrieveResponseTest {

    @Test
    fun create() {
        val ticketRetrieveResponse =
            TicketRetrieveResponse.builder()
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .addMessage(
                    TicketRetrieveResponse.Message.builder()
                        .addAttachment(
                            TicketRetrieveResponse.Message.Attachment.builder()
                                .contentType(
                                    TicketRetrieveResponse.Message.Attachment.ContentType.IMAGE_JPEG
                                )
                                .filename("filename")
                                .kind(TicketRetrieveResponse.Message.Attachment.Kind.IMAGE)
                                .publicId("publicId")
                                .sizeBytes(0L)
                                .status(TicketRetrieveResponse.Message.Attachment.Status.PENDING)
                                .url("url")
                                .build()
                        )
                        .body("body")
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .sender(TicketRetrieveResponse.Message.Sender.USER)
                        .build()
                )
                .publicId("publicId")
                .status(TicketRetrieveResponse.Status.OPEN)
                .subject("subject")
                .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        assertThat(ticketRetrieveResponse.createdAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(ticketRetrieveResponse.messages())
            .containsExactly(
                TicketRetrieveResponse.Message.builder()
                    .addAttachment(
                        TicketRetrieveResponse.Message.Attachment.builder()
                            .contentType(
                                TicketRetrieveResponse.Message.Attachment.ContentType.IMAGE_JPEG
                            )
                            .filename("filename")
                            .kind(TicketRetrieveResponse.Message.Attachment.Kind.IMAGE)
                            .publicId("publicId")
                            .sizeBytes(0L)
                            .status(TicketRetrieveResponse.Message.Attachment.Status.PENDING)
                            .url("url")
                            .build()
                    )
                    .body("body")
                    .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .sender(TicketRetrieveResponse.Message.Sender.USER)
                    .build()
            )
        assertThat(ticketRetrieveResponse.publicId()).isEqualTo("publicId")
        assertThat(ticketRetrieveResponse.status()).isEqualTo(TicketRetrieveResponse.Status.OPEN)
        assertThat(ticketRetrieveResponse.subject()).isEqualTo("subject")
        assertThat(ticketRetrieveResponse.updatedAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val ticketRetrieveResponse =
            TicketRetrieveResponse.builder()
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .addMessage(
                    TicketRetrieveResponse.Message.builder()
                        .addAttachment(
                            TicketRetrieveResponse.Message.Attachment.builder()
                                .contentType(
                                    TicketRetrieveResponse.Message.Attachment.ContentType.IMAGE_JPEG
                                )
                                .filename("filename")
                                .kind(TicketRetrieveResponse.Message.Attachment.Kind.IMAGE)
                                .publicId("publicId")
                                .sizeBytes(0L)
                                .status(TicketRetrieveResponse.Message.Attachment.Status.PENDING)
                                .url("url")
                                .build()
                        )
                        .body("body")
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .sender(TicketRetrieveResponse.Message.Sender.USER)
                        .build()
                )
                .publicId("publicId")
                .status(TicketRetrieveResponse.Status.OPEN)
                .subject("subject")
                .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        val roundtrippedTicketRetrieveResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(ticketRetrieveResponse),
                jacksonTypeRef<TicketRetrieveResponse>(),
            )

        assertThat(roundtrippedTicketRetrieveResponse).isEqualTo(ticketRetrieveResponse)
    }
}
