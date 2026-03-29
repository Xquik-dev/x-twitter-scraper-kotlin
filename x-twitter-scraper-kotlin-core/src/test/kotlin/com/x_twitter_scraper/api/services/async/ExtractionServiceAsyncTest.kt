// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async

import com.github.tomakehurst.wiremock.client.WireMock.anyUrl
import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.ok
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo
import com.github.tomakehurst.wiremock.junit5.WireMockTest
import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClientAsync
import com.x_twitter_scraper.api.models.extractions.ExtractionEstimateCostParams
import com.x_twitter_scraper.api.models.extractions.ExtractionExportResultsParams
import com.x_twitter_scraper.api.models.extractions.ExtractionListParams
import com.x_twitter_scraper.api.models.extractions.ExtractionRetrieveParams
import com.x_twitter_scraper.api.models.extractions.ExtractionRunParams
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.ResourceLock

@WireMockTest
@ResourceLock("https://github.com/wiremock/wiremock/issues/169")
internal class ExtractionServiceAsyncTest {

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun retrieve() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val extractionServiceAsync = client.extractions()

        val extraction =
            extractionServiceAsync.retrieve(
                ExtractionRetrieveParams.builder().id("id").after("after").limit(1L).build()
            )

        extraction.validate()
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun list() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val extractionServiceAsync = client.extractions()

        val extractions =
            extractionServiceAsync.list(
                ExtractionListParams.builder()
                    .after("after")
                    .limit(1L)
                    .status(ExtractionListParams.Status.RUNNING)
                    .toolType(ExtractionListParams.ToolType.ARTICLE_EXTRACTOR)
                    .build()
            )

        extractions.validate()
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun estimateCost() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val extractionServiceAsync = client.extractions()

        val response =
            extractionServiceAsync.estimateCost(
                ExtractionEstimateCostParams.builder()
                    .toolType(ExtractionEstimateCostParams.ToolType.ARTICLE_EXTRACTOR)
                    .advancedQuery("advancedQuery")
                    .exactPhrase("exactPhrase")
                    .excludeWords("excludeWords")
                    .searchQuery("searchQuery")
                    .targetCommunityId("targetCommunityId")
                    .targetListId("targetListId")
                    .targetSpaceId("targetSpaceId")
                    .targetTweetId("targetTweetId")
                    .targetUsername("targetUsername")
                    .build()
            )

        response.validate()
    }

    @Test
    suspend fun exportResults(wmRuntimeInfo: WireMockRuntimeInfo) {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(wmRuntimeInfo.httpBaseUrl)
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val extractionServiceAsync = client.extractions()
        stubFor(get(anyUrl()).willReturn(ok().withBody("abc")))

        val response =
            extractionServiceAsync.exportResults(
                ExtractionExportResultsParams.builder()
                    .id("id")
                    .format(ExtractionExportResultsParams.Format.CSV)
                    .build()
            )

        assertThat(response.body()).hasContent("abc")
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun run() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val extractionServiceAsync = client.extractions()

        val response =
            extractionServiceAsync.run(
                ExtractionRunParams.builder()
                    .toolType(ExtractionRunParams.ToolType.ARTICLE_EXTRACTOR)
                    .advancedQuery("advancedQuery")
                    .exactPhrase("exactPhrase")
                    .excludeWords("excludeWords")
                    .searchQuery("searchQuery")
                    .targetCommunityId("targetCommunityId")
                    .targetListId("targetListId")
                    .targetSpaceId("targetSpaceId")
                    .targetTweetId("targetTweetId")
                    .targetUsername("targetUsername")
                    .build()
            )

        response.validate()
    }
}
