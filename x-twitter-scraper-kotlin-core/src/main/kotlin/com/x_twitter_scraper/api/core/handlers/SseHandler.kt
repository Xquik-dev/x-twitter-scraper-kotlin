// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

@file:JvmName("SseHandler")

package com.x_twitter_scraper.api.core.handlers

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.json.JsonMapper
import com.x_twitter_scraper.api.core.http.HttpResponse
import com.x_twitter_scraper.api.core.http.HttpResponse.Handler
import com.x_twitter_scraper.api.core.http.SseMessage
import com.x_twitter_scraper.api.core.http.StreamResponse
import com.x_twitter_scraper.api.core.http.map
import com.x_twitter_scraper.api.errors.XTwitterScraperInvalidDataException

internal fun sseHandler(): Handler<StreamResponse<SseMessage>> = SseHandlerInternal

private object SseHandlerInternal : Handler<StreamResponse<SseMessage>> {

    override fun handle(response: HttpResponse): StreamResponse<SseMessage> {
        val reader = response.body().bufferedReader(Charsets.UTF_8)
        val messages = sequence {
            var event: String? = null
            var id: String? = null
            val data = StringBuilder()
            var hasData = false
            for (line in reader.lineSequence()) {
                if (line.isEmpty()) {
                    if (hasData) {
                        yield(SseMessage(event, data.toString(), id))
                    }
                    event = null
                    data.setLength(0)
                    hasData = false
                    continue
                }
                if (line.startsWith(":")) {
                    continue
                }
                val colon = line.indexOf(':')
                val field = if (colon == -1) line else line.substring(0, colon)
                val value = if (colon == -1) "" else line.substring(colon + 1).removePrefix(" ")
                when (field) {
                    "event" -> event = value
                    "data" -> {
                        if (hasData) {
                            data.append('\n')
                        }
                        data.append(value)
                        hasData = true
                    }
                    "id" -> if ('\u0000' !in value) id = value
                }
            }
        }
        return object : StreamResponse<SseMessage> {
            override fun asSequence(): Sequence<SseMessage> = messages

            override fun close() = response.close()
        }
    }
}

internal inline fun <reified T> Handler<StreamResponse<SseMessage>>.mapJson(
    jsonMapper: JsonMapper
): Handler<StreamResponse<T>> {
    val messages = this
    return object : Handler<StreamResponse<T>> {
        override fun handle(response: HttpResponse): StreamResponse<T> =
            messages.handle(response).map { message ->
                try {
                    jsonMapper.readValue(message.data, object : TypeReference<T>() {})
                } catch (e: Exception) {
                    throw XTwitterScraperInvalidDataException("Error reading server-sent event", e)
                }
            }
    }
}
