// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async.x

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.x.profile.ProfilePatchAllParams
import com.x_twitter_scraper.api.models.x.profile.ProfilePatchAllResponse
import com.x_twitter_scraper.api.models.x.profile.ProfileUpdateAvatarParams
import com.x_twitter_scraper.api.models.x.profile.ProfileUpdateAvatarResponse
import com.x_twitter_scraper.api.models.x.profile.ProfileUpdateBannerParams
import com.x_twitter_scraper.api.models.x.profile.ProfileUpdateBannerResponse

/** X write actions (tweets, likes, follows, DMs) */
interface ProfileServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: (ClientOptions.Builder) -> Unit): ProfileServiceAsync

    /** Update X profile */
    suspend fun patchAll(
        params: ProfilePatchAllParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): ProfilePatchAllResponse

    /** Update profile avatar */
    suspend fun updateAvatar(
        params: ProfileUpdateAvatarParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): ProfileUpdateAvatarResponse

    /** Update profile banner */
    suspend fun updateBanner(
        params: ProfileUpdateBannerParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): ProfileUpdateBannerResponse

    /**
     * A view of [ProfileServiceAsync] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: (ClientOptions.Builder) -> Unit
        ): ProfileServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `patch /x/profile`, but is otherwise the same as
         * [ProfileServiceAsync.patchAll].
         */
        @MustBeClosed
        suspend fun patchAll(
            params: ProfilePatchAllParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<ProfilePatchAllResponse>

        /**
         * Returns a raw HTTP response for `patch /x/profile/avatar`, but is otherwise the same as
         * [ProfileServiceAsync.updateAvatar].
         */
        @MustBeClosed
        suspend fun updateAvatar(
            params: ProfileUpdateAvatarParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<ProfileUpdateAvatarResponse>

        /**
         * Returns a raw HTTP response for `patch /x/profile/banner`, but is otherwise the same as
         * [ProfileServiceAsync.updateBanner].
         */
        @MustBeClosed
        suspend fun updateBanner(
            params: ProfileUpdateBannerParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<ProfileUpdateBannerResponse>
    }
}
