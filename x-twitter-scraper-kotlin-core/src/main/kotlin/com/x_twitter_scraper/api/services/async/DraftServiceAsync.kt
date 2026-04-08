// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponse
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.drafts.DraftCreateParams
import com.x_twitter_scraper.api.models.drafts.DraftDeleteParams
import com.x_twitter_scraper.api.models.drafts.DraftDetail
import com.x_twitter_scraper.api.models.drafts.DraftListParams
import com.x_twitter_scraper.api.models.drafts.DraftListResponse
import com.x_twitter_scraper.api.models.drafts.DraftRetrieveParams

/** Tweet composition, drafts, writing styles & radar */
interface DraftServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: (ClientOptions.Builder) -> Unit): DraftServiceAsync

    /** Save a tweet draft */
    suspend fun create(
        params: DraftCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): DraftDetail

    /** Get draft by ID */
    suspend fun retrieve(
        id: String,
        params: DraftRetrieveParams = DraftRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): DraftDetail = retrieve(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieve */
    suspend fun retrieve(
        params: DraftRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): DraftDetail

    /** @see retrieve */
    suspend fun retrieve(id: String, requestOptions: RequestOptions): DraftDetail =
        retrieve(id, DraftRetrieveParams.none(), requestOptions)

    /** List saved drafts */
    suspend fun list(
        params: DraftListParams = DraftListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): DraftListResponse

    /** @see list */
    suspend fun list(requestOptions: RequestOptions): DraftListResponse =
        list(DraftListParams.none(), requestOptions)

    /** Delete a draft */
    suspend fun delete(
        id: String,
        params: DraftDeleteParams = DraftDeleteParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ) = delete(params.toBuilder().id(id).build(), requestOptions)

    /** @see delete */
    suspend fun delete(
        params: DraftDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    )

    /** @see delete */
    suspend fun delete(id: String, requestOptions: RequestOptions) =
        delete(id, DraftDeleteParams.none(), requestOptions)

    /** A view of [DraftServiceAsync] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: (ClientOptions.Builder) -> Unit
        ): DraftServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /drafts`, but is otherwise the same as
         * [DraftServiceAsync.create].
         */
        @MustBeClosed
        suspend fun create(
            params: DraftCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<DraftDetail>

        /**
         * Returns a raw HTTP response for `get /drafts/{id}`, but is otherwise the same as
         * [DraftServiceAsync.retrieve].
         */
        @MustBeClosed
        suspend fun retrieve(
            id: String,
            params: DraftRetrieveParams = DraftRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<DraftDetail> =
            retrieve(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        suspend fun retrieve(
            params: DraftRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<DraftDetail>

        /** @see retrieve */
        @MustBeClosed
        suspend fun retrieve(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<DraftDetail> = retrieve(id, DraftRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /drafts`, but is otherwise the same as
         * [DraftServiceAsync.list].
         */
        @MustBeClosed
        suspend fun list(
            params: DraftListParams = DraftListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<DraftListResponse>

        /** @see list */
        @MustBeClosed
        suspend fun list(requestOptions: RequestOptions): HttpResponseFor<DraftListResponse> =
            list(DraftListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete /drafts/{id}`, but is otherwise the same as
         * [DraftServiceAsync.delete].
         */
        @MustBeClosed
        suspend fun delete(
            id: String,
            params: DraftDeleteParams = DraftDeleteParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponse = delete(params.toBuilder().id(id).build(), requestOptions)

        /** @see delete */
        @MustBeClosed
        suspend fun delete(
            params: DraftDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponse

        /** @see delete */
        @MustBeClosed
        suspend fun delete(id: String, requestOptions: RequestOptions): HttpResponse =
            delete(id, DraftDeleteParams.none(), requestOptions)
    }
}
