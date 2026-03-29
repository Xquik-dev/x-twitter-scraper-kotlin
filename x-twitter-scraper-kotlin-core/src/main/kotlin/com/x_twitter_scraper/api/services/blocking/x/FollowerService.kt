// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.blocking.x

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.x.followers.FollowerRetrieveCheckParams
import com.x_twitter_scraper.api.models.x.followers.FollowerRetrieveCheckResponse

/** X data lookups (subscription required) */
interface FollowerService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: (ClientOptions.Builder) -> Unit): FollowerService

    /** Check follow relationship */
    fun retrieveCheck(
        params: FollowerRetrieveCheckParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): FollowerRetrieveCheckResponse

    /** A view of [FollowerService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: (ClientOptions.Builder) -> Unit): FollowerService.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /x/followers/check`, but is otherwise the same as
         * [FollowerService.retrieveCheck].
         */
        @MustBeClosed
        fun retrieveCheck(
            params: FollowerRetrieveCheckParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<FollowerRetrieveCheckResponse>
    }
}
