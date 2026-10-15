// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CommunityPageTest {

    @Test
    fun create() {
        val communityPage =
            CommunityPage.builder()
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
                                .id("id")
                                .description("description")
                                .name("name")
                                .build()
                        )
                        .addSearchTag("buildinpublic")
                        .build()
                )
                .hasNextPage(true)
                .nextCursor("WzM1LjUxMjQ5N10=")
                .build()

        assertThat(communityPage.communities())
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
                            .id("id")
                            .description("description")
                            .name("name")
                            .build()
                    )
                    .addSearchTag("buildinpublic")
                    .build()
            )
        assertThat(communityPage.hasNextPage()).isEqualTo(true)
        assertThat(communityPage.nextCursor()).isEqualTo("WzM1LjUxMjQ5N10=")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val communityPage =
            CommunityPage.builder()
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
                                .id("id")
                                .description("description")
                                .name("name")
                                .build()
                        )
                        .addSearchTag("buildinpublic")
                        .build()
                )
                .hasNextPage(true)
                .nextCursor("WzM1LjUxMjQ5N10=")
                .build()

        val roundtrippedCommunityPage =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(communityPage),
                jacksonTypeRef<CommunityPage>(),
            )

        assertThat(roundtrippedCommunityPage).isEqualTo(communityPage)
    }
}
