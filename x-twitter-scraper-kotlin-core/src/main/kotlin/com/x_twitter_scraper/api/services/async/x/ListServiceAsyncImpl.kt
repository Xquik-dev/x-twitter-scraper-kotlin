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
import com.x_twitter_scraper.api.models.PaginatedTweets
import com.x_twitter_scraper.api.models.PaginatedUsers
import com.x_twitter_scraper.api.models.x.lists.ListRetrieveFollowersParams
import com.x_twitter_scraper.api.models.x.lists.ListRetrieveMembersParams
import com.x_twitter_scraper.api.models.x.lists.ListRetrieveParams
import com.x_twitter_scraper.api.models.x.lists.ListRetrieveResponse
import com.x_twitter_scraper.api.models.x.lists.ListRetrieveSearchParams
import com.x_twitter_scraper.api.models.x.lists.ListRetrieveSearchResponse
import com.x_twitter_scraper.api.models.x.lists.ListRetrieveTweetsParams

/** X List followers, members, and tweets */
class ListServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    ListServiceAsync {

    private val withRawResponse: ListServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): ListServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: (ClientOptions.Builder) -> Unit): ListServiceAsync =
        ListServiceAsyncImpl(clientOptions.toBuilder().apply(modifier).build())

    override suspend fun retrieveFollowers(
        params: ListRetrieveFollowersParams,
        requestOptions: RequestOptions,
    ): PaginatedUsers = withRawResponse().retrieveFollowers(params, requestOptions).parse()

    override suspend fun retrieveMembers(
        params: ListRetrieveMembersParams,
        requestOptions: RequestOptions,
    ): PaginatedUsers = withRawResponse().retrieveMembers(params, requestOptions).parse()

    override suspend fun retrieveTweets(
        params: ListRetrieveTweetsParams,
        requestOptions: RequestOptions,
    ): PaginatedTweets = withRawResponse().retrieveTweets(params, requestOptions).parse()

    override suspend fun retrieve(
        params: ListRetrieveParams,
        requestOptions: RequestOptions,
    ): ListRetrieveResponse = withRawResponse().retrieve(params, requestOptions).parse()

    override suspend fun retrieveSearch(
        params: ListRetrieveSearchParams,
        requestOptions: RequestOptions,
    ): ListRetrieveSearchResponse = withRawResponse().retrieveSearch(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        ListServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: (ClientOptions.Builder) -> Unit
        ): ListServiceAsync.WithRawResponse =
            ListServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier).build()
            )

        private val retrieveFollowersHandler: Handler<PaginatedUsers> =
            jsonHandler<PaginatedUsers>(clientOptions.jsonMapper)

        override suspend fun retrieveFollowers(
            params: ListRetrieveFollowersParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<PaginatedUsers> {
            checkRequired("id", params.id())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("x", "lists", params._pathParam(0), "followers")
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { retrieveFollowersHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val retrieveMembersHandler: Handler<PaginatedUsers> =
            jsonHandler<PaginatedUsers>(clientOptions.jsonMapper)

        override suspend fun retrieveMembers(
            params: ListRetrieveMembersParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<PaginatedUsers> {
            checkRequired("id", params.id())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("x", "lists", params._pathParam(0), "members")
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { retrieveMembersHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val retrieveTweetsHandler: Handler<PaginatedTweets> =
            jsonHandler<PaginatedTweets>(clientOptions.jsonMapper)

        override suspend fun retrieveTweets(
            params: ListRetrieveTweetsParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<PaginatedTweets> {
            checkRequired("id", params.id())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("x", "lists", params._pathParam(0), "tweets")
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { retrieveTweetsHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val retrieveHandler: Handler<ListRetrieveResponse> =
            jsonHandler<ListRetrieveResponse>(clientOptions.jsonMapper)

        override suspend fun retrieve(
            params: ListRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ListRetrieveResponse> {
            checkRequired("id", params.id())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("x", "lists", params._pathParam(0))
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

        private val retrieveSearchHandler: Handler<ListRetrieveSearchResponse> =
            jsonHandler<ListRetrieveSearchResponse>(clientOptions.jsonMapper)

        override suspend fun retrieveSearch(
            params: ListRetrieveSearchParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ListRetrieveSearchResponse> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("x", "lists", "search")
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { retrieveSearchHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }
    }
}
