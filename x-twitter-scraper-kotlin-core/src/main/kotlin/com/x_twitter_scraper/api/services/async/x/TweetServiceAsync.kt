// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async.x

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponse
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.x.tweets.TweetCreateParams
import com.x_twitter_scraper.api.models.x.tweets.TweetCreateResponse
import com.x_twitter_scraper.api.models.x.tweets.TweetGetFavoritersParams
import com.x_twitter_scraper.api.models.x.tweets.TweetGetFavoritersResponse
import com.x_twitter_scraper.api.models.x.tweets.TweetGetQuotesParams
import com.x_twitter_scraper.api.models.x.tweets.TweetGetQuotesResponse
import com.x_twitter_scraper.api.models.x.tweets.TweetGetRepliesParams
import com.x_twitter_scraper.api.models.x.tweets.TweetGetRepliesResponse
import com.x_twitter_scraper.api.models.x.tweets.TweetGetRetweetersParams
import com.x_twitter_scraper.api.models.x.tweets.TweetGetRetweetersResponse
import com.x_twitter_scraper.api.models.x.tweets.TweetGetThreadParams
import com.x_twitter_scraper.api.models.x.tweets.TweetGetThreadResponse
import com.x_twitter_scraper.api.models.x.tweets.TweetListParams
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

    fun like(): LikeServiceAsync

    fun retweet(): RetweetServiceAsync

    /** Create tweet */
    suspend fun create(
        params: TweetCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): TweetCreateResponse

    /** Get multiple tweets by IDs */
    suspend fun list(
        params: TweetListParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    )

    /** Get users who liked a tweet */
    suspend fun getFavoriters(
        id: String,
        params: TweetGetFavoritersParams = TweetGetFavoritersParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): TweetGetFavoritersResponse = getFavoriters(params.toBuilder().id(id).build(), requestOptions)

    /** @see getFavoriters */
    suspend fun getFavoriters(
        params: TweetGetFavoritersParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): TweetGetFavoritersResponse

    /** @see getFavoriters */
    suspend fun getFavoriters(
        id: String,
        requestOptions: RequestOptions,
    ): TweetGetFavoritersResponse =
        getFavoriters(id, TweetGetFavoritersParams.none(), requestOptions)

    /** Get quote tweets of a tweet */
    suspend fun getQuotes(
        id: String,
        params: TweetGetQuotesParams = TweetGetQuotesParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): TweetGetQuotesResponse = getQuotes(params.toBuilder().id(id).build(), requestOptions)

    /** @see getQuotes */
    suspend fun getQuotes(
        params: TweetGetQuotesParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): TweetGetQuotesResponse

    /** @see getQuotes */
    suspend fun getQuotes(id: String, requestOptions: RequestOptions): TweetGetQuotesResponse =
        getQuotes(id, TweetGetQuotesParams.none(), requestOptions)

    /** Get replies to a tweet */
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

    /** Get users who retweeted a tweet */
    suspend fun getRetweeters(
        id: String,
        params: TweetGetRetweetersParams = TweetGetRetweetersParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): TweetGetRetweetersResponse = getRetweeters(params.toBuilder().id(id).build(), requestOptions)

    /** @see getRetweeters */
    suspend fun getRetweeters(
        params: TweetGetRetweetersParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): TweetGetRetweetersResponse

    /** @see getRetweeters */
    suspend fun getRetweeters(
        id: String,
        requestOptions: RequestOptions,
    ): TweetGetRetweetersResponse =
        getRetweeters(id, TweetGetRetweetersParams.none(), requestOptions)

    /** Get thread context for a tweet */
    suspend fun getThread(
        id: String,
        params: TweetGetThreadParams = TweetGetThreadParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): TweetGetThreadResponse = getThread(params.toBuilder().id(id).build(), requestOptions)

    /** @see getThread */
    suspend fun getThread(
        params: TweetGetThreadParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): TweetGetThreadResponse

    /** @see getThread */
    suspend fun getThread(id: String, requestOptions: RequestOptions): TweetGetThreadResponse =
        getThread(id, TweetGetThreadParams.none(), requestOptions)

    /** Search tweets */
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

        fun like(): LikeServiceAsync.WithRawResponse

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
         * Returns a raw HTTP response for `get /x/tweets`, but is otherwise the same as
         * [TweetServiceAsync.list].
         */
        @MustBeClosed
        suspend fun list(
            params: TweetListParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponse

        /**
         * Returns a raw HTTP response for `get /x/tweets/{id}/favoriters`, but is otherwise the
         * same as [TweetServiceAsync.getFavoriters].
         */
        @MustBeClosed
        suspend fun getFavoriters(
            id: String,
            params: TweetGetFavoritersParams = TweetGetFavoritersParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<TweetGetFavoritersResponse> =
            getFavoriters(params.toBuilder().id(id).build(), requestOptions)

        /** @see getFavoriters */
        @MustBeClosed
        suspend fun getFavoriters(
            params: TweetGetFavoritersParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<TweetGetFavoritersResponse>

        /** @see getFavoriters */
        @MustBeClosed
        suspend fun getFavoriters(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<TweetGetFavoritersResponse> =
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
        ): HttpResponseFor<TweetGetQuotesResponse> =
            getQuotes(params.toBuilder().id(id).build(), requestOptions)

        /** @see getQuotes */
        @MustBeClosed
        suspend fun getQuotes(
            params: TweetGetQuotesParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<TweetGetQuotesResponse>

        /** @see getQuotes */
        @MustBeClosed
        suspend fun getQuotes(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<TweetGetQuotesResponse> =
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
        ): HttpResponseFor<TweetGetRetweetersResponse> =
            getRetweeters(params.toBuilder().id(id).build(), requestOptions)

        /** @see getRetweeters */
        @MustBeClosed
        suspend fun getRetweeters(
            params: TweetGetRetweetersParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<TweetGetRetweetersResponse>

        /** @see getRetweeters */
        @MustBeClosed
        suspend fun getRetweeters(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<TweetGetRetweetersResponse> =
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
        ): HttpResponseFor<TweetGetThreadResponse> =
            getThread(params.toBuilder().id(id).build(), requestOptions)

        /** @see getThread */
        @MustBeClosed
        suspend fun getThread(
            params: TweetGetThreadParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<TweetGetThreadResponse>

        /** @see getThread */
        @MustBeClosed
        suspend fun getThread(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<TweetGetThreadResponse> =
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
