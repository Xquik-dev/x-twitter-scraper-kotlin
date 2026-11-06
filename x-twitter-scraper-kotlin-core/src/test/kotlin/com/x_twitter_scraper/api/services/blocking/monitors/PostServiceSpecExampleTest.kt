// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.services.blocking.monitors

import com.github.tomakehurst.wiremock.client.WireMock.delete
import com.github.tomakehurst.wiremock.client.WireMock.deleteRequestedFor
import com.github.tomakehurst.wiremock.client.WireMock.equalTo
import com.github.tomakehurst.wiremock.client.WireMock.equalToJson
import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor
import com.github.tomakehurst.wiremock.client.WireMock.okJson
import com.github.tomakehurst.wiremock.client.WireMock.patch
import com.github.tomakehurst.wiremock.client.WireMock.patchRequestedFor
import com.github.tomakehurst.wiremock.client.WireMock.post
import com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo
import com.github.tomakehurst.wiremock.client.WireMock.verify
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo
import com.github.tomakehurst.wiremock.junit5.WireMockTest
import com.x_twitter_scraper.api.client.XTwitterScraperClient
import com.x_twitter_scraper.api.client.XTwitterScraperClientAsync
import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClient
import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClientAsync
import com.x_twitter_scraper.api.core.JsonValue
import com.x_twitter_scraper.api.models.monitors.posts.PostCreateParams
import com.x_twitter_scraper.api.models.monitors.posts.PostDeleteResponse
import com.x_twitter_scraper.api.models.monitors.posts.PostListParams
import com.x_twitter_scraper.api.models.monitors.posts.PostMonitor
import com.x_twitter_scraper.api.models.monitors.posts.PostRetrieveMetricsParams
import com.x_twitter_scraper.api.models.monitors.posts.PostUpdateParams
import java.time.OffsetDateTime
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.ResourceLock

@WireMockTest
@ResourceLock("https://github.com/wiremock/wiremock/issues/169")
internal class PostServiceSpecExampleTest {

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
    fun createParsesTheSpecExample() {
        stubFor(
            post(urlPathEqualTo("/monitors/posts")).willReturn(okJson(createPostMonitorExample))
        )
        val params =
            PostCreateParams.builder()
                .post("https://x.com/NASA/status/2102761519985332442")
                .name("Launch post")
                .addEventType(PostMonitor.EventType.POST_REPLY_NEW)
                .addEventType(PostMonitor.EventType.POST_LIKES_CHANGED)
                .isActive(true)
                .build()

        val response = client.monitors().posts().create(params)
        val asyncResponse = runBlocking { asyncClient.monitors().posts().create(params) }
        val rawResponse =
            client.monitors().posts().withRawResponse().create(params).use { it.parse() }
        val asyncRawResponse = runBlocking {
            asyncClient.monitors().posts().withRawResponse().create(params)
        }
            .use {
                it.parse()
            }

        verify(
            4,
            postRequestedFor(urlPathEqualTo("/monitors/posts"))
                .withRequestBody(
                    equalToJson(
                        """
                        {
                          "post": "https://x.com/NASA/status/2102761519985332442",
                          "name": "Launch post",
                          "eventTypes": ["post.reply.new", "post.likes.changed"],
                          "isActive": true
                        }
                        """
                    )
                ),
        )
        assertThat(asyncResponse).isEqualTo(response)
        assertThat(rawResponse).isEqualTo(response)
        assertThat(asyncRawResponse).isEqualTo(response)
        assertLaunchPostMonitor(response)
    }

    @Test
    fun retrieveParsesTheSpecExample() {
        stubFor(
            get(urlPathEqualTo("/monitors/posts/31")).willReturn(okJson(createPostMonitorExample))
        )

        val response = client.monitors().posts().retrieve("31")
        val asyncResponse = runBlocking { asyncClient.monitors().posts().retrieve("31") }

        verify(2, getRequestedFor(urlPathEqualTo("/monitors/posts/31")))
        assertThat(asyncResponse).isEqualTo(response)
        assertLaunchPostMonitor(response)
    }

    @Test
    fun updateSendsANullNameAndParsesThePausedMonitor() {
        stubFor(
            patch(urlPathEqualTo("/monitors/posts/31")).willReturn(okJson(updatePostMonitorExample))
        )
        val params = PostUpdateParams.builder().isActive(false).name(null as String?).build()

        val response = client.monitors().posts().update("31", params)
        val asyncResponse = runBlocking {
            asyncClient.monitors().posts().update(params.toBuilder().id("31").build())
        }

        verify(
            2,
            patchRequestedFor(urlPathEqualTo("/monitors/posts/31"))
                .withRequestBody(equalToJson("""{"isActive": false, "name": null}""")),
        )
        assertThat(asyncResponse).isEqualTo(response)
        assertThat(response.id()).isEqualTo("31")
        assertThat(response.name()).isNull()
        assertThat(response.isActive()).isFalse()
        assertThat(response.pausedReason()).isNull()
        assertThat(response.metrics().views().current()).isNull()
        assertThat(response.metrics().views().gain()).isNull()
        assertThat(response.metrics().likes().current()).isEqualTo(1890L)
        assertThat(response.metrics().likes().gain()).isEqualTo(5L)
    }

