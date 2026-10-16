// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.services.async.x

import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClientAsync
import com.x_twitter_scraper.api.models.x.communitynotes.CommunityNoteListContributorDeletedNotesParams
import com.x_twitter_scraper.api.models.x.communitynotes.CommunityNoteListContributorNotesParams
import com.x_twitter_scraper.api.models.x.communitynotes.CommunityNoteRetrieveContributorParams
import org.junit.jupiter.api.Test

internal class CommunityNoteServiceAsyncTest {

    @Test
    suspend fun retrieveContributor() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val communityNoteServiceAsync = client.x().communityNotes()

        val response =
            communityNoteServiceAsync.retrieveContributor(
                CommunityNoteRetrieveContributorParams.builder()
                    .alias("ardent-beach-friarbird")
                    .fields("id,text,author.username")
                    .omit("entities,media")
                    .build()
            )

        kotlin.test.assertNotNull(response)
    }

    @Test
    suspend fun listContributorNotes() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val communityNoteServiceAsync = client.x().communityNotes()

        val response =
            communityNoteServiceAsync.listContributorNotes(
                CommunityNoteListContributorNotesParams.builder()
                    .alias("ardent-beach-friarbird")
                    .cursor("cursor")
                    .fields("id,text,author.username")
                    .omit("entities,media")
                    .pageSize(1L)
                    .build()
            )

        kotlin.test.assertNotNull(response)
    }

    @Test
    suspend fun listContributorDeletedNotes() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val communityNoteServiceAsync = client.x().communityNotes()

        val response =
            communityNoteServiceAsync.listContributorDeletedNotes(
                CommunityNoteListContributorDeletedNotesParams.builder()
                    .alias("ardent-beach-friarbird")
                    .cursor("cursor")
                    .fields("id,text,author.username")
                    .omit("entities,media")
                    .pageSize(1L)
                    .build()
            )

        kotlin.test.assertNotNull(response)
    }
}
