// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.services.blocking

import com.sun.net.httpserver.HttpServer
import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClient
import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClientAsync
import com.x_twitter_scraper.api.core.RequestOptions
import com.x_twitter_scraper.api.models.events.EventStreamParams
import java.io.IOException
import java.io.InterruptedIOException
import java.net.InetAddress
import java.net.InetSocketAddress
import java.time.Duration
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.withTimeoutOrNull
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class EventServiceStreamTest {

    private val serverReleased = CountDownLatch(1)
    private val heartbeatSent = CountDownLatch(1)
    @Volatile private var sendEvents = true
    @Volatile private var pauseBeforeSecondEvent = Duration.ofMillis(1_500)
    private lateinit var server: HttpServer
    private lateinit var baseUrl: String

    @BeforeEach
    fun startServer() {
        server = HttpServer.create(InetSocketAddress(InetAddress.getByName("127.0.0.1"), 0), 0)
        server.createContext("/events/stream") { exchange ->
            try {
                exchange.responseHeaders.add("Content-Type", "text/event-stream")
                exchange.sendResponseHeaders(200, 0)
                exchange.responseBody.use { body ->
                    if (sendEvents) {
                        body.write(event("42"))
                        body.flush()
                    }
                    val secondEventAt = System.nanoTime() + pauseBeforeSecondEvent.toNanos()
                    while (
                        System.nanoTime() < secondEventAt &&
                            !serverReleased.await(100, TimeUnit.MILLISECONDS)
                    ) {
                        body.write(": keep-alive\n\n".toByteArray())
                        body.flush()
                        heartbeatSent.countDown()
                    }
                    if (sendEvents && serverReleased.count != 0L) {
                        body.write(event("43"))
                    }
                }
            } catch (e: IOException) {} finally {
                exchange.close()
            }
        }
        server.start()
        baseUrl = "http://127.0.0.1:${server.address.port}"
    }

    @AfterEach
    fun stopServer() {
        serverReleased.countDown()
        server.stop(0)
    }

    @Test
    fun streamOutlastsTheClientRequestTimeout() {
        val client =
            XTwitterScraperOkHttpClient.builder()
                .baseUrl(baseUrl)
                .apiKey("My API Key")
                .timeout(Duration.ofMillis(500))
                .build()

        val ids =
            client.events().stream().use { stream -> stream.asSequence().map { it.id() }.toList() }

        assertThat(ids).containsExactly("42", "43")
    }

    @Test
    suspend fun asyncStreamOutlastsTheClientRequestTimeout() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder()
                .baseUrl(baseUrl)
                .apiKey("My API Key")
                .timeout(Duration.ofMillis(500))
                .build()

        val events = withTimeout(10_000) { client.events().stream().toList() }

        assertThat(events.map { it.id() }).containsExactly("42", "43")
    }

    @Test
    fun streamHonorsAnExplicitRequestTimeout() {
        val client =
            XTwitterScraperOkHttpClient.builder().baseUrl(baseUrl).apiKey("My API Key").build()
        val requestOptions = RequestOptions.builder().timeout(Duration.ofMillis(500)).build()

        assertThatThrownBy {
                client.events().stream(EventStreamParams.none(), requestOptions).use { stream ->
                    stream.asSequence().toList()
                }
            }
            .isInstanceOf(InterruptedIOException::class.java)
    }

    @Test
    suspend fun cancellingAHeartbeatOnlyCollectorReturnsWithoutAnError() {
        sendEvents = false
        pauseBeforeSecondEvent = Duration.ofMinutes(1)
        val client =
            XTwitterScraperOkHttpClientAsync.builder().baseUrl(baseUrl).apiKey("My API Key").build()
        val errors = CopyOnWriteArrayList<Throwable>()

        coroutineScope {
            val collector = launch {
                try {
                    client.events().stream().collect {}
                } catch (e: CancellationException) {
                    throw e
                } catch (e: Exception) {
                    errors.add(e)
                }
            }
            try {
                withContext(Dispatchers.IO) {
                    assertThat(heartbeatSent.await(10, TimeUnit.SECONDS)).isTrue()
                }

                withTimeout(1_000) { collector.cancelAndJoin() }

                assertThat(errors).isEmpty()
            } finally {
                serverReleased.countDown()
                collector.cancelAndJoin()
            }
        }
    }

    @Test
    suspend fun firstReturnsBeforeTheNextEvent() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder().baseUrl(baseUrl).apiKey("My API Key").build()

        try {
            val event = withTimeout(1_000) { client.events().stream().first() }

            assertThat(event.id()).isEqualTo("42")
        } finally {
            serverReleased.countDown()
        }
    }

    @Test
    suspend fun timingOutAHeartbeatOnlyCollectorReturnsPromptly() {
        sendEvents = false
        pauseBeforeSecondEvent = Duration.ofSeconds(3)
        val client =
            XTwitterScraperOkHttpClientAsync.builder().baseUrl(baseUrl).apiKey("My API Key").build()

        try {
            val started = System.nanoTime()
            val events = withTimeoutOrNull(500) { client.events().stream().toList() }
            val elapsed = Duration.ofNanos(System.nanoTime() - started)

            assertThat(events).isNull()
            assertThat(elapsed).isLessThan(Duration.ofSeconds(2))
        } finally {
            serverReleased.countDown()
        }
    }

    @Test
    suspend fun takeReturnsBeforeTheNextEvent() {
        val client =
            XTwitterScraperOkHttpClientAsync.builder().baseUrl(baseUrl).apiKey("My API Key").build()

        try {
            val events = withTimeout(1_000) { client.events().stream().take(1).toList() }

            assertThat(events.map { it.id() }).containsExactly("42")
        } finally {
            serverReleased.countDown()
        }
    }

    private fun event(id: String): ByteArray =
        ("id: $id\nevent: tweet.new\ndata: {\"id\":\"$id\",\"type\":\"tweet.new\"," +
                "\"monitorId\":\"7\",\"monitorType\":\"account\"," +
                "\"occurredAt\":\"2025-01-15T12:00:00.000Z\",\"data\":{}}\n\n")
            .toByteArray()
}
