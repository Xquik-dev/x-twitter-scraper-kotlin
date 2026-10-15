// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.bookmarks

import com.x_twitter_scraper.api.core.http.QueryParams
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BookmarkRetrieveFoldersParamsTest {

    @Test
    fun create() {
        BookmarkRetrieveFoldersParams.builder().cursor("cursor").build()
    }

    @Test
    fun queryParams() {
        val params = BookmarkRetrieveFoldersParams.builder().cursor("cursor").build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().put("cursor", "cursor").build())
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params = BookmarkRetrieveFoldersParams.builder().build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().build())
    }
}
