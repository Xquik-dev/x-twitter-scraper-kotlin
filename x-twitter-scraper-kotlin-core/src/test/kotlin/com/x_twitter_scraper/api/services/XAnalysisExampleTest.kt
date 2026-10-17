// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.services

import com.github.tomakehurst.wiremock.client.WireMock.equalToJson
import com.github.tomakehurst.wiremock.client.WireMock.okJson
import com.github.tomakehurst.wiremock.client.WireMock.post
import com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo
import com.github.tomakehurst.wiremock.client.WireMock.verify
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo
import com.github.tomakehurst.wiremock.junit5.WireMockTest
import com.x_twitter_scraper.api.client.XTwitterScraperClient
import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClient
import com.x_twitter_scraper.api.core.JsonValue
import com.x_twitter_scraper.api.models.x.analysis.AnalysisBrandParams
import com.x_twitter_scraper.api.models.x.analysis.AnalysisClassifyParams
import com.x_twitter_scraper.api.models.x.analysis.AnalysisMarketSignalsParams
import com.x_twitter_scraper.api.models.x.analysis.AnalysisNewsParams
import com.x_twitter_scraper.api.models.x.analysis.AnalysisSentimentParams
import com.x_twitter_scraper.api.models.x.analysis.AnalysisViralScoreParams
import com.x_twitter_scraper.api.models.x.analysis.TweetAnalysisPage
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.ResourceLock

@WireMockTest
@ResourceLock("https://github.com/wiremock/wiremock/issues/169")
internal class XAnalysisExampleTest {

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
    suspend fun sentiment() {
        stubFor(
            post(urlPathEqualTo("/x/analysis/sentiment"))
                .willReturn(okJson(ExampleResponses.read("examples/TweetAnalysisPage.json")))
        )
        val params =
            AnalysisSentimentParams.builder()
                .cursor("DAACCgACGRElMJcAAA")
                .limit(20L)
                .listId("1234567890")
                .build()

        val responses =
            listOf(
                client.x().analysis().sentiment(params),
                client.async().x().analysis().sentiment(params),
            )

        verify(
            2,
            postRequestedFor(urlPathEqualTo("/x/analysis/sentiment"))
                .withRequestBody(
                    equalToJson(
                        "{\"cursor\": \"DAACCgACGRElMJcAAA\", \"limit\": 20, \"listId\": \"1234567890\"}"
                    )
                ),
        )
        responses.forEach { response ->
            assertThat(response.analysisSummary().cashtags()).hasSize(0)
            assertThat(response.analysisSummary().engagement()).isEqualTo(37L)
            assertThat(response.analysisSummary().monitor()!!.changedRows()).hasSize(1)
            assertThat(response.analysisSummary().monitor()!!.changedRows()[0].changes()).hasSize(1)
            assertThat(
                    response.analysisSummary().monitor()!!.changedRows()[0].changes()[0].current()
                )
                .isEqualTo("mixed")
            assertThat(
                    response.analysisSummary().monitor()!!.changedRows()[0].changes()[0].previous()
                )
                .isEqualTo("positive")
            assertThat(
                    response
                        .analysisSummary()
                        .monitor()!!
                        .changedRows()[0]
                        .changes()[0]
                        .questionId()
                )
                .isEqualTo("sentiment")
            assertThat(response.analysisSummary().monitor()!!.changedRows()[0].tweetId())
                .isEqualTo("1893456789012345678")
            assertThat(response.analysisSummary().rows().analyzed()).isEqualTo(1L)
            assertThat(response.analysisSummary().rows().failed()).isEqualTo(0L)
            assertThat(response.analysisSummary().rows().skipped()).isEqualTo(0L)
            assertThat(response.analysisSummary().schemaVersion()).isEqualTo(1L)
            assertThat(response.analysisSummary().sourceDomains()).hasSize(0)
            assertThat(response.analysisSummary().targets()).hasSize(0)
            assertThat(response.hasNextPage()).isEqualTo(false)
            assertThat(response.nextCursor()).isEqualTo("")
            assertThat(response.results()).hasSize(1)
            assertThat(response.results()[0].analysis().answers()).hasSize(3)
            assertThat(response.results()[0].analysis().answers()[0].questionId())
                .isEqualTo("sentiment")
            assertThat(response.results()[0].analysis().answers()[0].questionVersion())
                .isEqualTo("sentiment:2")
            assertThat(response.results()[0].analysis().answers()[0].type())
                .isEqualTo(TweetAnalysisPage.Result.Analysis.Answer.Type.CHOICE)
            assertThat(response.results()[0].analysis().answers()[0].confidence()).isEqualTo(0.88)
            assertThat(response.results()[0].analysis().answers()[1].questionId())
                .isEqualTo("intensity")
            assertThat(response.results()[0].analysis().answers()[1].questionVersion())
                .isEqualTo("sentiment:2")
            assertThat(response.results()[0].analysis().answers()[1].type())
                .isEqualTo(TweetAnalysisPage.Result.Analysis.Answer.Type.SCORE)
            assertThat(response.results()[0].analysis().answers()[1].confidence()).isEqualTo(0.81)
            assertThat(response.results()[0].analysis().answers()[2].questionId())
                .isEqualTo("sarcasm")
            assertThat(response.results()[0].analysis().answers()[2].questionVersion())
                .isEqualTo("sentiment:2")
            assertThat(response.results()[0].analysis().answers()[2].type())
                .isEqualTo(TweetAnalysisPage.Result.Analysis.Answer.Type.PROBABILITY)
            assertThat(response.results()[0].analysis().answers()[2].probability()).isEqualTo(0.03)
            assertThat(response.results()[0].analysis().contextAvailability().article())
                .isEqualTo(
                    TweetAnalysisPage.Result.Analysis.ContextAvailability.Article.NOT_SUPPLIED
                )
            assertThat(response.results()[0].analysis().contextAvailability().author())
                .isEqualTo(TweetAnalysisPage.Result.Analysis.ContextAvailability.Author.AVAILABLE)
            assertThat(response.results()[0].analysis().contextAvailability().media())
                .isEqualTo(TweetAnalysisPage.Result.Analysis.ContextAvailability.Media.NOT_SUPPLIED)
            assertThat(response.results()[0].analysis().contextAvailability().postText())
                .isEqualTo(TweetAnalysisPage.Result.Analysis.ContextAvailability.PostText.FULL)
            assertThat(response.results()[0].analysis().contextAvailability().quote())
                .isEqualTo(
                    TweetAnalysisPage.Result.Analysis.ContextAvailability.Quote.NOT_APPLICABLE
                )
            assertThat(response.results()[0].analysis().contextAvailability().reply())
                .isEqualTo(
                    TweetAnalysisPage.Result.Analysis.ContextAvailability.Reply.NOT_APPLICABLE
                )
            assertThat(response.results()[0].analysis().contextBytes()).isEqualTo(612L)
            assertThat(response.results()[0].analysis().questions()).hasSize(3)
            assertThat(response.results()[0].analysis().questions()[0].id()).isEqualTo("sentiment")
            assertThat(response.results()[0].analysis().questions()[0].version())
                .isEqualTo("sentiment:2")
            assertThat(response.results()[0].analysis().questions()[1].id()).isEqualTo("intensity")
            assertThat(response.results()[0].analysis().questions()[1].version())
                .isEqualTo("sentiment:2")
            assertThat(response.results()[0].analysis().questions()[2].id()).isEqualTo("sarcasm")
            assertThat(response.results()[0].analysis().questions()[2].version())
                .isEqualTo("sentiment:2")
            assertThat(response.results()[0].analysis()._status())
                .isEqualTo(JsonValue.from("succeeded"))
            assertThat(response.results()[0].cashtags()).hasSize(0)
            assertThat(response.results()[0].monitor()._baselineDatasetId())
                .isEqualTo(JsonValue.from(null))
            assertThat(response.results()[0].monitor().changedQuestionIds())
                .containsExactly("sentiment")
        }
    }

