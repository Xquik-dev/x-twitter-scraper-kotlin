// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.services.async

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.PaginatedTweets
import com.x_twitter_scraper.api.models.x.XAutocompleteSearchParams
import com.x_twitter_scraper.api.models.x.XAutocompleteSearchResponse
import com.x_twitter_scraper.api.models.x.XGetArticleParams
import com.x_twitter_scraper.api.models.x.XGetArticleResponse
import com.x_twitter_scraper.api.models.x.XGetHashflagsParams
import com.x_twitter_scraper.api.models.x.XGetHashflagsResponse
import com.x_twitter_scraper.api.models.x.XGetHomeTimelineParams
import com.x_twitter_scraper.api.models.x.XGetNewsStoryVersionsParams
import com.x_twitter_scraper.api.models.x.XGetNewsStoryVersionsResponse
import com.x_twitter_scraper.api.models.x.XGetNotificationsParams
import com.x_twitter_scraper.api.models.x.XGetNotificationsResponse
import com.x_twitter_scraper.api.models.x.XGetTrendLocationsParams
import com.x_twitter_scraper.api.models.x.XGetTrendLocationsResponse
import com.x_twitter_scraper.api.models.x.XGetTrendsHistoryParams
import com.x_twitter_scraper.api.models.x.XGetTrendsHistoryResponse
import com.x_twitter_scraper.api.models.x.XGetTrendsParams
import com.x_twitter_scraper.api.models.x.XGetTrendsResponse
import com.x_twitter_scraper.api.models.x.XSearchPlacesParams
import com.x_twitter_scraper.api.models.x.XSearchPlacesResponse
import com.x_twitter_scraper.api.services.async.x.AccountConnectionAttemptServiceAsync
import com.x_twitter_scraper.api.services.async.x.AccountConnectionChallengeServiceAsync
import com.x_twitter_scraper.api.services.async.x.AccountServiceAsync
import com.x_twitter_scraper.api.services.async.x.BookmarkServiceAsync
import com.x_twitter_scraper.api.services.async.x.CommunityServiceAsync
import com.x_twitter_scraper.api.services.async.x.DmServiceAsync
import com.x_twitter_scraper.api.services.async.x.FollowerServiceAsync
import com.x_twitter_scraper.api.services.async.x.ListServiceAsync
import com.x_twitter_scraper.api.services.async.x.MediaServiceAsync
import com.x_twitter_scraper.api.services.async.x.ProfileServiceAsync
import com.x_twitter_scraper.api.services.async.x.TweetServiceAsync
import com.x_twitter_scraper.api.services.async.x.UserServiceAsync
import com.x_twitter_scraper.api.services.async.x.WriteActionServiceAsync

