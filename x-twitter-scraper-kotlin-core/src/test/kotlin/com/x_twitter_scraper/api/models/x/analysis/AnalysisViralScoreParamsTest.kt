// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.analysis

import com.x_twitter_scraper.api.core.JsonValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AnalysisViralScoreParamsTest {

    @Test
    fun create() {
        AnalysisViralScoreParams.builder()
            .analysis(
                AnalysisViralScoreParams.Analysis.builder()
                    .concurrency(16L)
                    .context("Consumer headphones & customer service.")
                    .maxContextBytes(64000L)
                    .preset(AnalysisViralScoreParams.Analysis.Preset.COMPLAINTS)
                    .addQuestion(
                        AnalysisViralScoreParams.Analysis.Question.builder()
                            .id("request")
                            .instructions("What does the author ask for?")
                            .type(AnalysisViralScoreParams.Analysis.Question.Type.CHOICE)
                            .version("1")
                            .categories(
                                AnalysisViralScoreParams.Analysis.Question.Categories.builder()
                                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                                    .build()
                            )
                            .criteria(
                                AnalysisViralScoreParams.Analysis.Question.Criteria.builder()
                                    .no("Asks for no refund.")
                                    .yes("Asks for a refund.")
                                    .build()
                            )
                            .addLevel("No urgency.")
                            .build()
                    )
                    .addTarget(
                        AnalysisViralScoreParams.Analysis.Target.builder()
                            .name("Sony")
                            .addAlias("WH-1000XM6")
                            .build()
                    )
                    .build()
            )
            .addBaseline(
                AnalysisViralScoreParams.Baseline.builder()
                    .answers(
                        AnalysisViralScoreParams.Baseline.Answers.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .id("1893456789012345678")
                    .monitor(
                        AnalysisViralScoreParams.Baseline.Monitor.builder()
                            .configurationFingerprint(
                                "9f2c4e1b7a5d3c8e6f0a1b2c3d4e5f60718293a4b5c6d7e8f90a1b2c3d4e5f6a"
                            )
                            .build()
                    )
                    .tweet(
                        AnalysisViralScoreParams.Baseline.Tweet.builder()
                            .id("1893456789012345678")
                            .build()
                    )
                    .build()
            )
            .cursor("DAACCgACGRElMJcAAA")
            .limit(20L)
            .listId("1234567890")
            .query("Sony WH-1000XM6 lang:en")
            .queryType(AnalysisViralScoreParams.QueryType.LATEST)
            .quotesOf("1893456789012345678")
            .repliesTo("1893456789012345678")
            .sinceTime(AnalysisViralScoreParams.SinceTime.ofString("string"))
            .addText("We shipped dark mode today. Tell us what breaks.")
            .addTweetId("1893456789012345678")
            .untilTime(AnalysisViralScoreParams.UntilTime.ofString("string"))
            .username("@NASA")
            .build()
    }

    @Test
    fun body() {
        val params =
            AnalysisViralScoreParams.builder()
                .analysis(
                    AnalysisViralScoreParams.Analysis.builder()
                        .concurrency(16L)
                        .context("Consumer headphones & customer service.")
                        .maxContextBytes(64000L)
                        .preset(AnalysisViralScoreParams.Analysis.Preset.COMPLAINTS)
                        .addQuestion(
                            AnalysisViralScoreParams.Analysis.Question.builder()
                                .id("request")
                                .instructions("What does the author ask for?")
                                .type(AnalysisViralScoreParams.Analysis.Question.Type.CHOICE)
                                .version("1")
                                .categories(
                                    AnalysisViralScoreParams.Analysis.Question.Categories.builder()
                                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                                        .build()
                                )
                                .criteria(
                                    AnalysisViralScoreParams.Analysis.Question.Criteria.builder()
                                        .no("Asks for no refund.")
                                        .yes("Asks for a refund.")
                                        .build()
                                )
                                .addLevel("No urgency.")
                                .build()
                        )
                        .addTarget(
                            AnalysisViralScoreParams.Analysis.Target.builder()
                                .name("Sony")
                                .addAlias("WH-1000XM6")
                                .build()
                        )
                        .build()
                )
                .addBaseline(
                    AnalysisViralScoreParams.Baseline.builder()
                        .answers(
                            AnalysisViralScoreParams.Baseline.Answers.builder()
                                .putAdditionalProperty("foo", JsonValue.from("bar"))
                                .build()
                        )
                        .id("1893456789012345678")
                        .monitor(
                            AnalysisViralScoreParams.Baseline.Monitor.builder()
                                .configurationFingerprint(
                                    "9f2c4e1b7a5d3c8e6f0a1b2c3d4e5f60718293a4b5c6d7e8f90a1b2c3d4e5f6a"
                                )
                                .build()
                        )
                        .tweet(
                            AnalysisViralScoreParams.Baseline.Tweet.builder()
                                .id("1893456789012345678")
                                .build()
                        )
                        .build()
                )
                .cursor("DAACCgACGRElMJcAAA")
                .limit(20L)
                .listId("1234567890")
                .query("Sony WH-1000XM6 lang:en")
                .queryType(AnalysisViralScoreParams.QueryType.LATEST)
                .quotesOf("1893456789012345678")
                .repliesTo("1893456789012345678")
                .sinceTime(AnalysisViralScoreParams.SinceTime.ofString("string"))
                .addText("We shipped dark mode today. Tell us what breaks.")
                .addTweetId("1893456789012345678")
                .untilTime(AnalysisViralScoreParams.UntilTime.ofString("string"))
                .username("@NASA")
                .build()

        val body = params._body()

        assertThat(body.analysis())
            .isEqualTo(
                AnalysisViralScoreParams.Analysis.builder()
                    .concurrency(16L)
                    .context("Consumer headphones & customer service.")
                    .maxContextBytes(64000L)
                    .preset(AnalysisViralScoreParams.Analysis.Preset.COMPLAINTS)
                    .addQuestion(
                        AnalysisViralScoreParams.Analysis.Question.builder()
                            .id("request")
                            .instructions("What does the author ask for?")
                            .type(AnalysisViralScoreParams.Analysis.Question.Type.CHOICE)
                            .version("1")
                            .categories(
                                AnalysisViralScoreParams.Analysis.Question.Categories.builder()
                                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                                    .build()
                            )
                            .criteria(
                                AnalysisViralScoreParams.Analysis.Question.Criteria.builder()
                                    .no("Asks for no refund.")
                                    .yes("Asks for a refund.")
                                    .build()
                            )
                            .addLevel("No urgency.")
                            .build()
                    )
                    .addTarget(
                        AnalysisViralScoreParams.Analysis.Target.builder()
                            .name("Sony")
                            .addAlias("WH-1000XM6")
                            .build()
                    )
                    .build()
            )
        assertThat(body.baseline())
            .containsExactly(
                AnalysisViralScoreParams.Baseline.builder()
                    .answers(
                        AnalysisViralScoreParams.Baseline.Answers.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .id("1893456789012345678")
                    .monitor(
                        AnalysisViralScoreParams.Baseline.Monitor.builder()
                            .configurationFingerprint(
                                "9f2c4e1b7a5d3c8e6f0a1b2c3d4e5f60718293a4b5c6d7e8f90a1b2c3d4e5f6a"
                            )
                            .build()
                    )
                    .tweet(
                        AnalysisViralScoreParams.Baseline.Tweet.builder()
                            .id("1893456789012345678")
                            .build()
                    )
                    .build()
            )
        assertThat(body.cursor()).isEqualTo("DAACCgACGRElMJcAAA")
        assertThat(body.limit()).isEqualTo(20L)
        assertThat(body.listId()).isEqualTo("1234567890")
        assertThat(body.query()).isEqualTo("Sony WH-1000XM6 lang:en")
        assertThat(body.queryType()).isEqualTo(AnalysisViralScoreParams.QueryType.LATEST)
        assertThat(body.quotesOf()).isEqualTo("1893456789012345678")
        assertThat(body.repliesTo()).isEqualTo("1893456789012345678")
        assertThat(body.sinceTime())
            .isEqualTo(AnalysisViralScoreParams.SinceTime.ofString("string"))
        assertThat(body.texts()).containsExactly("We shipped dark mode today. Tell us what breaks.")
        assertThat(body.tweetIds()).containsExactly("1893456789012345678")
        assertThat(body.untilTime())
            .isEqualTo(AnalysisViralScoreParams.UntilTime.ofString("string"))
        assertThat(body.username()).isEqualTo("@NASA")
    }
}
