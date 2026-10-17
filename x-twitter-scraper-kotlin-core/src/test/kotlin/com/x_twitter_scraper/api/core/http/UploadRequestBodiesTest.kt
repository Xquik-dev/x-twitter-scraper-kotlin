package com.x_twitter_scraper.api.core.http

import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.JsonMissing
import com.x_twitter_scraper.api.core.MultipartField
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.jsonMapper
import com.x_twitter_scraper.api.models.support.tickets.TicketCreateParams
import com.x_twitter_scraper.api.models.support.tickets.TicketReplyParams
import com.x_twitter_scraper.api.models.x.media.MediaUploadParams
import com.x_twitter_scraper.api.models.x.profile.ProfileUpdateAvatarParams
import com.x_twitter_scraper.api.models.x.profile.ProfileUpdateBannerParams
import com.x_twitter_scraper.api.services.async.support.TicketServiceAsyncImpl
import com.x_twitter_scraper.api.services.async.x.MediaServiceAsyncImpl
import com.x_twitter_scraper.api.services.async.x.ProfileServiceAsyncImpl
import com.x_twitter_scraper.api.services.blocking.support.TicketServiceImpl
import com.x_twitter_scraper.api.services.blocking.x.MediaServiceImpl
import com.x_twitter_scraper.api.services.blocking.x.ProfileServiceImpl
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.util.Base64
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

internal class UploadRequestBodiesTest {

    private val png =
        Base64.getDecoder()
            .decode(
                "iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mP8/x8AAwMCAO+aWQAAAABJRU5ErkJggg=="
            )

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun ticketCreate_defaultAttachmentsUseBinaryContentType(addIndividually: Boolean) {
        val bytes = byteArrayOf(0xff.toByte(), 0xd8.toByte(), 0xff.toByte(), 0, 1)
        val httpClient = CapturingHttpClient()
        val options = ClientOptions.builder().httpClient(httpClient).apiKey("test").build()
        val params =
            TicketCreateParams.builder()
                .body("Please help")
                .subject("Upload issue")
                .build()
                .toBuilder()
                .apply {
                    if (addIndividually) {
                        addAttachment(bytes.inputStream())
                        addAttachment(bytes.inputStream())
                    } else {
                        attachments(listOf(bytes.inputStream(), bytes.inputStream()))
                    }
                }
                .build()
                .toBuilder()
                .build()

        TicketServiceImpl(options).withRawResponse().create(params).close()

        val attachments =
            multipartParts(checkNotNull(httpClient.request.body)).filter {
                disposition(it.first)["name"] == "attachments"
            }
        assertThat(attachments).hasSize(2)
        attachments.forEach {
            assertThat(disposition(it.first)).containsEntry("filename", "attachment")
            assertThat(it.first["content-type"]).isEqualTo("application/octet-stream")
            assertThat(it.second.toByteArray(Charsets.ISO_8859_1)).isEqualTo(bytes)
        }
    }

    @ParameterizedTest
    @CsvSource("false, false", "false, true", "true, false", "true, true")
    fun ticketCreate_withoutBodySendsOnlySubjectAndFileParts(
        async: Boolean,
        addIndividually: Boolean,
    ) =
        runBlocking<Unit> {
            val contents =
                listOf(byteArrayOf(0, 1, 0xff.toByte()), byteArrayOf(2, 3, 0xfe.toByte()))
            val httpClient = CapturingHttpClient()
            val options = ClientOptions.builder().httpClient(httpClient).apiKey("test").build()
            val params =
                TicketCreateParams.builder()
                    .subject("Upload issue")
                    .apply {
                        if (addIndividually) {
                            contents.forEach { addAttachment(it.inputStream()) }
                        } else {
                            attachments(contents.map { it.inputStream() })
                        }
                    }
                    .build()
                    .toBuilder()
                    .build()

            if (async) {
                TicketServiceAsyncImpl(options).withRawResponse().create(params).close()
            } else {
                TicketServiceImpl(options).withRawResponse().create(params).close()
            }

            val parts = multipartParts(checkNotNull(httpClient.request.body))
            assertThat(parts.map { disposition(it.first)["name"] })
                .containsExactlyInAnyOrder("subject", "attachments", "attachments")
            assertThat(parts.single { disposition(it.first)["name"] == "subject" }.second)
                .isEqualTo("Upload issue")
            val attachments = parts.filter { disposition(it.first)["name"] == "attachments" }
            attachments.forEachIndexed { index, part ->
                assertThat(disposition(part.first)).containsEntry("filename", "attachment")
                assertThat(part.first["content-type"]).isEqualTo("application/octet-stream")
                assertThat(part.second.toByteArray(Charsets.ISO_8859_1)).isEqualTo(contents[index])
            }
        }

