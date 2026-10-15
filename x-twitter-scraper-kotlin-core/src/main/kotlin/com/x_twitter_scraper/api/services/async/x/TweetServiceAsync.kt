// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.services.async.x

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.PaginatedTweets
import com.x_twitter_scraper.api.models.PaginatedUsers
import com.x_twitter_scraper.api.models.x.tweets.TweetCheckRepostParams
import com.x_twitter_scraper.api.models.x.tweets.TweetCheckRepostResponse
import com.x_twitter_scraper.api.models.x.tweets.TweetCreateParams
import com.x_twitter_scraper.api.models.x.tweets.TweetCreateResponse
import com.x_twitter_scraper.api.models.x.tweets.TweetDeleteParams
import com.x_twitter_scraper.api.models.x.tweets.TweetDeleteResponse
import com.x_twitter_scraper.api.models.x.tweets.TweetEmbedParams
import com.x_twitter_scraper.api.models.x.tweets.TweetEmbedResponse
import com.x_twitter_scraper.api.models.x.tweets.TweetGetFavoritersParams
import com.x_twitter_scraper.api.models.x.tweets.TweetGetHiddenRepliesParams
import com.x_twitter_scraper.api.models.x.tweets.TweetGetInsightsParams
import com.x_twitter_scraper.api.models.x.tweets.TweetGetInsightsResponse
import com.x_twitter_scraper.api.models.x.tweets.TweetGetQuotesParams
import com.x_twitter_scraper.api.models.x.tweets.TweetGetRepliesParams
import com.x_twitter_scraper.api.models.x.tweets.TweetGetRepliesResponse
import com.x_twitter_scraper.api.models.x.tweets.TweetGetRetweeterIdsParams
import com.x_twitter_scraper.api.models.x.tweets.TweetGetRetweeterIdsResponse
import com.x_twitter_scraper.api.models.x.tweets.TweetGetRetweetersParams
import com.x_twitter_scraper.api.models.x.tweets.TweetGetSubtitlesParams
import com.x_twitter_scraper.api.models.x.tweets.TweetGetSubtitlesResponse
import com.x_twitter_scraper.api.models.x.tweets.TweetGetThreadParams
import com.x_twitter_scraper.api.models.x.tweets.TweetListParams
import com.x_twitter_scraper.api.models.x.tweets.TweetListTopParams
import com.x_twitter_scraper.api.models.x.tweets.TweetResolveLinksParams
import com.x_twitter_scraper.api.models.x.tweets.TweetResolveLinksResponse
import com.x_twitter_scraper.api.models.x.tweets.TweetRetrieveParams
import com.x_twitter_scraper.api.models.x.tweets.TweetRetrieveResponse
import com.x_twitter_scraper.api.models.x.tweets.TweetSearchParams
import com.x_twitter_scraper.api.models.x.tweets.TweetSearchResponse
import com.x_twitter_scraper.api.models.x.tweets.TweetTranslateParams
import com.x_twitter_scraper.api.models.x.tweets.TweetTranslateResponse
import com.x_twitter_scraper.api.services.async.x.tweets.LikeServiceAsync
import com.x_twitter_scraper.api.services.async.x.tweets.RetweetServiceAsync

