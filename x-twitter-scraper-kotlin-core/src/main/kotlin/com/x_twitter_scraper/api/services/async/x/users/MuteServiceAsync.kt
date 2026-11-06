// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.services.async.x.users

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.x.users.mute.MuteCreateParams
import com.x_twitter_scraper.api.models.x.users.mute.MuteCreateResponse
import com.x_twitter_scraper.api.models.x.users.mute.MuteDeleteParams
import com.x_twitter_scraper.api.models.x.users.mute.MuteDeleteResponse

/** X write actions (tweets, likes, follows, DMs) */
interface MuteServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: (ClientOptions.Builder) -> Unit): MuteServiceAsync

    /** Mute user */
    suspend fun create(
        id: String,
        params: MuteCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): MuteCreateResponse = create(params.toBuilder().id(id).build(), requestOptions)

    /** @see create */
    suspend fun create(
        params: MuteCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): MuteCreateResponse

    /** Unmute user */
    suspend fun delete(
        id: String,
        params: MuteDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): MuteDeleteResponse = delete(params.toBuilder().id(id).build(), requestOptions)

    /** @see delete */
    suspend fun delete(
        params: MuteDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): MuteDeleteResponse

    /** A view of [MuteServiceAsync] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: (ClientOptions.Builder) -> Unit): MuteServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /x/users/{id}/mute`, but is otherwise the same as
         * [MuteServiceAsync.create].
         */
        @MustBeClosed
        suspend fun create(
            id: String,
            params: MuteCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<MuteCreateResponse> =
            create(params.toBuilder().id(id).build(), requestOptions)

        /** @see create */
        @MustBeClosed
        suspend fun create(
            params: MuteCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<MuteCreateResponse>

        /**
         * Returns a raw HTTP response for `delete /x/users/{id}/mute`, but is otherwise the same as
         * [MuteServiceAsync.delete].
         */
        @MustBeClosed
        suspend fun delete(
            id: String,
            params: MuteDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<MuteDeleteResponse> =
            delete(params.toBuilder().id(id).build(), requestOptions)

        /** @see delete */
        @MustBeClosed
        suspend fun delete(
            params: MuteDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<MuteDeleteResponse>
    }
}
