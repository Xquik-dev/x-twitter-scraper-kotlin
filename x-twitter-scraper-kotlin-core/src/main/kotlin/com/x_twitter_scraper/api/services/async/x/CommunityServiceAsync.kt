// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.services.async.x

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.CommunityPage
import com.x_twitter_scraper.api.models.PaginatedTweets
import com.x_twitter_scraper.api.models.PaginatedUsers
import com.x_twitter_scraper.api.models.x.communities.CommunityCreateParams
import com.x_twitter_scraper.api.models.x.communities.CommunityCreateResponse
import com.x_twitter_scraper.api.models.x.communities.CommunityDeleteParams
import com.x_twitter_scraper.api.models.x.communities.CommunityDeleteResponse
import com.x_twitter_scraper.api.models.x.communities.CommunityFindParams
import com.x_twitter_scraper.api.models.x.communities.CommunityListPopularParams
import com.x_twitter_scraper.api.models.x.communities.CommunityListSuggestedParams
import com.x_twitter_scraper.api.models.x.communities.CommunityListSuggestedResponse
import com.x_twitter_scraper.api.models.x.communities.CommunityListTopicsParams
import com.x_twitter_scraper.api.models.x.communities.CommunityListTopicsResponse
import com.x_twitter_scraper.api.models.x.communities.CommunityRetrieveInfoParams
import com.x_twitter_scraper.api.models.x.communities.CommunityRetrieveInfoResponse
import com.x_twitter_scraper.api.models.x.communities.CommunityRetrieveMembersParams
import com.x_twitter_scraper.api.models.x.communities.CommunityRetrieveModeratorsParams
import com.x_twitter_scraper.api.models.x.communities.CommunityRetrieveSearchParams
import com.x_twitter_scraper.api.models.x.communities.CommunitySearchMembersParams
import com.x_twitter_scraper.api.services.async.x.communities.JoinServiceAsync
import com.x_twitter_scraper.api.services.async.x.communities.MediaServiceAsync
import com.x_twitter_scraper.api.services.async.x.communities.TweetServiceAsync

