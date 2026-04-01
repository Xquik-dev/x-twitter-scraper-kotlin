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
                .feedback("feedback")
                .score(0.0)
                .addSuggestion("string")
                .text("text")
                .build()

        assertThat(composeCreateResponse.feedback()).isEqualTo("feedback")
        assertThat(composeCreateResponse.score()).isEqualTo(0.0)
        assertThat(composeCreateResponse.suggestions()).containsExactly("string")
        assertThat(composeCreateResponse.text()).isEqualTo("text")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val composeCreateResponse =
            ComposeCreateResponse.builder()
                .feedback("feedback")
                .score(0.0)
                .addSuggestion("string")
                .text("text")
                .build()

        val roundtrippedComposeCreateResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(composeCreateResponse),
                jacksonTypeRef<ComposeCreateResponse>(),
            )

        assertThat(roundtrippedComposeCreateResponse).isEqualTo(composeCreateResponse)
    }
}
