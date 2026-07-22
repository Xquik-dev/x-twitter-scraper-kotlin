// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponse
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.draws.DrawExportParams
import com.x_twitter_scraper.api.models.draws.DrawListParams
import com.x_twitter_scraper.api.models.draws.DrawListResponse
import com.x_twitter_scraper.api.models.draws.DrawRetrieveParams
import com.x_twitter_scraper.api.models.draws.DrawRetrieveResponse
import com.x_twitter_scraper.api.models.draws.DrawRunParams
import com.x_twitter_scraper.api.models.draws.DrawRunResponse

/** Giveaway draws from tweet replies */
interface DrawServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: (ClientOptions.Builder) -> Unit): DrawServiceAsync

    /** Get draw details */
    suspend fun retrieve(
        id: String,
        params: DrawRetrieveParams = DrawRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): DrawRetrieveResponse = retrieve(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieve */
    suspend fun retrieve(
        params: DrawRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): DrawRetrieveResponse

    /** @see retrieve */
    suspend fun retrieve(id: String, requestOptions: RequestOptions): DrawRetrieveResponse =
        retrieve(id, DrawRetrieveParams.none(), requestOptions)

    /** List draws */
    suspend fun list(
        params: DrawListParams = DrawListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): DrawListResponse

    /** @see list */
    suspend fun list(requestOptions: RequestOptions): DrawListResponse =
        list(DrawListParams.none(), requestOptions)

    /** Export draw data */
    @MustBeClosed
    suspend fun export(
        id: String,
        params: DrawExportParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): HttpResponse = export(params.toBuilder().id(id).build(), requestOptions)

    /** @see export */
    @MustBeClosed
    suspend fun export(
        params: DrawExportParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): HttpResponse

    /**
     * Runs a giveaway draw from a source tweet. The draw first checks the minimum credits needed to
     * inspect the source tweet and at least one candidate. Remaining credits cap how many replies
     * and retweeters can be inspected before filters and winner selection run.
     */
    suspend fun run(
        params: DrawRunParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): DrawRunResponse

    /** A view of [DrawServiceAsync] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: (ClientOptions.Builder) -> Unit): DrawServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /draws/{id}`, but is otherwise the same as
         * [DrawServiceAsync.retrieve].
         */
        @MustBeClosed
        suspend fun retrieve(
            id: String,
            params: DrawRetrieveParams = DrawRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<DrawRetrieveResponse> =
            retrieve(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        suspend fun retrieve(
            params: DrawRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<DrawRetrieveResponse>

        /** @see retrieve */
        @MustBeClosed
        suspend fun retrieve(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<DrawRetrieveResponse> =
            retrieve(id, DrawRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /draws`, but is otherwise the same as
         * [DrawServiceAsync.list].
         */
        @MustBeClosed
        suspend fun list(
            params: DrawListParams = DrawListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<DrawListResponse>

        /** @see list */
        @MustBeClosed
        suspend fun list(requestOptions: RequestOptions): HttpResponseFor<DrawListResponse> =
            list(DrawListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /draws/{id}/export`, but is otherwise the same as
         * [DrawServiceAsync.export].
         */
        @MustBeClosed
        suspend fun export(
            id: String,
            params: DrawExportParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponse = export(params.toBuilder().id(id).build(), requestOptions)

        /** @see export */
        @MustBeClosed
        suspend fun export(
            params: DrawExportParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponse

        /**
         * Returns a raw HTTP response for `post /draws`, but is otherwise the same as
         * [DrawServiceAsync.run].
         */
        @MustBeClosed
        suspend fun run(
            params: DrawRunParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<DrawRunResponse>
    }
}
