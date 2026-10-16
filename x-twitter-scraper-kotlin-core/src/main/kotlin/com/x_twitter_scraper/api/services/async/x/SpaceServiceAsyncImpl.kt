// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.services.async.x

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
import com.x_twitter_scraper.api.core.http.parseable
import com.x_twitter_scraper.api.core.prepareAsync
import com.x_twitter_scraper.api.models.x.spaces.SpaceRetrieveParams
import com.x_twitter_scraper.api.models.x.spaces.SpaceRetrieveReplayParams
import com.x_twitter_scraper.api.models.x.spaces.SpaceRetrieveReplayResponse
import com.x_twitter_scraper.api.models.x.spaces.SpaceRetrieveResponse
import com.x_twitter_scraper.api.models.x.spaces.SpaceSearchParams
import com.x_twitter_scraper.api.models.x.spaces.SpaceSearchResponse

/** X Space details, host, and speakers */
class SpaceServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    SpaceServiceAsync {

    private val withRawResponse: SpaceServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): SpaceServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: (ClientOptions.Builder) -> Unit): SpaceServiceAsync =
        SpaceServiceAsyncImpl(clientOptions.toBuilder().apply(modifier).build())

    override suspend fun retrieve(
        params: SpaceRetrieveParams,
        requestOptions: RequestOptions,
    ): SpaceRetrieveResponse = withRawResponse().retrieve(params, requestOptions).parse()

    override suspend fun retrieveReplay(
        params: SpaceRetrieveReplayParams,
        requestOptions: RequestOptions,
    ): SpaceRetrieveReplayResponse =
        withRawResponse().retrieveReplay(params, requestOptions).parse()

    override suspend fun search(
        params: SpaceSearchParams,
        requestOptions: RequestOptions,
    ): SpaceSearchResponse = withRawResponse().search(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        SpaceServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: (ClientOptions.Builder) -> Unit
        ): SpaceServiceAsync.WithRawResponse =
            SpaceServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier).build()
            )

        private val retrieveHandler: Handler<SpaceRetrieveResponse> =
            jsonHandler<SpaceRetrieveResponse>(clientOptions.jsonMapper)

        override suspend fun retrieve(
            params: SpaceRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SpaceRetrieveResponse> {
            checkRequired("id", params.id())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("x", "spaces", params._pathParam(0))
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

        private val retrieveReplayHandler: Handler<SpaceRetrieveReplayResponse> =
            jsonHandler<SpaceRetrieveReplayResponse>(clientOptions.jsonMapper)

        override suspend fun retrieveReplay(
            params: SpaceRetrieveReplayParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SpaceRetrieveReplayResponse> {
            checkRequired("id", params.id())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("x", "spaces", params._pathParam(0), "replay")
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { retrieveReplayHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val searchHandler: Handler<SpaceSearchResponse> =
            jsonHandler<SpaceSearchResponse>(clientOptions.jsonMapper)

        override suspend fun search(
            params: SpaceSearchParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SpaceSearchResponse> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("x", "spaces", "search")
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { searchHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }
    }
}
