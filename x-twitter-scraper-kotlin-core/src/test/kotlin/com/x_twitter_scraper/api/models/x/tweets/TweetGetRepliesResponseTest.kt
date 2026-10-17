// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.tweets

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.JsonMissing
import com.x_twitter_scraper.api.core.jsonMapper
import com.x_twitter_scraper.api.models.SearchTweet
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TweetGetRepliesResponseTest {

    @Test
    fun create() {
        val tweetGetRepliesResponse =
            TweetGetRepliesResponse.builder()
                .hasNextPage(true)
                .nextCursor("next_cursor")
                .addTweet(
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
                .diagnostic(
                    TweetGetRepliesResponse.Diagnostic.builder()
                        .complete(true)
                        .coveragePercentage(0.0)
                        .cursorFailures(0L)
                        .duplicateCount(0L)
                        .emptyFalseProgressPages(0L)
                        .malformedCount(0L)
                        .addMissingResponseModulesOrField("missingResponseModulesOrField")
                        .nestedReplyCount(0L)
                        .pagesAttempted(0L)
                        .recommendedFallback("recommendedFallback")
                        .repeatedCursorCount(0L)
                        .reportedReplyCount(0L)
                        .responseTruncated(true)
                        .richness(
                            TweetGetRepliesResponse.Diagnostic.Richness.builder()
                                .article(0L)
                                .author(0L)
                                .card(0L)
                                .communityNote(0L)
                                .createdAt(0L)
                                .engagementCounts(0L)
                                .entities(0L)
                                .language(0L)
                                .media(0L)
                                .quotedOrRepostedTweet(0L)
                                .text(0L)
                                .totalReplies(0L)
                                .url(0L)
                                .build()
                        )
                        .addStrategiesAttempted(
                            TweetGetRepliesResponse.Diagnostic.StrategiesAttempted.builder()
                                .name("name")
                                .newDirectReplies(0L)
                                .newNestedReplies(0L)
                                .pagesAttempted(0L)
                                .stopReason(
                                    TweetGetRepliesResponse.Diagnostic.StrategiesAttempted
                                        .StopReason
                                        .DEADLINE
                                )
                                .addNewDirectReplyPage(0L)
                                .build()
                        )
                        .targetDirectReplies(0L)
                        .uniqueDirectReplies(0L)
                        .unrelatedCount(0L)
                        .replyScopeComplete(true)
                        .replyTreeComplete(true)
                        .sourcesEnded(true)
                        .build()
                )
                .filteredCount(0L)
                .hiddenReplies(1L)
                .limitReached(true)
                .message(
                    "The post has more matching replies than limit. Raise limit to get them all."
                )
                .addNestedReply(
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
                .orderPartial(true)
                .partialBefore(OffsetDateTime.parse("2026-10-07T00:00:00.000Z"))
                .readBackTo(OffsetDateTime.parse("2026-10-04T10:07:00.000Z"))
                .searchContext(
                    TweetGetRepliesResponse.SearchContext.builder()
                        .smartTag(
                            TweetGetRepliesResponse.SearchContext.SmartTag.builder()
                                .id("BTC")
                                .active(true)
                                .supportsOhlc(false)
                                .build()
                        )
                        .build()
                )
                .searchedQuery("openai gpt")
                .windowComplete(true)
                .build()

        assertThat(tweetGetRepliesResponse.hasNextPage()).isEqualTo(true)
        assertThat(tweetGetRepliesResponse.nextCursor()).isEqualTo("next_cursor")
        assertThat(tweetGetRepliesResponse.tweets())
            .containsExactly(
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
        assertThat(tweetGetRepliesResponse.diagnostic())
            .isEqualTo(
                TweetGetRepliesResponse.Diagnostic.builder()
                    .complete(true)
                    .coveragePercentage(0.0)
                    .cursorFailures(0L)
                    .duplicateCount(0L)
                    .emptyFalseProgressPages(0L)
                    .malformedCount(0L)
                    .addMissingResponseModulesOrField("missingResponseModulesOrField")
                    .nestedReplyCount(0L)
                    .pagesAttempted(0L)
                    .recommendedFallback("recommendedFallback")
                    .repeatedCursorCount(0L)
                    .reportedReplyCount(0L)
                    .responseTruncated(true)
                    .richness(
                        TweetGetRepliesResponse.Diagnostic.Richness.builder()
                            .article(0L)
                            .author(0L)
                            .card(0L)
                            .communityNote(0L)
                            .createdAt(0L)
                            .engagementCounts(0L)
                            .entities(0L)
                            .language(0L)
                            .media(0L)
                            .quotedOrRepostedTweet(0L)
                            .text(0L)
                            .totalReplies(0L)
                            .url(0L)
                            .build()
                    )
                    .addStrategiesAttempted(
                        TweetGetRepliesResponse.Diagnostic.StrategiesAttempted.builder()
                            .name("name")
                            .newDirectReplies(0L)
                            .newNestedReplies(0L)
                            .pagesAttempted(0L)
                            .stopReason(
                                TweetGetRepliesResponse.Diagnostic.StrategiesAttempted.StopReason
                                    .DEADLINE
                            )
                            .addNewDirectReplyPage(0L)
                            .build()
                    )
                    .targetDirectReplies(0L)
                    .uniqueDirectReplies(0L)
                    .unrelatedCount(0L)
                    .replyScopeComplete(true)
                    .replyTreeComplete(true)
                    .sourcesEnded(true)
                    .build()
            )
        assertThat(tweetGetRepliesResponse.filteredCount()).isEqualTo(0L)
        assertThat(tweetGetRepliesResponse.hiddenReplies()).isEqualTo(1L)
        assertThat(tweetGetRepliesResponse.limitReached()).isEqualTo(true)
        assertThat(tweetGetRepliesResponse.message())
            .isEqualTo(
                "The post has more matching replies than limit. Raise limit to get them all."
            )
        assertThat(tweetGetRepliesResponse.nestedReplies())
            .containsExactly(
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
        assertThat(tweetGetRepliesResponse.orderPartial()).isEqualTo(true)
        assertThat(tweetGetRepliesResponse.partialBefore())
            .isEqualTo(OffsetDateTime.parse("2026-10-07T00:00:00.000Z"))
        assertThat(tweetGetRepliesResponse.readBackTo())
            .isEqualTo(OffsetDateTime.parse("2026-10-04T10:07:00.000Z"))
        assertThat(tweetGetRepliesResponse.searchContext())
            .isEqualTo(
                TweetGetRepliesResponse.SearchContext.builder()
                    .smartTag(
                        TweetGetRepliesResponse.SearchContext.SmartTag.builder()
                            .id("BTC")
                            .active(true)
                            .supportsOhlc(false)
                            .build()
                    )
                    .build()
            )
        assertThat(tweetGetRepliesResponse.searchedQuery()).isEqualTo("openai gpt")
        assertThat(tweetGetRepliesResponse.windowComplete()).isEqualTo(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val tweetGetRepliesResponse =
            TweetGetRepliesResponse.builder()
                .hasNextPage(true)
                .nextCursor("next_cursor")
                .addTweet(
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
                .diagnostic(
                    TweetGetRepliesResponse.Diagnostic.builder()
                        .complete(true)
                        .coveragePercentage(0.0)
                        .cursorFailures(0L)
                        .duplicateCount(0L)
                        .emptyFalseProgressPages(0L)
                        .malformedCount(0L)
                        .addMissingResponseModulesOrField("missingResponseModulesOrField")
                        .nestedReplyCount(0L)
                        .pagesAttempted(0L)
                        .recommendedFallback("recommendedFallback")
                        .repeatedCursorCount(0L)
                        .reportedReplyCount(0L)
                        .responseTruncated(true)
                        .richness(
                            TweetGetRepliesResponse.Diagnostic.Richness.builder()
                                .article(0L)
                                .author(0L)
                                .card(0L)
                                .communityNote(0L)
                                .createdAt(0L)
                                .engagementCounts(0L)
                                .entities(0L)
                                .language(0L)
                                .media(0L)
                                .quotedOrRepostedTweet(0L)
                                .text(0L)
                                .totalReplies(0L)
                                .url(0L)
                                .build()
                        )
                        .addStrategiesAttempted(
                            TweetGetRepliesResponse.Diagnostic.StrategiesAttempted.builder()
                                .name("name")
                                .newDirectReplies(0L)
                                .newNestedReplies(0L)
                                .pagesAttempted(0L)
                                .stopReason(
                                    TweetGetRepliesResponse.Diagnostic.StrategiesAttempted
                                        .StopReason
                                        .DEADLINE
                                )
                                .addNewDirectReplyPage(0L)
                                .build()
                        )
                        .targetDirectReplies(0L)
                        .uniqueDirectReplies(0L)
                        .unrelatedCount(0L)
                        .replyScopeComplete(true)
                        .replyTreeComplete(true)
                        .sourcesEnded(true)
                        .build()
                )
                .filteredCount(0L)
                .hiddenReplies(1L)
                .limitReached(true)
                .message(
                    "The post has more matching replies than limit. Raise limit to get them all."
                )
                .addNestedReply(
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
                .orderPartial(true)
                .partialBefore(OffsetDateTime.parse("2026-10-07T00:00:00.000Z"))
                .readBackTo(OffsetDateTime.parse("2026-10-04T10:07:00.000Z"))
                .searchContext(
                    TweetGetRepliesResponse.SearchContext.builder()
                        .smartTag(
                            TweetGetRepliesResponse.SearchContext.SmartTag.builder()
                                .id("BTC")
                                .active(true)
                                .supportsOhlc(false)
                                .build()
                        )
                        .build()
                )
                .searchedQuery("openai gpt")
                .windowComplete(true)
                .build()

        val roundtrippedTweetGetRepliesResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(tweetGetRepliesResponse),
                jacksonTypeRef<TweetGetRepliesResponse>(),
            )

        assertThat(roundtrippedTweetGetRepliesResponse).isEqualTo(tweetGetRepliesResponse)
    }

    @Test
    fun readsAdditionalFields() {
        val jsonMapper = jsonMapper()

        val tweetGetRepliesResponse0 =
            jsonMapper.readValue(
                """{"filtered_count":1,"hiddenReplies":1,"limit_reached":true,"message":"value","orderPartial":true,"partialBefore":"2026-10-17T00:00:00Z","readBackTo":"2026-10-17T00:00:00Z","searchContext":{},"searchedQuery":"value","windowComplete":true}""",
                jacksonTypeRef<TweetGetRepliesResponse>(),
            )
        assertThat(tweetGetRepliesResponse0.filteredCount()).isNotNull()
        assertThat(tweetGetRepliesResponse0.hiddenReplies()).isNotNull()
        assertThat(tweetGetRepliesResponse0.limitReached()).isNotNull()
        assertThat(tweetGetRepliesResponse0.message()).isNotNull()
        assertThat(tweetGetRepliesResponse0.orderPartial()).isNotNull()
        assertThat(tweetGetRepliesResponse0.partialBefore()).isNotNull()
        assertThat(tweetGetRepliesResponse0.readBackTo()).isNotNull()
        assertThat(tweetGetRepliesResponse0.searchContext()).isNotNull()
        assertThat(tweetGetRepliesResponse0.searchedQuery()).isNotNull()
        assertThat(tweetGetRepliesResponse0.windowComplete()).isNotNull()
        assertThat(jsonMapper.readTree(jsonMapper.writeValueAsString(tweetGetRepliesResponse0)))
            .isEqualTo(
                jsonMapper.readTree(
                    """{"filtered_count":1,"hiddenReplies":1,"limit_reached":true,"message":"value","orderPartial":true,"partialBefore":"2026-10-17T00:00:00Z","readBackTo":"2026-10-17T00:00:00Z","searchContext":{},"searchedQuery":"value","windowComplete":true}"""
                )
            )

        val diagnostic1 =
            jsonMapper.readValue(
                """{"replyScopeComplete":true,"replyTreeComplete":true,"sourcesEnded":true}""",
                jacksonTypeRef<TweetGetRepliesResponse.Diagnostic>(),
            )
        assertThat(diagnostic1.replyScopeComplete()).isNotNull()
        assertThat(diagnostic1.replyTreeComplete()).isNotNull()
        assertThat(diagnostic1.sourcesEnded()).isNotNull()
        assertThat(jsonMapper.readTree(jsonMapper.writeValueAsString(diagnostic1)))
            .isEqualTo(
                jsonMapper.readTree(
                    """{"replyScopeComplete":true,"replyTreeComplete":true,"sourcesEnded":true}"""
                )
            )

        val strategiesAttempted2 =
            jsonMapper.readValue(
                """{"newDirectReplyPages":[]}""",
                jacksonTypeRef<TweetGetRepliesResponse.Diagnostic.StrategiesAttempted>(),
            )
        assertThat(strategiesAttempted2.newDirectReplyPages()).isNotNull()
        assertThat(jsonMapper.readTree(jsonMapper.writeValueAsString(strategiesAttempted2)))
            .isEqualTo(jsonMapper.readTree("""{"newDirectReplyPages":[]}"""))
    }

    @Test
    fun keepsAdditionalFieldsOptional() {
        val tweetGetRepliesResponse0 =
            TweetGetRepliesResponse.builder()
                .hasNextPage(JsonMissing.of())
                .nextCursor(JsonMissing.of())
                .tweets(JsonMissing.of())
                .nestedReplies(JsonMissing.of())
                .build()
        assertThat(tweetGetRepliesResponse0.filteredCount()).isNull()
        assertThat(tweetGetRepliesResponse0.hiddenReplies()).isNull()
        assertThat(tweetGetRepliesResponse0.limitReached()).isNull()
        assertThat(tweetGetRepliesResponse0.message()).isNull()
        assertThat(tweetGetRepliesResponse0.orderPartial()).isNull()
        assertThat(tweetGetRepliesResponse0.partialBefore()).isNull()
        assertThat(tweetGetRepliesResponse0.readBackTo()).isNull()
        assertThat(tweetGetRepliesResponse0.searchContext()).isNull()
        assertThat(tweetGetRepliesResponse0.searchedQuery()).isNull()
        assertThat(tweetGetRepliesResponse0.windowComplete()).isNull()
        val diagnostic1 =
            TweetGetRepliesResponse.Diagnostic.builder()
                .complete(JsonMissing.of())
                .coveragePercentage(JsonMissing.of())
                .cursorFailures(JsonMissing.of())
                .duplicateCount(JsonMissing.of())
                .emptyFalseProgressPages(JsonMissing.of())
                .malformedCount(JsonMissing.of())
                .missingResponseModulesOrFields(JsonMissing.of())
                .nestedReplyCount(JsonMissing.of())
                .pagesAttempted(JsonMissing.of())
                .recommendedFallback(JsonMissing.of())
                .repeatedCursorCount(JsonMissing.of())
                .reportedReplyCount(JsonMissing.of())
                .responseTruncated(JsonMissing.of())
                .richness(JsonMissing.of())
                .strategiesAttempted(JsonMissing.of())
                .targetDirectReplies(JsonMissing.of())
                .uniqueDirectReplies(JsonMissing.of())
                .unrelatedCount(JsonMissing.of())
                .build()
        assertThat(diagnostic1.replyScopeComplete()).isNull()
        assertThat(diagnostic1.replyTreeComplete()).isNull()
        assertThat(diagnostic1.sourcesEnded()).isNull()
        val strategiesAttempted2 =
            TweetGetRepliesResponse.Diagnostic.StrategiesAttempted.builder()
                .name(JsonMissing.of())
                .newDirectReplies(JsonMissing.of())
                .newNestedReplies(JsonMissing.of())
                .pagesAttempted(JsonMissing.of())
                .stopReason(JsonMissing.of())
                .build()
        assertThat(strategiesAttempted2.newDirectReplyPages()).isNull()
    }
}
