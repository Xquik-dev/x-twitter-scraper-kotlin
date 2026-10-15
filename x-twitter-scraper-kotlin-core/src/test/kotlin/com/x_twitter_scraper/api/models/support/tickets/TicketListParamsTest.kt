// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.support.tickets

import com.x_twitter_scraper.api.core.http.QueryParams
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TicketListParamsTest {

    @Test
    fun create() {
        TicketListParams.builder().cursor("cursor").limit(1L).build()
    }

    @Test
    fun queryParams() {
        val params = TicketListParams.builder().cursor("cursor").limit(1L).build()

        val queryParams = params._queryParams()

        assertThat(queryParams)
            .isEqualTo(QueryParams.builder().put("cursor", "cursor").put("limit", "1").build())
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params = TicketListParams.builder().build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().build())
    }
}
