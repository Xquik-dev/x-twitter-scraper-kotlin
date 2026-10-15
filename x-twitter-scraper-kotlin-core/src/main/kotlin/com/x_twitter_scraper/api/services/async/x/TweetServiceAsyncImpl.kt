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
import com.x_twitter_scraper.api.core.http.json
import com.x_twitter_scraper.api.core.http.parseable
import com.x_twitter_scraper.api.core.prepareAsync
import com.x_twitter_scraper.api.models.PaginatedTweets
import com.x_twitter_scraper.api.models.PaginatedUsers
import com.x_twitter_scraper.api.models.x.tweets.TweetCheckRepostParams
import com.x_twitter_scraper.api.models.x.tweets.TweetCheckRepostResponse
import com.x_twitter_scraper.api.models.x.tweets.TweetCreateParams
import com.x_twitter_scraper.api.models.x.tweets.TweetCreateResponse
import com.x_twitter_scraper.api.models.x.tweets.TweetDeleteParams
import com.x_twitter_scraper.api.models.x.tweets.TweetDeleteResponse
import com.x_twitter_scraper.api.models.x.tweets.TweetEmbedParams
import com.x_twitter_scraper.api.models.x.tweets.TweetEmbedResponse
import com.x_twitter_scraper.api.models.x.tweets.TweetGetFavoritersParams
import com.x_twitter_scraper.api.models.x.tweets.TweetGetHiddenRepliesParams
import com.x_twitter_scraper.api.models.x.tweets.TweetGetInsightsParams
import com.x_twitter_scraper.api.models.x.tweets.TweetGetInsightsResponse
import com.x_twitter_scraper.api.models.x.tweets.TweetGetQuotesParams
import com.x_twitter_scraper.api.models.x.tweets.TweetGetRepliesParams
import com.x_twitter_scraper.api.models.x.tweets.TweetGetRepliesResponse
import com.x_twitter_scraper.api.models.x.tweets.TweetGetRetweeterIdsParams
import com.x_twitter_scraper.api.models.x.tweets.TweetGetRetweeterIdsResponse
import com.x_twitter_scraper.api.models.x.tweets.TweetGetRetweetersParams
import com.x_twitter_scraper.api.models.x.tweets.TweetGetSubtitlesParams
import com.x_twitter_scraper.api.models.x.tweets.TweetGetSubtitlesResponse
import com.x_twitter_scraper.api.models.x.tweets.TweetGetThreadParams
import com.x_twitter_scraper.api.models.x.tweets.TweetListParams
import com.x_twitter_scraper.api.models.x.tweets.TweetListTopParams
import com.x_twitter_scraper.api.models.x.tweets.TweetResolveLinksParams
import com.x_twitter_scraper.api.models.x.tweets.TweetResolveLinksResponse
import com.x_twitter_scraper.api.models.x.tweets.TweetRetrieveParams
import com.x_twitter_scraper.api.models.x.tweets.TweetRetrieveResponse
import com.x_twitter_scraper.api.models.x.tweets.TweetSearchParams
import com.x_twitter_scraper.api.models.x.tweets.TweetSearchResponse
import com.x_twitter_scraper.api.models.x.tweets.TweetTranslateParams
import com.x_twitter_scraper.api.models.x.tweets.TweetTranslateResponse
import com.x_twitter_scraper.api.services.async.x.tweets.LikeServiceAsync
import com.x_twitter_scraper.api.services.async.x.tweets.LikeServiceAsyncImpl
import com.x_twitter_scraper.api.services.async.x.tweets.RetweetServiceAsync
import com.x_twitter_scraper.api.services.async.x.tweets.RetweetServiceAsyncImpl

class TweetServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    TweetServiceAsync {

    private val withRawResponse: TweetServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val like: LikeServiceAsync by lazy { LikeServiceAsyncImpl(clientOptions) }

    private val retweet: RetweetServiceAsync by lazy { RetweetServiceAsyncImpl(clientOptions) }

