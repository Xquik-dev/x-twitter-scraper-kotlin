// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.analysis

import com.x_twitter_scraper.api.core.JsonValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AnalysisSentimentParamsTest {

    @Test
    fun create() {
        AnalysisSentimentParams.builder()
            .analysis(
                AnalysisSentimentParams.Analysis.builder()
                    .concurrency(16L)
                    .context("Consumer headphones & customer service.")
                    .maxContextBytes(64000L)
                    .preset(AnalysisSentimentParams.Analysis.Preset.COMPLAINTS)
                    .addQuestion(
                        AnalysisSentimentParams.Analysis.Question.builder()
                            .id("request")
                            .instructions("What does the author ask for?")
                            .type(AnalysisSentimentParams.Analysis.Question.Type.CHOICE)
                            .version("1")
                            .categories(
                                AnalysisSentimentParams.Analysis.Question.Categories.builder()
                                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                                    .build()
                            )
                            .criteria(
                                AnalysisSentimentParams.Analysis.Question.Criteria.builder()
                                    .no("Asks for no refund.")
                                    .yes("Asks for a refund.")
                                    .build()
                            )
                            .addLevel("No urgency.")
                            .build()
                    )
                    .addTarget(
                        AnalysisSentimentParams.Analysis.Target.builder()
                            .name("Sony")
                            .addAlias("WH-1000XM6")
                            .build()
                    )
                    .build()
            )
            .addBaseline(
                AnalysisSentimentParams.Baseline.builder()
                    .answers(
                        AnalysisSentimentParams.Baseline.Answers.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .id("1893456789012345678")
                    .monitor(
                        AnalysisSentimentParams.Baseline.Monitor.builder()
                            .configurationFingerprint(
                                "9f2c4e1b7a5d3c8e6f0a1b2c3d4e5f60718293a4b5c6d7e8f90a1b2c3d4e5f6a"
                            )
                            .build()
                    )
                    .tweet(
                        AnalysisSentimentParams.Baseline.Tweet.builder()
                            .id("1893456789012345678")
                            .build()
                    )
                    .build()
            )
            .cursor("DAACCgACGRElMJcAAA")
            .limit(20L)
            .listId("1234567890")
            .query("Sony WH-1000XM6 lang:en")
            .queryType(AnalysisSentimentParams.QueryType.LATEST)
            .quotesOf("1893456789012345678")
            .repliesTo("1893456789012345678")
            .sinceTime(AnalysisSentimentParams.SinceTime.ofString("string"))
            .addText("We shipped dark mode today. Tell us what breaks.")
            .addTweetId("1893456789012345678")
            .untilTime(AnalysisSentimentParams.UntilTime.ofString("string"))
            .username("@NASA")
            .build()
    }

    @Test
    fun body() {
        val params =
            AnalysisSentimentParams.builder()
                .analysis(
                    AnalysisSentimentParams.Analysis.builder()
                        .concurrency(16L)
                        .context("Consumer headphones & customer service.")
                        .maxContextBytes(64000L)
                        .preset(AnalysisSentimentParams.Analysis.Preset.COMPLAINTS)
                        .addQuestion(
                            AnalysisSentimentParams.Analysis.Question.builder()
                                .id("request")
                                .instructions("What does the author ask for?")
                                .type(AnalysisSentimentParams.Analysis.Question.Type.CHOICE)
                                .version("1")
                                .categories(
                                    AnalysisSentimentParams.Analysis.Question.Categories.builder()
                                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                                        .build()
                                )
                                .criteria(
                                    AnalysisSentimentParams.Analysis.Question.Criteria.builder()
                                        .no("Asks for no refund.")
                                        .yes("Asks for a refund.")
                                        .build()
                                )
                                .addLevel("No urgency.")
                                .build()
                        )
                        .addTarget(
                            AnalysisSentimentParams.Analysis.Target.builder()
                                .name("Sony")
                                .addAlias("WH-1000XM6")
                                .build()
                        )
                        .build()
                )
                .addBaseline(
                    AnalysisSentimentParams.Baseline.builder()
                        .answers(
                            AnalysisSentimentParams.Baseline.Answers.builder()
                                .putAdditionalProperty("foo", JsonValue.from("bar"))
                                .build()
                        )
                        .id("1893456789012345678")
                        .monitor(
                            AnalysisSentimentParams.Baseline.Monitor.builder()
                                .configurationFingerprint(
                                    "9f2c4e1b7a5d3c8e6f0a1b2c3d4e5f60718293a4b5c6d7e8f90a1b2c3d4e5f6a"
                                )
                                .build()
                        )
                        .tweet(
                            AnalysisSentimentParams.Baseline.Tweet.builder()
                                .id("1893456789012345678")
                                .build()
                        )
                        .build()
                )
                .cursor("DAACCgACGRElMJcAAA")
                .limit(20L)
                .listId("1234567890")
                .query("Sony WH-1000XM6 lang:en")
                .queryType(AnalysisSentimentParams.QueryType.LATEST)
                .quotesOf("1893456789012345678")
                .repliesTo("1893456789012345678")
                .sinceTime(AnalysisSentimentParams.SinceTime.ofString("string"))
                .addText("We shipped dark mode today. Tell us what breaks.")
                .addTweetId("1893456789012345678")
                .untilTime(AnalysisSentimentParams.UntilTime.ofString("string"))
                .username("@NASA")
                .build()

        val body = params._body()

        assertThat(body.analysis())
            .isEqualTo(
                AnalysisSentimentParams.Analysis.builder()
                    .concurrency(16L)
                    .context("Consumer headphones & customer service.")
                    .maxContextBytes(64000L)
                    .preset(AnalysisSentimentParams.Analysis.Preset.COMPLAINTS)
                    .addQuestion(
                        AnalysisSentimentParams.Analysis.Question.builder()
                            .id("request")
                            .instructions("What does the author ask for?")
                            .type(AnalysisSentimentParams.Analysis.Question.Type.CHOICE)
                            .version("1")
                            .categories(
                                AnalysisSentimentParams.Analysis.Question.Categories.builder()
                                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                                    .build()
                            )
                            .criteria(
                                AnalysisSentimentParams.Analysis.Question.Criteria.builder()
                                    .no("Asks for no refund.")
                                    .yes("Asks for a refund.")
                                    .build()
                            )
                            .addLevel("No urgency.")
                            .build()
                    )
                    .addTarget(
                        AnalysisSentimentParams.Analysis.Target.builder()
                            .name("Sony")
                            .addAlias("WH-1000XM6")
                            .build()
                    )
                    .build()
            )
        assertThat(body.baseline())
            .containsExactly(
                AnalysisSentimentParams.Baseline.builder()
                    .answers(
                        AnalysisSentimentParams.Baseline.Answers.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .id("1893456789012345678")
                    .monitor(
                        AnalysisSentimentParams.Baseline.Monitor.builder()
                            .configurationFingerprint(
                                "9f2c4e1b7a5d3c8e6f0a1b2c3d4e5f60718293a4b5c6d7e8f90a1b2c3d4e5f6a"
                            )
                            .build()
                    )
                    .tweet(
                        AnalysisSentimentParams.Baseline.Tweet.builder()
                            .id("1893456789012345678")
                            .build()
                    )
                    .build()
            )
        assertThat(body.cursor()).isEqualTo("DAACCgACGRElMJcAAA")
        assertThat(body.limit()).isEqualTo(20L)
        assertThat(body.listId()).isEqualTo("1234567890")
        assertThat(body.query()).isEqualTo("Sony WH-1000XM6 lang:en")
        assertThat(body.queryType()).isEqualTo(AnalysisSentimentParams.QueryType.LATEST)
        assertThat(body.quotesOf()).isEqualTo("1893456789012345678")
        assertThat(body.repliesTo()).isEqualTo("1893456789012345678")
        assertThat(body.sinceTime()).isEqualTo(AnalysisSentimentParams.SinceTime.ofString("string"))
        assertThat(body.texts()).containsExactly("We shipped dark mode today. Tell us what breaks.")
        assertThat(body.tweetIds()).containsExactly("1893456789012345678")
        assertThat(body.untilTime()).isEqualTo(AnalysisSentimentParams.UntilTime.ofString("string"))
        assertThat(body.username()).isEqualTo("@NASA")
    }
}
