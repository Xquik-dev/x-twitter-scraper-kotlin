// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async.x

import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClientAsync
import com.x_twitter_scraper.api.models.x.dm.DmRetrieveHistoryParams
import com.x_twitter_scraper.api.models.x.dm.DmUpdateParams
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

internal class DmServiceAsyncTest {

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun update() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val dmServiceAsync = client.x().dm()

        val dm =
            dmServiceAsync.update(
                DmUpdateParams.builder()
                    .userId("userId")
                    .account("account")
                    .text("text")
                    .addMediaId("string")
                    .replyToMessageId("reply_to_message_id")
                    .build()
            )

        dm.validate()
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun retrieveHistory() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val dmServiceAsync = client.x().dm()

        val response =
            dmServiceAsync.retrieveHistory(
                DmRetrieveHistoryParams.builder()
                    .userId("userId")
                    .cursor("cursor")
                    .maxId("maxId")
                    .build()
            )

        response.validate()
    }
}
