// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.users

import com.x_twitter_scraper.api.core.http.QueryParams
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserRetrieveVerifiedFollowersParamsTest {

    @Test
    fun create() {
        UserRetrieveVerifiedFollowersParams.builder()
            .id("id")
            .after("after")
            .bioContains("bioContains")
            .blueVerifiedOnly(true)
            .businessEmailOnly(true)
            .cursor("cursor")
            .emailDomains("acme.com,stanford.edu")
            .enrichProfiles(true)
            .fields("id,text,author.username")
            .hasEmail(true)
            .hasLocation(true)
            .hasWebsite(true)
            .limit(0L)
            .locationContains("locationContains")
            .maxFollowers(0L)
            .maxFollowing(0L)
            .maxScan(10000L)
            .maxStatuses(0L)
            .minAccountAgeDays(0L)
            .minFollowers(0L)
            .minFollowing(0L)
            .minStatuses(0L)
            .mode(UserRetrieveVerifiedFollowersParams.Mode.STANDARD)
            .omit("entities,media")
            .pageSize(200L)
            .usernameContains("usernameContains")
            .verifiedOnly(true)
            .verifiedType("verifiedType")
            .build()
    }

    @Test
    fun pathParams() {
        val params = UserRetrieveVerifiedFollowersParams.builder().id("id").build()

        assertThat(params._pathParam(0)).isEqualTo("id")
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun queryParams() {
        val params =
            UserRetrieveVerifiedFollowersParams.builder()
                .id("id")
                .after("after")
                .bioContains("bioContains")
                .blueVerifiedOnly(true)
                .businessEmailOnly(true)
                .cursor("cursor")
                .emailDomains("acme.com,stanford.edu")
                .enrichProfiles(true)
                .fields("id,text,author.username")
                .hasEmail(true)
                .hasLocation(true)
                .hasWebsite(true)
                .limit(0L)
                .locationContains("locationContains")
                .maxFollowers(0L)
                .maxFollowing(0L)
                .maxScan(10000L)
                .maxStatuses(0L)
                .minAccountAgeDays(0L)
                .minFollowers(0L)
                .minFollowing(0L)
                .minStatuses(0L)
                .mode(UserRetrieveVerifiedFollowersParams.Mode.STANDARD)
                .omit("entities,media")
                .pageSize(200L)
                .usernameContains("usernameContains")
                .verifiedOnly(true)
                .verifiedType("verifiedType")
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams)
            .isEqualTo(
                QueryParams.builder()
                    .put("after", "after")
                    .put("bioContains", "bioContains")
                    .put("blueVerifiedOnly", "true")
                    .put("businessEmailOnly", "true")
                    .put("cursor", "cursor")
                    .put("emailDomains", "acme.com,stanford.edu")
                    .put("enrichProfiles", "true")
                    .put("fields", "id,text,author.username")
                    .put("hasEmail", "true")
                    .put("hasLocation", "true")
                    .put("hasWebsite", "true")
                    .put("limit", "0")
                    .put("locationContains", "locationContains")
                    .put("maxFollowers", "0")
                    .put("maxFollowing", "0")
                    .put("maxScan", "10000")
                    .put("maxStatuses", "0")
                    .put("minAccountAgeDays", "0")
                    .put("minFollowers", "0")
                    .put("minFollowing", "0")
                    .put("minStatuses", "0")
                    .put("mode", "standard")
                    .put("omit", "entities,media")
                    .put("pageSize", "200")
                    .put("usernameContains", "usernameContains")
                    .put("verifiedOnly", "true")
                    .put("verifiedType", "verifiedType")
                    .build()
            )
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params = UserRetrieveVerifiedFollowersParams.builder().id("id").build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().build())
    }
}
