// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.models

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ErrorTest {

    @Test
    fun create() {
        val error =
            Error.builder()
                .error(Error.InnerError.LegacyErrorCode.INVALID_INPUT)
                .message("message")
                .reason("reason")
                .retryAfter(1L)
                .retryAfterMs(1L)
                .build()

        assertThat(error.error())
            .isEqualTo(
                Error.InnerError.ofLegacyErrorCode(Error.InnerError.LegacyErrorCode.INVALID_INPUT)
            )
        assertThat(error.message()).isEqualTo("message")
        assertThat(error.reason()).isEqualTo("reason")
        assertThat(error.retryAfter()).isEqualTo(1L)
        assertThat(error.retryAfterMs()).isEqualTo(1L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val error =
            Error.builder()
                .error(Error.InnerError.LegacyErrorCode.INVALID_INPUT)
                .message("message")
                .reason("reason")
                .retryAfter(1L)
                .retryAfterMs(1L)
                .build()

        val roundtrippedError =
            jsonMapper.readValue(jsonMapper.writeValueAsString(error), jacksonTypeRef<Error>())

        assertThat(roundtrippedError).isEqualTo(error)
    }
}