    @ParameterizedTest
    @CsvSource(
        "false, false, false",
        "false, false, true",
        "false, true, false",
        "false, true, true",
        "true, false, false",
        "true, false, true",
        "true, true, false",
        "true, true, true",
    )
    fun ticketReply_serializesAttachmentsAndOptionalBody(
        async: Boolean,
        addIndividually: Boolean,
        withBody: Boolean,
    ) =
        runBlocking<Unit> {
            val contents =
                listOf(byteArrayOf(0, 1, 0xff.toByte()), byteArrayOf(2, 3, 0xfe.toByte()))
            val httpClient = CapturingHttpClient()
            val options = ClientOptions.builder().httpClient(httpClient).apiKey("test").build()
            val params =
                TicketReplyParams.builder()
                    .id("tkt_a1b2c3d4e5f6a1b2c3d4e5f6")
                    .apply {
                        if (withBody) body("Please see the attachments")
                        if (addIndividually) {
                            contents.forEach { addAttachment(it.inputStream()) }
                        } else {
                            attachments(contents.map { it.inputStream() })
                        }
                    }
                    .build()
                    .toBuilder()
                    .build()

            if (async) {
                TicketServiceAsyncImpl(options).withRawResponse().reply(params).close()
            } else {
                TicketServiceImpl(options).withRawResponse().reply(params).close()
            }

            val parts = multipartParts(checkNotNull(httpClient.request.body))
            val names = parts.map { disposition(it.first)["name"] }
            if (withBody) {
                assertThat(names).containsExactlyInAnyOrder("body", "attachments", "attachments")
                assertThat(parts.single { disposition(it.first)["name"] == "body" }.second)
                    .isEqualTo("Please see the attachments")
            } else {
                assertThat(names).containsExactlyInAnyOrder("attachments", "attachments")
            }
            val attachments = parts.filter { disposition(it.first)["name"] == "attachments" }
            attachments.forEachIndexed { index, part ->
                assertThat(disposition(part.first)).containsEntry("filename", "attachment")
                assertThat(part.first["content-type"]).isEqualTo("application/octet-stream")
                assertThat(part.second.toByteArray(Charsets.ISO_8859_1)).isEqualTo(contents[index])
            }
        }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun ticketReply_preservesCustomAttachmentMetadata(async: Boolean) =
        runBlocking<Unit> {
            val httpClient = CapturingHttpClient()
            val options = ClientOptions.builder().httpClient(httpClient).apiKey("test").build()
            val params =
                TicketReplyParams.builder()
                    .id("tkt_a1b2c3d4e5f6a1b2c3d4e5f6")
                    .attachments(
                        MultipartField.builder<List<InputStream>>()
                            .value(listOf("first".byteInputStream()))
                            .filename("details.txt")
                            .contentType("text/plain")
                            .build()
                    )
                    .build()
                    .toBuilder()
                    .addAttachment("second".byteInputStream())
                    .build()

            if (async) {
                TicketServiceAsyncImpl(options).withRawResponse().reply(params).close()
            } else {
                TicketServiceImpl(options).withRawResponse().reply(params).close()
            }

            val parts = multipartParts(checkNotNull(httpClient.request.body))
            assertThat(parts.map { it.second }).containsExactly("first", "second")
            parts.forEach { part ->
                assertThat(disposition(part.first))
                    .containsEntry("name", "attachments")
                    .containsEntry("filename", "details.txt")
                assertThat(part.first["content-type"]).isEqualTo("text/plain")
            }
        }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun mediaUpload_rejectsLongVideoFlagForUrl(isLongVideo: Boolean) {
        assertThatThrownBy {
                MediaUploadParams.builder()
                    .account("@example")
                    .idempotencyKey("test")
                    .url("https://example.com/video.mp4")
                    .isLongVideo(isLongVideo)
                    .build()
            }
            .isInstanceOf(IllegalStateException::class.java)
            .hasMessage("`isLongVideo` requires `file`")
    }

