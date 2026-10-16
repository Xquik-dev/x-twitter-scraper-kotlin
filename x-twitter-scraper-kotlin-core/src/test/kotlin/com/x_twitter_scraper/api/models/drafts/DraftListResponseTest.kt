// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.drafts

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DraftListResponseTest {

    @Test
    fun create() {
        val draftListResponse =
            DraftListResponse.builder()
                .addDraft(
                    Draft.builder()
                        .id("id")
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .text("text")
                        .build()
                )
                .hasMore(true)
                .nextCursor("nextCursor")
                .build()

        assertThat(draftListResponse.drafts())
            .containsExactly(
                Draft.builder()
                    .id("id")
                    .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .text("text")
                    .build()
            )
        assertThat(draftListResponse.hasMore()).isEqualTo(true)
        assertThat(draftListResponse.nextCursor()).isEqualTo("nextCursor")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val draftListResponse =
            DraftListResponse.builder()
                .addDraft(
                    Draft.builder()
                        .id("id")
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .text("text")
                        .build()
                )
                .hasMore(true)
                .nextCursor("nextCursor")
                .build()

        val roundtrippedDraftListResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(draftListResponse),
                jacksonTypeRef<DraftListResponse>(),
            )

        assertThat(roundtrippedDraftListResponse).isEqualTo(draftListResponse)
    }
}
