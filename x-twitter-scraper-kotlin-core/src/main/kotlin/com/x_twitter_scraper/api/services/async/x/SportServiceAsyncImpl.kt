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
import com.x_twitter_scraper.api.models.x.sports.FootballBoxScore
import com.x_twitter_scraper.api.models.x.sports.FootballPlays
import com.x_twitter_scraper.api.models.x.sports.FootballProps
import com.x_twitter_scraper.api.models.x.sports.FootballRoster
import com.x_twitter_scraper.api.models.x.sports.FootballSchedule
import com.x_twitter_scraper.api.models.x.sports.FootballScoreboard
import com.x_twitter_scraper.api.models.x.sports.SportListGamesParams
import com.x_twitter_scraper.api.models.x.sports.SportListPostsParams
import com.x_twitter_scraper.api.models.x.sports.SportRetrieveAudienceParams
import com.x_twitter_scraper.api.models.x.sports.SportRetrieveBoxScoreParams
import com.x_twitter_scraper.api.models.x.sports.SportRetrievePlayerPropsParams
import com.x_twitter_scraper.api.models.x.sports.SportRetrievePlaysParams
import com.x_twitter_scraper.api.models.x.sports.SportRetrieveRosterParams
import com.x_twitter_scraper.api.models.x.sports.SportRetrieveScoreboardParams
import com.x_twitter_scraper.api.models.x.sports.SportsGameAudience
import com.x_twitter_scraper.api.models.x.sports.SportsGamePosts

/** NFL & college football games, scoreboards, plays & box scores */
class SportServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    SportServiceAsync {

    private val withRawResponse: SportServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): SportServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: (ClientOptions.Builder) -> Unit): SportServiceAsync =
        SportServiceAsyncImpl(clientOptions.toBuilder().apply(modifier).build())

    override suspend fun listGames(
        params: SportListGamesParams,
        requestOptions: RequestOptions,
    ): FootballSchedule = withRawResponse().listGames(params, requestOptions).parse()

    override suspend fun retrieveScoreboard(
        params: SportRetrieveScoreboardParams,
        requestOptions: RequestOptions,
    ): FootballScoreboard = withRawResponse().retrieveScoreboard(params, requestOptions).parse()

    override suspend fun retrieveBoxScore(
        params: SportRetrieveBoxScoreParams,
        requestOptions: RequestOptions,
    ): FootballBoxScore = withRawResponse().retrieveBoxScore(params, requestOptions).parse()

    override suspend fun retrievePlays(
        params: SportRetrievePlaysParams,
        requestOptions: RequestOptions,
    ): FootballPlays = withRawResponse().retrievePlays(params, requestOptions).parse()

    override suspend fun retrieveAudience(
        params: SportRetrieveAudienceParams,
        requestOptions: RequestOptions,
    ): SportsGameAudience = withRawResponse().retrieveAudience(params, requestOptions).parse()

    override suspend fun retrieveRoster(
        params: SportRetrieveRosterParams,
        requestOptions: RequestOptions,
    ): FootballRoster = withRawResponse().retrieveRoster(params, requestOptions).parse()

    override suspend fun retrievePlayerProps(
        params: SportRetrievePlayerPropsParams,
        requestOptions: RequestOptions,
    ): FootballProps = withRawResponse().retrievePlayerProps(params, requestOptions).parse()

    override suspend fun listPosts(
        params: SportListPostsParams,
        requestOptions: RequestOptions,
    ): SportsGamePosts = withRawResponse().listPosts(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        SportServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: (ClientOptions.Builder) -> Unit
        ): SportServiceAsync.WithRawResponse =
            SportServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier).build()
            )

        private val listGamesHandler: Handler<FootballSchedule> =
            jsonHandler<FootballSchedule>(clientOptions.jsonMapper)

        override suspend fun listGames(
            params: SportListGamesParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<FootballSchedule> {
            checkRequired("league", params.league())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("x", "sports", params._pathParam(0), "games")
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { listGamesHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val retrieveScoreboardHandler: Handler<FootballScoreboard> =
            jsonHandler<FootballScoreboard>(clientOptions.jsonMapper)

        override suspend fun retrieveScoreboard(
            params: SportRetrieveScoreboardParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<FootballScoreboard> {
            checkRequired("id", params.id())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "x",
                        "sports",
                        params._pathParam(0),
                        "games",
                        params._pathParam(1),
                    )
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { retrieveScoreboardHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val retrieveBoxScoreHandler: Handler<FootballBoxScore> =
            jsonHandler<FootballBoxScore>(clientOptions.jsonMapper)

        override suspend fun retrieveBoxScore(
            params: SportRetrieveBoxScoreParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<FootballBoxScore> {
            checkRequired("id", params.id())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "x",
                        "sports",
                        params._pathParam(0),
                        "games",
                        params._pathParam(1),
                        "box-score",
                    )
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { retrieveBoxScoreHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val retrievePlaysHandler: Handler<FootballPlays> =
            jsonHandler<FootballPlays>(clientOptions.jsonMapper)

        override suspend fun retrievePlays(
            params: SportRetrievePlaysParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<FootballPlays> {
            checkRequired("id", params.id())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "x",
                        "sports",
                        params._pathParam(0),
                        "games",
                        params._pathParam(1),
                        "plays",
                    )
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { retrievePlaysHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val retrieveAudienceHandler: Handler<SportsGameAudience> =
            jsonHandler<SportsGameAudience>(clientOptions.jsonMapper)

        override suspend fun retrieveAudience(
            params: SportRetrieveAudienceParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SportsGameAudience> {
            checkRequired("id", params.id())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "x",
                        "sports",
                        params._pathParam(0),
                        "games",
                        params._pathParam(1),
                        "audience",
                    )
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { retrieveAudienceHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val retrieveRosterHandler: Handler<FootballRoster> =
            jsonHandler<FootballRoster>(clientOptions.jsonMapper)

        override suspend fun retrieveRoster(
            params: SportRetrieveRosterParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<FootballRoster> {
            checkRequired("id", params.id())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "x",
                        "sports",
                        params._pathParam(0),
                        "games",
                        params._pathParam(1),
                        "roster",
                    )
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { retrieveRosterHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val retrievePlayerPropsHandler: Handler<FootballProps> =
            jsonHandler<FootballProps>(clientOptions.jsonMapper)

        override suspend fun retrievePlayerProps(
            params: SportRetrievePlayerPropsParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<FootballProps> {
            checkRequired("id", params.id())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "x",
                        "sports",
                        params._pathParam(0),
                        "games",
                        params._pathParam(1),
                        "player-props",
                    )
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { retrievePlayerPropsHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val listPostsHandler: Handler<SportsGamePosts> =
            jsonHandler<SportsGamePosts>(clientOptions.jsonMapper)

        override suspend fun listPosts(
            params: SportListPostsParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SportsGamePosts> {
            checkRequired("id", params.id())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "x",
                        "sports",
                        params._pathParam(0),
                        "games",
                        params._pathParam(1),
                        "posts",
                    )
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeAsync(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { listPostsHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }
    }
}
