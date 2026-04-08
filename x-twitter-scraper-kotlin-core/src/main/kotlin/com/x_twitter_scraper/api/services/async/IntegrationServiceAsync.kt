// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.integrations.Integration
import com.x_twitter_scraper.api.models.integrations.IntegrationCreateParams
import com.x_twitter_scraper.api.models.integrations.IntegrationDeleteParams
import com.x_twitter_scraper.api.models.integrations.IntegrationDeleteResponse
import com.x_twitter_scraper.api.models.integrations.IntegrationListDeliveriesParams
import com.x_twitter_scraper.api.models.integrations.IntegrationListDeliveriesResponse
import com.x_twitter_scraper.api.models.integrations.IntegrationListParams
import com.x_twitter_scraper.api.models.integrations.IntegrationListResponse
import com.x_twitter_scraper.api.models.integrations.IntegrationRetrieveParams
import com.x_twitter_scraper.api.models.integrations.IntegrationSendTestParams
import com.x_twitter_scraper.api.models.integrations.IntegrationSendTestResponse
import com.x_twitter_scraper.api.models.integrations.IntegrationUpdateParams

/** Push notification integrations (Telegram) */
interface IntegrationServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: (ClientOptions.Builder) -> Unit): IntegrationServiceAsync

    /** Create integration */
    suspend fun create(
        params: IntegrationCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): Integration

    /** Get integration details */
    suspend fun retrieve(
        id: String,
        params: IntegrationRetrieveParams = IntegrationRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): Integration = retrieve(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieve */
    suspend fun retrieve(
        params: IntegrationRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): Integration

    /** @see retrieve */
    suspend fun retrieve(id: String, requestOptions: RequestOptions): Integration =
        retrieve(id, IntegrationRetrieveParams.none(), requestOptions)

    /** Update integration */
    suspend fun update(
        id: String,
        params: IntegrationUpdateParams = IntegrationUpdateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): Integration = update(params.toBuilder().id(id).build(), requestOptions)

    /** @see update */
    suspend fun update(
        params: IntegrationUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): Integration

    /** @see update */
    suspend fun update(id: String, requestOptions: RequestOptions): Integration =
        update(id, IntegrationUpdateParams.none(), requestOptions)

    /** List integrations */
    suspend fun list(
        params: IntegrationListParams = IntegrationListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): IntegrationListResponse

    /** @see list */
    suspend fun list(requestOptions: RequestOptions): IntegrationListResponse =
        list(IntegrationListParams.none(), requestOptions)

    /** Delete integration */
    suspend fun delete(
        id: String,
        params: IntegrationDeleteParams = IntegrationDeleteParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): IntegrationDeleteResponse = delete(params.toBuilder().id(id).build(), requestOptions)

    /** @see delete */
    suspend fun delete(
        params: IntegrationDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): IntegrationDeleteResponse

    /** @see delete */
    suspend fun delete(id: String, requestOptions: RequestOptions): IntegrationDeleteResponse =
        delete(id, IntegrationDeleteParams.none(), requestOptions)

    /** List integration delivery history */
    suspend fun listDeliveries(
        id: String,
        params: IntegrationListDeliveriesParams = IntegrationListDeliveriesParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): IntegrationListDeliveriesResponse =
        listDeliveries(params.toBuilder().id(id).build(), requestOptions)

    /** @see listDeliveries */
    suspend fun listDeliveries(
        params: IntegrationListDeliveriesParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): IntegrationListDeliveriesResponse

    /** @see listDeliveries */
    suspend fun listDeliveries(
        id: String,
        requestOptions: RequestOptions,
    ): IntegrationListDeliveriesResponse =
        listDeliveries(id, IntegrationListDeliveriesParams.none(), requestOptions)

    /** Send test delivery */
    suspend fun sendTest(
        id: String,
        params: IntegrationSendTestParams = IntegrationSendTestParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): IntegrationSendTestResponse = sendTest(params.toBuilder().id(id).build(), requestOptions)

    /** @see sendTest */
    suspend fun sendTest(
        params: IntegrationSendTestParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): IntegrationSendTestResponse

    /** @see sendTest */
    suspend fun sendTest(id: String, requestOptions: RequestOptions): IntegrationSendTestResponse =
        sendTest(id, IntegrationSendTestParams.none(), requestOptions)

    /**
     * A view of [IntegrationServiceAsync] that provides access to raw HTTP responses for each
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
        ): IntegrationServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /integrations`, but is otherwise the same as
         * [IntegrationServiceAsync.create].
         */
        @MustBeClosed
        suspend fun create(
            params: IntegrationCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<Integration>

        /**
         * Returns a raw HTTP response for `get /integrations/{id}`, but is otherwise the same as
         * [IntegrationServiceAsync.retrieve].
         */
        @MustBeClosed
        suspend fun retrieve(
            id: String,
            params: IntegrationRetrieveParams = IntegrationRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<Integration> =
            retrieve(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        suspend fun retrieve(
            params: IntegrationRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<Integration>

        /** @see retrieve */
        @MustBeClosed
        suspend fun retrieve(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<Integration> =
            retrieve(id, IntegrationRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `patch /integrations/{id}`, but is otherwise the same as
         * [IntegrationServiceAsync.update].
         */
        @MustBeClosed
        suspend fun update(
            id: String,
            params: IntegrationUpdateParams = IntegrationUpdateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<Integration> = update(params.toBuilder().id(id).build(), requestOptions)

        /** @see update */
        @MustBeClosed
        suspend fun update(
            params: IntegrationUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<Integration>

        /** @see update */
        @MustBeClosed
        suspend fun update(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<Integration> = update(id, IntegrationUpdateParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /integrations`, but is otherwise the same as
         * [IntegrationServiceAsync.list].
         */
        @MustBeClosed
        suspend fun list(
            params: IntegrationListParams = IntegrationListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<IntegrationListResponse>

        /** @see list */
        @MustBeClosed
        suspend fun list(requestOptions: RequestOptions): HttpResponseFor<IntegrationListResponse> =
            list(IntegrationListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete /integrations/{id}`, but is otherwise the same as
         * [IntegrationServiceAsync.delete].
         */
        @MustBeClosed
        suspend fun delete(
            id: String,
            params: IntegrationDeleteParams = IntegrationDeleteParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<IntegrationDeleteResponse> =
            delete(params.toBuilder().id(id).build(), requestOptions)

        /** @see delete */
        @MustBeClosed
        suspend fun delete(
            params: IntegrationDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<IntegrationDeleteResponse>

        /** @see delete */
        @MustBeClosed
        suspend fun delete(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<IntegrationDeleteResponse> =
            delete(id, IntegrationDeleteParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /integrations/{id}/deliveries`, but is otherwise the
         * same as [IntegrationServiceAsync.listDeliveries].
         */
        @MustBeClosed
        suspend fun listDeliveries(
            id: String,
            params: IntegrationListDeliveriesParams = IntegrationListDeliveriesParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<IntegrationListDeliveriesResponse> =
            listDeliveries(params.toBuilder().id(id).build(), requestOptions)

        /** @see listDeliveries */
        @MustBeClosed
        suspend fun listDeliveries(
            params: IntegrationListDeliveriesParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<IntegrationListDeliveriesResponse>

        /** @see listDeliveries */
        @MustBeClosed
        suspend fun listDeliveries(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<IntegrationListDeliveriesResponse> =
            listDeliveries(id, IntegrationListDeliveriesParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /integrations/{id}/test`, but is otherwise the same
         * as [IntegrationServiceAsync.sendTest].
         */
        @MustBeClosed
        suspend fun sendTest(
            id: String,
            params: IntegrationSendTestParams = IntegrationSendTestParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<IntegrationSendTestResponse> =
            sendTest(params.toBuilder().id(id).build(), requestOptions)

        /** @see sendTest */
        @MustBeClosed
        suspend fun sendTest(
            params: IntegrationSendTestParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<IntegrationSendTestResponse>

        /** @see sendTest */
        @MustBeClosed
        suspend fun sendTest(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<IntegrationSendTestResponse> =
            sendTest(id, IntegrationSendTestParams.none(), requestOptions)
    }
}
