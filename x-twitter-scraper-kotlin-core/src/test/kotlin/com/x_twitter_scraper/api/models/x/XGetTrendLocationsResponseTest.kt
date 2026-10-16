// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class XGetTrendLocationsResponseTest {

    @Test
    fun create() {
        val xGetTrendLocationsResponse =
            XGetTrendLocationsResponse.builder()
                .count(0L)
                .addLocation(
                    XGetTrendLocationsResponse.Location.builder()
                        .country("country")
                        .countryCode("countryCode")
                        .name("name")
                        .parentWoeid(0L)
                        .placeType("placeType")
                        .placeTypeCode(0L)
                        .url("url")
                        .woeid(0L)
                        .build()
                )
                .total(0L)
                .message("message")
                .build()

        assertThat(xGetTrendLocationsResponse.count()).isEqualTo(0L)
        assertThat(xGetTrendLocationsResponse.locations())
            .containsExactly(
                XGetTrendLocationsResponse.Location.builder()
                    .country("country")
                    .countryCode("countryCode")
                    .name("name")
                    .parentWoeid(0L)
                    .placeType("placeType")
                    .placeTypeCode(0L)
                    .url("url")
                    .woeid(0L)
                    .build()
            )
        assertThat(xGetTrendLocationsResponse.total()).isEqualTo(0L)
        assertThat(xGetTrendLocationsResponse.message()).isEqualTo("message")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val xGetTrendLocationsResponse =
            XGetTrendLocationsResponse.builder()
                .count(0L)
                .addLocation(
                    XGetTrendLocationsResponse.Location.builder()
                        .country("country")
                        .countryCode("countryCode")
                        .name("name")
                        .parentWoeid(0L)
                        .placeType("placeType")
                        .placeTypeCode(0L)
                        .url("url")
                        .woeid(0L)
                        .build()
                )
                .total(0L)
                .message("message")
                .build()

        val roundtrippedXGetTrendLocationsResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(xGetTrendLocationsResponse),
                jacksonTypeRef<XGetTrendLocationsResponse>(),
            )

        assertThat(roundtrippedXGetTrendLocationsResponse).isEqualTo(xGetTrendLocationsResponse)
    }
}
