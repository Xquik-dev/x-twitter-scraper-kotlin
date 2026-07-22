// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async

import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClientAsync
import com.x_twitter_scraper.api.models.compose.ComposeCreateParams
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

internal class ComposeServiceAsyncTest {

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun create() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val composeServiceAsync = client.compose()

        val compose =
            composeServiceAsync.create(
                ComposeCreateParams.Body.ComposePrepareRequest.builder()
                    .topic("PostgreSQL query planning")
                    .goal(ComposeCreateParams.Body.ComposePrepareRequest.Goal.ENGAGEMENT)
                    .styleUsername("x")
                    .build()
            )

        compose.validate()
    }
}
