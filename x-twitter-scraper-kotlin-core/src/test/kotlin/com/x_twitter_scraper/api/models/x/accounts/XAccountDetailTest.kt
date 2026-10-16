// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.accounts

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class XAccountDetailTest {

    @Test
    fun create() {
        val xAccountDetail =
            XAccountDetail.builder()
                .id("id")
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .health(XAccountDetail.Health.HEALTHY)
                .status("status")
                .xUserId("xUserId")
                .xUsername("xUsername")
                .connectedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        assertThat(xAccountDetail.id()).isEqualTo("id")
        assertThat(xAccountDetail.createdAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(xAccountDetail.health()).isEqualTo(XAccountDetail.Health.HEALTHY)
        assertThat(xAccountDetail.status()).isEqualTo("status")
        assertThat(xAccountDetail.xUserId()).isEqualTo("xUserId")
        assertThat(xAccountDetail.xUsername()).isEqualTo("xUsername")
        assertThat(xAccountDetail.connectedAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(xAccountDetail.updatedAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val xAccountDetail =
            XAccountDetail.builder()
                .id("id")
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .health(XAccountDetail.Health.HEALTHY)
                .status("status")
                .xUserId("xUserId")
                .xUsername("xUsername")
                .connectedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        val roundtrippedXAccountDetail =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(xAccountDetail),
                jacksonTypeRef<XAccountDetail>(),
            )

        assertThat(roundtrippedXAccountDetail).isEqualTo(xAccountDetail)
    }
}
