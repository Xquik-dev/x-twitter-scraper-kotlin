// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.services

import com.x_twitter_scraper.api.models.Community
import org.assertj.core.api.Assertions.assertThat

internal fun assertCommunityExample(community: Community) {
    assertThat(community.id()).isEqualTo("1472105760389668865")
    assertThat(community.name()).isEqualTo("Tech Twitter")
    assertThat(community.memberCount()).isEqualTo(121468L)
    assertThat(community.isNsfw()).isEqualTo(false)
    assertThat(community.primaryTopic()!!.name()).isEqualTo("Technology")
    assertThat(community.bannerUrl())
        .isEqualTo("https://pbs.twimg.com/community_banner_img/example.jpg")
    assertThat(community.membersFacepile()).hasSize(1)
    val member = community.membersFacepile()!![0]
    assertThat(member.isCommunityMemberPreview()).isTrue()
    assertThat(member.asCommunityMemberPreview().id()).isEqualTo("2864203879")
    assertThat(member.asCommunityMemberPreview().profilePicture())
        .isEqualTo("https://pbs.twimg.com/profile_images/example.jpg")
}
