// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.extractions

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.JsonMissing
import com.x_twitter_scraper.api.core.jsonMapper
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ExtractionJobTest {

    @Test
    fun create() {
        val extractionJob =
            ExtractionJob.builder()
                .id("id")
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .status(ExtractionJob.Status.RUNNING)
                .toolType(ExtractionJob.ToolType.ARTICLE_EXTRACTOR)
                .totalResults(0L)
                .completedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .completionReason(ExtractionJob.CompletionReason.BUDGET_LIMITED)
                .effectiveResultsLimit(0L)
                .errorMessage("errorMessage")
                .resultsLimit(1L)
                .build()

        assertThat(extractionJob.id()).isEqualTo("id")
        assertThat(extractionJob.createdAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(extractionJob.status()).isEqualTo(ExtractionJob.Status.RUNNING)
        assertThat(extractionJob.toolType()).isEqualTo(ExtractionJob.ToolType.ARTICLE_EXTRACTOR)
        assertThat(extractionJob.totalResults()).isEqualTo(0L)
        assertThat(extractionJob.completedAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(extractionJob.completionReason())
            .isEqualTo(ExtractionJob.CompletionReason.BUDGET_LIMITED)
        assertThat(extractionJob.effectiveResultsLimit()).isEqualTo(0L)
        assertThat(extractionJob.errorMessage()).isEqualTo("errorMessage")
        assertThat(extractionJob.resultsLimit()).isEqualTo(1L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val extractionJob =
            ExtractionJob.builder()
                .id("id")
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .status(ExtractionJob.Status.RUNNING)
                .toolType(ExtractionJob.ToolType.ARTICLE_EXTRACTOR)
                .totalResults(0L)
                .completedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .completionReason(ExtractionJob.CompletionReason.BUDGET_LIMITED)
                .effectiveResultsLimit(0L)
                .errorMessage("errorMessage")
                .resultsLimit(1L)
                .build()

        val roundtrippedExtractionJob =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(extractionJob),
                jacksonTypeRef<ExtractionJob>(),
            )

        assertThat(roundtrippedExtractionJob).isEqualTo(extractionJob)
    }

    @Test
    fun readsAdditionalFields() {
        val jsonMapper = jsonMapper()

        val extractionJob0 =
            jsonMapper.readValue(
                """{"completionReason":"value","effectiveResultsLimit":1,"errorMessage":"value","resultsLimit":1}""",
                jacksonTypeRef<ExtractionJob>(),
            )
        assertThat(extractionJob0.completionReason()).isNotNull()
        assertThat(extractionJob0.effectiveResultsLimit()).isNotNull()
        assertThat(extractionJob0.errorMessage()).isNotNull()
        assertThat(extractionJob0.resultsLimit()).isNotNull()
        assertThat(jsonMapper.readTree(jsonMapper.writeValueAsString(extractionJob0)))
            .isEqualTo(
                jsonMapper.readTree(
                    """{"completionReason":"value","effectiveResultsLimit":1,"errorMessage":"value","resultsLimit":1}"""
                )
            )
    }

    @Test
    fun keepsAdditionalFieldsOptional() {
        val extractionJob0 =
            ExtractionJob.builder()
                .id(JsonMissing.of())
                .createdAt(JsonMissing.of())
                .status(JsonMissing.of())
                .toolType(JsonMissing.of())
                .totalResults(JsonMissing.of())
                .build()
        assertThat(extractionJob0.completionReason()).isNull()
        assertThat(extractionJob0.effectiveResultsLimit()).isNull()
        assertThat(extractionJob0.errorMessage()).isNull()
        assertThat(extractionJob0.resultsLimit()).isNull()
    }
}
