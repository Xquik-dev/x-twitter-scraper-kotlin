// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.users

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
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
                    UserRetrieveLiveResponse.Broadcast.builder()
                        .id("1nGeLMWbkpaKX")
                        .cameraRotation(0L)
                        .chatOption(1L)
                        .creator(
                            UserProfile.builder().id("id").name("name").username("username").build()
                        )
                        .endedAt(OffsetDateTime.parse("2026-09-17T01:17:35.036Z"))
                        .height(2160L)
                        .isAvailableForReplay(true)
                        .isHighLatency(true)
                        .isPrivateChat(true)
                        .isTitleEdited(true)
                        .lastPingAt(OffsetDateTime.parse("2026-09-17T01:17:32.232Z"))
                        .mediaKey("28_2097700444252635136")
                        .periscopeUser(
                            UserRetrieveLiveResponse.Broadcast.PeriscopeUser.builder()
                                .displayName("SpaceX")
                                .id("1raQZonlMGYKz")
                                .profilePicture("profilePicture")
                                .profilePictureOriginal("profilePictureOriginal")
                                .username("SpaceX")
                                .build()
                        )
                        .preLiveSlateUrl("https://pbs.twimg.com/media/HRyHXi3WoAAYJ6F.jpg")
                        .scheduledStart(OffsetDateTime.parse("2026-09-17T00:55:00.000Z"))
                        .source("LiveCms")
                        .startedAt(OffsetDateTime.parse("2026-09-17T00:54:56.506Z"))
                        .state("Ended")
                        .title("USSF-259 Mission")
                        .totalWatched(161758L)
                        .totalWatching(21639L)
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
                        .tweetId("2106868248524542376")
                        .version(151L)
                        .width(3840L)
                        .build()
                )
                .live(false)
                .userId("11348282")
                .userName("NASA")
                .build()

        assertThat(userRetrieveLiveResponse.broadcast())
            .isEqualTo(
                UserRetrieveLiveResponse.Broadcast.builder()
                    .id("1nGeLMWbkpaKX")
                    .cameraRotation(0L)
                    .chatOption(1L)
                    .creator(
                        UserProfile.builder().id("id").name("name").username("username").build()
                    )
                    .endedAt(OffsetDateTime.parse("2026-09-17T01:17:35.036Z"))
                    .height(2160L)
                    .isAvailableForReplay(true)
                    .isHighLatency(true)
                    .isPrivateChat(true)
                    .isTitleEdited(true)
                    .lastPingAt(OffsetDateTime.parse("2026-09-17T01:17:32.232Z"))
                    .mediaKey("28_2097700444252635136")
                    .periscopeUser(
                        UserRetrieveLiveResponse.Broadcast.PeriscopeUser.builder()
                            .displayName("SpaceX")
                            .id("1raQZonlMGYKz")
                            .profilePicture("profilePicture")
                            .profilePictureOriginal("profilePictureOriginal")
                            .username("SpaceX")
                            .build()
                    )
                    .preLiveSlateUrl("https://pbs.twimg.com/media/HRyHXi3WoAAYJ6F.jpg")
                    .scheduledStart(OffsetDateTime.parse("2026-09-17T00:55:00.000Z"))
                    .source("LiveCms")
                    .startedAt(OffsetDateTime.parse("2026-09-17T00:54:56.506Z"))
                    .state("Ended")
                    .title("USSF-259 Mission")
                    .totalWatched(161758L)
                    .totalWatching(21639L)
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
                    .tweetId("2106868248524542376")
                    .version(151L)
                    .width(3840L)
                    .build()
            )
        assertThat(userRetrieveLiveResponse.live()).isEqualTo(false)
        assertThat(userRetrieveLiveResponse.userId()).isEqualTo("11348282")
        assertThat(userRetrieveLiveResponse.userName()).isEqualTo("NASA")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val userRetrieveLiveResponse =
            UserRetrieveLiveResponse.builder()
                .broadcast(
                    UserRetrieveLiveResponse.Broadcast.builder()
                        .id("1nGeLMWbkpaKX")
                        .cameraRotation(0L)
                        .chatOption(1L)
                        .creator(
                            UserProfile.builder().id("id").name("name").username("username").build()
                        )
                        .endedAt(OffsetDateTime.parse("2026-09-17T01:17:35.036Z"))
                        .height(2160L)
                        .isAvailableForReplay(true)
                        .isHighLatency(true)
                        .isPrivateChat(true)
                        .isTitleEdited(true)
                        .lastPingAt(OffsetDateTime.parse("2026-09-17T01:17:32.232Z"))
                        .mediaKey("28_2097700444252635136")
                        .periscopeUser(
                            UserRetrieveLiveResponse.Broadcast.PeriscopeUser.builder()
                                .displayName("SpaceX")
                                .id("1raQZonlMGYKz")
                                .profilePicture("profilePicture")
                                .profilePictureOriginal("profilePictureOriginal")
                                .username("SpaceX")
                                .build()
                        )
                        .preLiveSlateUrl("https://pbs.twimg.com/media/HRyHXi3WoAAYJ6F.jpg")
                        .scheduledStart(OffsetDateTime.parse("2026-09-17T00:55:00.000Z"))
                        .source("LiveCms")
                        .startedAt(OffsetDateTime.parse("2026-09-17T00:54:56.506Z"))
                        .state("Ended")
                        .title("USSF-259 Mission")
                        .totalWatched(161758L)
                        .totalWatching(21639L)
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
                        .tweetId("2106868248524542376")
                        .version(151L)
                        .width(3840L)
                        .build()
                )
                .live(false)
                .userId("11348282")
                .userName("NASA")
                .build()

        val roundtrippedUserRetrieveLiveResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(userRetrieveLiveResponse),
                jacksonTypeRef<UserRetrieveLiveResponse>(),
            )

        assertThat(roundtrippedUserRetrieveLiveResponse).isEqualTo(userRetrieveLiveResponse)
    }
}
