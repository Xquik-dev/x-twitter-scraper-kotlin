// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async.x

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.PaginatedTweets
import com.x_twitter_scraper.api.models.PaginatedUsers
import com.x_twitter_scraper.api.models.UserProfile
import com.x_twitter_scraper.api.models.x.users.UserRemoveFollowerParams
import com.x_twitter_scraper.api.models.x.users.UserRemoveFollowerResponse
import com.x_twitter_scraper.api.models.x.users.UserRetrieveBatchParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveBatchResponse
import com.x_twitter_scraper.api.models.x.users.UserRetrieveFollowersParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveFollowersResponse
import com.x_twitter_scraper.api.models.x.users.UserRetrieveFollowersYouKnowParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveFollowingParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveFollowingResponse
import com.x_twitter_scraper.api.models.x.users.UserRetrieveLikesParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveMediaParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveMentionsParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveRepliesParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveSearchParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveTweetsParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveVerifiedFollowersParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveVerifiedFollowersResponse
import com.x_twitter_scraper.api.services.async.x.users.FollowServiceAsync

interface UserServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: (ClientOptions.Builder) -> Unit): UserServiceAsync

    /** X write actions (tweets, likes, follows, DMs) */
    fun follow(): FollowServiceAsync

    /** Get user profile with follower counts and verification */
    suspend fun retrieve(
        id: String,
        params: UserRetrieveParams = UserRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserProfile = retrieve(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieve */
    suspend fun retrieve(
        params: UserRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserProfile

    /** @see retrieve */
    suspend fun retrieve(id: String, requestOptions: RequestOptions): UserProfile =
        retrieve(id, UserRetrieveParams.none(), requestOptions)

    /** Remove follower */
    suspend fun removeFollower(
        id: String,
        params: UserRemoveFollowerParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserRemoveFollowerResponse =
        removeFollower(params.toBuilder().id(id).build(), requestOptions)

    /** @see removeFollower */
    suspend fun removeFollower(
        params: UserRemoveFollowerParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserRemoveFollowerResponse

    /** Look up multiple users by IDs in one call */
    suspend fun retrieveBatch(
        params: UserRetrieveBatchParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserRetrieveBatchResponse

    /** List followers of a user */
    suspend fun retrieveFollowers(
        id: String,
        params: UserRetrieveFollowersParams = UserRetrieveFollowersParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserRetrieveFollowersResponse =
        retrieveFollowers(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieveFollowers */
    suspend fun retrieveFollowers(
        params: UserRetrieveFollowersParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserRetrieveFollowersResponse

    /** @see retrieveFollowers */
    suspend fun retrieveFollowers(
        id: String,
        requestOptions: RequestOptions,
    ): UserRetrieveFollowersResponse =
        retrieveFollowers(id, UserRetrieveFollowersParams.none(), requestOptions)

    /** List mutual followers between you and a user */
    suspend fun retrieveFollowersYouKnow(
        id: String,
        params: UserRetrieveFollowersYouKnowParams = UserRetrieveFollowersYouKnowParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedUsers = retrieveFollowersYouKnow(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieveFollowersYouKnow */
    suspend fun retrieveFollowersYouKnow(
        params: UserRetrieveFollowersYouKnowParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedUsers

    /** @see retrieveFollowersYouKnow */
    suspend fun retrieveFollowersYouKnow(
        id: String,
        requestOptions: RequestOptions,
    ): PaginatedUsers =
        retrieveFollowersYouKnow(id, UserRetrieveFollowersYouKnowParams.none(), requestOptions)

    /** List accounts a user follows */
    suspend fun retrieveFollowing(
        id: String,
        params: UserRetrieveFollowingParams = UserRetrieveFollowingParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserRetrieveFollowingResponse =
        retrieveFollowing(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieveFollowing */
    suspend fun retrieveFollowing(
        params: UserRetrieveFollowingParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserRetrieveFollowingResponse

    /** @see retrieveFollowing */
    suspend fun retrieveFollowing(
        id: String,
        requestOptions: RequestOptions,
    ): UserRetrieveFollowingResponse =
        retrieveFollowing(id, UserRetrieveFollowingParams.none(), requestOptions)

    /** List tweets liked by a user */
    suspend fun retrieveLikes(
        id: String,
        params: UserRetrieveLikesParams = UserRetrieveLikesParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedTweets = retrieveLikes(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieveLikes */
    suspend fun retrieveLikes(
        params: UserRetrieveLikesParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedTweets

    /** @see retrieveLikes */
    suspend fun retrieveLikes(id: String, requestOptions: RequestOptions): PaginatedTweets =
        retrieveLikes(id, UserRetrieveLikesParams.none(), requestOptions)

    /** List media tweets posted by a user */
    suspend fun retrieveMedia(
        id: String,
        params: UserRetrieveMediaParams = UserRetrieveMediaParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedTweets = retrieveMedia(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieveMedia */
    suspend fun retrieveMedia(
        params: UserRetrieveMediaParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedTweets

    /** @see retrieveMedia */
    suspend fun retrieveMedia(id: String, requestOptions: RequestOptions): PaginatedTweets =
        retrieveMedia(id, UserRetrieveMediaParams.none(), requestOptions)

    /** List tweets mentioning a user */
    suspend fun retrieveMentions(
        id: String,
        params: UserRetrieveMentionsParams = UserRetrieveMentionsParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedTweets = retrieveMentions(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieveMentions */
    suspend fun retrieveMentions(
        params: UserRetrieveMentionsParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedTweets

    /** @see retrieveMentions */
    suspend fun retrieveMentions(id: String, requestOptions: RequestOptions): PaginatedTweets =
        retrieveMentions(id, UserRetrieveMentionsParams.none(), requestOptions)

    /**
     * Returns target-authored posts and replies. Omit mode for automatic maximum coverage. Pass
     * next_cursor unchanged. Unprefixed cursors stay legacy. Excludes other-author context.
     */
    suspend fun retrieveReplies(
        id: String,
        params: UserRetrieveRepliesParams = UserRetrieveRepliesParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedTweets = retrieveReplies(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieveReplies */
    suspend fun retrieveReplies(
        params: UserRetrieveRepliesParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedTweets

    /** @see retrieveReplies */
    suspend fun retrieveReplies(id: String, requestOptions: RequestOptions): PaginatedTweets =
        retrieveReplies(id, UserRetrieveRepliesParams.none(), requestOptions)

    /** Search users by name or username */
    suspend fun retrieveSearch(
        params: UserRetrieveSearchParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedUsers

    /**
     * Omit mode for automatic maximum coverage. Pass next_cursor unchanged. Unprefixed cursors use
     * legacy pagination. Shape and billing stay the same.
     */
    suspend fun retrieveTweets(
        id: String,
        params: UserRetrieveTweetsParams = UserRetrieveTweetsParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedTweets = retrieveTweets(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieveTweets */
    suspend fun retrieveTweets(
        params: UserRetrieveTweetsParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedTweets

    /** @see retrieveTweets */
    suspend fun retrieveTweets(id: String, requestOptions: RequestOptions): PaginatedTweets =
        retrieveTweets(id, UserRetrieveTweetsParams.none(), requestOptions)

    /** List verified followers of a user */
    suspend fun retrieveVerifiedFollowers(
        id: String,
        params: UserRetrieveVerifiedFollowersParams = UserRetrieveVerifiedFollowersParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserRetrieveVerifiedFollowersResponse =
        retrieveVerifiedFollowers(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieveVerifiedFollowers */
    suspend fun retrieveVerifiedFollowers(
        params: UserRetrieveVerifiedFollowersParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserRetrieveVerifiedFollowersResponse

    /** @see retrieveVerifiedFollowers */
    suspend fun retrieveVerifiedFollowers(
        id: String,
        requestOptions: RequestOptions,
    ): UserRetrieveVerifiedFollowersResponse =
        retrieveVerifiedFollowers(id, UserRetrieveVerifiedFollowersParams.none(), requestOptions)

    /** A view of [UserServiceAsync] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: (ClientOptions.Builder) -> Unit): UserServiceAsync.WithRawResponse

        /** X write actions (tweets, likes, follows, DMs) */
        fun follow(): FollowServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /x/users/{id}`, but is otherwise the same as
         * [UserServiceAsync.retrieve].
         */
        @MustBeClosed
        suspend fun retrieve(
            id: String,
            params: UserRetrieveParams = UserRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UserProfile> =
            retrieve(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        suspend fun retrieve(
            params: UserRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UserProfile>

        /** @see retrieve */
        @MustBeClosed
        suspend fun retrieve(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<UserProfile> = retrieve(id, UserRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /x/users/{id}/remove-follower`, but is otherwise
         * the same as [UserServiceAsync.removeFollower].
         */
        @MustBeClosed
        suspend fun removeFollower(
            id: String,
            params: UserRemoveFollowerParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UserRemoveFollowerResponse> =
            removeFollower(params.toBuilder().id(id).build(), requestOptions)

        /** @see removeFollower */
        @MustBeClosed
        suspend fun removeFollower(
            params: UserRemoveFollowerParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UserRemoveFollowerResponse>

        /**
         * Returns a raw HTTP response for `get /x/users/batch`, but is otherwise the same as
         * [UserServiceAsync.retrieveBatch].
         */
        @MustBeClosed
        suspend fun retrieveBatch(
            params: UserRetrieveBatchParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UserRetrieveBatchResponse>

        /**
         * Returns a raw HTTP response for `get /x/users/{id}/followers`, but is otherwise the same
         * as [UserServiceAsync.retrieveFollowers].
         */
        @MustBeClosed
        suspend fun retrieveFollowers(
            id: String,
            params: UserRetrieveFollowersParams = UserRetrieveFollowersParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UserRetrieveFollowersResponse> =
            retrieveFollowers(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieveFollowers */
        @MustBeClosed
        suspend fun retrieveFollowers(
            params: UserRetrieveFollowersParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UserRetrieveFollowersResponse>

        /** @see retrieveFollowers */
        @MustBeClosed
        suspend fun retrieveFollowers(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<UserRetrieveFollowersResponse> =
            retrieveFollowers(id, UserRetrieveFollowersParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/users/{id}/followers-you-know`, but is otherwise
         * the same as [UserServiceAsync.retrieveFollowersYouKnow].
         */
        @MustBeClosed
        suspend fun retrieveFollowersYouKnow(
            id: String,
            params: UserRetrieveFollowersYouKnowParams = UserRetrieveFollowersYouKnowParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedUsers> =
            retrieveFollowersYouKnow(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieveFollowersYouKnow */
        @MustBeClosed
        suspend fun retrieveFollowersYouKnow(
            params: UserRetrieveFollowersYouKnowParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedUsers>

        /** @see retrieveFollowersYouKnow */
        @MustBeClosed
        suspend fun retrieveFollowersYouKnow(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<PaginatedUsers> =
            retrieveFollowersYouKnow(id, UserRetrieveFollowersYouKnowParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/users/{id}/following`, but is otherwise the same
         * as [UserServiceAsync.retrieveFollowing].
         */
        @MustBeClosed
        suspend fun retrieveFollowing(
            id: String,
            params: UserRetrieveFollowingParams = UserRetrieveFollowingParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UserRetrieveFollowingResponse> =
            retrieveFollowing(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieveFollowing */
        @MustBeClosed
        suspend fun retrieveFollowing(
            params: UserRetrieveFollowingParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UserRetrieveFollowingResponse>

        /** @see retrieveFollowing */
        @MustBeClosed
        suspend fun retrieveFollowing(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<UserRetrieveFollowingResponse> =
            retrieveFollowing(id, UserRetrieveFollowingParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/users/{id}/likes`, but is otherwise the same as
         * [UserServiceAsync.retrieveLikes].
         */
        @MustBeClosed
        suspend fun retrieveLikes(
            id: String,
            params: UserRetrieveLikesParams = UserRetrieveLikesParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedTweets> =
            retrieveLikes(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieveLikes */
        @MustBeClosed
        suspend fun retrieveLikes(
            params: UserRetrieveLikesParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedTweets>

        /** @see retrieveLikes */
        @MustBeClosed
        suspend fun retrieveLikes(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<PaginatedTweets> =
            retrieveLikes(id, UserRetrieveLikesParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/users/{id}/media`, but is otherwise the same as
         * [UserServiceAsync.retrieveMedia].
         */
        @MustBeClosed
        suspend fun retrieveMedia(
            id: String,
            params: UserRetrieveMediaParams = UserRetrieveMediaParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedTweets> =
            retrieveMedia(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieveMedia */
        @MustBeClosed
        suspend fun retrieveMedia(
            params: UserRetrieveMediaParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedTweets>

        /** @see retrieveMedia */
        @MustBeClosed
        suspend fun retrieveMedia(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<PaginatedTweets> =
            retrieveMedia(id, UserRetrieveMediaParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/users/{id}/mentions`, but is otherwise the same
         * as [UserServiceAsync.retrieveMentions].
         */
        @MustBeClosed
        suspend fun retrieveMentions(
            id: String,
            params: UserRetrieveMentionsParams = UserRetrieveMentionsParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedTweets> =
            retrieveMentions(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieveMentions */
        @MustBeClosed
        suspend fun retrieveMentions(
            params: UserRetrieveMentionsParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedTweets>

        /** @see retrieveMentions */
        @MustBeClosed
        suspend fun retrieveMentions(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<PaginatedTweets> =
            retrieveMentions(id, UserRetrieveMentionsParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/users/{id}/replies`, but is otherwise the same as
         * [UserServiceAsync.retrieveReplies].
         */
        @MustBeClosed
        suspend fun retrieveReplies(
            id: String,
            params: UserRetrieveRepliesParams = UserRetrieveRepliesParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedTweets> =
            retrieveReplies(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieveReplies */
        @MustBeClosed
        suspend fun retrieveReplies(
            params: UserRetrieveRepliesParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedTweets>

        /** @see retrieveReplies */
        @MustBeClosed
        suspend fun retrieveReplies(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<PaginatedTweets> =
            retrieveReplies(id, UserRetrieveRepliesParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/users/search`, but is otherwise the same as
         * [UserServiceAsync.retrieveSearch].
         */
        @MustBeClosed
        suspend fun retrieveSearch(
            params: UserRetrieveSearchParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedUsers>

        /**
         * Returns a raw HTTP response for `get /x/users/{id}/tweets`, but is otherwise the same as
         * [UserServiceAsync.retrieveTweets].
         */
        @MustBeClosed
        suspend fun retrieveTweets(
            id: String,
            params: UserRetrieveTweetsParams = UserRetrieveTweetsParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedTweets> =
            retrieveTweets(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieveTweets */
        @MustBeClosed
        suspend fun retrieveTweets(
            params: UserRetrieveTweetsParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedTweets>

        /** @see retrieveTweets */
        @MustBeClosed
        suspend fun retrieveTweets(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<PaginatedTweets> =
            retrieveTweets(id, UserRetrieveTweetsParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/users/{id}/verified-followers`, but is otherwise
         * the same as [UserServiceAsync.retrieveVerifiedFollowers].
         */
        @MustBeClosed
        suspend fun retrieveVerifiedFollowers(
            id: String,
            params: UserRetrieveVerifiedFollowersParams =
                UserRetrieveVerifiedFollowersParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UserRetrieveVerifiedFollowersResponse> =
            retrieveVerifiedFollowers(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieveVerifiedFollowers */
        @MustBeClosed
        suspend fun retrieveVerifiedFollowers(
            params: UserRetrieveVerifiedFollowersParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UserRetrieveVerifiedFollowersResponse>

        /** @see retrieveVerifiedFollowers */
        @MustBeClosed
        suspend fun retrieveVerifiedFollowers(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<UserRetrieveVerifiedFollowersResponse> =
            retrieveVerifiedFollowers(
                id,
                UserRetrieveVerifiedFollowersParams.none(),
                requestOptions,
            )
    }
}
