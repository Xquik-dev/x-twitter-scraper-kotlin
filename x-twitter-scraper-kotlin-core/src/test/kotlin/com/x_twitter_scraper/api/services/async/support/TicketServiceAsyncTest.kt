// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.async.support

import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClientAsync
import com.x_twitter_scraper.api.models.support.tickets.TicketCreateParams
import com.x_twitter_scraper.api.models.support.tickets.TicketReplyParams
import com.x_twitter_scraper.api.models.support.tickets.TicketUpdateParams
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

internal class TicketServiceAsyncTest {

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun create() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val ticketServiceAsync = client.support().tickets()

        val ticket =
            ticketServiceAsync.create(
                TicketCreateParams.builder().body("body").subject("subject").build()
            )

        ticket.validate()
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun retrieve() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val ticketServiceAsync = client.support().tickets()

        val ticket = ticketServiceAsync.retrieve("id")

        ticket.validate()
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun update() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val ticketServiceAsync = client.support().tickets()

        val ticket =
            ticketServiceAsync.update(
                TicketUpdateParams.builder().id("id").status(TicketUpdateParams.Status.OPEN).build()
            )

        ticket.validate()
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun list() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val ticketServiceAsync = client.support().tickets()

        val tickets = ticketServiceAsync.list()

        tickets.validate()
    }

    @Disabled("Mock server tests are disabled")
    @Test
    suspend fun reply() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .build()
        val ticketServiceAsync = client.support().tickets()

        val response =
            ticketServiceAsync.reply(TicketReplyParams.builder().id("id").body("body").build())

        response.validate()
    }
}
