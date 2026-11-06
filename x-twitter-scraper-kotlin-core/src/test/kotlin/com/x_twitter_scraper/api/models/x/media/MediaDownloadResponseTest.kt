// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.media

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MediaDownloadResponseTest {

    @Test
    fun create() {
        val mediaDownloadResponse =
            MediaDownloadResponse.builder()
                .cacheHit(false)
                .galleryUrl("https://xquik.com/gallery/abc123")
                .totalMedia(5L)
                .totalTweets(2L)
                .tweetId("1234567890")
                .build()

        assertThat(mediaDownloadResponse.cacheHit()).isEqualTo(false)
        assertThat(mediaDownloadResponse.galleryUrl()).isEqualTo("https://xquik.com/gallery/abc123")
        assertThat(mediaDownloadResponse.totalMedia()).isEqualTo(5L)
        assertThat(mediaDownloadResponse.totalTweets()).isEqualTo(2L)
        assertThat(mediaDownloadResponse.tweetId()).isEqualTo("1234567890")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val mediaDownloadResponse =
            MediaDownloadResponse.builder()
                .cacheHit(false)
                .galleryUrl("https://xquik.com/gallery/abc123")
                .totalMedia(5L)
                .totalTweets(2L)
                .tweetId("1234567890")
                .build()

        val roundtrippedMediaDownloadResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(mediaDownloadResponse),
                jacksonTypeRef<MediaDownloadResponse>(),
            )

        assertThat(roundtrippedMediaDownloadResponse).isEqualTo(mediaDownloadResponse)
    }

    @Test
    fun readsTheCoverAndSizesOfAVideo() {
        val response =
            jsonMapper()
                .readValue(
                    downloadExample(
                        """
                        "mediaType": "video",
                        "posterUrl": "https://media.xquik.com/tweet_1234567890/0.jpg",
                        "qualities": [
                          {
                            "bitrate": 2176000,
                            "height": 720,
                            "url": "https://video.twimg.com/amplify_video/1/vid/avc1/1280x720/a.mp4",
                            "width": 1280
                          }
                        ]
                        """
                    ),
                    jacksonTypeRef<MediaDownloadResponse>(),
                )

        val item = checkNotNull(response.items()).single()
        val quality = item.qualities().single()

        assertThat(response.validate()).isSameAs(response)
        assertThat(item.posterUrl()).isEqualTo("https://media.xquik.com/tweet_1234567890/0.jpg")
        assertThat(quality.bitrate()).isEqualTo(2176000L)
        assertThat(quality.height()).isEqualTo(720L)
        assertThat(quality.width()).isEqualTo(1280L)
        assertThat(quality.url())
            .isEqualTo("https://video.twimg.com/amplify_video/1/vid/avc1/1280x720/a.mp4")
    }

    @Test
    fun readsAPhotoWithoutACoverOrSizes() {
        val response =
            jsonMapper()
                .readValue(
                    downloadExample(""""mediaType": "photo", "posterUrl": null, "qualities": []"""),
                    jacksonTypeRef<MediaDownloadResponse>(),
                )

        val item = checkNotNull(response.items()).single()

        assertThat(response.validate()).isSameAs(response)
        assertThat(item.posterUrl()).isNull()
        assertThat(item.qualities()).isEmpty()
    }

    private fun downloadExample(mediaFields: String): String =
        """
        {
          "tweetId": "1234567890",
          "items": [
            {
              "id": "42",
              "tweetId": "1234567890",
              "mediaUrl": "https://media.xquik.com/tweet_1234567890/0.mp4",
              "fileSize": "2097152",
              "createdAt": "2026-10-16T11:00:00.000Z",
              "authorUsername": "elonmusk",
              "authorDisplayName": "Elon Musk",
              "authorAvatar": null,
              "tweetText": "Launch day.",
              "likes": 1200,
              "retweets": 340,
              "views": 56000,
              $mediaFields
            }
          ]
        }
        """
}
