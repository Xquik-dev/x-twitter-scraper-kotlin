// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.models

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SearchTweetTest {

    @Test
    fun create() {
        val searchTweet =
            SearchTweet.builder()
                .id("1234567890")
                .text("Just launched our new feature!")
                .author(
                    SearchTweet.Author.builder()
                        .id("9876543210")
                        .name("Elon Musk")
                        .username("elonmusk")
                        .verified(true)
                        .build()
                )
                .bookmarkCount(2L)
                .createdAt("2025-01-15T12:00:00Z")
                .isNoteTweet(false)
                .likeCount(42L)
                .quoteCount(1L)
                .replyCount(3L)
                .retweetCount(5L)
                .viewCount(1500L)
                .build()

        assertThat(searchTweet.id()).isEqualTo("1234567890")
        assertThat(searchTweet.text()).isEqualTo("Just launched our new feature!")
        assertThat(searchTweet.author())
            .isEqualTo(
                SearchTweet.Author.builder()
                    .id("9876543210")
                    .name("Elon Musk")
                    .username("elonmusk")
                    .verified(true)
                    .build()
            )
        assertThat(searchTweet.bookmarkCount()).isEqualTo(2L)
        assertThat(searchTweet.createdAt()).isEqualTo("2025-01-15T12:00:00Z")
        assertThat(searchTweet.isNoteTweet()).isEqualTo(false)
        assertThat(searchTweet.likeCount()).isEqualTo(42L)
        assertThat(searchTweet.quoteCount()).isEqualTo(1L)
        assertThat(searchTweet.replyCount()).isEqualTo(3L)
        assertThat(searchTweet.retweetCount()).isEqualTo(5L)
        assertThat(searchTweet.viewCount()).isEqualTo(1500L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val searchTweet =
            SearchTweet.builder()
                .id("1234567890")
                .text("Just launched our new feature!")
                .author(
                    SearchTweet.Author.builder()
                        .id("9876543210")
                        .name("Elon Musk")
                        .username("elonmusk")
                        .verified(true)
                        .build()
                )
                .bookmarkCount(2L)
                .createdAt("2025-01-15T12:00:00Z")
                .isNoteTweet(false)
                .likeCount(42L)
                .quoteCount(1L)
                .replyCount(3L)
                .retweetCount(5L)
                .viewCount(1500L)
                .build()

        val roundtrippedSearchTweet =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(searchTweet),
                jacksonTypeRef<SearchTweet>(),
            )

        assertThat(roundtrippedSearchTweet).isEqualTo(searchTweet)
    }
}