    @Test
    suspend fun viralScore() {
        stubFor(
            post(urlPathEqualTo("/x/analysis/viral-score"))
                .willReturn(okJson(ExampleResponses.read("examples/TweetAnalysisPage.json")))
        )
        val params =
            AnalysisViralScoreParams.builder()
                .cursor("DAACCgACGRElMJcAAA")
                .limit(20L)
                .listId("1234567890")
                .build()

        val responses =
            listOf(
                client.x().analysis().viralScore(params),
                client.async().x().analysis().viralScore(params),
            )

        verify(
            2,
            postRequestedFor(urlPathEqualTo("/x/analysis/viral-score"))
                .withRequestBody(
                    equalToJson(
                        "{\"cursor\": \"DAACCgACGRElMJcAAA\", \"limit\": 20, \"listId\": \"1234567890\"}"
                    )
                ),
        )
        responses.forEach { response ->
            assertThat(response.analysisSummary().cashtags()).hasSize(0)
            assertThat(response.analysisSummary().engagement()).isEqualTo(37L)
            assertThat(response.analysisSummary().monitor()!!.changedRows()).hasSize(1)
            assertThat(response.analysisSummary().monitor()!!.changedRows()[0].changes()).hasSize(1)
            assertThat(
                    response.analysisSummary().monitor()!!.changedRows()[0].changes()[0].current()
                )
                .isEqualTo("mixed")
            assertThat(
                    response.analysisSummary().monitor()!!.changedRows()[0].changes()[0].previous()
                )
                .isEqualTo("positive")
            assertThat(
                    response
                        .analysisSummary()
                        .monitor()!!
                        .changedRows()[0]
                        .changes()[0]
                        .questionId()
                )
                .isEqualTo("sentiment")
            assertThat(response.analysisSummary().monitor()!!.changedRows()[0].tweetId())
                .isEqualTo("1893456789012345678")
            assertThat(response.analysisSummary().rows().analyzed()).isEqualTo(1L)
            assertThat(response.analysisSummary().rows().failed()).isEqualTo(0L)
            assertThat(response.analysisSummary().rows().skipped()).isEqualTo(0L)
            assertThat(response.analysisSummary().schemaVersion()).isEqualTo(1L)
            assertThat(response.analysisSummary().sourceDomains()).hasSize(0)
            assertThat(response.analysisSummary().targets()).hasSize(0)
            assertThat(response.hasNextPage()).isEqualTo(false)
            assertThat(response.nextCursor()).isEqualTo("")
            assertThat(response.results()).hasSize(1)
            assertThat(response.results()[0].analysis().answers()).hasSize(3)
            assertThat(response.results()[0].analysis().answers()[0].questionId())
                .isEqualTo("sentiment")
            assertThat(response.results()[0].analysis().answers()[0].questionVersion())
                .isEqualTo("sentiment:2")
            assertThat(response.results()[0].analysis().answers()[0].type())
                .isEqualTo(TweetAnalysisPage.Result.Analysis.Answer.Type.CHOICE)
            assertThat(response.results()[0].analysis().answers()[0].confidence()).isEqualTo(0.88)
            assertThat(response.results()[0].analysis().answers()[1].questionId())
                .isEqualTo("intensity")
            assertThat(response.results()[0].analysis().answers()[1].questionVersion())
                .isEqualTo("sentiment:2")
            assertThat(response.results()[0].analysis().answers()[1].type())
                .isEqualTo(TweetAnalysisPage.Result.Analysis.Answer.Type.SCORE)
            assertThat(response.results()[0].analysis().answers()[1].confidence()).isEqualTo(0.81)
            assertThat(response.results()[0].analysis().answers()[2].questionId())
                .isEqualTo("sarcasm")
            assertThat(response.results()[0].analysis().answers()[2].questionVersion())
                .isEqualTo("sentiment:2")
            assertThat(response.results()[0].analysis().answers()[2].type())
                .isEqualTo(TweetAnalysisPage.Result.Analysis.Answer.Type.PROBABILITY)
            assertThat(response.results()[0].analysis().answers()[2].probability()).isEqualTo(0.03)
            assertThat(response.results()[0].analysis().contextAvailability().article())
                .isEqualTo(
                    TweetAnalysisPage.Result.Analysis.ContextAvailability.Article.NOT_SUPPLIED
                )
            assertThat(response.results()[0].analysis().contextAvailability().author())
                .isEqualTo(TweetAnalysisPage.Result.Analysis.ContextAvailability.Author.AVAILABLE)
            assertThat(response.results()[0].analysis().contextAvailability().media())
                .isEqualTo(TweetAnalysisPage.Result.Analysis.ContextAvailability.Media.NOT_SUPPLIED)
            assertThat(response.results()[0].analysis().contextAvailability().postText())
                .isEqualTo(TweetAnalysisPage.Result.Analysis.ContextAvailability.PostText.FULL)
            assertThat(response.results()[0].analysis().contextAvailability().quote())
                .isEqualTo(
                    TweetAnalysisPage.Result.Analysis.ContextAvailability.Quote.NOT_APPLICABLE
                )
            assertThat(response.results()[0].analysis().contextAvailability().reply())
                .isEqualTo(
                    TweetAnalysisPage.Result.Analysis.ContextAvailability.Reply.NOT_APPLICABLE
                )
            assertThat(response.results()[0].analysis().contextBytes()).isEqualTo(612L)
            assertThat(response.results()[0].analysis().questions()).hasSize(3)
            assertThat(response.results()[0].analysis().questions()[0].id()).isEqualTo("sentiment")
            assertThat(response.results()[0].analysis().questions()[0].version())
                .isEqualTo("sentiment:2")
            assertThat(response.results()[0].analysis().questions()[1].id()).isEqualTo("intensity")
            assertThat(response.results()[0].analysis().questions()[1].version())
                .isEqualTo("sentiment:2")
            assertThat(response.results()[0].analysis().questions()[2].id()).isEqualTo("sarcasm")
            assertThat(response.results()[0].analysis().questions()[2].version())
                .isEqualTo("sentiment:2")
            assertThat(response.results()[0].analysis()._status())
                .isEqualTo(JsonValue.from("succeeded"))
            assertThat(response.results()[0].cashtags()).hasSize(0)
            assertThat(response.results()[0].monitor()._baselineDatasetId())
                .isEqualTo(JsonValue.from(null))
            assertThat(response.results()[0].monitor().changedQuestionIds())
                .containsExactly("sentiment")
        }
    }

