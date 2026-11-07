// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.services

import com.github.tomakehurst.wiremock.client.WireMock.delete
import com.github.tomakehurst.wiremock.client.WireMock.deleteRequestedFor
import com.github.tomakehurst.wiremock.client.WireMock.equalTo
import com.github.tomakehurst.wiremock.client.WireMock.equalToJson
import com.github.tomakehurst.wiremock.client.WireMock.okJson
import com.github.tomakehurst.wiremock.client.WireMock.post
import com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo
import com.github.tomakehurst.wiremock.client.WireMock.verify
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo
import com.github.tomakehurst.wiremock.junit5.WireMockTest
import com.github.tomakehurst.wiremock.matching.RequestPatternBuilder
import com.x_twitter_scraper.api.client.XTwitterScraperClient
import com.x_twitter_scraper.api.client.XTwitterScraperClientAsync
import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClient
import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClientAsync
import com.x_twitter_scraper.api.models.x.tweets.bookmark.BookmarkCreateParams
import com.x_twitter_scraper.api.models.x.tweets.bookmark.BookmarkCreateResponse
import com.x_twitter_scraper.api.models.x.tweets.bookmark.BookmarkDeleteParams
import com.x_twitter_scraper.api.models.x.tweets.bookmark.BookmarkDeleteResponse
import com.x_twitter_scraper.api.models.x.users.block.BlockCreateParams
import com.x_twitter_scraper.api.models.x.users.block.BlockCreateResponse
import com.x_twitter_scraper.api.models.x.users.block.BlockDeleteParams
import com.x_twitter_scraper.api.models.x.users.block.BlockDeleteResponse
import com.x_twitter_scraper.api.models.x.users.mute.MuteCreateParams
import com.x_twitter_scraper.api.models.x.users.mute.MuteCreateResponse
import com.x_twitter_scraper.api.models.x.users.mute.MuteDeleteParams
import com.x_twitter_scraper.api.models.x.users.mute.MuteDeleteResponse
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.ResourceLock

@WireMockTest
@ResourceLock("https://github.com/wiremock/wiremock/issues/169")
internal class WriteStateServiceTest {

    private lateinit var client: XTwitterScraperClient
    private lateinit var asyncClient: XTwitterScraperClientAsync

    @BeforeEach
    fun beforeEach(wmRuntimeInfo: WireMockRuntimeInfo) {
        client =
            XTwitterScraperOkHttpClient.builder()
                .baseUrl(wmRuntimeInfo.httpBaseUrl)
                .apiKey("My API Key")
                .responseValidation(true)
                .build()
        asyncClient =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(wmRuntimeInfo.httpBaseUrl)
                .apiKey("My API Key")
                .responseValidation(true)
                .build()
    }

    @Test
    fun bookmarkPostsAndReadsItsAction() {
        stubFor(
            post(urlPathEqualTo("/x/tweets/1/bookmark"))
                .willReturn(okJson(writeAction("bookmark", "tweet")))
        )
        val params =
            BookmarkCreateParams.builder()
                .id("1")
                .idempotencyKey("write-key-1")
                .account("@xquik_test")
                .build()

        val response = client.x().tweets().bookmark().create(params)
        val asyncResponse = runBlocking { asyncClient.x().tweets().bookmark().create(params) }

        verifyWrite(postRequestedFor(urlPathEqualTo("/x/tweets/1/bookmark")))
        assertThat(response.action()).isEqualTo(BookmarkCreateResponse.Action.BOOKMARK)
        assertThat(asyncResponse).isEqualTo(response)
    }

    @Test
    fun unbookmarkDeletesAndReadsItsAction() {
        stubFor(
            delete(urlPathEqualTo("/x/tweets/1/bookmark"))
                .willReturn(okJson(writeAction("unbookmark", "tweet")))
        )
        val params =
            BookmarkDeleteParams.builder()
                .id("1")
                .idempotencyKey("write-key-1")
                .account("@xquik_test")
                .build()

        val response = client.x().tweets().bookmark().delete(params)
        val asyncResponse = runBlocking { asyncClient.x().tweets().bookmark().delete(params) }

        verifyWrite(deleteRequestedFor(urlPathEqualTo("/x/tweets/1/bookmark")))
        assertThat(response.action()).isEqualTo(BookmarkDeleteResponse.Action.UNBOOKMARK)
        assertThat(asyncResponse).isEqualTo(response)
    }