interface CommunityServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: (ClientOptions.Builder) -> Unit): CommunityServiceAsync

    /** X write actions (tweets, likes, follows, DMs) */
    fun join(): JoinServiceAsync

    /** X Community info, members, and tweets */
    fun tweets(): TweetServiceAsync

    /** Find X Communities & read their info, members, posts & media */
    fun media(): MediaServiceAsync

    /** Create community */
    suspend fun create(
        params: CommunityCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CommunityCreateResponse

    /** Delete community */
    suspend fun delete(
        id: String,
        params: CommunityDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CommunityDeleteResponse = delete(params.toBuilder().id(id).build(), requestOptions)

    /** @see delete */
    suspend fun delete(
        params: CommunityDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CommunityDeleteResponse

    /** Get community name, description and member count */
    suspend fun retrieveInfo(
        id: String,
        params: CommunityRetrieveInfoParams = CommunityRetrieveInfoParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CommunityRetrieveInfoResponse =
        retrieveInfo(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieveInfo */
    suspend fun retrieveInfo(
        params: CommunityRetrieveInfoParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CommunityRetrieveInfoResponse

    /** @see retrieveInfo */
    suspend fun retrieveInfo(
        id: String,
        requestOptions: RequestOptions,
    ): CommunityRetrieveInfoResponse =
        retrieveInfo(id, CommunityRetrieveInfoParams.none(), requestOptions)

    /** List members of a community */
    suspend fun retrieveMembers(
        id: String,
        params: CommunityRetrieveMembersParams = CommunityRetrieveMembersParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedUsers = retrieveMembers(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieveMembers */
    suspend fun retrieveMembers(
        params: CommunityRetrieveMembersParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedUsers

    /** @see retrieveMembers */
    suspend fun retrieveMembers(id: String, requestOptions: RequestOptions): PaginatedUsers =
        retrieveMembers(id, CommunityRetrieveMembersParams.none(), requestOptions)

    /** List moderators of a community */
    suspend fun retrieveModerators(
        id: String,
        params: CommunityRetrieveModeratorsParams = CommunityRetrieveModeratorsParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedUsers = retrieveModerators(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieveModerators */
    suspend fun retrieveModerators(
        params: CommunityRetrieveModeratorsParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedUsers

    /** @see retrieveModerators */
    suspend fun retrieveModerators(id: String, requestOptions: RequestOptions): PaginatedUsers =
        retrieveModerators(id, CommunityRetrieveModeratorsParams.none(), requestOptions)

    /** Returns tweets, not community records. Requires a Community ID. */
    suspend fun retrieveSearch(
        params: CommunityRetrieveSearchParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedTweets

    /**
     * Returns the communities X finds for a keyword, in X's order. Each community costs 1 credit.
     * To search the tweets in Communities, use /x/communities/search.
     */
    suspend fun find(
        params: CommunityFindParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CommunityPage

    /**
     * Returns the communities X ranks highest under a topic or subtopic. Name it by topicId or by
     * topic name from /x/communities/topics. Each community costs 1 credit. A missing or unknown
     * topic returns invalid_community_topic & lists the topic names.
     */
    suspend fun listPopular(
        params: CommunityListPopularParams = CommunityListPopularParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CommunityPage

    /** @see listPopular */
    suspend fun listPopular(requestOptions: RequestOptions): CommunityPage =
        listPopular(CommunityListPopularParams.none(), requestOptions)

    /**
     * Returns the communities X suggests, with their full public fields. X picks them per request,
     * so 2 calls can differ. If X suggests none, Xquik asks up to 2 more times. An empty list is
     * free. Each community costs 1 credit.
     */
    suspend fun listSuggested(
        params: CommunityListSuggestedParams = CommunityListSuggestedParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CommunityListSuggestedResponse

    /** @see listSuggested */
    suspend fun listSuggested(requestOptions: RequestOptions): CommunityListSuggestedResponse =
        listSuggested(CommunityListSuggestedParams.none(), requestOptions)

    /**
     * Returns the community topics with their subtopics. Pass an ID as topicId, or a name as topic,
     * to /x/communities/popular. Each top-level topic costs 1 credit, its subtopics included.
     */
    suspend fun listTopics(
        params: CommunityListTopicsParams = CommunityListTopicsParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CommunityListTopicsResponse

    /** @see listTopics */
    suspend fun listTopics(requestOptions: RequestOptions): CommunityListTopicsResponse =
        listTopics(CommunityListTopicsParams.none(), requestOptions)

    /**
     * Finds a community's members by name or username, as the search box on X's members page does.
     * X checks up to 10 accounts that match, & the answer holds the members among them, each with
     * its role. Each member costs 1 credit. An answer without members is free.
     */
    suspend fun searchMembers(
        id: String,
        params: CommunitySearchMembersParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedUsers = searchMembers(params.toBuilder().id(id).build(), requestOptions)

    /** @see searchMembers */
    suspend fun searchMembers(
        params: CommunitySearchMembersParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedUsers

    /**
     * A view of [CommunityServiceAsync] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: (ClientOptions.Builder) -> Unit
        ): CommunityServiceAsync.WithRawResponse

        /** X write actions (tweets, likes, follows, DMs) */
        fun join(): JoinServiceAsync.WithRawResponse

        /** X Community info, members, and tweets */
        fun tweets(): TweetServiceAsync.WithRawResponse

        /** Find X Communities & read their info, members, posts & media */
        fun media(): MediaServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /x/communities`, but is otherwise the same as
         * [CommunityServiceAsync.create].
         */
        @MustBeClosed
        suspend fun create(
            params: CommunityCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<CommunityCreateResponse>

        /**
         * Returns a raw HTTP response for `delete /x/communities/{id}`, but is otherwise the same
         * as [CommunityServiceAsync.delete].
         */
        @MustBeClosed
        suspend fun delete(
            id: String,
            params: CommunityDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<CommunityDeleteResponse> =
            delete(params.toBuilder().id(id).build(), requestOptions)

        /** @see delete */
        @MustBeClosed
        suspend fun delete(
            params: CommunityDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<CommunityDeleteResponse>

        /**
         * Returns a raw HTTP response for `get /x/communities/{id}/info`, but is otherwise the same
         * as [CommunityServiceAsync.retrieveInfo].
         */
        @MustBeClosed
        suspend fun retrieveInfo(
            id: String,
            params: CommunityRetrieveInfoParams = CommunityRetrieveInfoParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<CommunityRetrieveInfoResponse> =
            retrieveInfo(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieveInfo */
        @MustBeClosed
        suspend fun retrieveInfo(
            params: CommunityRetrieveInfoParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<CommunityRetrieveInfoResponse>

        /** @see retrieveInfo */
        @MustBeClosed
        suspend fun retrieveInfo(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<CommunityRetrieveInfoResponse> =
            retrieveInfo(id, CommunityRetrieveInfoParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/communities/{id}/members`, but is otherwise the
         * same as [CommunityServiceAsync.retrieveMembers].
         */
        @MustBeClosed
        suspend fun retrieveMembers(
            id: String,
            params: CommunityRetrieveMembersParams = CommunityRetrieveMembersParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedUsers> =
            retrieveMembers(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieveMembers */
        @MustBeClosed
        suspend fun retrieveMembers(
            params: CommunityRetrieveMembersParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedUsers>

        /** @see retrieveMembers */
        @MustBeClosed
        suspend fun retrieveMembers(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<PaginatedUsers> =
            retrieveMembers(id, CommunityRetrieveMembersParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/communities/{id}/moderators`, but is otherwise
         * the same as [CommunityServiceAsync.retrieveModerators].
         */
        @MustBeClosed
        suspend fun retrieveModerators(
            id: String,
            params: CommunityRetrieveModeratorsParams = CommunityRetrieveModeratorsParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedUsers> =
            retrieveModerators(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieveModerators */
        @MustBeClosed
        suspend fun retrieveModerators(
            params: CommunityRetrieveModeratorsParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedUsers>

        /** @see retrieveModerators */
        @MustBeClosed
        suspend fun retrieveModerators(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<PaginatedUsers> =
            retrieveModerators(id, CommunityRetrieveModeratorsParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/communities/search`, but is otherwise the same as
         * [CommunityServiceAsync.retrieveSearch].
         */
        @MustBeClosed
        suspend fun retrieveSearch(
            params: CommunityRetrieveSearchParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedTweets>

        /**
         * Returns a raw HTTP response for `get /x/communities/find`, but is otherwise the same as
         * [CommunityServiceAsync.find].
         */
        @MustBeClosed
        suspend fun find(
            params: CommunityFindParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<CommunityPage>

        /**
         * Returns a raw HTTP response for `get /x/communities/popular`, but is otherwise the same
         * as [CommunityServiceAsync.listPopular].
         */
        @MustBeClosed
        suspend fun listPopular(
            params: CommunityListPopularParams = CommunityListPopularParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<CommunityPage>

        /** @see listPopular */
        @MustBeClosed
        suspend fun listPopular(requestOptions: RequestOptions): HttpResponseFor<CommunityPage> =
            listPopular(CommunityListPopularParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/communities/suggested`, but is otherwise the same
         * as [CommunityServiceAsync.listSuggested].
         */
        @MustBeClosed
        suspend fun listSuggested(
            params: CommunityListSuggestedParams = CommunityListSuggestedParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<CommunityListSuggestedResponse>

        /** @see listSuggested */
        @MustBeClosed
        suspend fun listSuggested(
            requestOptions: RequestOptions
        ): HttpResponseFor<CommunityListSuggestedResponse> =
            listSuggested(CommunityListSuggestedParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/communities/topics`, but is otherwise the same as
         * [CommunityServiceAsync.listTopics].
         */
        @MustBeClosed
        suspend fun listTopics(
            params: CommunityListTopicsParams = CommunityListTopicsParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<CommunityListTopicsResponse>

        /** @see listTopics */
        @MustBeClosed
        suspend fun listTopics(
            requestOptions: RequestOptions
        ): HttpResponseFor<CommunityListTopicsResponse> =
            listTopics(CommunityListTopicsParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/communities/{id}/members/search`, but is
         * otherwise the same as [CommunityServiceAsync.searchMembers].
         */
        @MustBeClosed
        suspend fun searchMembers(
            id: String,
            params: CommunitySearchMembersParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedUsers> =
            searchMembers(params.toBuilder().id(id).build(), requestOptions)

        /** @see searchMembers */
        @MustBeClosed
        suspend fun searchMembers(
            params: CommunitySearchMembersParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedUsers>
    }
}
