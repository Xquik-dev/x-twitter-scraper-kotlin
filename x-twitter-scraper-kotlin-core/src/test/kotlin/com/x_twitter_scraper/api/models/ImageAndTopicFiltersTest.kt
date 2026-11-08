// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import com.x_twitter_scraper.api.models.extractions.ExtractionEstimateCostParams
import com.x_twitter_scraper.api.models.extractions.ExtractionListResponse
import com.x_twitter_scraper.api.models.extractions.ExtractionRetrieveResponse
import com.x_twitter_scraper.api.models.extractions.ExtractionRunParams
import com.x_twitter_scraper.api.models.x.XGetArticleResponse
import com.x_twitter_scraper.api.models.x.media.MediaDownloadParams
import com.x_twitter_scraper.api.models.x.media.MediaDownloadResponse
import com.x_twitter_scraper.api.models.x.tweets.TweetSearchParams
import com.x_twitter_scraper.api.models.x.tweets.TweetSearchResponse
import com.x_twitter_scraper.api.models.x.users.UserRetrieveInsightsParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveMediaParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveRepliesParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveShadowbanParams
import com.x_twitter_scraper.api.models.x.users.UserRetrieveTweetsParams
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

internal class ImageAndTopicFiltersTest {

    private val tweet =
        """{"id":"1","text":"Rust 1.80 is out","bookmarkCount":0,"likeCount":3,"quoteCount":0,"replyCount":1,"retweetCount":0,"viewCount":40"""

    @Test
    fun topicFilteredPageReadsTheFilterAndEachScore() {
        val json =
            """{"tweets":[$tweet,"topicScore":0.92}],"has_next_page":true,"next_cursor":"c1","topicFilter":{"topic":"the Rust programming language","question":"Is this post about the Rust programming language?","cutoff":0.5,"checked":16,"kept":1,"unchecked":[{"id":"2","reason":"no_answer"}]}}"""

        val page = jsonMapper().readValue(json, jacksonTypeRef<PaginatedTweets>()).validate()

        val filter = page.topicFilter()!!
        assertThat(filter.topic()).isEqualTo("the Rust programming language")
        assertThat(filter.question()).isEqualTo("Is this post about the Rust programming language?")
        assertThat(filter.cutoff()).isEqualTo(0.5)
        assertThat(filter.checked()).isEqualTo(16L)
        assertThat(filter.kept()).isEqualTo(1L)
        assertThat(filter.unchecked().single().id()).isEqualTo("2")
        assertThat(filter.unchecked().single().reason()).isEqualTo("no_answer")
        assertThat(page.tweets().single().topicScore()).isEqualTo(0.92)
        assertThat(page.tweets().single().imageScore()).isNull()
        assertThat(page.imageFilter()).isNull()
        assertThat(page.toBuilder().build()).isEqualTo(page)
        assertThat(
                jsonMapper()
                    .readValue(
                        jsonMapper().writeValueAsString(page),
                        jacksonTypeRef<PaginatedTweets>(),
                    )
            )
            .isEqualTo(page)
    }

    @Test
    fun imageFilteredSearchReadsTheFilterAndEachScore() {
        val json =
            """{"tweets":[$tweet,"imageScore":0.81}],"has_next_page":false,"next_cursor":"","imageFilter":{"imageShows":"charts","question":"Does a picture attached to this post show a chart?","cutoff":0.5,"checked":4,"kept":1,"unchecked":[]}}"""

        val response = jsonMapper().readValue(json, jacksonTypeRef<TweetSearchResponse>())

        val page = response.paginatedTweets()!!.validate()
        val filter = page.imageFilter()!!
        assertThat(filter.imageShows()).isEqualTo("charts")
        assertThat(filter.question())
            .isEqualTo("Does a picture attached to this post show a chart?")
        assertThat(filter.cutoff()).isEqualTo(0.5)
        assertThat(filter.checked()).isEqualTo(4L)
        assertThat(filter.kept()).isEqualTo(1L)
        assertThat(filter.unchecked()).isEmpty()
        assertThat(page.tweets().single().imageScore()).isEqualTo(0.81)
        assertThat(page.topicFilter()).isNull()
    }

    @Test
    fun filterSummariesRequireEveryField() {
        assertThatThrownBy {
                TopicFilterSummary.builder()
                    .topic("the Rust programming language")
                    .question("Is this post about the Rust programming language?")
                    .cutoff(0.5)
                    .checked(1L)
                    .kept(1L)
                    .build()
            }
            .isInstanceOf(IllegalStateException::class.java)
            .hasMessage("`unchecked` is required, but was not set")
        assertThatThrownBy {
                ImageFilterSummary.builder()
                    .question("Does a picture attached to this post show a chart?")
                    .cutoff(0.5)
                    .checked(1L)
                    .kept(1L)
                    .unchecked(listOf<ImageFilterSummary.Unchecked>())
                    .build()
            }
            .isInstanceOf(IllegalStateException::class.java)
            .hasMessage("`imageShows` is required, but was not set")
    }

    @Test
    fun readsSendTopicAndImageShows() {
        val search =
            TweetSearchParams.builder()
                .q("rust")
                .topic("the Rust programming language")
                .imageShows("charts")
                .build()
        val tweets = UserRetrieveTweetsParams.builder().id("nasa").topic("rockets").build()
        val replies = UserRetrieveRepliesParams.builder().id("nasa").imageShows("memes").build()
        val media =
            UserRetrieveMediaParams.builder().id("nasa").imageShows("product_photos").build()

        assertThat(search._queryParams().values("topic"))
            .containsExactly("the Rust programming language")
        assertThat(search._queryParams().values("imageShows")).containsExactly("charts")
        assertThat(search.topic()).isEqualTo("the Rust programming language")
        assertThat(tweets._queryParams().values("topic")).containsExactly("rockets")
        assertThat(tweets._queryParams().keys()).doesNotContain("imageShows")
        assertThat(replies._queryParams().values("imageShows")).containsExactly("memes")
        assertThat(media._queryParams().values("imageShows")).containsExactly("product_photos")
        assertThat(media.toBuilder().build()).isEqualTo(media)
        assertThat(media.hashCode()).isEqualTo(media.toBuilder().build().hashCode())
        assertThat(media.toString()).contains("imageShows=product_photos")
    }