    @Test
    fun blockPostsAndReadsItsAction() {
        stubFor(
            post(urlPathEqualTo("/x/users/2/block"))
                .willReturn(okJson(writeAction("block", "user")))
        )
        val params =
            BlockCreateParams.builder()
                .id("2")
                .idempotencyKey("write-key-1")
                .account("@xquik_test")
                .build()

        val response = client.x().users().block().create(params)
        val asyncResponse = runBlocking { asyncClient.x().users().block().create(params) }

        verifyWrite(postRequestedFor(urlPathEqualTo("/x/users/2/block")))
        assertThat(response.action()).isEqualTo(BlockCreateResponse.Action.BLOCK)
        assertThat(asyncResponse).isEqualTo(response)
    }

    @Test
    fun unblockDeletesAndReadsItsAction() {
        stubFor(
            delete(urlPathEqualTo("/x/users/2/block"))
                .willReturn(okJson(writeAction("unblock", "user")))
        )
        val params =
            BlockDeleteParams.builder()
                .id("2")
                .idempotencyKey("write-key-1")
                .account("@xquik_test")
                .build()

        val response = client.x().users().block().delete(params)
        val asyncResponse = runBlocking { asyncClient.x().users().block().delete(params) }

        verifyWrite(deleteRequestedFor(urlPathEqualTo("/x/users/2/block")))
        assertThat(response.action()).isEqualTo(BlockDeleteResponse.Action.UNBLOCK)
        assertThat(asyncResponse).isEqualTo(response)
    }

    @Test
    fun mutePostsAndReadsItsAction() {
        stubFor(
            post(urlPathEqualTo("/x/users/3/mute")).willReturn(okJson(writeAction("mute", "user")))
        )
        val params =
            MuteCreateParams.builder()
                .id("3")
                .idempotencyKey("write-key-1")
                .account("@xquik_test")
                .build()

        val response = client.x().users().mute().create(params)
        val asyncResponse = runBlocking { asyncClient.x().users().mute().create(params) }

        verifyWrite(postRequestedFor(urlPathEqualTo("/x/users/3/mute")))
        assertThat(response.action()).isEqualTo(MuteCreateResponse.Action.MUTE)
        assertThat(asyncResponse).isEqualTo(response)
    }

    @Test
    fun unmuteDeletesAndReadsItsAction() {
        stubFor(
            delete(urlPathEqualTo("/x/users/3/mute"))
                .willReturn(okJson(writeAction("unmute", "user")))
        )
        val params =
            MuteDeleteParams.builder()
                .id("3")
                .idempotencyKey("write-key-1")
                .account("@xquik_test")
                .build()

        val response = client.x().users().mute().delete(params)
        val asyncResponse = runBlocking { asyncClient.x().users().mute().delete(params) }

        verifyWrite(deleteRequestedFor(urlPathEqualTo("/x/users/3/mute")))
        assertThat(response.action()).isEqualTo(MuteDeleteResponse.Action.UNMUTE)
        assertThat(asyncResponse).isEqualTo(response)
    }

    private fun verifyWrite(request: RequestPatternBuilder) {
        verify(
            2,
            request
                .withHeader("Idempotency-Key", equalTo("write-key-1"))
                .withRequestBody(equalToJson("""{"account": "@xquik_test"}""")),
        )
    }

    private fun writeAction(action: String, targetType: String): String =
        """
        {
          "object": "x_write_action",
          "id": "12345",
          "writeActionId": "12345",
          "action": "$action",
          "status": "success",
          "terminal": true,
          "retryable": false,
          "safeToRetry": false,
          "statusUrl": "/api/v1/x/write-actions/12345",
          "pollAfterMs": null,
          "charged": true,
          "chargedCredits": "10",
          "billing": {
            "status": "charged",
            "charged": true,
            "plannedCredits": "10",
            "chargedCredits": "10"
          },
          "request": {
            "hash": "0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef",
            "payload": {
              "target_id": "9876543210"
            }
          },
          "account": {
            "id": "42",
            "username": "example"
          },
          "target": {
            "type": "$targetType",
            "id": "9876543210"
          },
          "targetId": "9876543210",
          "result": {
            "type": "state_change",
            "id": "9876543210",
            "state": "$action"
          },
          "nextAction": null,
          "sendDispatched": true,
          "success": true
        }
        """
}
