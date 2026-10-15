// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.services

import com.github.tomakehurst.wiremock.client.WireMock.equalTo
import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor
import com.github.tomakehurst.wiremock.client.WireMock.okJson
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo
import com.github.tomakehurst.wiremock.client.WireMock.verify
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo
import com.github.tomakehurst.wiremock.junit5.WireMockTest
import com.x_twitter_scraper.api.client.XTwitterScraperClient
import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClient
import com.x_twitter_scraper.api.models.x.tweets.TweetCheckRepostParams
import com.x_twitter_scraper.api.models.x.tweets.TweetEmbedParams
import com.x_twitter_scraper.api.models.x.tweets.TweetGetHiddenRepliesParams
import com.x_twitter_scraper.api.models.x.tweets.TweetGetInsightsParams
import com.x_twitter_scraper.api.models.x.tweets.TweetGetRetweeterIdsParams
import com.x_twitter_scraper.api.models.x.tweets.TweetGetSubtitlesParams
import com.x_twitter_scraper.api.models.x.tweets.TweetListTopParams
import com.x_twitter_scraper.api.models.x.tweets.TweetResolveLinksParams
import com.x_twitter_scraper.api.models.x.tweets.TweetTranslateParams
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.ResourceLock

@WireMockTest
@ResourceLock("https://github.com/wiremock/wiremock/issues/169")
internal class XTweetsExampleTest {

    private lateinit var client: XTwitterScraperClient

    @BeforeEach
    fun beforeEach(wmRuntimeInfo: WireMockRuntimeInfo) {
        client =
            XTwitterScraperOkHttpClient.builder()
                .baseUrl(wmRuntimeInfo.httpBaseUrl)
                .apiKey("My API Key")
                .bearerToken("My Bearer Token")
                .responseValidation(true)
                .build()
    }

