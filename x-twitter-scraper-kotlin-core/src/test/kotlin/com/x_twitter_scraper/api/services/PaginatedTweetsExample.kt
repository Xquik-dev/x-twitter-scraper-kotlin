// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.services

import com.x_twitter_scraper.api.models.PaginatedTweets
import org.assertj.core.api.Assertions.assertThat

internal fun assertPaginatedTweetsExample(response: PaginatedTweets) {
    assertThat(response.hasNextPage()).isEqualTo(true)
    assertThat(response.nextCursor())
        .isEqualTo("DAAHCgABHTK6Vr___-sLAAIAAAATMjEwMTc3MDE4NjE1NjA1Mjk1OQgAAwAAAAIAAA")
    assertThat(response.tweets()).hasSize(2)
    assertThat(response.tweets()[0].id()).isEqualTo("2102761519985332442")
    assertThat(response.tweets()[0].text())
        .isEqualTo(
            "This galaxy is a little extra.\n\nMeet Messier 106, a spiral galaxy with an extra set of arms. These two additional arms are made of gas heated up by jets from the galaxy’s central black hole. The jets’ superheated shockwaves are depicted in royal blue by @chandraxray. https://t.co/421Lzs0r0c"
        )
    assertThat(response.tweets()[0].retweetCount()).isEqualTo(1364L)
    assertThat(response.tweets()[0].replyCount()).isEqualTo(271L)
    assertThat(response.tweets()[0].likeCount()).isEqualTo(9775L)
    assertThat(response.tweets()[0].quoteCount()).isEqualTo(85L)
    assertThat(response.tweets()[0].viewCount()).isEqualTo(962488L)
    assertThat(response.tweets()[0].bookmarkCount()).isEqualTo(599L)
    assertThat(response.tweets()[0].conversationId()).isEqualTo("2102761519985332442")
    assertThat(response.tweets()[0].isNoteTweet()).isEqualTo(false)
    assertThat(response.tweets()[0].isQuoteStatus()).isEqualTo(false)
    assertThat(response.tweets()[0].isReply()).isEqualTo(false)
    assertThat(response.tweets()[0].isTranslatable()).isEqualTo(false)
    assertThat(response.tweets()[0].lang()).isEqualTo("en")
    assertThat(response.tweets()[0].possiblySensitive()).isEqualTo(false)
    assertThat(response.tweets()[0].source())
        .isEqualTo("<a href=\"https://www.sprinklr.com\" rel=\"nofollow\">Sprinklr</a>")
    assertThat(response.tweets()[0].type()).isEqualTo("tweet")
    assertThat(response.tweets()[0].url())
        .isEqualTo("https://x.com/NASA/status/2102761519985332442")
    assertThat(response.tweets()[0].viewState()).isEqualTo("EnabledWithCount")
    assertThat(response.tweets()[0].createdAt()).isEqualTo("Wed Sep 23 14:06:28 +0000 2026")
}
