// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async.x

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.x.media.MediaDownloadParams
import com.x_twitter_scraper.api.models.x.media.MediaDownloadResponse
import com.x_twitter_scraper.api.models.x.media.MediaUploadParams
import com.x_twitter_scraper.api.models.x.media.MediaUploadResponse

/** Media upload & download */
interface MediaServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: (ClientOptions.Builder) -> Unit): MediaServiceAsync

    /** Download tweet media */
    suspend fun download(
        params: MediaDownloadParams = MediaDownloadParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): MediaDownloadResponse

    /** @see download */
    suspend fun download(requestOptions: RequestOptions): MediaDownloadResponse =
        download(MediaDownloadParams.none(), requestOptions)

    /** Upload media */
    suspend fun upload(
        params: MediaUploadParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): MediaUploadResponse

    /** A view of [MediaServiceAsync] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: (ClientOptions.Builder) -> Unit
        ): MediaServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /x/media/download`, but is otherwise the same as
         * [MediaServiceAsync.download].
         */
        @MustBeClosed
        suspend fun download(
            params: MediaDownloadParams = MediaDownloadParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<MediaDownloadResponse>

        /** @see download */
        @MustBeClosed
        suspend fun download(
            requestOptions: RequestOptions
        ): HttpResponseFor<MediaDownloadResponse> =
            download(MediaDownloadParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /x/media`, but is otherwise the same as
         * [MediaServiceAsync.upload].
         */
        @MustBeClosed
        suspend fun upload(
            params: MediaUploadParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<MediaUploadResponse>
    }
}
