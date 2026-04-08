// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async.x

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.x.accounts.AccountBulkRetryParams
import com.x_twitter_scraper.api.models.x.accounts.AccountBulkRetryResponse
import com.x_twitter_scraper.api.models.x.accounts.AccountCreateParams
import com.x_twitter_scraper.api.models.x.accounts.AccountCreateResponse
import com.x_twitter_scraper.api.models.x.accounts.AccountDeleteParams
import com.x_twitter_scraper.api.models.x.accounts.AccountDeleteResponse
import com.x_twitter_scraper.api.models.x.accounts.AccountListParams
import com.x_twitter_scraper.api.models.x.accounts.AccountListResponse
import com.x_twitter_scraper.api.models.x.accounts.AccountReauthParams
import com.x_twitter_scraper.api.models.x.accounts.AccountReauthResponse
import com.x_twitter_scraper.api.models.x.accounts.AccountRetrieveParams
import com.x_twitter_scraper.api.models.x.accounts.XAccountDetail

/** Connected X account management */
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

    /** Connect X account */
    suspend fun create(
        params: AccountCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): AccountCreateResponse

    /** Get X account details */
    suspend fun retrieve(
        id: String,
        params: AccountRetrieveParams = AccountRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): XAccountDetail = retrieve(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieve */
    suspend fun retrieve(
        params: AccountRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): XAccountDetail

    /** @see retrieve */
    suspend fun retrieve(id: String, requestOptions: RequestOptions): XAccountDetail =
        retrieve(id, AccountRetrieveParams.none(), requestOptions)

    /** List connected X accounts */
    suspend fun list(
        params: AccountListParams = AccountListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): AccountListResponse

    /** @see list */
    suspend fun list(requestOptions: RequestOptions): AccountListResponse =
        list(AccountListParams.none(), requestOptions)

    /** Disconnect X account */
    suspend fun delete(
        id: String,
        params: AccountDeleteParams = AccountDeleteParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): AccountDeleteResponse = delete(params.toBuilder().id(id).build(), requestOptions)

    /** @see delete */
    suspend fun delete(
        params: AccountDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): AccountDeleteResponse

    /** @see delete */
    suspend fun delete(id: String, requestOptions: RequestOptions): AccountDeleteResponse =
        delete(id, AccountDeleteParams.none(), requestOptions)

    /**
     * Clears loginFailedAt and loginFailureReason for all accounts with transient or automated
     * failure reasons, making them eligible for retry on next use.
     */
    suspend fun bulkRetry(
        params: AccountBulkRetryParams = AccountBulkRetryParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): AccountBulkRetryResponse

    /** @see bulkRetry */
    suspend fun bulkRetry(requestOptions: RequestOptions): AccountBulkRetryResponse =
        bulkRetry(AccountBulkRetryParams.none(), requestOptions)

    /** Re-authenticate X account */
    suspend fun reauth(
        id: String,
        params: AccountReauthParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): AccountReauthResponse = reauth(params.toBuilder().id(id).build(), requestOptions)

    /** @see reauth */
    suspend fun reauth(
        params: AccountReauthParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): AccountReauthResponse

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
         * Returns a raw HTTP response for `post /x/accounts`, but is otherwise the same as
         * [AccountServiceAsync.create].
         */
        @MustBeClosed
        suspend fun create(
            params: AccountCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<AccountCreateResponse>

        /**
         * Returns a raw HTTP response for `get /x/accounts/{id}`, but is otherwise the same as
         * [AccountServiceAsync.retrieve].
         */
        @MustBeClosed
        suspend fun retrieve(
            id: String,
            params: AccountRetrieveParams = AccountRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<XAccountDetail> =
            retrieve(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        suspend fun retrieve(
            params: AccountRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<XAccountDetail>

        /** @see retrieve */
        @MustBeClosed
        suspend fun retrieve(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<XAccountDetail> =
            retrieve(id, AccountRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/accounts`, but is otherwise the same as
         * [AccountServiceAsync.list].
         */
        @MustBeClosed
        suspend fun list(
            params: AccountListParams = AccountListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<AccountListResponse>

        /** @see list */
        @MustBeClosed
        suspend fun list(requestOptions: RequestOptions): HttpResponseFor<AccountListResponse> =
            list(AccountListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete /x/accounts/{id}`, but is otherwise the same as
         * [AccountServiceAsync.delete].
         */
        @MustBeClosed
        suspend fun delete(
            id: String,
            params: AccountDeleteParams = AccountDeleteParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<AccountDeleteResponse> =
            delete(params.toBuilder().id(id).build(), requestOptions)

        /** @see delete */
        @MustBeClosed
        suspend fun delete(
            params: AccountDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<AccountDeleteResponse>

        /** @see delete */
        @MustBeClosed
        suspend fun delete(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<AccountDeleteResponse> =
            delete(id, AccountDeleteParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /x/accounts/bulk-retry`, but is otherwise the same
         * as [AccountServiceAsync.bulkRetry].
         */
        @MustBeClosed
        suspend fun bulkRetry(
            params: AccountBulkRetryParams = AccountBulkRetryParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<AccountBulkRetryResponse>

        /** @see bulkRetry */
        @MustBeClosed
        suspend fun bulkRetry(
            requestOptions: RequestOptions
        ): HttpResponseFor<AccountBulkRetryResponse> =
            bulkRetry(AccountBulkRetryParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /x/accounts/{id}/reauth`, but is otherwise the same
         * as [AccountServiceAsync.reauth].
         */
        @MustBeClosed
        suspend fun reauth(
            id: String,
            params: AccountReauthParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<AccountReauthResponse> =
            reauth(params.toBuilder().id(id).build(), requestOptions)

        /** @see reauth */
        @MustBeClosed
        suspend fun reauth(
            params: AccountReauthParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<AccountReauthResponse>
    }
}
