// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.communities

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import com.x_twitter_scraper.api.models.Community
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CommunityListSuggestedResponseTest {

    @Test
    fun create() {
        val communityListSuggestedResponse =
            CommunityListSuggestedResponse.builder()
                .addCommunity(
                    Community.builder().id("id").isNsfw(true).memberCount(0L).name("name").build()
                )
                .count(0L)
                .message("message")
                .build()

        assertThat(communityListSuggestedResponse.communities())
            .containsExactly(
                Community.builder().id("id").isNsfw(true).memberCount(0L).name("name").build()
            )
        assertThat(communityListSuggestedResponse.count()).isEqualTo(0L)
        assertThat(communityListSuggestedResponse.message()).isEqualTo("message")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val communityListSuggestedResponse =
            CommunityListSuggestedResponse.builder()
                .addCommunity(
                    Community.builder().id("id").isNsfw(true).memberCount(0L).name("name").build()
                )
                .count(0L)
                .message("message")
                .build()

        val roundtrippedCommunityListSuggestedResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(communityListSuggestedResponse),
                jacksonTypeRef<CommunityListSuggestedResponse>(),
            )

        assertThat(roundtrippedCommunityListSuggestedResponse)
            .isEqualTo(communityListSuggestedResponse)
    }
}
