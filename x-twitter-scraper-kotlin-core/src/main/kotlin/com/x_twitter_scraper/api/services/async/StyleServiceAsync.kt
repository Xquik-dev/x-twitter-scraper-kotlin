// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponse
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.styles.StyleAnalyzeParams
import com.x_twitter_scraper.api.models.styles.StyleCompareParams
import com.x_twitter_scraper.api.models.styles.StyleCompareResponse
import com.x_twitter_scraper.api.models.styles.StyleDeleteParams
import com.x_twitter_scraper.api.models.styles.StyleGetPerformanceParams
import com.x_twitter_scraper.api.models.styles.StyleGetPerformanceResponse
import com.x_twitter_scraper.api.models.styles.StyleListParams
import com.x_twitter_scraper.api.models.styles.StyleListResponse
import com.x_twitter_scraper.api.models.styles.StyleProfile
import com.x_twitter_scraper.api.models.styles.StyleRetrieveParams
import com.x_twitter_scraper.api.models.styles.StyleUpdateParams

/** Tweet composition, drafts, writing styles & radar */
interface StyleServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: (ClientOptions.Builder) -> Unit): StyleServiceAsync

    /** Get cached style profile */
    suspend fun retrieve(
        username: String,
        params: StyleRetrieveParams = StyleRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): StyleProfile = retrieve(params.toBuilder().username(username).build(), requestOptions)

    /** @see retrieve */
    suspend fun retrieve(
        params: StyleRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): StyleProfile

    /** @see retrieve */
    suspend fun retrieve(username: String, requestOptions: RequestOptions): StyleProfile =
        retrieve(username, StyleRetrieveParams.none(), requestOptions)

    /** Save style profile with custom tweets */
    suspend fun update(
        username: String,
        params: StyleUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): StyleProfile = update(params.toBuilder().username(username).build(), requestOptions)

    /** @see update */
    suspend fun update(
        params: StyleUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): StyleProfile

    /** List cached style profiles */
    suspend fun list(
        params: StyleListParams = StyleListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): StyleListResponse

    /** @see list */
    suspend fun list(requestOptions: RequestOptions): StyleListResponse =
        list(StyleListParams.none(), requestOptions)

    /** Delete a style profile */
    suspend fun delete(
        username: String,
        params: StyleDeleteParams = StyleDeleteParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ) = delete(params.toBuilder().username(username).build(), requestOptions)

    /** @see delete */
    suspend fun delete(
        params: StyleDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    )

    /** @see delete */
    suspend fun delete(username: String, requestOptions: RequestOptions) =
        delete(username, StyleDeleteParams.none(), requestOptions)

    /** Analyze writing style from recent tweets */
    suspend fun analyze(
        params: StyleAnalyzeParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): StyleProfile

    /** Compare two style profiles */
    suspend fun compare(
        params: StyleCompareParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): StyleCompareResponse

    /** Get engagement metrics for style tweets */
    suspend fun getPerformance(
        username: String,
        params: StyleGetPerformanceParams = StyleGetPerformanceParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): StyleGetPerformanceResponse =
        getPerformance(params.toBuilder().username(username).build(), requestOptions)

    /** @see getPerformance */
    suspend fun getPerformance(
        params: StyleGetPerformanceParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): StyleGetPerformanceResponse

    /** @see getPerformance */
    suspend fun getPerformance(
        username: String,
        requestOptions: RequestOptions,
    ): StyleGetPerformanceResponse =
        getPerformance(username, StyleGetPerformanceParams.none(), requestOptions)

    /** A view of [StyleServiceAsync] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: (ClientOptions.Builder) -> Unit
        ): StyleServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /styles/{username}`, but is otherwise the same as
         * [StyleServiceAsync.retrieve].
         */
        @MustBeClosed
        suspend fun retrieve(
            username: String,
            params: StyleRetrieveParams = StyleRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<StyleProfile> =
            retrieve(params.toBuilder().username(username).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        suspend fun retrieve(
            params: StyleRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<StyleProfile>

        /** @see retrieve */
        @MustBeClosed
        suspend fun retrieve(
            username: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<StyleProfile> =
            retrieve(username, StyleRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `put /styles/{username}`, but is otherwise the same as
         * [StyleServiceAsync.update].
         */
        @MustBeClosed
        suspend fun update(
            username: String,
            params: StyleUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<StyleProfile> =
            update(params.toBuilder().username(username).build(), requestOptions)

        /** @see update */
        @MustBeClosed
        suspend fun update(
            params: StyleUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<StyleProfile>

        /**
         * Returns a raw HTTP response for `get /styles`, but is otherwise the same as
         * [StyleServiceAsync.list].
         */
        @MustBeClosed
        suspend fun list(
            params: StyleListParams = StyleListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<StyleListResponse>

        /** @see list */
        @MustBeClosed
        suspend fun list(requestOptions: RequestOptions): HttpResponseFor<StyleListResponse> =
            list(StyleListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete /styles/{username}`, but is otherwise the same as
         * [StyleServiceAsync.delete].
         */
        @MustBeClosed
        suspend fun delete(
            username: String,
            params: StyleDeleteParams = StyleDeleteParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponse = delete(params.toBuilder().username(username).build(), requestOptions)

        /** @see delete */
        @MustBeClosed
        suspend fun delete(
            params: StyleDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponse

        /** @see delete */
        @MustBeClosed
        suspend fun delete(username: String, requestOptions: RequestOptions): HttpResponse =
            delete(username, StyleDeleteParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /styles`, but is otherwise the same as
         * [StyleServiceAsync.analyze].
         */
        @MustBeClosed
        suspend fun analyze(
            params: StyleAnalyzeParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<StyleProfile>

        /**
         * Returns a raw HTTP response for `get /styles/compare`, but is otherwise the same as
         * [StyleServiceAsync.compare].
         */
        @MustBeClosed
        suspend fun compare(
            params: StyleCompareParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<StyleCompareResponse>

        /**
         * Returns a raw HTTP response for `get /styles/{username}/performance`, but is otherwise
         * the same as [StyleServiceAsync.getPerformance].
         */
        @MustBeClosed
        suspend fun getPerformance(
            username: String,
            params: StyleGetPerformanceParams = StyleGetPerformanceParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<StyleGetPerformanceResponse> =
            getPerformance(params.toBuilder().username(username).build(), requestOptions)

        /** @see getPerformance */
        @MustBeClosed
        suspend fun getPerformance(
            params: StyleGetPerformanceParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<StyleGetPerformanceResponse>

        /** @see getPerformance */
        @MustBeClosed
        suspend fun getPerformance(
            username: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<StyleGetPerformanceResponse> =
            getPerformance(username, StyleGetPerformanceParams.none(), requestOptions)
    }
}
