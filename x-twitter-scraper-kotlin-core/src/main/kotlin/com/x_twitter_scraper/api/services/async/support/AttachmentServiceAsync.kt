// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async.support

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponse
import com.x_twitter_scraper.api.models.support.attachments.AttachmentDownloadParams

/** Support ticket management */
interface AttachmentServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: (ClientOptions.Builder) -> Unit): AttachmentServiceAsync

    /**
     * Streams an authenticated user's support image or video. Video requests support one standard
     * byte range for seeking and resumable playback.
     */
    @MustBeClosed
    suspend fun download(
        id: String,
        params: AttachmentDownloadParams = AttachmentDownloadParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): HttpResponse = download(params.toBuilder().id(id).build(), requestOptions)

    /** @see download */
    @MustBeClosed
    suspend fun download(
        params: AttachmentDownloadParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): HttpResponse

    /** @see download */
    @MustBeClosed
    suspend fun download(id: String, requestOptions: RequestOptions): HttpResponse =
        download(id, AttachmentDownloadParams.none(), requestOptions)

    /**
     * A view of [AttachmentServiceAsync] that provides access to raw HTTP responses for each
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
        ): AttachmentServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /support/attachments/{id}`, but is otherwise the
         * same as [AttachmentServiceAsync.download].
         */
        @MustBeClosed
        suspend fun download(
            id: String,
            params: AttachmentDownloadParams = AttachmentDownloadParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponse = download(params.toBuilder().id(id).build(), requestOptions)

        /** @see download */
        @MustBeClosed
        suspend fun download(
            params: AttachmentDownloadParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponse

        /** @see download */
        @MustBeClosed
        suspend fun download(id: String, requestOptions: RequestOptions): HttpResponse =
            download(id, AttachmentDownloadParams.none(), requestOptions)
    }
}