    @ParameterizedTest
    @CsvSource("false, false", "false, true", "true, false", "true, true")
    fun mediaUpload_serializesLongVideoFlagForFile(async: Boolean, isLongVideo: Boolean) =
        runBlocking<Unit> {
            val httpClient = CapturingHttpClient()
            val options = ClientOptions.builder().httpClient(httpClient).apiKey("test").build()
            val params =
                MediaUploadParams.builder()
                    .account("@example")
                    .idempotencyKey("test")
                    .file(png.inputStream())
                    .isLongVideo(isLongVideo)
                    .build()
                    .toBuilder()
                    .build()

            if (async) {
                MediaServiceAsyncImpl(options).withRawResponse().upload(params).close()
            } else {
                MediaServiceImpl(options).withRawResponse().upload(params).close()
            }

            val parts = multipartParts(checkNotNull(httpClient.request.body))
            assertThat(params.isLongVideo()).isEqualTo(isLongVideo)
            assertThat(parts.single { disposition(it.first)["name"] == "is_long_video" }.second)
                .isEqualTo(isLongVideo.toString())
            assertPngPart(parts.single { disposition(it.first)["name"] == "file" }, "file")
        }

    @Test
    fun multipartFormData_omitsMissingFields() {
        val body =
            multipartFormData(
                jsonMapper(),
                mapOf("file" to MultipartField.of<InputStream>(JsonMissing.of())),
            )
        val output = ByteArrayOutputStream()

        body.writeTo(output)

        assertThat(output.toString("UTF-8")).doesNotContain("Content-Disposition")
    }

    @ParameterizedTest
    @CsvSource(
        "media, false, false",
        "media, false, true",
        "media, true, false",
        "media, true, true",
        "avatar, false, false",
        "avatar, false, true",
        "avatar, true, false",
        "avatar, true, true",
        "banner, false, false",
        "banner, false, true",
        "banner, true, false",
        "banner, true, true",
    )
    fun uploadRequests_useTheInputContentType(route: String, async: Boolean, file: Boolean) =
        runBlocking<Unit> {
            val httpClient = CapturingHttpClient()
            val options = ClientOptions.builder().httpClient(httpClient).apiKey("test").build()
            val url = "https://example.com/image.png"

            when (route) {
                "media" -> {
                    val params =
                        MediaUploadParams.builder()
                            .account("@example")
                            .idempotencyKey("test")
                            .apply { if (file) file(png.inputStream()) else url(url) }
                            .build()
                    if (async) {
                        MediaServiceAsyncImpl(options).withRawResponse().upload(params).close()
                    } else {
                        MediaServiceImpl(options).withRawResponse().upload(params).close()
                    }
                }
                "avatar" -> {
                    val params =
                        ProfileUpdateAvatarParams.builder()
                            .account("@example")
                            .idempotencyKey("test")
                            .apply { if (file) file(png.inputStream()) else url(url) }
                            .build()
                    if (async) {
                        ProfileServiceAsyncImpl(options)
                            .withRawResponse()
                            .updateAvatar(params)
                            .close()
                    } else {
                        ProfileServiceImpl(options).withRawResponse().updateAvatar(params).close()
                    }
                }
                "banner" -> {
                    val params =
                        ProfileUpdateBannerParams.builder()
                            .account("@example")
                            .idempotencyKey("test")
                            .apply { if (file) file(png.inputStream()) else url(url) }
                            .build()
                    if (async) {
                        ProfileServiceAsyncImpl(options)
                            .withRawResponse()
                            .updateBanner(params)
                            .close()
                    } else {
                        ProfileServiceImpl(options).withRawResponse().updateBanner(params).close()
                    }
                }
                else -> error("Unknown route $route")
            }

            val body = checkNotNull(httpClient.request.body)
            if (file) {
                val parts = multipartParts(body)
                assertThat(parts.map { disposition(it.first).getValue("name") })
                    .containsExactlyInAnyOrder("account", "file")
                val account = parts.single { disposition(it.first)["name"] == "account" }
                assertThat(account.first["content-type"]).isEqualTo("text/plain; charset=utf-8")
                assertThat(account.second).isEqualTo("@example")
                assertPngPart(parts.single { disposition(it.first)["name"] == "file" }, "file")
            } else {
                assertThat(body.contentType()).isEqualTo("application/json")
                val output = ByteArrayOutputStream()
                body.writeTo(output)
                assertThat(jsonMapper().readTree(output.toByteArray()))
                    .isEqualTo(jsonMapper().readTree("{\"account\":\"@example\",\"url\":\"$url\"}"))
            }
        }

