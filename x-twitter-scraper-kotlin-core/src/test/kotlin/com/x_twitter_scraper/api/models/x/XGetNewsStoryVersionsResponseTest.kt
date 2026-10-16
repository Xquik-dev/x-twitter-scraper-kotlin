// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class XGetNewsStoryVersionsResponseTest {

    @Test
    fun create() {
        val xGetNewsStoryVersionsResponse =
            XGetNewsStoryVersionsResponse.builder()
                .id("id")
                .count(0L)
                .addVersion(
                    XGetNewsStoryVersionsResponse.Version.builder()
                        .id("id")
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .headline("headline")
                        .text("text")
                        .build()
                )
                .build()

        assertThat(xGetNewsStoryVersionsResponse.id()).isEqualTo("id")
        assertThat(xGetNewsStoryVersionsResponse.count()).isEqualTo(0L)
        assertThat(xGetNewsStoryVersionsResponse.versions())
            .containsExactly(
                XGetNewsStoryVersionsResponse.Version.builder()
                    .id("id")
                    .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .headline("headline")
                    .text("text")
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val xGetNewsStoryVersionsResponse =
            XGetNewsStoryVersionsResponse.builder()
                .id("id")
                .count(0L)
                .addVersion(
                    XGetNewsStoryVersionsResponse.Version.builder()
                        .id("id")
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .headline("headline")
                        .text("text")
                        .build()
                )
                .build()

        val roundtrippedXGetNewsStoryVersionsResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(xGetNewsStoryVersionsResponse),
                jacksonTypeRef<XGetNewsStoryVersionsResponse>(),
            )

        assertThat(roundtrippedXGetNewsStoryVersionsResponse)
            .isEqualTo(xGetNewsStoryVersionsResponse)
    }
}
