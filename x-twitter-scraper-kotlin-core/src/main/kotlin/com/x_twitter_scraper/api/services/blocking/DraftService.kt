// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.blocking

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponse
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.drafts.DraftCreateParams
import com.x_twitter_scraper.api.models.drafts.DraftCreateResponse
import com.x_twitter_scraper.api.models.drafts.DraftDeleteParams
import com.x_twitter_scraper.api.models.drafts.DraftListParams
import com.x_twitter_scraper.api.models.drafts.DraftListResponse
import com.x_twitter_scraper.api.models.drafts.DraftRetrieveParams
import com.x_twitter_scraper.api.models.drafts.DraftRetrieveResponse

/** Tweet composition, drafts, writing styles & radar */
interface DraftService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: (ClientOptions.Builder) -> Unit): DraftService

    /** Save a tweet draft */
    fun create(
        params: DraftCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): DraftCreateResponse

    /** Get draft by ID */
    fun retrieve(
        id: String,
        params: DraftRetrieveParams = DraftRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): DraftRetrieveResponse = retrieve(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        params: DraftRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): DraftRetrieveResponse

    /** @see retrieve */
    fun retrieve(id: String, requestOptions: RequestOptions): DraftRetrieveResponse =
        retrieve(id, DraftRetrieveParams.none(), requestOptions)

    /** List saved drafts */
    fun list(
        params: DraftListParams = DraftListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): DraftListResponse

    /** @see list */
    fun list(requestOptions: RequestOptions): DraftListResponse =
        list(DraftListParams.none(), requestOptions)

    /** Delete a draft */
    fun delete(
        id: String,
        params: DraftDeleteParams = DraftDeleteParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ) = delete(params.toBuilder().id(id).build(), requestOptions)

    /** @see delete */
    fun delete(params: DraftDeleteParams, requestOptions: RequestOptions = RequestOptions.none())

    /** @see delete */
    fun delete(id: String, requestOptions: RequestOptions) =
        delete(id, DraftDeleteParams.none(), requestOptions)

    /** A view of [DraftService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: (ClientOptions.Builder) -> Unit): DraftService.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /drafts`, but is otherwise the same as
         * [DraftService.create].
         */
        @MustBeClosed
        fun create(
            params: DraftCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<DraftCreateResponse>

        /**
         * Returns a raw HTTP response for `get /drafts/{id}`, but is otherwise the same as
         * [DraftService.retrieve].
         */
        @MustBeClosed
        fun retrieve(
            id: String,
            params: DraftRetrieveParams = DraftRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<DraftRetrieveResponse> =
            retrieve(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: DraftRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<DraftRetrieveResponse>

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<DraftRetrieveResponse> =
            retrieve(id, DraftRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /drafts`, but is otherwise the same as
         * [DraftService.list].
         */
        @MustBeClosed
        fun list(
            params: DraftListParams = DraftListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<DraftListResponse>

        /** @see list */
        @MustBeClosed
        fun list(requestOptions: RequestOptions): HttpResponseFor<DraftListResponse> =
            list(DraftListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete /drafts/{id}`, but is otherwise the same as
         * [DraftService.delete].
         */
        @MustBeClosed
        fun delete(
            id: String,
            params: DraftDeleteParams = DraftDeleteParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponse = delete(params.toBuilder().id(id).build(), requestOptions)

        /** @see delete */
        @MustBeClosed
        fun delete(
            params: DraftDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponse

        /** @see delete */
        @MustBeClosed
        fun delete(id: String, requestOptions: RequestOptions): HttpResponse =
            delete(id, DraftDeleteParams.none(), requestOptions)
    }
}
