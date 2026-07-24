// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.blocking

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
interface ExtractionService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: (ClientOptions.Builder) -> Unit): ExtractionService

    /** Get extraction results */
    fun retrieve(
        id: String,
        params: ExtractionRetrieveParams = ExtractionRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): ExtractionRetrieveResponse = retrieve(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        params: ExtractionRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): ExtractionRetrieveResponse

    /** @see retrieve */
    fun retrieve(id: String, requestOptions: RequestOptions): ExtractionRetrieveResponse =
        retrieve(id, ExtractionRetrieveParams.none(), requestOptions)

    /** List extraction jobs */
    fun list(
        params: ExtractionListParams = ExtractionListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): ExtractionListResponse

    /** @see list */
    fun list(requestOptions: RequestOptions): ExtractionListResponse =
        list(ExtractionListParams.none(), requestOptions)

    /** Estimate extraction cost */
    fun estimateCost(
        params: ExtractionEstimateCostParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): ExtractionEstimateCostResponse

    /** Export extraction results */
    @MustBeClosed
    fun exportResults(
        id: String,
        params: ExtractionExportResultsParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): HttpResponse = exportResults(params.toBuilder().id(id).build(), requestOptions)

    /** @see exportResults */
    @MustBeClosed
    fun exportResults(
        params: ExtractionExportResultsParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): HttpResponse

    /** Run extraction */
    fun run(
        params: ExtractionRunParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): ExtractionRunResponse

    /** A view of [ExtractionService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: (ClientOptions.Builder) -> Unit
        ): ExtractionService.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /extractions/{id}`, but is otherwise the same as
         * [ExtractionService.retrieve].
         */
        @MustBeClosed
        fun retrieve(
            id: String,
            params: ExtractionRetrieveParams = ExtractionRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<ExtractionRetrieveResponse> =
            retrieve(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: ExtractionRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<ExtractionRetrieveResponse>

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ExtractionRetrieveResponse> =
            retrieve(id, ExtractionRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /extractions`, but is otherwise the same as
         * [ExtractionService.list].
         */
        @MustBeClosed
        fun list(
            params: ExtractionListParams = ExtractionListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<ExtractionListResponse>

        /** @see list */
        @MustBeClosed
        fun list(requestOptions: RequestOptions): HttpResponseFor<ExtractionListResponse> =
            list(ExtractionListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /extractions/estimate`, but is otherwise the same
         * as [ExtractionService.estimateCost].
         */
        @MustBeClosed
        fun estimateCost(
            params: ExtractionEstimateCostParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<ExtractionEstimateCostResponse>

        /**
         * Returns a raw HTTP response for `get /extractions/{id}/export`, but is otherwise the same
         * as [ExtractionService.exportResults].
         */
        @MustBeClosed
        fun exportResults(
            id: String,
            params: ExtractionExportResultsParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponse = exportResults(params.toBuilder().id(id).build(), requestOptions)

        /** @see exportResults */
        @MustBeClosed
        fun exportResults(
            params: ExtractionExportResultsParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponse

        /**
         * Returns a raw HTTP response for `post /extractions`, but is otherwise the same as
         * [ExtractionService.run].
         */
        @MustBeClosed
        fun run(
            params: ExtractionRunParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<ExtractionRunResponse>
    }
}
