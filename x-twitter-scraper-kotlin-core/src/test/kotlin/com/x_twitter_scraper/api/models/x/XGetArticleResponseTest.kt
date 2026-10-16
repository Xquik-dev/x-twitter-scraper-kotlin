// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.JsonValue
import com.x_twitter_scraper.api.core.jsonMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class XGetArticleResponseTest {

    @Test
    fun create() {
        val xGetArticleResponse =
            XGetArticleResponse.builder()
                .article(
                    XGetArticleResponse.Article.builder()
                        .articleId("2100651802404544512")
                        .bodyMarkdown(
                            "## Pinned DMs\n\n**Pinning DMs** now works on [desktop](https://x.com/discord)."
                        )
                        .bodyText("bodyText")
                        .bookmarkCount(423L)
                        .addContent(
                            XGetArticleResponse.Article.Content.builder()
                                .altText("A chart of daily active users")
                                .caption("Wordle is excellent because of its simplicity")
                                .durationMs(42376L)
                                .height(0L)
                                .addInlineStyleRange(
                                    XGetArticleResponse.Article.Content.InlineStyleRange.builder()
                                        .length(0L)
                                        .offset(0L)
                                        .style("style")
                                        .build()
                                )
                                .addLink(
                                    XGetArticleResponse.Article.Content.Link.builder()
                                        .length(13L)
                                        .offset(15L)
                                        .url("https://x.com/discord")
                                        .build()
                                )
                                .mediaType(XGetArticleResponse.Article.Content.MediaType.VIDEO)
                                .previewUrl("previewUrl")
                                .text("text")
                                .tweetId("2058951429612875794")
                                .type("type")
                                .url("url")
                                .addVariant(
                                    XGetArticleResponse.Article.Content.Variant.builder()
                                        .contentType("video/mp4")
                                        .url(
                                            "https://video.twimg.com/ext_tw_video/1765989573685837824/pu/vid/avc1/462x360/RbrgrAMdncI-EzW7.mp4?tag=12"
                                        )
                                        .bitrate(832000L)
                                        .build()
                                )
                                .width(0L)
                                .build()
                        )
                        .coverImageUrl("coverImageUrl")
                        .coverMedia(
                            XGetArticleResponse.Article.CoverMedia.builder()
                                .putAdditionalProperty("foo", JsonValue.from("bar"))
                                .build()
                        )
                        .createdAt("createdAt")
                        .lifecycleState(
                            XGetArticleResponse.Article.LifecycleState.builder()
                                .putAdditionalProperty("foo", JsonValue.from("bar"))
                                .build()
                        )
                        .likeCount(0L)
                        .metadata(
                            XGetArticleResponse.Article.Metadata.builder()
                                .putAdditionalProperty("foo", JsonValue.from("bar"))
                                .build()
                        )
                        .previewText("previewText")
                        .quoteCount(0L)
                        .replyCount(0L)
                        .retweetCount(105L)
                        .summaryText("- Pinned DMs now work on desktop.")
                        .title("title")
                        .viewCount(0L)
                        .build()
                )
                .author(
                    XGetArticleResponse.Author.builder()
                        .id("id")
                        .name("name")
                        .username("username")
                        .createdAt("createdAt")
                        .description("description")
                        .favouritesCount(0L)
                        .followersCount(0L)
                        .followingCount(0L)
                        .isBlueVerified(true)
                        .isTranslator(true)
                        .isVerified(true)
                        .location("location")
                        .mediaCount(0L)
                        .profileBannerUrl("profileBannerUrl")
                        .profilePicture("profilePicture")
                        .profilePictureOriginal("profilePictureOriginal")
                        .protected(true)
                        .statusesCount(0L)
                        .url("url")
                        .build()
                )
                .build()

        assertThat(xGetArticleResponse.article())
            .isEqualTo(
                XGetArticleResponse.Article.builder()
                    .articleId("2100651802404544512")
                    .bodyMarkdown(
                        "## Pinned DMs\n\n**Pinning DMs** now works on [desktop](https://x.com/discord)."
                    )
                    .bodyText("bodyText")
                    .bookmarkCount(423L)
                    .addContent(
                        XGetArticleResponse.Article.Content.builder()
                            .altText("A chart of daily active users")
                            .caption("Wordle is excellent because of its simplicity")
                            .durationMs(42376L)
                            .height(0L)
                            .addInlineStyleRange(
                                XGetArticleResponse.Article.Content.InlineStyleRange.builder()
                                    .length(0L)
                                    .offset(0L)
                                    .style("style")
                                    .build()
                            )
                            .addLink(
                                XGetArticleResponse.Article.Content.Link.builder()
                                    .length(13L)
                                    .offset(15L)
                                    .url("https://x.com/discord")
                                    .build()
                            )
                            .mediaType(XGetArticleResponse.Article.Content.MediaType.VIDEO)
                            .previewUrl("previewUrl")
                            .text("text")
                            .tweetId("2058951429612875794")
                            .type("type")
                            .url("url")
                            .addVariant(
                                XGetArticleResponse.Article.Content.Variant.builder()
                                    .contentType("video/mp4")
                                    .url(
                                        "https://video.twimg.com/ext_tw_video/1765989573685837824/pu/vid/avc1/462x360/RbrgrAMdncI-EzW7.mp4?tag=12"
                                    )
                                    .bitrate(832000L)
                                    .build()
                            )
                            .width(0L)
                            .build()
                    )
                    .coverImageUrl("coverImageUrl")
                    .coverMedia(
                        XGetArticleResponse.Article.CoverMedia.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .createdAt("createdAt")
                    .lifecycleState(
                        XGetArticleResponse.Article.LifecycleState.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .likeCount(0L)
                    .metadata(
                        XGetArticleResponse.Article.Metadata.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .previewText("previewText")
                    .quoteCount(0L)
                    .replyCount(0L)
                    .retweetCount(105L)
                    .summaryText("- Pinned DMs now work on desktop.")
                    .title("title")
                    .viewCount(0L)
                    .build()
            )
        assertThat(xGetArticleResponse.author())
            .isEqualTo(
                XGetArticleResponse.Author.builder()
                    .id("id")
                    .name("name")
                    .username("username")
                    .createdAt("createdAt")
                    .description("description")
                    .favouritesCount(0L)
                    .followersCount(0L)
                    .followingCount(0L)
                    .isBlueVerified(true)
                    .isTranslator(true)
                    .isVerified(true)
                    .location("location")
                    .mediaCount(0L)
                    .profileBannerUrl("profileBannerUrl")
                    .profilePicture("profilePicture")
                    .profilePictureOriginal("profilePictureOriginal")
                    .protected(true)
                    .statusesCount(0L)
                    .url("url")
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val xGetArticleResponse =
            XGetArticleResponse.builder()
                .article(
                    XGetArticleResponse.Article.builder()
                        .articleId("2100651802404544512")
                        .bodyMarkdown(
                            "## Pinned DMs\n\n**Pinning DMs** now works on [desktop](https://x.com/discord)."
                        )
                        .bodyText("bodyText")
                        .bookmarkCount(423L)
                        .addContent(
                            XGetArticleResponse.Article.Content.builder()
                                .altText("A chart of daily active users")
                                .caption("Wordle is excellent because of its simplicity")
                                .durationMs(42376L)
                                .height(0L)
                                .addInlineStyleRange(
                                    XGetArticleResponse.Article.Content.InlineStyleRange.builder()
                                        .length(0L)
                                        .offset(0L)
                                        .style("style")
                                        .build()
                                )
                                .addLink(
                                    XGetArticleResponse.Article.Content.Link.builder()
                                        .length(13L)
                                        .offset(15L)
                                        .url("https://x.com/discord")
                                        .build()
                                )
                                .mediaType(XGetArticleResponse.Article.Content.MediaType.VIDEO)
                                .previewUrl("previewUrl")
                                .text("text")
                                .tweetId("2058951429612875794")
                                .type("type")
                                .url("url")
                                .addVariant(
                                    XGetArticleResponse.Article.Content.Variant.builder()
                                        .contentType("video/mp4")
                                        .url(
                                            "https://video.twimg.com/ext_tw_video/1765989573685837824/pu/vid/avc1/462x360/RbrgrAMdncI-EzW7.mp4?tag=12"
                                        )
                                        .bitrate(832000L)
                                        .build()
                                )
                                .width(0L)
                                .build()
                        )
                        .coverImageUrl("coverImageUrl")
                        .coverMedia(
                            XGetArticleResponse.Article.CoverMedia.builder()
                                .putAdditionalProperty("foo", JsonValue.from("bar"))
                                .build()
                        )
                        .createdAt("createdAt")
                        .lifecycleState(
                            XGetArticleResponse.Article.LifecycleState.builder()
                                .putAdditionalProperty("foo", JsonValue.from("bar"))
                                .build()
                        )
                        .likeCount(0L)
                        .metadata(
                            XGetArticleResponse.Article.Metadata.builder()
                                .putAdditionalProperty("foo", JsonValue.from("bar"))
                                .build()
                        )
                        .previewText("previewText")
                        .quoteCount(0L)
                        .replyCount(0L)
                        .retweetCount(105L)
                        .summaryText("- Pinned DMs now work on desktop.")
                        .title("title")
                        .viewCount(0L)
                        .build()
                )
                .author(
                    XGetArticleResponse.Author.builder()
                        .id("id")
                        .name("name")
                        .username("username")
                        .createdAt("createdAt")
                        .description("description")
                        .favouritesCount(0L)
                        .followersCount(0L)
                        .followingCount(0L)
                        .isBlueVerified(true)
                        .isTranslator(true)
                        .isVerified(true)
                        .location("location")
                        .mediaCount(0L)
                        .profileBannerUrl("profileBannerUrl")
                        .profilePicture("profilePicture")
                        .profilePictureOriginal("profilePictureOriginal")
                        .protected(true)
                        .statusesCount(0L)
                        .url("url")
                        .build()
                )
                .build()

        val roundtrippedXGetArticleResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(xGetArticleResponse),
                jacksonTypeRef<XGetArticleResponse>(),
            )

        assertThat(roundtrippedXGetArticleResponse).isEqualTo(xGetArticleResponse)
    }
}
