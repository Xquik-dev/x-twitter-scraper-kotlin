// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.accounts

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class XAccountTest {

    @Test
    fun create() {
        val xAccount =
            XAccount.builder()
                .id("id")
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .health(XAccount.Health.HEALTHY)
                .status("status")
                .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .xUserId("xUserId")
                .xUsername("xUsername")
                .connectedAt(OffsetDateTime.parse("2025-03-10T08:30:00Z"))
                .build()

        assertThat(xAccount.id()).isEqualTo("id")
        assertThat(xAccount.createdAt()).isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(xAccount.health()).isEqualTo(XAccount.Health.HEALTHY)
        assertThat(xAccount.status()).isEqualTo("status")
        assertThat(xAccount.updatedAt()).isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(xAccount.xUserId()).isEqualTo("xUserId")
        assertThat(xAccount.xUsername()).isEqualTo("xUsername")
        assertThat(xAccount.connectedAt()).isEqualTo(OffsetDateTime.parse("2025-03-10T08:30:00Z"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val xAccount =
            XAccount.builder()
                .id("id")
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .health(XAccount.Health.HEALTHY)
                .status("status")
                .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .xUserId("xUserId")
                .xUsername("xUsername")
                .connectedAt(OffsetDateTime.parse("2025-03-10T08:30:00Z"))
                .build()

        val roundtrippedXAccount =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(xAccount),
                jacksonTypeRef<XAccount>(),
            )

        assertThat(roundtrippedXAccount).isEqualTo(xAccount)
    }
}
