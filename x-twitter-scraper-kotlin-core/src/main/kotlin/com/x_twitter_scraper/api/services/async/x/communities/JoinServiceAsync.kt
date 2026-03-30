// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async.x.communities

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.x.communities.CommunityActionResult
import com.x_twitter_scraper.api.models.x.communities.join.JoinCreateParams
import com.x_twitter_scraper.api.models.x.communities.join.JoinDeleteAllParams

/** X write actions (tweets, likes, follows, DMs) */
interface JoinServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: (ClientOptions.Builder) -> Unit): JoinServiceAsync

    /** Join community */
    suspend fun create(
        id: String,
        params: JoinCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CommunityActionResult = create(params.toBuilder().id(id).build(), requestOptions)

    /** @see create */
    suspend fun create(
        params: JoinCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CommunityActionResult

    /** Leave community */
    suspend fun deleteAll(
        id: String,
        params: JoinDeleteAllParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CommunityActionResult = deleteAll(params.toBuilder().id(id).build(), requestOptions)

    /** @see deleteAll */
    suspend fun deleteAll(
        params: JoinDeleteAllParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CommunityActionResult

    /** A view of [JoinServiceAsync] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: (ClientOptions.Builder) -> Unit): JoinServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /x/communities/{id}/join`, but is otherwise the
         * same as [JoinServiceAsync.create].
         */
        @MustBeClosed
        suspend fun create(
            id: String,
            params: JoinCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<CommunityActionResult> =
            create(params.toBuilder().id(id).build(), requestOptions)

        /** @see create */
        @MustBeClosed
        suspend fun create(
            params: JoinCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<CommunityActionResult>

        /**
         * Returns a raw HTTP response for `delete /x/communities/{id}/join`, but is otherwise the
         * same as [JoinServiceAsync.deleteAll].
         */
        @MustBeClosed
        suspend fun deleteAll(
            id: String,
            params: JoinDeleteAllParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<CommunityActionResult> =
            deleteAll(params.toBuilder().id(id).build(), requestOptions)

        /** @see deleteAll */
        @MustBeClosed
        suspend fun deleteAll(
            params: JoinDeleteAllParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<CommunityActionResult>
    }
}
