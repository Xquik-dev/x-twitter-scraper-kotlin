// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.services.async.x.users

import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClientAsync
import com.x_twitter_scraper.api.models.x.users.mute.MuteCreateParams
import com.x_twitter_scraper.api.models.x.users.mute.MuteDeleteParams
import org.junit.jupiter.api.Test

internal class MuteServiceAsyncTest {

    @Test
    suspend fun create() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val muteServiceAsync = client.x().users().mute()

        val mute =
            muteServiceAsync.create(
                MuteCreateParams.builder()
                    .id("id")
                    .idempotencyKey("Idempotency-Key")
                    .account("@elonmusk")
                    .build()
            )

        kotlin.test.assertNotNull(mute)
    }

    @Test
    suspend fun delete() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val muteServiceAsync = client.x().users().mute()

        val mute =
            muteServiceAsync.delete(
                MuteDeleteParams.builder()
                    .id("id")
                    .idempotencyKey("Idempotency-Key")
                    .account("@elonmusk")
                    .build()
            )

        kotlin.test.assertNotNull(mute)
    }
}
