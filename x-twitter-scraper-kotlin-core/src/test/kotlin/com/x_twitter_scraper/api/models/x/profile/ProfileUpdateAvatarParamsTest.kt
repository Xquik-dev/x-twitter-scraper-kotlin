// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.profile

import com.x_twitter_scraper.api.core.JsonMissing
import com.x_twitter_scraper.api.core.JsonNull
import com.x_twitter_scraper.api.core.MultipartField
import com.x_twitter_scraper.api.core.http.Headers
import java.io.InputStream
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

internal class ProfileUpdateAvatarParamsTest {

    @Test
    fun create() {
        ProfileUpdateAvatarParams.builder()
            .idempotencyKey("Idempotency-Key")
            .account("@elonmusk")
            .url("https://example.com/avatar.png")
            .build()
    }

    @Test
    fun headers() {
        val params =
            ProfileUpdateAvatarParams.builder()
                .idempotencyKey("Idempotency-Key")
                .account("@elonmusk")
                .url("https://example.com/avatar.png")
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(Headers.builder().put("Idempotency-Key", "Idempotency-Key").build())
    }

    @Test
    fun body() {
        val params =
            ProfileUpdateAvatarParams.builder()
                .idempotencyKey("Idempotency-Key")
                .account("@elonmusk")
                .url("https://example.com/avatar.png")
                .build()

        val body = params._body()

        assertThat(body.filterValues { !it.value.isMissing() && !it.value.isNull() })
            .usingRecursiveComparison()
            .withEqualsForType(
                { a, b -> a.readBytes() contentEquals b.readBytes() },
                InputStream::class.java,
            )
            .isEqualTo(
                mapOf(
                        "account" to MultipartField.of("@elonmusk"),
                        "url" to MultipartField.of("https://example.com/avatar.png"),
                    )
                    .mapValues { (_, field) ->
                        field.map { (it as? ByteArray)?.inputStream() ?: it }
                    }
            )
    }

    @Test
    fun fileWithoutUrl() {
        val params =
            ProfileUpdateAvatarParams.builder()
                .idempotencyKey("Idempotency-Key")
                .account("@example")
                .file("file bytes".byteInputStream())
                .build()
                .toBuilder()
                .build()

        assertThat(params.url()).isNull()
        assertThat(params.file()!!.readBytes()).isEqualTo("file bytes".toByteArray())
        assertThat(params._file().filename).isEqualTo("upload")
    }

    @Test
    fun omitsNullUploadInputs() {
        val urlParams =
            ProfileUpdateAvatarParams.builder()
                .idempotencyKey("Idempotency-Key")
                .account("@example")
                .url("https://example.com/image.png")
                .file(MultipartField.of<InputStream>(JsonNull.of()))
                .build()
        val fileParams =
            ProfileUpdateAvatarParams.builder()
                .idempotencyKey("Idempotency-Key")
                .account("@example")
                .url(MultipartField.of<String>(JsonNull.of()))
                .file("file bytes".byteInputStream())
                .build()

        assertThat(urlParams._body()).containsOnlyKeys("account", "url")
        assertThat(fileParams._body()).containsOnlyKeys("account", "file")
    }

    @Test
    fun rejectsBothUrlAndFile() {
        assertThatThrownBy {
                ProfileUpdateAvatarParams.builder()
                    .idempotencyKey("Idempotency-Key")
                    .account("@example")
                    .url("https://example.com/image.png")
                    .file("file bytes".byteInputStream())
                    .build()
            }
            .isInstanceOf(IllegalStateException::class.java)
            .hasMessage("Exactly one of `url` or `file` must be set")
    }

    @Test
    fun rejectsNeitherUrlNorFile() {
        listOf(JsonMissing.of(), JsonNull.of()).forEach { missing ->
            assertThatThrownBy {
                    ProfileUpdateAvatarParams.builder()
                        .idempotencyKey("Idempotency-Key")
                        .account("@example")
                        .url(MultipartField.of<String>(missing))
                        .file(MultipartField.of<InputStream>(missing))
                        .build()
                }
                .isInstanceOf(IllegalStateException::class.java)
                .hasMessage("Exactly one of `url` or `file` must be set")
        }
    }
}
