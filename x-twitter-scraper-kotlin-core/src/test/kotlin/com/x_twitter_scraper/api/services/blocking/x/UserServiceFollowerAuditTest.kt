// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.services.blocking.x

import com.github.tomakehurst.wiremock.client.WireMock.equalTo
import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor
import com.github.tomakehurst.wiremock.client.WireMock.okJson
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo
import com.github.tomakehurst.wiremock.client.WireMock.verify
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo
import com.github.tomakehurst.wiremock.junit5.WireMockTest
import com.x_twitter_scraper.api.client.XTwitterScraperClient
import com.x_twitter_scraper.api.client.XTwitterScraperClientAsync
import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClient
import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClientAsync
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.models.x.users.FollowerAudit
import com.x_twitter_scraper.api.models.x.users.FollowerClass
import com.x_twitter_scraper.api.models.x.users.UserRetrieveFollowerAuditParams
import com.x_twitter_scraper.api.services.ExampleResponses
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.ResourceLock

@WireMockTest
@ResourceLock("https://github.com/wiremock/wiremock/issues/169")
internal class UserServiceFollowerAuditTest {

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
    fun retrieveFollowerAuditSendsTheQueryAndReadsTheAudit() {
        stubFor(get(urlPathEqualTo("/x/users/NASA/follower-audit")).willReturn(okJson(example)))
        val params =
            UserRetrieveFollowerAuditParams.builder()
                .limit(200L)
                .fields("audit")
                .omit("audit.topFollowers")
                .build()

        val response = client.x().users().retrieveFollowerAudit("NASA", params)
        val asyncResponse = runBlocking {
            asyncClient.x().users().retrieveFollowerAudit("NASA", params)
        }
        val rawResponse =
            client.x().users().withRawResponse().retrieveFollowerAudit("NASA", params).use {
                assertThat(it.statusCode()).isEqualTo(200)
                it.parse()
            }
        val asyncRawResponse = runBlocking {
            asyncClient.x().users().withRawResponse().retrieveFollowerAudit("NASA", params)
        }
            .use { it.parse() }

        verify(
            4,
            getRequestedFor(urlPathEqualTo("/x/users/NASA/follower-audit"))
                .withQueryParam("limit", equalTo("200"))
                .withQueryParam("fields", equalTo("audit"))
                .withQueryParam("omit", equalTo("audit.topFollowers")),
        )
        assertThat(asyncResponse).isEqualTo(response)
        assertThat(rawResponse).isEqualTo(response)
        assertThat(asyncRawResponse).isEqualTo(response)
        assertThat(response.validate()).isSameAs(response)
        val audit = response.audit()
        assertThat(audit.account().userId()).isEqualTo("11348282")
        assertThat(audit.account().followers()).isEqualTo(92376187.0)
        assertThat(audit.coverage().percent()).isEqualTo(0.0002)
        assertThat(audit.coverage().read()).isEqualTo(200L)
        assertThat(audit.coverage().total()).isEqualTo(92376187L)
        assertThat(audit.quality().grade()).isEqualTo(FollowerAudit.Quality.Grade.B)
        assertThat(audit.quality().score()).isEqualTo(71L)
        assertThat(audit.classes().map { it.class_() })
            .containsExactly(
                FollowerClass.LIKELY_REAL,
                FollowerClass.INACTIVE,
                FollowerClass.LIKELY_FAKE,
                FollowerClass.TOO_NEW,
            )
        assertThat(audit.classes().map { it.count() }).containsExactly(122L, 41L, 29L, 8L)
        assertThat(audit.classes().map { it.percent() }).containsExactly(61.0, 20.5, 14.5, 4.0)
        assertThat(audit.signals().map { it.id() }).containsExactly("defaultPicture", "noBio")
        assertThat(audit.signals().map { it.points() }).containsExactly(2L, 1L)
        assertThat(audit.arrivalBands().map { it.from() }).containsExactly(1L, 101L)
        assertThat(audit.arrivalBands().map { it.to() }).containsExactly(100L, 200L)
        assertThat(audit.cohorts().single().likelyFake()).isEqualTo(6L)
        assertThat(audit.cohorts().single().weekStartMs()).isEqualTo(1761523200000L)
        assertThat(audit.languages().single().value()).isEqualTo("en")
        assertThat(audit.places().single().value()).isEqualTo("United States")
        assertThat(audit.verified().count()).isEqualTo(9L)
        assertThat(audit.verified().percent()).isEqualTo(4.5)
        val follower = audit.followers().single()
        assertThat(follower.userName()).isEqualTo("orbit_watcher")
        assertThat(follower.class_()).isEqualTo(FollowerClass.LIKELY_REAL)
        assertThat(follower.signals()).containsExactly("noBanner")
        assertThat(follower.joinedAtMs()).isEqualTo(1561939200000L)
        assertThat(follower.owner()).isNull()
        assertThat(audit.topFollowers()).isEqualTo(audit.followers())
    }

    @Test
    fun everyOverloadReadsTheSameAuditWithoutQueryParams() {
        stubFor(get(urlPathEqualTo("/x/users/11348282/follower-audit")).willReturn(okJson(example)))
        val params = UserRetrieveFollowerAuditParams.builder().id("11348282").build()
        val users = client.x().users()
        val rawUsers = users.withRawResponse()
        val asyncUsers = asyncClient.x().users()
        val asyncRawUsers = asyncUsers.withRawResponse()

        val responses =
            listOf(
                users.retrieveFollowerAudit("11348282"),
                users.retrieveFollowerAudit("11348282", RequestOptions.none()),
                users.retrieveFollowerAudit(params),
                rawUsers.retrieveFollowerAudit("11348282").use { it.parse() },
                rawUsers.retrieveFollowerAudit("11348282", RequestOptions.none()).use {
                    it.parse()
                },
                rawUsers.retrieveFollowerAudit(params).use { it.parse() },
            ) +
                runBlocking {
                    listOf(
                        asyncUsers.retrieveFollowerAudit("11348282"),
                        asyncUsers.retrieveFollowerAudit("11348282", RequestOptions.none()),
                        asyncUsers.retrieveFollowerAudit(params),
                        asyncRawUsers.retrieveFollowerAudit("11348282").use { it.parse() },
                        asyncRawUsers.retrieveFollowerAudit("11348282", RequestOptions.none()).use {
                            it.parse()
                        },
                        asyncRawUsers.retrieveFollowerAudit(params).use { it.parse() },
                    )
                }

        verify(12, getRequestedFor(urlPathEqualTo("/x/users/11348282/follower-audit")))
        assertThat(responses).hasSize(12)
        assertThat(responses.toSet()).hasSize(1)
        assertThat(responses.first().audit().quality().grade().known())
            .isEqualTo(FollowerAudit.Quality.Grade.Known.B)
    }

    companion object {

        private val example = ExampleResponses.read("examples/x/users/retrieveFollowerAudit.json")
    }
}
