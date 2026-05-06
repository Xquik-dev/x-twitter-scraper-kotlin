// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async.x

import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClientAsync
import com.x_twitter_scraper.api.models.x.dm.DmRetrieveHistoryParams
import com.x_twitter_scraper.api.models.x.dm.DmSendParams
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

internal class DmServiceAsyncTest {

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun retrieveHistory() {
        val client = XTwitterScraperOkHttpClientAsync.builder().apiKey("My API Key").build()
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

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun send() {
        val client = XTwitterScraperOkHttpClientAsync.builder().apiKey("My API Key").build()
        val dmServiceAsync = client.x().dm()

        val response =
            dmServiceAsync.send(
                DmSendParams.builder()
                    .userId("userId")
                    .account("@elonmusk")
                    .text("Example text content")
                    .addMediaId("1234567890123456789")
                    .replyToMessageId("1234567890123456789")
                    .build()
            )

        response.validate()
    }
}
