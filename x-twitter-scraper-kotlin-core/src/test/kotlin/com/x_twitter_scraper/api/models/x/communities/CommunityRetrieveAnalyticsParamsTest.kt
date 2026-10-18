// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.communities

import com.x_twitter_scraper.api.core.http.QueryParams
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CommunityRetrieveAnalyticsParamsTest {

    @Test
    fun create() {
        CommunityRetrieveAnalyticsParams.builder()
            .id("1493446837214187523")
            .fields("id,periodEndsAt,metrics")
            .omit("metrics.likes.changePercent")
            .build()
    }

    @Test
    fun pathParams() {
        val params = CommunityRetrieveAnalyticsParams.builder().id("1493446837214187523").build()

        assertThat(params._pathParam(0)).isEqualTo("1493446837214187523")
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun queryParams() {
        val params =
            CommunityRetrieveAnalyticsParams.builder()
                .id("1493446837214187523")
                .fields("id,periodEndsAt,metrics")
                .omit("metrics.likes.changePercent")
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams)
            .isEqualTo(
                QueryParams.builder()
                    .put("fields", "id,periodEndsAt,metrics")
                    .put("omit", "metrics.likes.changePercent")
                    .build()
            )
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params = CommunityRetrieveAnalyticsParams.builder().id("1493446837214187523").build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().build())
    }
}
