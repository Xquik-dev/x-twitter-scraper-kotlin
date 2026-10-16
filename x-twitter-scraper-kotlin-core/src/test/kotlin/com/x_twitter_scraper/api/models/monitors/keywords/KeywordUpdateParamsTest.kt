// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.monitors.keywords

import com.x_twitter_scraper.api.models.EventType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class KeywordUpdateParamsTest {

    @Test
    fun create() {
        KeywordUpdateParams.builder()
            .id("id")
            .addEventType(EventType.TWEET_NEW)
            .isActive(true)
            .name("Launch mentions")
            .build()
    }

    @Test
    fun pathParams() {
        val params = KeywordUpdateParams.builder().id("id").build()

        assertThat(params._pathParam(0)).isEqualTo("id")
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun body() {
        val params =
            KeywordUpdateParams.builder()
                .id("id")
                .addEventType(EventType.TWEET_NEW)
                .isActive(true)
                .name("Launch mentions")
                .build()

        val body = params._body()

        assertThat(body.eventTypes()).containsExactly(EventType.TWEET_NEW)
        assertThat(body.isActive()).isEqualTo(true)
        assertThat(body.name()).isEqualTo("Launch mentions")
    }
}
