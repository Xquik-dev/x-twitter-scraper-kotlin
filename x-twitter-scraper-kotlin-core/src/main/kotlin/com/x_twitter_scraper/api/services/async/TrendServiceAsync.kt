// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.trends.TrendListParams
import com.x_twitter_scraper.api.models.trends.TrendListResponse

/** Trending topics by region */
interface TrendServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: (ClientOptions.Builder) -> Unit): TrendServiceAsync

    /** Get regional trending topics */
    suspend fun list(
        params: TrendListParams = TrendListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): TrendListResponse

    /** @see list */
    suspend fun list(requestOptions: RequestOptions): TrendListResponse =
        list(TrendListParams.none(), requestOptions)

    /** A view of [TrendServiceAsync] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: (ClientOptions.Builder) -> Unit
        ): TrendServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /trends`, but is otherwise the same as
         * [TrendServiceAsync.list].
         */
        @MustBeClosed
        suspend fun list(
            params: TrendListParams = TrendListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<TrendListResponse>

        /** @see list */
        @MustBeClosed
        suspend fun list(requestOptions: RequestOptions): HttpResponseFor<TrendListResponse> =
            list(TrendListParams.none(), requestOptions)
    }
}
