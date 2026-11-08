// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.users

import com.x_twitter_scraper.api.core.http.QueryParams
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserRetrieveFollowerAuditParamsTest {

    @Test
    fun create() {
        UserRetrieveFollowerAuditParams.builder()
            .id("id")
            .fields("id,text,author.username")
            .limit(1L)
            .omit("entities,media")
            .build()
    }

    @Test
    fun pathParams() {
        val params = UserRetrieveFollowerAuditParams.builder().id("NASA").build()

        assertThat(params._pathParam(0)).isEqualTo("NASA")
        assertThat(params._pathParam(1)).isEqualTo("")
        assertThat(UserRetrieveFollowerAuditParams.none()._pathParam(0)).isEqualTo("")
    }

    @Test
    fun queryParams() {
        val params =
            UserRetrieveFollowerAuditParams.builder()
                .id("NASA")
                .fields("audit.coverage")
                .limit(500L)
                .omit("audit.followers")
                .putAdditionalQueryParam("source", "sdk")
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams)
            .isEqualTo(
                QueryParams.builder()
                    .put("fields", "audit.coverage")
                    .put("limit", "500")
                    .put("omit", "audit.followers")
                    .put("source", "sdk")
                    .build()
            )
        assertThat(params.limit()).isEqualTo(500L)
        assertThat(params.toBuilder().build()).isEqualTo(params)
        assertThat(params.toBuilder().build().hashCode()).isEqualTo(params.hashCode())
        assertThat(params.toBuilder().limit(null).build()).isNotEqualTo(params)
        assertThat(params.toString()).contains("limit=500")
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params = UserRetrieveFollowerAuditParams.builder().id("NASA").build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().build())
        assertThat(params.limit()).isNull()
    }
}
