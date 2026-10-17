// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.support.tickets

import com.x_twitter_scraper.api.core.MultipartField
import com.x_twitter_scraper.api.core.http.Headers
import java.io.InputStream
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

internal class TicketCreateParamsTest {

    @Test
    fun create() {
        TicketCreateParams.builder()
            .idempotencyKey("Idempotency-Key")
            .body("I am unable to connect my X account. Please help.")
            .subject("Cannot connect X account")
            .build()
    }

    @Test
    fun createWithoutBodyOrAttachments() {
        assertThatThrownBy { TicketCreateParams.builder().subject("Upload issue").build() }
            .isInstanceOf(IllegalStateException::class.java)
            .hasMessage("At least one of `body` or `attachments` must be set")
    }

    @Test
    fun bodyWithoutBodyOrAttachments() {
        assertThatThrownBy { TicketCreateParams.Body.builder().subject("Upload issue").build() }
            .isInstanceOf(IllegalStateException::class.java)
            .hasMessage("At least one of `body` or `attachments` must be set")
    }

    @Test
    fun createWithEmptyAttachments() {
        assertThatThrownBy {
                TicketCreateParams.builder()
                    .subject("Upload issue")
                    .attachments(emptyList())
                    .build()
            }
            .isInstanceOf(IllegalStateException::class.java)
            .hasMessage("At least one of `body` or `attachments` must be set")
    }

    @Test
    fun bodyWithEmptyAttachments() {
        assertThatThrownBy {
                TicketCreateParams.Body.builder()
                    .subject("Upload issue")
                    .attachments(emptyList())
                    .build()
            }
            .isInstanceOf(IllegalStateException::class.java)
            .hasMessage("At least one of `body` or `attachments` must be set")
    }

    @Test
    fun bodyWithBodyOnly() {
        val body = TicketCreateParams.Body.builder().subject("Upload issue").body("Details").build()

        assertThat(body.body()).isEqualTo("Details")
        assertThat(body.attachments()).isNull()
        assertThat(body.validate()).isSameAs(body)
        assertThat(body.toBuilder().build()).isEqualTo(body)
    }

    @Test
    fun createWithAttachmentsOnly() {
        val attachment = "details".byteInputStream()
        val params =
            TicketCreateParams.builder()
                .subject("Upload issue")
                .attachments(listOf(attachment))
                .build()

        assertThat(params.body()).isNull()
        assertThat(params.attachments()).containsExactly(attachment)
        assertThat(params._body()).doesNotContainKey("body")
        assertThat(params.toBuilder().build()).isEqualTo(params)
    }

    @Test
    fun bodyWithAttachmentsOnly() {
        val attachment = "details".byteInputStream()
        val body =
            TicketCreateParams.Body.builder()
                .subject("Upload issue")
                .addAttachment(attachment)
                .build()

        assertThat(body.body()).isNull()
        assertThat(body.attachments()).containsExactly(attachment)
        assertThat(body.validate()).isSameAs(body)
        assertThat(body.toBuilder().build()).isEqualTo(body)
    }

    @Test
    fun createWithBodyAndAttachments() {
        val attachment = "details".byteInputStream()
        val params =
            TicketCreateParams.builder()
                .subject("Upload issue")
                .body("Details")
                .addAttachment(attachment)
                .build()

        assertThat(params.body()).isEqualTo("Details")
        assertThat(params.attachments()).containsExactly(attachment)
    }

    @Test
    fun bodyWithBodyAndAttachments() {
        val attachment = "details".byteInputStream()
        val body =
            TicketCreateParams.Body.builder()
                .subject("Upload issue")
                .body("Details")
                .attachments(listOf(attachment))
                .build()

        assertThat(body.body()).isEqualTo("Details")
        assertThat(body.attachments()).containsExactly(attachment)
    }

    @Test
    fun headers() {
        val params =
            TicketCreateParams.builder()
                .idempotencyKey("Idempotency-Key")
                .body("I am unable to connect my X account. Please help.")
                .subject("Cannot connect X account")
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(Headers.builder().put("Idempotency-Key", "Idempotency-Key").build())
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params =
            TicketCreateParams.builder()
                .body("I am unable to connect my X account. Please help.")
                .subject("Cannot connect X account")
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            TicketCreateParams.builder()
                .idempotencyKey("Idempotency-Key")
                .body("I am unable to connect my X account. Please help.")
                .subject("Cannot connect X account")
                .build()

        val body = params._body()

        assertThat(body.filterValues { !it.value.isNull() })
            .usingRecursiveComparison()
            .withEqualsForType(
                { a, b -> a.readBytes() contentEquals b.readBytes() },
                InputStream::class.java,
            )
            .isEqualTo(
                mapOf(
                        "body" to
                            MultipartField.of("I am unable to connect my X account. Please help."),
                        "subject" to MultipartField.of("Cannot connect X account"),
                    )
                    .mapValues { (_, field) ->
                        field.map { (it as? ByteArray)?.inputStream() ?: it }
                    }
            )
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            TicketCreateParams.builder()
                .body("I am unable to connect my X account. Please help.")
                .subject("Cannot connect X account")
                .build()

        val body = params._body()

        assertThat(body.filterValues { !it.value.isNull() })
            .usingRecursiveComparison()
            .withEqualsForType(
                { a, b -> a.readBytes() contentEquals b.readBytes() },
                InputStream::class.java,
            )
            .isEqualTo(
                mapOf(
                        "body" to
                            MultipartField.of("I am unable to connect my X account. Please help."),
                        "subject" to MultipartField.of("Cannot connect X account"),
                    )
                    .mapValues { (_, field) ->
                        field.map { (it as? ByteArray)?.inputStream() ?: it }
                    }
            )
    }
}
