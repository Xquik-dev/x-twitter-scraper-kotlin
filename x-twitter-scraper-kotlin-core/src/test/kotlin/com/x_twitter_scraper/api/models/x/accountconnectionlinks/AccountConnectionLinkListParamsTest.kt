// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.accountconnectionlinks

import com.x_twitter_scraper.api.core.http.QueryParams
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AccountConnectionLinkListParamsTest {

    @Test
    fun create() {
        val params = AccountConnectionLinkListParams.none()

        assertThat(params.limit()).isNull()
        assertThat(params.cursor()).isNull()
        assertThat(params._queryParams()).isEqualTo(QueryParams.builder().build())
    }

    @Test
    fun queryParams() {
        val params = AccountConnectionLinkListParams.builder().limit(20L).cursor("abc123").build()

        assertThat(params.limit()).isEqualTo(20L)
        assertThat(params.cursor()).isEqualTo("abc123")
        assertThat(params._queryParams())
            .isEqualTo(QueryParams.builder().put("cursor", "abc123").put("limit", "20").build())
        assertThat(params.toBuilder().build()).isEqualTo(params)
        assertThat(params.toBuilder().build().hashCode()).isEqualTo(params.hashCode())
        assertThat(params.toString()).contains("limit=20", "cursor=abc123")
    }

    @Test
    fun nullClearsTheQueryParams() {
        val params =
            AccountConnectionLinkListParams.builder()
                .limit(20L)
                .cursor("abc123")
                .limit(null)
                .cursor(null)
                .build()

        assertThat(params).isEqualTo(AccountConnectionLinkListParams.none())
    }
}
