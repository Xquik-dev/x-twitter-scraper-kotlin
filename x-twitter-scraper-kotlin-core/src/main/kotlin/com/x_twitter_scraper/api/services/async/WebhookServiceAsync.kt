// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.webhooks.Webhook
import com.x_twitter_scraper.api.models.webhooks.WebhookCreateParams
import com.x_twitter_scraper.api.models.webhooks.WebhookCreateResponse
import com.x_twitter_scraper.api.models.webhooks.WebhookDeactivateParams
import com.x_twitter_scraper.api.models.webhooks.WebhookDeactivateResponse
import com.x_twitter_scraper.api.models.webhooks.WebhookListDeliveriesParams
import com.x_twitter_scraper.api.models.webhooks.WebhookListDeliveriesResponse
import com.x_twitter_scraper.api.models.webhooks.WebhookListParams
import com.x_twitter_scraper.api.models.webhooks.WebhookListResponse
import com.x_twitter_scraper.api.models.webhooks.WebhookResumeParams
import com.x_twitter_scraper.api.models.webhooks.WebhookResumeResponse
import com.x_twitter_scraper.api.models.webhooks.WebhookTestParams
import com.x_twitter_scraper.api.models.webhooks.WebhookTestResponse
import com.x_twitter_scraper.api.models.webhooks.WebhookUpdateParams

