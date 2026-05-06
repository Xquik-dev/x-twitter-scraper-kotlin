// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async.x

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.x.followers.FollowerCheckParams
import com.x_twitter_scraper.api.models.x.followers.FollowerCheckResponse

/** Look up, search, and explore user profiles and relationships */
interface FollowerServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: (ClientOptions.Builder) -> Unit): FollowerServiceAsync

    /** Check if one user follows another */
    suspend fun check(
        params: FollowerCheckParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): FollowerCheckResponse

    /**
     * A view of [FollowerServiceAsync] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: (ClientOptions.Builder) -> Unit
        ): FollowerServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /x/followers/check`, but is otherwise the same as
         * [FollowerServiceAsync.check].
         */
        @MustBeClosed
        suspend fun check(
            params: FollowerCheckParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<FollowerCheckResponse>
    }
}
