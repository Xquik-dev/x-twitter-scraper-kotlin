// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.services.blocking.x

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.PaginatedTweets
import com.x_twitter_scraper.api.models.PaginatedUsers
import com.x_twitter_scraper.api.models.UserProfile
import com.x_twitter_scraper.api.models.x.users.UserRemoveFollowerParams
import com.x_twitter_scraper.api.models.x.users.UserRemoveFollowerResponse
import com.x_twitter_scraper.api.models.x.users.UserRetrieveAffiliatesParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveArticlesParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveBatchParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveBatchResponse
import com.x_twitter_scraper.api.models.x.users.UserRetrieveBatchTweetsParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveBatchTweetsResponse
import com.x_twitter_scraper.api.models.x.users.UserRetrieveFollowerIdsParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveFollowerIdsResponse
import com.x_twitter_scraper.api.models.x.users.UserRetrieveFollowersParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveFollowersResponse
import com.x_twitter_scraper.api.models.x.users.UserRetrieveFollowersYouKnowParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveFollowingIdsParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveFollowingIdsResponse
import com.x_twitter_scraper.api.models.x.users.UserRetrieveFollowingParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveFollowingResponse
import com.x_twitter_scraper.api.models.x.users.UserRetrieveHighlightsParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveInsightsParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveInsightsResponse
import com.x_twitter_scraper.api.models.x.users.UserRetrieveLikesParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveListMembershipsParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveListMembershipsResponse
import com.x_twitter_scraper.api.models.x.users.UserRetrieveListsParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveListsResponse
import com.x_twitter_scraper.api.models.x.users.UserRetrieveLiveParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveLiveResponse
import com.x_twitter_scraper.api.models.x.users.UserRetrieveMediaParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveMentionsParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveRepliesParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveSearchParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveSimilarParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveSubscriptionsParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveTweetsParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveVerifiedFollowersParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveVerifiedFollowersResponse
import com.x_twitter_scraper.api.models.x.users.UserTranslateBioParams
import com.x_twitter_scraper.api.models.x.users.UserTranslateBioResponse
import com.x_twitter_scraper.api.services.blocking.x.users.FollowService

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

    /** Get user profile with follower counts and verification */
    fun retrieve(
        id: String,
        params: UserRetrieveParams = UserRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserProfile = retrieve(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        params: UserRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserProfile

    /** @see retrieve */
    fun retrieve(id: String, requestOptions: RequestOptions): UserProfile =
        retrieve(id, UserRetrieveParams.none(), requestOptions)

    /** Remove follower */
    fun removeFollower(
        id: String,
        params: UserRemoveFollowerParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserRemoveFollowerResponse =
        removeFollower(params.toBuilder().id(id).build(), requestOptions)

    /** @see removeFollower */
    fun removeFollower(
        params: UserRemoveFollowerParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserRemoveFollowerResponse

    /** Look up multiple users by IDs in one call */
    fun retrieveBatch(
        params: UserRetrieveBatchParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserRetrieveBatchResponse

    /** List followers of a user */
    fun retrieveFollowers(
        id: String,
        params: UserRetrieveFollowersParams = UserRetrieveFollowersParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserRetrieveFollowersResponse =
        retrieveFollowers(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieveFollowers */
    fun retrieveFollowers(
        params: UserRetrieveFollowersParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserRetrieveFollowersResponse

    /** @see retrieveFollowers */
    fun retrieveFollowers(
        id: String,
        requestOptions: RequestOptions,
    ): UserRetrieveFollowersResponse =
        retrieveFollowers(id, UserRetrieveFollowersParams.none(), requestOptions)

    /** List mutual followers between you and a user */
    fun retrieveFollowersYouKnow(
        id: String,
        params: UserRetrieveFollowersYouKnowParams = UserRetrieveFollowersYouKnowParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedUsers = retrieveFollowersYouKnow(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieveFollowersYouKnow */
    fun retrieveFollowersYouKnow(
        params: UserRetrieveFollowersYouKnowParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedUsers

    /** @see retrieveFollowersYouKnow */
    fun retrieveFollowersYouKnow(id: String, requestOptions: RequestOptions): PaginatedUsers =
        retrieveFollowersYouKnow(id, UserRetrieveFollowersYouKnowParams.none(), requestOptions)

    /** List accounts a user follows */
    fun retrieveFollowing(
        id: String,
        params: UserRetrieveFollowingParams = UserRetrieveFollowingParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserRetrieveFollowingResponse =
        retrieveFollowing(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieveFollowing */
    fun retrieveFollowing(
        params: UserRetrieveFollowingParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserRetrieveFollowingResponse

    /** @see retrieveFollowing */
    fun retrieveFollowing(
        id: String,
        requestOptions: RequestOptions,
    ): UserRetrieveFollowingResponse =
        retrieveFollowing(id, UserRetrieveFollowingParams.none(), requestOptions)

    /** List tweets liked by a user */
    fun retrieveLikes(
        id: String,
        params: UserRetrieveLikesParams = UserRetrieveLikesParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedTweets = retrieveLikes(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieveLikes */
    fun retrieveLikes(
        params: UserRetrieveLikesParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedTweets

    /** @see retrieveLikes */
    fun retrieveLikes(id: String, requestOptions: RequestOptions): PaginatedTweets =
        retrieveLikes(id, UserRetrieveLikesParams.none(), requestOptions)

    /** List media tweets posted by a user */
    fun retrieveMedia(
        id: String,
        params: UserRetrieveMediaParams = UserRetrieveMediaParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedTweets = retrieveMedia(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieveMedia */
    fun retrieveMedia(
        params: UserRetrieveMediaParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedTweets

    /** @see retrieveMedia */
    fun retrieveMedia(id: String, requestOptions: RequestOptions): PaginatedTweets =
        retrieveMedia(id, UserRetrieveMediaParams.none(), requestOptions)

    /** List tweets mentioning a user */
    fun retrieveMentions(
        id: String,
        params: UserRetrieveMentionsParams = UserRetrieveMentionsParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedTweets = retrieveMentions(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieveMentions */
    fun retrieveMentions(
        params: UserRetrieveMentionsParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedTweets

    /** @see retrieveMentions */
    fun retrieveMentions(id: String, requestOptions: RequestOptions): PaginatedTweets =
        retrieveMentions(id, UserRetrieveMentionsParams.none(), requestOptions)

    /**
     * Returns target-authored posts and replies. Omit mode for automatic maximum coverage. Pass
     * next_cursor unchanged. Unprefixed cursors stay legacy. Excludes other-author context.
     */
    fun retrieveReplies(
        id: String,
        params: UserRetrieveRepliesParams = UserRetrieveRepliesParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedTweets = retrieveReplies(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieveReplies */
    fun retrieveReplies(
        params: UserRetrieveRepliesParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedTweets

    /** @see retrieveReplies */
    fun retrieveReplies(id: String, requestOptions: RequestOptions): PaginatedTweets =
        retrieveReplies(id, UserRetrieveRepliesParams.none(), requestOptions)

    /** Search users by name or username */
    fun retrieveSearch(
        params: UserRetrieveSearchParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedUsers

    /**
     * Omit mode for automatic maximum coverage. Pass next_cursor unchanged. Unprefixed cursors use
     * legacy pagination. Shape and billing stay the same.
     */
    fun retrieveTweets(
        id: String,
        params: UserRetrieveTweetsParams = UserRetrieveTweetsParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedTweets = retrieveTweets(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieveTweets */
    fun retrieveTweets(
        params: UserRetrieveTweetsParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedTweets

    /** @see retrieveTweets */
    fun retrieveTweets(id: String, requestOptions: RequestOptions): PaginatedTweets =
        retrieveTweets(id, UserRetrieveTweetsParams.none(), requestOptions)

    /** List verified followers of a user */
    fun retrieveVerifiedFollowers(
        id: String,
        params: UserRetrieveVerifiedFollowersParams = UserRetrieveVerifiedFollowersParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserRetrieveVerifiedFollowersResponse =
        retrieveVerifiedFollowers(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieveVerifiedFollowers */
    fun retrieveVerifiedFollowers(
        params: UserRetrieveVerifiedFollowersParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserRetrieveVerifiedFollowersResponse

    /** @see retrieveVerifiedFollowers */
    fun retrieveVerifiedFollowers(
        id: String,
        requestOptions: RequestOptions,
    ): UserRetrieveVerifiedFollowersResponse =
        retrieveVerifiedFollowers(id, UserRetrieveVerifiedFollowersParams.none(), requestOptions)

    /**
     * Returns the newest posts of up to 20 users, named by user ID, username or profile link, in
     * the order named. Send ids or usernames, not both. Each post costs 1 credit. A user in
     * unavailable_ids or unprocessed_ids costs nothing.
     */
    fun retrieveBatchTweets(
        params: UserRetrieveBatchTweetsParams = UserRetrieveBatchTweetsParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserRetrieveBatchTweetsResponse

    /** @see retrieveBatchTweets */
    fun retrieveBatchTweets(requestOptions: RequestOptions): UserRetrieveBatchTweetsResponse =
        retrieveBatchTweets(UserRetrieveBatchTweetsParams.none(), requestOptions)

    /**
     * Returns the accounts an organization lists as its affiliates, in X's order. An account
     * without affiliates returns an empty list.
     */
    fun retrieveAffiliates(
        id: String,
        params: UserRetrieveAffiliatesParams = UserRetrieveAffiliatesParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedUsers = retrieveAffiliates(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieveAffiliates */
    fun retrieveAffiliates(
        params: UserRetrieveAffiliatesParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedUsers

    /** @see retrieveAffiliates */
    fun retrieveAffiliates(id: String, requestOptions: RequestOptions): PaginatedUsers =
        retrieveAffiliates(id, UserRetrieveAffiliatesParams.none(), requestOptions)

    /**
     * Returns the posts on the user's Articles tab, newest first. Each post's `article` holds the
     * Article's ID, title, preview, cover & dates. Read the full text with GET
     * /x/articles/{tweetId}.
     */
    fun retrieveArticles(
        id: String,
        params: UserRetrieveArticlesParams = UserRetrieveArticlesParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedTweets = retrieveArticles(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieveArticles */
    fun retrieveArticles(
        params: UserRetrieveArticlesParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedTweets

    /** @see retrieveArticles */
    fun retrieveArticles(id: String, requestOptions: RequestOptions): PaginatedTweets =
        retrieveArticles(id, UserRetrieveArticlesParams.none(), requestOptions)

    /**
     * Returns the user IDs of the selected user's followers, newest first, up to 5,000 a page. Use
     * it when you need IDs only. Each ID costs 1 credit.
     */
    fun retrieveFollowerIds(
        id: String,
        params: UserRetrieveFollowerIdsParams = UserRetrieveFollowerIdsParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserRetrieveFollowerIdsResponse =
        retrieveFollowerIds(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieveFollowerIds */
    fun retrieveFollowerIds(
        params: UserRetrieveFollowerIdsParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserRetrieveFollowerIdsResponse

    /** @see retrieveFollowerIds */
    fun retrieveFollowerIds(
        id: String,
        requestOptions: RequestOptions,
    ): UserRetrieveFollowerIdsResponse =
        retrieveFollowerIds(id, UserRetrieveFollowerIdsParams.none(), requestOptions)

    /**
     * Returns the user IDs of the accounts the selected user follows, newest first, up to 5,000 a
     * page. Use it when you need IDs only. Each ID costs 1 credit.
     */
    fun retrieveFollowingIds(
        id: String,
        params: UserRetrieveFollowingIdsParams = UserRetrieveFollowingIdsParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserRetrieveFollowingIdsResponse =
        retrieveFollowingIds(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieveFollowingIds */
    fun retrieveFollowingIds(
        params: UserRetrieveFollowingIdsParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserRetrieveFollowingIdsResponse

    /** @see retrieveFollowingIds */
    fun retrieveFollowingIds(
        id: String,
        requestOptions: RequestOptions,
    ): UserRetrieveFollowingIdsResponse =
        retrieveFollowingIds(id, UserRetrieveFollowingIdsParams.none(), requestOptions)

    /**
     * Returns the posts on the user's Highlights tab, in X's order. An account without highlights
     * returns an empty list.
     */
    fun retrieveHighlights(
        id: String,
        params: UserRetrieveHighlightsParams = UserRetrieveHighlightsParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedTweets = retrieveHighlights(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieveHighlights */
    fun retrieveHighlights(
        params: UserRetrieveHighlightsParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedTweets

    /** @see retrieveHighlights */
    fun retrieveHighlights(id: String, requestOptions: RequestOptions): PaginatedTweets =
        retrieveHighlights(id, UserRetrieveHighlightsParams.none(), requestOptions)

    /**
     * Returns engagement insights for 1 profile. They are engagement rate, averages per post and
     * posting rate, from its counts and its last 20 posts. Reposts and the pinned post stay out of
     * the averages. Each report costs 1 credit, whatever it reads.
     */
    fun retrieveInsights(
        id: String,
        params: UserRetrieveInsightsParams = UserRetrieveInsightsParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserRetrieveInsightsResponse =
        retrieveInsights(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieveInsights */
    fun retrieveInsights(
        params: UserRetrieveInsightsParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserRetrieveInsightsResponse

    /** @see retrieveInsights */
    fun retrieveInsights(id: String, requestOptions: RequestOptions): UserRetrieveInsightsResponse =
        retrieveInsights(id, UserRetrieveInsightsParams.none(), requestOptions)

    /**
     * Returns the public X Lists that include the selected user, as the profile's List memberships
     * page shows them. Each List costs 1 credit. A protected account answers 403.
     */
    fun retrieveListMemberships(
        id: String,
        params: UserRetrieveListMembershipsParams = UserRetrieveListMembershipsParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserRetrieveListMembershipsResponse =
        retrieveListMemberships(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieveListMemberships */
    fun retrieveListMemberships(
        params: UserRetrieveListMembershipsParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserRetrieveListMembershipsResponse

    /** @see retrieveListMemberships */
    fun retrieveListMemberships(
        id: String,
        requestOptions: RequestOptions,
    ): UserRetrieveListMembershipsResponse =
        retrieveListMemberships(id, UserRetrieveListMembershipsParams.none(), requestOptions)

    /**
     * Returns the public X Lists the selected user created, in X's order, as the profile's Lists
     * tab shows them. Each List costs 1 credit. A protected account answers 403.
     */
    fun retrieveLists(
        id: String,
        params: UserRetrieveListsParams = UserRetrieveListsParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserRetrieveListsResponse = retrieveLists(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieveLists */
    fun retrieveLists(
        params: UserRetrieveListsParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserRetrieveListsResponse

    /** @see retrieveLists */
    fun retrieveLists(id: String, requestOptions: RequestOptions): UserRetrieveListsResponse =
        retrieveLists(id, UserRetrieveListsParams.none(), requestOptions)

    /**
     * Says whether 1 user is broadcasting live video on X right now, with their latest broadcast,
     * live or ended. Each check costs 1 credit.
     */
    fun retrieveLive(
        id: String,
        params: UserRetrieveLiveParams = UserRetrieveLiveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserRetrieveLiveResponse = retrieveLive(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieveLive */
    fun retrieveLive(
        params: UserRetrieveLiveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserRetrieveLiveResponse

    /** @see retrieveLive */
    fun retrieveLive(id: String, requestOptions: RequestOptions): UserRetrieveLiveResponse =
        retrieveLive(id, UserRetrieveLiveParams.none(), requestOptions)

    /**
     * Returns the public accounts X lists as similar to the selected user, in X's order. X can list
     * slightly different accounts on each call. Each account costs 1 credit.
     */
    fun retrieveSimilar(
        id: String,
        params: UserRetrieveSimilarParams = UserRetrieveSimilarParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedUsers = retrieveSimilar(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieveSimilar */
    fun retrieveSimilar(
        params: UserRetrieveSimilarParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedUsers

    /** @see retrieveSimilar */
    fun retrieveSimilar(id: String, requestOptions: RequestOptions): PaginatedUsers =
        retrieveSimilar(id, UserRetrieveSimilarParams.none(), requestOptions)

    /**
     * Returns the creators the selected user subscribes to, in X's order. An account that hides its
     * subscriptions returns an empty list.
     */
    fun retrieveSubscriptions(
        id: String,
        params: UserRetrieveSubscriptionsParams = UserRetrieveSubscriptionsParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedUsers = retrieveSubscriptions(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieveSubscriptions */
    fun retrieveSubscriptions(
        params: UserRetrieveSubscriptionsParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PaginatedUsers

    /** @see retrieveSubscriptions */
    fun retrieveSubscriptions(id: String, requestOptions: RequestOptions): PaginatedUsers =
        retrieveSubscriptions(id, UserRetrieveSubscriptionsParams.none(), requestOptions)

    /**
     * Returns X's own translation of 1 profile's bio into the language you pick, with the links,
     * mentions, and tags X places in the translated text. A profile without a bio returns
     * translation null & a message, free of charge.
     */
    fun translateBio(
        id: String,
        params: UserTranslateBioParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserTranslateBioResponse = translateBio(params.toBuilder().id(id).build(), requestOptions)

    /** @see translateBio */
    fun translateBio(
        params: UserTranslateBioParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserTranslateBioResponse

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
         * Returns a raw HTTP response for `get /x/users/{id}`, but is otherwise the same as
         * [UserService.retrieve].
         */
        @MustBeClosed
        fun retrieve(
            id: String,
            params: UserRetrieveParams = UserRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UserProfile> =
            retrieve(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: UserRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UserProfile>

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(id: String, requestOptions: RequestOptions): HttpResponseFor<UserProfile> =
            retrieve(id, UserRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /x/users/{id}/remove-follower`, but is otherwise
         * the same as [UserService.removeFollower].
         */
        @MustBeClosed
        fun removeFollower(
            id: String,
            params: UserRemoveFollowerParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UserRemoveFollowerResponse> =
            removeFollower(params.toBuilder().id(id).build(), requestOptions)

        /** @see removeFollower */
        @MustBeClosed
        fun removeFollower(
            params: UserRemoveFollowerParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UserRemoveFollowerResponse>

        /**
         * Returns a raw HTTP response for `get /x/users/batch`, but is otherwise the same as
         * [UserService.retrieveBatch].
         */
        @MustBeClosed
        fun retrieveBatch(
            params: UserRetrieveBatchParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UserRetrieveBatchResponse>

        /**
         * Returns a raw HTTP response for `get /x/users/{id}/followers`, but is otherwise the same
         * as [UserService.retrieveFollowers].
         */
        @MustBeClosed
        fun retrieveFollowers(
            id: String,
            params: UserRetrieveFollowersParams = UserRetrieveFollowersParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UserRetrieveFollowersResponse> =
            retrieveFollowers(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieveFollowers */
        @MustBeClosed
        fun retrieveFollowers(
            params: UserRetrieveFollowersParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UserRetrieveFollowersResponse>

        /** @see retrieveFollowers */
        @MustBeClosed
        fun retrieveFollowers(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<UserRetrieveFollowersResponse> =
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
        ): HttpResponseFor<PaginatedUsers> =
            retrieveFollowersYouKnow(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieveFollowersYouKnow */
        @MustBeClosed
        fun retrieveFollowersYouKnow(
            params: UserRetrieveFollowersYouKnowParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedUsers>

        /** @see retrieveFollowersYouKnow */
        @MustBeClosed
        fun retrieveFollowersYouKnow(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<PaginatedUsers> =
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
        ): HttpResponseFor<UserRetrieveFollowingResponse> =
            retrieveFollowing(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieveFollowing */
        @MustBeClosed
        fun retrieveFollowing(
            params: UserRetrieveFollowingParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UserRetrieveFollowingResponse>

        /** @see retrieveFollowing */
        @MustBeClosed
        fun retrieveFollowing(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<UserRetrieveFollowingResponse> =
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
        ): HttpResponseFor<PaginatedTweets> =
            retrieveLikes(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieveLikes */
        @MustBeClosed
        fun retrieveLikes(
            params: UserRetrieveLikesParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedTweets>

        /** @see retrieveLikes */
        @MustBeClosed
        fun retrieveLikes(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<PaginatedTweets> =
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
        ): HttpResponseFor<PaginatedTweets> =
            retrieveMedia(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieveMedia */
        @MustBeClosed
        fun retrieveMedia(
            params: UserRetrieveMediaParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedTweets>

        /** @see retrieveMedia */
        @MustBeClosed
        fun retrieveMedia(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<PaginatedTweets> =
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
        ): HttpResponseFor<PaginatedTweets> =
            retrieveMentions(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieveMentions */
        @MustBeClosed
        fun retrieveMentions(
            params: UserRetrieveMentionsParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedTweets>

        /** @see retrieveMentions */
        @MustBeClosed
        fun retrieveMentions(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<PaginatedTweets> =
            retrieveMentions(id, UserRetrieveMentionsParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/users/{id}/replies`, but is otherwise the same as
         * [UserService.retrieveReplies].
         */
        @MustBeClosed
        fun retrieveReplies(
            id: String,
            params: UserRetrieveRepliesParams = UserRetrieveRepliesParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedTweets> =
            retrieveReplies(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieveReplies */
        @MustBeClosed
        fun retrieveReplies(
            params: UserRetrieveRepliesParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedTweets>

        /** @see retrieveReplies */
        @MustBeClosed
        fun retrieveReplies(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<PaginatedTweets> =
            retrieveReplies(id, UserRetrieveRepliesParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/users/search`, but is otherwise the same as
         * [UserService.retrieveSearch].
         */
        @MustBeClosed
        fun retrieveSearch(
            params: UserRetrieveSearchParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedUsers>

        /**
         * Returns a raw HTTP response for `get /x/users/{id}/tweets`, but is otherwise the same as
         * [UserService.retrieveTweets].
         */
        @MustBeClosed
        fun retrieveTweets(
            id: String,
            params: UserRetrieveTweetsParams = UserRetrieveTweetsParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedTweets> =
            retrieveTweets(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieveTweets */
        @MustBeClosed
        fun retrieveTweets(
            params: UserRetrieveTweetsParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedTweets>

        /** @see retrieveTweets */
        @MustBeClosed
        fun retrieveTweets(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<PaginatedTweets> =
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
        ): HttpResponseFor<UserRetrieveVerifiedFollowersResponse> =
            retrieveVerifiedFollowers(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieveVerifiedFollowers */
        @MustBeClosed
        fun retrieveVerifiedFollowers(
            params: UserRetrieveVerifiedFollowersParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UserRetrieveVerifiedFollowersResponse>

        /** @see retrieveVerifiedFollowers */
        @MustBeClosed
        fun retrieveVerifiedFollowers(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<UserRetrieveVerifiedFollowersResponse> =
            retrieveVerifiedFollowers(
                id,
                UserRetrieveVerifiedFollowersParams.none(),
                requestOptions,
            )

        /**
         * Returns a raw HTTP response for `get /x/users/batch/tweets`, but is otherwise the same as
         * [UserService.retrieveBatchTweets].
         */
        @MustBeClosed
        fun retrieveBatchTweets(
            params: UserRetrieveBatchTweetsParams = UserRetrieveBatchTweetsParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UserRetrieveBatchTweetsResponse>

        /** @see retrieveBatchTweets */
        @MustBeClosed
        fun retrieveBatchTweets(
            requestOptions: RequestOptions
        ): HttpResponseFor<UserRetrieveBatchTweetsResponse> =
            retrieveBatchTweets(UserRetrieveBatchTweetsParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/users/{id}/affiliates`, but is otherwise the same
         * as [UserService.retrieveAffiliates].
         */
        @MustBeClosed
        fun retrieveAffiliates(
            id: String,
            params: UserRetrieveAffiliatesParams = UserRetrieveAffiliatesParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedUsers> =
            retrieveAffiliates(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieveAffiliates */
        @MustBeClosed
        fun retrieveAffiliates(
            params: UserRetrieveAffiliatesParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedUsers>

        /** @see retrieveAffiliates */
        @MustBeClosed
        fun retrieveAffiliates(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<PaginatedUsers> =
            retrieveAffiliates(id, UserRetrieveAffiliatesParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/users/{id}/articles`, but is otherwise the same
         * as [UserService.retrieveArticles].
         */
        @MustBeClosed
        fun retrieveArticles(
            id: String,
            params: UserRetrieveArticlesParams = UserRetrieveArticlesParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedTweets> =
            retrieveArticles(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieveArticles */
        @MustBeClosed
        fun retrieveArticles(
            params: UserRetrieveArticlesParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedTweets>

        /** @see retrieveArticles */
        @MustBeClosed
        fun retrieveArticles(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<PaginatedTweets> =
            retrieveArticles(id, UserRetrieveArticlesParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/users/{id}/follower-ids`, but is otherwise the
         * same as [UserService.retrieveFollowerIds].
         */
        @MustBeClosed
        fun retrieveFollowerIds(
            id: String,
            params: UserRetrieveFollowerIdsParams = UserRetrieveFollowerIdsParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UserRetrieveFollowerIdsResponse> =
            retrieveFollowerIds(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieveFollowerIds */
        @MustBeClosed
        fun retrieveFollowerIds(
            params: UserRetrieveFollowerIdsParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UserRetrieveFollowerIdsResponse>

        /** @see retrieveFollowerIds */
        @MustBeClosed
        fun retrieveFollowerIds(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<UserRetrieveFollowerIdsResponse> =
            retrieveFollowerIds(id, UserRetrieveFollowerIdsParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/users/{id}/following-ids`, but is otherwise the
         * same as [UserService.retrieveFollowingIds].
         */
        @MustBeClosed
        fun retrieveFollowingIds(
            id: String,
            params: UserRetrieveFollowingIdsParams = UserRetrieveFollowingIdsParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UserRetrieveFollowingIdsResponse> =
            retrieveFollowingIds(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieveFollowingIds */
        @MustBeClosed
        fun retrieveFollowingIds(
            params: UserRetrieveFollowingIdsParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UserRetrieveFollowingIdsResponse>

        /** @see retrieveFollowingIds */
        @MustBeClosed
        fun retrieveFollowingIds(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<UserRetrieveFollowingIdsResponse> =
            retrieveFollowingIds(id, UserRetrieveFollowingIdsParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/users/{id}/highlights`, but is otherwise the same
         * as [UserService.retrieveHighlights].
         */
        @MustBeClosed
        fun retrieveHighlights(
            id: String,
            params: UserRetrieveHighlightsParams = UserRetrieveHighlightsParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedTweets> =
            retrieveHighlights(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieveHighlights */
        @MustBeClosed
        fun retrieveHighlights(
            params: UserRetrieveHighlightsParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedTweets>

        /** @see retrieveHighlights */
        @MustBeClosed
        fun retrieveHighlights(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<PaginatedTweets> =
            retrieveHighlights(id, UserRetrieveHighlightsParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/users/{id}/insights`, but is otherwise the same
         * as [UserService.retrieveInsights].
         */
        @MustBeClosed
        fun retrieveInsights(
            id: String,
            params: UserRetrieveInsightsParams = UserRetrieveInsightsParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UserRetrieveInsightsResponse> =
            retrieveInsights(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieveInsights */
        @MustBeClosed
        fun retrieveInsights(
            params: UserRetrieveInsightsParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UserRetrieveInsightsResponse>

        /** @see retrieveInsights */
        @MustBeClosed
        fun retrieveInsights(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<UserRetrieveInsightsResponse> =
            retrieveInsights(id, UserRetrieveInsightsParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/users/{id}/list-memberships`, but is otherwise
         * the same as [UserService.retrieveListMemberships].
         */
        @MustBeClosed
        fun retrieveListMemberships(
            id: String,
            params: UserRetrieveListMembershipsParams = UserRetrieveListMembershipsParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UserRetrieveListMembershipsResponse> =
            retrieveListMemberships(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieveListMemberships */
        @MustBeClosed
        fun retrieveListMemberships(
            params: UserRetrieveListMembershipsParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UserRetrieveListMembershipsResponse>

        /** @see retrieveListMemberships */
        @MustBeClosed
        fun retrieveListMemberships(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<UserRetrieveListMembershipsResponse> =
            retrieveListMemberships(id, UserRetrieveListMembershipsParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/users/{id}/lists`, but is otherwise the same as
         * [UserService.retrieveLists].
         */
        @MustBeClosed
        fun retrieveLists(
            id: String,
            params: UserRetrieveListsParams = UserRetrieveListsParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UserRetrieveListsResponse> =
            retrieveLists(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieveLists */
        @MustBeClosed
        fun retrieveLists(
            params: UserRetrieveListsParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UserRetrieveListsResponse>

        /** @see retrieveLists */
        @MustBeClosed
        fun retrieveLists(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<UserRetrieveListsResponse> =
            retrieveLists(id, UserRetrieveListsParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/users/{id}/live`, but is otherwise the same as
         * [UserService.retrieveLive].
         */
        @MustBeClosed
        fun retrieveLive(
            id: String,
            params: UserRetrieveLiveParams = UserRetrieveLiveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UserRetrieveLiveResponse> =
            retrieveLive(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieveLive */
        @MustBeClosed
        fun retrieveLive(
            params: UserRetrieveLiveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UserRetrieveLiveResponse>

        /** @see retrieveLive */
        @MustBeClosed
        fun retrieveLive(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<UserRetrieveLiveResponse> =
            retrieveLive(id, UserRetrieveLiveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/users/{id}/similar`, but is otherwise the same as
         * [UserService.retrieveSimilar].
         */
        @MustBeClosed
        fun retrieveSimilar(
            id: String,
            params: UserRetrieveSimilarParams = UserRetrieveSimilarParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedUsers> =
            retrieveSimilar(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieveSimilar */
        @MustBeClosed
        fun retrieveSimilar(
            params: UserRetrieveSimilarParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedUsers>

        /** @see retrieveSimilar */
        @MustBeClosed
        fun retrieveSimilar(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<PaginatedUsers> =
            retrieveSimilar(id, UserRetrieveSimilarParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/users/{id}/subscriptions`, but is otherwise the
         * same as [UserService.retrieveSubscriptions].
         */
        @MustBeClosed
        fun retrieveSubscriptions(
            id: String,
            params: UserRetrieveSubscriptionsParams = UserRetrieveSubscriptionsParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedUsers> =
            retrieveSubscriptions(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieveSubscriptions */
        @MustBeClosed
        fun retrieveSubscriptions(
            params: UserRetrieveSubscriptionsParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PaginatedUsers>

        /** @see retrieveSubscriptions */
        @MustBeClosed
        fun retrieveSubscriptions(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<PaginatedUsers> =
            retrieveSubscriptions(id, UserRetrieveSubscriptionsParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/users/{id}/translation`, but is otherwise the
         * same as [UserService.translateBio].
         */
        @MustBeClosed
        fun translateBio(
            id: String,
            params: UserTranslateBioParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UserTranslateBioResponse> =
            translateBio(params.toBuilder().id(id).build(), requestOptions)

        /** @see translateBio */
        @MustBeClosed
        fun translateBio(
            params: UserTranslateBioParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UserTranslateBioResponse>
    }
}
