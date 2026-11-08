// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.monitors

import com.x_twitter_scraper.api.models.EventType
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

internal class MonitorUpsertParamsTest {

    @Test
    fun pathParams() {
        val params =
            MonitorUpsertParams.builder()
                .username("elonmusk")
                .addEventType(EventType.TWEET_NEW)
                .build()

        assertThat(params._pathParam(0)).isEqualTo("elonmusk")
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun body() {
        val params =
            MonitorUpsertParams.builder()
                .username("elonmusk")
                .addEventType(EventType.TWEET_NEW)
                .name("Launch watch")
                .build()

        val body = params._body()

        assertThat(body.eventTypes()).containsExactly(EventType.TWEET_NEW)
        assertThat(body.name()).isEqualTo("Launch watch")
        assertThat(params.toBuilder().build()).isEqualTo(params)
    }

    @Test
    fun bodyWithoutTheLabel() {
        val params =
            MonitorUpsertParams.builder()
                .username("elonmusk")
                .eventTypes(listOf(EventType.TWEET_REPLY))
                .build()

        assertThat(params.eventTypes()).containsExactly(EventType.TWEET_REPLY)
        assertThat(params.name()).isNull()
    }

    @Test
    fun requiresTheEventTypes() {
        assertThatThrownBy { MonitorUpsertParams.builder().username("elonmusk").build() }
            .isInstanceOf(IllegalStateException::class.java)
    }
}
