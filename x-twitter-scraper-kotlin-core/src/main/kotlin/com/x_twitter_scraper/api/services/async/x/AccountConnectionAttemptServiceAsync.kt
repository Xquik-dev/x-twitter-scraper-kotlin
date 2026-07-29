// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async.x

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.x.accountconnectionattempts.AccountConnectionAttemptRetrieveParams
import com.x_twitter_scraper.api.models.x.accountconnectionattempts.AccountConnectionAttemptRetrieveResponse

/** Connected X account management */
interface AccountConnectionAttemptServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: (ClientOptions.Builder) -> Unit): AccountConnectionAttemptServiceAsync

    /** Get X account connection status */
    suspend fun retrieve(
        id: String,
        params: AccountConnectionAttemptRetrieveParams =
            AccountConnectionAttemptRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): AccountConnectionAttemptRetrieveResponse =
        retrieve(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieve */
    suspend fun retrieve(
        params: AccountConnectionAttemptRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): AccountConnectionAttemptRetrieveResponse

    /** @see retrieve */
    suspend fun retrieve(
        id: String,
        requestOptions: RequestOptions,
    ): AccountConnectionAttemptRetrieveResponse =
        retrieve(id, AccountConnectionAttemptRetrieveParams.none(), requestOptions)

    /**
     * A view of [AccountConnectionAttemptServiceAsync] that provides access to raw HTTP responses
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
        ): AccountConnectionAttemptServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /x/account-connection-attempts/{id}`, but is
         * otherwise the same as [AccountConnectionAttemptServiceAsync.retrieve].
         */
        @MustBeClosed
        suspend fun retrieve(
            id: String,
            params: AccountConnectionAttemptRetrieveParams =
                AccountConnectionAttemptRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<AccountConnectionAttemptRetrieveResponse> =
            retrieve(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        suspend fun retrieve(
            params: AccountConnectionAttemptRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<AccountConnectionAttemptRetrieveResponse>

        /** @see retrieve */
        @MustBeClosed
        suspend fun retrieve(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<AccountConnectionAttemptRetrieveResponse> =
            retrieve(id, AccountConnectionAttemptRetrieveParams.none(), requestOptions)
    }
}
