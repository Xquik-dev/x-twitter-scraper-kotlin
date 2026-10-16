// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.dm

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DmRetrieveHistoryResponseTest {

    @Test
    fun create() {
        val dmRetrieveHistoryResponse =
            DmRetrieveHistoryResponse.builder()
                .hasNextPage(true)
                .addMessage(
                    DmRetrieveHistoryResponse.Message.builder()
                        .id("id")
                        .receiverId("receiverId")
                        .senderId("senderId")
                        .createdAt("createdAt")
                        .mediaUrl("mediaUrl")
                        .text("text")
                        .build()
                )
                .nextCursor("next_cursor")
                .build()

        assertThat(dmRetrieveHistoryResponse.hasNextPage()).isEqualTo(true)
        assertThat(dmRetrieveHistoryResponse.messages())
            .containsExactly(
                DmRetrieveHistoryResponse.Message.builder()
                    .id("id")
                    .receiverId("receiverId")
                    .senderId("senderId")
                    .createdAt("createdAt")
                    .mediaUrl("mediaUrl")
                    .text("text")
                    .build()
            )
        assertThat(dmRetrieveHistoryResponse.nextCursor()).isEqualTo("next_cursor")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val dmRetrieveHistoryResponse =
            DmRetrieveHistoryResponse.builder()
                .hasNextPage(true)
                .addMessage(
                    DmRetrieveHistoryResponse.Message.builder()
                        .id("id")
                        .receiverId("receiverId")
                        .senderId("senderId")
                        .createdAt("createdAt")
                        .mediaUrl("mediaUrl")
                        .text("text")
                        .build()
                )
                .nextCursor("next_cursor")
                .build()

        val roundtrippedDmRetrieveHistoryResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(dmRetrieveHistoryResponse),
                jacksonTypeRef<DmRetrieveHistoryResponse>(),
            )

        assertThat(roundtrippedDmRetrieveHistoryResponse).isEqualTo(dmRetrieveHistoryResponse)
    }
}
