// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponse
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.extractions.ExtractionEstimateCostParams
import com.x_twitter_scraper.api.models.extractions.ExtractionEstimateCostResponse
import com.x_twitter_scraper.api.models.extractions.ExtractionExportResultsParams
import com.x_twitter_scraper.api.models.extractions.ExtractionListParams
import com.x_twitter_scraper.api.models.extractions.ExtractionListResponse
import com.x_twitter_scraper.api.models.extractions.ExtractionRetrieveParams
import com.x_twitter_scraper.api.models.extractions.ExtractionRetrieveResponse
import com.x_twitter_scraper.api.models.extractions.ExtractionRunParams
import com.x_twitter_scraper.api.models.extractions.ExtractionRunResponse

/** Bulk data extraction (23 tool types) */
interface ExtractionServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: (ClientOptions.Builder) -> Unit): ExtractionServiceAsync

    /** Get extraction results */
    suspend fun retrieve(
        id: String,
        params: ExtractionRetrieveParams = ExtractionRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): ExtractionRetrieveResponse = retrieve(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieve */
    suspend fun retrieve(
        params: ExtractionRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): ExtractionRetrieveResponse

    /** @see retrieve */
    suspend fun retrieve(id: String, requestOptions: RequestOptions): ExtractionRetrieveResponse =
        retrieve(id, ExtractionRetrieveParams.none(), requestOptions)

    /** List extraction jobs */
    suspend fun list(
        params: ExtractionListParams = ExtractionListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): ExtractionListResponse

    /** @see list */
    suspend fun list(requestOptions: RequestOptions): ExtractionListResponse =
        list(ExtractionListParams.none(), requestOptions)

    /** Estimate extraction cost */
    suspend fun estimateCost(
        params: ExtractionEstimateCostParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): ExtractionEstimateCostResponse

    /** Export extraction results */
    @MustBeClosed
    suspend fun exportResults(
        id: String,
        params: ExtractionExportResultsParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): HttpResponse = exportResults(params.toBuilder().id(id).build(), requestOptions)

    /** @see exportResults */
    @MustBeClosed
    suspend fun exportResults(
        params: ExtractionExportResultsParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): HttpResponse

    /** Run extraction */
    suspend fun run(
        params: ExtractionRunParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): ExtractionRunResponse

    /**
     * A view of [ExtractionServiceAsync] that provides access to raw HTTP responses for each
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
        ): ExtractionServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /extractions/{id}`, but is otherwise the same as
         * [ExtractionServiceAsync.retrieve].
         */
        @MustBeClosed
        suspend fun retrieve(
            id: String,
            params: ExtractionRetrieveParams = ExtractionRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<ExtractionRetrieveResponse> =
            retrieve(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        suspend fun retrieve(
            params: ExtractionRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<ExtractionRetrieveResponse>

        /** @see retrieve */
        @MustBeClosed
        suspend fun retrieve(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ExtractionRetrieveResponse> =
            retrieve(id, ExtractionRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /extractions`, but is otherwise the same as
         * [ExtractionServiceAsync.list].
         */
        @MustBeClosed
        suspend fun list(
            params: ExtractionListParams = ExtractionListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<ExtractionListResponse>

        /** @see list */
        @MustBeClosed
        suspend fun list(requestOptions: RequestOptions): HttpResponseFor<ExtractionListResponse> =
            list(ExtractionListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /extractions/estimate`, but is otherwise the same
         * as [ExtractionServiceAsync.estimateCost].
         */
        @MustBeClosed
        suspend fun estimateCost(
            params: ExtractionEstimateCostParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<ExtractionEstimateCostResponse>

        /**
         * Returns a raw HTTP response for `get /extractions/{id}/export`, but is otherwise the same
         * as [ExtractionServiceAsync.exportResults].
         */
        @MustBeClosed
        suspend fun exportResults(
            id: String,
            params: ExtractionExportResultsParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponse = exportResults(params.toBuilder().id(id).build(), requestOptions)

        /** @see exportResults */
        @MustBeClosed
        suspend fun exportResults(
            params: ExtractionExportResultsParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponse

        /**
         * Returns a raw HTTP response for `post /extractions`, but is otherwise the same as
         * [ExtractionServiceAsync.run].
         */
        @MustBeClosed
        suspend fun run(
            params: ExtractionRunParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<ExtractionRunResponse>
    }
}
