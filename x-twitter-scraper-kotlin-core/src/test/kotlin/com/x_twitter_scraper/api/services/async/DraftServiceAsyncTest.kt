// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async

import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClientAsync
import com.x_twitter_scraper.api.models.drafts.DraftCreateParams
import com.x_twitter_scraper.api.models.drafts.DraftListParams
import org.junit.jupiter.api.Test

internal class DraftServiceAsyncTest {

    @Test
    suspend fun create() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val draftServiceAsync = client.drafts()

        val draftDetail =
            draftServiceAsync.create(
                DraftCreateParams.builder()
                    .text("AI is the future of productivity")
                    .goal(DraftCreateParams.Goal.ENGAGEMENT)
                    .topic("AI trends")
                    .build()
            )

        kotlin.test.assertNotNull(draftDetail)
    }

    @Test
    suspend fun retrieve() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val draftServiceAsync = client.drafts()

        val draftDetail = draftServiceAsync.retrieve("id")

        kotlin.test.assertNotNull(draftDetail)
    }

    @Test
    suspend fun list() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val draftServiceAsync = client.drafts()

        val drafts =
            draftServiceAsync.list(
                DraftListParams.builder().afterCursor("afterCursor").limit(1L).build()
            )

        kotlin.test.assertNotNull(drafts)
    }

    @Test
    suspend fun delete() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val draftServiceAsync = client.drafts()

        draftServiceAsync.delete("id")
    }
}
