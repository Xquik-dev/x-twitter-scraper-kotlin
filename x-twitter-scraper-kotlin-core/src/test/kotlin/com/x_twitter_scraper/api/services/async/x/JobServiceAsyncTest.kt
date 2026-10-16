// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.services.async.x

import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClientAsync
import com.x_twitter_scraper.api.models.x.jobs.JobListLocationsParams
import com.x_twitter_scraper.api.models.x.jobs.JobRetrieveParams
import com.x_twitter_scraper.api.models.x.jobs.JobSearchParams
import org.junit.jupiter.api.Test

internal class JobServiceAsyncTest {

    @Test
    suspend fun retrieve() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val jobServiceAsync = client.x().jobs()

        val response =
            jobServiceAsync.retrieve(
                JobRetrieveParams.builder()
                    .id("1899202395210649861")
                    .fields("id,text,author.username")
                    .omit("entities,media")
                    .build()
            )

        kotlin.test.assertNotNull(response)
    }

    @Test
    suspend fun listLocations() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val jobServiceAsync = client.x().jobs()

        val response =
            jobServiceAsync.listLocations(
                JobListLocationsParams.builder()
                    .q("new york")
                    .fields("id,text,author.username")
                    .limit(5L)
                    .omit("entities,media")
                    .build()
            )

        kotlin.test.assertNotNull(response)
    }

    @Test
    suspend fun search() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val jobServiceAsync = client.x().jobs()

        val response =
            jobServiceAsync.search(
                JobSearchParams.builder()
                    .q("engineer")
                    .company("SpaceX")
                    .cursor("cursor")
                    .employmentType("full_time")
                    .fields("id,text,author.username")
                    .location("Berlin")
                    .locationId("1720462281849397612")
                    .locationType("remote,hybrid")
                    .omit("entities,media")
                    .pageSize(1L)
                    .seniority("senior,lead")
                    .build()
            )

        kotlin.test.assertNotNull(response)
    }
}
