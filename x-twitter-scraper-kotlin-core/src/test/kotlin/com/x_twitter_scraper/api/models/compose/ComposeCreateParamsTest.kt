// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.models.compose

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComposeCreateParamsTest {

    @Test
    fun create() {
        ComposeCreateParams.builder()
            .step(ComposeCreateParams.Step.COMPOSE)
            .additionalContext("https://x.com/elonmusk/status/1234567890")
            .callToAction("Follow for more")
            .draft("AI is changing everything. Here's why.")
            .goal(ComposeCreateParams.Goal.ENGAGEMENT)
            .hasLink(false)
            .hasMedia(false)
            .mediaType(ComposeCreateParams.MediaType.NONE)
            .styleUsername("elonmusk")
            .tone("professional")
            .topic("AI trends in 2025")
            .build()
    }

    @Test
    fun body() {
        val params =
            ComposeCreateParams.builder()
                .step(ComposeCreateParams.Step.COMPOSE)
                .additionalContext("https://x.com/elonmusk/status/1234567890")
                .callToAction("Follow for more")
                .draft("AI is changing everything. Here's why.")
                .goal(ComposeCreateParams.Goal.ENGAGEMENT)
                .hasLink(false)
                .hasMedia(false)
                .mediaType(ComposeCreateParams.MediaType.NONE)
                .styleUsername("elonmusk")
                .tone("professional")
                .topic("AI trends in 2025")
                .build()

        val body = params._body()

        assertThat(body.step()).isEqualTo(ComposeCreateParams.Step.COMPOSE)
        assertThat(body.additionalContext()).isEqualTo("https://x.com/elonmusk/status/1234567890")
        assertThat(body.callToAction()).isEqualTo("Follow for more")
        assertThat(body.draft()).isEqualTo("AI is changing everything. Here's why.")
        assertThat(body.goal()).isEqualTo(ComposeCreateParams.Goal.ENGAGEMENT)
        assertThat(body.hasLink()).isEqualTo(false)
        assertThat(body.hasMedia()).isEqualTo(false)
        assertThat(body.mediaType()).isEqualTo(ComposeCreateParams.MediaType.NONE)
        assertThat(body.styleUsername()).isEqualTo("elonmusk")
        assertThat(body.tone()).isEqualTo("professional")
        assertThat(body.topic()).isEqualTo("AI trends in 2025")
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params = ComposeCreateParams.builder().step(ComposeCreateParams.Step.COMPOSE).build()

        val body = params._body()

        assertThat(body.step()).isEqualTo(ComposeCreateParams.Step.COMPOSE)
    }
}