    @Test
    fun listParsesTheSpecExample() {
        stubFor(get(urlPathEqualTo("/monitors/posts")).willReturn(okJson(listPostMonitorsExample)))
        val params = PostListParams.builder().limit(1L).cursor("40").build()

        val response = client.monitors().posts().list(params)
        val asyncResponse = runBlocking { asyncClient.monitors().posts().list(params) }

        verify(
            2,
            getRequestedFor(urlPathEqualTo("/monitors/posts"))
                .withQueryParam("limit", equalTo("1"))
                .withQueryParam("cursor", equalTo("40")),
        )
        assertThat(asyncResponse).isEqualTo(response)
        assertThat(response.total()).isEqualTo(1L)
        assertThat(response.hasMore()).isTrue()
        assertThat(response.nextCursor()).isEqualTo("31")
        assertThat(response.monitors()).hasSize(1)
        val monitor = response.monitors()[0]
        assertThat(monitor.eventTypes())
            .containsExactly(
                PostMonitor.EventType.POST_REPLY_NEW,
                PostMonitor.EventType.POST_QUOTE_NEW,
                PostMonitor.EventType.POST_LIKES_CHANGED,
            )
        assertThat(monitor.isActive()).isFalse()
        assertThat(monitor.pausedReason()).isEqualTo(PostMonitor.PausedReason.INSUFFICIENT_CREDITS)
        assertThat(monitor.pausedAt()).isEqualTo(OffsetDateTime.parse("2026-10-22T12:00:00Z"))
        assertThat(monitor.metrics().views().current()).isEqualTo(541600L)
        assertThat(monitor.metrics().views().gain()).isEqualTo(500L)
    }

    @Test
    fun deleteParsesTheSpecExample() {
        stubFor(
            delete(urlPathEqualTo("/monitors/posts/31"))
                .willReturn(okJson(deletePostMonitorExample))
        )

        val response = client.monitors().posts().delete("31")
        val asyncResponse = runBlocking { asyncClient.monitors().posts().delete("31") }

        verify(2, deleteRequestedFor(urlPathEqualTo("/monitors/posts/31")))
        assertThat(asyncResponse).isEqualTo(response)
        assertThat(response.deletionStatus()).isEqualTo(PostDeleteResponse.DeletionStatus.DELETING)
        assertThat(response.statusUrl()).isEqualTo("/api/v1/monitors/posts/31")
        assertThat(response._success()).isEqualTo(JsonValue.from(true))
    }

    @Test
    fun retrieveMetricsParsesTheSpecExample() {
        stubFor(
            get(urlPathEqualTo("/monitors/posts/31/metrics"))
                .willReturn(okJson(getPostMonitorMetricsExample))
        )
        val params = PostRetrieveMetricsParams.builder().hours(2L).build()

        val response = client.monitors().posts().retrieveMetrics("31", params)
        val asyncResponse = runBlocking {
            asyncClient.monitors().posts().retrieveMetrics("31", params)
        }
        val rawResponse =
            client
                .monitors()
                .posts()
                .withRawResponse()
                .retrieveMetrics(params.toBuilder().id("31").build())
                .use { it.parse() }

        verify(
            3,
            getRequestedFor(urlPathEqualTo("/monitors/posts/31/metrics"))
                .withQueryParam("hours", equalTo("2")),
        )
        assertThat(asyncResponse).isEqualTo(response)
        assertThat(rawResponse).isEqualTo(response)
        assertThat(response.hours()).isEqualTo(2L)
        assertThat(response.buckets()).hasSize(2)
        assertThat(response.buckets()[0].at())
            .isEqualTo(OffsetDateTime.parse("2026-10-22T10:00:00.000Z"))
        assertThat(response.buckets()[0].metrics().likes().value()).isEqualTo(1885L)
        assertThat(response.buckets()[0].metrics().likes().gain()).isEqualTo(0L)
        val lastHour = response.buckets()[1]
        assertThat(lastHour.at()).isEqualTo(OffsetDateTime.parse("2026-10-22T11:00:00.000Z"))
        assertThat(lastHour.metrics().views().value()).isEqualTo(541600L)
        assertThat(lastHour.metrics().views().gain()).isEqualTo(500L)
        assertThat(lastHour.metrics().likes().value()).isEqualTo(1890L)
        assertThat(lastHour.metrics().likes().gain()).isEqualTo(5L)
        assertThat(lastHour.metrics().retweets().gain()).isEqualTo(0L)
        assertThat(lastHour.metrics().replies().value()).isEqualTo(86L)
        assertThat(lastHour.metrics().quotes().value()).isEqualTo(87L)
        assertThat(lastHour.metrics().bookmarks().gain()).isEqualTo(1L)
    }

