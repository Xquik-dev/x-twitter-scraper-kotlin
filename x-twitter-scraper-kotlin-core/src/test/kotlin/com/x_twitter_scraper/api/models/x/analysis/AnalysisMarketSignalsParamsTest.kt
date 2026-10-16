// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.analysis

import com.x_twitter_scraper.api.core.JsonValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AnalysisMarketSignalsParamsTest {

    @Test
    fun create() {
        AnalysisMarketSignalsParams.builder()
            .analysis(
                AnalysisMarketSignalsParams.Analysis.builder()
                    .concurrency(16L)
                    .context("Consumer headphones & customer service.")
                    .maxContextBytes(64000L)
                    .preset(AnalysisMarketSignalsParams.Analysis.Preset.COMPLAINTS)
                    .addQuestion(
                        AnalysisMarketSignalsParams.Analysis.Question.builder()
                            .id("request")
                            .instructions("What does the author ask for?")
                            .type(AnalysisMarketSignalsParams.Analysis.Question.Type.CHOICE)
                            .version("1")
                            .categories(
                                AnalysisMarketSignalsParams.Analysis.Question.Categories.builder()
                                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                                    .build()
                            )
                            .criteria(
                                AnalysisMarketSignalsParams.Analysis.Question.Criteria.builder()
                                    .no("Asks for no refund.")
                                    .yes("Asks for a refund.")
                                    .build()
                            )
                            .addLevel("No urgency.")
                            .build()
                    )
                    .addTarget(
                        AnalysisMarketSignalsParams.Analysis.Target.builder()
                            .name("Sony")
                            .addAlias("WH-1000XM6")
                            .build()
                    )
                    .build()
            )
            .addBaseline(
                AnalysisMarketSignalsParams.Baseline.builder()
                    .answers(
                        AnalysisMarketSignalsParams.Baseline.Answers.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .id("1893456789012345678")
                    .monitor(
                        AnalysisMarketSignalsParams.Baseline.Monitor.builder()
                            .configurationFingerprint(
                                "9f2c4e1b7a5d3c8e6f0a1b2c3d4e5f60718293a4b5c6d7e8f90a1b2c3d4e5f6a"
                            )
                            .build()
                    )
                    .tweet(
                        AnalysisMarketSignalsParams.Baseline.Tweet.builder()
                            .id("1893456789012345678")
                            .build()
                    )
                    .build()
            )
            .cursor("DAACCgACGRElMJcAAA")
            .limit(20L)
            .listId("1234567890")
            .query("Sony WH-1000XM6 lang:en")
            .queryType(AnalysisMarketSignalsParams.QueryType.LATEST)
            .quotesOf("1893456789012345678")
            .repliesTo("1893456789012345678")
            .sinceTime(AnalysisMarketSignalsParams.SinceTime.ofString("string"))
            .addText("We shipped dark mode today. Tell us what breaks.")
            .addTweetId("1893456789012345678")
            .untilTime(AnalysisMarketSignalsParams.UntilTime.ofString("string"))
            .username("@NASA")
            .build()
    }

    @Test
    fun body() {
        val params =
            AnalysisMarketSignalsParams.builder()
                .analysis(
                    AnalysisMarketSignalsParams.Analysis.builder()
                        .concurrency(16L)
                        .context("Consumer headphones & customer service.")
                        .maxContextBytes(64000L)
                        .preset(AnalysisMarketSignalsParams.Analysis.Preset.COMPLAINTS)
                        .addQuestion(
                            AnalysisMarketSignalsParams.Analysis.Question.builder()
                                .id("request")
                                .instructions("What does the author ask for?")
                                .type(AnalysisMarketSignalsParams.Analysis.Question.Type.CHOICE)
                                .version("1")
                                .categories(
                                    AnalysisMarketSignalsParams.Analysis.Question.Categories
                                        .builder()
                                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                                        .build()
                                )
                                .criteria(
                                    AnalysisMarketSignalsParams.Analysis.Question.Criteria.builder()
                                        .no("Asks for no refund.")
                                        .yes("Asks for a refund.")
                                        .build()
                                )
                                .addLevel("No urgency.")
                                .build()
                        )
                        .addTarget(
                            AnalysisMarketSignalsParams.Analysis.Target.builder()
                                .name("Sony")
                                .addAlias("WH-1000XM6")
                                .build()
                        )
                        .build()
                )
                .addBaseline(
                    AnalysisMarketSignalsParams.Baseline.builder()
                        .answers(
                            AnalysisMarketSignalsParams.Baseline.Answers.builder()
                                .putAdditionalProperty("foo", JsonValue.from("bar"))
                                .build()
                        )
                        .id("1893456789012345678")
                        .monitor(
                            AnalysisMarketSignalsParams.Baseline.Monitor.builder()
                                .configurationFingerprint(
                                    "9f2c4e1b7a5d3c8e6f0a1b2c3d4e5f60718293a4b5c6d7e8f90a1b2c3d4e5f6a"
                                )
                                .build()
                        )
                        .tweet(
                            AnalysisMarketSignalsParams.Baseline.Tweet.builder()
                                .id("1893456789012345678")
                                .build()
                        )
                        .build()
                )
                .cursor("DAACCgACGRElMJcAAA")
                .limit(20L)
                .listId("1234567890")
                .query("Sony WH-1000XM6 lang:en")
                .queryType(AnalysisMarketSignalsParams.QueryType.LATEST)
                .quotesOf("1893456789012345678")
                .repliesTo("1893456789012345678")
                .sinceTime(AnalysisMarketSignalsParams.SinceTime.ofString("string"))
                .addText("We shipped dark mode today. Tell us what breaks.")
                .addTweetId("1893456789012345678")
                .untilTime(AnalysisMarketSignalsParams.UntilTime.ofString("string"))
                .username("@NASA")
                .build()

        val body = params._body()

        assertThat(body.analysis())
            .isEqualTo(
                AnalysisMarketSignalsParams.Analysis.builder()
                    .concurrency(16L)
                    .context("Consumer headphones & customer service.")
                    .maxContextBytes(64000L)
                    .preset(AnalysisMarketSignalsParams.Analysis.Preset.COMPLAINTS)
                    .addQuestion(
                        AnalysisMarketSignalsParams.Analysis.Question.builder()
                            .id("request")
                            .instructions("What does the author ask for?")
                            .type(AnalysisMarketSignalsParams.Analysis.Question.Type.CHOICE)
                            .version("1")
                            .categories(
                                AnalysisMarketSignalsParams.Analysis.Question.Categories.builder()
                                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                                    .build()
                            )
                            .criteria(
                                AnalysisMarketSignalsParams.Analysis.Question.Criteria.builder()
                                    .no("Asks for no refund.")
                                    .yes("Asks for a refund.")
                                    .build()
                            )
                            .addLevel("No urgency.")
                            .build()
                    )
                    .addTarget(
                        AnalysisMarketSignalsParams.Analysis.Target.builder()
                            .name("Sony")
                            .addAlias("WH-1000XM6")
                            .build()
                    )
                    .build()
            )
        assertThat(body.baseline())
            .containsExactly(
                AnalysisMarketSignalsParams.Baseline.builder()
                    .answers(
                        AnalysisMarketSignalsParams.Baseline.Answers.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .id("1893456789012345678")
                    .monitor(
                        AnalysisMarketSignalsParams.Baseline.Monitor.builder()
                            .configurationFingerprint(
                                "9f2c4e1b7a5d3c8e6f0a1b2c3d4e5f60718293a4b5c6d7e8f90a1b2c3d4e5f6a"
                            )
                            .build()
                    )
                    .tweet(
                        AnalysisMarketSignalsParams.Baseline.Tweet.builder()
                            .id("1893456789012345678")
                            .build()
                    )
                    .build()
            )
        assertThat(body.cursor()).isEqualTo("DAACCgACGRElMJcAAA")
        assertThat(body.limit()).isEqualTo(20L)
        assertThat(body.listId()).isEqualTo("1234567890")
        assertThat(body.query()).isEqualTo("Sony WH-1000XM6 lang:en")
        assertThat(body.queryType()).isEqualTo(AnalysisMarketSignalsParams.QueryType.LATEST)
        assertThat(body.quotesOf()).isEqualTo("1893456789012345678")
        assertThat(body.repliesTo()).isEqualTo("1893456789012345678")
        assertThat(body.sinceTime())
            .isEqualTo(AnalysisMarketSignalsParams.SinceTime.ofString("string"))
        assertThat(body.texts()).containsExactly("We shipped dark mode today. Tell us what breaks.")
        assertThat(body.tweetIds()).containsExactly("1893456789012345678")
        assertThat(body.untilTime())
            .isEqualTo(AnalysisMarketSignalsParams.UntilTime.ofString("string"))
        assertThat(body.username()).isEqualTo("@NASA")
    }
}
