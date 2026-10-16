// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.extractions

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ExtractionRunResponseTest {

    @Test
    fun create() {
        val extractionRunResponse =
            ExtractionRunResponse.builder()
                .allowed(true)
                .creditsAvailable("creditsAvailable")
                .creditsRequired("creditsRequired")
                .estimatedResults(0L)
                .source("source")
                .id("ext_123")
                .pollAfterMs(1000L)
                .resolvedXUserId("resolvedXUserId")
                .status(ExtractionRunResponse.Status.RUNNING)
                .statusUrl("/api/v1/extractions/a1b2c3d4")
                .toolType(ExtractionRunResponse.ToolType.FOLLOWER_EXPLORER)
                .waitUrl("/api/v1/extractions/id?wait=10&limit=1&outputMode=compact")
                .build()

        assertThat(extractionRunResponse.allowed()).isEqualTo(true)
        assertThat(extractionRunResponse.creditsAvailable()).isEqualTo("creditsAvailable")
        assertThat(extractionRunResponse.creditsRequired()).isEqualTo("creditsRequired")
        assertThat(extractionRunResponse.estimatedResults()).isEqualTo(0L)
        assertThat(extractionRunResponse.source()).isEqualTo("source")
        assertThat(extractionRunResponse.id()).isEqualTo("ext_123")
        assertThat(extractionRunResponse.pollAfterMs()).isEqualTo(1000L)
        assertThat(extractionRunResponse.resolvedXUserId()).isEqualTo("resolvedXUserId")
        assertThat(extractionRunResponse.status()).isEqualTo(ExtractionRunResponse.Status.RUNNING)
        assertThat(extractionRunResponse.statusUrl()).isEqualTo("/api/v1/extractions/a1b2c3d4")
        assertThat(extractionRunResponse.toolType())
            .isEqualTo(ExtractionRunResponse.ToolType.FOLLOWER_EXPLORER)
        assertThat(extractionRunResponse.waitUrl())
            .isEqualTo("/api/v1/extractions/id?wait=10&limit=1&outputMode=compact")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val extractionRunResponse =
            ExtractionRunResponse.builder()
                .allowed(true)
                .creditsAvailable("creditsAvailable")
                .creditsRequired("creditsRequired")
                .estimatedResults(0L)
                .source("source")
                .id("ext_123")
                .pollAfterMs(1000L)
                .resolvedXUserId("resolvedXUserId")
                .status(ExtractionRunResponse.Status.RUNNING)
                .statusUrl("/api/v1/extractions/a1b2c3d4")
                .toolType(ExtractionRunResponse.ToolType.FOLLOWER_EXPLORER)
                .waitUrl("/api/v1/extractions/id?wait=10&limit=1&outputMode=compact")
                .build()

        val roundtrippedExtractionRunResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(extractionRunResponse),
                jacksonTypeRef<ExtractionRunResponse>(),
            )

        assertThat(roundtrippedExtractionRunResponse).isEqualTo(extractionRunResponse)
    }
}
