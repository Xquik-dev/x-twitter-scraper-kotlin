// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async.x.communities

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.x.communities.tweets.TweetListByCommunityPageAsync
import com.x_twitter_scraper.api.models.x.communities.tweets.TweetListByCommunityParams
import com.x_twitter_scraper.api.models.x.communities.tweets.TweetListPageAsync
import com.x_twitter_scraper.api.models.x.communities.tweets.TweetListParams

/** X data lookups (subscription required) */
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

    /** Search tweets across all communities */
    suspend fun list(
        params: TweetListParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): TweetListPageAsync

    /** Get community tweets */
    suspend fun listByCommunity(
        id: String,
        params: TweetListByCommunityParams = TweetListByCommunityParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): TweetListByCommunityPageAsync =
        listByCommunity(params.toBuilder().id(id).build(), requestOptions)

    /** @see listByCommunity */
    suspend fun listByCommunity(
        params: TweetListByCommunityParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): TweetListByCommunityPageAsync

    /** @see listByCommunity */
    suspend fun listByCommunity(
        id: String,
        requestOptions: RequestOptions,
    ): TweetListByCommunityPageAsync =
        listByCommunity(id, TweetListByCommunityParams.none(), requestOptions)

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

        /**
         * Returns a raw HTTP response for `get /x/communities/tweets`, but is otherwise the same as
         * [TweetServiceAsync.list].
         */
        @MustBeClosed
        suspend fun list(
            params: TweetListParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<TweetListPageAsync>

        /**
         * Returns a raw HTTP response for `get /x/communities/{id}/tweets`, but is otherwise the
         * same as [TweetServiceAsync.listByCommunity].
         */
        @MustBeClosed
        suspend fun listByCommunity(
            id: String,
            params: TweetListByCommunityParams = TweetListByCommunityParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<TweetListByCommunityPageAsync> =
            listByCommunity(params.toBuilder().id(id).build(), requestOptions)

        /** @see listByCommunity */
        @MustBeClosed
        suspend fun listByCommunity(
            params: TweetListByCommunityParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<TweetListByCommunityPageAsync>

        /** @see listByCommunity */
        @MustBeClosed
        suspend fun listByCommunity(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<TweetListByCommunityPageAsync> =
            listByCommunity(id, TweetListByCommunityParams.none(), requestOptions)
    }
}
