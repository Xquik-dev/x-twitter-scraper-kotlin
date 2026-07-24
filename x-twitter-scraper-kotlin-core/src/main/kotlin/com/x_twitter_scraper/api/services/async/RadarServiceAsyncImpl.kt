// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async

import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
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
import com.x_twitter_scraper.api.models.radar.RadarRetrieveTrendingTopicsParams
import com.x_twitter_scraper.api.models.radar.RadarRetrieveTrendingTopicsResponse

/** AI tweet composition, drafts, writing styles, and radar */
class RadarServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    RadarServiceAsync {

    private val withRawResponse: RadarServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): RadarServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: (ClientOptions.Builder) -> Unit): RadarServiceAsync =
        RadarServiceAsyncImpl(clientOptions.toBuilder().apply(modifier).build())

    override suspend fun retrieveTrendingTopics(
        params: RadarRetrieveTrendingTopicsParams,
        requestOptions: RequestOptions,
    ): RadarRetrieveTrendingTopicsResponse =
        // get /radar
        withRawResponse().retrieveTrendingTopics(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        RadarServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: (ClientOptions.Builder) -> Unit
        ): RadarServiceAsync.WithRawResponse =
            RadarServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier).build()
            )

        private val retrieveTrendingTopicsHandler: Handler<RadarRetrieveTrendingTopicsResponse> =
            jsonHandler<RadarRetrieveTrendingTopicsResponse>(clientOptions.jsonMapper)

        override suspend fun retrieveTrendingTopics(
            params: RadarRetrieveTrendingTopicsParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<RadarRetrieveTrendingTopicsResponse> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("radar")
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { retrieveTrendingTopicsHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }
    }
}
