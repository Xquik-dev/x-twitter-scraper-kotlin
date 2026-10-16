// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.communitynotes

import com.x_twitter_scraper.api.core.http.QueryParams
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CommunityNoteRetrieveContributorParamsTest {

    @Test
    fun create() {
        CommunityNoteRetrieveContributorParams.builder()
            .alias("ardent-beach-friarbird")
            .fields("id,text,author.username")
            .omit("entities,media")
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            CommunityNoteRetrieveContributorParams.builder().alias("ardent-beach-friarbird").build()

        assertThat(params._pathParam(0)).isEqualTo("ardent-beach-friarbird")
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun queryParams() {
        val params =
            CommunityNoteRetrieveContributorParams.builder()
                .alias("ardent-beach-friarbird")
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
        val params =
            CommunityNoteRetrieveContributorParams.builder().alias("ardent-beach-friarbird").build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().build())
    }
}
