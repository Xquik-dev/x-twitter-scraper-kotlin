// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.credits.CreditRetrieveBalanceParams
import com.x_twitter_scraper.api.models.credits.CreditRetrieveBalanceResponse
import com.x_twitter_scraper.api.models.credits.CreditTopupBalanceParams
import com.x_twitter_scraper.api.models.credits.CreditTopupBalanceResponse

/** Subscription & billing */
interface CreditServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: (ClientOptions.Builder) -> Unit): CreditServiceAsync

    /** Get credits balance */
    suspend fun retrieveBalance(
        params: CreditRetrieveBalanceParams = CreditRetrieveBalanceParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CreditRetrieveBalanceResponse

    /** @see retrieveBalance */
    suspend fun retrieveBalance(requestOptions: RequestOptions): CreditRetrieveBalanceResponse =
        retrieveBalance(CreditRetrieveBalanceParams.none(), requestOptions)

    /** Top up credits balance */
    suspend fun topupBalance(
        params: CreditTopupBalanceParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CreditTopupBalanceResponse

    /**
     * A view of [CreditServiceAsync] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: (ClientOptions.Builder) -> Unit
        ): CreditServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /credits`, but is otherwise the same as
         * [CreditServiceAsync.retrieveBalance].
         */
        @MustBeClosed
        suspend fun retrieveBalance(
            params: CreditRetrieveBalanceParams = CreditRetrieveBalanceParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<CreditRetrieveBalanceResponse>

        /** @see retrieveBalance */
        @MustBeClosed
        suspend fun retrieveBalance(
            requestOptions: RequestOptions
        ): HttpResponseFor<CreditRetrieveBalanceResponse> =
            retrieveBalance(CreditRetrieveBalanceParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /credits/topup`, but is otherwise the same as
         * [CreditServiceAsync.topupBalance].
         */
        @MustBeClosed
        suspend fun topupBalance(
            params: CreditTopupBalanceParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<CreditTopupBalanceResponse>
    }
}
