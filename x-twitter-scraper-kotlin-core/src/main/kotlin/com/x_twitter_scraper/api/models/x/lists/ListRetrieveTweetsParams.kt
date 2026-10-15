// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.lists

import com.fasterxml.jackson.annotation.JsonCreator
import com.x_twitter_scraper.api.core.Enum
import com.x_twitter_scraper.api.core.JsonField
import com.x_twitter_scraper.api.core.Params
import com.x_twitter_scraper.api.core.http.Headers
import com.x_twitter_scraper.api.core.http.QueryParams
import com.x_twitter_scraper.api.errors.XTwitterScraperInvalidDataException
import java.time.LocalDate
import java.util.Objects

/** List tweets from an X List */
class ListRetrieveTweetsParams
private constructor(
    private val id: String?,
    private val advancedQuery: String?,
    private val after: String?,
    private val anyWords: String?,
    private val blueVerifiedOnly: Boolean?,
    private val boundingBox: String?,
    private val cardName: String?,
    private val cashtags: String?,
    private val conversationId: String?,
    private val cursor: String?,
    private val exactPhrase: String?,
    private val excludeSource: String?,
    private val excludeWords: String?,
    private val fields: String?,
    private val fromUser: String?,
    private val geocode: String?,
    private val hashtags: String?,
    private val inReplyToTweetId: String?,
    private val includeReplies: Boolean?,
    private val keywords: String?,
    private val language: String?,
    private val maxFaves: Long?,
    private val maxId: String?,
    private val maxQuotes: Long?,
    private val maxReplies: Long?,
    private val maxRetweets: Long?,
    private val mediaType: MediaType?,
    private val mentioning: String?,
    private val minBookmarks: Long?,
    private val minLikes: Long?,
    private val minQuotes: Long?,
    private val minReplies: Long?,
    private val minRetweets: Long?,
    private val minViews: Long?,
    private val mode: Mode?,
    private val nativeRetweets: Boolean?,
    private val near: String?,
    private val news: Boolean?,
    private val omit: String?,
    private val pageSize: Long?,
    private val place: String?,
    private val placeCountry: String?,
    private val pointRadius: String?,
    private val quotes: Quotes?,
    private val quotesOfTweetId: String?,
    private val replies: Replies?,
    private val retweets: Retweets?,
    private val retweetsOfTweetId: String?,
    private val safe: Boolean?,
    private val sinceDate: LocalDate?,
    private val sinceId: String?,
    private val sinceTime: String?,
    private val source: String?,
    private val toUser: String?,
    private val untilDate: LocalDate?,
    private val untilTime: String?,
    private val url: String?,
    private val verifiedOnly: Boolean?,
    private val within: String?,
    private val withinTime: String?,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    fun id(): String? = id

    /** Raw advanced search query appended as-is. */
    fun advancedQuery(): String? = advancedQuery

    /** Legacy cursor alias. Prefer cursor. */
    fun after(): String? = after

    /** Words or quoted phrases where any one can match. Separate with spaces, commas, or lines. */
    fun anyWords(): String? = anyWords

    /** Only return tweets from Blue-verified authors. */
    fun blueVerifiedOnly(): Boolean? = blueVerifiedOnly

    /** Geo bounding box, such as -74.1 40.6 -73.9 40.8. */
    fun boundingBox(): String? = boundingBox

    /**
     * Keep only Tweets with this card type, such as poll2choice_text_only. Tweet search checks each
     * Tweet's card.
     */
    fun cardName(): String? = cardName

    /** Cashtags separated by spaces, commas, or lines. */
    fun cashtags(): String? = cashtags

    /** Conversation ID filter. */
    fun conversationId(): String? = conversationId

    /**
     * Cursor from the previous response. Existing unprefixed cursors keep legacy standard behavior.
     * Pages can be empty before more rows. Follow next_cursor. Also read as next_cursor,
     * next_token, pagination_token or continuation_token.
     */
    fun cursor(): String? = cursor

    /** Match this literal phrase, including any hyphens. */
    fun exactPhrase(): String? = exactPhrase

    /**
     * X search no longer supports excludeSource. A Tweet search with it answers 424 & charges
     * nothing.
     */
    fun excludeSource(): String? = excludeSource

    /** Words or quoted phrases to exclude. Separate with spaces, commas, or lines. */
    fun excludeWords(): String? = excludeWords

    /**
     * Fields to keep in each row, comma-separated. Use dots for nested fields, such as
     * author.username. A list keeps the field in each item. Case & underscores do not matter. A row
     * that lacks a field leaves it out. Cursors, counts & diagnostics always stay. An unknown name
     * answers 400 unknown_field with the valid names, before any read. The price stays the same.
     * SDK row types then hold only the named fields.
     */
    fun fields(): String? = fields

    /** Filter by author username. */
    fun fromUser(): String? = fromUser

    /**
     * X search no longer supports geocode. A Tweet search with it answers 424 & charges nothing.
     */
    fun geocode(): String? = geocode

    /** Hashtags separated by spaces, commas, or lines. */
    fun hashtags(): String? = hashtags

    /** Only replies to this tweet ID. */
    fun inReplyToTweetId(): String? = inReplyToTweetId

    /** Include reply tweets unless replies specifies another mode. */
    fun includeReplies(): Boolean? = includeReplies

    /** Words the Tweets must match, in X search syntax. */
    fun keywords(): String? = keywords

    /** Filter by language. Its alias is `lang`. */
    fun language(): String? = language

    /** Maximum likes threshold. maxLikes & max_likes also work. */
    fun maxFaves(): Long? = maxFaves

    /** Return Tweets at or below this ID. */
    fun maxId(): String? = maxId

    /** Maximum quotes threshold. */
    fun maxQuotes(): Long? = maxQuotes

    /** Maximum replies threshold. */
    fun maxReplies(): Long? = maxReplies

    /** Maximum retweets threshold. */
    fun maxRetweets(): Long? = maxRetweets

    /** Filter media. Its aliases are has_video & has_media. */
    fun mediaType(): MediaType? = mediaType

    /** Filter tweets mentioning a username. */
    fun mentioning(): String? = mentioning

    /** Minimum bookmark count threshold. */
    fun minBookmarks(): Long? = minBookmarks

    /** Minimum likes. Its aliases are minFaves, min_likes & min_faves. */
    fun minLikes(): Long? = minLikes

    /** Minimum quote count threshold. */
    fun minQuotes(): Long? = minQuotes

    /** Minimum replies threshold. */
    fun minReplies(): Long? = minReplies

    /** Minimum retweets threshold. */
    fun minRetweets(): Long? = minRetweets

    /** Minimum view count threshold. */
    fun minViews(): Long? = minViews

    /**
     * Omit mode for resumable maximum coverage. Standard keeps legacy pagination. Coverage returns
     * diagnostics once and rejects cursors.
     */
    fun mode(): Mode? = mode

    /** Only return native reposts. */
    fun nativeRetweets(): Boolean? = nativeRetweets

    /**
     * A place name. A country, such as United States, works like placeCountry & keeps Tweets tagged
     * there. A city keeps Tweets about it.
     */
    fun near(): String? = near

    /** X no longer searches filter:news. */
    fun news(): Boolean? = news

    /**
     * Fields to drop from each row, comma-separated. It takes the same names as fields. With
     * fields, it drops from the kept fields.
     */
    fun omit(): String? = omit

    /**
     * Automatic pages accept 1-300 Tweets, standard pages 1-100. Default 20. Follow next_cursor for
     * more pages. Its aliases are limit, count, max_results, maxItems, max_items & per_page.
     */
    fun pageSize(): Long? = pageSize

    /** Search within a place ID. */
    fun place(): String? = place

    /** Search within a country code. */
    fun placeCountry(): String? = placeCountry

    /** Geo point radius, such as -73.99 40.73 25mi. */
    fun pointRadius(): String? = pointRadius

    /** Only when the caller requests a quote mode. */
    fun quotes(): Quotes? = quotes

    /** Only quotes of this tweet ID. */
    fun quotesOfTweetId(): String? = quotesOfTweetId

    /** Reply mode. Exclude keeps own-thread replies. */
    fun replies(): Replies? = replies

    /** Only when the caller requests a repost mode. */
    fun retweets(): Retweets? = retweets

    /** Only retweets of this tweet ID. */
    fun retweetsOfTweetId(): String? = retweetsOfTweetId

    /** Enable the safe-search filter. */
    fun safe(): Boolean? = safe

    /** Start date in YYYY-MM-DD format, as a UTC day. */
    fun sinceDate(): LocalDate? = sinceDate

    /** Return Tweets whose IDs exceed this ID. */
    fun sinceId(): String? = sinceId

    /**
     * Inclusive ISO bound for Tweet creation time, such as 2026-09-25T19:20:41Z. Unix seconds also
     * work. A time without an offset is UTC.
     */
    fun sinceTime(): String? = sinceTime

    /** X search no longer supports source. A Tweet search with it answers 424 & charges nothing. */
    fun source(): String? = source

    /** Filter replies sent to a username. */
    fun toUser(): String? = toUser

    /** End date in YYYY-MM-DD format, as a UTC day, inclusive, so its own posts count. */
    fun untilDate(): LocalDate? = untilDate

    /**
     * Exclusive ISO bound for Tweet creation time. Unix seconds also work. A time without an offset
     * is UTC.
     */
    fun untilTime(): String? = untilTime

    /** URL substring or domain filter. */
    fun url(): String? = url

    /** Only return tweets from verified authors. */
    fun verifiedOnly(): Boolean? = verifiedOnly

    /**
     * X search no longer supports within. A Tweet search with it answers 424 & charges nothing. Use
     * near alone.
     */
    fun within(): String? = within

    /** Keeps Tweets from a recent window, such as 90m or 7d. Use a whole number & s, m, h or d. */
    fun withinTime(): String? = withinTime

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        fun none(): ListRetrieveTweetsParams = builder().build()

        /** Returns a mutable builder for constructing an instance of [ListRetrieveTweetsParams]. */
        fun builder() = Builder()
    }

    /** A builder for [ListRetrieveTweetsParams]. */
    class Builder internal constructor() {

        private var id: String? = null
        private var advancedQuery: String? = null
        private var after: String? = null
        private var anyWords: String? = null
        private var blueVerifiedOnly: Boolean? = null
        private var boundingBox: String? = null
        private var cardName: String? = null
        private var cashtags: String? = null
        private var conversationId: String? = null
        private var cursor: String? = null
        private var exactPhrase: String? = null
        private var excludeSource: String? = null
        private var excludeWords: String? = null
        private var fields: String? = null
        private var fromUser: String? = null
        private var geocode: String? = null
        private var hashtags: String? = null
        private var inReplyToTweetId: String? = null
        private var includeReplies: Boolean? = null
        private var keywords: String? = null
        private var language: String? = null
        private var maxFaves: Long? = null
        private var maxId: String? = null
        private var maxQuotes: Long? = null
        private var maxReplies: Long? = null
        private var maxRetweets: Long? = null
        private var mediaType: MediaType? = null
        private var mentioning: String? = null
        private var minBookmarks: Long? = null
        private var minLikes: Long? = null
        private var minQuotes: Long? = null
        private var minReplies: Long? = null
        private var minRetweets: Long? = null
        private var minViews: Long? = null
        private var mode: Mode? = null
        private var nativeRetweets: Boolean? = null
        private var near: String? = null
        private var news: Boolean? = null
        private var omit: String? = null
        private var pageSize: Long? = null
        private var place: String? = null
        private var placeCountry: String? = null
        private var pointRadius: String? = null
        private var quotes: Quotes? = null
        private var quotesOfTweetId: String? = null
        private var replies: Replies? = null
        private var retweets: Retweets? = null
        private var retweetsOfTweetId: String? = null
        private var safe: Boolean? = null
        private var sinceDate: LocalDate? = null
        private var sinceId: String? = null
        private var sinceTime: String? = null
        private var source: String? = null
        private var toUser: String? = null
        private var untilDate: LocalDate? = null
        private var untilTime: String? = null
        private var url: String? = null
        private var verifiedOnly: Boolean? = null
        private var within: String? = null
        private var withinTime: String? = null
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        internal fun from(listRetrieveTweetsParams: ListRetrieveTweetsParams) = apply {
            id = listRetrieveTweetsParams.id
            advancedQuery = listRetrieveTweetsParams.advancedQuery
            after = listRetrieveTweetsParams.after
            anyWords = listRetrieveTweetsParams.anyWords
            blueVerifiedOnly = listRetrieveTweetsParams.blueVerifiedOnly
            boundingBox = listRetrieveTweetsParams.boundingBox
            cardName = listRetrieveTweetsParams.cardName
            cashtags = listRetrieveTweetsParams.cashtags
            conversationId = listRetrieveTweetsParams.conversationId
            cursor = listRetrieveTweetsParams.cursor
            exactPhrase = listRetrieveTweetsParams.exactPhrase
            excludeSource = listRetrieveTweetsParams.excludeSource
            excludeWords = listRetrieveTweetsParams.excludeWords
            fields = listRetrieveTweetsParams.fields
            fromUser = listRetrieveTweetsParams.fromUser
            geocode = listRetrieveTweetsParams.geocode
            hashtags = listRetrieveTweetsParams.hashtags
            inReplyToTweetId = listRetrieveTweetsParams.inReplyToTweetId
            includeReplies = listRetrieveTweetsParams.includeReplies
            keywords = listRetrieveTweetsParams.keywords
            language = listRetrieveTweetsParams.language
            maxFaves = listRetrieveTweetsParams.maxFaves
            maxId = listRetrieveTweetsParams.maxId
            maxQuotes = listRetrieveTweetsParams.maxQuotes
            maxReplies = listRetrieveTweetsParams.maxReplies
            maxRetweets = listRetrieveTweetsParams.maxRetweets
            mediaType = listRetrieveTweetsParams.mediaType
            mentioning = listRetrieveTweetsParams.mentioning
            minBookmarks = listRetrieveTweetsParams.minBookmarks
            minLikes = listRetrieveTweetsParams.minLikes
            minQuotes = listRetrieveTweetsParams.minQuotes
            minReplies = listRetrieveTweetsParams.minReplies
            minRetweets = listRetrieveTweetsParams.minRetweets
            minViews = listRetrieveTweetsParams.minViews
            mode = listRetrieveTweetsParams.mode
            nativeRetweets = listRetrieveTweetsParams.nativeRetweets
            near = listRetrieveTweetsParams.near
            news = listRetrieveTweetsParams.news
            omit = listRetrieveTweetsParams.omit
            pageSize = listRetrieveTweetsParams.pageSize
            place = listRetrieveTweetsParams.place
            placeCountry = listRetrieveTweetsParams.placeCountry
            pointRadius = listRetrieveTweetsParams.pointRadius
            quotes = listRetrieveTweetsParams.quotes
            quotesOfTweetId = listRetrieveTweetsParams.quotesOfTweetId
            replies = listRetrieveTweetsParams.replies
            retweets = listRetrieveTweetsParams.retweets
            retweetsOfTweetId = listRetrieveTweetsParams.retweetsOfTweetId
            safe = listRetrieveTweetsParams.safe
            sinceDate = listRetrieveTweetsParams.sinceDate
            sinceId = listRetrieveTweetsParams.sinceId
            sinceTime = listRetrieveTweetsParams.sinceTime
            source = listRetrieveTweetsParams.source
            toUser = listRetrieveTweetsParams.toUser
            untilDate = listRetrieveTweetsParams.untilDate
            untilTime = listRetrieveTweetsParams.untilTime
            url = listRetrieveTweetsParams.url
            verifiedOnly = listRetrieveTweetsParams.verifiedOnly
            within = listRetrieveTweetsParams.within
            withinTime = listRetrieveTweetsParams.withinTime
            additionalHeaders = listRetrieveTweetsParams.additionalHeaders.toBuilder()
            additionalQueryParams = listRetrieveTweetsParams.additionalQueryParams.toBuilder()
        }

        fun id(id: String?) = apply { this.id = id }

        /** Raw advanced search query appended as-is. */
        fun advancedQuery(advancedQuery: String?) = apply { this.advancedQuery = advancedQuery }

        /** Legacy cursor alias. Prefer cursor. */
        fun after(after: String?) = apply { this.after = after }

        /**
         * Words or quoted phrases where any one can match. Separate with spaces, commas, or lines.
         */
        fun anyWords(anyWords: String?) = apply { this.anyWords = anyWords }

        /** Only return tweets from Blue-verified authors. */
        fun blueVerifiedOnly(blueVerifiedOnly: Boolean?) = apply {
            this.blueVerifiedOnly = blueVerifiedOnly
        }

        /** Alias for [Builder.blueVerifiedOnly]. */
        fun blueVerifiedOnly(blueVerifiedOnly: Boolean) =
            blueVerifiedOnly(blueVerifiedOnly as Boolean?)

        /** Geo bounding box, such as -74.1 40.6 -73.9 40.8. */
        fun boundingBox(boundingBox: String?) = apply { this.boundingBox = boundingBox }

        /**
         * Keep only Tweets with this card type, such as poll2choice_text_only. Tweet search checks
         * each Tweet's card.
         */
        fun cardName(cardName: String?) = apply { this.cardName = cardName }

        /** Cashtags separated by spaces, commas, or lines. */
        fun cashtags(cashtags: String?) = apply { this.cashtags = cashtags }

        /** Conversation ID filter. */
        fun conversationId(conversationId: String?) = apply { this.conversationId = conversationId }

        /**
         * Cursor from the previous response. Existing unprefixed cursors keep legacy standard
         * behavior. Pages can be empty before more rows. Follow next_cursor. Also read as
         * next_cursor, next_token, pagination_token or continuation_token.
         */
        fun cursor(cursor: String?) = apply { this.cursor = cursor }

        /** Match this literal phrase, including any hyphens. */
        fun exactPhrase(exactPhrase: String?) = apply { this.exactPhrase = exactPhrase }

        /**
         * X search no longer supports excludeSource. A Tweet search with it answers 424 & charges
         * nothing.
         */
        fun excludeSource(excludeSource: String?) = apply { this.excludeSource = excludeSource }

        /** Words or quoted phrases to exclude. Separate with spaces, commas, or lines. */
        fun excludeWords(excludeWords: String?) = apply { this.excludeWords = excludeWords }

        /**
         * Fields to keep in each row, comma-separated. Use dots for nested fields, such as
         * author.username. A list keeps the field in each item. Case & underscores do not matter. A
         * row that lacks a field leaves it out. Cursors, counts & diagnostics always stay. An
         * unknown name answers 400 unknown_field with the valid names, before any read. The price
         * stays the same. SDK row types then hold only the named fields.
         */
        fun fields(fields: String?) = apply { this.fields = fields }

        /** Filter by author username. */
        fun fromUser(fromUser: String?) = apply { this.fromUser = fromUser }

        /**
         * X search no longer supports geocode. A Tweet search with it answers 424 & charges
         * nothing.
         */
        fun geocode(geocode: String?) = apply { this.geocode = geocode }

        /** Hashtags separated by spaces, commas, or lines. */
        fun hashtags(hashtags: String?) = apply { this.hashtags = hashtags }

        /** Only replies to this tweet ID. */
        fun inReplyToTweetId(inReplyToTweetId: String?) = apply {
            this.inReplyToTweetId = inReplyToTweetId
        }

        /** Include reply tweets unless replies specifies another mode. */
        fun includeReplies(includeReplies: Boolean?) = apply {
            this.includeReplies = includeReplies
        }

        /** Alias for [Builder.includeReplies]. */
        fun includeReplies(includeReplies: Boolean) = includeReplies(includeReplies as Boolean?)

        /** Words the Tweets must match, in X search syntax. */
        fun keywords(keywords: String?) = apply { this.keywords = keywords }

        /** Filter by language. Its alias is `lang`. */
        fun language(language: String?) = apply { this.language = language }

        /** Maximum likes threshold. maxLikes & max_likes also work. */
        fun maxFaves(maxFaves: Long?) = apply { this.maxFaves = maxFaves }

        /** Alias for [Builder.maxFaves]. */
        fun maxFaves(maxFaves: Long) = maxFaves(maxFaves as Long?)

        /** Return Tweets at or below this ID. */
        fun maxId(maxId: String?) = apply { this.maxId = maxId }

        /** Maximum quotes threshold. */
        fun maxQuotes(maxQuotes: Long?) = apply { this.maxQuotes = maxQuotes }

        /** Alias for [Builder.maxQuotes]. */
        fun maxQuotes(maxQuotes: Long) = maxQuotes(maxQuotes as Long?)

        /** Maximum replies threshold. */
        fun maxReplies(maxReplies: Long?) = apply { this.maxReplies = maxReplies }

        /** Alias for [Builder.maxReplies]. */
        fun maxReplies(maxReplies: Long) = maxReplies(maxReplies as Long?)

        /** Maximum retweets threshold. */
        fun maxRetweets(maxRetweets: Long?) = apply { this.maxRetweets = maxRetweets }

        /** Alias for [Builder.maxRetweets]. */
        fun maxRetweets(maxRetweets: Long) = maxRetweets(maxRetweets as Long?)

        /** Filter media. Its aliases are has_video & has_media. */
        fun mediaType(mediaType: MediaType?) = apply { this.mediaType = mediaType }

        /** Filter tweets mentioning a username. */
        fun mentioning(mentioning: String?) = apply { this.mentioning = mentioning }

        /** Minimum bookmark count threshold. */
        fun minBookmarks(minBookmarks: Long?) = apply { this.minBookmarks = minBookmarks }

        /** Alias for [Builder.minBookmarks]. */
        fun minBookmarks(minBookmarks: Long) = minBookmarks(minBookmarks as Long?)

        /** Minimum likes. Its aliases are minFaves, min_likes & min_faves. */
        fun minLikes(minLikes: Long?) = apply { this.minLikes = minLikes }

        /** Alias for [Builder.minLikes]. */
        fun minLikes(minLikes: Long) = minLikes(minLikes as Long?)

        /** Minimum quote count threshold. */
        fun minQuotes(minQuotes: Long?) = apply { this.minQuotes = minQuotes }

        /** Alias for [Builder.minQuotes]. */
        fun minQuotes(minQuotes: Long) = minQuotes(minQuotes as Long?)

        /** Minimum replies threshold. */
        fun minReplies(minReplies: Long?) = apply { this.minReplies = minReplies }

        /** Alias for [Builder.minReplies]. */
        fun minReplies(minReplies: Long) = minReplies(minReplies as Long?)

        /** Minimum retweets threshold. */
        fun minRetweets(minRetweets: Long?) = apply { this.minRetweets = minRetweets }

        /** Alias for [Builder.minRetweets]. */
        fun minRetweets(minRetweets: Long) = minRetweets(minRetweets as Long?)

        /** Minimum view count threshold. */
        fun minViews(minViews: Long?) = apply { this.minViews = minViews }

        /** Alias for [Builder.minViews]. */
        fun minViews(minViews: Long) = minViews(minViews as Long?)

        /**
         * Omit mode for resumable maximum coverage. Standard keeps legacy pagination. Coverage
         * returns diagnostics once and rejects cursors.
         */
        fun mode(mode: Mode?) = apply { this.mode = mode }

        /** Only return native reposts. */
        fun nativeRetweets(nativeRetweets: Boolean?) = apply {
            this.nativeRetweets = nativeRetweets
        }

        /** Alias for [Builder.nativeRetweets]. */
        fun nativeRetweets(nativeRetweets: Boolean) = nativeRetweets(nativeRetweets as Boolean?)

        /**
         * A place name. A country, such as United States, works like placeCountry & keeps Tweets
         * tagged there. A city keeps Tweets about it.
         */
        fun near(near: String?) = apply { this.near = near }

        /** X no longer searches filter:news. */
        fun news(news: Boolean?) = apply { this.news = news }

        /** Alias for [Builder.news]. */
        fun news(news: Boolean) = news(news as Boolean?)

        /**
         * Fields to drop from each row, comma-separated. It takes the same names as fields. With
         * fields, it drops from the kept fields.
         */
        fun omit(omit: String?) = apply { this.omit = omit }

        /**
         * Automatic pages accept 1-300 Tweets, standard pages 1-100. Default 20. Follow next_cursor
         * for more pages. Its aliases are limit, count, max_results, maxItems, max_items &
         * per_page.
         */
        fun pageSize(pageSize: Long?) = apply { this.pageSize = pageSize }

        /** Alias for [Builder.pageSize]. */
        fun pageSize(pageSize: Long) = pageSize(pageSize as Long?)

        /** Search within a place ID. */
        fun place(place: String?) = apply { this.place = place }

        /** Search within a country code. */
        fun placeCountry(placeCountry: String?) = apply { this.placeCountry = placeCountry }

        /** Geo point radius, such as -73.99 40.73 25mi. */
        fun pointRadius(pointRadius: String?) = apply { this.pointRadius = pointRadius }

        /** Only when the caller requests a quote mode. */
        fun quotes(quotes: Quotes?) = apply { this.quotes = quotes }

        /** Only quotes of this tweet ID. */
        fun quotesOfTweetId(quotesOfTweetId: String?) = apply {
            this.quotesOfTweetId = quotesOfTweetId
        }

        /** Reply mode. Exclude keeps own-thread replies. */
        fun replies(replies: Replies?) = apply { this.replies = replies }

        /** Only when the caller requests a repost mode. */
        fun retweets(retweets: Retweets?) = apply { this.retweets = retweets }

        /** Only retweets of this tweet ID. */
        fun retweetsOfTweetId(retweetsOfTweetId: String?) = apply {
            this.retweetsOfTweetId = retweetsOfTweetId
        }

        /** Enable the safe-search filter. */
        fun safe(safe: Boolean?) = apply { this.safe = safe }

        /** Alias for [Builder.safe]. */
        fun safe(safe: Boolean) = safe(safe as Boolean?)

        /** Start date in YYYY-MM-DD format, as a UTC day. */
        fun sinceDate(sinceDate: LocalDate?) = apply { this.sinceDate = sinceDate }

        /** Return Tweets whose IDs exceed this ID. */
        fun sinceId(sinceId: String?) = apply { this.sinceId = sinceId }

        /**
         * Inclusive ISO bound for Tweet creation time, such as 2026-09-25T19:20:41Z. Unix seconds
         * also work. A time without an offset is UTC.
         */
        fun sinceTime(sinceTime: String?) = apply { this.sinceTime = sinceTime }

        /**
         * X search no longer supports source. A Tweet search with it answers 424 & charges nothing.
         */
        fun source(source: String?) = apply { this.source = source }

        /** Filter replies sent to a username. */
        fun toUser(toUser: String?) = apply { this.toUser = toUser }

        /** End date in YYYY-MM-DD format, as a UTC day, inclusive, so its own posts count. */
        fun untilDate(untilDate: LocalDate?) = apply { this.untilDate = untilDate }

        /**
         * Exclusive ISO bound for Tweet creation time. Unix seconds also work. A time without an
         * offset is UTC.
         */
        fun untilTime(untilTime: String?) = apply { this.untilTime = untilTime }

        /** URL substring or domain filter. */
        fun url(url: String?) = apply { this.url = url }

        /** Only return tweets from verified authors. */
        fun verifiedOnly(verifiedOnly: Boolean?) = apply { this.verifiedOnly = verifiedOnly }

        /** Alias for [Builder.verifiedOnly]. */
        fun verifiedOnly(verifiedOnly: Boolean) = verifiedOnly(verifiedOnly as Boolean?)

        /**
         * X search no longer supports within. A Tweet search with it answers 424 & charges nothing.
         * Use near alone.
         */
        fun within(within: String?) = apply { this.within = within }

        /**
         * Keeps Tweets from a recent window, such as 90m or 7d. Use a whole number & s, m, h or d.
         */
        fun withinTime(withinTime: String?) = apply { this.withinTime = withinTime }

        fun additionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.clear()
            putAllAdditionalHeaders(additionalHeaders)
        }

        fun additionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.clear()
            putAllAdditionalHeaders(additionalHeaders)
        }

        fun putAdditionalHeader(name: String, value: String) = apply {
            additionalHeaders.put(name, value)
        }

        fun putAdditionalHeaders(name: String, values: Iterable<String>) = apply {
            additionalHeaders.put(name, values)
        }

        fun putAllAdditionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.putAll(additionalHeaders)
        }

        fun putAllAdditionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.putAll(additionalHeaders)
        }

        fun replaceAdditionalHeaders(name: String, value: String) = apply {
            additionalHeaders.replace(name, value)
        }

        fun replaceAdditionalHeaders(name: String, values: Iterable<String>) = apply {
            additionalHeaders.replace(name, values)
        }

        fun replaceAllAdditionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.replaceAll(additionalHeaders)
        }

        fun replaceAllAdditionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.replaceAll(additionalHeaders)
        }

        fun removeAdditionalHeaders(name: String) = apply { additionalHeaders.remove(name) }

        fun removeAllAdditionalHeaders(names: Set<String>) = apply {
            additionalHeaders.removeAll(names)
        }

        fun additionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.clear()
            putAllAdditionalQueryParams(additionalQueryParams)
        }

        fun additionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) = apply {
            this.additionalQueryParams.clear()
            putAllAdditionalQueryParams(additionalQueryParams)
        }

        fun putAdditionalQueryParam(key: String, value: String) = apply {
            additionalQueryParams.put(key, value)
        }

        fun putAdditionalQueryParams(key: String, values: Iterable<String>) = apply {
            additionalQueryParams.put(key, values)
        }

        fun putAllAdditionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.putAll(additionalQueryParams)
        }

        fun putAllAdditionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) =
            apply {
                this.additionalQueryParams.putAll(additionalQueryParams)
            }

        fun replaceAdditionalQueryParams(key: String, value: String) = apply {
            additionalQueryParams.replace(key, value)
        }

        fun replaceAdditionalQueryParams(key: String, values: Iterable<String>) = apply {
            additionalQueryParams.replace(key, values)
        }

        fun replaceAllAdditionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.replaceAll(additionalQueryParams)
        }

        fun replaceAllAdditionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) =
            apply {
                this.additionalQueryParams.replaceAll(additionalQueryParams)
            }

        fun removeAdditionalQueryParams(key: String) = apply { additionalQueryParams.remove(key) }

        fun removeAllAdditionalQueryParams(keys: Set<String>) = apply {
            additionalQueryParams.removeAll(keys)
        }

        /**
         * Returns an immutable instance of [ListRetrieveTweetsParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): ListRetrieveTweetsParams =
            ListRetrieveTweetsParams(
                id,
                advancedQuery,
                after,
                anyWords,
                blueVerifiedOnly,
                boundingBox,
                cardName,
                cashtags,
                conversationId,
                cursor,
                exactPhrase,
                excludeSource,
                excludeWords,
                fields,
                fromUser,
                geocode,
                hashtags,
                inReplyToTweetId,
                includeReplies,
                keywords,
                language,
                maxFaves,
                maxId,
                maxQuotes,
                maxReplies,
                maxRetweets,
                mediaType,
                mentioning,
                minBookmarks,
                minLikes,
                minQuotes,
                minReplies,
                minRetweets,
                minViews,
                mode,
                nativeRetweets,
                near,
                news,
                omit,
                pageSize,
                place,
                placeCountry,
                pointRadius,
                quotes,
                quotesOfTweetId,
                replies,
                retweets,
                retweetsOfTweetId,
                safe,
                sinceDate,
                sinceId,
                sinceTime,
                source,
                toUser,
                untilDate,
                untilTime,
                url,
                verifiedOnly,
                within,
                withinTime,
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    fun _pathParam(index: Int): String =
        when (index) {
            0 -> id ?: ""
            else -> ""
        }

    override fun _headers(): Headers = additionalHeaders

    override fun _queryParams(): QueryParams =
        QueryParams.builder()
            .apply {
                advancedQuery?.let { put("advancedQuery", it) }
                after?.let { put("after", it) }
                anyWords?.let { put("anyWords", it) }
                blueVerifiedOnly?.let { put("blueVerifiedOnly", it.toString()) }
                boundingBox?.let { put("boundingBox", it) }
                cardName?.let { put("cardName", it) }
                cashtags?.let { put("cashtags", it) }
                conversationId?.let { put("conversationId", it) }
                cursor?.let { put("cursor", it) }
                exactPhrase?.let { put("exactPhrase", it) }
                excludeSource?.let { put("excludeSource", it) }
                excludeWords?.let { put("excludeWords", it) }
                fields?.let { put("fields", it) }
                fromUser?.let { put("fromUser", it) }
                geocode?.let { put("geocode", it) }
                hashtags?.let { put("hashtags", it) }
                inReplyToTweetId?.let { put("inReplyToTweetId", it) }
                includeReplies?.let { put("includeReplies", it.toString()) }
                keywords?.let { put("keywords", it) }
                language?.let { put("language", it) }
                maxFaves?.let { put("maxFaves", it.toString()) }
                maxId?.let { put("maxId", it) }
                maxQuotes?.let { put("maxQuotes", it.toString()) }
                maxReplies?.let { put("maxReplies", it.toString()) }
                maxRetweets?.let { put("maxRetweets", it.toString()) }
                mediaType?.let { put("mediaType", it.toString()) }
                mentioning?.let { put("mentioning", it) }
                minBookmarks?.let { put("minBookmarks", it.toString()) }
                minLikes?.let { put("minLikes", it.toString()) }
                minQuotes?.let { put("minQuotes", it.toString()) }
                minReplies?.let { put("minReplies", it.toString()) }
                minRetweets?.let { put("minRetweets", it.toString()) }
                minViews?.let { put("minViews", it.toString()) }
                mode?.let { put("mode", it.toString()) }
                nativeRetweets?.let { put("nativeRetweets", it.toString()) }
                near?.let { put("near", it) }
                news?.let { put("news", it.toString()) }
                omit?.let { put("omit", it) }
                pageSize?.let { put("pageSize", it.toString()) }
                place?.let { put("place", it) }
                placeCountry?.let { put("placeCountry", it) }
                pointRadius?.let { put("pointRadius", it) }
                quotes?.let { put("quotes", it.toString()) }
                quotesOfTweetId?.let { put("quotesOfTweetId", it) }
                replies?.let { put("replies", it.toString()) }
                retweets?.let { put("retweets", it.toString()) }
                retweetsOfTweetId?.let { put("retweetsOfTweetId", it) }
                safe?.let { put("safe", it.toString()) }
                sinceDate?.let { put("sinceDate", it.toString()) }
                sinceId?.let { put("sinceId", it) }
                sinceTime?.let { put("sinceTime", it) }
                source?.let { put("source", it) }
                toUser?.let { put("toUser", it) }
                untilDate?.let { put("untilDate", it.toString()) }
                untilTime?.let { put("untilTime", it) }
                url?.let { put("url", it) }
                verifiedOnly?.let { put("verifiedOnly", it.toString()) }
                within?.let { put("within", it) }
                withinTime?.let { put("withinTime", it) }
                putAll(additionalQueryParams)
            }
            .build()

    /** Filter media. Its aliases are has_video & has_media. */
    class MediaType @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

        /** Returns this class instance's raw value. */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            val IMAGES = of("images")

            val VIDEOS = of("videos")

            val GIFS = of("gifs")

            val MEDIA = of("media")

            val LINKS = of("links")

            val NONE = of("none")

            fun of(value: String) = MediaType(JsonField.of(value))
        }

        /** An enum containing [MediaType]'s known values. */
        enum class Known {
            IMAGES,
            VIDEOS,
            GIFS,
            MEDIA,
            LINKS,
            NONE,
        }

        /**
         * An enum containing [MediaType]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [MediaType] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            IMAGES,
            VIDEOS,
            GIFS,
            MEDIA,
            LINKS,
            NONE,
            /**
             * An enum member indicating that [MediaType] was instantiated with an unknown value.
             */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         */
        fun value(): Value =
            when (this) {
                IMAGES -> Value.IMAGES
                VIDEOS -> Value.VIDEOS
                GIFS -> Value.GIFS
                MEDIA -> Value.MEDIA
                LINKS -> Value.LINKS
                NONE -> Value.NONE
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
                IMAGES -> Known.IMAGES
                VIDEOS -> Known.VIDEOS
                GIFS -> Known.GIFS
                MEDIA -> Known.MEDIA
                LINKS -> Known.LINKS
                NONE -> Known.NONE
                else -> throw XTwitterScraperInvalidDataException("Unknown MediaType: $value")
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
        fun validate(): MediaType = apply {
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

            return other is MediaType && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    /**
     * Omit mode for resumable maximum coverage. Standard keeps legacy pagination. Coverage returns
     * diagnostics once and rejects cursors.
     */
    class Mode @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

        /** Returns this class instance's raw value. */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            val STANDARD = of("standard")

            val COVERAGE = of("coverage")

            fun of(value: String) = Mode(JsonField.of(value))
        }

        /** An enum containing [Mode]'s known values. */
        enum class Known {
            STANDARD,
            COVERAGE,
        }

        /**
         * An enum containing [Mode]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Mode] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            STANDARD,
            COVERAGE,
            /** An enum member indicating that [Mode] was instantiated with an unknown value. */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         */
        fun value(): Value =
            when (this) {
                STANDARD -> Value.STANDARD
                COVERAGE -> Value.COVERAGE
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
                STANDARD -> Known.STANDARD
                COVERAGE -> Known.COVERAGE
                else -> throw XTwitterScraperInvalidDataException("Unknown Mode: $value")
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
        fun validate(): Mode = apply {
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

            return other is Mode && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    /** Only when the caller requests a quote mode. */
    class Quotes @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

        /** Returns this class instance's raw value. */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            val INCLUDE = of("include")

            val EXCLUDE = of("exclude")

            val ONLY = of("only")

            fun of(value: String) = Quotes(JsonField.of(value))
        }

        /** An enum containing [Quotes]'s known values. */
        enum class Known {
            INCLUDE,
            EXCLUDE,
            ONLY,
        }

        /**
         * An enum containing [Quotes]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Quotes] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            INCLUDE,
            EXCLUDE,
            ONLY,
            /** An enum member indicating that [Quotes] was instantiated with an unknown value. */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         */
        fun value(): Value =
            when (this) {
                INCLUDE -> Value.INCLUDE
                EXCLUDE -> Value.EXCLUDE
                ONLY -> Value.ONLY
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
                INCLUDE -> Known.INCLUDE
                EXCLUDE -> Known.EXCLUDE
                ONLY -> Known.ONLY
                else -> throw XTwitterScraperInvalidDataException("Unknown Quotes: $value")
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
        fun validate(): Quotes = apply {
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

            return other is Quotes && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    /** Reply mode. Exclude keeps own-thread replies. */
    class Replies @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

        /** Returns this class instance's raw value. */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            val INCLUDE = of("include")

            val EXCLUDE = of("exclude")

            val ONLY = of("only")

            fun of(value: String) = Replies(JsonField.of(value))
        }

        /** An enum containing [Replies]'s known values. */
        enum class Known {
            INCLUDE,
            EXCLUDE,
            ONLY,
        }

        /**
         * An enum containing [Replies]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Replies] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            INCLUDE,
            EXCLUDE,
            ONLY,
            /** An enum member indicating that [Replies] was instantiated with an unknown value. */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         */
        fun value(): Value =
            when (this) {
                INCLUDE -> Value.INCLUDE
                EXCLUDE -> Value.EXCLUDE
                ONLY -> Value.ONLY
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
                INCLUDE -> Known.INCLUDE
                EXCLUDE -> Known.EXCLUDE
                ONLY -> Known.ONLY
                else -> throw XTwitterScraperInvalidDataException("Unknown Replies: $value")
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
        fun validate(): Replies = apply {
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

            return other is Replies && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    /** Only when the caller requests a repost mode. */
    class Retweets @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

        /** Returns this class instance's raw value. */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            val INCLUDE = of("include")

            val EXCLUDE = of("exclude")

            val ONLY = of("only")

            fun of(value: String) = Retweets(JsonField.of(value))
        }

        /** An enum containing [Retweets]'s known values. */
        enum class Known {
            INCLUDE,
            EXCLUDE,
            ONLY,
        }

        /**
         * An enum containing [Retweets]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Retweets] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            INCLUDE,
            EXCLUDE,
            ONLY,
            /** An enum member indicating that [Retweets] was instantiated with an unknown value. */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         */
        fun value(): Value =
            when (this) {
                INCLUDE -> Value.INCLUDE
                EXCLUDE -> Value.EXCLUDE
                ONLY -> Value.ONLY
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
                INCLUDE -> Known.INCLUDE
                EXCLUDE -> Known.EXCLUDE
                ONLY -> Known.ONLY
                else -> throw XTwitterScraperInvalidDataException("Unknown Retweets: $value")
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
        fun validate(): Retweets = apply {
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

            return other is Retweets && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ListRetrieveTweetsParams &&
            id == other.id &&
            advancedQuery == other.advancedQuery &&
            after == other.after &&
            anyWords == other.anyWords &&
            blueVerifiedOnly == other.blueVerifiedOnly &&
            boundingBox == other.boundingBox &&
            cardName == other.cardName &&
            cashtags == other.cashtags &&
            conversationId == other.conversationId &&
            cursor == other.cursor &&
            exactPhrase == other.exactPhrase &&
            excludeSource == other.excludeSource &&
            excludeWords == other.excludeWords &&
            fields == other.fields &&
            fromUser == other.fromUser &&
            geocode == other.geocode &&
            hashtags == other.hashtags &&
            inReplyToTweetId == other.inReplyToTweetId &&
            includeReplies == other.includeReplies &&
            keywords == other.keywords &&
            language == other.language &&
            maxFaves == other.maxFaves &&
            maxId == other.maxId &&
            maxQuotes == other.maxQuotes &&
            maxReplies == other.maxReplies &&
            maxRetweets == other.maxRetweets &&
            mediaType == other.mediaType &&
            mentioning == other.mentioning &&
            minBookmarks == other.minBookmarks &&
            minLikes == other.minLikes &&
            minQuotes == other.minQuotes &&
            minReplies == other.minReplies &&
            minRetweets == other.minRetweets &&
            minViews == other.minViews &&
            mode == other.mode &&
            nativeRetweets == other.nativeRetweets &&
            near == other.near &&
            news == other.news &&
            omit == other.omit &&
            pageSize == other.pageSize &&
            place == other.place &&
            placeCountry == other.placeCountry &&
            pointRadius == other.pointRadius &&
            quotes == other.quotes &&
            quotesOfTweetId == other.quotesOfTweetId &&
            replies == other.replies &&
            retweets == other.retweets &&
            retweetsOfTweetId == other.retweetsOfTweetId &&
            safe == other.safe &&
            sinceDate == other.sinceDate &&
            sinceId == other.sinceId &&
            sinceTime == other.sinceTime &&
            source == other.source &&
            toUser == other.toUser &&
            untilDate == other.untilDate &&
            untilTime == other.untilTime &&
            url == other.url &&
            verifiedOnly == other.verifiedOnly &&
            within == other.within &&
            withinTime == other.withinTime &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(
            id,
            advancedQuery,
            after,
            anyWords,
            blueVerifiedOnly,
            boundingBox,
            cardName,
            cashtags,
            conversationId,
            cursor,
            exactPhrase,
            excludeSource,
            excludeWords,
            fields,
            fromUser,
            geocode,
            hashtags,
            inReplyToTweetId,
            includeReplies,
            keywords,
            language,
            maxFaves,
            maxId,
            maxQuotes,
            maxReplies,
            maxRetweets,
            mediaType,
            mentioning,
            minBookmarks,
            minLikes,
            minQuotes,
            minReplies,
            minRetweets,
            minViews,
            mode,
            nativeRetweets,
            near,
            news,
            omit,
            pageSize,
            place,
            placeCountry,
            pointRadius,
            quotes,
            quotesOfTweetId,
            replies,
            retweets,
            retweetsOfTweetId,
            safe,
            sinceDate,
            sinceId,
            sinceTime,
            source,
            toUser,
            untilDate,
            untilTime,
            url,
            verifiedOnly,
            within,
            withinTime,
            additionalHeaders,
            additionalQueryParams,
        )

    override fun toString() =
        "ListRetrieveTweetsParams{id=$id, advancedQuery=$advancedQuery, after=$after, anyWords=$anyWords, blueVerifiedOnly=$blueVerifiedOnly, boundingBox=$boundingBox, cardName=$cardName, cashtags=$cashtags, conversationId=$conversationId, cursor=$cursor, exactPhrase=$exactPhrase, excludeSource=$excludeSource, excludeWords=$excludeWords, fields=$fields, fromUser=$fromUser, geocode=$geocode, hashtags=$hashtags, inReplyToTweetId=$inReplyToTweetId, includeReplies=$includeReplies, keywords=$keywords, language=$language, maxFaves=$maxFaves, maxId=$maxId, maxQuotes=$maxQuotes, maxReplies=$maxReplies, maxRetweets=$maxRetweets, mediaType=$mediaType, mentioning=$mentioning, minBookmarks=$minBookmarks, minLikes=$minLikes, minQuotes=$minQuotes, minReplies=$minReplies, minRetweets=$minRetweets, minViews=$minViews, mode=$mode, nativeRetweets=$nativeRetweets, near=$near, news=$news, omit=$omit, pageSize=$pageSize, place=$place, placeCountry=$placeCountry, pointRadius=$pointRadius, quotes=$quotes, quotesOfTweetId=$quotesOfTweetId, replies=$replies, retweets=$retweets, retweetsOfTweetId=$retweetsOfTweetId, safe=$safe, sinceDate=$sinceDate, sinceId=$sinceId, sinceTime=$sinceTime, source=$source, toUser=$toUser, untilDate=$untilDate, untilTime=$untilTime, url=$url, verifiedOnly=$verifiedOnly, within=$within, withinTime=$withinTime, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
