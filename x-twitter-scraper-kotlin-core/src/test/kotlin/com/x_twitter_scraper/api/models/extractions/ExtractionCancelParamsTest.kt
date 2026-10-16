// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.extractions

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ExtractionCancelParamsTest {

    @Test
    fun create() {
        ExtractionCancelParams.builder().id("L3NjAbCdEfGh").build()
    }

    @Test
    fun pathParams() {
        val params = ExtractionCancelParams.builder().id("L3NjAbCdEfGh").build()

        assertThat(params._pathParam(0)).isEqualTo("L3NjAbCdEfGh")
        assertThat(params._pathParam(1)).isEqualTo("")
    }
}
