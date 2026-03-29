// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.models.compose

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComposeCreateParamsTest {

    @Test
    fun create() {
        ComposeCreateParams.builder()
            .step(ComposeCreateParams.Step.COMPOSE)
            .additionalContext("additionalContext")
            .callToAction("callToAction")
            .draft("draft")
            .goal(ComposeCreateParams.Goal.ENGAGEMENT)
            .hasLink(true)
            .hasMedia(true)
            .mediaType(ComposeCreateParams.MediaType.PHOTO)
            .styleUsername("styleUsername")
            .tone("tone")
            .topic("topic")
            .build()
    }

    @Test
    fun body() {
        val params =
            ComposeCreateParams.builder()
                .step(ComposeCreateParams.Step.COMPOSE)
                .additionalContext("additionalContext")
                .callToAction("callToAction")
                .draft("draft")
                .goal(ComposeCreateParams.Goal.ENGAGEMENT)
                .hasLink(true)
                .hasMedia(true)
                .mediaType(ComposeCreateParams.MediaType.PHOTO)
                .styleUsername("styleUsername")
                .tone("tone")
                .topic("topic")
                .build()

        val body = params._body()

        assertThat(body.step()).isEqualTo(ComposeCreateParams.Step.COMPOSE)
        assertThat(body.additionalContext()).isEqualTo("additionalContext")
        assertThat(body.callToAction()).isEqualTo("callToAction")
        assertThat(body.draft()).isEqualTo("draft")
        assertThat(body.goal()).isEqualTo(ComposeCreateParams.Goal.ENGAGEMENT)
        assertThat(body.hasLink()).isEqualTo(true)
        assertThat(body.hasMedia()).isEqualTo(true)
        assertThat(body.mediaType()).isEqualTo(ComposeCreateParams.MediaType.PHOTO)
        assertThat(body.styleUsername()).isEqualTo("styleUsername")
        assertThat(body.tone()).isEqualTo("tone")
        assertThat(body.topic()).isEqualTo("topic")
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params = ComposeCreateParams.builder().step(ComposeCreateParams.Step.COMPOSE).build()

        val body = params._body()

        assertThat(body.step()).isEqualTo(ComposeCreateParams.Step.COMPOSE)
    }
}
