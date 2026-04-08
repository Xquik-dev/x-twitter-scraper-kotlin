// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.events.EventDetail
import com.x_twitter_scraper.api.models.events.EventListParams
import com.x_twitter_scraper.api.models.events.EventListResponse
import com.x_twitter_scraper.api.models.events.EventRetrieveParams

/** Activity events from monitored accounts */
interface EventServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: (ClientOptions.Builder) -> Unit): EventServiceAsync

    /** Get event */
    suspend fun retrieve(
        id: String,
        params: EventRetrieveParams = EventRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): EventDetail = retrieve(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieve */
    suspend fun retrieve(
        params: EventRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): EventDetail

    /** @see retrieve */
    suspend fun retrieve(id: String, requestOptions: RequestOptions): EventDetail =
        retrieve(id, EventRetrieveParams.none(), requestOptions)

    /** List events */
    suspend fun list(
        params: EventListParams = EventListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): EventListResponse

    /** @see list */
    suspend fun list(requestOptions: RequestOptions): EventListResponse =
        list(EventListParams.none(), requestOptions)

    /** A view of [EventServiceAsync] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: (ClientOptions.Builder) -> Unit
        ): EventServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /events/{id}`, but is otherwise the same as
         * [EventServiceAsync.retrieve].
         */
        @MustBeClosed
        suspend fun retrieve(
            id: String,
            params: EventRetrieveParams = EventRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<EventDetail> =
            retrieve(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        suspend fun retrieve(
            params: EventRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<EventDetail>

        /** @see retrieve */
        @MustBeClosed
        suspend fun retrieve(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<EventDetail> = retrieve(id, EventRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /events`, but is otherwise the same as
         * [EventServiceAsync.list].
         */
        @MustBeClosed
        suspend fun list(
            params: EventListParams = EventListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<EventListResponse>

        /** @see list */
        @MustBeClosed
        suspend fun list(requestOptions: RequestOptions): HttpResponseFor<EventListResponse> =
            list(EventListParams.none(), requestOptions)
    }
}
