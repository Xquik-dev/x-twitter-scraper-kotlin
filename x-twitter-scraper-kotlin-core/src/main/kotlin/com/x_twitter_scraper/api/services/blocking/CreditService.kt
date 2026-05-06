// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.blocking

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.credits.CreditRetrieveBalanceParams
import com.x_twitter_scraper.api.models.credits.CreditRetrieveBalanceResponse
import com.x_twitter_scraper.api.models.credits.CreditTopupBalanceParams
import com.x_twitter_scraper.api.models.credits.CreditTopupBalanceResponse

/** Subscription, billing, and credits */
interface CreditService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: (ClientOptions.Builder) -> Unit): CreditService

    /** Get credits balance */
    fun retrieveBalance(
        params: CreditRetrieveBalanceParams = CreditRetrieveBalanceParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CreditRetrieveBalanceResponse

    /** @see retrieveBalance */
    fun retrieveBalance(requestOptions: RequestOptions): CreditRetrieveBalanceResponse =
        retrieveBalance(CreditRetrieveBalanceParams.none(), requestOptions)

    /** Top up credits balance */
    fun topupBalance(
        params: CreditTopupBalanceParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CreditTopupBalanceResponse

    /** A view of [CreditService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: (ClientOptions.Builder) -> Unit): CreditService.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /credits`, but is otherwise the same as
         * [CreditService.retrieveBalance].
         */
        @MustBeClosed
        fun retrieveBalance(
            params: CreditRetrieveBalanceParams = CreditRetrieveBalanceParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<CreditRetrieveBalanceResponse>

        /** @see retrieveBalance */
        @MustBeClosed
        fun retrieveBalance(
            requestOptions: RequestOptions
        ): HttpResponseFor<CreditRetrieveBalanceResponse> =
            retrieveBalance(CreditRetrieveBalanceParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /credits/topup`, but is otherwise the same as
         * [CreditService.topupBalance].
         */
        @MustBeClosed
        fun topupBalance(
            params: CreditTopupBalanceParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<CreditTopupBalanceResponse>
    }
}
