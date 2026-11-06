// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.extractions

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.JsonMissing
import com.x_twitter_scraper.api.core.JsonValue
import com.x_twitter_scraper.api.core.jsonMapper
import com.x_twitter_scraper.api.models.TweetMedia
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ExtractionRetrieveResponseTest {

    @Test
    fun create() {
        val extractionRetrieveResponse =
            ExtractionRetrieveResponse.builder()
                .hasMore(true)
                .job(
                    ExtractionRetrieveResponse.Job.builder()
                        .id("id")
                        .completedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .completionReason(
                            ExtractionRetrieveResponse.Job.CompletionReason.BUDGET_LIMITED
                        )
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .effectiveResultsLimit(0L)
                        .errorMessage("errorMessage")
                        .resultsLimit(1L)
                        .status(ExtractionRetrieveResponse.Job.Status.PENDING)
                        .toolType(ExtractionRetrieveResponse.Job.ToolType.FOLLOWER_EXPLORER)
                        .totalResults(0L)
                        .url("https://dashboard.xquik.com/activity/a1")
                        .build()
                )
                .addResult(
                    ExtractionRetrieveResponse.Result.builder()
                        .id("id")
                        .bookmarkCount(0L)
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .email("press@example.com")
                        .enrichmentData(
                            ExtractionRetrieveResponse.Result.EnrichmentData.builder()
                                .putAdditionalProperty("foo", JsonValue.from("bar"))
                                .build()
                        )
                        .likeCount(0L)
                        .addMedia(
                            TweetMedia.builder()
                                .mediaUrl("mediaUrl")
                                .type(TweetMedia.Type.PHOTO)
                                .url("url")
                                .build()
                        )
                        .quoteCount(0L)
                        .replyCount(0L)
                        .retweetCount(0L)
                        .tweetCreatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .tweetId("tweetId")
                        .tweetText("tweetText")
                        .tweetUrl("https://example.com")
                        .viewCount(0L)
                        .xDisplayName("xDisplayName")
                        .xFollowersCount(0L)
                        .xProfileImageUrl("https://example.com")
                        .xUserId("xUserId")
                        .xUsername("xUsername")
                        .xVerified(true)
                        .build()
                )
                .nextCursor("nextCursor")
                .pollAfterMs(0L)
                .waitUrl("/api/v1/extractions/id?wait=10&limit=1&outputMode=compact")
                .build()

        assertThat(extractionRetrieveResponse.hasMore()).isEqualTo(true)
        assertThat(extractionRetrieveResponse.job())
            .isEqualTo(
                ExtractionRetrieveResponse.Job.builder()
                    .id("id")
                    .completedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .completionReason(
                        ExtractionRetrieveResponse.Job.CompletionReason.BUDGET_LIMITED
                    )
                    .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .effectiveResultsLimit(0L)
                    .errorMessage("errorMessage")
                    .resultsLimit(1L)
                    .status(ExtractionRetrieveResponse.Job.Status.PENDING)
                    .toolType(ExtractionRetrieveResponse.Job.ToolType.FOLLOWER_EXPLORER)
                    .totalResults(0L)
                    .url("https://dashboard.xquik.com/activity/a1")
                    .build()
            )
        assertThat(extractionRetrieveResponse.results())
            .containsExactly(
                ExtractionRetrieveResponse.Result.builder()
                    .id("id")
                    .bookmarkCount(0L)
                    .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .email("press@example.com")
                    .enrichmentData(
                        ExtractionRetrieveResponse.Result.EnrichmentData.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .likeCount(0L)
                    .addMedia(
                        TweetMedia.builder()
                            .mediaUrl("mediaUrl")
                            .type(TweetMedia.Type.PHOTO)
                            .url("url")
                            .build()
                    )
                    .quoteCount(0L)
                    .replyCount(0L)
                    .retweetCount(0L)
                    .tweetCreatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .tweetId("tweetId")
                    .tweetText("tweetText")
                    .tweetUrl("https://example.com")
                    .viewCount(0L)
                    .xDisplayName("xDisplayName")
                    .xFollowersCount(0L)
                    .xProfileImageUrl("https://example.com")
                    .xUserId("xUserId")
                    .xUsername("xUsername")
                    .xVerified(true)
                    .build()
            )
        assertThat(extractionRetrieveResponse.nextCursor()).isEqualTo("nextCursor")
        assertThat(extractionRetrieveResponse.pollAfterMs()).isEqualTo(0L)
        assertThat(extractionRetrieveResponse.waitUrl())
            .isEqualTo("/api/v1/extractions/id?wait=10&limit=1&outputMode=compact")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val extractionRetrieveResponse =
            ExtractionRetrieveResponse.builder()
                .hasMore(true)
                .job(
                    ExtractionRetrieveResponse.Job.builder()
                        .id("id")
                        .completedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .completionReason(
                            ExtractionRetrieveResponse.Job.CompletionReason.BUDGET_LIMITED
                        )
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .effectiveResultsLimit(0L)
                        .errorMessage("errorMessage")
                        .resultsLimit(1L)
                        .status(ExtractionRetrieveResponse.Job.Status.PENDING)
                        .toolType(ExtractionRetrieveResponse.Job.ToolType.FOLLOWER_EXPLORER)
                        .totalResults(0L)
                        .url("https://dashboard.xquik.com/activity/a1")
                        .build()
                )
                .addResult(
                    ExtractionRetrieveResponse.Result.builder()
                        .id("id")
                        .bookmarkCount(0L)
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .email("press@example.com")
                        .enrichmentData(
                            ExtractionRetrieveResponse.Result.EnrichmentData.builder()
                                .putAdditionalProperty("foo", JsonValue.from("bar"))
                                .build()
                        )
                        .likeCount(0L)
                        .addMedia(
                            TweetMedia.builder()
                                .mediaUrl("mediaUrl")
                                .type(TweetMedia.Type.PHOTO)
                                .url("url")
                                .build()
                        )
                        .quoteCount(0L)
                        .replyCount(0L)
                        .retweetCount(0L)
                        .tweetCreatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .tweetId("tweetId")
                        .tweetText("tweetText")
                        .tweetUrl("https://example.com")
                        .viewCount(0L)
                        .xDisplayName("xDisplayName")
                        .xFollowersCount(0L)
                        .xProfileImageUrl("https://example.com")
                        .xUserId("xUserId")
                        .xUsername("xUsername")
                        .xVerified(true)
                        .build()
                )
                .nextCursor("nextCursor")
                .pollAfterMs(0L)
                .waitUrl("/api/v1/extractions/id?wait=10&limit=1&outputMode=compact")
                .build()

        val roundtrippedExtractionRetrieveResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(extractionRetrieveResponse),
                jacksonTypeRef<ExtractionRetrieveResponse>(),
            )

        assertThat(roundtrippedExtractionRetrieveResponse).isEqualTo(extractionRetrieveResponse)
    }

    @Test
    fun readsAdditionalFields() {
        val jsonMapper = jsonMapper()

        val extractionRetrieveResponse0 =
            jsonMapper.readValue(
                """{"pollAfterMs":1,"waitUrl":"value"}""",
                jacksonTypeRef<ExtractionRetrieveResponse>(),
            )
        assertThat(extractionRetrieveResponse0.pollAfterMs()).isNotNull()
        assertThat(extractionRetrieveResponse0.waitUrl()).isNotNull()
        assertThat(jsonMapper.readTree(jsonMapper.writeValueAsString(extractionRetrieveResponse0)))
            .isEqualTo(jsonMapper.readTree("""{"pollAfterMs":1,"waitUrl":"value"}"""))
    }

    @Test
    fun keepsAdditionalFieldsOptional() {
        val extractionRetrieveResponse0 =
            ExtractionRetrieveResponse.builder()
                .hasMore(JsonMissing.of())
                .job(JsonMissing.of())
                .results(JsonMissing.of())
                .build()
        assertThat(extractionRetrieveResponse0.pollAfterMs()).isNull()
        assertThat(extractionRetrieveResponse0.waitUrl()).isNull()
    }

    @Test
    fun readsTheQuotedPostOfAFlatRow() {
        val result =
            jsonMapper()
                .readValue(
                    """
                    {
                      "id": "1",
                      "xUserId": "44196397",
                      "createdAt": "2026-10-16T11:00:00.000Z",
                      "possiblySensitive": true,
                      "quotedMedia": [
                        {
                          "mediaUrl": "https://pbs.twimg.com/media/a.jpg",
                          "type": "photo",
                          "url": "https://t.co/a"
                        }
                      ],
                      "quotedPossiblySensitive": false,
                      "quotedPost": {
                        "id": "2",
                        "text": "Launch day.",
                        "createdAt": "2026-10-15T09:00:00.000Z",
                        "author": {
                          "name": "Example",
                          "userName": "example",
                          "profilePicture": "https://pbs.twimg.com/profile_images/1/a.jpg"
                        }
                      }
                    }
                    """,
                    jacksonTypeRef<ExtractionRetrieveResponse.Result>(),
                )

        val quotedPost = checkNotNull(result.quotedPost())
        val author = checkNotNull(quotedPost.author())

        assertThat(result.validate()).isSameAs(result)
        assertThat(result.possiblySensitive()).isEqualTo(true)
        assertThat(result.quotedPossiblySensitive()).isEqualTo(false)
        assertThat(checkNotNull(result.quotedMedia()).single().mediaUrl())
            .isEqualTo("https://pbs.twimg.com/media/a.jpg")
        assertThat(quotedPost.id()).isEqualTo("2")
        assertThat(quotedPost.text()).isEqualTo("Launch day.")
        assertThat(quotedPost.createdAt()).isEqualTo("2026-10-15T09:00:00.000Z")
        assertThat(author.name()).isEqualTo("Example")
        assertThat(author.userName()).isEqualTo("example")
        assertThat(author.profilePicture())
            .isEqualTo("https://pbs.twimg.com/profile_images/1/a.jpg")
    }
}
