// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.x.XGetArticleParams
import com.x_twitter_scraper.api.models.x.XGetArticleResponse
import com.x_twitter_scraper.api.models.x.XGetHomeTimelineParams
import com.x_twitter_scraper.api.models.x.XGetHomeTimelineResponse
import com.x_twitter_scraper.api.models.x.XGetNotificationsParams
import com.x_twitter_scraper.api.models.x.XGetNotificationsResponse
import com.x_twitter_scraper.api.models.x.XGetTrendsParams
import com.x_twitter_scraper.api.models.x.XGetTrendsResponse
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

/** X data lookups (subscription required) */
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

    fun tweets(): TweetServiceAsync

    /** X data lookups (subscription required) */
    fun users(): UserServiceAsync

    /** X data lookups (subscription required) */
    fun followers(): FollowerServiceAsync

    fun dm(): DmServiceAsync

    /** Media upload & download */
    fun media(): MediaServiceAsync

    /** X write actions (tweets, likes, follows, DMs) */
    fun profile(): ProfileServiceAsync

    fun communities(): CommunityServiceAsync

    /** Connected X account management */
    fun accounts(): AccountServiceAsync

    /** X data lookups (subscription required) */
    fun bookmarks(): BookmarkServiceAsync

    /** X data lookups (subscription required) */
    fun lists(): ListServiceAsync

    /** Retrieve the full content of an X Article (long-form post) by tweet ID. */
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
    ): XGetHomeTimelineResponse

    /** @see getHomeTimeline */
    suspend fun getHomeTimeline(requestOptions: RequestOptions): XGetHomeTimelineResponse =
        getHomeTimeline(XGetHomeTimelineParams.none(), requestOptions)

    /** Get notifications */
    suspend fun getNotifications(
        params: XGetNotificationsParams = XGetNotificationsParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): XGetNotificationsResponse

    /** @see getNotifications */
    suspend fun getNotifications(requestOptions: RequestOptions): XGetNotificationsResponse =
        getNotifications(XGetNotificationsParams.none(), requestOptions)

    /** Get trending topics */
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

        fun tweets(): TweetServiceAsync.WithRawResponse

        /** X data lookups (subscription required) */
        fun users(): UserServiceAsync.WithRawResponse

        /** X data lookups (subscription required) */
        fun followers(): FollowerServiceAsync.WithRawResponse

        fun dm(): DmServiceAsync.WithRawResponse

        /** Media upload & download */
        fun media(): MediaServiceAsync.WithRawResponse

        /** X write actions (tweets, likes, follows, DMs) */
        fun profile(): ProfileServiceAsync.WithRawResponse

        fun communities(): CommunityServiceAsync.WithRawResponse

        /** Connected X account management */
        fun accounts(): AccountServiceAsync.WithRawResponse

        /** X data lookups (subscription required) */
        fun bookmarks(): BookmarkServiceAsync.WithRawResponse

        /** X data lookups (subscription required) */
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
        ): HttpResponseFor<XGetHomeTimelineResponse>

        /** @see getHomeTimeline */
        @MustBeClosed
        suspend fun getHomeTimeline(
            requestOptions: RequestOptions
        ): HttpResponseFor<XGetHomeTimelineResponse> =
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
