// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.core.http

import com.x_twitter_scraper.api.core.handlers.emptyHandler
import com.x_twitter_scraper.api.core.handlers.jsonHandler
import com.x_twitter_scraper.api.core.handlers.stringHandler
import com.x_twitter_scraper.api.core.jsonMapper
import com.x_twitter_scraper.api.errors.XTwitterScraperInvalidDataException
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.net.Proxy
import java.nio.charset.StandardCharsets
import java.util.Base64
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

internal class CoreHttpHelpersTest {

    @Test
    fun basicProxyAuthenticatorsEncodeCredentials() {
        val request =
            HttpRequest.builder().method(HttpMethod.GET).baseUrl("https://example.com").build()
        val response = response(407, "")

        val defaultRequest =
            ProxyAuthenticator.basic("user", "pass")
                .authenticate(Proxy.NO_PROXY, request, response)
                .get()
        val utf8Request =
            ProxyAuthenticator.basic("üser", "päss", StandardCharsets.UTF_8)
                .authenticate(Proxy.NO_PROXY, request, response)
                .get()

        assertThat(defaultRequest.headers.values("Proxy-Authorization"))
            .containsExactly("Basic dXNlcjpwYXNz")
        val utf8Token =
            Base64.getEncoder().encodeToString("üser:päss".toByteArray(StandardCharsets.UTF_8))
        assertThat(utf8Request.headers.values("Proxy-Authorization"))
            .containsExactly("Basic $utf8Token")
    }

    @Test
    fun streamMappingTransformsValuesAndDelegatesClose() {
        var closed = false
        val source =
            object : StreamResponse<Int> {
                override fun asSequence(): Sequence<Int> = sequenceOf(1, 2)

                override fun close() {
                    closed = true
                }
            }
        val mapped = source.map { it * 2 }

        assertThat(mapped.asSequence().toList()).containsExactly(2, 4)
        mapped.close()
        assertThat(closed).isTrue()
    }

    @Test
    fun responseHandlersReadExpectedContent() {
        assertThat(emptyHandler().handle(response(204, ""))).isNull()
        assertThat(stringHandler().handle(response(200, "hello"))).isEqualTo("hello")
        assertThat(
                jsonHandler<Map<String, String>>(jsonMapper())
                    .handle(response(200, """{"key":"value"}"""))
            )
            .containsEntry("key", "value")
        assertThatThrownBy {
                jsonHandler<Map<String, String>>(jsonMapper()).handle(response(200, "{"))
            }
            .isInstanceOf(XTwitterScraperInvalidDataException::class.java)
            .hasMessage("Error reading response")
    }

    private fun response(status: Int, content: String): HttpResponse =
        object : HttpResponse {
            override fun statusCode(): Int = status

            override fun headers(): Headers = Headers.builder().build()

            override fun body(): InputStream =
                ByteArrayInputStream(content.toByteArray(StandardCharsets.UTF_8))

            override fun close() {}
        }
}