    @Test
    fun insightsAndShadowbanSendLimit() {
        val insights = UserRetrieveInsightsParams.builder().id("nasa").limit(50L).build()
        val shadowban = UserRetrieveShadowbanParams.builder().id("nasa").limit(80L).build()

        assertThat(insights._queryParams().values("limit")).containsExactly("50")
        assertThat(shadowban._queryParams().values("limit")).containsExactly("80")
        assertThat(insights.limit()).isEqualTo(50L)
        assertThat(shadowban.toBuilder().limit(null).build()._queryParams().keys())
            .doesNotContain("limit")
    }

    @Test
    fun extractionsAndEstimatesSendTopicAndImageShows() {
        val run =
            ExtractionRunParams.builder()
                .toolType(ExtractionRunParams.ToolType.TWEET_SEARCH_EXTRACTOR)
                .searchQuery("rust")
                .topic("the Rust programming language")
                .build()
        val estimate =
            ExtractionEstimateCostParams.builder()
                .toolType(ExtractionEstimateCostParams.ToolType.TWEET_SEARCH_EXTRACTOR)
                .searchQuery("launch")
                .imageShows("charts")
                .build()

        assertThat(run.topic()).isEqualTo("the Rust programming language")
        assertThat(run.imageShows()).isNull()
        assertThat(jsonMapper().writeValueAsString(run._body()))
            .contains("\"topic\":\"the Rust programming language\"")
            .doesNotContain("imageShows")
        assertThat(estimate.imageShows()).isEqualTo("charts")
        assertThat(jsonMapper().writeValueAsString(estimate._body()))
            .contains("\"imageShows\":\"charts\"")
            .doesNotContain("\"topic\"")
    }

    @Test
    fun extractionJobsReadTheirFilters() {
        val list =
            jsonMapper()
                .readValue(
                    """{"extractions":[{"id":"ext_1","url":"https://dashboard.xquik.com/activity/a1","toolType":"tweet_search_extractor","status":"completed","totalResults":4,"createdAt":"2026-11-08T00:00:00Z","topicFilter":{"topic":"rockets","question":"Is this post about rockets?","cutoff":0.5,"checked":9,"kept":4,"unchecked":[]}}],"hasMore":false}""",
                    jacksonTypeRef<ExtractionListResponse>(),
                )
                .validate()
        val retrieve =
            jsonMapper()
                .readValue(
                    """{"job":{"id":"ext_2","imageFilter":{"imageShows":"memes","question":"Is a picture attached to this post a meme?","cutoff":0.5,"checked":3,"kept":2,"unchecked":[{"id":"9","reason":"no_safe_photos"}]}},"results":[{"id":"r1","xUserId":"44196397","createdAt":"2026-11-08T00:00:00Z","isDeleted":true}],"hasMore":false}""",
                    jacksonTypeRef<ExtractionRetrieveResponse>(),
                )

        assertThat(list.extractions().single().topicFilter()?.kept()).isEqualTo(4L)
        assertThat(list.extractions().single().imageFilter()).isNull()
        val job = retrieve.job()
        assertThat(job.imageFilter()?.imageShows()).isEqualTo("memes")
        assertThat(job.imageFilter()?.unchecked()?.single()?.reason()).isEqualTo("no_safe_photos")
        assertThat(job.topicFilter()).isNull()
        assertThat(retrieve.results().single().isDeleted()).isTrue()
        assertThat(job.toBuilder().build()).isEqualTo(job)
    }

    @Test
    fun mediaDownloadSendsImageShowsAndReadsTheFilter() {
        val params = MediaDownloadParams.builder().tweetId("1").imageShows("charts").build()
        val response =
            jsonMapper()
                .readValue(
                    """{"tweetId":"1","totalMedia":1,"imageFilter":{"imageShows":"charts","question":"Does a picture attached to this post show a chart?","cutoff":0.5,"checked":1,"kept":1,"unchecked":[]}}""",
                    jacksonTypeRef<MediaDownloadResponse>(),
                )

        assertThat(params.imageShows()).isEqualTo("charts")
        assertThat(jsonMapper().writeValueAsString(params._body()))
            .contains("\"imageShows\":\"charts\"")
        assertThat(response.imageFilter()?.checked()).isEqualTo(1L)
        assertThat(response.toBuilder().build()).isEqualTo(response)
    }

    @Test
    fun articleReadsItsUnknownCounts() {
        val response =
            jsonMapper()
                .readValue(
                    """{"article":{"title":"Launch notes","likeCount":0,"unknownCounts":["likeCount","viewCount"]}}""",
                    jacksonTypeRef<XGetArticleResponse>(),
                )
                .validate()

        val article = response.article()
        assertThat(article.unknownCounts())
            .containsExactly(
                SearchTweet.UnknownCount.LIKE_COUNT,
                SearchTweet.UnknownCount.VIEW_COUNT,
            )
        assertThat(
                article
                    .toBuilder()
                    .unknownCounts(listOf<SearchTweet.UnknownCount>())
                    .addUnknownCount(SearchTweet.UnknownCount.BOOKMARK_COUNT)
                    .build()
                    .unknownCounts()
            )
            .containsExactly(SearchTweet.UnknownCount.BOOKMARK_COUNT)
    }
}
