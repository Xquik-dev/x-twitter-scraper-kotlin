// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.JsonValue
import com.x_twitter_scraper.api.core.jsonMapper
import com.x_twitter_scraper.api.errors.XTwitterScraperInvalidDataException
import com.x_twitter_scraper.api.services.ExampleResponses
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

internal class CommunityAnalyticsTest {

    @Test
    fun create() {
        val likes = CommunityAnalyticsMetric.builder().count(15298L).changePercent(-1.2).build()
        val newMembers = CommunityAnalyticsMetric.builder().count(557L).changePercent(-1.5).build()
        val posts = CommunityAnalyticsMetric.builder().count(5108L).changePercent(0.5).build()
        val replies = CommunityAnalyticsMetric.builder().count(6851L).changePercent(-0.8).build()
        val uniquePosters =
            CommunityAnalyticsMetric.builder().count(1800L).changePercent(-0.3).build()
        val metrics =
            CommunityAnalytics.Metrics.builder()
                .likes(likes)
                .newMembers(newMembers)
                .posts(posts)
                .replies(replies)
                .uniquePosters(uniquePosters)
                .build()
        val communityAnalytics =
            CommunityAnalytics.builder()
                .id("1493446837214187523")
                .metrics(metrics)
                .periodEndsAt(OffsetDateTime.parse("2026-10-16T23:59:59.999Z"))
                .build()

        assertThat(communityAnalytics.id()).isEqualTo("1493446837214187523")
        assertThat(communityAnalytics.periodEndsAt())
            .isEqualTo(OffsetDateTime.parse("2026-10-16T23:59:59.999Z"))
        assertThat(communityAnalytics.metrics()).isEqualTo(metrics)
        assertThat(metrics.likes()).isEqualTo(likes)
        assertThat(metrics.newMembers()).isEqualTo(newMembers)
        assertThat(metrics.posts()).isEqualTo(posts)
        assertThat(metrics.replies()).isEqualTo(replies)
        assertThat(metrics.uniquePosters()).isEqualTo(uniquePosters)
        assertThat(communityAnalytics.validate()).isSameAs(communityAnalytics)
        assertThat(communityAnalytics.isValid()).isTrue()
        assertThat(communityAnalytics.toBuilder().build()).isEqualTo(communityAnalytics)
        assertThat(metrics.toBuilder().build()).isEqualTo(metrics)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val communityAnalytics =
            jsonMapper.readValue(
                ExampleResponses.read("examples/x/communities/retrieveAnalytics.json"),
                jacksonTypeRef<CommunityAnalytics>(),
            )
        val withAdditionalProperties =
            communityAnalytics
                .toBuilder()
                .putAdditionalProperty("extra", JsonValue.from("value"))
                .metrics(
                    communityAnalytics
                        .metrics()
                        .toBuilder()
                        .putAdditionalProperty("extra", JsonValue.from("metrics"))
                        .build()
                )
                .build()

        val roundtrippedCommunityAnalytics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(withAdditionalProperties),
                jacksonTypeRef<CommunityAnalytics>(),
            )

        assertThat(roundtrippedCommunityAnalytics).isEqualTo(withAdditionalProperties)
        assertThat(roundtrippedCommunityAnalytics.hashCode())
            .isEqualTo(withAdditionalProperties.hashCode())
        assertThat(roundtrippedCommunityAnalytics.metrics().hashCode())
            .isEqualTo(withAdditionalProperties.metrics().hashCode())
    }

    @Test
    fun requiredFields() {
        assertThatThrownBy { CommunityAnalytics.builder().build() }
            .isInstanceOf(IllegalStateException::class.java)
            .hasMessageContaining("id")
        assertThatThrownBy { CommunityAnalytics.Metrics.builder().build() }
            .isInstanceOf(IllegalStateException::class.java)
            .hasMessageContaining("likes")
    }

    @Test
    fun validateMissingMetric() {
        val communityAnalytics =
            jsonMapper()
                .readValue(
                    """{"id":"1493446837214187523","periodEndsAt":"2026-10-16T23:59:59.999Z","metrics":{}}""",
                    jacksonTypeRef<CommunityAnalytics>(),
                )

        assertThat(communityAnalytics.isValid()).isFalse()
        assertThat(communityAnalytics.metrics().isValid()).isFalse()
        assertThatThrownBy { communityAnalytics.validate() }
            .isInstanceOf(XTwitterScraperInvalidDataException::class.java)
            .hasMessageContaining("likes")
    }
}