    @Test
    suspend fun brand() {
        stubFor(
            post(urlPathEqualTo("/x/analysis/brand"))
                .willReturn(okJson(ExampleResponses.read("examples/TweetAnalysisPage.json")))
        )
        val params =
            AnalysisBrandParams.builder()
                .cursor("DAACCgACGRElMJcAAA")
                .limit(20L)
                .listId("1234567890")
                .build()

        val responses =
            listOf(client.x().analysis().brand(params), client.async().x().analysis().brand(params))

        verify(
            2,
            postRequestedFor(urlPathEqualTo("/x/analysis/brand"))
                .withRequestBody(
                    equalToJson(
                        "{\"cursor\": \"DAACCgACGRElMJcAAA\", \"limit\": 20, \"listId\": \"1234567890\"}"
                    )
                ),
        )
        responses.forEach { response ->
            assertThat(response.analysisSummary().cashtags()).hasSize(0)
            assertThat(response.analysisSummary().engagement()).isEqualTo(37L)
            assertThat(response.analysisSummary().monitor()!!.changedRows()).hasSize(1)
            assertThat(response.analysisSummary().monitor()!!.changedRows()[0].changes()).hasSize(1)
            assertThat(
                    response.analysisSummary().monitor()!!.changedRows()[0].changes()[0].current()
                )
                .isEqualTo("mixed")
            assertThat(
                    response.analysisSummary().monitor()!!.changedRows()[0].changes()[0].previous()
                )
                .isEqualTo("positive")
            assertThat(
                    response
                        .analysisSummary()
                        .monitor()!!
                        .changedRows()[0]
                        .changes()[0]
                        .questionId()
                )
                .isEqualTo("sentiment")
            assertThat(response.analysisSummary().monitor()!!.changedRows()[0].tweetId())
                .isEqualTo("1893456789012345678")
            assertThat(response.analysisSummary().rows().analyzed()).isEqualTo(1L)
            assertThat(response.analysisSummary().rows().failed()).isEqualTo(0L)
            assertThat(response.analysisSummary().rows().skipped()).isEqualTo(0L)
            assertThat(response.analysisSummary().schemaVersion()).isEqualTo(1L)
            assertThat(response.analysisSummary().sourceDomains()).hasSize(0)
            assertThat(response.analysisSummary().targets()).hasSize(0)
            assertThat(response.hasNextPage()).isEqualTo(false)
            assertThat(response.nextCursor()).isEqualTo("")
            assertThat(response.results()).hasSize(1)
            assertThat(response.results()[0].analysis().answers()).hasSize(3)
            assertThat(response.results()[0].analysis().answers()[0].questionId())
                .isEqualTo("sentiment")
            assertThat(response.results()[0].analysis().answers()[0].questionVersion())
                .isEqualTo("sentiment:2")
            assertThat(response.results()[0].analysis().answers()[0].type())
                .isEqualTo(TweetAnalysisPage.Result.Analysis.Answer.Type.CHOICE)
            assertThat(response.results()[0].analysis().answers()[0].confidence()).isEqualTo(0.88)
            assertThat(response.results()[0].analysis().answers()[1].questionId())
                .isEqualTo("intensity")
            assertThat(response.results()[0].analysis().answers()[1].questionVersion())
                .isEqualTo("sentiment:2")
            assertThat(response.results()[0].analysis().answers()[1].type())
                .isEqualTo(TweetAnalysisPage.Result.Analysis.Answer.Type.SCORE)
            assertThat(response.results()[0].analysis().answers()[1].confidence()).isEqualTo(0.81)
            assertThat(response.results()[0].analysis().answers()[2].questionId())
                .isEqualTo("sarcasm")
            assertThat(response.results()[0].analysis().answers()[2].questionVersion())
                .isEqualTo("sentiment:2")
            assertThat(response.results()[0].analysis().answers()[2].type())
                .isEqualTo(TweetAnalysisPage.Result.Analysis.Answer.Type.PROBABILITY)
            assertThat(response.results()[0].analysis().answers()[2].probability()).isEqualTo(0.03)
            assertThat(response.results()[0].analysis().contextAvailability().article())
                .isEqualTo(
                    TweetAnalysisPage.Result.Analysis.ContextAvailability.Article.NOT_SUPPLIED
                )
            assertThat(response.results()[0].analysis().contextAvailability().author())
                .isEqualTo(TweetAnalysisPage.Result.Analysis.ContextAvailability.Author.AVAILABLE)
            assertThat(response.results()[0].analysis().contextAvailability().media())
                .isEqualTo(TweetAnalysisPage.Result.Analysis.ContextAvailability.Media.NOT_SUPPLIED)
            assertThat(response.results()[0].analysis().contextAvailability().postText())
                .isEqualTo(TweetAnalysisPage.Result.Analysis.ContextAvailability.PostText.FULL)
            assertThat(response.results()[0].analysis().contextAvailability().quote())
                .isEqualTo(
                    TweetAnalysisPage.Result.Analysis.ContextAvailability.Quote.NOT_APPLICABLE
                )
            assertThat(response.results()[0].analysis().contextAvailability().reply())
                .isEqualTo(
                    TweetAnalysisPage.Result.Analysis.ContextAvailability.Reply.NOT_APPLICABLE
                )
            assertThat(response.results()[0].analysis().contextBytes()).isEqualTo(612L)
            assertThat(response.results()[0].analysis().questions()).hasSize(3)
            assertThat(response.results()[0].analysis().questions()[0].id()).isEqualTo("sentiment")
            assertThat(response.results()[0].analysis().questions()[0].version())
                .isEqualTo("sentiment:2")
            assertThat(response.results()[0].analysis().questions()[1].id()).isEqualTo("intensity")
            assertThat(response.results()[0].analysis().questions()[1].version())
                .isEqualTo("sentiment:2")
            assertThat(response.results()[0].analysis().questions()[2].id()).isEqualTo("sarcasm")
            assertThat(response.results()[0].analysis().questions()[2].version())
                .isEqualTo("sentiment:2")
            assertThat(response.results()[0].analysis()._status())
                .isEqualTo(JsonValue.from("succeeded"))
            assertThat(response.results()[0].cashtags()).hasSize(0)
            assertThat(response.results()[0].monitor()._baselineDatasetId())
                .isEqualTo(JsonValue.from(null))
            assertThat(response.results()[0].monitor().changedQuestionIds())
                .containsExactly("sentiment")
        }
    }

