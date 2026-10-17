// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.users

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import com.x_twitter_scraper.api.models.Broadcast
import com.x_twitter_scraper.api.models.SearchTweet
import com.x_twitter_scraper.api.models.UserProfile
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserRetrieveLiveResponseTest {

    @Test
    fun create() {
        val userRetrieveLiveResponse =
            UserRetrieveLiveResponse.builder()
                .broadcast(
                    Broadcast.builder()
                        .id("id")
                        .cameraRotation(0L)
                        .chatOption(0L)
                        .creator(
                            UserProfile.builder().id("id").name("name").username("username").build()
                        )
                        .endedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .height(0L)
                        .isAvailableForReplay(true)
                        .isHighLatency(true)
                        .isPrivateChat(true)
                        .isTitleEdited(true)
                        .lastPingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .mediaKey("mediaKey")
                        .periscopeUser(
                            Broadcast.PeriscopeUser.builder()
                                .id("id")
                                .displayName("displayName")
                                .profilePicture("profilePicture")
                                .profilePictureOriginal("profilePictureOriginal")
                                .username("username")
                                .build()
                        )
                        .preLiveSlateUrl("preLiveSlateUrl")
                        .scheduledStart(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .source("source")
                        .startedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .state("state")
                        .title("title")
                        .totalWatched(0L)
                        .totalWatching(0L)
                        .tweet(
                            SearchTweet.builder()
                                .id("id")
                                .bookmarkCount(0L)
                                .likeCount(0L)
                                .quoteCount(0L)
                                .replyCount(0L)
                                .retweetCount(0L)
                                .text("text")
                                .viewCount(0L)
                                .build()
                        )
                        .tweetId("tweetId")
                        .version(0L)
                        .width(0L)
                        .build()
                )
                .live(true)
                .userId("userId")
                .userName("userName")
                .build()

        assertThat(userRetrieveLiveResponse.broadcast())
            .isEqualTo(
                Broadcast.builder()
                    .id("id")
                    .cameraRotation(0L)
                    .chatOption(0L)
                    .creator(
                        UserProfile.builder().id("id").name("name").username("username").build()
                    )
                    .endedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .height(0L)
                    .isAvailableForReplay(true)
                    .isHighLatency(true)
                    .isPrivateChat(true)
                    .isTitleEdited(true)
                    .lastPingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .mediaKey("mediaKey")
                    .periscopeUser(
                        Broadcast.PeriscopeUser.builder()
                            .id("id")
                            .displayName("displayName")
                            .profilePicture("profilePicture")
                            .profilePictureOriginal("profilePictureOriginal")
                            .username("username")
                            .build()
                    )
                    .preLiveSlateUrl("preLiveSlateUrl")
                    .scheduledStart(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .source("source")
                    .startedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .state("state")
                    .title("title")
                    .totalWatched(0L)
                    .totalWatching(0L)
                    .tweet(
                        SearchTweet.builder()
                            .id("id")
                            .bookmarkCount(0L)
                            .likeCount(0L)
                            .quoteCount(0L)
                            .replyCount(0L)
                            .retweetCount(0L)
                            .text("text")
                            .viewCount(0L)
                            .build()
                    )
                    .tweetId("tweetId")
                    .version(0L)
                    .width(0L)
                    .build()
            )
        assertThat(userRetrieveLiveResponse.live()).isEqualTo(true)
        assertThat(userRetrieveLiveResponse.userId()).isEqualTo("userId")
        assertThat(userRetrieveLiveResponse.userName()).isEqualTo("userName")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val userRetrieveLiveResponse =
            UserRetrieveLiveResponse.builder()
                .broadcast(
                    Broadcast.builder()
                        .id("id")
                        .cameraRotation(0L)
                        .chatOption(0L)
                        .creator(
                            UserProfile.builder().id("id").name("name").username("username").build()
                        )
                        .endedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .height(0L)
                        .isAvailableForReplay(true)
                        .isHighLatency(true)
                        .isPrivateChat(true)
                        .isTitleEdited(true)
                        .lastPingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .mediaKey("mediaKey")
                        .periscopeUser(
                            Broadcast.PeriscopeUser.builder()
                                .id("id")
                                .displayName("displayName")
                                .profilePicture("profilePicture")
                                .profilePictureOriginal("profilePictureOriginal")
                                .username("username")
                                .build()
                        )
                        .preLiveSlateUrl("preLiveSlateUrl")
                        .scheduledStart(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .source("source")
                        .startedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .state("state")
                        .title("title")
                        .totalWatched(0L)
                        .totalWatching(0L)
                        .tweet(
                            SearchTweet.builder()
                                .id("id")
                                .bookmarkCount(0L)
                                .likeCount(0L)
                                .quoteCount(0L)
                                .replyCount(0L)
                                .retweetCount(0L)
                                .text("text")
                                .viewCount(0L)
                                .build()
                        )
                        .tweetId("tweetId")
                        .version(0L)
                        .width(0L)
                        .build()
                )
                .live(true)
                .userId("userId")
                .userName("userName")
                .build()

        val roundtrippedUserRetrieveLiveResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(userRetrieveLiveResponse),
                jacksonTypeRef<UserRetrieveLiveResponse>(),
            )

        assertThat(roundtrippedUserRetrieveLiveResponse).isEqualTo(userRetrieveLiveResponse)
    }
}
