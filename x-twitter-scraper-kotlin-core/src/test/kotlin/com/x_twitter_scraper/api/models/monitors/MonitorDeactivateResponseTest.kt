// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.monitors

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MonitorDeactivateResponseTest {

    @Test
    fun create() {
        val monitorDeactivateResponse =
            MonitorDeactivateResponse.builder()
                .deletionStatus("deletionStatus")
                .statusUrl("/api/v1/monitors/42")
                .build()

        assertThat(monitorDeactivateResponse.deletionStatus()).isEqualTo("deletionStatus")
        assertThat(monitorDeactivateResponse.statusUrl()).isEqualTo("/api/v1/monitors/42")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val monitorDeactivateResponse =
            MonitorDeactivateResponse.builder()
                .deletionStatus("deletionStatus")
                .statusUrl("/api/v1/monitors/42")
                .build()

        val roundtrippedMonitorDeactivateResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(monitorDeactivateResponse),
                jacksonTypeRef<MonitorDeactivateResponse>(),
            )

        assertThat(roundtrippedMonitorDeactivateResponse).isEqualTo(monitorDeactivateResponse)
    }
}