    @Test
    suspend fun listTop() {
        stubFor(
            get(urlPathEqualTo("/x/tweets/top"))
                .willReturn(okJson(ExampleResponses.read("examples/x/tweets/PaginatedTweets.json")))
        )
        val params =
            TweetListTopParams.builder()
                .country("US")
                .language("en")
                .metric(TweetListTopParams.Metric.LIKES)
                .build()

        val responses =
            listOf(
                client.x().tweets().listTop(params),
                client.async().x().tweets().listTop(params),
            )

        verify(
            2,
            getRequestedFor(urlPathEqualTo("/x/tweets/top"))
                .withQueryParam("country", equalTo("US"))
                .withQueryParam("language", equalTo("en"))
                .withQueryParam("metric", equalTo("likes")),
        )
        responses.forEach { response ->
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
    }

    @Test
    suspend fun embed() {
        stubFor(
            get(urlPathEqualTo("/x/tweets/2102882545427714266/embed"))
                .willReturn(okJson(ExampleResponses.read("examples/x/tweets/embed.json")))
        )
        val params =
            TweetEmbedParams.builder()
                .id("2102882545427714266")
                .hideMedia(true)
                .hideThread(true)
                .omitScript(true)
                .build()

        val responses =
            listOf(
                client.x().tweets().embed(params),
                client.async().x().tweets().embed(params),
            )

        verify(
            2,
            getRequestedFor(urlPathEqualTo("/x/tweets/2102882545427714266/embed"))
                .withQueryParam("hideMedia", equalTo("true"))
                .withQueryParam("hideThread", equalTo("true"))
                .withQueryParam("omitScript", equalTo("true")),
        )
        responses.forEach { response ->
            assertThat(response.embed().authorName()).isEqualTo("NASA")
            assertThat(response.embed().authorUrl()).isEqualTo("https://x.com/NASA")
            assertThat(response.embed().html())
                .isEqualTo("<blockquote class=\"twitter-tweet\">...</blockquote>")
            assertThat(response.embed().tweetId()).isEqualTo("2102882545427714266")
            assertThat(response.embed().url())
                .isEqualTo("https://x.com/NASA/status/2102882545427714266")
            assertThat(response.embed().width()).isEqualTo(550L)
        }
    }

    @Test
    suspend fun getHiddenReplies() {
        stubFor(
            get(urlPathEqualTo("/x/tweets/2102882545427714266/hidden-replies"))
                .willReturn(okJson(ExampleResponses.read("examples/x/tweets/PaginatedTweets.json")))
        )
        val params =
            TweetGetHiddenRepliesParams.builder()
                .id("2102882545427714266")
                .anyWords("anyWords")
                .blueVerifiedOnly(true)
                .cashtags("cashtags")
                .build()

        val responses =
            listOf(
                client.x().tweets().getHiddenReplies(params),
                client.async().x().tweets().getHiddenReplies(params),
            )

        verify(
            2,
            getRequestedFor(urlPathEqualTo("/x/tweets/2102882545427714266/hidden-replies"))
                .withQueryParam("anyWords", equalTo("anyWords"))
                .withQueryParam("blueVerifiedOnly", equalTo("true"))
                .withQueryParam("cashtags", equalTo("cashtags")),
        )
        responses.forEach { response ->
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
    }

    @Test
    suspend fun getInsights() {
        stubFor(
            get(urlPathEqualTo("/x/tweets/2102882545427714266/insights"))
                .willReturn(okJson(ExampleResponses.read("examples/x/tweets/getInsights.json")))
        )
        val params = TweetGetInsightsParams.builder().id("2102882545427714266").build()

        val responses =
            listOf(
                client.x().tweets().getInsights(params),
                client.async().x().tweets().getInsights(params),
            )

        verify(
            2,
            getRequestedFor(urlPathEqualTo("/x/tweets/2102882545427714266/insights")),
        )
        responses.forEach { response ->
            assertThat(response.insights().ageHours()).isEqualTo(133.88)
            assertThat(response.insights().amplification()).isEqualTo(0.0964)
            assertThat(response.insights().authorId()).isEqualTo("11348282")
            assertThat(response.insights().authorUserName()).isEqualTo("NASA")
            assertThat(response.insights().conversation()).isEqualTo(0.0229)
            assertThat(response.insights().createdAt())
                .isEqualTo(OffsetDateTime.parse("2026-09-23T22:07:23.000Z"))
            assertThat(response.insights().engagementPerView()).isEqualTo(0.7839)
            assertThat(response.insights().engagementRate()).isEqualTo(0.011)
            assertThat(response.insights().engagements()).isEqualTo(10122.0)
            assertThat(response.insights().engagementsPerHour()).isEqualTo(75.605)
            assertThat(response.insights().metrics().bookmarks()).isEqualTo(661.0)
            assertThat(response.insights().metrics().likes()).isEqualTo(9043.0)
            assertThat(response.insights().metrics().quotes()).isEqualTo(100.0)
            assertThat(response.insights().metrics().replies()).isEqualTo(207.0)
            assertThat(response.insights().metrics().reposts()).isEqualTo(772.0)
            assertThat(response.insights().metrics().views()).isEqualTo(1291231.0)
            assertThat(response.insights().tweetId()).isEqualTo("2102882545427714266")
        }
    }

    @Test
    suspend fun getRetweeterIds() {
        stubFor(
            get(urlPathEqualTo("/x/tweets/2102882545427714266/retweeter-ids"))
                .willReturn(
                    okJson(ExampleResponses.read("examples/x/tweets/PaginatedUserIds.json"))
                )
        )
        val params =
            TweetGetRetweeterIdsParams.builder().id("2102882545427714266").pageSize(200L).build()

        val responses =
            listOf(
                client.x().tweets().getRetweeterIds(params),
                client.async().x().tweets().getRetweeterIds(params),
            )

        verify(
            2,
            getRequestedFor(urlPathEqualTo("/x/tweets/2102882545427714266/retweeter-ids"))
                .withQueryParam("pageSize", equalTo("200")),
        )
        responses.forEach { response ->
            assertThat(response.hasNextPage()).isEqualTo(true)
            assertThat(response.ids()).containsExactly("9876543210", "44196397")
            assertThat(response.nextCursor()).isEqualTo("1878079982871151535")
        }
    }

    @Test
    suspend fun checkRepost() {
        stubFor(
            get(urlPathEqualTo("/x/tweets/2102882545427714266/retweeters/check"))
                .willReturn(okJson(ExampleResponses.read("examples/x/tweets/checkRepost.json")))
        )
        val params = TweetCheckRepostParams.builder().id("2102882545427714266").user("nasa").build()

        val responses =
            listOf(
                client.x().tweets().checkRepost(params),
                client.async().x().tweets().checkRepost(params),
            )

        verify(
            2,
            getRequestedFor(urlPathEqualTo("/x/tweets/2102882545427714266/retweeters/check"))
                .withQueryParam("user", equalTo("nasa")),
        )
        responses.forEach { response ->
            assertThat(response.checkedPosts()).isEqualTo(20L)
            assertThat(response.complete()).isEqualTo(false)
            assertThat(response.isRetweeted()).isEqualTo(true)
            assertThat(response.retweetId()).isEqualTo("2098456494362460375")
            assertThat(response.retweetedAt()).isEqualTo("2026-09-11T16:59:50.000Z")
            assertThat(response.tweetId()).isEqualTo("2098439298617856319")
            assertThat(response.userId()).isEqualTo("3065618342")
        }
    }

    @Test
    suspend fun getSubtitles() {
        stubFor(
            get(urlPathEqualTo("/x/tweets/2102882545427714266/subtitles"))
                .willReturn(okJson(ExampleResponses.read("examples/x/tweets/getSubtitles.json")))
        )
        val params = TweetGetSubtitlesParams.builder().id("2102882545427714266").build()

        val responses =
            listOf(
                client.x().tweets().getSubtitles(params),
                client.async().x().tweets().getSubtitles(params),
            )

        verify(
            2,
            getRequestedFor(urlPathEqualTo("/x/tweets/2102882545427714266/subtitles")),
        )
        responses.forEach { response ->
            assertThat(response.subtitles().tracks()).hasSize(1)
            assertThat(response.subtitles().tracks()[0].autoGenerated()).isEqualTo(false)
            assertThat(response.subtitles().tracks()[0].cues()).hasSize(1)
            assertThat(response.subtitles().tracks()[0].cues()[0].endMs()).isEqualTo(3370L)
            assertThat(response.subtitles().tracks()[0].cues()[0].startMs()).isEqualTo(0L)
            assertThat(response.subtitles().tracks()[0].cues()[0].text())
                .isEqualTo("This asteroid\n2024 YR4 might get interesting.")
            assertThat(response.subtitles().tracks()[0].text())
                .isEqualTo("This asteroid 2024 YR4 might get interesting.")
            assertThat(response.subtitles().tracks()[0].truncated()).isEqualTo(false)
            assertThat(response.subtitles().tracks()[0].url())
                .isEqualTo(
                    "https://video.twimg.com/subtitles/amplify_video/2104593621043314689/0/-FdxBBY2iGcoP5wb.vtt"
                )
            assertThat(response.subtitles().tracks()[0].language()).isEqualTo("en")
            assertThat(response.subtitles().tracks()[0].mediaKey())
                .isEqualTo("13_2104593621043314689")
            assertThat(response.subtitles().tracks()[0].name())
                .isEqualTo("Planetary Defenders_Caption.en_US.srt")
            assertThat(response.subtitles().tweetId()).isEqualTo("2104593672624886205")
        }
    }

    @Test
    suspend fun translate() {
        stubFor(
            get(urlPathEqualTo("/x/tweets/2102882545427714266/translation"))
                .willReturn(okJson(ExampleResponses.read("examples/x/tweets/translate.json")))
        )
        val params = TweetTranslateParams.builder().id("2102882545427714266").language("tr").build()

        val responses =
            listOf(
                client.x().tweets().translate(params),
                client.async().x().tweets().translate(params),
            )

        verify(
            2,
            getRequestedFor(urlPathEqualTo("/x/tweets/2102882545427714266/translation"))
                .withQueryParam("language", equalTo("tr")),
        )
        responses.forEach { response ->
            assertThat(response.translation().entities().urls()!!).hasSize(1)
            assertThat(response.translation().entities().urls()!![0].displayUrl())
                .isEqualTo("go.nasa.gov/3VcvAQn")
            assertThat(response.translation().entities().urls()!![0].expandedUrl())
                .isEqualTo("https://go.nasa.gov/3VcvAQn")
            assertThat(response.translation().entities().urls()!![0].indices()!!)
                .containsExactly(49L, 72L)
            assertThat(response.translation().entities().urls()!![0].url())
                .isEqualTo("https://t.co/Xe1nm2pkSX")
            assertThat(response.translation().language()).isEqualTo("tr")
            assertThat(response.translation().text())
                .isEqualTo(
                    "Artemis Anlaşmaları’na Hoş Geldiniz, Hırvatistan https://t.co/Xe1nm2pkSX"
                )
            assertThat(response.translation().tweetId()).isEqualTo("2102882545427714266")
            assertThat(response.translation().sourceLanguage()).isEqualTo("en")
        }
    }

    @Test
    suspend fun resolveLinks() {
        stubFor(
            get(urlPathEqualTo("/x/links/resolve"))
                .willReturn(okJson(ExampleResponses.read("examples/x/tweets/resolveLinks.json")))
        )
        val params = TweetResolveLinksParams.builder().urls("https://t.co/Xe1nm2pkSX").build()

        val responses =
            listOf(
                client.x().tweets().resolveLinks(params),
                client.async().x().tweets().resolveLinks(params),
            )

        verify(
            2,
            getRequestedFor(urlPathEqualTo("/x/links/resolve"))
                .withQueryParam("urls", equalTo("https://t.co/Xe1nm2pkSX")),
        )
        responses.forEach { response ->
            assertThat(response.links()).hasSize(1)
            assertThat(response.links()[0].url()).isEqualTo("https://t.co/Xe1nm2pkSX")
            assertThat(response.links()[0].resolvedUrl()).isEqualTo("https://go.nasa.gov/3VcvAQn")
            assertThat(response.unprocessedUrls()).hasSize(0)
        }
    }
}
