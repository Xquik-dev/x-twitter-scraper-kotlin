// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.models.extractions

import java.time.LocalDate
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ExtractionRunParamsTest {

    @Test
    fun create() {
        ExtractionRunParams.builder()
            .toolType(ExtractionRunParams.ToolType.FOLLOWER_EXPLORER)
            .advancedQuery("min_faves:100")
            .anyWords("ChatGPT AI model")
            .boundingBox("-74.1 40.6 -73.9 40.8")
            .cashtags("\$TSLA \$NVDA")
            .conversationId("1234567890")
            .exactPhrase("artificial intelligence")
            .excludeWords("spam")
            .fromUser("nasa")
            .hashtags("#AI startups")
            .inReplyToTweetId("1234567890")
            .language("en")
            .listId("1234567890")
            .mediaType(ExtractionRunParams.MediaType.IMAGES)
            .mentioning("example_user")
            .minFaves(10L)
            .minQuotes(2L)
            .minReplies(3L)
            .minRetweets(5L)
            .place("96683cc9126741d1")
            .placeCountry("US")
            .pointRadius("-73.99 40.73 25mi")
            .quotes(ExtractionRunParams.Quotes.INCLUDE)
            .quotesOfTweetId("1234567890")
            .replies(ExtractionRunParams.Replies.INCLUDE)
            .resultsLimit(1000L)
            .retweets(ExtractionRunParams.Retweets.EXCLUDE)
            .retweetsOfTweetId("1234567890")
            .searchQuery("AI trends 2025")
            .sinceDate(LocalDate.parse("2025-01-01"))
            .targetCommunityId("1500000000000000000")
            .targetListId("1234567890")
            .targetSpaceId("1vOGwMdBqpwGB")
            .targetTweetId("1234567890")
            .targetUsername("elonmusk")
            .toUser("openai")
            .untilDate(LocalDate.parse("2025-12-31"))
            .url("example.com")
            .verifiedOnly(false)
            .build()
    }

    @Test
    fun body() {
        val params =
            ExtractionRunParams.builder()
                .toolType(ExtractionRunParams.ToolType.FOLLOWER_EXPLORER)
                .advancedQuery("min_faves:100")
                .anyWords("ChatGPT AI model")
                .boundingBox("-74.1 40.6 -73.9 40.8")
                .cashtags("\$TSLA \$NVDA")
                .conversationId("1234567890")
                .exactPhrase("artificial intelligence")
                .excludeWords("spam")
                .fromUser("nasa")
                .hashtags("#AI startups")
                .inReplyToTweetId("1234567890")
                .language("en")
                .listId("1234567890")
                .mediaType(ExtractionRunParams.MediaType.IMAGES)
                .mentioning("example_user")
                .minFaves(10L)
                .minQuotes(2L)
                .minReplies(3L)
                .minRetweets(5L)
                .place("96683cc9126741d1")
                .placeCountry("US")
                .pointRadius("-73.99 40.73 25mi")
                .quotes(ExtractionRunParams.Quotes.INCLUDE)
                .quotesOfTweetId("1234567890")
                .replies(ExtractionRunParams.Replies.INCLUDE)
                .resultsLimit(1000L)
                .retweets(ExtractionRunParams.Retweets.EXCLUDE)
                .retweetsOfTweetId("1234567890")
                .searchQuery("AI trends 2025")
                .sinceDate(LocalDate.parse("2025-01-01"))
                .targetCommunityId("1500000000000000000")
                .targetListId("1234567890")
                .targetSpaceId("1vOGwMdBqpwGB")
                .targetTweetId("1234567890")
                .targetUsername("elonmusk")
                .toUser("openai")
                .untilDate(LocalDate.parse("2025-12-31"))
                .url("example.com")
                .verifiedOnly(false)
                .build()

        val body = params._body()

        assertThat(body.toolType()).isEqualTo(ExtractionRunParams.ToolType.FOLLOWER_EXPLORER)
        assertThat(body.advancedQuery()).isEqualTo("min_faves:100")
        assertThat(body.anyWords()).isEqualTo("ChatGPT AI model")
        assertThat(body.boundingBox()).isEqualTo("-74.1 40.6 -73.9 40.8")
        assertThat(body.cashtags()).isEqualTo("\$TSLA \$NVDA")
        assertThat(body.conversationId()).isEqualTo("1234567890")
        assertThat(body.exactPhrase()).isEqualTo("artificial intelligence")
        assertThat(body.excludeWords()).isEqualTo("spam")
        assertThat(body.fromUser()).isEqualTo("nasa")
        assertThat(body.hashtags()).isEqualTo("#AI startups")
        assertThat(body.inReplyToTweetId()).isEqualTo("1234567890")
        assertThat(body.language()).isEqualTo("en")
        assertThat(body.listId()).isEqualTo("1234567890")
        assertThat(body.mediaType()).isEqualTo(ExtractionRunParams.MediaType.IMAGES)
        assertThat(body.mentioning()).isEqualTo("example_user")
        assertThat(body.minFaves()).isEqualTo(10L)
        assertThat(body.minQuotes()).isEqualTo(2L)
        assertThat(body.minReplies()).isEqualTo(3L)
        assertThat(body.minRetweets()).isEqualTo(5L)
        assertThat(body.place()).isEqualTo("96683cc9126741d1")
        assertThat(body.placeCountry()).isEqualTo("US")
        assertThat(body.pointRadius()).isEqualTo("-73.99 40.73 25mi")
        assertThat(body.quotes()).isEqualTo(ExtractionRunParams.Quotes.INCLUDE)
        assertThat(body.quotesOfTweetId()).isEqualTo("1234567890")
        assertThat(body.replies()).isEqualTo(ExtractionRunParams.Replies.INCLUDE)
        assertThat(body.resultsLimit()).isEqualTo(1000L)
        assertThat(body.retweets()).isEqualTo(ExtractionRunParams.Retweets.EXCLUDE)
        assertThat(body.retweetsOfTweetId()).isEqualTo("1234567890")
        assertThat(body.searchQuery()).isEqualTo("AI trends 2025")
        assertThat(body.sinceDate()).isEqualTo(LocalDate.parse("2025-01-01"))
        assertThat(body.targetCommunityId()).isEqualTo("1500000000000000000")
        assertThat(body.targetListId()).isEqualTo("1234567890")
        assertThat(body.targetSpaceId()).isEqualTo("1vOGwMdBqpwGB")
        assertThat(body.targetTweetId()).isEqualTo("1234567890")
        assertThat(body.targetUsername()).isEqualTo("elonmusk")
        assertThat(body.toUser()).isEqualTo("openai")
        assertThat(body.untilDate()).isEqualTo(LocalDate.parse("2025-12-31"))
        assertThat(body.url()).isEqualTo("example.com")
        assertThat(body.verifiedOnly()).isEqualTo(false)
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            ExtractionRunParams.builder()
                .toolType(ExtractionRunParams.ToolType.FOLLOWER_EXPLORER)
                .build()

        val body = params._body()

        assertThat(body.toolType()).isEqualTo(ExtractionRunParams.ToolType.FOLLOWER_EXPLORER)
    }
}
