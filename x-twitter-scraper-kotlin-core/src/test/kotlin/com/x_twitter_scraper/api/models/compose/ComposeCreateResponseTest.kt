// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.models.compose

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComposeCreateResponseTest {

    @Test
    fun create() {
        val composeCreateResponse =
            ComposeCreateResponse.builder()
                .feedback("Strong hook. Consider adding a call to action.")
                .score(78.0)
                .addSuggestion("Add a thread hook")
                .addSuggestion("Include a relevant hashtag")
                .text("AI is reshaping every industry. Here are 5 trends to watch in 2025.")
                .build()

        assertThat(composeCreateResponse.feedback())
            .isEqualTo("Strong hook. Consider adding a call to action.")
        assertThat(composeCreateResponse.score()).isEqualTo(78.0)
        assertThat(composeCreateResponse.suggestions())
            .containsExactly("Add a thread hook", "Include a relevant hashtag")
        assertThat(composeCreateResponse.text())
            .isEqualTo("AI is reshaping every industry. Here are 5 trends to watch in 2025.")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val composeCreateResponse =
            ComposeCreateResponse.builder()
                .feedback("Strong hook. Consider adding a call to action.")
                .score(78.0)
                .addSuggestion("Add a thread hook")
                .addSuggestion("Include a relevant hashtag")
                .text("AI is reshaping every industry. Here are 5 trends to watch in 2025.")
                .build()

        val roundtrippedComposeCreateResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(composeCreateResponse),
                jacksonTypeRef<ComposeCreateResponse>(),
            )

        assertThat(roundtrippedComposeCreateResponse).isEqualTo(composeCreateResponse)
    }
}
