// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.JsonValue
import com.x_twitter_scraper.api.core.jsonMapper
import com.x_twitter_scraper.api.errors.XTwitterScraperInvalidDataException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

internal class CommunityAnalyticsMetricTest {

    @Test
    fun create() {
        val communityAnalyticsMetric =
            CommunityAnalyticsMetric.builder()
                .count(9007199254740991L)
                .changePercent(-1.2076202776880853)
                .build()

        assertThat(communityAnalyticsMetric.count()).isEqualTo(9007199254740991L)
        assertThat(communityAnalyticsMetric.changePercent()).isEqualTo(-1.2076202776880853)
        assertThat(communityAnalyticsMetric.validate()).isSameAs(communityAnalyticsMetric)
        assertThat(communityAnalyticsMetric.isValid()).isTrue()
        assertThat(communityAnalyticsMetric.toBuilder().build()).isEqualTo(communityAnalyticsMetric)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val communityAnalyticsMetric =
            CommunityAnalyticsMetric.builder()
                .count(0L)
                .changePercent(0.5907837731390311)
                .putAdditionalProperty("extra", JsonValue.from("value"))
                .build()

        val roundtrippedCommunityAnalyticsMetric =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(communityAnalyticsMetric),
                jacksonTypeRef<CommunityAnalyticsMetric>(),
            )

        assertThat(roundtrippedCommunityAnalyticsMetric).isEqualTo(communityAnalyticsMetric)
        assertThat(roundtrippedCommunityAnalyticsMetric.hashCode())
            .isEqualTo(communityAnalyticsMetric.hashCode())
    }

    @Test
    fun requiredFields() {
        assertThatThrownBy { CommunityAnalyticsMetric.builder().count(0L).build() }
            .isInstanceOf(IllegalStateException::class.java)
            .hasMessageContaining("changePercent")
        assertThatThrownBy { CommunityAnalyticsMetric.builder().changePercent(0.0).build() }
            .isInstanceOf(IllegalStateException::class.java)
            .hasMessageContaining("count")
    }

    @Test
    fun validateInvalidCount() {
        val communityAnalyticsMetric =
            CommunityAnalyticsMetric.builder()
                .count(JsonValue.from("invalid"))
                .changePercent(0.0)
                .build()

        assertThat(communityAnalyticsMetric.isValid()).isFalse()
        assertThatThrownBy { communityAnalyticsMetric.validate() }
            .isInstanceOf(XTwitterScraperInvalidDataException::class.java)
            .hasMessageContaining("count")
    }
}
