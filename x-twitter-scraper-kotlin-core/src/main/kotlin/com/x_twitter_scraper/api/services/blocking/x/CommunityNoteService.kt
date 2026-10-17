// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.services.blocking.x

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.x.communitynotes.CommunityNoteListContributorDeletedNotesParams
import com.x_twitter_scraper.api.models.x.communitynotes.CommunityNoteListContributorNotesParams
import com.x_twitter_scraper.api.models.x.communitynotes.CommunityNoteRetrieveContributorParams
import com.x_twitter_scraper.api.models.x.communitynotes.CommunityNoteRetrieveContributorResponse
import com.x_twitter_scraper.api.models.x.communitynotes.PaginatedCommunityNotes

/** Community Notes contributors, their notes, ratings & deleted notes */
interface CommunityNoteService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: (ClientOptions.Builder) -> Unit): CommunityNoteService

    /**
     * Returns 1 Community Notes contributor by the alias X gives them. Includes note counts by
     * rating, rating counts & the deleted notes count. Each lookup costs 1 credit.
     */
    fun retrieveContributor(
        alias: String,
        params: CommunityNoteRetrieveContributorParams =
            CommunityNoteRetrieveContributorParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CommunityNoteRetrieveContributorResponse =
        retrieveContributor(params.toBuilder().alias(alias).build(), requestOptions)

    /** @see retrieveContributor */
    fun retrieveContributor(
        params: CommunityNoteRetrieveContributorParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CommunityNoteRetrieveContributorResponse

    /** @see retrieveContributor */
    fun retrieveContributor(
        alias: String,
        requestOptions: RequestOptions,
    ): CommunityNoteRetrieveContributorResponse =
        retrieveContributor(alias, CommunityNoteRetrieveContributorParams.none(), requestOptions)

    /**
     * Returns a contributor's notes, newest first, each with the post it is on. Includes notes X
     * has not rated helpful, which x.com shows under no post. Each note costs 1 credit. Follow
     * next_cursor for more.
     */
    fun listContributorNotes(
        alias: String,
        params: CommunityNoteListContributorNotesParams =
            CommunityNoteListContributorNotesParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedCommunityNotes =
        listContributorNotes(params.toBuilder().alias(alias).build(), requestOptions)

    /** @see listContributorNotes */
    fun listContributorNotes(
        params: CommunityNoteListContributorNotesParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedCommunityNotes

    /** @see listContributorNotes */
    fun listContributorNotes(
        alias: String,
        requestOptions: RequestOptions,
    ): PaginatedCommunityNotes =
        listContributorNotes(alias, CommunityNoteListContributorNotesParams.none(), requestOptions)

    /**
     * Returns the notes a contributor deleted, newest first. X keeps each note's ID, times, rating
     * & tags. A notice replaces its text & post. Each note costs 1 credit. A contributor who
     * deleted none returns an empty list, free.
     */
    fun listContributorDeletedNotes(
        alias: String,
        params: CommunityNoteListContributorDeletedNotesParams =
            CommunityNoteListContributorDeletedNotesParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedCommunityNotes =
        listContributorDeletedNotes(params.toBuilder().alias(alias).build(), requestOptions)

    /** @see listContributorDeletedNotes */
    fun listContributorDeletedNotes(
        params: CommunityNoteListContributorDeletedNotesParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedCommunityNotes

    /** @see listContributorDeletedNotes */
    fun listContributorDeletedNotes(
        alias: String,
        requestOptions: RequestOptions,
    ): PaginatedCommunityNotes =
        listContributorDeletedNotes(
            alias,
            CommunityNoteListContributorDeletedNotesParams.none(),
            requestOptions,
        )

    /**
     * A view of [CommunityNoteService] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: (ClientOptions.Builder) -> Unit
        ): CommunityNoteService.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /x/community-notes/contributors/{alias}`, but is
         * otherwise the same as [CommunityNoteService.retrieveContributor].
         */
        @MustBeClosed
        fun retrieveContributor(
            alias: String,
            params: CommunityNoteRetrieveContributorParams =
                CommunityNoteRetrieveContributorParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<CommunityNoteRetrieveContributorResponse> =
            retrieveContributor(params.toBuilder().alias(alias).build(), requestOptions)

        /** @see retrieveContributor */
        @MustBeClosed
        fun retrieveContributor(
            params: CommunityNoteRetrieveContributorParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<CommunityNoteRetrieveContributorResponse>

        /** @see retrieveContributor */
        @MustBeClosed
        fun retrieveContributor(
            alias: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<CommunityNoteRetrieveContributorResponse> =
            retrieveContributor(
                alias,
                CommunityNoteRetrieveContributorParams.none(),
                requestOptions,
            )

        /**
         * Returns a raw HTTP response for `get /x/community-notes/contributors/{alias}/notes`, but
         * is otherwise the same as [CommunityNoteService.listContributorNotes].
         */
        @MustBeClosed
        fun listContributorNotes(
            alias: String,
            params: CommunityNoteListContributorNotesParams =
                CommunityNoteListContributorNotesParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedCommunityNotes> =
            listContributorNotes(params.toBuilder().alias(alias).build(), requestOptions)

        /** @see listContributorNotes */
        @MustBeClosed
        fun listContributorNotes(
            params: CommunityNoteListContributorNotesParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedCommunityNotes>

        /** @see listContributorNotes */
        @MustBeClosed
        fun listContributorNotes(
            alias: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<PaginatedCommunityNotes> =
            listContributorNotes(
                alias,
                CommunityNoteListContributorNotesParams.none(),
                requestOptions,
            )

        /**
         * Returns a raw HTTP response for `get
         * /x/community-notes/contributors/{alias}/deleted-notes`, but is otherwise the same as
         * [CommunityNoteService.listContributorDeletedNotes].
         */
        @MustBeClosed
        fun listContributorDeletedNotes(
            alias: String,
            params: CommunityNoteListContributorDeletedNotesParams =
                CommunityNoteListContributorDeletedNotesParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedCommunityNotes> =
            listContributorDeletedNotes(params.toBuilder().alias(alias).build(), requestOptions)

        /** @see listContributorDeletedNotes */
        @MustBeClosed
        fun listContributorDeletedNotes(
            params: CommunityNoteListContributorDeletedNotesParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedCommunityNotes>

        /** @see listContributorDeletedNotes */
        @MustBeClosed
        fun listContributorDeletedNotes(
            alias: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<PaginatedCommunityNotes> =
            listContributorDeletedNotes(
                alias,
                CommunityNoteListContributorDeletedNotesParams.none(),
                requestOptions,
            )
    }
}
