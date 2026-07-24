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
import com.x_twitter_scraper.api.core.http.json
import com.x_twitter_scraper.api.core.http.parseable
import com.x_twitter_scraper.api.core.prepareAsync
import com.x_twitter_scraper.api.models.account.AccountRetrieveParams
import com.x_twitter_scraper.api.models.account.AccountRetrieveResponse
import com.x_twitter_scraper.api.models.account.AccountSetXUsernameParams
import com.x_twitter_scraper.api.models.account.AccountSetXUsernameResponse
import com.x_twitter_scraper.api.models.account.AccountUpdateLocaleParams
import com.x_twitter_scraper.api.models.account.AccountUpdateLocaleResponse

/** Account info and settings */
class AccountServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    AccountServiceAsync {

    private val withRawResponse: AccountServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): AccountServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: (ClientOptions.Builder) -> Unit): AccountServiceAsync =
        AccountServiceAsyncImpl(clientOptions.toBuilder().apply(modifier).build())

    override suspend fun retrieve(
        params: AccountRetrieveParams,
        requestOptions: RequestOptions,
    ): AccountRetrieveResponse =
        // get /account
        withRawResponse().retrieve(params, requestOptions).parse()

    override suspend fun setXUsername(
        params: AccountSetXUsernameParams,
        requestOptions: RequestOptions,
    ): AccountSetXUsernameResponse =
        // put /account/x-identity
        withRawResponse().setXUsername(params, requestOptions).parse()

    override suspend fun updateLocale(
        params: AccountUpdateLocaleParams,
        requestOptions: RequestOptions,
    ): AccountUpdateLocaleResponse =
        // patch /account
        withRawResponse().updateLocale(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        AccountServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: (ClientOptions.Builder) -> Unit
        ): AccountServiceAsync.WithRawResponse =
            AccountServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier).build()
            )

        private val retrieveHandler: Handler<AccountRetrieveResponse> =
            jsonHandler<AccountRetrieveResponse>(clientOptions.jsonMapper)

        override suspend fun retrieve(
            params: AccountRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<AccountRetrieveResponse> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("account")
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

        private val setXUsernameHandler: Handler<AccountSetXUsernameResponse> =
            jsonHandler<AccountSetXUsernameResponse>(clientOptions.jsonMapper)

        override suspend fun setXUsername(
            params: AccountSetXUsernameParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<AccountSetXUsernameResponse> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.PUT)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("account", "x-identity")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { setXUsernameHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val updateLocaleHandler: Handler<AccountUpdateLocaleResponse> =
            jsonHandler<AccountUpdateLocaleResponse>(clientOptions.jsonMapper)

        override suspend fun updateLocale(
            params: AccountUpdateLocaleParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<AccountUpdateLocaleResponse> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.PATCH)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("account")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { updateLocaleHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }
    }
}
