// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.users

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserRetrieveInsightsResponseTest {

    @Test
    fun create() {
        val userRetrieveInsightsResponse =
            UserRetrieveInsightsResponse.builder()
                .insights(
                    UserRetrieveInsightsResponse.Insights.builder()
                        .accountAgeDays(0.0)
                        .averages(
                            UserRetrieveInsightsResponse.Insights.Averages.builder()
                                .bookmarks(0.0)
                                .engagements(0.0)
                                .likes(0.0)
                                .quotes(0.0)
                                .replies(0.0)
                                .reposts(0.0)
                                .views(0.0)
                                .build()
                        )
                        .engagementPerView(0.0)
                        .engagementRate(0.0)
                        .followers(0.0)
                        .following(0.0)
                        .posts(0.0)
                        .postsPerDay(0.0)
                        .postsPrivate(true)
                        .sample(
                            UserRetrieveInsightsResponse.Insights.Sample.builder()
                                .newestAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                                .oldestAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                                .posts(0.0)
                                .build()
                        )
                        .topPostId("topPostId")
                        .userId("userId")
                        .userName("userName")
                        .build()
                )
                .message("message")
                .build()

        assertThat(userRetrieveInsightsResponse.insights())
            .isEqualTo(
                UserRetrieveInsightsResponse.Insights.builder()
                    .accountAgeDays(0.0)
                    .averages(
                        UserRetrieveInsightsResponse.Insights.Averages.builder()
                            .bookmarks(0.0)
                            .engagements(0.0)
                            .likes(0.0)
                            .quotes(0.0)
                            .replies(0.0)
                            .reposts(0.0)
                            .views(0.0)
                            .build()
                    )
                    .engagementPerView(0.0)
                    .engagementRate(0.0)
                    .followers(0.0)
                    .following(0.0)
                    .posts(0.0)
                    .postsPerDay(0.0)
                    .postsPrivate(true)
                    .sample(
                        UserRetrieveInsightsResponse.Insights.Sample.builder()
                            .newestAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                            .oldestAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                            .posts(0.0)
                            .build()
                    )
                    .topPostId("topPostId")
                    .userId("userId")
                    .userName("userName")
                    .build()
            )
        assertThat(userRetrieveInsightsResponse.message()).isEqualTo("message")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val userRetrieveInsightsResponse =
            UserRetrieveInsightsResponse.builder()
                .insights(
                    UserRetrieveInsightsResponse.Insights.builder()
                        .accountAgeDays(0.0)
                        .averages(
                            UserRetrieveInsightsResponse.Insights.Averages.builder()
                                .bookmarks(0.0)
                                .engagements(0.0)
                                .likes(0.0)
                                .quotes(0.0)
                                .replies(0.0)
                                .reposts(0.0)
                                .views(0.0)
                                .build()
                        )
                        .engagementPerView(0.0)
                        .engagementRate(0.0)
                        .followers(0.0)
                        .following(0.0)
                        .posts(0.0)
                        .postsPerDay(0.0)
                        .postsPrivate(true)
                        .sample(
                            UserRetrieveInsightsResponse.Insights.Sample.builder()
                                .newestAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                                .oldestAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                                .posts(0.0)
                                .build()
                        )
                        .topPostId("topPostId")
                        .userId("userId")
                        .userName("userName")
                        .build()
                )
                .message("message")
                .build()

        val roundtrippedUserRetrieveInsightsResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(userRetrieveInsightsResponse),
                jacksonTypeRef<UserRetrieveInsightsResponse>(),
            )

        assertThat(roundtrippedUserRetrieveInsightsResponse).isEqualTo(userRetrieveInsightsResponse)
    }
}
