// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.models.extractions

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ExtractionRunParamsTest {

    @Test
    fun create() {
        ExtractionRunParams.builder()
            .toolType(ExtractionRunParams.ToolType.FOLLOWER_EXPLORER)
            .advancedQuery("min_faves:100")
            .exactPhrase("artificial intelligence")
            .excludeWords("spam")
            .searchQuery("AI trends 2025")
            .targetCommunityId("1500000000000000000")
            .targetListId("1234567890")
            .targetSpaceId("1vOGwMdBqpwGB")
            .targetTweetId("1234567890")
            .targetUsername("elonmusk")
            .build()
    }

    @Test
    fun body() {
        val params =
            ExtractionRunParams.builder()
                .toolType(ExtractionRunParams.ToolType.FOLLOWER_EXPLORER)
                .advancedQuery("min_faves:100")
                .exactPhrase("artificial intelligence")
                .excludeWords("spam")
                .searchQuery("AI trends 2025")
                .targetCommunityId("1500000000000000000")
                .targetListId("1234567890")
                .targetSpaceId("1vOGwMdBqpwGB")
                .targetTweetId("1234567890")
                .targetUsername("elonmusk")
                .build()

        val body = params._body()

        assertThat(body.toolType()).isEqualTo(ExtractionRunParams.ToolType.FOLLOWER_EXPLORER)
        assertThat(body.advancedQuery()).isEqualTo("min_faves:100")
        assertThat(body.exactPhrase()).isEqualTo("artificial intelligence")
        assertThat(body.excludeWords()).isEqualTo("spam")
        assertThat(body.searchQuery()).isEqualTo("AI trends 2025")
        assertThat(body.targetCommunityId()).isEqualTo("1500000000000000000")
        assertThat(body.targetListId()).isEqualTo("1234567890")
        assertThat(body.targetSpaceId()).isEqualTo("1vOGwMdBqpwGB")
        assertThat(body.targetTweetId()).isEqualTo("1234567890")
        assertThat(body.targetUsername()).isEqualTo("elonmusk")
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
