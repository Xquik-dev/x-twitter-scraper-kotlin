// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.JsonMissing
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

    @Test
    fun readsAdditionalFields() {
        val jsonMapper = jsonMapper()

        val content0 =
            jsonMapper.readValue(
                """{"altText":"value","caption":"value","durationMs":1,"links":[],"mediaType":"value","tweetId":"value","variants":[]}""",
                jacksonTypeRef<XGetArticleResponse.Article.Content>(),
            )
        assertThat(content0.altText()).isNotNull()
        assertThat(content0.caption()).isNotNull()
        assertThat(content0.durationMs()).isNotNull()
        assertThat(content0.links()).isNotNull()
        assertThat(content0.mediaType()).isNotNull()
        assertThat(content0.tweetId()).isNotNull()
        assertThat(content0.variants()).isNotNull()
        assertThat(jsonMapper.readTree(jsonMapper.writeValueAsString(content0)))
            .isEqualTo(
                jsonMapper.readTree(
                    """{"altText":"value","caption":"value","durationMs":1,"links":[],"mediaType":"value","tweetId":"value","variants":[]}"""
                )
            )

        val author1 =
            jsonMapper.readValue(
                """{"profilePictureOriginal":"value"}""",
                jacksonTypeRef<XGetArticleResponse.Author>(),
            )
        assertThat(author1.profilePictureOriginal()).isNotNull()
        assertThat(jsonMapper.readTree(jsonMapper.writeValueAsString(author1)))
            .isEqualTo(jsonMapper.readTree("""{"profilePictureOriginal":"value"}"""))

        val article2 =
            jsonMapper.readValue(
                """{"articleId":"value","bodyMarkdown":"value","bookmarkCount":1,"coverMedia":{},"lifecycleState":{},"metadata":{},"retweetCount":1,"summaryText":"value"}""",
                jacksonTypeRef<XGetArticleResponse.Article>(),
            )
        assertThat(article2.articleId()).isNotNull()
        assertThat(article2.bodyMarkdown()).isNotNull()
        assertThat(article2.bookmarkCount()).isNotNull()
        assertThat(article2.coverMedia()).isNotNull()
        assertThat(article2.lifecycleState()).isNotNull()
        assertThat(article2.metadata()).isNotNull()
        assertThat(article2.retweetCount()).isNotNull()
        assertThat(article2.summaryText()).isNotNull()
        assertThat(jsonMapper.readTree(jsonMapper.writeValueAsString(article2)))
            .isEqualTo(
                jsonMapper.readTree(
                    """{"articleId":"value","bodyMarkdown":"value","bookmarkCount":1,"coverMedia":{},"lifecycleState":{},"metadata":{},"retweetCount":1,"summaryText":"value"}"""
                )
            )
    }

    @Test
    fun keepsAdditionalFieldsOptional() {
        val content0 =
            XGetArticleResponse.Article.Content.builder()
                .inlineStyleRanges(JsonMissing.of())
                .build()
        assertThat(content0.altText()).isNull()
        assertThat(content0.caption()).isNull()
        assertThat(content0.durationMs()).isNull()
        assertThat(content0.links()).isNull()
        assertThat(content0.mediaType()).isNull()
        assertThat(content0.tweetId()).isNull()
        assertThat(content0.variants()).isNull()
        val author1 =
            XGetArticleResponse.Author.builder()
                .id(JsonMissing.of())
                .name(JsonMissing.of())
                .username(JsonMissing.of())
                .build()
        assertThat(author1.profilePictureOriginal()).isNull()
        val article2 = XGetArticleResponse.Article.builder().contents(JsonMissing.of()).build()
        assertThat(article2.articleId()).isNull()
        assertThat(article2.bodyMarkdown()).isNull()
        assertThat(article2.bookmarkCount()).isNull()
        assertThat(article2.coverMedia()).isNull()
        assertThat(article2.lifecycleState()).isNull()
        assertThat(article2.metadata()).isNull()
        assertThat(article2.retweetCount()).isNull()
        assertThat(article2.summaryText()).isNull()
    }
}