/** Webhook endpoint management and delivery */
interface WebhookServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: (ClientOptions.Builder) -> Unit): WebhookServiceAsync

    /** Create webhook */
    suspend fun create(
        params: WebhookCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): WebhookCreateResponse

    /** Update webhook */
    suspend fun update(
        id: String,
        params: WebhookUpdateParams = WebhookUpdateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): Webhook = update(params.toBuilder().id(id).build(), requestOptions)

    /** @see update */
    suspend fun update(
        params: WebhookUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): Webhook

    /** @see update */
    suspend fun update(id: String, requestOptions: RequestOptions): Webhook =
        update(id, WebhookUpdateParams.none(), requestOptions)

    /** List webhooks */
    suspend fun list(
        params: WebhookListParams = WebhookListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): WebhookListResponse

    /** @see list */
    suspend fun list(requestOptions: RequestOptions): WebhookListResponse =
        list(WebhookListParams.none(), requestOptions)

    /** Deactivate webhook */
    suspend fun deactivate(
        id: String,
        params: WebhookDeactivateParams = WebhookDeactivateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): WebhookDeactivateResponse = deactivate(params.toBuilder().id(id).build(), requestOptions)

    /** @see deactivate */
    suspend fun deactivate(
        params: WebhookDeactivateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): WebhookDeactivateResponse

    /** @see deactivate */
    suspend fun deactivate(id: String, requestOptions: RequestOptions): WebhookDeactivateResponse =
        deactivate(id, WebhookDeactivateParams.none(), requestOptions)

    /** List webhook deliveries */
    suspend fun listDeliveries(
        id: String,
        params: WebhookListDeliveriesParams = WebhookListDeliveriesParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): WebhookListDeliveriesResponse =
        listDeliveries(params.toBuilder().id(id).build(), requestOptions)

    /** @see listDeliveries */
    suspend fun listDeliveries(
        params: WebhookListDeliveriesParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): WebhookListDeliveriesResponse

    /** @see listDeliveries */
    suspend fun listDeliveries(
        id: String,
        requestOptions: RequestOptions,
    ): WebhookListDeliveriesResponse =
        listDeliveries(id, WebhookListDeliveriesParams.none(), requestOptions)

    /** Test and resume webhook endpoint */
    suspend fun resume(
        id: String,
        params: WebhookResumeParams = WebhookResumeParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): WebhookResumeResponse = resume(params.toBuilder().id(id).build(), requestOptions)

    /** @see resume */
    suspend fun resume(
        params: WebhookResumeParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): WebhookResumeResponse

    /** @see resume */
    suspend fun resume(id: String, requestOptions: RequestOptions): WebhookResumeResponse =
        resume(id, WebhookResumeParams.none(), requestOptions)

    /** Test webhook endpoint */
    suspend fun test(
        id: String,
        params: WebhookTestParams = WebhookTestParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): WebhookTestResponse = test(params.toBuilder().id(id).build(), requestOptions)

    /** @see test */
    suspend fun test(
        params: WebhookTestParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): WebhookTestResponse

    /** @see test */
    suspend fun test(id: String, requestOptions: RequestOptions): WebhookTestResponse =
        test(id, WebhookTestParams.none(), requestOptions)

    /**
     * A view of [WebhookServiceAsync] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: (ClientOptions.Builder) -> Unit
        ): WebhookServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /webhooks`, but is otherwise the same as
         * [WebhookServiceAsync.create].
         */
        @MustBeClosed
        suspend fun create(
            params: WebhookCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<WebhookCreateResponse>

        /**
         * Returns a raw HTTP response for `patch /webhooks/{id}`, but is otherwise the same as
         * [WebhookServiceAsync.update].
         */
        @MustBeClosed
        suspend fun update(
            id: String,
            params: WebhookUpdateParams = WebhookUpdateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<Webhook> = update(params.toBuilder().id(id).build(), requestOptions)

        /** @see update */
        @MustBeClosed
        suspend fun update(
            params: WebhookUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<Webhook>

        /** @see update */
        @MustBeClosed
        suspend fun update(id: String, requestOptions: RequestOptions): HttpResponseFor<Webhook> =
            update(id, WebhookUpdateParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /webhooks`, but is otherwise the same as
         * [WebhookServiceAsync.list].
         */
        @MustBeClosed
        suspend fun list(
            params: WebhookListParams = WebhookListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<WebhookListResponse>

        /** @see list */
        @MustBeClosed
        suspend fun list(requestOptions: RequestOptions): HttpResponseFor<WebhookListResponse> =
            list(WebhookListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete /webhooks/{id}`, but is otherwise the same as
         * [WebhookServiceAsync.deactivate].
         */
        @MustBeClosed
        suspend fun deactivate(
            id: String,
            params: WebhookDeactivateParams = WebhookDeactivateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<WebhookDeactivateResponse> =
            deactivate(params.toBuilder().id(id).build(), requestOptions)

        /** @see deactivate */
        @MustBeClosed
        suspend fun deactivate(
            params: WebhookDeactivateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<WebhookDeactivateResponse>

        /** @see deactivate */
        @MustBeClosed
        suspend fun deactivate(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<WebhookDeactivateResponse> =
            deactivate(id, WebhookDeactivateParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /webhooks/{id}/deliveries`, but is otherwise the
         * same as [WebhookServiceAsync.listDeliveries].
         */
        @MustBeClosed
        suspend fun listDeliveries(
            id: String,
            params: WebhookListDeliveriesParams = WebhookListDeliveriesParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<WebhookListDeliveriesResponse> =
            listDeliveries(params.toBuilder().id(id).build(), requestOptions)

        /** @see listDeliveries */
        @MustBeClosed
        suspend fun listDeliveries(
            params: WebhookListDeliveriesParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<WebhookListDeliveriesResponse>

        /** @see listDeliveries */
        @MustBeClosed
        suspend fun listDeliveries(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<WebhookListDeliveriesResponse> =
            listDeliveries(id, WebhookListDeliveriesParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /webhooks/{id}/resume`, but is otherwise the same
         * as [WebhookServiceAsync.resume].
         */
        @MustBeClosed
        suspend fun resume(
            id: String,
            params: WebhookResumeParams = WebhookResumeParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<WebhookResumeResponse> =
            resume(params.toBuilder().id(id).build(), requestOptions)

        /** @see resume */
        @MustBeClosed
        suspend fun resume(
            params: WebhookResumeParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<WebhookResumeResponse>

        /** @see resume */
        @MustBeClosed
        suspend fun resume(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<WebhookResumeResponse> =
            resume(id, WebhookResumeParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /webhooks/{id}/test`, but is otherwise the same as
         * [WebhookServiceAsync.test].
         */
        @MustBeClosed
        suspend fun test(
            id: String,
            params: WebhookTestParams = WebhookTestParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<WebhookTestResponse> =
            test(params.toBuilder().id(id).build(), requestOptions)

        /** @see test */
        @MustBeClosed
        suspend fun test(
            params: WebhookTestParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<WebhookTestResponse>

        /** @see test */
        @MustBeClosed
        suspend fun test(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<WebhookTestResponse> = test(id, WebhookTestParams.none(), requestOptions)
    }
}
