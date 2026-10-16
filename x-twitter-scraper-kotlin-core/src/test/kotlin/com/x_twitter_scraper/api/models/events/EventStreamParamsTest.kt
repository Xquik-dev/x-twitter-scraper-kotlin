// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.events

import com.x_twitter_scraper.api.core.http.Headers
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class EventStreamParamsTest {

    @Test
    fun create() {
        EventStreamParams.builder().lastEventId("42").build()
    }

    @Test
    fun headers() {
        val params = EventStreamParams.builder().lastEventId("42").build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().put("Last-Event-ID", "42").build())
    }
}
