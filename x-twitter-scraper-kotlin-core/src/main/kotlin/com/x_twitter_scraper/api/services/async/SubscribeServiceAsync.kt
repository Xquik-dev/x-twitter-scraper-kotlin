// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.subscribe.SubscribeCreateParams
import com.x_twitter_scraper.api.models.subscribe.SubscribeCreateResponse

/** Subscription, billing, and credits */
interface SubscribeServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: (ClientOptions.Builder) -> Unit): SubscribeServiceAsync

    /**
     * Create a subscription checkout or billing-management URL only after the user confirms. The
     * request never completes payment by itself.
     */
    suspend fun create(
        params: SubscribeCreateParams = SubscribeCreateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): SubscribeCreateResponse

    /** @see create */
    suspend fun create(requestOptions: RequestOptions): SubscribeCreateResponse =
        create(SubscribeCreateParams.none(), requestOptions)

    /**
     * A view of [SubscribeServiceAsync] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: (ClientOptions.Builder) -> Unit
        ): SubscribeServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /subscribe`, but is otherwise the same as
         * [SubscribeServiceAsync.create].
         */
        @MustBeClosed
        suspend fun create(
            params: SubscribeCreateParams = SubscribeCreateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<SubscribeCreateResponse>

        /** @see create */
        @MustBeClosed
        suspend fun create(
            requestOptions: RequestOptions
        ): HttpResponseFor<SubscribeCreateResponse> =
            create(SubscribeCreateParams.none(), requestOptions)
    }
}