interface TweetServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: (ClientOptions.Builder) -> Unit): TweetServiceAsync

    /** X write actions (tweets, likes, follows, DMs) */
    fun like(): LikeServiceAsync

    /** X write actions (tweets, likes, follows, DMs) */
    fun retweet(): RetweetServiceAsync

    /** Create tweet */
    suspend fun create(
        params: TweetCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): TweetCreateResponse

    /** Get tweet with full text, author, metrics and media */
    suspend fun retrieve(
        id: String,
        params: TweetRetrieveParams = TweetRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): TweetRetrieveResponse = retrieve(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieve */
    suspend fun retrieve(
        params: TweetRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): TweetRetrieveResponse

    /** @see retrieve */
    suspend fun retrieve(id: String, requestOptions: RequestOptions): TweetRetrieveResponse =
        retrieve(id, TweetRetrieveParams.none(), requestOptions)

    /** Get multiple tweets by IDs */
    suspend fun list(
        params: TweetListParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedTweets

    /** Delete tweet */
    suspend fun delete(
        id: String,
        params: TweetDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): TweetDeleteResponse = delete(params.toBuilder().id(id).build(), requestOptions)

    /** @see delete */
    suspend fun delete(
        params: TweetDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): TweetDeleteResponse

    /**
     * Returns liker profiles that X makes visible for the post. X can withhold liker identities
     * even when the post reports likes. In that case this endpoint returns 424
     * `favoriters_unavailable` instead of a misleading empty success.
     */
    suspend fun getFavoriters(
        id: String,
        params: TweetGetFavoritersParams = TweetGetFavoritersParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedUsers = getFavoriters(params.toBuilder().id(id).build(), requestOptions)

    /** @see getFavoriters */
    suspend fun getFavoriters(
        params: TweetGetFavoritersParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedUsers

    /** @see getFavoriters */
    suspend fun getFavoriters(id: String, requestOptions: RequestOptions): PaginatedUsers =
        getFavoriters(id, TweetGetFavoritersParams.none(), requestOptions)

    /** List quote tweets of a tweet */
    suspend fun getQuotes(
        id: String,
        params: TweetGetQuotesParams = TweetGetQuotesParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedTweets = getQuotes(params.toBuilder().id(id).build(), requestOptions)

    /** @see getQuotes */
    suspend fun getQuotes(
        params: TweetGetQuotesParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedTweets

    /** @see getQuotes */
    suspend fun getQuotes(id: String, requestOptions: RequestOptions): PaginatedTweets =
        getQuotes(id, TweetGetQuotesParams.none(), requestOptions)

    /**
     * Returns direct replies. Omit mode for automatic maximum coverage with resumable pagination.
     * Complete mode returns nested replies, diagnostics, and 424 when direct coverage stays below
     * 80%.
     */
    suspend fun getReplies(
        id: String,
        params: TweetGetRepliesParams = TweetGetRepliesParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): TweetGetRepliesResponse = getReplies(params.toBuilder().id(id).build(), requestOptions)

    /** @see getReplies */
    suspend fun getReplies(
        params: TweetGetRepliesParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): TweetGetRepliesResponse

    /** @see getReplies */
    suspend fun getReplies(id: String, requestOptions: RequestOptions): TweetGetRepliesResponse =
        getReplies(id, TweetGetRepliesParams.none(), requestOptions)

    /** List users who retweeted a tweet */
    suspend fun getRetweeters(
        id: String,
        params: TweetGetRetweetersParams = TweetGetRetweetersParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedUsers = getRetweeters(params.toBuilder().id(id).build(), requestOptions)

    /** @see getRetweeters */
    suspend fun getRetweeters(
        params: TweetGetRetweetersParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedUsers

    /** @see getRetweeters */
    suspend fun getRetweeters(id: String, requestOptions: RequestOptions): PaginatedUsers =
        getRetweeters(id, TweetGetRetweetersParams.none(), requestOptions)

    /** Get full conversation thread for a tweet */
    suspend fun getThread(
        id: String,
        params: TweetGetThreadParams = TweetGetThreadParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedTweets = getThread(params.toBuilder().id(id).build(), requestOptions)

    /** @see getThread */
    suspend fun getThread(
        params: TweetGetThreadParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedTweets

    /** @see getThread */
    suspend fun getThread(id: String, requestOptions: RequestOptions): PaginatedTweets =
        getThread(id, TweetGetThreadParams.none(), requestOptions)

    /**
     * No-mode search maximizes coverage. New cursorless `Latest` sessions return rows newest-first
     * across cursor pages. Existing cursors preserve their established ordering.
     */
    suspend fun search(
        params: TweetSearchParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): TweetSearchResponse

    /**
     * Returns the tweets X ranks highest by 1 metric over the last day, week or month, as x.com's
     * Inspiration page shows them. Covers all of X unless you pick a country or a language. Pages
     * hold up to 20 tweets, and the list ends at 100. Each tweet costs 1 credit.
     */
    suspend fun listTop(
        params: TweetListTopParams = TweetListTopParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedTweets

    /** @see listTop */
    suspend fun listTop(requestOptions: RequestOptions): PaginatedTweets =
        listTop(TweetListTopParams.none(), requestOptions)

    /**
     * Returns the HTML that X gives websites to embed 1 public tweet, with the tweet's link and
     * author.
     */
    suspend fun embed(
        id: String,
        params: TweetEmbedParams = TweetEmbedParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): TweetEmbedResponse = embed(params.toBuilder().id(id).build(), requestOptions)

    /** @see embed */
    suspend fun embed(
        params: TweetEmbedParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): TweetEmbedResponse

    /** @see embed */
    suspend fun embed(id: String, requestOptions: RequestOptions): TweetEmbedResponse =
        embed(id, TweetEmbedParams.none(), requestOptions)

    /**
     * Returns the replies the post's author hid, oldest first, with the replies to them. A post
     * without hidden replies returns an empty list.
     */
    suspend fun getHiddenReplies(
        id: String,
        params: TweetGetHiddenRepliesParams = TweetGetHiddenRepliesParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedTweets = getHiddenReplies(params.toBuilder().id(id).build(), requestOptions)

    /** @see getHiddenReplies */
    suspend fun getHiddenReplies(
        params: TweetGetHiddenRepliesParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedTweets

    /** @see getHiddenReplies */
    suspend fun getHiddenReplies(id: String, requestOptions: RequestOptions): PaginatedTweets =
        getHiddenReplies(id, TweetGetHiddenRepliesParams.none(), requestOptions)

    /**
     * Returns engagement insights for 1 tweet. They are engagements per hour since it went up, per
     * view and per follower of its author, and how far it spread. Each report costs 1 credit,
     * whatever it reads.
     */
    suspend fun getInsights(
        id: String,
        params: TweetGetInsightsParams = TweetGetInsightsParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): TweetGetInsightsResponse = getInsights(params.toBuilder().id(id).build(), requestOptions)

    /** @see getInsights */
    suspend fun getInsights(
        params: TweetGetInsightsParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): TweetGetInsightsResponse

    /** @see getInsights */
    suspend fun getInsights(id: String, requestOptions: RequestOptions): TweetGetInsightsResponse =
        getInsights(id, TweetGetInsightsParams.none(), requestOptions)

    /**
     * Returns the user IDs of a post's reposters, in the order & pages of
     * /x/tweets/{id}/retweeters. Use it when you need IDs only. Each ID costs 1 credit. A post
     * nobody reposted returns an empty list for free.
     */
    suspend fun getRetweeterIds(
        id: String,
        params: TweetGetRetweeterIdsParams = TweetGetRetweeterIdsParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): TweetGetRetweeterIdsResponse =
        getRetweeterIds(params.toBuilder().id(id).build(), requestOptions)

    /** @see getRetweeterIds */
    suspend fun getRetweeterIds(
        params: TweetGetRetweeterIdsParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): TweetGetRetweeterIdsResponse

    /** @see getRetweeterIds */
    suspend fun getRetweeterIds(
        id: String,
        requestOptions: RequestOptions,
    ): TweetGetRetweeterIdsResponse =
        getRetweeterIds(id, TweetGetRetweeterIdsParams.none(), requestOptions)

    /**
     * Returns whether 1 account reposted the post, read from its newest posts. A repost older than
     * the posts read returns false with complete false. Each check costs 1 credit.
     */
    suspend fun checkRepost(
        id: String,
        params: TweetCheckRepostParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): TweetCheckRepostResponse = checkRepost(params.toBuilder().id(id).build(), requestOptions)

    /** @see checkRepost */
    suspend fun checkRepost(
        params: TweetCheckRepostParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): TweetCheckRepostResponse

    /**
     * Returns the caption tracks of 1 public tweet's videos, as X's player reads them. Each track
     * has its language, name, timed cues, and full text. A tweet without video, or a video without
     * captions, returns no track and costs nothing.
     */
    suspend fun getSubtitles(
        id: String,
        params: TweetGetSubtitlesParams = TweetGetSubtitlesParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): TweetGetSubtitlesResponse = getSubtitles(params.toBuilder().id(id).build(), requestOptions)

    /** @see getSubtitles */
    suspend fun getSubtitles(
        params: TweetGetSubtitlesParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): TweetGetSubtitlesResponse

    /** @see getSubtitles */
    suspend fun getSubtitles(
        id: String,
        requestOptions: RequestOptions,
    ): TweetGetSubtitlesResponse = getSubtitles(id, TweetGetSubtitlesParams.none(), requestOptions)

    /**
     * Returns X's own translation of 1 public tweet into the language you pick, with the links,
     * mentions, and tags X places in the translated text.
     */
    suspend fun translate(
        id: String,
        params: TweetTranslateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): TweetTranslateResponse = translate(params.toBuilder().id(id).build(), requestOptions)

    /** @see translate */
    suspend fun translate(
        params: TweetTranslateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): TweetTranslateResponse

    /**
     * Returns where each t.co link leads, as X's own redirect says. Xquik reads the redirect and
     * never visits the target site. A link t.co does not have comes back without `resolvedUrl` and
     * costs nothing. A link X does not answer for in time comes back in `unprocessedUrls`, free.
     * Each resolved link costs 1 credit.
     */
    suspend fun resolveLinks(
        params: TweetResolveLinksParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): TweetResolveLinksResponse

    /** A view of [TweetServiceAsync] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: (ClientOptions.Builder) -> Unit
        ): TweetServiceAsync.WithRawResponse

        /** X write actions (tweets, likes, follows, DMs) */
        fun like(): LikeServiceAsync.WithRawResponse

        /** X write actions (tweets, likes, follows, DMs) */
        fun retweet(): RetweetServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /x/tweets`, but is otherwise the same as
         * [TweetServiceAsync.create].
         */
        @MustBeClosed
        suspend fun create(
            params: TweetCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<TweetCreateResponse>

        /**
         * Returns a raw HTTP response for `get /x/tweets/{id}`, but is otherwise the same as
         * [TweetServiceAsync.retrieve].
         */
        @MustBeClosed
        suspend fun retrieve(
            id: String,
            params: TweetRetrieveParams = TweetRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<TweetRetrieveResponse> =
            retrieve(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        suspend fun retrieve(
            params: TweetRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<TweetRetrieveResponse>

        /** @see retrieve */
        @MustBeClosed
        suspend fun retrieve(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<TweetRetrieveResponse> =
            retrieve(id, TweetRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/tweets`, but is otherwise the same as
         * [TweetServiceAsync.list].
         */
        @MustBeClosed
        suspend fun list(
            params: TweetListParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedTweets>

        /**
         * Returns a raw HTTP response for `delete /x/tweets/{id}`, but is otherwise the same as
         * [TweetServiceAsync.delete].
         */
        @MustBeClosed
        suspend fun delete(
            id: String,
            params: TweetDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<TweetDeleteResponse> =
            delete(params.toBuilder().id(id).build(), requestOptions)

        /** @see delete */
        @MustBeClosed
        suspend fun delete(
            params: TweetDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<TweetDeleteResponse>

        /**
         * Returns a raw HTTP response for `get /x/tweets/{id}/favoriters`, but is otherwise the
         * same as [TweetServiceAsync.getFavoriters].
         */
        @MustBeClosed
        suspend fun getFavoriters(
            id: String,
            params: TweetGetFavoritersParams = TweetGetFavoritersParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedUsers> =
            getFavoriters(params.toBuilder().id(id).build(), requestOptions)

        /** @see getFavoriters */
        @MustBeClosed
        suspend fun getFavoriters(
            params: TweetGetFavoritersParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedUsers>

        /** @see getFavoriters */
        @MustBeClosed
        suspend fun getFavoriters(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<PaginatedUsers> =
            getFavoriters(id, TweetGetFavoritersParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/tweets/{id}/quotes`, but is otherwise the same as
         * [TweetServiceAsync.getQuotes].
         */
        @MustBeClosed
        suspend fun getQuotes(
            id: String,
            params: TweetGetQuotesParams = TweetGetQuotesParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedTweets> =
            getQuotes(params.toBuilder().id(id).build(), requestOptions)

        /** @see getQuotes */
        @MustBeClosed
        suspend fun getQuotes(
            params: TweetGetQuotesParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedTweets>

        /** @see getQuotes */
        @MustBeClosed
        suspend fun getQuotes(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<PaginatedTweets> =
            getQuotes(id, TweetGetQuotesParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/tweets/{id}/replies`, but is otherwise the same
         * as [TweetServiceAsync.getReplies].
         */
        @MustBeClosed
        suspend fun getReplies(
            id: String,
            params: TweetGetRepliesParams = TweetGetRepliesParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<TweetGetRepliesResponse> =
            getReplies(params.toBuilder().id(id).build(), requestOptions)

        /** @see getReplies */
        @MustBeClosed
        suspend fun getReplies(
            params: TweetGetRepliesParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<TweetGetRepliesResponse>

        /** @see getReplies */
        @MustBeClosed
        suspend fun getReplies(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<TweetGetRepliesResponse> =
            getReplies(id, TweetGetRepliesParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/tweets/{id}/retweeters`, but is otherwise the
         * same as [TweetServiceAsync.getRetweeters].
         */
        @MustBeClosed
        suspend fun getRetweeters(
            id: String,
            params: TweetGetRetweetersParams = TweetGetRetweetersParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedUsers> =
            getRetweeters(params.toBuilder().id(id).build(), requestOptions)

        /** @see getRetweeters */
        @MustBeClosed
        suspend fun getRetweeters(
            params: TweetGetRetweetersParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedUsers>

        /** @see getRetweeters */
        @MustBeClosed
        suspend fun getRetweeters(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<PaginatedUsers> =
            getRetweeters(id, TweetGetRetweetersParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/tweets/{id}/thread`, but is otherwise the same as
         * [TweetServiceAsync.getThread].
         */
        @MustBeClosed
        suspend fun getThread(
            id: String,
            params: TweetGetThreadParams = TweetGetThreadParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedTweets> =
            getThread(params.toBuilder().id(id).build(), requestOptions)

        /** @see getThread */
        @MustBeClosed
        suspend fun getThread(
            params: TweetGetThreadParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedTweets>

        /** @see getThread */
        @MustBeClosed
        suspend fun getThread(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<PaginatedTweets> =
            getThread(id, TweetGetThreadParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/tweets/search`, but is otherwise the same as
         * [TweetServiceAsync.search].
         */
        @MustBeClosed
        suspend fun search(
            params: TweetSearchParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<TweetSearchResponse>

        /**
         * Returns a raw HTTP response for `get /x/tweets/top`, but is otherwise the same as
         * [TweetServiceAsync.listTop].
         */
        @MustBeClosed
        suspend fun listTop(
            params: TweetListTopParams = TweetListTopParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedTweets>

        /** @see listTop */
        @MustBeClosed
        suspend fun listTop(requestOptions: RequestOptions): HttpResponseFor<PaginatedTweets> =
            listTop(TweetListTopParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/tweets/{id}/embed`, but is otherwise the same as
         * [TweetServiceAsync.embed].
         */
        @MustBeClosed
        suspend fun embed(
            id: String,
            params: TweetEmbedParams = TweetEmbedParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<TweetEmbedResponse> =
            embed(params.toBuilder().id(id).build(), requestOptions)

        /** @see embed */
        @MustBeClosed
        suspend fun embed(
            params: TweetEmbedParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<TweetEmbedResponse>

        /** @see embed */
        @MustBeClosed
        suspend fun embed(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<TweetEmbedResponse> = embed(id, TweetEmbedParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/tweets/{id}/hidden-replies`, but is otherwise the
         * same as [TweetServiceAsync.getHiddenReplies].
         */
        @MustBeClosed
        suspend fun getHiddenReplies(
            id: String,
            params: TweetGetHiddenRepliesParams = TweetGetHiddenRepliesParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedTweets> =
            getHiddenReplies(params.toBuilder().id(id).build(), requestOptions)

        /** @see getHiddenReplies */
        @MustBeClosed
        suspend fun getHiddenReplies(
            params: TweetGetHiddenRepliesParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedTweets>

        /** @see getHiddenReplies */
        @MustBeClosed
        suspend fun getHiddenReplies(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<PaginatedTweets> =
            getHiddenReplies(id, TweetGetHiddenRepliesParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/tweets/{id}/insights`, but is otherwise the same
         * as [TweetServiceAsync.getInsights].
         */
        @MustBeClosed
        suspend fun getInsights(
            id: String,
            params: TweetGetInsightsParams = TweetGetInsightsParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<TweetGetInsightsResponse> =
            getInsights(params.toBuilder().id(id).build(), requestOptions)

        /** @see getInsights */
        @MustBeClosed
        suspend fun getInsights(
            params: TweetGetInsightsParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<TweetGetInsightsResponse>

        /** @see getInsights */
        @MustBeClosed
        suspend fun getInsights(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<TweetGetInsightsResponse> =
            getInsights(id, TweetGetInsightsParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/tweets/{id}/retweeter-ids`, but is otherwise the
         * same as [TweetServiceAsync.getRetweeterIds].
         */
        @MustBeClosed
        suspend fun getRetweeterIds(
            id: String,
            params: TweetGetRetweeterIdsParams = TweetGetRetweeterIdsParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<TweetGetRetweeterIdsResponse> =
            getRetweeterIds(params.toBuilder().id(id).build(), requestOptions)

        /** @see getRetweeterIds */
        @MustBeClosed
        suspend fun getRetweeterIds(
            params: TweetGetRetweeterIdsParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<TweetGetRetweeterIdsResponse>

        /** @see getRetweeterIds */
        @MustBeClosed
        suspend fun getRetweeterIds(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<TweetGetRetweeterIdsResponse> =
            getRetweeterIds(id, TweetGetRetweeterIdsParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/tweets/{id}/retweeters/check`, but is otherwise
         * the same as [TweetServiceAsync.checkRepost].
         */
        @MustBeClosed
        suspend fun checkRepost(
            id: String,
            params: TweetCheckRepostParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<TweetCheckRepostResponse> =
            checkRepost(params.toBuilder().id(id).build(), requestOptions)

        /** @see checkRepost */
        @MustBeClosed
        suspend fun checkRepost(
            params: TweetCheckRepostParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<TweetCheckRepostResponse>

        /**
         * Returns a raw HTTP response for `get /x/tweets/{id}/subtitles`, but is otherwise the same
         * as [TweetServiceAsync.getSubtitles].
         */
        @MustBeClosed
        suspend fun getSubtitles(
            id: String,
            params: TweetGetSubtitlesParams = TweetGetSubtitlesParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<TweetGetSubtitlesResponse> =
            getSubtitles(params.toBuilder().id(id).build(), requestOptions)

        /** @see getSubtitles */
        @MustBeClosed
        suspend fun getSubtitles(
            params: TweetGetSubtitlesParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<TweetGetSubtitlesResponse>

        /** @see getSubtitles */
        @MustBeClosed
        suspend fun getSubtitles(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<TweetGetSubtitlesResponse> =
            getSubtitles(id, TweetGetSubtitlesParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/tweets/{id}/translation`, but is otherwise the
         * same as [TweetServiceAsync.translate].
         */
        @MustBeClosed
        suspend fun translate(
            id: String,
            params: TweetTranslateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<TweetTranslateResponse> =
            translate(params.toBuilder().id(id).build(), requestOptions)

        /** @see translate */
        @MustBeClosed
        suspend fun translate(
            params: TweetTranslateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<TweetTranslateResponse>

        /**
         * Returns a raw HTTP response for `get /x/links/resolve`, but is otherwise the same as
         * [TweetServiceAsync.resolveLinks].
         */
        @MustBeClosed
        suspend fun resolveLinks(
            params: TweetResolveLinksParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<TweetResolveLinksResponse>
    }
}
