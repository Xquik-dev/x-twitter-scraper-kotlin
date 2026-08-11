// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.blocking

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponse
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.credits.CreditRedirectTopupCheckoutParams
import com.x_twitter_scraper.api.models.credits.CreditRetrieveBalanceParams
import com.x_twitter_scraper.api.models.credits.CreditRetrieveBalanceResponse
import com.x_twitter_scraper.api.models.credits.CreditRetrieveTopupStatusParams
import com.x_twitter_scraper.api.models.credits.CreditRetrieveTopupStatusResponse
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

    /** Redirect to an active top-up payment page */
    fun redirectTopupCheckout(
        params: CreditRedirectTopupCheckoutParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    )

    /** Get credits balance */
    fun retrieveBalance(
        params: CreditRetrieveBalanceParams = CreditRetrieveBalanceParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CreditRetrieveBalanceResponse

    /** @see retrieveBalance */
    fun retrieveBalance(requestOptions: RequestOptions): CreditRetrieveBalanceResponse =
        retrieveBalance(CreditRetrieveBalanceParams.none(), requestOptions)

    /** Get top-up billing status */
    fun retrieveTopupStatus(
        params: CreditRetrieveTopupStatusParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CreditRetrieveTopupStatusResponse

    /**
     * Create a hosted checkout only after the user confirms. The request never completes payment or
     * adds credits.
     */
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
         * Returns a raw HTTP response for `get /credits/topup/redirect`, but is otherwise the same
         * as [CreditService.redirectTopupCheckout].
         */
        @MustBeClosed
        fun redirectTopupCheckout(
            params: CreditRedirectTopupCheckoutParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponse

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
         * Returns a raw HTTP response for `get /credits/topup/status`, but is otherwise the same as
         * [CreditService.retrieveTopupStatus].
         */
        @MustBeClosed
        fun retrieveTopupStatus(
            params: CreditRetrieveTopupStatusParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<CreditRetrieveTopupStatusResponse>

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
