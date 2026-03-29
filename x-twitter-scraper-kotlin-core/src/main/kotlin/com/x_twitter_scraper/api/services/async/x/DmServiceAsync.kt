// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async.x

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.x.dm.DmRetrieveHistoryParams
import com.x_twitter_scraper.api.models.x.dm.DmRetrieveHistoryResponse
import com.x_twitter_scraper.api.models.x.dm.DmUpdateParams
import com.x_twitter_scraper.api.models.x.dm.DmUpdateResponse

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

    /** Send direct message */
    suspend fun update(
        userId: String,
        params: DmUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): DmUpdateResponse = update(params.toBuilder().userId(userId).build(), requestOptions)

    /** @see update */
    suspend fun update(
        params: DmUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): DmUpdateResponse

    /** Get DM conversation history */
    suspend fun retrieveHistory(
        userId: String,
        params: DmRetrieveHistoryParams = DmRetrieveHistoryParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): DmRetrieveHistoryResponse =
        retrieveHistory(params.toBuilder().userId(userId).build(), requestOptions)

    /** @see retrieveHistory */
    suspend fun retrieveHistory(
        params: DmRetrieveHistoryParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): DmRetrieveHistoryResponse

    /** @see retrieveHistory */
    suspend fun retrieveHistory(
        userId: String,
        requestOptions: RequestOptions,
    ): DmRetrieveHistoryResponse =
        retrieveHistory(userId, DmRetrieveHistoryParams.none(), requestOptions)

    /** A view of [DmServiceAsync] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: (ClientOptions.Builder) -> Unit): DmServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /x/dm/{userId}`, but is otherwise the same as
         * [DmServiceAsync.update].
         */
        @MustBeClosed
        suspend fun update(
            userId: String,
            params: DmUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<DmUpdateResponse> =
            update(params.toBuilder().userId(userId).build(), requestOptions)

        /** @see update */
        @MustBeClosed
        suspend fun update(
            params: DmUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<DmUpdateResponse>

        /**
         * Returns a raw HTTP response for `get /x/dm/{userId}/history`, but is otherwise the same
         * as [DmServiceAsync.retrieveHistory].
         */
        @MustBeClosed
        suspend fun retrieveHistory(
            userId: String,
            params: DmRetrieveHistoryParams = DmRetrieveHistoryParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<DmRetrieveHistoryResponse> =
            retrieveHistory(params.toBuilder().userId(userId).build(), requestOptions)

        /** @see retrieveHistory */
        @MustBeClosed
        suspend fun retrieveHistory(
            params: DmRetrieveHistoryParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<DmRetrieveHistoryResponse>

        /** @see retrieveHistory */
        @MustBeClosed
        suspend fun retrieveHistory(
            userId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<DmRetrieveHistoryResponse> =
            retrieveHistory(userId, DmRetrieveHistoryParams.none(), requestOptions)
    }
}
