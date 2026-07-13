// File generated from our OpenAPI spec by Stainless.

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
import com.x_twitter_scraper.api.models.x.accountconnectionchallenges.AccountConnectionChallengeSubmitParams
import com.x_twitter_scraper.api.models.x.accountconnectionchallenges.AccountConnectionChallengeSubmitResponse

/** Connected X account management */
class AccountConnectionChallengeServiceAsyncImpl
internal constructor(private val clientOptions: ClientOptions) :
    AccountConnectionChallengeServiceAsync {

    private val withRawResponse: AccountConnectionChallengeServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): AccountConnectionChallengeServiceAsync.WithRawResponse =
        withRawResponse

    override fun withOptions(
        modifier: (ClientOptions.Builder) -> Unit
    ): AccountConnectionChallengeServiceAsync =
        AccountConnectionChallengeServiceAsyncImpl(
            clientOptions.toBuilder().apply(modifier).build()
        )

    override suspend fun submit(
        params: AccountConnectionChallengeSubmitParams,
        requestOptions: RequestOptions,
    ): AccountConnectionChallengeSubmitResponse =
        // post /x/account-connection-challenges/{id}/submit
        withRawResponse().submit(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        AccountConnectionChallengeServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: (ClientOptions.Builder) -> Unit
        ): AccountConnectionChallengeServiceAsync.WithRawResponse =
            AccountConnectionChallengeServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier).build()
            )

        private val submitHandler: Handler<AccountConnectionChallengeSubmitResponse> =
            jsonHandler<AccountConnectionChallengeSubmitResponse>(clientOptions.jsonMapper)

        override suspend fun submit(
            params: AccountConnectionChallengeSubmitParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<AccountConnectionChallengeSubmitResponse> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("id", params.id())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "x",
                        "account-connection-challenges",
                        params._pathParam(0),
                        "submit",
                    )
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { submitHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }
    }
}
