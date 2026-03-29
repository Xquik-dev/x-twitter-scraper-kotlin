// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.models.x.profile

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ProfilePatchAllParamsTest {

    @Test
    fun create() {
        ProfilePatchAllParams.builder()
            .account("account")
            .description("description")
            .location("location")
            .name("name")
            .url("url")
            .build()
    }

    @Test
    fun body() {
        val params =
            ProfilePatchAllParams.builder()
                .account("account")
                .description("description")
                .location("location")
                .name("name")
                .url("url")
                .build()

        val body = params._body()

        assertThat(body.account()).isEqualTo("account")
        assertThat(body.description()).isEqualTo("description")
        assertThat(body.location()).isEqualTo("location")
        assertThat(body.name()).isEqualTo("name")
        assertThat(body.url()).isEqualTo("url")
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params = ProfilePatchAllParams.builder().account("account").build()

        val body = params._body()

        assertThat(body.account()).isEqualTo("account")
    }
}
