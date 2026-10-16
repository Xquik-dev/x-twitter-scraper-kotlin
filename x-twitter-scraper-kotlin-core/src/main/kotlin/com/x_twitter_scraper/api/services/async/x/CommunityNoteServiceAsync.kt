// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.services.async.x

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.x.communitynotes.CommunityNoteListContributorDeletedNotesParams
import com.x_twitter_scraper.api.models.x.communitynotes.CommunityNoteListContributorDeletedNotesResponse
import com.x_twitter_scraper.api.models.x.communitynotes.CommunityNoteListContributorNotesParams
import com.x_twitter_scraper.api.models.x.communitynotes.CommunityNoteListContributorNotesResponse
import com.x_twitter_scraper.api.models.x.communitynotes.CommunityNoteRetrieveContributorParams
import com.x_twitter_scraper.api.models.x.communitynotes.CommunityNoteRetrieveContributorResponse

/** Community Notes contributors, their notes, ratings & deleted notes */
interface CommunityNoteServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: (ClientOptions.Builder) -> Unit): CommunityNoteServiceAsync

    /**
     * Returns 1 Community Notes contributor by the alias X gives them. Includes note counts by
     * rating, rating counts & the deleted notes count. Each lookup costs 1 credit.
     */
    suspend fun retrieveContributor(
        alias: String,
        params: CommunityNoteRetrieveContributorParams =
            CommunityNoteRetrieveContributorParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CommunityNoteRetrieveContributorResponse =
        retrieveContributor(params.toBuilder().alias(alias).build(), requestOptions)

    /** @see retrieveContributor */
    suspend fun retrieveContributor(
        params: CommunityNoteRetrieveContributorParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CommunityNoteRetrieveContributorResponse

    /** @see retrieveContributor */
    suspend fun retrieveContributor(
        alias: String,
        requestOptions: RequestOptions,
    ): CommunityNoteRetrieveContributorResponse =
        retrieveContributor(alias, CommunityNoteRetrieveContributorParams.none(), requestOptions)

    /**
     * Returns a contributor's notes, newest first, each with the post it is on. Includes notes X
     * has not rated helpful, which x.com shows under no post. Each note costs 1 credit. Follow
     * next_cursor for more.
     */
    suspend fun listContributorNotes(
        alias: String,
        params: CommunityNoteListContributorNotesParams =
            CommunityNoteListContributorNotesParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CommunityNoteListContributorNotesResponse =
        listContributorNotes(params.toBuilder().alias(alias).build(), requestOptions)

    /** @see listContributorNotes */
    suspend fun listContributorNotes(
        params: CommunityNoteListContributorNotesParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CommunityNoteListContributorNotesResponse

    /** @see listContributorNotes */
    suspend fun listContributorNotes(
        alias: String,
        requestOptions: RequestOptions,
    ): CommunityNoteListContributorNotesResponse =
        listContributorNotes(alias, CommunityNoteListContributorNotesParams.none(), requestOptions)

    /**
     * Returns the notes a contributor deleted, newest first. X keeps each note's ID, times, rating
     * & tags. A notice replaces its text & post. Each note costs 1 credit. A contributor who
     * deleted none returns an empty list, free.
     */
    suspend fun listContributorDeletedNotes(
        alias: String,
        params: CommunityNoteListContributorDeletedNotesParams =
            CommunityNoteListContributorDeletedNotesParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CommunityNoteListContributorDeletedNotesResponse =
        listContributorDeletedNotes(params.toBuilder().alias(alias).build(), requestOptions)

    /** @see listContributorDeletedNotes */
    suspend fun listContributorDeletedNotes(
        params: CommunityNoteListContributorDeletedNotesParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CommunityNoteListContributorDeletedNotesResponse

    /** @see listContributorDeletedNotes */
    suspend fun listContributorDeletedNotes(
        alias: String,
        requestOptions: RequestOptions,
    ): CommunityNoteListContributorDeletedNotesResponse =
        listContributorDeletedNotes(
            alias,
            CommunityNoteListContributorDeletedNotesParams.none(),
            requestOptions,
        )

    /**
     * A view of [CommunityNoteServiceAsync] that provides access to raw HTTP responses for each
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
        ): CommunityNoteServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /x/community-notes/contributors/{alias}`, but is
         * otherwise the same as [CommunityNoteServiceAsync.retrieveContributor].
         */
        @MustBeClosed
        suspend fun retrieveContributor(
            alias: String,
            params: CommunityNoteRetrieveContributorParams =
                CommunityNoteRetrieveContributorParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<CommunityNoteRetrieveContributorResponse> =
            retrieveContributor(params.toBuilder().alias(alias).build(), requestOptions)

        /** @see retrieveContributor */
        @MustBeClosed
        suspend fun retrieveContributor(
            params: CommunityNoteRetrieveContributorParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<CommunityNoteRetrieveContributorResponse>

        /** @see retrieveContributor */
        @MustBeClosed
        suspend fun retrieveContributor(
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
         * is otherwise the same as [CommunityNoteServiceAsync.listContributorNotes].
         */
        @MustBeClosed
        suspend fun listContributorNotes(
            alias: String,
            params: CommunityNoteListContributorNotesParams =
                CommunityNoteListContributorNotesParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<CommunityNoteListContributorNotesResponse> =
            listContributorNotes(params.toBuilder().alias(alias).build(), requestOptions)

        /** @see listContributorNotes */
        @MustBeClosed
        suspend fun listContributorNotes(
            params: CommunityNoteListContributorNotesParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<CommunityNoteListContributorNotesResponse>

        /** @see listContributorNotes */
        @MustBeClosed
        suspend fun listContributorNotes(
            alias: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<CommunityNoteListContributorNotesResponse> =
            listContributorNotes(
                alias,
                CommunityNoteListContributorNotesParams.none(),
                requestOptions,
            )

        /**
         * Returns a raw HTTP response for `get
         * /x/community-notes/contributors/{alias}/deleted-notes`, but is otherwise the same as
         * [CommunityNoteServiceAsync.listContributorDeletedNotes].
         */
        @MustBeClosed
        suspend fun listContributorDeletedNotes(
            alias: String,
            params: CommunityNoteListContributorDeletedNotesParams =
                CommunityNoteListContributorDeletedNotesParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<CommunityNoteListContributorDeletedNotesResponse> =
            listContributorDeletedNotes(params.toBuilder().alias(alias).build(), requestOptions)

        /** @see listContributorDeletedNotes */
        @MustBeClosed
        suspend fun listContributorDeletedNotes(
            params: CommunityNoteListContributorDeletedNotesParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<CommunityNoteListContributorDeletedNotesResponse>

        /** @see listContributorDeletedNotes */
        @MustBeClosed
        suspend fun listContributorDeletedNotes(
            alias: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<CommunityNoteListContributorDeletedNotesResponse> =
            listContributorDeletedNotes(
                alias,
                CommunityNoteListContributorDeletedNotesParams.none(),
                requestOptions,
            )
    }
}