    @ParameterizedTest
    @CsvSource(
        "89504e470d0a1a0a00ff01020304, image/png, upload.png",
        "ffd8ff00ff010203040506070809, image/jpeg, upload.jpg",
        "47494638376100ff0102030405, image/gif, upload.gif",
        "47494638396100ff0102030405, image/gif, upload.gif",
        "52494646000000005745425000ff, image/webp, upload.webp",
        "00000018667479706176696600ff, image/avif, upload.avif",
        "00000018667479706176697300ff, image/avif, upload.avif",
        "000000186674797069736f6d00ff, video/mp4, upload.mp4",
        "000000186674797069736f3200ff, video/mp4, upload.mp4",
        "00000018667479706d70343100ff, video/mp4, upload.mp4",
        "00000018667479706d70343200ff, video/mp4, upload.mp4",
        "00000018667479706176633100ff, video/mp4, upload.mp4",
        "00000018667479704d34562000ff, video/mp4, upload.mp4",
        "00000018667479706865696300ff, application/octet-stream, upload",
        "00000018667479706865697800ff, application/octet-stream, upload",
        "00000018667479706d69663100ff, application/octet-stream, upload",
        "00000018667479707174202000ff, application/octet-stream, upload",
        "000102030405060708090a0b00ff, application/octet-stream, upload",
        "89504e470d0a1a, application/octet-stream, upload",
        "000000186674797069736f, application/octet-stream, upload",
        "'', application/octet-stream, upload",
    )
    fun uploadRequests_detectFileContentTypeAndPreserveBytes(
        hex: String,
        contentType: String,
        filename: String,
    ) {
        val bytes = hex.chunked(2).map { it.toInt(16).toByte() }.toByteArray()
        listOf(false, true).forEach { markSupported ->
            fun stream(): InputStream {
                if (markSupported) return bytes.inputStream()
                val input = bytes.inputStream()
                return object : InputStream() {
                    override fun read(): Int = input.read()

                    override fun read(buffer: ByteArray, offset: Int, length: Int): Int =
                        input.read(buffer, offset, minOf(length, 1))
                }
            }

            listOf("media", "avatar", "banner").forEach { route ->
                val httpClient = CapturingHttpClient()
                val options = ClientOptions.builder().httpClient(httpClient).apiKey("test").build()
                when (route) {
                    "media" -> {
                        val params =
                            MediaUploadParams.builder()
                                .account("@example")
                                .idempotencyKey("test")
                                .file(stream())
                                .apply { if (contentType == "video/mp4") isLongVideo(true) }
                                .build()
                        MediaServiceImpl(options).withRawResponse().upload(params).close()
                    }
                    "avatar" -> {
                        val params =
                            ProfileUpdateAvatarParams.builder()
                                .account("@example")
                                .idempotencyKey("test")
                                .file(stream())
                                .build()
                        ProfileServiceImpl(options).withRawResponse().updateAvatar(params).close()
                    }
                    "banner" -> {
                        val params =
                            ProfileUpdateBannerParams.builder()
                                .account("@example")
                                .idempotencyKey("test")
                                .file(stream())
                                .build()
                        ProfileServiceImpl(options).withRawResponse().updateBanner(params).close()
                    }
                    else -> error("Unknown route $route")
                }

                val parts = multipartParts(checkNotNull(httpClient.request.body))
                val part = parts.single { disposition(it.first)["name"] == "file" }
                assertThat(disposition(part.first)).containsEntry("filename", filename)
                assertThat(part.first["content-type"]).isEqualTo(contentType)
                assertThat(part.second.toByteArray(Charsets.ISO_8859_1)).isEqualTo(bytes)
                if (route == "media" && contentType == "video/mp4") {
                    val flag = parts.single { disposition(it.first)["name"] == "is_long_video" }
                    assertThat(flag.second).isEqualTo("true")
                }
            }
        }
    }

