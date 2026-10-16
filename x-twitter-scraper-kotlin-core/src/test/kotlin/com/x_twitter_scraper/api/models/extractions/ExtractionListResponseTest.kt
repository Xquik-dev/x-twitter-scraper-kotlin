// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.extractions

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ExtractionListResponseTest {

    @Test
    fun create() {
        val extractionListResponse =
            ExtractionListResponse.builder()
                .addExtraction(
                    ExtractionJob.builder()
                        .id("id")
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .status(ExtractionJob.Status.RUNNING)
                        .toolType(ExtractionJob.ToolType.ARTICLE_EXTRACTOR)
                        .totalResults(0L)
                        .build()
                )
                .hasMore(true)
                .nextCursor("nextCursor")
                .build()

        assertThat(extractionListResponse.extractions())
            .containsExactly(
                ExtractionJob.builder()
                    .id("id")
                    .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .status(ExtractionJob.Status.RUNNING)
                    .toolType(ExtractionJob.ToolType.ARTICLE_EXTRACTOR)
                    .totalResults(0L)
                    .build()
            )
        assertThat(extractionListResponse.hasMore()).isEqualTo(true)
        assertThat(extractionListResponse.nextCursor()).isEqualTo("nextCursor")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val extractionListResponse =
            ExtractionListResponse.builder()
                .addExtraction(
                    ExtractionJob.builder()
                        .id("id")
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .status(ExtractionJob.Status.RUNNING)
                        .toolType(ExtractionJob.ToolType.ARTICLE_EXTRACTOR)
                        .totalResults(0L)
                        .build()
                )
                .hasMore(true)
                .nextCursor("nextCursor")
                .build()

        val roundtrippedExtractionListResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(extractionListResponse),
                jacksonTypeRef<ExtractionListResponse>(),
            )

        assertThat(roundtrippedExtractionListResponse).isEqualTo(extractionListResponse)
    }
}
