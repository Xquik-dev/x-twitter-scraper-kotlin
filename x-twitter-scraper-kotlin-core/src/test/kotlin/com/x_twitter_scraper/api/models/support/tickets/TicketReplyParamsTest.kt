// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.support.tickets

import com.x_twitter_scraper.api.core.MultipartField
import com.x_twitter_scraper.api.core.http.Headers
import java.io.InputStream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TicketReplyParamsTest {

    @Test
    fun create() {
        TicketReplyParams.builder()
            .id("tkt_a1b2c3d4e5f6a1b2c3d4e5f6")
            .idempotencyKey("Idempotency-Key")
            .body("Thank you for the update.")
            .build()
    }

    @Test
    fun createWithoutBody() {
        val params = TicketReplyParams.builder().id("tkt_a1b2c3d4e5f6a1b2c3d4e5f6").build()

        assertThat(params.body()).isNull()
        assertThat(params._body()).doesNotContainKey("body")
        assertThat(params.toBuilder().build()).isEqualTo(params)
    }

    @Test
    fun bodyWithoutBody() {
        val body = TicketReplyParams.Body.builder().build()

        assertThat(body.body()).isNull()
        assertThat(body.validate()).isSameAs(body)
        assertThat(body.toBuilder().build()).isEqualTo(body)
    }

    @Test
    fun attachments() {
        val first = "first".byteInputStream()
        val second = "second".byteInputStream()
        val body =
            TicketReplyParams.Body.builder()
                .attachments(
                    MultipartField.builder<List<InputStream>>()
                        .value(listOf(first))
                        .filename("details.txt")
                        .contentType("text/plain")
                        .build()
                )
                .build()
                .toBuilder()
                .addAttachment(second)
                .build()
        val params = TicketReplyParams.builder().body(body).build().toBuilder().build()

        assertThat(params.body()).isNull()
        assertThat(params.attachments()).containsExactly(first, second)
        assertThat(params._attachments().filename).isEqualTo("details.txt")
        assertThat(params._attachments().contentType).isEqualTo("text/plain")
        assertThat(body.attachments()).containsExactly(first, second)
        assertThat(body.validate()).isSameAs(body)
        assertThat(params).isEqualTo(params.toBuilder().build())
    }

    @Test
    fun pathParams() {
        val params =
            TicketReplyParams.builder()
                .id("tkt_a1b2c3d4e5f6a1b2c3d4e5f6")
                .body("Thank you for the update.")
                .build()

        assertThat(params._pathParam(0)).isEqualTo("tkt_a1b2c3d4e5f6a1b2c3d4e5f6")
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            TicketReplyParams.builder()
                .id("tkt_a1b2c3d4e5f6a1b2c3d4e5f6")
                .idempotencyKey("Idempotency-Key")
                .body("Thank you for the update.")
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(Headers.builder().put("Idempotency-Key", "Idempotency-Key").build())
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params =
            TicketReplyParams.builder()
                .id("tkt_a1b2c3d4e5f6a1b2c3d4e5f6")
                .body("Thank you for the update.")
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            TicketReplyParams.builder()
                .id("tkt_a1b2c3d4e5f6a1b2c3d4e5f6")
                .idempotencyKey("Idempotency-Key")
                .body("Thank you for the update.")
                .build()

        val body = params._body()

        assertThat(body.filterValues { !it.value.isNull() })
            .usingRecursiveComparison()
            .withEqualsForType(
                { a, b -> a.readBytes() contentEquals b.readBytes() },
                InputStream::class.java,
            )
            .isEqualTo(
                mapOf("body" to MultipartField.of("Thank you for the update.")).mapValues {
                    (_, field) ->
                    field.map { (it as? ByteArray)?.inputStream() ?: it }
                }
            )
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            TicketReplyParams.builder()
                .id("tkt_a1b2c3d4e5f6a1b2c3d4e5f6")
                .body("Thank you for the update.")
                .build()

        val body = params._body()

        assertThat(body.filterValues { !it.value.isNull() })
            .usingRecursiveComparison()
            .withEqualsForType(
                { a, b -> a.readBytes() contentEquals b.readBytes() },
                InputStream::class.java,
            )
            .isEqualTo(
                mapOf("body" to MultipartField.of("Thank you for the update.")).mapValues {
                    (_, field) ->
                    field.map { (it as? ByteArray)?.inputStream() ?: it }
                }
            )
    }
}
