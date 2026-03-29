// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.models.integrations

import com.x_twitter_scraper.api.core.JsonValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class IntegrationUpdateParamsTest {

    @Test
    fun create() {
        IntegrationUpdateParams.builder()
            .id("id")
            .addEventType(IntegrationUpdateParams.EventType.TWEET_NEW)
            .filters(
                IntegrationUpdateParams.Filters.builder()
                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                    .build()
            )
            .isActive(true)
            .messageTemplate(
                IntegrationUpdateParams.MessageTemplate.builder()
                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                    .build()
            )
            .name("name")
            .scopeAllMonitors(true)
            .silentPush(true)
            .build()
    }

    @Test
    fun pathParams() {
        val params = IntegrationUpdateParams.builder().id("id").build()

        assertThat(params._pathParam(0)).isEqualTo("id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun body() {
        val params =
            IntegrationUpdateParams.builder()
                .id("id")
                .addEventType(IntegrationUpdateParams.EventType.TWEET_NEW)
                .filters(
                    IntegrationUpdateParams.Filters.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .isActive(true)
                .messageTemplate(
                    IntegrationUpdateParams.MessageTemplate.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .name("name")
                .scopeAllMonitors(true)
                .silentPush(true)
                .build()

        val body = params._body()

        assertThat(body.eventTypes()).containsExactly(IntegrationUpdateParams.EventType.TWEET_NEW)
        assertThat(body.filters())
            .isEqualTo(
                IntegrationUpdateParams.Filters.builder()
                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                    .build()
            )
        assertThat(body.isActive()).isEqualTo(true)
        assertThat(body.messageTemplate())
            .isEqualTo(
                IntegrationUpdateParams.MessageTemplate.builder()
                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                    .build()
            )
        assertThat(body.name()).isEqualTo("name")
        assertThat(body.scopeAllMonitors()).isEqualTo(true)
        assertThat(body.silentPush()).isEqualTo(true)
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params = IntegrationUpdateParams.builder().id("id").build()

        val body = params._body()
    }
}
