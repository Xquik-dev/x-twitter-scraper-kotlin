// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.spaces

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SpaceRetrieveReplayResponseTest {

    @Test
    fun create() {
        val spaceRetrieveReplayResponse =
            SpaceRetrieveReplayResponse.builder()
                .replay(
                    SpaceRetrieveReplayResponse.Replay.builder()
                        .url("url")
                        .status("status")
                        .streamType("streamType")
                        .build()
                )
                .build()

        assertThat(spaceRetrieveReplayResponse.replay())
            .isEqualTo(
                SpaceRetrieveReplayResponse.Replay.builder()
                    .url("url")
                    .status("status")
                    .streamType("streamType")
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val spaceRetrieveReplayResponse =
            SpaceRetrieveReplayResponse.builder()
                .replay(
                    SpaceRetrieveReplayResponse.Replay.builder()
                        .url("url")
                        .status("status")
                        .streamType("streamType")
                        .build()
                )
                .build()

        val roundtrippedSpaceRetrieveReplayResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(spaceRetrieveReplayResponse),
                jacksonTypeRef<SpaceRetrieveReplayResponse>(),
            )

        assertThat(roundtrippedSpaceRetrieveReplayResponse).isEqualTo(spaceRetrieveReplayResponse)
    }
}
