// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class XAutocompleteSearchResponseTest {

    @Test
    fun create() {
        val xAutocompleteSearchResponse =
            XAutocompleteSearchResponse.builder()
                .addCashtag(
                    XAutocompleteSearchResponse.Cashtag.builder()
                        .ticker("ticker")
                        .chainLogoUrl("chainLogoUrl")
                        .contractAddress("contractAddress")
                        .currency("currency")
                        .dailyChangePercent(0.0)
                        .exchangeShortName("exchangeShortName")
                        .logoUrl("logoUrl")
                        .marketCap("marketCap")
                        .name("name")
                        .price("price")
                        .smartTag("smartTag")
                        .build()
                )
                .count(0L)
                .addHashtag(
                    XAutocompleteSearchResponse.Hashtag.builder()
                        .hashtag("hashtag")
                        .inline(true)
                        .score(0.0)
                        .addToken("token")
                        .build()
                )
                .query("query")
                .addTopic(
                    XAutocompleteSearchResponse.Topic.builder()
                        .topic("topic")
                        .inline(true)
                        .score(0.0)
                        .addToken("token")
                        .build()
                )
                .addUser(
                    XAutocompleteSearchResponse.User.builder()
                        .id("id")
                        .name("name")
                        .username("username")
                        .addBadge(
                            XAutocompleteSearchResponse.User.Badge.builder()
                                .badgeType("badgeType")
                                .badgeUrl("badgeUrl")
                                .description("description")
                                .build()
                        )
                        .inline(true)
                        .isBlueVerified(true)
                        .isPersonaMediaGenable(true)
                        .isVerified(true)
                        .location("location")
                        .profilePicture("profilePicture")
                        .profilePictureOriginal("profilePictureOriginal")
                        .protected(true)
                        .score(0.0)
                        .addToken("token")
                        .verified(true)
                        .verifiedType("verifiedType")
                        .build()
                )
                .build()

        assertThat(xAutocompleteSearchResponse.cashtags())
            .containsExactly(
                XAutocompleteSearchResponse.Cashtag.builder()
                    .ticker("ticker")
                    .chainLogoUrl("chainLogoUrl")
                    .contractAddress("contractAddress")
                    .currency("currency")
                    .dailyChangePercent(0.0)
                    .exchangeShortName("exchangeShortName")
                    .logoUrl("logoUrl")
                    .marketCap("marketCap")
                    .name("name")
                    .price("price")
                    .smartTag("smartTag")
                    .build()
            )
        assertThat(xAutocompleteSearchResponse.count()).isEqualTo(0L)
        assertThat(xAutocompleteSearchResponse.hashtags())
            .containsExactly(
                XAutocompleteSearchResponse.Hashtag.builder()
                    .hashtag("hashtag")
                    .inline(true)
                    .score(0.0)
                    .addToken("token")
                    .build()
            )
        assertThat(xAutocompleteSearchResponse.query()).isEqualTo("query")
        assertThat(xAutocompleteSearchResponse.topics())
            .containsExactly(
                XAutocompleteSearchResponse.Topic.builder()
                    .topic("topic")
                    .inline(true)
                    .score(0.0)
                    .addToken("token")
                    .build()
            )
        assertThat(xAutocompleteSearchResponse.users())
            .containsExactly(
                XAutocompleteSearchResponse.User.builder()
                    .id("id")
                    .name("name")
                    .username("username")
                    .addBadge(
                        XAutocompleteSearchResponse.User.Badge.builder()
                            .badgeType("badgeType")
                            .badgeUrl("badgeUrl")
                            .description("description")
                            .build()
                    )
                    .inline(true)
                    .isBlueVerified(true)
                    .isPersonaMediaGenable(true)
                    .isVerified(true)
                    .location("location")
                    .profilePicture("profilePicture")
                    .profilePictureOriginal("profilePictureOriginal")
                    .protected(true)
                    .score(0.0)
                    .addToken("token")
                    .verified(true)
                    .verifiedType("verifiedType")
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val xAutocompleteSearchResponse =
            XAutocompleteSearchResponse.builder()
                .addCashtag(
                    XAutocompleteSearchResponse.Cashtag.builder()
                        .ticker("ticker")
                        .chainLogoUrl("chainLogoUrl")
                        .contractAddress("contractAddress")
                        .currency("currency")
                        .dailyChangePercent(0.0)
                        .exchangeShortName("exchangeShortName")
                        .logoUrl("logoUrl")
                        .marketCap("marketCap")
                        .name("name")
                        .price("price")
                        .smartTag("smartTag")
                        .build()
                )
                .count(0L)
                .addHashtag(
                    XAutocompleteSearchResponse.Hashtag.builder()
                        .hashtag("hashtag")
                        .inline(true)
                        .score(0.0)
                        .addToken("token")
                        .build()
                )
                .query("query")
                .addTopic(
                    XAutocompleteSearchResponse.Topic.builder()
                        .topic("topic")
                        .inline(true)
                        .score(0.0)
                        .addToken("token")
                        .build()
                )
                .addUser(
                    XAutocompleteSearchResponse.User.builder()
                        .id("id")
                        .name("name")
                        .username("username")
                        .addBadge(
                            XAutocompleteSearchResponse.User.Badge.builder()
                                .badgeType("badgeType")
                                .badgeUrl("badgeUrl")
                                .description("description")
                                .build()
                        )
                        .inline(true)
                        .isBlueVerified(true)
                        .isPersonaMediaGenable(true)
                        .isVerified(true)
                        .location("location")
                        .profilePicture("profilePicture")
                        .profilePictureOriginal("profilePictureOriginal")
                        .protected(true)
                        .score(0.0)
                        .addToken("token")
                        .verified(true)
                        .verifiedType("verifiedType")
                        .build()
                )
                .build()

        val roundtrippedXAutocompleteSearchResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(xAutocompleteSearchResponse),
                jacksonTypeRef<XAutocompleteSearchResponse>(),
            )

        assertThat(roundtrippedXAutocompleteSearchResponse).isEqualTo(xAutocompleteSearchResponse)
    }
}
