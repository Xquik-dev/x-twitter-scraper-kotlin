// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async.x

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.x.dm.DmRetrieveHistoryParams
import com.x_twitter_scraper.api.models.x.dm.DmRetrieveHistoryResponse
import com.x_twitter_scraper.api.models.x.dm.DmSendParams
import com.x_twitter_scraper.api.models.x.dm.DmSendResponse

interface DmServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: (ClientOptions.Builder) -> Unit): DmServiceAsync

    /** Get DM conversation history */
    suspend fun retrieveHistory(
        userId: String,
        params: DmRetrieveHistoryParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): DmRetrieveHistoryResponse =
        retrieveHistory(params.toBuilder().userId(userId).build(), requestOptions)

    /** @see retrieveHistory */
    suspend fun retrieveHistory(
        params: DmRetrieveHistoryParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): DmRetrieveHistoryResponse

    /** Send direct message */
    suspend fun send(
        userId: String,
        params: DmSendParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): DmSendResponse = send(params.toBuilder().userId(userId).build(), requestOptions)

    /** @see send */
    suspend fun send(
        params: DmSendParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): DmSendResponse

    /** A view of [DmServiceAsync] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: (ClientOptions.Builder) -> Unit): DmServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /x/dm/{userId}/history`, but is otherwise the same
         * as [DmServiceAsync.retrieveHistory].
         */
        @MustBeClosed
        suspend fun retrieveHistory(
            userId: String,
            params: DmRetrieveHistoryParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<DmRetrieveHistoryResponse> =
            retrieveHistory(params.toBuilder().userId(userId).build(), requestOptions)

        /** @see retrieveHistory */
        @MustBeClosed
        suspend fun retrieveHistory(
            params: DmRetrieveHistoryParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<DmRetrieveHistoryResponse>

        /**
         * Returns a raw HTTP response for `post /x/dm/{userId}`, but is otherwise the same as
         * [DmServiceAsync.send].
         */
        @MustBeClosed
        suspend fun send(
            userId: String,
            params: DmSendParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<DmSendResponse> =
            send(params.toBuilder().userId(userId).build(), requestOptions)

        /** @see send */
        @MustBeClosed
        suspend fun send(
            params: DmSendParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<DmSendResponse>
    }
}
