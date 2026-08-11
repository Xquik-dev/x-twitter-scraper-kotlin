// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async.x

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.PaginatedTweets
import com.x_twitter_scraper.api.models.PaginatedUsers
import com.x_twitter_scraper.api.models.x.tweets.TweetCreateParams
import com.x_twitter_scraper.api.models.x.tweets.TweetCreateResponse
import com.x_twitter_scraper.api.models.x.tweets.TweetDeleteParams
import com.x_twitter_scraper.api.models.x.tweets.TweetDeleteResponse
import com.x_twitter_scraper.api.models.x.tweets.TweetGetFavoritersParams
import com.x_twitter_scraper.api.models.x.tweets.TweetGetQuotesParams
import com.x_twitter_scraper.api.models.x.tweets.TweetGetRepliesParams
import com.x_twitter_scraper.api.models.x.tweets.TweetGetRepliesResponse
import com.x_twitter_scraper.api.models.x.tweets.TweetGetRetweetersParams
import com.x_twitter_scraper.api.models.x.tweets.TweetGetThreadParams
import com.x_twitter_scraper.api.models.x.tweets.TweetListParams
import com.x_twitter_scraper.api.models.x.tweets.TweetRetrieveParams
import com.x_twitter_scraper.api.models.x.tweets.TweetRetrieveResponse
import com.x_twitter_scraper.api.models.x.tweets.TweetSearchParams
import com.x_twitter_scraper.api.models.x.tweets.TweetSearchResponse
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

    /** No-mode search maximizes coverage. */
    suspend fun search(
        params: TweetSearchParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): TweetSearchResponse

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
    }
}
