// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.extractions

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ExtractionEstimateCostResponseTest {

    @Test
    fun create() {
        val extractionEstimateCostResponse =
            ExtractionEstimateCostResponse.builder()
                .allowed(true)
                .creditsAvailable("creditsAvailable")
                .creditsRequired("creditsRequired")
                .estimatedResults(0L)
                .source(ExtractionEstimateCostResponse.Source.FOLLOWERS)
                .resolvedXUserId("resolvedXUserId")
                .build()

        assertThat(extractionEstimateCostResponse.allowed()).isEqualTo(true)
        assertThat(extractionEstimateCostResponse.creditsAvailable()).isEqualTo("creditsAvailable")
        assertThat(extractionEstimateCostResponse.creditsRequired()).isEqualTo("creditsRequired")
        assertThat(extractionEstimateCostResponse.estimatedResults()).isEqualTo(0L)
        assertThat(extractionEstimateCostResponse.source())
            .isEqualTo(ExtractionEstimateCostResponse.Source.FOLLOWERS)
        assertThat(extractionEstimateCostResponse.resolvedXUserId()).isEqualTo("resolvedXUserId")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val extractionEstimateCostResponse =
            ExtractionEstimateCostResponse.builder()
                .allowed(true)
                .creditsAvailable("creditsAvailable")
                .creditsRequired("creditsRequired")
                .estimatedResults(0L)
                .source(ExtractionEstimateCostResponse.Source.FOLLOWERS)
                .resolvedXUserId("resolvedXUserId")
                .build()

        val roundtrippedExtractionEstimateCostResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(extractionEstimateCostResponse),
                jacksonTypeRef<ExtractionEstimateCostResponse>(),
            )

        assertThat(roundtrippedExtractionEstimateCostResponse)
            .isEqualTo(extractionEstimateCostResponse)
    }
}
