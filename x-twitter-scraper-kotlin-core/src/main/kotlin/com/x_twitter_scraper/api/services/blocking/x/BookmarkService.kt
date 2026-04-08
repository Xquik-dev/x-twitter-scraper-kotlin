// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.blocking.x

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.x.bookmarks.BookmarkListPage
import com.x_twitter_scraper.api.models.x.bookmarks.BookmarkListParams
import com.x_twitter_scraper.api.models.x.bookmarks.BookmarkRetrieveFoldersParams
import com.x_twitter_scraper.api.models.x.bookmarks.BookmarkRetrieveFoldersResponse

/** X data lookups (subscription required) */
interface BookmarkService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: (ClientOptions.Builder) -> Unit): BookmarkService

    /** Get bookmarked tweets */
    fun list(
        params: BookmarkListParams = BookmarkListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BookmarkListPage

    /** @see list */
    fun list(requestOptions: RequestOptions): BookmarkListPage =
        list(BookmarkListParams.none(), requestOptions)

    /** Get bookmark folders */
    fun retrieveFolders(
        params: BookmarkRetrieveFoldersParams = BookmarkRetrieveFoldersParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BookmarkRetrieveFoldersResponse

    /** @see retrieveFolders */
    fun retrieveFolders(requestOptions: RequestOptions): BookmarkRetrieveFoldersResponse =
        retrieveFolders(BookmarkRetrieveFoldersParams.none(), requestOptions)

    /** A view of [BookmarkService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: (ClientOptions.Builder) -> Unit): BookmarkService.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /x/bookmarks`, but is otherwise the same as
         * [BookmarkService.list].
         */
        @MustBeClosed
        fun list(
            params: BookmarkListParams = BookmarkListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BookmarkListPage>

        /** @see list */
        @MustBeClosed
        fun list(requestOptions: RequestOptions): HttpResponseFor<BookmarkListPage> =
            list(BookmarkListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/bookmarks/folders`, but is otherwise the same as
         * [BookmarkService.retrieveFolders].
         */
        @MustBeClosed
        fun retrieveFolders(
            params: BookmarkRetrieveFoldersParams = BookmarkRetrieveFoldersParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BookmarkRetrieveFoldersResponse>

        /** @see retrieveFolders */
        @MustBeClosed
        fun retrieveFolders(
            requestOptions: RequestOptions
        ): HttpResponseFor<BookmarkRetrieveFoldersResponse> =
            retrieveFolders(BookmarkRetrieveFoldersParams.none(), requestOptions)
    }
}
