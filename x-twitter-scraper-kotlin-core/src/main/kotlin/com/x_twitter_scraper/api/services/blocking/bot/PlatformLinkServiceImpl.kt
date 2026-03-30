// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.services.blocking.bot

import com.x_twitter_scraper.api.core.ClientOptions

class PlatformLinkServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    PlatformLinkService {

    private val withRawResponse: PlatformLinkService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): PlatformLinkService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: (ClientOptions.Builder) -> Unit): PlatformLinkService =
        PlatformLinkServiceImpl(clientOptions.toBuilder().apply(modifier).build())

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        PlatformLinkService.WithRawResponse {

        override fun withOptions(
            modifier: (ClientOptions.Builder) -> Unit
        ): PlatformLinkService.WithRawResponse =
            PlatformLinkServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier).build()
            )
    }
}