    @Test
    suspend fun news() {
        stubFor(
            post(urlPathEqualTo("/x/analysis/news"))
                .willReturn(okJson(ExampleResponses.read("examples/TweetAnalysisPage.json")))
        )
        val params =
            AnalysisNewsParams.builder()
                .cursor("DAACCgACGRElMJcAAA")
                .limit(20L)
                .listId("1234567890")
                .build()

        val responses =
            listOf(client.x().analysis().news(params), client.async().x().analysis().news(params))

        verify(
            2,
            postRequestedFor(urlPathEqualTo("/x/analysis/news"))
                .withRequestBody(
                    equalToJson(
                        "{\"cursor\": \"DAACCgACGRElMJcAAA\", \"limit\": 20, \"listId\": \"1234567890\"}"
                    )
                ),
        )
        responses.forEach { response ->
            assertThat(response.analysisSummary().cashtags()).hasSize(0)
            assertThat(response.analysisSummary().engagement()).isEqualTo(37L)
            assertThat(response.analysisSummary().monitor()!!.changedRows()).hasSize(1)
            assertThat(response.analysisSummary().monitor()!!.changedRows()[0].changes()).hasSize(1)
            assertThat(
                    response.analysisSummary().monitor()!!.changedRows()[0].changes()[0].current()
                )
                .isEqualTo("mixed")
            assertThat(
                    response.analysisSummary().monitor()!!.changedRows()[0].changes()[0].previous()
                )
                .isEqualTo("positive")
            assertThat(
                    response
                        .analysisSummary()
                        .monitor()!!
                        .changedRows()[0]
                        .changes()[0]
                        .questionId()
                )
                .isEqualTo("sentiment")
            assertThat(response.analysisSummary().monitor()!!.changedRows()[0].tweetId())
                .isEqualTo("1893456789012345678")
            assertThat(response.analysisSummary().rows().analyzed()).isEqualTo(1L)
            assertThat(response.analysisSummary().rows().failed()).isEqualTo(0L)
            assertThat(response.analysisSummary().rows().skipped()).isEqualTo(0L)
            assertThat(response.analysisSummary().schemaVersion()).isEqualTo(1L)
            assertThat(response.analysisSummary().sourceDomains()).hasSize(0)
            assertThat(response.analysisSummary().targets()).hasSize(0)
            assertThat(response.hasNextPage()).isEqualTo(false)
            assertThat(response.nextCursor()).isEqualTo("")
            assertThat(response.results()).hasSize(1)
            assertThat(response.results()[0].analysis().answers()).hasSize(3)
            assertThat(response.results()[0].analysis().answers()[0].questionId())
                .isEqualTo("sentiment")
            assertThat(response.results()[0].analysis().answers()[0].questionVersion())
                .isEqualTo("sentiment:2")
            assertThat(response.results()[0].analysis().answers()[0].type())
                .isEqualTo(TweetAnalysisPage.Result.Analysis.Answer.Type.CHOICE)
            assertThat(response.results()[0].analysis().answers()[0].confidence()).isEqualTo(0.88)
            assertThat(response.results()[0].analysis().answers()[1].questionId())
                .isEqualTo("intensity")
            assertThat(response.results()[0].analysis().answers()[1].questionVersion())
                .isEqualTo("sentiment:2")
            assertThat(response.results()[0].analysis().answers()[1].type())
                .isEqualTo(TweetAnalysisPage.Result.Analysis.Answer.Type.SCORE)
            assertThat(response.results()[0].analysis().answers()[1].confidence()).isEqualTo(0.81)
            assertThat(response.results()[0].analysis().answers()[2].questionId())
                .isEqualTo("sarcasm")
            assertThat(response.results()[0].analysis().answers()[2].questionVersion())
                .isEqualTo("sentiment:2")
            assertThat(response.results()[0].analysis().answers()[2].type())
                .isEqualTo(TweetAnalysisPage.Result.Analysis.Answer.Type.PROBABILITY)
            assertThat(response.results()[0].analysis().answers()[2].probability()).isEqualTo(0.03)
            assertThat(response.results()[0].analysis().contextAvailability().article())
                .isEqualTo(
                    TweetAnalysisPage.Result.Analysis.ContextAvailability.Article.NOT_SUPPLIED
                )
            assertThat(response.results()[0].analysis().contextAvailability().author())
                .isEqualTo(TweetAnalysisPage.Result.Analysis.ContextAvailability.Author.AVAILABLE)
            assertThat(response.results()[0].analysis().contextAvailability().media())
                .isEqualTo(TweetAnalysisPage.Result.Analysis.ContextAvailability.Media.NOT_SUPPLIED)
            assertThat(response.results()[0].analysis().contextAvailability().postText())
                .isEqualTo(TweetAnalysisPage.Result.Analysis.ContextAvailability.PostText.FULL)
            assertThat(response.results()[0].analysis().contextAvailability().quote())
                .isEqualTo(
                    TweetAnalysisPage.Result.Analysis.ContextAvailability.Quote.NOT_APPLICABLE
                )
            assertThat(response.results()[0].analysis().contextAvailability().reply())
                .isEqualTo(
                    TweetAnalysisPage.Result.Analysis.ContextAvailability.Reply.NOT_APPLICABLE
                )
            assertThat(response.results()[0].analysis().contextBytes()).isEqualTo(612L)
            assertThat(response.results()[0].analysis().questions()).hasSize(3)
            assertThat(response.results()[0].analysis().questions()[0].id()).isEqualTo("sentiment")
            assertThat(response.results()[0].analysis().questions()[0].version())
                .isEqualTo("sentiment:2")
            assertThat(response.results()[0].analysis().questions()[1].id()).isEqualTo("intensity")
            assertThat(response.results()[0].analysis().questions()[1].version())
                .isEqualTo("sentiment:2")
            assertThat(response.results()[0].analysis().questions()[2].id()).isEqualTo("sarcasm")
            assertThat(response.results()[0].analysis().questions()[2].version())
                .isEqualTo("sentiment:2")
            assertThat(response.results()[0].analysis()._status())
                .isEqualTo(JsonValue.from("succeeded"))
            assertThat(response.results()[0].cashtags()).hasSize(0)
            assertThat(response.results()[0].monitor()._baselineDatasetId())
                .isEqualTo(JsonValue.from(null))
            assertThat(response.results()[0].monitor().changedQuestionIds())
                .containsExactly("sentiment")
        }
    }

