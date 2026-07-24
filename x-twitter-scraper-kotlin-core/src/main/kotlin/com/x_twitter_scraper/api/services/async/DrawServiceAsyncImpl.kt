// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

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
import com.x_twitter_scraper.api.models.draws.DrawExportParams
import com.x_twitter_scraper.api.models.draws.DrawListParams
import com.x_twitter_scraper.api.models.draws.DrawListResponse
import com.x_twitter_scraper.api.models.draws.DrawRetrieveParams
import com.x_twitter_scraper.api.models.draws.DrawRetrieveResponse
import com.x_twitter_scraper.api.models.draws.DrawRunParams
import com.x_twitter_scraper.api.models.draws.DrawRunResponse

/** Giveaway draws from tweet replies */
class DrawServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    DrawServiceAsync {

    private val withRawResponse: DrawServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): DrawServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: (ClientOptions.Builder) -> Unit): DrawServiceAsync =
        DrawServiceAsyncImpl(clientOptions.toBuilder().apply(modifier).build())

    override suspend fun retrieve(
        params: DrawRetrieveParams,
        requestOptions: RequestOptions,
    ): DrawRetrieveResponse =
        // get /draws/{id}
        withRawResponse().retrieve(params, requestOptions).parse()

    override suspend fun list(
        params: DrawListParams,
        requestOptions: RequestOptions,
    ): DrawListResponse =
        // get /draws
        withRawResponse().list(params, requestOptions).parse()

    override suspend fun export(
        params: DrawExportParams,
        requestOptions: RequestOptions,
    ): HttpResponse =
        // get /draws/{id}/export
        withRawResponse().export(params, requestOptions)

    override suspend fun run(
        params: DrawRunParams,
        requestOptions: RequestOptions,
    ): DrawRunResponse =
        // post /draws
        withRawResponse().run(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        DrawServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: (ClientOptions.Builder) -> Unit
        ): DrawServiceAsync.WithRawResponse =
            DrawServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier).build()
            )

        private val retrieveHandler: Handler<DrawRetrieveResponse> =
            jsonHandler<DrawRetrieveResponse>(clientOptions.jsonMapper)

        override suspend fun retrieve(
            params: DrawRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<DrawRetrieveResponse> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("id", params.id())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("draws", params._pathParam(0))
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

        private val listHandler: Handler<DrawListResponse> =
            jsonHandler<DrawListResponse>(clientOptions.jsonMapper)

        override suspend fun list(
            params: DrawListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<DrawListResponse> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("draws")
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

        override suspend fun export(
            params: DrawExportParams,
            requestOptions: RequestOptions,
        ): HttpResponse {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("id", params.id())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("draws", params._pathParam(0), "export")
                    .putHeader("Accept", "application/octet-stream")
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, requestOptions)
            return errorHandler.handle(response)
        }

        private val runHandler: Handler<DrawRunResponse> =
            jsonHandler<DrawRunResponse>(clientOptions.jsonMapper)

        override suspend fun run(
            params: DrawRunParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<DrawRunResponse> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("draws")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { runHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }
    }
}
