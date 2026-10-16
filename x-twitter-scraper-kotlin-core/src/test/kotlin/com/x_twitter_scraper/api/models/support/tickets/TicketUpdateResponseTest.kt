// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.support.tickets

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TicketUpdateResponseTest {

    @Test
    fun create() {
        val ticketUpdateResponse =
            TicketUpdateResponse.builder()
                .publicId("publicId")
                .status(TicketUpdateResponse.Status.OPEN)
                .build()

        assertThat(ticketUpdateResponse.publicId()).isEqualTo("publicId")
        assertThat(ticketUpdateResponse.status()).isEqualTo(TicketUpdateResponse.Status.OPEN)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val ticketUpdateResponse =
            TicketUpdateResponse.builder()
                .publicId("publicId")
                .status(TicketUpdateResponse.Status.OPEN)
                .build()

        val roundtrippedTicketUpdateResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(ticketUpdateResponse),
                jacksonTypeRef<TicketUpdateResponse>(),
            )

        assertThat(roundtrippedTicketUpdateResponse).isEqualTo(ticketUpdateResponse)
    }
}