    private fun multipartParts(body: HttpRequestBody): List<Pair<Map<String, String>, String>> {
        assertThat(body.contentType()).startsWith("multipart/form-data; boundary=")
        val boundary = body.contentType()!!.substringAfter("boundary=")
        val output = ByteArrayOutputStream()
        body.writeTo(output)
        return output.toString("ISO-8859-1").split("--$boundary").drop(1).dropLast(1).map {
            val (headers, content) =
                it.removePrefix("\r\n").removeSuffix("\r\n").split("\r\n\r\n", limit = 2)
            headers.split("\r\n").associate { header ->
                header.substringBefore(":").lowercase() to header.substringAfter(":").trim()
            } to content
        }
    }

    @Test
    fun fileParts_preserveCustomMetadata() {
        fun field() =
            MultipartField.builder<InputStream>()
                .value("image".byteInputStream())
                .filename("photo.jpg")
                .contentType("image/jpeg")
                .build()

        val bodies =
            listOf(
                MediaUploadParams.builder()
                    .account("@example")
                    .idempotencyKey("test")
                    .file(field())
                    .build()
                    .toBuilder()
                    .build()
                    ._body(),
                ProfileUpdateAvatarParams.builder()
                    .account("@example")
                    .idempotencyKey("test")
                    .file(field())
                    .build()
                    .toBuilder()
                    .build()
                    ._body(),
                ProfileUpdateBannerParams.builder()
                    .account("@example")
                    .idempotencyKey("test")
                    .file(field())
                    .build()
                    .toBuilder()
                    .build()
                    ._body(),
            )

        bodies.forEach { fields ->
            val part = multipartParts(multipartFormData(jsonMapper(), fields)).last()
            assertThat(disposition(part.first))
                .containsEntry("name", "file")
                .containsEntry("filename", "photo.jpg")
            assertThat(part.first["content-type"]).isEqualTo("image/jpeg")
        }

        val params =
            TicketCreateParams.builder()
                .body("Please help")
                .subject("Upload issue")
                .attachments(
                    MultipartField.builder<List<InputStream>>()
                        .value(listOf("first".byteInputStream()))
                        .filename("photo.jpg")
                        .contentType("image/jpeg")
                        .build()
                )
                .build()
                .toBuilder()
                .addAttachment("second".byteInputStream())
                .build()
        val attachments =
            multipartParts(multipartFormData(jsonMapper(), params._body())).filter {
                disposition(it.first)["name"] == "attachments"
            }
        assertThat(attachments).hasSize(2)
        attachments.forEach {
            assertThat(disposition(it.first))
                .containsEntry("name", "attachments")
                .containsEntry("filename", "photo.jpg")
            assertThat(it.first["content-type"]).isEqualTo("image/jpeg")
        }
    }

    private fun disposition(headers: Map<String, String>): Map<String, String> =
        headers.getValue("content-disposition").split("; ").drop(1).associate {
            it.substringBefore("=") to it.substringAfter("=").removeSurrounding("\"")
        }

    private fun assertPngPart(part: Pair<Map<String, String>, String>, name: String) {
        val disposition = disposition(part.first)
        assertThat(disposition["name"]).isEqualTo(name)
        assertThat(disposition["filename"]).isNotBlank()
        assertThat(part.first["content-type"]).isEqualTo("image/png")
        assertThat(part.second.toByteArray(Charsets.ISO_8859_1)).isEqualTo(png)
    }

    private class CapturingHttpClient : HttpClient {

        lateinit var request: HttpRequest

        override fun execute(request: HttpRequest, requestOptions: RequestOptions): HttpResponse {
            this.request = request
            return object : HttpResponse {
                override fun statusCode(): Int = 200

                override fun headers(): Headers = Headers.builder().build()

                override fun body(): InputStream = "{}".byteInputStream()

                override fun close() {}
            }
        }

        override suspend fun executeAsync(
            request: HttpRequest,
            requestOptions: RequestOptions,
        ): HttpResponse = execute(request, requestOptions)

        override fun close() {}
    }
}
