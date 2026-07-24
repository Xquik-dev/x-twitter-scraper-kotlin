// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async.x

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.x.accountconnectionchallenges.AccountConnectionChallengeSubmitParams
import com.x_twitter_scraper.api.models.x.accountconnectionchallenges.AccountConnectionChallengeSubmitResponse

/** Connected X account management */
interface AccountConnectionChallengeServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(
        modifier: (ClientOptions.Builder) -> Unit
    ): AccountConnectionChallengeServiceAsync

    /** Submit X account email verification code */
    suspend fun submit(
        id: String,
        params: AccountConnectionChallengeSubmitParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): AccountConnectionChallengeSubmitResponse =
        submit(params.toBuilder().id(id).build(), requestOptions)

    /** @see submit */
    suspend fun submit(
        params: AccountConnectionChallengeSubmitParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): AccountConnectionChallengeSubmitResponse

    /**
     * A view of [AccountConnectionChallengeServiceAsync] that provides access to raw HTTP responses
     * for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: (ClientOptions.Builder) -> Unit
        ): AccountConnectionChallengeServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /x/account-connection-challenges/{id}/submit`, but
         * is otherwise the same as [AccountConnectionChallengeServiceAsync.submit].
         */
        @MustBeClosed
        suspend fun submit(
            id: String,
            params: AccountConnectionChallengeSubmitParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<AccountConnectionChallengeSubmitResponse> =
            submit(params.toBuilder().id(id).build(), requestOptions)

        /** @see submit */
        @MustBeClosed
        suspend fun submit(
            params: AccountConnectionChallengeSubmitParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<AccountConnectionChallengeSubmitResponse>
    }
}