interface XServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: (ClientOptions.Builder) -> Unit): XServiceAsync

    /** X write actions (tweets, likes, follows, DMs) */
    fun writeActions(): WriteActionServiceAsync

    fun tweets(): TweetServiceAsync

    fun users(): UserServiceAsync

    /** Look up, search, and explore user profiles and relationships */
    fun followers(): FollowerServiceAsync

    fun dm(): DmServiceAsync

    fun media(): MediaServiceAsync

    /** X write actions (tweets, likes, follows, DMs) */
    fun profile(): ProfileServiceAsync

    fun communities(): CommunityServiceAsync

    /** Connected X account management */
    fun accounts(): AccountServiceAsync

    /** Connected X account management */
    fun accountConnectionChallenges(): AccountConnectionChallengeServiceAsync

    /** Connected X account management */
    fun accountConnectionAttempts(): AccountConnectionAttemptServiceAsync

    /** Look up, search, and analyze individual tweets */
    fun bookmarks(): BookmarkServiceAsync

    /** X List followers, members, and tweets */
    fun lists(): ListServiceAsync

    /**
     * Retrieve the full content of an X Article (long-form post) by numeric tweet ID. Returns
     * article_not_found when the tweet is valid but is not an X Article.
     */
    suspend fun getArticle(
        tweetId: String,
        params: XGetArticleParams = XGetArticleParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): XGetArticleResponse = getArticle(params.toBuilder().tweetId(tweetId).build(), requestOptions)

    /** @see getArticle */
    suspend fun getArticle(
        params: XGetArticleParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): XGetArticleResponse

    /** @see getArticle */
    suspend fun getArticle(tweetId: String, requestOptions: RequestOptions): XGetArticleResponse =
        getArticle(tweetId, XGetArticleParams.none(), requestOptions)

    /** Get home timeline */
    suspend fun getHomeTimeline(
        params: XGetHomeTimelineParams = XGetHomeTimelineParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedTweets

    /** @see getHomeTimeline */
    suspend fun getHomeTimeline(requestOptions: RequestOptions): PaginatedTweets =
        getHomeTimeline(XGetHomeTimelineParams.none(), requestOptions)

    /** Get notifications */
    suspend fun getNotifications(
        params: XGetNotificationsParams = XGetNotificationsParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): XGetNotificationsResponse

    /** @see getNotifications */
    suspend fun getNotifications(requestOptions: RequestOptions): XGetNotificationsResponse =
        getNotifications(XGetNotificationsParams.none(), requestOptions)

    /**
     * Returns X's current trends for a place you choose. Pick it by woeid, country or location
     * name. Without one, trends are worldwide. Add at to read what was trending on Twitter at a
     * past time, such as yesterday. Xquik keeps a Twitter trends archive from 2026-10-13 02:20 UTC
     * on, with 1 list per place every 5 minutes. Each request costs 1 credit, however many trends
     * it returns.
     */
    suspend fun getTrends(
        params: XGetTrendsParams = XGetTrendsParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): XGetTrendsResponse

    /** @see getTrends */
    suspend fun getTrends(requestOptions: RequestOptions): XGetTrendsResponse =
        getTrends(XGetTrendsParams.none(), requestOptions)

    /**
     * Returns the accounts, topics, hashtags & cashtags X suggests while someone types a search. X
     * may rank them differently per request. Costs 1 credit per suggestion.
     */
    suspend fun autocompleteSearch(
        params: XAutocompleteSearchParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): XAutocompleteSearchResponse

    /**
     * Returns X's hashflags, the custom emoji X shows after some hashtags. Lists the ones running
     * now unless activeOnly is false. Each returned hashflag costs 1 credit.
     */
    suspend fun getHashflags(
        params: XGetHashflagsParams = XGetHashflagsParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): XGetHashflagsResponse

    /** @see getHashflags */
    suspend fun getHashflags(requestOptions: RequestOptions): XGetHashflagsResponse =
        getHashflags(XGetHashflagsParams.none(), requestOptions)

    /**
     * Finds places on X by name. Pass a place's id as place to /x/tweets/search to read posts
     * tagged there. X ranks the places, so their order can differ between requests. Each returned
     * place costs 1 credit.
     */
    suspend fun searchPlaces(
        params: XSearchPlacesParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): XSearchPlacesResponse

    /**
     * Returns the places X offers trends for. Pass a location's woeid to /x/trends. Each returned
     * location costs 1 credit.
     */
    suspend fun getTrendLocations(
        params: XGetTrendLocationsParams = XGetTrendLocationsParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): XGetTrendLocationsResponse

    /** @see getTrendLocations */
    suspend fun getTrendLocations(requestOptions: RequestOptions): XGetTrendLocationsResponse =
        getTrendLocations(XGetTrendLocationsParams.none(), requestOptions)

    /**
     * Returns the Twitter trends archive of a place, as the lists X showed there over a range of
     * time. Use it for trending on Twitter yesterday, last week or any day since 2026-10-13 02:20
     * UTC. Xquik stores 1 list per place every 5 minutes and keeps it for 25 months. Pick the place
     * by woeid, country or location name, the range with from & to, and the step with every. from &
     * to take a time or a span back from now, such as 7d, so a schedule can read the last day on
     * every run. Each step returns the first list stored in it, with its own time. Each returned
     * list costs 1 credit, so 24 lists cost 24 credits. A step with no stored list returns none and
     * costs nothing. 1 request returns 24 lists, or up to 100 with limit.
     */
    suspend fun getTrendsHistory(
        params: XGetTrendsHistoryParams = XGetTrendsHistoryParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): XGetTrendsHistoryResponse

    /** @see getTrendsHistory */
    suspend fun getTrendsHistory(requestOptions: RequestOptions): XGetTrendsHistoryResponse =
        getTrendsHistory(XGetTrendsHistoryParams.none(), requestOptions)

    /**
     * Returns each version X wrote of 1 news story, the AI trends x.com shows under Explore & News.
     * Each version has its time, headline & text, oldest first, so you can see how the story
     * changed. X lists every version at once. Each request costs 1 credit.
     */
    suspend fun getNewsStoryVersions(
        id: String,
        params: XGetNewsStoryVersionsParams = XGetNewsStoryVersionsParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): XGetNewsStoryVersionsResponse =
        getNewsStoryVersions(params.toBuilder().id(id).build(), requestOptions)

    /** @see getNewsStoryVersions */
    suspend fun getNewsStoryVersions(
        params: XGetNewsStoryVersionsParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): XGetNewsStoryVersionsResponse

    /** @see getNewsStoryVersions */
    suspend fun getNewsStoryVersions(
        id: String,
        requestOptions: RequestOptions,
    ): XGetNewsStoryVersionsResponse =
        getNewsStoryVersions(id, XGetNewsStoryVersionsParams.none(), requestOptions)

    /** A view of [XServiceAsync] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: (ClientOptions.Builder) -> Unit): XServiceAsync.WithRawResponse

        /** X write actions (tweets, likes, follows, DMs) */
        fun writeActions(): WriteActionServiceAsync.WithRawResponse

        fun tweets(): TweetServiceAsync.WithRawResponse

        fun users(): UserServiceAsync.WithRawResponse

        /** Look up, search, and explore user profiles and relationships */
        fun followers(): FollowerServiceAsync.WithRawResponse

        fun dm(): DmServiceAsync.WithRawResponse

        fun media(): MediaServiceAsync.WithRawResponse

        /** X write actions (tweets, likes, follows, DMs) */
        fun profile(): ProfileServiceAsync.WithRawResponse

        fun communities(): CommunityServiceAsync.WithRawResponse

        /** Connected X account management */
        fun accounts(): AccountServiceAsync.WithRawResponse

        /** Connected X account management */
        fun accountConnectionChallenges(): AccountConnectionChallengeServiceAsync.WithRawResponse

        /** Connected X account management */
        fun accountConnectionAttempts(): AccountConnectionAttemptServiceAsync.WithRawResponse

        /** Look up, search, and analyze individual tweets */
        fun bookmarks(): BookmarkServiceAsync.WithRawResponse

        /** X List followers, members, and tweets */
        fun lists(): ListServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /x/articles/{tweetId}`, but is otherwise the same as
         * [XServiceAsync.getArticle].
         */
        @MustBeClosed
        suspend fun getArticle(
            tweetId: String,
            params: XGetArticleParams = XGetArticleParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<XGetArticleResponse> =
            getArticle(params.toBuilder().tweetId(tweetId).build(), requestOptions)

        /** @see getArticle */
        @MustBeClosed
        suspend fun getArticle(
            params: XGetArticleParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<XGetArticleResponse>

        /** @see getArticle */
        @MustBeClosed
        suspend fun getArticle(
            tweetId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<XGetArticleResponse> =
            getArticle(tweetId, XGetArticleParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/timeline`, but is otherwise the same as
         * [XServiceAsync.getHomeTimeline].
         */
        @MustBeClosed
        suspend fun getHomeTimeline(
            params: XGetHomeTimelineParams = XGetHomeTimelineParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedTweets>

        /** @see getHomeTimeline */
        @MustBeClosed
        suspend fun getHomeTimeline(
            requestOptions: RequestOptions
        ): HttpResponseFor<PaginatedTweets> =
            getHomeTimeline(XGetHomeTimelineParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/notifications`, but is otherwise the same as
         * [XServiceAsync.getNotifications].
         */
        @MustBeClosed
        suspend fun getNotifications(
            params: XGetNotificationsParams = XGetNotificationsParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<XGetNotificationsResponse>

        /** @see getNotifications */
        @MustBeClosed
        suspend fun getNotifications(
            requestOptions: RequestOptions
        ): HttpResponseFor<XGetNotificationsResponse> =
            getNotifications(XGetNotificationsParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/trends`, but is otherwise the same as
         * [XServiceAsync.getTrends].
         */
        @MustBeClosed
        suspend fun getTrends(
            params: XGetTrendsParams = XGetTrendsParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<XGetTrendsResponse>

        /** @see getTrends */
        @MustBeClosed
        suspend fun getTrends(requestOptions: RequestOptions): HttpResponseFor<XGetTrendsResponse> =
            getTrends(XGetTrendsParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/search/autocomplete`, but is otherwise the same
         * as [XServiceAsync.autocompleteSearch].
         */
        @MustBeClosed
        suspend fun autocompleteSearch(
            params: XAutocompleteSearchParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<XAutocompleteSearchResponse>

        /**
         * Returns a raw HTTP response for `get /x/hashflags`, but is otherwise the same as
         * [XServiceAsync.getHashflags].
         */
        @MustBeClosed
        suspend fun getHashflags(
            params: XGetHashflagsParams = XGetHashflagsParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<XGetHashflagsResponse>

        /** @see getHashflags */
        @MustBeClosed
        suspend fun getHashflags(
            requestOptions: RequestOptions
        ): HttpResponseFor<XGetHashflagsResponse> =
            getHashflags(XGetHashflagsParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/places/search`, but is otherwise the same as
         * [XServiceAsync.searchPlaces].
         */
        @MustBeClosed
        suspend fun searchPlaces(
            params: XSearchPlacesParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<XSearchPlacesResponse>

        /**
         * Returns a raw HTTP response for `get /x/trends/locations`, but is otherwise the same as
         * [XServiceAsync.getTrendLocations].
         */
        @MustBeClosed
        suspend fun getTrendLocations(
            params: XGetTrendLocationsParams = XGetTrendLocationsParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<XGetTrendLocationsResponse>

        /** @see getTrendLocations */
        @MustBeClosed
        suspend fun getTrendLocations(
            requestOptions: RequestOptions
        ): HttpResponseFor<XGetTrendLocationsResponse> =
            getTrendLocations(XGetTrendLocationsParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/trends/history`, but is otherwise the same as
         * [XServiceAsync.getTrendsHistory].
         */
        @MustBeClosed
        suspend fun getTrendsHistory(
            params: XGetTrendsHistoryParams = XGetTrendsHistoryParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<XGetTrendsHistoryResponse>

        /** @see getTrendsHistory */
        @MustBeClosed
        suspend fun getTrendsHistory(
            requestOptions: RequestOptions
        ): HttpResponseFor<XGetTrendsHistoryResponse> =
            getTrendsHistory(XGetTrendsHistoryParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/news/{id}/versions`, but is otherwise the same as
         * [XServiceAsync.getNewsStoryVersions].
         */
        @MustBeClosed
        suspend fun getNewsStoryVersions(
            id: String,
            params: XGetNewsStoryVersionsParams = XGetNewsStoryVersionsParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<XGetNewsStoryVersionsResponse> =
            getNewsStoryVersions(params.toBuilder().id(id).build(), requestOptions)

        /** @see getNewsStoryVersions */
        @MustBeClosed
        suspend fun getNewsStoryVersions(
            params: XGetNewsStoryVersionsParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<XGetNewsStoryVersionsResponse>

        /** @see getNewsStoryVersions */
        @MustBeClosed
        suspend fun getNewsStoryVersions(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<XGetNewsStoryVersionsResponse> =
            getNewsStoryVersions(id, XGetNewsStoryVersionsParams.none(), requestOptions)
    }
}