    @Test
    suspend fun marketSignals() {
        stubFor(
            post(urlPathEqualTo("/x/analysis/market-signals"))
                .willReturn(okJson(ExampleResponses.read("examples/TweetAnalysisPage.json")))
        )
        val params =
            AnalysisMarketSignalsParams.builder()
                .cursor("DAACCgACGRElMJcAAA")
                .limit(20L)
                .listId("1234567890")
                .build()

        val responses =
            listOf(
                client.x().analysis().marketSignals(params),
                client.async().x().analysis().marketSignals(params),
            )

        verify(
            2,
            postRequestedFor(urlPathEqualTo("/x/analysis/market-signals"))
                .withRequestBody(
                    equalToJson(
                        "{\"cursor\": \"DAACCgACGRElMJcAAA\", \"limit\": 20, \"listId\": \"1234567890\"}"
                    )
                ),
        )
        responses.forEach { response ->
            assertThat(response.analysisSummary().cashtags()).hasSize(0)
            assertThat(response.analysisSummary().engagement()).isEqualTo(37L)
            assertThat(response.analysisSummary().monitor()!!.changedRows()).hasSize(1)
            assertThat(response.analysisSummary().monitor()!!.changedRows()[0].changes()).hasSize(1)
            assertThat(
                    response.analysisSummary().monitor()!!.changedRows()[0].changes()[0].current()
                )
                .isEqualTo("mixed")
            assertThat(
                    response.analysisSummary().monitor()!!.changedRows()[0].changes()[0].previous()
                )
                .isEqualTo("positive")
            assertThat(
                    response
                        .analysisSummary()
                        .monitor()!!
                        .changedRows()[0]
                        .changes()[0]
                        .questionId()
                )
                .isEqualTo("sentiment")
            assertThat(response.analysisSummary().monitor()!!.changedRows()[0].tweetId())
                .isEqualTo("1893456789012345678")
            assertThat(response.analysisSummary().rows().analyzed()).isEqualTo(1L)
            assertThat(response.analysisSummary().rows().failed()).isEqualTo(0L)
            assertThat(response.analysisSummary().rows().skipped()).isEqualTo(0L)
            assertThat(response.analysisSummary().schemaVersion()).isEqualTo(1L)
            assertThat(response.analysisSummary().sourceDomains()).hasSize(0)
            assertThat(response.analysisSummary().targets()).hasSize(0)
            assertThat(response.hasNextPage()).isEqualTo(false)
            assertThat(response.nextCursor()).isEqualTo("")
            assertThat(response.results()).hasSize(1)
            assertThat(response.results()[0].analysis().answers()).hasSize(3)
            assertThat(response.results()[0].analysis().answers()[0].questionId())
                .isEqualTo("sentiment")
            assertThat(response.results()[0].analysis().answers()[0].questionVersion())
                .isEqualTo("sentiment:2")
            assertThat(response.results()[0].analysis().answers()[0].type())
                .isEqualTo(TweetAnalysisPage.Result.Analysis.Answer.Type.CHOICE)
            assertThat(response.results()[0].analysis().answers()[0].confidence()).isEqualTo(0.88)
            assertThat(response.results()[0].analysis().answers()[1].questionId())
                .isEqualTo("intensity")
            assertThat(response.results()[0].analysis().answers()[1].questionVersion())
                .isEqualTo("sentiment:2")
            assertThat(response.results()[0].analysis().answers()[1].type())
                .isEqualTo(TweetAnalysisPage.Result.Analysis.Answer.Type.SCORE)
            assertThat(response.results()[0].analysis().answers()[1].confidence()).isEqualTo(0.81)
            assertThat(response.results()[0].analysis().answers()[2].questionId())
                .isEqualTo("sarcasm")
            assertThat(response.results()[0].analysis().answers()[2].questionVersion())
                .isEqualTo("sentiment:2")
            assertThat(response.results()[0].analysis().answers()[2].type())
                .isEqualTo(TweetAnalysisPage.Result.Analysis.Answer.Type.PROBABILITY)
            assertThat(response.results()[0].analysis().answers()[2].probability()).isEqualTo(0.03)
            assertThat(response.results()[0].analysis().contextAvailability().article())
                .isEqualTo(
                    TweetAnalysisPage.Result.Analysis.ContextAvailability.Article.NOT_SUPPLIED
                )
            assertThat(response.results()[0].analysis().contextAvailability().author())
                .isEqualTo(TweetAnalysisPage.Result.Analysis.ContextAvailability.Author.AVAILABLE)
            assertThat(response.results()[0].analysis().contextAvailability().media())
                .isEqualTo(TweetAnalysisPage.Result.Analysis.ContextAvailability.Media.NOT_SUPPLIED)
            assertThat(response.results()[0].analysis().contextAvailability().postText())
                .isEqualTo(TweetAnalysisPage.Result.Analysis.ContextAvailability.PostText.FULL)
            assertThat(response.results()[0].analysis().contextAvailability().quote())
                .isEqualTo(
                    TweetAnalysisPage.Result.Analysis.ContextAvailability.Quote.NOT_APPLICABLE
                )
            assertThat(response.results()[0].analysis().contextAvailability().reply())
                .isEqualTo(
                    TweetAnalysisPage.Result.Analysis.ContextAvailability.Reply.NOT_APPLICABLE
                )
            assertThat(response.results()[0].analysis().contextBytes()).isEqualTo(612L)
            assertThat(response.results()[0].analysis().questions()).hasSize(3)
            assertThat(response.results()[0].analysis().questions()[0].id()).isEqualTo("sentiment")
            assertThat(response.results()[0].analysis().questions()[0].version())
                .isEqualTo("sentiment:2")
            assertThat(response.results()[0].analysis().questions()[1].id()).isEqualTo("intensity")
            assertThat(response.results()[0].analysis().questions()[1].version())
                .isEqualTo("sentiment:2")
            assertThat(response.results()[0].analysis().questions()[2].id()).isEqualTo("sarcasm")
            assertThat(response.results()[0].analysis().questions()[2].version())
                .isEqualTo("sentiment:2")
            assertThat(response.results()[0].analysis()._status())
                .isEqualTo(JsonValue.from("succeeded"))
            assertThat(response.results()[0].cashtags()).hasSize(0)
            assertThat(response.results()[0].monitor()._baselineDatasetId())
                .isEqualTo(JsonValue.from(null))
            assertThat(response.results()[0].monitor().changedQuestionIds())
                .containsExactly("sentiment")
        }
    }