    private fun assertLaunchPostMonitor(monitor: PostMonitor) {
        assertThat(monitor.id()).isEqualTo("31")
        assertThat(monitor.name()).isEqualTo("Launch post")
        assertThat(monitor.postId()).isEqualTo("2102761519985332442")
        assertThat(monitor.postUrl()).isEqualTo("https://x.com/NASA/status/2102761519985332442")
        assertThat(monitor.authorId()).isEqualTo("11348282")
        assertThat(monitor.authorUsername()).isEqualTo("NASA")
        assertThat(monitor.authorName()).isEqualTo("NASA")
        assertThat(monitor.authorAffiliate())
            .isEqualTo(
                PostMonitor.AuthorAffiliate.builder()
                    .badgeUrl(
                        "https://pbs.twimg.com/profile_images/1955359038532653056/OSHY3ewP_bigger.jpg"
                    )
                    .description("X")
                    .url("https://twitter.com/X")
                    .build()
            )
        assertThat(monitor.text()).isEqualTo("We shipped post monitors.")
        assertThat(monitor.postedAt()).isEqualTo(OffsetDateTime.parse("2026-09-29T17:19:49.000Z"))
        assertThat(monitor.eventTypes())
            .containsExactly(
                PostMonitor.EventType.POST_REPLY_NEW,
                PostMonitor.EventType.POST_LIKES_CHANGED,
            )
        assertThat(monitor.isActive()).isTrue()
        assertThat(monitor.createdAt()).isEqualTo(OffsetDateTime.parse("2026-10-22T11:00:00Z"))
        assertThat(monitor.nextBillingAt()).isEqualTo(OffsetDateTime.parse("2026-10-22T12:00:00Z"))
        assertThat(monitor.pausedReason()).isNull()
        assertThat(monitor.pausedAt()).isNull()
        assertThat(monitor.metrics().views().current()).isEqualTo(541100L)
        assertThat(monitor.metrics().likes().current()).isEqualTo(1885L)
        assertThat(monitor.metrics().retweets().current()).isEqualTo(112L)
        assertThat(monitor.metrics().replies().current()).isEqualTo(85L)
        assertThat(monitor.metrics().quotes().current()).isEqualTo(87L)
        assertThat(monitor.metrics().bookmarks().current()).isEqualTo(470L)
        assertThat(monitor.metrics().bookmarks().gain()).isEqualTo(0L)
    }

