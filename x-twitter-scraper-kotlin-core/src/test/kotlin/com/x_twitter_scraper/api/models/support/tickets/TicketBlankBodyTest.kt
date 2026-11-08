// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.support.tickets

import java.io.ByteArrayInputStream
import java.io.InputStream
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

internal class TicketBlankBodyTest {

    @Test
    fun ticketWithABlankBodyAndNoAttachmentFailsToBuild() {
        listOf("", " ", "\n\t").forEach { body ->
            assertThatThrownBy {
                    TicketCreateParams.builder()
                        .body(body)
                        .subject("Cannot connect X account")
                        .build()
                }
                .isInstanceOf(IllegalStateException::class.java)
                .hasMessage("At least one of `body` or `attachments` must be set")
        }
        assertThatThrownBy {
                TicketCreateParams.builder()
                    .body(" ")
                    .subject("Cannot connect X account")
                    .attachments(listOf<InputStream>())
                    .build()
            }
            .isInstanceOf(IllegalStateException::class.java)
            .hasMessage("At least one of `body` or `attachments` must be set")
    }

    @Test
    fun ticketWithABlankBodyAndAnAttachmentBuilds() {
        val params =
            TicketCreateParams.builder()
                .body("")
                .subject("Cannot connect X account")
                .addAttachment(ByteArrayInputStream("screenshot".toByteArray()))
                .build()

        assertThat(params.body()).isEmpty()
        assertThat(params.attachments()).hasSize(1)
    }

    @Test
    fun ticketWithTextBuildsWithoutAttachments() {
        val params =
            TicketCreateParams.builder()
                .body("I am unable to connect my X account. Please help.")
                .subject("Cannot connect X account")
                .build()

        assertThat(params.body()).isEqualTo("I am unable to connect my X account. Please help.")
        assertThat(params.attachments()).isNull()
    }

    @Test
    fun replyWithABlankBodyFailsToBuild() {
        listOf("", "  ", "\r\n").forEach { body ->
            assertThatThrownBy {
                    TicketReplyParams.builder()
                        .id("tkt_a1b2c3d4e5f6a1b2c3d4e5f6")
                        .body(body)
                        .build()
                }
                .isInstanceOf(IllegalStateException::class.java)
                .hasMessage("`body` must not be blank")
        }
        assertThat(
                TicketReplyParams.builder()
                    .id("tkt_a1b2c3d4e5f6a1b2c3d4e5f6")
                    .body("Thank you for the update.")
                    .build()
                    .body()
            )
            .isEqualTo("Thank you for the update.")
    }
}
