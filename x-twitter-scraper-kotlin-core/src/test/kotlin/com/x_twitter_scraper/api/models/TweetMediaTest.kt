// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.JsonValue
import com.x_twitter_scraper.api.core.jsonMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TweetMediaTest {

    @Test
    fun create() {
        val tweetMedia =
            TweetMedia.builder()
                .mediaUrl("mediaUrl")
                .type(TweetMedia.Type.PHOTO)
                .url("url")
                .id("id")
                .adultContent(true)
                .allowDownload(true)
                .altText("altText")
                .addAspectRatio(0L)
                .availabilityReason("Dmcaed")
                .availabilityStatus("availabilityStatus")
                .description("description")
                .displayUrl("displayUrl")
                .durationMillis(0L)
                .embeddable(true)
                .expandedUrl("expandedUrl")
                .faceRects(
                    TweetMedia.FaceRects.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .addFocusRect(TweetMedia.FocusRect.builder().h(0L).w(0L).x(0L).y(0L).build())
                .graphicViolence(true)
                .grokImageAnnotation(
                    TweetMedia.GrokImageAnnotation.builder()
                        .prompt("prompt")
                        .upsampledPrompt("upsampledPrompt")
                        .build()
                )
                .grokPostId("grokPostId")
                .height(0L)
                .addIndex(0L)
                .mediaKey("mediaKey")
                .monetizable(true)
                .otherSensitiveContent(true)
                .sizes(
                    TweetMedia.Sizes.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .sourceStatusId("sourceStatusId")
                .sourceUser(
                    UserProfile.builder().id("id").name("name").username("username").build()
                )
                .sourceUserId("sourceUserId")
                .addTag(
                    TweetMedia.Tag.builder()
                        .name("name")
                        .screenName("screen_name")
                        .type("type")
                        .userId("user_id")
                        .build()
                )
                .title("title")
                .addVideoVariant(
                    TweetMedia.VideoVariant.builder()
                        .contentType("contentType")
                        .url("url")
                        .bitrate(0L)
                        .build()
                )
                .visitSiteUrl("visitSiteUrl")
                .watchNowUrl("watchNowUrl")
                .width(0L)
                .build()

        assertThat(tweetMedia.mediaUrl()).isEqualTo("mediaUrl")
        assertThat(tweetMedia.type()).isEqualTo(TweetMedia.Type.PHOTO)
        assertThat(tweetMedia.url()).isEqualTo("url")
        assertThat(tweetMedia.id()).isEqualTo("id")
        assertThat(tweetMedia.adultContent()).isEqualTo(true)
        assertThat(tweetMedia.allowDownload()).isEqualTo(true)
        assertThat(tweetMedia.altText()).isEqualTo("altText")
        assertThat(tweetMedia.aspectRatio()).containsExactly(0L)
        assertThat(tweetMedia.availabilityReason()).isEqualTo("Dmcaed")
        assertThat(tweetMedia.availabilityStatus()).isEqualTo("availabilityStatus")
        assertThat(tweetMedia.description()).isEqualTo("description")
        assertThat(tweetMedia.displayUrl()).isEqualTo("displayUrl")
        assertThat(tweetMedia.durationMillis()).isEqualTo(0L)
        assertThat(tweetMedia.embeddable()).isEqualTo(true)
        assertThat(tweetMedia.expandedUrl()).isEqualTo("expandedUrl")
        assertThat(tweetMedia.faceRects())
            .isEqualTo(
                TweetMedia.FaceRects.builder()
                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                    .build()
            )
        assertThat(tweetMedia.focusRects())
            .containsExactly(TweetMedia.FocusRect.builder().h(0L).w(0L).x(0L).y(0L).build())
        assertThat(tweetMedia.graphicViolence()).isEqualTo(true)
        assertThat(tweetMedia.grokImageAnnotation())
            .isEqualTo(
                TweetMedia.GrokImageAnnotation.builder()
                    .prompt("prompt")
                    .upsampledPrompt("upsampledPrompt")
                    .build()
            )
        assertThat(tweetMedia.grokPostId()).isEqualTo("grokPostId")
        assertThat(tweetMedia.height()).isEqualTo(0L)
        assertThat(tweetMedia.indices()).containsExactly(0L)
        assertThat(tweetMedia.mediaKey()).isEqualTo("mediaKey")
        assertThat(tweetMedia.monetizable()).isEqualTo(true)
        assertThat(tweetMedia.otherSensitiveContent()).isEqualTo(true)
        assertThat(tweetMedia.sizes())
            .isEqualTo(
                TweetMedia.Sizes.builder()
                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                    .build()
            )
        assertThat(tweetMedia.sourceStatusId()).isEqualTo("sourceStatusId")
        assertThat(tweetMedia.sourceUser())
            .isEqualTo(UserProfile.builder().id("id").name("name").username("username").build())
        assertThat(tweetMedia.sourceUserId()).isEqualTo("sourceUserId")
        assertThat(tweetMedia.tags())
            .containsExactly(
                TweetMedia.Tag.builder()
                    .name("name")
                    .screenName("screen_name")
                    .type("type")
                    .userId("user_id")
                    .build()
            )
        assertThat(tweetMedia.title()).isEqualTo("title")
        assertThat(tweetMedia.videoVariants())
            .containsExactly(
                TweetMedia.VideoVariant.builder()
                    .contentType("contentType")
                    .url("url")
                    .bitrate(0L)
                    .build()
            )
        assertThat(tweetMedia.visitSiteUrl()).isEqualTo("visitSiteUrl")
        assertThat(tweetMedia.watchNowUrl()).isEqualTo("watchNowUrl")
        assertThat(tweetMedia.width()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val tweetMedia =
            TweetMedia.builder()
                .mediaUrl("mediaUrl")
                .type(TweetMedia.Type.PHOTO)
                .url("url")
                .id("id")
                .adultContent(true)
                .allowDownload(true)
                .altText("altText")
                .addAspectRatio(0L)
                .availabilityReason("Dmcaed")
                .availabilityStatus("availabilityStatus")
                .description("description")
                .displayUrl("displayUrl")
                .durationMillis(0L)
                .embeddable(true)
                .expandedUrl("expandedUrl")
                .faceRects(
                    TweetMedia.FaceRects.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .addFocusRect(TweetMedia.FocusRect.builder().h(0L).w(0L).x(0L).y(0L).build())
                .graphicViolence(true)
                .grokImageAnnotation(
                    TweetMedia.GrokImageAnnotation.builder()
                        .prompt("prompt")
                        .upsampledPrompt("upsampledPrompt")
                        .build()
                )
                .grokPostId("grokPostId")
                .height(0L)
                .addIndex(0L)
                .mediaKey("mediaKey")
                .monetizable(true)
                .otherSensitiveContent(true)
                .sizes(
                    TweetMedia.Sizes.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .sourceStatusId("sourceStatusId")
                .sourceUser(
                    UserProfile.builder().id("id").name("name").username("username").build()
                )
                .sourceUserId("sourceUserId")
                .addTag(
                    TweetMedia.Tag.builder()
                        .name("name")
                        .screenName("screen_name")
                        .type("type")
                        .userId("user_id")
                        .build()
                )
                .title("title")
                .addVideoVariant(
                    TweetMedia.VideoVariant.builder()
                        .contentType("contentType")
                        .url("url")
                        .bitrate(0L)
                        .build()
                )
                .visitSiteUrl("visitSiteUrl")
                .watchNowUrl("watchNowUrl")
                .width(0L)
                .build()

        val roundtrippedTweetMedia =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(tweetMedia),
                jacksonTypeRef<TweetMedia>(),
            )

        assertThat(roundtrippedTweetMedia).isEqualTo(tweetMedia)
    }
}
