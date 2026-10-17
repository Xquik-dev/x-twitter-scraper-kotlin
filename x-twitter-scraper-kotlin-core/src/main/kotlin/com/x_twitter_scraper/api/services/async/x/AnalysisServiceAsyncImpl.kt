// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.services.async.x

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
import com.x_twitter_scraper.api.models.x.analysis.AnalysisBrandParams
import com.x_twitter_scraper.api.models.x.analysis.AnalysisClassifyParams
import com.x_twitter_scraper.api.models.x.analysis.AnalysisMarketSignalsParams
import com.x_twitter_scraper.api.models.x.analysis.AnalysisNewsParams
import com.x_twitter_scraper.api.models.x.analysis.AnalysisSentimentParams
import com.x_twitter_scraper.api.models.x.analysis.AnalysisViralScoreParams
import com.x_twitter_scraper.api.models.x.analysis.TweetAnalysisPage

/** AI analysis of posts, at 2 credits per analyzed post */
class AnalysisServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    AnalysisServiceAsync {

    private val withRawResponse: AnalysisServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): AnalysisServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: (ClientOptions.Builder) -> Unit): AnalysisServiceAsync =
        AnalysisServiceAsyncImpl(clientOptions.toBuilder().apply(modifier).build())

    override suspend fun sentiment(
        params: AnalysisSentimentParams,
        requestOptions: RequestOptions,
    ): TweetAnalysisPage = withRawResponse().sentiment(params, requestOptions).parse()

    override suspend fun viralScore(
        params: AnalysisViralScoreParams,
        requestOptions: RequestOptions,
    ): TweetAnalysisPage = withRawResponse().viralScore(params, requestOptions).parse()

    override suspend fun brand(
        params: AnalysisBrandParams,
        requestOptions: RequestOptions,
    ): TweetAnalysisPage = withRawResponse().brand(params, requestOptions).parse()

    override suspend fun news(
        params: AnalysisNewsParams,
        requestOptions: RequestOptions,
    ): TweetAnalysisPage = withRawResponse().news(params, requestOptions).parse()

    override suspend fun marketSignals(
        params: AnalysisMarketSignalsParams,
        requestOptions: RequestOptions,
    ): TweetAnalysisPage = withRawResponse().marketSignals(params, requestOptions).parse()

    override suspend fun classify(
        params: AnalysisClassifyParams,
        requestOptions: RequestOptions,
    ): TweetAnalysisPage = withRawResponse().classify(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        AnalysisServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: (ClientOptions.Builder) -> Unit
        ): AnalysisServiceAsync.WithRawResponse =
            AnalysisServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier).build()
            )

        private val sentimentHandler: Handler<TweetAnalysisPage> =
            jsonHandler<TweetAnalysisPage>(clientOptions.jsonMapper)

        override suspend fun sentiment(
            params: AnalysisSentimentParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<TweetAnalysisPage> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("x", "analysis", "sentiment")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { sentimentHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val viralScoreHandler: Handler<TweetAnalysisPage> =
            jsonHandler<TweetAnalysisPage>(clientOptions.jsonMapper)

        override suspend fun viralScore(
            params: AnalysisViralScoreParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<TweetAnalysisPage> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("x", "analysis", "viral-score")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { viralScoreHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val brandHandler: Handler<TweetAnalysisPage> =
            jsonHandler<TweetAnalysisPage>(clientOptions.jsonMapper)

        override suspend fun brand(
            params: AnalysisBrandParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<TweetAnalysisPage> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("x", "analysis", "brand")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { brandHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val newsHandler: Handler<TweetAnalysisPage> =
            jsonHandler<TweetAnalysisPage>(clientOptions.jsonMapper)

        override suspend fun news(
            params: AnalysisNewsParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<TweetAnalysisPage> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("x", "analysis", "news")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { newsHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val marketSignalsHandler: Handler<TweetAnalysisPage> =
            jsonHandler<TweetAnalysisPage>(clientOptions.jsonMapper)

        override suspend fun marketSignals(
            params: AnalysisMarketSignalsParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<TweetAnalysisPage> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("x", "analysis", "market-signals")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { marketSignalsHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val classifyHandler: Handler<TweetAnalysisPage> =
            jsonHandler<TweetAnalysisPage>(clientOptions.jsonMapper)

        override suspend fun classify(
            params: AnalysisClassifyParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<TweetAnalysisPage> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("x", "analysis", "classify")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { classifyHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }
    }
}
