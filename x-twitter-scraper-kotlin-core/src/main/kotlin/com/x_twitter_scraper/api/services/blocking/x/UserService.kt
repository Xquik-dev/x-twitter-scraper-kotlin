// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.blocking.x

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponse
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.x.users.UserRetrieveBatchParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveFollowersParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveFollowersYouKnowParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveFollowersYouKnowResponse
import com.x_twitter_scraper.api.models.x.users.UserRetrieveFollowingParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveLikesParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveLikesResponse
import com.x_twitter_scraper.api.models.x.users.UserRetrieveMediaParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveMediaResponse
import com.x_twitter_scraper.api.models.x.users.UserRetrieveMentionsParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveResponse
import com.x_twitter_scraper.api.models.x.users.UserRetrieveSearchParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveTweetsParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveTweetsResponse
import com.x_twitter_scraper.api.models.x.users.UserRetrieveVerifiedFollowersParams
import com.x_twitter_scraper.api.services.blocking.x.users.FollowService

/** X data lookups (subscription required) */
interface UserService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: (ClientOptions.Builder) -> Unit): UserService

    /** X write actions (tweets, likes, follows, DMs) */
    fun follow(): FollowService

    /** Look up X user */
    fun retrieve(
        username: String,
        params: UserRetrieveParams = UserRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserRetrieveResponse =
        retrieve(params.toBuilder().username(username).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        params: UserRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserRetrieveResponse

    /** @see retrieve */
    fun retrieve(username: String, requestOptions: RequestOptions): UserRetrieveResponse =
        retrieve(username, UserRetrieveParams.none(), requestOptions)

    /** Get multiple users by IDs */
    fun retrieveBatch(
        params: UserRetrieveBatchParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    )

    /** Get user followers */
    fun retrieveFollowers(
        id: String,
        params: UserRetrieveFollowersParams = UserRetrieveFollowersParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ) = retrieveFollowers(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieveFollowers */
    fun retrieveFollowers(
        params: UserRetrieveFollowersParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    )

    /** @see retrieveFollowers */
    fun retrieveFollowers(id: String, requestOptions: RequestOptions) =
        retrieveFollowers(id, UserRetrieveFollowersParams.none(), requestOptions)

    /** Get followers you know for a user */
    fun retrieveFollowersYouKnow(
        id: String,
        params: UserRetrieveFollowersYouKnowParams = UserRetrieveFollowersYouKnowParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserRetrieveFollowersYouKnowResponse =
        retrieveFollowersYouKnow(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieveFollowersYouKnow */
    fun retrieveFollowersYouKnow(
        params: UserRetrieveFollowersYouKnowParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserRetrieveFollowersYouKnowResponse

    /** @see retrieveFollowersYouKnow */
    fun retrieveFollowersYouKnow(
        id: String,
        requestOptions: RequestOptions,
    ): UserRetrieveFollowersYouKnowResponse =
        retrieveFollowersYouKnow(id, UserRetrieveFollowersYouKnowParams.none(), requestOptions)

    /** Get users this user follows */
    fun retrieveFollowing(
        id: String,
        params: UserRetrieveFollowingParams = UserRetrieveFollowingParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ) = retrieveFollowing(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieveFollowing */
    fun retrieveFollowing(
        params: UserRetrieveFollowingParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    )

    /** @see retrieveFollowing */
    fun retrieveFollowing(id: String, requestOptions: RequestOptions) =
        retrieveFollowing(id, UserRetrieveFollowingParams.none(), requestOptions)

    /** Get tweets liked by a user */
    fun retrieveLikes(
        id: String,
        params: UserRetrieveLikesParams = UserRetrieveLikesParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserRetrieveLikesResponse = retrieveLikes(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieveLikes */
    fun retrieveLikes(
        params: UserRetrieveLikesParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserRetrieveLikesResponse

    /** @see retrieveLikes */
    fun retrieveLikes(id: String, requestOptions: RequestOptions): UserRetrieveLikesResponse =
        retrieveLikes(id, UserRetrieveLikesParams.none(), requestOptions)

    /** Get media tweets by a user */
    fun retrieveMedia(
        id: String,
        params: UserRetrieveMediaParams = UserRetrieveMediaParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserRetrieveMediaResponse = retrieveMedia(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieveMedia */
    fun retrieveMedia(
        params: UserRetrieveMediaParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserRetrieveMediaResponse

    /** @see retrieveMedia */
    fun retrieveMedia(id: String, requestOptions: RequestOptions): UserRetrieveMediaResponse =
        retrieveMedia(id, UserRetrieveMediaParams.none(), requestOptions)

    /** Get tweets mentioning a user */
    fun retrieveMentions(
        id: String,
        params: UserRetrieveMentionsParams = UserRetrieveMentionsParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ) = retrieveMentions(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieveMentions */
    fun retrieveMentions(
        params: UserRetrieveMentionsParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    )

    /** @see retrieveMentions */
    fun retrieveMentions(id: String, requestOptions: RequestOptions) =
        retrieveMentions(id, UserRetrieveMentionsParams.none(), requestOptions)

    /** Search users by name or username */
    fun retrieveSearch(
        params: UserRetrieveSearchParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    )

    /** Get recent tweets by a user */
    fun retrieveTweets(
        id: String,
        params: UserRetrieveTweetsParams = UserRetrieveTweetsParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserRetrieveTweetsResponse =
        retrieveTweets(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieveTweets */
    fun retrieveTweets(
        params: UserRetrieveTweetsParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserRetrieveTweetsResponse

    /** @see retrieveTweets */
    fun retrieveTweets(id: String, requestOptions: RequestOptions): UserRetrieveTweetsResponse =
        retrieveTweets(id, UserRetrieveTweetsParams.none(), requestOptions)

    /** Get verified followers */
    fun retrieveVerifiedFollowers(
        id: String,
        params: UserRetrieveVerifiedFollowersParams = UserRetrieveVerifiedFollowersParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ) = retrieveVerifiedFollowers(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieveVerifiedFollowers */
    fun retrieveVerifiedFollowers(
        params: UserRetrieveVerifiedFollowersParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    )

    /** @see retrieveVerifiedFollowers */
    fun retrieveVerifiedFollowers(id: String, requestOptions: RequestOptions) =
        retrieveVerifiedFollowers(id, UserRetrieveVerifiedFollowersParams.none(), requestOptions)

    /** A view of [UserService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: (ClientOptions.Builder) -> Unit): UserService.WithRawResponse

        /** X write actions (tweets, likes, follows, DMs) */
        fun follow(): FollowService.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /x/users/{username}`, but is otherwise the same as
         * [UserService.retrieve].
         */
        @MustBeClosed
        fun retrieve(
            username: String,
            params: UserRetrieveParams = UserRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UserRetrieveResponse> =
            retrieve(params.toBuilder().username(username).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: UserRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UserRetrieveResponse>

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            username: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<UserRetrieveResponse> =
            retrieve(username, UserRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/users/batch`, but is otherwise the same as
         * [UserService.retrieveBatch].
         */
        @MustBeClosed
        fun retrieveBatch(
            params: UserRetrieveBatchParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponse

        /**
         * Returns a raw HTTP response for `get /x/users/{id}/followers`, but is otherwise the same
         * as [UserService.retrieveFollowers].
         */
        @MustBeClosed
        fun retrieveFollowers(
            id: String,
            params: UserRetrieveFollowersParams = UserRetrieveFollowersParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponse = retrieveFollowers(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieveFollowers */
        @MustBeClosed
        fun retrieveFollowers(
            params: UserRetrieveFollowersParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponse

        /** @see retrieveFollowers */
        @MustBeClosed
        fun retrieveFollowers(id: String, requestOptions: RequestOptions): HttpResponse =
            retrieveFollowers(id, UserRetrieveFollowersParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/users/{id}/followers-you-know`, but is otherwise
         * the same as [UserService.retrieveFollowersYouKnow].
         */
        @MustBeClosed
        fun retrieveFollowersYouKnow(
            id: String,
            params: UserRetrieveFollowersYouKnowParams = UserRetrieveFollowersYouKnowParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UserRetrieveFollowersYouKnowResponse> =
            retrieveFollowersYouKnow(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieveFollowersYouKnow */
        @MustBeClosed
        fun retrieveFollowersYouKnow(
            params: UserRetrieveFollowersYouKnowParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UserRetrieveFollowersYouKnowResponse>

        /** @see retrieveFollowersYouKnow */
        @MustBeClosed
        fun retrieveFollowersYouKnow(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<UserRetrieveFollowersYouKnowResponse> =
            retrieveFollowersYouKnow(id, UserRetrieveFollowersYouKnowParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/users/{id}/following`, but is otherwise the same
         * as [UserService.retrieveFollowing].
         */
        @MustBeClosed
        fun retrieveFollowing(
            id: String,
            params: UserRetrieveFollowingParams = UserRetrieveFollowingParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponse = retrieveFollowing(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieveFollowing */
        @MustBeClosed
        fun retrieveFollowing(
            params: UserRetrieveFollowingParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponse

        /** @see retrieveFollowing */
        @MustBeClosed
        fun retrieveFollowing(id: String, requestOptions: RequestOptions): HttpResponse =
            retrieveFollowing(id, UserRetrieveFollowingParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/users/{id}/likes`, but is otherwise the same as
         * [UserService.retrieveLikes].
         */
        @MustBeClosed
        fun retrieveLikes(
            id: String,
            params: UserRetrieveLikesParams = UserRetrieveLikesParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UserRetrieveLikesResponse> =
            retrieveLikes(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieveLikes */
        @MustBeClosed
        fun retrieveLikes(
            params: UserRetrieveLikesParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UserRetrieveLikesResponse>

        /** @see retrieveLikes */
        @MustBeClosed
        fun retrieveLikes(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<UserRetrieveLikesResponse> =
            retrieveLikes(id, UserRetrieveLikesParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/users/{id}/media`, but is otherwise the same as
         * [UserService.retrieveMedia].
         */
        @MustBeClosed
        fun retrieveMedia(
            id: String,
            params: UserRetrieveMediaParams = UserRetrieveMediaParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UserRetrieveMediaResponse> =
            retrieveMedia(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieveMedia */
        @MustBeClosed
        fun retrieveMedia(
            params: UserRetrieveMediaParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UserRetrieveMediaResponse>

        /** @see retrieveMedia */
        @MustBeClosed
        fun retrieveMedia(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<UserRetrieveMediaResponse> =
            retrieveMedia(id, UserRetrieveMediaParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/users/{id}/mentions`, but is otherwise the same
         * as [UserService.retrieveMentions].
         */
        @MustBeClosed
        fun retrieveMentions(
            id: String,
            params: UserRetrieveMentionsParams = UserRetrieveMentionsParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponse = retrieveMentions(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieveMentions */
        @MustBeClosed
        fun retrieveMentions(
            params: UserRetrieveMentionsParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponse

        /** @see retrieveMentions */
        @MustBeClosed
        fun retrieveMentions(id: String, requestOptions: RequestOptions): HttpResponse =
            retrieveMentions(id, UserRetrieveMentionsParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/users/search`, but is otherwise the same as
         * [UserService.retrieveSearch].
         */
        @MustBeClosed
        fun retrieveSearch(
            params: UserRetrieveSearchParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponse

        /**
         * Returns a raw HTTP response for `get /x/users/{id}/tweets`, but is otherwise the same as
         * [UserService.retrieveTweets].
         */
        @MustBeClosed
        fun retrieveTweets(
            id: String,
            params: UserRetrieveTweetsParams = UserRetrieveTweetsParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UserRetrieveTweetsResponse> =
            retrieveTweets(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieveTweets */
        @MustBeClosed
        fun retrieveTweets(
            params: UserRetrieveTweetsParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UserRetrieveTweetsResponse>

        /** @see retrieveTweets */
        @MustBeClosed
        fun retrieveTweets(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<UserRetrieveTweetsResponse> =
            retrieveTweets(id, UserRetrieveTweetsParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/users/{id}/verified-followers`, but is otherwise
         * the same as [UserService.retrieveVerifiedFollowers].
         */
        @MustBeClosed
        fun retrieveVerifiedFollowers(
            id: String,
            params: UserRetrieveVerifiedFollowersParams =
                UserRetrieveVerifiedFollowersParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponse =
            retrieveVerifiedFollowers(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieveVerifiedFollowers */
        @MustBeClosed
        fun retrieveVerifiedFollowers(
            params: UserRetrieveVerifiedFollowersParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponse

        /** @see retrieveVerifiedFollowers */
        @MustBeClosed
        fun retrieveVerifiedFollowers(id: String, requestOptions: RequestOptions): HttpResponse =
            retrieveVerifiedFollowers(
                id,
                UserRetrieveVerifiedFollowersParams.none(),
                requestOptions,
            )
    }
}
