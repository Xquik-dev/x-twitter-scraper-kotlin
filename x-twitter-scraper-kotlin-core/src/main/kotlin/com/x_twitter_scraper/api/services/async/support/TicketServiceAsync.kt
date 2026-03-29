// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async.support

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.support.tickets.TicketCreateParams
import com.x_twitter_scraper.api.models.support.tickets.TicketCreateResponse
import com.x_twitter_scraper.api.models.support.tickets.TicketListParams
import com.x_twitter_scraper.api.models.support.tickets.TicketListResponse
import com.x_twitter_scraper.api.models.support.tickets.TicketReplyParams
import com.x_twitter_scraper.api.models.support.tickets.TicketReplyResponse
import com.x_twitter_scraper.api.models.support.tickets.TicketRetrieveParams
import com.x_twitter_scraper.api.models.support.tickets.TicketRetrieveResponse
import com.x_twitter_scraper.api.models.support.tickets.TicketUpdateParams
import com.x_twitter_scraper.api.models.support.tickets.TicketUpdateResponse

/** Support ticket management */
interface TicketServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: (ClientOptions.Builder) -> Unit): TicketServiceAsync

    /** Create a support ticket */
    suspend fun create(
        params: TicketCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): TicketCreateResponse

    /** Get ticket with all messages */
    suspend fun retrieve(
        id: String,
        params: TicketRetrieveParams = TicketRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): TicketRetrieveResponse = retrieve(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieve */
    suspend fun retrieve(
        params: TicketRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): TicketRetrieveResponse

    /** @see retrieve */
    suspend fun retrieve(id: String, requestOptions: RequestOptions): TicketRetrieveResponse =
        retrieve(id, TicketRetrieveParams.none(), requestOptions)

    /** Update ticket status */
    suspend fun update(
        id: String,
        params: TicketUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): TicketUpdateResponse = update(params.toBuilder().id(id).build(), requestOptions)

    /** @see update */
    suspend fun update(
        params: TicketUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): TicketUpdateResponse

    /** List user's support tickets */
    suspend fun list(
        params: TicketListParams = TicketListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): TicketListResponse

    /** @see list */
    suspend fun list(requestOptions: RequestOptions): TicketListResponse =
        list(TicketListParams.none(), requestOptions)

    /** Reply to a support ticket */
    suspend fun reply(
        id: String,
        params: TicketReplyParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): TicketReplyResponse = reply(params.toBuilder().id(id).build(), requestOptions)

    /** @see reply */
    suspend fun reply(
        params: TicketReplyParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): TicketReplyResponse

    /**
     * A view of [TicketServiceAsync] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: (ClientOptions.Builder) -> Unit
        ): TicketServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /support/tickets`, but is otherwise the same as
         * [TicketServiceAsync.create].
         */
        @MustBeClosed
        suspend fun create(
            params: TicketCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<TicketCreateResponse>

        /**
         * Returns a raw HTTP response for `get /support/tickets/{id}`, but is otherwise the same as
         * [TicketServiceAsync.retrieve].
         */
        @MustBeClosed
        suspend fun retrieve(
            id: String,
            params: TicketRetrieveParams = TicketRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<TicketRetrieveResponse> =
            retrieve(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        suspend fun retrieve(
            params: TicketRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<TicketRetrieveResponse>

        /** @see retrieve */
        @MustBeClosed
        suspend fun retrieve(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<TicketRetrieveResponse> =
            retrieve(id, TicketRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `patch /support/tickets/{id}`, but is otherwise the same
         * as [TicketServiceAsync.update].
         */
        @MustBeClosed
        suspend fun update(
            id: String,
            params: TicketUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<TicketUpdateResponse> =
            update(params.toBuilder().id(id).build(), requestOptions)

        /** @see update */
        @MustBeClosed
        suspend fun update(
            params: TicketUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<TicketUpdateResponse>

        /**
         * Returns a raw HTTP response for `get /support/tickets`, but is otherwise the same as
         * [TicketServiceAsync.list].
         */
        @MustBeClosed
        suspend fun list(
            params: TicketListParams = TicketListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<TicketListResponse>

        /** @see list */
        @MustBeClosed
        suspend fun list(requestOptions: RequestOptions): HttpResponseFor<TicketListResponse> =
            list(TicketListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /support/tickets/{id}/messages`, but is otherwise
         * the same as [TicketServiceAsync.reply].
         */
        @MustBeClosed
        suspend fun reply(
            id: String,
            params: TicketReplyParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<TicketReplyResponse> =
            reply(params.toBuilder().id(id).build(), requestOptions)

        /** @see reply */
        @MustBeClosed
        suspend fun reply(
            params: TicketReplyParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<TicketReplyResponse>
    }
}
