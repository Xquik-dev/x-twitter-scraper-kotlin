// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async.bot

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.bot.platformlinks.PlatformLinkCreateParams
import com.x_twitter_scraper.api.models.bot.platformlinks.PlatformLinkCreateResponse
import com.x_twitter_scraper.api.models.bot.platformlinks.PlatformLinkDeleteParams
import com.x_twitter_scraper.api.models.bot.platformlinks.PlatformLinkDeleteResponse
import com.x_twitter_scraper.api.models.bot.platformlinks.PlatformLinkLookupParams
import com.x_twitter_scraper.api.models.bot.platformlinks.PlatformLinkLookupResponse

/** Telegram bot service endpoints */
interface PlatformLinkServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: (ClientOptions.Builder) -> Unit): PlatformLinkServiceAsync

    /** Link a platform user to an Xquik account */
    suspend fun create(
        params: PlatformLinkCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PlatformLinkCreateResponse

    /** Unlink a platform user from an Xquik account */
    suspend fun delete(
        params: PlatformLinkDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PlatformLinkDeleteResponse

    /** Look up an Xquik user by platform identity */
    suspend fun lookup(
        params: PlatformLinkLookupParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PlatformLinkLookupResponse

    /**
     * A view of [PlatformLinkServiceAsync] that provides access to raw HTTP responses for each
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
        ): PlatformLinkServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /bot/platform-links`, but is otherwise the same as
         * [PlatformLinkServiceAsync.create].
         */
        @MustBeClosed
        suspend fun create(
            params: PlatformLinkCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PlatformLinkCreateResponse>

        /**
         * Returns a raw HTTP response for `delete /bot/platform-links`, but is otherwise the same
         * as [PlatformLinkServiceAsync.delete].
         */
        @MustBeClosed
        suspend fun delete(
            params: PlatformLinkDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PlatformLinkDeleteResponse>

        /**
         * Returns a raw HTTP response for `get /bot/platform-links/lookup`, but is otherwise the
         * same as [PlatformLinkServiceAsync.lookup].
         */
        @MustBeClosed
        suspend fun lookup(
            params: PlatformLinkLookupParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PlatformLinkLookupResponse>
    }
}
