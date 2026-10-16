// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.communities

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import com.x_twitter_scraper.api.models.CommunityTopic
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CommunityListTopicsResponseTest {

    @Test
    fun create() {
        val communityListTopicsResponse =
            CommunityListTopicsResponse.builder()
                .count(0L)
                .addTopic(
                    CommunityTopic.builder()
                        .id("id")
                        .name("name")
                        .subtopics(
                            listOf(CommunityTopic.Subtopic.builder().id("id").name("name").build())
                        )
                        .build()
                )
                .message("message")
                .build()

        assertThat(communityListTopicsResponse.count()).isEqualTo(0L)
        assertThat(communityListTopicsResponse.topics())
            .containsExactly(
                CommunityTopic.builder()
                    .id("id")
                    .name("name")
                    .subtopics(
                        listOf(CommunityTopic.Subtopic.builder().id("id").name("name").build())
                    )
                    .build()
            )
        assertThat(communityListTopicsResponse.message()).isEqualTo("message")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val communityListTopicsResponse =
            CommunityListTopicsResponse.builder()
                .count(0L)
                .addTopic(
                    CommunityTopic.builder()
                        .id("id")
                        .name("name")
                        .subtopics(
                            listOf(CommunityTopic.Subtopic.builder().id("id").name("name").build())
                        )
                        .build()
                )
                .message("message")
                .build()

        val roundtrippedCommunityListTopicsResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(communityListTopicsResponse),
                jacksonTypeRef<CommunityListTopicsResponse>(),
            )

        assertThat(roundtrippedCommunityListTopicsResponse).isEqualTo(communityListTopicsResponse)
    }
}
