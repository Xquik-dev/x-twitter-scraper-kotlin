// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.services.async

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.events.Event
import com.x_twitter_scraper.api.models.events.EventDetail
import com.x_twitter_scraper.api.models.events.EventListParams
import com.x_twitter_scraper.api.models.events.EventListResponse
import com.x_twitter_scraper.api.models.events.EventRetrieveParams
import com.x_twitter_scraper.api.models.events.EventStreamParams
import kotlinx.coroutines.flow.Flow

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

    /**
     * Streams the account's monitor events as server-sent events, each named by its event type and
     * numbered by its event ID. A reconnect that sends Last-Event-ID first gets every event after
     * that one from the last 30 days, then each new event. A connection lasts up to 30 minutes and
     * sends a comment every 15 seconds while idle. Keep up to 5 streams open per account. Events
     * and streams cost no credits.
     */
    fun stream(
        params: EventStreamParams = EventStreamParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): Flow<Event>

    /** @see stream */
    fun stream(requestOptions: RequestOptions): Flow<Event> =
        stream(EventStreamParams.none(), requestOptions)

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

        /**
         * Returns a raw HTTP response for `get /events/stream`, but is otherwise the same as
         * [EventServiceAsync.stream].
         */
        @MustBeClosed
        suspend fun stream(
            params: EventStreamParams = EventStreamParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<Flow<Event>>

        /** @see stream */
        @MustBeClosed
        suspend fun stream(requestOptions: RequestOptions): HttpResponseFor<Flow<Event>> =
            stream(EventStreamParams.none(), requestOptions)
    }
}
