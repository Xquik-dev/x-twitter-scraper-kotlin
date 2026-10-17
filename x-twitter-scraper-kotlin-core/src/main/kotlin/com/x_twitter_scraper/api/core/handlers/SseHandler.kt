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

internal fun sseHandler(): Handler<StreamResponse<SseMessage>> = streamHandler { _, lines ->
    val state = SseState()
    for ((index, line) in lines.withIndex()) {
        state.decode(if (index == 0) line.removePrefix("\uFEFF") else line)?.let { yield(it) }
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

private class SseState {

    private var event: String? = null
    private val data = mutableListOf<String>()
    private var lastId: String? = null

    fun decode(line: String): SseMessage? {
        if (line.isEmpty()) {
            return dispatch()
        }
        if (line.startsWith(':')) {
            return null
        }
        val colon = line.indexOf(':')
        val field = if (colon == -1) line else line.substring(0, colon)
        val value = if (colon == -1) "" else line.substring(colon + 1).removePrefix(" ")
        when (field) {
            "event" -> event = value
            "data" -> data.add(value)
            "id" -> if (!value.contains('\u0000')) lastId = value
        }
        return null
    }

    private fun dispatch(): SseMessage? {
        val message =
            if (data.isEmpty()) null else SseMessage(event, data.joinToString("\n"), lastId)
        event = null
        data.clear()
        return message
    }
}
