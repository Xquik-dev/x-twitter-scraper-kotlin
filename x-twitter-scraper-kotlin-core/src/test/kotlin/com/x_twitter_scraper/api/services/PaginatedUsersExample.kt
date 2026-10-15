// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.services

import com.x_twitter_scraper.api.models.PaginatedUsers
import org.assertj.core.api.Assertions.assertThat

internal fun assertPaginatedUsersExample(response: PaginatedUsers) {
    assertThat(response.hasNextPage()).isEqualTo(false)
    assertThat(response.nextCursor()).isEqualTo("")
    assertThat(response.users()).hasSize(2)
    assertThat(response.users()[0].id()).isEqualTo("34743251")
    assertThat(response.users()[0].username()).isEqualTo("SpaceX")
    assertThat(response.users()[0].name()).isEqualTo("SpaceX")
    assertThat(response.users()[0].followers()).isEqualTo(41936873L)
    assertThat(response.users()[0].following()).isEqualTo(127L)
    assertThat(response.users()[0].verified()).isEqualTo(true)
    assertThat(response.users()[0].isBlueVerified()).isEqualTo(true)
    assertThat(response.users()[0].isVerified()).isEqualTo(true)
    assertThat(response.users()[0].profilePicture())
        .isEqualTo("https://pbs.twimg.com/profile_images/1697749409851985920/HbrI04tM_normal.jpg")
    assertThat(response.users()[0].coverPicture())
        .isEqualTo("https://pbs.twimg.com/profile_banners/34743251/1681251194")
    assertThat(response.users()[0].profileBannerUrl())
        .isEqualTo("https://pbs.twimg.com/profile_banners/34743251/1681251194")
    assertThat(response.users()[0].description())
        .isEqualTo(
            "SpaceX designs, manufactures and launches the world's most advanced rockets and spacecraft"
        )
    assertThat(response.users()[0].location()).isEqualTo("Earth")
    assertThat(response.users()[0].createdAt()).isEqualTo("Thu Apr 23 21:53:30 +0000 2009")
    assertThat(response.users()[0].statusesCount()).isEqualTo(11834L)
    assertThat(response.users()[0].mediaCount()).isEqualTo(4713L)
    assertThat(response.users()[0].protected()).isEqualTo(false)
    assertThat(response.users()[0].url()).isEqualTo("http://spacex.com")
    assertThat(response.users()[0].favouritesCount()).isEqualTo(529L)
    assertThat(response.users()[0].possiblySensitive()).isEqualTo(false)
    assertThat(response.users()[0].verifiedType()).isEqualTo("Business")
    assertThat(response.users()[0].hasGraduatedAccess()).isEqualTo(true)
    assertThat(response.users()[0].parodyCommentaryFanLabel()).isEqualTo("None")
    assertThat(response.users()[0].profileDescriptionLanguage()).isEqualTo("en")
    assertThat(response.users()[0].profileImageShape()).isEqualTo("Square")
    assertThat(response.users()[0].profileTranslatorType()).isEqualTo("none")
    assertThat(response.users()[0].superFollowEligible()).isEqualTo(false)
}
