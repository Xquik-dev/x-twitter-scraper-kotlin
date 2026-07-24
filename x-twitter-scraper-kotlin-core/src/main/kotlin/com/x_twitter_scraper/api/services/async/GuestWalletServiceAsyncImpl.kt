// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async

import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.SecurityOptions
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
import com.x_twitter_scraper.api.models.guestwallets.GuestWalletCreateParams
import com.x_twitter_scraper.api.models.guestwallets.GuestWalletCreateResponse
import com.x_twitter_scraper.api.models.guestwallets.GuestWalletRetrieveStatusParams
import com.x_twitter_scraper.api.models.guestwallets.GuestWalletRetrieveStatusResponse
import com.x_twitter_scraper.api.models.guestwallets.GuestWalletTopupParams
import com.x_twitter_scraper.api.models.guestwallets.GuestWalletTopupResponse

/** Accountless prepaid access for paid read endpoints */
class GuestWalletServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    GuestWalletServiceAsync {

    private val withRawResponse: GuestWalletServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): GuestWalletServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: (ClientOptions.Builder) -> Unit): GuestWalletServiceAsync =
        GuestWalletServiceAsyncImpl(clientOptions.toBuilder().apply(modifier).build())

    override suspend fun create(
        params: GuestWalletCreateParams,
        requestOptions: RequestOptions,
    ): GuestWalletCreateResponse =
        // post /guest-wallets
        withRawResponse().create(params, requestOptions).parse()

    override suspend fun retrieveStatus(
        params: GuestWalletRetrieveStatusParams,
        requestOptions: RequestOptions,
    ): GuestWalletRetrieveStatusResponse =
        // get /guest-wallets/status
        withRawResponse().retrieveStatus(params, requestOptions).parse()

    override suspend fun topup(
        params: GuestWalletTopupParams,
        requestOptions: RequestOptions,
    ): GuestWalletTopupResponse =
        // post /guest-wallets/topups
        withRawResponse().topup(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        GuestWalletServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: (ClientOptions.Builder) -> Unit
        ): GuestWalletServiceAsync.WithRawResponse =
            GuestWalletServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier).build()
            )

        private val createHandler: Handler<GuestWalletCreateResponse> =
            jsonHandler<GuestWalletCreateResponse>(clientOptions.jsonMapper)

        override suspend fun create(
            params: GuestWalletCreateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<GuestWalletCreateResponse> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("guest-wallets")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareAsync(clientOptions, params, SecurityOptions.none())
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

        private val retrieveStatusHandler: Handler<GuestWalletRetrieveStatusResponse> =
            jsonHandler<GuestWalletRetrieveStatusResponse>(clientOptions.jsonMapper)

        override suspend fun retrieveStatus(
            params: GuestWalletRetrieveStatusParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<GuestWalletRetrieveStatusResponse> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("guest-wallets", "status")
                    .build()
                    .prepareAsync(
                        clientOptions,
                        params,
                        SecurityOptions.builder().apiKey(true).build(),
                    )
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { retrieveStatusHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val topupHandler: Handler<GuestWalletTopupResponse> =
            jsonHandler<GuestWalletTopupResponse>(clientOptions.jsonMapper)

        override suspend fun topup(
            params: GuestWalletTopupParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<GuestWalletTopupResponse> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("guest-wallets", "topups")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareAsync(
                        clientOptions,
                        params,
                        SecurityOptions.builder().apiKey(true).build(),
                    )
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { topupHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }
    }
}
