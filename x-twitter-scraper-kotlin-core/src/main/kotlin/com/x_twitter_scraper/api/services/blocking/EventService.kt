// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.blocking

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.events.EventListParams
import com.x_twitter_scraper.api.models.events.EventListResponse
import com.x_twitter_scraper.api.models.events.EventRetrieveParams
import com.x_twitter_scraper.api.models.events.EventRetrieveResponse

/** Activity events from monitored accounts */
interface EventService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: (ClientOptions.Builder) -> Unit): EventService

    /** Get event */
    fun retrieve(
        id: String,
        params: EventRetrieveParams = EventRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): EventRetrieveResponse = retrieve(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        params: EventRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): EventRetrieveResponse

    /** @see retrieve */
    fun retrieve(id: String, requestOptions: RequestOptions): EventRetrieveResponse =
        retrieve(id, EventRetrieveParams.none(), requestOptions)

    /** List events */
    fun list(
        params: EventListParams = EventListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): EventListResponse

    /** @see list */
    fun list(requestOptions: RequestOptions): EventListResponse =
        list(EventListParams.none(), requestOptions)

    /** A view of [EventService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: (ClientOptions.Builder) -> Unit): EventService.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /events/{id}`, but is otherwise the same as
         * [EventService.retrieve].
         */
        @MustBeClosed
        fun retrieve(
            id: String,
            params: EventRetrieveParams = EventRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<EventRetrieveResponse> =
            retrieve(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: EventRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<EventRetrieveResponse>

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<EventRetrieveResponse> =
            retrieve(id, EventRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /events`, but is otherwise the same as
         * [EventService.list].
         */
        @MustBeClosed
        fun list(
            params: EventListParams = EventListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<EventListResponse>

        /** @see list */
        @MustBeClosed
        fun list(requestOptions: RequestOptions): HttpResponseFor<EventListResponse> =
            list(EventListParams.none(), requestOptions)
    }
}
