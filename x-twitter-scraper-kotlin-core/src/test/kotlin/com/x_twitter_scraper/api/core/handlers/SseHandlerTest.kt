// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.core.handlers

import com.x_twitter_scraper.api.core.http.Headers
import com.x_twitter_scraper.api.core.http.HttpResponse
import com.x_twitter_scraper.api.core.http.SseMessage
import com.x_twitter_scraper.api.core.jsonMapper
import com.x_twitter_scraper.api.errors.XTwitterScraperInvalidDataException
import com.x_twitter_scraper.api.models.EventType
import com.x_twitter_scraper.api.models.events.Event
import java.io.InputStream
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

internal class SseHandlerTest {

    private class StubResponse(body: String) : HttpResponse {

        private val body = body.byteInputStream()

        var closed = false

        override fun statusCode(): Int = 200

        override fun headers(): Headers = Headers.builder().build()

        override fun body(): InputStream = body

        override fun close() {
            closed = true
        }
    }

    @Test
    fun readsEachEventAndSkipsCommentsAndEventsWithoutData() {
        val response =
            StubResponse(
                ": connected\n\nid: 41\nevent: tweet.new\ndata: {\"id\":\"41\"}\n\n: keep-alive\n\nevent: tweet.reply\ndata: line 1\ndata:line 2\n\n"
            )

        val messages = sseHandler().handle(response).use { it.asSequence().toList() }

        assertThat(messages)
            .containsExactly(
                SseMessage("tweet.new", "{\"id\":\"41\"}", "41"),
                SseMessage("tweet.reply", "line 1\nline 2", "41"),
            )
        assertThat(response.closed).isTrue()
    }

    @Test
    fun readsCarriageReturnLineEndings() {
        val response = StubResponse("id: 7\r\ndata: first\r\n\r\ndata: second\r\n\r\n")

        val messages = sseHandler().handle(response).use { it.asSequence().toList() }

        assertThat(messages)
            .containsExactly(SseMessage(null, "first", "7"), SseMessage(null, "second", "7"))
    }

    @Test
    fun dropsAnEventThatEndsBeforeItsBlankLine() {
        val response = StubResponse("data: complete\n\ndata: cut off")

        val messages = sseHandler().handle(response).use { it.asSequence().toList() }

        assertThat(messages).containsExactly(SseMessage(null, "complete", null))
    }

    @Test
    fun mapsEachEventToItsJsonModel() {
        val response =
            StubResponse(
                "id: 42\nevent: tweet.new\ndata: {\"id\":\"42\",\"type\":\"tweet.new\",\"monitorId\":\"7\",\"monitorType\":\"account\",\"occurredAt\":\"2025-01-15T12:00:00.000Z\",\"data\":{}}\n\n"
            )

        val events =
            sseHandler().mapJson<Event>(jsonMapper()).handle(response).use {
                it.asSequence().toList()
            }

        assertThat(events).hasSize(1)
        assertThat(events[0].id()).isEqualTo("42")
        assertThat(events[0].type()).isEqualTo(EventType.TWEET_NEW)
        assertThat(events[0].monitorId()).isEqualTo("7")
        assertThat(events[0].monitorType()).isEqualTo(Event.MonitorType.ACCOUNT)
        assertThat(events[0].occurredAt())
            .isEqualTo(OffsetDateTime.parse("2025-01-15T12:00:00.000Z"))
    }

    @Test
    fun failsOnEventDataThatIsNotJson() {
        val response = StubResponse("data: not json\n\n")

        assertThatThrownBy {
                sseHandler().mapJson<Event>(jsonMapper()).handle(response).use {
                    it.asSequence().toList()
                }
            }
            .isInstanceOf(XTwitterScraperInvalidDataException::class.java)
            .hasMessage("Error reading server-sent event")
    }
}
