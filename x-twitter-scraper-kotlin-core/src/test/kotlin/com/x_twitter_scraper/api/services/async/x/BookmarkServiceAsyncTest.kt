// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async.x

import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClientAsync
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

internal class BookmarkServiceAsyncTest {

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun list() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val bookmarkServiceAsync = client.x().bookmarks()

        val page = bookmarkServiceAsync.list()

        page.response().validate()
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun retrieveFolders() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val bookmarkServiceAsync = client.x().bookmarks()

        val response = bookmarkServiceAsync.retrieveFolders()

        response.validate()
    }
}
