// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.services

import com.github.tomakehurst.wiremock.client.WireMock.equalTo
import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor
import com.github.tomakehurst.wiremock.client.WireMock.okJson
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo
import com.github.tomakehurst.wiremock.client.WireMock.verify
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo
import com.github.tomakehurst.wiremock.junit5.WireMockTest
import com.x_twitter_scraper.api.client.XTwitterScraperClient
import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClient
import com.x_twitter_scraper.api.models.x.jobs.JobListLocationsParams
import com.x_twitter_scraper.api.models.x.jobs.JobRetrieveParams
import com.x_twitter_scraper.api.models.x.jobs.JobSearchParams
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.ResourceLock

@WireMockTest
@ResourceLock("https://github.com/wiremock/wiremock/issues/169")
internal class XJobsExampleTest {

    private lateinit var client: XTwitterScraperClient

    @BeforeEach
    fun beforeEach(wmRuntimeInfo: WireMockRuntimeInfo) {
        client =
            XTwitterScraperOkHttpClient.builder()
                .baseUrl(wmRuntimeInfo.httpBaseUrl)
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .responseValidation(true)
                .build()
    }

    @Test
    suspend fun retrieve() {
        stubFor(
            get(urlPathEqualTo("/x/jobs/1899202395210649861"))
                .willReturn(okJson(ExampleResponses.read("examples/x/jobs/retrieve.json")))
        )
        val params = JobRetrieveParams.builder().id("1899202395210649861").build()

        val responses =
            listOf(client.x().jobs().retrieve(params), client.async().x().jobs().retrieve(params))

        verify(2, getRequestedFor(urlPathEqualTo("/x/jobs/1899202395210649861")))
        responses.forEach { response ->
            assertThat(response.job().id()).isEqualTo("1899202395210649861")
            assertThat(response.job().url()).isEqualTo("https://x.com/i/jobs/1899202395210649861")
            assertThat(response.job().account()!!.id()).isEqualTo("1812126202804711425")
            assertThat(response.job().account()!!.verified()).isEqualTo(true)
            assertThat(response.job().account()!!.name()).isEqualTo("commonware")
            assertThat(response.job().account()!!.username()).isEqualTo("commonwarexyz")
            assertThat(response.job().account()!!.verifiedType()).isEqualTo("Business")
            assertThat(response.job().applyUrl()).isEqualTo("https://xquik.com/example")
            assertThat(response.job().description())
                .isEqualTo("What is Commonware? We build open primitives.")
            assertThat(response.job().descriptionMarkdown()).isEqualTo("# What is Commonware?")
            assertThat(response.job().employmentType()).isEqualTo("full_time")
            assertThat(response.job().featured()).isEqualTo(true)
            assertThat(response.job().formattedSalary()).isEqualTo("Starting at \$225K")
            assertThat(response.job().jobFunction()).isEqualTo("software_engineering")
            assertThat(response.job().location()).isEqualTo("United States")
            assertThat(response.job().locationType()).isEqualTo("remote")
            assertThat(response.job().salaryCurrencyCode()).isEqualTo("USD")
            assertThat(response.job().salaryInterval()).isEqualTo("annually")
            assertThat(response.job().salaryMin()).isEqualTo(225000.0)
            assertThat(response.job().seniorityLevel()).isEqualTo("lead")
            assertThat(response.job().shortDescription())
                .isEqualTo("Commonware is hiring founding engineers.")
            assertThat(response.job().title()).isEqualTo("Engineer")
        }
    }

    @Test
    suspend fun listLocations() {
        stubFor(
            get(urlPathEqualTo("/x/jobs/locations"))
                .willReturn(okJson(ExampleResponses.read("examples/x/jobs/listLocations.json")))
        )
        val params = JobListLocationsParams.builder().q("new york").limit(5L).build()

        val responses =
            listOf(
                client.x().jobs().listLocations(params),
                client.async().x().jobs().listLocations(params),
            )

        verify(
            2,
            getRequestedFor(urlPathEqualTo("/x/jobs/locations"))
                .withQueryParam("q", equalTo("new york"))
                .withQueryParam("limit", equalTo("5")),
        )
        responses.forEach { response ->
            assertThat(response.count()).isEqualTo(2L)
            assertThat(response.locations()).hasSize(2)
            assertThat(response.locations()[0].id()).isEqualTo("1720462281849397612")
            assertThat(response.locations()[0].name()).isEqualTo("New York, US")
            assertThat(response.locations()[1].id()).isEqualTo("1720462281920696805")
            assertThat(response.locations()[1].name()).isEqualTo("New York City, NY")
        }
    }

    @Test
    suspend fun search() {
        stubFor(
            get(urlPathEqualTo("/x/jobs/search"))
                .willReturn(okJson(ExampleResponses.read("examples/x/jobs/search.json")))
        )
        val params =
            JobSearchParams.builder()
                .q("engineer")
                .company("SpaceX")
                .employmentType("full_time")
                .location("Berlin")
                .locationId("1720462281849397612")
                .locationType("remote,hybrid")
                .pageSize(1L)
                .seniority("senior,lead")
                .build()

        val responses =
            listOf(client.x().jobs().search(params), client.async().x().jobs().search(params))

        verify(
            2,
            getRequestedFor(urlPathEqualTo("/x/jobs/search"))
                .withQueryParam("q", equalTo("engineer"))
                .withQueryParam("company", equalTo("SpaceX"))
                .withQueryParam("employmentType", equalTo("full_time"))
                .withQueryParam("location", equalTo("Berlin"))
                .withQueryParam("locationId", equalTo("1720462281849397612"))
                .withQueryParam("locationType", equalTo("remote,hybrid"))
                .withQueryParam("pageSize", equalTo("1"))
                .withQueryParam("seniority", equalTo("senior,lead")),
        )
        responses.forEach { response ->
            assertThat(response.hasNextPage()).isEqualTo(true)
            assertThat(response.jobs()).hasSize(1)
            assertThat(response.jobs()[0].id()).isEqualTo("1899202395210649861")
            assertThat(response.jobs()[0].url())
                .isEqualTo("https://x.com/i/jobs/1899202395210649861")
            assertThat(response.jobs()[0].account()!!.id()).isEqualTo("1812126202804711425")
            assertThat(response.jobs()[0].account()!!.verified()).isEqualTo(true)
            assertThat(response.jobs()[0].account()!!.name()).isEqualTo("commonware")
            assertThat(response.jobs()[0].account()!!.username()).isEqualTo("commonwarexyz")
            assertThat(response.jobs()[0].account()!!.verifiedType()).isEqualTo("Business")
            assertThat(response.jobs()[0].applyUrl()).isEqualTo("https://xquik.com/example")
            assertThat(response.jobs()[0].formattedSalary()).isEqualTo("Starting at \$225K")
            assertThat(response.jobs()[0].location()).isEqualTo("United States")
            assertThat(response.jobs()[0].salaryCurrencyCode()).isEqualTo("USD")
            assertThat(response.jobs()[0].salaryInterval()).isEqualTo("annually")
            assertThat(response.jobs()[0].salaryMin()).isEqualTo(225000.0)
            assertThat(response.jobs()[0].title()).isEqualTo("Engineer")
            assertThat(response.nextCursor()).isEqualTo("DAACCgACGRElMJcAAA")
        }
    }
}
