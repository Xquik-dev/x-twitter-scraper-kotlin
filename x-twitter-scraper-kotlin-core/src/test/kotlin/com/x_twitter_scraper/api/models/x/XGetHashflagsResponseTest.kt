// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class XGetHashflagsResponseTest {

    @Test
    fun create() {
        val xGetHashflagsResponse =
            XGetHashflagsResponse.builder()
                .count(0L)
                .addHashflag(
                    XGetHashflagsResponse.Hashflag.builder()
                        .addAnimation(
                            XGetHashflagsResponse.Hashflag.Animation.builder()
                                .assetUrl("assetUrl")
                                .context("context")
                                .priority(0.0)
                                .build()
                        )
                        .assetUrl("assetUrl")
                        .endsAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .hashtag("hashtag")
                        .isHashfettiEnabled(true)
                        .startsAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .build()
                )
                .total(0L)
                .message("message")
                .build()

        assertThat(xGetHashflagsResponse.count()).isEqualTo(0L)
        assertThat(xGetHashflagsResponse.hashflags())
            .containsExactly(
                XGetHashflagsResponse.Hashflag.builder()
                    .addAnimation(
                        XGetHashflagsResponse.Hashflag.Animation.builder()
                            .assetUrl("assetUrl")
                            .context("context")
                            .priority(0.0)
                            .build()
                    )
                    .assetUrl("assetUrl")
                    .endsAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .hashtag("hashtag")
                    .isHashfettiEnabled(true)
                    .startsAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .build()
            )
        assertThat(xGetHashflagsResponse.total()).isEqualTo(0L)
        assertThat(xGetHashflagsResponse.message()).isEqualTo("message")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val xGetHashflagsResponse =
            XGetHashflagsResponse.builder()
                .count(0L)
                .addHashflag(
                    XGetHashflagsResponse.Hashflag.builder()
                        .addAnimation(
                            XGetHashflagsResponse.Hashflag.Animation.builder()
                                .assetUrl("assetUrl")
                                .context("context")
                                .priority(0.0)
                                .build()
                        )
                        .assetUrl("assetUrl")
                        .endsAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .hashtag("hashtag")
                        .isHashfettiEnabled(true)
                        .startsAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .build()
                )
                .total(0L)
                .message("message")
                .build()

        val roundtrippedXGetHashflagsResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(xGetHashflagsResponse),
                jacksonTypeRef<XGetHashflagsResponse>(),
            )

        assertThat(roundtrippedXGetHashflagsResponse).isEqualTo(xGetHashflagsResponse)
    }
}
