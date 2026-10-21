// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.monitors.keywords

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class KeywordDeactivateResponseTest {

    @Test
    fun create() {
        val keywordDeactivateResponse =
            KeywordDeactivateResponse.builder()
                .deletionStatus("deletionStatus")
                .statusUrl("/api/v1/monitors/42")
                .build()

        assertThat(keywordDeactivateResponse.deletionStatus()).isEqualTo("deletionStatus")
        assertThat(keywordDeactivateResponse.statusUrl()).isEqualTo("/api/v1/monitors/42")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val keywordDeactivateResponse =
            KeywordDeactivateResponse.builder()
                .deletionStatus("deletionStatus")
                .statusUrl("/api/v1/monitors/42")
                .build()

        val roundtrippedKeywordDeactivateResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(keywordDeactivateResponse),
                jacksonTypeRef<KeywordDeactivateResponse>(),
            )

        assertThat(roundtrippedKeywordDeactivateResponse).isEqualTo(keywordDeactivateResponse)
    }
}
