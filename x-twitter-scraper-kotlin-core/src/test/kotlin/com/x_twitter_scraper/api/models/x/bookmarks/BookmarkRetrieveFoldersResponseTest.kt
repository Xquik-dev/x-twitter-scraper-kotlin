// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.bookmarks

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.JsonMissing
import com.x_twitter_scraper.api.core.jsonMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BookmarkRetrieveFoldersResponseTest {

    @Test
    fun create() {
        val bookmarkRetrieveFoldersResponse =
            BookmarkRetrieveFoldersResponse.builder()
                .addFolder(
                    BookmarkRetrieveFoldersResponse.Folder.builder()
                        .id("id")
                        .name("name")
                        .media(
                            BookmarkRetrieveFoldersResponse.Folder.Media.builder()
                                .id("id")
                                .mediaId("mediaId")
                                .mediaKey("mediaKey")
                                .originalImageHeight(0L)
                                .originalImageUrl("originalImageUrl")
                                .originalImageWidth(0L)
                                .addPalette(
                                    BookmarkRetrieveFoldersResponse.Folder.Media.Palette.builder()
                                        .percentage(0.0)
                                        .rgb(
                                            BookmarkRetrieveFoldersResponse.Folder.Media.Palette.Rgb
                                                .builder()
                                                .blue(0.0)
                                                .green(0.0)
                                                .red(0.0)
                                                .build()
                                        )
                                        .build()
                                )
                                .type("type")
                                .build()
                        )
                        .build()
                )
                .hasNextPage(true)
                .nextCursor("next_cursor")
                .build()

        assertThat(bookmarkRetrieveFoldersResponse.folders())
            .containsExactly(
                BookmarkRetrieveFoldersResponse.Folder.builder()
                    .id("id")
                    .name("name")
                    .media(
                        BookmarkRetrieveFoldersResponse.Folder.Media.builder()
                            .id("id")
                            .mediaId("mediaId")
                            .mediaKey("mediaKey")
                            .originalImageHeight(0L)
                            .originalImageUrl("originalImageUrl")
                            .originalImageWidth(0L)
                            .addPalette(
                                BookmarkRetrieveFoldersResponse.Folder.Media.Palette.builder()
                                    .percentage(0.0)
                                    .rgb(
                                        BookmarkRetrieveFoldersResponse.Folder.Media.Palette.Rgb
                                            .builder()
                                            .blue(0.0)
                                            .green(0.0)
                                            .red(0.0)
                                            .build()
                                    )
                                    .build()
                            )
                            .type("type")
                            .build()
                    )
                    .build()
            )
        assertThat(bookmarkRetrieveFoldersResponse.hasNextPage()).isEqualTo(true)
        assertThat(bookmarkRetrieveFoldersResponse.nextCursor()).isEqualTo("next_cursor")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val bookmarkRetrieveFoldersResponse =
            BookmarkRetrieveFoldersResponse.builder()
                .addFolder(
                    BookmarkRetrieveFoldersResponse.Folder.builder()
                        .id("id")
                        .name("name")
                        .media(
                            BookmarkRetrieveFoldersResponse.Folder.Media.builder()
                                .id("id")
                                .mediaId("mediaId")
                                .mediaKey("mediaKey")
                                .originalImageHeight(0L)
                                .originalImageUrl("originalImageUrl")
                                .originalImageWidth(0L)
                                .addPalette(
                                    BookmarkRetrieveFoldersResponse.Folder.Media.Palette.builder()
                                        .percentage(0.0)
                                        .rgb(
                                            BookmarkRetrieveFoldersResponse.Folder.Media.Palette.Rgb
                                                .builder()
                                                .blue(0.0)
                                                .green(0.0)
                                                .red(0.0)
                                                .build()
                                        )
                                        .build()
                                )
                                .type("type")
                                .build()
                        )
                        .build()
                )
                .hasNextPage(true)
                .nextCursor("next_cursor")
                .build()

        val roundtrippedBookmarkRetrieveFoldersResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(bookmarkRetrieveFoldersResponse),
                jacksonTypeRef<BookmarkRetrieveFoldersResponse>(),
            )

        assertThat(roundtrippedBookmarkRetrieveFoldersResponse)
            .isEqualTo(bookmarkRetrieveFoldersResponse)
    }

    @Test
    fun readsAdditionalFields() {
        val jsonMapper = jsonMapper()

        val folder0 =
            jsonMapper.readValue(
                """{"media":{}}""",
                jacksonTypeRef<BookmarkRetrieveFoldersResponse.Folder>(),
            )
        assertThat(folder0.media()).isNotNull()
        assertThat(jsonMapper.readTree(jsonMapper.writeValueAsString(folder0)))
            .isEqualTo(jsonMapper.readTree("""{"media":{}}"""))
    }

    @Test
    fun keepsAdditionalFieldsOptional() {
        val folder0 =
            BookmarkRetrieveFoldersResponse.Folder.builder()
                .id(JsonMissing.of())
                .name(JsonMissing.of())
                .build()
        assertThat(folder0.media()).isNull()
    }
}
