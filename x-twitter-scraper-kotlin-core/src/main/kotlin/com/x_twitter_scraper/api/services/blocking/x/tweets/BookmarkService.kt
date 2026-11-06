// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.services.blocking.x.tweets

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.x.tweets.bookmark.BookmarkCreateParams
import com.x_twitter_scraper.api.models.x.tweets.bookmark.BookmarkCreateResponse
import com.x_twitter_scraper.api.models.x.tweets.bookmark.BookmarkDeleteParams
import com.x_twitter_scraper.api.models.x.tweets.bookmark.BookmarkDeleteResponse

/** X write actions (tweets, likes, follows, DMs) */
interface BookmarkService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: (ClientOptions.Builder) -> Unit): BookmarkService

    /** Bookmark tweet */
    fun create(
        id: String,
        params: BookmarkCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BookmarkCreateResponse = create(params.toBuilder().id(id).build(), requestOptions)

    /** @see create */
    fun create(
        params: BookmarkCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BookmarkCreateResponse

    /** Unbookmark tweet */
    fun delete(
        id: String,
        params: BookmarkDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BookmarkDeleteResponse = delete(params.toBuilder().id(id).build(), requestOptions)

    /** @see delete */
    fun delete(
        params: BookmarkDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BookmarkDeleteResponse

    /** A view of [BookmarkService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: (ClientOptions.Builder) -> Unit): BookmarkService.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /x/tweets/{id}/bookmark`, but is otherwise the same
         * as [BookmarkService.create].
         */
        @MustBeClosed
        fun create(
            id: String,
            params: BookmarkCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BookmarkCreateResponse> =
            create(params.toBuilder().id(id).build(), requestOptions)

        /** @see create */
        @MustBeClosed
        fun create(
            params: BookmarkCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BookmarkCreateResponse>

        /**
         * Returns a raw HTTP response for `delete /x/tweets/{id}/bookmark`, but is otherwise the
         * same as [BookmarkService.delete].
         */
        @MustBeClosed
        fun delete(
            id: String,
            params: BookmarkDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BookmarkDeleteResponse> =
            delete(params.toBuilder().id(id).build(), requestOptions)

        /** @see delete */
        @MustBeClosed
        fun delete(
            params: BookmarkDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BookmarkDeleteResponse>
    }
}
