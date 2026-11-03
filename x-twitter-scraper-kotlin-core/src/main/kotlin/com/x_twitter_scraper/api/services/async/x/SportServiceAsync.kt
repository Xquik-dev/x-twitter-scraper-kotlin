// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.services.async.x

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponseFor
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
interface SportServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: (ClientOptions.Builder) -> Unit): SportServiceAsync

    /**
     * Lists NFL or college football games in a time window, with teams & scores. Use it to find
     * game IDs & team IDs. Without from or to, X picks about 5 weeks around today. Each request
     * costs 1 credit.
     */
    suspend fun listGames(
        league: String,
        params: SportListGamesParams = SportListGamesParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): FootballSchedule = listGames(params.toBuilder().league(league).build(), requestOptions)

    /** @see listGames */
    suspend fun listGames(
        params: SportListGamesParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): FootballSchedule

    /** @see listGames */
    suspend fun listGames(league: String, requestOptions: RequestOptions): FootballSchedule =
        listGames(league, SportListGamesParams.none(), requestOptions)

    /**
     * Returns 1 game's scoreboard. It has the teams, the score by period, the status & clock, down
     * & distance, the venue & the latest play. Each request costs 1 credit.
     */
    suspend fun retrieveScoreboard(
        id: String,
        params: SportRetrieveScoreboardParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): FootballScoreboard = retrieveScoreboard(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieveScoreboard */
    suspend fun retrieveScoreboard(
        params: SportRetrieveScoreboardParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): FootballScoreboard

    /**
     * Returns each team's totals & each player's stats for 1 game. Stats cover passing, rushing,
     * receiving, defense, kicking & returns. A game that has not started has no stats yet. Each
     * request costs 1 credit.
     */
    suspend fun retrieveBoxScore(
        id: String,
        params: SportRetrieveBoxScoreParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): FootballBoxScore = retrieveBoxScore(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieveBoxScore */
    suspend fun retrieveBoxScore(
        params: SportRetrieveBoxScoreParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): FootballBoxScore

    /**
     * Returns every drive & play of 1 game, by quarter. Each play has its clock, down & distance,
     * details, penalties & linked posts. Each request costs 1 credit.
     */
    suspend fun retrievePlays(
        id: String,
        params: SportRetrievePlaysParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): FootballPlays = retrievePlays(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrievePlays */
    suspend fun retrievePlays(
        params: SportRetrievePlaysParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): FootballPlays

    /**
     * Returns who follows 1 game on X. It has live viewers, fan picks per team & the game chat's
     * state. Each request costs 1 credit.
     */
    suspend fun retrieveAudience(
        id: String,
        params: SportRetrieveAudienceParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): SportsGameAudience = retrieveAudience(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieveAudience */
    suspend fun retrieveAudience(
        params: SportRetrieveAudienceParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): SportsGameAudience

    /**
     * Returns both teams' players for 1 NFL game. Each player has a jersey, position, headshot & X
     * account. Each request costs 1 credit.
     */
    suspend fun retrieveRoster(
        id: String,
        params: SportRetrieveRosterParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): FootballRoster = retrieveRoster(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieveRoster */
    suspend fun retrieveRoster(
        params: SportRetrieveRosterParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): FootballRoster

    /**
     * Returns the player prop lines & American odds X shows for 1 NFL game. X lists props before &
     * during a game. Other games return no categories. Each request costs 1 credit.
     */
    suspend fun retrievePlayerProps(
        id: String,
        params: SportRetrievePlayerPropsParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): FootballProps = retrievePlayerProps(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrievePlayerProps */
    suspend fun retrievePlayerProps(
        params: SportRetrievePlayerPropsParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): FootballProps

    /**
     * Returns a game's top or latest posts, plus an X search you can run for more. Works before,
     * during & after the game. Pages hold up to 20 posts, & each post costs 1 credit.
     */
    suspend fun listPosts(
        id: String,
        params: SportListPostsParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): SportsGamePosts = listPosts(params.toBuilder().id(id).build(), requestOptions)

    /** @see listPosts */
    suspend fun listPosts(
        params: SportListPostsParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): SportsGamePosts

    /** A view of [SportServiceAsync] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: (ClientOptions.Builder) -> Unit
        ): SportServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /x/sports/{league}/games`, but is otherwise the same
         * as [SportServiceAsync.listGames].
         */
        @MustBeClosed
        suspend fun listGames(
            league: String,
            params: SportListGamesParams = SportListGamesParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<FootballSchedule> =
            listGames(params.toBuilder().league(league).build(), requestOptions)

        /** @see listGames */
        @MustBeClosed
        suspend fun listGames(
            params: SportListGamesParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<FootballSchedule>

        /** @see listGames */
        @MustBeClosed
        suspend fun listGames(
            league: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<FootballSchedule> =
            listGames(league, SportListGamesParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/sports/{league}/games/{id}`, but is otherwise the
         * same as [SportServiceAsync.retrieveScoreboard].
         */
        @MustBeClosed
        suspend fun retrieveScoreboard(
            id: String,
            params: SportRetrieveScoreboardParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<FootballScoreboard> =
            retrieveScoreboard(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieveScoreboard */
        @MustBeClosed
        suspend fun retrieveScoreboard(
            params: SportRetrieveScoreboardParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<FootballScoreboard>

        /**
         * Returns a raw HTTP response for `get /x/sports/{league}/games/{id}/box-score`, but is
         * otherwise the same as [SportServiceAsync.retrieveBoxScore].
         */
        @MustBeClosed
        suspend fun retrieveBoxScore(
            id: String,
            params: SportRetrieveBoxScoreParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<FootballBoxScore> =
            retrieveBoxScore(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieveBoxScore */
        @MustBeClosed
        suspend fun retrieveBoxScore(
            params: SportRetrieveBoxScoreParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<FootballBoxScore>

        /**
         * Returns a raw HTTP response for `get /x/sports/{league}/games/{id}/plays`, but is
         * otherwise the same as [SportServiceAsync.retrievePlays].
         */
        @MustBeClosed
        suspend fun retrievePlays(
            id: String,
            params: SportRetrievePlaysParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<FootballPlays> =
            retrievePlays(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrievePlays */
        @MustBeClosed
        suspend fun retrievePlays(
            params: SportRetrievePlaysParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<FootballPlays>

        /**
         * Returns a raw HTTP response for `get /x/sports/{league}/games/{id}/audience`, but is
         * otherwise the same as [SportServiceAsync.retrieveAudience].
         */
        @MustBeClosed
        suspend fun retrieveAudience(
            id: String,
            params: SportRetrieveAudienceParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<SportsGameAudience> =
            retrieveAudience(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieveAudience */
        @MustBeClosed
        suspend fun retrieveAudience(
            params: SportRetrieveAudienceParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<SportsGameAudience>

        /**
         * Returns a raw HTTP response for `get /x/sports/{league}/games/{id}/roster`, but is
         * otherwise the same as [SportServiceAsync.retrieveRoster].
         */
        @MustBeClosed
        suspend fun retrieveRoster(
            id: String,
            params: SportRetrieveRosterParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<FootballRoster> =
            retrieveRoster(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieveRoster */
        @MustBeClosed
        suspend fun retrieveRoster(
            params: SportRetrieveRosterParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<FootballRoster>

        /**
         * Returns a raw HTTP response for `get /x/sports/{league}/games/{id}/player-props`, but is
         * otherwise the same as [SportServiceAsync.retrievePlayerProps].
         */
        @MustBeClosed
        suspend fun retrievePlayerProps(
            id: String,
            params: SportRetrievePlayerPropsParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<FootballProps> =
            retrievePlayerProps(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrievePlayerProps */
        @MustBeClosed
        suspend fun retrievePlayerProps(
            params: SportRetrievePlayerPropsParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<FootballProps>

        /**
         * Returns a raw HTTP response for `get /x/sports/{league}/games/{id}/posts`, but is
         * otherwise the same as [SportServiceAsync.listPosts].
         */
        @MustBeClosed
        suspend fun listPosts(
            id: String,
            params: SportListPostsParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<SportsGamePosts> =
            listPosts(params.toBuilder().id(id).build(), requestOptions)

        /** @see listPosts */
        @MustBeClosed
        suspend fun listPosts(
            params: SportListPostsParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<SportsGamePosts>
    }
}
