// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.core.http

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface StreamResponse<T> : AutoCloseable {

    fun asSequence(): Sequence<T>
}

internal fun <T, R> StreamResponse<T>.map(transform: (T) -> R): StreamResponse<R> =
    object : StreamResponse<R> {
        override fun asSequence(): Sequence<R> = this@map.asSequence().map(transform)

        override fun close() = this@map.close()
    }

internal fun <T> StreamResponse<T>.asFlow(): Flow<T> {
    val response = this
    return flow { response.use { stream -> stream.asSequence().forEach { emit(it) } } }
        .flowOn(Dispatchers.IO)
}
