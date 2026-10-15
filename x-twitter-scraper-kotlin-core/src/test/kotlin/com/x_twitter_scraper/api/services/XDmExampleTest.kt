// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.services

import com.github.tomakehurst.wiremock.client.WireMock.delete
import com.github.tomakehurst.wiremock.client.WireMock.deleteRequestedFor
import com.github.tomakehurst.wiremock.client.WireMock.equalTo
import com.github.tomakehurst.wiremock.client.WireMock.okJson
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo
import com.github.tomakehurst.wiremock.client.WireMock.verify
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo
import com.github.tomakehurst.wiremock.junit5.WireMockTest
import com.x_twitter_scraper.api.client.XTwitterScraperClient
import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClient
import com.x_twitter_scraper.api.core.JsonValue
import com.x_twitter_scraper.api.models.x.dm.DmDeleteMessageParams
import com.x_twitter_scraper.api.models.x.dm.DmDeleteMessageResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.ResourceLock

@WireMockTest
@ResourceLock("https://github.com/wiremock/wiremock/issues/169")
internal class XDmExampleTest {

    private lateinit var client: XTwitterScraperClient

    @BeforeEach
    fun beforeEach(wmRuntimeInfo: WireMockRuntimeInfo) {
        client =
            XTwitterScraperOkHttpClient.builder()
                .baseUrl(wmRuntimeInfo.httpBaseUrl)
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .responseValidation(true)
                .build()
    }

    @Test
    suspend fun deleteMessage() {
        stubFor(
            delete(urlPathEqualTo("/x/dm/44196397/messages/1789012345678901234"))
                .willReturn(okJson(ExampleResponses.read("examples/x/dm/deleteMessage.json")))
        )
        val params =
            DmDeleteMessageParams.builder()
                .userId("44196397")
                .messageId("1789012345678901234")
                .account("account")
                .idempotencyKey("Idempotency-Key")
                .build()

        val responses =
            listOf(
                client.x().dm().deleteMessage(params),
                client.async().x().dm().deleteMessage(params),
            )

        verify(
            2,
            deleteRequestedFor(urlPathEqualTo("/x/dm/44196397/messages/1789012345678901234"))
                .withQueryParam("account", equalTo("account"))
                .withHeader("Idempotency-Key", equalTo("Idempotency-Key")),
        )
        responses.forEach { response ->
            assertThat(response.id()).isEqualTo("12345")
            assertThat(response.account()!!.id()).isEqualTo("42")
            assertThat(response.account()!!.username()).isEqualTo("example")
            assertThat(response.action()).isEqualTo(DmDeleteMessageResponse.Action.LIKE)
            assertThat(response.billing().charged()).isEqualTo(true)
            assertThat(response.billing().chargedCredits()).isEqualTo("10")
            assertThat(response.billing().plannedCredits()).isEqualTo("10")
            assertThat(response.billing().status())
                .isEqualTo(DmDeleteMessageResponse.Billing.Status.CHARGED)
            assertThat(response.charged()).isEqualTo(true)
            assertThat(response.chargedCredits()).isEqualTo("10")
            assertThat(response.nextAction()).isNull()
            assertThat(response._object_()).isEqualTo(JsonValue.from("x_write_action"))
            assertThat(response.pollAfterMs()).isNull()
            assertThat(response.request().hash())
                .isEqualTo("0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef")
            assertThat(response.result()!!.id()).isEqualTo("9876543210")
            assertThat(response.result()!!.state()).isEqualTo("liked")
            assertThat(response.result()!!.type())
                .isEqualTo(DmDeleteMessageResponse.Result.Type.STATE_CHANGE)
            assertThat(response.retryable()).isEqualTo(false)
            assertThat(response.safeToRetry()).isEqualTo(false)
            assertThat(response.sendDispatched()).isEqualTo(true)
            assertThat(response.status()).isEqualTo(DmDeleteMessageResponse.Status.SUCCESS)
            assertThat(response.statusUrl()).isEqualTo("/api/v1/x/write-actions/12345")
            assertThat(response.success()).isEqualTo(true)
            assertThat(response.target()!!.id()).isEqualTo("9876543210")
            assertThat(response.target()!!.type())
                .isEqualTo(DmDeleteMessageResponse.Target.Type.TWEET)
            assertThat(response.targetId()).isEqualTo("9876543210")
            assertThat(response.terminal()).isEqualTo(true)
            assertThat(response.writeActionId()).isEqualTo("12345")
        }
    }
}
