// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async.bot

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
import com.x_twitter_scraper.api.models.bot.platformlinks.PlatformLinkCreateParams
import com.x_twitter_scraper.api.models.bot.platformlinks.PlatformLinkCreateResponse
import com.x_twitter_scraper.api.models.bot.platformlinks.PlatformLinkDeleteParams
import com.x_twitter_scraper.api.models.bot.platformlinks.PlatformLinkDeleteResponse
import com.x_twitter_scraper.api.models.bot.platformlinks.PlatformLinkLookupParams
import com.x_twitter_scraper.api.models.bot.platformlinks.PlatformLinkLookupResponse

/** Telegram bot service endpoints */
class PlatformLinkServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    PlatformLinkServiceAsync {

    private val withRawResponse: PlatformLinkServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): PlatformLinkServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: (ClientOptions.Builder) -> Unit): PlatformLinkServiceAsync =
        PlatformLinkServiceAsyncImpl(clientOptions.toBuilder().apply(modifier).build())

    override suspend fun create(
        params: PlatformLinkCreateParams,
        requestOptions: RequestOptions,
    ): PlatformLinkCreateResponse =
        // post /bot/platform-links
        withRawResponse().create(params, requestOptions).parse()

    override suspend fun delete(
        params: PlatformLinkDeleteParams,
        requestOptions: RequestOptions,
    ): PlatformLinkDeleteResponse =
        // delete /bot/platform-links
        withRawResponse().delete(params, requestOptions).parse()

    override suspend fun lookup(
        params: PlatformLinkLookupParams,
        requestOptions: RequestOptions,
    ): PlatformLinkLookupResponse =
        // get /bot/platform-links/lookup
        withRawResponse().lookup(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        PlatformLinkServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: (ClientOptions.Builder) -> Unit
        ): PlatformLinkServiceAsync.WithRawResponse =
            PlatformLinkServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier).build()
            )

        private val createHandler: Handler<PlatformLinkCreateResponse> =
            jsonHandler<PlatformLinkCreateResponse>(clientOptions.jsonMapper)

        override suspend fun create(
            params: PlatformLinkCreateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<PlatformLinkCreateResponse> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("bot", "platform-links")
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

        private val deleteHandler: Handler<PlatformLinkDeleteResponse> =
            jsonHandler<PlatformLinkDeleteResponse>(clientOptions.jsonMapper)

        override suspend fun delete(
            params: PlatformLinkDeleteParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<PlatformLinkDeleteResponse> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.DELETE)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("bot", "platform-links")
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

        private val lookupHandler: Handler<PlatformLinkLookupResponse> =
            jsonHandler<PlatformLinkLookupResponse>(clientOptions.jsonMapper)

        override suspend fun lookup(
            params: PlatformLinkLookupParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<PlatformLinkLookupResponse> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("bot", "platform-links", "lookup")
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { lookupHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }
    }
}
