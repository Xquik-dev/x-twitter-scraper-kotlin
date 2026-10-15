// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.communities

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import com.x_twitter_scraper.api.models.Community
import com.x_twitter_scraper.api.models.UserProfile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CommunityListSuggestedResponseTest {

    @Test
    fun create() {
        val communityListSuggestedResponse =
            CommunityListSuggestedResponse.builder()
                .addCommunity(
                    Community.builder()
                        .id("id")
                        .isNsfw(true)
                        .memberCount(0L)
                        .name("name")
                        .admin(
                            UserProfile.builder().id("id").name("name").username("username").build()
                        )
                        .banner(
                            Community.Banner.builder()
                                .url("url")
                                .addColor(
                                    Community.Banner.Color.builder()
                                        .blue(0L)
                                        .green(0L)
                                        .percentage(0.0)
                                        .red(0L)
                                        .build()
                                )
                                .focus(
                                    Community.Banner.Focus.builder()
                                        .height(0L)
                                        .left(0L)
                                        .top(0L)
                                        .width(0L)
                                        .build()
                                )
                                .height(0L)
                                .width(0L)
                                .build()
                        )
                        .bannerUrl("banner_url")
                        .createdAt("created_at")
                        .creator(
                            Community.Creator.ofUserProfile(
                                UserProfile.builder()
                                    .id("id")
                                    .name("name")
                                    .username("username")
                                    .build()
                            )
                        )
                        .description("description")
                        .invitesPolicy("invites_policy")
                        .joinPolicy("join_policy")
                        .addMembersFacepile(
                            Community.MembersFacepile.ofUserProfile(
                                UserProfile.builder()
                                    .id("id")
                                    .name("name")
                                    .username("username")
                                    .build()
                            )
                        )
                        .moderatorCount(0L)
                        .primaryTopic(
                            Community.PrimaryTopic.builder().name("name").id("id").build()
                        )
                        .question("What are you working on?")
                        .addRule(
                            Community.Rule.builder()
                                .description("description")
                                .id("id")
                                .name("name")
                                .build()
                        )
                        .addSearchTag("buildinpublic")
                        .build()
                )
                .count(1L)
                .message("X suggested no communities. Try again soon.")
                .build()

        assertThat(communityListSuggestedResponse.communities())
            .containsExactly(
                Community.builder()
                    .id("id")
                    .isNsfw(true)
                    .memberCount(0L)
                    .name("name")
                    .admin(UserProfile.builder().id("id").name("name").username("username").build())
                    .banner(
                        Community.Banner.builder()
                            .url("url")
                            .addColor(
                                Community.Banner.Color.builder()
                                    .blue(0L)
                                    .green(0L)
                                    .percentage(0.0)
                                    .red(0L)
                                    .build()
                            )
                            .focus(
                                Community.Banner.Focus.builder()
                                    .height(0L)
                                    .left(0L)
                                    .top(0L)
                                    .width(0L)
                                    .build()
                            )
                            .height(0L)
                            .width(0L)
                            .build()
                    )
                    .bannerUrl("banner_url")
                    .createdAt("created_at")
                    .creator(
                        Community.Creator.ofUserProfile(
                            UserProfile.builder().id("id").name("name").username("username").build()
                        )
                    )
                    .description("description")
                    .invitesPolicy("invites_policy")
                    .joinPolicy("join_policy")
                    .addMembersFacepile(
                        Community.MembersFacepile.ofUserProfile(
                            UserProfile.builder().id("id").name("name").username("username").build()
                        )
                    )
                    .moderatorCount(0L)
                    .primaryTopic(Community.PrimaryTopic.builder().name("name").id("id").build())
                    .question("What are you working on?")
                    .addRule(
                        Community.Rule.builder()
                            .description("description")
                            .id("id")
                            .name("name")
                            .build()
                    )
                    .addSearchTag("buildinpublic")
                    .build()
            )
        assertThat(communityListSuggestedResponse.count()).isEqualTo(1L)
        assertThat(communityListSuggestedResponse.message())
            .isEqualTo("X suggested no communities. Try again soon.")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val communityListSuggestedResponse =
            CommunityListSuggestedResponse.builder()
                .addCommunity(
                    Community.builder()
                        .id("id")
                        .isNsfw(true)
                        .memberCount(0L)
                        .name("name")
                        .admin(
                            UserProfile.builder().id("id").name("name").username("username").build()
                        )
                        .banner(
                            Community.Banner.builder()
                                .url("url")
                                .addColor(
                                    Community.Banner.Color.builder()
                                        .blue(0L)
                                        .green(0L)
                                        .percentage(0.0)
                                        .red(0L)
                                        .build()
                                )
                                .focus(
                                    Community.Banner.Focus.builder()
                                        .height(0L)
                                        .left(0L)
                                        .top(0L)
                                        .width(0L)
                                        .build()
                                )
                                .height(0L)
                                .width(0L)
                                .build()
                        )
                        .bannerUrl("banner_url")
                        .createdAt("created_at")
                        .creator(
                            Community.Creator.ofUserProfile(
                                UserProfile.builder()
                                    .id("id")
                                    .name("name")
                                    .username("username")
                                    .build()
                            )
                        )
                        .description("description")
                        .invitesPolicy("invites_policy")
                        .joinPolicy("join_policy")
                        .addMembersFacepile(
                            Community.MembersFacepile.ofUserProfile(
                                UserProfile.builder()
                                    .id("id")
                                    .name("name")
                                    .username("username")
                                    .build()
                            )
                        )
                        .moderatorCount(0L)
                        .primaryTopic(
                            Community.PrimaryTopic.builder().name("name").id("id").build()
                        )
                        .question("What are you working on?")
                        .addRule(
                            Community.Rule.builder()
                                .description("description")
                                .id("id")
                                .name("name")
                                .build()
                        )
                        .addSearchTag("buildinpublic")
                        .build()
                )
                .count(1L)
                .message("X suggested no communities. Try again soon.")
                .build()

        val roundtrippedCommunityListSuggestedResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(communityListSuggestedResponse),
                jacksonTypeRef<CommunityListSuggestedResponse>(),
            )

        assertThat(roundtrippedCommunityListSuggestedResponse)
            .isEqualTo(communityListSuggestedResponse)
    }
}
