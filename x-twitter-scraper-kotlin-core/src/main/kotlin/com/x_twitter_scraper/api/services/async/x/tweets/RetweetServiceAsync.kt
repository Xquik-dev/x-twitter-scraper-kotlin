// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async.x.tweets

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.x.tweets.retweet.RetweetCreateParams
import com.x_twitter_scraper.api.models.x.tweets.retweet.RetweetCreateResponse
import com.x_twitter_scraper.api.models.x.tweets.retweet.RetweetDeleteParams
import com.x_twitter_scraper.api.models.x.tweets.retweet.RetweetDeleteResponse

/** X write actions (tweets, likes, follows, DMs) */
interface RetweetServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: (ClientOptions.Builder) -> Unit): RetweetServiceAsync

    /** Retweet */
    suspend fun create(
        id: String,
        params: RetweetCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): RetweetCreateResponse = create(params.toBuilder().id(id).build(), requestOptions)

    /** @see create */
    suspend fun create(
        params: RetweetCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): RetweetCreateResponse

    /** Unretweet */
    suspend fun delete(
        id: String,
        params: RetweetDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): RetweetDeleteResponse = delete(params.toBuilder().id(id).build(), requestOptions)

    /** @see delete */
    suspend fun delete(
        params: RetweetDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): RetweetDeleteResponse

    /**
     * A view of [RetweetServiceAsync] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: (ClientOptions.Builder) -> Unit
        ): RetweetServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /x/tweets/{id}/retweet`, but is otherwise the same
         * as [RetweetServiceAsync.create].
         */
        @MustBeClosed
        suspend fun create(
            id: String,
            params: RetweetCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<RetweetCreateResponse> =
            create(params.toBuilder().id(id).build(), requestOptions)

        /** @see create */
        @MustBeClosed
        suspend fun create(
            params: RetweetCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<RetweetCreateResponse>

        /**
         * Returns a raw HTTP response for `delete /x/tweets/{id}/retweet`, but is otherwise the
         * same as [RetweetServiceAsync.delete].
         */
        @MustBeClosed
        suspend fun delete(
            id: String,
            params: RetweetDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<RetweetDeleteResponse> =
            delete(params.toBuilder().id(id).build(), requestOptions)

        /** @see delete */
        @MustBeClosed
        suspend fun delete(
            params: RetweetDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<RetweetDeleteResponse>
    }
}
