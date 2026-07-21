// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async.support

import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.checkRequired
import com.x_twitter_scraper.api.core.handlers.errorBodyHandler
import com.x_twitter_scraper.api.core.handlers.errorHandler
import com.x_twitter_scraper.api.core.handlers.jsonHandler
import com.x_twitter_scraper.api.core.http.HttpMethod
import com.x_twitter_scraper.api.core.http.HttpRequest
import com.x_twitter_scraper.api.core.http.HttpResponse
import com.x_twitter_scraper.api.core.http.HttpResponse.Handler
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.core.http.json
import com.x_twitter_scraper.api.core.http.multipartFormData
import com.x_twitter_scraper.api.core.http.parseable
import com.x_twitter_scraper.api.core.prepareAsync
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
class TicketServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    TicketServiceAsync {

    private val withRawResponse: TicketServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): TicketServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: (ClientOptions.Builder) -> Unit): TicketServiceAsync =
        TicketServiceAsyncImpl(clientOptions.toBuilder().apply(modifier).build())

    override suspend fun create(
        params: TicketCreateParams,
        requestOptions: RequestOptions,
    ): TicketCreateResponse =
        // post /support/tickets
        withRawResponse().create(params, requestOptions).parse()

    override suspend fun retrieve(
        params: TicketRetrieveParams,
        requestOptions: RequestOptions,
    ): TicketRetrieveResponse =
        // get /support/tickets/{id}
        withRawResponse().retrieve(params, requestOptions).parse()

    override suspend fun update(
        params: TicketUpdateParams,
        requestOptions: RequestOptions,
    ): TicketUpdateResponse =
        // patch /support/tickets/{id}
        withRawResponse().update(params, requestOptions).parse()

    override suspend fun list(
        params: TicketListParams,
        requestOptions: RequestOptions,
    ): TicketListResponse =
        // get /support/tickets
        withRawResponse().list(params, requestOptions).parse()

    override suspend fun reply(
        params: TicketReplyParams,
        requestOptions: RequestOptions,
    ): TicketReplyResponse =
        // post /support/tickets/{id}/messages
        withRawResponse().reply(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        TicketServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: (ClientOptions.Builder) -> Unit
        ): TicketServiceAsync.WithRawResponse =
            TicketServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier).build()
            )

        private val createHandler: Handler<TicketCreateResponse> =
            jsonHandler<TicketCreateResponse>(clientOptions.jsonMapper)

        override suspend fun create(
            params: TicketCreateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<TicketCreateResponse> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("support", "tickets")
                    .body(multipartFormData(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { createHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val retrieveHandler: Handler<TicketRetrieveResponse> =
            jsonHandler<TicketRetrieveResponse>(clientOptions.jsonMapper)

        override suspend fun retrieve(
            params: TicketRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<TicketRetrieveResponse> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("id", params.id())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("support", "tickets", params._pathParam(0))
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { retrieveHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val updateHandler: Handler<TicketUpdateResponse> =
            jsonHandler<TicketUpdateResponse>(clientOptions.jsonMapper)

        override suspend fun update(
            params: TicketUpdateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<TicketUpdateResponse> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("id", params.id())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.PATCH)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("support", "tickets", params._pathParam(0))
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { updateHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val listHandler: Handler<TicketListResponse> =
            jsonHandler<TicketListResponse>(clientOptions.jsonMapper)

        override suspend fun list(
            params: TicketListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<TicketListResponse> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("support", "tickets")
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { listHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val replyHandler: Handler<TicketReplyResponse> =
            jsonHandler<TicketReplyResponse>(clientOptions.jsonMapper)

        override suspend fun reply(
            params: TicketReplyParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<TicketReplyResponse> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("id", params.id())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("support", "tickets", params._pathParam(0), "messages")
                    .body(multipartFormData(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { replyHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }
    }
}
