// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.services.async.x

import com.google.errorprone.annotations.MustBeClosed
import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.x.jobs.JobListLocationsParams
import com.x_twitter_scraper.api.models.x.jobs.JobListLocationsResponse
import com.x_twitter_scraper.api.models.x.jobs.JobRetrieveParams
import com.x_twitter_scraper.api.models.x.jobs.JobRetrieveResponse
import com.x_twitter_scraper.api.models.x.jobs.JobSearchParams
import com.x_twitter_scraper.api.models.x.jobs.JobSearchResponse

/** Public X job listings, search and details */
interface JobServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: (ClientOptions.Builder) -> Unit): JobServiceAsync

    /**
     * Returns a public job listing with its description as text & Markdown, work type, seniority,
     * salary, company & apply link. A job that closed returns job_not_found & is free.
     */
    suspend fun retrieve(
        id: String,
        params: JobRetrieveParams = JobRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): JobRetrieveResponse = retrieve(params.toBuilder().id(id).build(), requestOptions)

    /** @see retrieve */
    suspend fun retrieve(
        params: JobRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): JobRetrieveResponse

    /** @see retrieve */
    suspend fun retrieve(id: String, requestOptions: RequestOptions): JobRetrieveResponse =
        retrieve(id, JobRetrieveParams.none(), requestOptions)

    /**
     * Returns the places whose names match a query, each with the location ID that GET
     * /x/jobs/search takes. No match returns an empty list, free.
     */
    suspend fun listLocations(
        params: JobListLocationsParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): JobListLocationsResponse

    /**
     * Returns job listings with title, location, salary, company & apply link. Add filters to
     * narrow them. A closed job does not appear & costs nothing. Follow next_cursor for more.
     */
    suspend fun search(
        params: JobSearchParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): JobSearchResponse

    /** A view of [JobServiceAsync] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: (ClientOptions.Builder) -> Unit): JobServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /x/jobs/{id}`, but is otherwise the same as
         * [JobServiceAsync.retrieve].
         */
        @MustBeClosed
        suspend fun retrieve(
            id: String,
            params: JobRetrieveParams = JobRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<JobRetrieveResponse> =
            retrieve(params.toBuilder().id(id).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        suspend fun retrieve(
            params: JobRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<JobRetrieveResponse>

        /** @see retrieve */
        @MustBeClosed
        suspend fun retrieve(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<JobRetrieveResponse> =
            retrieve(id, JobRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /x/jobs/locations`, but is otherwise the same as
         * [JobServiceAsync.listLocations].
         */
        @MustBeClosed
        suspend fun listLocations(
            params: JobListLocationsParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<JobListLocationsResponse>

        /**
         * Returns a raw HTTP response for `get /x/jobs/search`, but is otherwise the same as
         * [JobServiceAsync.search].
         */
        @MustBeClosed
        suspend fun search(
            params: JobSearchParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<JobSearchResponse>
    }
}