    override fun withRawResponse(): TweetServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: (ClientOptions.Builder) -> Unit): TweetServiceAsync =
        TweetServiceAsyncImpl(clientOptions.toBuilder().apply(modifier).build())

    /** X write actions (tweets, likes, follows, DMs) */
    override fun like(): LikeServiceAsync = like

    /** X write actions (tweets, likes, follows, DMs) */
    override fun retweet(): RetweetServiceAsync = retweet

    override suspend fun create(
        params: TweetCreateParams,
        requestOptions: RequestOptions,
    ): TweetCreateResponse = withRawResponse().create(params, requestOptions).parse()

    override suspend fun retrieve(
        params: TweetRetrieveParams,
        requestOptions: RequestOptions,
    ): TweetRetrieveResponse = withRawResponse().retrieve(params, requestOptions).parse()

    override suspend fun list(
        params: TweetListParams,
        requestOptions: RequestOptions,
    ): PaginatedTweets = withRawResponse().list(params, requestOptions).parse()

    override suspend fun delete(
        params: TweetDeleteParams,
        requestOptions: RequestOptions,
    ): TweetDeleteResponse = withRawResponse().delete(params, requestOptions).parse()

    override suspend fun getFavoriters(
        params: TweetGetFavoritersParams,
        requestOptions: RequestOptions,
    ): PaginatedUsers = withRawResponse().getFavoriters(params, requestOptions).parse()

    override suspend fun getQuotes(
        params: TweetGetQuotesParams,
        requestOptions: RequestOptions,
    ): PaginatedTweets = withRawResponse().getQuotes(params, requestOptions).parse()

    override suspend fun getReplies(
        params: TweetGetRepliesParams,
        requestOptions: RequestOptions,
    ): TweetGetRepliesResponse = withRawResponse().getReplies(params, requestOptions).parse()

    override suspend fun getRetweeters(
        params: TweetGetRetweetersParams,
        requestOptions: RequestOptions,
    ): PaginatedUsers = withRawResponse().getRetweeters(params, requestOptions).parse()

    override suspend fun getThread(
        params: TweetGetThreadParams,
        requestOptions: RequestOptions,
    ): PaginatedTweets = withRawResponse().getThread(params, requestOptions).parse()

    override suspend fun search(
        params: TweetSearchParams,
        requestOptions: RequestOptions,
    ): TweetSearchResponse = withRawResponse().search(params, requestOptions).parse()

    override suspend fun listTop(
        params: TweetListTopParams,
        requestOptions: RequestOptions,
    ): PaginatedTweets = withRawResponse().listTop(params, requestOptions).parse()

    override suspend fun embed(
        params: TweetEmbedParams,
        requestOptions: RequestOptions,
    ): TweetEmbedResponse = withRawResponse().embed(params, requestOptions).parse()

    override suspend fun getHiddenReplies(
        params: TweetGetHiddenRepliesParams,
        requestOptions: RequestOptions,
    ): PaginatedTweets = withRawResponse().getHiddenReplies(params, requestOptions).parse()

    override suspend fun getInsights(
        params: TweetGetInsightsParams,
        requestOptions: RequestOptions,
    ): TweetGetInsightsResponse = withRawResponse().getInsights(params, requestOptions).parse()

    override suspend fun getRetweeterIds(
        params: TweetGetRetweeterIdsParams,
        requestOptions: RequestOptions,
    ): TweetGetRetweeterIdsResponse =
        withRawResponse().getRetweeterIds(params, requestOptions).parse()

    override suspend fun checkRepost(
        params: TweetCheckRepostParams,
        requestOptions: RequestOptions,
    ): TweetCheckRepostResponse = withRawResponse().checkRepost(params, requestOptions).parse()

    override suspend fun getSubtitles(
        params: TweetGetSubtitlesParams,
        requestOptions: RequestOptions,
    ): TweetGetSubtitlesResponse = withRawResponse().getSubtitles(params, requestOptions).parse()

    override suspend fun translate(
        params: TweetTranslateParams,
        requestOptions: RequestOptions,
    ): TweetTranslateResponse = withRawResponse().translate(params, requestOptions).parse()

    override suspend fun resolveLinks(
        params: TweetResolveLinksParams,
        requestOptions: RequestOptions,
    ): TweetResolveLinksResponse = withRawResponse().resolveLinks(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        TweetServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        private val like: LikeServiceAsync.WithRawResponse by lazy {
            LikeServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        private val retweet: RetweetServiceAsync.WithRawResponse by lazy {
            RetweetServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: (ClientOptions.Builder) -> Unit
        ): TweetServiceAsync.WithRawResponse =
            TweetServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier).build()
            )

        /** X write actions (tweets, likes, follows, DMs) */
        override fun like(): LikeServiceAsync.WithRawResponse = like

        /** X write actions (tweets, likes, follows, DMs) */
        override fun retweet(): RetweetServiceAsync.WithRawResponse = retweet

        private val createHandler: Handler<TweetCreateResponse> =
            jsonHandler<TweetCreateResponse>(clientOptions.jsonMapper)

        override suspend fun create(
            params: TweetCreateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<TweetCreateResponse> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("x", "tweets")
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

        private val retrieveHandler: Handler<TweetRetrieveResponse> =
            jsonHandler<TweetRetrieveResponse>(clientOptions.jsonMapper)

        override suspend fun retrieve(
            params: TweetRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<TweetRetrieveResponse> {
            checkRequired("id", params.id())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("x", "tweets", params._pathParam(0))
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

        private val listHandler: Handler<PaginatedTweets> =
            jsonHandler<PaginatedTweets>(clientOptions.jsonMapper)

        override suspend fun list(
            params: TweetListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<PaginatedTweets> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("x", "tweets")
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

        private val deleteHandler: Handler<TweetDeleteResponse> =
            jsonHandler<TweetDeleteResponse>(clientOptions.jsonMapper)

        override suspend fun delete(
            params: TweetDeleteParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<TweetDeleteResponse> {
            checkRequired("id", params.id())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.DELETE)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("x", "tweets", params._pathParam(0))
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

        private val getFavoritersHandler: Handler<PaginatedUsers> =
            jsonHandler<PaginatedUsers>(clientOptions.jsonMapper)

        override suspend fun getFavoriters(
            params: TweetGetFavoritersParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<PaginatedUsers> {
            checkRequired("id", params.id())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("x", "tweets", params._pathParam(0), "favoriters")
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { getFavoritersHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val getQuotesHandler: Handler<PaginatedTweets> =
            jsonHandler<PaginatedTweets>(clientOptions.jsonMapper)

        override suspend fun getQuotes(
            params: TweetGetQuotesParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<PaginatedTweets> {
            checkRequired("id", params.id())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("x", "tweets", params._pathParam(0), "quotes")
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { getQuotesHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val getRepliesHandler: Handler<TweetGetRepliesResponse> =
            jsonHandler<TweetGetRepliesResponse>(clientOptions.jsonMapper)

        override suspend fun getReplies(
            params: TweetGetRepliesParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<TweetGetRepliesResponse> {
            checkRequired("id", params.id())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("x", "tweets", params._pathParam(0), "replies")
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { getRepliesHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val getRetweetersHandler: Handler<PaginatedUsers> =
            jsonHandler<PaginatedUsers>(clientOptions.jsonMapper)

        override suspend fun getRetweeters(
            params: TweetGetRetweetersParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<PaginatedUsers> {
            checkRequired("id", params.id())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("x", "tweets", params._pathParam(0), "retweeters")
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { getRetweetersHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val getThreadHandler: Handler<PaginatedTweets> =
            jsonHandler<PaginatedTweets>(clientOptions.jsonMapper)

        override suspend fun getThread(
            params: TweetGetThreadParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<PaginatedTweets> {
            checkRequired("id", params.id())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("x", "tweets", params._pathParam(0), "thread")
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { getThreadHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val searchHandler: Handler<TweetSearchResponse> =
            jsonHandler<TweetSearchResponse>(clientOptions.jsonMapper)

        override suspend fun search(
            params: TweetSearchParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<TweetSearchResponse> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("x", "tweets", "search")
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

        private val listTopHandler: Handler<PaginatedTweets> =
            jsonHandler<PaginatedTweets>(clientOptions.jsonMapper)

        override suspend fun listTop(
            params: TweetListTopParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<PaginatedTweets> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("x", "tweets", "top")
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { listTopHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val embedHandler: Handler<TweetEmbedResponse> =
            jsonHandler<TweetEmbedResponse>(clientOptions.jsonMapper)

        override suspend fun embed(
            params: TweetEmbedParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<TweetEmbedResponse> {
            checkRequired("id", params.id())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("x", "tweets", params._pathParam(0), "embed")
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { embedHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val getHiddenRepliesHandler: Handler<PaginatedTweets> =
            jsonHandler<PaginatedTweets>(clientOptions.jsonMapper)

        override suspend fun getHiddenReplies(
            params: TweetGetHiddenRepliesParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<PaginatedTweets> {
            checkRequired("id", params.id())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("x", "tweets", params._pathParam(0), "hidden-replies")
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { getHiddenRepliesHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val getInsightsHandler: Handler<TweetGetInsightsResponse> =
            jsonHandler<TweetGetInsightsResponse>(clientOptions.jsonMapper)

        override suspend fun getInsights(
            params: TweetGetInsightsParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<TweetGetInsightsResponse> {
            checkRequired("id", params.id())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("x", "tweets", params._pathParam(0), "insights")
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { getInsightsHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val getRetweeterIdsHandler: Handler<TweetGetRetweeterIdsResponse> =
            jsonHandler<TweetGetRetweeterIdsResponse>(clientOptions.jsonMapper)

        override suspend fun getRetweeterIds(
            params: TweetGetRetweeterIdsParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<TweetGetRetweeterIdsResponse> {
            checkRequired("id", params.id())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("x", "tweets", params._pathParam(0), "retweeter-ids")
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { getRetweeterIdsHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val checkRepostHandler: Handler<TweetCheckRepostResponse> =
            jsonHandler<TweetCheckRepostResponse>(clientOptions.jsonMapper)

        override suspend fun checkRepost(
            params: TweetCheckRepostParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<TweetCheckRepostResponse> {
            checkRequired("id", params.id())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("x", "tweets", params._pathParam(0), "retweeters", "check")
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { checkRepostHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val getSubtitlesHandler: Handler<TweetGetSubtitlesResponse> =
            jsonHandler<TweetGetSubtitlesResponse>(clientOptions.jsonMapper)

        override suspend fun getSubtitles(
            params: TweetGetSubtitlesParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<TweetGetSubtitlesResponse> {
            checkRequired("id", params.id())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("x", "tweets", params._pathParam(0), "subtitles")
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { getSubtitlesHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val translateHandler: Handler<TweetTranslateResponse> =
            jsonHandler<TweetTranslateResponse>(clientOptions.jsonMapper)

        override suspend fun translate(
            params: TweetTranslateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<TweetTranslateResponse> {
            checkRequired("id", params.id())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("x", "tweets", params._pathParam(0), "translation")
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { translateHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val resolveLinksHandler: Handler<TweetResolveLinksResponse> =
            jsonHandler<TweetResolveLinksResponse>(clientOptions.jsonMapper)

        override suspend fun resolveLinks(
            params: TweetResolveLinksParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<TweetResolveLinksResponse> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("x", "links", "resolve")
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { resolveLinksHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }
    }
}
