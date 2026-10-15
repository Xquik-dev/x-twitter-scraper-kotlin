// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.extractions

import com.x_twitter_scraper.api.core.http.QueryParams
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ExtractionRetrieveParamsTest {

    @Test
    fun create() {
        ExtractionRetrieveParams.builder()
            .id("L3NjAbCdEfGh")
            .cursor("cursor")
            .fieldStyle(ExtractionRetrieveParams.FieldStyle.SOURCE)
            .includeRaw(true)
            .limit(100L)
            .outputMode(ExtractionRetrieveParams.OutputMode.COMPACT)
            .outputPreset(ExtractionRetrieveParams.OutputPreset.NESTED)
            .wait(0L)
            .build()
    }

    @Test
    fun pathParams() {
        val params = ExtractionRetrieveParams.builder().id("L3NjAbCdEfGh").build()

        assertThat(params._pathParam(0)).isEqualTo("L3NjAbCdEfGh")
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun queryParams() {
        val params =
            ExtractionRetrieveParams.builder()
                .id("L3NjAbCdEfGh")
                .cursor("cursor")
                .fieldStyle(ExtractionRetrieveParams.FieldStyle.SOURCE)
                .includeRaw(true)
                .limit(100L)
                .outputMode(ExtractionRetrieveParams.OutputMode.COMPACT)
                .outputPreset(ExtractionRetrieveParams.OutputPreset.NESTED)
                .wait(0L)
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams)
            .isEqualTo(
                QueryParams.builder()
                    .put("cursor", "cursor")
                    .put("fieldStyle", "source")
                    .put("includeRaw", "true")
                    .put("limit", "100")
                    .put("outputMode", "compact")
                    .put("outputPreset", "nested")
                    .put("wait", "0")
                    .build()
            )
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params = ExtractionRetrieveParams.builder().id("L3NjAbCdEfGh").build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().build())
    }
}