    @Test
    suspend fun classify() {
        stubFor(
            post(urlPathEqualTo("/x/analysis/classify"))
                .willReturn(okJson(ExampleResponses.read("examples/TweetAnalysisPage.json")))
        )
        val params =
            AnalysisClassifyParams.builder()
                .cursor("DAACCgACGRElMJcAAA")
                .limit(20L)
                .listId("1234567890")
                .build()

        val responses =
            listOf(
                client.x().analysis().classify(params),
                client.async().x().analysis().classify(params),
            )

        verify(
            2,
            postRequestedFor(urlPathEqualTo("/x/analysis/classify"))
                .withRequestBody(
                    equalToJson(
                        "{\"cursor\": \"DAACCgACGRElMJcAAA\", \"limit\": 20, \"listId\": \"1234567890\"}"
                    )
                ),
        )
        responses.forEach { response ->
            assertThat(response.analysisSummary().cashtags()).hasSize(0)
            assertThat(response.analysisSummary().engagement()).isEqualTo(37L)
            assertThat(response.analysisSummary().monitor()!!.changedRows()).hasSize(1)
            assertThat(response.analysisSummary().monitor()!!.changedRows()[0].changes()).hasSize(1)
            assertThat(
                    response.analysisSummary().monitor()!!.changedRows()[0].changes()[0].current()
                )
                .isEqualTo("mixed")
            assertThat(
                    response.analysisSummary().monitor()!!.changedRows()[0].changes()[0].previous()
                )
                .isEqualTo("positive")
            assertThat(
                    response
                        .analysisSummary()
                        .monitor()!!
                        .changedRows()[0]
                        .changes()[0]
                        .questionId()
                )
                .isEqualTo("sentiment")
            assertThat(response.analysisSummary().monitor()!!.changedRows()[0].tweetId())
                .isEqualTo("1893456789012345678")
            assertThat(response.analysisSummary().rows().analyzed()).isEqualTo(1L)
            assertThat(response.analysisSummary().rows().failed()).isEqualTo(0L)
            assertThat(response.analysisSummary().rows().skipped()).isEqualTo(0L)
            assertThat(response.analysisSummary().schemaVersion()).isEqualTo(1L)
            assertThat(response.analysisSummary().sourceDomains()).hasSize(0)
            assertThat(response.analysisSummary().targets()).hasSize(0)
            assertThat(response.hasNextPage()).isEqualTo(false)
            assertThat(response.nextCursor()).isEqualTo("")
            assertThat(response.results()).hasSize(1)
            assertThat(response.results()[0].analysis().answers()).hasSize(3)
            assertThat(response.results()[0].analysis().answers()[0].questionId())
                .isEqualTo("sentiment")
            assertThat(response.results()[0].analysis().answers()[0].questionVersion())
                .isEqualTo("sentiment:2")
            assertThat(response.results()[0].analysis().answers()[0].type())
                .isEqualTo(TweetAnalysisPage.Result.Analysis.Answer.Type.CHOICE)
            assertThat(response.results()[0].analysis().answers()[0].confidence()).isEqualTo(0.88)
            assertThat(response.results()[0].analysis().answers()[1].questionId())
                .isEqualTo("intensity")
            assertThat(response.results()[0].analysis().answers()[1].questionVersion())
                .isEqualTo("sentiment:2")
            assertThat(response.results()[0].analysis().answers()[1].type())
                .isEqualTo(TweetAnalysisPage.Result.Analysis.Answer.Type.SCORE)
            assertThat(response.results()[0].analysis().answers()[1].confidence()).isEqualTo(0.81)
            assertThat(response.results()[0].analysis().answers()[2].questionId())
                .isEqualTo("sarcasm")
            assertThat(response.results()[0].analysis().answers()[2].questionVersion())
                .isEqualTo("sentiment:2")
            assertThat(response.results()[0].analysis().answers()[2].type())
                .isEqualTo(TweetAnalysisPage.Result.Analysis.Answer.Type.PROBABILITY)
            assertThat(response.results()[0].analysis().answers()[2].probability()).isEqualTo(0.03)
            assertThat(response.results()[0].analysis().contextAvailability().article())
                .isEqualTo(
                    TweetAnalysisPage.Result.Analysis.ContextAvailability.Article.NOT_SUPPLIED
                )
            assertThat(response.results()[0].analysis().contextAvailability().author())
                .isEqualTo(TweetAnalysisPage.Result.Analysis.ContextAvailability.Author.AVAILABLE)
            assertThat(response.results()[0].analysis().contextAvailability().media())
                .isEqualTo(TweetAnalysisPage.Result.Analysis.ContextAvailability.Media.NOT_SUPPLIED)
            assertThat(response.results()[0].analysis().contextAvailability().postText())
                .isEqualTo(TweetAnalysisPage.Result.Analysis.ContextAvailability.PostText.FULL)
            assertThat(response.results()[0].analysis().contextAvailability().quote())
                .isEqualTo(
                    TweetAnalysisPage.Result.Analysis.ContextAvailability.Quote.NOT_APPLICABLE
                )
            assertThat(response.results()[0].analysis().contextAvailability().reply())
                .isEqualTo(
                    TweetAnalysisPage.Result.Analysis.ContextAvailability.Reply.NOT_APPLICABLE
                )
            assertThat(response.results()[0].analysis().contextBytes()).isEqualTo(612L)
            assertThat(response.results()[0].analysis().questions()).hasSize(3)
            assertThat(response.results()[0].analysis().questions()[0].id()).isEqualTo("sentiment")
            assertThat(response.results()[0].analysis().questions()[0].version())
                .isEqualTo("sentiment:2")
            assertThat(response.results()[0].analysis().questions()[1].id()).isEqualTo("intensity")
            assertThat(response.results()[0].analysis().questions()[1].version())
                .isEqualTo("sentiment:2")
            assertThat(response.results()[0].analysis().questions()[2].id()).isEqualTo("sarcasm")
            assertThat(response.results()[0].analysis().questions()[2].version())
                .isEqualTo("sentiment:2")
            assertThat(response.results()[0].analysis()._status())
                .isEqualTo(JsonValue.from("succeeded"))
            assertThat(response.results()[0].cashtags()).hasSize(0)
            assertThat(response.results()[0].monitor()._baselineDatasetId())
                .isEqualTo(JsonValue.from(null))
            assertThat(response.results()[0].monitor().changedQuestionIds())
                .containsExactly("sentiment")
        }
    }
}
