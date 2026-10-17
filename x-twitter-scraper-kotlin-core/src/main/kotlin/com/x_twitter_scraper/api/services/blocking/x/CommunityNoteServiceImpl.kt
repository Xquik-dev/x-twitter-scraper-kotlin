// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

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
import com.x_twitter_scraper.api.core.http.parseable
import com.x_twitter_scraper.api.core.prepare
import com.x_twitter_scraper.api.models.x.communitynotes.CommunityNoteListContributorDeletedNotesParams
import com.x_twitter_scraper.api.models.x.communitynotes.CommunityNoteListContributorNotesParams
import com.x_twitter_scraper.api.models.x.communitynotes.CommunityNoteRetrieveContributorParams
import com.x_twitter_scraper.api.models.x.communitynotes.CommunityNoteRetrieveContributorResponse
import com.x_twitter_scraper.api.models.x.communitynotes.PaginatedCommunityNotes

/** Community Notes contributors, their notes, ratings & deleted notes */
class CommunityNoteServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    CommunityNoteService {

    private val withRawResponse: CommunityNoteService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): CommunityNoteService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: (ClientOptions.Builder) -> Unit): CommunityNoteService =
        CommunityNoteServiceImpl(clientOptions.toBuilder().apply(modifier).build())

    override fun retrieveContributor(
        params: CommunityNoteRetrieveContributorParams,
        requestOptions: RequestOptions,
    ): CommunityNoteRetrieveContributorResponse =
        withRawResponse().retrieveContributor(params, requestOptions).parse()

    override fun listContributorNotes(
        params: CommunityNoteListContributorNotesParams,
        requestOptions: RequestOptions,
    ): PaginatedCommunityNotes =
        withRawResponse().listContributorNotes(params, requestOptions).parse()

    override fun listContributorDeletedNotes(
        params: CommunityNoteListContributorDeletedNotesParams,
        requestOptions: RequestOptions,
    ): PaginatedCommunityNotes =
        withRawResponse().listContributorDeletedNotes(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        CommunityNoteService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: (ClientOptions.Builder) -> Unit
        ): CommunityNoteService.WithRawResponse =
            CommunityNoteServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier).build()
            )

        private val retrieveContributorHandler: Handler<CommunityNoteRetrieveContributorResponse> =
            jsonHandler<CommunityNoteRetrieveContributorResponse>(clientOptions.jsonMapper)

        override fun retrieveContributor(
            params: CommunityNoteRetrieveContributorParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<CommunityNoteRetrieveContributorResponse> {
            checkRequired("alias", params.alias())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("x", "community-notes", "contributors", params._pathParam(0))
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { retrieveContributorHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val listContributorNotesHandler: Handler<PaginatedCommunityNotes> =
            jsonHandler<PaginatedCommunityNotes>(clientOptions.jsonMapper)

        override fun listContributorNotes(
            params: CommunityNoteListContributorNotesParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<PaginatedCommunityNotes> {
            checkRequired("alias", params.alias())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "x",
                        "community-notes",
                        "contributors",
                        params._pathParam(0),
                        "notes",
                    )
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { listContributorNotesHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val listContributorDeletedNotesHandler: Handler<PaginatedCommunityNotes> =
            jsonHandler<PaginatedCommunityNotes>(clientOptions.jsonMapper)

        override fun listContributorDeletedNotes(
            params: CommunityNoteListContributorDeletedNotesParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<PaginatedCommunityNotes> {
            checkRequired("alias", params.alias())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "x",
                        "community-notes",
                        "contributors",
                        params._pathParam(0),
                        "deleted-notes",
                    )
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { listContributorDeletedNotesHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }
    }
}
