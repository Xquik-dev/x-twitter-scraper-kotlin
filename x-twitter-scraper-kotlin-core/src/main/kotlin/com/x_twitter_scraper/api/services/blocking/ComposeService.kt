// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.blocking

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.compose.ComposeCreateParams
import com.x_twitter_scraper.api.models.compose.ComposeCreateResponse

/** AI tweet composition, drafts, writing styles, and radar */
interface ComposeService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: (ClientOptions.Builder) -> Unit): ComposeService

    /**
     * Run one step of Xquik's three-step writing workflow. Compose returns questions and editorial
     * rules. Refine returns goal-specific guidance. Score applies deterministic text checks. It
     * does not predict reach or expose X ranking weights.
     */
    fun create(
        params: ComposeCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): ComposeCreateResponse

    /** @see create */
    fun create(
        body: ComposeCreateParams.Body,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): ComposeCreateResponse =
        create(ComposeCreateParams.builder().body(body).build(), requestOptions)

    /** @see create */
    fun create(
        composePrepareRequest: ComposeCreateParams.Body.ComposePrepareRequest,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): ComposeCreateResponse =
        create(
            ComposeCreateParams.Body.ofComposePrepareRequest(composePrepareRequest),
            requestOptions,
        )

    /** @see create */
    fun create(
        composeRefineRequest: ComposeCreateParams.Body.ComposeRefineRequest,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): ComposeCreateResponse =
        create(
            ComposeCreateParams.Body.ofComposeRefineRequest(composeRefineRequest),
            requestOptions,
        )

    /** @see create */
    fun create(
        composeScoreRequest: ComposeCreateParams.Body.ComposeScoreRequest,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): ComposeCreateResponse =
        create(ComposeCreateParams.Body.ofComposeScoreRequest(composeScoreRequest), requestOptions)

    /** A view of [ComposeService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: (ClientOptions.Builder) -> Unit): ComposeService.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /compose`, but is otherwise the same as
         * [ComposeService.create].
         */
        @MustBeClosed
        fun create(
            params: ComposeCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<ComposeCreateResponse>

        /** @see create */
        @MustBeClosed
        fun create(
            body: ComposeCreateParams.Body,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<ComposeCreateResponse> =
            create(ComposeCreateParams.builder().body(body).build(), requestOptions)

        /** @see create */
        @MustBeClosed
        fun create(
            composePrepareRequest: ComposeCreateParams.Body.ComposePrepareRequest,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<ComposeCreateResponse> =
            create(
                ComposeCreateParams.Body.ofComposePrepareRequest(composePrepareRequest),
                requestOptions,
            )

        /** @see create */
        @MustBeClosed
        fun create(
            composeRefineRequest: ComposeCreateParams.Body.ComposeRefineRequest,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<ComposeCreateResponse> =
            create(
                ComposeCreateParams.Body.ofComposeRefineRequest(composeRefineRequest),
                requestOptions,
            )

        /** @see create */
        @MustBeClosed
        fun create(
            composeScoreRequest: ComposeCreateParams.Body.ComposeScoreRequest,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<ComposeCreateResponse> =
            create(
                ComposeCreateParams.Body.ofComposeScoreRequest(composeScoreRequest),
                requestOptions,
            )
    }
}
