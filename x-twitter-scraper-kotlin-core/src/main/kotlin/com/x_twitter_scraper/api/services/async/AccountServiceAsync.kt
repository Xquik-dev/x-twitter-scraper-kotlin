// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.account.AccountRetrieveParams
import com.x_twitter_scraper.api.models.account.AccountRetrieveResponse
import com.x_twitter_scraper.api.models.account.AccountSetXUsernameParams
import com.x_twitter_scraper.api.models.account.AccountSetXUsernameResponse
import com.x_twitter_scraper.api.models.account.AccountUpdateLocaleParams
import com.x_twitter_scraper.api.models.account.AccountUpdateLocaleResponse

/** Account info & settings */
interface AccountServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: (ClientOptions.Builder) -> Unit): AccountServiceAsync

    /** Get account info */
    suspend fun retrieve(
        params: AccountRetrieveParams = AccountRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): AccountRetrieveResponse

    /** @see retrieve */
    suspend fun retrieve(requestOptions: RequestOptions): AccountRetrieveResponse =
        retrieve(AccountRetrieveParams.none(), requestOptions)

    /** Set linked X username */
    suspend fun setXUsername(
        params: AccountSetXUsernameParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): AccountSetXUsernameResponse

    /** Update account locale */
    suspend fun updateLocale(
        params: AccountUpdateLocaleParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): AccountUpdateLocaleResponse

    /**
     * A view of [AccountServiceAsync] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: (ClientOptions.Builder) -> Unit
        ): AccountServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /account`, but is otherwise the same as
         * [AccountServiceAsync.retrieve].
         */
        @MustBeClosed
        suspend fun retrieve(
            params: AccountRetrieveParams = AccountRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<AccountRetrieveResponse>

        /** @see retrieve */
        @MustBeClosed
        suspend fun retrieve(
            requestOptions: RequestOptions
        ): HttpResponseFor<AccountRetrieveResponse> =
            retrieve(AccountRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `put /account/x-identity`, but is otherwise the same as
         * [AccountServiceAsync.setXUsername].
         */
        @MustBeClosed
        suspend fun setXUsername(
            params: AccountSetXUsernameParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<AccountSetXUsernameResponse>

        /**
         * Returns a raw HTTP response for `patch /account`, but is otherwise the same as
         * [AccountServiceAsync.updateLocale].
         */
        @MustBeClosed
        suspend fun updateLocale(
            params: AccountUpdateLocaleParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<AccountUpdateLocaleResponse>
    }
}
