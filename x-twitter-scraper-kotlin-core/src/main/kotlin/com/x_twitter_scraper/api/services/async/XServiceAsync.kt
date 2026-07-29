// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.PaginatedTweets
import com.x_twitter_scraper.api.models.x.XGetArticleParams
import com.x_twitter_scraper.api.models.x.XGetArticleResponse
import com.x_twitter_scraper.api.models.x.XGetHomeTimelineParams
import com.x_twitter_scraper.api.models.x.XGetNotificationsParams
import com.x_twitter_scraper.api.models.x.XGetNotificationsResponse
import com.x_twitter_scraper.api.models.x.XGetTrendsParams
import com.x_twitter_scraper.api.models.x.XGetTrendsResponse
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

    /** Get trending hashtags and topics from X by region */
    suspend fun getTrends(
        params: XGetTrendsParams = XGetTrendsParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): XGetTrendsResponse

    /** @see getTrends */
    suspend fun getTrends(requestOptions: RequestOptions): XGetTrendsResponse =
        getTrends(XGetTrendsParams.none(), requestOptions)

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
    }
}