    companion object {

        private val createPostMonitorExample =
            """
            {
              "id": "31",
              "name": "Launch post",
              "postId": "2102761519985332442",
              "postUrl": "https://x.com/NASA/status/2102761519985332442",
              "authorId": "11348282",
              "authorUsername": "NASA",
              "authorName": "NASA",
              "authorAffiliate": {
                "badgeUrl": "https://pbs.twimg.com/profile_images/1955359038532653056/OSHY3ewP_bigger.jpg",
                "description": "X",
                "url": "https://twitter.com/X"
              },
              "text": "We shipped post monitors.",
              "postedAt": "2026-09-29T17:19:49.000Z",
              "eventTypes": [
                "post.reply.new",
                "post.likes.changed"
              ],
              "isActive": true,
              "createdAt": "2026-10-22T11:00:00Z",
              "nextBillingAt": "2026-10-22T12:00:00Z",
              "metrics": {
                "views": {
                  "current": 541100,
                  "gain": 0
                },
                "likes": {
                  "current": 1885,
                  "gain": 0
                },
                "retweets": {
                  "current": 112,
                  "gain": 0
                },
                "replies": {
                  "current": 85,
                  "gain": 0
                },
                "quotes": {
                  "current": 87,
                  "gain": 0
                },
                "bookmarks": {
                  "current": 470,
                  "gain": 0
                }
              },
              "media": [],
              "baseline": {
                "at": "2026-10-22T11:00:00Z",
                "views": 541100,
                "likes": 1885,
                "retweets": 112,
                "replies": 85,
                "quotes": 87,
                "bookmarks": 470
              }
            }
            """
                .trimIndent()

        private val updatePostMonitorExample =
            """
            {
              "id": "31",
              "postId": "2102761519985332442",
              "postUrl": "https://x.com/NASA/status/2102761519985332442",
              "authorId": "11348282",
              "authorUsername": "NASA",
              "authorName": "NASA",
              "text": "We shipped post monitors.",
              "postedAt": "2026-09-29T17:19:49.000Z",
              "eventTypes": [
                "post.reply.new",
                "post.likes.changed"
              ],
              "isActive": false,
              "createdAt": "2026-10-22T11:00:00Z",
              "nextBillingAt": "2026-10-22T12:00:00Z",
              "metrics": {
                "views": {
                  "current": null,
                  "gain": null
                },
                "likes": {
                  "current": 1890,
                  "gain": 5
                },
                "retweets": {
                  "current": 112,
                  "gain": 0
                },
                "replies": {
                  "current": 86,
                  "gain": 1
                },
                "quotes": {
                  "current": 87,
                  "gain": 0
                },
                "bookmarks": {
                  "current": 471,
                  "gain": 1
                }
              },
              "media": [],
              "baseline": {
                "at": "2026-10-22T11:00:00Z",
                "views": 541100,
                "likes": 1885,
                "retweets": 112,
                "replies": 85,
                "quotes": 87,
                "bookmarks": 470
              }
            }
            """
                .trimIndent()

        private val listPostMonitorsExample =
            """
            {
              "monitors": [
                {
                  "id": "31",
                  "name": "Launch post",
                  "postId": "2102761519985332442",
                  "postUrl": "https://x.com/NASA/status/2102761519985332442",
                  "authorId": "11348282",
                  "authorUsername": "NASA",
                  "authorName": "NASA",
                  "text": "We shipped post monitors.",
                  "postedAt": "2026-09-29T17:19:49.000Z",
                  "eventTypes": [
                    "post.reply.new",
                    "post.quote.new",
                    "post.likes.changed"
                  ],
                  "isActive": false,
                  "createdAt": "2026-10-22T11:00:00Z",
                  "nextBillingAt": "2026-10-22T12:00:00Z",
                  "pausedReason": "insufficient_credits",
                  "pausedAt": "2026-10-22T12:00:00Z",
                  "metrics": {
                    "views": {
                      "current": 541600,
                      "gain": 500
                    },
                    "likes": {
                      "current": 1890,
                      "gain": 5
                    },
                    "retweets": {
                      "current": 112,
                      "gain": 0
                    },
                    "replies": {
                      "current": 86,
                      "gain": 1
                    },
                    "quotes": {
                      "current": 87,
                      "gain": 0
                    },
                    "bookmarks": {
                      "current": 471,
                      "gain": 1
                    }
                  },
                  "media": [],
                  "baseline": {
                    "at": "2026-10-22T11:00:00Z",
                    "views": 541100,
                    "likes": 1885,
                    "retweets": 112,
                    "replies": 85,
                    "quotes": 87,
                    "bookmarks": 470
                  }
                }
              ],
              "total": 1,
              "hasMore": true,
              "nextCursor": "31"
            }
            """
                .trimIndent()

        private val deletePostMonitorExample =
            """
            {
              "deletionStatus": "deleting",
              "statusUrl": "/api/v1/monitors/posts/31",
              "success": true
            }
            """
                .trimIndent()

        private val getPostMonitorMetricsExample =
            """
            {
              "hours": 2,
              "buckets": [
                {
                  "at": "2026-10-22T10:00:00.000Z",
                  "metrics": {
                    "views": {
                      "value": 541100,
                      "gain": 0
                    },
                    "likes": {
                      "value": 1885,
                      "gain": 0
                    },
                    "retweets": {
                      "value": 112,
                      "gain": 0
                    },
                    "replies": {
                      "value": 85,
                      "gain": 0
                    },
                    "quotes": {
                      "value": 87,
                      "gain": 0
                    },
                    "bookmarks": {
                      "value": 470,
                      "gain": 0
                    }
                  }
                },
                {
                  "at": "2026-10-22T11:00:00.000Z",
                  "metrics": {
                    "views": {
                      "value": 541600,
                      "gain": 500
                    },
                    "likes": {
                      "value": 1890,
                      "gain": 5
                    },
                    "retweets": {
                      "value": 112,
                      "gain": 0
                    },
                    "replies": {
                      "value": 86,
                      "gain": 1
                    },
                    "quotes": {
                      "value": 87,
                      "gain": 0
                    },
                    "bookmarks": {
                      "value": 471,
                      "gain": 1
                    }
                  }
                }
              ]
            }
            """
                .trimIndent()
    }
}
