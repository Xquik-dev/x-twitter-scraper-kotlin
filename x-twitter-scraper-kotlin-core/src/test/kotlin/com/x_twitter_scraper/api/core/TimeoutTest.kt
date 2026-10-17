// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.core

import com.x_twitter_scraper.api.core.http.HttpClient
import java.time.Duration
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock

internal class TimeoutTest {

    @Test
    fun streamDefaultsHaveNoRequestTimeoutAndAReadTimeout() {
        val timeout = Timeout.default().forStream()

        assertThat(timeout.request()).isEqualTo(Duration.ZERO)
        assertThat(timeout.read()).isEqualTo(Duration.ofMinutes(1))
        assertThat(timeout.connect()).isEqualTo(Duration.ofMinutes(1))
        assertThat(timeout.write()).isEqualTo(Duration.ofMinutes(1))
    }

    @Test
    fun streamPreservesTheCallersPhaseTimeouts() {
        val timeout =
            Timeout.builder()
                .connect(Duration.ofSeconds(2))
                .read(Duration.ofSeconds(3))
                .write(Duration.ofSeconds(4))
                .request(Duration.ofSeconds(5))
                .build()
                .forStream()

        assertThat(timeout.request()).isEqualTo(Duration.ZERO)
        assertThat(timeout.connect()).isEqualTo(Duration.ofSeconds(2))
        assertThat(timeout.read()).isEqualTo(Duration.ofSeconds(3))
        assertThat(timeout.write()).isEqualTo(Duration.ofSeconds(4))
    }

    @Test
    fun streamReadTimeoutDoesNotInheritTheRequestTimeout() {
        val timeout = Timeout.builder().request(Duration.ofMillis(500)).build().forStream()

        assertThat(timeout.request()).isEqualTo(Duration.ZERO)
        assertThat(timeout.read()).isEqualTo(Duration.ofMinutes(1))
        assertThat(timeout.write()).isEqualTo(Duration.ofMillis(500))
    }

    @Test
    fun requestOptionsPreserveStreamDefaultsAndExplicitOverrides() {
        val clientOptions =
            ClientOptions.builder()
                .httpClient(mock<HttpClient>())
                .timeout(
                    Timeout.builder().read(Duration.ZERO).request(Duration.ofMillis(500)).build()
                )
                .responseValidation(true)
                .build()
        val defaults = RequestOptions.forStream(clientOptions)
        val requestOptions =
            RequestOptions.builder().timeout(Duration.ofSeconds(2)).build().applyDefaults(defaults)
        val defaultTimeout = checkNotNull(defaults.timeout)
        val requestTimeout = checkNotNull(requestOptions.timeout)

        assertThat(defaults.responseValidation).isTrue()
        assertThat(defaultTimeout.request()).isEqualTo(Duration.ZERO)
        assertThat(defaultTimeout.read()).isEqualTo(Duration.ZERO)
        assertThat(requestOptions.responseValidation).isTrue()
        assertThat(requestTimeout.request()).isEqualTo(Duration.ofSeconds(2))
        assertThat(requestTimeout.read()).isEqualTo(Duration.ZERO)
    }
}
