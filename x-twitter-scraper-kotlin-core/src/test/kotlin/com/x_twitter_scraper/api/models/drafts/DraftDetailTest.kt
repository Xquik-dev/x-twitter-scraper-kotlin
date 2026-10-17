// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.drafts

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.JsonMissing
import com.x_twitter_scraper.api.core.jsonMapper
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DraftDetailTest {

    @Test
    fun create() {
        val draftDetail =
            DraftDetail.builder()
                .id("id")
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .text("text")
                .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .goal("goal")
                .addIgnoredField("plan")
                .topic("topic")
                .build()

        assertThat(draftDetail.id()).isEqualTo("id")
        assertThat(draftDetail.createdAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(draftDetail.text()).isEqualTo("text")
        assertThat(draftDetail.updatedAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(draftDetail.goal()).isEqualTo("goal")
        assertThat(draftDetail.ignoredFields()).containsExactly("plan")
        assertThat(draftDetail.topic()).isEqualTo("topic")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val draftDetail =
            DraftDetail.builder()
                .id("id")
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .text("text")
                .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .goal("goal")
                .addIgnoredField("plan")
                .topic("topic")
                .build()

        val roundtrippedDraftDetail =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(draftDetail),
                jacksonTypeRef<DraftDetail>(),
            )

        assertThat(roundtrippedDraftDetail).isEqualTo(draftDetail)
    }

    @Test
    fun readsAdditionalFields() {
        val jsonMapper = jsonMapper()

        val draftDetail0 =
            jsonMapper.readValue(
                """{"ignoredFields":[]}""",
                jacksonTypeRef<DraftDetail>(),
            )
        assertThat(draftDetail0.ignoredFields()).isNotNull()
        assertThat(jsonMapper.readTree(jsonMapper.writeValueAsString(draftDetail0)))
            .isEqualTo(jsonMapper.readTree("""{"ignoredFields":[]}"""))
    }

    @Test
    fun keepsAdditionalFieldsOptional() {
        val draftDetail0 =
            DraftDetail.builder()
                .id(JsonMissing.of())
                .createdAt(JsonMissing.of())
                .text(JsonMissing.of())
                .updatedAt(JsonMissing.of())
                .build()
        assertThat(draftDetail0.ignoredFields()).isNull()
    }
}
