// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.services.async.x.users

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.x.users.block.BlockCreateParams
import com.x_twitter_scraper.api.models.x.users.block.BlockCreateResponse
import com.x_twitter_scraper.api.models.x.users.block.BlockDeleteParams
import com.x_twitter_scraper.api.models.x.users.block.BlockDeleteResponse

/** X write actions (tweets, likes, follows, DMs) */
interface BlockServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: (ClientOptions.Builder) -> Unit): BlockServiceAsync

    /** Block user */
    suspend fun create(
        id: String,
        params: BlockCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BlockCreateResponse = create(params.toBuilder().id(id).build(), requestOptions)

    /** @see create */
    suspend fun create(
        params: BlockCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BlockCreateResponse

    /** Unblock user */
    suspend fun delete(
        id: String,
        params: BlockDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BlockDeleteResponse = delete(params.toBuilder().id(id).build(), requestOptions)

    /** @see delete */
    suspend fun delete(
        params: BlockDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BlockDeleteResponse

    /** A view of [BlockServiceAsync] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: (ClientOptions.Builder) -> Unit
        ): BlockServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /x/users/{id}/block`, but is otherwise the same as
         * [BlockServiceAsync.create].
         */
        @MustBeClosed
        suspend fun create(
            id: String,
            params: BlockCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BlockCreateResponse> =
            create(params.toBuilder().id(id).build(), requestOptions)

        /** @see create */
        @MustBeClosed
        suspend fun create(
            params: BlockCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BlockCreateResponse>

        /**
         * Returns a raw HTTP response for `delete /x/users/{id}/block`, but is otherwise the same
         * as [BlockServiceAsync.delete].
         */
        @MustBeClosed
        suspend fun delete(
            id: String,
            params: BlockDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BlockDeleteResponse> =
            delete(params.toBuilder().id(id).build(), requestOptions)

        /** @see delete */
        @MustBeClosed
        suspend fun delete(
            params: BlockDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BlockDeleteResponse>
    }
}
