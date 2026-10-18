// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models

import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.BaseDeserializer
import com.x_twitter_scraper.api.core.BaseSerializer
import com.x_twitter_scraper.api.core.Enum
import com.x_twitter_scraper.api.core.ExcludeMissing
import com.x_twitter_scraper.api.core.JsonField
import com.x_twitter_scraper.api.core.JsonMissing
import com.x_twitter_scraper.api.core.JsonValue
import com.x_twitter_scraper.api.core.allMaxBy
import com.x_twitter_scraper.api.core.checkKnown
import com.x_twitter_scraper.api.core.checkRequired
import com.x_twitter_scraper.api.core.getOrThrow
import com.x_twitter_scraper.api.core.toImmutable
import com.x_twitter_scraper.api.errors.XTwitterScraperInvalidDataException
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects

/**
 * Tweet returned from search results with inline author info. A zero metric can mean X did not
 * report the count.
 */
class SearchTweet
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val bookmarkCount: JsonField<Long>,
    private val unknownCounts: JsonField<List<UnknownCount>>,
    private val likeCount: JsonField<Long>,
    private val quoteCount: JsonField<Long>,
    private val replyCount: JsonField<Long>,
    private val retweetCount: JsonField<Long>,
    private val text: JsonField<String>,
    private val viewCount: JsonField<Long>,
    private val article: JsonField<Article>,
    private val author: JsonField<UserProfile>,
    private val authorUnavailable: JsonField<AuthorUnavailable>,
    private val card: JsonField<Card>,
    private val cashtagAttachments: JsonField<List<CashtagAttachment>>,
    private val communityId: JsonField<String>,
    private val communityName: JsonField<String>,
    private val communityNote: JsonField<CommunityNote>,
    private val contentDisclosure: JsonField<ContentDisclosure>,
    private val conversationControl: JsonField<ConversationControl>,
    private val conversationId: JsonField<String>,
    private val coordinates: JsonField<Coordinates>,
    private val createdAt: JsonField<String>,
    private val displayTextRange: JsonField<List<Long>>,
    private val edit: JsonField<Edit>,
    private val entities: JsonField<Entities>,
    private val exclusiveContent: JsonField<ExclusiveContent>,
    private val geo: JsonField<Geo>,
    private val grokShareAttachment: JsonField<GrokShareAttachment>,
    private val grokTranslatedPost: JsonField<GrokTranslatedPost>,
    private val hasCommunityNotes: JsonField<Boolean>,
    private val inReplyToId: JsonField<String>,
    private val inReplyToUserId: JsonField<String>,
    private val inReplyToUsername: JsonField<String>,
    private val interstitial: JsonField<Interstitial>,
    private val isLimitedReply: JsonField<Boolean>,
    private val isNoteTweet: JsonField<Boolean>,
    private val isPinned: JsonField<Boolean>,
    private val isQuoteStatus: JsonField<Boolean>,
    private val isReply: JsonField<Boolean>,
    private val isRetweet: JsonField<Boolean>,
    private val isTranslatable: JsonField<Boolean>,
    private val jetfuelAttachment: JsonField<JetfuelAttachment>,
    private val lang: JsonField<String>,
    private val limitedActions: JsonField<List<LimitedAction>>,
    private val media: JsonField<List<TweetMedia>>,
    private val noteTweet: JsonField<NoteTweet>,
    private val parentTweet: JsonField<EmbeddedTweet>,
    private val place: JsonField<Place>,
    private val possiblySensitive: JsonField<Boolean>,
    private val postCta: JsonField<PostCta>,
    private val previousCounts: JsonField<PreviousCounts>,
    private val quotedTweetId: JsonField<String>,
    private val quotedTweetPermalink: JsonField<QuotedTweetPermalink>,
    private val quotedTweetUnavailable: JsonField<QuotedTweetUnavailable>,
    private val quotedTweet: JsonField<EmbeddedTweet>,
    private val reactionContext: JsonField<ReactionContext>,
    private val retweetedAt: JsonField<OffsetDateTime>,
    private val retweetedTweet: JsonField<EmbeddedTweet>,
    private val scopes: JsonField<Scopes>,
    private val source: JsonField<String>,
    private val sportsContext: JsonField<SportsContext>,
    private val subscriberBadge: JsonField<SubscriberBadge>,
    private val textHighlights: JsonField<List<TextHighlight>>,
    private val tombstone: JsonField<Tombstone>,
    private val type: JsonField<String>,
    private val unavailableAuthorId: JsonField<String>,
    private val unmentionedUserIds: JsonField<List<String>>,
    private val url: JsonField<String>,
    private val viewState: JsonField<String>,
    private val hiddenByAuthor: JsonField<Boolean>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("bookmarkCount")
        @ExcludeMissing
        bookmarkCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("unknownCounts")
        @ExcludeMissing
        unknownCounts: JsonField<List<UnknownCount>> = JsonMissing.of(),
        @JsonProperty("likeCount") @ExcludeMissing likeCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("quoteCount") @ExcludeMissing quoteCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("replyCount") @ExcludeMissing replyCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("retweetCount")
        @ExcludeMissing
        retweetCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("text") @ExcludeMissing text: JsonField<String> = JsonMissing.of(),
        @JsonProperty("viewCount") @ExcludeMissing viewCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("article") @ExcludeMissing article: JsonField<Article> = JsonMissing.of(),
        @JsonProperty("author") @ExcludeMissing author: JsonField<UserProfile> = JsonMissing.of(),
        @JsonProperty("authorUnavailable")
        @ExcludeMissing
        authorUnavailable: JsonField<AuthorUnavailable> = JsonMissing.of(),
        @JsonProperty("card") @ExcludeMissing card: JsonField<Card> = JsonMissing.of(),
        @JsonProperty("cashtagAttachments")
        @ExcludeMissing
        cashtagAttachments: JsonField<List<CashtagAttachment>> = JsonMissing.of(),
        @JsonProperty("communityId")
        @ExcludeMissing
        communityId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("communityName")
        @ExcludeMissing
        communityName: JsonField<String> = JsonMissing.of(),
        @JsonProperty("communityNote")
        @ExcludeMissing
        communityNote: JsonField<CommunityNote> = JsonMissing.of(),
        @JsonProperty("contentDisclosure")
        @ExcludeMissing
        contentDisclosure: JsonField<ContentDisclosure> = JsonMissing.of(),
        @JsonProperty("conversationControl")
        @ExcludeMissing
        conversationControl: JsonField<ConversationControl> = JsonMissing.of(),
        @JsonProperty("conversationId")
        @ExcludeMissing
        conversationId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("coordinates")
        @ExcludeMissing
        coordinates: JsonField<Coordinates> = JsonMissing.of(),
        @JsonProperty("createdAt") @ExcludeMissing createdAt: JsonField<String> = JsonMissing.of(),
        @JsonProperty("displayTextRange")
        @ExcludeMissing
        displayTextRange: JsonField<List<Long>> = JsonMissing.of(),
        @JsonProperty("edit") @ExcludeMissing edit: JsonField<Edit> = JsonMissing.of(),
        @JsonProperty("entities") @ExcludeMissing entities: JsonField<Entities> = JsonMissing.of(),
        @JsonProperty("exclusiveContent")
        @ExcludeMissing
        exclusiveContent: JsonField<ExclusiveContent> = JsonMissing.of(),
        @JsonProperty("geo") @ExcludeMissing geo: JsonField<Geo> = JsonMissing.of(),
        @JsonProperty("grokShareAttachment")
        @ExcludeMissing
        grokShareAttachment: JsonField<GrokShareAttachment> = JsonMissing.of(),
        @JsonProperty("grokTranslatedPost")
        @ExcludeMissing
        grokTranslatedPost: JsonField<GrokTranslatedPost> = JsonMissing.of(),
        @JsonProperty("hasCommunityNotes")
        @ExcludeMissing
        hasCommunityNotes: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("inReplyToId")
        @ExcludeMissing
        inReplyToId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("inReplyToUserId")
        @ExcludeMissing
        inReplyToUserId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("inReplyToUsername")
        @ExcludeMissing
        inReplyToUsername: JsonField<String> = JsonMissing.of(),
        @JsonProperty("interstitial")
        @ExcludeMissing
        interstitial: JsonField<Interstitial> = JsonMissing.of(),
        @JsonProperty("isLimitedReply")
        @ExcludeMissing
        isLimitedReply: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("isNoteTweet")
        @ExcludeMissing
        isNoteTweet: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("isPinned") @ExcludeMissing isPinned: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("isQuoteStatus")
        @ExcludeMissing
        isQuoteStatus: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("isReply") @ExcludeMissing isReply: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("isRetweet") @ExcludeMissing isRetweet: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("isTranslatable")
        @ExcludeMissing
        isTranslatable: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("jetfuelAttachment")
        @ExcludeMissing
        jetfuelAttachment: JsonField<JetfuelAttachment> = JsonMissing.of(),
        @JsonProperty("lang") @ExcludeMissing lang: JsonField<String> = JsonMissing.of(),
        @JsonProperty("limitedActions")
        @ExcludeMissing
        limitedActions: JsonField<List<LimitedAction>> = JsonMissing.of(),
        @JsonProperty("media")
        @ExcludeMissing
        media: JsonField<List<TweetMedia>> = JsonMissing.of(),
        @JsonProperty("noteTweet")
        @ExcludeMissing
        noteTweet: JsonField<NoteTweet> = JsonMissing.of(),
        @JsonProperty("parentTweet")
        @ExcludeMissing
        parentTweet: JsonField<EmbeddedTweet> = JsonMissing.of(),
        @JsonProperty("place") @ExcludeMissing place: JsonField<Place> = JsonMissing.of(),
        @JsonProperty("possiblySensitive")
        @ExcludeMissing
        possiblySensitive: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("postCta") @ExcludeMissing postCta: JsonField<PostCta> = JsonMissing.of(),
        @JsonProperty("previousCounts")
        @ExcludeMissing
        previousCounts: JsonField<PreviousCounts> = JsonMissing.of(),
        @JsonProperty("quotedTweetId")
        @ExcludeMissing
        quotedTweetId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("quotedTweetPermalink")
        @ExcludeMissing
        quotedTweetPermalink: JsonField<QuotedTweetPermalink> = JsonMissing.of(),
        @JsonProperty("quotedTweetUnavailable")
        @ExcludeMissing
        quotedTweetUnavailable: JsonField<QuotedTweetUnavailable> = JsonMissing.of(),
        @JsonProperty("quoted_tweet")
        @ExcludeMissing
        quotedTweet: JsonField<EmbeddedTweet> = JsonMissing.of(),
        @JsonProperty("reactionContext")
        @ExcludeMissing
        reactionContext: JsonField<ReactionContext> = JsonMissing.of(),
        @JsonProperty("retweetedAt")
        @ExcludeMissing
        retweetedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("retweeted_tweet")
        @ExcludeMissing
        retweetedTweet: JsonField<EmbeddedTweet> = JsonMissing.of(),
        @JsonProperty("scopes") @ExcludeMissing scopes: JsonField<Scopes> = JsonMissing.of(),
        @JsonProperty("source") @ExcludeMissing source: JsonField<String> = JsonMissing.of(),
        @JsonProperty("sportsContext")
        @ExcludeMissing
        sportsContext: JsonField<SportsContext> = JsonMissing.of(),
        @JsonProperty("subscriberBadge")
        @ExcludeMissing
        subscriberBadge: JsonField<SubscriberBadge> = JsonMissing.of(),
        @JsonProperty("textHighlights")
        @ExcludeMissing
        textHighlights: JsonField<List<TextHighlight>> = JsonMissing.of(),
        @JsonProperty("tombstone")
        @ExcludeMissing
        tombstone: JsonField<Tombstone> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonField<String> = JsonMissing.of(),
        @JsonProperty("unavailableAuthorId")
        @ExcludeMissing
        unavailableAuthorId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("unmentionedUserIds")
        @ExcludeMissing
        unmentionedUserIds: JsonField<List<String>> = JsonMissing.of(),
        @JsonProperty("url") @ExcludeMissing url: JsonField<String> = JsonMissing.of(),
        @JsonProperty("viewState") @ExcludeMissing viewState: JsonField<String> = JsonMissing.of(),
        @JsonProperty("hiddenByAuthor")
        @ExcludeMissing
        hiddenByAuthor: JsonField<Boolean> = JsonMissing.of(),
    ) : this(
        id,
        bookmarkCount,
        unknownCounts,
        likeCount,
        quoteCount,
        replyCount,
        retweetCount,
        text,
        viewCount,
        article,
        author,
        authorUnavailable,
        card,
        cashtagAttachments,
        communityId,
        communityName,
        communityNote,
        contentDisclosure,
        conversationControl,
        conversationId,
        coordinates,
        createdAt,
        displayTextRange,
        edit,
        entities,
        exclusiveContent,
        geo,
        grokShareAttachment,
        grokTranslatedPost,
        hasCommunityNotes,
        inReplyToId,
        inReplyToUserId,
        inReplyToUsername,
        interstitial,
        isLimitedReply,
        isNoteTweet,
        isPinned,
        isQuoteStatus,
        isReply,
        isRetweet,
        isTranslatable,
        jetfuelAttachment,
        lang,
        limitedActions,
        media,
        noteTweet,
        parentTweet,
        place,
        possiblySensitive,
        postCta,
        previousCounts,
        quotedTweetId,
        quotedTweetPermalink,
        quotedTweetUnavailable,
        quotedTweet,
        reactionContext,
        retweetedAt,
        retweetedTweet,
        scopes,
        source,
        sportsContext,
        subscriberBadge,
        textHighlights,
        tombstone,
        type,
        unavailableAuthorId,
        unmentionedUserIds,
        url,
        viewState,
        hiddenByAuthor,
        mutableMapOf(),
    )

    /**
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun id(): String = id.getRequired("id")

    /**
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun bookmarkCount(): Long = bookmarkCount.getRequired("bookmarkCount")

    /**
     * Counts X did not send for this post read 0 and remain unknown.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun unknownCounts(): List<UnknownCount>? = unknownCounts.getNullable("unknownCounts")

    /**
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun likeCount(): Long = likeCount.getRequired("likeCount")

    /**
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun quoteCount(): Long = quoteCount.getRequired("quoteCount")

    /**
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun replyCount(): Long = replyCount.getRequired("replyCount")

    /**
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun retweetCount(): Long = retweetCount.getRequired("retweetCount")

    /**
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun text(): String = text.getRequired("text")

    /**
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun viewCount(): Long = viewCount.getRequired("viewCount")

    /**
     * Article metadata attached to a tweet.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun article(): Article? = article.getNullable("article")

    /**
     * X user profile with bio, follower counts, and verification status.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun author(): UserProfile? = author.getNullable("author")

    /**
     * Why the tweet has no author. X serves a suspended or deleted account as an unavailable user
     * with a reason such as Suspended or NotFound. Omitted when the author is available.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun authorUnavailable(): AuthorUnavailable? = authorUnavailable.getNullable("authorUnavailable")

    /**
     * Public card metadata attached to a tweet.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun card(): Card? = card.getNullable("card")

    /**
     * Price charts the author attached under the post, 1 per cashtag. Absent when the post has
     * none. X may send a cashtag without a chart window.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun cashtagAttachments(): List<CashtagAttachment>? =
        cashtagAttachments.getNullable("cashtagAttachments")

    /**
     * Community ID.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun communityId(): String? = communityId.getNullable("communityId")

    /**
     * Community name. Present when X sends it with the post.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun communityName(): String? = communityName.getNullable("communityName")

    /**
     * Community Note presentation metadata returned by X.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun communityNote(): CommunityNote? = communityNote.getNullable("communityNote")

    /**
     * Content disclosure metadata shown by X when a tweet is labeled as paid partnership content or
     * AI-generated media.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun contentDisclosure(): ContentDisclosure? = contentDisclosure.getNullable("contentDisclosure")

    /**
     * Reply policy & conversation owner.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun conversationControl(): ConversationControl? =
        conversationControl.getNullable("conversationControl")

    /**
     * Root tweet ID for the search result conversation
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun conversationId(): String? = conversationId.getNullable("conversationId")

    /**
     * Author-published geographic point with 2 numeric coordinates.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun coordinates(): Coordinates? = coordinates.getNullable("coordinates")

    /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
    fun createdAt(): String? = createdAt.getNullable("createdAt")

    /**
     * Rendered text's start and end offsets.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun displayTextRange(): List<Long>? = displayTextRange.getNullable("displayTextRange")

    /**
     * Edit history metadata returned by X.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun edit(): Edit? = edit.getNullable("edit")

    /**
     * Parsed search-result entities including URLs, mentions, hashtags, and media markers
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun entities(): Entities? = entities.getNullable("entities")

    /**
     * Marks a subscriber-only post with the creator whose subscription unlocks it. Non-subscribers
     * get the preview text & the subscribe link. Omitted on public posts.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun exclusiveContent(): ExclusiveContent? = exclusiveContent.getNullable("exclusiveContent")

    /**
     * Author-published geographic point with 2 numeric coordinates.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun geo(): Geo? = geo.getNullable("geo")

    /**
     * Public metadata whose fields X defines.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun grokShareAttachment(): GrokShareAttachment? =
        grokShareAttachment.getNullable("grokShareAttachment")

    /**
     * Public metadata whose fields X defines.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun grokTranslatedPost(): GrokTranslatedPost? =
        grokTranslatedPost.getNullable("grokTranslatedPost")

    /**
     * Whether X holds Community Notes on the post, shown or not.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun hasCommunityNotes(): Boolean? = hasCommunityNotes.getNullable("hasCommunityNotes")

    /**
     * ID of the tweet this result replies to.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun inReplyToId(): String? = inReplyToId.getNullable("inReplyToId")

    /**
     * ID of the user this result replies to.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun inReplyToUserId(): String? = inReplyToUserId.getNullable("inReplyToUserId")

    /**
     * Username this result replies to.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun inReplyToUsername(): String? = inReplyToUsername.getNullable("inReplyToUsername")

    /**
     * The notice x.com covers the post with until the reader views it, such as X's public interest
     * notice on a post that broke its rules but stays up.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun interstitial(): Interstitial? = interstitial.getNullable("interstitial")

    /**
     * Whether the tweet has limited reply permissions
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun isLimitedReply(): Boolean? = isLimitedReply.getNullable("isLimitedReply")

    /**
     * True for Note Tweets (long-form content, up to 25,000 characters)
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun isNoteTweet(): Boolean? = isNoteTweet.getNullable("isNoteTweet")

    /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
    fun isPinned(): Boolean? = isPinned.getNullable("isPinned")

    /**
     * True when this search result quotes another tweet
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun isQuoteStatus(): Boolean? = isQuoteStatus.getNullable("isQuoteStatus")

    /**
     * True when this search result is a reply
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun isReply(): Boolean? = isReply.getNullable("isReply")

    /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
    fun isRetweet(): Boolean? = isRetweet.getNullable("isRetweet")

    /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
    fun isTranslatable(): Boolean? = isTranslatable.getNullable("isTranslatable")

    /**
     * Public metadata whose fields X defines.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun jetfuelAttachment(): JetfuelAttachment? = jetfuelAttachment.getNullable("jetfuelAttachment")

    /**
     * Search result language code.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun lang(): String? = lang.getNullable("lang")

    /**
     * Public restrictions & prompts.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun limitedActions(): List<LimitedAction>? = limitedActions.getNullable("limitedActions")

    /**
     * Media, omitted when unavailable.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun media(): List<TweetMedia>? = media.getNullable("media")

    /**
     * Complete Note Tweet content and rich-text metadata.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun noteTweet(): NoteTweet? = noteTweet.getNullable("noteTweet")

    /**
     * A parent, quoted or retweeted tweet. It has the TweetPublicData fields and an author. Nesting
     * stops after 4 levels.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun parentTweet(): EmbeddedTweet? = parentTweet.getNullable("parentTweet")

    /**
     * Public place metadata attached to a tweet.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun place(): Place? = place.getNullable("place")

    /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
    fun possiblySensitive(): Boolean? = possiblySensitive.getNullable("possiblySensitive")

    /**
     * Public metadata whose fields X defines.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun postCta(): PostCta? = postCta.getNullable("postCta")

    /**
     * Engagement counts retained from a prior tweet edit.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun previousCounts(): PreviousCounts? = previousCounts.getNullable("previousCounts")

    /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
    fun quotedTweetId(): String? = quotedTweetId.getNullable("quotedTweetId")

    /**
     * The quoted post's link, kept when X withholds that post.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun quotedTweetPermalink(): QuotedTweetPermalink? =
        quotedTweetPermalink.getNullable("quotedTweetPermalink")

    /**
     * Why X shows no quoted post. Omitted when X names no reason, as its legacy answers never do.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun quotedTweetUnavailable(): QuotedTweetUnavailable? =
        quotedTweetUnavailable.getNullable("quotedTweetUnavailable")

    /**
     * Quoted or retweeted tweet context. Every object includes id, text, and engagement metrics. A
     * zero metric can mean X did not report the count. Author, media, and conversation fields
     * appear when available.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun quotedTweet(): EmbeddedTweet? = quotedTweet.getNullable("quoted_tweet")

    /**
     * Public post & user this reaction references.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun reactionContext(): ReactionContext? = reactionContext.getNullable("reactionContext")

    /**
     * Repost event time in UTC. Null when unavailable. Omitted for other posts.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun retweetedAt(): OffsetDateTime? = retweetedAt.getNullable("retweetedAt")

    /**
     * Quoted or retweeted tweet context. Every object includes id, text, and engagement metrics. A
     * zero metric can mean X did not report the count. Author, media, and conversation fields
     * appear when available.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun retweetedTweet(): EmbeddedTweet? = retweetedTweet.getNullable("retweeted_tweet")

    /**
     * Public metadata whose fields X defines.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun scopes(): Scopes? = scopes.getNullable("scopes")

    /**
     * The app the post was sent from, as X labels it. X sends it for some posts only.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun source(): String? = source.getNullable("source")

    /**
     * Game metadata attached to this post.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun sportsContext(): SportsContext? = sportsContext.getNullable("sportsContext")

    /**
     * The Subscriber badge X shows on a reply. The reply's author subscribes to the creator who
     * started the conversation. Omitted on a post without the badge.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun subscriberBadge(): SubscriberBadge? = subscriberBadge.getNullable("subscriberBadge")

    /**
     * Where `text` matches the search, on each row a search read. Offsets count code points of
     * `text`, as `displayTextRange` does. Empty when X marks nothing in the post. X marks matches
     * only in a post's first 280 characters.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun textHighlights(): List<TextHighlight>? = textHighlights.getNullable("textHighlights")

    /**
     * Public visibility notice attached to an available tweet.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun tombstone(): Tombstone? = tombstone.getNullable("tombstone")

    /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
    fun type(): String? = type.getNullable("type")

    /**
     * The author's ID when X withholds the author's profile.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun unavailableAuthorId(): String? = unavailableAuthorId.getNullable("unavailableAuthorId")

    /**
     * User IDs that left this conversation.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun unmentionedUserIds(): List<String>? = unmentionedUserIds.getNullable("unmentionedUserIds")

    /**
     * Search result permalink.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun url(): String? = url.getNullable("url")

    /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
    fun viewState(): String? = viewState.getNullable("viewState")

    /**
     * Returns the raw JSON value of [id].
     *
     * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    /**
     * Returns the raw JSON value of [bookmarkCount].
     *
     * Unlike [bookmarkCount], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("bookmarkCount")
    @ExcludeMissing
    fun _bookmarkCount(): JsonField<Long> = bookmarkCount

    /**
     * Returns the raw JSON value of [unknownCounts].
     *
     * Unlike [unknownCounts], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("unknownCounts")
    @ExcludeMissing
    fun _unknownCounts(): JsonField<List<UnknownCount>> = unknownCounts

    /**
     * Returns the raw JSON value of [likeCount].
     *
     * Unlike [likeCount], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("likeCount") @ExcludeMissing fun _likeCount(): JsonField<Long> = likeCount

    /**
     * Returns the raw JSON value of [quoteCount].
     *
     * Unlike [quoteCount], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("quoteCount") @ExcludeMissing fun _quoteCount(): JsonField<Long> = quoteCount

    /**
     * Returns the raw JSON value of [replyCount].
     *
     * Unlike [replyCount], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("replyCount") @ExcludeMissing fun _replyCount(): JsonField<Long> = replyCount

    /**
     * Returns the raw JSON value of [retweetCount].
     *
     * Unlike [retweetCount], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("retweetCount")
    @ExcludeMissing
    fun _retweetCount(): JsonField<Long> = retweetCount

    /**
     * Returns the raw JSON value of [text].
     *
     * Unlike [text], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("text") @ExcludeMissing fun _text(): JsonField<String> = text

    /**
     * Returns the raw JSON value of [viewCount].
     *
     * Unlike [viewCount], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("viewCount") @ExcludeMissing fun _viewCount(): JsonField<Long> = viewCount

    /**
     * Returns the raw JSON value of [article].
     *
     * Unlike [article], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("article") @ExcludeMissing fun _article(): JsonField<Article> = article

    /**
     * Returns the raw JSON value of [author].
     *
     * Unlike [author], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("author") @ExcludeMissing fun _author(): JsonField<UserProfile> = author

    /**
     * Returns the raw JSON value of [authorUnavailable].
     *
     * Unlike [authorUnavailable], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("authorUnavailable")
    @ExcludeMissing
    fun _authorUnavailable(): JsonField<AuthorUnavailable> = authorUnavailable

    /**
     * Returns the raw JSON value of [card].
     *
     * Unlike [card], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("card") @ExcludeMissing fun _card(): JsonField<Card> = card

    /**
     * Returns the raw JSON value of [cashtagAttachments].
     *
     * Unlike [cashtagAttachments], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("cashtagAttachments")
    @ExcludeMissing
    fun _cashtagAttachments(): JsonField<List<CashtagAttachment>> = cashtagAttachments

    /**
     * Returns the raw JSON value of [communityId].
     *
     * Unlike [communityId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("communityId") @ExcludeMissing fun _communityId(): JsonField<String> = communityId

    /**
     * Returns the raw JSON value of [communityName].
     *
     * Unlike [communityName], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("communityName")
    @ExcludeMissing
    fun _communityName(): JsonField<String> = communityName

    /**
     * Returns the raw JSON value of [communityNote].
     *
     * Unlike [communityNote], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("communityNote")
    @ExcludeMissing
    fun _communityNote(): JsonField<CommunityNote> = communityNote

    /**
     * Returns the raw JSON value of [contentDisclosure].
     *
     * Unlike [contentDisclosure], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("contentDisclosure")
    @ExcludeMissing
    fun _contentDisclosure(): JsonField<ContentDisclosure> = contentDisclosure

    /**
     * Returns the raw JSON value of [conversationControl].
     *
     * Unlike [conversationControl], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("conversationControl")
    @ExcludeMissing
    fun _conversationControl(): JsonField<ConversationControl> = conversationControl

    /**
     * Returns the raw JSON value of [conversationId].
     *
     * Unlike [conversationId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("conversationId")
    @ExcludeMissing
    fun _conversationId(): JsonField<String> = conversationId

    /**
     * Returns the raw JSON value of [coordinates].
     *
     * Unlike [coordinates], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("coordinates")
    @ExcludeMissing
    fun _coordinates(): JsonField<Coordinates> = coordinates

    /**
     * Returns the raw JSON value of [createdAt].
     *
     * Unlike [createdAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("createdAt") @ExcludeMissing fun _createdAt(): JsonField<String> = createdAt

    /**
     * Returns the raw JSON value of [displayTextRange].
     *
     * Unlike [displayTextRange], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("displayTextRange")
    @ExcludeMissing
    fun _displayTextRange(): JsonField<List<Long>> = displayTextRange

    /**
     * Returns the raw JSON value of [edit].
     *
     * Unlike [edit], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("edit") @ExcludeMissing fun _edit(): JsonField<Edit> = edit

    /**
     * Returns the raw JSON value of [entities].
     *
     * Unlike [entities], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("entities") @ExcludeMissing fun _entities(): JsonField<Entities> = entities

    /**
     * Returns the raw JSON value of [exclusiveContent].
     *
     * Unlike [exclusiveContent], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("exclusiveContent")
    @ExcludeMissing
    fun _exclusiveContent(): JsonField<ExclusiveContent> = exclusiveContent

    /**
     * Returns the raw JSON value of [geo].
     *
     * Unlike [geo], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("geo") @ExcludeMissing fun _geo(): JsonField<Geo> = geo

    /**
     * Returns the raw JSON value of [grokShareAttachment].
     *
     * Unlike [grokShareAttachment], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("grokShareAttachment")
    @ExcludeMissing
    fun _grokShareAttachment(): JsonField<GrokShareAttachment> = grokShareAttachment

    /**
     * Returns the raw JSON value of [grokTranslatedPost].
     *
     * Unlike [grokTranslatedPost], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("grokTranslatedPost")
    @ExcludeMissing
    fun _grokTranslatedPost(): JsonField<GrokTranslatedPost> = grokTranslatedPost

    /**
     * Returns the raw JSON value of [hasCommunityNotes].
     *
     * Unlike [hasCommunityNotes], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("hasCommunityNotes")
    @ExcludeMissing
    fun _hasCommunityNotes(): JsonField<Boolean> = hasCommunityNotes

    /**
     * Returns the raw JSON value of [inReplyToId].
     *
     * Unlike [inReplyToId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("inReplyToId") @ExcludeMissing fun _inReplyToId(): JsonField<String> = inReplyToId

    /**
     * Returns the raw JSON value of [inReplyToUserId].
     *
     * Unlike [inReplyToUserId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("inReplyToUserId")
    @ExcludeMissing
    fun _inReplyToUserId(): JsonField<String> = inReplyToUserId

    /**
     * Returns the raw JSON value of [inReplyToUsername].
     *
     * Unlike [inReplyToUsername], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("inReplyToUsername")
    @ExcludeMissing
    fun _inReplyToUsername(): JsonField<String> = inReplyToUsername

    /**
     * Returns the raw JSON value of [interstitial].
     *
     * Unlike [interstitial], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("interstitial")
    @ExcludeMissing
    fun _interstitial(): JsonField<Interstitial> = interstitial

    /**
     * Returns the raw JSON value of [isLimitedReply].
     *
     * Unlike [isLimitedReply], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("isLimitedReply")
    @ExcludeMissing
    fun _isLimitedReply(): JsonField<Boolean> = isLimitedReply

    /**
     * Returns the raw JSON value of [isNoteTweet].
     *
     * Unlike [isNoteTweet], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("isNoteTweet")
    @ExcludeMissing
    fun _isNoteTweet(): JsonField<Boolean> = isNoteTweet

    /**
     * Returns the raw JSON value of [isPinned].
     *
     * Unlike [isPinned], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("isPinned") @ExcludeMissing fun _isPinned(): JsonField<Boolean> = isPinned

    /**
     * Returns the raw JSON value of [isQuoteStatus].
     *
     * Unlike [isQuoteStatus], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("isQuoteStatus")
    @ExcludeMissing
    fun _isQuoteStatus(): JsonField<Boolean> = isQuoteStatus

    /**
     * Returns the raw JSON value of [isReply].
     *
     * Unlike [isReply], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("isReply") @ExcludeMissing fun _isReply(): JsonField<Boolean> = isReply

    /**
     * Returns the raw JSON value of [isRetweet].
     *
     * Unlike [isRetweet], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("isRetweet") @ExcludeMissing fun _isRetweet(): JsonField<Boolean> = isRetweet

    /**
     * Returns the raw JSON value of [isTranslatable].
     *
     * Unlike [isTranslatable], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("isTranslatable")
    @ExcludeMissing
    fun _isTranslatable(): JsonField<Boolean> = isTranslatable

    /**
     * Returns the raw JSON value of [jetfuelAttachment].
     *
     * Unlike [jetfuelAttachment], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("jetfuelAttachment")
    @ExcludeMissing
    fun _jetfuelAttachment(): JsonField<JetfuelAttachment> = jetfuelAttachment

    /**
     * Returns the raw JSON value of [lang].
     *
     * Unlike [lang], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("lang") @ExcludeMissing fun _lang(): JsonField<String> = lang

    /**
     * Returns the raw JSON value of [limitedActions].
     *
     * Unlike [limitedActions], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("limitedActions")
    @ExcludeMissing
    fun _limitedActions(): JsonField<List<LimitedAction>> = limitedActions

    /**
     * Returns the raw JSON value of [media].
     *
     * Unlike [media], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("media") @ExcludeMissing fun _media(): JsonField<List<TweetMedia>> = media

    /**
     * Returns the raw JSON value of [noteTweet].
     *
     * Unlike [noteTweet], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("noteTweet") @ExcludeMissing fun _noteTweet(): JsonField<NoteTweet> = noteTweet

    /**
     * Returns the raw JSON value of [parentTweet].
     *
     * Unlike [parentTweet], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("parentTweet")
    @ExcludeMissing
    fun _parentTweet(): JsonField<EmbeddedTweet> = parentTweet

    /**
     * Returns the raw JSON value of [place].
     *
     * Unlike [place], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("place") @ExcludeMissing fun _place(): JsonField<Place> = place

    /**
     * Returns the raw JSON value of [possiblySensitive].
     *
     * Unlike [possiblySensitive], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("possiblySensitive")
    @ExcludeMissing
    fun _possiblySensitive(): JsonField<Boolean> = possiblySensitive

    /**
     * Returns the raw JSON value of [postCta].
     *
     * Unlike [postCta], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("postCta") @ExcludeMissing fun _postCta(): JsonField<PostCta> = postCta

    /**
     * Returns the raw JSON value of [previousCounts].
     *
     * Unlike [previousCounts], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("previousCounts")
    @ExcludeMissing
    fun _previousCounts(): JsonField<PreviousCounts> = previousCounts

    /**
     * Returns the raw JSON value of [quotedTweetId].
     *
     * Unlike [quotedTweetId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("quotedTweetId")
    @ExcludeMissing
    fun _quotedTweetId(): JsonField<String> = quotedTweetId

    /**
     * Returns the raw JSON value of [quotedTweetPermalink].
     *
     * Unlike [quotedTweetPermalink], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("quotedTweetPermalink")
    @ExcludeMissing
    fun _quotedTweetPermalink(): JsonField<QuotedTweetPermalink> = quotedTweetPermalink

    /**
     * Returns the raw JSON value of [quotedTweetUnavailable].
     *
     * Unlike [quotedTweetUnavailable], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("quotedTweetUnavailable")
    @ExcludeMissing
    fun _quotedTweetUnavailable(): JsonField<QuotedTweetUnavailable> = quotedTweetUnavailable

    /**
     * Returns the raw JSON value of [quotedTweet].
     *
     * Unlike [quotedTweet], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("quoted_tweet")
    @ExcludeMissing
    fun _quotedTweet(): JsonField<EmbeddedTweet> = quotedTweet

    /**
     * Returns the raw JSON value of [reactionContext].
     *
     * Unlike [reactionContext], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("reactionContext")
    @ExcludeMissing
    fun _reactionContext(): JsonField<ReactionContext> = reactionContext

    /**
     * Returns the raw JSON value of [retweetedAt].
     *
     * Unlike [retweetedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("retweetedAt")
    @ExcludeMissing
    fun _retweetedAt(): JsonField<OffsetDateTime> = retweetedAt

    /**
     * Returns the raw JSON value of [retweetedTweet].
     *
     * Unlike [retweetedTweet], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("retweeted_tweet")
    @ExcludeMissing
    fun _retweetedTweet(): JsonField<EmbeddedTweet> = retweetedTweet

    /**
     * Returns the raw JSON value of [scopes].
     *
     * Unlike [scopes], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("scopes") @ExcludeMissing fun _scopes(): JsonField<Scopes> = scopes

    /**
     * Returns the raw JSON value of [source].
     *
     * Unlike [source], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("source") @ExcludeMissing fun _source(): JsonField<String> = source

    /**
     * Returns the raw JSON value of [sportsContext].
     *
     * Unlike [sportsContext], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("sportsContext")
    @ExcludeMissing
    fun _sportsContext(): JsonField<SportsContext> = sportsContext

    /**
     * Returns the raw JSON value of [subscriberBadge].
     *
     * Unlike [subscriberBadge], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("subscriberBadge")
    @ExcludeMissing
    fun _subscriberBadge(): JsonField<SubscriberBadge> = subscriberBadge

    /**
     * Returns the raw JSON value of [textHighlights].
     *
     * Unlike [textHighlights], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("textHighlights")
    @ExcludeMissing
    fun _textHighlights(): JsonField<List<TextHighlight>> = textHighlights

    /**
     * Returns the raw JSON value of [tombstone].
     *
     * Unlike [tombstone], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("tombstone") @ExcludeMissing fun _tombstone(): JsonField<Tombstone> = tombstone

    /**
     * Returns the raw JSON value of [type].
     *
     * Unlike [type], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<String> = type

    /**
     * Returns the raw JSON value of [unavailableAuthorId].
     *
     * Unlike [unavailableAuthorId], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("unavailableAuthorId")
    @ExcludeMissing
    fun _unavailableAuthorId(): JsonField<String> = unavailableAuthorId

    /**
     * Returns the raw JSON value of [unmentionedUserIds].
     *
     * Unlike [unmentionedUserIds], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("unmentionedUserIds")
    @ExcludeMissing
    fun _unmentionedUserIds(): JsonField<List<String>> = unmentionedUserIds

    /**
     * Returns the raw JSON value of [url].
     *
     * Unlike [url], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("url") @ExcludeMissing fun _url(): JsonField<String> = url

    /**
     * Returns the raw JSON value of [viewState].
     *
     * Unlike [viewState], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("viewState") @ExcludeMissing fun _viewState(): JsonField<String> = viewState

    /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
    fun hiddenByAuthor(): Boolean? = hiddenByAuthor.getNullable("hiddenByAuthor")

    /**
     * Returns the raw JSON value of [hiddenByAuthor].
     *
     * Unlike [hiddenByAuthor], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("hiddenByAuthor")
    @ExcludeMissing
    fun _hiddenByAuthor(): JsonField<Boolean> = hiddenByAuthor

    @JsonAnySetter
    private fun putAdditionalProperty(key: String, value: JsonValue) {
        additionalProperties.put(key, value)
    }

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> =
        Collections.unmodifiableMap(additionalProperties)

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [SearchTweet].
         *
         * The following fields are required:
         * ```kotlin
         * .id()
         * .bookmarkCount()
         * .likeCount()
         * .quoteCount()
         * .replyCount()
         * .retweetCount()
         * .text()
         * .viewCount()
         * ```
         */
        fun builder() = Builder()
    }

    /** A builder for [SearchTweet]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var bookmarkCount: JsonField<Long>? = null
        private var unknownCounts: JsonField<MutableList<UnknownCount>>? = null
        private var likeCount: JsonField<Long>? = null
        private var quoteCount: JsonField<Long>? = null
        private var replyCount: JsonField<Long>? = null
        private var retweetCount: JsonField<Long>? = null
        private var text: JsonField<String>? = null
        private var viewCount: JsonField<Long>? = null
        private var article: JsonField<Article> = JsonMissing.of()
        private var author: JsonField<UserProfile> = JsonMissing.of()
        private var authorUnavailable: JsonField<AuthorUnavailable> = JsonMissing.of()
        private var card: JsonField<Card> = JsonMissing.of()
        private var cashtagAttachments: JsonField<MutableList<CashtagAttachment>>? = null
        private var communityId: JsonField<String> = JsonMissing.of()
        private var communityName: JsonField<String> = JsonMissing.of()
        private var communityNote: JsonField<CommunityNote> = JsonMissing.of()
        private var contentDisclosure: JsonField<ContentDisclosure> = JsonMissing.of()
        private var conversationControl: JsonField<ConversationControl> = JsonMissing.of()
        private var conversationId: JsonField<String> = JsonMissing.of()
        private var coordinates: JsonField<Coordinates> = JsonMissing.of()
        private var createdAt: JsonField<String> = JsonMissing.of()
        private var displayTextRange: JsonField<MutableList<Long>>? = null
        private var edit: JsonField<Edit> = JsonMissing.of()
        private var entities: JsonField<Entities> = JsonMissing.of()
        private var exclusiveContent: JsonField<ExclusiveContent> = JsonMissing.of()
        private var geo: JsonField<Geo> = JsonMissing.of()
        private var grokShareAttachment: JsonField<GrokShareAttachment> = JsonMissing.of()
        private var grokTranslatedPost: JsonField<GrokTranslatedPost> = JsonMissing.of()
        private var hasCommunityNotes: JsonField<Boolean> = JsonMissing.of()
        private var inReplyToId: JsonField<String> = JsonMissing.of()
        private var inReplyToUserId: JsonField<String> = JsonMissing.of()
        private var inReplyToUsername: JsonField<String> = JsonMissing.of()
        private var interstitial: JsonField<Interstitial> = JsonMissing.of()
        private var isLimitedReply: JsonField<Boolean> = JsonMissing.of()
        private var isNoteTweet: JsonField<Boolean> = JsonMissing.of()
        private var isPinned: JsonField<Boolean> = JsonMissing.of()
        private var isQuoteStatus: JsonField<Boolean> = JsonMissing.of()
        private var isReply: JsonField<Boolean> = JsonMissing.of()
        private var isRetweet: JsonField<Boolean> = JsonMissing.of()
        private var isTranslatable: JsonField<Boolean> = JsonMissing.of()
        private var jetfuelAttachment: JsonField<JetfuelAttachment> = JsonMissing.of()
        private var lang: JsonField<String> = JsonMissing.of()
        private var limitedActions: JsonField<MutableList<LimitedAction>>? = null
        private var media: JsonField<MutableList<TweetMedia>>? = null
        private var noteTweet: JsonField<NoteTweet> = JsonMissing.of()
        private var parentTweet: JsonField<EmbeddedTweet> = JsonMissing.of()
        private var place: JsonField<Place> = JsonMissing.of()
        private var possiblySensitive: JsonField<Boolean> = JsonMissing.of()
        private var postCta: JsonField<PostCta> = JsonMissing.of()
        private var previousCounts: JsonField<PreviousCounts> = JsonMissing.of()
        private var quotedTweetId: JsonField<String> = JsonMissing.of()
        private var quotedTweetPermalink: JsonField<QuotedTweetPermalink> = JsonMissing.of()
        private var quotedTweetUnavailable: JsonField<QuotedTweetUnavailable> = JsonMissing.of()
        private var quotedTweet: JsonField<EmbeddedTweet> = JsonMissing.of()
        private var reactionContext: JsonField<ReactionContext> = JsonMissing.of()
        private var retweetedAt: JsonField<OffsetDateTime> = JsonMissing.of()
        private var retweetedTweet: JsonField<EmbeddedTweet> = JsonMissing.of()
        private var scopes: JsonField<Scopes> = JsonMissing.of()
        private var source: JsonField<String> = JsonMissing.of()
        private var sportsContext: JsonField<SportsContext> = JsonMissing.of()
        private var subscriberBadge: JsonField<SubscriberBadge> = JsonMissing.of()
        private var textHighlights: JsonField<MutableList<TextHighlight>>? = null
        private var tombstone: JsonField<Tombstone> = JsonMissing.of()
        private var type: JsonField<String> = JsonMissing.of()
        private var unavailableAuthorId: JsonField<String> = JsonMissing.of()
        private var unmentionedUserIds: JsonField<MutableList<String>>? = null
        private var url: JsonField<String> = JsonMissing.of()
        private var viewState: JsonField<String> = JsonMissing.of()
        private var hiddenByAuthor: JsonField<Boolean> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        internal fun from(searchTweet: SearchTweet) = apply {
            id = searchTweet.id
            bookmarkCount = searchTweet.bookmarkCount
            unknownCounts = searchTweet.unknownCounts.map { it.toMutableList() }
            likeCount = searchTweet.likeCount
            quoteCount = searchTweet.quoteCount
            replyCount = searchTweet.replyCount
            retweetCount = searchTweet.retweetCount
            text = searchTweet.text
            viewCount = searchTweet.viewCount
            article = searchTweet.article
            author = searchTweet.author
            authorUnavailable = searchTweet.authorUnavailable
            card = searchTweet.card
            cashtagAttachments = searchTweet.cashtagAttachments.map { it.toMutableList() }
            communityId = searchTweet.communityId
            communityName = searchTweet.communityName
            communityNote = searchTweet.communityNote
            contentDisclosure = searchTweet.contentDisclosure
            conversationControl = searchTweet.conversationControl
            conversationId = searchTweet.conversationId
            coordinates = searchTweet.coordinates
            createdAt = searchTweet.createdAt
            displayTextRange = searchTweet.displayTextRange.map { it.toMutableList() }
            edit = searchTweet.edit
            entities = searchTweet.entities
            exclusiveContent = searchTweet.exclusiveContent
            geo = searchTweet.geo
            grokShareAttachment = searchTweet.grokShareAttachment
            grokTranslatedPost = searchTweet.grokTranslatedPost
            hasCommunityNotes = searchTweet.hasCommunityNotes
            inReplyToId = searchTweet.inReplyToId
            inReplyToUserId = searchTweet.inReplyToUserId
            inReplyToUsername = searchTweet.inReplyToUsername
            interstitial = searchTweet.interstitial
            isLimitedReply = searchTweet.isLimitedReply
            isNoteTweet = searchTweet.isNoteTweet
            isPinned = searchTweet.isPinned
            isQuoteStatus = searchTweet.isQuoteStatus
            isReply = searchTweet.isReply
            isRetweet = searchTweet.isRetweet
            isTranslatable = searchTweet.isTranslatable
            jetfuelAttachment = searchTweet.jetfuelAttachment
            lang = searchTweet.lang
            limitedActions = searchTweet.limitedActions.map { it.toMutableList() }
            media = searchTweet.media.map { it.toMutableList() }
            noteTweet = searchTweet.noteTweet
            parentTweet = searchTweet.parentTweet
            place = searchTweet.place
            possiblySensitive = searchTweet.possiblySensitive
            postCta = searchTweet.postCta
            previousCounts = searchTweet.previousCounts
            quotedTweetId = searchTweet.quotedTweetId
            quotedTweetPermalink = searchTweet.quotedTweetPermalink
            quotedTweetUnavailable = searchTweet.quotedTweetUnavailable
            quotedTweet = searchTweet.quotedTweet
            reactionContext = searchTweet.reactionContext
            retweetedAt = searchTweet.retweetedAt
            retweetedTweet = searchTweet.retweetedTweet
            scopes = searchTweet.scopes
            source = searchTweet.source
            sportsContext = searchTweet.sportsContext
            subscriberBadge = searchTweet.subscriberBadge
            textHighlights = searchTweet.textHighlights.map { it.toMutableList() }
            tombstone = searchTweet.tombstone
            type = searchTweet.type
            unavailableAuthorId = searchTweet.unavailableAuthorId
            unmentionedUserIds = searchTweet.unmentionedUserIds.map { it.toMutableList() }
            url = searchTweet.url
            viewState = searchTweet.viewState
            hiddenByAuthor = searchTweet.hiddenByAuthor
            additionalProperties = searchTweet.additionalProperties.toMutableMap()
        }

        fun id(id: String) = id(JsonField.of(id))

        /** Sets [Builder.id] to an arbitrary JSON value. */
        fun id(id: JsonField<String>) = apply { this.id = id }

        fun bookmarkCount(bookmarkCount: Long) = bookmarkCount(JsonField.of(bookmarkCount))

        /** Sets [Builder.bookmarkCount] to an arbitrary JSON value. */
        fun bookmarkCount(bookmarkCount: JsonField<Long>) = apply {
            this.bookmarkCount = bookmarkCount
        }

        /** Counts X did not send for this post read 0 and remain unknown. */
        fun unknownCounts(unknownCounts: List<UnknownCount>) =
            unknownCounts(JsonField.of(unknownCounts))

        /** Sets [Builder.unknownCounts] to an arbitrary JSON value. */
        fun unknownCounts(unknownCounts: JsonField<List<UnknownCount>>) = apply {
            this.unknownCounts = unknownCounts.map { it.toMutableList() }
        }

        /**
         * Adds a single [UnknownCount] to [unknownCounts].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addUnknownCount(unknownCount: UnknownCount) = apply {
            unknownCounts =
                (unknownCounts ?: JsonField.of(mutableListOf())).also {
                    checkKnown("unknownCounts", it).add(unknownCount)
                }
        }

        fun likeCount(likeCount: Long) = likeCount(JsonField.of(likeCount))

        /** Sets [Builder.likeCount] to an arbitrary JSON value. */
        fun likeCount(likeCount: JsonField<Long>) = apply { this.likeCount = likeCount }

        fun quoteCount(quoteCount: Long) = quoteCount(JsonField.of(quoteCount))

        /** Sets [Builder.quoteCount] to an arbitrary JSON value. */
        fun quoteCount(quoteCount: JsonField<Long>) = apply { this.quoteCount = quoteCount }

        fun replyCount(replyCount: Long) = replyCount(JsonField.of(replyCount))

        /** Sets [Builder.replyCount] to an arbitrary JSON value. */
        fun replyCount(replyCount: JsonField<Long>) = apply { this.replyCount = replyCount }

        fun retweetCount(retweetCount: Long) = retweetCount(JsonField.of(retweetCount))

        /** Sets [Builder.retweetCount] to an arbitrary JSON value. */
        fun retweetCount(retweetCount: JsonField<Long>) = apply { this.retweetCount = retweetCount }

        fun text(text: String) = text(JsonField.of(text))

        /** Sets [Builder.text] to an arbitrary JSON value. */
        fun text(text: JsonField<String>) = apply { this.text = text }

        fun viewCount(viewCount: Long) = viewCount(JsonField.of(viewCount))

        /** Sets [Builder.viewCount] to an arbitrary JSON value. */
        fun viewCount(viewCount: JsonField<Long>) = apply { this.viewCount = viewCount }

        /** Article metadata attached to a tweet. */
        fun article(article: Article) = article(JsonField.of(article))

        /** Sets [Builder.article] to an arbitrary JSON value. */
        fun article(article: JsonField<Article>) = apply { this.article = article }

        /** X user profile with bio, follower counts, and verification status. */
        fun author(author: UserProfile) = author(JsonField.of(author))

        /** Sets [Builder.author] to an arbitrary JSON value. */
        fun author(author: JsonField<UserProfile>) = apply { this.author = author }

        /**
         * Why the tweet has no author. X serves a suspended or deleted account as an unavailable
         * user with a reason such as Suspended or NotFound. Omitted when the author is available.
         */
        fun authorUnavailable(authorUnavailable: AuthorUnavailable) =
            authorUnavailable(JsonField.of(authorUnavailable))

        /** Sets [Builder.authorUnavailable] to an arbitrary JSON value. */
        fun authorUnavailable(authorUnavailable: JsonField<AuthorUnavailable>) = apply {
            this.authorUnavailable = authorUnavailable
        }

        /** Public card metadata attached to a tweet. */
        fun card(card: Card) = card(JsonField.of(card))

        /** Sets [Builder.card] to an arbitrary JSON value. */
        fun card(card: JsonField<Card>) = apply { this.card = card }

        /**
         * Price charts the author attached under the post, 1 per cashtag. Absent when the post has
         * none. X may send a cashtag without a chart window.
         */
        fun cashtagAttachments(cashtagAttachments: List<CashtagAttachment>) =
            cashtagAttachments(JsonField.of(cashtagAttachments))

        /** Sets [Builder.cashtagAttachments] to an arbitrary JSON value. */
        fun cashtagAttachments(cashtagAttachments: JsonField<List<CashtagAttachment>>) = apply {
            this.cashtagAttachments = cashtagAttachments.map { it.toMutableList() }
        }

        /**
         * Adds a single [CashtagAttachment] to [cashtagAttachments].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addCashtagAttachment(cashtagAttachment: CashtagAttachment) = apply {
            cashtagAttachments =
                (cashtagAttachments ?: JsonField.of(mutableListOf())).also {
                    checkKnown("cashtagAttachments", it).add(cashtagAttachment)
                }
        }

        /** Community ID. */
        fun communityId(communityId: String) = communityId(JsonField.of(communityId))

        /** Sets [Builder.communityId] to an arbitrary JSON value. */
        fun communityId(communityId: JsonField<String>) = apply { this.communityId = communityId }

        /** Community name. Present when X sends it with the post. */
        fun communityName(communityName: String) = communityName(JsonField.of(communityName))

        /** Sets [Builder.communityName] to an arbitrary JSON value. */
        fun communityName(communityName: JsonField<String>) = apply {
            this.communityName = communityName
        }

        /** Community Note presentation metadata returned by X. */
        fun communityNote(communityNote: CommunityNote) = communityNote(JsonField.of(communityNote))

        /** Sets [Builder.communityNote] to an arbitrary JSON value. */
        fun communityNote(communityNote: JsonField<CommunityNote>) = apply {
            this.communityNote = communityNote
        }

        /**
         * Content disclosure metadata shown by X when a tweet is labeled as paid partnership
         * content or AI-generated media.
         */
        fun contentDisclosure(contentDisclosure: ContentDisclosure) =
            contentDisclosure(JsonField.of(contentDisclosure))

        /** Sets [Builder.contentDisclosure] to an arbitrary JSON value. */
        fun contentDisclosure(contentDisclosure: JsonField<ContentDisclosure>) = apply {
            this.contentDisclosure = contentDisclosure
        }

        /** Reply policy & conversation owner. */
        fun conversationControl(conversationControl: ConversationControl) =
            conversationControl(JsonField.of(conversationControl))

        /** Sets [Builder.conversationControl] to an arbitrary JSON value. */
        fun conversationControl(conversationControl: JsonField<ConversationControl>) = apply {
            this.conversationControl = conversationControl
        }

        /** Root tweet ID for the search result conversation */
        fun conversationId(conversationId: String) = conversationId(JsonField.of(conversationId))

        /** Sets [Builder.conversationId] to an arbitrary JSON value. */
        fun conversationId(conversationId: JsonField<String>) = apply {
            this.conversationId = conversationId
        }

        /** Author-published geographic point with 2 numeric coordinates. */
        fun coordinates(coordinates: Coordinates) = coordinates(JsonField.of(coordinates))

        /** Sets [Builder.coordinates] to an arbitrary JSON value. */
        fun coordinates(coordinates: JsonField<Coordinates>) = apply {
            this.coordinates = coordinates
        }

        fun createdAt(createdAt: String) = createdAt(JsonField.of(createdAt))

        /** Sets [Builder.createdAt] to an arbitrary JSON value. */
        fun createdAt(createdAt: JsonField<String>) = apply { this.createdAt = createdAt }

        /** Rendered text's start and end offsets. */
        fun displayTextRange(displayTextRange: List<Long>) =
            displayTextRange(JsonField.of(displayTextRange))

        /** Sets [Builder.displayTextRange] to an arbitrary JSON value. */
        fun displayTextRange(displayTextRange: JsonField<List<Long>>) = apply {
            this.displayTextRange = displayTextRange.map { it.toMutableList() }
        }

        /**
         * Adds a single [Long] to [displayTextRange].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addDisplayTextRange(displayTextRange: Long) = apply {
            this.displayTextRange =
                (this.displayTextRange ?: JsonField.of(mutableListOf())).also {
                    checkKnown("displayTextRange", it).add(displayTextRange)
                }
        }

        /** Edit history metadata returned by X. */
        fun edit(edit: Edit) = edit(JsonField.of(edit))

        /** Sets [Builder.edit] to an arbitrary JSON value. */
        fun edit(edit: JsonField<Edit>) = apply { this.edit = edit }

        /** Parsed search-result entities including URLs, mentions, hashtags, and media markers */
        fun entities(entities: Entities) = entities(JsonField.of(entities))

        /** Sets [Builder.entities] to an arbitrary JSON value. */
        fun entities(entities: JsonField<Entities>) = apply { this.entities = entities }

        /**
         * Marks a subscriber-only post with the creator whose subscription unlocks it.
         * Non-subscribers get the preview text & the subscribe link. Omitted on public posts.
         */
        fun exclusiveContent(exclusiveContent: ExclusiveContent) =
            exclusiveContent(JsonField.of(exclusiveContent))

        /** Sets [Builder.exclusiveContent] to an arbitrary JSON value. */
        fun exclusiveContent(exclusiveContent: JsonField<ExclusiveContent>) = apply {
            this.exclusiveContent = exclusiveContent
        }

        /** Author-published geographic point with 2 numeric coordinates. */
        fun geo(geo: Geo) = geo(JsonField.of(geo))

        /** Sets [Builder.geo] to an arbitrary JSON value. */
        fun geo(geo: JsonField<Geo>) = apply { this.geo = geo }

        /** Public metadata whose fields X defines. */
        fun grokShareAttachment(grokShareAttachment: GrokShareAttachment) =
            grokShareAttachment(JsonField.of(grokShareAttachment))

        /** Sets [Builder.grokShareAttachment] to an arbitrary JSON value. */
        fun grokShareAttachment(grokShareAttachment: JsonField<GrokShareAttachment>) = apply {
            this.grokShareAttachment = grokShareAttachment
        }

        /** Public metadata whose fields X defines. */
        fun grokTranslatedPost(grokTranslatedPost: GrokTranslatedPost) =
            grokTranslatedPost(JsonField.of(grokTranslatedPost))

        /** Sets [Builder.grokTranslatedPost] to an arbitrary JSON value. */
        fun grokTranslatedPost(grokTranslatedPost: JsonField<GrokTranslatedPost>) = apply {
            this.grokTranslatedPost = grokTranslatedPost
        }

        /** Whether X holds Community Notes on the post, shown or not. */
        fun hasCommunityNotes(hasCommunityNotes: Boolean) =
            hasCommunityNotes(JsonField.of(hasCommunityNotes))

        /** Sets [Builder.hasCommunityNotes] to an arbitrary JSON value. */
        fun hasCommunityNotes(hasCommunityNotes: JsonField<Boolean>) = apply {
            this.hasCommunityNotes = hasCommunityNotes
        }

        /** ID of the tweet this result replies to. */
        fun inReplyToId(inReplyToId: String) = inReplyToId(JsonField.of(inReplyToId))

        /** Sets [Builder.inReplyToId] to an arbitrary JSON value. */
        fun inReplyToId(inReplyToId: JsonField<String>) = apply { this.inReplyToId = inReplyToId }

        /** ID of the user this result replies to. */
        fun inReplyToUserId(inReplyToUserId: String) =
            inReplyToUserId(JsonField.of(inReplyToUserId))

        /** Sets [Builder.inReplyToUserId] to an arbitrary JSON value. */
        fun inReplyToUserId(inReplyToUserId: JsonField<String>) = apply {
            this.inReplyToUserId = inReplyToUserId
        }

        /** Username this result replies to. */
        fun inReplyToUsername(inReplyToUsername: String) =
            inReplyToUsername(JsonField.of(inReplyToUsername))

        /** Sets [Builder.inReplyToUsername] to an arbitrary JSON value. */
        fun inReplyToUsername(inReplyToUsername: JsonField<String>) = apply {
            this.inReplyToUsername = inReplyToUsername
        }

        /**
         * The notice x.com covers the post with until the reader views it, such as X's public
         * interest notice on a post that broke its rules but stays up.
         */
        fun interstitial(interstitial: Interstitial) = interstitial(JsonField.of(interstitial))

        /** Sets [Builder.interstitial] to an arbitrary JSON value. */
        fun interstitial(interstitial: JsonField<Interstitial>) = apply {
            this.interstitial = interstitial
        }

        /** Whether the tweet has limited reply permissions */
        fun isLimitedReply(isLimitedReply: Boolean) = isLimitedReply(JsonField.of(isLimitedReply))

        /** Sets [Builder.isLimitedReply] to an arbitrary JSON value. */
        fun isLimitedReply(isLimitedReply: JsonField<Boolean>) = apply {
            this.isLimitedReply = isLimitedReply
        }

        /** True for Note Tweets (long-form content, up to 25,000 characters) */
        fun isNoteTweet(isNoteTweet: Boolean) = isNoteTweet(JsonField.of(isNoteTweet))

        /** Sets [Builder.isNoteTweet] to an arbitrary JSON value. */
        fun isNoteTweet(isNoteTweet: JsonField<Boolean>) = apply { this.isNoteTweet = isNoteTweet }

        fun isPinned(isPinned: Boolean) = isPinned(JsonField.of(isPinned))

        /** Sets [Builder.isPinned] to an arbitrary JSON value. */
        fun isPinned(isPinned: JsonField<Boolean>) = apply { this.isPinned = isPinned }

        /** True when this search result quotes another tweet */
        fun isQuoteStatus(isQuoteStatus: Boolean) = isQuoteStatus(JsonField.of(isQuoteStatus))

        /** Sets [Builder.isQuoteStatus] to an arbitrary JSON value. */
        fun isQuoteStatus(isQuoteStatus: JsonField<Boolean>) = apply {
            this.isQuoteStatus = isQuoteStatus
        }

        /** True when this search result is a reply */
        fun isReply(isReply: Boolean) = isReply(JsonField.of(isReply))

        /** Sets [Builder.isReply] to an arbitrary JSON value. */
        fun isReply(isReply: JsonField<Boolean>) = apply { this.isReply = isReply }

        fun isRetweet(isRetweet: Boolean) = isRetweet(JsonField.of(isRetweet))

        /** Sets [Builder.isRetweet] to an arbitrary JSON value. */
        fun isRetweet(isRetweet: JsonField<Boolean>) = apply { this.isRetweet = isRetweet }

        fun isTranslatable(isTranslatable: Boolean) = isTranslatable(JsonField.of(isTranslatable))

        /** Sets [Builder.isTranslatable] to an arbitrary JSON value. */
        fun isTranslatable(isTranslatable: JsonField<Boolean>) = apply {
            this.isTranslatable = isTranslatable
        }

        /** Public metadata whose fields X defines. */
        fun jetfuelAttachment(jetfuelAttachment: JetfuelAttachment) =
            jetfuelAttachment(JsonField.of(jetfuelAttachment))

        /** Sets [Builder.jetfuelAttachment] to an arbitrary JSON value. */
        fun jetfuelAttachment(jetfuelAttachment: JsonField<JetfuelAttachment>) = apply {
            this.jetfuelAttachment = jetfuelAttachment
        }

        /** Search result language code. */
        fun lang(lang: String) = lang(JsonField.of(lang))

        /** Sets [Builder.lang] to an arbitrary JSON value. */
        fun lang(lang: JsonField<String>) = apply { this.lang = lang }

        /** Public restrictions & prompts. */
        fun limitedActions(limitedActions: List<LimitedAction>) =
            limitedActions(JsonField.of(limitedActions))

        /** Sets [Builder.limitedActions] to an arbitrary JSON value. */
        fun limitedActions(limitedActions: JsonField<List<LimitedAction>>) = apply {
            this.limitedActions = limitedActions.map { it.toMutableList() }
        }

        /**
         * Adds a single [LimitedAction] to [limitedActions].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addLimitedAction(limitedAction: LimitedAction) = apply {
            limitedActions =
                (limitedActions ?: JsonField.of(mutableListOf())).also {
                    checkKnown("limitedActions", it).add(limitedAction)
                }
        }

        /** Media, omitted when unavailable. */
        fun media(media: List<TweetMedia>) = media(JsonField.of(media))

        /** Sets [Builder.media] to an arbitrary JSON value. */
        fun media(media: JsonField<List<TweetMedia>>) = apply {
            this.media = media.map { it.toMutableList() }
        }

        /**
         * Adds a single [TweetMedia] to [media].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addMedia(media: TweetMedia) = apply {
            this.media =
                (this.media ?: JsonField.of(mutableListOf())).also {
                    checkKnown("media", it).add(media)
                }
        }

        /** Complete Note Tweet content and rich-text metadata. */
        fun noteTweet(noteTweet: NoteTweet) = noteTweet(JsonField.of(noteTweet))

        /** Sets [Builder.noteTweet] to an arbitrary JSON value. */
        fun noteTweet(noteTweet: JsonField<NoteTweet>) = apply { this.noteTweet = noteTweet }

        /**
         * A parent, quoted or retweeted tweet. It has the TweetPublicData fields and an author.
         * Nesting stops after 4 levels.
         */
        fun parentTweet(parentTweet: EmbeddedTweet) = parentTweet(JsonField.of(parentTweet))

        /** Sets [Builder.parentTweet] to an arbitrary JSON value. */
        fun parentTweet(parentTweet: JsonField<EmbeddedTweet>) = apply {
            this.parentTweet = parentTweet
        }

        /** Public place metadata attached to a tweet. */
        fun place(place: Place) = place(JsonField.of(place))

        /** Sets [Builder.place] to an arbitrary JSON value. */
        fun place(place: JsonField<Place>) = apply { this.place = place }

        fun possiblySensitive(possiblySensitive: Boolean) =
            possiblySensitive(JsonField.of(possiblySensitive))

        /** Sets [Builder.possiblySensitive] to an arbitrary JSON value. */
        fun possiblySensitive(possiblySensitive: JsonField<Boolean>) = apply {
            this.possiblySensitive = possiblySensitive
        }

        /** Public metadata whose fields X defines. */
        fun postCta(postCta: PostCta) = postCta(JsonField.of(postCta))

        /** Sets [Builder.postCta] to an arbitrary JSON value. */
        fun postCta(postCta: JsonField<PostCta>) = apply { this.postCta = postCta }

        /** Engagement counts retained from a prior tweet edit. */
        fun previousCounts(previousCounts: PreviousCounts) =
            previousCounts(JsonField.of(previousCounts))

        /** Sets [Builder.previousCounts] to an arbitrary JSON value. */
        fun previousCounts(previousCounts: JsonField<PreviousCounts>) = apply {
            this.previousCounts = previousCounts
        }

        fun quotedTweetId(quotedTweetId: String) = quotedTweetId(JsonField.of(quotedTweetId))

        /** Sets [Builder.quotedTweetId] to an arbitrary JSON value. */
        fun quotedTweetId(quotedTweetId: JsonField<String>) = apply {
            this.quotedTweetId = quotedTweetId
        }

        /** The quoted post's link, kept when X withholds that post. */
        fun quotedTweetPermalink(quotedTweetPermalink: QuotedTweetPermalink) =
            quotedTweetPermalink(JsonField.of(quotedTweetPermalink))

        /** Sets [Builder.quotedTweetPermalink] to an arbitrary JSON value. */
        fun quotedTweetPermalink(quotedTweetPermalink: JsonField<QuotedTweetPermalink>) = apply {
            this.quotedTweetPermalink = quotedTweetPermalink
        }

        /**
         * Why X shows no quoted post. Omitted when X names no reason, as its legacy answers never
         * do.
         */
        fun quotedTweetUnavailable(quotedTweetUnavailable: QuotedTweetUnavailable) =
            quotedTweetUnavailable(JsonField.of(quotedTweetUnavailable))

        /** Sets [Builder.quotedTweetUnavailable] to an arbitrary JSON value. */
        fun quotedTweetUnavailable(quotedTweetUnavailable: JsonField<QuotedTweetUnavailable>) =
            apply {
                this.quotedTweetUnavailable = quotedTweetUnavailable
            }

        /**
         * Quoted or retweeted tweet context. Every object includes id, text, and engagement
         * metrics. A zero metric can mean X did not report the count. Author, media, and
         * conversation fields appear when available.
         */
        fun quotedTweet(quotedTweet: EmbeddedTweet) = quotedTweet(JsonField.of(quotedTweet))

        /** Sets [Builder.quotedTweet] to an arbitrary JSON value. */
        fun quotedTweet(quotedTweet: JsonField<EmbeddedTweet>) = apply {
            this.quotedTweet = quotedTweet
        }

        /** Public post & user this reaction references. */
        fun reactionContext(reactionContext: ReactionContext) =
            reactionContext(JsonField.of(reactionContext))

        /** Sets [Builder.reactionContext] to an arbitrary JSON value. */
        fun reactionContext(reactionContext: JsonField<ReactionContext>) = apply {
            this.reactionContext = reactionContext
        }

        /** Repost event time in UTC. Null when unavailable. Omitted for other posts. */
        fun retweetedAt(retweetedAt: OffsetDateTime?) =
            retweetedAt(JsonField.ofNullable(retweetedAt))

        /** Sets [Builder.retweetedAt] to an arbitrary JSON value. */
        fun retweetedAt(retweetedAt: JsonField<OffsetDateTime>) = apply {
            this.retweetedAt = retweetedAt
        }

        /**
         * Quoted or retweeted tweet context. Every object includes id, text, and engagement
         * metrics. A zero metric can mean X did not report the count. Author, media, and
         * conversation fields appear when available.
         */
        fun retweetedTweet(retweetedTweet: EmbeddedTweet) =
            retweetedTweet(JsonField.of(retweetedTweet))

        /** Sets [Builder.retweetedTweet] to an arbitrary JSON value. */
        fun retweetedTweet(retweetedTweet: JsonField<EmbeddedTweet>) = apply {
            this.retweetedTweet = retweetedTweet
        }

        /** Public metadata whose fields X defines. */
        fun scopes(scopes: Scopes) = scopes(JsonField.of(scopes))

        /** Sets [Builder.scopes] to an arbitrary JSON value. */
        fun scopes(scopes: JsonField<Scopes>) = apply { this.scopes = scopes }

        /** The app the post was sent from, as X labels it. X sends it for some posts only. */
        fun source(source: String) = source(JsonField.of(source))

        /** Sets [Builder.source] to an arbitrary JSON value. */
        fun source(source: JsonField<String>) = apply { this.source = source }

        /** Game metadata attached to this post. */
        fun sportsContext(sportsContext: SportsContext) = sportsContext(JsonField.of(sportsContext))

        /** Sets [Builder.sportsContext] to an arbitrary JSON value. */
        fun sportsContext(sportsContext: JsonField<SportsContext>) = apply {
            this.sportsContext = sportsContext
        }

        /**
         * The Subscriber badge X shows on a reply. The reply's author subscribes to the creator who
         * started the conversation. Omitted on a post without the badge.
         */
        fun subscriberBadge(subscriberBadge: SubscriberBadge) =
            subscriberBadge(JsonField.of(subscriberBadge))

        /** Sets [Builder.subscriberBadge] to an arbitrary JSON value. */
        fun subscriberBadge(subscriberBadge: JsonField<SubscriberBadge>) = apply {
            this.subscriberBadge = subscriberBadge
        }

        /**
         * Where `text` matches the search, on each row a search read. Offsets count code points of
         * `text`, as `displayTextRange` does. Empty when X marks nothing in the post. X marks
         * matches only in a post's first 280 characters.
         */
        fun textHighlights(textHighlights: List<TextHighlight>) =
            textHighlights(JsonField.of(textHighlights))

        /** Sets [Builder.textHighlights] to an arbitrary JSON value. */
        fun textHighlights(textHighlights: JsonField<List<TextHighlight>>) = apply {
            this.textHighlights = textHighlights.map { it.toMutableList() }
        }

        /**
         * Adds a single [TextHighlight] to [textHighlights].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addTextHighlight(textHighlight: TextHighlight) = apply {
            textHighlights =
                (textHighlights ?: JsonField.of(mutableListOf())).also {
                    checkKnown("textHighlights", it).add(textHighlight)
                }
        }

        /** Public visibility notice attached to an available tweet. */
        fun tombstone(tombstone: Tombstone) = tombstone(JsonField.of(tombstone))

        /** Sets [Builder.tombstone] to an arbitrary JSON value. */
        fun tombstone(tombstone: JsonField<Tombstone>) = apply { this.tombstone = tombstone }

        fun type(type: String) = type(JsonField.of(type))

        /** Sets [Builder.type] to an arbitrary JSON value. */
        fun type(type: JsonField<String>) = apply { this.type = type }

        /** The author's ID when X withholds the author's profile. */
        fun unavailableAuthorId(unavailableAuthorId: String) =
            unavailableAuthorId(JsonField.of(unavailableAuthorId))

        /** Sets [Builder.unavailableAuthorId] to an arbitrary JSON value. */
        fun unavailableAuthorId(unavailableAuthorId: JsonField<String>) = apply {
            this.unavailableAuthorId = unavailableAuthorId
        }

        /** User IDs that left this conversation. */
        fun unmentionedUserIds(unmentionedUserIds: List<String>) =
            unmentionedUserIds(JsonField.of(unmentionedUserIds))

        /** Sets [Builder.unmentionedUserIds] to an arbitrary JSON value. */
        fun unmentionedUserIds(unmentionedUserIds: JsonField<List<String>>) = apply {
            this.unmentionedUserIds = unmentionedUserIds.map { it.toMutableList() }
        }

        /**
         * Adds a single [String] to [unmentionedUserIds].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addUnmentionedUserId(unmentionedUserId: String) = apply {
            unmentionedUserIds =
                (unmentionedUserIds ?: JsonField.of(mutableListOf())).also {
                    checkKnown("unmentionedUserIds", it).add(unmentionedUserId)
                }
        }

        /** Search result permalink. */
        fun url(url: String) = url(JsonField.of(url))

        /** Sets [Builder.url] to an arbitrary JSON value. */
        fun url(url: JsonField<String>) = apply { this.url = url }

        fun viewState(viewState: String) = viewState(JsonField.of(viewState))

        /** Sets [Builder.viewState] to an arbitrary JSON value. */
        fun viewState(viewState: JsonField<String>) = apply { this.viewState = viewState }

        fun hiddenByAuthor(hiddenByAuthor: Boolean) = hiddenByAuthor(JsonField.of(hiddenByAuthor))

        /** Sets [Builder.hiddenByAuthor] to an arbitrary JSON value. */
        fun hiddenByAuthor(hiddenByAuthor: JsonField<Boolean>) = apply {
            this.hiddenByAuthor = hiddenByAuthor
        }

        fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.clear()
            putAllAdditionalProperties(additionalProperties)
        }

        fun putAdditionalProperty(key: String, value: JsonValue) = apply {
            additionalProperties.put(key, value)
        }

        fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.putAll(additionalProperties)
        }

        fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

        fun removeAllAdditionalProperties(keys: Set<String>) = apply {
            keys.forEach(::removeAdditionalProperty)
        }

        /**
         * Returns an immutable instance of [SearchTweet].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```kotlin
         * .id()
         * .bookmarkCount()
         * .likeCount()
         * .quoteCount()
         * .replyCount()
         * .retweetCount()
         * .text()
         * .viewCount()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): SearchTweet =
            SearchTweet(
                checkRequired("id", id),
                checkRequired("bookmarkCount", bookmarkCount),
                (unknownCounts ?: JsonMissing.of()).map { it.toImmutable() },
                checkRequired("likeCount", likeCount),
                checkRequired("quoteCount", quoteCount),
                checkRequired("replyCount", replyCount),
                checkRequired("retweetCount", retweetCount),
                checkRequired("text", text),
                checkRequired("viewCount", viewCount),
                article,
                author,
                authorUnavailable,
                card,
                (cashtagAttachments ?: JsonMissing.of()).map { it.toImmutable() },
                communityId,
                communityName,
                communityNote,
                contentDisclosure,
                conversationControl,
                conversationId,
                coordinates,
                createdAt,
                (displayTextRange ?: JsonMissing.of()).map { it.toImmutable() },
                edit,
                entities,
                exclusiveContent,
                geo,
                grokShareAttachment,
                grokTranslatedPost,
                hasCommunityNotes,
                inReplyToId,
                inReplyToUserId,
                inReplyToUsername,
                interstitial,
                isLimitedReply,
                isNoteTweet,
                isPinned,
                isQuoteStatus,
                isReply,
                isRetweet,
                isTranslatable,
                jetfuelAttachment,
                lang,
                (limitedActions ?: JsonMissing.of()).map { it.toImmutable() },
                (media ?: JsonMissing.of()).map { it.toImmutable() },
                noteTweet,
                parentTweet,
                place,
                possiblySensitive,
                postCta,
                previousCounts,
                quotedTweetId,
                quotedTweetPermalink,
                quotedTweetUnavailable,
                quotedTweet,
                reactionContext,
                retweetedAt,
                retweetedTweet,
                scopes,
                source,
                sportsContext,
                subscriberBadge,
                (textHighlights ?: JsonMissing.of()).map { it.toImmutable() },
                tombstone,
                type,
                unavailableAuthorId,
                (unmentionedUserIds ?: JsonMissing.of()).map { it.toImmutable() },
                url,
                viewState,
                hiddenByAuthor,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    /**
     * Validates that the types of all values in this object match their expected types recursively.
     *
     * This method is _not_ forwards compatible with new types from the API for existing fields.
     *
     * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't match
     *   its expected type.
     */
    fun validate(): SearchTweet = apply {
        if (validated) {
            return@apply
        }

        id()
        bookmarkCount()
        unknownCounts()?.forEach { it.validate() }
        likeCount()
        quoteCount()
        replyCount()
        retweetCount()
        text()
        viewCount()
        article()?.validate()
        author()?.validate()
        authorUnavailable()?.validate()
        card()?.validate()
        cashtagAttachments()?.forEach { it.validate() }
        communityId()
        communityName()
        communityNote()?.validate()
        contentDisclosure()?.validate()
        conversationControl()?.validate()
        conversationId()
        coordinates()?.validate()
        createdAt()
        displayTextRange()
        edit()?.validate()
        entities()?.validate()
        exclusiveContent()?.validate()
        geo()?.validate()
        grokShareAttachment()?.validate()
        grokTranslatedPost()?.validate()
        hasCommunityNotes()
        inReplyToId()
        inReplyToUserId()
        inReplyToUsername()
        interstitial()?.validate()
        isLimitedReply()
        isNoteTweet()
        isPinned()
        isQuoteStatus()
        isReply()
        isRetweet()
        isTranslatable()
        jetfuelAttachment()?.validate()
        lang()
        limitedActions()?.forEach { it.validate() }
        media()?.forEach { it.validate() }
        noteTweet()?.validate()
        parentTweet()?.validate()
        place()?.validate()
        possiblySensitive()
        postCta()?.validate()
        previousCounts()?.validate()
        quotedTweetId()
        quotedTweetPermalink()?.validate()
        quotedTweetUnavailable()?.validate()
        quotedTweet()?.validate()
        reactionContext()?.validate()
        retweetedAt()
        retweetedTweet()?.validate()
        scopes()?.validate()
        source()
        sportsContext()?.validate()
        subscriberBadge()?.validate()
        textHighlights()?.forEach { it.validate() }
        tombstone()?.validate()
        type()
        unavailableAuthorId()
        unmentionedUserIds()
        url()
        viewState()
        hiddenByAuthor()
        validated = true
    }

    fun isValid(): Boolean =
        try {
            validate()
            true
        } catch (e: XTwitterScraperInvalidDataException) {
            false
        }

    internal fun validity(): Int =
        (if (hiddenByAuthor.asKnown() == null) 0 else 1) +
            (if (id.asKnown() == null) 0 else 1) +
            (if (bookmarkCount.asKnown() == null) 0 else 1) +
            (unknownCounts.asKnown()?.sumOf { it.validity() } ?: 0) +
            (if (likeCount.asKnown() == null) 0 else 1) +
            (if (quoteCount.asKnown() == null) 0 else 1) +
            (if (replyCount.asKnown() == null) 0 else 1) +
            (if (retweetCount.asKnown() == null) 0 else 1) +
            (if (text.asKnown() == null) 0 else 1) +
            (if (viewCount.asKnown() == null) 0 else 1) +
            (article.asKnown()?.validity() ?: 0) +
            (author.asKnown()?.validity() ?: 0) +
            (authorUnavailable.asKnown()?.validity() ?: 0) +
            (card.asKnown()?.validity() ?: 0) +
            (cashtagAttachments.asKnown()?.sumOf { it.validity() } ?: 0) +
            (if (communityId.asKnown() == null) 0 else 1) +
            (if (communityName.asKnown() == null) 0 else 1) +
            (communityNote.asKnown()?.validity() ?: 0) +
            (contentDisclosure.asKnown()?.validity() ?: 0) +
            (conversationControl.asKnown()?.validity() ?: 0) +
            (if (conversationId.asKnown() == null) 0 else 1) +
            (coordinates.asKnown()?.validity() ?: 0) +
            (if (createdAt.asKnown() == null) 0 else 1) +
            (displayTextRange.asKnown()?.size ?: 0) +
            (edit.asKnown()?.validity() ?: 0) +
            (entities.asKnown()?.validity() ?: 0) +
            (exclusiveContent.asKnown()?.validity() ?: 0) +
            (geo.asKnown()?.validity() ?: 0) +
            (grokShareAttachment.asKnown()?.validity() ?: 0) +
            (grokTranslatedPost.asKnown()?.validity() ?: 0) +
            (if (hasCommunityNotes.asKnown() == null) 0 else 1) +
            (if (inReplyToId.asKnown() == null) 0 else 1) +
            (if (inReplyToUserId.asKnown() == null) 0 else 1) +
            (if (inReplyToUsername.asKnown() == null) 0 else 1) +
            (interstitial.asKnown()?.validity() ?: 0) +
            (if (isLimitedReply.asKnown() == null) 0 else 1) +
            (if (isNoteTweet.asKnown() == null) 0 else 1) +
            (if (isPinned.asKnown() == null) 0 else 1) +
            (if (isQuoteStatus.asKnown() == null) 0 else 1) +
            (if (isReply.asKnown() == null) 0 else 1) +
            (if (isRetweet.asKnown() == null) 0 else 1) +
            (if (isTranslatable.asKnown() == null) 0 else 1) +
            (jetfuelAttachment.asKnown()?.validity() ?: 0) +
            (if (lang.asKnown() == null) 0 else 1) +
            (limitedActions.asKnown()?.sumOf { it.validity() } ?: 0) +
            (media.asKnown()?.sumOf { it.validity() } ?: 0) +
            (noteTweet.asKnown()?.validity() ?: 0) +
            (parentTweet.asKnown()?.validity() ?: 0) +
            (place.asKnown()?.validity() ?: 0) +
            (if (possiblySensitive.asKnown() == null) 0 else 1) +
            (postCta.asKnown()?.validity() ?: 0) +
            (previousCounts.asKnown()?.validity() ?: 0) +
            (if (quotedTweetId.asKnown() == null) 0 else 1) +
            (quotedTweetPermalink.asKnown()?.validity() ?: 0) +
            (quotedTweetUnavailable.asKnown()?.validity() ?: 0) +
            (quotedTweet.asKnown()?.validity() ?: 0) +
            (reactionContext.asKnown()?.validity() ?: 0) +
            (if (retweetedAt.asKnown() == null) 0 else 1) +
            (retweetedTweet.asKnown()?.validity() ?: 0) +
            (scopes.asKnown()?.validity() ?: 0) +
            (if (source.asKnown() == null) 0 else 1) +
            (sportsContext.asKnown()?.validity() ?: 0) +
            (subscriberBadge.asKnown()?.validity() ?: 0) +
            (textHighlights.asKnown()?.sumOf { it.validity() } ?: 0) +
            (tombstone.asKnown()?.validity() ?: 0) +
            (if (type.asKnown() == null) 0 else 1) +
            (if (unavailableAuthorId.asKnown() == null) 0 else 1) +
            (unmentionedUserIds.asKnown()?.size ?: 0) +
            (if (url.asKnown() == null) 0 else 1) +
            (if (viewState.asKnown() == null) 0 else 1)

    /** An X Article preview and its lifecycle metadata. */
    class Article
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val id: JsonField<String>,
        private val coverMedia: JsonField<CoverMedia>,
        private val coverMediaUrl: JsonField<String>,
        private val lifecycleState: JsonField<LifecycleState>,
        private val metadata: JsonField<Metadata>,
        private val previewText: JsonField<String>,
        private val title: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
            @JsonProperty("coverMedia")
            @ExcludeMissing
            coverMedia: JsonField<CoverMedia> = JsonMissing.of(),
            @JsonProperty("coverMediaUrl")
            @ExcludeMissing
            coverMediaUrl: JsonField<String> = JsonMissing.of(),
            @JsonProperty("lifecycleState")
            @ExcludeMissing
            lifecycleState: JsonField<LifecycleState> = JsonMissing.of(),
            @JsonProperty("metadata")
            @ExcludeMissing
            metadata: JsonField<Metadata> = JsonMissing.of(),
            @JsonProperty("previewText")
            @ExcludeMissing
            previewText: JsonField<String> = JsonMissing.of(),
            @JsonProperty("title") @ExcludeMissing title: JsonField<String> = JsonMissing.of(),
        ) : this(
            id,
            coverMedia,
            coverMediaUrl,
            lifecycleState,
            metadata,
            previewText,
            title,
            mutableMapOf(),
        )

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun id(): String? = id.getNullable("id")

        /**
         * Public metadata whose fields X defines.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun coverMedia(): CoverMedia? = coverMedia.getNullable("coverMedia")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun coverMediaUrl(): String? = coverMediaUrl.getNullable("coverMediaUrl")

        /**
         * Public metadata whose fields X defines.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun lifecycleState(): LifecycleState? = lifecycleState.getNullable("lifecycleState")

        /**
         * Public metadata whose fields X defines.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun metadata(): Metadata? = metadata.getNullable("metadata")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun previewText(): String? = previewText.getNullable("previewText")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun title(): String? = title.getNullable("title")

        /**
         * Returns the raw JSON value of [id].
         *
         * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

        /**
         * Returns the raw JSON value of [coverMedia].
         *
         * Unlike [coverMedia], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("coverMedia")
        @ExcludeMissing
        fun _coverMedia(): JsonField<CoverMedia> = coverMedia

        /**
         * Returns the raw JSON value of [coverMediaUrl].
         *
         * Unlike [coverMediaUrl], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("coverMediaUrl")
        @ExcludeMissing
        fun _coverMediaUrl(): JsonField<String> = coverMediaUrl

        /**
         * Returns the raw JSON value of [lifecycleState].
         *
         * Unlike [lifecycleState], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("lifecycleState")
        @ExcludeMissing
        fun _lifecycleState(): JsonField<LifecycleState> = lifecycleState

        /**
         * Returns the raw JSON value of [metadata].
         *
         * Unlike [metadata], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("metadata") @ExcludeMissing fun _metadata(): JsonField<Metadata> = metadata

        /**
         * Returns the raw JSON value of [previewText].
         *
         * Unlike [previewText], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("previewText")
        @ExcludeMissing
        fun _previewText(): JsonField<String> = previewText

        /**
         * Returns the raw JSON value of [title].
         *
         * Unlike [title], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("title") @ExcludeMissing fun _title(): JsonField<String> = title

        @JsonAnySetter
        private fun putAdditionalProperty(key: String, value: JsonValue) {
            additionalProperties.put(key, value)
        }

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> =
            Collections.unmodifiableMap(additionalProperties)

        fun toBuilder() = Builder().from(this)

        companion object {

            /** Returns a mutable builder for constructing an instance of [Article]. */
            fun builder() = Builder()
        }

        /** A builder for [Article]. */
        class Builder internal constructor() {

            private var id: JsonField<String> = JsonMissing.of()
            private var coverMedia: JsonField<CoverMedia> = JsonMissing.of()
            private var coverMediaUrl: JsonField<String> = JsonMissing.of()
            private var lifecycleState: JsonField<LifecycleState> = JsonMissing.of()
            private var metadata: JsonField<Metadata> = JsonMissing.of()
            private var previewText: JsonField<String> = JsonMissing.of()
            private var title: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(article: Article) = apply {
                id = article.id
                coverMedia = article.coverMedia
                coverMediaUrl = article.coverMediaUrl
                lifecycleState = article.lifecycleState
                metadata = article.metadata
                previewText = article.previewText
                title = article.title
                additionalProperties = article.additionalProperties.toMutableMap()
            }

            fun id(id: String) = id(JsonField.of(id))

            /** Sets [Builder.id] to an arbitrary JSON value. */
            fun id(id: JsonField<String>) = apply { this.id = id }

            /** Public metadata whose fields X defines. */
            fun coverMedia(coverMedia: CoverMedia) = coverMedia(JsonField.of(coverMedia))

            /** Sets [Builder.coverMedia] to an arbitrary JSON value. */
            fun coverMedia(coverMedia: JsonField<CoverMedia>) = apply {
                this.coverMedia = coverMedia
            }

            fun coverMediaUrl(coverMediaUrl: String) = coverMediaUrl(JsonField.of(coverMediaUrl))

            /** Sets [Builder.coverMediaUrl] to an arbitrary JSON value. */
            fun coverMediaUrl(coverMediaUrl: JsonField<String>) = apply {
                this.coverMediaUrl = coverMediaUrl
            }

            /** Public metadata whose fields X defines. */
            fun lifecycleState(lifecycleState: LifecycleState) =
                lifecycleState(JsonField.of(lifecycleState))

            /** Sets [Builder.lifecycleState] to an arbitrary JSON value. */
            fun lifecycleState(lifecycleState: JsonField<LifecycleState>) = apply {
                this.lifecycleState = lifecycleState
            }

            /** Public metadata whose fields X defines. */
            fun metadata(metadata: Metadata) = metadata(JsonField.of(metadata))

            /** Sets [Builder.metadata] to an arbitrary JSON value. */
            fun metadata(metadata: JsonField<Metadata>) = apply { this.metadata = metadata }

            fun previewText(previewText: String) = previewText(JsonField.of(previewText))

            /** Sets [Builder.previewText] to an arbitrary JSON value. */
            fun previewText(previewText: JsonField<String>) = apply {
                this.previewText = previewText
            }

            fun title(title: String) = title(JsonField.of(title))

            /** Sets [Builder.title] to an arbitrary JSON value. */
            fun title(title: JsonField<String>) = apply { this.title = title }

            fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.clear()
                putAllAdditionalProperties(additionalProperties)
            }

            fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                additionalProperties.put(key, value)
            }

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            /**
             * Returns an immutable instance of [Article].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): Article =
                Article(
                    id,
                    coverMedia,
                    coverMediaUrl,
                    lifecycleState,
                    metadata,
                    previewText,
                    title,
                    additionalProperties.toMutableMap(),
                )
        }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
         *   match its expected type.
         */
        fun validate(): Article = apply {
            if (validated) {
                return@apply
            }

            id()
            coverMedia()?.validate()
            coverMediaUrl()
            lifecycleState()?.validate()
            metadata()?.validate()
            previewText()
            title()
            validated = true
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: XTwitterScraperInvalidDataException) {
                false
            }

        internal fun validity(): Int =
            (if (id.asKnown() == null) 0 else 1) +
                (coverMedia.asKnown()?.validity() ?: 0) +
                (if (coverMediaUrl.asKnown() == null) 0 else 1) +
                (lifecycleState.asKnown()?.validity() ?: 0) +
                (metadata.asKnown()?.validity() ?: 0) +
                (if (previewText.asKnown() == null) 0 else 1) +
                (if (title.asKnown() == null) 0 else 1)

        /** Public metadata whose fields X defines. */
        class CoverMedia
        @JsonCreator
        private constructor(
            @com.fasterxml.jackson.annotation.JsonValue
            private val additionalProperties: Map<String, JsonValue>
        ) {

            @JsonAnyGetter
            @ExcludeMissing
            fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

            fun toBuilder() = Builder().from(this)

            companion object {

                /** Returns a mutable builder for constructing an instance of [CoverMedia]. */
                fun builder() = Builder()
            }

            /** A builder for [CoverMedia]. */
            class Builder internal constructor() {

                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                internal fun from(coverMedia: CoverMedia) = apply {
                    additionalProperties = coverMedia.additionalProperties.toMutableMap()
                }

                fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                    this.additionalProperties.clear()
                    putAllAdditionalProperties(additionalProperties)
                }

                fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                    additionalProperties.put(key, value)
                }

                fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) =
                    apply {
                        this.additionalProperties.putAll(additionalProperties)
                    }

                fun removeAdditionalProperty(key: String) = apply {
                    additionalProperties.remove(key)
                }

                fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                    keys.forEach(::removeAdditionalProperty)
                }

                /**
                 * Returns an immutable instance of [CoverMedia].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 */
                fun build(): CoverMedia = CoverMedia(additionalProperties.toImmutable())
            }

            private var validated: Boolean = false

            /**
             * Validates that the types of all values in this object match their expected types
             * recursively.
             *
             * This method is _not_ forwards compatible with new types from the API for existing
             * fields.
             *
             * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
             *   match its expected type.
             */
            fun validate(): CoverMedia = apply {
                if (validated) {
                    return@apply
                }

                validated = true
            }

            fun isValid(): Boolean =
                try {
                    validate()
                    true
                } catch (e: XTwitterScraperInvalidDataException) {
                    false
                }

            internal fun validity(): Int = additionalProperties.count { (_, value) ->
                !value.isNull() && !value.isMissing()
            }

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is CoverMedia && additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy { Objects.hash(additionalProperties) }

            override fun hashCode(): Int = hashCode

            override fun toString() = "CoverMedia{additionalProperties=$additionalProperties}"
        }

        /** Public metadata whose fields X defines. */
        class LifecycleState
        @JsonCreator
        private constructor(
            @com.fasterxml.jackson.annotation.JsonValue
            private val additionalProperties: Map<String, JsonValue>
        ) {

            @JsonAnyGetter
            @ExcludeMissing
            fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

            fun toBuilder() = Builder().from(this)

            companion object {

                /** Returns a mutable builder for constructing an instance of [LifecycleState]. */
                fun builder() = Builder()
            }

            /** A builder for [LifecycleState]. */
            class Builder internal constructor() {

                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                internal fun from(lifecycleState: LifecycleState) = apply {
                    additionalProperties = lifecycleState.additionalProperties.toMutableMap()
                }

                fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                    this.additionalProperties.clear()
                    putAllAdditionalProperties(additionalProperties)
                }

                fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                    additionalProperties.put(key, value)
                }

                fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) =
                    apply {
                        this.additionalProperties.putAll(additionalProperties)
                    }

                fun removeAdditionalProperty(key: String) = apply {
                    additionalProperties.remove(key)
                }

                fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                    keys.forEach(::removeAdditionalProperty)
                }

                /**
                 * Returns an immutable instance of [LifecycleState].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 */
                fun build(): LifecycleState = LifecycleState(additionalProperties.toImmutable())
            }

            private var validated: Boolean = false

            /**
             * Validates that the types of all values in this object match their expected types
             * recursively.
             *
             * This method is _not_ forwards compatible with new types from the API for existing
             * fields.
             *
             * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
             *   match its expected type.
             */
            fun validate(): LifecycleState = apply {
                if (validated) {
                    return@apply
                }

                validated = true
            }

            fun isValid(): Boolean =
                try {
                    validate()
                    true
                } catch (e: XTwitterScraperInvalidDataException) {
                    false
                }

            internal fun validity(): Int = additionalProperties.count { (_, value) ->
                !value.isNull() && !value.isMissing()
            }

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is LifecycleState && additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy { Objects.hash(additionalProperties) }

            override fun hashCode(): Int = hashCode

            override fun toString() = "LifecycleState{additionalProperties=$additionalProperties}"
        }

        /** Public metadata whose fields X defines. */
        class Metadata
        @JsonCreator
        private constructor(
            @com.fasterxml.jackson.annotation.JsonValue
            private val additionalProperties: Map<String, JsonValue>
        ) {

            @JsonAnyGetter
            @ExcludeMissing
            fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

            fun toBuilder() = Builder().from(this)

            companion object {

                /** Returns a mutable builder for constructing an instance of [Metadata]. */
                fun builder() = Builder()
            }

            /** A builder for [Metadata]. */
            class Builder internal constructor() {

                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                internal fun from(metadata: Metadata) = apply {
                    additionalProperties = metadata.additionalProperties.toMutableMap()
                }

                fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                    this.additionalProperties.clear()
                    putAllAdditionalProperties(additionalProperties)
                }

                fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                    additionalProperties.put(key, value)
                }

                fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) =
                    apply {
                        this.additionalProperties.putAll(additionalProperties)
                    }

                fun removeAdditionalProperty(key: String) = apply {
                    additionalProperties.remove(key)
                }

                fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                    keys.forEach(::removeAdditionalProperty)
                }

                /**
                 * Returns an immutable instance of [Metadata].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 */
                fun build(): Metadata = Metadata(additionalProperties.toImmutable())
            }

            private var validated: Boolean = false

            /**
             * Validates that the types of all values in this object match their expected types
             * recursively.
             *
             * This method is _not_ forwards compatible with new types from the API for existing
             * fields.
             *
             * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
             *   match its expected type.
             */
            fun validate(): Metadata = apply {
                if (validated) {
                    return@apply
                }

                validated = true
            }

            fun isValid(): Boolean =
                try {
                    validate()
                    true
                } catch (e: XTwitterScraperInvalidDataException) {
                    false
                }

            internal fun validity(): Int = additionalProperties.count { (_, value) ->
                !value.isNull() && !value.isMissing()
            }

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is Metadata && additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy { Objects.hash(additionalProperties) }

            override fun hashCode(): Int = hashCode

            override fun toString() = "Metadata{additionalProperties=$additionalProperties}"
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Article &&
                id == other.id &&
                coverMedia == other.coverMedia &&
                coverMediaUrl == other.coverMediaUrl &&
                lifecycleState == other.lifecycleState &&
                metadata == other.metadata &&
                previewText == other.previewText &&
                title == other.title &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(
                id,
                coverMedia,
                coverMediaUrl,
                lifecycleState,
                metadata,
                previewText,
                title,
                additionalProperties,
            )
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Article{id=$id, coverMedia=$coverMedia, coverMediaUrl=$coverMediaUrl, lifecycleState=$lifecycleState, metadata=$metadata, previewText=$previewText, title=$title, additionalProperties=$additionalProperties}"
    }

    /**
     * Why the tweet has no author. X serves a suspended or deleted account as an unavailable user
     * with a reason such as Suspended or NotFound. Omitted when the author is available.
     */
    class AuthorUnavailable
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val reason: JsonField<String>,
        private val message: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("reason") @ExcludeMissing reason: JsonField<String> = JsonMissing.of(),
            @JsonProperty("message") @ExcludeMissing message: JsonField<String> = JsonMissing.of(),
        ) : this(reason, message, mutableMapOf())

        /**
         * X's reason code.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or
         *   is unexpectedly missing or null.
         */
        fun reason(): String = reason.getRequired("reason")

        /**
         * X's notice text for the reason.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun message(): String? = message.getNullable("message")

        /**
         * Returns the raw JSON value of [reason].
         *
         * Unlike [reason], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("reason") @ExcludeMissing fun _reason(): JsonField<String> = reason

        /**
         * Returns the raw JSON value of [message].
         *
         * Unlike [message], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("message") @ExcludeMissing fun _message(): JsonField<String> = message

        @JsonAnySetter
        private fun putAdditionalProperty(key: String, value: JsonValue) {
            additionalProperties.put(key, value)
        }

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> =
            Collections.unmodifiableMap(additionalProperties)

        fun toBuilder() = Builder().from(this)

        companion object {

            /**
             * Returns a mutable builder for constructing an instance of [AuthorUnavailable].
             *
             * The following fields are required:
             * ```kotlin
             * .reason()
             * ```
             */
            fun builder() = Builder()
        }

        /** A builder for [AuthorUnavailable]. */
        class Builder internal constructor() {

            private var reason: JsonField<String>? = null
            private var message: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(authorUnavailable: AuthorUnavailable) = apply {
                reason = authorUnavailable.reason
                message = authorUnavailable.message
                additionalProperties = authorUnavailable.additionalProperties.toMutableMap()
            }

            /** X's reason code. */
            fun reason(reason: String) = reason(JsonField.of(reason))

            /** Sets [Builder.reason] to an arbitrary JSON value. */
            fun reason(reason: JsonField<String>) = apply { this.reason = reason }

            /** X's notice text for the reason. */
            fun message(message: String) = message(JsonField.of(message))

            /** Sets [Builder.message] to an arbitrary JSON value. */
            fun message(message: JsonField<String>) = apply { this.message = message }

            fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.clear()
                putAllAdditionalProperties(additionalProperties)
            }

            fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                additionalProperties.put(key, value)
            }

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            /**
             * Returns an immutable instance of [AuthorUnavailable].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             *
             * The following fields are required:
             * ```kotlin
             * .reason()
             * ```
             *
             * @throws IllegalStateException if any required field is unset.
             */
            fun build(): AuthorUnavailable =
                AuthorUnavailable(
                    checkRequired("reason", reason),
                    message,
                    additionalProperties.toMutableMap(),
                )
        }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
         *   match its expected type.
         */
        fun validate(): AuthorUnavailable = apply {
            if (validated) {
                return@apply
            }

            reason()
            message()
            validated = true
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: XTwitterScraperInvalidDataException) {
                false
            }

        internal fun validity(): Int =
            (if (reason.asKnown() == null) 0 else 1) + (if (message.asKnown() == null) 0 else 1)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is AuthorUnavailable &&
                reason == other.reason &&
                message == other.message &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(reason, message, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "AuthorUnavailable{reason=$reason, message=$message, additionalProperties=$additionalProperties}"
    }

    /** A public card and the profiles it references. */
    class Card
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val id: JsonField<String>,
        private val bindingValues: JsonField<BindingValues>,
        private val name: JsonField<String>,
        private val platform: JsonField<Platform>,
        private val url: JsonField<String>,
        private val userReferenceErrors: JsonField<List<UserReferenceError>>,
        private val userReferences: JsonField<List<UserProfile>>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
            @JsonProperty("bindingValues")
            @ExcludeMissing
            bindingValues: JsonField<BindingValues> = JsonMissing.of(),
            @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
            @JsonProperty("platform")
            @ExcludeMissing
            platform: JsonField<Platform> = JsonMissing.of(),
            @JsonProperty("url") @ExcludeMissing url: JsonField<String> = JsonMissing.of(),
            @JsonProperty("userReferenceErrors")
            @ExcludeMissing
            userReferenceErrors: JsonField<List<UserReferenceError>> = JsonMissing.of(),
            @JsonProperty("userReferences")
            @ExcludeMissing
            userReferences: JsonField<List<UserProfile>> = JsonMissing.of(),
        ) : this(
            id,
            bindingValues,
            name,
            platform,
            url,
            userReferenceErrors,
            userReferences,
            mutableMapOf(),
        )

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun id(): String? = id.getNullable("id")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun bindingValues(): BindingValues? = bindingValues.getNullable("bindingValues")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun name(): String? = name.getNullable("name")

        /**
         * Public metadata whose fields X defines.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun platform(): Platform? = platform.getNullable("platform")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun url(): String? = url.getNullable("url")

        /**
         * Unresolved card user references.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun userReferenceErrors(): List<UserReferenceError>? =
            userReferenceErrors.getNullable("userReferenceErrors")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun userReferences(): List<UserProfile>? = userReferences.getNullable("userReferences")

        /**
         * Returns the raw JSON value of [id].
         *
         * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

        /**
         * Returns the raw JSON value of [bindingValues].
         *
         * Unlike [bindingValues], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("bindingValues")
        @ExcludeMissing
        fun _bindingValues(): JsonField<BindingValues> = bindingValues

        /**
         * Returns the raw JSON value of [name].
         *
         * Unlike [name], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<String> = name

        /**
         * Returns the raw JSON value of [platform].
         *
         * Unlike [platform], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("platform") @ExcludeMissing fun _platform(): JsonField<Platform> = platform

        /**
         * Returns the raw JSON value of [url].
         *
         * Unlike [url], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("url") @ExcludeMissing fun _url(): JsonField<String> = url

        /**
         * Returns the raw JSON value of [userReferenceErrors].
         *
         * Unlike [userReferenceErrors], this method doesn't throw if the JSON field has an
         * unexpected type.
         */
        @JsonProperty("userReferenceErrors")
        @ExcludeMissing
        fun _userReferenceErrors(): JsonField<List<UserReferenceError>> = userReferenceErrors

        /**
         * Returns the raw JSON value of [userReferences].
         *
         * Unlike [userReferences], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("userReferences")
        @ExcludeMissing
        fun _userReferences(): JsonField<List<UserProfile>> = userReferences

        @JsonAnySetter
        private fun putAdditionalProperty(key: String, value: JsonValue) {
            additionalProperties.put(key, value)
        }

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> =
            Collections.unmodifiableMap(additionalProperties)

        fun toBuilder() = Builder().from(this)

        companion object {

            /** Returns a mutable builder for constructing an instance of [Card]. */
            fun builder() = Builder()
        }

        /** A builder for [Card]. */
        class Builder internal constructor() {

            private var id: JsonField<String> = JsonMissing.of()
            private var bindingValues: JsonField<BindingValues> = JsonMissing.of()
            private var name: JsonField<String> = JsonMissing.of()
            private var platform: JsonField<Platform> = JsonMissing.of()
            private var url: JsonField<String> = JsonMissing.of()
            private var userReferenceErrors: JsonField<MutableList<UserReferenceError>>? = null
            private var userReferences: JsonField<MutableList<UserProfile>>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(card: Card) = apply {
                id = card.id
                bindingValues = card.bindingValues
                name = card.name
                platform = card.platform
                url = card.url
                userReferenceErrors = card.userReferenceErrors.map { it.toMutableList() }
                userReferences = card.userReferences.map { it.toMutableList() }
                additionalProperties = card.additionalProperties.toMutableMap()
            }

            fun id(id: String) = id(JsonField.of(id))

            /** Sets [Builder.id] to an arbitrary JSON value. */
            fun id(id: JsonField<String>) = apply { this.id = id }

            fun bindingValues(bindingValues: BindingValues) =
                bindingValues(JsonField.of(bindingValues))

            /** Sets [Builder.bindingValues] to an arbitrary JSON value. */
            fun bindingValues(bindingValues: JsonField<BindingValues>) = apply {
                this.bindingValues = bindingValues
            }

            fun name(name: String) = name(JsonField.of(name))

            /** Sets [Builder.name] to an arbitrary JSON value. */
            fun name(name: JsonField<String>) = apply { this.name = name }

            /** Public metadata whose fields X defines. */
            fun platform(platform: Platform) = platform(JsonField.of(platform))

            /** Sets [Builder.platform] to an arbitrary JSON value. */
            fun platform(platform: JsonField<Platform>) = apply { this.platform = platform }

            fun url(url: String) = url(JsonField.of(url))

            /** Sets [Builder.url] to an arbitrary JSON value. */
            fun url(url: JsonField<String>) = apply { this.url = url }

            /** Unresolved card user references. */
            fun userReferenceErrors(userReferenceErrors: List<UserReferenceError>) =
                userReferenceErrors(JsonField.of(userReferenceErrors))

            /** Sets [Builder.userReferenceErrors] to an arbitrary JSON value. */
            fun userReferenceErrors(userReferenceErrors: JsonField<List<UserReferenceError>>) =
                apply {
                    this.userReferenceErrors = userReferenceErrors.map { it.toMutableList() }
                }

            /**
             * Adds a single [UserReferenceError] to [userReferenceErrors].
             *
             * @throws IllegalStateException if the field was previously set to a non-list.
             */
            fun addUserReferenceError(userReferenceError: UserReferenceError) = apply {
                userReferenceErrors =
                    (userReferenceErrors ?: JsonField.of(mutableListOf())).also {
                        checkKnown("userReferenceErrors", it).add(userReferenceError)
                    }
            }

            fun userReferences(userReferences: List<UserProfile>) =
                userReferences(JsonField.of(userReferences))

            /** Sets [Builder.userReferences] to an arbitrary JSON value. */
            fun userReferences(userReferences: JsonField<List<UserProfile>>) = apply {
                this.userReferences = userReferences.map { it.toMutableList() }
            }

            /**
             * Adds a single [UserProfile] to [userReferences].
             *
             * @throws IllegalStateException if the field was previously set to a non-list.
             */
            fun addUserReference(userReference: UserProfile) = apply {
                userReferences =
                    (userReferences ?: JsonField.of(mutableListOf())).also {
                        checkKnown("userReferences", it).add(userReference)
                    }
            }

            fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.clear()
                putAllAdditionalProperties(additionalProperties)
            }

            fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                additionalProperties.put(key, value)
            }

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            /**
             * Returns an immutable instance of [Card].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): Card =
                Card(
                    id,
                    bindingValues,
                    name,
                    platform,
                    url,
                    (userReferenceErrors ?: JsonMissing.of()).map { it.toImmutable() },
                    (userReferences ?: JsonMissing.of()).map { it.toImmutable() },
                    additionalProperties.toMutableMap(),
                )
        }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
         *   match its expected type.
         */
        fun validate(): Card = apply {
            if (validated) {
                return@apply
            }

            id()
            bindingValues()?.validate()
            name()
            platform()?.validate()
            url()
            userReferenceErrors()?.forEach { it.validate() }
            userReferences()?.forEach { it.validate() }
            validated = true
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: XTwitterScraperInvalidDataException) {
                false
            }

        internal fun validity(): Int =
            (if (id.asKnown() == null) 0 else 1) +
                (bindingValues.asKnown()?.validity() ?: 0) +
                (if (name.asKnown() == null) 0 else 1) +
                (platform.asKnown()?.validity() ?: 0) +
                (if (url.asKnown() == null) 0 else 1) +
                (userReferenceErrors.asKnown()?.sumOf { it.validity() } ?: 0) +
                (userReferences.asKnown()?.sumOf { it.validity() } ?: 0)

        /** Public metadata whose fields X defines. */
        class BindingValues
        @JsonCreator
        private constructor(
            @com.fasterxml.jackson.annotation.JsonValue
            private val additionalProperties: Map<String, JsonValue>
        ) {

            @JsonAnyGetter
            @ExcludeMissing
            fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

            fun toBuilder() = Builder().from(this)

            companion object {

                /** Returns a mutable builder for constructing an instance of [BindingValues]. */
                fun builder() = Builder()
            }

            /** A builder for [BindingValues]. */
            class Builder internal constructor() {

                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                internal fun from(bindingValues: BindingValues) = apply {
                    additionalProperties = bindingValues.additionalProperties.toMutableMap()
                }

                fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                    this.additionalProperties.clear()
                    putAllAdditionalProperties(additionalProperties)
                }

                fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                    additionalProperties.put(key, value)
                }

                fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) =
                    apply {
                        this.additionalProperties.putAll(additionalProperties)
                    }

                fun removeAdditionalProperty(key: String) = apply {
                    additionalProperties.remove(key)
                }

                fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                    keys.forEach(::removeAdditionalProperty)
                }

                /**
                 * Returns an immutable instance of [BindingValues].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 */
                fun build(): BindingValues = BindingValues(additionalProperties.toImmutable())
            }

            private var validated: Boolean = false

            /**
             * Validates that the types of all values in this object match their expected types
             * recursively.
             *
             * This method is _not_ forwards compatible with new types from the API for existing
             * fields.
             *
             * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
             *   match its expected type.
             */
            fun validate(): BindingValues = apply {
                if (validated) {
                    return@apply
                }

                validated = true
            }

            fun isValid(): Boolean =
                try {
                    validate()
                    true
                } catch (e: XTwitterScraperInvalidDataException) {
                    false
                }

            internal fun validity(): Int = additionalProperties.count { (_, value) ->
                !value.isNull() && !value.isMissing()
            }

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is BindingValues && additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy { Objects.hash(additionalProperties) }

            override fun hashCode(): Int = hashCode

            override fun toString() = "BindingValues{additionalProperties=$additionalProperties}"
        }

        /** Public metadata whose fields X defines. */
        class Platform
        @JsonCreator
        private constructor(
            @com.fasterxml.jackson.annotation.JsonValue
            private val additionalProperties: Map<String, JsonValue>
        ) {

            @JsonAnyGetter
            @ExcludeMissing
            fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

            fun toBuilder() = Builder().from(this)

            companion object {

                /** Returns a mutable builder for constructing an instance of [Platform]. */
                fun builder() = Builder()
            }

            /** A builder for [Platform]. */
            class Builder internal constructor() {

                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                internal fun from(platform: Platform) = apply {
                    additionalProperties = platform.additionalProperties.toMutableMap()
                }

                fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                    this.additionalProperties.clear()
                    putAllAdditionalProperties(additionalProperties)
                }

                fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                    additionalProperties.put(key, value)
                }

                fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) =
                    apply {
                        this.additionalProperties.putAll(additionalProperties)
                    }

                fun removeAdditionalProperty(key: String) = apply {
                    additionalProperties.remove(key)
                }

                fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                    keys.forEach(::removeAdditionalProperty)
                }

                /**
                 * Returns an immutable instance of [Platform].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 */
                fun build(): Platform = Platform(additionalProperties.toImmutable())
            }

            private var validated: Boolean = false

            /**
             * Validates that the types of all values in this object match their expected types
             * recursively.
             *
             * This method is _not_ forwards compatible with new types from the API for existing
             * fields.
             *
             * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
             *   match its expected type.
             */
            fun validate(): Platform = apply {
                if (validated) {
                    return@apply
                }

                validated = true
            }

            fun isValid(): Boolean =
                try {
                    validate()
                    true
                } catch (e: XTwitterScraperInvalidDataException) {
                    false
                }

            internal fun validity(): Int = additionalProperties.count { (_, value) ->
                !value.isNull() && !value.isMissing()
            }

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is Platform && additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy { Objects.hash(additionalProperties) }

            override fun hashCode(): Int = hashCode

            override fun toString() = "Platform{additionalProperties=$additionalProperties}"
        }

        class UserReferenceError
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val message: JsonField<String>,
            private val reason: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("message")
                @ExcludeMissing
                message: JsonField<String> = JsonMissing.of(),
                @JsonProperty("reason")
                @ExcludeMissing
                reason: JsonField<String> = JsonMissing.of(),
            ) : this(message, reason, mutableMapOf())

            /**
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun message(): String? = message.getNullable("message")

            /**
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun reason(): String? = reason.getNullable("reason")

            /**
             * Returns the raw JSON value of [message].
             *
             * Unlike [message], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("message") @ExcludeMissing fun _message(): JsonField<String> = message

            /**
             * Returns the raw JSON value of [reason].
             *
             * Unlike [reason], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("reason") @ExcludeMissing fun _reason(): JsonField<String> = reason

            @JsonAnySetter
            private fun putAdditionalProperty(key: String, value: JsonValue) {
                additionalProperties.put(key, value)
            }

            @JsonAnyGetter
            @ExcludeMissing
            fun _additionalProperties(): Map<String, JsonValue> =
                Collections.unmodifiableMap(additionalProperties)

            fun toBuilder() = Builder().from(this)

            companion object {

                /**
                 * Returns a mutable builder for constructing an instance of [UserReferenceError].
                 */
                fun builder() = Builder()
            }

            /** A builder for [UserReferenceError]. */
            class Builder internal constructor() {

                private var message: JsonField<String> = JsonMissing.of()
                private var reason: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                internal fun from(userReferenceError: UserReferenceError) = apply {
                    message = userReferenceError.message
                    reason = userReferenceError.reason
                    additionalProperties = userReferenceError.additionalProperties.toMutableMap()
                }

                fun message(message: String) = message(JsonField.of(message))

                /** Sets [Builder.message] to an arbitrary JSON value. */
                fun message(message: JsonField<String>) = apply { this.message = message }

                fun reason(reason: String) = reason(JsonField.of(reason))

                /** Sets [Builder.reason] to an arbitrary JSON value. */
                fun reason(reason: JsonField<String>) = apply { this.reason = reason }

                fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                    this.additionalProperties.clear()
                    putAllAdditionalProperties(additionalProperties)
                }

                fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                    additionalProperties.put(key, value)
                }

                fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) =
                    apply {
                        this.additionalProperties.putAll(additionalProperties)
                    }

                fun removeAdditionalProperty(key: String) = apply {
                    additionalProperties.remove(key)
                }

                fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                    keys.forEach(::removeAdditionalProperty)
                }

                /**
                 * Returns an immutable instance of [UserReferenceError].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 */
                fun build(): UserReferenceError =
                    UserReferenceError(message, reason, additionalProperties.toMutableMap())
            }

            private var validated: Boolean = false

            /**
             * Validates that the types of all values in this object match their expected types
             * recursively.
             *
             * This method is _not_ forwards compatible with new types from the API for existing
             * fields.
             *
             * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
             *   match its expected type.
             */
            fun validate(): UserReferenceError = apply {
                if (validated) {
                    return@apply
                }

                message()
                reason()
                validated = true
            }

            fun isValid(): Boolean =
                try {
                    validate()
                    true
                } catch (e: XTwitterScraperInvalidDataException) {
                    false
                }

            internal fun validity(): Int =
                (if (message.asKnown() == null) 0 else 1) + (if (reason.asKnown() == null) 0 else 1)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is UserReferenceError &&
                    message == other.message &&
                    reason == other.reason &&
                    additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(message, reason, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "UserReferenceError{message=$message, reason=$reason, additionalProperties=$additionalProperties}"
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Card &&
                id == other.id &&
                bindingValues == other.bindingValues &&
                name == other.name &&
                platform == other.platform &&
                url == other.url &&
                userReferenceErrors == other.userReferenceErrors &&
                userReferences == other.userReferences &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(
                id,
                bindingValues,
                name,
                platform,
                url,
                userReferenceErrors,
                userReferences,
                additionalProperties,
            )
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Card{id=$id, bindingValues=$bindingValues, name=$name, platform=$platform, url=$url, userReferenceErrors=$userReferenceErrors, userReferences=$userReferences, additionalProperties=$additionalProperties}"
    }

    class CashtagAttachment
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val cashtag: JsonField<String>,
        private val candleSize: JsonField<String>,
        private val endTime: JsonField<OffsetDateTime>,
        private val sizeVariant: JsonField<Long>,
        private val startTime: JsonField<OffsetDateTime>,
        private val timeframe: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("cashtag") @ExcludeMissing cashtag: JsonField<String> = JsonMissing.of(),
            @JsonProperty("candleSize")
            @ExcludeMissing
            candleSize: JsonField<String> = JsonMissing.of(),
            @JsonProperty("endTime")
            @ExcludeMissing
            endTime: JsonField<OffsetDateTime> = JsonMissing.of(),
            @JsonProperty("sizeVariant")
            @ExcludeMissing
            sizeVariant: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("startTime")
            @ExcludeMissing
            startTime: JsonField<OffsetDateTime> = JsonMissing.of(),
            @JsonProperty("timeframe")
            @ExcludeMissing
            timeframe: JsonField<String> = JsonMissing.of(),
        ) : this(cashtag, candleSize, endTime, sizeVariant, startTime, timeframe, mutableMapOf())

        /**
         * The cashtag with its `$`.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or
         *   is unexpectedly missing or null.
         */
        fun cashtag(): String = cashtag.getRequired("cashtag")

        /**
         * Span of each candle.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun candleSize(): String? = candleSize.getNullable("candleSize")

        /**
         * Time of the chart's last price.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun endTime(): OffsetDateTime? = endTime.getNullable("endTime")

        /**
         * X's size for the chart.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun sizeVariant(): Long? = sizeVariant.getNullable("sizeVariant")

        /**
         * Time of the chart's first candle.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun startTime(): OffsetDateTime? = startTime.getNullable("startTime")

        /**
         * Span the chart covers.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun timeframe(): String? = timeframe.getNullable("timeframe")

        /**
         * Returns the raw JSON value of [cashtag].
         *
         * Unlike [cashtag], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("cashtag") @ExcludeMissing fun _cashtag(): JsonField<String> = cashtag

        /**
         * Returns the raw JSON value of [candleSize].
         *
         * Unlike [candleSize], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("candleSize")
        @ExcludeMissing
        fun _candleSize(): JsonField<String> = candleSize

        /**
         * Returns the raw JSON value of [endTime].
         *
         * Unlike [endTime], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("endTime") @ExcludeMissing fun _endTime(): JsonField<OffsetDateTime> = endTime

        /**
         * Returns the raw JSON value of [sizeVariant].
         *
         * Unlike [sizeVariant], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("sizeVariant")
        @ExcludeMissing
        fun _sizeVariant(): JsonField<Long> = sizeVariant

        /**
         * Returns the raw JSON value of [startTime].
         *
         * Unlike [startTime], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("startTime")
        @ExcludeMissing
        fun _startTime(): JsonField<OffsetDateTime> = startTime

        /**
         * Returns the raw JSON value of [timeframe].
         *
         * Unlike [timeframe], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("timeframe") @ExcludeMissing fun _timeframe(): JsonField<String> = timeframe

        @JsonAnySetter
        private fun putAdditionalProperty(key: String, value: JsonValue) {
            additionalProperties.put(key, value)
        }

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> =
            Collections.unmodifiableMap(additionalProperties)

        fun toBuilder() = Builder().from(this)

        companion object {

            /**
             * Returns a mutable builder for constructing an instance of [CashtagAttachment].
             *
             * The following fields are required:
             * ```kotlin
             * .cashtag()
             * ```
             */
            fun builder() = Builder()
        }

        /** A builder for [CashtagAttachment]. */
        class Builder internal constructor() {

            private var cashtag: JsonField<String>? = null
            private var candleSize: JsonField<String> = JsonMissing.of()
            private var endTime: JsonField<OffsetDateTime> = JsonMissing.of()
            private var sizeVariant: JsonField<Long> = JsonMissing.of()
            private var startTime: JsonField<OffsetDateTime> = JsonMissing.of()
            private var timeframe: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(cashtagAttachment: CashtagAttachment) = apply {
                cashtag = cashtagAttachment.cashtag
                candleSize = cashtagAttachment.candleSize
                endTime = cashtagAttachment.endTime
                sizeVariant = cashtagAttachment.sizeVariant
                startTime = cashtagAttachment.startTime
                timeframe = cashtagAttachment.timeframe
                additionalProperties = cashtagAttachment.additionalProperties.toMutableMap()
            }

            /** The cashtag with its `$`. */
            fun cashtag(cashtag: String) = cashtag(JsonField.of(cashtag))

            /** Sets [Builder.cashtag] to an arbitrary JSON value. */
            fun cashtag(cashtag: JsonField<String>) = apply { this.cashtag = cashtag }

            /** Span of each candle. */
            fun candleSize(candleSize: String) = candleSize(JsonField.of(candleSize))

            /** Sets [Builder.candleSize] to an arbitrary JSON value. */
            fun candleSize(candleSize: JsonField<String>) = apply { this.candleSize = candleSize }

            /** Time of the chart's last price. */
            fun endTime(endTime: OffsetDateTime) = endTime(JsonField.of(endTime))

            /** Sets [Builder.endTime] to an arbitrary JSON value. */
            fun endTime(endTime: JsonField<OffsetDateTime>) = apply { this.endTime = endTime }

            /** X's size for the chart. */
            fun sizeVariant(sizeVariant: Long) = sizeVariant(JsonField.of(sizeVariant))

            /** Sets [Builder.sizeVariant] to an arbitrary JSON value. */
            fun sizeVariant(sizeVariant: JsonField<Long>) = apply { this.sizeVariant = sizeVariant }

            /** Time of the chart's first candle. */
            fun startTime(startTime: OffsetDateTime) = startTime(JsonField.of(startTime))

            /** Sets [Builder.startTime] to an arbitrary JSON value. */
            fun startTime(startTime: JsonField<OffsetDateTime>) = apply {
                this.startTime = startTime
            }

            /** Span the chart covers. */
            fun timeframe(timeframe: String) = timeframe(JsonField.of(timeframe))

            /** Sets [Builder.timeframe] to an arbitrary JSON value. */
            fun timeframe(timeframe: JsonField<String>) = apply { this.timeframe = timeframe }

            fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.clear()
                putAllAdditionalProperties(additionalProperties)
            }

            fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                additionalProperties.put(key, value)
            }

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            /**
             * Returns an immutable instance of [CashtagAttachment].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             *
             * The following fields are required:
             * ```kotlin
             * .cashtag()
             * ```
             *
             * @throws IllegalStateException if any required field is unset.
             */
            fun build(): CashtagAttachment =
                CashtagAttachment(
                    checkRequired("cashtag", cashtag),
                    candleSize,
                    endTime,
                    sizeVariant,
                    startTime,
                    timeframe,
                    additionalProperties.toMutableMap(),
                )
        }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
         *   match its expected type.
         */
        fun validate(): CashtagAttachment = apply {
            if (validated) {
                return@apply
            }

            cashtag()
            candleSize()
            endTime()
            sizeVariant()
            startTime()
            timeframe()
            validated = true
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: XTwitterScraperInvalidDataException) {
                false
            }

        internal fun validity(): Int =
            (if (cashtag.asKnown() == null) 0 else 1) +
                (if (candleSize.asKnown() == null) 0 else 1) +
                (if (endTime.asKnown() == null) 0 else 1) +
                (if (sizeVariant.asKnown() == null) 0 else 1) +
                (if (startTime.asKnown() == null) 0 else 1) +
                (if (timeframe.asKnown() == null) 0 else 1)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is CashtagAttachment &&
                cashtag == other.cashtag &&
                candleSize == other.candleSize &&
                endTime == other.endTime &&
                sizeVariant == other.sizeVariant &&
                startTime == other.startTime &&
                timeframe == other.timeframe &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(
                cashtag,
                candleSize,
                endTime,
                sizeVariant,
                startTime,
                timeframe,
                additionalProperties,
            )
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "CashtagAttachment{cashtag=$cashtag, candleSize=$candleSize, endTime=$endTime, sizeVariant=$sizeVariant, startTime=$startTime, timeframe=$timeframe, additionalProperties=$additionalProperties}"
    }

    /** Community Note presentation metadata returned by X. */
    class CommunityNote
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val id: JsonField<String>,
        private val destinationUrl: JsonField<String>,
        private val footer: JsonField<String>,
        private val footerIconType: JsonField<String>,
        private val iconType: JsonField<String>,
        private val metadata: JsonField<Metadata>,
        private val shortTitle: JsonField<String>,
        private val subtitle: JsonField<String>,
        private val title: JsonField<String>,
        private val visualStyle: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
            @JsonProperty("destinationUrl")
            @ExcludeMissing
            destinationUrl: JsonField<String> = JsonMissing.of(),
            @JsonProperty("footer") @ExcludeMissing footer: JsonField<String> = JsonMissing.of(),
            @JsonProperty("footerIconType")
            @ExcludeMissing
            footerIconType: JsonField<String> = JsonMissing.of(),
            @JsonProperty("iconType")
            @ExcludeMissing
            iconType: JsonField<String> = JsonMissing.of(),
            @JsonProperty("metadata")
            @ExcludeMissing
            metadata: JsonField<Metadata> = JsonMissing.of(),
            @JsonProperty("shortTitle")
            @ExcludeMissing
            shortTitle: JsonField<String> = JsonMissing.of(),
            @JsonProperty("subtitle")
            @ExcludeMissing
            subtitle: JsonField<String> = JsonMissing.of(),
            @JsonProperty("title") @ExcludeMissing title: JsonField<String> = JsonMissing.of(),
            @JsonProperty("visualStyle")
            @ExcludeMissing
            visualStyle: JsonField<String> = JsonMissing.of(),
        ) : this(
            id,
            destinationUrl,
            footer,
            footerIconType,
            iconType,
            metadata,
            shortTitle,
            subtitle,
            title,
            visualStyle,
            mutableMapOf(),
        )

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun id(): String? = id.getNullable("id")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun destinationUrl(): String? = destinationUrl.getNullable("destinationUrl")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun footer(): String? = footer.getNullable("footer")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun footerIconType(): String? = footerIconType.getNullable("footerIconType")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun iconType(): String? = iconType.getNullable("iconType")

        /**
         * Public metadata whose fields X defines.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun metadata(): Metadata? = metadata.getNullable("metadata")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun shortTitle(): String? = shortTitle.getNullable("shortTitle")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun subtitle(): String? = subtitle.getNullable("subtitle")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun title(): String? = title.getNullable("title")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun visualStyle(): String? = visualStyle.getNullable("visualStyle")

        /**
         * Returns the raw JSON value of [id].
         *
         * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

        /**
         * Returns the raw JSON value of [destinationUrl].
         *
         * Unlike [destinationUrl], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("destinationUrl")
        @ExcludeMissing
        fun _destinationUrl(): JsonField<String> = destinationUrl

        /**
         * Returns the raw JSON value of [footer].
         *
         * Unlike [footer], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("footer") @ExcludeMissing fun _footer(): JsonField<String> = footer

        /**
         * Returns the raw JSON value of [footerIconType].
         *
         * Unlike [footerIconType], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("footerIconType")
        @ExcludeMissing
        fun _footerIconType(): JsonField<String> = footerIconType

        /**
         * Returns the raw JSON value of [iconType].
         *
         * Unlike [iconType], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("iconType") @ExcludeMissing fun _iconType(): JsonField<String> = iconType

        /**
         * Returns the raw JSON value of [metadata].
         *
         * Unlike [metadata], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("metadata") @ExcludeMissing fun _metadata(): JsonField<Metadata> = metadata

        /**
         * Returns the raw JSON value of [shortTitle].
         *
         * Unlike [shortTitle], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("shortTitle")
        @ExcludeMissing
        fun _shortTitle(): JsonField<String> = shortTitle

        /**
         * Returns the raw JSON value of [subtitle].
         *
         * Unlike [subtitle], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("subtitle") @ExcludeMissing fun _subtitle(): JsonField<String> = subtitle

        /**
         * Returns the raw JSON value of [title].
         *
         * Unlike [title], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("title") @ExcludeMissing fun _title(): JsonField<String> = title

        /**
         * Returns the raw JSON value of [visualStyle].
         *
         * Unlike [visualStyle], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("visualStyle")
        @ExcludeMissing
        fun _visualStyle(): JsonField<String> = visualStyle

        @JsonAnySetter
        private fun putAdditionalProperty(key: String, value: JsonValue) {
            additionalProperties.put(key, value)
        }

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> =
            Collections.unmodifiableMap(additionalProperties)

        fun toBuilder() = Builder().from(this)

        companion object {

            /** Returns a mutable builder for constructing an instance of [CommunityNote]. */
            fun builder() = Builder()
        }

        /** A builder for [CommunityNote]. */
        class Builder internal constructor() {

            private var id: JsonField<String> = JsonMissing.of()
            private var destinationUrl: JsonField<String> = JsonMissing.of()
            private var footer: JsonField<String> = JsonMissing.of()
            private var footerIconType: JsonField<String> = JsonMissing.of()
            private var iconType: JsonField<String> = JsonMissing.of()
            private var metadata: JsonField<Metadata> = JsonMissing.of()
            private var shortTitle: JsonField<String> = JsonMissing.of()
            private var subtitle: JsonField<String> = JsonMissing.of()
            private var title: JsonField<String> = JsonMissing.of()
            private var visualStyle: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(communityNote: CommunityNote) = apply {
                id = communityNote.id
                destinationUrl = communityNote.destinationUrl
                footer = communityNote.footer
                footerIconType = communityNote.footerIconType
                iconType = communityNote.iconType
                metadata = communityNote.metadata
                shortTitle = communityNote.shortTitle
                subtitle = communityNote.subtitle
                title = communityNote.title
                visualStyle = communityNote.visualStyle
                additionalProperties = communityNote.additionalProperties.toMutableMap()
            }

            fun id(id: String) = id(JsonField.of(id))

            /** Sets [Builder.id] to an arbitrary JSON value. */
            fun id(id: JsonField<String>) = apply { this.id = id }

            fun destinationUrl(destinationUrl: String) =
                destinationUrl(JsonField.of(destinationUrl))

            /** Sets [Builder.destinationUrl] to an arbitrary JSON value. */
            fun destinationUrl(destinationUrl: JsonField<String>) = apply {
                this.destinationUrl = destinationUrl
            }

            fun footer(footer: String) = footer(JsonField.of(footer))

            /** Sets [Builder.footer] to an arbitrary JSON value. */
            fun footer(footer: JsonField<String>) = apply { this.footer = footer }

            fun footerIconType(footerIconType: String) =
                footerIconType(JsonField.of(footerIconType))

            /** Sets [Builder.footerIconType] to an arbitrary JSON value. */
            fun footerIconType(footerIconType: JsonField<String>) = apply {
                this.footerIconType = footerIconType
            }

            fun iconType(iconType: String) = iconType(JsonField.of(iconType))

            /** Sets [Builder.iconType] to an arbitrary JSON value. */
            fun iconType(iconType: JsonField<String>) = apply { this.iconType = iconType }

            /** Public metadata whose fields X defines. */
            fun metadata(metadata: Metadata) = metadata(JsonField.of(metadata))

            /** Sets [Builder.metadata] to an arbitrary JSON value. */
            fun metadata(metadata: JsonField<Metadata>) = apply { this.metadata = metadata }

            fun shortTitle(shortTitle: String) = shortTitle(JsonField.of(shortTitle))

            /** Sets [Builder.shortTitle] to an arbitrary JSON value. */
            fun shortTitle(shortTitle: JsonField<String>) = apply { this.shortTitle = shortTitle }

            fun subtitle(subtitle: String) = subtitle(JsonField.of(subtitle))

            /** Sets [Builder.subtitle] to an arbitrary JSON value. */
            fun subtitle(subtitle: JsonField<String>) = apply { this.subtitle = subtitle }

            fun title(title: String) = title(JsonField.of(title))

            /** Sets [Builder.title] to an arbitrary JSON value. */
            fun title(title: JsonField<String>) = apply { this.title = title }

            fun visualStyle(visualStyle: String) = visualStyle(JsonField.of(visualStyle))

            /** Sets [Builder.visualStyle] to an arbitrary JSON value. */
            fun visualStyle(visualStyle: JsonField<String>) = apply {
                this.visualStyle = visualStyle
            }

            fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.clear()
                putAllAdditionalProperties(additionalProperties)
            }

            fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                additionalProperties.put(key, value)
            }

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            /**
             * Returns an immutable instance of [CommunityNote].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): CommunityNote =
                CommunityNote(
                    id,
                    destinationUrl,
                    footer,
                    footerIconType,
                    iconType,
                    metadata,
                    shortTitle,
                    subtitle,
                    title,
                    visualStyle,
                    additionalProperties.toMutableMap(),
                )
        }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
         *   match its expected type.
         */
        fun validate(): CommunityNote = apply {
            if (validated) {
                return@apply
            }

            id()
            destinationUrl()
            footer()
            footerIconType()
            iconType()
            metadata()?.validate()
            shortTitle()
            subtitle()
            title()
            visualStyle()
            validated = true
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: XTwitterScraperInvalidDataException) {
                false
            }

        internal fun validity(): Int =
            (if (id.asKnown() == null) 0 else 1) +
                (if (destinationUrl.asKnown() == null) 0 else 1) +
                (if (footer.asKnown() == null) 0 else 1) +
                (if (footerIconType.asKnown() == null) 0 else 1) +
                (if (iconType.asKnown() == null) 0 else 1) +
                (metadata.asKnown()?.validity() ?: 0) +
                (if (shortTitle.asKnown() == null) 0 else 1) +
                (if (subtitle.asKnown() == null) 0 else 1) +
                (if (title.asKnown() == null) 0 else 1) +
                (if (visualStyle.asKnown() == null) 0 else 1)

        /** Public metadata whose fields X defines. */
        class Metadata
        @JsonCreator
        private constructor(
            @com.fasterxml.jackson.annotation.JsonValue
            private val additionalProperties: Map<String, JsonValue>
        ) {

            @JsonAnyGetter
            @ExcludeMissing
            fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

            fun toBuilder() = Builder().from(this)

            companion object {

                /** Returns a mutable builder for constructing an instance of [Metadata]. */
                fun builder() = Builder()
            }

            /** A builder for [Metadata]. */
            class Builder internal constructor() {

                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                internal fun from(metadata: Metadata) = apply {
                    additionalProperties = metadata.additionalProperties.toMutableMap()
                }

                fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                    this.additionalProperties.clear()
                    putAllAdditionalProperties(additionalProperties)
                }

                fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                    additionalProperties.put(key, value)
                }

                fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) =
                    apply {
                        this.additionalProperties.putAll(additionalProperties)
                    }

                fun removeAdditionalProperty(key: String) = apply {
                    additionalProperties.remove(key)
                }

                fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                    keys.forEach(::removeAdditionalProperty)
                }

                /**
                 * Returns an immutable instance of [Metadata].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 */
                fun build(): Metadata = Metadata(additionalProperties.toImmutable())
            }

            private var validated: Boolean = false

            /**
             * Validates that the types of all values in this object match their expected types
             * recursively.
             *
             * This method is _not_ forwards compatible with new types from the API for existing
             * fields.
             *
             * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
             *   match its expected type.
             */
            fun validate(): Metadata = apply {
                if (validated) {
                    return@apply
                }

                validated = true
            }

            fun isValid(): Boolean =
                try {
                    validate()
                    true
                } catch (e: XTwitterScraperInvalidDataException) {
                    false
                }

            internal fun validity(): Int = additionalProperties.count { (_, value) ->
                !value.isNull() && !value.isMissing()
            }

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is Metadata && additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy { Objects.hash(additionalProperties) }

            override fun hashCode(): Int = hashCode

            override fun toString() = "Metadata{additionalProperties=$additionalProperties}"
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is CommunityNote &&
                id == other.id &&
                destinationUrl == other.destinationUrl &&
                footer == other.footer &&
                footerIconType == other.footerIconType &&
                iconType == other.iconType &&
                metadata == other.metadata &&
                shortTitle == other.shortTitle &&
                subtitle == other.subtitle &&
                title == other.title &&
                visualStyle == other.visualStyle &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(
                id,
                destinationUrl,
                footer,
                footerIconType,
                iconType,
                metadata,
                shortTitle,
                subtitle,
                title,
                visualStyle,
                additionalProperties,
            )
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "CommunityNote{id=$id, destinationUrl=$destinationUrl, footer=$footer, footerIconType=$footerIconType, iconType=$iconType, metadata=$metadata, shortTitle=$shortTitle, subtitle=$subtitle, title=$title, visualStyle=$visualStyle, additionalProperties=$additionalProperties}"
    }

    /** Reply policy & conversation owner. */
    class ConversationControl
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val allowedCountryCodes: JsonField<List<String>>,
        private val inviteViaMention: JsonField<Boolean>,
        private val mode: JsonField<String>,
        private val ownerId: JsonField<String>,
        private val ownerUsername: JsonField<String>,
        private val policy: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("allowedCountryCodes")
            @ExcludeMissing
            allowedCountryCodes: JsonField<List<String>> = JsonMissing.of(),
            @JsonProperty("inviteViaMention")
            @ExcludeMissing
            inviteViaMention: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("mode") @ExcludeMissing mode: JsonField<String> = JsonMissing.of(),
            @JsonProperty("ownerId") @ExcludeMissing ownerId: JsonField<String> = JsonMissing.of(),
            @JsonProperty("ownerUsername")
            @ExcludeMissing
            ownerUsername: JsonField<String> = JsonMissing.of(),
            @JsonProperty("policy") @ExcludeMissing policy: JsonField<String> = JsonMissing.of(),
        ) : this(
            allowedCountryCodes,
            inviteViaMention,
            mode,
            ownerId,
            ownerUsername,
            policy,
            mutableMapOf(),
        )

        /**
         * Regions whose accounts may reply, as country codes. Sent with the `country_of_origin`
         * mode.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun allowedCountryCodes(): List<String>? =
            allowedCountryCodes.getNullable("allowedCountryCodes")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun inviteViaMention(): Boolean? = inviteViaMention.getNullable("inviteViaMention")

        /**
         * Reply policy from X's newer field, such as `my_network`, `premium` or
         * `country_of_origin`. Omitted when X sends none.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun mode(): String? = mode.getNullable("mode")

        /**
         * The conversation owner's account ID.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun ownerId(): String? = ownerId.getNullable("ownerId")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun ownerUsername(): String? = ownerUsername.getNullable("ownerUsername")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun policy(): String? = policy.getNullable("policy")

        /**
         * Returns the raw JSON value of [allowedCountryCodes].
         *
         * Unlike [allowedCountryCodes], this method doesn't throw if the JSON field has an
         * unexpected type.
         */
        @JsonProperty("allowedCountryCodes")
        @ExcludeMissing
        fun _allowedCountryCodes(): JsonField<List<String>> = allowedCountryCodes

        /**
         * Returns the raw JSON value of [inviteViaMention].
         *
         * Unlike [inviteViaMention], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("inviteViaMention")
        @ExcludeMissing
        fun _inviteViaMention(): JsonField<Boolean> = inviteViaMention

        /**
         * Returns the raw JSON value of [mode].
         *
         * Unlike [mode], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("mode") @ExcludeMissing fun _mode(): JsonField<String> = mode

        /**
         * Returns the raw JSON value of [ownerId].
         *
         * Unlike [ownerId], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("ownerId") @ExcludeMissing fun _ownerId(): JsonField<String> = ownerId

        /**
         * Returns the raw JSON value of [ownerUsername].
         *
         * Unlike [ownerUsername], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("ownerUsername")
        @ExcludeMissing
        fun _ownerUsername(): JsonField<String> = ownerUsername

        /**
         * Returns the raw JSON value of [policy].
         *
         * Unlike [policy], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("policy") @ExcludeMissing fun _policy(): JsonField<String> = policy

        @JsonAnySetter
        private fun putAdditionalProperty(key: String, value: JsonValue) {
            additionalProperties.put(key, value)
        }

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> =
            Collections.unmodifiableMap(additionalProperties)

        fun toBuilder() = Builder().from(this)

        companion object {

            /** Returns a mutable builder for constructing an instance of [ConversationControl]. */
            fun builder() = Builder()
        }

        /** A builder for [ConversationControl]. */
        class Builder internal constructor() {

            private var allowedCountryCodes: JsonField<MutableList<String>>? = null
            private var inviteViaMention: JsonField<Boolean> = JsonMissing.of()
            private var mode: JsonField<String> = JsonMissing.of()
            private var ownerId: JsonField<String> = JsonMissing.of()
            private var ownerUsername: JsonField<String> = JsonMissing.of()
            private var policy: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(conversationControl: ConversationControl) = apply {
                allowedCountryCodes =
                    conversationControl.allowedCountryCodes.map { it.toMutableList() }
                inviteViaMention = conversationControl.inviteViaMention
                mode = conversationControl.mode
                ownerId = conversationControl.ownerId
                ownerUsername = conversationControl.ownerUsername
                policy = conversationControl.policy
                additionalProperties = conversationControl.additionalProperties.toMutableMap()
            }

            /**
             * Regions whose accounts may reply, as country codes. Sent with the `country_of_origin`
             * mode.
             */
            fun allowedCountryCodes(allowedCountryCodes: List<String>) =
                allowedCountryCodes(JsonField.of(allowedCountryCodes))

            /** Sets [Builder.allowedCountryCodes] to an arbitrary JSON value. */
            fun allowedCountryCodes(allowedCountryCodes: JsonField<List<String>>) = apply {
                this.allowedCountryCodes = allowedCountryCodes.map { it.toMutableList() }
            }

            /**
             * Adds a single [String] to [allowedCountryCodes].
             *
             * @throws IllegalStateException if the field was previously set to a non-list.
             */
            fun addAllowedCountryCode(allowedCountryCode: String) = apply {
                allowedCountryCodes =
                    (allowedCountryCodes ?: JsonField.of(mutableListOf())).also {
                        checkKnown("allowedCountryCodes", it).add(allowedCountryCode)
                    }
            }

            fun inviteViaMention(inviteViaMention: Boolean) =
                inviteViaMention(JsonField.of(inviteViaMention))

            /** Sets [Builder.inviteViaMention] to an arbitrary JSON value. */
            fun inviteViaMention(inviteViaMention: JsonField<Boolean>) = apply {
                this.inviteViaMention = inviteViaMention
            }

            /**
             * Reply policy from X's newer field, such as `my_network`, `premium` or
             * `country_of_origin`. Omitted when X sends none.
             */
            fun mode(mode: String) = mode(JsonField.of(mode))

            /** Sets [Builder.mode] to an arbitrary JSON value. */
            fun mode(mode: JsonField<String>) = apply { this.mode = mode }

            /** The conversation owner's account ID. */
            fun ownerId(ownerId: String) = ownerId(JsonField.of(ownerId))

            /** Sets [Builder.ownerId] to an arbitrary JSON value. */
            fun ownerId(ownerId: JsonField<String>) = apply { this.ownerId = ownerId }

            fun ownerUsername(ownerUsername: String) = ownerUsername(JsonField.of(ownerUsername))

            /** Sets [Builder.ownerUsername] to an arbitrary JSON value. */
            fun ownerUsername(ownerUsername: JsonField<String>) = apply {
                this.ownerUsername = ownerUsername
            }

            fun policy(policy: String) = policy(JsonField.of(policy))

            /** Sets [Builder.policy] to an arbitrary JSON value. */
            fun policy(policy: JsonField<String>) = apply { this.policy = policy }

            fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.clear()
                putAllAdditionalProperties(additionalProperties)
            }

            fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                additionalProperties.put(key, value)
            }

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            /**
             * Returns an immutable instance of [ConversationControl].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): ConversationControl =
                ConversationControl(
                    (allowedCountryCodes ?: JsonMissing.of()).map { it.toImmutable() },
                    inviteViaMention,
                    mode,
                    ownerId,
                    ownerUsername,
                    policy,
                    additionalProperties.toMutableMap(),
                )
        }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
         *   match its expected type.
         */
        fun validate(): ConversationControl = apply {
            if (validated) {
                return@apply
            }

            allowedCountryCodes()
            inviteViaMention()
            mode()
            ownerId()
            ownerUsername()
            policy()
            validated = true
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: XTwitterScraperInvalidDataException) {
                false
            }

        internal fun validity(): Int =
            (allowedCountryCodes.asKnown()?.size ?: 0) +
                (if (inviteViaMention.asKnown() == null) 0 else 1) +
                (if (mode.asKnown() == null) 0 else 1) +
                (if (ownerId.asKnown() == null) 0 else 1) +
                (if (ownerUsername.asKnown() == null) 0 else 1) +
                (if (policy.asKnown() == null) 0 else 1)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is ConversationControl &&
                allowedCountryCodes == other.allowedCountryCodes &&
                inviteViaMention == other.inviteViaMention &&
                mode == other.mode &&
                ownerId == other.ownerId &&
                ownerUsername == other.ownerUsername &&
                policy == other.policy &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(
                allowedCountryCodes,
                inviteViaMention,
                mode,
                ownerId,
                ownerUsername,
                policy,
                additionalProperties,
            )
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "ConversationControl{allowedCountryCodes=$allowedCountryCodes, inviteViaMention=$inviteViaMention, mode=$mode, ownerId=$ownerId, ownerUsername=$ownerUsername, policy=$policy, additionalProperties=$additionalProperties}"
    }

    /** Author-published geographic point with 2 numeric coordinates. */
    class Coordinates
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val coordinates: JsonField<List<Double>>,
        private val type: JsonField<Type>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("coordinates")
            @ExcludeMissing
            coordinates: JsonField<List<Double>> = JsonMissing.of(),
            @JsonProperty("type") @ExcludeMissing type: JsonField<Type> = JsonMissing.of(),
        ) : this(coordinates, type, mutableMapOf())

        /**
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or
         *   is unexpectedly missing or null.
         */
        fun coordinates(): List<Double> = coordinates.getRequired("coordinates")

        /**
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or
         *   is unexpectedly missing or null.
         */
        fun type(): Type = type.getRequired("type")

        /**
         * Returns the raw JSON value of [coordinates].
         *
         * Unlike [coordinates], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("coordinates")
        @ExcludeMissing
        fun _coordinates(): JsonField<List<Double>> = coordinates

        /**
         * Returns the raw JSON value of [type].
         *
         * Unlike [type], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<Type> = type

        @JsonAnySetter
        private fun putAdditionalProperty(key: String, value: JsonValue) {
            additionalProperties.put(key, value)
        }

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> =
            Collections.unmodifiableMap(additionalProperties)

        fun toBuilder() = Builder().from(this)

        companion object {

            /**
             * Returns a mutable builder for constructing an instance of [Coordinates].
             *
             * The following fields are required:
             * ```kotlin
             * .coordinates()
             * .type()
             * ```
             */
            fun builder() = Builder()
        }

        /** A builder for [Coordinates]. */
        class Builder internal constructor() {

            private var coordinates: JsonField<MutableList<Double>>? = null
            private var type: JsonField<Type>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(coordinates: Coordinates) = apply {
                this.coordinates = coordinates.coordinates.map { it.toMutableList() }
                type = coordinates.type
                additionalProperties = coordinates.additionalProperties.toMutableMap()
            }

            fun coordinates(coordinates: List<Double>) = coordinates(JsonField.of(coordinates))

            /** Sets [Builder.coordinates] to an arbitrary JSON value. */
            fun coordinates(coordinates: JsonField<List<Double>>) = apply {
                this.coordinates = coordinates.map { it.toMutableList() }
            }

            /**
             * Adds a single [Double] to [coordinates].
             *
             * @throws IllegalStateException if the field was previously set to a non-list.
             */
            fun addCoordinate(coordinate: Double) = apply {
                coordinates =
                    (coordinates ?: JsonField.of(mutableListOf())).also {
                        checkKnown("coordinates", it).add(coordinate)
                    }
            }

            fun type(type: Type) = type(JsonField.of(type))

            /** Sets [Builder.type] to an arbitrary JSON value. */
            fun type(type: JsonField<Type>) = apply { this.type = type }

            fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.clear()
                putAllAdditionalProperties(additionalProperties)
            }

            fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                additionalProperties.put(key, value)
            }

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            /**
             * Returns an immutable instance of [Coordinates].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             *
             * The following fields are required:
             * ```kotlin
             * .coordinates()
             * .type()
             * ```
             *
             * @throws IllegalStateException if any required field is unset.
             */
            fun build(): Coordinates =
                Coordinates(
                    checkRequired("coordinates", coordinates).map { it.toImmutable() },
                    checkRequired("type", type),
                    additionalProperties.toMutableMap(),
                )
        }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
         *   match its expected type.
         */
        fun validate(): Coordinates = apply {
            if (validated) {
                return@apply
            }

            coordinates()
            type().validate()
            validated = true
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: XTwitterScraperInvalidDataException) {
                false
            }

        internal fun validity(): Int =
            (coordinates.asKnown()?.size ?: 0) + (type.asKnown()?.validity() ?: 0)

        class Type @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

            /** Returns this class instance's raw value. */
            @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

            companion object {

                val POINT = of("Point")

                fun of(value: String) = Type(JsonField.of(value))
            }

            /** An enum containing [Type]'s known values. */
            enum class Known {
                POINT
            }

            /**
             * An enum containing [Type]'s known values, as well as an [_UNKNOWN] member.
             *
             * An instance of [Type] can contain an unknown value in a couple of cases:
             * - It was deserialized from data that doesn't match any known member. For example, if
             *   the SDK is on an older version than the API, then the API may respond with new
             *   members that the SDK is unaware of.
             * - It was constructed with an arbitrary value using the [of] method.
             */
            enum class Value {
                POINT,
                /** An enum member indicating that [Type] was instantiated with an unknown value. */
                _UNKNOWN,
            }

            /**
             * Returns an enum member corresponding to this class instance's value, or
             * [Value._UNKNOWN] if the class was instantiated with an unknown value.
             */
            fun value(): Value =
                when (this) {
                    POINT -> Value.POINT
                    else -> Value._UNKNOWN
                }

            /**
             * Returns an enum member corresponding to this class instance's value.
             *
             * @throws XTwitterScraperInvalidDataException if this class instance's value is a not a
             *   known member.
             */
            fun known(): Known =
                when (this) {
                    POINT -> Known.POINT
                    else -> throw XTwitterScraperInvalidDataException("Unknown Type: $value")
                }

            /**
             * Returns this class instance's primitive wire representation.
             *
             * @throws XTwitterScraperInvalidDataException if this class instance's value does not
             *   have the expected primitive type.
             */
            fun asString(): String =
                _value().asString()
                    ?: throw XTwitterScraperInvalidDataException("Value is not a String")

            private var validated: Boolean = false

            /**
             * Validates that the types of all values in this object match their expected types
             * recursively.
             *
             * This method is _not_ forwards compatible with new types from the API for existing
             * fields.
             *
             * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
             *   match its expected type.
             */
            fun validate(): Type = apply {
                if (validated) {
                    return@apply
                }

                known()
                validated = true
            }

            fun isValid(): Boolean =
                try {
                    validate()
                    true
                } catch (e: XTwitterScraperInvalidDataException) {
                    false
                }

            internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is Type && value == other.value
            }

            override fun hashCode() = value.hashCode()

            override fun toString() = value.toString()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Coordinates &&
                coordinates == other.coordinates &&
                type == other.type &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(coordinates, type, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Coordinates{coordinates=$coordinates, type=$type, additionalProperties=$additionalProperties}"
    }

    /** Lists edit-chain identifiers and the remaining edit window. */
    class Edit
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val editTweetIds: JsonField<List<String>>,
        private val editableUntilMsecs: JsonField<String>,
        private val initialTweetId: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("editTweetIds")
            @ExcludeMissing
            editTweetIds: JsonField<List<String>> = JsonMissing.of(),
            @JsonProperty("editableUntilMsecs")
            @ExcludeMissing
            editableUntilMsecs: JsonField<String> = JsonMissing.of(),
            @JsonProperty("initialTweetId")
            @ExcludeMissing
            initialTweetId: JsonField<String> = JsonMissing.of(),
        ) : this(editTweetIds, editableUntilMsecs, initialTweetId, mutableMapOf())

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun editTweetIds(): List<String>? = editTweetIds.getNullable("editTweetIds")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun editableUntilMsecs(): String? = editableUntilMsecs.getNullable("editableUntilMsecs")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun initialTweetId(): String? = initialTweetId.getNullable("initialTweetId")

        /**
         * Returns the raw JSON value of [editTweetIds].
         *
         * Unlike [editTweetIds], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("editTweetIds")
        @ExcludeMissing
        fun _editTweetIds(): JsonField<List<String>> = editTweetIds

        /**
         * Returns the raw JSON value of [editableUntilMsecs].
         *
         * Unlike [editableUntilMsecs], this method doesn't throw if the JSON field has an
         * unexpected type.
         */
        @JsonProperty("editableUntilMsecs")
        @ExcludeMissing
        fun _editableUntilMsecs(): JsonField<String> = editableUntilMsecs

        /**
         * Returns the raw JSON value of [initialTweetId].
         *
         * Unlike [initialTweetId], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("initialTweetId")
        @ExcludeMissing
        fun _initialTweetId(): JsonField<String> = initialTweetId

        @JsonAnySetter
        private fun putAdditionalProperty(key: String, value: JsonValue) {
            additionalProperties.put(key, value)
        }

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> =
            Collections.unmodifiableMap(additionalProperties)

        fun toBuilder() = Builder().from(this)

        companion object {

            /** Returns a mutable builder for constructing an instance of [Edit]. */
            fun builder() = Builder()
        }

        /** A builder for [Edit]. */
        class Builder internal constructor() {

            private var editTweetIds: JsonField<MutableList<String>>? = null
            private var editableUntilMsecs: JsonField<String> = JsonMissing.of()
            private var initialTweetId: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(edit: Edit) = apply {
                editTweetIds = edit.editTweetIds.map { it.toMutableList() }
                editableUntilMsecs = edit.editableUntilMsecs
                initialTweetId = edit.initialTweetId
                additionalProperties = edit.additionalProperties.toMutableMap()
            }

            fun editTweetIds(editTweetIds: List<String>) = editTweetIds(JsonField.of(editTweetIds))

            /** Sets [Builder.editTweetIds] to an arbitrary JSON value. */
            fun editTweetIds(editTweetIds: JsonField<List<String>>) = apply {
                this.editTweetIds = editTweetIds.map { it.toMutableList() }
            }

            /**
             * Adds a single [String] to [editTweetIds].
             *
             * @throws IllegalStateException if the field was previously set to a non-list.
             */
            fun addEditTweetId(editTweetId: String) = apply {
                editTweetIds =
                    (editTweetIds ?: JsonField.of(mutableListOf())).also {
                        checkKnown("editTweetIds", it).add(editTweetId)
                    }
            }

            fun editableUntilMsecs(editableUntilMsecs: String) =
                editableUntilMsecs(JsonField.of(editableUntilMsecs))

            /** Sets [Builder.editableUntilMsecs] to an arbitrary JSON value. */
            fun editableUntilMsecs(editableUntilMsecs: JsonField<String>) = apply {
                this.editableUntilMsecs = editableUntilMsecs
            }

            fun initialTweetId(initialTweetId: String) =
                initialTweetId(JsonField.of(initialTweetId))

            /** Sets [Builder.initialTweetId] to an arbitrary JSON value. */
            fun initialTweetId(initialTweetId: JsonField<String>) = apply {
                this.initialTweetId = initialTweetId
            }

            fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.clear()
                putAllAdditionalProperties(additionalProperties)
            }

            fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                additionalProperties.put(key, value)
            }

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            /**
             * Returns an immutable instance of [Edit].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): Edit =
                Edit(
                    (editTweetIds ?: JsonMissing.of()).map { it.toImmutable() },
                    editableUntilMsecs,
                    initialTweetId,
                    additionalProperties.toMutableMap(),
                )
        }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
         *   match its expected type.
         */
        fun validate(): Edit = apply {
            if (validated) {
                return@apply
            }

            editTweetIds()
            editableUntilMsecs()
            initialTweetId()
            validated = true
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: XTwitterScraperInvalidDataException) {
                false
            }

        internal fun validity(): Int =
            (editTweetIds.asKnown()?.size ?: 0) +
                (if (editableUntilMsecs.asKnown() == null) 0 else 1) +
                (if (initialTweetId.asKnown() == null) 0 else 1)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Edit &&
                editTweetIds == other.editTweetIds &&
                editableUntilMsecs == other.editableUntilMsecs &&
                initialTweetId == other.initialTweetId &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(editTweetIds, editableUntilMsecs, initialTweetId, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Edit{editTweetIds=$editTweetIds, editableUntilMsecs=$editableUntilMsecs, initialTweetId=$initialTweetId, additionalProperties=$additionalProperties}"
    }

    /** Lists hashtags, symbols, links, and mentions from tweet text. */
    class Entities
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val hashtags: JsonField<List<Hashtag>>,
        private val smarttags: JsonField<List<Smarttag>>,
        private val symbols: JsonField<List<Symbol>>,
        private val timestamps: JsonField<List<Timestamp>>,
        private val urls: JsonField<List<Url>>,
        private val userMentions: JsonField<List<UserMention>>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("hashtags")
            @ExcludeMissing
            hashtags: JsonField<List<Hashtag>> = JsonMissing.of(),
            @JsonProperty("smarttags")
            @ExcludeMissing
            smarttags: JsonField<List<Smarttag>> = JsonMissing.of(),
            @JsonProperty("symbols")
            @ExcludeMissing
            symbols: JsonField<List<Symbol>> = JsonMissing.of(),
            @JsonProperty("timestamps")
            @ExcludeMissing
            timestamps: JsonField<List<Timestamp>> = JsonMissing.of(),
            @JsonProperty("urls") @ExcludeMissing urls: JsonField<List<Url>> = JsonMissing.of(),
            @JsonProperty("user_mentions")
            @ExcludeMissing
            userMentions: JsonField<List<UserMention>> = JsonMissing.of(),
        ) : this(hashtags, smarttags, symbols, timestamps, urls, userMentions, mutableMapOf())

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun hashtags(): List<Hashtag>? = hashtags.getNullable("hashtags")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun smarttags(): List<Smarttag>? = smarttags.getNullable("smarttags")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun symbols(): List<Symbol>? = symbols.getNullable("symbols")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun timestamps(): List<Timestamp>? = timestamps.getNullable("timestamps")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun urls(): List<Url>? = urls.getNullable("urls")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun userMentions(): List<UserMention>? = userMentions.getNullable("user_mentions")

        /**
         * Returns the raw JSON value of [hashtags].
         *
         * Unlike [hashtags], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("hashtags")
        @ExcludeMissing
        fun _hashtags(): JsonField<List<Hashtag>> = hashtags

        /**
         * Returns the raw JSON value of [smarttags].
         *
         * Unlike [smarttags], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("smarttags")
        @ExcludeMissing
        fun _smarttags(): JsonField<List<Smarttag>> = smarttags

        /**
         * Returns the raw JSON value of [symbols].
         *
         * Unlike [symbols], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("symbols") @ExcludeMissing fun _symbols(): JsonField<List<Symbol>> = symbols

        /**
         * Returns the raw JSON value of [timestamps].
         *
         * Unlike [timestamps], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("timestamps")
        @ExcludeMissing
        fun _timestamps(): JsonField<List<Timestamp>> = timestamps

        /**
         * Returns the raw JSON value of [urls].
         *
         * Unlike [urls], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("urls") @ExcludeMissing fun _urls(): JsonField<List<Url>> = urls

        /**
         * Returns the raw JSON value of [userMentions].
         *
         * Unlike [userMentions], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("user_mentions")
        @ExcludeMissing
        fun _userMentions(): JsonField<List<UserMention>> = userMentions

        @JsonAnySetter
        private fun putAdditionalProperty(key: String, value: JsonValue) {
            additionalProperties.put(key, value)
        }

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> =
            Collections.unmodifiableMap(additionalProperties)

        fun toBuilder() = Builder().from(this)

        companion object {

            /** Returns a mutable builder for constructing an instance of [Entities]. */
            fun builder() = Builder()
        }

        /** A builder for [Entities]. */
        class Builder internal constructor() {

            private var hashtags: JsonField<MutableList<Hashtag>>? = null
            private var smarttags: JsonField<MutableList<Smarttag>>? = null
            private var symbols: JsonField<MutableList<Symbol>>? = null
            private var timestamps: JsonField<MutableList<Timestamp>>? = null
            private var urls: JsonField<MutableList<Url>>? = null
            private var userMentions: JsonField<MutableList<UserMention>>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(entities: Entities) = apply {
                hashtags = entities.hashtags.map { it.toMutableList() }
                smarttags = entities.smarttags.map { it.toMutableList() }
                symbols = entities.symbols.map { it.toMutableList() }
                timestamps = entities.timestamps.map { it.toMutableList() }
                urls = entities.urls.map { it.toMutableList() }
                userMentions = entities.userMentions.map { it.toMutableList() }
                additionalProperties = entities.additionalProperties.toMutableMap()
            }

            fun hashtags(hashtags: List<Hashtag>) = hashtags(JsonField.of(hashtags))

            /** Sets [Builder.hashtags] to an arbitrary JSON value. */
            fun hashtags(hashtags: JsonField<List<Hashtag>>) = apply {
                this.hashtags = hashtags.map { it.toMutableList() }
            }

            /**
             * Adds a single [Hashtag] to [hashtags].
             *
             * @throws IllegalStateException if the field was previously set to a non-list.
             */
            fun addHashtag(hashtag: Hashtag) = apply {
                hashtags =
                    (hashtags ?: JsonField.of(mutableListOf())).also {
                        checkKnown("hashtags", it).add(hashtag)
                    }
            }

            fun smarttags(smarttags: List<Smarttag>) = smarttags(JsonField.of(smarttags))

            /** Sets [Builder.smarttags] to an arbitrary JSON value. */
            fun smarttags(smarttags: JsonField<List<Smarttag>>) = apply {
                this.smarttags = smarttags.map { it.toMutableList() }
            }

            /**
             * Adds a single [Smarttag] to [smarttags].
             *
             * @throws IllegalStateException if the field was previously set to a non-list.
             */
            fun addSmarttag(smarttag: Smarttag) = apply {
                smarttags =
                    (smarttags ?: JsonField.of(mutableListOf())).also {
                        checkKnown("smarttags", it).add(smarttag)
                    }
            }

            fun symbols(symbols: List<Symbol>) = symbols(JsonField.of(symbols))

            /** Sets [Builder.symbols] to an arbitrary JSON value. */
            fun symbols(symbols: JsonField<List<Symbol>>) = apply {
                this.symbols = symbols.map { it.toMutableList() }
            }

            /**
             * Adds a single [Symbol] to [symbols].
             *
             * @throws IllegalStateException if the field was previously set to a non-list.
             */
            fun addSymbol(symbol: Symbol) = apply {
                symbols =
                    (symbols ?: JsonField.of(mutableListOf())).also {
                        checkKnown("symbols", it).add(symbol)
                    }
            }

            fun timestamps(timestamps: List<Timestamp>) = timestamps(JsonField.of(timestamps))

            /** Sets [Builder.timestamps] to an arbitrary JSON value. */
            fun timestamps(timestamps: JsonField<List<Timestamp>>) = apply {
                this.timestamps = timestamps.map { it.toMutableList() }
            }

            /**
             * Adds a single [Timestamp] to [timestamps].
             *
             * @throws IllegalStateException if the field was previously set to a non-list.
             */
            fun addTimestamp(timestamp: Timestamp) = apply {
                timestamps =
                    (timestamps ?: JsonField.of(mutableListOf())).also {
                        checkKnown("timestamps", it).add(timestamp)
                    }
            }

            fun urls(urls: List<Url>) = urls(JsonField.of(urls))

            /** Sets [Builder.urls] to an arbitrary JSON value. */
            fun urls(urls: JsonField<List<Url>>) = apply {
                this.urls = urls.map { it.toMutableList() }
            }

            /**
             * Adds a single [Url] to [urls].
             *
             * @throws IllegalStateException if the field was previously set to a non-list.
             */
            fun addUrl(url: Url) = apply {
                urls =
                    (urls ?: JsonField.of(mutableListOf())).also {
                        checkKnown("urls", it).add(url)
                    }
            }

            fun userMentions(userMentions: List<UserMention>) =
                userMentions(JsonField.of(userMentions))

            /** Sets [Builder.userMentions] to an arbitrary JSON value. */
            fun userMentions(userMentions: JsonField<List<UserMention>>) = apply {
                this.userMentions = userMentions.map { it.toMutableList() }
            }

            /**
             * Adds a single [UserMention] to [userMentions].
             *
             * @throws IllegalStateException if the field was previously set to a non-list.
             */
            fun addUserMention(userMention: UserMention) = apply {
                userMentions =
                    (userMentions ?: JsonField.of(mutableListOf())).also {
                        checkKnown("userMentions", it).add(userMention)
                    }
            }

            fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.clear()
                putAllAdditionalProperties(additionalProperties)
            }

            fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                additionalProperties.put(key, value)
            }

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            /**
             * Returns an immutable instance of [Entities].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): Entities =
                Entities(
                    (hashtags ?: JsonMissing.of()).map { it.toImmutable() },
                    (smarttags ?: JsonMissing.of()).map { it.toImmutable() },
                    (symbols ?: JsonMissing.of()).map { it.toImmutable() },
                    (timestamps ?: JsonMissing.of()).map { it.toImmutable() },
                    (urls ?: JsonMissing.of()).map { it.toImmutable() },
                    (userMentions ?: JsonMissing.of()).map { it.toImmutable() },
                    additionalProperties.toMutableMap(),
                )
        }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
         *   match its expected type.
         */
        fun validate(): Entities = apply {
            if (validated) {
                return@apply
            }

            hashtags()?.forEach { it.validate() }
            smarttags()?.forEach { it.validate() }
            symbols()?.forEach { it.validate() }
            timestamps()?.forEach { it.validate() }
            urls()?.forEach { it.validate() }
            userMentions()?.forEach { it.validate() }
            validated = true
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: XTwitterScraperInvalidDataException) {
                false
            }

        internal fun validity(): Int =
            (hashtags.asKnown()?.sumOf { it.validity() } ?: 0) +
                (smarttags.asKnown()?.sumOf { it.validity() } ?: 0) +
                (symbols.asKnown()?.sumOf { it.validity() } ?: 0) +
                (timestamps.asKnown()?.sumOf { it.validity() } ?: 0) +
                (urls.asKnown()?.sumOf { it.validity() } ?: 0) +
                (userMentions.asKnown()?.sumOf { it.validity() } ?: 0)

        /** Hashtag text and its source offsets in a tweet. */
        class Hashtag
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val text: JsonField<String>,
            private val indices: JsonField<List<Long>>,
            private val tag: JsonField<Tag>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("text") @ExcludeMissing text: JsonField<String> = JsonMissing.of(),
                @JsonProperty("indices")
                @ExcludeMissing
                indices: JsonField<List<Long>> = JsonMissing.of(),
                @JsonProperty("tag") @ExcludeMissing tag: JsonField<Tag> = JsonMissing.of(),
            ) : this(text, indices, tag, mutableMapOf())

            /**
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type
             *   or is unexpectedly missing or null.
             */
            fun text(): String = text.getRequired("text")

            /**
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun indices(): List<Long>? = indices.getNullable("indices")

            /**
             * X's SmartTag for a cashtag, smarttag or hashtag. Xquik leaves out X's trading
             * platforms, which differ by reader.
             *
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun tag(): Tag? = tag.getNullable("tag")

            /**
             * Returns the raw JSON value of [text].
             *
             * Unlike [text], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("text") @ExcludeMissing fun _text(): JsonField<String> = text

            /**
             * Returns the raw JSON value of [indices].
             *
             * Unlike [indices], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("indices") @ExcludeMissing fun _indices(): JsonField<List<Long>> = indices

            /**
             * Returns the raw JSON value of [tag].
             *
             * Unlike [tag], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("tag") @ExcludeMissing fun _tag(): JsonField<Tag> = tag

            @JsonAnySetter
            private fun putAdditionalProperty(key: String, value: JsonValue) {
                additionalProperties.put(key, value)
            }

            @JsonAnyGetter
            @ExcludeMissing
            fun _additionalProperties(): Map<String, JsonValue> =
                Collections.unmodifiableMap(additionalProperties)

            fun toBuilder() = Builder().from(this)

            companion object {

                /**
                 * Returns a mutable builder for constructing an instance of [Hashtag].
                 *
                 * The following fields are required:
                 * ```kotlin
                 * .text()
                 * ```
                 */
                fun builder() = Builder()
            }

            /** A builder for [Hashtag]. */
            class Builder internal constructor() {

                private var text: JsonField<String>? = null
                private var indices: JsonField<MutableList<Long>>? = null
                private var tag: JsonField<Tag> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                internal fun from(hashtag: Hashtag) = apply {
                    text = hashtag.text
                    indices = hashtag.indices.map { it.toMutableList() }
                    tag = hashtag.tag
                    additionalProperties = hashtag.additionalProperties.toMutableMap()
                }

                fun text(text: String) = text(JsonField.of(text))

                /** Sets [Builder.text] to an arbitrary JSON value. */
                fun text(text: JsonField<String>) = apply { this.text = text }

                fun indices(indices: List<Long>) = indices(JsonField.of(indices))

                /** Sets [Builder.indices] to an arbitrary JSON value. */
                fun indices(indices: JsonField<List<Long>>) = apply {
                    this.indices = indices.map { it.toMutableList() }
                }

                /**
                 * Adds a single [Long] to [indices].
                 *
                 * @throws IllegalStateException if the field was previously set to a non-list.
                 */
                fun addIndex(index: Long) = apply {
                    indices =
                        (indices ?: JsonField.of(mutableListOf())).also {
                            checkKnown("indices", it).add(index)
                        }
                }

                /**
                 * X's SmartTag for a cashtag, smarttag or hashtag. Xquik leaves out X's trading
                 * platforms, which differ by reader.
                 */
                fun tag(tag: Tag) = tag(JsonField.of(tag))

                /** Sets [Builder.tag] to an arbitrary JSON value. */
                fun tag(tag: JsonField<Tag>) = apply { this.tag = tag }

                fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                    this.additionalProperties.clear()
                    putAllAdditionalProperties(additionalProperties)
                }

                fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                    additionalProperties.put(key, value)
                }

                fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) =
                    apply {
                        this.additionalProperties.putAll(additionalProperties)
                    }

                fun removeAdditionalProperty(key: String) = apply {
                    additionalProperties.remove(key)
                }

                fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                    keys.forEach(::removeAdditionalProperty)
                }

                /**
                 * Returns an immutable instance of [Hashtag].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 *
                 * The following fields are required:
                 * ```kotlin
                 * .text()
                 * ```
                 *
                 * @throws IllegalStateException if any required field is unset.
                 */
                fun build(): Hashtag =
                    Hashtag(
                        checkRequired("text", text),
                        (indices ?: JsonMissing.of()).map { it.toImmutable() },
                        tag,
                        additionalProperties.toMutableMap(),
                    )
            }

            private var validated: Boolean = false

            /**
             * Validates that the types of all values in this object match their expected types
             * recursively.
             *
             * This method is _not_ forwards compatible with new types from the API for existing
             * fields.
             *
             * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
             *   match its expected type.
             */
            fun validate(): Hashtag = apply {
                if (validated) {
                    return@apply
                }

                text()
                indices()
                tag()?.validate()
                validated = true
            }

            fun isValid(): Boolean =
                try {
                    validate()
                    true
                } catch (e: XTwitterScraperInvalidDataException) {
                    false
                }

            internal fun validity(): Int =
                (if (text.asKnown() == null) 0 else 1) +
                    (indices.asKnown()?.size ?: 0) +
                    (tag.asKnown()?.validity() ?: 0)

            /**
             * X's SmartTag for a cashtag, smarttag or hashtag. Xquik leaves out X's trading
             * platforms, which differ by reader.
             */
            class Tag
            @JsonCreator(mode = JsonCreator.Mode.DISABLED)
            private constructor(
                private val id: JsonField<String>,
                private val info: JsonField<Info>,
                private val additionalProperties: MutableMap<String, JsonValue>,
            ) {

                @JsonCreator
                private constructor(
                    @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
                    @JsonProperty("info") @ExcludeMissing info: JsonField<Info> = JsonMissing.of(),
                ) : this(id, info, mutableMapOf())

                /**
                 * X's ID for the SmartTag.
                 *
                 * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected
                 *   type.
                 */
                fun id(): String? = id.getNullable("id")

                /**
                 * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected
                 *   type.
                 */
                fun info(): Info? = info.getNullable("info")

                /**
                 * Returns the raw JSON value of [id].
                 *
                 * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
                 */
                @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

                /**
                 * Returns the raw JSON value of [info].
                 *
                 * Unlike [info], this method doesn't throw if the JSON field has an unexpected
                 * type.
                 */
                @JsonProperty("info") @ExcludeMissing fun _info(): JsonField<Info> = info

                @JsonAnySetter
                private fun putAdditionalProperty(key: String, value: JsonValue) {
                    additionalProperties.put(key, value)
                }

                @JsonAnyGetter
                @ExcludeMissing
                fun _additionalProperties(): Map<String, JsonValue> =
                    Collections.unmodifiableMap(additionalProperties)

                fun toBuilder() = Builder().from(this)

                companion object {

                    /** Returns a mutable builder for constructing an instance of [Tag]. */
                    fun builder() = Builder()
                }

                /** A builder for [Tag]. */
                class Builder internal constructor() {

                    private var id: JsonField<String> = JsonMissing.of()
                    private var info: JsonField<Info> = JsonMissing.of()
                    private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                    internal fun from(tag: Tag) = apply {
                        id = tag.id
                        info = tag.info
                        additionalProperties = tag.additionalProperties.toMutableMap()
                    }

                    /** X's ID for the SmartTag. */
                    fun id(id: String) = id(JsonField.of(id))

                    /** Sets [Builder.id] to an arbitrary JSON value. */
                    fun id(id: JsonField<String>) = apply { this.id = id }

                    fun info(info: Info) = info(JsonField.of(info))

                    /** Sets [Builder.info] to an arbitrary JSON value. */
                    fun info(info: JsonField<Info>) = apply { this.info = info }

                    fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                        this.additionalProperties.clear()
                        putAllAdditionalProperties(additionalProperties)
                    }

                    fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                        additionalProperties.put(key, value)
                    }

                    fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) =
                        apply {
                            this.additionalProperties.putAll(additionalProperties)
                        }

                    fun removeAdditionalProperty(key: String) = apply {
                        additionalProperties.remove(key)
                    }

                    fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                        keys.forEach(::removeAdditionalProperty)
                    }

                    /**
                     * Returns an immutable instance of [Tag].
                     *
                     * Further updates to this [Builder] will not mutate the returned instance.
                     */
                    fun build(): Tag = Tag(id, info, additionalProperties.toMutableMap())
                }

                private var validated: Boolean = false

                /**
                 * Validates that the types of all values in this object match their expected types
                 * recursively.
                 *
                 * This method is _not_ forwards compatible with new types from the API for existing
                 * fields.
                 *
                 * @throws XTwitterScraperInvalidDataException if any value type in this object
                 *   doesn't match its expected type.
                 */
                fun validate(): Tag = apply {
                    if (validated) {
                        return@apply
                    }

                    id()
                    info()?.validate()
                    validated = true
                }

                fun isValid(): Boolean =
                    try {
                        validate()
                        true
                    } catch (e: XTwitterScraperInvalidDataException) {
                        false
                    }

                internal fun validity(): Int =
                    (if (id.asKnown() == null) 0 else 1) + (info.asKnown()?.validity() ?: 0)

                class Info
                @JsonCreator(mode = JsonCreator.Mode.DISABLED)
                private constructor(
                    private val info: JsonField<InnerInfo>,
                    private val additionalProperties: MutableMap<String, JsonValue>,
                ) {

                    @JsonCreator
                    private constructor(
                        @JsonProperty("info")
                        @ExcludeMissing
                        info: JsonField<InnerInfo> = JsonMissing.of()
                    ) : this(info, mutableMapOf())

                    /**
                     * @throws XTwitterScraperInvalidDataException if the JSON field has an
                     *   unexpected type.
                     */
                    fun info(): InnerInfo? = info.getNullable("info")

                    /**
                     * Returns the raw JSON value of [info].
                     *
                     * Unlike [info], this method doesn't throw if the JSON field has an unexpected
                     * type.
                     */
                    @JsonProperty("info") @ExcludeMissing fun _info(): JsonField<InnerInfo> = info

                    @JsonAnySetter
                    private fun putAdditionalProperty(key: String, value: JsonValue) {
                        additionalProperties.put(key, value)
                    }

                    @JsonAnyGetter
                    @ExcludeMissing
                    fun _additionalProperties(): Map<String, JsonValue> =
                        Collections.unmodifiableMap(additionalProperties)

                    fun toBuilder() = Builder().from(this)

                    companion object {

                        /** Returns a mutable builder for constructing an instance of [Info]. */
                        fun builder() = Builder()
                    }

                    /** A builder for [Info]. */
                    class Builder internal constructor() {

                        private var info: JsonField<InnerInfo> = JsonMissing.of()
                        private var additionalProperties: MutableMap<String, JsonValue> =
                            mutableMapOf()

                        internal fun from(info: Info) = apply {
                            this.info = info.info
                            additionalProperties = info.additionalProperties.toMutableMap()
                        }

                        fun info(info: InnerInfo) = info(JsonField.of(info))

                        /** Sets [Builder.info] to an arbitrary JSON value. */
                        fun info(info: JsonField<InnerInfo>) = apply { this.info = info }

                        fun additionalProperties(additionalProperties: Map<String, JsonValue>) =
                            apply {
                                this.additionalProperties.clear()
                                putAllAdditionalProperties(additionalProperties)
                            }

                        fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                            additionalProperties.put(key, value)
                        }

                        fun putAllAdditionalProperties(
                            additionalProperties: Map<String, JsonValue>
                        ) = apply {
                            this.additionalProperties.putAll(additionalProperties)
                        }

                        fun removeAdditionalProperty(key: String) = apply {
                            additionalProperties.remove(key)
                        }

                        fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                            keys.forEach(::removeAdditionalProperty)
                        }

                        /**
                         * Returns an immutable instance of [Info].
                         *
                         * Further updates to this [Builder] will not mutate the returned instance.
                         */
                        fun build(): Info = Info(info, additionalProperties.toMutableMap())
                    }

                    private var validated: Boolean = false

                    /**
                     * Validates that the types of all values in this object match their expected
                     * types recursively.
                     *
                     * This method is _not_ forwards compatible with new types from the API for
                     * existing fields.
                     *
                     * @throws XTwitterScraperInvalidDataException if any value type in this object
                     *   doesn't match its expected type.
                     */
                    fun validate(): Info = apply {
                        if (validated) {
                            return@apply
                        }

                        info()?.validate()
                        validated = true
                    }

                    fun isValid(): Boolean =
                        try {
                            validate()
                            true
                        } catch (e: XTwitterScraperInvalidDataException) {
                            false
                        }

                    internal fun validity(): Int = (info.asKnown()?.validity() ?: 0)

                    class InnerInfo
                    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
                    private constructor(
                        private val active: JsonField<Boolean>,
                        private val name: JsonField<String>,
                        private val ticker: JsonField<String>,
                        private val additionalProperties: MutableMap<String, JsonValue>,
                    ) {

                        @JsonCreator
                        private constructor(
                            @JsonProperty("active")
                            @ExcludeMissing
                            active: JsonField<Boolean> = JsonMissing.of(),
                            @JsonProperty("name")
                            @ExcludeMissing
                            name: JsonField<String> = JsonMissing.of(),
                            @JsonProperty("ticker")
                            @ExcludeMissing
                            ticker: JsonField<String> = JsonMissing.of(),
                        ) : this(active, name, ticker, mutableMapOf())

                        /**
                         * Whether X marks the SmartTag active.
                         *
                         * @throws XTwitterScraperInvalidDataException if the JSON field has an
                         *   unexpected type.
                         */
                        fun active(): Boolean? = active.getNullable("active")

                        /**
                         * @throws XTwitterScraperInvalidDataException if the JSON field has an
                         *   unexpected type.
                         */
                        fun name(): String? = name.getNullable("name")

                        /**
                         * @throws XTwitterScraperInvalidDataException if the JSON field has an
                         *   unexpected type.
                         */
                        fun ticker(): String? = ticker.getNullable("ticker")

                        /**
                         * Returns the raw JSON value of [active].
                         *
                         * Unlike [active], this method doesn't throw if the JSON field has an
                         * unexpected type.
                         */
                        @JsonProperty("active")
                        @ExcludeMissing
                        fun _active(): JsonField<Boolean> = active

                        /**
                         * Returns the raw JSON value of [name].
                         *
                         * Unlike [name], this method doesn't throw if the JSON field has an
                         * unexpected type.
                         */
                        @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<String> = name

                        /**
                         * Returns the raw JSON value of [ticker].
                         *
                         * Unlike [ticker], this method doesn't throw if the JSON field has an
                         * unexpected type.
                         */
                        @JsonProperty("ticker")
                        @ExcludeMissing
                        fun _ticker(): JsonField<String> = ticker

                        @JsonAnySetter
                        private fun putAdditionalProperty(key: String, value: JsonValue) {
                            additionalProperties.put(key, value)
                        }

                        @JsonAnyGetter
                        @ExcludeMissing
                        fun _additionalProperties(): Map<String, JsonValue> =
                            Collections.unmodifiableMap(additionalProperties)

                        fun toBuilder() = Builder().from(this)

                        companion object {

                            /**
                             * Returns a mutable builder for constructing an instance of
                             * [InnerInfo].
                             */
                            fun builder() = Builder()
                        }

                        /** A builder for [InnerInfo]. */
                        class Builder internal constructor() {

                            private var active: JsonField<Boolean> = JsonMissing.of()
                            private var name: JsonField<String> = JsonMissing.of()
                            private var ticker: JsonField<String> = JsonMissing.of()
                            private var additionalProperties: MutableMap<String, JsonValue> =
                                mutableMapOf()

                            internal fun from(innerInfo: InnerInfo) = apply {
                                active = innerInfo.active
                                name = innerInfo.name
                                ticker = innerInfo.ticker
                                additionalProperties = innerInfo.additionalProperties.toMutableMap()
                            }

                            /** Whether X marks the SmartTag active. */
                            fun active(active: Boolean) = active(JsonField.of(active))

                            /** Sets [Builder.active] to an arbitrary JSON value. */
                            fun active(active: JsonField<Boolean>) = apply { this.active = active }

                            fun name(name: String) = name(JsonField.of(name))

                            /** Sets [Builder.name] to an arbitrary JSON value. */
                            fun name(name: JsonField<String>) = apply { this.name = name }

                            fun ticker(ticker: String) = ticker(JsonField.of(ticker))

                            /** Sets [Builder.ticker] to an arbitrary JSON value. */
                            fun ticker(ticker: JsonField<String>) = apply { this.ticker = ticker }

                            fun additionalProperties(additionalProperties: Map<String, JsonValue>) =
                                apply {
                                    this.additionalProperties.clear()
                                    putAllAdditionalProperties(additionalProperties)
                                }

                            fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                                additionalProperties.put(key, value)
                            }

                            fun putAllAdditionalProperties(
                                additionalProperties: Map<String, JsonValue>
                            ) = apply {
                                this.additionalProperties.putAll(additionalProperties)
                            }

                            fun removeAdditionalProperty(key: String) = apply {
                                additionalProperties.remove(key)
                            }

                            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                                keys.forEach(::removeAdditionalProperty)
                            }

                            /**
                             * Returns an immutable instance of [InnerInfo].
                             *
                             * Further updates to this [Builder] will not mutate the returned
                             * instance.
                             */
                            fun build(): InnerInfo =
                                InnerInfo(active, name, ticker, additionalProperties.toMutableMap())
                        }

                        private var validated: Boolean = false

                        /**
                         * Validates that the types of all values in this object match their
                         * expected types recursively.
                         *
                         * This method is _not_ forwards compatible with new types from the API for
                         * existing fields.
                         *
                         * @throws XTwitterScraperInvalidDataException if any value type in this
                         *   object doesn't match its expected type.
                         */
                        fun validate(): InnerInfo = apply {
                            if (validated) {
                                return@apply
                            }

                            active()
                            name()
                            ticker()
                            validated = true
                        }

                        fun isValid(): Boolean =
                            try {
                                validate()
                                true
                            } catch (e: XTwitterScraperInvalidDataException) {
                                false
                            }

                        internal fun validity(): Int =
                            (if (active.asKnown() == null) 0 else 1) +
                                (if (name.asKnown() == null) 0 else 1) +
                                (if (ticker.asKnown() == null) 0 else 1)

                        override fun equals(other: Any?): Boolean {
                            if (this === other) {
                                return true
                            }

                            return other is InnerInfo &&
                                active == other.active &&
                                name == other.name &&
                                ticker == other.ticker &&
                                additionalProperties == other.additionalProperties
                        }

                        private val hashCode: Int by lazy {
                            Objects.hash(active, name, ticker, additionalProperties)
                        }

                        override fun hashCode(): Int = hashCode

                        override fun toString() =
                            "InnerInfo{active=$active, name=$name, ticker=$ticker, additionalProperties=$additionalProperties}"
                    }

                    override fun equals(other: Any?): Boolean {
                        if (this === other) {
                            return true
                        }

                        return other is Info &&
                            info == other.info &&
                            additionalProperties == other.additionalProperties
                    }

                    private val hashCode: Int by lazy {
                        Objects.hash(info, additionalProperties)
                    }

                    override fun hashCode(): Int = hashCode

                    override fun toString() =
                        "Info{info=$info, additionalProperties=$additionalProperties}"
                }

                override fun equals(other: Any?): Boolean {
                    if (this === other) {
                        return true
                    }

                    return other is Tag &&
                        id == other.id &&
                        info == other.info &&
                        additionalProperties == other.additionalProperties
                }

                private val hashCode: Int by lazy {
                    Objects.hash(id, info, additionalProperties)
                }

                override fun hashCode(): Int = hashCode

                override fun toString() =
                    "Tag{id=$id, info=$info, additionalProperties=$additionalProperties}"
            }

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is Hashtag &&
                    text == other.text &&
                    indices == other.indices &&
                    tag == other.tag &&
                    additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(text, indices, tag, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "Hashtag{text=$text, indices=$indices, tag=$tag, additionalProperties=$additionalProperties}"
        }

        /** Indexed smart-tag, cashtag, or video timestamp metadata. */
        class Smarttag
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val indices: JsonField<List<Long>>,
            private val seconds: JsonField<Double>,
            private val tag: JsonField<Tag>,
            private val text: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("indices")
                @ExcludeMissing
                indices: JsonField<List<Long>> = JsonMissing.of(),
                @JsonProperty("seconds")
                @ExcludeMissing
                seconds: JsonField<Double> = JsonMissing.of(),
                @JsonProperty("tag") @ExcludeMissing tag: JsonField<Tag> = JsonMissing.of(),
                @JsonProperty("text") @ExcludeMissing text: JsonField<String> = JsonMissing.of(),
            ) : this(indices, seconds, tag, text, mutableMapOf())

            /**
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun indices(): List<Long>? = indices.getNullable("indices")

            /**
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun seconds(): Double? = seconds.getNullable("seconds")

            /**
             * X's SmartTag for a cashtag, smarttag or hashtag. Xquik leaves out X's trading
             * platforms, which differ by reader.
             *
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun tag(): Tag? = tag.getNullable("tag")

            /**
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun text(): String? = text.getNullable("text")

            /**
             * Returns the raw JSON value of [indices].
             *
             * Unlike [indices], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("indices") @ExcludeMissing fun _indices(): JsonField<List<Long>> = indices

            /**
             * Returns the raw JSON value of [seconds].
             *
             * Unlike [seconds], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("seconds") @ExcludeMissing fun _seconds(): JsonField<Double> = seconds

            /**
             * Returns the raw JSON value of [tag].
             *
             * Unlike [tag], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("tag") @ExcludeMissing fun _tag(): JsonField<Tag> = tag

            /**
             * Returns the raw JSON value of [text].
             *
             * Unlike [text], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("text") @ExcludeMissing fun _text(): JsonField<String> = text

            @JsonAnySetter
            private fun putAdditionalProperty(key: String, value: JsonValue) {
                additionalProperties.put(key, value)
            }

            @JsonAnyGetter
            @ExcludeMissing
            fun _additionalProperties(): Map<String, JsonValue> =
                Collections.unmodifiableMap(additionalProperties)

            fun toBuilder() = Builder().from(this)

            companion object {

                /** Returns a mutable builder for constructing an instance of [Smarttag]. */
                fun builder() = Builder()
            }

            /** A builder for [Smarttag]. */
            class Builder internal constructor() {

                private var indices: JsonField<MutableList<Long>>? = null
                private var seconds: JsonField<Double> = JsonMissing.of()
                private var tag: JsonField<Tag> = JsonMissing.of()
                private var text: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                internal fun from(smarttag: Smarttag) = apply {
                    indices = smarttag.indices.map { it.toMutableList() }
                    seconds = smarttag.seconds
                    tag = smarttag.tag
                    text = smarttag.text
                    additionalProperties = smarttag.additionalProperties.toMutableMap()
                }

                fun indices(indices: List<Long>) = indices(JsonField.of(indices))

                /** Sets [Builder.indices] to an arbitrary JSON value. */
                fun indices(indices: JsonField<List<Long>>) = apply {
                    this.indices = indices.map { it.toMutableList() }
                }

                /**
                 * Adds a single [Long] to [indices].
                 *
                 * @throws IllegalStateException if the field was previously set to a non-list.
                 */
                fun addIndex(index: Long) = apply {
                    indices =
                        (indices ?: JsonField.of(mutableListOf())).also {
                            checkKnown("indices", it).add(index)
                        }
                }

                fun seconds(seconds: Double) = seconds(JsonField.of(seconds))

                /** Sets [Builder.seconds] to an arbitrary JSON value. */
                fun seconds(seconds: JsonField<Double>) = apply { this.seconds = seconds }

                /**
                 * X's SmartTag for a cashtag, smarttag or hashtag. Xquik leaves out X's trading
                 * platforms, which differ by reader.
                 */
                fun tag(tag: Tag) = tag(JsonField.of(tag))

                /** Sets [Builder.tag] to an arbitrary JSON value. */
                fun tag(tag: JsonField<Tag>) = apply { this.tag = tag }

                fun text(text: String) = text(JsonField.of(text))

                /** Sets [Builder.text] to an arbitrary JSON value. */
                fun text(text: JsonField<String>) = apply { this.text = text }

                fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                    this.additionalProperties.clear()
                    putAllAdditionalProperties(additionalProperties)
                }

                fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                    additionalProperties.put(key, value)
                }

                fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) =
                    apply {
                        this.additionalProperties.putAll(additionalProperties)
                    }

                fun removeAdditionalProperty(key: String) = apply {
                    additionalProperties.remove(key)
                }

                fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                    keys.forEach(::removeAdditionalProperty)
                }

                /**
                 * Returns an immutable instance of [Smarttag].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 */
                fun build(): Smarttag =
                    Smarttag(
                        (indices ?: JsonMissing.of()).map { it.toImmutable() },
                        seconds,
                        tag,
                        text,
                        additionalProperties.toMutableMap(),
                    )
            }

            private var validated: Boolean = false

            /**
             * Validates that the types of all values in this object match their expected types
             * recursively.
             *
             * This method is _not_ forwards compatible with new types from the API for existing
             * fields.
             *
             * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
             *   match its expected type.
             */
            fun validate(): Smarttag = apply {
                if (validated) {
                    return@apply
                }

                indices()
                seconds()
                tag()?.validate()
                text()
                validated = true
            }

            fun isValid(): Boolean =
                try {
                    validate()
                    true
                } catch (e: XTwitterScraperInvalidDataException) {
                    false
                }

            internal fun validity(): Int =
                (indices.asKnown()?.size ?: 0) +
                    (if (seconds.asKnown() == null) 0 else 1) +
                    (tag.asKnown()?.validity() ?: 0) +
                    (if (text.asKnown() == null) 0 else 1)

            /**
             * X's SmartTag for a cashtag, smarttag or hashtag. Xquik leaves out X's trading
             * platforms, which differ by reader.
             */
            class Tag
            @JsonCreator(mode = JsonCreator.Mode.DISABLED)
            private constructor(
                private val id: JsonField<String>,
                private val info: JsonField<Info>,
                private val additionalProperties: MutableMap<String, JsonValue>,
            ) {

                @JsonCreator
                private constructor(
                    @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
                    @JsonProperty("info") @ExcludeMissing info: JsonField<Info> = JsonMissing.of(),
                ) : this(id, info, mutableMapOf())

                /**
                 * X's ID for the SmartTag.
                 *
                 * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected
                 *   type.
                 */
                fun id(): String? = id.getNullable("id")

                /**
                 * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected
                 *   type.
                 */
                fun info(): Info? = info.getNullable("info")

                /**
                 * Returns the raw JSON value of [id].
                 *
                 * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
                 */
                @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

                /**
                 * Returns the raw JSON value of [info].
                 *
                 * Unlike [info], this method doesn't throw if the JSON field has an unexpected
                 * type.
                 */
                @JsonProperty("info") @ExcludeMissing fun _info(): JsonField<Info> = info

                @JsonAnySetter
                private fun putAdditionalProperty(key: String, value: JsonValue) {
                    additionalProperties.put(key, value)
                }

                @JsonAnyGetter
                @ExcludeMissing
                fun _additionalProperties(): Map<String, JsonValue> =
                    Collections.unmodifiableMap(additionalProperties)

                fun toBuilder() = Builder().from(this)

                companion object {

                    /** Returns a mutable builder for constructing an instance of [Tag]. */
                    fun builder() = Builder()
                }

                /** A builder for [Tag]. */
                class Builder internal constructor() {

                    private var id: JsonField<String> = JsonMissing.of()
                    private var info: JsonField<Info> = JsonMissing.of()
                    private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                    internal fun from(tag: Tag) = apply {
                        id = tag.id
                        info = tag.info
                        additionalProperties = tag.additionalProperties.toMutableMap()
                    }

                    /** X's ID for the SmartTag. */
                    fun id(id: String) = id(JsonField.of(id))

                    /** Sets [Builder.id] to an arbitrary JSON value. */
                    fun id(id: JsonField<String>) = apply { this.id = id }

                    fun info(info: Info) = info(JsonField.of(info))

                    /** Sets [Builder.info] to an arbitrary JSON value. */
                    fun info(info: JsonField<Info>) = apply { this.info = info }

                    fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                        this.additionalProperties.clear()
                        putAllAdditionalProperties(additionalProperties)
                    }

                    fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                        additionalProperties.put(key, value)
                    }

                    fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) =
                        apply {
                            this.additionalProperties.putAll(additionalProperties)
                        }

                    fun removeAdditionalProperty(key: String) = apply {
                        additionalProperties.remove(key)
                    }

                    fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                        keys.forEach(::removeAdditionalProperty)
                    }

                    /**
                     * Returns an immutable instance of [Tag].
                     *
                     * Further updates to this [Builder] will not mutate the returned instance.
                     */
                    fun build(): Tag = Tag(id, info, additionalProperties.toMutableMap())
                }

                private var validated: Boolean = false

                /**
                 * Validates that the types of all values in this object match their expected types
                 * recursively.
                 *
                 * This method is _not_ forwards compatible with new types from the API for existing
                 * fields.
                 *
                 * @throws XTwitterScraperInvalidDataException if any value type in this object
                 *   doesn't match its expected type.
                 */
                fun validate(): Tag = apply {
                    if (validated) {
                        return@apply
                    }

                    id()
                    info()?.validate()
                    validated = true
                }

                fun isValid(): Boolean =
                    try {
                        validate()
                        true
                    } catch (e: XTwitterScraperInvalidDataException) {
                        false
                    }

                internal fun validity(): Int =
                    (if (id.asKnown() == null) 0 else 1) + (info.asKnown()?.validity() ?: 0)

                class Info
                @JsonCreator(mode = JsonCreator.Mode.DISABLED)
                private constructor(
                    private val info: JsonField<InnerInfo>,
                    private val additionalProperties: MutableMap<String, JsonValue>,
                ) {

                    @JsonCreator
                    private constructor(
                        @JsonProperty("info")
                        @ExcludeMissing
                        info: JsonField<InnerInfo> = JsonMissing.of()
                    ) : this(info, mutableMapOf())

                    /**
                     * @throws XTwitterScraperInvalidDataException if the JSON field has an
                     *   unexpected type.
                     */
                    fun info(): InnerInfo? = info.getNullable("info")

                    /**
                     * Returns the raw JSON value of [info].
                     *
                     * Unlike [info], this method doesn't throw if the JSON field has an unexpected
                     * type.
                     */
                    @JsonProperty("info") @ExcludeMissing fun _info(): JsonField<InnerInfo> = info

                    @JsonAnySetter
                    private fun putAdditionalProperty(key: String, value: JsonValue) {
                        additionalProperties.put(key, value)
                    }

                    @JsonAnyGetter
                    @ExcludeMissing
                    fun _additionalProperties(): Map<String, JsonValue> =
                        Collections.unmodifiableMap(additionalProperties)

                    fun toBuilder() = Builder().from(this)

                    companion object {

                        /** Returns a mutable builder for constructing an instance of [Info]. */
                        fun builder() = Builder()
                    }

                    /** A builder for [Info]. */
                    class Builder internal constructor() {

                        private var info: JsonField<InnerInfo> = JsonMissing.of()
                        private var additionalProperties: MutableMap<String, JsonValue> =
                            mutableMapOf()

                        internal fun from(info: Info) = apply {
                            this.info = info.info
                            additionalProperties = info.additionalProperties.toMutableMap()
                        }

                        fun info(info: InnerInfo) = info(JsonField.of(info))

                        /** Sets [Builder.info] to an arbitrary JSON value. */
                        fun info(info: JsonField<InnerInfo>) = apply { this.info = info }

                        fun additionalProperties(additionalProperties: Map<String, JsonValue>) =
                            apply {
                                this.additionalProperties.clear()
                                putAllAdditionalProperties(additionalProperties)
                            }

                        fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                            additionalProperties.put(key, value)
                        }

                        fun putAllAdditionalProperties(
                            additionalProperties: Map<String, JsonValue>
                        ) = apply {
                            this.additionalProperties.putAll(additionalProperties)
                        }

                        fun removeAdditionalProperty(key: String) = apply {
                            additionalProperties.remove(key)
                        }

                        fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                            keys.forEach(::removeAdditionalProperty)
                        }

                        /**
                         * Returns an immutable instance of [Info].
                         *
                         * Further updates to this [Builder] will not mutate the returned instance.
                         */
                        fun build(): Info = Info(info, additionalProperties.toMutableMap())
                    }

                    private var validated: Boolean = false

                    /**
                     * Validates that the types of all values in this object match their expected
                     * types recursively.
                     *
                     * This method is _not_ forwards compatible with new types from the API for
                     * existing fields.
                     *
                     * @throws XTwitterScraperInvalidDataException if any value type in this object
                     *   doesn't match its expected type.
                     */
                    fun validate(): Info = apply {
                        if (validated) {
                            return@apply
                        }

                        info()?.validate()
                        validated = true
                    }

                    fun isValid(): Boolean =
                        try {
                            validate()
                            true
                        } catch (e: XTwitterScraperInvalidDataException) {
                            false
                        }

                    internal fun validity(): Int = (info.asKnown()?.validity() ?: 0)

                    class InnerInfo
                    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
                    private constructor(
                        private val active: JsonField<Boolean>,
                        private val name: JsonField<String>,
                        private val ticker: JsonField<String>,
                        private val additionalProperties: MutableMap<String, JsonValue>,
                    ) {

                        @JsonCreator
                        private constructor(
                            @JsonProperty("active")
                            @ExcludeMissing
                            active: JsonField<Boolean> = JsonMissing.of(),
                            @JsonProperty("name")
                            @ExcludeMissing
                            name: JsonField<String> = JsonMissing.of(),
                            @JsonProperty("ticker")
                            @ExcludeMissing
                            ticker: JsonField<String> = JsonMissing.of(),
                        ) : this(active, name, ticker, mutableMapOf())

                        /**
                         * Whether X marks the SmartTag active.
                         *
                         * @throws XTwitterScraperInvalidDataException if the JSON field has an
                         *   unexpected type.
                         */
                        fun active(): Boolean? = active.getNullable("active")

                        /**
                         * @throws XTwitterScraperInvalidDataException if the JSON field has an
                         *   unexpected type.
                         */
                        fun name(): String? = name.getNullable("name")

                        /**
                         * @throws XTwitterScraperInvalidDataException if the JSON field has an
                         *   unexpected type.
                         */
                        fun ticker(): String? = ticker.getNullable("ticker")

                        /**
                         * Returns the raw JSON value of [active].
                         *
                         * Unlike [active], this method doesn't throw if the JSON field has an
                         * unexpected type.
                         */
                        @JsonProperty("active")
                        @ExcludeMissing
                        fun _active(): JsonField<Boolean> = active

                        /**
                         * Returns the raw JSON value of [name].
                         *
                         * Unlike [name], this method doesn't throw if the JSON field has an
                         * unexpected type.
                         */
                        @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<String> = name

                        /**
                         * Returns the raw JSON value of [ticker].
                         *
                         * Unlike [ticker], this method doesn't throw if the JSON field has an
                         * unexpected type.
                         */
                        @JsonProperty("ticker")
                        @ExcludeMissing
                        fun _ticker(): JsonField<String> = ticker

                        @JsonAnySetter
                        private fun putAdditionalProperty(key: String, value: JsonValue) {
                            additionalProperties.put(key, value)
                        }

                        @JsonAnyGetter
                        @ExcludeMissing
                        fun _additionalProperties(): Map<String, JsonValue> =
                            Collections.unmodifiableMap(additionalProperties)

                        fun toBuilder() = Builder().from(this)

                        companion object {

                            /**
                             * Returns a mutable builder for constructing an instance of
                             * [InnerInfo].
                             */
                            fun builder() = Builder()
                        }

                        /** A builder for [InnerInfo]. */
                        class Builder internal constructor() {

                            private var active: JsonField<Boolean> = JsonMissing.of()
                            private var name: JsonField<String> = JsonMissing.of()
                            private var ticker: JsonField<String> = JsonMissing.of()
                            private var additionalProperties: MutableMap<String, JsonValue> =
                                mutableMapOf()

                            internal fun from(innerInfo: InnerInfo) = apply {
                                active = innerInfo.active
                                name = innerInfo.name
                                ticker = innerInfo.ticker
                                additionalProperties = innerInfo.additionalProperties.toMutableMap()
                            }

                            /** Whether X marks the SmartTag active. */
                            fun active(active: Boolean) = active(JsonField.of(active))

                            /** Sets [Builder.active] to an arbitrary JSON value. */
                            fun active(active: JsonField<Boolean>) = apply { this.active = active }

                            fun name(name: String) = name(JsonField.of(name))

                            /** Sets [Builder.name] to an arbitrary JSON value. */
                            fun name(name: JsonField<String>) = apply { this.name = name }

                            fun ticker(ticker: String) = ticker(JsonField.of(ticker))

                            /** Sets [Builder.ticker] to an arbitrary JSON value. */
                            fun ticker(ticker: JsonField<String>) = apply { this.ticker = ticker }

                            fun additionalProperties(additionalProperties: Map<String, JsonValue>) =
                                apply {
                                    this.additionalProperties.clear()
                                    putAllAdditionalProperties(additionalProperties)
                                }

                            fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                                additionalProperties.put(key, value)
                            }

                            fun putAllAdditionalProperties(
                                additionalProperties: Map<String, JsonValue>
                            ) = apply {
                                this.additionalProperties.putAll(additionalProperties)
                            }

                            fun removeAdditionalProperty(key: String) = apply {
                                additionalProperties.remove(key)
                            }

                            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                                keys.forEach(::removeAdditionalProperty)
                            }

                            /**
                             * Returns an immutable instance of [InnerInfo].
                             *
                             * Further updates to this [Builder] will not mutate the returned
                             * instance.
                             */
                            fun build(): InnerInfo =
                                InnerInfo(active, name, ticker, additionalProperties.toMutableMap())
                        }

                        private var validated: Boolean = false

                        /**
                         * Validates that the types of all values in this object match their
                         * expected types recursively.
                         *
                         * This method is _not_ forwards compatible with new types from the API for
                         * existing fields.
                         *
                         * @throws XTwitterScraperInvalidDataException if any value type in this
                         *   object doesn't match its expected type.
                         */
                        fun validate(): InnerInfo = apply {
                            if (validated) {
                                return@apply
                            }

                            active()
                            name()
                            ticker()
                            validated = true
                        }

                        fun isValid(): Boolean =
                            try {
                                validate()
                                true
                            } catch (e: XTwitterScraperInvalidDataException) {
                                false
                            }

                        internal fun validity(): Int =
                            (if (active.asKnown() == null) 0 else 1) +
                                (if (name.asKnown() == null) 0 else 1) +
                                (if (ticker.asKnown() == null) 0 else 1)

                        override fun equals(other: Any?): Boolean {
                            if (this === other) {
                                return true
                            }

                            return other is InnerInfo &&
                                active == other.active &&
                                name == other.name &&
                                ticker == other.ticker &&
                                additionalProperties == other.additionalProperties
                        }

                        private val hashCode: Int by lazy {
                            Objects.hash(active, name, ticker, additionalProperties)
                        }

                        override fun hashCode(): Int = hashCode

                        override fun toString() =
                            "InnerInfo{active=$active, name=$name, ticker=$ticker, additionalProperties=$additionalProperties}"
                    }

                    override fun equals(other: Any?): Boolean {
                        if (this === other) {
                            return true
                        }

                        return other is Info &&
                            info == other.info &&
                            additionalProperties == other.additionalProperties
                    }

                    private val hashCode: Int by lazy {
                        Objects.hash(info, additionalProperties)
                    }

                    override fun hashCode(): Int = hashCode

                    override fun toString() =
                        "Info{info=$info, additionalProperties=$additionalProperties}"
                }

                override fun equals(other: Any?): Boolean {
                    if (this === other) {
                        return true
                    }

                    return other is Tag &&
                        id == other.id &&
                        info == other.info &&
                        additionalProperties == other.additionalProperties
                }

                private val hashCode: Int by lazy {
                    Objects.hash(id, info, additionalProperties)
                }

                override fun hashCode(): Int = hashCode

                override fun toString() =
                    "Tag{id=$id, info=$info, additionalProperties=$additionalProperties}"
            }

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is Smarttag &&
                    indices == other.indices &&
                    seconds == other.seconds &&
                    tag == other.tag &&
                    text == other.text &&
                    additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(indices, seconds, tag, text, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "Smarttag{indices=$indices, seconds=$seconds, tag=$tag, text=$text, additionalProperties=$additionalProperties}"
        }

        /** Indexed smart-tag, cashtag, or video timestamp metadata. */
        class Symbol
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val indices: JsonField<List<Long>>,
            private val seconds: JsonField<Double>,
            private val tag: JsonField<Tag>,
            private val text: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("indices")
                @ExcludeMissing
                indices: JsonField<List<Long>> = JsonMissing.of(),
                @JsonProperty("seconds")
                @ExcludeMissing
                seconds: JsonField<Double> = JsonMissing.of(),
                @JsonProperty("tag") @ExcludeMissing tag: JsonField<Tag> = JsonMissing.of(),
                @JsonProperty("text") @ExcludeMissing text: JsonField<String> = JsonMissing.of(),
            ) : this(indices, seconds, tag, text, mutableMapOf())

            /**
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun indices(): List<Long>? = indices.getNullable("indices")

            /**
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun seconds(): Double? = seconds.getNullable("seconds")

            /**
             * X's SmartTag for a cashtag, smarttag or hashtag. Xquik leaves out X's trading
             * platforms, which differ by reader.
             *
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun tag(): Tag? = tag.getNullable("tag")

            /**
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun text(): String? = text.getNullable("text")

            /**
             * Returns the raw JSON value of [indices].
             *
             * Unlike [indices], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("indices") @ExcludeMissing fun _indices(): JsonField<List<Long>> = indices

            /**
             * Returns the raw JSON value of [seconds].
             *
             * Unlike [seconds], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("seconds") @ExcludeMissing fun _seconds(): JsonField<Double> = seconds

            /**
             * Returns the raw JSON value of [tag].
             *
             * Unlike [tag], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("tag") @ExcludeMissing fun _tag(): JsonField<Tag> = tag

            /**
             * Returns the raw JSON value of [text].
             *
             * Unlike [text], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("text") @ExcludeMissing fun _text(): JsonField<String> = text

            @JsonAnySetter
            private fun putAdditionalProperty(key: String, value: JsonValue) {
                additionalProperties.put(key, value)
            }

            @JsonAnyGetter
            @ExcludeMissing
            fun _additionalProperties(): Map<String, JsonValue> =
                Collections.unmodifiableMap(additionalProperties)

            fun toBuilder() = Builder().from(this)

            companion object {

                /** Returns a mutable builder for constructing an instance of [Symbol]. */
                fun builder() = Builder()
            }

            /** A builder for [Symbol]. */
            class Builder internal constructor() {

                private var indices: JsonField<MutableList<Long>>? = null
                private var seconds: JsonField<Double> = JsonMissing.of()
                private var tag: JsonField<Tag> = JsonMissing.of()
                private var text: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                internal fun from(symbol: Symbol) = apply {
                    indices = symbol.indices.map { it.toMutableList() }
                    seconds = symbol.seconds
                    tag = symbol.tag
                    text = symbol.text
                    additionalProperties = symbol.additionalProperties.toMutableMap()
                }

                fun indices(indices: List<Long>) = indices(JsonField.of(indices))

                /** Sets [Builder.indices] to an arbitrary JSON value. */
                fun indices(indices: JsonField<List<Long>>) = apply {
                    this.indices = indices.map { it.toMutableList() }
                }

                /**
                 * Adds a single [Long] to [indices].
                 *
                 * @throws IllegalStateException if the field was previously set to a non-list.
                 */
                fun addIndex(index: Long) = apply {
                    indices =
                        (indices ?: JsonField.of(mutableListOf())).also {
                            checkKnown("indices", it).add(index)
                        }
                }

                fun seconds(seconds: Double) = seconds(JsonField.of(seconds))

                /** Sets [Builder.seconds] to an arbitrary JSON value. */
                fun seconds(seconds: JsonField<Double>) = apply { this.seconds = seconds }

                /**
                 * X's SmartTag for a cashtag, smarttag or hashtag. Xquik leaves out X's trading
                 * platforms, which differ by reader.
                 */
                fun tag(tag: Tag) = tag(JsonField.of(tag))

                /** Sets [Builder.tag] to an arbitrary JSON value. */
                fun tag(tag: JsonField<Tag>) = apply { this.tag = tag }

                fun text(text: String) = text(JsonField.of(text))

                /** Sets [Builder.text] to an arbitrary JSON value. */
                fun text(text: JsonField<String>) = apply { this.text = text }

                fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                    this.additionalProperties.clear()
                    putAllAdditionalProperties(additionalProperties)
                }

                fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                    additionalProperties.put(key, value)
                }

                fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) =
                    apply {
                        this.additionalProperties.putAll(additionalProperties)
                    }

                fun removeAdditionalProperty(key: String) = apply {
                    additionalProperties.remove(key)
                }

                fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                    keys.forEach(::removeAdditionalProperty)
                }

                /**
                 * Returns an immutable instance of [Symbol].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 */
                fun build(): Symbol =
                    Symbol(
                        (indices ?: JsonMissing.of()).map { it.toImmutable() },
                        seconds,
                        tag,
                        text,
                        additionalProperties.toMutableMap(),
                    )
            }

            private var validated: Boolean = false

            /**
             * Validates that the types of all values in this object match their expected types
             * recursively.
             *
             * This method is _not_ forwards compatible with new types from the API for existing
             * fields.
             *
             * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
             *   match its expected type.
             */
            fun validate(): Symbol = apply {
                if (validated) {
                    return@apply
                }

                indices()
                seconds()
                tag()?.validate()
                text()
                validated = true
            }

            fun isValid(): Boolean =
                try {
                    validate()
                    true
                } catch (e: XTwitterScraperInvalidDataException) {
                    false
                }

            internal fun validity(): Int =
                (indices.asKnown()?.size ?: 0) +
                    (if (seconds.asKnown() == null) 0 else 1) +
                    (tag.asKnown()?.validity() ?: 0) +
                    (if (text.asKnown() == null) 0 else 1)

            /**
             * X's SmartTag for a cashtag, smarttag or hashtag. Xquik leaves out X's trading
             * platforms, which differ by reader.
             */
            class Tag
            @JsonCreator(mode = JsonCreator.Mode.DISABLED)
            private constructor(
                private val id: JsonField<String>,
                private val info: JsonField<Info>,
                private val additionalProperties: MutableMap<String, JsonValue>,
            ) {

                @JsonCreator
                private constructor(
                    @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
                    @JsonProperty("info") @ExcludeMissing info: JsonField<Info> = JsonMissing.of(),
                ) : this(id, info, mutableMapOf())

                /**
                 * X's ID for the SmartTag.
                 *
                 * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected
                 *   type.
                 */
                fun id(): String? = id.getNullable("id")

                /**
                 * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected
                 *   type.
                 */
                fun info(): Info? = info.getNullable("info")

                /**
                 * Returns the raw JSON value of [id].
                 *
                 * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
                 */
                @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

                /**
                 * Returns the raw JSON value of [info].
                 *
                 * Unlike [info], this method doesn't throw if the JSON field has an unexpected
                 * type.
                 */
                @JsonProperty("info") @ExcludeMissing fun _info(): JsonField<Info> = info

                @JsonAnySetter
                private fun putAdditionalProperty(key: String, value: JsonValue) {
                    additionalProperties.put(key, value)
                }

                @JsonAnyGetter
                @ExcludeMissing
                fun _additionalProperties(): Map<String, JsonValue> =
                    Collections.unmodifiableMap(additionalProperties)

                fun toBuilder() = Builder().from(this)

                companion object {

                    /** Returns a mutable builder for constructing an instance of [Tag]. */
                    fun builder() = Builder()
                }

                /** A builder for [Tag]. */
                class Builder internal constructor() {

                    private var id: JsonField<String> = JsonMissing.of()
                    private var info: JsonField<Info> = JsonMissing.of()
                    private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                    internal fun from(tag: Tag) = apply {
                        id = tag.id
                        info = tag.info
                        additionalProperties = tag.additionalProperties.toMutableMap()
                    }

                    /** X's ID for the SmartTag. */
                    fun id(id: String) = id(JsonField.of(id))

                    /** Sets [Builder.id] to an arbitrary JSON value. */
                    fun id(id: JsonField<String>) = apply { this.id = id }

                    fun info(info: Info) = info(JsonField.of(info))

                    /** Sets [Builder.info] to an arbitrary JSON value. */
                    fun info(info: JsonField<Info>) = apply { this.info = info }

                    fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                        this.additionalProperties.clear()
                        putAllAdditionalProperties(additionalProperties)
                    }

                    fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                        additionalProperties.put(key, value)
                    }

                    fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) =
                        apply {
                            this.additionalProperties.putAll(additionalProperties)
                        }

                    fun removeAdditionalProperty(key: String) = apply {
                        additionalProperties.remove(key)
                    }

                    fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                        keys.forEach(::removeAdditionalProperty)
                    }

                    /**
                     * Returns an immutable instance of [Tag].
                     *
                     * Further updates to this [Builder] will not mutate the returned instance.
                     */
                    fun build(): Tag = Tag(id, info, additionalProperties.toMutableMap())
                }

                private var validated: Boolean = false

                /**
                 * Validates that the types of all values in this object match their expected types
                 * recursively.
                 *
                 * This method is _not_ forwards compatible with new types from the API for existing
                 * fields.
                 *
                 * @throws XTwitterScraperInvalidDataException if any value type in this object
                 *   doesn't match its expected type.
                 */
                fun validate(): Tag = apply {
                    if (validated) {
                        return@apply
                    }

                    id()
                    info()?.validate()
                    validated = true
                }

                fun isValid(): Boolean =
                    try {
                        validate()
                        true
                    } catch (e: XTwitterScraperInvalidDataException) {
                        false
                    }

                internal fun validity(): Int =
                    (if (id.asKnown() == null) 0 else 1) + (info.asKnown()?.validity() ?: 0)

                class Info
                @JsonCreator(mode = JsonCreator.Mode.DISABLED)
                private constructor(
                    private val info: JsonField<InnerInfo>,
                    private val additionalProperties: MutableMap<String, JsonValue>,
                ) {

                    @JsonCreator
                    private constructor(
                        @JsonProperty("info")
                        @ExcludeMissing
                        info: JsonField<InnerInfo> = JsonMissing.of()
                    ) : this(info, mutableMapOf())

                    /**
                     * @throws XTwitterScraperInvalidDataException if the JSON field has an
                     *   unexpected type.
                     */
                    fun info(): InnerInfo? = info.getNullable("info")

                    /**
                     * Returns the raw JSON value of [info].
                     *
                     * Unlike [info], this method doesn't throw if the JSON field has an unexpected
                     * type.
                     */
                    @JsonProperty("info") @ExcludeMissing fun _info(): JsonField<InnerInfo> = info

                    @JsonAnySetter
                    private fun putAdditionalProperty(key: String, value: JsonValue) {
                        additionalProperties.put(key, value)
                    }

                    @JsonAnyGetter
                    @ExcludeMissing
                    fun _additionalProperties(): Map<String, JsonValue> =
                        Collections.unmodifiableMap(additionalProperties)

                    fun toBuilder() = Builder().from(this)

                    companion object {

                        /** Returns a mutable builder for constructing an instance of [Info]. */
                        fun builder() = Builder()
                    }

                    /** A builder for [Info]. */
                    class Builder internal constructor() {

                        private var info: JsonField<InnerInfo> = JsonMissing.of()
                        private var additionalProperties: MutableMap<String, JsonValue> =
                            mutableMapOf()

                        internal fun from(info: Info) = apply {
                            this.info = info.info
                            additionalProperties = info.additionalProperties.toMutableMap()
                        }

                        fun info(info: InnerInfo) = info(JsonField.of(info))

                        /** Sets [Builder.info] to an arbitrary JSON value. */
                        fun info(info: JsonField<InnerInfo>) = apply { this.info = info }

                        fun additionalProperties(additionalProperties: Map<String, JsonValue>) =
                            apply {
                                this.additionalProperties.clear()
                                putAllAdditionalProperties(additionalProperties)
                            }

                        fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                            additionalProperties.put(key, value)
                        }

                        fun putAllAdditionalProperties(
                            additionalProperties: Map<String, JsonValue>
                        ) = apply {
                            this.additionalProperties.putAll(additionalProperties)
                        }

                        fun removeAdditionalProperty(key: String) = apply {
                            additionalProperties.remove(key)
                        }

                        fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                            keys.forEach(::removeAdditionalProperty)
                        }

                        /**
                         * Returns an immutable instance of [Info].
                         *
                         * Further updates to this [Builder] will not mutate the returned instance.
                         */
                        fun build(): Info = Info(info, additionalProperties.toMutableMap())
                    }

                    private var validated: Boolean = false

                    /**
                     * Validates that the types of all values in this object match their expected
                     * types recursively.
                     *
                     * This method is _not_ forwards compatible with new types from the API for
                     * existing fields.
                     *
                     * @throws XTwitterScraperInvalidDataException if any value type in this object
                     *   doesn't match its expected type.
                     */
                    fun validate(): Info = apply {
                        if (validated) {
                            return@apply
                        }

                        info()?.validate()
                        validated = true
                    }

                    fun isValid(): Boolean =
                        try {
                            validate()
                            true
                        } catch (e: XTwitterScraperInvalidDataException) {
                            false
                        }

                    internal fun validity(): Int = (info.asKnown()?.validity() ?: 0)

                    class InnerInfo
                    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
                    private constructor(
                        private val active: JsonField<Boolean>,
                        private val name: JsonField<String>,
                        private val ticker: JsonField<String>,
                        private val additionalProperties: MutableMap<String, JsonValue>,
                    ) {

                        @JsonCreator
                        private constructor(
                            @JsonProperty("active")
                            @ExcludeMissing
                            active: JsonField<Boolean> = JsonMissing.of(),
                            @JsonProperty("name")
                            @ExcludeMissing
                            name: JsonField<String> = JsonMissing.of(),
                            @JsonProperty("ticker")
                            @ExcludeMissing
                            ticker: JsonField<String> = JsonMissing.of(),
                        ) : this(active, name, ticker, mutableMapOf())

                        /**
                         * Whether X marks the SmartTag active.
                         *
                         * @throws XTwitterScraperInvalidDataException if the JSON field has an
                         *   unexpected type.
                         */
                        fun active(): Boolean? = active.getNullable("active")

                        /**
                         * @throws XTwitterScraperInvalidDataException if the JSON field has an
                         *   unexpected type.
                         */
                        fun name(): String? = name.getNullable("name")

                        /**
                         * @throws XTwitterScraperInvalidDataException if the JSON field has an
                         *   unexpected type.
                         */
                        fun ticker(): String? = ticker.getNullable("ticker")

                        /**
                         * Returns the raw JSON value of [active].
                         *
                         * Unlike [active], this method doesn't throw if the JSON field has an
                         * unexpected type.
                         */
                        @JsonProperty("active")
                        @ExcludeMissing
                        fun _active(): JsonField<Boolean> = active

                        /**
                         * Returns the raw JSON value of [name].
                         *
                         * Unlike [name], this method doesn't throw if the JSON field has an
                         * unexpected type.
                         */
                        @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<String> = name

                        /**
                         * Returns the raw JSON value of [ticker].
                         *
                         * Unlike [ticker], this method doesn't throw if the JSON field has an
                         * unexpected type.
                         */
                        @JsonProperty("ticker")
                        @ExcludeMissing
                        fun _ticker(): JsonField<String> = ticker

                        @JsonAnySetter
                        private fun putAdditionalProperty(key: String, value: JsonValue) {
                            additionalProperties.put(key, value)
                        }

                        @JsonAnyGetter
                        @ExcludeMissing
                        fun _additionalProperties(): Map<String, JsonValue> =
                            Collections.unmodifiableMap(additionalProperties)

                        fun toBuilder() = Builder().from(this)

                        companion object {

                            /**
                             * Returns a mutable builder for constructing an instance of
                             * [InnerInfo].
                             */
                            fun builder() = Builder()
                        }

                        /** A builder for [InnerInfo]. */
                        class Builder internal constructor() {

                            private var active: JsonField<Boolean> = JsonMissing.of()
                            private var name: JsonField<String> = JsonMissing.of()
                            private var ticker: JsonField<String> = JsonMissing.of()
                            private var additionalProperties: MutableMap<String, JsonValue> =
                                mutableMapOf()

                            internal fun from(innerInfo: InnerInfo) = apply {
                                active = innerInfo.active
                                name = innerInfo.name
                                ticker = innerInfo.ticker
                                additionalProperties = innerInfo.additionalProperties.toMutableMap()
                            }

                            /** Whether X marks the SmartTag active. */
                            fun active(active: Boolean) = active(JsonField.of(active))

                            /** Sets [Builder.active] to an arbitrary JSON value. */
                            fun active(active: JsonField<Boolean>) = apply { this.active = active }

                            fun name(name: String) = name(JsonField.of(name))

                            /** Sets [Builder.name] to an arbitrary JSON value. */
                            fun name(name: JsonField<String>) = apply { this.name = name }

                            fun ticker(ticker: String) = ticker(JsonField.of(ticker))

                            /** Sets [Builder.ticker] to an arbitrary JSON value. */
                            fun ticker(ticker: JsonField<String>) = apply { this.ticker = ticker }

                            fun additionalProperties(additionalProperties: Map<String, JsonValue>) =
                                apply {
                                    this.additionalProperties.clear()
                                    putAllAdditionalProperties(additionalProperties)
                                }

                            fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                                additionalProperties.put(key, value)
                            }

                            fun putAllAdditionalProperties(
                                additionalProperties: Map<String, JsonValue>
                            ) = apply {
                                this.additionalProperties.putAll(additionalProperties)
                            }

                            fun removeAdditionalProperty(key: String) = apply {
                                additionalProperties.remove(key)
                            }

                            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                                keys.forEach(::removeAdditionalProperty)
                            }

                            /**
                             * Returns an immutable instance of [InnerInfo].
                             *
                             * Further updates to this [Builder] will not mutate the returned
                             * instance.
                             */
                            fun build(): InnerInfo =
                                InnerInfo(active, name, ticker, additionalProperties.toMutableMap())
                        }

                        private var validated: Boolean = false

                        /**
                         * Validates that the types of all values in this object match their
                         * expected types recursively.
                         *
                         * This method is _not_ forwards compatible with new types from the API for
                         * existing fields.
                         *
                         * @throws XTwitterScraperInvalidDataException if any value type in this
                         *   object doesn't match its expected type.
                         */
                        fun validate(): InnerInfo = apply {
                            if (validated) {
                                return@apply
                            }

                            active()
                            name()
                            ticker()
                            validated = true
                        }

                        fun isValid(): Boolean =
                            try {
                                validate()
                                true
                            } catch (e: XTwitterScraperInvalidDataException) {
                                false
                            }

                        internal fun validity(): Int =
                            (if (active.asKnown() == null) 0 else 1) +
                                (if (name.asKnown() == null) 0 else 1) +
                                (if (ticker.asKnown() == null) 0 else 1)

                        override fun equals(other: Any?): Boolean {
                            if (this === other) {
                                return true
                            }

                            return other is InnerInfo &&
                                active == other.active &&
                                name == other.name &&
                                ticker == other.ticker &&
                                additionalProperties == other.additionalProperties
                        }

                        private val hashCode: Int by lazy {
                            Objects.hash(active, name, ticker, additionalProperties)
                        }

                        override fun hashCode(): Int = hashCode

                        override fun toString() =
                            "InnerInfo{active=$active, name=$name, ticker=$ticker, additionalProperties=$additionalProperties}"
                    }

                    override fun equals(other: Any?): Boolean {
                        if (this === other) {
                            return true
                        }

                        return other is Info &&
                            info == other.info &&
                            additionalProperties == other.additionalProperties
                    }

                    private val hashCode: Int by lazy {
                        Objects.hash(info, additionalProperties)
                    }

                    override fun hashCode(): Int = hashCode

                    override fun toString() =
                        "Info{info=$info, additionalProperties=$additionalProperties}"
                }

                override fun equals(other: Any?): Boolean {
                    if (this === other) {
                        return true
                    }

                    return other is Tag &&
                        id == other.id &&
                        info == other.info &&
                        additionalProperties == other.additionalProperties
                }

                private val hashCode: Int by lazy {
                    Objects.hash(id, info, additionalProperties)
                }

                override fun hashCode(): Int = hashCode

                override fun toString() =
                    "Tag{id=$id, info=$info, additionalProperties=$additionalProperties}"
            }

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is Symbol &&
                    indices == other.indices &&
                    seconds == other.seconds &&
                    tag == other.tag &&
                    text == other.text &&
                    additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(indices, seconds, tag, text, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "Symbol{indices=$indices, seconds=$seconds, tag=$tag, text=$text, additionalProperties=$additionalProperties}"
        }

        /** Indexed smart-tag, cashtag, or video timestamp metadata. */
        class Timestamp
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val indices: JsonField<List<Long>>,
            private val seconds: JsonField<Double>,
            private val tag: JsonField<Tag>,
            private val text: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("indices")
                @ExcludeMissing
                indices: JsonField<List<Long>> = JsonMissing.of(),
                @JsonProperty("seconds")
                @ExcludeMissing
                seconds: JsonField<Double> = JsonMissing.of(),
                @JsonProperty("tag") @ExcludeMissing tag: JsonField<Tag> = JsonMissing.of(),
                @JsonProperty("text") @ExcludeMissing text: JsonField<String> = JsonMissing.of(),
            ) : this(indices, seconds, tag, text, mutableMapOf())

            /**
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun indices(): List<Long>? = indices.getNullable("indices")

            /**
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun seconds(): Double? = seconds.getNullable("seconds")

            /**
             * X's SmartTag for a cashtag, smarttag or hashtag. Xquik leaves out X's trading
             * platforms, which differ by reader.
             *
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun tag(): Tag? = tag.getNullable("tag")

            /**
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun text(): String? = text.getNullable("text")

            /**
             * Returns the raw JSON value of [indices].
             *
             * Unlike [indices], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("indices") @ExcludeMissing fun _indices(): JsonField<List<Long>> = indices

            /**
             * Returns the raw JSON value of [seconds].
             *
             * Unlike [seconds], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("seconds") @ExcludeMissing fun _seconds(): JsonField<Double> = seconds

            /**
             * Returns the raw JSON value of [tag].
             *
             * Unlike [tag], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("tag") @ExcludeMissing fun _tag(): JsonField<Tag> = tag

            /**
             * Returns the raw JSON value of [text].
             *
             * Unlike [text], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("text") @ExcludeMissing fun _text(): JsonField<String> = text

            @JsonAnySetter
            private fun putAdditionalProperty(key: String, value: JsonValue) {
                additionalProperties.put(key, value)
            }

            @JsonAnyGetter
            @ExcludeMissing
            fun _additionalProperties(): Map<String, JsonValue> =
                Collections.unmodifiableMap(additionalProperties)

            fun toBuilder() = Builder().from(this)

            companion object {

                /** Returns a mutable builder for constructing an instance of [Timestamp]. */
                fun builder() = Builder()
            }

            /** A builder for [Timestamp]. */
            class Builder internal constructor() {

                private var indices: JsonField<MutableList<Long>>? = null
                private var seconds: JsonField<Double> = JsonMissing.of()
                private var tag: JsonField<Tag> = JsonMissing.of()
                private var text: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                internal fun from(timestamp: Timestamp) = apply {
                    indices = timestamp.indices.map { it.toMutableList() }
                    seconds = timestamp.seconds
                    tag = timestamp.tag
                    text = timestamp.text
                    additionalProperties = timestamp.additionalProperties.toMutableMap()
                }

                fun indices(indices: List<Long>) = indices(JsonField.of(indices))

                /** Sets [Builder.indices] to an arbitrary JSON value. */
                fun indices(indices: JsonField<List<Long>>) = apply {
                    this.indices = indices.map { it.toMutableList() }
                }

                /**
                 * Adds a single [Long] to [indices].
                 *
                 * @throws IllegalStateException if the field was previously set to a non-list.
                 */
                fun addIndex(index: Long) = apply {
                    indices =
                        (indices ?: JsonField.of(mutableListOf())).also {
                            checkKnown("indices", it).add(index)
                        }
                }

                fun seconds(seconds: Double) = seconds(JsonField.of(seconds))

                /** Sets [Builder.seconds] to an arbitrary JSON value. */
                fun seconds(seconds: JsonField<Double>) = apply { this.seconds = seconds }

                /**
                 * X's SmartTag for a cashtag, smarttag or hashtag. Xquik leaves out X's trading
                 * platforms, which differ by reader.
                 */
                fun tag(tag: Tag) = tag(JsonField.of(tag))

                /** Sets [Builder.tag] to an arbitrary JSON value. */
                fun tag(tag: JsonField<Tag>) = apply { this.tag = tag }

                fun text(text: String) = text(JsonField.of(text))

                /** Sets [Builder.text] to an arbitrary JSON value. */
                fun text(text: JsonField<String>) = apply { this.text = text }

                fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                    this.additionalProperties.clear()
                    putAllAdditionalProperties(additionalProperties)
                }

                fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                    additionalProperties.put(key, value)
                }

                fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) =
                    apply {
                        this.additionalProperties.putAll(additionalProperties)
                    }

                fun removeAdditionalProperty(key: String) = apply {
                    additionalProperties.remove(key)
                }

                fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                    keys.forEach(::removeAdditionalProperty)
                }

                /**
                 * Returns an immutable instance of [Timestamp].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 */
                fun build(): Timestamp =
                    Timestamp(
                        (indices ?: JsonMissing.of()).map { it.toImmutable() },
                        seconds,
                        tag,
                        text,
                        additionalProperties.toMutableMap(),
                    )
            }

            private var validated: Boolean = false

            /**
             * Validates that the types of all values in this object match their expected types
             * recursively.
             *
             * This method is _not_ forwards compatible with new types from the API for existing
             * fields.
             *
             * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
             *   match its expected type.
             */
            fun validate(): Timestamp = apply {
                if (validated) {
                    return@apply
                }

                indices()
                seconds()
                tag()?.validate()
                text()
                validated = true
            }

            fun isValid(): Boolean =
                try {
                    validate()
                    true
                } catch (e: XTwitterScraperInvalidDataException) {
                    false
                }

            internal fun validity(): Int =
                (indices.asKnown()?.size ?: 0) +
                    (if (seconds.asKnown() == null) 0 else 1) +
                    (tag.asKnown()?.validity() ?: 0) +
                    (if (text.asKnown() == null) 0 else 1)

            /**
             * X's SmartTag for a cashtag, smarttag or hashtag. Xquik leaves out X's trading
             * platforms, which differ by reader.
             */
            class Tag
            @JsonCreator(mode = JsonCreator.Mode.DISABLED)
            private constructor(
                private val id: JsonField<String>,
                private val info: JsonField<Info>,
                private val additionalProperties: MutableMap<String, JsonValue>,
            ) {

                @JsonCreator
                private constructor(
                    @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
                    @JsonProperty("info") @ExcludeMissing info: JsonField<Info> = JsonMissing.of(),
                ) : this(id, info, mutableMapOf())

                /**
                 * X's ID for the SmartTag.
                 *
                 * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected
                 *   type.
                 */
                fun id(): String? = id.getNullable("id")

                /**
                 * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected
                 *   type.
                 */
                fun info(): Info? = info.getNullable("info")

                /**
                 * Returns the raw JSON value of [id].
                 *
                 * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
                 */
                @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

                /**
                 * Returns the raw JSON value of [info].
                 *
                 * Unlike [info], this method doesn't throw if the JSON field has an unexpected
                 * type.
                 */
                @JsonProperty("info") @ExcludeMissing fun _info(): JsonField<Info> = info

                @JsonAnySetter
                private fun putAdditionalProperty(key: String, value: JsonValue) {
                    additionalProperties.put(key, value)
                }

                @JsonAnyGetter
                @ExcludeMissing
                fun _additionalProperties(): Map<String, JsonValue> =
                    Collections.unmodifiableMap(additionalProperties)

                fun toBuilder() = Builder().from(this)

                companion object {

                    /** Returns a mutable builder for constructing an instance of [Tag]. */
                    fun builder() = Builder()
                }

                /** A builder for [Tag]. */
                class Builder internal constructor() {

                    private var id: JsonField<String> = JsonMissing.of()
                    private var info: JsonField<Info> = JsonMissing.of()
                    private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                    internal fun from(tag: Tag) = apply {
                        id = tag.id
                        info = tag.info
                        additionalProperties = tag.additionalProperties.toMutableMap()
                    }

                    /** X's ID for the SmartTag. */
                    fun id(id: String) = id(JsonField.of(id))

                    /** Sets [Builder.id] to an arbitrary JSON value. */
                    fun id(id: JsonField<String>) = apply { this.id = id }

                    fun info(info: Info) = info(JsonField.of(info))

                    /** Sets [Builder.info] to an arbitrary JSON value. */
                    fun info(info: JsonField<Info>) = apply { this.info = info }

                    fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                        this.additionalProperties.clear()
                        putAllAdditionalProperties(additionalProperties)
                    }

                    fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                        additionalProperties.put(key, value)
                    }

                    fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) =
                        apply {
                            this.additionalProperties.putAll(additionalProperties)
                        }

                    fun removeAdditionalProperty(key: String) = apply {
                        additionalProperties.remove(key)
                    }

                    fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                        keys.forEach(::removeAdditionalProperty)
                    }

                    /**
                     * Returns an immutable instance of [Tag].
                     *
                     * Further updates to this [Builder] will not mutate the returned instance.
                     */
                    fun build(): Tag = Tag(id, info, additionalProperties.toMutableMap())
                }

                private var validated: Boolean = false

                /**
                 * Validates that the types of all values in this object match their expected types
                 * recursively.
                 *
                 * This method is _not_ forwards compatible with new types from the API for existing
                 * fields.
                 *
                 * @throws XTwitterScraperInvalidDataException if any value type in this object
                 *   doesn't match its expected type.
                 */
                fun validate(): Tag = apply {
                    if (validated) {
                        return@apply
                    }

                    id()
                    info()?.validate()
                    validated = true
                }

                fun isValid(): Boolean =
                    try {
                        validate()
                        true
                    } catch (e: XTwitterScraperInvalidDataException) {
                        false
                    }

                internal fun validity(): Int =
                    (if (id.asKnown() == null) 0 else 1) + (info.asKnown()?.validity() ?: 0)

                class Info
                @JsonCreator(mode = JsonCreator.Mode.DISABLED)
                private constructor(
                    private val info: JsonField<InnerInfo>,
                    private val additionalProperties: MutableMap<String, JsonValue>,
                ) {

                    @JsonCreator
                    private constructor(
                        @JsonProperty("info")
                        @ExcludeMissing
                        info: JsonField<InnerInfo> = JsonMissing.of()
                    ) : this(info, mutableMapOf())

                    /**
                     * @throws XTwitterScraperInvalidDataException if the JSON field has an
                     *   unexpected type.
                     */
                    fun info(): InnerInfo? = info.getNullable("info")

                    /**
                     * Returns the raw JSON value of [info].
                     *
                     * Unlike [info], this method doesn't throw if the JSON field has an unexpected
                     * type.
                     */
                    @JsonProperty("info") @ExcludeMissing fun _info(): JsonField<InnerInfo> = info

                    @JsonAnySetter
                    private fun putAdditionalProperty(key: String, value: JsonValue) {
                        additionalProperties.put(key, value)
                    }

                    @JsonAnyGetter
                    @ExcludeMissing
                    fun _additionalProperties(): Map<String, JsonValue> =
                        Collections.unmodifiableMap(additionalProperties)

                    fun toBuilder() = Builder().from(this)

                    companion object {

                        /** Returns a mutable builder for constructing an instance of [Info]. */
                        fun builder() = Builder()
                    }

                    /** A builder for [Info]. */
                    class Builder internal constructor() {

                        private var info: JsonField<InnerInfo> = JsonMissing.of()
                        private var additionalProperties: MutableMap<String, JsonValue> =
                            mutableMapOf()

                        internal fun from(info: Info) = apply {
                            this.info = info.info
                            additionalProperties = info.additionalProperties.toMutableMap()
                        }

                        fun info(info: InnerInfo) = info(JsonField.of(info))

                        /** Sets [Builder.info] to an arbitrary JSON value. */
                        fun info(info: JsonField<InnerInfo>) = apply { this.info = info }

                        fun additionalProperties(additionalProperties: Map<String, JsonValue>) =
                            apply {
                                this.additionalProperties.clear()
                                putAllAdditionalProperties(additionalProperties)
                            }

                        fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                            additionalProperties.put(key, value)
                        }

                        fun putAllAdditionalProperties(
                            additionalProperties: Map<String, JsonValue>
                        ) = apply {
                            this.additionalProperties.putAll(additionalProperties)
                        }

                        fun removeAdditionalProperty(key: String) = apply {
                            additionalProperties.remove(key)
                        }

                        fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                            keys.forEach(::removeAdditionalProperty)
                        }

                        /**
                         * Returns an immutable instance of [Info].
                         *
                         * Further updates to this [Builder] will not mutate the returned instance.
                         */
                        fun build(): Info = Info(info, additionalProperties.toMutableMap())
                    }

                    private var validated: Boolean = false

                    /**
                     * Validates that the types of all values in this object match their expected
                     * types recursively.
                     *
                     * This method is _not_ forwards compatible with new types from the API for
                     * existing fields.
                     *
                     * @throws XTwitterScraperInvalidDataException if any value type in this object
                     *   doesn't match its expected type.
                     */
                    fun validate(): Info = apply {
                        if (validated) {
                            return@apply
                        }

                        info()?.validate()
                        validated = true
                    }

                    fun isValid(): Boolean =
                        try {
                            validate()
                            true
                        } catch (e: XTwitterScraperInvalidDataException) {
                            false
                        }

                    internal fun validity(): Int = (info.asKnown()?.validity() ?: 0)

                    class InnerInfo
                    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
                    private constructor(
                        private val active: JsonField<Boolean>,
                        private val name: JsonField<String>,
                        private val ticker: JsonField<String>,
                        private val additionalProperties: MutableMap<String, JsonValue>,
                    ) {

                        @JsonCreator
                        private constructor(
                            @JsonProperty("active")
                            @ExcludeMissing
                            active: JsonField<Boolean> = JsonMissing.of(),
                            @JsonProperty("name")
                            @ExcludeMissing
                            name: JsonField<String> = JsonMissing.of(),
                            @JsonProperty("ticker")
                            @ExcludeMissing
                            ticker: JsonField<String> = JsonMissing.of(),
                        ) : this(active, name, ticker, mutableMapOf())

                        /**
                         * Whether X marks the SmartTag active.
                         *
                         * @throws XTwitterScraperInvalidDataException if the JSON field has an
                         *   unexpected type.
                         */
                        fun active(): Boolean? = active.getNullable("active")

                        /**
                         * @throws XTwitterScraperInvalidDataException if the JSON field has an
                         *   unexpected type.
                         */
                        fun name(): String? = name.getNullable("name")

                        /**
                         * @throws XTwitterScraperInvalidDataException if the JSON field has an
                         *   unexpected type.
                         */
                        fun ticker(): String? = ticker.getNullable("ticker")

                        /**
                         * Returns the raw JSON value of [active].
                         *
                         * Unlike [active], this method doesn't throw if the JSON field has an
                         * unexpected type.
                         */
                        @JsonProperty("active")
                        @ExcludeMissing
                        fun _active(): JsonField<Boolean> = active

                        /**
                         * Returns the raw JSON value of [name].
                         *
                         * Unlike [name], this method doesn't throw if the JSON field has an
                         * unexpected type.
                         */
                        @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<String> = name

                        /**
                         * Returns the raw JSON value of [ticker].
                         *
                         * Unlike [ticker], this method doesn't throw if the JSON field has an
                         * unexpected type.
                         */
                        @JsonProperty("ticker")
                        @ExcludeMissing
                        fun _ticker(): JsonField<String> = ticker

                        @JsonAnySetter
                        private fun putAdditionalProperty(key: String, value: JsonValue) {
                            additionalProperties.put(key, value)
                        }

                        @JsonAnyGetter
                        @ExcludeMissing
                        fun _additionalProperties(): Map<String, JsonValue> =
                            Collections.unmodifiableMap(additionalProperties)

                        fun toBuilder() = Builder().from(this)

                        companion object {

                            /**
                             * Returns a mutable builder for constructing an instance of
                             * [InnerInfo].
                             */
                            fun builder() = Builder()
                        }

                        /** A builder for [InnerInfo]. */
                        class Builder internal constructor() {

                            private var active: JsonField<Boolean> = JsonMissing.of()
                            private var name: JsonField<String> = JsonMissing.of()
                            private var ticker: JsonField<String> = JsonMissing.of()
                            private var additionalProperties: MutableMap<String, JsonValue> =
                                mutableMapOf()

                            internal fun from(innerInfo: InnerInfo) = apply {
                                active = innerInfo.active
                                name = innerInfo.name
                                ticker = innerInfo.ticker
                                additionalProperties = innerInfo.additionalProperties.toMutableMap()
                            }

                            /** Whether X marks the SmartTag active. */
                            fun active(active: Boolean) = active(JsonField.of(active))

                            /** Sets [Builder.active] to an arbitrary JSON value. */
                            fun active(active: JsonField<Boolean>) = apply { this.active = active }

                            fun name(name: String) = name(JsonField.of(name))

                            /** Sets [Builder.name] to an arbitrary JSON value. */
                            fun name(name: JsonField<String>) = apply { this.name = name }

                            fun ticker(ticker: String) = ticker(JsonField.of(ticker))

                            /** Sets [Builder.ticker] to an arbitrary JSON value. */
                            fun ticker(ticker: JsonField<String>) = apply { this.ticker = ticker }

                            fun additionalProperties(additionalProperties: Map<String, JsonValue>) =
                                apply {
                                    this.additionalProperties.clear()
                                    putAllAdditionalProperties(additionalProperties)
                                }

                            fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                                additionalProperties.put(key, value)
                            }

                            fun putAllAdditionalProperties(
                                additionalProperties: Map<String, JsonValue>
                            ) = apply {
                                this.additionalProperties.putAll(additionalProperties)
                            }

                            fun removeAdditionalProperty(key: String) = apply {
                                additionalProperties.remove(key)
                            }

                            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                                keys.forEach(::removeAdditionalProperty)
                            }

                            /**
                             * Returns an immutable instance of [InnerInfo].
                             *
                             * Further updates to this [Builder] will not mutate the returned
                             * instance.
                             */
                            fun build(): InnerInfo =
                                InnerInfo(active, name, ticker, additionalProperties.toMutableMap())
                        }

                        private var validated: Boolean = false

                        /**
                         * Validates that the types of all values in this object match their
                         * expected types recursively.
                         *
                         * This method is _not_ forwards compatible with new types from the API for
                         * existing fields.
                         *
                         * @throws XTwitterScraperInvalidDataException if any value type in this
                         *   object doesn't match its expected type.
                         */
                        fun validate(): InnerInfo = apply {
                            if (validated) {
                                return@apply
                            }

                            active()
                            name()
                            ticker()
                            validated = true
                        }

                        fun isValid(): Boolean =
                            try {
                                validate()
                                true
                            } catch (e: XTwitterScraperInvalidDataException) {
                                false
                            }

                        internal fun validity(): Int =
                            (if (active.asKnown() == null) 0 else 1) +
                                (if (name.asKnown() == null) 0 else 1) +
                                (if (ticker.asKnown() == null) 0 else 1)

                        override fun equals(other: Any?): Boolean {
                            if (this === other) {
                                return true
                            }

                            return other is InnerInfo &&
                                active == other.active &&
                                name == other.name &&
                                ticker == other.ticker &&
                                additionalProperties == other.additionalProperties
                        }

                        private val hashCode: Int by lazy {
                            Objects.hash(active, name, ticker, additionalProperties)
                        }

                        override fun hashCode(): Int = hashCode

                        override fun toString() =
                            "InnerInfo{active=$active, name=$name, ticker=$ticker, additionalProperties=$additionalProperties}"
                    }

                    override fun equals(other: Any?): Boolean {
                        if (this === other) {
                            return true
                        }

                        return other is Info &&
                            info == other.info &&
                            additionalProperties == other.additionalProperties
                    }

                    private val hashCode: Int by lazy {
                        Objects.hash(info, additionalProperties)
                    }

                    override fun hashCode(): Int = hashCode

                    override fun toString() =
                        "Info{info=$info, additionalProperties=$additionalProperties}"
                }

                override fun equals(other: Any?): Boolean {
                    if (this === other) {
                        return true
                    }

                    return other is Tag &&
                        id == other.id &&
                        info == other.info &&
                        additionalProperties == other.additionalProperties
                }

                private val hashCode: Int by lazy {
                    Objects.hash(id, info, additionalProperties)
                }

                override fun hashCode(): Int = hashCode

                override fun toString() =
                    "Tag{id=$id, info=$info, additionalProperties=$additionalProperties}"
            }

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is Timestamp &&
                    indices == other.indices &&
                    seconds == other.seconds &&
                    tag == other.tag &&
                    text == other.text &&
                    additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(indices, seconds, tag, text, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "Timestamp{indices=$indices, seconds=$seconds, tag=$tag, text=$text, additionalProperties=$additionalProperties}"
        }

        /** Shortened, display, and expanded URLs from tweet text. */
        class Url
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val displayUrl: JsonField<String>,
            private val expandedUrl: JsonField<String>,
            private val indices: JsonField<List<Long>>,
            private val url: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("display_url")
                @ExcludeMissing
                displayUrl: JsonField<String> = JsonMissing.of(),
                @JsonProperty("expanded_url")
                @ExcludeMissing
                expandedUrl: JsonField<String> = JsonMissing.of(),
                @JsonProperty("indices")
                @ExcludeMissing
                indices: JsonField<List<Long>> = JsonMissing.of(),
                @JsonProperty("url") @ExcludeMissing url: JsonField<String> = JsonMissing.of(),
            ) : this(displayUrl, expandedUrl, indices, url, mutableMapOf())

            /**
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun displayUrl(): String? = displayUrl.getNullable("display_url")

            /**
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun expandedUrl(): String? = expandedUrl.getNullable("expanded_url")

            /**
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun indices(): List<Long>? = indices.getNullable("indices")

            /**
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun url(): String? = url.getNullable("url")

            /**
             * Returns the raw JSON value of [displayUrl].
             *
             * Unlike [displayUrl], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("display_url")
            @ExcludeMissing
            fun _displayUrl(): JsonField<String> = displayUrl

            /**
             * Returns the raw JSON value of [expandedUrl].
             *
             * Unlike [expandedUrl], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("expanded_url")
            @ExcludeMissing
            fun _expandedUrl(): JsonField<String> = expandedUrl

            /**
             * Returns the raw JSON value of [indices].
             *
             * Unlike [indices], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("indices") @ExcludeMissing fun _indices(): JsonField<List<Long>> = indices

            /**
             * Returns the raw JSON value of [url].
             *
             * Unlike [url], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("url") @ExcludeMissing fun _url(): JsonField<String> = url

            @JsonAnySetter
            private fun putAdditionalProperty(key: String, value: JsonValue) {
                additionalProperties.put(key, value)
            }

            @JsonAnyGetter
            @ExcludeMissing
            fun _additionalProperties(): Map<String, JsonValue> =
                Collections.unmodifiableMap(additionalProperties)

            fun toBuilder() = Builder().from(this)

            companion object {

                /** Returns a mutable builder for constructing an instance of [Url]. */
                fun builder() = Builder()
            }

            /** A builder for [Url]. */
            class Builder internal constructor() {

                private var displayUrl: JsonField<String> = JsonMissing.of()
                private var expandedUrl: JsonField<String> = JsonMissing.of()
                private var indices: JsonField<MutableList<Long>>? = null
                private var url: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                internal fun from(url: Url) = apply {
                    displayUrl = url.displayUrl
                    expandedUrl = url.expandedUrl
                    indices = url.indices.map { it.toMutableList() }
                    this.url = url.url
                    additionalProperties = url.additionalProperties.toMutableMap()
                }

                fun displayUrl(displayUrl: String) = displayUrl(JsonField.of(displayUrl))

                /** Sets [Builder.displayUrl] to an arbitrary JSON value. */
                fun displayUrl(displayUrl: JsonField<String>) = apply {
                    this.displayUrl = displayUrl
                }

                fun expandedUrl(expandedUrl: String) = expandedUrl(JsonField.of(expandedUrl))

                /** Sets [Builder.expandedUrl] to an arbitrary JSON value. */
                fun expandedUrl(expandedUrl: JsonField<String>) = apply {
                    this.expandedUrl = expandedUrl
                }

                fun indices(indices: List<Long>) = indices(JsonField.of(indices))

                /** Sets [Builder.indices] to an arbitrary JSON value. */
                fun indices(indices: JsonField<List<Long>>) = apply {
                    this.indices = indices.map { it.toMutableList() }
                }

                /**
                 * Adds a single [Long] to [indices].
                 *
                 * @throws IllegalStateException if the field was previously set to a non-list.
                 */
                fun addIndex(index: Long) = apply {
                    indices =
                        (indices ?: JsonField.of(mutableListOf())).also {
                            checkKnown("indices", it).add(index)
                        }
                }

                fun url(url: String) = url(JsonField.of(url))

                /** Sets [Builder.url] to an arbitrary JSON value. */
                fun url(url: JsonField<String>) = apply { this.url = url }

                fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                    this.additionalProperties.clear()
                    putAllAdditionalProperties(additionalProperties)
                }

                fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                    additionalProperties.put(key, value)
                }

                fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) =
                    apply {
                        this.additionalProperties.putAll(additionalProperties)
                    }

                fun removeAdditionalProperty(key: String) = apply {
                    additionalProperties.remove(key)
                }

                fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                    keys.forEach(::removeAdditionalProperty)
                }

                /**
                 * Returns an immutable instance of [Url].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 */
                fun build(): Url =
                    Url(
                        displayUrl,
                        expandedUrl,
                        (indices ?: JsonMissing.of()).map { it.toImmutable() },
                        url,
                        additionalProperties.toMutableMap(),
                    )
            }

            private var validated: Boolean = false

            /**
             * Validates that the types of all values in this object match their expected types
             * recursively.
             *
             * This method is _not_ forwards compatible with new types from the API for existing
             * fields.
             *
             * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
             *   match its expected type.
             */
            fun validate(): Url = apply {
                if (validated) {
                    return@apply
                }

                displayUrl()
                expandedUrl()
                indices()
                url()
                validated = true
            }

            fun isValid(): Boolean =
                try {
                    validate()
                    true
                } catch (e: XTwitterScraperInvalidDataException) {
                    false
                }

            internal fun validity(): Int =
                (if (displayUrl.asKnown() == null) 0 else 1) +
                    (if (expandedUrl.asKnown() == null) 0 else 1) +
                    (indices.asKnown()?.size ?: 0) +
                    (if (url.asKnown() == null) 0 else 1)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is Url &&
                    displayUrl == other.displayUrl &&
                    expandedUrl == other.expandedUrl &&
                    indices == other.indices &&
                    url == other.url &&
                    additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(displayUrl, expandedUrl, indices, url, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "Url{displayUrl=$displayUrl, expandedUrl=$expandedUrl, indices=$indices, url=$url, additionalProperties=$additionalProperties}"
        }

        /** Profile identity and source offsets of a mention. */
        class UserMention
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val screenName: JsonField<String>,
            private val idStr: JsonField<String>,
            private val indices: JsonField<List<Long>>,
            private val name: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("screen_name")
                @ExcludeMissing
                screenName: JsonField<String> = JsonMissing.of(),
                @JsonProperty("id_str") @ExcludeMissing idStr: JsonField<String> = JsonMissing.of(),
                @JsonProperty("indices")
                @ExcludeMissing
                indices: JsonField<List<Long>> = JsonMissing.of(),
                @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
            ) : this(screenName, idStr, indices, name, mutableMapOf())

            /**
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type
             *   or is unexpectedly missing or null.
             */
            fun screenName(): String = screenName.getRequired("screen_name")

            /**
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun idStr(): String? = idStr.getNullable("id_str")

            /**
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun indices(): List<Long>? = indices.getNullable("indices")

            /**
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun name(): String? = name.getNullable("name")

            /**
             * Returns the raw JSON value of [screenName].
             *
             * Unlike [screenName], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("screen_name")
            @ExcludeMissing
            fun _screenName(): JsonField<String> = screenName

            /**
             * Returns the raw JSON value of [idStr].
             *
             * Unlike [idStr], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("id_str") @ExcludeMissing fun _idStr(): JsonField<String> = idStr

            /**
             * Returns the raw JSON value of [indices].
             *
             * Unlike [indices], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("indices") @ExcludeMissing fun _indices(): JsonField<List<Long>> = indices

            /**
             * Returns the raw JSON value of [name].
             *
             * Unlike [name], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<String> = name

            @JsonAnySetter
            private fun putAdditionalProperty(key: String, value: JsonValue) {
                additionalProperties.put(key, value)
            }

            @JsonAnyGetter
            @ExcludeMissing
            fun _additionalProperties(): Map<String, JsonValue> =
                Collections.unmodifiableMap(additionalProperties)

            fun toBuilder() = Builder().from(this)

            companion object {

                /**
                 * Returns a mutable builder for constructing an instance of [UserMention].
                 *
                 * The following fields are required:
                 * ```kotlin
                 * .screenName()
                 * ```
                 */
                fun builder() = Builder()
            }

            /** A builder for [UserMention]. */
            class Builder internal constructor() {

                private var screenName: JsonField<String>? = null
                private var idStr: JsonField<String> = JsonMissing.of()
                private var indices: JsonField<MutableList<Long>>? = null
                private var name: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                internal fun from(userMention: UserMention) = apply {
                    screenName = userMention.screenName
                    idStr = userMention.idStr
                    indices = userMention.indices.map { it.toMutableList() }
                    name = userMention.name
                    additionalProperties = userMention.additionalProperties.toMutableMap()
                }

                fun screenName(screenName: String) = screenName(JsonField.of(screenName))

                /** Sets [Builder.screenName] to an arbitrary JSON value. */
                fun screenName(screenName: JsonField<String>) = apply {
                    this.screenName = screenName
                }

                fun idStr(idStr: String) = idStr(JsonField.of(idStr))

                /** Sets [Builder.idStr] to an arbitrary JSON value. */
                fun idStr(idStr: JsonField<String>) = apply { this.idStr = idStr }

                fun indices(indices: List<Long>) = indices(JsonField.of(indices))

                /** Sets [Builder.indices] to an arbitrary JSON value. */
                fun indices(indices: JsonField<List<Long>>) = apply {
                    this.indices = indices.map { it.toMutableList() }
                }

                /**
                 * Adds a single [Long] to [indices].
                 *
                 * @throws IllegalStateException if the field was previously set to a non-list.
                 */
                fun addIndex(index: Long) = apply {
                    indices =
                        (indices ?: JsonField.of(mutableListOf())).also {
                            checkKnown("indices", it).add(index)
                        }
                }

                fun name(name: String) = name(JsonField.of(name))

                /** Sets [Builder.name] to an arbitrary JSON value. */
                fun name(name: JsonField<String>) = apply { this.name = name }

                fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                    this.additionalProperties.clear()
                    putAllAdditionalProperties(additionalProperties)
                }

                fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                    additionalProperties.put(key, value)
                }

                fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) =
                    apply {
                        this.additionalProperties.putAll(additionalProperties)
                    }

                fun removeAdditionalProperty(key: String) = apply {
                    additionalProperties.remove(key)
                }

                fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                    keys.forEach(::removeAdditionalProperty)
                }

                /**
                 * Returns an immutable instance of [UserMention].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 *
                 * The following fields are required:
                 * ```kotlin
                 * .screenName()
                 * ```
                 *
                 * @throws IllegalStateException if any required field is unset.
                 */
                fun build(): UserMention =
                    UserMention(
                        checkRequired("screenName", screenName),
                        idStr,
                        (indices ?: JsonMissing.of()).map { it.toImmutable() },
                        name,
                        additionalProperties.toMutableMap(),
                    )
            }

            private var validated: Boolean = false

            /**
             * Validates that the types of all values in this object match their expected types
             * recursively.
             *
             * This method is _not_ forwards compatible with new types from the API for existing
             * fields.
             *
             * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
             *   match its expected type.
             */
            fun validate(): UserMention = apply {
                if (validated) {
                    return@apply
                }

                screenName()
                idStr()
                indices()
                name()
                validated = true
            }

            fun isValid(): Boolean =
                try {
                    validate()
                    true
                } catch (e: XTwitterScraperInvalidDataException) {
                    false
                }

            internal fun validity(): Int =
                (if (screenName.asKnown() == null) 0 else 1) +
                    (if (idStr.asKnown() == null) 0 else 1) +
                    (indices.asKnown()?.size ?: 0) +
                    (if (name.asKnown() == null) 0 else 1)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is UserMention &&
                    screenName == other.screenName &&
                    idStr == other.idStr &&
                    indices == other.indices &&
                    name == other.name &&
                    additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(screenName, idStr, indices, name, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "UserMention{screenName=$screenName, idStr=$idStr, indices=$indices, name=$name, additionalProperties=$additionalProperties}"
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Entities &&
                hashtags == other.hashtags &&
                smarttags == other.smarttags &&
                symbols == other.symbols &&
                timestamps == other.timestamps &&
                urls == other.urls &&
                userMentions == other.userMentions &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(
                hashtags,
                smarttags,
                symbols,
                timestamps,
                urls,
                userMentions,
                additionalProperties,
            )
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Entities{hashtags=$hashtags, smarttags=$smarttags, symbols=$symbols, timestamps=$timestamps, urls=$urls, userMentions=$userMentions, additionalProperties=$additionalProperties}"
    }

    /**
     * Marks a subscriber-only post with the creator whose subscription unlocks it. Non-subscribers
     * get the preview text & the subscribe link. Omitted on public posts.
     */
    class ExclusiveContent
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val creatorUserName: JsonField<String>,
        private val subscribeUrl: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("creatorUserName")
            @ExcludeMissing
            creatorUserName: JsonField<String> = JsonMissing.of(),
            @JsonProperty("subscribeUrl")
            @ExcludeMissing
            subscribeUrl: JsonField<String> = JsonMissing.of(),
        ) : this(creatorUserName, subscribeUrl, mutableMapOf())

        /**
         * Username of the creator whose subscription unlocks the post.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or
         *   is unexpectedly missing or null.
         */
        fun creatorUserName(): String = creatorUserName.getRequired("creatorUserName")

        /**
         * Subscribe link X shows non-subscribers. Omitted when X serves the full post.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun subscribeUrl(): String? = subscribeUrl.getNullable("subscribeUrl")

        /**
         * Returns the raw JSON value of [creatorUserName].
         *
         * Unlike [creatorUserName], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("creatorUserName")
        @ExcludeMissing
        fun _creatorUserName(): JsonField<String> = creatorUserName

        /**
         * Returns the raw JSON value of [subscribeUrl].
         *
         * Unlike [subscribeUrl], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("subscribeUrl")
        @ExcludeMissing
        fun _subscribeUrl(): JsonField<String> = subscribeUrl

        @JsonAnySetter
        private fun putAdditionalProperty(key: String, value: JsonValue) {
            additionalProperties.put(key, value)
        }

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> =
            Collections.unmodifiableMap(additionalProperties)

        fun toBuilder() = Builder().from(this)

        companion object {

            /**
             * Returns a mutable builder for constructing an instance of [ExclusiveContent].
             *
             * The following fields are required:
             * ```kotlin
             * .creatorUserName()
             * ```
             */
            fun builder() = Builder()
        }

        /** A builder for [ExclusiveContent]. */
        class Builder internal constructor() {

            private var creatorUserName: JsonField<String>? = null
            private var subscribeUrl: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(exclusiveContent: ExclusiveContent) = apply {
                creatorUserName = exclusiveContent.creatorUserName
                subscribeUrl = exclusiveContent.subscribeUrl
                additionalProperties = exclusiveContent.additionalProperties.toMutableMap()
            }

            /** Username of the creator whose subscription unlocks the post. */
            fun creatorUserName(creatorUserName: String) =
                creatorUserName(JsonField.of(creatorUserName))

            /** Sets [Builder.creatorUserName] to an arbitrary JSON value. */
            fun creatorUserName(creatorUserName: JsonField<String>) = apply {
                this.creatorUserName = creatorUserName
            }

            /** Subscribe link X shows non-subscribers. Omitted when X serves the full post. */
            fun subscribeUrl(subscribeUrl: String) = subscribeUrl(JsonField.of(subscribeUrl))

            /** Sets [Builder.subscribeUrl] to an arbitrary JSON value. */
            fun subscribeUrl(subscribeUrl: JsonField<String>) = apply {
                this.subscribeUrl = subscribeUrl
            }

            fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.clear()
                putAllAdditionalProperties(additionalProperties)
            }

            fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                additionalProperties.put(key, value)
            }

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            /**
             * Returns an immutable instance of [ExclusiveContent].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             *
             * The following fields are required:
             * ```kotlin
             * .creatorUserName()
             * ```
             *
             * @throws IllegalStateException if any required field is unset.
             */
            fun build(): ExclusiveContent =
                ExclusiveContent(
                    checkRequired("creatorUserName", creatorUserName),
                    subscribeUrl,
                    additionalProperties.toMutableMap(),
                )
        }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
         *   match its expected type.
         */
        fun validate(): ExclusiveContent = apply {
            if (validated) {
                return@apply
            }

            creatorUserName()
            subscribeUrl()
            validated = true
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: XTwitterScraperInvalidDataException) {
                false
            }

        internal fun validity(): Int =
            (if (creatorUserName.asKnown() == null) 0 else 1) +
                (if (subscribeUrl.asKnown() == null) 0 else 1)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is ExclusiveContent &&
                creatorUserName == other.creatorUserName &&
                subscribeUrl == other.subscribeUrl &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(creatorUserName, subscribeUrl, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "ExclusiveContent{creatorUserName=$creatorUserName, subscribeUrl=$subscribeUrl, additionalProperties=$additionalProperties}"
    }

    /** Author-published geographic point with 2 numeric coordinates. */
    class Geo
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val coordinates: JsonField<List<Double>>,
        private val type: JsonField<Type>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("coordinates")
            @ExcludeMissing
            coordinates: JsonField<List<Double>> = JsonMissing.of(),
            @JsonProperty("type") @ExcludeMissing type: JsonField<Type> = JsonMissing.of(),
        ) : this(coordinates, type, mutableMapOf())

        /**
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or
         *   is unexpectedly missing or null.
         */
        fun coordinates(): List<Double> = coordinates.getRequired("coordinates")

        /**
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or
         *   is unexpectedly missing or null.
         */
        fun type(): Type = type.getRequired("type")

        /**
         * Returns the raw JSON value of [coordinates].
         *
         * Unlike [coordinates], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("coordinates")
        @ExcludeMissing
        fun _coordinates(): JsonField<List<Double>> = coordinates

        /**
         * Returns the raw JSON value of [type].
         *
         * Unlike [type], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<Type> = type

        @JsonAnySetter
        private fun putAdditionalProperty(key: String, value: JsonValue) {
            additionalProperties.put(key, value)
        }

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> =
            Collections.unmodifiableMap(additionalProperties)

        fun toBuilder() = Builder().from(this)

        companion object {

            /**
             * Returns a mutable builder for constructing an instance of [Geo].
             *
             * The following fields are required:
             * ```kotlin
             * .coordinates()
             * .type()
             * ```
             */
            fun builder() = Builder()
        }

        /** A builder for [Geo]. */
        class Builder internal constructor() {

            private var coordinates: JsonField<MutableList<Double>>? = null
            private var type: JsonField<Type>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(geo: Geo) = apply {
                coordinates = geo.coordinates.map { it.toMutableList() }
                type = geo.type
                additionalProperties = geo.additionalProperties.toMutableMap()
            }

            fun coordinates(coordinates: List<Double>) = coordinates(JsonField.of(coordinates))

            /** Sets [Builder.coordinates] to an arbitrary JSON value. */
            fun coordinates(coordinates: JsonField<List<Double>>) = apply {
                this.coordinates = coordinates.map { it.toMutableList() }
            }

            /**
             * Adds a single [Double] to [coordinates].
             *
             * @throws IllegalStateException if the field was previously set to a non-list.
             */
            fun addCoordinate(coordinate: Double) = apply {
                coordinates =
                    (coordinates ?: JsonField.of(mutableListOf())).also {
                        checkKnown("coordinates", it).add(coordinate)
                    }
            }

            fun type(type: Type) = type(JsonField.of(type))

            /** Sets [Builder.type] to an arbitrary JSON value. */
            fun type(type: JsonField<Type>) = apply { this.type = type }

            fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.clear()
                putAllAdditionalProperties(additionalProperties)
            }

            fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                additionalProperties.put(key, value)
            }

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            /**
             * Returns an immutable instance of [Geo].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             *
             * The following fields are required:
             * ```kotlin
             * .coordinates()
             * .type()
             * ```
             *
             * @throws IllegalStateException if any required field is unset.
             */
            fun build(): Geo =
                Geo(
                    checkRequired("coordinates", coordinates).map { it.toImmutable() },
                    checkRequired("type", type),
                    additionalProperties.toMutableMap(),
                )
        }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
         *   match its expected type.
         */
        fun validate(): Geo = apply {
            if (validated) {
                return@apply
            }

            coordinates()
            type().validate()
            validated = true
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: XTwitterScraperInvalidDataException) {
                false
            }

        internal fun validity(): Int =
            (coordinates.asKnown()?.size ?: 0) + (type.asKnown()?.validity() ?: 0)

        class Type @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

            /** Returns this class instance's raw value. */
            @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

            companion object {

                val POINT = of("Point")

                fun of(value: String) = Type(JsonField.of(value))
            }

            /** An enum containing [Type]'s known values. */
            enum class Known {
                POINT
            }

            /**
             * An enum containing [Type]'s known values, as well as an [_UNKNOWN] member.
             *
             * An instance of [Type] can contain an unknown value in a couple of cases:
             * - It was deserialized from data that doesn't match any known member. For example, if
             *   the SDK is on an older version than the API, then the API may respond with new
             *   members that the SDK is unaware of.
             * - It was constructed with an arbitrary value using the [of] method.
             */
            enum class Value {
                POINT,
                /** An enum member indicating that [Type] was instantiated with an unknown value. */
                _UNKNOWN,
            }

            /**
             * Returns an enum member corresponding to this class instance's value, or
             * [Value._UNKNOWN] if the class was instantiated with an unknown value.
             */
            fun value(): Value =
                when (this) {
                    POINT -> Value.POINT
                    else -> Value._UNKNOWN
                }

            /**
             * Returns an enum member corresponding to this class instance's value.
             *
             * @throws XTwitterScraperInvalidDataException if this class instance's value is a not a
             *   known member.
             */
            fun known(): Known =
                when (this) {
                    POINT -> Known.POINT
                    else -> throw XTwitterScraperInvalidDataException("Unknown Type: $value")
                }

            /**
             * Returns this class instance's primitive wire representation.
             *
             * @throws XTwitterScraperInvalidDataException if this class instance's value does not
             *   have the expected primitive type.
             */
            fun asString(): String =
                _value().asString()
                    ?: throw XTwitterScraperInvalidDataException("Value is not a String")

            private var validated: Boolean = false

            /**
             * Validates that the types of all values in this object match their expected types
             * recursively.
             *
             * This method is _not_ forwards compatible with new types from the API for existing
             * fields.
             *
             * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
             *   match its expected type.
             */
            fun validate(): Type = apply {
                if (validated) {
                    return@apply
                }

                known()
                validated = true
            }

            fun isValid(): Boolean =
                try {
                    validate()
                    true
                } catch (e: XTwitterScraperInvalidDataException) {
                    false
                }

            internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is Type && value == other.value
            }

            override fun hashCode() = value.hashCode()

            override fun toString() = value.toString()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Geo &&
                coordinates == other.coordinates &&
                type == other.type &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(coordinates, type, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Geo{coordinates=$coordinates, type=$type, additionalProperties=$additionalProperties}"
    }

    /** Public metadata whose fields X defines. */
    class GrokShareAttachment
    @JsonCreator
    private constructor(
        @com.fasterxml.jackson.annotation.JsonValue
        private val additionalProperties: Map<String, JsonValue>
    ) {

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

        fun toBuilder() = Builder().from(this)

        companion object {

            /** Returns a mutable builder for constructing an instance of [GrokShareAttachment]. */
            fun builder() = Builder()
        }

        /** A builder for [GrokShareAttachment]. */
        class Builder internal constructor() {

            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(grokShareAttachment: GrokShareAttachment) = apply {
                additionalProperties = grokShareAttachment.additionalProperties.toMutableMap()
            }

            fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.clear()
                putAllAdditionalProperties(additionalProperties)
            }

            fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                additionalProperties.put(key, value)
            }

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            /**
             * Returns an immutable instance of [GrokShareAttachment].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): GrokShareAttachment =
                GrokShareAttachment(additionalProperties.toImmutable())
        }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
         *   match its expected type.
         */
        fun validate(): GrokShareAttachment = apply {
            if (validated) {
                return@apply
            }

            validated = true
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: XTwitterScraperInvalidDataException) {
                false
            }

        internal fun validity(): Int = additionalProperties.count { (_, value) ->
            !value.isNull() && !value.isMissing()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is GrokShareAttachment &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() = "GrokShareAttachment{additionalProperties=$additionalProperties}"
    }

    /** Public metadata whose fields X defines. */
    class GrokTranslatedPost
    @JsonCreator
    private constructor(
        @com.fasterxml.jackson.annotation.JsonValue
        private val additionalProperties: Map<String, JsonValue>
    ) {

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

        fun toBuilder() = Builder().from(this)

        companion object {

            /** Returns a mutable builder for constructing an instance of [GrokTranslatedPost]. */
            fun builder() = Builder()
        }

        /** A builder for [GrokTranslatedPost]. */
        class Builder internal constructor() {

            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(grokTranslatedPost: GrokTranslatedPost) = apply {
                additionalProperties = grokTranslatedPost.additionalProperties.toMutableMap()
            }

            fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.clear()
                putAllAdditionalProperties(additionalProperties)
            }

            fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                additionalProperties.put(key, value)
            }

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            /**
             * Returns an immutable instance of [GrokTranslatedPost].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): GrokTranslatedPost = GrokTranslatedPost(additionalProperties.toImmutable())
        }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
         *   match its expected type.
         */
        fun validate(): GrokTranslatedPost = apply {
            if (validated) {
                return@apply
            }

            validated = true
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: XTwitterScraperInvalidDataException) {
                false
            }

        internal fun validity(): Int = additionalProperties.count { (_, value) ->
            !value.isNull() && !value.isMissing()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is GrokTranslatedPost && additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() = "GrokTranslatedPost{additionalProperties=$additionalProperties}"
    }

    /**
     * The notice x.com covers the post with until the reader views it, such as X's public interest
     * notice on a post that broke its rules but stays up.
     */
    class Interstitial
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val displayType: JsonField<String>,
        private val text: JsonField<Text>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("displayType")
            @ExcludeMissing
            displayType: JsonField<String> = JsonMissing.of(),
            @JsonProperty("text") @ExcludeMissing text: JsonField<Text> = JsonMissing.of(),
        ) : this(displayType, text, mutableMapOf())

        /**
         * How x.com shows the notice. `NonCompliant` marks a post that broke X's rules.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun displayType(): String? = displayType.getNullable("displayType")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun text(): Text? = text.getNullable("text")

        /**
         * Returns the raw JSON value of [displayType].
         *
         * Unlike [displayType], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("displayType")
        @ExcludeMissing
        fun _displayType(): JsonField<String> = displayType

        /**
         * Returns the raw JSON value of [text].
         *
         * Unlike [text], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("text") @ExcludeMissing fun _text(): JsonField<Text> = text

        @JsonAnySetter
        private fun putAdditionalProperty(key: String, value: JsonValue) {
            additionalProperties.put(key, value)
        }

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> =
            Collections.unmodifiableMap(additionalProperties)

        fun toBuilder() = Builder().from(this)

        companion object {

            /** Returns a mutable builder for constructing an instance of [Interstitial]. */
            fun builder() = Builder()
        }

        /** A builder for [Interstitial]. */
        class Builder internal constructor() {

            private var displayType: JsonField<String> = JsonMissing.of()
            private var text: JsonField<Text> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(interstitial: Interstitial) = apply {
                displayType = interstitial.displayType
                text = interstitial.text
                additionalProperties = interstitial.additionalProperties.toMutableMap()
            }

            /** How x.com shows the notice. `NonCompliant` marks a post that broke X's rules. */
            fun displayType(displayType: String) = displayType(JsonField.of(displayType))

            /** Sets [Builder.displayType] to an arbitrary JSON value. */
            fun displayType(displayType: JsonField<String>) = apply {
                this.displayType = displayType
            }

            fun text(text: Text) = text(JsonField.of(text))

            /** Sets [Builder.text] to an arbitrary JSON value. */
            fun text(text: JsonField<Text>) = apply { this.text = text }

            fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.clear()
                putAllAdditionalProperties(additionalProperties)
            }

            fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                additionalProperties.put(key, value)
            }

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            /**
             * Returns an immutable instance of [Interstitial].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): Interstitial =
                Interstitial(displayType, text, additionalProperties.toMutableMap())
        }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
         *   match its expected type.
         */
        fun validate(): Interstitial = apply {
            if (validated) {
                return@apply
            }

            displayType()
            text()?.validate()
            validated = true
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: XTwitterScraperInvalidDataException) {
                false
            }

        internal fun validity(): Int =
            (if (displayType.asKnown() == null) 0 else 1) + (text.asKnown()?.validity() ?: 0)

        class Text
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val entities: JsonField<List<Entity>>,
            private val rtl: JsonField<Boolean>,
            private val text: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("entities")
                @ExcludeMissing
                entities: JsonField<List<Entity>> = JsonMissing.of(),
                @JsonProperty("rtl") @ExcludeMissing rtl: JsonField<Boolean> = JsonMissing.of(),
                @JsonProperty("text") @ExcludeMissing text: JsonField<String> = JsonMissing.of(),
            ) : this(entities, rtl, text, mutableMapOf())

            /**
             * Links inside the notice, by character range.
             *
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun entities(): List<Entity>? = entities.getNullable("entities")

            /**
             * Whether X writes it right to left.
             *
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun rtl(): Boolean? = rtl.getNullable("rtl")

            /**
             * The notice's text.
             *
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun text(): String? = text.getNullable("text")

            /**
             * Returns the raw JSON value of [entities].
             *
             * Unlike [entities], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("entities")
            @ExcludeMissing
            fun _entities(): JsonField<List<Entity>> = entities

            /**
             * Returns the raw JSON value of [rtl].
             *
             * Unlike [rtl], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("rtl") @ExcludeMissing fun _rtl(): JsonField<Boolean> = rtl

            /**
             * Returns the raw JSON value of [text].
             *
             * Unlike [text], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("text") @ExcludeMissing fun _text(): JsonField<String> = text

            @JsonAnySetter
            private fun putAdditionalProperty(key: String, value: JsonValue) {
                additionalProperties.put(key, value)
            }

            @JsonAnyGetter
            @ExcludeMissing
            fun _additionalProperties(): Map<String, JsonValue> =
                Collections.unmodifiableMap(additionalProperties)

            fun toBuilder() = Builder().from(this)

            companion object {

                /** Returns a mutable builder for constructing an instance of [Text]. */
                fun builder() = Builder()
            }

            /** A builder for [Text]. */
            class Builder internal constructor() {

                private var entities: JsonField<MutableList<Entity>>? = null
                private var rtl: JsonField<Boolean> = JsonMissing.of()
                private var text: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                internal fun from(text: Text) = apply {
                    entities = text.entities.map { it.toMutableList() }
                    rtl = text.rtl
                    this.text = text.text
                    additionalProperties = text.additionalProperties.toMutableMap()
                }

                /** Links inside the notice, by character range. */
                fun entities(entities: List<Entity>) = entities(JsonField.of(entities))

                /** Sets [Builder.entities] to an arbitrary JSON value. */
                fun entities(entities: JsonField<List<Entity>>) = apply {
                    this.entities = entities.map { it.toMutableList() }
                }

                /**
                 * Adds a single [Entity] to [entities].
                 *
                 * @throws IllegalStateException if the field was previously set to a non-list.
                 */
                fun addEntity(entity: Entity) = apply {
                    entities =
                        (entities ?: JsonField.of(mutableListOf())).also {
                            checkKnown("entities", it).add(entity)
                        }
                }

                /** Whether X writes it right to left. */
                fun rtl(rtl: Boolean) = rtl(JsonField.of(rtl))

                /** Sets [Builder.rtl] to an arbitrary JSON value. */
                fun rtl(rtl: JsonField<Boolean>) = apply { this.rtl = rtl }

                /** The notice's text. */
                fun text(text: String) = text(JsonField.of(text))

                /** Sets [Builder.text] to an arbitrary JSON value. */
                fun text(text: JsonField<String>) = apply { this.text = text }

                fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                    this.additionalProperties.clear()
                    putAllAdditionalProperties(additionalProperties)
                }

                fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                    additionalProperties.put(key, value)
                }

                fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) =
                    apply {
                        this.additionalProperties.putAll(additionalProperties)
                    }

                fun removeAdditionalProperty(key: String) = apply {
                    additionalProperties.remove(key)
                }

                fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                    keys.forEach(::removeAdditionalProperty)
                }

                /**
                 * Returns an immutable instance of [Text].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 */
                fun build(): Text =
                    Text(
                        (entities ?: JsonMissing.of()).map { it.toImmutable() },
                        rtl,
                        text,
                        additionalProperties.toMutableMap(),
                    )
            }

            private var validated: Boolean = false

            /**
             * Validates that the types of all values in this object match their expected types
             * recursively.
             *
             * This method is _not_ forwards compatible with new types from the API for existing
             * fields.
             *
             * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
             *   match its expected type.
             */
            fun validate(): Text = apply {
                if (validated) {
                    return@apply
                }

                entities()?.forEach { it.validate() }
                rtl()
                text()
                validated = true
            }

            fun isValid(): Boolean =
                try {
                    validate()
                    true
                } catch (e: XTwitterScraperInvalidDataException) {
                    false
                }

            internal fun validity(): Int =
                (entities.asKnown()?.sumOf { it.validity() } ?: 0) +
                    (if (rtl.asKnown() == null) 0 else 1) +
                    (if (text.asKnown() == null) 0 else 1)

            class Entity
            @JsonCreator(mode = JsonCreator.Mode.DISABLED)
            private constructor(
                private val fromIndex: JsonField<Long>,
                private val ref: JsonField<Ref>,
                private val toIndex: JsonField<Long>,
                private val additionalProperties: MutableMap<String, JsonValue>,
            ) {

                @JsonCreator
                private constructor(
                    @JsonProperty("fromIndex")
                    @ExcludeMissing
                    fromIndex: JsonField<Long> = JsonMissing.of(),
                    @JsonProperty("ref") @ExcludeMissing ref: JsonField<Ref> = JsonMissing.of(),
                    @JsonProperty("toIndex")
                    @ExcludeMissing
                    toIndex: JsonField<Long> = JsonMissing.of(),
                ) : this(fromIndex, ref, toIndex, mutableMapOf())

                /**
                 * Whole number from 0.
                 *
                 * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected
                 *   type.
                 */
                fun fromIndex(): Long? = fromIndex.getNullable("fromIndex")

                /**
                 * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected
                 *   type.
                 */
                fun ref(): Ref? = ref.getNullable("ref")

                /**
                 * Whole number from 0.
                 *
                 * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected
                 *   type.
                 */
                fun toIndex(): Long? = toIndex.getNullable("toIndex")

                /**
                 * Returns the raw JSON value of [fromIndex].
                 *
                 * Unlike [fromIndex], this method doesn't throw if the JSON field has an unexpected
                 * type.
                 */
                @JsonProperty("fromIndex")
                @ExcludeMissing
                fun _fromIndex(): JsonField<Long> = fromIndex

                /**
                 * Returns the raw JSON value of [ref].
                 *
                 * Unlike [ref], this method doesn't throw if the JSON field has an unexpected type.
                 */
                @JsonProperty("ref") @ExcludeMissing fun _ref(): JsonField<Ref> = ref

                /**
                 * Returns the raw JSON value of [toIndex].
                 *
                 * Unlike [toIndex], this method doesn't throw if the JSON field has an unexpected
                 * type.
                 */
                @JsonProperty("toIndex") @ExcludeMissing fun _toIndex(): JsonField<Long> = toIndex

                @JsonAnySetter
                private fun putAdditionalProperty(key: String, value: JsonValue) {
                    additionalProperties.put(key, value)
                }

                @JsonAnyGetter
                @ExcludeMissing
                fun _additionalProperties(): Map<String, JsonValue> =
                    Collections.unmodifiableMap(additionalProperties)

                fun toBuilder() = Builder().from(this)

                companion object {

                    /** Returns a mutable builder for constructing an instance of [Entity]. */
                    fun builder() = Builder()
                }

                /** A builder for [Entity]. */
                class Builder internal constructor() {

                    private var fromIndex: JsonField<Long> = JsonMissing.of()
                    private var ref: JsonField<Ref> = JsonMissing.of()
                    private var toIndex: JsonField<Long> = JsonMissing.of()
                    private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                    internal fun from(entity: Entity) = apply {
                        fromIndex = entity.fromIndex
                        ref = entity.ref
                        toIndex = entity.toIndex
                        additionalProperties = entity.additionalProperties.toMutableMap()
                    }

                    /** Whole number from 0. */
                    fun fromIndex(fromIndex: Long) = fromIndex(JsonField.of(fromIndex))

                    /** Sets [Builder.fromIndex] to an arbitrary JSON value. */
                    fun fromIndex(fromIndex: JsonField<Long>) = apply { this.fromIndex = fromIndex }

                    fun ref(ref: Ref) = ref(JsonField.of(ref))

                    /** Sets [Builder.ref] to an arbitrary JSON value. */
                    fun ref(ref: JsonField<Ref>) = apply { this.ref = ref }

                    /** Whole number from 0. */
                    fun toIndex(toIndex: Long) = toIndex(JsonField.of(toIndex))

                    /** Sets [Builder.toIndex] to an arbitrary JSON value. */
                    fun toIndex(toIndex: JsonField<Long>) = apply { this.toIndex = toIndex }

                    fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                        this.additionalProperties.clear()
                        putAllAdditionalProperties(additionalProperties)
                    }

                    fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                        additionalProperties.put(key, value)
                    }

                    fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) =
                        apply {
                            this.additionalProperties.putAll(additionalProperties)
                        }

                    fun removeAdditionalProperty(key: String) = apply {
                        additionalProperties.remove(key)
                    }

                    fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                        keys.forEach(::removeAdditionalProperty)
                    }

                    /**
                     * Returns an immutable instance of [Entity].
                     *
                     * Further updates to this [Builder] will not mutate the returned instance.
                     */
                    fun build(): Entity =
                        Entity(fromIndex, ref, toIndex, additionalProperties.toMutableMap())
                }

                private var validated: Boolean = false

                /**
                 * Validates that the types of all values in this object match their expected types
                 * recursively.
                 *
                 * This method is _not_ forwards compatible with new types from the API for existing
                 * fields.
                 *
                 * @throws XTwitterScraperInvalidDataException if any value type in this object
                 *   doesn't match its expected type.
                 */
                fun validate(): Entity = apply {
                    if (validated) {
                        return@apply
                    }

                    fromIndex()
                    ref()?.validate()
                    toIndex()
                    validated = true
                }

                fun isValid(): Boolean =
                    try {
                        validate()
                        true
                    } catch (e: XTwitterScraperInvalidDataException) {
                        false
                    }

                internal fun validity(): Int =
                    (if (fromIndex.asKnown() == null) 0 else 1) +
                        (ref.asKnown()?.validity() ?: 0) +
                        (if (toIndex.asKnown() == null) 0 else 1)

                class Ref
                @JsonCreator(mode = JsonCreator.Mode.DISABLED)
                private constructor(
                    private val screenName: JsonField<String>,
                    private val text: JsonField<String>,
                    private val type: JsonField<String>,
                    private val url: JsonField<String>,
                    private val urlType: JsonField<String>,
                    private val additionalProperties: MutableMap<String, JsonValue>,
                ) {

                    @JsonCreator
                    private constructor(
                        @JsonProperty("screenName")
                        @ExcludeMissing
                        screenName: JsonField<String> = JsonMissing.of(),
                        @JsonProperty("text")
                        @ExcludeMissing
                        text: JsonField<String> = JsonMissing.of(),
                        @JsonProperty("type")
                        @ExcludeMissing
                        type: JsonField<String> = JsonMissing.of(),
                        @JsonProperty("url")
                        @ExcludeMissing
                        url: JsonField<String> = JsonMissing.of(),
                        @JsonProperty("urlType")
                        @ExcludeMissing
                        urlType: JsonField<String> = JsonMissing.of(),
                    ) : this(screenName, text, type, url, urlType, mutableMapOf())

                    /**
                     * @throws XTwitterScraperInvalidDataException if the JSON field has an
                     *   unexpected type.
                     */
                    fun screenName(): String? = screenName.getNullable("screenName")

                    /**
                     * @throws XTwitterScraperInvalidDataException if the JSON field has an
                     *   unexpected type.
                     */
                    fun text(): String? = text.getNullable("text")

                    /**
                     * @throws XTwitterScraperInvalidDataException if the JSON field has an
                     *   unexpected type.
                     */
                    fun type(): String? = type.getNullable("type")

                    /**
                     * @throws XTwitterScraperInvalidDataException if the JSON field has an
                     *   unexpected type.
                     */
                    fun url(): String? = url.getNullable("url")

                    /**
                     * @throws XTwitterScraperInvalidDataException if the JSON field has an
                     *   unexpected type.
                     */
                    fun urlType(): String? = urlType.getNullable("urlType")

                    /**
                     * Returns the raw JSON value of [screenName].
                     *
                     * Unlike [screenName], this method doesn't throw if the JSON field has an
                     * unexpected type.
                     */
                    @JsonProperty("screenName")
                    @ExcludeMissing
                    fun _screenName(): JsonField<String> = screenName

                    /**
                     * Returns the raw JSON value of [text].
                     *
                     * Unlike [text], this method doesn't throw if the JSON field has an unexpected
                     * type.
                     */
                    @JsonProperty("text") @ExcludeMissing fun _text(): JsonField<String> = text

                    /**
                     * Returns the raw JSON value of [type].
                     *
                     * Unlike [type], this method doesn't throw if the JSON field has an unexpected
                     * type.
                     */
                    @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<String> = type

                    /**
                     * Returns the raw JSON value of [url].
                     *
                     * Unlike [url], this method doesn't throw if the JSON field has an unexpected
                     * type.
                     */
                    @JsonProperty("url") @ExcludeMissing fun _url(): JsonField<String> = url

                    /**
                     * Returns the raw JSON value of [urlType].
                     *
                     * Unlike [urlType], this method doesn't throw if the JSON field has an
                     * unexpected type.
                     */
                    @JsonProperty("urlType")
                    @ExcludeMissing
                    fun _urlType(): JsonField<String> = urlType

                    @JsonAnySetter
                    private fun putAdditionalProperty(key: String, value: JsonValue) {
                        additionalProperties.put(key, value)
                    }

                    @JsonAnyGetter
                    @ExcludeMissing
                    fun _additionalProperties(): Map<String, JsonValue> =
                        Collections.unmodifiableMap(additionalProperties)

                    fun toBuilder() = Builder().from(this)

                    companion object {

                        /** Returns a mutable builder for constructing an instance of [Ref]. */
                        fun builder() = Builder()
                    }

                    /** A builder for [Ref]. */
                    class Builder internal constructor() {

                        private var screenName: JsonField<String> = JsonMissing.of()
                        private var text: JsonField<String> = JsonMissing.of()
                        private var type: JsonField<String> = JsonMissing.of()
                        private var url: JsonField<String> = JsonMissing.of()
                        private var urlType: JsonField<String> = JsonMissing.of()
                        private var additionalProperties: MutableMap<String, JsonValue> =
                            mutableMapOf()

                        internal fun from(ref: Ref) = apply {
                            screenName = ref.screenName
                            text = ref.text
                            type = ref.type
                            url = ref.url
                            urlType = ref.urlType
                            additionalProperties = ref.additionalProperties.toMutableMap()
                        }

                        fun screenName(screenName: String) = screenName(JsonField.of(screenName))

                        /** Sets [Builder.screenName] to an arbitrary JSON value. */
                        fun screenName(screenName: JsonField<String>) = apply {
                            this.screenName = screenName
                        }

                        fun text(text: String) = text(JsonField.of(text))

                        /** Sets [Builder.text] to an arbitrary JSON value. */
                        fun text(text: JsonField<String>) = apply { this.text = text }

                        fun type(type: String) = type(JsonField.of(type))

                        /** Sets [Builder.type] to an arbitrary JSON value. */
                        fun type(type: JsonField<String>) = apply { this.type = type }

                        fun url(url: String) = url(JsonField.of(url))

                        /** Sets [Builder.url] to an arbitrary JSON value. */
                        fun url(url: JsonField<String>) = apply { this.url = url }

                        fun urlType(urlType: String) = urlType(JsonField.of(urlType))

                        /** Sets [Builder.urlType] to an arbitrary JSON value. */
                        fun urlType(urlType: JsonField<String>) = apply { this.urlType = urlType }

                        fun additionalProperties(additionalProperties: Map<String, JsonValue>) =
                            apply {
                                this.additionalProperties.clear()
                                putAllAdditionalProperties(additionalProperties)
                            }

                        fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                            additionalProperties.put(key, value)
                        }

                        fun putAllAdditionalProperties(
                            additionalProperties: Map<String, JsonValue>
                        ) = apply {
                            this.additionalProperties.putAll(additionalProperties)
                        }

                        fun removeAdditionalProperty(key: String) = apply {
                            additionalProperties.remove(key)
                        }

                        fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                            keys.forEach(::removeAdditionalProperty)
                        }

                        /**
                         * Returns an immutable instance of [Ref].
                         *
                         * Further updates to this [Builder] will not mutate the returned instance.
                         */
                        fun build(): Ref =
                            Ref(
                                screenName,
                                text,
                                type,
                                url,
                                urlType,
                                additionalProperties.toMutableMap(),
                            )
                    }

                    private var validated: Boolean = false

                    /**
                     * Validates that the types of all values in this object match their expected
                     * types recursively.
                     *
                     * This method is _not_ forwards compatible with new types from the API for
                     * existing fields.
                     *
                     * @throws XTwitterScraperInvalidDataException if any value type in this object
                     *   doesn't match its expected type.
                     */
                    fun validate(): Ref = apply {
                        if (validated) {
                            return@apply
                        }

                        screenName()
                        text()
                        type()
                        url()
                        urlType()
                        validated = true
                    }

                    fun isValid(): Boolean =
                        try {
                            validate()
                            true
                        } catch (e: XTwitterScraperInvalidDataException) {
                            false
                        }

                    internal fun validity(): Int =
                        (if (screenName.asKnown() == null) 0 else 1) +
                            (if (text.asKnown() == null) 0 else 1) +
                            (if (type.asKnown() == null) 0 else 1) +
                            (if (url.asKnown() == null) 0 else 1) +
                            (if (urlType.asKnown() == null) 0 else 1)

                    override fun equals(other: Any?): Boolean {
                        if (this === other) {
                            return true
                        }

                        return other is Ref &&
                            screenName == other.screenName &&
                            text == other.text &&
                            type == other.type &&
                            url == other.url &&
                            urlType == other.urlType &&
                            additionalProperties == other.additionalProperties
                    }

                    private val hashCode: Int by lazy {
                        Objects.hash(screenName, text, type, url, urlType, additionalProperties)
                    }

                    override fun hashCode(): Int = hashCode

                    override fun toString() =
                        "Ref{screenName=$screenName, text=$text, type=$type, url=$url, urlType=$urlType, additionalProperties=$additionalProperties}"
                }

                override fun equals(other: Any?): Boolean {
                    if (this === other) {
                        return true
                    }

                    return other is Entity &&
                        fromIndex == other.fromIndex &&
                        ref == other.ref &&
                        toIndex == other.toIndex &&
                        additionalProperties == other.additionalProperties
                }

                private val hashCode: Int by lazy {
                    Objects.hash(fromIndex, ref, toIndex, additionalProperties)
                }

                override fun hashCode(): Int = hashCode

                override fun toString() =
                    "Entity{fromIndex=$fromIndex, ref=$ref, toIndex=$toIndex, additionalProperties=$additionalProperties}"
            }

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is Text &&
                    entities == other.entities &&
                    rtl == other.rtl &&
                    text == other.text &&
                    additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(entities, rtl, text, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "Text{entities=$entities, rtl=$rtl, text=$text, additionalProperties=$additionalProperties}"
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Interstitial &&
                displayType == other.displayType &&
                text == other.text &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(displayType, text, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Interstitial{displayType=$displayType, text=$text, additionalProperties=$additionalProperties}"
    }

    /** Public metadata whose fields X defines. */
    class JetfuelAttachment
    @JsonCreator
    private constructor(
        @com.fasterxml.jackson.annotation.JsonValue
        private val additionalProperties: Map<String, JsonValue>
    ) {

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

        fun toBuilder() = Builder().from(this)

        companion object {

            /** Returns a mutable builder for constructing an instance of [JetfuelAttachment]. */
            fun builder() = Builder()
        }

        /** A builder for [JetfuelAttachment]. */
        class Builder internal constructor() {

            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(jetfuelAttachment: JetfuelAttachment) = apply {
                additionalProperties = jetfuelAttachment.additionalProperties.toMutableMap()
            }

            fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.clear()
                putAllAdditionalProperties(additionalProperties)
            }

            fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                additionalProperties.put(key, value)
            }

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            /**
             * Returns an immutable instance of [JetfuelAttachment].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): JetfuelAttachment = JetfuelAttachment(additionalProperties.toImmutable())
        }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
         *   match its expected type.
         */
        fun validate(): JetfuelAttachment = apply {
            if (validated) {
                return@apply
            }

            validated = true
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: XTwitterScraperInvalidDataException) {
                false
            }

        internal fun validity(): Int = additionalProperties.count { (_, value) ->
            !value.isNull() && !value.isMissing()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is JetfuelAttachment && additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() = "JetfuelAttachment{additionalProperties=$additionalProperties}"
    }

    class LimitedAction
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val action: JsonField<String>,
        private val prompt: JsonField<Prompt>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("action") @ExcludeMissing action: JsonField<String> = JsonMissing.of(),
            @JsonProperty("prompt") @ExcludeMissing prompt: JsonField<Prompt> = JsonMissing.of(),
        ) : this(action, prompt, mutableMapOf())

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun action(): String? = action.getNullable("action")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun prompt(): Prompt? = prompt.getNullable("prompt")

        /**
         * Returns the raw JSON value of [action].
         *
         * Unlike [action], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("action") @ExcludeMissing fun _action(): JsonField<String> = action

        /**
         * Returns the raw JSON value of [prompt].
         *
         * Unlike [prompt], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("prompt") @ExcludeMissing fun _prompt(): JsonField<Prompt> = prompt

        @JsonAnySetter
        private fun putAdditionalProperty(key: String, value: JsonValue) {
            additionalProperties.put(key, value)
        }

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> =
            Collections.unmodifiableMap(additionalProperties)

        fun toBuilder() = Builder().from(this)

        companion object {

            /** Returns a mutable builder for constructing an instance of [LimitedAction]. */
            fun builder() = Builder()
        }

        /** A builder for [LimitedAction]. */
        class Builder internal constructor() {

            private var action: JsonField<String> = JsonMissing.of()
            private var prompt: JsonField<Prompt> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(limitedAction: LimitedAction) = apply {
                action = limitedAction.action
                prompt = limitedAction.prompt
                additionalProperties = limitedAction.additionalProperties.toMutableMap()
            }

            fun action(action: String) = action(JsonField.of(action))

            /** Sets [Builder.action] to an arbitrary JSON value. */
            fun action(action: JsonField<String>) = apply { this.action = action }

            fun prompt(prompt: Prompt) = prompt(JsonField.of(prompt))

            /** Sets [Builder.prompt] to an arbitrary JSON value. */
            fun prompt(prompt: JsonField<Prompt>) = apply { this.prompt = prompt }

            fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.clear()
                putAllAdditionalProperties(additionalProperties)
            }

            fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                additionalProperties.put(key, value)
            }

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            /**
             * Returns an immutable instance of [LimitedAction].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): LimitedAction =
                LimitedAction(action, prompt, additionalProperties.toMutableMap())
        }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
         *   match its expected type.
         */
        fun validate(): LimitedAction = apply {
            if (validated) {
                return@apply
            }

            action()
            prompt()?.validate()
            validated = true
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: XTwitterScraperInvalidDataException) {
                false
            }

        internal fun validity(): Int =
            (if (action.asKnown() == null) 0 else 1) + (prompt.asKnown()?.validity() ?: 0)

        class Prompt
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val ctaType: JsonField<String>,
            private val headline: JsonField<String>,
            private val metadata: JsonField<Metadata>,
            private val subtext: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("ctaType")
                @ExcludeMissing
                ctaType: JsonField<String> = JsonMissing.of(),
                @JsonProperty("headline")
                @ExcludeMissing
                headline: JsonField<String> = JsonMissing.of(),
                @JsonProperty("metadata")
                @ExcludeMissing
                metadata: JsonField<Metadata> = JsonMissing.of(),
                @JsonProperty("subtext")
                @ExcludeMissing
                subtext: JsonField<String> = JsonMissing.of(),
            ) : this(ctaType, headline, metadata, subtext, mutableMapOf())

            /**
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun ctaType(): String? = ctaType.getNullable("ctaType")

            /**
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun headline(): String? = headline.getNullable("headline")

            /**
             * Public metadata whose fields X defines.
             *
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun metadata(): Metadata? = metadata.getNullable("metadata")

            /**
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun subtext(): String? = subtext.getNullable("subtext")

            /**
             * Returns the raw JSON value of [ctaType].
             *
             * Unlike [ctaType], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("ctaType") @ExcludeMissing fun _ctaType(): JsonField<String> = ctaType

            /**
             * Returns the raw JSON value of [headline].
             *
             * Unlike [headline], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("headline") @ExcludeMissing fun _headline(): JsonField<String> = headline

            /**
             * Returns the raw JSON value of [metadata].
             *
             * Unlike [metadata], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("metadata")
            @ExcludeMissing
            fun _metadata(): JsonField<Metadata> = metadata

            /**
             * Returns the raw JSON value of [subtext].
             *
             * Unlike [subtext], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("subtext") @ExcludeMissing fun _subtext(): JsonField<String> = subtext

            @JsonAnySetter
            private fun putAdditionalProperty(key: String, value: JsonValue) {
                additionalProperties.put(key, value)
            }

            @JsonAnyGetter
            @ExcludeMissing
            fun _additionalProperties(): Map<String, JsonValue> =
                Collections.unmodifiableMap(additionalProperties)

            fun toBuilder() = Builder().from(this)

            companion object {

                /** Returns a mutable builder for constructing an instance of [Prompt]. */
                fun builder() = Builder()
            }

            /** A builder for [Prompt]. */
            class Builder internal constructor() {

                private var ctaType: JsonField<String> = JsonMissing.of()
                private var headline: JsonField<String> = JsonMissing.of()
                private var metadata: JsonField<Metadata> = JsonMissing.of()
                private var subtext: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                internal fun from(prompt: Prompt) = apply {
                    ctaType = prompt.ctaType
                    headline = prompt.headline
                    metadata = prompt.metadata
                    subtext = prompt.subtext
                    additionalProperties = prompt.additionalProperties.toMutableMap()
                }

                fun ctaType(ctaType: String) = ctaType(JsonField.of(ctaType))

                /** Sets [Builder.ctaType] to an arbitrary JSON value. */
                fun ctaType(ctaType: JsonField<String>) = apply { this.ctaType = ctaType }

                fun headline(headline: String) = headline(JsonField.of(headline))

                /** Sets [Builder.headline] to an arbitrary JSON value. */
                fun headline(headline: JsonField<String>) = apply { this.headline = headline }

                /** Public metadata whose fields X defines. */
                fun metadata(metadata: Metadata) = metadata(JsonField.of(metadata))

                /** Sets [Builder.metadata] to an arbitrary JSON value. */
                fun metadata(metadata: JsonField<Metadata>) = apply { this.metadata = metadata }

                fun subtext(subtext: String) = subtext(JsonField.of(subtext))

                /** Sets [Builder.subtext] to an arbitrary JSON value. */
                fun subtext(subtext: JsonField<String>) = apply { this.subtext = subtext }

                fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                    this.additionalProperties.clear()
                    putAllAdditionalProperties(additionalProperties)
                }

                fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                    additionalProperties.put(key, value)
                }

                fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) =
                    apply {
                        this.additionalProperties.putAll(additionalProperties)
                    }

                fun removeAdditionalProperty(key: String) = apply {
                    additionalProperties.remove(key)
                }

                fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                    keys.forEach(::removeAdditionalProperty)
                }

                /**
                 * Returns an immutable instance of [Prompt].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 */
                fun build(): Prompt =
                    Prompt(
                        ctaType,
                        headline,
                        metadata,
                        subtext,
                        additionalProperties.toMutableMap(),
                    )
            }

            private var validated: Boolean = false

            /**
             * Validates that the types of all values in this object match their expected types
             * recursively.
             *
             * This method is _not_ forwards compatible with new types from the API for existing
             * fields.
             *
             * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
             *   match its expected type.
             */
            fun validate(): Prompt = apply {
                if (validated) {
                    return@apply
                }

                ctaType()
                headline()
                metadata()?.validate()
                subtext()
                validated = true
            }

            fun isValid(): Boolean =
                try {
                    validate()
                    true
                } catch (e: XTwitterScraperInvalidDataException) {
                    false
                }

            internal fun validity(): Int =
                (if (ctaType.asKnown() == null) 0 else 1) +
                    (if (headline.asKnown() == null) 0 else 1) +
                    (metadata.asKnown()?.validity() ?: 0) +
                    (if (subtext.asKnown() == null) 0 else 1)

            /** Public metadata whose fields X defines. */
            class Metadata
            @JsonCreator
            private constructor(
                @com.fasterxml.jackson.annotation.JsonValue
                private val additionalProperties: Map<String, JsonValue>
            ) {

                @JsonAnyGetter
                @ExcludeMissing
                fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

                fun toBuilder() = Builder().from(this)

                companion object {

                    /** Returns a mutable builder for constructing an instance of [Metadata]. */
                    fun builder() = Builder()
                }

                /** A builder for [Metadata]. */
                class Builder internal constructor() {

                    private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                    internal fun from(metadata: Metadata) = apply {
                        additionalProperties = metadata.additionalProperties.toMutableMap()
                    }

                    fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                        this.additionalProperties.clear()
                        putAllAdditionalProperties(additionalProperties)
                    }

                    fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                        additionalProperties.put(key, value)
                    }

                    fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) =
                        apply {
                            this.additionalProperties.putAll(additionalProperties)
                        }

                    fun removeAdditionalProperty(key: String) = apply {
                        additionalProperties.remove(key)
                    }

                    fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                        keys.forEach(::removeAdditionalProperty)
                    }

                    /**
                     * Returns an immutable instance of [Metadata].
                     *
                     * Further updates to this [Builder] will not mutate the returned instance.
                     */
                    fun build(): Metadata = Metadata(additionalProperties.toImmutable())
                }

                private var validated: Boolean = false

                /**
                 * Validates that the types of all values in this object match their expected types
                 * recursively.
                 *
                 * This method is _not_ forwards compatible with new types from the API for existing
                 * fields.
                 *
                 * @throws XTwitterScraperInvalidDataException if any value type in this object
                 *   doesn't match its expected type.
                 */
                fun validate(): Metadata = apply {
                    if (validated) {
                        return@apply
                    }

                    validated = true
                }

                fun isValid(): Boolean =
                    try {
                        validate()
                        true
                    } catch (e: XTwitterScraperInvalidDataException) {
                        false
                    }

                internal fun validity(): Int = additionalProperties.count { (_, value) ->
                    !value.isNull() && !value.isMissing()
                }

                override fun equals(other: Any?): Boolean {
                    if (this === other) {
                        return true
                    }

                    return other is Metadata && additionalProperties == other.additionalProperties
                }

                private val hashCode: Int by lazy { Objects.hash(additionalProperties) }

                override fun hashCode(): Int = hashCode

                override fun toString() = "Metadata{additionalProperties=$additionalProperties}"
            }

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is Prompt &&
                    ctaType == other.ctaType &&
                    headline == other.headline &&
                    metadata == other.metadata &&
                    subtext == other.subtext &&
                    additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(ctaType, headline, metadata, subtext, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "Prompt{ctaType=$ctaType, headline=$headline, metadata=$metadata, subtext=$subtext, additionalProperties=$additionalProperties}"
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is LimitedAction &&
                action == other.action &&
                prompt == other.prompt &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(action, prompt, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "LimitedAction{action=$action, prompt=$prompt, additionalProperties=$additionalProperties}"
    }

    /** Complete Note Tweet content and rich-text metadata. */
    class NoteTweet
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val text: JsonField<String>,
        private val id: JsonField<String>,
        private val entities: JsonField<Entities>,
        private val inlineMedia: JsonField<List<InlineMedia>>,
        private val isExpandable: JsonField<Boolean>,
        private val noteId: JsonField<String>,
        private val richtextTags: JsonField<List<RichtextTag>>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("text") @ExcludeMissing text: JsonField<String> = JsonMissing.of(),
            @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
            @JsonProperty("entities")
            @ExcludeMissing
            entities: JsonField<Entities> = JsonMissing.of(),
            @JsonProperty("inlineMedia")
            @ExcludeMissing
            inlineMedia: JsonField<List<InlineMedia>> = JsonMissing.of(),
            @JsonProperty("isExpandable")
            @ExcludeMissing
            isExpandable: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("noteId") @ExcludeMissing noteId: JsonField<String> = JsonMissing.of(),
            @JsonProperty("richtextTags")
            @ExcludeMissing
            richtextTags: JsonField<List<RichtextTag>> = JsonMissing.of(),
        ) : this(
            text,
            id,
            entities,
            inlineMedia,
            isExpandable,
            noteId,
            richtextTags,
            mutableMapOf(),
        )

        /**
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or
         *   is unexpectedly missing or null.
         */
        fun text(): String = text.getRequired("text")

        /**
         * X's global ID of the Note, base64 of `NoteTweet:<noteId>`.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun id(): String? = id.getNullable("id")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun entities(): Entities? = entities.getNullable("entities")

        /**
         * Inline media positions in the Note Tweet text.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun inlineMedia(): List<InlineMedia>? = inlineMedia.getNullable("inlineMedia")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun isExpandable(): Boolean? = isExpandable.getNullable("isExpandable")

        /**
         * The Note's own ID, apart from the post's.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun noteId(): String? = noteId.getNullable("noteId")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun richtextTags(): List<RichtextTag>? = richtextTags.getNullable("richtextTags")

        /**
         * Returns the raw JSON value of [text].
         *
         * Unlike [text], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("text") @ExcludeMissing fun _text(): JsonField<String> = text

        /**
         * Returns the raw JSON value of [id].
         *
         * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

        /**
         * Returns the raw JSON value of [entities].
         *
         * Unlike [entities], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("entities") @ExcludeMissing fun _entities(): JsonField<Entities> = entities

        /**
         * Returns the raw JSON value of [inlineMedia].
         *
         * Unlike [inlineMedia], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("inlineMedia")
        @ExcludeMissing
        fun _inlineMedia(): JsonField<List<InlineMedia>> = inlineMedia

        /**
         * Returns the raw JSON value of [isExpandable].
         *
         * Unlike [isExpandable], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("isExpandable")
        @ExcludeMissing
        fun _isExpandable(): JsonField<Boolean> = isExpandable

        /**
         * Returns the raw JSON value of [noteId].
         *
         * Unlike [noteId], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("noteId") @ExcludeMissing fun _noteId(): JsonField<String> = noteId

        /**
         * Returns the raw JSON value of [richtextTags].
         *
         * Unlike [richtextTags], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("richtextTags")
        @ExcludeMissing
        fun _richtextTags(): JsonField<List<RichtextTag>> = richtextTags

        @JsonAnySetter
        private fun putAdditionalProperty(key: String, value: JsonValue) {
            additionalProperties.put(key, value)
        }

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> =
            Collections.unmodifiableMap(additionalProperties)

        fun toBuilder() = Builder().from(this)

        companion object {

            /**
             * Returns a mutable builder for constructing an instance of [NoteTweet].
             *
             * The following fields are required:
             * ```kotlin
             * .text()
             * ```
             */
            fun builder() = Builder()
        }

        /** A builder for [NoteTweet]. */
        class Builder internal constructor() {

            private var text: JsonField<String>? = null
            private var id: JsonField<String> = JsonMissing.of()
            private var entities: JsonField<Entities> = JsonMissing.of()
            private var inlineMedia: JsonField<MutableList<InlineMedia>>? = null
            private var isExpandable: JsonField<Boolean> = JsonMissing.of()
            private var noteId: JsonField<String> = JsonMissing.of()
            private var richtextTags: JsonField<MutableList<RichtextTag>>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(noteTweet: NoteTweet) = apply {
                text = noteTweet.text
                id = noteTweet.id
                entities = noteTweet.entities
                inlineMedia = noteTweet.inlineMedia.map { it.toMutableList() }
                isExpandable = noteTweet.isExpandable
                noteId = noteTweet.noteId
                richtextTags = noteTweet.richtextTags.map { it.toMutableList() }
                additionalProperties = noteTweet.additionalProperties.toMutableMap()
            }

            fun text(text: String) = text(JsonField.of(text))

            /** Sets [Builder.text] to an arbitrary JSON value. */
            fun text(text: JsonField<String>) = apply { this.text = text }

            /** X's global ID of the Note, base64 of `NoteTweet:<noteId>`. */
            fun id(id: String) = id(JsonField.of(id))

            /** Sets [Builder.id] to an arbitrary JSON value. */
            fun id(id: JsonField<String>) = apply { this.id = id }

            fun entities(entities: Entities) = entities(JsonField.of(entities))

            /** Sets [Builder.entities] to an arbitrary JSON value. */
            fun entities(entities: JsonField<Entities>) = apply { this.entities = entities }

            /** Inline media positions in the Note Tweet text. */
            fun inlineMedia(inlineMedia: List<InlineMedia>) = inlineMedia(JsonField.of(inlineMedia))

            /** Sets [Builder.inlineMedia] to an arbitrary JSON value. */
            fun inlineMedia(inlineMedia: JsonField<List<InlineMedia>>) = apply {
                this.inlineMedia = inlineMedia.map { it.toMutableList() }
            }

            /**
             * Adds a single [InlineMedia] to [inlineMedia].
             *
             * @throws IllegalStateException if the field was previously set to a non-list.
             */
            fun addInlineMedia(inlineMedia: InlineMedia) = apply {
                this.inlineMedia =
                    (this.inlineMedia ?: JsonField.of(mutableListOf())).also {
                        checkKnown("inlineMedia", it).add(inlineMedia)
                    }
            }

            fun isExpandable(isExpandable: Boolean) = isExpandable(JsonField.of(isExpandable))

            /** Sets [Builder.isExpandable] to an arbitrary JSON value. */
            fun isExpandable(isExpandable: JsonField<Boolean>) = apply {
                this.isExpandable = isExpandable
            }

            /** The Note's own ID, apart from the post's. */
            fun noteId(noteId: String) = noteId(JsonField.of(noteId))

            /** Sets [Builder.noteId] to an arbitrary JSON value. */
            fun noteId(noteId: JsonField<String>) = apply { this.noteId = noteId }

            fun richtextTags(richtextTags: List<RichtextTag>) =
                richtextTags(JsonField.of(richtextTags))

            /** Sets [Builder.richtextTags] to an arbitrary JSON value. */
            fun richtextTags(richtextTags: JsonField<List<RichtextTag>>) = apply {
                this.richtextTags = richtextTags.map { it.toMutableList() }
            }

            /**
             * Adds a single [RichtextTag] to [richtextTags].
             *
             * @throws IllegalStateException if the field was previously set to a non-list.
             */
            fun addRichtextTag(richtextTag: RichtextTag) = apply {
                richtextTags =
                    (richtextTags ?: JsonField.of(mutableListOf())).also {
                        checkKnown("richtextTags", it).add(richtextTag)
                    }
            }

            fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.clear()
                putAllAdditionalProperties(additionalProperties)
            }

            fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                additionalProperties.put(key, value)
            }

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            /**
             * Returns an immutable instance of [NoteTweet].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             *
             * The following fields are required:
             * ```kotlin
             * .text()
             * ```
             *
             * @throws IllegalStateException if any required field is unset.
             */
            fun build(): NoteTweet =
                NoteTweet(
                    checkRequired("text", text),
                    id,
                    entities,
                    (inlineMedia ?: JsonMissing.of()).map { it.toImmutable() },
                    isExpandable,
                    noteId,
                    (richtextTags ?: JsonMissing.of()).map { it.toImmutable() },
                    additionalProperties.toMutableMap(),
                )
        }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
         *   match its expected type.
         */
        fun validate(): NoteTweet = apply {
            if (validated) {
                return@apply
            }

            text()
            id()
            entities()?.validate()
            inlineMedia()?.forEach { it.validate() }
            isExpandable()
            noteId()
            richtextTags()?.forEach { it.validate() }
            validated = true
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: XTwitterScraperInvalidDataException) {
                false
            }

        internal fun validity(): Int =
            (if (text.asKnown() == null) 0 else 1) +
                (if (id.asKnown() == null) 0 else 1) +
                (entities.asKnown()?.validity() ?: 0) +
                (inlineMedia.asKnown()?.sumOf { it.validity() } ?: 0) +
                (if (isExpandable.asKnown() == null) 0 else 1) +
                (if (noteId.asKnown() == null) 0 else 1) +
                (richtextTags.asKnown()?.sumOf { it.validity() } ?: 0)

        /** Lists hashtags, symbols, links, and mentions from tweet text. */
        class Entities
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val hashtags: JsonField<List<Hashtag>>,
            private val smarttags: JsonField<List<Smarttag>>,
            private val symbols: JsonField<List<Symbol>>,
            private val timestamps: JsonField<List<Timestamp>>,
            private val urls: JsonField<List<Url>>,
            private val userMentions: JsonField<List<UserMention>>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("hashtags")
                @ExcludeMissing
                hashtags: JsonField<List<Hashtag>> = JsonMissing.of(),
                @JsonProperty("smarttags")
                @ExcludeMissing
                smarttags: JsonField<List<Smarttag>> = JsonMissing.of(),
                @JsonProperty("symbols")
                @ExcludeMissing
                symbols: JsonField<List<Symbol>> = JsonMissing.of(),
                @JsonProperty("timestamps")
                @ExcludeMissing
                timestamps: JsonField<List<Timestamp>> = JsonMissing.of(),
                @JsonProperty("urls") @ExcludeMissing urls: JsonField<List<Url>> = JsonMissing.of(),
                @JsonProperty("user_mentions")
                @ExcludeMissing
                userMentions: JsonField<List<UserMention>> = JsonMissing.of(),
            ) : this(hashtags, smarttags, symbols, timestamps, urls, userMentions, mutableMapOf())

            /**
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun hashtags(): List<Hashtag>? = hashtags.getNullable("hashtags")

            /**
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun smarttags(): List<Smarttag>? = smarttags.getNullable("smarttags")

            /**
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun symbols(): List<Symbol>? = symbols.getNullable("symbols")

            /**
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun timestamps(): List<Timestamp>? = timestamps.getNullable("timestamps")

            /**
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun urls(): List<Url>? = urls.getNullable("urls")

            /**
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun userMentions(): List<UserMention>? = userMentions.getNullable("user_mentions")

            /**
             * Returns the raw JSON value of [hashtags].
             *
             * Unlike [hashtags], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("hashtags")
            @ExcludeMissing
            fun _hashtags(): JsonField<List<Hashtag>> = hashtags

            /**
             * Returns the raw JSON value of [smarttags].
             *
             * Unlike [smarttags], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("smarttags")
            @ExcludeMissing
            fun _smarttags(): JsonField<List<Smarttag>> = smarttags

            /**
             * Returns the raw JSON value of [symbols].
             *
             * Unlike [symbols], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("symbols")
            @ExcludeMissing
            fun _symbols(): JsonField<List<Symbol>> = symbols

            /**
             * Returns the raw JSON value of [timestamps].
             *
             * Unlike [timestamps], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("timestamps")
            @ExcludeMissing
            fun _timestamps(): JsonField<List<Timestamp>> = timestamps

            /**
             * Returns the raw JSON value of [urls].
             *
             * Unlike [urls], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("urls") @ExcludeMissing fun _urls(): JsonField<List<Url>> = urls

            /**
             * Returns the raw JSON value of [userMentions].
             *
             * Unlike [userMentions], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("user_mentions")
            @ExcludeMissing
            fun _userMentions(): JsonField<List<UserMention>> = userMentions

            @JsonAnySetter
            private fun putAdditionalProperty(key: String, value: JsonValue) {
                additionalProperties.put(key, value)
            }

            @JsonAnyGetter
            @ExcludeMissing
            fun _additionalProperties(): Map<String, JsonValue> =
                Collections.unmodifiableMap(additionalProperties)

            fun toBuilder() = Builder().from(this)

            companion object {

                /** Returns a mutable builder for constructing an instance of [Entities]. */
                fun builder() = Builder()
            }

            /** A builder for [Entities]. */
            class Builder internal constructor() {

                private var hashtags: JsonField<MutableList<Hashtag>>? = null
                private var smarttags: JsonField<MutableList<Smarttag>>? = null
                private var symbols: JsonField<MutableList<Symbol>>? = null
                private var timestamps: JsonField<MutableList<Timestamp>>? = null
                private var urls: JsonField<MutableList<Url>>? = null
                private var userMentions: JsonField<MutableList<UserMention>>? = null
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                internal fun from(entities: Entities) = apply {
                    hashtags = entities.hashtags.map { it.toMutableList() }
                    smarttags = entities.smarttags.map { it.toMutableList() }
                    symbols = entities.symbols.map { it.toMutableList() }
                    timestamps = entities.timestamps.map { it.toMutableList() }
                    urls = entities.urls.map { it.toMutableList() }
                    userMentions = entities.userMentions.map { it.toMutableList() }
                    additionalProperties = entities.additionalProperties.toMutableMap()
                }

                fun hashtags(hashtags: List<Hashtag>) = hashtags(JsonField.of(hashtags))

                /** Sets [Builder.hashtags] to an arbitrary JSON value. */
                fun hashtags(hashtags: JsonField<List<Hashtag>>) = apply {
                    this.hashtags = hashtags.map { it.toMutableList() }
                }

                /**
                 * Adds a single [Hashtag] to [hashtags].
                 *
                 * @throws IllegalStateException if the field was previously set to a non-list.
                 */
                fun addHashtag(hashtag: Hashtag) = apply {
                    hashtags =
                        (hashtags ?: JsonField.of(mutableListOf())).also {
                            checkKnown("hashtags", it).add(hashtag)
                        }
                }

                fun smarttags(smarttags: List<Smarttag>) = smarttags(JsonField.of(smarttags))

                /** Sets [Builder.smarttags] to an arbitrary JSON value. */
                fun smarttags(smarttags: JsonField<List<Smarttag>>) = apply {
                    this.smarttags = smarttags.map { it.toMutableList() }
                }

                /**
                 * Adds a single [Smarttag] to [smarttags].
                 *
                 * @throws IllegalStateException if the field was previously set to a non-list.
                 */
                fun addSmarttag(smarttag: Smarttag) = apply {
                    smarttags =
                        (smarttags ?: JsonField.of(mutableListOf())).also {
                            checkKnown("smarttags", it).add(smarttag)
                        }
                }

                fun symbols(symbols: List<Symbol>) = symbols(JsonField.of(symbols))

                /** Sets [Builder.symbols] to an arbitrary JSON value. */
                fun symbols(symbols: JsonField<List<Symbol>>) = apply {
                    this.symbols = symbols.map { it.toMutableList() }
                }

                /**
                 * Adds a single [Symbol] to [symbols].
                 *
                 * @throws IllegalStateException if the field was previously set to a non-list.
                 */
                fun addSymbol(symbol: Symbol) = apply {
                    symbols =
                        (symbols ?: JsonField.of(mutableListOf())).also {
                            checkKnown("symbols", it).add(symbol)
                        }
                }

                fun timestamps(timestamps: List<Timestamp>) = timestamps(JsonField.of(timestamps))

                /** Sets [Builder.timestamps] to an arbitrary JSON value. */
                fun timestamps(timestamps: JsonField<List<Timestamp>>) = apply {
                    this.timestamps = timestamps.map { it.toMutableList() }
                }

                /**
                 * Adds a single [Timestamp] to [timestamps].
                 *
                 * @throws IllegalStateException if the field was previously set to a non-list.
                 */
                fun addTimestamp(timestamp: Timestamp) = apply {
                    timestamps =
                        (timestamps ?: JsonField.of(mutableListOf())).also {
                            checkKnown("timestamps", it).add(timestamp)
                        }
                }

                fun urls(urls: List<Url>) = urls(JsonField.of(urls))

                /** Sets [Builder.urls] to an arbitrary JSON value. */
                fun urls(urls: JsonField<List<Url>>) = apply {
                    this.urls = urls.map { it.toMutableList() }
                }

                /**
                 * Adds a single [Url] to [urls].
                 *
                 * @throws IllegalStateException if the field was previously set to a non-list.
                 */
                fun addUrl(url: Url) = apply {
                    urls =
                        (urls ?: JsonField.of(mutableListOf())).also {
                            checkKnown("urls", it).add(url)
                        }
                }

                fun userMentions(userMentions: List<UserMention>) =
                    userMentions(JsonField.of(userMentions))

                /** Sets [Builder.userMentions] to an arbitrary JSON value. */
                fun userMentions(userMentions: JsonField<List<UserMention>>) = apply {
                    this.userMentions = userMentions.map { it.toMutableList() }
                }

                /**
                 * Adds a single [UserMention] to [userMentions].
                 *
                 * @throws IllegalStateException if the field was previously set to a non-list.
                 */
                fun addUserMention(userMention: UserMention) = apply {
                    userMentions =
                        (userMentions ?: JsonField.of(mutableListOf())).also {
                            checkKnown("userMentions", it).add(userMention)
                        }
                }

                fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                    this.additionalProperties.clear()
                    putAllAdditionalProperties(additionalProperties)
                }

                fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                    additionalProperties.put(key, value)
                }

                fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) =
                    apply {
                        this.additionalProperties.putAll(additionalProperties)
                    }

                fun removeAdditionalProperty(key: String) = apply {
                    additionalProperties.remove(key)
                }

                fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                    keys.forEach(::removeAdditionalProperty)
                }

                /**
                 * Returns an immutable instance of [Entities].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 */
                fun build(): Entities =
                    Entities(
                        (hashtags ?: JsonMissing.of()).map { it.toImmutable() },
                        (smarttags ?: JsonMissing.of()).map { it.toImmutable() },
                        (symbols ?: JsonMissing.of()).map { it.toImmutable() },
                        (timestamps ?: JsonMissing.of()).map { it.toImmutable() },
                        (urls ?: JsonMissing.of()).map { it.toImmutable() },
                        (userMentions ?: JsonMissing.of()).map { it.toImmutable() },
                        additionalProperties.toMutableMap(),
                    )
            }

            private var validated: Boolean = false

            /**
             * Validates that the types of all values in this object match their expected types
             * recursively.
             *
             * This method is _not_ forwards compatible with new types from the API for existing
             * fields.
             *
             * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
             *   match its expected type.
             */
            fun validate(): Entities = apply {
                if (validated) {
                    return@apply
                }

                hashtags()?.forEach { it.validate() }
                smarttags()?.forEach { it.validate() }
                symbols()?.forEach { it.validate() }
                timestamps()?.forEach { it.validate() }
                urls()?.forEach { it.validate() }
                userMentions()?.forEach { it.validate() }
                validated = true
            }

            fun isValid(): Boolean =
                try {
                    validate()
                    true
                } catch (e: XTwitterScraperInvalidDataException) {
                    false
                }

            internal fun validity(): Int =
                (hashtags.asKnown()?.sumOf { it.validity() } ?: 0) +
                    (smarttags.asKnown()?.sumOf { it.validity() } ?: 0) +
                    (symbols.asKnown()?.sumOf { it.validity() } ?: 0) +
                    (timestamps.asKnown()?.sumOf { it.validity() } ?: 0) +
                    (urls.asKnown()?.sumOf { it.validity() } ?: 0) +
                    (userMentions.asKnown()?.sumOf { it.validity() } ?: 0)

            /** Hashtag text and its source offsets in a tweet. */
            class Hashtag
            @JsonCreator(mode = JsonCreator.Mode.DISABLED)
            private constructor(
                private val text: JsonField<String>,
                private val indices: JsonField<List<Long>>,
                private val tag: JsonField<Tag>,
                private val additionalProperties: MutableMap<String, JsonValue>,
            ) {

                @JsonCreator
                private constructor(
                    @JsonProperty("text")
                    @ExcludeMissing
                    text: JsonField<String> = JsonMissing.of(),
                    @JsonProperty("indices")
                    @ExcludeMissing
                    indices: JsonField<List<Long>> = JsonMissing.of(),
                    @JsonProperty("tag") @ExcludeMissing tag: JsonField<Tag> = JsonMissing.of(),
                ) : this(text, indices, tag, mutableMapOf())

                /**
                 * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected
                 *   type or is unexpectedly missing or null.
                 */
                fun text(): String = text.getRequired("text")

                /**
                 * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected
                 *   type.
                 */
                fun indices(): List<Long>? = indices.getNullable("indices")

                /**
                 * X's SmartTag for a cashtag, smarttag or hashtag. Xquik leaves out X's trading
                 * platforms, which differ by reader.
                 *
                 * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected
                 *   type.
                 */
                fun tag(): Tag? = tag.getNullable("tag")

                /**
                 * Returns the raw JSON value of [text].
                 *
                 * Unlike [text], this method doesn't throw if the JSON field has an unexpected
                 * type.
                 */
                @JsonProperty("text") @ExcludeMissing fun _text(): JsonField<String> = text

                /**
                 * Returns the raw JSON value of [indices].
                 *
                 * Unlike [indices], this method doesn't throw if the JSON field has an unexpected
                 * type.
                 */
                @JsonProperty("indices")
                @ExcludeMissing
                fun _indices(): JsonField<List<Long>> = indices

                /**
                 * Returns the raw JSON value of [tag].
                 *
                 * Unlike [tag], this method doesn't throw if the JSON field has an unexpected type.
                 */
                @JsonProperty("tag") @ExcludeMissing fun _tag(): JsonField<Tag> = tag

                @JsonAnySetter
                private fun putAdditionalProperty(key: String, value: JsonValue) {
                    additionalProperties.put(key, value)
                }

                @JsonAnyGetter
                @ExcludeMissing
                fun _additionalProperties(): Map<String, JsonValue> =
                    Collections.unmodifiableMap(additionalProperties)

                fun toBuilder() = Builder().from(this)

                companion object {

                    /**
                     * Returns a mutable builder for constructing an instance of [Hashtag].
                     *
                     * The following fields are required:
                     * ```kotlin
                     * .text()
                     * ```
                     */
                    fun builder() = Builder()
                }

                /** A builder for [Hashtag]. */
                class Builder internal constructor() {

                    private var text: JsonField<String>? = null
                    private var indices: JsonField<MutableList<Long>>? = null
                    private var tag: JsonField<Tag> = JsonMissing.of()
                    private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                    internal fun from(hashtag: Hashtag) = apply {
                        text = hashtag.text
                        indices = hashtag.indices.map { it.toMutableList() }
                        tag = hashtag.tag
                        additionalProperties = hashtag.additionalProperties.toMutableMap()
                    }

                    fun text(text: String) = text(JsonField.of(text))

                    /** Sets [Builder.text] to an arbitrary JSON value. */
                    fun text(text: JsonField<String>) = apply { this.text = text }

                    fun indices(indices: List<Long>) = indices(JsonField.of(indices))

                    /** Sets [Builder.indices] to an arbitrary JSON value. */
                    fun indices(indices: JsonField<List<Long>>) = apply {
                        this.indices = indices.map { it.toMutableList() }
                    }

                    /**
                     * Adds a single [Long] to [indices].
                     *
                     * @throws IllegalStateException if the field was previously set to a non-list.
                     */
                    fun addIndex(index: Long) = apply {
                        indices =
                            (indices ?: JsonField.of(mutableListOf())).also {
                                checkKnown("indices", it).add(index)
                            }
                    }

                    /**
                     * X's SmartTag for a cashtag, smarttag or hashtag. Xquik leaves out X's trading
                     * platforms, which differ by reader.
                     */
                    fun tag(tag: Tag) = tag(JsonField.of(tag))

                    /** Sets [Builder.tag] to an arbitrary JSON value. */
                    fun tag(tag: JsonField<Tag>) = apply { this.tag = tag }

                    fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                        this.additionalProperties.clear()
                        putAllAdditionalProperties(additionalProperties)
                    }

                    fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                        additionalProperties.put(key, value)
                    }

                    fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) =
                        apply {
                            this.additionalProperties.putAll(additionalProperties)
                        }

                    fun removeAdditionalProperty(key: String) = apply {
                        additionalProperties.remove(key)
                    }

                    fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                        keys.forEach(::removeAdditionalProperty)
                    }

                    /**
                     * Returns an immutable instance of [Hashtag].
                     *
                     * Further updates to this [Builder] will not mutate the returned instance.
                     *
                     * The following fields are required:
                     * ```kotlin
                     * .text()
                     * ```
                     *
                     * @throws IllegalStateException if any required field is unset.
                     */
                    fun build(): Hashtag =
                        Hashtag(
                            checkRequired("text", text),
                            (indices ?: JsonMissing.of()).map { it.toImmutable() },
                            tag,
                            additionalProperties.toMutableMap(),
                        )
                }

                private var validated: Boolean = false

                /**
                 * Validates that the types of all values in this object match their expected types
                 * recursively.
                 *
                 * This method is _not_ forwards compatible with new types from the API for existing
                 * fields.
                 *
                 * @throws XTwitterScraperInvalidDataException if any value type in this object
                 *   doesn't match its expected type.
                 */
                fun validate(): Hashtag = apply {
                    if (validated) {
                        return@apply
                    }

                    text()
                    indices()
                    tag()?.validate()
                    validated = true
                }

                fun isValid(): Boolean =
                    try {
                        validate()
                        true
                    } catch (e: XTwitterScraperInvalidDataException) {
                        false
                    }

                internal fun validity(): Int =
                    (if (text.asKnown() == null) 0 else 1) +
                        (indices.asKnown()?.size ?: 0) +
                        (tag.asKnown()?.validity() ?: 0)

                /**
                 * X's SmartTag for a cashtag, smarttag or hashtag. Xquik leaves out X's trading
                 * platforms, which differ by reader.
                 */
                class Tag
                @JsonCreator(mode = JsonCreator.Mode.DISABLED)
                private constructor(
                    private val id: JsonField<String>,
                    private val info: JsonField<Info>,
                    private val additionalProperties: MutableMap<String, JsonValue>,
                ) {

                    @JsonCreator
                    private constructor(
                        @JsonProperty("id")
                        @ExcludeMissing
                        id: JsonField<String> = JsonMissing.of(),
                        @JsonProperty("info")
                        @ExcludeMissing
                        info: JsonField<Info> = JsonMissing.of(),
                    ) : this(id, info, mutableMapOf())

                    /**
                     * X's ID for the SmartTag.
                     *
                     * @throws XTwitterScraperInvalidDataException if the JSON field has an
                     *   unexpected type.
                     */
                    fun id(): String? = id.getNullable("id")

                    /**
                     * @throws XTwitterScraperInvalidDataException if the JSON field has an
                     *   unexpected type.
                     */
                    fun info(): Info? = info.getNullable("info")

                    /**
                     * Returns the raw JSON value of [id].
                     *
                     * Unlike [id], this method doesn't throw if the JSON field has an unexpected
                     * type.
                     */
                    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

                    /**
                     * Returns the raw JSON value of [info].
                     *
                     * Unlike [info], this method doesn't throw if the JSON field has an unexpected
                     * type.
                     */
                    @JsonProperty("info") @ExcludeMissing fun _info(): JsonField<Info> = info

                    @JsonAnySetter
                    private fun putAdditionalProperty(key: String, value: JsonValue) {
                        additionalProperties.put(key, value)
                    }

                    @JsonAnyGetter
                    @ExcludeMissing
                    fun _additionalProperties(): Map<String, JsonValue> =
                        Collections.unmodifiableMap(additionalProperties)

                    fun toBuilder() = Builder().from(this)

                    companion object {

                        /** Returns a mutable builder for constructing an instance of [Tag]. */
                        fun builder() = Builder()
                    }

                    /** A builder for [Tag]. */
                    class Builder internal constructor() {

                        private var id: JsonField<String> = JsonMissing.of()
                        private var info: JsonField<Info> = JsonMissing.of()
                        private var additionalProperties: MutableMap<String, JsonValue> =
                            mutableMapOf()

                        internal fun from(tag: Tag) = apply {
                            id = tag.id
                            info = tag.info
                            additionalProperties = tag.additionalProperties.toMutableMap()
                        }

                        /** X's ID for the SmartTag. */
                        fun id(id: String) = id(JsonField.of(id))

                        /** Sets [Builder.id] to an arbitrary JSON value. */
                        fun id(id: JsonField<String>) = apply { this.id = id }

                        fun info(info: Info) = info(JsonField.of(info))

                        /** Sets [Builder.info] to an arbitrary JSON value. */
                        fun info(info: JsonField<Info>) = apply { this.info = info }

                        fun additionalProperties(additionalProperties: Map<String, JsonValue>) =
                            apply {
                                this.additionalProperties.clear()
                                putAllAdditionalProperties(additionalProperties)
                            }

                        fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                            additionalProperties.put(key, value)
                        }

                        fun putAllAdditionalProperties(
                            additionalProperties: Map<String, JsonValue>
                        ) = apply {
                            this.additionalProperties.putAll(additionalProperties)
                        }

                        fun removeAdditionalProperty(key: String) = apply {
                            additionalProperties.remove(key)
                        }

                        fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                            keys.forEach(::removeAdditionalProperty)
                        }

                        /**
                         * Returns an immutable instance of [Tag].
                         *
                         * Further updates to this [Builder] will not mutate the returned instance.
                         */
                        fun build(): Tag = Tag(id, info, additionalProperties.toMutableMap())
                    }

                    private var validated: Boolean = false

                    /**
                     * Validates that the types of all values in this object match their expected
                     * types recursively.
                     *
                     * This method is _not_ forwards compatible with new types from the API for
                     * existing fields.
                     *
                     * @throws XTwitterScraperInvalidDataException if any value type in this object
                     *   doesn't match its expected type.
                     */
                    fun validate(): Tag = apply {
                        if (validated) {
                            return@apply
                        }

                        id()
                        info()?.validate()
                        validated = true
                    }

                    fun isValid(): Boolean =
                        try {
                            validate()
                            true
                        } catch (e: XTwitterScraperInvalidDataException) {
                            false
                        }

                    internal fun validity(): Int =
                        (if (id.asKnown() == null) 0 else 1) + (info.asKnown()?.validity() ?: 0)

                    class Info
                    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
                    private constructor(
                        private val info: JsonField<InnerInfo>,
                        private val additionalProperties: MutableMap<String, JsonValue>,
                    ) {

                        @JsonCreator
                        private constructor(
                            @JsonProperty("info")
                            @ExcludeMissing
                            info: JsonField<InnerInfo> = JsonMissing.of()
                        ) : this(info, mutableMapOf())

                        /**
                         * @throws XTwitterScraperInvalidDataException if the JSON field has an
                         *   unexpected type.
                         */
                        fun info(): InnerInfo? = info.getNullable("info")

                        /**
                         * Returns the raw JSON value of [info].
                         *
                         * Unlike [info], this method doesn't throw if the JSON field has an
                         * unexpected type.
                         */
                        @JsonProperty("info")
                        @ExcludeMissing
                        fun _info(): JsonField<InnerInfo> = info

                        @JsonAnySetter
                        private fun putAdditionalProperty(key: String, value: JsonValue) {
                            additionalProperties.put(key, value)
                        }

                        @JsonAnyGetter
                        @ExcludeMissing
                        fun _additionalProperties(): Map<String, JsonValue> =
                            Collections.unmodifiableMap(additionalProperties)

                        fun toBuilder() = Builder().from(this)

                        companion object {

                            /** Returns a mutable builder for constructing an instance of [Info]. */
                            fun builder() = Builder()
                        }

                        /** A builder for [Info]. */
                        class Builder internal constructor() {

                            private var info: JsonField<InnerInfo> = JsonMissing.of()
                            private var additionalProperties: MutableMap<String, JsonValue> =
                                mutableMapOf()

                            internal fun from(info: Info) = apply {
                                this.info = info.info
                                additionalProperties = info.additionalProperties.toMutableMap()
                            }

                            fun info(info: InnerInfo) = info(JsonField.of(info))

                            /** Sets [Builder.info] to an arbitrary JSON value. */
                            fun info(info: JsonField<InnerInfo>) = apply { this.info = info }

                            fun additionalProperties(additionalProperties: Map<String, JsonValue>) =
                                apply {
                                    this.additionalProperties.clear()
                                    putAllAdditionalProperties(additionalProperties)
                                }

                            fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                                additionalProperties.put(key, value)
                            }

                            fun putAllAdditionalProperties(
                                additionalProperties: Map<String, JsonValue>
                            ) = apply {
                                this.additionalProperties.putAll(additionalProperties)
                            }

                            fun removeAdditionalProperty(key: String) = apply {
                                additionalProperties.remove(key)
                            }

                            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                                keys.forEach(::removeAdditionalProperty)
                            }

                            /**
                             * Returns an immutable instance of [Info].
                             *
                             * Further updates to this [Builder] will not mutate the returned
                             * instance.
                             */
                            fun build(): Info = Info(info, additionalProperties.toMutableMap())
                        }

                        private var validated: Boolean = false

                        /**
                         * Validates that the types of all values in this object match their
                         * expected types recursively.
                         *
                         * This method is _not_ forwards compatible with new types from the API for
                         * existing fields.
                         *
                         * @throws XTwitterScraperInvalidDataException if any value type in this
                         *   object doesn't match its expected type.
                         */
                        fun validate(): Info = apply {
                            if (validated) {
                                return@apply
                            }

                            info()?.validate()
                            validated = true
                        }

                        fun isValid(): Boolean =
                            try {
                                validate()
                                true
                            } catch (e: XTwitterScraperInvalidDataException) {
                                false
                            }

                        internal fun validity(): Int = (info.asKnown()?.validity() ?: 0)

                        class InnerInfo
                        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
                        private constructor(
                            private val active: JsonField<Boolean>,
                            private val name: JsonField<String>,
                            private val ticker: JsonField<String>,
                            private val additionalProperties: MutableMap<String, JsonValue>,
                        ) {

                            @JsonCreator
                            private constructor(
                                @JsonProperty("active")
                                @ExcludeMissing
                                active: JsonField<Boolean> = JsonMissing.of(),
                                @JsonProperty("name")
                                @ExcludeMissing
                                name: JsonField<String> = JsonMissing.of(),
                                @JsonProperty("ticker")
                                @ExcludeMissing
                                ticker: JsonField<String> = JsonMissing.of(),
                            ) : this(active, name, ticker, mutableMapOf())

                            /**
                             * Whether X marks the SmartTag active.
                             *
                             * @throws XTwitterScraperInvalidDataException if the JSON field has an
                             *   unexpected type.
                             */
                            fun active(): Boolean? = active.getNullable("active")

                            /**
                             * @throws XTwitterScraperInvalidDataException if the JSON field has an
                             *   unexpected type.
                             */
                            fun name(): String? = name.getNullable("name")

                            /**
                             * @throws XTwitterScraperInvalidDataException if the JSON field has an
                             *   unexpected type.
                             */
                            fun ticker(): String? = ticker.getNullable("ticker")

                            /**
                             * Returns the raw JSON value of [active].
                             *
                             * Unlike [active], this method doesn't throw if the JSON field has an
                             * unexpected type.
                             */
                            @JsonProperty("active")
                            @ExcludeMissing
                            fun _active(): JsonField<Boolean> = active

                            /**
                             * Returns the raw JSON value of [name].
                             *
                             * Unlike [name], this method doesn't throw if the JSON field has an
                             * unexpected type.
                             */
                            @JsonProperty("name")
                            @ExcludeMissing
                            fun _name(): JsonField<String> = name

                            /**
                             * Returns the raw JSON value of [ticker].
                             *
                             * Unlike [ticker], this method doesn't throw if the JSON field has an
                             * unexpected type.
                             */
                            @JsonProperty("ticker")
                            @ExcludeMissing
                            fun _ticker(): JsonField<String> = ticker

                            @JsonAnySetter
                            private fun putAdditionalProperty(key: String, value: JsonValue) {
                                additionalProperties.put(key, value)
                            }

                            @JsonAnyGetter
                            @ExcludeMissing
                            fun _additionalProperties(): Map<String, JsonValue> =
                                Collections.unmodifiableMap(additionalProperties)

                            fun toBuilder() = Builder().from(this)

                            companion object {

                                /**
                                 * Returns a mutable builder for constructing an instance of
                                 * [InnerInfo].
                                 */
                                fun builder() = Builder()
                            }

                            /** A builder for [InnerInfo]. */
                            class Builder internal constructor() {

                                private var active: JsonField<Boolean> = JsonMissing.of()
                                private var name: JsonField<String> = JsonMissing.of()
                                private var ticker: JsonField<String> = JsonMissing.of()
                                private var additionalProperties: MutableMap<String, JsonValue> =
                                    mutableMapOf()

                                internal fun from(innerInfo: InnerInfo) = apply {
                                    active = innerInfo.active
                                    name = innerInfo.name
                                    ticker = innerInfo.ticker
                                    additionalProperties =
                                        innerInfo.additionalProperties.toMutableMap()
                                }

                                /** Whether X marks the SmartTag active. */
                                fun active(active: Boolean) = active(JsonField.of(active))

                                /** Sets [Builder.active] to an arbitrary JSON value. */
                                fun active(active: JsonField<Boolean>) = apply {
                                    this.active = active
                                }

                                fun name(name: String) = name(JsonField.of(name))

                                /** Sets [Builder.name] to an arbitrary JSON value. */
                                fun name(name: JsonField<String>) = apply { this.name = name }

                                fun ticker(ticker: String) = ticker(JsonField.of(ticker))

                                /** Sets [Builder.ticker] to an arbitrary JSON value. */
                                fun ticker(ticker: JsonField<String>) = apply {
                                    this.ticker = ticker
                                }

                                fun additionalProperties(
                                    additionalProperties: Map<String, JsonValue>
                                ) = apply {
                                    this.additionalProperties.clear()
                                    putAllAdditionalProperties(additionalProperties)
                                }

                                fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                                    additionalProperties.put(key, value)
                                }

                                fun putAllAdditionalProperties(
                                    additionalProperties: Map<String, JsonValue>
                                ) = apply {
                                    this.additionalProperties.putAll(additionalProperties)
                                }

                                fun removeAdditionalProperty(key: String) = apply {
                                    additionalProperties.remove(key)
                                }

                                fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                                    keys.forEach(::removeAdditionalProperty)
                                }

                                /**
                                 * Returns an immutable instance of [InnerInfo].
                                 *
                                 * Further updates to this [Builder] will not mutate the returned
                                 * instance.
                                 */
                                fun build(): InnerInfo =
                                    InnerInfo(
                                        active,
                                        name,
                                        ticker,
                                        additionalProperties.toMutableMap(),
                                    )
                            }

                            private var validated: Boolean = false

                            /**
                             * Validates that the types of all values in this object match their
                             * expected types recursively.
                             *
                             * This method is _not_ forwards compatible with new types from the API
                             * for existing fields.
                             *
                             * @throws XTwitterScraperInvalidDataException if any value type in this
                             *   object doesn't match its expected type.
                             */
                            fun validate(): InnerInfo = apply {
                                if (validated) {
                                    return@apply
                                }

                                active()
                                name()
                                ticker()
                                validated = true
                            }

                            fun isValid(): Boolean =
                                try {
                                    validate()
                                    true
                                } catch (e: XTwitterScraperInvalidDataException) {
                                    false
                                }

                            internal fun validity(): Int =
                                (if (active.asKnown() == null) 0 else 1) +
                                    (if (name.asKnown() == null) 0 else 1) +
                                    (if (ticker.asKnown() == null) 0 else 1)

                            override fun equals(other: Any?): Boolean {
                                if (this === other) {
                                    return true
                                }

                                return other is InnerInfo &&
                                    active == other.active &&
                                    name == other.name &&
                                    ticker == other.ticker &&
                                    additionalProperties == other.additionalProperties
                            }

                            private val hashCode: Int by lazy {
                                Objects.hash(active, name, ticker, additionalProperties)
                            }

                            override fun hashCode(): Int = hashCode

                            override fun toString() =
                                "InnerInfo{active=$active, name=$name, ticker=$ticker, additionalProperties=$additionalProperties}"
                        }

                        override fun equals(other: Any?): Boolean {
                            if (this === other) {
                                return true
                            }

                            return other is Info &&
                                info == other.info &&
                                additionalProperties == other.additionalProperties
                        }

                        private val hashCode: Int by lazy {
                            Objects.hash(info, additionalProperties)
                        }

                        override fun hashCode(): Int = hashCode

                        override fun toString() =
                            "Info{info=$info, additionalProperties=$additionalProperties}"
                    }

                    override fun equals(other: Any?): Boolean {
                        if (this === other) {
                            return true
                        }

                        return other is Tag &&
                            id == other.id &&
                            info == other.info &&
                            additionalProperties == other.additionalProperties
                    }

                    private val hashCode: Int by lazy {
                        Objects.hash(id, info, additionalProperties)
                    }

                    override fun hashCode(): Int = hashCode

                    override fun toString() =
                        "Tag{id=$id, info=$info, additionalProperties=$additionalProperties}"
                }

                override fun equals(other: Any?): Boolean {
                    if (this === other) {
                        return true
                    }

                    return other is Hashtag &&
                        text == other.text &&
                        indices == other.indices &&
                        tag == other.tag &&
                        additionalProperties == other.additionalProperties
                }

                private val hashCode: Int by lazy {
                    Objects.hash(text, indices, tag, additionalProperties)
                }

                override fun hashCode(): Int = hashCode

                override fun toString() =
                    "Hashtag{text=$text, indices=$indices, tag=$tag, additionalProperties=$additionalProperties}"
            }

            /** Indexed smart-tag, cashtag, or video timestamp metadata. */
            class Smarttag
            @JsonCreator(mode = JsonCreator.Mode.DISABLED)
            private constructor(
                private val indices: JsonField<List<Long>>,
                private val seconds: JsonField<Double>,
                private val tag: JsonField<Tag>,
                private val text: JsonField<String>,
                private val additionalProperties: MutableMap<String, JsonValue>,
            ) {

                @JsonCreator
                private constructor(
                    @JsonProperty("indices")
                    @ExcludeMissing
                    indices: JsonField<List<Long>> = JsonMissing.of(),
                    @JsonProperty("seconds")
                    @ExcludeMissing
                    seconds: JsonField<Double> = JsonMissing.of(),
                    @JsonProperty("tag") @ExcludeMissing tag: JsonField<Tag> = JsonMissing.of(),
                    @JsonProperty("text")
                    @ExcludeMissing
                    text: JsonField<String> = JsonMissing.of(),
                ) : this(indices, seconds, tag, text, mutableMapOf())

                /**
                 * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected
                 *   type.
                 */
                fun indices(): List<Long>? = indices.getNullable("indices")

                /**
                 * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected
                 *   type.
                 */
                fun seconds(): Double? = seconds.getNullable("seconds")

                /**
                 * X's SmartTag for a cashtag, smarttag or hashtag. Xquik leaves out X's trading
                 * platforms, which differ by reader.
                 *
                 * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected
                 *   type.
                 */
                fun tag(): Tag? = tag.getNullable("tag")

                /**
                 * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected
                 *   type.
                 */
                fun text(): String? = text.getNullable("text")

                /**
                 * Returns the raw JSON value of [indices].
                 *
                 * Unlike [indices], this method doesn't throw if the JSON field has an unexpected
                 * type.
                 */
                @JsonProperty("indices")
                @ExcludeMissing
                fun _indices(): JsonField<List<Long>> = indices

                /**
                 * Returns the raw JSON value of [seconds].
                 *
                 * Unlike [seconds], this method doesn't throw if the JSON field has an unexpected
                 * type.
                 */
                @JsonProperty("seconds") @ExcludeMissing fun _seconds(): JsonField<Double> = seconds

                /**
                 * Returns the raw JSON value of [tag].
                 *
                 * Unlike [tag], this method doesn't throw if the JSON field has an unexpected type.
                 */
                @JsonProperty("tag") @ExcludeMissing fun _tag(): JsonField<Tag> = tag

                /**
                 * Returns the raw JSON value of [text].
                 *
                 * Unlike [text], this method doesn't throw if the JSON field has an unexpected
                 * type.
                 */
                @JsonProperty("text") @ExcludeMissing fun _text(): JsonField<String> = text

                @JsonAnySetter
                private fun putAdditionalProperty(key: String, value: JsonValue) {
                    additionalProperties.put(key, value)
                }

                @JsonAnyGetter
                @ExcludeMissing
                fun _additionalProperties(): Map<String, JsonValue> =
                    Collections.unmodifiableMap(additionalProperties)

                fun toBuilder() = Builder().from(this)

                companion object {

                    /** Returns a mutable builder for constructing an instance of [Smarttag]. */
                    fun builder() = Builder()
                }

                /** A builder for [Smarttag]. */
                class Builder internal constructor() {

                    private var indices: JsonField<MutableList<Long>>? = null
                    private var seconds: JsonField<Double> = JsonMissing.of()
                    private var tag: JsonField<Tag> = JsonMissing.of()
                    private var text: JsonField<String> = JsonMissing.of()
                    private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                    internal fun from(smarttag: Smarttag) = apply {
                        indices = smarttag.indices.map { it.toMutableList() }
                        seconds = smarttag.seconds
                        tag = smarttag.tag
                        text = smarttag.text
                        additionalProperties = smarttag.additionalProperties.toMutableMap()
                    }

                    fun indices(indices: List<Long>) = indices(JsonField.of(indices))

                    /** Sets [Builder.indices] to an arbitrary JSON value. */
                    fun indices(indices: JsonField<List<Long>>) = apply {
                        this.indices = indices.map { it.toMutableList() }
                    }

                    /**
                     * Adds a single [Long] to [indices].
                     *
                     * @throws IllegalStateException if the field was previously set to a non-list.
                     */
                    fun addIndex(index: Long) = apply {
                        indices =
                            (indices ?: JsonField.of(mutableListOf())).also {
                                checkKnown("indices", it).add(index)
                            }
                    }

                    fun seconds(seconds: Double) = seconds(JsonField.of(seconds))

                    /** Sets [Builder.seconds] to an arbitrary JSON value. */
                    fun seconds(seconds: JsonField<Double>) = apply { this.seconds = seconds }

                    /**
                     * X's SmartTag for a cashtag, smarttag or hashtag. Xquik leaves out X's trading
                     * platforms, which differ by reader.
                     */
                    fun tag(tag: Tag) = tag(JsonField.of(tag))

                    /** Sets [Builder.tag] to an arbitrary JSON value. */
                    fun tag(tag: JsonField<Tag>) = apply { this.tag = tag }

                    fun text(text: String) = text(JsonField.of(text))

                    /** Sets [Builder.text] to an arbitrary JSON value. */
                    fun text(text: JsonField<String>) = apply { this.text = text }

                    fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                        this.additionalProperties.clear()
                        putAllAdditionalProperties(additionalProperties)
                    }

                    fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                        additionalProperties.put(key, value)
                    }

                    fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) =
                        apply {
                            this.additionalProperties.putAll(additionalProperties)
                        }

                    fun removeAdditionalProperty(key: String) = apply {
                        additionalProperties.remove(key)
                    }

                    fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                        keys.forEach(::removeAdditionalProperty)
                    }

                    /**
                     * Returns an immutable instance of [Smarttag].
                     *
                     * Further updates to this [Builder] will not mutate the returned instance.
                     */
                    fun build(): Smarttag =
                        Smarttag(
                            (indices ?: JsonMissing.of()).map { it.toImmutable() },
                            seconds,
                            tag,
                            text,
                            additionalProperties.toMutableMap(),
                        )
                }

                private var validated: Boolean = false

                /**
                 * Validates that the types of all values in this object match their expected types
                 * recursively.
                 *
                 * This method is _not_ forwards compatible with new types from the API for existing
                 * fields.
                 *
                 * @throws XTwitterScraperInvalidDataException if any value type in this object
                 *   doesn't match its expected type.
                 */
                fun validate(): Smarttag = apply {
                    if (validated) {
                        return@apply
                    }

                    indices()
                    seconds()
                    tag()?.validate()
                    text()
                    validated = true
                }

                fun isValid(): Boolean =
                    try {
                        validate()
                        true
                    } catch (e: XTwitterScraperInvalidDataException) {
                        false
                    }

                internal fun validity(): Int =
                    (indices.asKnown()?.size ?: 0) +
                        (if (seconds.asKnown() == null) 0 else 1) +
                        (tag.asKnown()?.validity() ?: 0) +
                        (if (text.asKnown() == null) 0 else 1)

                /**
                 * X's SmartTag for a cashtag, smarttag or hashtag. Xquik leaves out X's trading
                 * platforms, which differ by reader.
                 */
                class Tag
                @JsonCreator(mode = JsonCreator.Mode.DISABLED)
                private constructor(
                    private val id: JsonField<String>,
                    private val info: JsonField<Info>,
                    private val additionalProperties: MutableMap<String, JsonValue>,
                ) {

                    @JsonCreator
                    private constructor(
                        @JsonProperty("id")
                        @ExcludeMissing
                        id: JsonField<String> = JsonMissing.of(),
                        @JsonProperty("info")
                        @ExcludeMissing
                        info: JsonField<Info> = JsonMissing.of(),
                    ) : this(id, info, mutableMapOf())

                    /**
                     * X's ID for the SmartTag.
                     *
                     * @throws XTwitterScraperInvalidDataException if the JSON field has an
                     *   unexpected type.
                     */
                    fun id(): String? = id.getNullable("id")

                    /**
                     * @throws XTwitterScraperInvalidDataException if the JSON field has an
                     *   unexpected type.
                     */
                    fun info(): Info? = info.getNullable("info")

                    /**
                     * Returns the raw JSON value of [id].
                     *
                     * Unlike [id], this method doesn't throw if the JSON field has an unexpected
                     * type.
                     */
                    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

                    /**
                     * Returns the raw JSON value of [info].
                     *
                     * Unlike [info], this method doesn't throw if the JSON field has an unexpected
                     * type.
                     */
                    @JsonProperty("info") @ExcludeMissing fun _info(): JsonField<Info> = info

                    @JsonAnySetter
                    private fun putAdditionalProperty(key: String, value: JsonValue) {
                        additionalProperties.put(key, value)
                    }

                    @JsonAnyGetter
                    @ExcludeMissing
                    fun _additionalProperties(): Map<String, JsonValue> =
                        Collections.unmodifiableMap(additionalProperties)

                    fun toBuilder() = Builder().from(this)

                    companion object {

                        /** Returns a mutable builder for constructing an instance of [Tag]. */
                        fun builder() = Builder()
                    }

                    /** A builder for [Tag]. */
                    class Builder internal constructor() {

                        private var id: JsonField<String> = JsonMissing.of()
                        private var info: JsonField<Info> = JsonMissing.of()
                        private var additionalProperties: MutableMap<String, JsonValue> =
                            mutableMapOf()

                        internal fun from(tag: Tag) = apply {
                            id = tag.id
                            info = tag.info
                            additionalProperties = tag.additionalProperties.toMutableMap()
                        }

                        /** X's ID for the SmartTag. */
                        fun id(id: String) = id(JsonField.of(id))

                        /** Sets [Builder.id] to an arbitrary JSON value. */
                        fun id(id: JsonField<String>) = apply { this.id = id }

                        fun info(info: Info) = info(JsonField.of(info))

                        /** Sets [Builder.info] to an arbitrary JSON value. */
                        fun info(info: JsonField<Info>) = apply { this.info = info }

                        fun additionalProperties(additionalProperties: Map<String, JsonValue>) =
                            apply {
                                this.additionalProperties.clear()
                                putAllAdditionalProperties(additionalProperties)
                            }

                        fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                            additionalProperties.put(key, value)
                        }

                        fun putAllAdditionalProperties(
                            additionalProperties: Map<String, JsonValue>
                        ) = apply {
                            this.additionalProperties.putAll(additionalProperties)
                        }

                        fun removeAdditionalProperty(key: String) = apply {
                            additionalProperties.remove(key)
                        }

                        fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                            keys.forEach(::removeAdditionalProperty)
                        }

                        /**
                         * Returns an immutable instance of [Tag].
                         *
                         * Further updates to this [Builder] will not mutate the returned instance.
                         */
                        fun build(): Tag = Tag(id, info, additionalProperties.toMutableMap())
                    }

                    private var validated: Boolean = false

                    /**
                     * Validates that the types of all values in this object match their expected
                     * types recursively.
                     *
                     * This method is _not_ forwards compatible with new types from the API for
                     * existing fields.
                     *
                     * @throws XTwitterScraperInvalidDataException if any value type in this object
                     *   doesn't match its expected type.
                     */
                    fun validate(): Tag = apply {
                        if (validated) {
                            return@apply
                        }

                        id()
                        info()?.validate()
                        validated = true
                    }

                    fun isValid(): Boolean =
                        try {
                            validate()
                            true
                        } catch (e: XTwitterScraperInvalidDataException) {
                            false
                        }

                    internal fun validity(): Int =
                        (if (id.asKnown() == null) 0 else 1) + (info.asKnown()?.validity() ?: 0)

                    class Info
                    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
                    private constructor(
                        private val info: JsonField<InnerInfo>,
                        private val additionalProperties: MutableMap<String, JsonValue>,
                    ) {

                        @JsonCreator
                        private constructor(
                            @JsonProperty("info")
                            @ExcludeMissing
                            info: JsonField<InnerInfo> = JsonMissing.of()
                        ) : this(info, mutableMapOf())

                        /**
                         * @throws XTwitterScraperInvalidDataException if the JSON field has an
                         *   unexpected type.
                         */
                        fun info(): InnerInfo? = info.getNullable("info")

                        /**
                         * Returns the raw JSON value of [info].
                         *
                         * Unlike [info], this method doesn't throw if the JSON field has an
                         * unexpected type.
                         */
                        @JsonProperty("info")
                        @ExcludeMissing
                        fun _info(): JsonField<InnerInfo> = info

                        @JsonAnySetter
                        private fun putAdditionalProperty(key: String, value: JsonValue) {
                            additionalProperties.put(key, value)
                        }

                        @JsonAnyGetter
                        @ExcludeMissing
                        fun _additionalProperties(): Map<String, JsonValue> =
                            Collections.unmodifiableMap(additionalProperties)

                        fun toBuilder() = Builder().from(this)

                        companion object {

                            /** Returns a mutable builder for constructing an instance of [Info]. */
                            fun builder() = Builder()
                        }

                        /** A builder for [Info]. */
                        class Builder internal constructor() {

                            private var info: JsonField<InnerInfo> = JsonMissing.of()
                            private var additionalProperties: MutableMap<String, JsonValue> =
                                mutableMapOf()

                            internal fun from(info: Info) = apply {
                                this.info = info.info
                                additionalProperties = info.additionalProperties.toMutableMap()
                            }

                            fun info(info: InnerInfo) = info(JsonField.of(info))

                            /** Sets [Builder.info] to an arbitrary JSON value. */
                            fun info(info: JsonField<InnerInfo>) = apply { this.info = info }

                            fun additionalProperties(additionalProperties: Map<String, JsonValue>) =
                                apply {
                                    this.additionalProperties.clear()
                                    putAllAdditionalProperties(additionalProperties)
                                }

                            fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                                additionalProperties.put(key, value)
                            }

                            fun putAllAdditionalProperties(
                                additionalProperties: Map<String, JsonValue>
                            ) = apply {
                                this.additionalProperties.putAll(additionalProperties)
                            }

                            fun removeAdditionalProperty(key: String) = apply {
                                additionalProperties.remove(key)
                            }

                            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                                keys.forEach(::removeAdditionalProperty)
                            }

                            /**
                             * Returns an immutable instance of [Info].
                             *
                             * Further updates to this [Builder] will not mutate the returned
                             * instance.
                             */
                            fun build(): Info = Info(info, additionalProperties.toMutableMap())
                        }

                        private var validated: Boolean = false

                        /**
                         * Validates that the types of all values in this object match their
                         * expected types recursively.
                         *
                         * This method is _not_ forwards compatible with new types from the API for
                         * existing fields.
                         *
                         * @throws XTwitterScraperInvalidDataException if any value type in this
                         *   object doesn't match its expected type.
                         */
                        fun validate(): Info = apply {
                            if (validated) {
                                return@apply
                            }

                            info()?.validate()
                            validated = true
                        }

                        fun isValid(): Boolean =
                            try {
                                validate()
                                true
                            } catch (e: XTwitterScraperInvalidDataException) {
                                false
                            }

                        internal fun validity(): Int = (info.asKnown()?.validity() ?: 0)

                        class InnerInfo
                        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
                        private constructor(
                            private val active: JsonField<Boolean>,
                            private val name: JsonField<String>,
                            private val ticker: JsonField<String>,
                            private val additionalProperties: MutableMap<String, JsonValue>,
                        ) {

                            @JsonCreator
                            private constructor(
                                @JsonProperty("active")
                                @ExcludeMissing
                                active: JsonField<Boolean> = JsonMissing.of(),
                                @JsonProperty("name")
                                @ExcludeMissing
                                name: JsonField<String> = JsonMissing.of(),
                                @JsonProperty("ticker")
                                @ExcludeMissing
                                ticker: JsonField<String> = JsonMissing.of(),
                            ) : this(active, name, ticker, mutableMapOf())

                            /**
                             * Whether X marks the SmartTag active.
                             *
                             * @throws XTwitterScraperInvalidDataException if the JSON field has an
                             *   unexpected type.
                             */
                            fun active(): Boolean? = active.getNullable("active")

                            /**
                             * @throws XTwitterScraperInvalidDataException if the JSON field has an
                             *   unexpected type.
                             */
                            fun name(): String? = name.getNullable("name")

                            /**
                             * @throws XTwitterScraperInvalidDataException if the JSON field has an
                             *   unexpected type.
                             */
                            fun ticker(): String? = ticker.getNullable("ticker")

                            /**
                             * Returns the raw JSON value of [active].
                             *
                             * Unlike [active], this method doesn't throw if the JSON field has an
                             * unexpected type.
                             */
                            @JsonProperty("active")
                            @ExcludeMissing
                            fun _active(): JsonField<Boolean> = active

                            /**
                             * Returns the raw JSON value of [name].
                             *
                             * Unlike [name], this method doesn't throw if the JSON field has an
                             * unexpected type.
                             */
                            @JsonProperty("name")
                            @ExcludeMissing
                            fun _name(): JsonField<String> = name

                            /**
                             * Returns the raw JSON value of [ticker].
                             *
                             * Unlike [ticker], this method doesn't throw if the JSON field has an
                             * unexpected type.
                             */
                            @JsonProperty("ticker")
                            @ExcludeMissing
                            fun _ticker(): JsonField<String> = ticker

                            @JsonAnySetter
                            private fun putAdditionalProperty(key: String, value: JsonValue) {
                                additionalProperties.put(key, value)
                            }

                            @JsonAnyGetter
                            @ExcludeMissing
                            fun _additionalProperties(): Map<String, JsonValue> =
                                Collections.unmodifiableMap(additionalProperties)

                            fun toBuilder() = Builder().from(this)

                            companion object {

                                /**
                                 * Returns a mutable builder for constructing an instance of
                                 * [InnerInfo].
                                 */
                                fun builder() = Builder()
                            }

                            /** A builder for [InnerInfo]. */
                            class Builder internal constructor() {

                                private var active: JsonField<Boolean> = JsonMissing.of()
                                private var name: JsonField<String> = JsonMissing.of()
                                private var ticker: JsonField<String> = JsonMissing.of()
                                private var additionalProperties: MutableMap<String, JsonValue> =
                                    mutableMapOf()

                                internal fun from(innerInfo: InnerInfo) = apply {
                                    active = innerInfo.active
                                    name = innerInfo.name
                                    ticker = innerInfo.ticker
                                    additionalProperties =
                                        innerInfo.additionalProperties.toMutableMap()
                                }

                                /** Whether X marks the SmartTag active. */
                                fun active(active: Boolean) = active(JsonField.of(active))

                                /** Sets [Builder.active] to an arbitrary JSON value. */
                                fun active(active: JsonField<Boolean>) = apply {
                                    this.active = active
                                }

                                fun name(name: String) = name(JsonField.of(name))

                                /** Sets [Builder.name] to an arbitrary JSON value. */
                                fun name(name: JsonField<String>) = apply { this.name = name }

                                fun ticker(ticker: String) = ticker(JsonField.of(ticker))

                                /** Sets [Builder.ticker] to an arbitrary JSON value. */
                                fun ticker(ticker: JsonField<String>) = apply {
                                    this.ticker = ticker
                                }

                                fun additionalProperties(
                                    additionalProperties: Map<String, JsonValue>
                                ) = apply {
                                    this.additionalProperties.clear()
                                    putAllAdditionalProperties(additionalProperties)
                                }

                                fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                                    additionalProperties.put(key, value)
                                }

                                fun putAllAdditionalProperties(
                                    additionalProperties: Map<String, JsonValue>
                                ) = apply {
                                    this.additionalProperties.putAll(additionalProperties)
                                }

                                fun removeAdditionalProperty(key: String) = apply {
                                    additionalProperties.remove(key)
                                }

                                fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                                    keys.forEach(::removeAdditionalProperty)
                                }

                                /**
                                 * Returns an immutable instance of [InnerInfo].
                                 *
                                 * Further updates to this [Builder] will not mutate the returned
                                 * instance.
                                 */
                                fun build(): InnerInfo =
                                    InnerInfo(
                                        active,
                                        name,
                                        ticker,
                                        additionalProperties.toMutableMap(),
                                    )
                            }

                            private var validated: Boolean = false

                            /**
                             * Validates that the types of all values in this object match their
                             * expected types recursively.
                             *
                             * This method is _not_ forwards compatible with new types from the API
                             * for existing fields.
                             *
                             * @throws XTwitterScraperInvalidDataException if any value type in this
                             *   object doesn't match its expected type.
                             */
                            fun validate(): InnerInfo = apply {
                                if (validated) {
                                    return@apply
                                }

                                active()
                                name()
                                ticker()
                                validated = true
                            }

                            fun isValid(): Boolean =
                                try {
                                    validate()
                                    true
                                } catch (e: XTwitterScraperInvalidDataException) {
                                    false
                                }

                            internal fun validity(): Int =
                                (if (active.asKnown() == null) 0 else 1) +
                                    (if (name.asKnown() == null) 0 else 1) +
                                    (if (ticker.asKnown() == null) 0 else 1)

                            override fun equals(other: Any?): Boolean {
                                if (this === other) {
                                    return true
                                }

                                return other is InnerInfo &&
                                    active == other.active &&
                                    name == other.name &&
                                    ticker == other.ticker &&
                                    additionalProperties == other.additionalProperties
                            }

                            private val hashCode: Int by lazy {
                                Objects.hash(active, name, ticker, additionalProperties)
                            }

                            override fun hashCode(): Int = hashCode

                            override fun toString() =
                                "InnerInfo{active=$active, name=$name, ticker=$ticker, additionalProperties=$additionalProperties}"
                        }

                        override fun equals(other: Any?): Boolean {
                            if (this === other) {
                                return true
                            }

                            return other is Info &&
                                info == other.info &&
                                additionalProperties == other.additionalProperties
                        }

                        private val hashCode: Int by lazy {
                            Objects.hash(info, additionalProperties)
                        }

                        override fun hashCode(): Int = hashCode

                        override fun toString() =
                            "Info{info=$info, additionalProperties=$additionalProperties}"
                    }

                    override fun equals(other: Any?): Boolean {
                        if (this === other) {
                            return true
                        }

                        return other is Tag &&
                            id == other.id &&
                            info == other.info &&
                            additionalProperties == other.additionalProperties
                    }

                    private val hashCode: Int by lazy {
                        Objects.hash(id, info, additionalProperties)
                    }

                    override fun hashCode(): Int = hashCode

                    override fun toString() =
                        "Tag{id=$id, info=$info, additionalProperties=$additionalProperties}"
                }

                override fun equals(other: Any?): Boolean {
                    if (this === other) {
                        return true
                    }

                    return other is Smarttag &&
                        indices == other.indices &&
                        seconds == other.seconds &&
                        tag == other.tag &&
                        text == other.text &&
                        additionalProperties == other.additionalProperties
                }

                private val hashCode: Int by lazy {
                    Objects.hash(indices, seconds, tag, text, additionalProperties)
                }

                override fun hashCode(): Int = hashCode

                override fun toString() =
                    "Smarttag{indices=$indices, seconds=$seconds, tag=$tag, text=$text, additionalProperties=$additionalProperties}"
            }

            /** Indexed smart-tag, cashtag, or video timestamp metadata. */
            class Symbol
            @JsonCreator(mode = JsonCreator.Mode.DISABLED)
            private constructor(
                private val indices: JsonField<List<Long>>,
                private val seconds: JsonField<Double>,
                private val tag: JsonField<Tag>,
                private val text: JsonField<String>,
                private val additionalProperties: MutableMap<String, JsonValue>,
            ) {

                @JsonCreator
                private constructor(
                    @JsonProperty("indices")
                    @ExcludeMissing
                    indices: JsonField<List<Long>> = JsonMissing.of(),
                    @JsonProperty("seconds")
                    @ExcludeMissing
                    seconds: JsonField<Double> = JsonMissing.of(),
                    @JsonProperty("tag") @ExcludeMissing tag: JsonField<Tag> = JsonMissing.of(),
                    @JsonProperty("text")
                    @ExcludeMissing
                    text: JsonField<String> = JsonMissing.of(),
                ) : this(indices, seconds, tag, text, mutableMapOf())

                /**
                 * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected
                 *   type.
                 */
                fun indices(): List<Long>? = indices.getNullable("indices")

                /**
                 * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected
                 *   type.
                 */
                fun seconds(): Double? = seconds.getNullable("seconds")

                /**
                 * X's SmartTag for a cashtag, smarttag or hashtag. Xquik leaves out X's trading
                 * platforms, which differ by reader.
                 *
                 * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected
                 *   type.
                 */
                fun tag(): Tag? = tag.getNullable("tag")

                /**
                 * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected
                 *   type.
                 */
                fun text(): String? = text.getNullable("text")

                /**
                 * Returns the raw JSON value of [indices].
                 *
                 * Unlike [indices], this method doesn't throw if the JSON field has an unexpected
                 * type.
                 */
                @JsonProperty("indices")
                @ExcludeMissing
                fun _indices(): JsonField<List<Long>> = indices

                /**
                 * Returns the raw JSON value of [seconds].
                 *
                 * Unlike [seconds], this method doesn't throw if the JSON field has an unexpected
                 * type.
                 */
                @JsonProperty("seconds") @ExcludeMissing fun _seconds(): JsonField<Double> = seconds

                /**
                 * Returns the raw JSON value of [tag].
                 *
                 * Unlike [tag], this method doesn't throw if the JSON field has an unexpected type.
                 */
                @JsonProperty("tag") @ExcludeMissing fun _tag(): JsonField<Tag> = tag

                /**
                 * Returns the raw JSON value of [text].
                 *
                 * Unlike [text], this method doesn't throw if the JSON field has an unexpected
                 * type.
                 */
                @JsonProperty("text") @ExcludeMissing fun _text(): JsonField<String> = text

                @JsonAnySetter
                private fun putAdditionalProperty(key: String, value: JsonValue) {
                    additionalProperties.put(key, value)
                }

                @JsonAnyGetter
                @ExcludeMissing
                fun _additionalProperties(): Map<String, JsonValue> =
                    Collections.unmodifiableMap(additionalProperties)

                fun toBuilder() = Builder().from(this)

                companion object {

                    /** Returns a mutable builder for constructing an instance of [Symbol]. */
                    fun builder() = Builder()
                }

                /** A builder for [Symbol]. */
                class Builder internal constructor() {

                    private var indices: JsonField<MutableList<Long>>? = null
                    private var seconds: JsonField<Double> = JsonMissing.of()
                    private var tag: JsonField<Tag> = JsonMissing.of()
                    private var text: JsonField<String> = JsonMissing.of()
                    private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                    internal fun from(symbol: Symbol) = apply {
                        indices = symbol.indices.map { it.toMutableList() }
                        seconds = symbol.seconds
                        tag = symbol.tag
                        text = symbol.text
                        additionalProperties = symbol.additionalProperties.toMutableMap()
                    }

                    fun indices(indices: List<Long>) = indices(JsonField.of(indices))

                    /** Sets [Builder.indices] to an arbitrary JSON value. */
                    fun indices(indices: JsonField<List<Long>>) = apply {
                        this.indices = indices.map { it.toMutableList() }
                    }

                    /**
                     * Adds a single [Long] to [indices].
                     *
                     * @throws IllegalStateException if the field was previously set to a non-list.
                     */
                    fun addIndex(index: Long) = apply {
                        indices =
                            (indices ?: JsonField.of(mutableListOf())).also {
                                checkKnown("indices", it).add(index)
                            }
                    }

                    fun seconds(seconds: Double) = seconds(JsonField.of(seconds))

                    /** Sets [Builder.seconds] to an arbitrary JSON value. */
                    fun seconds(seconds: JsonField<Double>) = apply { this.seconds = seconds }

                    /**
                     * X's SmartTag for a cashtag, smarttag or hashtag. Xquik leaves out X's trading
                     * platforms, which differ by reader.
                     */
                    fun tag(tag: Tag) = tag(JsonField.of(tag))

                    /** Sets [Builder.tag] to an arbitrary JSON value. */
                    fun tag(tag: JsonField<Tag>) = apply { this.tag = tag }

                    fun text(text: String) = text(JsonField.of(text))

                    /** Sets [Builder.text] to an arbitrary JSON value. */
                    fun text(text: JsonField<String>) = apply { this.text = text }

                    fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                        this.additionalProperties.clear()
                        putAllAdditionalProperties(additionalProperties)
                    }

                    fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                        additionalProperties.put(key, value)
                    }

                    fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) =
                        apply {
                            this.additionalProperties.putAll(additionalProperties)
                        }

                    fun removeAdditionalProperty(key: String) = apply {
                        additionalProperties.remove(key)
                    }

                    fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                        keys.forEach(::removeAdditionalProperty)
                    }

                    /**
                     * Returns an immutable instance of [Symbol].
                     *
                     * Further updates to this [Builder] will not mutate the returned instance.
                     */
                    fun build(): Symbol =
                        Symbol(
                            (indices ?: JsonMissing.of()).map { it.toImmutable() },
                            seconds,
                            tag,
                            text,
                            additionalProperties.toMutableMap(),
                        )
                }

                private var validated: Boolean = false

                /**
                 * Validates that the types of all values in this object match their expected types
                 * recursively.
                 *
                 * This method is _not_ forwards compatible with new types from the API for existing
                 * fields.
                 *
                 * @throws XTwitterScraperInvalidDataException if any value type in this object
                 *   doesn't match its expected type.
                 */
                fun validate(): Symbol = apply {
                    if (validated) {
                        return@apply
                    }

                    indices()
                    seconds()
                    tag()?.validate()
                    text()
                    validated = true
                }

                fun isValid(): Boolean =
                    try {
                        validate()
                        true
                    } catch (e: XTwitterScraperInvalidDataException) {
                        false
                    }

                internal fun validity(): Int =
                    (indices.asKnown()?.size ?: 0) +
                        (if (seconds.asKnown() == null) 0 else 1) +
                        (tag.asKnown()?.validity() ?: 0) +
                        (if (text.asKnown() == null) 0 else 1)

                /**
                 * X's SmartTag for a cashtag, smarttag or hashtag. Xquik leaves out X's trading
                 * platforms, which differ by reader.
                 */
                class Tag
                @JsonCreator(mode = JsonCreator.Mode.DISABLED)
                private constructor(
                    private val id: JsonField<String>,
                    private val info: JsonField<Info>,
                    private val additionalProperties: MutableMap<String, JsonValue>,
                ) {

                    @JsonCreator
                    private constructor(
                        @JsonProperty("id")
                        @ExcludeMissing
                        id: JsonField<String> = JsonMissing.of(),
                        @JsonProperty("info")
                        @ExcludeMissing
                        info: JsonField<Info> = JsonMissing.of(),
                    ) : this(id, info, mutableMapOf())

                    /**
                     * X's ID for the SmartTag.
                     *
                     * @throws XTwitterScraperInvalidDataException if the JSON field has an
                     *   unexpected type.
                     */
                    fun id(): String? = id.getNullable("id")

                    /**
                     * @throws XTwitterScraperInvalidDataException if the JSON field has an
                     *   unexpected type.
                     */
                    fun info(): Info? = info.getNullable("info")

                    /**
                     * Returns the raw JSON value of [id].
                     *
                     * Unlike [id], this method doesn't throw if the JSON field has an unexpected
                     * type.
                     */
                    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

                    /**
                     * Returns the raw JSON value of [info].
                     *
                     * Unlike [info], this method doesn't throw if the JSON field has an unexpected
                     * type.
                     */
                    @JsonProperty("info") @ExcludeMissing fun _info(): JsonField<Info> = info

                    @JsonAnySetter
                    private fun putAdditionalProperty(key: String, value: JsonValue) {
                        additionalProperties.put(key, value)
                    }

                    @JsonAnyGetter
                    @ExcludeMissing
                    fun _additionalProperties(): Map<String, JsonValue> =
                        Collections.unmodifiableMap(additionalProperties)

                    fun toBuilder() = Builder().from(this)

                    companion object {

                        /** Returns a mutable builder for constructing an instance of [Tag]. */
                        fun builder() = Builder()
                    }

                    /** A builder for [Tag]. */
                    class Builder internal constructor() {

                        private var id: JsonField<String> = JsonMissing.of()
                        private var info: JsonField<Info> = JsonMissing.of()
                        private var additionalProperties: MutableMap<String, JsonValue> =
                            mutableMapOf()

                        internal fun from(tag: Tag) = apply {
                            id = tag.id
                            info = tag.info
                            additionalProperties = tag.additionalProperties.toMutableMap()
                        }

                        /** X's ID for the SmartTag. */
                        fun id(id: String) = id(JsonField.of(id))

                        /** Sets [Builder.id] to an arbitrary JSON value. */
                        fun id(id: JsonField<String>) = apply { this.id = id }

                        fun info(info: Info) = info(JsonField.of(info))

                        /** Sets [Builder.info] to an arbitrary JSON value. */
                        fun info(info: JsonField<Info>) = apply { this.info = info }

                        fun additionalProperties(additionalProperties: Map<String, JsonValue>) =
                            apply {
                                this.additionalProperties.clear()
                                putAllAdditionalProperties(additionalProperties)
                            }

                        fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                            additionalProperties.put(key, value)
                        }

                        fun putAllAdditionalProperties(
                            additionalProperties: Map<String, JsonValue>
                        ) = apply {
                            this.additionalProperties.putAll(additionalProperties)
                        }

                        fun removeAdditionalProperty(key: String) = apply {
                            additionalProperties.remove(key)
                        }

                        fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                            keys.forEach(::removeAdditionalProperty)
                        }

                        /**
                         * Returns an immutable instance of [Tag].
                         *
                         * Further updates to this [Builder] will not mutate the returned instance.
                         */
                        fun build(): Tag = Tag(id, info, additionalProperties.toMutableMap())
                    }

                    private var validated: Boolean = false

                    /**
                     * Validates that the types of all values in this object match their expected
                     * types recursively.
                     *
                     * This method is _not_ forwards compatible with new types from the API for
                     * existing fields.
                     *
                     * @throws XTwitterScraperInvalidDataException if any value type in this object
                     *   doesn't match its expected type.
                     */
                    fun validate(): Tag = apply {
                        if (validated) {
                            return@apply
                        }

                        id()
                        info()?.validate()
                        validated = true
                    }

                    fun isValid(): Boolean =
                        try {
                            validate()
                            true
                        } catch (e: XTwitterScraperInvalidDataException) {
                            false
                        }

                    internal fun validity(): Int =
                        (if (id.asKnown() == null) 0 else 1) + (info.asKnown()?.validity() ?: 0)

                    class Info
                    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
                    private constructor(
                        private val info: JsonField<InnerInfo>,
                        private val additionalProperties: MutableMap<String, JsonValue>,
                    ) {

                        @JsonCreator
                        private constructor(
                            @JsonProperty("info")
                            @ExcludeMissing
                            info: JsonField<InnerInfo> = JsonMissing.of()
                        ) : this(info, mutableMapOf())

                        /**
                         * @throws XTwitterScraperInvalidDataException if the JSON field has an
                         *   unexpected type.
                         */
                        fun info(): InnerInfo? = info.getNullable("info")

                        /**
                         * Returns the raw JSON value of [info].
                         *
                         * Unlike [info], this method doesn't throw if the JSON field has an
                         * unexpected type.
                         */
                        @JsonProperty("info")
                        @ExcludeMissing
                        fun _info(): JsonField<InnerInfo> = info

                        @JsonAnySetter
                        private fun putAdditionalProperty(key: String, value: JsonValue) {
                            additionalProperties.put(key, value)
                        }

                        @JsonAnyGetter
                        @ExcludeMissing
                        fun _additionalProperties(): Map<String, JsonValue> =
                            Collections.unmodifiableMap(additionalProperties)

                        fun toBuilder() = Builder().from(this)

                        companion object {

                            /** Returns a mutable builder for constructing an instance of [Info]. */
                            fun builder() = Builder()
                        }

                        /** A builder for [Info]. */
                        class Builder internal constructor() {

                            private var info: JsonField<InnerInfo> = JsonMissing.of()
                            private var additionalProperties: MutableMap<String, JsonValue> =
                                mutableMapOf()

                            internal fun from(info: Info) = apply {
                                this.info = info.info
                                additionalProperties = info.additionalProperties.toMutableMap()
                            }

                            fun info(info: InnerInfo) = info(JsonField.of(info))

                            /** Sets [Builder.info] to an arbitrary JSON value. */
                            fun info(info: JsonField<InnerInfo>) = apply { this.info = info }

                            fun additionalProperties(additionalProperties: Map<String, JsonValue>) =
                                apply {
                                    this.additionalProperties.clear()
                                    putAllAdditionalProperties(additionalProperties)
                                }

                            fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                                additionalProperties.put(key, value)
                            }

                            fun putAllAdditionalProperties(
                                additionalProperties: Map<String, JsonValue>
                            ) = apply {
                                this.additionalProperties.putAll(additionalProperties)
                            }

                            fun removeAdditionalProperty(key: String) = apply {
                                additionalProperties.remove(key)
                            }

                            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                                keys.forEach(::removeAdditionalProperty)
                            }

                            /**
                             * Returns an immutable instance of [Info].
                             *
                             * Further updates to this [Builder] will not mutate the returned
                             * instance.
                             */
                            fun build(): Info = Info(info, additionalProperties.toMutableMap())
                        }

                        private var validated: Boolean = false

                        /**
                         * Validates that the types of all values in this object match their
                         * expected types recursively.
                         *
                         * This method is _not_ forwards compatible with new types from the API for
                         * existing fields.
                         *
                         * @throws XTwitterScraperInvalidDataException if any value type in this
                         *   object doesn't match its expected type.
                         */
                        fun validate(): Info = apply {
                            if (validated) {
                                return@apply
                            }

                            info()?.validate()
                            validated = true
                        }

                        fun isValid(): Boolean =
                            try {
                                validate()
                                true
                            } catch (e: XTwitterScraperInvalidDataException) {
                                false
                            }

                        internal fun validity(): Int = (info.asKnown()?.validity() ?: 0)

                        class InnerInfo
                        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
                        private constructor(
                            private val active: JsonField<Boolean>,
                            private val name: JsonField<String>,
                            private val ticker: JsonField<String>,
                            private val additionalProperties: MutableMap<String, JsonValue>,
                        ) {

                            @JsonCreator
                            private constructor(
                                @JsonProperty("active")
                                @ExcludeMissing
                                active: JsonField<Boolean> = JsonMissing.of(),
                                @JsonProperty("name")
                                @ExcludeMissing
                                name: JsonField<String> = JsonMissing.of(),
                                @JsonProperty("ticker")
                                @ExcludeMissing
                                ticker: JsonField<String> = JsonMissing.of(),
                            ) : this(active, name, ticker, mutableMapOf())

                            /**
                             * Whether X marks the SmartTag active.
                             *
                             * @throws XTwitterScraperInvalidDataException if the JSON field has an
                             *   unexpected type.
                             */
                            fun active(): Boolean? = active.getNullable("active")

                            /**
                             * @throws XTwitterScraperInvalidDataException if the JSON field has an
                             *   unexpected type.
                             */
                            fun name(): String? = name.getNullable("name")

                            /**
                             * @throws XTwitterScraperInvalidDataException if the JSON field has an
                             *   unexpected type.
                             */
                            fun ticker(): String? = ticker.getNullable("ticker")

                            /**
                             * Returns the raw JSON value of [active].
                             *
                             * Unlike [active], this method doesn't throw if the JSON field has an
                             * unexpected type.
                             */
                            @JsonProperty("active")
                            @ExcludeMissing
                            fun _active(): JsonField<Boolean> = active

                            /**
                             * Returns the raw JSON value of [name].
                             *
                             * Unlike [name], this method doesn't throw if the JSON field has an
                             * unexpected type.
                             */
                            @JsonProperty("name")
                            @ExcludeMissing
                            fun _name(): JsonField<String> = name

                            /**
                             * Returns the raw JSON value of [ticker].
                             *
                             * Unlike [ticker], this method doesn't throw if the JSON field has an
                             * unexpected type.
                             */
                            @JsonProperty("ticker")
                            @ExcludeMissing
                            fun _ticker(): JsonField<String> = ticker

                            @JsonAnySetter
                            private fun putAdditionalProperty(key: String, value: JsonValue) {
                                additionalProperties.put(key, value)
                            }

                            @JsonAnyGetter
                            @ExcludeMissing
                            fun _additionalProperties(): Map<String, JsonValue> =
                                Collections.unmodifiableMap(additionalProperties)

                            fun toBuilder() = Builder().from(this)

                            companion object {

                                /**
                                 * Returns a mutable builder for constructing an instance of
                                 * [InnerInfo].
                                 */
                                fun builder() = Builder()
                            }

                            /** A builder for [InnerInfo]. */
                            class Builder internal constructor() {

                                private var active: JsonField<Boolean> = JsonMissing.of()
                                private var name: JsonField<String> = JsonMissing.of()
                                private var ticker: JsonField<String> = JsonMissing.of()
                                private var additionalProperties: MutableMap<String, JsonValue> =
                                    mutableMapOf()

                                internal fun from(innerInfo: InnerInfo) = apply {
                                    active = innerInfo.active
                                    name = innerInfo.name
                                    ticker = innerInfo.ticker
                                    additionalProperties =
                                        innerInfo.additionalProperties.toMutableMap()
                                }

                                /** Whether X marks the SmartTag active. */
                                fun active(active: Boolean) = active(JsonField.of(active))

                                /** Sets [Builder.active] to an arbitrary JSON value. */
                                fun active(active: JsonField<Boolean>) = apply {
                                    this.active = active
                                }

                                fun name(name: String) = name(JsonField.of(name))

                                /** Sets [Builder.name] to an arbitrary JSON value. */
                                fun name(name: JsonField<String>) = apply { this.name = name }

                                fun ticker(ticker: String) = ticker(JsonField.of(ticker))

                                /** Sets [Builder.ticker] to an arbitrary JSON value. */
                                fun ticker(ticker: JsonField<String>) = apply {
                                    this.ticker = ticker
                                }

                                fun additionalProperties(
                                    additionalProperties: Map<String, JsonValue>
                                ) = apply {
                                    this.additionalProperties.clear()
                                    putAllAdditionalProperties(additionalProperties)
                                }

                                fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                                    additionalProperties.put(key, value)
                                }

                                fun putAllAdditionalProperties(
                                    additionalProperties: Map<String, JsonValue>
                                ) = apply {
                                    this.additionalProperties.putAll(additionalProperties)
                                }

                                fun removeAdditionalProperty(key: String) = apply {
                                    additionalProperties.remove(key)
                                }

                                fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                                    keys.forEach(::removeAdditionalProperty)
                                }

                                /**
                                 * Returns an immutable instance of [InnerInfo].
                                 *
                                 * Further updates to this [Builder] will not mutate the returned
                                 * instance.
                                 */
                                fun build(): InnerInfo =
                                    InnerInfo(
                                        active,
                                        name,
                                        ticker,
                                        additionalProperties.toMutableMap(),
                                    )
                            }

                            private var validated: Boolean = false

                            /**
                             * Validates that the types of all values in this object match their
                             * expected types recursively.
                             *
                             * This method is _not_ forwards compatible with new types from the API
                             * for existing fields.
                             *
                             * @throws XTwitterScraperInvalidDataException if any value type in this
                             *   object doesn't match its expected type.
                             */
                            fun validate(): InnerInfo = apply {
                                if (validated) {
                                    return@apply
                                }

                                active()
                                name()
                                ticker()
                                validated = true
                            }

                            fun isValid(): Boolean =
                                try {
                                    validate()
                                    true
                                } catch (e: XTwitterScraperInvalidDataException) {
                                    false
                                }

                            internal fun validity(): Int =
                                (if (active.asKnown() == null) 0 else 1) +
                                    (if (name.asKnown() == null) 0 else 1) +
                                    (if (ticker.asKnown() == null) 0 else 1)

                            override fun equals(other: Any?): Boolean {
                                if (this === other) {
                                    return true
                                }

                                return other is InnerInfo &&
                                    active == other.active &&
                                    name == other.name &&
                                    ticker == other.ticker &&
                                    additionalProperties == other.additionalProperties
                            }

                            private val hashCode: Int by lazy {
                                Objects.hash(active, name, ticker, additionalProperties)
                            }

                            override fun hashCode(): Int = hashCode

                            override fun toString() =
                                "InnerInfo{active=$active, name=$name, ticker=$ticker, additionalProperties=$additionalProperties}"
                        }

                        override fun equals(other: Any?): Boolean {
                            if (this === other) {
                                return true
                            }

                            return other is Info &&
                                info == other.info &&
                                additionalProperties == other.additionalProperties
                        }

                        private val hashCode: Int by lazy {
                            Objects.hash(info, additionalProperties)
                        }

                        override fun hashCode(): Int = hashCode

                        override fun toString() =
                            "Info{info=$info, additionalProperties=$additionalProperties}"
                    }

                    override fun equals(other: Any?): Boolean {
                        if (this === other) {
                            return true
                        }

                        return other is Tag &&
                            id == other.id &&
                            info == other.info &&
                            additionalProperties == other.additionalProperties
                    }

                    private val hashCode: Int by lazy {
                        Objects.hash(id, info, additionalProperties)
                    }

                    override fun hashCode(): Int = hashCode

                    override fun toString() =
                        "Tag{id=$id, info=$info, additionalProperties=$additionalProperties}"
                }

                override fun equals(other: Any?): Boolean {
                    if (this === other) {
                        return true
                    }

                    return other is Symbol &&
                        indices == other.indices &&
                        seconds == other.seconds &&
                        tag == other.tag &&
                        text == other.text &&
                        additionalProperties == other.additionalProperties
                }

                private val hashCode: Int by lazy {
                    Objects.hash(indices, seconds, tag, text, additionalProperties)
                }

                override fun hashCode(): Int = hashCode

                override fun toString() =
                    "Symbol{indices=$indices, seconds=$seconds, tag=$tag, text=$text, additionalProperties=$additionalProperties}"
            }

            /** Indexed smart-tag, cashtag, or video timestamp metadata. */
            class Timestamp
            @JsonCreator(mode = JsonCreator.Mode.DISABLED)
            private constructor(
                private val indices: JsonField<List<Long>>,
                private val seconds: JsonField<Double>,
                private val tag: JsonField<Tag>,
                private val text: JsonField<String>,
                private val additionalProperties: MutableMap<String, JsonValue>,
            ) {

                @JsonCreator
                private constructor(
                    @JsonProperty("indices")
                    @ExcludeMissing
                    indices: JsonField<List<Long>> = JsonMissing.of(),
                    @JsonProperty("seconds")
                    @ExcludeMissing
                    seconds: JsonField<Double> = JsonMissing.of(),
                    @JsonProperty("tag") @ExcludeMissing tag: JsonField<Tag> = JsonMissing.of(),
                    @JsonProperty("text")
                    @ExcludeMissing
                    text: JsonField<String> = JsonMissing.of(),
                ) : this(indices, seconds, tag, text, mutableMapOf())

                /**
                 * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected
                 *   type.
                 */
                fun indices(): List<Long>? = indices.getNullable("indices")

                /**
                 * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected
                 *   type.
                 */
                fun seconds(): Double? = seconds.getNullable("seconds")

                /**
                 * X's SmartTag for a cashtag, smarttag or hashtag. Xquik leaves out X's trading
                 * platforms, which differ by reader.
                 *
                 * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected
                 *   type.
                 */
                fun tag(): Tag? = tag.getNullable("tag")

                /**
                 * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected
                 *   type.
                 */
                fun text(): String? = text.getNullable("text")

                /**
                 * Returns the raw JSON value of [indices].
                 *
                 * Unlike [indices], this method doesn't throw if the JSON field has an unexpected
                 * type.
                 */
                @JsonProperty("indices")
                @ExcludeMissing
                fun _indices(): JsonField<List<Long>> = indices

                /**
                 * Returns the raw JSON value of [seconds].
                 *
                 * Unlike [seconds], this method doesn't throw if the JSON field has an unexpected
                 * type.
                 */
                @JsonProperty("seconds") @ExcludeMissing fun _seconds(): JsonField<Double> = seconds

                /**
                 * Returns the raw JSON value of [tag].
                 *
                 * Unlike [tag], this method doesn't throw if the JSON field has an unexpected type.
                 */
                @JsonProperty("tag") @ExcludeMissing fun _tag(): JsonField<Tag> = tag

                /**
                 * Returns the raw JSON value of [text].
                 *
                 * Unlike [text], this method doesn't throw if the JSON field has an unexpected
                 * type.
                 */
                @JsonProperty("text") @ExcludeMissing fun _text(): JsonField<String> = text

                @JsonAnySetter
                private fun putAdditionalProperty(key: String, value: JsonValue) {
                    additionalProperties.put(key, value)
                }

                @JsonAnyGetter
                @ExcludeMissing
                fun _additionalProperties(): Map<String, JsonValue> =
                    Collections.unmodifiableMap(additionalProperties)

                fun toBuilder() = Builder().from(this)

                companion object {

                    /** Returns a mutable builder for constructing an instance of [Timestamp]. */
                    fun builder() = Builder()
                }

                /** A builder for [Timestamp]. */
                class Builder internal constructor() {

                    private var indices: JsonField<MutableList<Long>>? = null
                    private var seconds: JsonField<Double> = JsonMissing.of()
                    private var tag: JsonField<Tag> = JsonMissing.of()
                    private var text: JsonField<String> = JsonMissing.of()
                    private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                    internal fun from(timestamp: Timestamp) = apply {
                        indices = timestamp.indices.map { it.toMutableList() }
                        seconds = timestamp.seconds
                        tag = timestamp.tag
                        text = timestamp.text
                        additionalProperties = timestamp.additionalProperties.toMutableMap()
                    }

                    fun indices(indices: List<Long>) = indices(JsonField.of(indices))

                    /** Sets [Builder.indices] to an arbitrary JSON value. */
                    fun indices(indices: JsonField<List<Long>>) = apply {
                        this.indices = indices.map { it.toMutableList() }
                    }

                    /**
                     * Adds a single [Long] to [indices].
                     *
                     * @throws IllegalStateException if the field was previously set to a non-list.
                     */
                    fun addIndex(index: Long) = apply {
                        indices =
                            (indices ?: JsonField.of(mutableListOf())).also {
                                checkKnown("indices", it).add(index)
                            }
                    }

                    fun seconds(seconds: Double) = seconds(JsonField.of(seconds))

                    /** Sets [Builder.seconds] to an arbitrary JSON value. */
                    fun seconds(seconds: JsonField<Double>) = apply { this.seconds = seconds }

                    /**
                     * X's SmartTag for a cashtag, smarttag or hashtag. Xquik leaves out X's trading
                     * platforms, which differ by reader.
                     */
                    fun tag(tag: Tag) = tag(JsonField.of(tag))

                    /** Sets [Builder.tag] to an arbitrary JSON value. */
                    fun tag(tag: JsonField<Tag>) = apply { this.tag = tag }

                    fun text(text: String) = text(JsonField.of(text))

                    /** Sets [Builder.text] to an arbitrary JSON value. */
                    fun text(text: JsonField<String>) = apply { this.text = text }

                    fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                        this.additionalProperties.clear()
                        putAllAdditionalProperties(additionalProperties)
                    }

                    fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                        additionalProperties.put(key, value)
                    }

                    fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) =
                        apply {
                            this.additionalProperties.putAll(additionalProperties)
                        }

                    fun removeAdditionalProperty(key: String) = apply {
                        additionalProperties.remove(key)
                    }

                    fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                        keys.forEach(::removeAdditionalProperty)
                    }

                    /**
                     * Returns an immutable instance of [Timestamp].
                     *
                     * Further updates to this [Builder] will not mutate the returned instance.
                     */
                    fun build(): Timestamp =
                        Timestamp(
                            (indices ?: JsonMissing.of()).map { it.toImmutable() },
                            seconds,
                            tag,
                            text,
                            additionalProperties.toMutableMap(),
                        )
                }

                private var validated: Boolean = false

                /**
                 * Validates that the types of all values in this object match their expected types
                 * recursively.
                 *
                 * This method is _not_ forwards compatible with new types from the API for existing
                 * fields.
                 *
                 * @throws XTwitterScraperInvalidDataException if any value type in this object
                 *   doesn't match its expected type.
                 */
                fun validate(): Timestamp = apply {
                    if (validated) {
                        return@apply
                    }

                    indices()
                    seconds()
                    tag()?.validate()
                    text()
                    validated = true
                }

                fun isValid(): Boolean =
                    try {
                        validate()
                        true
                    } catch (e: XTwitterScraperInvalidDataException) {
                        false
                    }

                internal fun validity(): Int =
                    (indices.asKnown()?.size ?: 0) +
                        (if (seconds.asKnown() == null) 0 else 1) +
                        (tag.asKnown()?.validity() ?: 0) +
                        (if (text.asKnown() == null) 0 else 1)

                /**
                 * X's SmartTag for a cashtag, smarttag or hashtag. Xquik leaves out X's trading
                 * platforms, which differ by reader.
                 */
                class Tag
                @JsonCreator(mode = JsonCreator.Mode.DISABLED)
                private constructor(
                    private val id: JsonField<String>,
                    private val info: JsonField<Info>,
                    private val additionalProperties: MutableMap<String, JsonValue>,
                ) {

                    @JsonCreator
                    private constructor(
                        @JsonProperty("id")
                        @ExcludeMissing
                        id: JsonField<String> = JsonMissing.of(),
                        @JsonProperty("info")
                        @ExcludeMissing
                        info: JsonField<Info> = JsonMissing.of(),
                    ) : this(id, info, mutableMapOf())

                    /**
                     * X's ID for the SmartTag.
                     *
                     * @throws XTwitterScraperInvalidDataException if the JSON field has an
                     *   unexpected type.
                     */
                    fun id(): String? = id.getNullable("id")

                    /**
                     * @throws XTwitterScraperInvalidDataException if the JSON field has an
                     *   unexpected type.
                     */
                    fun info(): Info? = info.getNullable("info")

                    /**
                     * Returns the raw JSON value of [id].
                     *
                     * Unlike [id], this method doesn't throw if the JSON field has an unexpected
                     * type.
                     */
                    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

                    /**
                     * Returns the raw JSON value of [info].
                     *
                     * Unlike [info], this method doesn't throw if the JSON field has an unexpected
                     * type.
                     */
                    @JsonProperty("info") @ExcludeMissing fun _info(): JsonField<Info> = info

                    @JsonAnySetter
                    private fun putAdditionalProperty(key: String, value: JsonValue) {
                        additionalProperties.put(key, value)
                    }

                    @JsonAnyGetter
                    @ExcludeMissing
                    fun _additionalProperties(): Map<String, JsonValue> =
                        Collections.unmodifiableMap(additionalProperties)

                    fun toBuilder() = Builder().from(this)

                    companion object {

                        /** Returns a mutable builder for constructing an instance of [Tag]. */
                        fun builder() = Builder()
                    }

                    /** A builder for [Tag]. */
                    class Builder internal constructor() {

                        private var id: JsonField<String> = JsonMissing.of()
                        private var info: JsonField<Info> = JsonMissing.of()
                        private var additionalProperties: MutableMap<String, JsonValue> =
                            mutableMapOf()

                        internal fun from(tag: Tag) = apply {
                            id = tag.id
                            info = tag.info
                            additionalProperties = tag.additionalProperties.toMutableMap()
                        }

                        /** X's ID for the SmartTag. */
                        fun id(id: String) = id(JsonField.of(id))

                        /** Sets [Builder.id] to an arbitrary JSON value. */
                        fun id(id: JsonField<String>) = apply { this.id = id }

                        fun info(info: Info) = info(JsonField.of(info))

                        /** Sets [Builder.info] to an arbitrary JSON value. */
                        fun info(info: JsonField<Info>) = apply { this.info = info }

                        fun additionalProperties(additionalProperties: Map<String, JsonValue>) =
                            apply {
                                this.additionalProperties.clear()
                                putAllAdditionalProperties(additionalProperties)
                            }

                        fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                            additionalProperties.put(key, value)
                        }

                        fun putAllAdditionalProperties(
                            additionalProperties: Map<String, JsonValue>
                        ) = apply {
                            this.additionalProperties.putAll(additionalProperties)
                        }

                        fun removeAdditionalProperty(key: String) = apply {
                            additionalProperties.remove(key)
                        }

                        fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                            keys.forEach(::removeAdditionalProperty)
                        }

                        /**
                         * Returns an immutable instance of [Tag].
                         *
                         * Further updates to this [Builder] will not mutate the returned instance.
                         */
                        fun build(): Tag = Tag(id, info, additionalProperties.toMutableMap())
                    }

                    private var validated: Boolean = false

                    /**
                     * Validates that the types of all values in this object match their expected
                     * types recursively.
                     *
                     * This method is _not_ forwards compatible with new types from the API for
                     * existing fields.
                     *
                     * @throws XTwitterScraperInvalidDataException if any value type in this object
                     *   doesn't match its expected type.
                     */
                    fun validate(): Tag = apply {
                        if (validated) {
                            return@apply
                        }

                        id()
                        info()?.validate()
                        validated = true
                    }

                    fun isValid(): Boolean =
                        try {
                            validate()
                            true
                        } catch (e: XTwitterScraperInvalidDataException) {
                            false
                        }

                    internal fun validity(): Int =
                        (if (id.asKnown() == null) 0 else 1) + (info.asKnown()?.validity() ?: 0)

                    class Info
                    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
                    private constructor(
                        private val info: JsonField<InnerInfo>,
                        private val additionalProperties: MutableMap<String, JsonValue>,
                    ) {

                        @JsonCreator
                        private constructor(
                            @JsonProperty("info")
                            @ExcludeMissing
                            info: JsonField<InnerInfo> = JsonMissing.of()
                        ) : this(info, mutableMapOf())

                        /**
                         * @throws XTwitterScraperInvalidDataException if the JSON field has an
                         *   unexpected type.
                         */
                        fun info(): InnerInfo? = info.getNullable("info")

                        /**
                         * Returns the raw JSON value of [info].
                         *
                         * Unlike [info], this method doesn't throw if the JSON field has an
                         * unexpected type.
                         */
                        @JsonProperty("info")
                        @ExcludeMissing
                        fun _info(): JsonField<InnerInfo> = info

                        @JsonAnySetter
                        private fun putAdditionalProperty(key: String, value: JsonValue) {
                            additionalProperties.put(key, value)
                        }

                        @JsonAnyGetter
                        @ExcludeMissing
                        fun _additionalProperties(): Map<String, JsonValue> =
                            Collections.unmodifiableMap(additionalProperties)

                        fun toBuilder() = Builder().from(this)

                        companion object {

                            /** Returns a mutable builder for constructing an instance of [Info]. */
                            fun builder() = Builder()
                        }

                        /** A builder for [Info]. */
                        class Builder internal constructor() {

                            private var info: JsonField<InnerInfo> = JsonMissing.of()
                            private var additionalProperties: MutableMap<String, JsonValue> =
                                mutableMapOf()

                            internal fun from(info: Info) = apply {
                                this.info = info.info
                                additionalProperties = info.additionalProperties.toMutableMap()
                            }

                            fun info(info: InnerInfo) = info(JsonField.of(info))

                            /** Sets [Builder.info] to an arbitrary JSON value. */
                            fun info(info: JsonField<InnerInfo>) = apply { this.info = info }

                            fun additionalProperties(additionalProperties: Map<String, JsonValue>) =
                                apply {
                                    this.additionalProperties.clear()
                                    putAllAdditionalProperties(additionalProperties)
                                }

                            fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                                additionalProperties.put(key, value)
                            }

                            fun putAllAdditionalProperties(
                                additionalProperties: Map<String, JsonValue>
                            ) = apply {
                                this.additionalProperties.putAll(additionalProperties)
                            }

                            fun removeAdditionalProperty(key: String) = apply {
                                additionalProperties.remove(key)
                            }

                            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                                keys.forEach(::removeAdditionalProperty)
                            }

                            /**
                             * Returns an immutable instance of [Info].
                             *
                             * Further updates to this [Builder] will not mutate the returned
                             * instance.
                             */
                            fun build(): Info = Info(info, additionalProperties.toMutableMap())
                        }

                        private var validated: Boolean = false

                        /**
                         * Validates that the types of all values in this object match their
                         * expected types recursively.
                         *
                         * This method is _not_ forwards compatible with new types from the API for
                         * existing fields.
                         *
                         * @throws XTwitterScraperInvalidDataException if any value type in this
                         *   object doesn't match its expected type.
                         */
                        fun validate(): Info = apply {
                            if (validated) {
                                return@apply
                            }

                            info()?.validate()
                            validated = true
                        }

                        fun isValid(): Boolean =
                            try {
                                validate()
                                true
                            } catch (e: XTwitterScraperInvalidDataException) {
                                false
                            }

                        internal fun validity(): Int = (info.asKnown()?.validity() ?: 0)

                        class InnerInfo
                        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
                        private constructor(
                            private val active: JsonField<Boolean>,
                            private val name: JsonField<String>,
                            private val ticker: JsonField<String>,
                            private val additionalProperties: MutableMap<String, JsonValue>,
                        ) {

                            @JsonCreator
                            private constructor(
                                @JsonProperty("active")
                                @ExcludeMissing
                                active: JsonField<Boolean> = JsonMissing.of(),
                                @JsonProperty("name")
                                @ExcludeMissing
                                name: JsonField<String> = JsonMissing.of(),
                                @JsonProperty("ticker")
                                @ExcludeMissing
                                ticker: JsonField<String> = JsonMissing.of(),
                            ) : this(active, name, ticker, mutableMapOf())

                            /**
                             * Whether X marks the SmartTag active.
                             *
                             * @throws XTwitterScraperInvalidDataException if the JSON field has an
                             *   unexpected type.
                             */
                            fun active(): Boolean? = active.getNullable("active")

                            /**
                             * @throws XTwitterScraperInvalidDataException if the JSON field has an
                             *   unexpected type.
                             */
                            fun name(): String? = name.getNullable("name")

                            /**
                             * @throws XTwitterScraperInvalidDataException if the JSON field has an
                             *   unexpected type.
                             */
                            fun ticker(): String? = ticker.getNullable("ticker")

                            /**
                             * Returns the raw JSON value of [active].
                             *
                             * Unlike [active], this method doesn't throw if the JSON field has an
                             * unexpected type.
                             */
                            @JsonProperty("active")
                            @ExcludeMissing
                            fun _active(): JsonField<Boolean> = active

                            /**
                             * Returns the raw JSON value of [name].
                             *
                             * Unlike [name], this method doesn't throw if the JSON field has an
                             * unexpected type.
                             */
                            @JsonProperty("name")
                            @ExcludeMissing
                            fun _name(): JsonField<String> = name

                            /**
                             * Returns the raw JSON value of [ticker].
                             *
                             * Unlike [ticker], this method doesn't throw if the JSON field has an
                             * unexpected type.
                             */
                            @JsonProperty("ticker")
                            @ExcludeMissing
                            fun _ticker(): JsonField<String> = ticker

                            @JsonAnySetter
                            private fun putAdditionalProperty(key: String, value: JsonValue) {
                                additionalProperties.put(key, value)
                            }

                            @JsonAnyGetter
                            @ExcludeMissing
                            fun _additionalProperties(): Map<String, JsonValue> =
                                Collections.unmodifiableMap(additionalProperties)

                            fun toBuilder() = Builder().from(this)

                            companion object {

                                /**
                                 * Returns a mutable builder for constructing an instance of
                                 * [InnerInfo].
                                 */
                                fun builder() = Builder()
                            }

                            /** A builder for [InnerInfo]. */
                            class Builder internal constructor() {

                                private var active: JsonField<Boolean> = JsonMissing.of()
                                private var name: JsonField<String> = JsonMissing.of()
                                private var ticker: JsonField<String> = JsonMissing.of()
                                private var additionalProperties: MutableMap<String, JsonValue> =
                                    mutableMapOf()

                                internal fun from(innerInfo: InnerInfo) = apply {
                                    active = innerInfo.active
                                    name = innerInfo.name
                                    ticker = innerInfo.ticker
                                    additionalProperties =
                                        innerInfo.additionalProperties.toMutableMap()
                                }

                                /** Whether X marks the SmartTag active. */
                                fun active(active: Boolean) = active(JsonField.of(active))

                                /** Sets [Builder.active] to an arbitrary JSON value. */
                                fun active(active: JsonField<Boolean>) = apply {
                                    this.active = active
                                }

                                fun name(name: String) = name(JsonField.of(name))

                                /** Sets [Builder.name] to an arbitrary JSON value. */
                                fun name(name: JsonField<String>) = apply { this.name = name }

                                fun ticker(ticker: String) = ticker(JsonField.of(ticker))

                                /** Sets [Builder.ticker] to an arbitrary JSON value. */
                                fun ticker(ticker: JsonField<String>) = apply {
                                    this.ticker = ticker
                                }

                                fun additionalProperties(
                                    additionalProperties: Map<String, JsonValue>
                                ) = apply {
                                    this.additionalProperties.clear()
                                    putAllAdditionalProperties(additionalProperties)
                                }

                                fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                                    additionalProperties.put(key, value)
                                }

                                fun putAllAdditionalProperties(
                                    additionalProperties: Map<String, JsonValue>
                                ) = apply {
                                    this.additionalProperties.putAll(additionalProperties)
                                }

                                fun removeAdditionalProperty(key: String) = apply {
                                    additionalProperties.remove(key)
                                }

                                fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                                    keys.forEach(::removeAdditionalProperty)
                                }

                                /**
                                 * Returns an immutable instance of [InnerInfo].
                                 *
                                 * Further updates to this [Builder] will not mutate the returned
                                 * instance.
                                 */
                                fun build(): InnerInfo =
                                    InnerInfo(
                                        active,
                                        name,
                                        ticker,
                                        additionalProperties.toMutableMap(),
                                    )
                            }

                            private var validated: Boolean = false

                            /**
                             * Validates that the types of all values in this object match their
                             * expected types recursively.
                             *
                             * This method is _not_ forwards compatible with new types from the API
                             * for existing fields.
                             *
                             * @throws XTwitterScraperInvalidDataException if any value type in this
                             *   object doesn't match its expected type.
                             */
                            fun validate(): InnerInfo = apply {
                                if (validated) {
                                    return@apply
                                }

                                active()
                                name()
                                ticker()
                                validated = true
                            }

                            fun isValid(): Boolean =
                                try {
                                    validate()
                                    true
                                } catch (e: XTwitterScraperInvalidDataException) {
                                    false
                                }

                            internal fun validity(): Int =
                                (if (active.asKnown() == null) 0 else 1) +
                                    (if (name.asKnown() == null) 0 else 1) +
                                    (if (ticker.asKnown() == null) 0 else 1)

                            override fun equals(other: Any?): Boolean {
                                if (this === other) {
                                    return true
                                }

                                return other is InnerInfo &&
                                    active == other.active &&
                                    name == other.name &&
                                    ticker == other.ticker &&
                                    additionalProperties == other.additionalProperties
                            }

                            private val hashCode: Int by lazy {
                                Objects.hash(active, name, ticker, additionalProperties)
                            }

                            override fun hashCode(): Int = hashCode

                            override fun toString() =
                                "InnerInfo{active=$active, name=$name, ticker=$ticker, additionalProperties=$additionalProperties}"
                        }

                        override fun equals(other: Any?): Boolean {
                            if (this === other) {
                                return true
                            }

                            return other is Info &&
                                info == other.info &&
                                additionalProperties == other.additionalProperties
                        }

                        private val hashCode: Int by lazy {
                            Objects.hash(info, additionalProperties)
                        }

                        override fun hashCode(): Int = hashCode

                        override fun toString() =
                            "Info{info=$info, additionalProperties=$additionalProperties}"
                    }

                    override fun equals(other: Any?): Boolean {
                        if (this === other) {
                            return true
                        }

                        return other is Tag &&
                            id == other.id &&
                            info == other.info &&
                            additionalProperties == other.additionalProperties
                    }

                    private val hashCode: Int by lazy {
                        Objects.hash(id, info, additionalProperties)
                    }

                    override fun hashCode(): Int = hashCode

                    override fun toString() =
                        "Tag{id=$id, info=$info, additionalProperties=$additionalProperties}"
                }

                override fun equals(other: Any?): Boolean {
                    if (this === other) {
                        return true
                    }

                    return other is Timestamp &&
                        indices == other.indices &&
                        seconds == other.seconds &&
                        tag == other.tag &&
                        text == other.text &&
                        additionalProperties == other.additionalProperties
                }

                private val hashCode: Int by lazy {
                    Objects.hash(indices, seconds, tag, text, additionalProperties)
                }

                override fun hashCode(): Int = hashCode

                override fun toString() =
                    "Timestamp{indices=$indices, seconds=$seconds, tag=$tag, text=$text, additionalProperties=$additionalProperties}"
            }

            /** Shortened, display, and expanded URLs from tweet text. */
            class Url
            @JsonCreator(mode = JsonCreator.Mode.DISABLED)
            private constructor(
                private val displayUrl: JsonField<String>,
                private val expandedUrl: JsonField<String>,
                private val indices: JsonField<List<Long>>,
                private val url: JsonField<String>,
                private val additionalProperties: MutableMap<String, JsonValue>,
            ) {

                @JsonCreator
                private constructor(
                    @JsonProperty("display_url")
                    @ExcludeMissing
                    displayUrl: JsonField<String> = JsonMissing.of(),
                    @JsonProperty("expanded_url")
                    @ExcludeMissing
                    expandedUrl: JsonField<String> = JsonMissing.of(),
                    @JsonProperty("indices")
                    @ExcludeMissing
                    indices: JsonField<List<Long>> = JsonMissing.of(),
                    @JsonProperty("url") @ExcludeMissing url: JsonField<String> = JsonMissing.of(),
                ) : this(displayUrl, expandedUrl, indices, url, mutableMapOf())

                /**
                 * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected
                 *   type.
                 */
                fun displayUrl(): String? = displayUrl.getNullable("display_url")

                /**
                 * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected
                 *   type.
                 */
                fun expandedUrl(): String? = expandedUrl.getNullable("expanded_url")

                /**
                 * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected
                 *   type.
                 */
                fun indices(): List<Long>? = indices.getNullable("indices")

                /**
                 * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected
                 *   type.
                 */
                fun url(): String? = url.getNullable("url")

                /**
                 * Returns the raw JSON value of [displayUrl].
                 *
                 * Unlike [displayUrl], this method doesn't throw if the JSON field has an
                 * unexpected type.
                 */
                @JsonProperty("display_url")
                @ExcludeMissing
                fun _displayUrl(): JsonField<String> = displayUrl

                /**
                 * Returns the raw JSON value of [expandedUrl].
                 *
                 * Unlike [expandedUrl], this method doesn't throw if the JSON field has an
                 * unexpected type.
                 */
                @JsonProperty("expanded_url")
                @ExcludeMissing
                fun _expandedUrl(): JsonField<String> = expandedUrl

                /**
                 * Returns the raw JSON value of [indices].
                 *
                 * Unlike [indices], this method doesn't throw if the JSON field has an unexpected
                 * type.
                 */
                @JsonProperty("indices")
                @ExcludeMissing
                fun _indices(): JsonField<List<Long>> = indices

                /**
                 * Returns the raw JSON value of [url].
                 *
                 * Unlike [url], this method doesn't throw if the JSON field has an unexpected type.
                 */
                @JsonProperty("url") @ExcludeMissing fun _url(): JsonField<String> = url

                @JsonAnySetter
                private fun putAdditionalProperty(key: String, value: JsonValue) {
                    additionalProperties.put(key, value)
                }

                @JsonAnyGetter
                @ExcludeMissing
                fun _additionalProperties(): Map<String, JsonValue> =
                    Collections.unmodifiableMap(additionalProperties)

                fun toBuilder() = Builder().from(this)

                companion object {

                    /** Returns a mutable builder for constructing an instance of [Url]. */
                    fun builder() = Builder()
                }

                /** A builder for [Url]. */
                class Builder internal constructor() {

                    private var displayUrl: JsonField<String> = JsonMissing.of()
                    private var expandedUrl: JsonField<String> = JsonMissing.of()
                    private var indices: JsonField<MutableList<Long>>? = null
                    private var url: JsonField<String> = JsonMissing.of()
                    private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                    internal fun from(url: Url) = apply {
                        displayUrl = url.displayUrl
                        expandedUrl = url.expandedUrl
                        indices = url.indices.map { it.toMutableList() }
                        this.url = url.url
                        additionalProperties = url.additionalProperties.toMutableMap()
                    }

                    fun displayUrl(displayUrl: String) = displayUrl(JsonField.of(displayUrl))

                    /** Sets [Builder.displayUrl] to an arbitrary JSON value. */
                    fun displayUrl(displayUrl: JsonField<String>) = apply {
                        this.displayUrl = displayUrl
                    }

                    fun expandedUrl(expandedUrl: String) = expandedUrl(JsonField.of(expandedUrl))

                    /** Sets [Builder.expandedUrl] to an arbitrary JSON value. */
                    fun expandedUrl(expandedUrl: JsonField<String>) = apply {
                        this.expandedUrl = expandedUrl
                    }

                    fun indices(indices: List<Long>) = indices(JsonField.of(indices))

                    /** Sets [Builder.indices] to an arbitrary JSON value. */
                    fun indices(indices: JsonField<List<Long>>) = apply {
                        this.indices = indices.map { it.toMutableList() }
                    }

                    /**
                     * Adds a single [Long] to [indices].
                     *
                     * @throws IllegalStateException if the field was previously set to a non-list.
                     */
                    fun addIndex(index: Long) = apply {
                        indices =
                            (indices ?: JsonField.of(mutableListOf())).also {
                                checkKnown("indices", it).add(index)
                            }
                    }

                    fun url(url: String) = url(JsonField.of(url))

                    /** Sets [Builder.url] to an arbitrary JSON value. */
                    fun url(url: JsonField<String>) = apply { this.url = url }

                    fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                        this.additionalProperties.clear()
                        putAllAdditionalProperties(additionalProperties)
                    }

                    fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                        additionalProperties.put(key, value)
                    }

                    fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) =
                        apply {
                            this.additionalProperties.putAll(additionalProperties)
                        }

                    fun removeAdditionalProperty(key: String) = apply {
                        additionalProperties.remove(key)
                    }

                    fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                        keys.forEach(::removeAdditionalProperty)
                    }

                    /**
                     * Returns an immutable instance of [Url].
                     *
                     * Further updates to this [Builder] will not mutate the returned instance.
                     */
                    fun build(): Url =
                        Url(
                            displayUrl,
                            expandedUrl,
                            (indices ?: JsonMissing.of()).map { it.toImmutable() },
                            url,
                            additionalProperties.toMutableMap(),
                        )
                }

                private var validated: Boolean = false

                /**
                 * Validates that the types of all values in this object match their expected types
                 * recursively.
                 *
                 * This method is _not_ forwards compatible with new types from the API for existing
                 * fields.
                 *
                 * @throws XTwitterScraperInvalidDataException if any value type in this object
                 *   doesn't match its expected type.
                 */
                fun validate(): Url = apply {
                    if (validated) {
                        return@apply
                    }

                    displayUrl()
                    expandedUrl()
                    indices()
                    url()
                    validated = true
                }

                fun isValid(): Boolean =
                    try {
                        validate()
                        true
                    } catch (e: XTwitterScraperInvalidDataException) {
                        false
                    }

                internal fun validity(): Int =
                    (if (displayUrl.asKnown() == null) 0 else 1) +
                        (if (expandedUrl.asKnown() == null) 0 else 1) +
                        (indices.asKnown()?.size ?: 0) +
                        (if (url.asKnown() == null) 0 else 1)

                override fun equals(other: Any?): Boolean {
                    if (this === other) {
                        return true
                    }

                    return other is Url &&
                        displayUrl == other.displayUrl &&
                        expandedUrl == other.expandedUrl &&
                        indices == other.indices &&
                        url == other.url &&
                        additionalProperties == other.additionalProperties
                }

                private val hashCode: Int by lazy {
                    Objects.hash(displayUrl, expandedUrl, indices, url, additionalProperties)
                }

                override fun hashCode(): Int = hashCode

                override fun toString() =
                    "Url{displayUrl=$displayUrl, expandedUrl=$expandedUrl, indices=$indices, url=$url, additionalProperties=$additionalProperties}"
            }

            /** Profile identity and source offsets of a mention. */
            class UserMention
            @JsonCreator(mode = JsonCreator.Mode.DISABLED)
            private constructor(
                private val screenName: JsonField<String>,
                private val idStr: JsonField<String>,
                private val indices: JsonField<List<Long>>,
                private val name: JsonField<String>,
                private val additionalProperties: MutableMap<String, JsonValue>,
            ) {

                @JsonCreator
                private constructor(
                    @JsonProperty("screen_name")
                    @ExcludeMissing
                    screenName: JsonField<String> = JsonMissing.of(),
                    @JsonProperty("id_str")
                    @ExcludeMissing
                    idStr: JsonField<String> = JsonMissing.of(),
                    @JsonProperty("indices")
                    @ExcludeMissing
                    indices: JsonField<List<Long>> = JsonMissing.of(),
                    @JsonProperty("name")
                    @ExcludeMissing
                    name: JsonField<String> = JsonMissing.of(),
                ) : this(screenName, idStr, indices, name, mutableMapOf())

                /**
                 * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected
                 *   type or is unexpectedly missing or null.
                 */
                fun screenName(): String = screenName.getRequired("screen_name")

                /**
                 * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected
                 *   type.
                 */
                fun idStr(): String? = idStr.getNullable("id_str")

                /**
                 * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected
                 *   type.
                 */
                fun indices(): List<Long>? = indices.getNullable("indices")

                /**
                 * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected
                 *   type.
                 */
                fun name(): String? = name.getNullable("name")

                /**
                 * Returns the raw JSON value of [screenName].
                 *
                 * Unlike [screenName], this method doesn't throw if the JSON field has an
                 * unexpected type.
                 */
                @JsonProperty("screen_name")
                @ExcludeMissing
                fun _screenName(): JsonField<String> = screenName

                /**
                 * Returns the raw JSON value of [idStr].
                 *
                 * Unlike [idStr], this method doesn't throw if the JSON field has an unexpected
                 * type.
                 */
                @JsonProperty("id_str") @ExcludeMissing fun _idStr(): JsonField<String> = idStr

                /**
                 * Returns the raw JSON value of [indices].
                 *
                 * Unlike [indices], this method doesn't throw if the JSON field has an unexpected
                 * type.
                 */
                @JsonProperty("indices")
                @ExcludeMissing
                fun _indices(): JsonField<List<Long>> = indices

                /**
                 * Returns the raw JSON value of [name].
                 *
                 * Unlike [name], this method doesn't throw if the JSON field has an unexpected
                 * type.
                 */
                @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<String> = name

                @JsonAnySetter
                private fun putAdditionalProperty(key: String, value: JsonValue) {
                    additionalProperties.put(key, value)
                }

                @JsonAnyGetter
                @ExcludeMissing
                fun _additionalProperties(): Map<String, JsonValue> =
                    Collections.unmodifiableMap(additionalProperties)

                fun toBuilder() = Builder().from(this)

                companion object {

                    /**
                     * Returns a mutable builder for constructing an instance of [UserMention].
                     *
                     * The following fields are required:
                     * ```kotlin
                     * .screenName()
                     * ```
                     */
                    fun builder() = Builder()
                }

                /** A builder for [UserMention]. */
                class Builder internal constructor() {

                    private var screenName: JsonField<String>? = null
                    private var idStr: JsonField<String> = JsonMissing.of()
                    private var indices: JsonField<MutableList<Long>>? = null
                    private var name: JsonField<String> = JsonMissing.of()
                    private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                    internal fun from(userMention: UserMention) = apply {
                        screenName = userMention.screenName
                        idStr = userMention.idStr
                        indices = userMention.indices.map { it.toMutableList() }
                        name = userMention.name
                        additionalProperties = userMention.additionalProperties.toMutableMap()
                    }

                    fun screenName(screenName: String) = screenName(JsonField.of(screenName))

                    /** Sets [Builder.screenName] to an arbitrary JSON value. */
                    fun screenName(screenName: JsonField<String>) = apply {
                        this.screenName = screenName
                    }

                    fun idStr(idStr: String) = idStr(JsonField.of(idStr))

                    /** Sets [Builder.idStr] to an arbitrary JSON value. */
                    fun idStr(idStr: JsonField<String>) = apply { this.idStr = idStr }

                    fun indices(indices: List<Long>) = indices(JsonField.of(indices))

                    /** Sets [Builder.indices] to an arbitrary JSON value. */
                    fun indices(indices: JsonField<List<Long>>) = apply {
                        this.indices = indices.map { it.toMutableList() }
                    }

                    /**
                     * Adds a single [Long] to [indices].
                     *
                     * @throws IllegalStateException if the field was previously set to a non-list.
                     */
                    fun addIndex(index: Long) = apply {
                        indices =
                            (indices ?: JsonField.of(mutableListOf())).also {
                                checkKnown("indices", it).add(index)
                            }
                    }

                    fun name(name: String) = name(JsonField.of(name))

                    /** Sets [Builder.name] to an arbitrary JSON value. */
                    fun name(name: JsonField<String>) = apply { this.name = name }

                    fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                        this.additionalProperties.clear()
                        putAllAdditionalProperties(additionalProperties)
                    }

                    fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                        additionalProperties.put(key, value)
                    }

                    fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) =
                        apply {
                            this.additionalProperties.putAll(additionalProperties)
                        }

                    fun removeAdditionalProperty(key: String) = apply {
                        additionalProperties.remove(key)
                    }

                    fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                        keys.forEach(::removeAdditionalProperty)
                    }

                    /**
                     * Returns an immutable instance of [UserMention].
                     *
                     * Further updates to this [Builder] will not mutate the returned instance.
                     *
                     * The following fields are required:
                     * ```kotlin
                     * .screenName()
                     * ```
                     *
                     * @throws IllegalStateException if any required field is unset.
                     */
                    fun build(): UserMention =
                        UserMention(
                            checkRequired("screenName", screenName),
                            idStr,
                            (indices ?: JsonMissing.of()).map { it.toImmutable() },
                            name,
                            additionalProperties.toMutableMap(),
                        )
                }

                private var validated: Boolean = false

                /**
                 * Validates that the types of all values in this object match their expected types
                 * recursively.
                 *
                 * This method is _not_ forwards compatible with new types from the API for existing
                 * fields.
                 *
                 * @throws XTwitterScraperInvalidDataException if any value type in this object
                 *   doesn't match its expected type.
                 */
                fun validate(): UserMention = apply {
                    if (validated) {
                        return@apply
                    }

                    screenName()
                    idStr()
                    indices()
                    name()
                    validated = true
                }

                fun isValid(): Boolean =
                    try {
                        validate()
                        true
                    } catch (e: XTwitterScraperInvalidDataException) {
                        false
                    }

                internal fun validity(): Int =
                    (if (screenName.asKnown() == null) 0 else 1) +
                        (if (idStr.asKnown() == null) 0 else 1) +
                        (indices.asKnown()?.size ?: 0) +
                        (if (name.asKnown() == null) 0 else 1)

                override fun equals(other: Any?): Boolean {
                    if (this === other) {
                        return true
                    }

                    return other is UserMention &&
                        screenName == other.screenName &&
                        idStr == other.idStr &&
                        indices == other.indices &&
                        name == other.name &&
                        additionalProperties == other.additionalProperties
                }

                private val hashCode: Int by lazy {
                    Objects.hash(screenName, idStr, indices, name, additionalProperties)
                }

                override fun hashCode(): Int = hashCode

                override fun toString() =
                    "UserMention{screenName=$screenName, idStr=$idStr, indices=$indices, name=$name, additionalProperties=$additionalProperties}"
            }

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is Entities &&
                    hashtags == other.hashtags &&
                    smarttags == other.smarttags &&
                    symbols == other.symbols &&
                    timestamps == other.timestamps &&
                    urls == other.urls &&
                    userMentions == other.userMentions &&
                    additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(
                    hashtags,
                    smarttags,
                    symbols,
                    timestamps,
                    urls,
                    userMentions,
                    additionalProperties,
                )
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "Entities{hashtags=$hashtags, smarttags=$smarttags, symbols=$symbols, timestamps=$timestamps, urls=$urls, userMentions=$userMentions, additionalProperties=$additionalProperties}"
        }

        class InlineMedia
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val index: JsonField<Long>,
            private val mediaId: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("index") @ExcludeMissing index: JsonField<Long> = JsonMissing.of(),
                @JsonProperty("mediaId")
                @ExcludeMissing
                mediaId: JsonField<String> = JsonMissing.of(),
            ) : this(index, mediaId, mutableMapOf())

            /**
             * Whole number from 0.
             *
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type
             *   or is unexpectedly missing or null.
             */
            fun index(): Long = index.getRequired("index")

            /**
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type
             *   or is unexpectedly missing or null.
             */
            fun mediaId(): String = mediaId.getRequired("mediaId")

            /**
             * Returns the raw JSON value of [index].
             *
             * Unlike [index], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("index") @ExcludeMissing fun _index(): JsonField<Long> = index

            /**
             * Returns the raw JSON value of [mediaId].
             *
             * Unlike [mediaId], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("mediaId") @ExcludeMissing fun _mediaId(): JsonField<String> = mediaId

            @JsonAnySetter
            private fun putAdditionalProperty(key: String, value: JsonValue) {
                additionalProperties.put(key, value)
            }

            @JsonAnyGetter
            @ExcludeMissing
            fun _additionalProperties(): Map<String, JsonValue> =
                Collections.unmodifiableMap(additionalProperties)

            fun toBuilder() = Builder().from(this)

            companion object {

                /**
                 * Returns a mutable builder for constructing an instance of [InlineMedia].
                 *
                 * The following fields are required:
                 * ```kotlin
                 * .index()
                 * .mediaId()
                 * ```
                 */
                fun builder() = Builder()
            }

            /** A builder for [InlineMedia]. */
            class Builder internal constructor() {

                private var index: JsonField<Long>? = null
                private var mediaId: JsonField<String>? = null
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                internal fun from(inlineMedia: InlineMedia) = apply {
                    index = inlineMedia.index
                    mediaId = inlineMedia.mediaId
                    additionalProperties = inlineMedia.additionalProperties.toMutableMap()
                }

                /** Whole number from 0. */
                fun index(index: Long) = index(JsonField.of(index))

                /** Sets [Builder.index] to an arbitrary JSON value. */
                fun index(index: JsonField<Long>) = apply { this.index = index }

                fun mediaId(mediaId: String) = mediaId(JsonField.of(mediaId))

                /** Sets [Builder.mediaId] to an arbitrary JSON value. */
                fun mediaId(mediaId: JsonField<String>) = apply { this.mediaId = mediaId }

                fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                    this.additionalProperties.clear()
                    putAllAdditionalProperties(additionalProperties)
                }

                fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                    additionalProperties.put(key, value)
                }

                fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) =
                    apply {
                        this.additionalProperties.putAll(additionalProperties)
                    }

                fun removeAdditionalProperty(key: String) = apply {
                    additionalProperties.remove(key)
                }

                fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                    keys.forEach(::removeAdditionalProperty)
                }

                /**
                 * Returns an immutable instance of [InlineMedia].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 *
                 * The following fields are required:
                 * ```kotlin
                 * .index()
                 * .mediaId()
                 * ```
                 *
                 * @throws IllegalStateException if any required field is unset.
                 */
                fun build(): InlineMedia =
                    InlineMedia(
                        checkRequired("index", index),
                        checkRequired("mediaId", mediaId),
                        additionalProperties.toMutableMap(),
                    )
            }

            private var validated: Boolean = false

            /**
             * Validates that the types of all values in this object match their expected types
             * recursively.
             *
             * This method is _not_ forwards compatible with new types from the API for existing
             * fields.
             *
             * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
             *   match its expected type.
             */
            fun validate(): InlineMedia = apply {
                if (validated) {
                    return@apply
                }

                index()
                mediaId()
                validated = true
            }

            fun isValid(): Boolean =
                try {
                    validate()
                    true
                } catch (e: XTwitterScraperInvalidDataException) {
                    false
                }

            internal fun validity(): Int =
                (if (index.asKnown() == null) 0 else 1) + (if (mediaId.asKnown() == null) 0 else 1)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is InlineMedia &&
                    index == other.index &&
                    mediaId == other.mediaId &&
                    additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(index, mediaId, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "InlineMedia{index=$index, mediaId=$mediaId, additionalProperties=$additionalProperties}"
        }

        class RichtextTag
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val fromIndex: JsonField<Long>,
            private val toIndex: JsonField<Long>,
            private val types: JsonField<List<String>>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("fromIndex")
                @ExcludeMissing
                fromIndex: JsonField<Long> = JsonMissing.of(),
                @JsonProperty("toIndex")
                @ExcludeMissing
                toIndex: JsonField<Long> = JsonMissing.of(),
                @JsonProperty("types")
                @ExcludeMissing
                types: JsonField<List<String>> = JsonMissing.of(),
            ) : this(fromIndex, toIndex, types, mutableMapOf())

            /**
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type
             *   or is unexpectedly missing or null.
             */
            fun fromIndex(): Long = fromIndex.getRequired("fromIndex")

            /**
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type
             *   or is unexpectedly missing or null.
             */
            fun toIndex(): Long = toIndex.getRequired("toIndex")

            /**
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type
             *   or is unexpectedly missing or null.
             */
            fun types(): List<String> = types.getRequired("types")

            /**
             * Returns the raw JSON value of [fromIndex].
             *
             * Unlike [fromIndex], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("fromIndex") @ExcludeMissing fun _fromIndex(): JsonField<Long> = fromIndex

            /**
             * Returns the raw JSON value of [toIndex].
             *
             * Unlike [toIndex], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("toIndex") @ExcludeMissing fun _toIndex(): JsonField<Long> = toIndex

            /**
             * Returns the raw JSON value of [types].
             *
             * Unlike [types], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("types") @ExcludeMissing fun _types(): JsonField<List<String>> = types

            @JsonAnySetter
            private fun putAdditionalProperty(key: String, value: JsonValue) {
                additionalProperties.put(key, value)
            }

            @JsonAnyGetter
            @ExcludeMissing
            fun _additionalProperties(): Map<String, JsonValue> =
                Collections.unmodifiableMap(additionalProperties)

            fun toBuilder() = Builder().from(this)

            companion object {

                /**
                 * Returns a mutable builder for constructing an instance of [RichtextTag].
                 *
                 * The following fields are required:
                 * ```kotlin
                 * .fromIndex()
                 * .toIndex()
                 * .types()
                 * ```
                 */
                fun builder() = Builder()
            }

            /** A builder for [RichtextTag]. */
            class Builder internal constructor() {

                private var fromIndex: JsonField<Long>? = null
                private var toIndex: JsonField<Long>? = null
                private var types: JsonField<MutableList<String>>? = null
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                internal fun from(richtextTag: RichtextTag) = apply {
                    fromIndex = richtextTag.fromIndex
                    toIndex = richtextTag.toIndex
                    types = richtextTag.types.map { it.toMutableList() }
                    additionalProperties = richtextTag.additionalProperties.toMutableMap()
                }

                fun fromIndex(fromIndex: Long) = fromIndex(JsonField.of(fromIndex))

                /** Sets [Builder.fromIndex] to an arbitrary JSON value. */
                fun fromIndex(fromIndex: JsonField<Long>) = apply { this.fromIndex = fromIndex }

                fun toIndex(toIndex: Long) = toIndex(JsonField.of(toIndex))

                /** Sets [Builder.toIndex] to an arbitrary JSON value. */
                fun toIndex(toIndex: JsonField<Long>) = apply { this.toIndex = toIndex }

                fun types(types: List<String>) = types(JsonField.of(types))

                /** Sets [Builder.types] to an arbitrary JSON value. */
                fun types(types: JsonField<List<String>>) = apply {
                    this.types = types.map { it.toMutableList() }
                }

                /**
                 * Adds a single [String] to [types].
                 *
                 * @throws IllegalStateException if the field was previously set to a non-list.
                 */
                fun addType(type: String) = apply {
                    types =
                        (types ?: JsonField.of(mutableListOf())).also {
                            checkKnown("types", it).add(type)
                        }
                }

                fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                    this.additionalProperties.clear()
                    putAllAdditionalProperties(additionalProperties)
                }

                fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                    additionalProperties.put(key, value)
                }

                fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) =
                    apply {
                        this.additionalProperties.putAll(additionalProperties)
                    }

                fun removeAdditionalProperty(key: String) = apply {
                    additionalProperties.remove(key)
                }

                fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                    keys.forEach(::removeAdditionalProperty)
                }

                /**
                 * Returns an immutable instance of [RichtextTag].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 *
                 * The following fields are required:
                 * ```kotlin
                 * .fromIndex()
                 * .toIndex()
                 * .types()
                 * ```
                 *
                 * @throws IllegalStateException if any required field is unset.
                 */
                fun build(): RichtextTag =
                    RichtextTag(
                        checkRequired("fromIndex", fromIndex),
                        checkRequired("toIndex", toIndex),
                        checkRequired("types", types).map { it.toImmutable() },
                        additionalProperties.toMutableMap(),
                    )
            }

            private var validated: Boolean = false

            /**
             * Validates that the types of all values in this object match their expected types
             * recursively.
             *
             * This method is _not_ forwards compatible with new types from the API for existing
             * fields.
             *
             * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
             *   match its expected type.
             */
            fun validate(): RichtextTag = apply {
                if (validated) {
                    return@apply
                }

                fromIndex()
                toIndex()
                types()
                validated = true
            }

            fun isValid(): Boolean =
                try {
                    validate()
                    true
                } catch (e: XTwitterScraperInvalidDataException) {
                    false
                }

            internal fun validity(): Int =
                (if (fromIndex.asKnown() == null) 0 else 1) +
                    (if (toIndex.asKnown() == null) 0 else 1) +
                    (types.asKnown()?.size ?: 0)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is RichtextTag &&
                    fromIndex == other.fromIndex &&
                    toIndex == other.toIndex &&
                    types == other.types &&
                    additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(fromIndex, toIndex, types, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "RichtextTag{fromIndex=$fromIndex, toIndex=$toIndex, types=$types, additionalProperties=$additionalProperties}"
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is NoteTweet &&
                text == other.text &&
                id == other.id &&
                entities == other.entities &&
                inlineMedia == other.inlineMedia &&
                isExpandable == other.isExpandable &&
                noteId == other.noteId &&
                richtextTags == other.richtextTags &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(
                text,
                id,
                entities,
                inlineMedia,
                isExpandable,
                noteId,
                richtextTags,
                additionalProperties,
            )
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "NoteTweet{text=$text, id=$id, entities=$entities, inlineMedia=$inlineMedia, isExpandable=$isExpandable, noteId=$noteId, richtextTags=$richtextTags, additionalProperties=$additionalProperties}"
    }

    /** Public place metadata on a geotagged tweet. */
    class Place
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val id: JsonField<String>,
        private val boundingBox: JsonField<BoundingBox>,
        private val country: JsonField<String>,
        private val countryCode: JsonField<String>,
        private val fullName: JsonField<String>,
        private val name: JsonField<String>,
        private val placeType: JsonField<String>,
        private val url: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
            @JsonProperty("boundingBox")
            @ExcludeMissing
            boundingBox: JsonField<BoundingBox> = JsonMissing.of(),
            @JsonProperty("country") @ExcludeMissing country: JsonField<String> = JsonMissing.of(),
            @JsonProperty("countryCode")
            @ExcludeMissing
            countryCode: JsonField<String> = JsonMissing.of(),
            @JsonProperty("fullName")
            @ExcludeMissing
            fullName: JsonField<String> = JsonMissing.of(),
            @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
            @JsonProperty("placeType")
            @ExcludeMissing
            placeType: JsonField<String> = JsonMissing.of(),
            @JsonProperty("url") @ExcludeMissing url: JsonField<String> = JsonMissing.of(),
        ) : this(
            id,
            boundingBox,
            country,
            countryCode,
            fullName,
            name,
            placeType,
            url,
            mutableMapOf(),
        )

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun id(): String? = id.getNullable("id")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun boundingBox(): BoundingBox? = boundingBox.getNullable("boundingBox")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun country(): String? = country.getNullable("country")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun countryCode(): String? = countryCode.getNullable("countryCode")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun fullName(): String? = fullName.getNullable("fullName")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun name(): String? = name.getNullable("name")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun placeType(): String? = placeType.getNullable("placeType")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun url(): String? = url.getNullable("url")

        /**
         * Returns the raw JSON value of [id].
         *
         * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

        /**
         * Returns the raw JSON value of [boundingBox].
         *
         * Unlike [boundingBox], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("boundingBox")
        @ExcludeMissing
        fun _boundingBox(): JsonField<BoundingBox> = boundingBox

        /**
         * Returns the raw JSON value of [country].
         *
         * Unlike [country], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("country") @ExcludeMissing fun _country(): JsonField<String> = country

        /**
         * Returns the raw JSON value of [countryCode].
         *
         * Unlike [countryCode], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("countryCode")
        @ExcludeMissing
        fun _countryCode(): JsonField<String> = countryCode

        /**
         * Returns the raw JSON value of [fullName].
         *
         * Unlike [fullName], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("fullName") @ExcludeMissing fun _fullName(): JsonField<String> = fullName

        /**
         * Returns the raw JSON value of [name].
         *
         * Unlike [name], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<String> = name

        /**
         * Returns the raw JSON value of [placeType].
         *
         * Unlike [placeType], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("placeType") @ExcludeMissing fun _placeType(): JsonField<String> = placeType

        /**
         * Returns the raw JSON value of [url].
         *
         * Unlike [url], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("url") @ExcludeMissing fun _url(): JsonField<String> = url

        @JsonAnySetter
        private fun putAdditionalProperty(key: String, value: JsonValue) {
            additionalProperties.put(key, value)
        }

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> =
            Collections.unmodifiableMap(additionalProperties)

        fun toBuilder() = Builder().from(this)

        companion object {

            /** Returns a mutable builder for constructing an instance of [Place]. */
            fun builder() = Builder()
        }

        /** A builder for [Place]. */
        class Builder internal constructor() {

            private var id: JsonField<String> = JsonMissing.of()
            private var boundingBox: JsonField<BoundingBox> = JsonMissing.of()
            private var country: JsonField<String> = JsonMissing.of()
            private var countryCode: JsonField<String> = JsonMissing.of()
            private var fullName: JsonField<String> = JsonMissing.of()
            private var name: JsonField<String> = JsonMissing.of()
            private var placeType: JsonField<String> = JsonMissing.of()
            private var url: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(place: Place) = apply {
                id = place.id
                boundingBox = place.boundingBox
                country = place.country
                countryCode = place.countryCode
                fullName = place.fullName
                name = place.name
                placeType = place.placeType
                url = place.url
                additionalProperties = place.additionalProperties.toMutableMap()
            }

            fun id(id: String) = id(JsonField.of(id))

            /** Sets [Builder.id] to an arbitrary JSON value. */
            fun id(id: JsonField<String>) = apply { this.id = id }

            fun boundingBox(boundingBox: BoundingBox) = boundingBox(JsonField.of(boundingBox))

            /** Sets [Builder.boundingBox] to an arbitrary JSON value. */
            fun boundingBox(boundingBox: JsonField<BoundingBox>) = apply {
                this.boundingBox = boundingBox
            }

            fun country(country: String) = country(JsonField.of(country))

            /** Sets [Builder.country] to an arbitrary JSON value. */
            fun country(country: JsonField<String>) = apply { this.country = country }

            fun countryCode(countryCode: String) = countryCode(JsonField.of(countryCode))

            /** Sets [Builder.countryCode] to an arbitrary JSON value. */
            fun countryCode(countryCode: JsonField<String>) = apply {
                this.countryCode = countryCode
            }

            fun fullName(fullName: String) = fullName(JsonField.of(fullName))

            /** Sets [Builder.fullName] to an arbitrary JSON value. */
            fun fullName(fullName: JsonField<String>) = apply { this.fullName = fullName }

            fun name(name: String) = name(JsonField.of(name))

            /** Sets [Builder.name] to an arbitrary JSON value. */
            fun name(name: JsonField<String>) = apply { this.name = name }

            fun placeType(placeType: String) = placeType(JsonField.of(placeType))

            /** Sets [Builder.placeType] to an arbitrary JSON value. */
            fun placeType(placeType: JsonField<String>) = apply { this.placeType = placeType }

            fun url(url: String) = url(JsonField.of(url))

            /** Sets [Builder.url] to an arbitrary JSON value. */
            fun url(url: JsonField<String>) = apply { this.url = url }

            fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.clear()
                putAllAdditionalProperties(additionalProperties)
            }

            fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                additionalProperties.put(key, value)
            }

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            /**
             * Returns an immutable instance of [Place].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): Place =
                Place(
                    id,
                    boundingBox,
                    country,
                    countryCode,
                    fullName,
                    name,
                    placeType,
                    url,
                    additionalProperties.toMutableMap(),
                )
        }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
         *   match its expected type.
         */
        fun validate(): Place = apply {
            if (validated) {
                return@apply
            }

            id()
            boundingBox()?.validate()
            country()
            countryCode()
            fullName()
            name()
            placeType()
            url()
            validated = true
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: XTwitterScraperInvalidDataException) {
                false
            }

        internal fun validity(): Int =
            (if (id.asKnown() == null) 0 else 1) +
                (boundingBox.asKnown()?.validity() ?: 0) +
                (if (country.asKnown() == null) 0 else 1) +
                (if (countryCode.asKnown() == null) 0 else 1) +
                (if (fullName.asKnown() == null) 0 else 1) +
                (if (name.asKnown() == null) 0 else 1) +
                (if (placeType.asKnown() == null) 0 else 1) +
                (if (url.asKnown() == null) 0 else 1)

        /** Public metadata whose fields X defines. */
        class BoundingBox
        @JsonCreator
        private constructor(
            @com.fasterxml.jackson.annotation.JsonValue
            private val additionalProperties: Map<String, JsonValue>
        ) {

            @JsonAnyGetter
            @ExcludeMissing
            fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

            fun toBuilder() = Builder().from(this)

            companion object {

                /** Returns a mutable builder for constructing an instance of [BoundingBox]. */
                fun builder() = Builder()
            }

            /** A builder for [BoundingBox]. */
            class Builder internal constructor() {

                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                internal fun from(boundingBox: BoundingBox) = apply {
                    additionalProperties = boundingBox.additionalProperties.toMutableMap()
                }

                fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                    this.additionalProperties.clear()
                    putAllAdditionalProperties(additionalProperties)
                }

                fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                    additionalProperties.put(key, value)
                }

                fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) =
                    apply {
                        this.additionalProperties.putAll(additionalProperties)
                    }

                fun removeAdditionalProperty(key: String) = apply {
                    additionalProperties.remove(key)
                }

                fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                    keys.forEach(::removeAdditionalProperty)
                }

                /**
                 * Returns an immutable instance of [BoundingBox].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 */
                fun build(): BoundingBox = BoundingBox(additionalProperties.toImmutable())
            }

            private var validated: Boolean = false

            /**
             * Validates that the types of all values in this object match their expected types
             * recursively.
             *
             * This method is _not_ forwards compatible with new types from the API for existing
             * fields.
             *
             * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
             *   match its expected type.
             */
            fun validate(): BoundingBox = apply {
                if (validated) {
                    return@apply
                }

                validated = true
            }

            fun isValid(): Boolean =
                try {
                    validate()
                    true
                } catch (e: XTwitterScraperInvalidDataException) {
                    false
                }

            internal fun validity(): Int = additionalProperties.count { (_, value) ->
                !value.isNull() && !value.isMissing()
            }

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is BoundingBox && additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy { Objects.hash(additionalProperties) }

            override fun hashCode(): Int = hashCode

            override fun toString() = "BoundingBox{additionalProperties=$additionalProperties}"
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Place &&
                id == other.id &&
                boundingBox == other.boundingBox &&
                country == other.country &&
                countryCode == other.countryCode &&
                fullName == other.fullName &&
                name == other.name &&
                placeType == other.placeType &&
                url == other.url &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(
                id,
                boundingBox,
                country,
                countryCode,
                fullName,
                name,
                placeType,
                url,
                additionalProperties,
            )
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Place{id=$id, boundingBox=$boundingBox, country=$country, countryCode=$countryCode, fullName=$fullName, name=$name, placeType=$placeType, url=$url, additionalProperties=$additionalProperties}"
    }

    /** Public metadata whose fields X defines. */
    class PostCta
    @JsonCreator
    private constructor(
        @com.fasterxml.jackson.annotation.JsonValue
        private val additionalProperties: Map<String, JsonValue>
    ) {

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

        fun toBuilder() = Builder().from(this)

        companion object {

            /** Returns a mutable builder for constructing an instance of [PostCta]. */
            fun builder() = Builder()
        }

        /** A builder for [PostCta]. */
        class Builder internal constructor() {

            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(postCta: PostCta) = apply {
                additionalProperties = postCta.additionalProperties.toMutableMap()
            }

            fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.clear()
                putAllAdditionalProperties(additionalProperties)
            }

            fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                additionalProperties.put(key, value)
            }

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            /**
             * Returns an immutable instance of [PostCta].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): PostCta = PostCta(additionalProperties.toImmutable())
        }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
         *   match its expected type.
         */
        fun validate(): PostCta = apply {
            if (validated) {
                return@apply
            }

            validated = true
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: XTwitterScraperInvalidDataException) {
                false
            }

        internal fun validity(): Int = additionalProperties.count { (_, value) ->
            !value.isNull() && !value.isMissing()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is PostCta && additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() = "PostCta{additionalProperties=$additionalProperties}"
    }

    /** Engagement counts retained from a prior tweet edit. */
    class PreviousCounts
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val bookmarkCount: JsonField<Long>,
        private val likeCount: JsonField<Long>,
        private val quoteCount: JsonField<Long>,
        private val replyCount: JsonField<Long>,
        private val retweetCount: JsonField<Long>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("bookmarkCount")
            @ExcludeMissing
            bookmarkCount: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("likeCount")
            @ExcludeMissing
            likeCount: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("quoteCount")
            @ExcludeMissing
            quoteCount: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("replyCount")
            @ExcludeMissing
            replyCount: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("retweetCount")
            @ExcludeMissing
            retweetCount: JsonField<Long> = JsonMissing.of(),
        ) : this(bookmarkCount, likeCount, quoteCount, replyCount, retweetCount, mutableMapOf())

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun bookmarkCount(): Long? = bookmarkCount.getNullable("bookmarkCount")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun likeCount(): Long? = likeCount.getNullable("likeCount")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun quoteCount(): Long? = quoteCount.getNullable("quoteCount")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun replyCount(): Long? = replyCount.getNullable("replyCount")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun retweetCount(): Long? = retweetCount.getNullable("retweetCount")

        /**
         * Returns the raw JSON value of [bookmarkCount].
         *
         * Unlike [bookmarkCount], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("bookmarkCount")
        @ExcludeMissing
        fun _bookmarkCount(): JsonField<Long> = bookmarkCount

        /**
         * Returns the raw JSON value of [likeCount].
         *
         * Unlike [likeCount], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("likeCount") @ExcludeMissing fun _likeCount(): JsonField<Long> = likeCount

        /**
         * Returns the raw JSON value of [quoteCount].
         *
         * Unlike [quoteCount], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("quoteCount") @ExcludeMissing fun _quoteCount(): JsonField<Long> = quoteCount

        /**
         * Returns the raw JSON value of [replyCount].
         *
         * Unlike [replyCount], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("replyCount") @ExcludeMissing fun _replyCount(): JsonField<Long> = replyCount

        /**
         * Returns the raw JSON value of [retweetCount].
         *
         * Unlike [retweetCount], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("retweetCount")
        @ExcludeMissing
        fun _retweetCount(): JsonField<Long> = retweetCount

        @JsonAnySetter
        private fun putAdditionalProperty(key: String, value: JsonValue) {
            additionalProperties.put(key, value)
        }

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> =
            Collections.unmodifiableMap(additionalProperties)

        fun toBuilder() = Builder().from(this)

        companion object {

            /** Returns a mutable builder for constructing an instance of [PreviousCounts]. */
            fun builder() = Builder()
        }

        /** A builder for [PreviousCounts]. */
        class Builder internal constructor() {

            private var bookmarkCount: JsonField<Long> = JsonMissing.of()
            private var likeCount: JsonField<Long> = JsonMissing.of()
            private var quoteCount: JsonField<Long> = JsonMissing.of()
            private var replyCount: JsonField<Long> = JsonMissing.of()
            private var retweetCount: JsonField<Long> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(previousCounts: PreviousCounts) = apply {
                bookmarkCount = previousCounts.bookmarkCount
                likeCount = previousCounts.likeCount
                quoteCount = previousCounts.quoteCount
                replyCount = previousCounts.replyCount
                retweetCount = previousCounts.retweetCount
                additionalProperties = previousCounts.additionalProperties.toMutableMap()
            }

            fun bookmarkCount(bookmarkCount: Long) = bookmarkCount(JsonField.of(bookmarkCount))

            /** Sets [Builder.bookmarkCount] to an arbitrary JSON value. */
            fun bookmarkCount(bookmarkCount: JsonField<Long>) = apply {
                this.bookmarkCount = bookmarkCount
            }

            fun likeCount(likeCount: Long) = likeCount(JsonField.of(likeCount))

            /** Sets [Builder.likeCount] to an arbitrary JSON value. */
            fun likeCount(likeCount: JsonField<Long>) = apply { this.likeCount = likeCount }

            fun quoteCount(quoteCount: Long) = quoteCount(JsonField.of(quoteCount))

            /** Sets [Builder.quoteCount] to an arbitrary JSON value. */
            fun quoteCount(quoteCount: JsonField<Long>) = apply { this.quoteCount = quoteCount }

            fun replyCount(replyCount: Long) = replyCount(JsonField.of(replyCount))

            /** Sets [Builder.replyCount] to an arbitrary JSON value. */
            fun replyCount(replyCount: JsonField<Long>) = apply { this.replyCount = replyCount }

            fun retweetCount(retweetCount: Long) = retweetCount(JsonField.of(retweetCount))

            /** Sets [Builder.retweetCount] to an arbitrary JSON value. */
            fun retweetCount(retweetCount: JsonField<Long>) = apply {
                this.retweetCount = retweetCount
            }

            fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.clear()
                putAllAdditionalProperties(additionalProperties)
            }

            fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                additionalProperties.put(key, value)
            }

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            /**
             * Returns an immutable instance of [PreviousCounts].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): PreviousCounts =
                PreviousCounts(
                    bookmarkCount,
                    likeCount,
                    quoteCount,
                    replyCount,
                    retweetCount,
                    additionalProperties.toMutableMap(),
                )
        }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
         *   match its expected type.
         */
        fun validate(): PreviousCounts = apply {
            if (validated) {
                return@apply
            }

            bookmarkCount()
            likeCount()
            quoteCount()
            replyCount()
            retweetCount()
            validated = true
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: XTwitterScraperInvalidDataException) {
                false
            }

        internal fun validity(): Int =
            (if (bookmarkCount.asKnown() == null) 0 else 1) +
                (if (likeCount.asKnown() == null) 0 else 1) +
                (if (quoteCount.asKnown() == null) 0 else 1) +
                (if (replyCount.asKnown() == null) 0 else 1) +
                (if (retweetCount.asKnown() == null) 0 else 1)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is PreviousCounts &&
                bookmarkCount == other.bookmarkCount &&
                likeCount == other.likeCount &&
                quoteCount == other.quoteCount &&
                replyCount == other.replyCount &&
                retweetCount == other.retweetCount &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(
                bookmarkCount,
                likeCount,
                quoteCount,
                replyCount,
                retweetCount,
                additionalProperties,
            )
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "PreviousCounts{bookmarkCount=$bookmarkCount, likeCount=$likeCount, quoteCount=$quoteCount, replyCount=$replyCount, retweetCount=$retweetCount, additionalProperties=$additionalProperties}"
    }

    /** The quoted post's link, kept when X withholds that post. */
    class QuotedTweetPermalink
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val displayUrl: JsonField<String>,
        private val expandedUrl: JsonField<String>,
        private val url: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("displayUrl")
            @ExcludeMissing
            displayUrl: JsonField<String> = JsonMissing.of(),
            @JsonProperty("expandedUrl")
            @ExcludeMissing
            expandedUrl: JsonField<String> = JsonMissing.of(),
            @JsonProperty("url") @ExcludeMissing url: JsonField<String> = JsonMissing.of(),
        ) : this(displayUrl, expandedUrl, url, mutableMapOf())

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun displayUrl(): String? = displayUrl.getNullable("displayUrl")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun expandedUrl(): String? = expandedUrl.getNullable("expandedUrl")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun url(): String? = url.getNullable("url")

        /**
         * Returns the raw JSON value of [displayUrl].
         *
         * Unlike [displayUrl], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("displayUrl")
        @ExcludeMissing
        fun _displayUrl(): JsonField<String> = displayUrl

        /**
         * Returns the raw JSON value of [expandedUrl].
         *
         * Unlike [expandedUrl], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("expandedUrl")
        @ExcludeMissing
        fun _expandedUrl(): JsonField<String> = expandedUrl

        /**
         * Returns the raw JSON value of [url].
         *
         * Unlike [url], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("url") @ExcludeMissing fun _url(): JsonField<String> = url

        @JsonAnySetter
        private fun putAdditionalProperty(key: String, value: JsonValue) {
            additionalProperties.put(key, value)
        }

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> =
            Collections.unmodifiableMap(additionalProperties)

        fun toBuilder() = Builder().from(this)

        companion object {

            /** Returns a mutable builder for constructing an instance of [QuotedTweetPermalink]. */
            fun builder() = Builder()
        }

        /** A builder for [QuotedTweetPermalink]. */
        class Builder internal constructor() {

            private var displayUrl: JsonField<String> = JsonMissing.of()
            private var expandedUrl: JsonField<String> = JsonMissing.of()
            private var url: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(quotedTweetPermalink: QuotedTweetPermalink) = apply {
                displayUrl = quotedTweetPermalink.displayUrl
                expandedUrl = quotedTweetPermalink.expandedUrl
                url = quotedTweetPermalink.url
                additionalProperties = quotedTweetPermalink.additionalProperties.toMutableMap()
            }

            fun displayUrl(displayUrl: String) = displayUrl(JsonField.of(displayUrl))

            /** Sets [Builder.displayUrl] to an arbitrary JSON value. */
            fun displayUrl(displayUrl: JsonField<String>) = apply { this.displayUrl = displayUrl }

            fun expandedUrl(expandedUrl: String) = expandedUrl(JsonField.of(expandedUrl))

            /** Sets [Builder.expandedUrl] to an arbitrary JSON value. */
            fun expandedUrl(expandedUrl: JsonField<String>) = apply {
                this.expandedUrl = expandedUrl
            }

            fun url(url: String) = url(JsonField.of(url))

            /** Sets [Builder.url] to an arbitrary JSON value. */
            fun url(url: JsonField<String>) = apply { this.url = url }

            fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.clear()
                putAllAdditionalProperties(additionalProperties)
            }

            fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                additionalProperties.put(key, value)
            }

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            /**
             * Returns an immutable instance of [QuotedTweetPermalink].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): QuotedTweetPermalink =
                QuotedTweetPermalink(
                    displayUrl,
                    expandedUrl,
                    url,
                    additionalProperties.toMutableMap(),
                )
        }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
         *   match its expected type.
         */
        fun validate(): QuotedTweetPermalink = apply {
            if (validated) {
                return@apply
            }

            displayUrl()
            expandedUrl()
            url()
            validated = true
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: XTwitterScraperInvalidDataException) {
                false
            }

        internal fun validity(): Int =
            (if (displayUrl.asKnown() == null) 0 else 1) +
                (if (expandedUrl.asKnown() == null) 0 else 1) +
                (if (url.asKnown() == null) 0 else 1)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is QuotedTweetPermalink &&
                displayUrl == other.displayUrl &&
                expandedUrl == other.expandedUrl &&
                url == other.url &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(displayUrl, expandedUrl, url, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "QuotedTweetPermalink{displayUrl=$displayUrl, expandedUrl=$expandedUrl, url=$url, additionalProperties=$additionalProperties}"
    }

    /**
     * Why X shows no quoted post. Omitted when X names no reason, as its legacy answers never do.
     */
    class QuotedTweetUnavailable
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val reason: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("reason") @ExcludeMissing reason: JsonField<String> = JsonMissing.of()
        ) : this(reason, mutableMapOf())

        /**
         * X's reason, such as `Suspended` for a suspended author or `Protected` for an author who
         * protects their posts.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or
         *   is unexpectedly missing or null.
         */
        fun reason(): String = reason.getRequired("reason")

        /**
         * Returns the raw JSON value of [reason].
         *
         * Unlike [reason], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("reason") @ExcludeMissing fun _reason(): JsonField<String> = reason

        @JsonAnySetter
        private fun putAdditionalProperty(key: String, value: JsonValue) {
            additionalProperties.put(key, value)
        }

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> =
            Collections.unmodifiableMap(additionalProperties)

        fun toBuilder() = Builder().from(this)

        companion object {

            /**
             * Returns a mutable builder for constructing an instance of [QuotedTweetUnavailable].
             *
             * The following fields are required:
             * ```kotlin
             * .reason()
             * ```
             */
            fun builder() = Builder()
        }

        /** A builder for [QuotedTweetUnavailable]. */
        class Builder internal constructor() {

            private var reason: JsonField<String>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(quotedTweetUnavailable: QuotedTweetUnavailable) = apply {
                reason = quotedTweetUnavailable.reason
                additionalProperties = quotedTweetUnavailable.additionalProperties.toMutableMap()
            }

            /**
             * X's reason, such as `Suspended` for a suspended author or `Protected` for an author
             * who protects their posts.
             */
            fun reason(reason: String) = reason(JsonField.of(reason))

            /** Sets [Builder.reason] to an arbitrary JSON value. */
            fun reason(reason: JsonField<String>) = apply { this.reason = reason }

            fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.clear()
                putAllAdditionalProperties(additionalProperties)
            }

            fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                additionalProperties.put(key, value)
            }

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            /**
             * Returns an immutable instance of [QuotedTweetUnavailable].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             *
             * The following fields are required:
             * ```kotlin
             * .reason()
             * ```
             *
             * @throws IllegalStateException if any required field is unset.
             */
            fun build(): QuotedTweetUnavailable =
                QuotedTweetUnavailable(
                    checkRequired("reason", reason),
                    additionalProperties.toMutableMap(),
                )
        }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
         *   match its expected type.
         */
        fun validate(): QuotedTweetUnavailable = apply {
            if (validated) {
                return@apply
            }

            reason()
            validated = true
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: XTwitterScraperInvalidDataException) {
                false
            }

        internal fun validity(): Int = (if (reason.asKnown() == null) 0 else 1)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is QuotedTweetUnavailable &&
                reason == other.reason &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(reason, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "QuotedTweetUnavailable{reason=$reason, additionalProperties=$additionalProperties}"
    }

    /** Public post & user this reaction references. */
    class ReactionContext
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val reactedToPostId: JsonField<String>,
        private val reactedToUser: JsonField<UserProfile>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("reactedToPostId")
            @ExcludeMissing
            reactedToPostId: JsonField<String> = JsonMissing.of(),
            @JsonProperty("reactedToUser")
            @ExcludeMissing
            reactedToUser: JsonField<UserProfile> = JsonMissing.of(),
        ) : this(reactedToPostId, reactedToUser, mutableMapOf())

        /**
         * Referenced post ID.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun reactedToPostId(): String? = reactedToPostId.getNullable("reactedToPostId")

        /**
         * Public X profile.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun reactedToUser(): UserProfile? = reactedToUser.getNullable("reactedToUser")

        /**
         * Returns the raw JSON value of [reactedToPostId].
         *
         * Unlike [reactedToPostId], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("reactedToPostId")
        @ExcludeMissing
        fun _reactedToPostId(): JsonField<String> = reactedToPostId

        /**
         * Returns the raw JSON value of [reactedToUser].
         *
         * Unlike [reactedToUser], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("reactedToUser")
        @ExcludeMissing
        fun _reactedToUser(): JsonField<UserProfile> = reactedToUser

        @JsonAnySetter
        private fun putAdditionalProperty(key: String, value: JsonValue) {
            additionalProperties.put(key, value)
        }

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> =
            Collections.unmodifiableMap(additionalProperties)

        fun toBuilder() = Builder().from(this)

        companion object {

            /** Returns a mutable builder for constructing an instance of [ReactionContext]. */
            fun builder() = Builder()
        }

        /** A builder for [ReactionContext]. */
        class Builder internal constructor() {

            private var reactedToPostId: JsonField<String> = JsonMissing.of()
            private var reactedToUser: JsonField<UserProfile> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(reactionContext: ReactionContext) = apply {
                reactedToPostId = reactionContext.reactedToPostId
                reactedToUser = reactionContext.reactedToUser
                additionalProperties = reactionContext.additionalProperties.toMutableMap()
            }

            /** Referenced post ID. */
            fun reactedToPostId(reactedToPostId: String) =
                reactedToPostId(JsonField.of(reactedToPostId))

            /** Sets [Builder.reactedToPostId] to an arbitrary JSON value. */
            fun reactedToPostId(reactedToPostId: JsonField<String>) = apply {
                this.reactedToPostId = reactedToPostId
            }

            /** Public X profile. */
            fun reactedToUser(reactedToUser: UserProfile) =
                reactedToUser(JsonField.of(reactedToUser))

            /** Sets [Builder.reactedToUser] to an arbitrary JSON value. */
            fun reactedToUser(reactedToUser: JsonField<UserProfile>) = apply {
                this.reactedToUser = reactedToUser
            }

            fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.clear()
                putAllAdditionalProperties(additionalProperties)
            }

            fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                additionalProperties.put(key, value)
            }

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            /**
             * Returns an immutable instance of [ReactionContext].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): ReactionContext =
                ReactionContext(reactedToPostId, reactedToUser, additionalProperties.toMutableMap())
        }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
         *   match its expected type.
         */
        fun validate(): ReactionContext = apply {
            if (validated) {
                return@apply
            }

            reactedToPostId()
            reactedToUser()?.validate()
            validated = true
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: XTwitterScraperInvalidDataException) {
                false
            }

        internal fun validity(): Int =
            (if (reactedToPostId.asKnown() == null) 0 else 1) +
                (reactedToUser.asKnown()?.validity() ?: 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is ReactionContext &&
                reactedToPostId == other.reactedToPostId &&
                reactedToUser == other.reactedToUser &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(reactedToPostId, reactedToUser, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "ReactionContext{reactedToPostId=$reactedToPostId, reactedToUser=$reactedToUser, additionalProperties=$additionalProperties}"
    }

    /** Public metadata whose fields X defines. */
    class Scopes
    @JsonCreator
    private constructor(
        @com.fasterxml.jackson.annotation.JsonValue
        private val additionalProperties: Map<String, JsonValue>
    ) {

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

        fun toBuilder() = Builder().from(this)

        companion object {

            /** Returns a mutable builder for constructing an instance of [Scopes]. */
            fun builder() = Builder()
        }

        /** A builder for [Scopes]. */
        class Builder internal constructor() {

            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(scopes: Scopes) = apply {
                additionalProperties = scopes.additionalProperties.toMutableMap()
            }

            fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.clear()
                putAllAdditionalProperties(additionalProperties)
            }

            fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                additionalProperties.put(key, value)
            }

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            /**
             * Returns an immutable instance of [Scopes].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): Scopes = Scopes(additionalProperties.toImmutable())
        }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
         *   match its expected type.
         */
        fun validate(): Scopes = apply {
            if (validated) {
                return@apply
            }

            validated = true
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: XTwitterScraperInvalidDataException) {
                false
            }

        internal fun validity(): Int = additionalProperties.count { (_, value) ->
            !value.isNull() && !value.isMissing()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Scopes && additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() = "Scopes{additionalProperties=$additionalProperties}"
    }

    /** Game metadata attached to this post. */
    class SportsContext
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val competitors: JsonField<List<Competitor>>,
        private val gameId: JsonField<String>,
        private val league: JsonField<String>,
        private val scheduledAtMs: JsonField<ScheduledAtMs>,
        private val state: JsonField<String>,
        private val statusText: JsonField<String>,
        private val title: JsonField<String>,
        private val url: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("competitors")
            @ExcludeMissing
            competitors: JsonField<List<Competitor>> = JsonMissing.of(),
            @JsonProperty("gameId") @ExcludeMissing gameId: JsonField<String> = JsonMissing.of(),
            @JsonProperty("league") @ExcludeMissing league: JsonField<String> = JsonMissing.of(),
            @JsonProperty("scheduledAtMs")
            @ExcludeMissing
            scheduledAtMs: JsonField<ScheduledAtMs> = JsonMissing.of(),
            @JsonProperty("state") @ExcludeMissing state: JsonField<String> = JsonMissing.of(),
            @JsonProperty("statusText")
            @ExcludeMissing
            statusText: JsonField<String> = JsonMissing.of(),
            @JsonProperty("title") @ExcludeMissing title: JsonField<String> = JsonMissing.of(),
            @JsonProperty("url") @ExcludeMissing url: JsonField<String> = JsonMissing.of(),
        ) : this(
            competitors,
            gameId,
            league,
            scheduledAtMs,
            state,
            statusText,
            title,
            url,
            mutableMapOf(),
        )

        /**
         * Teams & reported scores.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun competitors(): List<Competitor>? = competitors.getNullable("competitors")

        /**
         * Game ID.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun gameId(): String? = gameId.getNullable("gameId")

        /**
         * League ID.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun league(): String? = league.getNullable("league")

        /**
         * Scheduled Unix milliseconds, retaining the source type.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun scheduledAtMs(): ScheduledAtMs? = scheduledAtMs.getNullable("scheduledAtMs")

        /**
         * Reported game state.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun state(): String? = state.getNullable("state")

        /**
         * Game progress text.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun statusText(): String? = statusText.getNullable("statusText")

        /**
         * Game title.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun title(): String? = title.getNullable("title")

        /**
         * Game link.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun url(): String? = url.getNullable("url")

        /**
         * Returns the raw JSON value of [competitors].
         *
         * Unlike [competitors], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("competitors")
        @ExcludeMissing
        fun _competitors(): JsonField<List<Competitor>> = competitors

        /**
         * Returns the raw JSON value of [gameId].
         *
         * Unlike [gameId], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("gameId") @ExcludeMissing fun _gameId(): JsonField<String> = gameId

        /**
         * Returns the raw JSON value of [league].
         *
         * Unlike [league], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("league") @ExcludeMissing fun _league(): JsonField<String> = league

        /**
         * Returns the raw JSON value of [scheduledAtMs].
         *
         * Unlike [scheduledAtMs], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("scheduledAtMs")
        @ExcludeMissing
        fun _scheduledAtMs(): JsonField<ScheduledAtMs> = scheduledAtMs

        /**
         * Returns the raw JSON value of [state].
         *
         * Unlike [state], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("state") @ExcludeMissing fun _state(): JsonField<String> = state

        /**
         * Returns the raw JSON value of [statusText].
         *
         * Unlike [statusText], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("statusText")
        @ExcludeMissing
        fun _statusText(): JsonField<String> = statusText

        /**
         * Returns the raw JSON value of [title].
         *
         * Unlike [title], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("title") @ExcludeMissing fun _title(): JsonField<String> = title

        /**
         * Returns the raw JSON value of [url].
         *
         * Unlike [url], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("url") @ExcludeMissing fun _url(): JsonField<String> = url

        @JsonAnySetter
        private fun putAdditionalProperty(key: String, value: JsonValue) {
            additionalProperties.put(key, value)
        }

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> =
            Collections.unmodifiableMap(additionalProperties)

        fun toBuilder() = Builder().from(this)

        companion object {

            /** Returns a mutable builder for constructing an instance of [SportsContext]. */
            fun builder() = Builder()
        }

        /** A builder for [SportsContext]. */
        class Builder internal constructor() {

            private var competitors: JsonField<MutableList<Competitor>>? = null
            private var gameId: JsonField<String> = JsonMissing.of()
            private var league: JsonField<String> = JsonMissing.of()
            private var scheduledAtMs: JsonField<ScheduledAtMs> = JsonMissing.of()
            private var state: JsonField<String> = JsonMissing.of()
            private var statusText: JsonField<String> = JsonMissing.of()
            private var title: JsonField<String> = JsonMissing.of()
            private var url: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(sportsContext: SportsContext) = apply {
                competitors = sportsContext.competitors.map { it.toMutableList() }
                gameId = sportsContext.gameId
                league = sportsContext.league
                scheduledAtMs = sportsContext.scheduledAtMs
                state = sportsContext.state
                statusText = sportsContext.statusText
                title = sportsContext.title
                url = sportsContext.url
                additionalProperties = sportsContext.additionalProperties.toMutableMap()
            }

            /** Teams & reported scores. */
            fun competitors(competitors: List<Competitor>) = competitors(JsonField.of(competitors))

            /** Sets [Builder.competitors] to an arbitrary JSON value. */
            fun competitors(competitors: JsonField<List<Competitor>>) = apply {
                this.competitors = competitors.map { it.toMutableList() }
            }

            /**
             * Adds a single [Competitor] to [competitors].
             *
             * @throws IllegalStateException if the field was previously set to a non-list.
             */
            fun addCompetitor(competitor: Competitor) = apply {
                competitors =
                    (competitors ?: JsonField.of(mutableListOf())).also {
                        checkKnown("competitors", it).add(competitor)
                    }
            }

            /** Game ID. */
            fun gameId(gameId: String) = gameId(JsonField.of(gameId))

            /** Sets [Builder.gameId] to an arbitrary JSON value. */
            fun gameId(gameId: JsonField<String>) = apply { this.gameId = gameId }

            /** League ID. */
            fun league(league: String) = league(JsonField.of(league))

            /** Sets [Builder.league] to an arbitrary JSON value. */
            fun league(league: JsonField<String>) = apply { this.league = league }

            /** Scheduled Unix milliseconds, retaining the source type. */
            fun scheduledAtMs(scheduledAtMs: ScheduledAtMs) =
                scheduledAtMs(JsonField.of(scheduledAtMs))

            /** Sets [Builder.scheduledAtMs] to an arbitrary JSON value. */
            fun scheduledAtMs(scheduledAtMs: JsonField<ScheduledAtMs>) = apply {
                this.scheduledAtMs = scheduledAtMs
            }

            /** Alias for calling [scheduledAtMs] with `ScheduledAtMs.ofDouble(double)`. */
            fun scheduledAtMs(double: Double) = scheduledAtMs(ScheduledAtMs.ofDouble(double))

            /** Alias for calling [scheduledAtMs] with `ScheduledAtMs.ofString(string)`. */
            fun scheduledAtMs(string: String) = scheduledAtMs(ScheduledAtMs.ofString(string))

            /** Reported game state. */
            fun state(state: String) = state(JsonField.of(state))

            /** Sets [Builder.state] to an arbitrary JSON value. */
            fun state(state: JsonField<String>) = apply { this.state = state }

            /** Game progress text. */
            fun statusText(statusText: String) = statusText(JsonField.of(statusText))

            /** Sets [Builder.statusText] to an arbitrary JSON value. */
            fun statusText(statusText: JsonField<String>) = apply { this.statusText = statusText }

            /** Game title. */
            fun title(title: String) = title(JsonField.of(title))

            /** Sets [Builder.title] to an arbitrary JSON value. */
            fun title(title: JsonField<String>) = apply { this.title = title }

            /** Game link. */
            fun url(url: String) = url(JsonField.of(url))

            /** Sets [Builder.url] to an arbitrary JSON value. */
            fun url(url: JsonField<String>) = apply { this.url = url }

            fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.clear()
                putAllAdditionalProperties(additionalProperties)
            }

            fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                additionalProperties.put(key, value)
            }

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            /**
             * Returns an immutable instance of [SportsContext].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): SportsContext =
                SportsContext(
                    (competitors ?: JsonMissing.of()).map { it.toImmutable() },
                    gameId,
                    league,
                    scheduledAtMs,
                    state,
                    statusText,
                    title,
                    url,
                    additionalProperties.toMutableMap(),
                )
        }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
         *   match its expected type.
         */
        fun validate(): SportsContext = apply {
            if (validated) {
                return@apply
            }

            competitors()?.forEach { it.validate() }
            gameId()
            league()
            scheduledAtMs()?.validate()
            state()
            statusText()
            title()
            url()
            validated = true
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: XTwitterScraperInvalidDataException) {
                false
            }

        internal fun validity(): Int =
            (competitors.asKnown()?.sumOf { it.validity() } ?: 0) +
                (if (gameId.asKnown() == null) 0 else 1) +
                (if (league.asKnown() == null) 0 else 1) +
                (scheduledAtMs.asKnown()?.validity() ?: 0) +
                (if (state.asKnown() == null) 0 else 1) +
                (if (statusText.asKnown() == null) 0 else 1) +
                (if (title.asKnown() == null) 0 else 1) +
                (if (url.asKnown() == null) 0 else 1)

        class Competitor
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val abbreviation: JsonField<String>,
            private val color: JsonField<String>,
            private val darkLogoUrl: JsonField<String>,
            private val isActive: JsonField<Boolean>,
            private val isWinner: JsonField<Boolean>,
            private val logoUrl: JsonField<String>,
            private val name: JsonField<String>,
            private val score: JsonField<String>,
            private val teamId: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("abbreviation")
                @ExcludeMissing
                abbreviation: JsonField<String> = JsonMissing.of(),
                @JsonProperty("color") @ExcludeMissing color: JsonField<String> = JsonMissing.of(),
                @JsonProperty("darkLogoUrl")
                @ExcludeMissing
                darkLogoUrl: JsonField<String> = JsonMissing.of(),
                @JsonProperty("isActive")
                @ExcludeMissing
                isActive: JsonField<Boolean> = JsonMissing.of(),
                @JsonProperty("isWinner")
                @ExcludeMissing
                isWinner: JsonField<Boolean> = JsonMissing.of(),
                @JsonProperty("logoUrl")
                @ExcludeMissing
                logoUrl: JsonField<String> = JsonMissing.of(),
                @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
                @JsonProperty("score") @ExcludeMissing score: JsonField<String> = JsonMissing.of(),
                @JsonProperty("teamId")
                @ExcludeMissing
                teamId: JsonField<String> = JsonMissing.of(),
            ) : this(
                abbreviation,
                color,
                darkLogoUrl,
                isActive,
                isWinner,
                logoUrl,
                name,
                score,
                teamId,
                mutableMapOf(),
            )

            /**
             * Team abbreviation.
             *
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun abbreviation(): String? = abbreviation.getNullable("abbreviation")

            /**
             * Team display color.
             *
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun color(): String? = color.getNullable("color")

            /**
             * Logo for dark backgrounds.
             *
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun darkLogoUrl(): String? = darkLogoUrl.getNullable("darkLogoUrl")

            /**
             * Active competitor.
             *
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun isActive(): Boolean? = isActive.getNullable("isActive")

            /**
             * Winning competitor.
             *
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun isWinner(): Boolean? = isWinner.getNullable("isWinner")

            /**
             * Logo for light backgrounds.
             *
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun logoUrl(): String? = logoUrl.getNullable("logoUrl")

            /**
             * Team name.
             *
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun name(): String? = name.getNullable("name")

            /**
             * Score display text.
             *
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun score(): String? = score.getNullable("score")

            /**
             * Team ID.
             *
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun teamId(): String? = teamId.getNullable("teamId")

            /**
             * Returns the raw JSON value of [abbreviation].
             *
             * Unlike [abbreviation], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("abbreviation")
            @ExcludeMissing
            fun _abbreviation(): JsonField<String> = abbreviation

            /**
             * Returns the raw JSON value of [color].
             *
             * Unlike [color], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("color") @ExcludeMissing fun _color(): JsonField<String> = color

            /**
             * Returns the raw JSON value of [darkLogoUrl].
             *
             * Unlike [darkLogoUrl], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("darkLogoUrl")
            @ExcludeMissing
            fun _darkLogoUrl(): JsonField<String> = darkLogoUrl

            /**
             * Returns the raw JSON value of [isActive].
             *
             * Unlike [isActive], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("isActive") @ExcludeMissing fun _isActive(): JsonField<Boolean> = isActive

            /**
             * Returns the raw JSON value of [isWinner].
             *
             * Unlike [isWinner], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("isWinner") @ExcludeMissing fun _isWinner(): JsonField<Boolean> = isWinner

            /**
             * Returns the raw JSON value of [logoUrl].
             *
             * Unlike [logoUrl], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("logoUrl") @ExcludeMissing fun _logoUrl(): JsonField<String> = logoUrl

            /**
             * Returns the raw JSON value of [name].
             *
             * Unlike [name], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<String> = name

            /**
             * Returns the raw JSON value of [score].
             *
             * Unlike [score], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("score") @ExcludeMissing fun _score(): JsonField<String> = score

            /**
             * Returns the raw JSON value of [teamId].
             *
             * Unlike [teamId], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("teamId") @ExcludeMissing fun _teamId(): JsonField<String> = teamId

            @JsonAnySetter
            private fun putAdditionalProperty(key: String, value: JsonValue) {
                additionalProperties.put(key, value)
            }

            @JsonAnyGetter
            @ExcludeMissing
            fun _additionalProperties(): Map<String, JsonValue> =
                Collections.unmodifiableMap(additionalProperties)

            fun toBuilder() = Builder().from(this)

            companion object {

                /** Returns a mutable builder for constructing an instance of [Competitor]. */
                fun builder() = Builder()
            }

            /** A builder for [Competitor]. */
            class Builder internal constructor() {

                private var abbreviation: JsonField<String> = JsonMissing.of()
                private var color: JsonField<String> = JsonMissing.of()
                private var darkLogoUrl: JsonField<String> = JsonMissing.of()
                private var isActive: JsonField<Boolean> = JsonMissing.of()
                private var isWinner: JsonField<Boolean> = JsonMissing.of()
                private var logoUrl: JsonField<String> = JsonMissing.of()
                private var name: JsonField<String> = JsonMissing.of()
                private var score: JsonField<String> = JsonMissing.of()
                private var teamId: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                internal fun from(competitor: Competitor) = apply {
                    abbreviation = competitor.abbreviation
                    color = competitor.color
                    darkLogoUrl = competitor.darkLogoUrl
                    isActive = competitor.isActive
                    isWinner = competitor.isWinner
                    logoUrl = competitor.logoUrl
                    name = competitor.name
                    score = competitor.score
                    teamId = competitor.teamId
                    additionalProperties = competitor.additionalProperties.toMutableMap()
                }

                /** Team abbreviation. */
                fun abbreviation(abbreviation: String) = abbreviation(JsonField.of(abbreviation))

                /** Sets [Builder.abbreviation] to an arbitrary JSON value. */
                fun abbreviation(abbreviation: JsonField<String>) = apply {
                    this.abbreviation = abbreviation
                }

                /** Team display color. */
                fun color(color: String) = color(JsonField.of(color))

                /** Sets [Builder.color] to an arbitrary JSON value. */
                fun color(color: JsonField<String>) = apply { this.color = color }

                /** Logo for dark backgrounds. */
                fun darkLogoUrl(darkLogoUrl: String) = darkLogoUrl(JsonField.of(darkLogoUrl))

                /** Sets [Builder.darkLogoUrl] to an arbitrary JSON value. */
                fun darkLogoUrl(darkLogoUrl: JsonField<String>) = apply {
                    this.darkLogoUrl = darkLogoUrl
                }

                /** Active competitor. */
                fun isActive(isActive: Boolean) = isActive(JsonField.of(isActive))

                /** Sets [Builder.isActive] to an arbitrary JSON value. */
                fun isActive(isActive: JsonField<Boolean>) = apply { this.isActive = isActive }

                /** Winning competitor. */
                fun isWinner(isWinner: Boolean) = isWinner(JsonField.of(isWinner))

                /** Sets [Builder.isWinner] to an arbitrary JSON value. */
                fun isWinner(isWinner: JsonField<Boolean>) = apply { this.isWinner = isWinner }

                /** Logo for light backgrounds. */
                fun logoUrl(logoUrl: String) = logoUrl(JsonField.of(logoUrl))

                /** Sets [Builder.logoUrl] to an arbitrary JSON value. */
                fun logoUrl(logoUrl: JsonField<String>) = apply { this.logoUrl = logoUrl }

                /** Team name. */
                fun name(name: String) = name(JsonField.of(name))

                /** Sets [Builder.name] to an arbitrary JSON value. */
                fun name(name: JsonField<String>) = apply { this.name = name }

                /** Score display text. */
                fun score(score: String) = score(JsonField.of(score))

                /** Sets [Builder.score] to an arbitrary JSON value. */
                fun score(score: JsonField<String>) = apply { this.score = score }

                /** Team ID. */
                fun teamId(teamId: String) = teamId(JsonField.of(teamId))

                /** Sets [Builder.teamId] to an arbitrary JSON value. */
                fun teamId(teamId: JsonField<String>) = apply { this.teamId = teamId }

                fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                    this.additionalProperties.clear()
                    putAllAdditionalProperties(additionalProperties)
                }

                fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                    additionalProperties.put(key, value)
                }

                fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) =
                    apply {
                        this.additionalProperties.putAll(additionalProperties)
                    }

                fun removeAdditionalProperty(key: String) = apply {
                    additionalProperties.remove(key)
                }

                fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                    keys.forEach(::removeAdditionalProperty)
                }

                /**
                 * Returns an immutable instance of [Competitor].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 */
                fun build(): Competitor =
                    Competitor(
                        abbreviation,
                        color,
                        darkLogoUrl,
                        isActive,
                        isWinner,
                        logoUrl,
                        name,
                        score,
                        teamId,
                        additionalProperties.toMutableMap(),
                    )
            }

            private var validated: Boolean = false

            /**
             * Validates that the types of all values in this object match their expected types
             * recursively.
             *
             * This method is _not_ forwards compatible with new types from the API for existing
             * fields.
             *
             * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
             *   match its expected type.
             */
            fun validate(): Competitor = apply {
                if (validated) {
                    return@apply
                }

                abbreviation()
                color()
                darkLogoUrl()
                isActive()
                isWinner()
                logoUrl()
                name()
                score()
                teamId()
                validated = true
            }

            fun isValid(): Boolean =
                try {
                    validate()
                    true
                } catch (e: XTwitterScraperInvalidDataException) {
                    false
                }

            internal fun validity(): Int =
                (if (abbreviation.asKnown() == null) 0 else 1) +
                    (if (color.asKnown() == null) 0 else 1) +
                    (if (darkLogoUrl.asKnown() == null) 0 else 1) +
                    (if (isActive.asKnown() == null) 0 else 1) +
                    (if (isWinner.asKnown() == null) 0 else 1) +
                    (if (logoUrl.asKnown() == null) 0 else 1) +
                    (if (name.asKnown() == null) 0 else 1) +
                    (if (score.asKnown() == null) 0 else 1) +
                    (if (teamId.asKnown() == null) 0 else 1)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is Competitor &&
                    abbreviation == other.abbreviation &&
                    color == other.color &&
                    darkLogoUrl == other.darkLogoUrl &&
                    isActive == other.isActive &&
                    isWinner == other.isWinner &&
                    logoUrl == other.logoUrl &&
                    name == other.name &&
                    score == other.score &&
                    teamId == other.teamId &&
                    additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(
                    abbreviation,
                    color,
                    darkLogoUrl,
                    isActive,
                    isWinner,
                    logoUrl,
                    name,
                    score,
                    teamId,
                    additionalProperties,
                )
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "Competitor{abbreviation=$abbreviation, color=$color, darkLogoUrl=$darkLogoUrl, isActive=$isActive, isWinner=$isWinner, logoUrl=$logoUrl, name=$name, score=$score, teamId=$teamId, additionalProperties=$additionalProperties}"
        }

        /** Scheduled Unix milliseconds, retaining the source type. */
        @JsonDeserialize(using = ScheduledAtMs.Deserializer::class)
        @JsonSerialize(using = ScheduledAtMs.Serializer::class)
        class ScheduledAtMs
        private constructor(
            private val double: Double? = null,
            private val string: String? = null,
            private val _json: JsonValue? = null,
        ) {

            fun double(): Double? = double

            fun string(): String? = string

            fun isDouble(): Boolean = double != null

            fun isString(): Boolean = string != null

            fun asDouble(): Double = double.getOrThrow("double")

            fun asString(): String = string.getOrThrow("string")

            fun _json(): JsonValue? = _json

            /**
             * Maps this instance's current variant to a value of type [T] using the given
             * [visitor].
             *
             * @throws XTwitterScraperInvalidDataException if [Visitor.unknown] is not overridden in
             *   [visitor] and the current variant is unknown.
             */
            fun <T> accept(visitor: Visitor<T>): T =
                when {
                    double != null -> visitor.visitDouble(double)
                    string != null -> visitor.visitString(string)
                    else -> visitor.unknown(_json)
                }

            private var validated: Boolean = false

            /**
             * Validates that the types of all values in this object match their expected types
             * recursively.
             *
             * This method is _not_ forwards compatible with new types from the API for existing
             * fields.
             *
             * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
             *   match its expected type.
             */
            fun validate(): ScheduledAtMs = apply {
                if (validated) {
                    return@apply
                }

                accept(
                    object : Visitor<Unit> {
                        override fun visitDouble(double: Double) {}

                        override fun visitString(string: String) {}
                    }
                )
                validated = true
            }

            fun isValid(): Boolean =
                try {
                    validate()
                    true
                } catch (e: XTwitterScraperInvalidDataException) {
                    false
                }

            internal fun validity(): Int =
                accept(
                    object : Visitor<Int> {
                        override fun visitDouble(double: Double) = 1

                        override fun visitString(string: String) = 1

                        override fun unknown(json: JsonValue?) = 0
                    }
                )

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is ScheduledAtMs && double == other.double && string == other.string
            }

            override fun hashCode(): Int = Objects.hash(double, string)

            override fun toString(): String =
                when {
                    double != null -> "ScheduledAtMs{double=$double}"
                    string != null -> "ScheduledAtMs{string=$string}"
                    _json != null -> "ScheduledAtMs{_unknown=$_json}"
                    else -> throw IllegalStateException("Invalid ScheduledAtMs")
                }

            companion object {

                fun ofDouble(double: Double) = ScheduledAtMs(double = double)

                fun ofString(string: String) = ScheduledAtMs(string = string)
            }

            /**
             * An interface that defines how to map each variant of [ScheduledAtMs] to a value of
             * type [T].
             */
            interface Visitor<out T> {

                fun visitDouble(double: Double): T

                fun visitString(string: String): T

                /**
                 * Maps an unknown variant of [ScheduledAtMs] to a value of type [T].
                 *
                 * An instance of [ScheduledAtMs] can contain an unknown variant if it was
                 * deserialized from data that doesn't match any known variant.
                 *
                 * @throws XTwitterScraperInvalidDataException in the default implementation.
                 */
                fun unknown(json: JsonValue?): T {
                    throw XTwitterScraperInvalidDataException("Unknown ScheduledAtMs: $json")
                }
            }

            internal class Deserializer : BaseDeserializer<ScheduledAtMs>(ScheduledAtMs::class) {

                override fun ObjectCodec.deserialize(node: JsonNode): ScheduledAtMs {
                    val json = JsonValue.fromJsonNode(node)

                    val bestMatches =
                        sequenceOf(
                                tryDeserialize(node, jacksonTypeRef<Double>())?.let {
                                    ScheduledAtMs(double = it, _json = json)
                                },
                                tryDeserialize(node, jacksonTypeRef<String>())?.let {
                                    ScheduledAtMs(string = it, _json = json)
                                },
                            )
                            .filterNotNull()
                            .allMaxBy { it.validity() }
                            .toList()
                    return when (bestMatches.size) {
                        0 -> ScheduledAtMs(_json = json)
                        1 -> bestMatches.single()
                        else -> bestMatches.firstOrNull { it.isValid() } ?: bestMatches.first()
                    }
                }
            }

            internal class Serializer : BaseSerializer<ScheduledAtMs>(ScheduledAtMs::class) {

                override fun serialize(
                    value: ScheduledAtMs,
                    generator: JsonGenerator,
                    provider: SerializerProvider,
                ) {
                    when {
                        value.double != null -> generator.writeObject(value.double)
                        value.string != null -> generator.writeObject(value.string)
                        value._json != null -> generator.writeObject(value._json)
                        else -> throw IllegalStateException("Invalid ScheduledAtMs")
                    }
                }
            }
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is SportsContext &&
                competitors == other.competitors &&
                gameId == other.gameId &&
                league == other.league &&
                scheduledAtMs == other.scheduledAtMs &&
                state == other.state &&
                statusText == other.statusText &&
                title == other.title &&
                url == other.url &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(
                competitors,
                gameId,
                league,
                scheduledAtMs,
                state,
                statusText,
                title,
                url,
                additionalProperties,
            )
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "SportsContext{competitors=$competitors, gameId=$gameId, league=$league, scheduledAtMs=$scheduledAtMs, state=$state, statusText=$statusText, title=$title, url=$url, additionalProperties=$additionalProperties}"
    }

    /**
     * The Subscriber badge X shows on a reply. The reply's author subscribes to the creator who
     * started the conversation. Omitted on a post without the badge.
     */
    class SubscriberBadge
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val creatorId: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("creatorId")
            @ExcludeMissing
            creatorId: JsonField<String> = JsonMissing.of()
        ) : this(creatorId, mutableMapOf())

        /**
         * Account ID of the creator the author subscribes to.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or
         *   is unexpectedly missing or null.
         */
        fun creatorId(): String = creatorId.getRequired("creatorId")

        /**
         * Returns the raw JSON value of [creatorId].
         *
         * Unlike [creatorId], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("creatorId") @ExcludeMissing fun _creatorId(): JsonField<String> = creatorId

        @JsonAnySetter
        private fun putAdditionalProperty(key: String, value: JsonValue) {
            additionalProperties.put(key, value)
        }

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> =
            Collections.unmodifiableMap(additionalProperties)

        fun toBuilder() = Builder().from(this)

        companion object {

            /**
             * Returns a mutable builder for constructing an instance of [SubscriberBadge].
             *
             * The following fields are required:
             * ```kotlin
             * .creatorId()
             * ```
             */
            fun builder() = Builder()
        }

        /** A builder for [SubscriberBadge]. */
        class Builder internal constructor() {

            private var creatorId: JsonField<String>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(subscriberBadge: SubscriberBadge) = apply {
                creatorId = subscriberBadge.creatorId
                additionalProperties = subscriberBadge.additionalProperties.toMutableMap()
            }

            /** Account ID of the creator the author subscribes to. */
            fun creatorId(creatorId: String) = creatorId(JsonField.of(creatorId))

            /** Sets [Builder.creatorId] to an arbitrary JSON value. */
            fun creatorId(creatorId: JsonField<String>) = apply { this.creatorId = creatorId }

            fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.clear()
                putAllAdditionalProperties(additionalProperties)
            }

            fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                additionalProperties.put(key, value)
            }

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            /**
             * Returns an immutable instance of [SubscriberBadge].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             *
             * The following fields are required:
             * ```kotlin
             * .creatorId()
             * ```
             *
             * @throws IllegalStateException if any required field is unset.
             */
            fun build(): SubscriberBadge =
                SubscriberBadge(
                    checkRequired("creatorId", creatorId),
                    additionalProperties.toMutableMap(),
                )
        }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
         *   match its expected type.
         */
        fun validate(): SubscriberBadge = apply {
            if (validated) {
                return@apply
            }

            creatorId()
            validated = true
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: XTwitterScraperInvalidDataException) {
                false
            }

        internal fun validity(): Int = (if (creatorId.asKnown() == null) 0 else 1)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is SubscriberBadge &&
                creatorId == other.creatorId &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(creatorId, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "SubscriberBadge{creatorId=$creatorId, additionalProperties=$additionalProperties}"
    }

    class TextHighlight
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val endIndex: JsonField<Long>,
        private val startIndex: JsonField<Long>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("endIndex") @ExcludeMissing endIndex: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("startIndex")
            @ExcludeMissing
            startIndex: JsonField<Long> = JsonMissing.of(),
        ) : this(endIndex, startIndex, mutableMapOf())

        /**
         * The first code point after the match.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or
         *   is unexpectedly missing or null.
         */
        fun endIndex(): Long = endIndex.getRequired("endIndex")

        /**
         * The first code point of the match.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or
         *   is unexpectedly missing or null.
         */
        fun startIndex(): Long = startIndex.getRequired("startIndex")

        /**
         * Returns the raw JSON value of [endIndex].
         *
         * Unlike [endIndex], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("endIndex") @ExcludeMissing fun _endIndex(): JsonField<Long> = endIndex

        /**
         * Returns the raw JSON value of [startIndex].
         *
         * Unlike [startIndex], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("startIndex") @ExcludeMissing fun _startIndex(): JsonField<Long> = startIndex

        @JsonAnySetter
        private fun putAdditionalProperty(key: String, value: JsonValue) {
            additionalProperties.put(key, value)
        }

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> =
            Collections.unmodifiableMap(additionalProperties)

        fun toBuilder() = Builder().from(this)

        companion object {

            /**
             * Returns a mutable builder for constructing an instance of [TextHighlight].
             *
             * The following fields are required:
             * ```kotlin
             * .endIndex()
             * .startIndex()
             * ```
             */
            fun builder() = Builder()
        }

        /** A builder for [TextHighlight]. */
        class Builder internal constructor() {

            private var endIndex: JsonField<Long>? = null
            private var startIndex: JsonField<Long>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(textHighlight: TextHighlight) = apply {
                endIndex = textHighlight.endIndex
                startIndex = textHighlight.startIndex
                additionalProperties = textHighlight.additionalProperties.toMutableMap()
            }

            /** The first code point after the match. */
            fun endIndex(endIndex: Long) = endIndex(JsonField.of(endIndex))

            /** Sets [Builder.endIndex] to an arbitrary JSON value. */
            fun endIndex(endIndex: JsonField<Long>) = apply { this.endIndex = endIndex }

            /** The first code point of the match. */
            fun startIndex(startIndex: Long) = startIndex(JsonField.of(startIndex))

            /** Sets [Builder.startIndex] to an arbitrary JSON value. */
            fun startIndex(startIndex: JsonField<Long>) = apply { this.startIndex = startIndex }

            fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.clear()
                putAllAdditionalProperties(additionalProperties)
            }

            fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                additionalProperties.put(key, value)
            }

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            /**
             * Returns an immutable instance of [TextHighlight].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             *
             * The following fields are required:
             * ```kotlin
             * .endIndex()
             * .startIndex()
             * ```
             *
             * @throws IllegalStateException if any required field is unset.
             */
            fun build(): TextHighlight =
                TextHighlight(
                    checkRequired("endIndex", endIndex),
                    checkRequired("startIndex", startIndex),
                    additionalProperties.toMutableMap(),
                )
        }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
         *   match its expected type.
         */
        fun validate(): TextHighlight = apply {
            if (validated) {
                return@apply
            }

            endIndex()
            startIndex()
            validated = true
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: XTwitterScraperInvalidDataException) {
                false
            }

        internal fun validity(): Int =
            (if (endIndex.asKnown() == null) 0 else 1) +
                (if (startIndex.asKnown() == null) 0 else 1)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is TextHighlight &&
                endIndex == other.endIndex &&
                startIndex == other.startIndex &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(endIndex, startIndex, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "TextHighlight{endIndex=$endIndex, startIndex=$startIndex, additionalProperties=$additionalProperties}"
    }

    /** Public visibility notice attached to an available tweet. */
    class Tombstone
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val text: JsonField<Text>,
        private val type: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("text") @ExcludeMissing text: JsonField<Text> = JsonMissing.of(),
            @JsonProperty("type") @ExcludeMissing type: JsonField<String> = JsonMissing.of(),
        ) : this(text, type, mutableMapOf())

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun text(): Text? = text.getNullable("text")

        /**
         * Visibility notice type.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun type(): String? = type.getNullable("type")

        /**
         * Returns the raw JSON value of [text].
         *
         * Unlike [text], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("text") @ExcludeMissing fun _text(): JsonField<Text> = text

        /**
         * Returns the raw JSON value of [type].
         *
         * Unlike [type], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<String> = type

        @JsonAnySetter
        private fun putAdditionalProperty(key: String, value: JsonValue) {
            additionalProperties.put(key, value)
        }

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> =
            Collections.unmodifiableMap(additionalProperties)

        fun toBuilder() = Builder().from(this)

        companion object {

            /** Returns a mutable builder for constructing an instance of [Tombstone]. */
            fun builder() = Builder()
        }

        /** A builder for [Tombstone]. */
        class Builder internal constructor() {

            private var text: JsonField<Text> = JsonMissing.of()
            private var type: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(tombstone: Tombstone) = apply {
                text = tombstone.text
                type = tombstone.type
                additionalProperties = tombstone.additionalProperties.toMutableMap()
            }

            fun text(text: Text) = text(JsonField.of(text))

            /** Sets [Builder.text] to an arbitrary JSON value. */
            fun text(text: JsonField<Text>) = apply { this.text = text }

            /** Visibility notice type. */
            fun type(type: String) = type(JsonField.of(type))

            /** Sets [Builder.type] to an arbitrary JSON value. */
            fun type(type: JsonField<String>) = apply { this.type = type }

            fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.clear()
                putAllAdditionalProperties(additionalProperties)
            }

            fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                additionalProperties.put(key, value)
            }

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            /**
             * Returns an immutable instance of [Tombstone].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): Tombstone = Tombstone(text, type, additionalProperties.toMutableMap())
        }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
         *   match its expected type.
         */
        fun validate(): Tombstone = apply {
            if (validated) {
                return@apply
            }

            text()?.validate()
            type()
            validated = true
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: XTwitterScraperInvalidDataException) {
                false
            }

        internal fun validity(): Int =
            (text.asKnown()?.validity() ?: 0) + (if (type.asKnown() == null) 0 else 1)

        class Text
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val entities: JsonField<List<Entity>>,
            private val rtl: JsonField<Boolean>,
            private val text: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("entities")
                @ExcludeMissing
                entities: JsonField<List<Entity>> = JsonMissing.of(),
                @JsonProperty("rtl") @ExcludeMissing rtl: JsonField<Boolean> = JsonMissing.of(),
                @JsonProperty("text") @ExcludeMissing text: JsonField<String> = JsonMissing.of(),
            ) : this(entities, rtl, text, mutableMapOf())

            /**
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun entities(): List<Entity>? = entities.getNullable("entities")

            /**
             * Right-to-left text direction.
             *
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun rtl(): Boolean? = rtl.getNullable("rtl")

            /**
             * Human-readable notice text.
             *
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun text(): String? = text.getNullable("text")

            /**
             * Returns the raw JSON value of [entities].
             *
             * Unlike [entities], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("entities")
            @ExcludeMissing
            fun _entities(): JsonField<List<Entity>> = entities

            /**
             * Returns the raw JSON value of [rtl].
             *
             * Unlike [rtl], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("rtl") @ExcludeMissing fun _rtl(): JsonField<Boolean> = rtl

            /**
             * Returns the raw JSON value of [text].
             *
             * Unlike [text], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("text") @ExcludeMissing fun _text(): JsonField<String> = text

            @JsonAnySetter
            private fun putAdditionalProperty(key: String, value: JsonValue) {
                additionalProperties.put(key, value)
            }

            @JsonAnyGetter
            @ExcludeMissing
            fun _additionalProperties(): Map<String, JsonValue> =
                Collections.unmodifiableMap(additionalProperties)

            fun toBuilder() = Builder().from(this)

            companion object {

                /** Returns a mutable builder for constructing an instance of [Text]. */
                fun builder() = Builder()
            }

            /** A builder for [Text]. */
            class Builder internal constructor() {

                private var entities: JsonField<MutableList<Entity>>? = null
                private var rtl: JsonField<Boolean> = JsonMissing.of()
                private var text: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                internal fun from(text: Text) = apply {
                    entities = text.entities.map { it.toMutableList() }
                    rtl = text.rtl
                    this.text = text.text
                    additionalProperties = text.additionalProperties.toMutableMap()
                }

                fun entities(entities: List<Entity>) = entities(JsonField.of(entities))

                /** Sets [Builder.entities] to an arbitrary JSON value. */
                fun entities(entities: JsonField<List<Entity>>) = apply {
                    this.entities = entities.map { it.toMutableList() }
                }

                /**
                 * Adds a single [Entity] to [entities].
                 *
                 * @throws IllegalStateException if the field was previously set to a non-list.
                 */
                fun addEntity(entity: Entity) = apply {
                    entities =
                        (entities ?: JsonField.of(mutableListOf())).also {
                            checkKnown("entities", it).add(entity)
                        }
                }

                /** Right-to-left text direction. */
                fun rtl(rtl: Boolean) = rtl(JsonField.of(rtl))

                /** Sets [Builder.rtl] to an arbitrary JSON value. */
                fun rtl(rtl: JsonField<Boolean>) = apply { this.rtl = rtl }

                /** Human-readable notice text. */
                fun text(text: String) = text(JsonField.of(text))

                /** Sets [Builder.text] to an arbitrary JSON value. */
                fun text(text: JsonField<String>) = apply { this.text = text }

                fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                    this.additionalProperties.clear()
                    putAllAdditionalProperties(additionalProperties)
                }

                fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                    additionalProperties.put(key, value)
                }

                fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) =
                    apply {
                        this.additionalProperties.putAll(additionalProperties)
                    }

                fun removeAdditionalProperty(key: String) = apply {
                    additionalProperties.remove(key)
                }

                fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                    keys.forEach(::removeAdditionalProperty)
                }

                /**
                 * Returns an immutable instance of [Text].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 */
                fun build(): Text =
                    Text(
                        (entities ?: JsonMissing.of()).map { it.toImmutable() },
                        rtl,
                        text,
                        additionalProperties.toMutableMap(),
                    )
            }

            private var validated: Boolean = false

            /**
             * Validates that the types of all values in this object match their expected types
             * recursively.
             *
             * This method is _not_ forwards compatible with new types from the API for existing
             * fields.
             *
             * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
             *   match its expected type.
             */
            fun validate(): Text = apply {
                if (validated) {
                    return@apply
                }

                entities()?.forEach { it.validate() }
                rtl()
                text()
                validated = true
            }

            fun isValid(): Boolean =
                try {
                    validate()
                    true
                } catch (e: XTwitterScraperInvalidDataException) {
                    false
                }

            internal fun validity(): Int =
                (entities.asKnown()?.sumOf { it.validity() } ?: 0) +
                    (if (rtl.asKnown() == null) 0 else 1) +
                    (if (text.asKnown() == null) 0 else 1)

            class Entity
            @JsonCreator(mode = JsonCreator.Mode.DISABLED)
            private constructor(
                private val fromIndex: JsonField<Long>,
                private val ref: JsonField<Ref>,
                private val toIndex: JsonField<Long>,
                private val additionalProperties: MutableMap<String, JsonValue>,
            ) {

                @JsonCreator
                private constructor(
                    @JsonProperty("fromIndex")
                    @ExcludeMissing
                    fromIndex: JsonField<Long> = JsonMissing.of(),
                    @JsonProperty("ref") @ExcludeMissing ref: JsonField<Ref> = JsonMissing.of(),
                    @JsonProperty("toIndex")
                    @ExcludeMissing
                    toIndex: JsonField<Long> = JsonMissing.of(),
                ) : this(fromIndex, ref, toIndex, mutableMapOf())

                /**
                 * Whole number from 0.
                 *
                 * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected
                 *   type.
                 */
                fun fromIndex(): Long? = fromIndex.getNullable("fromIndex")

                /**
                 * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected
                 *   type.
                 */
                fun ref(): Ref? = ref.getNullable("ref")

                /**
                 * Whole number from 0.
                 *
                 * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected
                 *   type.
                 */
                fun toIndex(): Long? = toIndex.getNullable("toIndex")

                /**
                 * Returns the raw JSON value of [fromIndex].
                 *
                 * Unlike [fromIndex], this method doesn't throw if the JSON field has an unexpected
                 * type.
                 */
                @JsonProperty("fromIndex")
                @ExcludeMissing
                fun _fromIndex(): JsonField<Long> = fromIndex

                /**
                 * Returns the raw JSON value of [ref].
                 *
                 * Unlike [ref], this method doesn't throw if the JSON field has an unexpected type.
                 */
                @JsonProperty("ref") @ExcludeMissing fun _ref(): JsonField<Ref> = ref

                /**
                 * Returns the raw JSON value of [toIndex].
                 *
                 * Unlike [toIndex], this method doesn't throw if the JSON field has an unexpected
                 * type.
                 */
                @JsonProperty("toIndex") @ExcludeMissing fun _toIndex(): JsonField<Long> = toIndex

                @JsonAnySetter
                private fun putAdditionalProperty(key: String, value: JsonValue) {
                    additionalProperties.put(key, value)
                }

                @JsonAnyGetter
                @ExcludeMissing
                fun _additionalProperties(): Map<String, JsonValue> =
                    Collections.unmodifiableMap(additionalProperties)

                fun toBuilder() = Builder().from(this)

                companion object {

                    /** Returns a mutable builder for constructing an instance of [Entity]. */
                    fun builder() = Builder()
                }

                /** A builder for [Entity]. */
                class Builder internal constructor() {

                    private var fromIndex: JsonField<Long> = JsonMissing.of()
                    private var ref: JsonField<Ref> = JsonMissing.of()
                    private var toIndex: JsonField<Long> = JsonMissing.of()
                    private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                    internal fun from(entity: Entity) = apply {
                        fromIndex = entity.fromIndex
                        ref = entity.ref
                        toIndex = entity.toIndex
                        additionalProperties = entity.additionalProperties.toMutableMap()
                    }

                    /** Whole number from 0. */
                    fun fromIndex(fromIndex: Long) = fromIndex(JsonField.of(fromIndex))

                    /** Sets [Builder.fromIndex] to an arbitrary JSON value. */
                    fun fromIndex(fromIndex: JsonField<Long>) = apply { this.fromIndex = fromIndex }

                    fun ref(ref: Ref) = ref(JsonField.of(ref))

                    /** Sets [Builder.ref] to an arbitrary JSON value. */
                    fun ref(ref: JsonField<Ref>) = apply { this.ref = ref }

                    /** Whole number from 0. */
                    fun toIndex(toIndex: Long) = toIndex(JsonField.of(toIndex))

                    /** Sets [Builder.toIndex] to an arbitrary JSON value. */
                    fun toIndex(toIndex: JsonField<Long>) = apply { this.toIndex = toIndex }

                    fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                        this.additionalProperties.clear()
                        putAllAdditionalProperties(additionalProperties)
                    }

                    fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                        additionalProperties.put(key, value)
                    }

                    fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) =
                        apply {
                            this.additionalProperties.putAll(additionalProperties)
                        }

                    fun removeAdditionalProperty(key: String) = apply {
                        additionalProperties.remove(key)
                    }

                    fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                        keys.forEach(::removeAdditionalProperty)
                    }

                    /**
                     * Returns an immutable instance of [Entity].
                     *
                     * Further updates to this [Builder] will not mutate the returned instance.
                     */
                    fun build(): Entity =
                        Entity(fromIndex, ref, toIndex, additionalProperties.toMutableMap())
                }

                private var validated: Boolean = false

                /**
                 * Validates that the types of all values in this object match their expected types
                 * recursively.
                 *
                 * This method is _not_ forwards compatible with new types from the API for existing
                 * fields.
                 *
                 * @throws XTwitterScraperInvalidDataException if any value type in this object
                 *   doesn't match its expected type.
                 */
                fun validate(): Entity = apply {
                    if (validated) {
                        return@apply
                    }

                    fromIndex()
                    ref()?.validate()
                    toIndex()
                    validated = true
                }

                fun isValid(): Boolean =
                    try {
                        validate()
                        true
                    } catch (e: XTwitterScraperInvalidDataException) {
                        false
                    }

                internal fun validity(): Int =
                    (if (fromIndex.asKnown() == null) 0 else 1) +
                        (ref.asKnown()?.validity() ?: 0) +
                        (if (toIndex.asKnown() == null) 0 else 1)

                class Ref
                @JsonCreator(mode = JsonCreator.Mode.DISABLED)
                private constructor(
                    private val screenName: JsonField<String>,
                    private val text: JsonField<String>,
                    private val type: JsonField<String>,
                    private val url: JsonField<String>,
                    private val urlType: JsonField<String>,
                    private val additionalProperties: MutableMap<String, JsonValue>,
                ) {

                    @JsonCreator
                    private constructor(
                        @JsonProperty("screenName")
                        @ExcludeMissing
                        screenName: JsonField<String> = JsonMissing.of(),
                        @JsonProperty("text")
                        @ExcludeMissing
                        text: JsonField<String> = JsonMissing.of(),
                        @JsonProperty("type")
                        @ExcludeMissing
                        type: JsonField<String> = JsonMissing.of(),
                        @JsonProperty("url")
                        @ExcludeMissing
                        url: JsonField<String> = JsonMissing.of(),
                        @JsonProperty("urlType")
                        @ExcludeMissing
                        urlType: JsonField<String> = JsonMissing.of(),
                    ) : this(screenName, text, type, url, urlType, mutableMapOf())

                    /**
                     * @throws XTwitterScraperInvalidDataException if the JSON field has an
                     *   unexpected type.
                     */
                    fun screenName(): String? = screenName.getNullable("screenName")

                    /**
                     * @throws XTwitterScraperInvalidDataException if the JSON field has an
                     *   unexpected type.
                     */
                    fun text(): String? = text.getNullable("text")

                    /**
                     * @throws XTwitterScraperInvalidDataException if the JSON field has an
                     *   unexpected type.
                     */
                    fun type(): String? = type.getNullable("type")

                    /**
                     * @throws XTwitterScraperInvalidDataException if the JSON field has an
                     *   unexpected type.
                     */
                    fun url(): String? = url.getNullable("url")

                    /**
                     * @throws XTwitterScraperInvalidDataException if the JSON field has an
                     *   unexpected type.
                     */
                    fun urlType(): String? = urlType.getNullable("urlType")

                    /**
                     * Returns the raw JSON value of [screenName].
                     *
                     * Unlike [screenName], this method doesn't throw if the JSON field has an
                     * unexpected type.
                     */
                    @JsonProperty("screenName")
                    @ExcludeMissing
                    fun _screenName(): JsonField<String> = screenName

                    /**
                     * Returns the raw JSON value of [text].
                     *
                     * Unlike [text], this method doesn't throw if the JSON field has an unexpected
                     * type.
                     */
                    @JsonProperty("text") @ExcludeMissing fun _text(): JsonField<String> = text

                    /**
                     * Returns the raw JSON value of [type].
                     *
                     * Unlike [type], this method doesn't throw if the JSON field has an unexpected
                     * type.
                     */
                    @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<String> = type

                    /**
                     * Returns the raw JSON value of [url].
                     *
                     * Unlike [url], this method doesn't throw if the JSON field has an unexpected
                     * type.
                     */
                    @JsonProperty("url") @ExcludeMissing fun _url(): JsonField<String> = url

                    /**
                     * Returns the raw JSON value of [urlType].
                     *
                     * Unlike [urlType], this method doesn't throw if the JSON field has an
                     * unexpected type.
                     */
                    @JsonProperty("urlType")
                    @ExcludeMissing
                    fun _urlType(): JsonField<String> = urlType

                    @JsonAnySetter
                    private fun putAdditionalProperty(key: String, value: JsonValue) {
                        additionalProperties.put(key, value)
                    }

                    @JsonAnyGetter
                    @ExcludeMissing
                    fun _additionalProperties(): Map<String, JsonValue> =
                        Collections.unmodifiableMap(additionalProperties)

                    fun toBuilder() = Builder().from(this)

                    companion object {

                        /** Returns a mutable builder for constructing an instance of [Ref]. */
                        fun builder() = Builder()
                    }

                    /** A builder for [Ref]. */
                    class Builder internal constructor() {

                        private var screenName: JsonField<String> = JsonMissing.of()
                        private var text: JsonField<String> = JsonMissing.of()
                        private var type: JsonField<String> = JsonMissing.of()
                        private var url: JsonField<String> = JsonMissing.of()
                        private var urlType: JsonField<String> = JsonMissing.of()
                        private var additionalProperties: MutableMap<String, JsonValue> =
                            mutableMapOf()

                        internal fun from(ref: Ref) = apply {
                            screenName = ref.screenName
                            text = ref.text
                            type = ref.type
                            url = ref.url
                            urlType = ref.urlType
                            additionalProperties = ref.additionalProperties.toMutableMap()
                        }

                        fun screenName(screenName: String) = screenName(JsonField.of(screenName))

                        /** Sets [Builder.screenName] to an arbitrary JSON value. */
                        fun screenName(screenName: JsonField<String>) = apply {
                            this.screenName = screenName
                        }

                        fun text(text: String) = text(JsonField.of(text))

                        /** Sets [Builder.text] to an arbitrary JSON value. */
                        fun text(text: JsonField<String>) = apply { this.text = text }

                        fun type(type: String) = type(JsonField.of(type))

                        /** Sets [Builder.type] to an arbitrary JSON value. */
                        fun type(type: JsonField<String>) = apply { this.type = type }

                        fun url(url: String) = url(JsonField.of(url))

                        /** Sets [Builder.url] to an arbitrary JSON value. */
                        fun url(url: JsonField<String>) = apply { this.url = url }

                        fun urlType(urlType: String) = urlType(JsonField.of(urlType))

                        /** Sets [Builder.urlType] to an arbitrary JSON value. */
                        fun urlType(urlType: JsonField<String>) = apply { this.urlType = urlType }

                        fun additionalProperties(additionalProperties: Map<String, JsonValue>) =
                            apply {
                                this.additionalProperties.clear()
                                putAllAdditionalProperties(additionalProperties)
                            }

                        fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                            additionalProperties.put(key, value)
                        }

                        fun putAllAdditionalProperties(
                            additionalProperties: Map<String, JsonValue>
                        ) = apply {
                            this.additionalProperties.putAll(additionalProperties)
                        }

                        fun removeAdditionalProperty(key: String) = apply {
                            additionalProperties.remove(key)
                        }

                        fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                            keys.forEach(::removeAdditionalProperty)
                        }

                        /**
                         * Returns an immutable instance of [Ref].
                         *
                         * Further updates to this [Builder] will not mutate the returned instance.
                         */
                        fun build(): Ref =
                            Ref(
                                screenName,
                                text,
                                type,
                                url,
                                urlType,
                                additionalProperties.toMutableMap(),
                            )
                    }

                    private var validated: Boolean = false

                    /**
                     * Validates that the types of all values in this object match their expected
                     * types recursively.
                     *
                     * This method is _not_ forwards compatible with new types from the API for
                     * existing fields.
                     *
                     * @throws XTwitterScraperInvalidDataException if any value type in this object
                     *   doesn't match its expected type.
                     */
                    fun validate(): Ref = apply {
                        if (validated) {
                            return@apply
                        }

                        screenName()
                        text()
                        type()
                        url()
                        urlType()
                        validated = true
                    }

                    fun isValid(): Boolean =
                        try {
                            validate()
                            true
                        } catch (e: XTwitterScraperInvalidDataException) {
                            false
                        }

                    internal fun validity(): Int =
                        (if (screenName.asKnown() == null) 0 else 1) +
                            (if (text.asKnown() == null) 0 else 1) +
                            (if (type.asKnown() == null) 0 else 1) +
                            (if (url.asKnown() == null) 0 else 1) +
                            (if (urlType.asKnown() == null) 0 else 1)

                    override fun equals(other: Any?): Boolean {
                        if (this === other) {
                            return true
                        }

                        return other is Ref &&
                            screenName == other.screenName &&
                            text == other.text &&
                            type == other.type &&
                            url == other.url &&
                            urlType == other.urlType &&
                            additionalProperties == other.additionalProperties
                    }

                    private val hashCode: Int by lazy {
                        Objects.hash(screenName, text, type, url, urlType, additionalProperties)
                    }

                    override fun hashCode(): Int = hashCode

                    override fun toString() =
                        "Ref{screenName=$screenName, text=$text, type=$type, url=$url, urlType=$urlType, additionalProperties=$additionalProperties}"
                }

                override fun equals(other: Any?): Boolean {
                    if (this === other) {
                        return true
                    }

                    return other is Entity &&
                        fromIndex == other.fromIndex &&
                        ref == other.ref &&
                        toIndex == other.toIndex &&
                        additionalProperties == other.additionalProperties
                }

                private val hashCode: Int by lazy {
                    Objects.hash(fromIndex, ref, toIndex, additionalProperties)
                }

                override fun hashCode(): Int = hashCode

                override fun toString() =
                    "Entity{fromIndex=$fromIndex, ref=$ref, toIndex=$toIndex, additionalProperties=$additionalProperties}"
            }

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is Text &&
                    entities == other.entities &&
                    rtl == other.rtl &&
                    text == other.text &&
                    additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(entities, rtl, text, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "Text{entities=$entities, rtl=$rtl, text=$text, additionalProperties=$additionalProperties}"
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Tombstone &&
                text == other.text &&
                type == other.type &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(text, type, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Tombstone{text=$text, type=$type, additionalProperties=$additionalProperties}"
    }

    class UnknownCount @JsonCreator private constructor(private val value: JsonField<String>) :
        Enum {

        /** Returns this class instance's raw value. */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            val RETWEET_COUNT = of("retweetCount")

            val REPLY_COUNT = of("replyCount")

            val LIKE_COUNT = of("likeCount")

            val QUOTE_COUNT = of("quoteCount")

            val VIEW_COUNT = of("viewCount")

            val BOOKMARK_COUNT = of("bookmarkCount")

            fun of(value: String) = UnknownCount(JsonField.of(value))
        }

        /** An enum containing [UnknownCount]'s known values. */
        enum class Known {
            RETWEET_COUNT,
            REPLY_COUNT,
            LIKE_COUNT,
            QUOTE_COUNT,
            VIEW_COUNT,
            BOOKMARK_COUNT,
        }

        /**
         * An enum containing [UnknownCount]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [UnknownCount] can contain an unknown value in a couple of cases.
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            RETWEET_COUNT,
            REPLY_COUNT,
            LIKE_COUNT,
            QUOTE_COUNT,
            VIEW_COUNT,
            BOOKMARK_COUNT,
            /**
             * An enum member indicating that [UnknownCount] was instantiated with an unknown value.
             */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         */
        fun value(): Value =
            when (this) {
                RETWEET_COUNT -> Value.RETWEET_COUNT
                REPLY_COUNT -> Value.REPLY_COUNT
                LIKE_COUNT -> Value.LIKE_COUNT
                QUOTE_COUNT -> Value.QUOTE_COUNT
                VIEW_COUNT -> Value.VIEW_COUNT
                BOOKMARK_COUNT -> Value.BOOKMARK_COUNT
                else -> Value._UNKNOWN
            }

        /**
         * Returns an enum member corresponding to this class instance's value.
         *
         * @throws XTwitterScraperInvalidDataException if this class instance's value is a not a
         *   known member.
         */
        fun known(): Known =
            when (this) {
                RETWEET_COUNT -> Known.RETWEET_COUNT
                REPLY_COUNT -> Known.REPLY_COUNT
                LIKE_COUNT -> Known.LIKE_COUNT
                QUOTE_COUNT -> Known.QUOTE_COUNT
                VIEW_COUNT -> Known.VIEW_COUNT
                BOOKMARK_COUNT -> Known.BOOKMARK_COUNT
                else -> throw XTwitterScraperInvalidDataException("Unknown UnknownCount: $value")
            }

        /**
         * Returns this class instance's primitive wire representation.
         *
         * @throws XTwitterScraperInvalidDataException if this class instance's value does not have
         *   the expected primitive type.
         */
        fun asString(): String =
            _value().asString()
                ?: throw XTwitterScraperInvalidDataException("Value is not a String")

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
         *   match its expected type.
         */
        fun validate(): UnknownCount = apply {
            if (validated) {
                return@apply
            }

            known()
            validated = true
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: XTwitterScraperInvalidDataException) {
                false
            }

        internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is UnknownCount && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is SearchTweet &&
            id == other.id &&
            bookmarkCount == other.bookmarkCount &&
            unknownCounts == other.unknownCounts &&
            likeCount == other.likeCount &&
            quoteCount == other.quoteCount &&
            replyCount == other.replyCount &&
            retweetCount == other.retweetCount &&
            text == other.text &&
            viewCount == other.viewCount &&
            article == other.article &&
            author == other.author &&
            authorUnavailable == other.authorUnavailable &&
            card == other.card &&
            cashtagAttachments == other.cashtagAttachments &&
            communityId == other.communityId &&
            communityName == other.communityName &&
            communityNote == other.communityNote &&
            contentDisclosure == other.contentDisclosure &&
            conversationControl == other.conversationControl &&
            conversationId == other.conversationId &&
            coordinates == other.coordinates &&
            createdAt == other.createdAt &&
            displayTextRange == other.displayTextRange &&
            edit == other.edit &&
            entities == other.entities &&
            exclusiveContent == other.exclusiveContent &&
            geo == other.geo &&
            grokShareAttachment == other.grokShareAttachment &&
            grokTranslatedPost == other.grokTranslatedPost &&
            hasCommunityNotes == other.hasCommunityNotes &&
            inReplyToId == other.inReplyToId &&
            inReplyToUserId == other.inReplyToUserId &&
            inReplyToUsername == other.inReplyToUsername &&
            interstitial == other.interstitial &&
            isLimitedReply == other.isLimitedReply &&
            isNoteTweet == other.isNoteTweet &&
            isPinned == other.isPinned &&
            isQuoteStatus == other.isQuoteStatus &&
            isReply == other.isReply &&
            isRetweet == other.isRetweet &&
            isTranslatable == other.isTranslatable &&
            jetfuelAttachment == other.jetfuelAttachment &&
            lang == other.lang &&
            limitedActions == other.limitedActions &&
            media == other.media &&
            noteTweet == other.noteTweet &&
            parentTweet == other.parentTweet &&
            place == other.place &&
            possiblySensitive == other.possiblySensitive &&
            postCta == other.postCta &&
            previousCounts == other.previousCounts &&
            quotedTweetId == other.quotedTweetId &&
            quotedTweetPermalink == other.quotedTweetPermalink &&
            quotedTweetUnavailable == other.quotedTweetUnavailable &&
            quotedTweet == other.quotedTweet &&
            reactionContext == other.reactionContext &&
            retweetedAt == other.retweetedAt &&
            retweetedTweet == other.retweetedTweet &&
            scopes == other.scopes &&
            source == other.source &&
            sportsContext == other.sportsContext &&
            subscriberBadge == other.subscriberBadge &&
            textHighlights == other.textHighlights &&
            tombstone == other.tombstone &&
            type == other.type &&
            unavailableAuthorId == other.unavailableAuthorId &&
            unmentionedUserIds == other.unmentionedUserIds &&
            url == other.url &&
            viewState == other.viewState &&
            hiddenByAuthor == other.hiddenByAuthor &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            id,
            bookmarkCount,
            unknownCounts,
            likeCount,
            quoteCount,
            replyCount,
            retweetCount,
            text,
            viewCount,
            article,
            author,
            authorUnavailable,
            card,
            cashtagAttachments,
            communityId,
            communityName,
            communityNote,
            contentDisclosure,
            conversationControl,
            conversationId,
            coordinates,
            createdAt,
            displayTextRange,
            edit,
            entities,
            exclusiveContent,
            geo,
            grokShareAttachment,
            grokTranslatedPost,
            hasCommunityNotes,
            inReplyToId,
            inReplyToUserId,
            inReplyToUsername,
            interstitial,
            isLimitedReply,
            isNoteTweet,
            isPinned,
            isQuoteStatus,
            isReply,
            isRetweet,
            isTranslatable,
            jetfuelAttachment,
            lang,
            limitedActions,
            media,
            noteTweet,
            parentTweet,
            place,
            possiblySensitive,
            postCta,
            previousCounts,
            quotedTweetId,
            quotedTweetPermalink,
            quotedTweetUnavailable,
            quotedTweet,
            reactionContext,
            retweetedAt,
            retweetedTweet,
            scopes,
            source,
            sportsContext,
            subscriberBadge,
            textHighlights,
            tombstone,
            type,
            unavailableAuthorId,
            unmentionedUserIds,
            url,
            viewState,
            hiddenByAuthor,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "SearchTweet{id=$id, bookmarkCount=$bookmarkCount, unknownCounts=$unknownCounts, likeCount=$likeCount, quoteCount=$quoteCount, replyCount=$replyCount, retweetCount=$retweetCount, text=$text, viewCount=$viewCount, article=$article, author=$author, authorUnavailable=$authorUnavailable, card=$card, cashtagAttachments=$cashtagAttachments, communityId=$communityId, communityName=$communityName, communityNote=$communityNote, contentDisclosure=$contentDisclosure, conversationControl=$conversationControl, conversationId=$conversationId, coordinates=$coordinates, createdAt=$createdAt, displayTextRange=$displayTextRange, edit=$edit, entities=$entities, exclusiveContent=$exclusiveContent, geo=$geo, grokShareAttachment=$grokShareAttachment, grokTranslatedPost=$grokTranslatedPost, hasCommunityNotes=$hasCommunityNotes, inReplyToId=$inReplyToId, inReplyToUserId=$inReplyToUserId, inReplyToUsername=$inReplyToUsername, interstitial=$interstitial, isLimitedReply=$isLimitedReply, isNoteTweet=$isNoteTweet, isPinned=$isPinned, isQuoteStatus=$isQuoteStatus, isReply=$isReply, isRetweet=$isRetweet, isTranslatable=$isTranslatable, jetfuelAttachment=$jetfuelAttachment, lang=$lang, limitedActions=$limitedActions, media=$media, noteTweet=$noteTweet, parentTweet=$parentTweet, place=$place, possiblySensitive=$possiblySensitive, postCta=$postCta, previousCounts=$previousCounts, quotedTweetId=$quotedTweetId, quotedTweetPermalink=$quotedTweetPermalink, quotedTweetUnavailable=$quotedTweetUnavailable, quotedTweet=$quotedTweet, reactionContext=$reactionContext, retweetedAt=$retweetedAt, retweetedTweet=$retweetedTweet, scopes=$scopes, source=$source, sportsContext=$sportsContext, subscriberBadge=$subscriberBadge, textHighlights=$textHighlights, tombstone=$tombstone, type=$type, unavailableAuthorId=$unavailableAuthorId, unmentionedUserIds=$unmentionedUserIds, url=$url, viewState=$viewState, hiddenByAuthor=$hiddenByAuthor, additionalProperties=$additionalProperties}"
}
