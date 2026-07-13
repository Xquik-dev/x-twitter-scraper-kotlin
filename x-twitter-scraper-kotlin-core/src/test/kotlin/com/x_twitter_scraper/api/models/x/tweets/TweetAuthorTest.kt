// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.models.x.tweets

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.JsonValue
import com.x_twitter_scraper.api.core.jsonMapper
import com.x_twitter_scraper.api.models.UserProfile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TweetAuthorTest {

    @Test
    fun create() {
        val tweetAuthor =
            TweetAuthor.builder()
                .id("9876543210")
                .name("Elon Musk")
                .username("elonmusk")
                .automatedBy("example_user")
                .canDm(false)
                .communityRole("Member")
                .coverPicture("https://pbs.twimg.com/profile_banners/example.jpg")
                .createdAt("2009-06-02T20:12:29Z")
                .description("CEO of Tesla, SpaceX, and X")
                .favouritesCount(18000L)
                .followers(150000000L)
                .following(500L)
                .hasCustomTimelines(true)
                .isAutomated(false)
                .isBlueVerified(true)
                .isTranslator(false)
                .isVerified(true)
                .location("Austin, TX")
                .mediaCount(1200L)
                .addPinnedTweetId("1234567890")
                .possiblySensitive(false)
                .profileBio(
                    UserProfile.ProfileBio.builder()
                        .putAdditionalProperty("description", JsonValue.from("bar"))
                        .putAdditionalProperty("entities", JsonValue.from("bar"))
                        .build()
                )
                .profileBannerUrl("https://pbs.twimg.com/profile_banners/example.jpg")
                .profilePicture("https://pbs.twimg.com/profile_images/example.jpg")
                .protected(false)
                .statusesCount(35000L)
                .unavailable(false)
                .unavailableReason("suspended")
                .url("https://xquik.com")
                .verified(true)
                .verifiedType("Business")
                .viewerFollowedBy(false)
                .viewerFollowing(true)
                .addWithheldInCountry("DE")
                .build()

        assertThat(tweetAuthor.id()).isEqualTo("9876543210")
        assertThat(tweetAuthor.name()).isEqualTo("Elon Musk")
        assertThat(tweetAuthor.username()).isEqualTo("elonmusk")
        assertThat(tweetAuthor.automatedBy()).isEqualTo("example_user")
        assertThat(tweetAuthor.canDm()).isEqualTo(false)
        assertThat(tweetAuthor.communityRole()).isEqualTo("Member")
        assertThat(tweetAuthor.coverPicture())
            .isEqualTo("https://pbs.twimg.com/profile_banners/example.jpg")
        assertThat(tweetAuthor.createdAt()).isEqualTo("2009-06-02T20:12:29Z")
        assertThat(tweetAuthor.description()).isEqualTo("CEO of Tesla, SpaceX, and X")
        assertThat(tweetAuthor.favouritesCount()).isEqualTo(18000L)
        assertThat(tweetAuthor.followers()).isEqualTo(150000000L)
        assertThat(tweetAuthor.following()).isEqualTo(500L)
        assertThat(tweetAuthor.hasCustomTimelines()).isEqualTo(true)
        assertThat(tweetAuthor.isAutomated()).isEqualTo(false)
        assertThat(tweetAuthor.isBlueVerified()).isEqualTo(true)
        assertThat(tweetAuthor.isTranslator()).isEqualTo(false)
        assertThat(tweetAuthor.isVerified()).isEqualTo(true)
        assertThat(tweetAuthor.location()).isEqualTo("Austin, TX")
        assertThat(tweetAuthor.mediaCount()).isEqualTo(1200L)
        assertThat(tweetAuthor.pinnedTweetIds()).containsExactly("1234567890")
        assertThat(tweetAuthor.possiblySensitive()).isEqualTo(false)
        assertThat(tweetAuthor.profileBio())
            .isEqualTo(
                UserProfile.ProfileBio.builder()
                    .putAdditionalProperty("description", JsonValue.from("bar"))
                    .putAdditionalProperty("entities", JsonValue.from("bar"))
                    .build()
            )
        assertThat(tweetAuthor.profileBannerUrl())
            .isEqualTo("https://pbs.twimg.com/profile_banners/example.jpg")
        assertThat(tweetAuthor.profilePicture())
            .isEqualTo("https://pbs.twimg.com/profile_images/example.jpg")
        assertThat(tweetAuthor.protected()).isEqualTo(false)
        assertThat(tweetAuthor.statusesCount()).isEqualTo(35000L)
        assertThat(tweetAuthor.unavailable()).isEqualTo(false)
        assertThat(tweetAuthor.unavailableReason()).isEqualTo("suspended")
        assertThat(tweetAuthor.url()).isEqualTo("https://xquik.com")
        assertThat(tweetAuthor.verified()).isEqualTo(true)
        assertThat(tweetAuthor.verifiedType()).isEqualTo("Business")
        assertThat(tweetAuthor.viewerFollowedBy()).isEqualTo(false)
        assertThat(tweetAuthor.viewerFollowing()).isEqualTo(true)
        assertThat(tweetAuthor.withheldInCountries()).containsExactly("DE")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val tweetAuthor =
            TweetAuthor.builder()
                .id("9876543210")
                .name("Elon Musk")
                .username("elonmusk")
                .automatedBy("example_user")
                .canDm(false)
                .communityRole("Member")
                .coverPicture("https://pbs.twimg.com/profile_banners/example.jpg")
                .createdAt("2009-06-02T20:12:29Z")
                .description("CEO of Tesla, SpaceX, and X")
                .favouritesCount(18000L)
                .followers(150000000L)
                .following(500L)
                .hasCustomTimelines(true)
                .isAutomated(false)
                .isBlueVerified(true)
                .isTranslator(false)
                .isVerified(true)
                .location("Austin, TX")
                .mediaCount(1200L)
                .addPinnedTweetId("1234567890")
                .possiblySensitive(false)
                .profileBio(
                    UserProfile.ProfileBio.builder()
                        .putAdditionalProperty("description", JsonValue.from("bar"))
                        .putAdditionalProperty("entities", JsonValue.from("bar"))
                        .build()
                )
                .profileBannerUrl("https://pbs.twimg.com/profile_banners/example.jpg")
                .profilePicture("https://pbs.twimg.com/profile_images/example.jpg")
                .protected(false)
                .statusesCount(35000L)
                .unavailable(false)
                .unavailableReason("suspended")
                .url("https://xquik.com")
                .verified(true)
                .verifiedType("Business")
                .viewerFollowedBy(false)
                .viewerFollowing(true)
                .addWithheldInCountry("DE")
                .build()

        val roundtrippedTweetAuthor =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(tweetAuthor),
                jacksonTypeRef<TweetAuthor>(),
            )

        assertThat(roundtrippedTweetAuthor).isEqualTo(tweetAuthor)
    }
}
