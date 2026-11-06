// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.services.async.x.tweets

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
import com.x_twitter_scraper.api.models.x.tweets.bookmark.BookmarkCreateParams
import com.x_twitter_scraper.api.models.x.tweets.bookmark.BookmarkCreateResponse
import com.x_twitter_scraper.api.models.x.tweets.bookmark.BookmarkDeleteParams
import com.x_twitter_scraper.api.models.x.tweets.bookmark.BookmarkDeleteResponse

/** X write actions (tweets, likes, follows, DMs) */
class BookmarkServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    BookmarkServiceAsync {

    private val withRawResponse: BookmarkServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): BookmarkServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: (ClientOptions.Builder) -> Unit): BookmarkServiceAsync =
        BookmarkServiceAsyncImpl(clientOptions.toBuilder().apply(modifier).build())

    override suspend fun create(
        params: BookmarkCreateParams,
        requestOptions: RequestOptions,
    ): BookmarkCreateResponse = withRawResponse().create(params, requestOptions).parse()

    override suspend fun delete(
        params: BookmarkDeleteParams,
        requestOptions: RequestOptions,
    ): BookmarkDeleteResponse = withRawResponse().delete(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        BookmarkServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: (ClientOptions.Builder) -> Unit
        ): BookmarkServiceAsync.WithRawResponse =
            BookmarkServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier).build()
            )

        private val createHandler: Handler<BookmarkCreateResponse> =
            jsonHandler<BookmarkCreateResponse>(clientOptions.jsonMapper)

        override suspend fun create(
            params: BookmarkCreateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BookmarkCreateResponse> {
            checkRequired("id", params.id())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("x", "tweets", params._pathParam(0), "bookmark")
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

        private val deleteHandler: Handler<BookmarkDeleteResponse> =
            jsonHandler<BookmarkDeleteResponse>(clientOptions.jsonMapper)

        override suspend fun delete(
            params: BookmarkDeleteParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BookmarkDeleteResponse> {
            checkRequired("id", params.id())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.DELETE)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("x", "tweets", params._pathParam(0), "bookmark")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { deleteHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }
    }
}
