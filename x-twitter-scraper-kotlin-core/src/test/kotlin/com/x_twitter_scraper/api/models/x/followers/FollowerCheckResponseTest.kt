// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.followers

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.JsonMissing
import com.x_twitter_scraper.api.core.jsonMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class FollowerCheckResponseTest {

    @Test
    fun create() {
        val followerCheckResponse =
            FollowerCheckResponse.builder()
                .isFollowedBy(true)
                .isFollowing(true)
                .sourceUsername("sourceUsername")
                .targetUsername("targetUsername")
                .sourceId("11348282")
                .targetId("34743251")
                .build()

        assertThat(followerCheckResponse.isFollowedBy()).isEqualTo(true)
        assertThat(followerCheckResponse.isFollowing()).isEqualTo(true)
        assertThat(followerCheckResponse.sourceUsername()).isEqualTo("sourceUsername")
        assertThat(followerCheckResponse.targetUsername()).isEqualTo("targetUsername")
        assertThat(followerCheckResponse.sourceId()).isEqualTo("11348282")
        assertThat(followerCheckResponse.targetId()).isEqualTo("34743251")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val followerCheckResponse =
            FollowerCheckResponse.builder()
                .isFollowedBy(true)
                .isFollowing(true)
                .sourceUsername("sourceUsername")
                .targetUsername("targetUsername")
                .sourceId("11348282")
                .targetId("34743251")
                .build()

        val roundtrippedFollowerCheckResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(followerCheckResponse),
                jacksonTypeRef<FollowerCheckResponse>(),
            )

        assertThat(roundtrippedFollowerCheckResponse).isEqualTo(followerCheckResponse)
    }

    @Test
    fun readsAdditionalFields() {
        val jsonMapper = jsonMapper()

        val followerCheckResponse0 =
            jsonMapper.readValue(
                """{"sourceId":"value","targetId":"value"}""",
                jacksonTypeRef<FollowerCheckResponse>(),
            )
        assertThat(followerCheckResponse0.sourceId()).isNotNull()
        assertThat(followerCheckResponse0.targetId()).isNotNull()
        assertThat(jsonMapper.readTree(jsonMapper.writeValueAsString(followerCheckResponse0)))
            .isEqualTo(jsonMapper.readTree("""{"sourceId":"value","targetId":"value"}"""))
    }

    @Test
    fun keepsAdditionalFieldsOptional() {
        val followerCheckResponse0 =
            FollowerCheckResponse.builder()
                .isFollowedBy(JsonMissing.of())
                .isFollowing(JsonMissing.of())
                .sourceUsername(JsonMissing.of())
                .targetUsername(JsonMissing.of())
                .build()
        assertThat(followerCheckResponse0.sourceId()).isNull()
        assertThat(followerCheckResponse0.targetId()).isNull()
    }
}
