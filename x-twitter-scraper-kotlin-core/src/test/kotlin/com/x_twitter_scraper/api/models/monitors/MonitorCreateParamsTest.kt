// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.monitors

import com.x_twitter_scraper.api.models.EventType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MonitorCreateParamsTest {

    @Test
    fun create() {
        MonitorCreateParams.builder()
            .addEventType(EventType.TWEET_NEW)
            .username("username")
            .name("Launch watch")
            .build()
    }

    @Test
    fun body() {
        val params =
            MonitorCreateParams.builder()
                .addEventType(EventType.TWEET_NEW)
                .username("username")
                .name("Launch watch")
                .build()

        val body = params._body()

        assertThat(body.eventTypes()).containsExactly(EventType.TWEET_NEW)
        assertThat(body.username()).isEqualTo("username")
        assertThat(body.name()).isEqualTo("Launch watch")
    }
}
