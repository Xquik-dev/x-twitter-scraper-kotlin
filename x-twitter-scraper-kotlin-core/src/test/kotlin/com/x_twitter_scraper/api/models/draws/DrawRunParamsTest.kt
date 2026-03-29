// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.models.draws

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DrawRunParamsTest {

    @Test
    fun create() {
        DrawRunParams.builder()
            .tweetUrl("https://example.com")
            .backupCount(0L)
            .filterAccountAgeDays(0L)
            .filterLanguage("filterLanguage")
            .filterMinFollowers(0L)
            .mustFollowUsername("mustFollowUsername")
            .mustRetweet(true)
            .addRequiredHashtag("string")
            .addRequiredKeyword("string")
            .addRequiredMention("string")
            .uniqueAuthorsOnly(true)
            .winnerCount(0L)
            .build()
    }

    @Test
    fun body() {
        val params =
            DrawRunParams.builder()
                .tweetUrl("https://example.com")
                .backupCount(0L)
                .filterAccountAgeDays(0L)
                .filterLanguage("filterLanguage")
                .filterMinFollowers(0L)
                .mustFollowUsername("mustFollowUsername")
                .mustRetweet(true)
                .addRequiredHashtag("string")
                .addRequiredKeyword("string")
                .addRequiredMention("string")
                .uniqueAuthorsOnly(true)
                .winnerCount(0L)
                .build()

        val body = params._body()

        assertThat(body.tweetUrl()).isEqualTo("https://example.com")
        assertThat(body.backupCount()).isEqualTo(0L)
        assertThat(body.filterAccountAgeDays()).isEqualTo(0L)
        assertThat(body.filterLanguage()).isEqualTo("filterLanguage")
        assertThat(body.filterMinFollowers()).isEqualTo(0L)
        assertThat(body.mustFollowUsername()).isEqualTo("mustFollowUsername")
        assertThat(body.mustRetweet()).isEqualTo(true)
        assertThat(body.requiredHashtags()).containsExactly("string")
        assertThat(body.requiredKeywords()).containsExactly("string")
        assertThat(body.requiredMentions()).containsExactly("string")
        assertThat(body.uniqueAuthorsOnly()).isEqualTo(true)
        assertThat(body.winnerCount()).isEqualTo(0L)
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params = DrawRunParams.builder().tweetUrl("https://example.com").build()

        val body = params._body()

        assertThat(body.tweetUrl()).isEqualTo("https://example.com")
    }
}
