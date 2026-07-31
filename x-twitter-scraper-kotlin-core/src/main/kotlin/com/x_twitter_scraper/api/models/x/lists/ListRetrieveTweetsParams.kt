// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.models.x.lists

import com.x_twitter_scraper.api.core.Params
import com.x_twitter_scraper.api.core.http.Headers
import com.x_twitter_scraper.api.core.http.QueryParams
import java.util.Objects

/** List tweets from an X List */
class ListRetrieveTweetsParams
private constructor(
    private val id: String?,
    private val cursor: String?,
    private val includeReplies: Boolean?,
    private val pageSize: Long?,
    private val sinceTime: String?,
    private val untilTime: String?,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    fun id(): String? = id

    /** Pagination cursor for list tweets */
    fun cursor(): String? = cursor

    /** Include replies (default false) */
    fun includeReplies(): Boolean? = includeReplies

    /**
     * Maximum page items (1-100, default 20). Source, filters, or credits can reduce results.
     * Continue while has_next_page is true. Deprecated limit and count aliases remain accepted.
     */
    fun pageSize(): Long? = pageSize

    /** Unix timestamp - filter after */
    fun sinceTime(): String? = sinceTime

    /** Unix timestamp - filter before */
    fun untilTime(): String? = untilTime

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        fun none(): ListRetrieveTweetsParams = builder().build()

        /** Returns a mutable builder for constructing an instance of [ListRetrieveTweetsParams]. */
        fun builder() = Builder()
    }

    /** A builder for [ListRetrieveTweetsParams]. */
    class Builder internal constructor() {

        private var id: String? = null
        private var cursor: String? = null
        private var includeReplies: Boolean? = null
        private var pageSize: Long? = null
        private var sinceTime: String? = null
        private var untilTime: String? = null
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        internal fun from(listRetrieveTweetsParams: ListRetrieveTweetsParams) = apply {
            id = listRetrieveTweetsParams.id
            cursor = listRetrieveTweetsParams.cursor
            includeReplies = listRetrieveTweetsParams.includeReplies
            pageSize = listRetrieveTweetsParams.pageSize
            sinceTime = listRetrieveTweetsParams.sinceTime
            untilTime = listRetrieveTweetsParams.untilTime
            additionalHeaders = listRetrieveTweetsParams.additionalHeaders.toBuilder()
            additionalQueryParams = listRetrieveTweetsParams.additionalQueryParams.toBuilder()
        }

        fun id(id: String?) = apply { this.id = id }

        /** Pagination cursor for list tweets */
        fun cursor(cursor: String?) = apply { this.cursor = cursor }

        /** Include replies (default false) */
        fun includeReplies(includeReplies: Boolean?) = apply {
            this.includeReplies = includeReplies
        }

        /**
         * Alias for [Builder.includeReplies].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun includeReplies(includeReplies: Boolean) = includeReplies(includeReplies as Boolean?)

        /**
         * Maximum page items (1-100, default 20). Source, filters, or credits can reduce results.
         * Continue while has_next_page is true. Deprecated limit and count aliases remain accepted.
         */
        fun pageSize(pageSize: Long?) = apply { this.pageSize = pageSize }

        /**
         * Alias for [Builder.pageSize].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun pageSize(pageSize: Long) = pageSize(pageSize as Long?)

        /** Unix timestamp - filter after */
        fun sinceTime(sinceTime: String?) = apply { this.sinceTime = sinceTime }

        /** Unix timestamp - filter before */
        fun untilTime(untilTime: String?) = apply { this.untilTime = untilTime }

        fun additionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.clear()
            putAllAdditionalHeaders(additionalHeaders)
        }

        fun additionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.clear()
            putAllAdditionalHeaders(additionalHeaders)
        }

        fun putAdditionalHeader(name: String, value: String) = apply {
            additionalHeaders.put(name, value)
        }

        fun putAdditionalHeaders(name: String, values: Iterable<String>) = apply {
            additionalHeaders.put(name, values)
        }

        fun putAllAdditionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.putAll(additionalHeaders)
        }

        fun putAllAdditionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.putAll(additionalHeaders)
        }

        fun replaceAdditionalHeaders(name: String, value: String) = apply {
            additionalHeaders.replace(name, value)
        }

        fun replaceAdditionalHeaders(name: String, values: Iterable<String>) = apply {
            additionalHeaders.replace(name, values)
        }

        fun replaceAllAdditionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.replaceAll(additionalHeaders)
        }

        fun replaceAllAdditionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.replaceAll(additionalHeaders)
        }

        fun removeAdditionalHeaders(name: String) = apply { additionalHeaders.remove(name) }

        fun removeAllAdditionalHeaders(names: Set<String>) = apply {
            additionalHeaders.removeAll(names)
        }

        fun additionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.clear()
            putAllAdditionalQueryParams(additionalQueryParams)
        }

        fun additionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) = apply {
            this.additionalQueryParams.clear()
            putAllAdditionalQueryParams(additionalQueryParams)
        }

        fun putAdditionalQueryParam(key: String, value: String) = apply {
            additionalQueryParams.put(key, value)
        }

        fun putAdditionalQueryParams(key: String, values: Iterable<String>) = apply {
            additionalQueryParams.put(key, values)
        }

        fun putAllAdditionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.putAll(additionalQueryParams)
        }

        fun putAllAdditionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) =
            apply {
                this.additionalQueryParams.putAll(additionalQueryParams)
            }

        fun replaceAdditionalQueryParams(key: String, value: String) = apply {
            additionalQueryParams.replace(key, value)
        }

        fun replaceAdditionalQueryParams(key: String, values: Iterable<String>) = apply {
            additionalQueryParams.replace(key, values)
        }

        fun replaceAllAdditionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.replaceAll(additionalQueryParams)
        }

        fun replaceAllAdditionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) =
            apply {
                this.additionalQueryParams.replaceAll(additionalQueryParams)
            }

        fun removeAdditionalQueryParams(key: String) = apply { additionalQueryParams.remove(key) }

        fun removeAllAdditionalQueryParams(keys: Set<String>) = apply {
            additionalQueryParams.removeAll(keys)
        }

        /**
         * Returns an immutable instance of [ListRetrieveTweetsParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): ListRetrieveTweetsParams =
            ListRetrieveTweetsParams(
                id,
                cursor,
                includeReplies,
                pageSize,
                sinceTime,
                untilTime,
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    fun _pathParam(index: Int): String =
        when (index) {
            0 -> id ?: ""
            else -> ""
        }

    override fun _headers(): Headers = additionalHeaders

    override fun _queryParams(): QueryParams =
        QueryParams.builder()
            .apply {
                cursor?.let { put("cursor", it) }
                includeReplies?.let { put("includeReplies", it.toString()) }
                pageSize?.let { put("pageSize", it.toString()) }
                sinceTime?.let { put("sinceTime", it) }
                untilTime?.let { put("untilTime", it) }
                putAll(additionalQueryParams)
            }
            .build()

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ListRetrieveTweetsParams &&
            id == other.id &&
            cursor == other.cursor &&
            includeReplies == other.includeReplies &&
            pageSize == other.pageSize &&
            sinceTime == other.sinceTime &&
            untilTime == other.untilTime &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(
            id,
            cursor,
            includeReplies,
            pageSize,
            sinceTime,
            untilTime,
            additionalHeaders,
            additionalQueryParams,
        )

    override fun toString() =
        "ListRetrieveTweetsParams{id=$id, cursor=$cursor, includeReplies=$includeReplies, pageSize=$pageSize, sinceTime=$sinceTime, untilTime=$untilTime, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
