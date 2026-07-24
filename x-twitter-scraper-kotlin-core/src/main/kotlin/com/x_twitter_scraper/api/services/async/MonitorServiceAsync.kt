// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.monitors.Monitor
import com.x_twitter_scraper.api.models.monitors.MonitorCreateParams
import com.x_twitter_scraper.api.models.monitors.MonitorCreateResponse
import com.x_twitter_scraper.api.models.monitors.MonitorDeactivateParams
import com.x_twitter_scraper.api.models.monitors.MonitorDeactivateResponse
import com.x_twitter_scraper.api.models.monitors.MonitorListParams
import com.x_twitter_scraper.api.models.monitors.MonitorListResponse
import com.x_twitter_scraper.api.models.monitors.MonitorRetrieveParams
import com.x_twitter_scraper.api.models.monitors.MonitorUpdateParams
import com.x_twitter_scraper.api.services.async.monitors.KeywordServiceAsync

/** X account monitoring with 1-second checks */
interface MonitorServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: (ClientOptions.Builder) -> Unit): MonitorServiceAsync

    /** X account monitoring with 1-second checks */
    fun keywords(): KeywordServiceAsync

    /**
     * Creates an account monitor. Monitors are unlimited. Active monitors check every 1 second and
     * cost 21 credits per hour. Events and webhook deliveries are included. Creation requires
     * available credits for the first hourly charge and username lookup.
     */
    suspend fun create(
        params: MonitorCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): MonitorCreateResponse

    /** Get monitor */
    suspend fun retrieve(
        id: String,
        params: MonitorRetrieveParams = MonitorRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): Monitor = retrieve(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieve */
    suspend fun retrieve(
        params: MonitorRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): Monitor

    /** @see retrieve */
    suspend fun retrieve(id: String, requestOptions: RequestOptions): Monitor =
        retrieve(id, MonitorRetrieveParams.none(), requestOptions)

    /** Update monitor */
    suspend fun update(
        id: String,
        params: MonitorUpdateParams = MonitorUpdateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): Monitor = update(params.toBuilder().id(id).build(), requestOptions)

    /** @see update */
    suspend fun update(
        params: MonitorUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): Monitor

    /** @see update */
    suspend fun update(id: String, requestOptions: RequestOptions): Monitor =
        update(id, MonitorUpdateParams.none(), requestOptions)

    /** List monitors */
    suspend fun list(
        params: MonitorListParams = MonitorListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): MonitorListResponse

    /** @see list */
    suspend fun list(requestOptions: RequestOptions): MonitorListResponse =
        list(MonitorListParams.none(), requestOptions)

    /** Delete monitor */
    suspend fun deactivate(
        id: String,
        params: MonitorDeactivateParams = MonitorDeactivateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): MonitorDeactivateResponse = deactivate(params.toBuilder().id(id).build(), requestOptions)

    /** @see deactivate */
    suspend fun deactivate(
        params: MonitorDeactivateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): MonitorDeactivateResponse

    /** @see deactivate */
    suspend fun deactivate(id: String, requestOptions: RequestOptions): MonitorDeactivateResponse =
        deactivate(id, MonitorDeactivateParams.none(), requestOptions)

    /**
     * A view of [MonitorServiceAsync] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: (ClientOptions.Builder) -> Unit
        ): MonitorServiceAsync.WithRawResponse

        /** X account monitoring with 1-second checks */
        fun keywords(): KeywordServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /monitors`, but is otherwise the same as
         * [MonitorServiceAsync.create].
         */
        @MustBeClosed
        suspend fun create(
            params: MonitorCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<MonitorCreateResponse>

        /**
         * Returns a raw HTTP response for `get /monitors/{id}`, but is otherwise the same as
         * [MonitorServiceAsync.retrieve].
         */
        @MustBeClosed
        suspend fun retrieve(
            id: String,
            params: MonitorRetrieveParams = MonitorRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<Monitor> = retrieve(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        suspend fun retrieve(
            params: MonitorRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<Monitor>

        /** @see retrieve */
        @MustBeClosed
        suspend fun retrieve(id: String, requestOptions: RequestOptions): HttpResponseFor<Monitor> =
            retrieve(id, MonitorRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `patch /monitors/{id}`, but is otherwise the same as
         * [MonitorServiceAsync.update].
         */
        @MustBeClosed
        suspend fun update(
            id: String,
            params: MonitorUpdateParams = MonitorUpdateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<Monitor> = update(params.toBuilder().id(id).build(), requestOptions)

        /** @see update */
        @MustBeClosed
        suspend fun update(
            params: MonitorUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<Monitor>

        /** @see update */
        @MustBeClosed
        suspend fun update(id: String, requestOptions: RequestOptions): HttpResponseFor<Monitor> =
            update(id, MonitorUpdateParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /monitors`, but is otherwise the same as
         * [MonitorServiceAsync.list].
         */
        @MustBeClosed
        suspend fun list(
            params: MonitorListParams = MonitorListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<MonitorListResponse>

        /** @see list */
        @MustBeClosed
        suspend fun list(requestOptions: RequestOptions): HttpResponseFor<MonitorListResponse> =
            list(MonitorListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete /monitors/{id}`, but is otherwise the same as
         * [MonitorServiceAsync.deactivate].
         */
        @MustBeClosed
        suspend fun deactivate(
            id: String,
            params: MonitorDeactivateParams = MonitorDeactivateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<MonitorDeactivateResponse> =
            deactivate(params.toBuilder().id(id).build(), requestOptions)

        /** @see deactivate */
        @MustBeClosed
        suspend fun deactivate(
            params: MonitorDeactivateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<MonitorDeactivateResponse>

        /** @see deactivate */
        @MustBeClosed
        suspend fun deactivate(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<MonitorDeactivateResponse> =
            deactivate(id, MonitorDeactivateParams.none(), requestOptions)
    }
}
