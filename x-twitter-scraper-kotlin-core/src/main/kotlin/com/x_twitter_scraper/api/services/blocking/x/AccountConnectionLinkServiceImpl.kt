package com.x_twitter_scraper.api.services.blocking.x

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
import com.x_twitter_scraper.api.core.prepare
import com.x_twitter_scraper.api.models.x.accountconnectionlinks.AccountConnectionLink
import com.x_twitter_scraper.api.models.x.accountconnectionlinks.AccountConnectionLinkCreateParams
import com.x_twitter_scraper.api.models.x.accountconnectionlinks.AccountConnectionLinkCreateResponse
import com.x_twitter_scraper.api.models.x.accountconnectionlinks.AccountConnectionLinkDeleteParams
import com.x_twitter_scraper.api.models.x.accountconnectionlinks.AccountConnectionLinkDeleteResponse
import com.x_twitter_scraper.api.models.x.accountconnectionlinks.AccountConnectionLinkRetrieveParams

/** Connected X account management */
class AccountConnectionLinkServiceImpl
internal constructor(private val clientOptions: ClientOptions) : AccountConnectionLinkService {

    private val withRawResponse: AccountConnectionLinkService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): AccountConnectionLinkService.WithRawResponse = withRawResponse

    override fun withOptions(
        modifier: (ClientOptions.Builder) -> Unit
    ): AccountConnectionLinkService =
        AccountConnectionLinkServiceImpl(clientOptions.toBuilder().apply(modifier).build())

    override fun create(
        params: AccountConnectionLinkCreateParams,
        requestOptions: RequestOptions,
    ): AccountConnectionLinkCreateResponse =
        withRawResponse().create(params, requestOptions).parse()

    override fun retrieve(
        params: AccountConnectionLinkRetrieveParams,
        requestOptions: RequestOptions,
    ): AccountConnectionLink = withRawResponse().retrieve(params, requestOptions).parse()

    override fun delete(
        params: AccountConnectionLinkDeleteParams,
        requestOptions: RequestOptions,
    ): AccountConnectionLinkDeleteResponse =
        withRawResponse().delete(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        AccountConnectionLinkService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: (ClientOptions.Builder) -> Unit
        ): AccountConnectionLinkService.WithRawResponse =
            AccountConnectionLinkServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier).build()
            )

        private val createHandler: Handler<AccountConnectionLinkCreateResponse> =
            jsonHandler<AccountConnectionLinkCreateResponse>(clientOptions.jsonMapper)

        override fun create(
            params: AccountConnectionLinkCreateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<AccountConnectionLinkCreateResponse> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("x", "account-connection-links")
                    .apply { params._body()?.let { body(json(clientOptions.jsonMapper, it)) } }
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
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

        private val retrieveHandler: Handler<AccountConnectionLink> =
            jsonHandler<AccountConnectionLink>(clientOptions.jsonMapper)

        override fun retrieve(
            params: AccountConnectionLinkRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<AccountConnectionLink> {
            checkRequired("id", params.id())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("x", "account-connection-links", params._pathParam(0))
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
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

        private val deleteHandler: Handler<AccountConnectionLinkDeleteResponse> =
            jsonHandler<AccountConnectionLinkDeleteResponse>(clientOptions.jsonMapper)

        override fun delete(
            params: AccountConnectionLinkDeleteParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<AccountConnectionLinkDeleteResponse> {
            checkRequired("id", params.id())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.DELETE)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("x", "account-connection-links", params._pathParam(0))
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
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
