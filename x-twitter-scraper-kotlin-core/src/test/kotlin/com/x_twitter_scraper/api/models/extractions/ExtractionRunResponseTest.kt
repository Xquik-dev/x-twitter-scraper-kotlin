// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.extractions

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.JsonMissing
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

    @Test
    fun readsAdditionalFields() {
        val jsonMapper = jsonMapper()

        val extractionRunResponse0 =
            jsonMapper.readValue(
                """{"id":"value","pollAfterMs":1,"status":"value","statusUrl":"value","toolType":"value","waitUrl":"value"}""",
                jacksonTypeRef<ExtractionRunResponse>(),
            )
        assertThat(extractionRunResponse0.id()).isNotNull()
        assertThat(extractionRunResponse0.pollAfterMs()).isNotNull()
        assertThat(extractionRunResponse0.status()).isNotNull()
        assertThat(extractionRunResponse0.statusUrl()).isNotNull()
        assertThat(extractionRunResponse0.toolType()).isNotNull()
        assertThat(extractionRunResponse0.waitUrl()).isNotNull()
        assertThat(jsonMapper.readTree(jsonMapper.writeValueAsString(extractionRunResponse0)))
            .isEqualTo(
                jsonMapper.readTree(
                    """{"id":"value","pollAfterMs":1,"status":"value","statusUrl":"value","toolType":"value","waitUrl":"value"}"""
                )
            )
    }

    @Test
    fun keepsAdditionalFieldsOptional() {
        val extractionRunResponse0 =
            ExtractionRunResponse.builder()
                .allowed(JsonMissing.of())
                .creditsAvailable(JsonMissing.of())
                .creditsRequired(JsonMissing.of())
                .estimatedResults(JsonMissing.of())
                .source(JsonMissing.of())
                .build()
        assertThat(extractionRunResponse0.id()).isNull()
        assertThat(extractionRunResponse0.pollAfterMs()).isNull()
        assertThat(extractionRunResponse0.status()).isNull()
        assertThat(extractionRunResponse0.statusUrl()).isNull()
        assertThat(extractionRunResponse0.toolType()).isNull()
        assertThat(extractionRunResponse0.waitUrl()).isNull()
    }
}
