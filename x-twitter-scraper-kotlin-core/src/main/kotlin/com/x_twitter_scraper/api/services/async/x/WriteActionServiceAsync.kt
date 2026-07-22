// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async.x

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.x.writeactions.WriteActionRetrieveParams
import com.x_twitter_scraper.api.models.x.writeactions.WriteActionRetrieveResponse

/** X write actions (tweets, likes, follows, DMs) */
interface WriteActionServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: (ClientOptions.Builder) -> Unit): WriteActionServiceAsync

    /** Get write action status */
    suspend fun retrieve(
        id: String,
        params: WriteActionRetrieveParams = WriteActionRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): WriteActionRetrieveResponse = retrieve(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieve */
    suspend fun retrieve(
        params: WriteActionRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): WriteActionRetrieveResponse

    /** @see retrieve */
    suspend fun retrieve(id: String, requestOptions: RequestOptions): WriteActionRetrieveResponse =
        retrieve(id, WriteActionRetrieveParams.none(), requestOptions)

    /**
     * A view of [WriteActionServiceAsync] that provides access to raw HTTP responses for each
     * method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: (ClientOptions.Builder) -> Unit
        ): WriteActionServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /x/write-actions/{id}`, but is otherwise the same as
         * [WriteActionServiceAsync.retrieve].
         */
        @MustBeClosed
        suspend fun retrieve(
            id: String,
            params: WriteActionRetrieveParams = WriteActionRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<WriteActionRetrieveResponse> =
            retrieve(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        suspend fun retrieve(
            params: WriteActionRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<WriteActionRetrieveResponse>

        /** @see retrieve */
        @MustBeClosed
        suspend fun retrieve(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<WriteActionRetrieveResponse> =
            retrieve(id, WriteActionRetrieveParams.none(), requestOptions)
    }
}
