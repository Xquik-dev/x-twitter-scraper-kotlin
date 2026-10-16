// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.communitynotes

import com.x_twitter_scraper.api.core.http.QueryParams
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CommunityNoteListContributorDeletedNotesParamsTest {

    @Test
    fun create() {
        CommunityNoteListContributorDeletedNotesParams.builder()
            .alias("ardent-beach-friarbird")
            .cursor("cursor")
            .fields("id,text,author.username")
            .omit("entities,media")
            .pageSize(1L)
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            CommunityNoteListContributorDeletedNotesParams.builder()
                .alias("ardent-beach-friarbird")
                .build()

        assertThat(params._pathParam(0)).isEqualTo("ardent-beach-friarbird")
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun queryParams() {
        val params =
            CommunityNoteListContributorDeletedNotesParams.builder()
                .alias("ardent-beach-friarbird")
                .cursor("cursor")
                .fields("id,text,author.username")
                .omit("entities,media")
                .pageSize(1L)
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams)
            .isEqualTo(
                QueryParams.builder()
                    .put("cursor", "cursor")
                    .put("fields", "id,text,author.username")
                    .put("omit", "entities,media")
                    .put("pageSize", "1")
                    .build()
            )
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params =
            CommunityNoteListContributorDeletedNotesParams.builder()
                .alias("ardent-beach-friarbird")
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().build())
    }
}
