// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.compose.ComposeCreateParams
import com.x_twitter_scraper.api.models.compose.ComposeCreateResponse

/** AI tweet composition, drafts, writing styles, and radar */
interface ComposeServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: (ClientOptions.Builder) -> Unit): ComposeServiceAsync

    /** Compose, refine, or score a tweet */
    suspend fun create(
        params: ComposeCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): ComposeCreateResponse

    /**
     * A view of [ComposeServiceAsync] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: (ClientOptions.Builder) -> Unit
        ): ComposeServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /compose`, but is otherwise the same as
         * [ComposeServiceAsync.create].
         */
        @MustBeClosed
        suspend fun create(
            params: ComposeCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<ComposeCreateResponse>
    }
}
