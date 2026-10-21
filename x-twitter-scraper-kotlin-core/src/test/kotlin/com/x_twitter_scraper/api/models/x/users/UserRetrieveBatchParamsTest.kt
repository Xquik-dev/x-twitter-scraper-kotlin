// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.users

import com.x_twitter_scraper.api.core.http.QueryParams
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserRetrieveBatchParamsTest {

    @Test
    fun create() {
        UserRetrieveBatchParams.builder()
            .ids("ids")
            .bioContains("bioContains")
            .blueVerifiedOnly(true)
            .businessEmailOnly(true)
            .emailDomains("acme.com,stanford.edu")
            .fields("id,text,author.username")
            .hasEmail(true)
            .hasLocation(true)
            .hasWebsite(true)
            .locationContains("locationContains")
            .maxFollowers(0L)
            .maxFollowing(0L)
            .maxStatuses(0L)
            .minAccountAgeDays(0L)
            .minFollowers(0L)
            .minFollowing(0L)
            .minStatuses(0L)
            .omit("entities,media")
            .usernameContains("usernameContains")
            .verifiedOnly(true)
            .verifiedType("verifiedType")
            .usernames("nasa,@OpenAI")
            .build()
    }

    @Test
    fun queryParams() {
        val params =
            UserRetrieveBatchParams.builder()
                .ids("ids")
                .bioContains("bioContains")
                .blueVerifiedOnly(true)
                .businessEmailOnly(true)
                .emailDomains("acme.com,stanford.edu")
                .fields("id,text,author.username")
                .hasEmail(true)
                .hasLocation(true)
                .hasWebsite(true)
                .locationContains("locationContains")
                .maxFollowers(0L)
                .maxFollowing(0L)
                .maxStatuses(0L)
                .minAccountAgeDays(0L)
                .minFollowers(0L)
                .minFollowing(0L)
                .minStatuses(0L)
                .omit("entities,media")
                .usernameContains("usernameContains")
                .verifiedOnly(true)
                .verifiedType("verifiedType")
                .usernames("nasa,@OpenAI")
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams)
            .isEqualTo(
                QueryParams.builder()
                    .put("ids", "ids")
                    .put("bioContains", "bioContains")
                    .put("blueVerifiedOnly", "true")
                    .put("businessEmailOnly", "true")
                    .put("emailDomains", "acme.com,stanford.edu")
                    .put("fields", "id,text,author.username")
                    .put("hasEmail", "true")
                    .put("hasLocation", "true")
                    .put("hasWebsite", "true")
                    .put("locationContains", "locationContains")
                    .put("maxFollowers", "0")
                    .put("maxFollowing", "0")
                    .put("maxStatuses", "0")
                    .put("minAccountAgeDays", "0")
                    .put("minFollowers", "0")
                    .put("minFollowing", "0")
                    .put("minStatuses", "0")
                    .put("omit", "entities,media")
                    .put("usernameContains", "usernameContains")
                    .put("verifiedOnly", "true")
                    .put("verifiedType", "verifiedType")
                    .put("usernames", "nasa,@OpenAI")
                    .build()
            )
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params = UserRetrieveBatchParams.builder().ids("ids").build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().put("ids", "ids").build())
    }
}
