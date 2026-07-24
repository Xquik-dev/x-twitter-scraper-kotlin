// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async.support

import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClientAsync
import com.x_twitter_scraper.api.models.support.tickets.TicketCreateParams
import com.x_twitter_scraper.api.models.support.tickets.TicketReplyParams
import com.x_twitter_scraper.api.models.support.tickets.TicketUpdateParams
import org.junit.jupiter.api.Test

internal class TicketServiceAsyncTest {

    @Test
    suspend fun create() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val ticketServiceAsync = client.support().tickets()

        val ticket =
            ticketServiceAsync.create(
                TicketCreateParams.builder()
                    .idempotencyKey("Idempotency-Key")
                    .body("I am unable to connect my X account. Please help.")
                    .subject("Cannot connect X account")
                    .build()
            )

        kotlin.test.assertNotNull(ticket)
    }

    @Test
    suspend fun retrieve() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val ticketServiceAsync = client.support().tickets()

        val ticket = ticketServiceAsync.retrieve("tkt_a1b2c3d4e5f6a1b2c3d4e5f6")

        kotlin.test.assertNotNull(ticket)
    }

    @Test
    suspend fun update() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val ticketServiceAsync = client.support().tickets()

        val ticket =
            ticketServiceAsync.update(
                TicketUpdateParams.builder()
                    .id("tkt_a1b2c3d4e5f6a1b2c3d4e5f6")
                    .status(TicketUpdateParams.Status.RESOLVED)
                    .build()
            )

        kotlin.test.assertNotNull(ticket)
    }

    @Test
    suspend fun list() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val ticketServiceAsync = client.support().tickets()

        val tickets = ticketServiceAsync.list()

        kotlin.test.assertNotNull(tickets)
    }

    @Test
    suspend fun reply() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(com.x_twitter_scraper.api.LoopbackTestServer.baseUrl())
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val ticketServiceAsync = client.support().tickets()

        val response =
            ticketServiceAsync.reply(
                TicketReplyParams.builder()
                    .id("tkt_a1b2c3d4e5f6a1b2c3d4e5f6")
                    .idempotencyKey("Idempotency-Key")
                    .body("Thank you for the update.")
                    .build()
            )

        kotlin.test.assertNotNull(response)
    }
}
