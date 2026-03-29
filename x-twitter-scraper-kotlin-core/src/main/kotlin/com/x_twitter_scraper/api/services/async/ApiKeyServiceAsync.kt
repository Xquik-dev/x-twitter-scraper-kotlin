// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.apikeys.ApiKeyCreateParams
import com.x_twitter_scraper.api.models.apikeys.ApiKeyCreateResponse
import com.x_twitter_scraper.api.models.apikeys.ApiKeyListParams
import com.x_twitter_scraper.api.models.apikeys.ApiKeyListResponse
import com.x_twitter_scraper.api.models.apikeys.ApiKeyRevokeParams
import com.x_twitter_scraper.api.models.apikeys.ApiKeyRevokeResponse

/** API key management (session auth only) */
interface ApiKeyServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: (ClientOptions.Builder) -> Unit): ApiKeyServiceAsync

    /** Create API key */
    suspend fun create(
        params: ApiKeyCreateParams = ApiKeyCreateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): ApiKeyCreateResponse

    /** @see create */
    suspend fun create(requestOptions: RequestOptions): ApiKeyCreateResponse =
        create(ApiKeyCreateParams.none(), requestOptions)

    /** List API keys */
    suspend fun list(
        params: ApiKeyListParams = ApiKeyListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): ApiKeyListResponse

    /** @see list */
    suspend fun list(requestOptions: RequestOptions): ApiKeyListResponse =
        list(ApiKeyListParams.none(), requestOptions)

    /** Revoke API key */
    suspend fun revoke(
        id: String,
        params: ApiKeyRevokeParams = ApiKeyRevokeParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): ApiKeyRevokeResponse = revoke(params.toBuilder().id(id).build(), requestOptions)

    /** @see revoke */
    suspend fun revoke(
        params: ApiKeyRevokeParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): ApiKeyRevokeResponse

    /** @see revoke */
    suspend fun revoke(id: String, requestOptions: RequestOptions): ApiKeyRevokeResponse =
        revoke(id, ApiKeyRevokeParams.none(), requestOptions)

    /**
     * A view of [ApiKeyServiceAsync] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: (ClientOptions.Builder) -> Unit
        ): ApiKeyServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /api-keys`, but is otherwise the same as
         * [ApiKeyServiceAsync.create].
         */
        @MustBeClosed
        suspend fun create(
            params: ApiKeyCreateParams = ApiKeyCreateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<ApiKeyCreateResponse>

        /** @see create */
        @MustBeClosed
        suspend fun create(requestOptions: RequestOptions): HttpResponseFor<ApiKeyCreateResponse> =
            create(ApiKeyCreateParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /api-keys`, but is otherwise the same as
         * [ApiKeyServiceAsync.list].
         */
        @MustBeClosed
        suspend fun list(
            params: ApiKeyListParams = ApiKeyListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<ApiKeyListResponse>

        /** @see list */
        @MustBeClosed
        suspend fun list(requestOptions: RequestOptions): HttpResponseFor<ApiKeyListResponse> =
            list(ApiKeyListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete /api-keys/{id}`, but is otherwise the same as
         * [ApiKeyServiceAsync.revoke].
         */
        @MustBeClosed
        suspend fun revoke(
            id: String,
            params: ApiKeyRevokeParams = ApiKeyRevokeParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<ApiKeyRevokeResponse> =
            revoke(params.toBuilder().id(id).build(), requestOptions)

        /** @see revoke */
        @MustBeClosed
        suspend fun revoke(
            params: ApiKeyRevokeParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<ApiKeyRevokeResponse>

        /** @see revoke */
        @MustBeClosed
        suspend fun revoke(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ApiKeyRevokeResponse> =
            revoke(id, ApiKeyRevokeParams.none(), requestOptions)
    }
}
