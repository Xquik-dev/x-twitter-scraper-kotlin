// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.models.extractions

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ExtractionRunParamsTest {

    @Test
    fun create() {
        ExtractionRunParams.builder()
            .toolType(ExtractionRunParams.ToolType.ARTICLE_EXTRACTOR)
            .advancedQuery("advancedQuery")
            .exactPhrase("exactPhrase")
            .excludeWords("excludeWords")
            .searchQuery("searchQuery")
            .targetCommunityId("targetCommunityId")
            .targetListId("targetListId")
            .targetSpaceId("targetSpaceId")
            .targetTweetId("targetTweetId")
            .targetUsername("targetUsername")
            .build()
    }

    @Test
    fun body() {
        val params =
            ExtractionRunParams.builder()
                .toolType(ExtractionRunParams.ToolType.ARTICLE_EXTRACTOR)
                .advancedQuery("advancedQuery")
                .exactPhrase("exactPhrase")
                .excludeWords("excludeWords")
                .searchQuery("searchQuery")
                .targetCommunityId("targetCommunityId")
                .targetListId("targetListId")
                .targetSpaceId("targetSpaceId")
                .targetTweetId("targetTweetId")
                .targetUsername("targetUsername")
                .build()

        val body = params._body()

        assertThat(body.toolType()).isEqualTo(ExtractionRunParams.ToolType.ARTICLE_EXTRACTOR)
        assertThat(body.advancedQuery()).isEqualTo("advancedQuery")
        assertThat(body.exactPhrase()).isEqualTo("exactPhrase")
        assertThat(body.excludeWords()).isEqualTo("excludeWords")
        assertThat(body.searchQuery()).isEqualTo("searchQuery")
        assertThat(body.targetCommunityId()).isEqualTo("targetCommunityId")
        assertThat(body.targetListId()).isEqualTo("targetListId")
        assertThat(body.targetSpaceId()).isEqualTo("targetSpaceId")
        assertThat(body.targetTweetId()).isEqualTo("targetTweetId")
        assertThat(body.targetUsername()).isEqualTo("targetUsername")
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            ExtractionRunParams.builder()
                .toolType(ExtractionRunParams.ToolType.ARTICLE_EXTRACTOR)
                .build()

        val body = params._body()

        assertThat(body.toolType()).isEqualTo(ExtractionRunParams.ToolType.ARTICLE_EXTRACTOR)
    }
}
