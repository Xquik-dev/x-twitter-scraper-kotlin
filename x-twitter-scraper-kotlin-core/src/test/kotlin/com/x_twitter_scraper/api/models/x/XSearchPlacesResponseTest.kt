// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.JsonValue
import com.x_twitter_scraper.api.core.jsonMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class XSearchPlacesResponseTest {

    @Test
    fun create() {
        val xSearchPlacesResponse =
            XSearchPlacesResponse.builder()
                .count(0L)
                .addPlace(
                    XSearchPlacesResponse.Place.builder()
                        .id("id")
                        .attributes(
                            XSearchPlacesResponse.Place.Attributes.builder()
                                .putAdditionalProperty("foo", JsonValue.from("bar"))
                                .build()
                        )
                        .boundingBox(
                            XSearchPlacesResponse.Place.BoundingBox.builder()
                                .addCoordinate(listOf(listOf(0.0)))
                                .type("type")
                                .build()
                        )
                        .addCentroid(0.0)
                        .addContainedWithin(
                            XSearchPlacesResponse.Place.ContainedWithin.builder()
                                .id("id")
                                .attributes(
                                    XSearchPlacesResponse.Place.ContainedWithin.Attributes.builder()
                                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                                        .build()
                                )
                                .boundingBox(
                                    XSearchPlacesResponse.Place.ContainedWithin.BoundingBox
                                        .builder()
                                        .addCoordinate(listOf(listOf(0.0)))
                                        .type("type")
                                        .build()
                                )
                                .addCentroid(0.0)
                                .country("country")
                                .countryCode("countryCode")
                                .fullName("fullName")
                                .name("name")
                                .placeType("placeType")
                                .url("url")
                                .build()
                        )
                        .country("country")
                        .countryCode("countryCode")
                        .fullName("fullName")
                        .name("name")
                        .placeType("placeType")
                        .url("url")
                        .build()
                )
                .total(0L)
                .message("message")
                .build()

        assertThat(xSearchPlacesResponse.count()).isEqualTo(0L)
        assertThat(xSearchPlacesResponse.places())
            .containsExactly(
                XSearchPlacesResponse.Place.builder()
                    .id("id")
                    .attributes(
                        XSearchPlacesResponse.Place.Attributes.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .boundingBox(
                        XSearchPlacesResponse.Place.BoundingBox.builder()
                            .addCoordinate(listOf(listOf(0.0)))
                            .type("type")
                            .build()
                    )
                    .addCentroid(0.0)
                    .addContainedWithin(
                        XSearchPlacesResponse.Place.ContainedWithin.builder()
                            .id("id")
                            .attributes(
                                XSearchPlacesResponse.Place.ContainedWithin.Attributes.builder()
                                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                                    .build()
                            )
                            .boundingBox(
                                XSearchPlacesResponse.Place.ContainedWithin.BoundingBox.builder()
                                    .addCoordinate(listOf(listOf(0.0)))
                                    .type("type")
                                    .build()
                            )
                            .addCentroid(0.0)
                            .country("country")
                            .countryCode("countryCode")
                            .fullName("fullName")
                            .name("name")
                            .placeType("placeType")
                            .url("url")
                            .build()
                    )
                    .country("country")
                    .countryCode("countryCode")
                    .fullName("fullName")
                    .name("name")
                    .placeType("placeType")
                    .url("url")
                    .build()
            )
        assertThat(xSearchPlacesResponse.total()).isEqualTo(0L)
        assertThat(xSearchPlacesResponse.message()).isEqualTo("message")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val xSearchPlacesResponse =
            XSearchPlacesResponse.builder()
                .count(0L)
                .addPlace(
                    XSearchPlacesResponse.Place.builder()
                        .id("id")
                        .attributes(
                            XSearchPlacesResponse.Place.Attributes.builder()
                                .putAdditionalProperty("foo", JsonValue.from("bar"))
                                .build()
                        )
                        .boundingBox(
                            XSearchPlacesResponse.Place.BoundingBox.builder()
                                .addCoordinate(listOf(listOf(0.0)))
                                .type("type")
                                .build()
                        )
                        .addCentroid(0.0)
                        .addContainedWithin(
                            XSearchPlacesResponse.Place.ContainedWithin.builder()
                                .id("id")
                                .attributes(
                                    XSearchPlacesResponse.Place.ContainedWithin.Attributes.builder()
                                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                                        .build()
                                )
                                .boundingBox(
                                    XSearchPlacesResponse.Place.ContainedWithin.BoundingBox
                                        .builder()
                                        .addCoordinate(listOf(listOf(0.0)))
                                        .type("type")
                                        .build()
                                )
                                .addCentroid(0.0)
                                .country("country")
                                .countryCode("countryCode")
                                .fullName("fullName")
                                .name("name")
                                .placeType("placeType")
                                .url("url")
                                .build()
                        )
                        .country("country")
                        .countryCode("countryCode")
                        .fullName("fullName")
                        .name("name")
                        .placeType("placeType")
                        .url("url")
                        .build()
                )
                .total(0L)
                .message("message")
                .build()

        val roundtrippedXSearchPlacesResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(xSearchPlacesResponse),
                jacksonTypeRef<XSearchPlacesResponse>(),
            )

        assertThat(roundtrippedXSearchPlacesResponse).isEqualTo(xSearchPlacesResponse)
    }
}
