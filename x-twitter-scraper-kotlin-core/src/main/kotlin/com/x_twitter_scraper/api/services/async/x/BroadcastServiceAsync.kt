// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.services.async.x

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.x.broadcasts.BroadcastRetrieveParams
import com.x_twitter_scraper.api.models.x.broadcasts.BroadcastRetrieveResponse

/** X live video details, watch counts, and broadcaster */
interface BroadcastServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: (ClientOptions.Builder) -> Unit): BroadcastServiceAsync

    /**
     * Returns 1 X broadcast, the live video X hosts at x.com/i/broadcasts, live or ended. Accepts
     * the broadcast ID or the URL-encoded broadcast URL. Reading a broadcast adds no view to it.
     */
    suspend fun retrieve(
        id: String,
        params: BroadcastRetrieveParams = BroadcastRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BroadcastRetrieveResponse = retrieve(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieve */
    suspend fun retrieve(
        params: BroadcastRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BroadcastRetrieveResponse

    /** @see retrieve */
    suspend fun retrieve(id: String, requestOptions: RequestOptions): BroadcastRetrieveResponse =
        retrieve(id, BroadcastRetrieveParams.none(), requestOptions)

    /**
     * A view of [BroadcastServiceAsync] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: (ClientOptions.Builder) -> Unit
        ): BroadcastServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /x/broadcasts/{id}`, but is otherwise the same as
         * [BroadcastServiceAsync.retrieve].
         */
        @MustBeClosed
        suspend fun retrieve(
            id: String,
            params: BroadcastRetrieveParams = BroadcastRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BroadcastRetrieveResponse> =
            retrieve(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        suspend fun retrieve(
            params: BroadcastRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BroadcastRetrieveResponse>

        /** @see retrieve */
        @MustBeClosed
        suspend fun retrieve(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BroadcastRetrieveResponse> =
            retrieve(id, BroadcastRetrieveParams.none(), requestOptions)
    }
}
