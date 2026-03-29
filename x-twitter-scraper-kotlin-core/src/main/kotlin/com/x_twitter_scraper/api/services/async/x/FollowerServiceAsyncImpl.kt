// File generated from our OpenAPI spec by Stainless.

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
import com.x_twitter_scraper.api.core.http.parseable
import com.x_twitter_scraper.api.core.prepareAsync
import com.x_twitter_scraper.api.models.x.followers.FollowerRetrieveCheckParams
import com.x_twitter_scraper.api.models.x.followers.FollowerRetrieveCheckResponse

/** X data lookups (subscription required) */
class FollowerServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    FollowerServiceAsync {

    private val withRawResponse: FollowerServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): FollowerServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: (ClientOptions.Builder) -> Unit): FollowerServiceAsync =
        FollowerServiceAsyncImpl(clientOptions.toBuilder().apply(modifier).build())

    override suspend fun retrieveCheck(
        params: FollowerRetrieveCheckParams,
        requestOptions: RequestOptions,
    ): FollowerRetrieveCheckResponse =
        // get /x/followers/check
        withRawResponse().retrieveCheck(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        FollowerServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: (ClientOptions.Builder) -> Unit
        ): FollowerServiceAsync.WithRawResponse =
            FollowerServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier).build()
            )

        private val retrieveCheckHandler: Handler<FollowerRetrieveCheckResponse> =
            jsonHandler<FollowerRetrieveCheckResponse>(clientOptions.jsonMapper)

        override suspend fun retrieveCheck(
            params: FollowerRetrieveCheckParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<FollowerRetrieveCheckResponse> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("x", "followers", "check")
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { retrieveCheckHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }
    }
}
