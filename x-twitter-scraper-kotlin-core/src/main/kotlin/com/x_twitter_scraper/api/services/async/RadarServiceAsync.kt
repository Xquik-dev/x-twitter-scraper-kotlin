// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.radar.RadarRetrieveTrendingTopicsParams
import com.x_twitter_scraper.api.models.radar.RadarRetrieveTrendingTopicsResponse

/** AI tweet composition, drafts, writing styles, and radar */
interface RadarServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: (ClientOptions.Builder) -> Unit): RadarServiceAsync

    /** Get trending topics from curated sources */
    suspend fun retrieveTrendingTopics(
        params: RadarRetrieveTrendingTopicsParams = RadarRetrieveTrendingTopicsParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): RadarRetrieveTrendingTopicsResponse

    /** @see retrieveTrendingTopics */
    suspend fun retrieveTrendingTopics(
        requestOptions: RequestOptions
    ): RadarRetrieveTrendingTopicsResponse =
        retrieveTrendingTopics(RadarRetrieveTrendingTopicsParams.none(), requestOptions)

    /** A view of [RadarServiceAsync] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: (ClientOptions.Builder) -> Unit
        ): RadarServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /radar`, but is otherwise the same as
         * [RadarServiceAsync.retrieveTrendingTopics].
         */
        @MustBeClosed
        suspend fun retrieveTrendingTopics(
            params: RadarRetrieveTrendingTopicsParams = RadarRetrieveTrendingTopicsParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<RadarRetrieveTrendingTopicsResponse>

        /** @see retrieveTrendingTopics */
        @MustBeClosed
        suspend fun retrieveTrendingTopics(
            requestOptions: RequestOptions
        ): HttpResponseFor<RadarRetrieveTrendingTopicsResponse> =
            retrieveTrendingTopics(RadarRetrieveTrendingTopicsParams.none(), requestOptions)
    }
}
