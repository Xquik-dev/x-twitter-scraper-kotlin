package com.x_twitter_scraper.api.models

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.JsonValue
import com.x_twitter_scraper.api.core.jsonMapper
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BroadcastTest {

    @Test
    fun create() {
        val broadcast =
            Broadcast.builder()
                .id("1nGeLMWbkpaKX")
                .cameraRotation(0L)
                .chatOption(1L)
                .creator(UserProfile.builder().id("id").name("name").username("username").build())
                .endedAt(OffsetDateTime.parse("2026-09-17T01:17:35.036Z"))
                .height(2160L)
                .isAvailableForReplay(true)
                .isHighLatency(true)
                .isPrivateChat(true)
                .isTitleEdited(true)
                .lastPingAt(OffsetDateTime.parse("2026-09-17T01:17:32.232Z"))
                .mediaKey("28_2097700444252635136")
                .periscopeUser(
                    Broadcast.PeriscopeUser.builder()
                        .id("1raQZonlMGYKz")
                        .displayName("SpaceX")
                        .profilePicture("https://example.com")
                        .profilePictureOriginal("https://example.com")
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

        assertThat(broadcast.id()).isEqualTo("1nGeLMWbkpaKX")
        assertThat(broadcast.title()).isEqualTo("USSF-259 Mission")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val broadcast =
            Broadcast.builder()
                .id("1nGeLMWbkpaKX")
                .cameraRotation(0L)
                .chatOption(1L)
                .creator(UserProfile.builder().id("id").name("name").username("username").build())
                .endedAt(OffsetDateTime.parse("2026-09-17T01:17:35.036Z"))
                .height(2160L)
                .isAvailableForReplay(true)
                .isHighLatency(true)
                .isPrivateChat(true)
                .isTitleEdited(true)
                .lastPingAt(OffsetDateTime.parse("2026-09-17T01:17:32.232Z"))
                .mediaKey("28_2097700444252635136")
                .periscopeUser(
                    Broadcast.PeriscopeUser.builder()
                        .id("1raQZonlMGYKz")
                        .displayName("SpaceX")
                        .profilePicture("https://example.com")
                        .profilePictureOriginal("https://example.com")
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
                .toBuilder()
                .putAdditionalProperty("futureField", JsonValue.from("value"))
                .build()

        val roundtrippedBroadcast =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(broadcast),
                jacksonTypeRef<Broadcast>(),
            )

        assertThat(roundtrippedBroadcast).isEqualTo(broadcast)
        assertThat(roundtrippedBroadcast._additionalProperties())
            .containsEntry("futureField", JsonValue.from("value"))
    }

    @Test
    fun validate() {
        val broadcast =
            Broadcast.builder()
                .id("1nGeLMWbkpaKX")
                .cameraRotation(0L)
                .chatOption(1L)
                .creator(UserProfile.builder().id("id").name("name").username("username").build())
                .endedAt(OffsetDateTime.parse("2026-09-17T01:17:35.036Z"))
                .height(2160L)
                .isAvailableForReplay(true)
                .isHighLatency(true)
                .isPrivateChat(true)
                .isTitleEdited(true)
                .lastPingAt(OffsetDateTime.parse("2026-09-17T01:17:32.232Z"))
                .mediaKey("28_2097700444252635136")
                .periscopeUser(
                    Broadcast.PeriscopeUser.builder()
                        .id("1raQZonlMGYKz")
                        .displayName("SpaceX")
                        .profilePicture("https://example.com")
                        .profilePictureOriginal("https://example.com")
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

        assertThat(broadcast.validate()).isSameAs(broadcast)
        assertThat(broadcast.isValid()).isTrue()
    }
}
