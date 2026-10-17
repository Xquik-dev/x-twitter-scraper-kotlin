package com.x_twitter_scraper.api.models.x.communities

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.JsonValue
import com.x_twitter_scraper.api.core.jsonMapper
import com.x_twitter_scraper.api.models.Community
import com.x_twitter_scraper.api.models.UserProfile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CommunityRetrieveInfoResponseTest {

    @Test
    fun create() {
        val community =
            Community.builder()
                .id("1500000000000000000")
                .name("Tesla Fans")
                .memberCount(10000L)
                .isNsfw(false)
                .creator(
                    Community.Creator.ofUserProfile(
                        UserProfile.builder()
                            .id("44196397")
                            .username("elonmusk")
                            .name("Elon Musk")
                            .verified(true)
                            .build()
                    )
                )
                .primaryTopic(Community.PrimaryTopic.builder().name("Technology").build())
                .addRule(Community.Rule.builder().name("Be respectful").build())
                .build()
        val communityRetrieveInfoResponse =
            CommunityRetrieveInfoResponse.builder().community(community).build()

        assertThat(communityRetrieveInfoResponse.community()).isEqualTo(community)
        assertThat(communityRetrieveInfoResponse.community().creator()?.asUserProfile()?.username())
            .isEqualTo("elonmusk")
        assertThat(communityRetrieveInfoResponse.validate()).isSameAs(communityRetrieveInfoResponse)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val communityRetrieveInfoResponse =
            CommunityRetrieveInfoResponse.builder()
                .community(
                    Community.builder()
                        .id("1500000000000000000")
                        .name("Tesla Fans")
                        .memberCount(10000L)
                        .isNsfw(false)
                        .putAdditionalProperty("futureField", JsonValue.from("value"))
                        .build()
                )
                .build()

        val roundtrippedCommunityRetrieveInfoResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(communityRetrieveInfoResponse),
                jacksonTypeRef<CommunityRetrieveInfoResponse>(),
            )

        assertThat(roundtrippedCommunityRetrieveInfoResponse)
            .isEqualTo(communityRetrieveInfoResponse)
        assertThat(roundtrippedCommunityRetrieveInfoResponse.validate())
            .isSameAs(roundtrippedCommunityRetrieveInfoResponse)
    }

    @Test
    fun readsCreatorVariantsAndPreservesAdditionalFields() {
        val jsonMapper = jsonMapper()
        val minimal =
            jsonMapper
                .readValue(
                    """{"community":{"id":"1500000000000000000","name":"Tesla Fans","member_count":10000,"is_nsfw":false,"creator":{"id":"44196397","username":"elonmusk","verified":true,"futureField":42}}}""",
                    jacksonTypeRef<CommunityRetrieveInfoResponse>(),
                )
                .validate()
        val profile =
            jsonMapper
                .readValue(
                    """{"community":{"id":"1500000000000000000","name":"Tesla Fans","member_count":10000,"is_nsfw":false,"creator":{"id":"44196397","username":"elonmusk","name":"Elon Musk","verified":true,"futureField":42}}}""",
                    jacksonTypeRef<CommunityRetrieveInfoResponse>(),
                )
                .validate()

        assertThat(minimal.community().creator()?.asUnionMember1()?.username())
            .isEqualTo("elonmusk")
        assertThat(minimal.community().creator()?.asUnionMember1()?._additionalProperties())
            .containsEntry("futureField", JsonValue.from(42))
        assertThat(profile.community().creator()?.asUserProfile()?.name()).isEqualTo("Elon Musk")
        assertThat(profile.community().creator()?.asUserProfile()?._additionalProperties())
            .containsEntry("futureField", JsonValue.from(42))
    }
}
