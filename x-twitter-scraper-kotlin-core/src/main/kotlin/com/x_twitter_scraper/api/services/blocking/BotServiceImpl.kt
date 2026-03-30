// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.blocking

import com.x_twitter_scraper.api.core.ClientOptions
import com.x_twitter_scraper.api.services.blocking.bot.PlatformLinkService
import com.x_twitter_scraper.api.services.blocking.bot.PlatformLinkServiceImpl

class BotServiceImpl internal constructor(private val clientOptions: ClientOptions) : BotService {

    private val withRawResponse: BotService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val platformLinks: PlatformLinkService by lazy {
        PlatformLinkServiceImpl(clientOptions)
    }

    override fun withRawResponse(): BotService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: (ClientOptions.Builder) -> Unit): BotService =
        BotServiceImpl(clientOptions.toBuilder().apply(modifier).build())

    override fun platformLinks(): PlatformLinkService = platformLinks

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        BotService.WithRawResponse {

        private val platformLinks: PlatformLinkService.WithRawResponse by lazy {
            PlatformLinkServiceImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: (ClientOptions.Builder) -> Unit
        ): BotService.WithRawResponse =
            BotServiceImpl.WithRawResponseImpl(clientOptions.toBuilder().apply(modifier).build())

        override fun platformLinks(): PlatformLinkService.WithRawResponse = platformLinks
    }
}
