// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.analysis

import com.x_twitter_scraper.api.core.JsonValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AnalysisNewsParamsTest {

    @Test
    fun create() {
        AnalysisNewsParams.builder()
            .analysis(
                AnalysisNewsParams.Analysis.builder()
                    .concurrency(16L)
                    .context("Consumer headphones & customer service.")
                    .maxContextBytes(64000L)
                    .preset(AnalysisNewsParams.Analysis.Preset.COMPLAINTS)
                    .addQuestion(
                        AnalysisNewsParams.Analysis.Question.builder()
                            .id("request")
                            .instructions("What does the author ask for?")
                            .type(AnalysisNewsParams.Analysis.Question.Type.CHOICE)
                            .version("1")
                            .categories(
                                AnalysisNewsParams.Analysis.Question.Categories.builder()
                                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                                    .build()
                            )
                            .criteria(
                                AnalysisNewsParams.Analysis.Question.Criteria.builder()
                                    .no("Asks for no refund.")
                                    .yes("Asks for a refund.")
                                    .build()
                            )
                            .addLevel("No urgency.")
                            .build()
                    )
                    .addTarget(
                        AnalysisNewsParams.Analysis.Target.builder()
                            .name("Sony")
                            .addAlias("WH-1000XM6")
                            .build()
                    )
                    .build()
            )
            .addBaseline(
                AnalysisNewsParams.Baseline.builder()
                    .answers(
                        AnalysisNewsParams.Baseline.Answers.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .id("1893456789012345678")
                    .monitor(
                        AnalysisNewsParams.Baseline.Monitor.builder()
                            .configurationFingerprint(
                                "9f2c4e1b7a5d3c8e6f0a1b2c3d4e5f60718293a4b5c6d7e8f90a1b2c3d4e5f6a"
                            )
                            .build()
                    )
                    .tweet(
                        AnalysisNewsParams.Baseline.Tweet.builder()
                            .id("1893456789012345678")
                            .build()
                    )
                    .build()
            )
            .cursor("DAACCgACGRElMJcAAA")
            .limit(20L)
            .listId("1234567890")
            .query("Sony WH-1000XM6 lang:en")
            .queryType(AnalysisNewsParams.QueryType.LATEST)
            .quotesOf("1893456789012345678")
            .repliesTo("1893456789012345678")
            .sinceTime(AnalysisNewsParams.SinceTime.ofString("string"))
            .addText("We shipped dark mode today. Tell us what breaks.")
            .addTweetId("1893456789012345678")
            .untilTime(AnalysisNewsParams.UntilTime.ofString("string"))
            .username("@NASA")
            .build()
    }

    @Test
    fun body() {
        val params =
            AnalysisNewsParams.builder()
                .analysis(
                    AnalysisNewsParams.Analysis.builder()
                        .concurrency(16L)
                        .context("Consumer headphones & customer service.")
                        .maxContextBytes(64000L)
                        .preset(AnalysisNewsParams.Analysis.Preset.COMPLAINTS)
                        .addQuestion(
                            AnalysisNewsParams.Analysis.Question.builder()
                                .id("request")
                                .instructions("What does the author ask for?")
                                .type(AnalysisNewsParams.Analysis.Question.Type.CHOICE)
                                .version("1")
                                .categories(
                                    AnalysisNewsParams.Analysis.Question.Categories.builder()
                                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                                        .build()
                                )
                                .criteria(
                                    AnalysisNewsParams.Analysis.Question.Criteria.builder()
                                        .no("Asks for no refund.")
                                        .yes("Asks for a refund.")
                                        .build()
                                )
                                .addLevel("No urgency.")
                                .build()
                        )
                        .addTarget(
                            AnalysisNewsParams.Analysis.Target.builder()
                                .name("Sony")
                                .addAlias("WH-1000XM6")
                                .build()
                        )
                        .build()
                )
                .addBaseline(
                    AnalysisNewsParams.Baseline.builder()
                        .answers(
                            AnalysisNewsParams.Baseline.Answers.builder()
                                .putAdditionalProperty("foo", JsonValue.from("bar"))
                                .build()
                        )
                        .id("1893456789012345678")
                        .monitor(
                            AnalysisNewsParams.Baseline.Monitor.builder()
                                .configurationFingerprint(
                                    "9f2c4e1b7a5d3c8e6f0a1b2c3d4e5f60718293a4b5c6d7e8f90a1b2c3d4e5f6a"
                                )
                                .build()
                        )
                        .tweet(
                            AnalysisNewsParams.Baseline.Tweet.builder()
                                .id("1893456789012345678")
                                .build()
                        )
                        .build()
                )
                .cursor("DAACCgACGRElMJcAAA")
                .limit(20L)
                .listId("1234567890")
                .query("Sony WH-1000XM6 lang:en")
                .queryType(AnalysisNewsParams.QueryType.LATEST)
                .quotesOf("1893456789012345678")
                .repliesTo("1893456789012345678")
                .sinceTime(AnalysisNewsParams.SinceTime.ofString("string"))
                .addText("We shipped dark mode today. Tell us what breaks.")
                .addTweetId("1893456789012345678")
                .untilTime(AnalysisNewsParams.UntilTime.ofString("string"))
                .username("@NASA")
                .build()

        val body = params._body()

        assertThat(body.analysis())
            .isEqualTo(
                AnalysisNewsParams.Analysis.builder()
                    .concurrency(16L)
                    .context("Consumer headphones & customer service.")
                    .maxContextBytes(64000L)
                    .preset(AnalysisNewsParams.Analysis.Preset.COMPLAINTS)
                    .addQuestion(
                        AnalysisNewsParams.Analysis.Question.builder()
                            .id("request")
                            .instructions("What does the author ask for?")
                            .type(AnalysisNewsParams.Analysis.Question.Type.CHOICE)
                            .version("1")
                            .categories(
                                AnalysisNewsParams.Analysis.Question.Categories.builder()
                                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                                    .build()
                            )
                            .criteria(
                                AnalysisNewsParams.Analysis.Question.Criteria.builder()
                                    .no("Asks for no refund.")
                                    .yes("Asks for a refund.")
                                    .build()
                            )
                            .addLevel("No urgency.")
                            .build()
                    )
                    .addTarget(
                        AnalysisNewsParams.Analysis.Target.builder()
                            .name("Sony")
                            .addAlias("WH-1000XM6")
                            .build()
                    )
                    .build()
            )
        assertThat(body.baseline())
            .containsExactly(
                AnalysisNewsParams.Baseline.builder()
                    .answers(
                        AnalysisNewsParams.Baseline.Answers.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .id("1893456789012345678")
                    .monitor(
                        AnalysisNewsParams.Baseline.Monitor.builder()
                            .configurationFingerprint(
                                "9f2c4e1b7a5d3c8e6f0a1b2c3d4e5f60718293a4b5c6d7e8f90a1b2c3d4e5f6a"
                            )
                            .build()
                    )
                    .tweet(
                        AnalysisNewsParams.Baseline.Tweet.builder()
                            .id("1893456789012345678")
                            .build()
                    )
                    .build()
            )
        assertThat(body.cursor()).isEqualTo("DAACCgACGRElMJcAAA")
        assertThat(body.limit()).isEqualTo(20L)
        assertThat(body.listId()).isEqualTo("1234567890")
        assertThat(body.query()).isEqualTo("Sony WH-1000XM6 lang:en")
        assertThat(body.queryType()).isEqualTo(AnalysisNewsParams.QueryType.LATEST)
        assertThat(body.quotesOf()).isEqualTo("1893456789012345678")
        assertThat(body.repliesTo()).isEqualTo("1893456789012345678")
        assertThat(body.sinceTime()).isEqualTo(AnalysisNewsParams.SinceTime.ofString("string"))
        assertThat(body.texts()).containsExactly("We shipped dark mode today. Tell us what breaks.")
        assertThat(body.tweetIds()).containsExactly("1893456789012345678")
        assertThat(body.untilTime()).isEqualTo(AnalysisNewsParams.UntilTime.ofString("string"))
        assertThat(body.username()).isEqualTo("@NASA")
    }
}
