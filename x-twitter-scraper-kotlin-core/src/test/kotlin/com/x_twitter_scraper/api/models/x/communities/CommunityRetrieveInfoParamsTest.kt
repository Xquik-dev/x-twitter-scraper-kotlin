// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.communities

import com.x_twitter_scraper.api.core.http.QueryParams
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CommunityRetrieveInfoParamsTest {

    @Test
    fun create() {
        CommunityRetrieveInfoParams.builder()
            .id("1500000000000000000")
            .fields("id,text,author.username")
            .omit("entities,media")
            .build()
    }

    @Test
    fun pathParams() {
        val params = CommunityRetrieveInfoParams.builder().id("1500000000000000000").build()

        assertThat(params._pathParam(0)).isEqualTo("1500000000000000000")
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun queryParams() {
        val params =
            CommunityRetrieveInfoParams.builder()
                .id("1500000000000000000")
                .fields("id,text,author.username")
                .omit("entities,media")
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams)
            .isEqualTo(
                QueryParams.builder()
                    .put("fields", "id,text,author.username")
                    .put("omit", "entities,media")
                    .build()
            )
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params = CommunityRetrieveInfoParams.builder().id("1500000000000000000").build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().build())
    }
}
