// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.guestwallets.GuestWalletCreateParams
import com.x_twitter_scraper.api.models.guestwallets.GuestWalletCreateResponse
import com.x_twitter_scraper.api.models.guestwallets.GuestWalletRetrieveStatusParams
import com.x_twitter_scraper.api.models.guestwallets.GuestWalletRetrieveStatusResponse
import com.x_twitter_scraper.api.models.guestwallets.GuestWalletTopupParams
import com.x_twitter_scraper.api.models.guestwallets.GuestWalletTopupResponse

/** Accountless prepaid access for paid read endpoints */
interface GuestWalletServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: (ClientOptions.Builder) -> Unit): GuestWalletServiceAsync

    /**
     * Create a one-use Stripe-hosted checkout after the user explicitly confirms a $10-$250 USD
     * amount. This request creates no charge by itself. The user opens checkout_url on Stripe. This
     * endpoint returns the paid-read API key without requiring an Xquik account, email, dashboard,
     * or Xquik web page. An idempotent replay returns the same key.
     */
    suspend fun create(
        params: GuestWalletCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): GuestWalletCreateResponse

    /**
     * Poll after Stripe payment. Use usable to decide whether paid reads can run. An active wallet
     * can remain usable while a top-up is pending. A new wallet becomes usable only after verified
     * webhook fulfillment. Send the guest key as Authorization: Bearer.
     */
    suspend fun retrieveStatus(
        params: GuestWalletRetrieveStatusParams = GuestWalletRetrieveStatusParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): GuestWalletRetrieveStatusResponse

    /** @see retrieveStatus */
    suspend fun retrieveStatus(requestOptions: RequestOptions): GuestWalletRetrieveStatusResponse =
        retrieveStatus(GuestWalletRetrieveStatusParams.none(), requestOptions)

    /**
     * Create a one-use Stripe-hosted checkout for an existing paid-read guest key after the user
     * explicitly confirms a $10-$250 USD amount. The key remains the same. This request creates no
     * charge by itself and never redirects through an Xquik web page.
     */
    suspend fun topup(
        params: GuestWalletTopupParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): GuestWalletTopupResponse

    /**
     * A view of [GuestWalletServiceAsync] that provides access to raw HTTP responses for each
     * method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: (ClientOptions.Builder) -> Unit
        ): GuestWalletServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /guest-wallets`, but is otherwise the same as
         * [GuestWalletServiceAsync.create].
         */
        @MustBeClosed
        suspend fun create(
            params: GuestWalletCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<GuestWalletCreateResponse>

        /**
         * Returns a raw HTTP response for `get /guest-wallets/status`, but is otherwise the same as
         * [GuestWalletServiceAsync.retrieveStatus].
         */
        @MustBeClosed
        suspend fun retrieveStatus(
            params: GuestWalletRetrieveStatusParams = GuestWalletRetrieveStatusParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<GuestWalletRetrieveStatusResponse>

        /** @see retrieveStatus */
        @MustBeClosed
        suspend fun retrieveStatus(
            requestOptions: RequestOptions
        ): HttpResponseFor<GuestWalletRetrieveStatusResponse> =
            retrieveStatus(GuestWalletRetrieveStatusParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /guest-wallets/topups`, but is otherwise the same
         * as [GuestWalletServiceAsync.topup].
         */
        @MustBeClosed
        suspend fun topup(
            params: GuestWalletTopupParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<GuestWalletTopupResponse>
    }
}
