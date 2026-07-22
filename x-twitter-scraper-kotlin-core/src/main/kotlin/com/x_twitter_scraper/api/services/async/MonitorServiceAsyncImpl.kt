// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async

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
import com.x_twitter_scraper.api.core.http.parseable
import com.x_twitter_scraper.api.core.prepareAsync
import com.x_twitter_scraper.api.models.monitors.Monitor
import com.x_twitter_scraper.api.models.monitors.MonitorCreateParams
import com.x_twitter_scraper.api.models.monitors.MonitorCreateResponse
import com.x_twitter_scraper.api.models.monitors.MonitorDeactivateParams
import com.x_twitter_scraper.api.models.monitors.MonitorDeactivateResponse
import com.x_twitter_scraper.api.models.monitors.MonitorListParams
import com.x_twitter_scraper.api.models.monitors.MonitorListResponse
import com.x_twitter_scraper.api.models.monitors.MonitorRetrieveParams
import com.x_twitter_scraper.api.models.monitors.MonitorUpdateParams
import com.x_twitter_scraper.api.services.async.monitors.KeywordServiceAsync
import com.x_twitter_scraper.api.services.async.monitors.KeywordServiceAsyncImpl

/** Real-time X account monitoring */
class MonitorServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    MonitorServiceAsync {

    private val withRawResponse: MonitorServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val keywords: KeywordServiceAsync by lazy { KeywordServiceAsyncImpl(clientOptions) }

    override fun withRawResponse(): MonitorServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: (ClientOptions.Builder) -> Unit): MonitorServiceAsync =
        MonitorServiceAsyncImpl(clientOptions.toBuilder().apply(modifier).build())

    /** Real-time X account monitoring */
    override fun keywords(): KeywordServiceAsync = keywords

    override suspend fun create(
        params: MonitorCreateParams,
        requestOptions: RequestOptions,
    ): MonitorCreateResponse =
        // post /monitors
        withRawResponse().create(params, requestOptions).parse()

    override suspend fun retrieve(
        params: MonitorRetrieveParams,
        requestOptions: RequestOptions,
    ): Monitor =
        // get /monitors/{id}
        withRawResponse().retrieve(params, requestOptions).parse()

    override suspend fun update(
        params: MonitorUpdateParams,
        requestOptions: RequestOptions,
    ): Monitor =
        // patch /monitors/{id}
        withRawResponse().update(params, requestOptions).parse()

    override suspend fun list(
        params: MonitorListParams,
        requestOptions: RequestOptions,
    ): MonitorListResponse =
        // get /monitors
        withRawResponse().list(params, requestOptions).parse()

    override suspend fun deactivate(
        params: MonitorDeactivateParams,
        requestOptions: RequestOptions,
    ): MonitorDeactivateResponse =
        // delete /monitors/{id}
        withRawResponse().deactivate(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        MonitorServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        private val keywords: KeywordServiceAsync.WithRawResponse by lazy {
            KeywordServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: (ClientOptions.Builder) -> Unit
        ): MonitorServiceAsync.WithRawResponse =
            MonitorServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier).build()
            )

        /** Real-time X account monitoring */
        override fun keywords(): KeywordServiceAsync.WithRawResponse = keywords

        private val createHandler: Handler<MonitorCreateResponse> =
            jsonHandler<MonitorCreateResponse>(clientOptions.jsonMapper)

        override suspend fun create(
            params: MonitorCreateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<MonitorCreateResponse> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("monitors")
                    .body(json(clientOptions.jsonMapper, params._body()))
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

        private val retrieveHandler: Handler<Monitor> =
            jsonHandler<Monitor>(clientOptions.jsonMapper)

        override suspend fun retrieve(
            params: MonitorRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<Monitor> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("id", params.id())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("monitors", params._pathParam(0))
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

        private val updateHandler: Handler<Monitor> = jsonHandler<Monitor>(clientOptions.jsonMapper)

        override suspend fun update(
            params: MonitorUpdateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<Monitor> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("id", params.id())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.PATCH)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("monitors", params._pathParam(0))
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

        private val listHandler: Handler<MonitorListResponse> =
            jsonHandler<MonitorListResponse>(clientOptions.jsonMapper)

        override suspend fun list(
            params: MonitorListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<MonitorListResponse> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("monitors")
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

        private val deactivateHandler: Handler<MonitorDeactivateResponse> =
            jsonHandler<MonitorDeactivateResponse>(clientOptions.jsonMapper)

        override suspend fun deactivate(
            params: MonitorDeactivateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<MonitorDeactivateResponse> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("id", params.id())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.DELETE)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("monitors", params._pathParam(0))
                    .apply { params._body()?.let { body(json(clientOptions.jsonMapper, it)) } }
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { deactivateHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }
    }
}
