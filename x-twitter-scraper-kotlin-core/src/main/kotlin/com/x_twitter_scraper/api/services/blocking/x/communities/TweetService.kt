// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.blocking.x.communities

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.PaginatedTweets
import com.x_twitter_scraper.api.models.x.communities.tweets.TweetListByCommunityParams
import com.x_twitter_scraper.api.models.x.communities.tweets.TweetListParams

/** X Community info, members, and tweets */
interface TweetService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: (ClientOptions.Builder) -> Unit): TweetService

    /** List tweets across all communities */
    fun list(
        params: TweetListParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedTweets

    /** List tweets posted in a community */
    fun listByCommunity(
        id: String,
        params: TweetListByCommunityParams = TweetListByCommunityParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedTweets = listByCommunity(params.toBuilder().id(id).build(), requestOptions)

    /** @see listByCommunity */
    fun listByCommunity(
        params: TweetListByCommunityParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedTweets

    /** @see listByCommunity */
    fun listByCommunity(id: String, requestOptions: RequestOptions): PaginatedTweets =
        listByCommunity(id, TweetListByCommunityParams.none(), requestOptions)

    /** A view of [TweetService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: (ClientOptions.Builder) -> Unit): TweetService.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /x/communities/tweets`, but is otherwise the same as
         * [TweetService.list].
         */
        @MustBeClosed
        fun list(
            params: TweetListParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedTweets>

        /**
         * Returns a raw HTTP response for `get /x/communities/{id}/tweets`, but is otherwise the
         * same as [TweetService.listByCommunity].
         */
        @MustBeClosed
        fun listByCommunity(
            id: String,
            params: TweetListByCommunityParams = TweetListByCommunityParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedTweets> =
            listByCommunity(params.toBuilder().id(id).build(), requestOptions)

        /** @see listByCommunity */
        @MustBeClosed
        fun listByCommunity(
            params: TweetListByCommunityParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedTweets>

        /** @see listByCommunity */
        @MustBeClosed
        fun listByCommunity(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<PaginatedTweets> =
            listByCommunity(id, TweetListByCommunityParams.none(), requestOptions)
    }
}
