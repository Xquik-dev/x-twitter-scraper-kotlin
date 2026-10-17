// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.tweets

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.JsonMissing
import com.x_twitter_scraper.api.core.JsonValue
import com.x_twitter_scraper.api.core.jsonMapper
import com.x_twitter_scraper.api.models.SearchTweet
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TweetGetRepliesResponseTest {

    @Test
    fun create() {
        val tweetGetRepliesResponse =
            TweetGetRepliesResponse.builder()
                .hasNextPage(true)
                .nextCursor("next_cursor")
                .addTweet(
                    SearchTweet.builder()
                        .id("id")
                        .bookmarkCount(0L)
                        .likeCount(0L)
                        .quoteCount(0L)
                        .replyCount(0L)
                        .retweetCount(0L)
                        .text("text")
                        .viewCount(0L)
                        .build()
                )
                .diagnostic(
                    TweetGetRepliesResponse.Diagnostic.builder()
                        .complete(true)
                        .coveragePercentage(0.0)
                        .cursorFailures(0L)
                        .duplicateCount(0L)
                        .emptyFalseProgressPages(0L)
                        .malformedCount(0L)
                        .addMissingResponseModulesOrField("missingResponseModulesOrField")
                        .nestedReplyCount(0L)
                        .pagesAttempted(0L)
                        .recommendedFallback("recommendedFallback")
                        .repeatedCursorCount(0L)
                        .reportedReplyCount(0L)
                        .responseTruncated(true)
                        .richness(
                            TweetGetRepliesResponse.Diagnostic.Richness.builder()
                                .article(0L)
                                .author(0L)
                                .card(0L)
                                .communityNote(0L)
                                .createdAt(0L)
                                .engagementCounts(0L)
                                .entities(0L)
                                .language(0L)
                                .media(0L)
                                .quotedOrRepostedTweet(0L)
                                .text(0L)
                                .totalReplies(0L)
                                .url(0L)
                                .build()
                        )
                        .addStrategiesAttempted(
                            TweetGetRepliesResponse.Diagnostic.StrategiesAttempted.builder()
                                .name("name")
                                .newDirectReplies(0L)
                                .newNestedReplies(0L)
                                .pagesAttempted(0L)
                                .stopReason(
                                    TweetGetRepliesResponse.Diagnostic.StrategiesAttempted
                                        .StopReason
                                        .DEADLINE
                                )
                                .addNewDirectReplyPage(0L)
                                .build()
                        )
                        .targetDirectReplies(0L)
                        .uniqueDirectReplies(0L)
                        .unrelatedCount(0L)
                        .replyScopeComplete(true)
                        .replyTreeComplete(true)
                        .sourcesEnded(true)
                        .build()
                )
                .filteredCount(0L)
                .hiddenReplies(1L)
                .limitReached(true)
                .message(
                    "The post has more matching replies than limit. Raise limit to get them all."
                )
                .addNestedReply(
                    SearchTweet.builder()
                        .id("id")
                        .bookmarkCount(0L)
                        .likeCount(0L)
                        .quoteCount(0L)
                        .replyCount(0L)
                        .retweetCount(0L)
                        .text("text")
                        .viewCount(0L)
                        .build()
                )
                .orderPartial(true)
                .partialBefore(OffsetDateTime.parse("2026-10-07T00:00:00.000Z"))
                .readBackTo(OffsetDateTime.parse("2026-10-04T10:07:00.000Z"))
                .searchContext(
                    TweetGetRepliesResponse.SearchContext.builder()
                        .smartTag(
                            TweetGetRepliesResponse.SearchContext.SmartTag.builder()
                                .id("BTC")
                                .active(true)
                                .supportsOhlc(false)
                                .build()
                        )
                        .build()
                )
                .searchedQuery("openai gpt")
                .windowComplete(true)
                .build()

        assertThat(tweetGetRepliesResponse.hasNextPage()).isEqualTo(true)
        assertThat(tweetGetRepliesResponse.nextCursor()).isEqualTo("next_cursor")
        assertThat(tweetGetRepliesResponse.tweets())
            .containsExactly(
                SearchTweet.builder()
                    .id("id")
                    .bookmarkCount(0L)
                    .likeCount(0L)
                    .quoteCount(0L)
                    .replyCount(0L)
                    .retweetCount(0L)
                    .text("text")
                    .viewCount(0L)
                    .build()
            )
        assertThat(tweetGetRepliesResponse.diagnostic())
            .isEqualTo(
                TweetGetRepliesResponse.Diagnostic.builder()
                    .complete(true)
                    .coveragePercentage(0.0)
                    .cursorFailures(0L)
                    .duplicateCount(0L)
                    .emptyFalseProgressPages(0L)
                    .malformedCount(0L)
                    .addMissingResponseModulesOrField("missingResponseModulesOrField")
                    .nestedReplyCount(0L)
                    .pagesAttempted(0L)
                    .recommendedFallback("recommendedFallback")
                    .repeatedCursorCount(0L)
                    .reportedReplyCount(0L)
                    .responseTruncated(true)
                    .richness(
                        TweetGetRepliesResponse.Diagnostic.Richness.builder()
                            .article(0L)
                            .author(0L)
                            .card(0L)
                            .communityNote(0L)
                            .createdAt(0L)
                            .engagementCounts(0L)
                            .entities(0L)
                            .language(0L)
                            .media(0L)
                            .quotedOrRepostedTweet(0L)
                            .text(0L)
                            .totalReplies(0L)
                            .url(0L)
                            .build()
                    )
                    .addStrategiesAttempted(
                        TweetGetRepliesResponse.Diagnostic.StrategiesAttempted.builder()
                            .name("name")
                            .newDirectReplies(0L)
                            .newNestedReplies(0L)
                            .pagesAttempted(0L)
                            .stopReason(
                                TweetGetRepliesResponse.Diagnostic.StrategiesAttempted.StopReason
                                    .DEADLINE
                            )
                            .addNewDirectReplyPage(0L)
                            .build()
                    )
                    .targetDirectReplies(0L)
                    .uniqueDirectReplies(0L)
                    .unrelatedCount(0L)
                    .replyScopeComplete(true)
                    .replyTreeComplete(true)
                    .sourcesEnded(true)
                    .build()
            )
        assertThat(tweetGetRepliesResponse.filteredCount()).isEqualTo(0L)
        assertThat(tweetGetRepliesResponse.hiddenReplies()).isEqualTo(1L)
        assertThat(tweetGetRepliesResponse.limitReached()).isEqualTo(true)
        assertThat(tweetGetRepliesResponse.message())
            .isEqualTo(
                "The post has more matching replies than limit. Raise limit to get them all."
            )
        assertThat(tweetGetRepliesResponse.nestedReplies())
            .containsExactly(
                SearchTweet.builder()
                    .id("id")
                    .bookmarkCount(0L)
                    .likeCount(0L)
                    .quoteCount(0L)
                    .replyCount(0L)
                    .retweetCount(0L)
                    .text("text")
                    .viewCount(0L)
                    .build()
            )
        assertThat(tweetGetRepliesResponse.orderPartial()).isEqualTo(true)
        assertThat(tweetGetRepliesResponse.partialBefore())
            .isEqualTo(OffsetDateTime.parse("2026-10-07T00:00:00.000Z"))
        assertThat(tweetGetRepliesResponse.readBackTo())
            .isEqualTo(OffsetDateTime.parse("2026-10-04T10:07:00.000Z"))
        assertThat(tweetGetRepliesResponse.searchContext())
            .isEqualTo(
                TweetGetRepliesResponse.SearchContext.builder()
                    .smartTag(
                        TweetGetRepliesResponse.SearchContext.SmartTag.builder()
                            .id("BTC")
                            .active(true)
                            .supportsOhlc(false)
                            .build()
                    )
                    .build()
            )
        assertThat(tweetGetRepliesResponse.searchedQuery()).isEqualTo("openai gpt")
        assertThat(tweetGetRepliesResponse.windowComplete()).isEqualTo(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val tweetGetRepliesResponse =
            TweetGetRepliesResponse.builder()
                .hasNextPage(true)
                .nextCursor("next_cursor")
                .addTweet(
                    SearchTweet.builder()
                        .id("id")
                        .bookmarkCount(0L)
                        .likeCount(0L)
                        .quoteCount(0L)
                        .replyCount(0L)
                        .retweetCount(0L)
                        .text("text")
                        .viewCount(0L)
                        .build()
                )
                .diagnostic(
                    TweetGetRepliesResponse.Diagnostic.builder()
                        .complete(true)
                        .coveragePercentage(0.0)
                        .cursorFailures(0L)
                        .duplicateCount(0L)
                        .emptyFalseProgressPages(0L)
                        .malformedCount(0L)
                        .addMissingResponseModulesOrField("missingResponseModulesOrField")
                        .nestedReplyCount(0L)
                        .pagesAttempted(0L)
                        .recommendedFallback("recommendedFallback")
                        .repeatedCursorCount(0L)
                        .reportedReplyCount(0L)
                        .responseTruncated(true)
                        .richness(
                            TweetGetRepliesResponse.Diagnostic.Richness.builder()
                                .article(0L)
                                .author(0L)
                                .card(0L)
                                .communityNote(0L)
                                .createdAt(0L)
                                .engagementCounts(0L)
                                .entities(0L)
                                .language(0L)
                                .media(0L)
                                .quotedOrRepostedTweet(0L)
                                .text(0L)
                                .totalReplies(0L)
                                .url(0L)
                                .build()
                        )
                        .addStrategiesAttempted(
                            TweetGetRepliesResponse.Diagnostic.StrategiesAttempted.builder()
                                .name("name")
                                .newDirectReplies(0L)
                                .newNestedReplies(0L)
                                .pagesAttempted(0L)
                                .stopReason(
                                    TweetGetRepliesResponse.Diagnostic.StrategiesAttempted
                                        .StopReason
                                        .DEADLINE
                                )
                                .addNewDirectReplyPage(0L)
                                .build()
                        )
                        .targetDirectReplies(0L)
                        .uniqueDirectReplies(0L)
                        .unrelatedCount(0L)
                        .replyScopeComplete(true)
                        .replyTreeComplete(true)
                        .sourcesEnded(true)
                        .build()
                )
                .filteredCount(0L)
                .hiddenReplies(1L)
                .limitReached(true)
                .message(
                    "The post has more matching replies than limit. Raise limit to get them all."
                )
                .addNestedReply(
                    SearchTweet.builder()
                        .id("id")
                        .bookmarkCount(0L)
                        .likeCount(0L)
                        .quoteCount(0L)
                        .replyCount(0L)
                        .retweetCount(0L)
                        .text("text")
                        .viewCount(0L)
                        .build()
                )
                .orderPartial(true)
                .partialBefore(OffsetDateTime.parse("2026-10-07T00:00:00.000Z"))
                .readBackTo(OffsetDateTime.parse("2026-10-04T10:07:00.000Z"))
                .searchContext(
                    TweetGetRepliesResponse.SearchContext.builder()
                        .smartTag(
                            TweetGetRepliesResponse.SearchContext.SmartTag.builder()
                                .id("BTC")
                                .active(true)
                                .supportsOhlc(false)
                                .build()
                        )
                        .build()
                )
                .searchedQuery("openai gpt")
                .windowComplete(true)
                .build()

        val roundtrippedTweetGetRepliesResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(tweetGetRepliesResponse),
                jacksonTypeRef<TweetGetRepliesResponse>(),
            )

        assertThat(roundtrippedTweetGetRepliesResponse).isEqualTo(tweetGetRepliesResponse)
    }

    @Test
    fun readsAdditionalFields() {
        val jsonMapper = jsonMapper()

        val tweetGetRepliesResponse0 =
            jsonMapper.readValue(
                """{"filtered_count":1,"hiddenReplies":1,"limit_reached":true,"message":"value","orderPartial":true,"partialBefore":"2026-10-17T00:00:00Z","readBackTo":"2026-10-17T00:00:00Z","searchContext":{},"searchedQuery":"value","windowComplete":true}""",
                jacksonTypeRef<TweetGetRepliesResponse>(),
            )
        assertThat(tweetGetRepliesResponse0.filteredCount()).isNotNull()
        assertThat(tweetGetRepliesResponse0.hiddenReplies()).isNotNull()
        assertThat(tweetGetRepliesResponse0.limitReached()).isNotNull()
        assertThat(tweetGetRepliesResponse0.message()).isNotNull()
        assertThat(tweetGetRepliesResponse0.orderPartial()).isNotNull()
        assertThat(tweetGetRepliesResponse0.partialBefore()).isNotNull()
        assertThat(tweetGetRepliesResponse0.readBackTo()).isNotNull()
        assertThat(tweetGetRepliesResponse0.searchContext()).isNotNull()
        assertThat(tweetGetRepliesResponse0.searchedQuery()).isNotNull()
        assertThat(tweetGetRepliesResponse0.windowComplete()).isNotNull()
        assertThat(jsonMapper.readTree(jsonMapper.writeValueAsString(tweetGetRepliesResponse0)))
            .isEqualTo(
                jsonMapper.readTree(
                    """{"filtered_count":1,"hiddenReplies":1,"limit_reached":true,"message":"value","orderPartial":true,"partialBefore":"2026-10-17T00:00:00Z","readBackTo":"2026-10-17T00:00:00Z","searchContext":{},"searchedQuery":"value","windowComplete":true}"""
                )
            )

        val diagnostic1 =
            jsonMapper.readValue(
                """{"replyScopeComplete":true,"replyTreeComplete":true,"sourcesEnded":true}""",
                jacksonTypeRef<TweetGetRepliesResponse.Diagnostic>(),
            )
        assertThat(diagnostic1.replyScopeComplete()).isNotNull()
        assertThat(diagnostic1.replyTreeComplete()).isNotNull()
        assertThat(diagnostic1.sourcesEnded()).isNotNull()
        assertThat(jsonMapper.readTree(jsonMapper.writeValueAsString(diagnostic1)))
            .isEqualTo(
                jsonMapper.readTree(
                    """{"replyScopeComplete":true,"replyTreeComplete":true,"sourcesEnded":true}"""
                )
            )

        val strategiesAttempted2 =
            jsonMapper.readValue(
                """{"newDirectReplyPages":[]}""",
                jacksonTypeRef<TweetGetRepliesResponse.Diagnostic.StrategiesAttempted>(),
            )
        assertThat(strategiesAttempted2.newDirectReplyPages()).isNotNull()
        assertThat(jsonMapper.readTree(jsonMapper.writeValueAsString(strategiesAttempted2)))
            .isEqualTo(jsonMapper.readTree("""{"newDirectReplyPages":[]}"""))
    }

    @Test
    fun keepsAdditionalFieldsOptional() {
        val tweetGetRepliesResponse0 =
            TweetGetRepliesResponse.builder()
                .hasNextPage(JsonMissing.of())
                .nextCursor(JsonMissing.of())
                .tweets(JsonMissing.of())
                .nestedReplies(JsonMissing.of())
                .build()
        assertThat(tweetGetRepliesResponse0.filteredCount()).isNull()
        assertThat(tweetGetRepliesResponse0.hiddenReplies()).isNull()
        assertThat(tweetGetRepliesResponse0.limitReached()).isNull()
        assertThat(tweetGetRepliesResponse0.message()).isNull()
        assertThat(tweetGetRepliesResponse0.orderPartial()).isNull()
        assertThat(tweetGetRepliesResponse0.partialBefore()).isNull()
        assertThat(tweetGetRepliesResponse0.readBackTo()).isNull()
        assertThat(tweetGetRepliesResponse0.searchContext()).isNull()
        assertThat(tweetGetRepliesResponse0.searchedQuery()).isNull()
        assertThat(tweetGetRepliesResponse0.windowComplete()).isNull()
        val diagnostic1 =
            TweetGetRepliesResponse.Diagnostic.builder()
                .complete(JsonMissing.of())
                .coveragePercentage(JsonMissing.of())
                .cursorFailures(JsonMissing.of())
                .duplicateCount(JsonMissing.of())
                .emptyFalseProgressPages(JsonMissing.of())
                .malformedCount(JsonMissing.of())
                .missingResponseModulesOrFields(JsonMissing.of())
                .nestedReplyCount(JsonMissing.of())
                .pagesAttempted(JsonMissing.of())
                .recommendedFallback(JsonMissing.of())
                .repeatedCursorCount(JsonMissing.of())
                .reportedReplyCount(JsonMissing.of())
                .responseTruncated(JsonMissing.of())
                .richness(JsonMissing.of())
                .strategiesAttempted(JsonMissing.of())
                .targetDirectReplies(JsonMissing.of())
                .uniqueDirectReplies(JsonMissing.of())
                .unrelatedCount(JsonMissing.of())
                .build()
        assertThat(diagnostic1.replyScopeComplete()).isNull()
        assertThat(diagnostic1.replyTreeComplete()).isNull()
        assertThat(diagnostic1.sourcesEnded()).isNull()
        val strategiesAttempted2 =
            TweetGetRepliesResponse.Diagnostic.StrategiesAttempted.builder()
                .name(JsonMissing.of())
                .newDirectReplies(JsonMissing.of())
                .newNestedReplies(JsonMissing.of())
                .pagesAttempted(JsonMissing.of())
                .stopReason(JsonMissing.of())
                .build()
        assertThat(strategiesAttempted2.newDirectReplyPages()).isNull()
    }

    @Test
    fun readsFullyPopulatedEmbeddedRetweet() {
        val jsonMapper = jsonMapper()
        val json =
            """{"has_next_page":false,"next_cursor":"","tweets":[{"id":"1234567890","text":"Just launched!","retweetCount":5,"replyCount":3,"likeCount":42,"quoteCount":1,"viewCount":1500,"bookmarkCount":2,"retweeted_tweet":{"id":"1234567890","text":"Just launched!","type":"tweet","createdAt":"2025-01-15T12:00:00Z","url":"https://x.com/example_user/status/1234567890","lang":"en","isReply":false,"isLimitedReply":false,"isNoteTweet":false,"isPinned":false,"isQuoteStatus":false,"isRetweet":false,"quotedTweetId":"value","quotedTweetPermalink":{"displayUrl":"value","expandedUrl":"value","url":"value"},"quotedTweetUnavailable":{"reason":"Suspended"},"retweetedAt":"2026-09-11T20:24:45.000Z","inReplyToId":"1234567890","inReplyToUserId":"9876543210","inReplyToUsername":"example_user","conversationId":"1234567890","conversationControl":{"allowedCountryCodes":["TR"],"inviteViaMention":true,"mode":"my_network","ownerId":"1902917852849385472","ownerUsername":"value","policy":"by_invitation"},"limitedActions":[{"action":"Reply","prompt":{"ctaType":"SeeConversation","headline":"value","metadata":{},"subtext":"value"}}],"source":"<a href=\"https://mobile.twitter.com\" rel=\"nofollow\">Twitter Web App</a>","displayTextRange":[0,31],"textHighlights":[{"startIndex":5,"endIndex":13}],"contentDisclosure":{"advertising":{"isPaidPromotion":true},"aiGenerated":{"detectionSource":"GrokSignature","hasAiGeneratedMedia":true}},"article":{"coverMedia":{},"id":"value","lifecycleState":{},"metadata":{},"title":"value","previewText":"value","coverMediaUrl":"value"},"authorUnavailable":{"reason":"Suspended","message":"value"},"unavailableAuthorId":"value","card":{"id":"value","name":"value","url":"value","bindingValues":{},"platform":{},"userReferenceErrors":[{"message":"User unavailable","reason":"Suspended"}],"userReferences":[{"id":"11348282","username":"NASA","name":"NASA","followers":92376187,"accountBasedIn":null,"following":115,"verified":true,"isBlueVerified":true,"isVerified":true,"profilePicture":"https://pbs.twimg.com/profile_images/1321163587679784960/0ZxKlEKB_normal.jpg","profilePictureOriginal":"https://pbs.twimg.com/profile_images/1321163587679784960/0ZxKlEKB.jpg","coverPicture":"https://pbs.twimg.com/profile_banners/11348282/1788992290","profileBannerUrl":"https://pbs.twimg.com/profile_banners/11348282/1788992290","description":"Making the seemingly impossible, possible. ✨","location":"Pale Blue Dot","createdAt":"Wed Dec 19 20:20:32 +0000 2007","birthdate":{"day":1,"month":10,"year":1958},"statusesCount":74336,"mediaCount":28160,"protected":false,"url":"http://www.nasa.gov/","contactInfo":{"links":[{"url":"http://www.nasa.gov/","network":"website","source":"url"}]},"favouritesCount":17003,"possiblySensitive":false,"verifiedType":"Government","profile_bio":{"description":"Making the seemingly impossible, possible. ✨","entities":{"description":{},"url":{"urls":[{"display_url":"nasa.gov","expanded_url":"http://www.nasa.gov/","indices":[0,23],"url":"https://t.co/9NkQJKAnuU"}]}}},"businessAccountAffiliatesCount":85,"creatorSubscriptionsCount":0,"hasGraduatedAccess":true,"hasHiddenSubscriptionsOnProfile":false,"highlightsInfo":{"canHighlightTweets":true,"highlightedTweets":"280"},"identityVerification":{"isIdentityVerified":false,"verifiedSinceMsec":"1249674830941"},"isProfileTranslatable":false,"parodyCommentaryFanLabel":"None","profileDescriptionLanguage":"en","profileImageShape":"Square","profileSortEnabled":true,"profileTranslatorType":"regular","superFollowEligible":false,"verificationReason":{"entities":[{"fromIndex":91,"toIndex":101,"url":"https://help.twitter.com/en/rules-and-policies/profile-labels","urlType":"ExternalUrl"}],"text":"This account is verified because it is a government or multilateral organization account.  Learn more"}}]},"cashtagAttachments":[{"cashtag":"\u0024TSLA","candleSize":"5m","timeframe":"1D","startTime":"2026-10-09T13:30:00.000Z","endTime":"2026-10-09T13:42:08.796Z","sizeVariant":1}],"communityId":"value","communityName":"Build in Public","communityNote":{"id":"value","title":"value","shortTitle":"value","subtitle":"value","footer":"value","footerIconType":"value","destinationUrl":"value","iconType":"value","visualStyle":"value","metadata":{}},"hasCommunityNotes":true,"edit":{"editTweetIds":["value"],"editableUntilMsecs":"value","initialTweetId":"value"},"exclusiveContent":{"creatorUserName":"value","subscribeUrl":"value"},"isTranslatable":true,"grokShareAttachment":{},"grokTranslatedPost":{},"jetfuelAttachment":{},"noteTweet":{"id":"value","noteId":"2100306607800229889","text":"value","isExpandable":true,"entities":{"hashtags":[{"indices":[0],"tag":{"id":"U21hcnRUYWc6VFNMQQ==","info":{"info":{"active":true,"name":"Tesla Inc","ticker":"TSLA"}}},"text":"value"}],"smarttags":[{"indices":[0],"seconds":1,"text":"value","tag":{"id":"U21hcnRUYWc6VFNMQQ==","info":{"info":{"active":true,"name":"Tesla Inc","ticker":"TSLA"}}}}],"symbols":[{"indices":[0],"seconds":1,"text":"value","tag":{"id":"U21hcnRUYWc6VFNMQQ==","info":{"info":{"active":true,"name":"Tesla Inc","ticker":"TSLA"}}}}],"timestamps":[{"indices":[0],"seconds":1,"text":"value","tag":{"id":"U21hcnRUYWc6VFNMQQ==","info":{"info":{"active":true,"name":"Tesla Inc","ticker":"TSLA"}}}}],"urls":[{"display_url":"value","expanded_url":"value","indices":[0],"url":"value"}],"user_mentions":[{"id_str":"value","indices":[0],"name":"value","screen_name":"value"}]},"inlineMedia":[{"index":0,"mediaId":"value"}],"richtextTags":[{"fromIndex":0,"toIndex":0,"types":["value"]}]},"place":{"boundingBox":{},"country":"value","countryCode":"value","fullName":"value","id":"value","name":"value","placeType":"value","url":"value"},"coordinates":{"coordinates":[1],"type":"Point"},"geo":{"coordinates":[1],"type":"Point"},"postCta":{},"possiblySensitive":true,"previousCounts":{"bookmarkCount":0,"likeCount":0,"quoteCount":0,"replyCount":0,"retweetCount":0},"reactionContext":{"reactedToPostId":"value","reactedToUser":{"id":"9876543210","name":"Example User","username":"example_user","followers":1200,"verified":true}},"sportsContext":{"gameId":"value","league":"value","title":"value","url":"value","state":"value","statusText":"value","scheduledAtMs":1,"competitors":[{"teamId":"value","color":"value","isActive":true,"isWinner":true,"abbreviation":"value","name":"value","score":"value","logoUrl":"value","darkLogoUrl":"value"}]},"subscriberBadge":{"creatorId":"44196397"},"tombstone":{"type":"value","text":{"text":"value","rtl":true,"entities":[{"fromIndex":0,"toIndex":0,"ref":{"type":"value","url":"value","urlType":"value","text":"value","screenName":"value"}}]}},"interstitial":{"displayType":"NonCompliant","text":{"text":"This Post violated the X Rules about glorifying violence. However, X has determined that it may be in the public's interest for the Post to remain accessible. Learn more","rtl":true,"entities":[{"fromIndex":0,"toIndex":0,"ref":{"type":"TimelineUrl","url":"https://help.x.com/rules-and-policies/public-interest","urlType":"ExternalUrl","text":"value","screenName":"value"}}]}},"unmentionedUserIds":["value"],"viewState":"value","entities":{"hashtags":[{"indices":[0],"tag":{"id":"U21hcnRUYWc6VFNMQQ==","info":{"info":{"active":true,"name":"Tesla Inc","ticker":"TSLA"}}},"text":"value"}],"smarttags":[{"indices":[0],"seconds":1,"text":"value","tag":{"id":"U21hcnRUYWc6VFNMQQ==","info":{"info":{"active":true,"name":"Tesla Inc","ticker":"TSLA"}}}}],"symbols":[{"indices":[0],"seconds":1,"text":"value","tag":{"id":"U21hcnRUYWc6VFNMQQ==","info":{"info":{"active":true,"name":"Tesla Inc","ticker":"TSLA"}}}}],"timestamps":[{"indices":[0],"seconds":1,"text":"value","tag":{"id":"U21hcnRUYWc6VFNMQQ==","info":{"info":{"active":true,"name":"Tesla Inc","ticker":"TSLA"}}}}],"urls":[{"display_url":"value","expanded_url":"value","indices":[0],"url":"value"}],"user_mentions":[{"id_str":"value","indices":[0],"name":"value","screen_name":"value"}]},"scopes":{},"media":[{"mediaUrl":"value","type":"photo","url":"value","adultContent":true,"allowDownload":true,"altText":"value","aspectRatio":[0],"availabilityReason":"Dmcaed","availabilityStatus":"Available","description":"value","displayUrl":"value","durationMillis":0,"expandedUrl":"value","embeddable":true,"faceRects":{},"focusRects":[{"x":0,"y":0,"w":0,"h":0}],"grokImageAnnotation":{"prompt":"value","upsampledPrompt":"value"},"grokPostId":"value","graphicViolence":true,"height":0,"id":"value","indices":[0],"mediaKey":"value","monetizable":true,"otherSensitiveContent":true,"sizes":{},"sourceStatusId":"value","sourceUser":{"id":"11348282","username":"NASA","name":"NASA","followers":92376187,"accountBasedIn":null,"following":115,"verified":true,"isBlueVerified":true,"isVerified":true,"profilePicture":"https://pbs.twimg.com/profile_images/1321163587679784960/0ZxKlEKB_normal.jpg","profilePictureOriginal":"https://pbs.twimg.com/profile_images/1321163587679784960/0ZxKlEKB.jpg","coverPicture":"https://pbs.twimg.com/profile_banners/11348282/1788992290","profileBannerUrl":"https://pbs.twimg.com/profile_banners/11348282/1788992290","description":"Making the seemingly impossible, possible. ✨","location":"Pale Blue Dot","createdAt":"Wed Dec 19 20:20:32 +0000 2007","birthdate":{"day":1,"month":10,"year":1958},"statusesCount":74336,"mediaCount":28160,"protected":false,"url":"http://www.nasa.gov/","contactInfo":{"links":[{"url":"http://www.nasa.gov/","network":"website","source":"url"}]},"favouritesCount":17003,"possiblySensitive":false,"verifiedType":"Government","profile_bio":{"description":"Making the seemingly impossible, possible. ✨","entities":{"description":{},"url":{"urls":[{"display_url":"nasa.gov","expanded_url":"http://www.nasa.gov/","indices":[0,23],"url":"https://t.co/9NkQJKAnuU"}]}}},"businessAccountAffiliatesCount":85,"creatorSubscriptionsCount":0,"hasGraduatedAccess":true,"hasHiddenSubscriptionsOnProfile":false,"highlightsInfo":{"canHighlightTweets":true,"highlightedTweets":"280"},"identityVerification":{"isIdentityVerified":false,"verifiedSinceMsec":"1249674830941"},"isProfileTranslatable":false,"parodyCommentaryFanLabel":"None","profileDescriptionLanguage":"en","profileImageShape":"Square","profileSortEnabled":true,"profileTranslatorType":"regular","superFollowEligible":false,"verificationReason":{"entities":[{"fromIndex":91,"toIndex":101,"url":"https://help.twitter.com/en/rules-and-policies/profile-labels","urlType":"ExternalUrl"}],"text":"This account is verified because it is a government or multilateral organization account.  Learn more"}},"sourceUserId":"value","tags":[{"name":"value","screen_name":"value","type":"value","user_id":"value"}],"title":"value","videoVariants":[{"bitrate":0,"contentType":"value","url":"value"}],"watchNowUrl":"value","visitSiteUrl":"value","width":0}],"retweetCount":5,"replyCount":3,"likeCount":42,"quoteCount":1,"viewCount":1500,"bookmarkCount":2,"author":{"id":"9876543210","name":"Example User","username":"example_user","followers":1200,"verified":true},"parentTweet":{"id":"1234567890","text":"Just launched!","createdAt":"2025-01-15T12:00:00Z","retweetCount":5,"replyCount":3,"likeCount":42,"quoteCount":1,"viewCount":1500,"bookmarkCount":2,"custom":{"deep":[true,1,"value"]}},"quoted_tweet":{"id":"1234567890","text":"Just launched!","createdAt":"2025-01-15T12:00:00Z","retweetCount":5,"replyCount":3,"likeCount":42,"quoteCount":1,"viewCount":1500,"bookmarkCount":2,"custom":{"deep":[true,1,"value"]}},"retweeted_tweet":{"id":"1234567890","text":"Just launched!","bookmarkCount":2,"likeCount":42,"quoteCount":1,"replyCount":3,"retweetCount":5,"viewCount":1500,"isPinned":true,"author":{"id":"9876543210","name":"Example User","username":"example_user","followers":1200,"verified":true},"custom":{"deep":[true,1,"value"]}}}}]}"""

        val response = jsonMapper.readValue(json, jacksonTypeRef<TweetGetRepliesResponse>())
        val retweetedTweet = response.tweets().single().retweetedTweet()!!

        assertThat(retweetedTweet.id()).isEqualTo("1234567890")
        assertThat(retweetedTweet.bookmarkCount()).isEqualTo(2L)
        assertThat(retweetedTweet.likeCount()).isEqualTo(42L)
        assertThat(retweetedTweet.quoteCount()).isEqualTo(1L)
        assertThat(retweetedTweet.replyCount()).isEqualTo(3L)
        assertThat(retweetedTweet.retweetCount()).isEqualTo(5L)
        assertThat(retweetedTweet.text()).isEqualTo("Just launched!")
        assertThat(retweetedTweet.viewCount()).isEqualTo(1500L)
        assertThat(retweetedTweet.article()).isNotNull()
        assertThat(retweetedTweet.author()).isNotNull()
        assertThat(retweetedTweet.authorUnavailable()).isNotNull()
        assertThat(retweetedTweet.card()).isNotNull()
        assertThat(retweetedTweet.cashtagAttachments()).isNotNull()
        assertThat(retweetedTweet.communityId()).isEqualTo("value")
        assertThat(retweetedTweet.communityName()).isEqualTo("Build in Public")
        assertThat(retweetedTweet.communityNote()).isNotNull()
        assertThat(retweetedTweet.contentDisclosure()).isNotNull()
        assertThat(retweetedTweet.conversationControl()).isNotNull()
        assertThat(retweetedTweet.conversationId()).isEqualTo("1234567890")
        assertThat(retweetedTweet.coordinates()).isNotNull()
        assertThat(retweetedTweet.createdAt()).isEqualTo("2025-01-15T12:00:00Z")
        assertThat(retweetedTweet.displayTextRange()).isNotNull()
        assertThat(retweetedTweet.edit()).isNotNull()
        assertThat(retweetedTweet.entities()).isNotNull()
        assertThat(retweetedTweet.exclusiveContent()).isNotNull()
        assertThat(retweetedTweet.geo()).isNotNull()
        assertThat(retweetedTweet.grokShareAttachment()).isNotNull()
        assertThat(retweetedTweet.grokTranslatedPost()).isNotNull()
        assertThat(retweetedTweet.hasCommunityNotes()).isEqualTo(true)
        assertThat(retweetedTweet.inReplyToId()).isEqualTo("1234567890")
        assertThat(retweetedTweet.inReplyToUserId()).isEqualTo("9876543210")
        assertThat(retweetedTweet.inReplyToUsername()).isEqualTo("example_user")
        assertThat(retweetedTweet.interstitial()).isNotNull()
        assertThat(retweetedTweet.isLimitedReply()).isEqualTo(false)
        assertThat(retweetedTweet.isNoteTweet()).isEqualTo(false)
        assertThat(retweetedTweet.isPinned()).isEqualTo(false)
        assertThat(retweetedTweet.isQuoteStatus()).isEqualTo(false)
        assertThat(retweetedTweet.isReply()).isEqualTo(false)
        assertThat(retweetedTweet.isRetweet()).isEqualTo(false)
        assertThat(retweetedTweet.isTranslatable()).isEqualTo(true)
        assertThat(retweetedTweet.jetfuelAttachment()).isNotNull()
        assertThat(retweetedTweet.lang()).isEqualTo("en")
        assertThat(retweetedTweet.limitedActions()).isNotNull()
        assertThat(retweetedTweet.media()).isNotNull()
        assertThat(retweetedTweet.noteTweet()).isNotNull()
        assertThat(retweetedTweet.parentTweet()).isNotNull()
        assertThat(retweetedTweet.place()).isNotNull()
        assertThat(retweetedTweet.possiblySensitive()).isEqualTo(true)
        assertThat(retweetedTweet.postCta()).isNotNull()
        assertThat(retweetedTweet.previousCounts()).isNotNull()
        assertThat(retweetedTweet.quotedTweetId()).isEqualTo("value")
        assertThat(retweetedTweet.quotedTweetPermalink()).isNotNull()
        assertThat(retweetedTweet.quotedTweetUnavailable()).isNotNull()
        assertThat(retweetedTweet.quotedTweet()).isNotNull()
        assertThat(retweetedTweet.reactionContext()).isNotNull()
        assertThat(retweetedTweet.retweetedAt()).isNotNull()
        assertThat(retweetedTweet.retweetedTweet()).isNotNull()
        assertThat(retweetedTweet.scopes()).isNotNull()
        assertThat(retweetedTweet.source())
            .isEqualTo(
                "<a href=\"https://mobile.twitter.com\" rel=\"nofollow\">Twitter Web App</a>"
            )
        assertThat(retweetedTweet.sportsContext()).isNotNull()
        assertThat(retweetedTweet.subscriberBadge()).isNotNull()
        assertThat(retweetedTweet.textHighlights()).isNotNull()
        assertThat(retweetedTweet.tombstone()).isNotNull()
        assertThat(retweetedTweet.type()).isEqualTo("tweet")
        assertThat(retweetedTweet.unavailableAuthorId()).isEqualTo("value")
        assertThat(retweetedTweet.unmentionedUserIds()).isNotNull()
        assertThat(retweetedTweet.url()).isEqualTo("https://x.com/example_user/status/1234567890")
        assertThat(retweetedTweet.viewState()).isEqualTo("value")
        assertThat(retweetedTweet.retweetedAt())
            .isEqualTo(OffsetDateTime.parse("2026-09-11T20:24:45.000Z"))
        assertThat(retweetedTweet.author()!!.username()).isEqualTo("example_user")
        assertThat(retweetedTweet.author()!!.followers()).isEqualTo(1200L)
        assertThat(retweetedTweet.cashtagAttachments()!!.single().cashtag()).isEqualTo("\$TSLA")
        assertThat(retweetedTweet.article()!!.coverMedia()).isNotNull()
        assertThat(retweetedTweet.card()!!.platform()).isNotNull()
        assertThat(retweetedTweet.communityNote()!!.footerIconType()).isNotNull()
        assertThat(retweetedTweet.edit()!!.initialTweetId()).isNotNull()
        assertThat(retweetedTweet.noteTweet()!!.inlineMedia()).hasSize(1)
        assertThat(retweetedTweet.parentTweet()!!.id()).isEqualTo("1234567890")
        assertThat(retweetedTweet.quotedTweet()!!.text()).isEqualTo("Just launched!")
        assertThat(retweetedTweet.retweetedTweet()!!.text()).isEqualTo("Just launched!")
        assertThat(retweetedTweet.retweetedTweet()!!.isPinned()).isEqualTo(true)
        assertThat(retweetedTweet.retweetedTweet()!!.author()!!.username())
            .isEqualTo("example_user")
        assertThat(retweetedTweet.retweetedTweet()!!._additionalProperties()["custom"])
            .isEqualTo(JsonValue.from(mapOf("deep" to listOf(true, 1, "value"))))
        assertThat(retweetedTweet.toBuilder().build()).isEqualTo(retweetedTweet)
        val roundtrippedResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(response),
                jacksonTypeRef<TweetGetRepliesResponse>(),
            )
        assertThat(roundtrippedResponse).isEqualTo(response)
        response.validate()
    }
}
