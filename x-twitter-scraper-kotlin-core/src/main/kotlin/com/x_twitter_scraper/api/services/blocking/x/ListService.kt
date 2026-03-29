// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.blocking.x

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponse
import com.x_twitter_scraper.api.models.x.lists.ListRetrieveFollowersParams
import com.x_twitter_scraper.api.models.x.lists.ListRetrieveMembersParams
import com.x_twitter_scraper.api.models.x.lists.ListRetrieveTweetsParams

/** X data lookups (subscription required) */
interface ListService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: (ClientOptions.Builder) -> Unit): ListService

    /** Get list followers */
    fun retrieveFollowers(
        id: String,
        params: ListRetrieveFollowersParams = ListRetrieveFollowersParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ) = retrieveFollowers(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieveFollowers */
    fun retrieveFollowers(
        params: ListRetrieveFollowersParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    )

    /** @see retrieveFollowers */
    fun retrieveFollowers(id: String, requestOptions: RequestOptions) =
        retrieveFollowers(id, ListRetrieveFollowersParams.none(), requestOptions)

    /** Get list members */
    fun retrieveMembers(
        id: String,
        params: ListRetrieveMembersParams = ListRetrieveMembersParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ) = retrieveMembers(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieveMembers */
    fun retrieveMembers(
        params: ListRetrieveMembersParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    )

    /** @see retrieveMembers */
    fun retrieveMembers(id: String, requestOptions: RequestOptions) =
        retrieveMembers(id, ListRetrieveMembersParams.none(), requestOptions)

    /** Get list tweets */
    fun retrieveTweets(
        id: String,
        params: ListRetrieveTweetsParams = ListRetrieveTweetsParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ) = retrieveTweets(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieveTweets */
    fun retrieveTweets(
        params: ListRetrieveTweetsParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    )

    /** @see retrieveTweets */
    fun retrieveTweets(id: String, requestOptions: RequestOptions) =
        retrieveTweets(id, ListRetrieveTweetsParams.none(), requestOptions)

    /** A view of [ListService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: (ClientOptions.Builder) -> Unit): ListService.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /x/lists/{id}/followers`, but is otherwise the same
         * as [ListService.retrieveFollowers].
         */
        @MustBeClosed
        fun retrieveFollowers(
            id: String,
            params: ListRetrieveFollowersParams = ListRetrieveFollowersParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponse = retrieveFollowers(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieveFollowers */
        @MustBeClosed
        fun retrieveFollowers(
            params: ListRetrieveFollowersParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponse

        /** @see retrieveFollowers */
        @MustBeClosed
        fun retrieveFollowers(id: String, requestOptions: RequestOptions): HttpResponse =
            retrieveFollowers(id, ListRetrieveFollowersParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/lists/{id}/members`, but is otherwise the same as
         * [ListService.retrieveMembers].
         */
        @MustBeClosed
        fun retrieveMembers(
            id: String,
            params: ListRetrieveMembersParams = ListRetrieveMembersParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponse = retrieveMembers(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieveMembers */
        @MustBeClosed
        fun retrieveMembers(
            params: ListRetrieveMembersParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponse

        /** @see retrieveMembers */
        @MustBeClosed
        fun retrieveMembers(id: String, requestOptions: RequestOptions): HttpResponse =
            retrieveMembers(id, ListRetrieveMembersParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/lists/{id}/tweets`, but is otherwise the same as
         * [ListService.retrieveTweets].
         */
        @MustBeClosed
        fun retrieveTweets(
            id: String,
            params: ListRetrieveTweetsParams = ListRetrieveTweetsParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponse = retrieveTweets(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieveTweets */
        @MustBeClosed
        fun retrieveTweets(
            params: ListRetrieveTweetsParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponse

        /** @see retrieveTweets */
        @MustBeClosed
        fun retrieveTweets(id: String, requestOptions: RequestOptions): HttpResponse =
            retrieveTweets(id, ListRetrieveTweetsParams.none(), requestOptions)
    }
}
