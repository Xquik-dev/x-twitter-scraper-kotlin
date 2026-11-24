// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import com.x_twitter_scraper.api.models.x.tweets.TweetDetail
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CommunityUnavailableTest {

    private inline fun <reified T> read(json: String): T =
        jsonMapper().readValue(json, jacksonTypeRef<T>())

    private fun post(id: String, extra: String = ""): String =
        """{"id":"$id","text":"Just launched!","bookmarkCount":0,"likeCount":0,"quoteCount":0,""" +
            """"replyCount":0,"retweetCount":0,"viewCount":0$extra}"""

    private fun unavailable(reason: String) = ""","communityUnavailable":{"reason":"$reason"}"""

    private fun searchTweet(reasons: List<String>?): SearchTweet {
        val (own, parent, quoted, retweeted) = reasons?.map(::unavailable) ?: List(4) { "" }
        val embedded =
            ""","parentTweet":${post("2", parent)},"quoted_tweet":${post("3", quoted)},""" +
                """"retweeted_tweet":${post("4", retweeted)}"""
        return read(post("1", own + embedded))
    }

    private fun reasons(tweet: SearchTweet): List<String?> =
        (listOf(tweet.communityUnavailable()) +
                listOf(tweet.parentTweet(), tweet.quotedTweet(), tweet.retweetedTweet()).map {
                    checkNotNull(it).communityUnavailable()
                })
            .map { it?.reason() }

    @Test
    fun everyPostSaysWhyXShowsNoCommunity() {
        val tweet = searchTweet(listOf("Suspended", "Removed", "NotFound", "Protected"))
        val detail = read<TweetDetail>(post("5", unavailable("Suspended")))

        assertThat(reasons(tweet)).containsExactly("Suspended", "Removed", "NotFound", "Protected")
        assertThat(detail.communityUnavailable()?.reason()).isEqualTo("Suspended")
        assertThat(tweet.isValid()).isTrue()
        assertThat(detail.isValid()).isTrue()
    }

    @Test
    fun aPostXNamesNoReasonForHasNone() {
        val tweet = searchTweet(null)

        assertThat(reasons(tweet)).containsOnlyNulls()
        assertThat(read<TweetDetail>(post("5")).communityUnavailable()).isNull()
        assertThat(tweet.isValid()).isTrue()
    }

    @Test
    fun aCommunityUnavailableWithoutAReasonIsInvalid() {
        val missing = ""","communityUnavailable":{}"""

        assertThat(read<SearchTweet>(post("1", missing)).isValid()).isFalse()
        assertThat(read<EmbeddedTweet>(post("2", missing)).isValid()).isFalse()
        assertThat(read<TweetDetail>(post("5", missing)).isValid()).isFalse()
    }

    @Test
    fun aReasonSurvivesTheBuilderAndJson() {
        val reason = SearchTweet.CommunityUnavailable.builder().reason("Suspended").build()
        val embedded =
            EmbeddedTweet.builder()
                .id("2")
                .bookmarkCount(0L)
                .likeCount(0L)
                .quoteCount(0L)
                .replyCount(0L)
                .retweetCount(0L)
                .text("Just launched!")
                .viewCount(0L)
                .communityUnavailable(reason)
                .build()
        val tweet =
            SearchTweet.builder()
                .id("1")
                .bookmarkCount(0L)
                .likeCount(0L)
                .quoteCount(0L)
                .replyCount(0L)
                .retweetCount(0L)
                .text("Just launched!")
                .viewCount(0L)
                .communityUnavailable(reason)
                .quotedTweet(embedded)
                .build()
        val detail =
            TweetDetail.builder()
                .id("5")
                .bookmarkCount(0L)
                .likeCount(0L)
                .quoteCount(0L)
                .replyCount(0L)
                .retweetCount(0L)
                .text("Just launched!")
                .viewCount(0L)
                .communityUnavailable(reason)
                .build()
        val json = jsonMapper()

        assertThat(read<SearchTweet>(json.writeValueAsString(tweet))).isEqualTo(tweet)
        assertThat(read<TweetDetail>(json.writeValueAsString(detail))).isEqualTo(detail)
        assertThat(tweet.toBuilder().build()).isEqualTo(tweet)
        assertThat(detail.toBuilder().build()).isEqualTo(detail)
        assertThat(tweet.quotedTweet()?.communityUnavailable()?.reason()).isEqualTo("Suspended")
        assertThat(tweet.toString())
            .contains("communityUnavailable=CommunityUnavailable{reason=Suspended")
        assertThat(detail.validate()).isSameAs(detail)
    }
}
