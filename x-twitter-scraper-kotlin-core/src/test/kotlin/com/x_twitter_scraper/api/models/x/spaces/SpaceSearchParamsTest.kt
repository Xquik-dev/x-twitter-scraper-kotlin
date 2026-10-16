// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.spaces

import com.x_twitter_scraper.api.core.http.QueryParams
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SpaceSearchParamsTest {

    @Test
    fun create() {
        SpaceSearchParams.builder()
            .q("from:solana")
            .cursor("cursor")
            .fields("id,text,author.username")
            .omit("entities,media")
            .pageSize(1L)
            .queryType(SpaceSearchParams.QueryType.LATEST)
            .build()
    }

    @Test
    fun queryParams() {
        val params =
            SpaceSearchParams.builder()
                .q("from:solana")
                .cursor("cursor")
                .fields("id,text,author.username")
                .omit("entities,media")
                .pageSize(1L)
                .queryType(SpaceSearchParams.QueryType.LATEST)
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams)
            .isEqualTo(
                QueryParams.builder()
                    .put("q", "from:solana")
                    .put("cursor", "cursor")
                    .put("fields", "id,text,author.username")
                    .put("omit", "entities,media")
                    .put("pageSize", "1")
                    .put("queryType", "Latest")
                    .build()
            )
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params = SpaceSearchParams.builder().q("from:solana").build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().put("q", "from:solana").build())
    }
}
