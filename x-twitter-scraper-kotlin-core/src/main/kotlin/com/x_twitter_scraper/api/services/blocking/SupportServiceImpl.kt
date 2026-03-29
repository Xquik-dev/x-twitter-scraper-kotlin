// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.blocking

import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.services.blocking.support.TicketService
import com.x_twitter_scraper.api.services.blocking.support.TicketServiceImpl

class SupportServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    SupportService {

    private val withRawResponse: SupportService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val tickets: TicketService by lazy { TicketServiceImpl(clientOptions) }

    override fun withRawResponse(): SupportService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: (ClientOptions.Builder) -> Unit): SupportService =
        SupportServiceImpl(clientOptions.toBuilder().apply(modifier).build())

    /** Support ticket management */
    override fun tickets(): TicketService = tickets

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        SupportService.WithRawResponse {

        private val tickets: TicketService.WithRawResponse by lazy {
            TicketServiceImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: (ClientOptions.Builder) -> Unit
        ): SupportService.WithRawResponse =
            SupportServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier).build()
            )

        /** Support ticket management */
        override fun tickets(): TicketService.WithRawResponse = tickets
    }
}
