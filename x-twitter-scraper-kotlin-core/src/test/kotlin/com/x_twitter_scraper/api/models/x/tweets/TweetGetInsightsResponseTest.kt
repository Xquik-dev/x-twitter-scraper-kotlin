// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.tweets

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TweetGetInsightsResponseTest {

    @Test
    fun create() {
        val tweetGetInsightsResponse =
            TweetGetInsightsResponse.builder()
                .insights(
                    TweetGetInsightsResponse.Insights.builder()
                        .ageHours(0.0)
                        .amplification(0.0)
                        .authorId("authorId")
                        .authorUserName("authorUserName")
                        .conversation(0.0)
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .engagementPerView(0.0)
                        .engagementRate(0.0)
                        .engagements(0.0)
                        .engagementsPerHour(0.0)
                        .metrics(
                            TweetGetInsightsResponse.Insights.Metrics.builder()
                                .bookmarks(0.0)
                                .likes(0.0)
                                .quotes(0.0)
                                .replies(0.0)
                                .reposts(0.0)
                                .views(0.0)
                                .build()
                        )
                        .tweetId("tweetId")
                        .build()
                )
                .build()

        assertThat(tweetGetInsightsResponse.insights())
            .isEqualTo(
                TweetGetInsightsResponse.Insights.builder()
                    .ageHours(0.0)
                    .amplification(0.0)
                    .authorId("authorId")
                    .authorUserName("authorUserName")
                    .conversation(0.0)
                    .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .engagementPerView(0.0)
                    .engagementRate(0.0)
                    .engagements(0.0)
                    .engagementsPerHour(0.0)
                    .metrics(
                        TweetGetInsightsResponse.Insights.Metrics.builder()
                            .bookmarks(0.0)
                            .likes(0.0)
                            .quotes(0.0)
                            .replies(0.0)
                            .reposts(0.0)
                            .views(0.0)
                            .build()
                    )
                    .tweetId("tweetId")
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val tweetGetInsightsResponse =
            TweetGetInsightsResponse.builder()
                .insights(
                    TweetGetInsightsResponse.Insights.builder()
                        .ageHours(0.0)
                        .amplification(0.0)
                        .authorId("authorId")
                        .authorUserName("authorUserName")
                        .conversation(0.0)
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .engagementPerView(0.0)
                        .engagementRate(0.0)
                        .engagements(0.0)
                        .engagementsPerHour(0.0)
                        .metrics(
                            TweetGetInsightsResponse.Insights.Metrics.builder()
                                .bookmarks(0.0)
                                .likes(0.0)
                                .quotes(0.0)
                                .replies(0.0)
                                .reposts(0.0)
                                .views(0.0)
                                .build()
                        )
                        .tweetId("tweetId")
                        .build()
                )
                .build()

        val roundtrippedTweetGetInsightsResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(tweetGetInsightsResponse),
                jacksonTypeRef<TweetGetInsightsResponse>(),
            )

        assertThat(roundtrippedTweetGetInsightsResponse).isEqualTo(tweetGetInsightsResponse)
    }
}
