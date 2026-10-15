// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CommunityTest {

    @Test
    fun create() {
        val community =
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

        assertThat(community.id()).isEqualTo("id")
        assertThat(community.isNsfw()).isEqualTo(true)
        assertThat(community.memberCount()).isEqualTo(0L)
        assertThat(community.name()).isEqualTo("name")
        assertThat(community.admin())
            .isEqualTo(UserProfile.builder().id("id").name("name").username("username").build())
        assertThat(community.banner())
            .isEqualTo(
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
        assertThat(community.bannerUrl()).isEqualTo("banner_url")
        assertThat(community.createdAt()).isEqualTo("created_at")
        assertThat(community.creator())
            .isEqualTo(
                Community.Creator.ofUserProfile(
                    UserProfile.builder().id("id").name("name").username("username").build()
                )
            )
        assertThat(community.description()).isEqualTo("description")
        assertThat(community.invitesPolicy()).isEqualTo("invites_policy")
        assertThat(community.joinPolicy()).isEqualTo("join_policy")
        assertThat(community.membersFacepile())
            .containsExactly(
                Community.MembersFacepile.ofUserProfile(
                    UserProfile.builder().id("id").name("name").username("username").build()
                )
            )
        assertThat(community.moderatorCount()).isEqualTo(0L)
        assertThat(community.primaryTopic())
            .isEqualTo(Community.PrimaryTopic.builder().name("name").id("id").build())
        assertThat(community.question()).isEqualTo("What are you working on?")
        assertThat(community.rules())
            .containsExactly(
                Community.Rule.builder().id("id").description("description").name("name").build()
            )
        assertThat(community.searchTags()).containsExactly("buildinpublic")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val community =
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

        val roundtrippedCommunity =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(community),
                jacksonTypeRef<Community>(),
            )

        assertThat(roundtrippedCommunity).isEqualTo(community)
    }

    @Test
    fun readsPartialCreator() {
        val community =
            jsonMapper()
                .readValue(
                    """{"id":"1","name":"Builders","member_count":2,"is_nsfw":false,"creator":{"id":"7","username":"maker","verified":true}}""",
                    jacksonTypeRef<Community>(),
                )

        val creator = community.creator()!!
        assertThat(creator.isUnionMember1()).isTrue()
        assertThat(creator.asUnionMember1().id()).isEqualTo("7")
        assertThat(creator.asUnionMember1().username()).isEqualTo("maker")
        assertThat(creator.asUnionMember1().verified()).isEqualTo(true)
    }

    @Test
    fun readsFullCreator() {
        val community =
            jsonMapper()
                .readValue(
                    """{"id":"1","name":"Builders","member_count":2,"is_nsfw":false,"creator":{"id":"7","name":"Maker","username":"maker"}}""",
                    jacksonTypeRef<Community>(),
                )

        val creator = community.creator()!!
        assertThat(creator.isUserProfile()).isTrue()
        assertThat(creator.asUserProfile().id()).isEqualTo("7")
        assertThat(creator.asUserProfile().name()).isEqualTo("Maker")
        assertThat(creator.asUserProfile().username()).isEqualTo("maker")
    }
}
