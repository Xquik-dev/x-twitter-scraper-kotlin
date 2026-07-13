// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async.monitors

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.monitors.keywords.KeywordCreateParams
import com.x_twitter_scraper.api.models.monitors.keywords.KeywordCreateResponse
import com.x_twitter_scraper.api.models.monitors.keywords.KeywordDeactivateParams
import com.x_twitter_scraper.api.models.monitors.keywords.KeywordDeactivateResponse
import com.x_twitter_scraper.api.models.monitors.keywords.KeywordListParams
import com.x_twitter_scraper.api.models.monitors.keywords.KeywordListResponse
import com.x_twitter_scraper.api.models.monitors.keywords.KeywordRetrieveParams
import com.x_twitter_scraper.api.models.monitors.keywords.KeywordRetrieveResponse
import com.x_twitter_scraper.api.models.monitors.keywords.KeywordUpdateParams
import com.x_twitter_scraper.api.models.monitors.keywords.KeywordUpdateResponse

/** Real-time X account monitoring */
interface KeywordServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: (ClientOptions.Builder) -> Unit): KeywordServiceAsync

    /**
     * Creates an instant keyword monitor. Keyword monitors are unlimited. Active monitors check
     * every 1 second and cost 21 credits per hour. Events and webhook deliveries are included.
     * Creation requires available credits for the first hourly charge.
     */
    suspend fun create(
        params: KeywordCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): KeywordCreateResponse

    /** Get keyword monitor */
    suspend fun retrieve(
        id: String,
        params: KeywordRetrieveParams = KeywordRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): KeywordRetrieveResponse = retrieve(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieve */
    suspend fun retrieve(
        params: KeywordRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): KeywordRetrieveResponse

    /** @see retrieve */
    suspend fun retrieve(id: String, requestOptions: RequestOptions): KeywordRetrieveResponse =
        retrieve(id, KeywordRetrieveParams.none(), requestOptions)

    /** Update keyword monitor */
    suspend fun update(
        id: String,
        params: KeywordUpdateParams = KeywordUpdateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): KeywordUpdateResponse = update(params.toBuilder().id(id).build(), requestOptions)

    /** @see update */
    suspend fun update(
        params: KeywordUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): KeywordUpdateResponse

    /** @see update */
    suspend fun update(id: String, requestOptions: RequestOptions): KeywordUpdateResponse =
        update(id, KeywordUpdateParams.none(), requestOptions)

    /** List keyword monitors */
    suspend fun list(
        params: KeywordListParams = KeywordListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): KeywordListResponse

    /** @see list */
    suspend fun list(requestOptions: RequestOptions): KeywordListResponse =
        list(KeywordListParams.none(), requestOptions)

    /** Delete keyword monitor */
    suspend fun deactivate(
        id: String,
        params: KeywordDeactivateParams = KeywordDeactivateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): KeywordDeactivateResponse = deactivate(params.toBuilder().id(id).build(), requestOptions)

    /** @see deactivate */
    suspend fun deactivate(
        params: KeywordDeactivateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): KeywordDeactivateResponse

    /** @see deactivate */
    suspend fun deactivate(id: String, requestOptions: RequestOptions): KeywordDeactivateResponse =
        deactivate(id, KeywordDeactivateParams.none(), requestOptions)

    /**
     * A view of [KeywordServiceAsync] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: (ClientOptions.Builder) -> Unit
        ): KeywordServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /monitors/keywords`, but is otherwise the same as
         * [KeywordServiceAsync.create].
         */
        @MustBeClosed
        suspend fun create(
            params: KeywordCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<KeywordCreateResponse>

        /**
         * Returns a raw HTTP response for `get /monitors/keywords/{id}`, but is otherwise the same
         * as [KeywordServiceAsync.retrieve].
         */
        @MustBeClosed
        suspend fun retrieve(
            id: String,
            params: KeywordRetrieveParams = KeywordRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<KeywordRetrieveResponse> =
            retrieve(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        suspend fun retrieve(
            params: KeywordRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<KeywordRetrieveResponse>

        /** @see retrieve */
        @MustBeClosed
        suspend fun retrieve(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<KeywordRetrieveResponse> =
            retrieve(id, KeywordRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `patch /monitors/keywords/{id}`, but is otherwise the
         * same as [KeywordServiceAsync.update].
         */
        @MustBeClosed
        suspend fun update(
            id: String,
            params: KeywordUpdateParams = KeywordUpdateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<KeywordUpdateResponse> =
            update(params.toBuilder().id(id).build(), requestOptions)

        /** @see update */
        @MustBeClosed
        suspend fun update(
            params: KeywordUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<KeywordUpdateResponse>

        /** @see update */
        @MustBeClosed
        suspend fun update(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<KeywordUpdateResponse> =
            update(id, KeywordUpdateParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /monitors/keywords`, but is otherwise the same as
         * [KeywordServiceAsync.list].
         */
        @MustBeClosed
        suspend fun list(
            params: KeywordListParams = KeywordListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<KeywordListResponse>

        /** @see list */
        @MustBeClosed
        suspend fun list(requestOptions: RequestOptions): HttpResponseFor<KeywordListResponse> =
            list(KeywordListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete /monitors/keywords/{id}`, but is otherwise the
         * same as [KeywordServiceAsync.deactivate].
         */
        @MustBeClosed
        suspend fun deactivate(
            id: String,
            params: KeywordDeactivateParams = KeywordDeactivateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<KeywordDeactivateResponse> =
            deactivate(params.toBuilder().id(id).build(), requestOptions)

        /** @see deactivate */
        @MustBeClosed
        suspend fun deactivate(
            params: KeywordDeactivateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<KeywordDeactivateResponse>

        /** @see deactivate */
        @MustBeClosed
        suspend fun deactivate(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<KeywordDeactivateResponse> =
            deactivate(id, KeywordDeactivateParams.none(), requestOptions)
    }
}
