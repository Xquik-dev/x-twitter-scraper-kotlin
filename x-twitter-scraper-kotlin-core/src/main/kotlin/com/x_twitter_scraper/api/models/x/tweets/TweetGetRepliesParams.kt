// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.models.x.tweets

import com.fasterxml.jackson.annotation.JsonCreator
import com.x_twitter_scraper.api.core.Enum
import com.x_twitter_scraper.api.core.JsonField
import com.x_twitter_scraper.api.core.Params
import com.x_twitter_scraper.api.core.http.Headers
import com.x_twitter_scraper.api.core.http.QueryParams
import com.x_twitter_scraper.api.errors.XTwitterScraperInvalidDataException
import java.time.LocalDate
import java.util.Objects

/**
 * Returns direct replies. Omit mode for automatic maximum coverage with resumable pagination.
 * Complete mode returns nested replies, diagnostics, and 424 when direct coverage stays below 80%.
 */
class TweetGetRepliesParams
private constructor(
    private val id: String?,
    private val anyWords: String?,
    private val blueVerifiedOnly: Boolean?,
    private val cardName: String?,
    private val cashtags: String?,
    private val conversationId: String?,
    private val cursor: String?,
    private val exactPhrase: String?,
    private val excludeOriginalAuthor: Boolean?,
    private val excludeSource: String?,
    private val excludeWords: String?,
    private val fromUser: String?,
    private val geocode: String?,
    private val hashtags: String?,
    private val hasMediaOnly: Boolean?,
    private val includeOriginalPost: Boolean?,
    private val inReplyToTweetId: String?,
    private val language: String?,
    private val limit: Long?,
    private val maxDepth: Long?,
    private val maxFaves: Long?,
    private val maxId: String?,
    private val maxQuotes: Long?,
    private val maxReplies: Long?,
    private val maxRetweets: Long?,
    private val mediaType: MediaType?,
    private val mentioning: String?,
    private val minBookmarks: Long?,
    private val minFaves: Long?,
    private val minQuotes: Long?,
    private val minReplies: Long?,
    private val minRetweets: Long?,
    private val minViews: Long?,
    private val mode: Mode?,
    private val nativeRetweets: Boolean?,
    private val near: String?,
    private val news: Boolean?,
    private val pageSize: Long?,
    private val quotes: Quotes?,
    private val quotesOfTweetId: String?,
    private val replies: Replies?,
    private val retweets: Retweets?,
    private val retweetsOfTweetId: String?,
    private val safe: Boolean?,
    private val scope: Scope?,
    private val sinceDate: LocalDate?,
    private val sinceId: String?,
    private val sinceTime: String?,
    private val sort: Sort?,
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

    /** Words or quoted phrases where any one can match. Separate with spaces, commas, or lines. */
    fun anyWords(): String? = anyWords

    /** Only return tweets from Blue-verified authors. */
    fun blueVerifiedOnly(): Boolean? = blueVerifiedOnly

    /** Match the Tweet card name. */
    fun cardName(): String? = cardName

    /** Cashtags separated by spaces, commas, or lines. */
    fun cashtags(): String? = cashtags

    /** Conversation ID filter. */
    fun conversationId(): String? = conversationId

    /**
     * Cursor from the previous response. Xquik cursors resume automatic coverage. Existing
     * unprefixed cursors keep legacy standard behavior.
     */
    fun cursor(): String? = cursor

    /** Exact phrase to match. */
    fun exactPhrase(): String? = exactPhrase

    /** Exclude replies written by the source-post author. */
    fun excludeOriginalAuthor(): Boolean? = excludeOriginalAuthor

    /** Exclude a source application. */
    fun excludeSource(): String? = excludeSource

    /** Words or quoted phrases to exclude. Separate with spaces, commas, or lines. */
    fun excludeWords(): String? = excludeWords

    /** Filter by author username. */
    fun fromUser(): String? = fromUser

    /** Match latitude, longitude, and radius. */
    fun geocode(): String? = geocode

    /** Hashtags separated by spaces, commas, or lines. */
    fun hashtags(): String? = hashtags

    /** Only return replies containing media. */
    fun hasMediaOnly(): Boolean? = hasMediaOnly

    /** Include the source post and count it toward limit. */
    fun includeOriginalPost(): Boolean? = includeOriginalPost

    /** Only replies to this tweet ID. */
    fun inReplyToTweetId(): String? = inReplyToTweetId

    /** Language code filter, e.g. en or tr. */
    fun language(): String? = language

    /**
     * With mode=complete, maximum combined direct and nested reply rows (1-25000, default 25000).
     * Automatic pages accept 1-300. Standard pages accept 1-100. Prefer pageSize outside complete
     * mode.
     */
    fun limit(): Long? = limit

    /** Maximum reply depth from the source post. */
    fun maxDepth(): Long? = maxDepth

    /** Maximum likes threshold. maxLikes is also accepted. */
    fun maxFaves(): Long? = maxFaves

    /** Return Tweets older than this Tweet ID. */
    fun maxId(): String? = maxId

    /** Maximum quotes threshold. */
    fun maxQuotes(): Long? = maxQuotes

    /** Maximum replies threshold. */
    fun maxReplies(): Long? = maxReplies

    /** Maximum retweets threshold. */
    fun maxRetweets(): Long? = maxRetweets

    /** Filter by media type. */
    fun mediaType(): MediaType? = mediaType

    /** Filter tweets mentioning a username. */
    fun mentioning(): String? = mentioning

    /** Minimum bookmark count threshold. */
    fun minBookmarks(): Long? = minBookmarks

    /** Minimum likes threshold. */
    fun minFaves(): Long? = minFaves

    /** Minimum quote count threshold. */
    fun minQuotes(): Long? = minQuotes

    /** Minimum replies threshold. */
    fun minReplies(): Long? = minReplies

    /** Minimum retweets threshold. */
    fun minRetweets(): Long? = minRetweets

    /** Minimum view count threshold. */
    fun minViews(): Long? = minViews

    /**
     * Optional advanced override. Omit mode for automatic maximum direct reply coverage with
     * pagination. Standard keeps legacy pagination. Complete returns direct and nested replies with
     * diagnostics, scope, depth, sorting, and original-post controls.
     */
    fun mode(): Mode? = mode

    /** Only return native reposts. */
    fun nativeRetweets(): Boolean? = nativeRetweets

    /** Match a place name. */
    fun near(): String? = near

    /** Only return news results. */
    fun news(): Boolean? = news

    /**
     * Automatic pages accept 1-300 Tweets. Standard pages keep 1-100. Default 20. Continue while
     * has_next_page is true. Deprecated aliases remain accepted.
     */
    fun pageSize(): Long? = pageSize

    /** Quote mode. */
    fun quotes(): Quotes? = quotes

    /** Only quotes of this tweet ID. */
    fun quotesOfTweetId(): String? = quotesOfTweetId

    /** Reply mode. */
    fun replies(): Replies? = replies

    /** Retweet mode. */
    fun retweets(): Retweets? = retweets

    /** Only retweets of this tweet ID. */
    fun retweetsOfTweetId(): String? = retweetsOfTweetId

    /** Enable the safe-search filter. */
    fun safe(): Boolean? = safe

    /** Select all replies, direct replies, or nested replies. */
    fun scope(): Scope? = scope

    /** Start date in YYYY-MM-DD format. */
    fun sinceDate(): LocalDate? = sinceDate

    /** Return Tweets newer than this Tweet ID. */
    fun sinceId(): String? = sinceId

    /** Unix timestamp - return replies posted after this time */
    fun sinceTime(): String? = sinceTime

    /** Sort the selected replies before applying limit. */
    fun sort(): Sort? = sort

    /** Match the source application. */
    fun source(): String? = source

    /** Filter replies sent to a username. */
    fun toUser(): String? = toUser

    /** End date in YYYY-MM-DD format. */
    fun untilDate(): LocalDate? = untilDate

    /** Unix timestamp - return replies posted before this time */
    fun untilTime(): String? = untilTime

    /** URL substring or domain filter. */
    fun url(): String? = url

    /** Only return tweets from verified authors. */
    fun verifiedOnly(): Boolean? = verifiedOnly

    /** Set the radius for the near filter. */
    fun within(): String? = within

    /** Match Tweets inside a recent time window. */
    fun withinTime(): String? = withinTime

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        fun none(): TweetGetRepliesParams = builder().build()

        /** Returns a mutable builder for constructing an instance of [TweetGetRepliesParams]. */
        fun builder() = Builder()
    }

    /** A builder for [TweetGetRepliesParams]. */
    class Builder internal constructor() {

        private var id: String? = null
        private var anyWords: String? = null
        private var blueVerifiedOnly: Boolean? = null
        private var cardName: String? = null
        private var cashtags: String? = null
        private var conversationId: String? = null
        private var cursor: String? = null
        private var exactPhrase: String? = null
        private var excludeOriginalAuthor: Boolean? = null
        private var excludeSource: String? = null
        private var excludeWords: String? = null
        private var fromUser: String? = null
        private var geocode: String? = null
        private var hashtags: String? = null
        private var hasMediaOnly: Boolean? = null
        private var includeOriginalPost: Boolean? = null
        private var inReplyToTweetId: String? = null
        private var language: String? = null
        private var limit: Long? = null
        private var maxDepth: Long? = null
        private var maxFaves: Long? = null
        private var maxId: String? = null
        private var maxQuotes: Long? = null
        private var maxReplies: Long? = null
        private var maxRetweets: Long? = null
        private var mediaType: MediaType? = null
        private var mentioning: String? = null
        private var minBookmarks: Long? = null
        private var minFaves: Long? = null
        private var minQuotes: Long? = null
        private var minReplies: Long? = null
        private var minRetweets: Long? = null
        private var minViews: Long? = null
        private var mode: Mode? = null
        private var nativeRetweets: Boolean? = null
        private var near: String? = null
        private var news: Boolean? = null
        private var pageSize: Long? = null
        private var quotes: Quotes? = null
        private var quotesOfTweetId: String? = null
        private var replies: Replies? = null
        private var retweets: Retweets? = null
        private var retweetsOfTweetId: String? = null
        private var safe: Boolean? = null
        private var scope: Scope? = null
        private var sinceDate: LocalDate? = null
        private var sinceId: String? = null
        private var sinceTime: String? = null
        private var sort: Sort? = null
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

        internal fun from(tweetGetRepliesParams: TweetGetRepliesParams) = apply {
            id = tweetGetRepliesParams.id
            anyWords = tweetGetRepliesParams.anyWords
            blueVerifiedOnly = tweetGetRepliesParams.blueVerifiedOnly
            cardName = tweetGetRepliesParams.cardName
            cashtags = tweetGetRepliesParams.cashtags
            conversationId = tweetGetRepliesParams.conversationId
            cursor = tweetGetRepliesParams.cursor
            exactPhrase = tweetGetRepliesParams.exactPhrase
            excludeOriginalAuthor = tweetGetRepliesParams.excludeOriginalAuthor
            excludeSource = tweetGetRepliesParams.excludeSource
            excludeWords = tweetGetRepliesParams.excludeWords
            fromUser = tweetGetRepliesParams.fromUser
            geocode = tweetGetRepliesParams.geocode
            hashtags = tweetGetRepliesParams.hashtags
            hasMediaOnly = tweetGetRepliesParams.hasMediaOnly
            includeOriginalPost = tweetGetRepliesParams.includeOriginalPost
            inReplyToTweetId = tweetGetRepliesParams.inReplyToTweetId
            language = tweetGetRepliesParams.language
            limit = tweetGetRepliesParams.limit
            maxDepth = tweetGetRepliesParams.maxDepth
            maxFaves = tweetGetRepliesParams.maxFaves
            maxId = tweetGetRepliesParams.maxId
            maxQuotes = tweetGetRepliesParams.maxQuotes
            maxReplies = tweetGetRepliesParams.maxReplies
            maxRetweets = tweetGetRepliesParams.maxRetweets
            mediaType = tweetGetRepliesParams.mediaType
            mentioning = tweetGetRepliesParams.mentioning
            minBookmarks = tweetGetRepliesParams.minBookmarks
            minFaves = tweetGetRepliesParams.minFaves
            minQuotes = tweetGetRepliesParams.minQuotes
            minReplies = tweetGetRepliesParams.minReplies
            minRetweets = tweetGetRepliesParams.minRetweets
            minViews = tweetGetRepliesParams.minViews
            mode = tweetGetRepliesParams.mode
            nativeRetweets = tweetGetRepliesParams.nativeRetweets
            near = tweetGetRepliesParams.near
            news = tweetGetRepliesParams.news
            pageSize = tweetGetRepliesParams.pageSize
            quotes = tweetGetRepliesParams.quotes
            quotesOfTweetId = tweetGetRepliesParams.quotesOfTweetId
            replies = tweetGetRepliesParams.replies
            retweets = tweetGetRepliesParams.retweets
            retweetsOfTweetId = tweetGetRepliesParams.retweetsOfTweetId
            safe = tweetGetRepliesParams.safe
            scope = tweetGetRepliesParams.scope
            sinceDate = tweetGetRepliesParams.sinceDate
            sinceId = tweetGetRepliesParams.sinceId
            sinceTime = tweetGetRepliesParams.sinceTime
            sort = tweetGetRepliesParams.sort
            source = tweetGetRepliesParams.source
            toUser = tweetGetRepliesParams.toUser
            untilDate = tweetGetRepliesParams.untilDate
            untilTime = tweetGetRepliesParams.untilTime
            url = tweetGetRepliesParams.url
            verifiedOnly = tweetGetRepliesParams.verifiedOnly
            within = tweetGetRepliesParams.within
            withinTime = tweetGetRepliesParams.withinTime
            additionalHeaders = tweetGetRepliesParams.additionalHeaders.toBuilder()
            additionalQueryParams = tweetGetRepliesParams.additionalQueryParams.toBuilder()
        }

        fun id(id: String?) = apply { this.id = id }

        /**
         * Words or quoted phrases where any one can match. Separate with spaces, commas, or lines.
         */
        fun anyWords(anyWords: String?) = apply { this.anyWords = anyWords }

        /** Only return tweets from Blue-verified authors. */
        fun blueVerifiedOnly(blueVerifiedOnly: Boolean?) = apply {
            this.blueVerifiedOnly = blueVerifiedOnly
        }

        /**
         * Alias for [Builder.blueVerifiedOnly].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun blueVerifiedOnly(blueVerifiedOnly: Boolean) =
            blueVerifiedOnly(blueVerifiedOnly as Boolean?)

        /** Match the Tweet card name. */
        fun cardName(cardName: String?) = apply { this.cardName = cardName }

        /** Cashtags separated by spaces, commas, or lines. */
        fun cashtags(cashtags: String?) = apply { this.cashtags = cashtags }

        /** Conversation ID filter. */
        fun conversationId(conversationId: String?) = apply { this.conversationId = conversationId }

        /**
         * Cursor from the previous response. Xquik cursors resume automatic coverage. Existing
         * unprefixed cursors keep legacy standard behavior.
         */
        fun cursor(cursor: String?) = apply { this.cursor = cursor }

        /** Exact phrase to match. */
        fun exactPhrase(exactPhrase: String?) = apply { this.exactPhrase = exactPhrase }

        /** Exclude replies written by the source-post author. */
        fun excludeOriginalAuthor(excludeOriginalAuthor: Boolean?) = apply {
            this.excludeOriginalAuthor = excludeOriginalAuthor
        }

        /**
         * Alias for [Builder.excludeOriginalAuthor].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun excludeOriginalAuthor(excludeOriginalAuthor: Boolean) =
            excludeOriginalAuthor(excludeOriginalAuthor as Boolean?)

        /** Exclude a source application. */
        fun excludeSource(excludeSource: String?) = apply { this.excludeSource = excludeSource }

        /** Words or quoted phrases to exclude. Separate with spaces, commas, or lines. */
        fun excludeWords(excludeWords: String?) = apply { this.excludeWords = excludeWords }

        /** Filter by author username. */
        fun fromUser(fromUser: String?) = apply { this.fromUser = fromUser }

        /** Match latitude, longitude, and radius. */
        fun geocode(geocode: String?) = apply { this.geocode = geocode }

        /** Hashtags separated by spaces, commas, or lines. */
        fun hashtags(hashtags: String?) = apply { this.hashtags = hashtags }

        /** Only return replies containing media. */
        fun hasMediaOnly(hasMediaOnly: Boolean?) = apply { this.hasMediaOnly = hasMediaOnly }

        /**
         * Alias for [Builder.hasMediaOnly].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun hasMediaOnly(hasMediaOnly: Boolean) = hasMediaOnly(hasMediaOnly as Boolean?)

        /** Include the source post and count it toward limit. */
        fun includeOriginalPost(includeOriginalPost: Boolean?) = apply {
            this.includeOriginalPost = includeOriginalPost
        }

        /**
         * Alias for [Builder.includeOriginalPost].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun includeOriginalPost(includeOriginalPost: Boolean) =
            includeOriginalPost(includeOriginalPost as Boolean?)

        /** Only replies to this tweet ID. */
        fun inReplyToTweetId(inReplyToTweetId: String?) = apply {
            this.inReplyToTweetId = inReplyToTweetId
        }

        /** Language code filter, e.g. en or tr. */
        fun language(language: String?) = apply { this.language = language }

        /**
         * With mode=complete, maximum combined direct and nested reply rows (1-25000, default
         * 25000). Automatic pages accept 1-300. Standard pages accept 1-100. Prefer pageSize
         * outside complete mode.
         */
        fun limit(limit: Long?) = apply { this.limit = limit }

        /**
         * Alias for [Builder.limit].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun limit(limit: Long) = limit(limit as Long?)

        /** Maximum reply depth from the source post. */
        fun maxDepth(maxDepth: Long?) = apply { this.maxDepth = maxDepth }

        /**
         * Alias for [Builder.maxDepth].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun maxDepth(maxDepth: Long) = maxDepth(maxDepth as Long?)

        /** Maximum likes threshold. maxLikes is also accepted. */
        fun maxFaves(maxFaves: Long?) = apply { this.maxFaves = maxFaves }

        /**
         * Alias for [Builder.maxFaves].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun maxFaves(maxFaves: Long) = maxFaves(maxFaves as Long?)

        /** Return Tweets older than this Tweet ID. */
        fun maxId(maxId: String?) = apply { this.maxId = maxId }

        /** Maximum quotes threshold. */
        fun maxQuotes(maxQuotes: Long?) = apply { this.maxQuotes = maxQuotes }

        /**
         * Alias for [Builder.maxQuotes].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun maxQuotes(maxQuotes: Long) = maxQuotes(maxQuotes as Long?)

        /** Maximum replies threshold. */
        fun maxReplies(maxReplies: Long?) = apply { this.maxReplies = maxReplies }

        /**
         * Alias for [Builder.maxReplies].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun maxReplies(maxReplies: Long) = maxReplies(maxReplies as Long?)

        /** Maximum retweets threshold. */
        fun maxRetweets(maxRetweets: Long?) = apply { this.maxRetweets = maxRetweets }

        /**
         * Alias for [Builder.maxRetweets].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun maxRetweets(maxRetweets: Long) = maxRetweets(maxRetweets as Long?)

        /** Filter by media type. */
        fun mediaType(mediaType: MediaType?) = apply { this.mediaType = mediaType }

        /** Filter tweets mentioning a username. */
        fun mentioning(mentioning: String?) = apply { this.mentioning = mentioning }

        /** Minimum bookmark count threshold. */
        fun minBookmarks(minBookmarks: Long?) = apply { this.minBookmarks = minBookmarks }

        /**
         * Alias for [Builder.minBookmarks].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun minBookmarks(minBookmarks: Long) = minBookmarks(minBookmarks as Long?)

        /** Minimum likes threshold. */
        fun minFaves(minFaves: Long?) = apply { this.minFaves = minFaves }

        /**
         * Alias for [Builder.minFaves].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun minFaves(minFaves: Long) = minFaves(minFaves as Long?)

        /** Minimum quote count threshold. */
        fun minQuotes(minQuotes: Long?) = apply { this.minQuotes = minQuotes }

        /**
         * Alias for [Builder.minQuotes].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun minQuotes(minQuotes: Long) = minQuotes(minQuotes as Long?)

        /** Minimum replies threshold. */
        fun minReplies(minReplies: Long?) = apply { this.minReplies = minReplies }

        /**
         * Alias for [Builder.minReplies].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun minReplies(minReplies: Long) = minReplies(minReplies as Long?)

        /** Minimum retweets threshold. */
        fun minRetweets(minRetweets: Long?) = apply { this.minRetweets = minRetweets }

        /**
         * Alias for [Builder.minRetweets].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun minRetweets(minRetweets: Long) = minRetweets(minRetweets as Long?)

        /** Minimum view count threshold. */
        fun minViews(minViews: Long?) = apply { this.minViews = minViews }

        /**
         * Alias for [Builder.minViews].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun minViews(minViews: Long) = minViews(minViews as Long?)

        /**
         * Optional advanced override. Omit mode for automatic maximum direct reply coverage with
         * pagination. Standard keeps legacy pagination. Complete returns direct and nested replies
         * with diagnostics, scope, depth, sorting, and original-post controls.
         */
        fun mode(mode: Mode?) = apply { this.mode = mode }

        /** Only return native reposts. */
        fun nativeRetweets(nativeRetweets: Boolean?) = apply {
            this.nativeRetweets = nativeRetweets
        }

        /**
         * Alias for [Builder.nativeRetweets].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun nativeRetweets(nativeRetweets: Boolean) = nativeRetweets(nativeRetweets as Boolean?)

        /** Match a place name. */
        fun near(near: String?) = apply { this.near = near }

        /** Only return news results. */
        fun news(news: Boolean?) = apply { this.news = news }

        /**
         * Alias for [Builder.news].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun news(news: Boolean) = news(news as Boolean?)

        /**
         * Automatic pages accept 1-300 Tweets. Standard pages keep 1-100. Default 20. Continue
         * while has_next_page is true. Deprecated aliases remain accepted.
         */
        fun pageSize(pageSize: Long?) = apply { this.pageSize = pageSize }

        /**
         * Alias for [Builder.pageSize].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun pageSize(pageSize: Long) = pageSize(pageSize as Long?)

        /** Quote mode. */
        fun quotes(quotes: Quotes?) = apply { this.quotes = quotes }

        /** Only quotes of this tweet ID. */
        fun quotesOfTweetId(quotesOfTweetId: String?) = apply {
            this.quotesOfTweetId = quotesOfTweetId
        }

        /** Reply mode. */
        fun replies(replies: Replies?) = apply { this.replies = replies }

        /** Retweet mode. */
        fun retweets(retweets: Retweets?) = apply { this.retweets = retweets }

        /** Only retweets of this tweet ID. */
        fun retweetsOfTweetId(retweetsOfTweetId: String?) = apply {
            this.retweetsOfTweetId = retweetsOfTweetId
        }

        /** Enable the safe-search filter. */
        fun safe(safe: Boolean?) = apply { this.safe = safe }

        /**
         * Alias for [Builder.safe].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun safe(safe: Boolean) = safe(safe as Boolean?)

        /** Select all replies, direct replies, or nested replies. */
        fun scope(scope: Scope?) = apply { this.scope = scope }

        /** Start date in YYYY-MM-DD format. */
        fun sinceDate(sinceDate: LocalDate?) = apply { this.sinceDate = sinceDate }

        /** Return Tweets newer than this Tweet ID. */
        fun sinceId(sinceId: String?) = apply { this.sinceId = sinceId }

        /** Unix timestamp - return replies posted after this time */
        fun sinceTime(sinceTime: String?) = apply { this.sinceTime = sinceTime }

        /** Sort the selected replies before applying limit. */
        fun sort(sort: Sort?) = apply { this.sort = sort }

        /** Match the source application. */
        fun source(source: String?) = apply { this.source = source }

        /** Filter replies sent to a username. */
        fun toUser(toUser: String?) = apply { this.toUser = toUser }

        /** End date in YYYY-MM-DD format. */
        fun untilDate(untilDate: LocalDate?) = apply { this.untilDate = untilDate }

        /** Unix timestamp - return replies posted before this time */
        fun untilTime(untilTime: String?) = apply { this.untilTime = untilTime }

        /** URL substring or domain filter. */
        fun url(url: String?) = apply { this.url = url }

        /** Only return tweets from verified authors. */
        fun verifiedOnly(verifiedOnly: Boolean?) = apply { this.verifiedOnly = verifiedOnly }

        /**
         * Alias for [Builder.verifiedOnly].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun verifiedOnly(verifiedOnly: Boolean) = verifiedOnly(verifiedOnly as Boolean?)

        /** Set the radius for the near filter. */
        fun within(within: String?) = apply { this.within = within }

        /** Match Tweets inside a recent time window. */
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
         * Returns an immutable instance of [TweetGetRepliesParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): TweetGetRepliesParams =
            TweetGetRepliesParams(
                id,
                anyWords,
                blueVerifiedOnly,
                cardName,
                cashtags,
                conversationId,
                cursor,
                exactPhrase,
                excludeOriginalAuthor,
                excludeSource,
                excludeWords,
                fromUser,
                geocode,
                hashtags,
                hasMediaOnly,
                includeOriginalPost,
                inReplyToTweetId,
                language,
                limit,
                maxDepth,
                maxFaves,
                maxId,
                maxQuotes,
                maxReplies,
                maxRetweets,
                mediaType,
                mentioning,
                minBookmarks,
                minFaves,
                minQuotes,
                minReplies,
                minRetweets,
                minViews,
                mode,
                nativeRetweets,
                near,
                news,
                pageSize,
                quotes,
                quotesOfTweetId,
                replies,
                retweets,
                retweetsOfTweetId,
                safe,
                scope,
                sinceDate,
                sinceId,
                sinceTime,
                sort,
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
                anyWords?.let { put("anyWords", it) }
                blueVerifiedOnly?.let { put("blueVerifiedOnly", it.toString()) }
                cardName?.let { put("cardName", it) }
                cashtags?.let { put("cashtags", it) }
                conversationId?.let { put("conversationId", it) }
                cursor?.let { put("cursor", it) }
                exactPhrase?.let { put("exactPhrase", it) }
                excludeOriginalAuthor?.let { put("excludeOriginalAuthor", it.toString()) }
                excludeSource?.let { put("excludeSource", it) }
                excludeWords?.let { put("excludeWords", it) }
                fromUser?.let { put("fromUser", it) }
                geocode?.let { put("geocode", it) }
                hashtags?.let { put("hashtags", it) }
                hasMediaOnly?.let { put("hasMediaOnly", it.toString()) }
                includeOriginalPost?.let { put("includeOriginalPost", it.toString()) }
                inReplyToTweetId?.let { put("inReplyToTweetId", it) }
                language?.let { put("language", it) }
                limit?.let { put("limit", it.toString()) }
                maxDepth?.let { put("maxDepth", it.toString()) }
                maxFaves?.let { put("maxFaves", it.toString()) }
                maxId?.let { put("maxId", it) }
                maxQuotes?.let { put("maxQuotes", it.toString()) }
                maxReplies?.let { put("maxReplies", it.toString()) }
                maxRetweets?.let { put("maxRetweets", it.toString()) }
                mediaType?.let { put("mediaType", it.toString()) }
                mentioning?.let { put("mentioning", it) }
                minBookmarks?.let { put("minBookmarks", it.toString()) }
                minFaves?.let { put("minFaves", it.toString()) }
                minQuotes?.let { put("minQuotes", it.toString()) }
                minReplies?.let { put("minReplies", it.toString()) }
                minRetweets?.let { put("minRetweets", it.toString()) }
                minViews?.let { put("minViews", it.toString()) }
                mode?.let { put("mode", it.toString()) }
                nativeRetweets?.let { put("nativeRetweets", it.toString()) }
                near?.let { put("near", it) }
                news?.let { put("news", it.toString()) }
                pageSize?.let { put("pageSize", it.toString()) }
                quotes?.let { put("quotes", it.toString()) }
                quotesOfTweetId?.let { put("quotesOfTweetId", it) }
                replies?.let { put("replies", it.toString()) }
                retweets?.let { put("retweets", it.toString()) }
                retweetsOfTweetId?.let { put("retweetsOfTweetId", it) }
                safe?.let { put("safe", it.toString()) }
                scope?.let { put("scope", it.toString()) }
                sinceDate?.let { put("sinceDate", it.toString()) }
                sinceId?.let { put("sinceId", it) }
                sinceTime?.let { put("sinceTime", it) }
                sort?.let { put("sort", it.toString()) }
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

    /** Filter by media type. */
    class MediaType @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

        /**
         * Returns this class instance's raw value.
         *
         * This is usually only useful if this instance was deserialized from data that doesn't
         * match any known member, and you want to know that value. For example, if the SDK is on an
         * older version than the API, then the API may respond with new members that the SDK is
         * unaware of.
         */
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
         *
         * Use the [known] method instead if you're certain the value is always known or if you want
         * to throw for the unknown case.
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
         * Use the [value] method instead if you're uncertain the value is always known and don't
         * want to throw for the unknown case.
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
         * This differs from the [toString] method because that method is primarily for debugging
         * and generally doesn't throw.
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

        /**
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
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
     * Optional advanced override. Omit mode for automatic maximum direct reply coverage with
     * pagination. Standard keeps legacy pagination. Complete returns direct and nested replies with
     * diagnostics, scope, depth, sorting, and original-post controls.
     */
    class Mode @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

        /**
         * Returns this class instance's raw value.
         *
         * This is usually only useful if this instance was deserialized from data that doesn't
         * match any known member, and you want to know that value. For example, if the SDK is on an
         * older version than the API, then the API may respond with new members that the SDK is
         * unaware of.
         */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            val STANDARD = of("standard")

            val COMPLETE = of("complete")

            fun of(value: String) = Mode(JsonField.of(value))
        }

        /** An enum containing [Mode]'s known values. */
        enum class Known {
            STANDARD,
            COMPLETE,
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
            COMPLETE,
            /** An enum member indicating that [Mode] was instantiated with an unknown value. */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         *
         * Use the [known] method instead if you're certain the value is always known or if you want
         * to throw for the unknown case.
         */
        fun value(): Value =
            when (this) {
                STANDARD -> Value.STANDARD
                COMPLETE -> Value.COMPLETE
                else -> Value._UNKNOWN
            }

        /**
         * Returns an enum member corresponding to this class instance's value.
         *
         * Use the [value] method instead if you're uncertain the value is always known and don't
         * want to throw for the unknown case.
         *
         * @throws XTwitterScraperInvalidDataException if this class instance's value is a not a
         *   known member.
         */
        fun known(): Known =
            when (this) {
                STANDARD -> Known.STANDARD
                COMPLETE -> Known.COMPLETE
                else -> throw XTwitterScraperInvalidDataException("Unknown Mode: $value")
            }

        /**
         * Returns this class instance's primitive wire representation.
         *
         * This differs from the [toString] method because that method is primarily for debugging
         * and generally doesn't throw.
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

        /**
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
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

    /** Quote mode. */
    class Quotes @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

        /**
         * Returns this class instance's raw value.
         *
         * This is usually only useful if this instance was deserialized from data that doesn't
         * match any known member, and you want to know that value. For example, if the SDK is on an
         * older version than the API, then the API may respond with new members that the SDK is
         * unaware of.
         */
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
         *
         * Use the [known] method instead if you're certain the value is always known or if you want
         * to throw for the unknown case.
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
         * Use the [value] method instead if you're uncertain the value is always known and don't
         * want to throw for the unknown case.
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
         * This differs from the [toString] method because that method is primarily for debugging
         * and generally doesn't throw.
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

        /**
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
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

    /** Reply mode. */
    class Replies @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

        /**
         * Returns this class instance's raw value.
         *
         * This is usually only useful if this instance was deserialized from data that doesn't
         * match any known member, and you want to know that value. For example, if the SDK is on an
         * older version than the API, then the API may respond with new members that the SDK is
         * unaware of.
         */
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
         *
         * Use the [known] method instead if you're certain the value is always known or if you want
         * to throw for the unknown case.
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
         * Use the [value] method instead if you're uncertain the value is always known and don't
         * want to throw for the unknown case.
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
         * This differs from the [toString] method because that method is primarily for debugging
         * and generally doesn't throw.
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

        /**
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
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

    /** Retweet mode. */
    class Retweets @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

        /**
         * Returns this class instance's raw value.
         *
         * This is usually only useful if this instance was deserialized from data that doesn't
         * match any known member, and you want to know that value. For example, if the SDK is on an
         * older version than the API, then the API may respond with new members that the SDK is
         * unaware of.
         */
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
         *
         * Use the [known] method instead if you're certain the value is always known or if you want
         * to throw for the unknown case.
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
         * Use the [value] method instead if you're uncertain the value is always known and don't
         * want to throw for the unknown case.
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
         * This differs from the [toString] method because that method is primarily for debugging
         * and generally doesn't throw.
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

        /**
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
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

    /** Select all replies, direct replies, or nested replies. */
    class Scope @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

        /**
         * Returns this class instance's raw value.
         *
         * This is usually only useful if this instance was deserialized from data that doesn't
         * match any known member, and you want to know that value. For example, if the SDK is on an
         * older version than the API, then the API may respond with new members that the SDK is
         * unaware of.
         */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            val ALL = of("all")

            val DIRECT = of("direct")

            val NESTED = of("nested")

            fun of(value: String) = Scope(JsonField.of(value))
        }

        /** An enum containing [Scope]'s known values. */
        enum class Known {
            ALL,
            DIRECT,
            NESTED,
        }

        /**
         * An enum containing [Scope]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Scope] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            ALL,
            DIRECT,
            NESTED,
            /** An enum member indicating that [Scope] was instantiated with an unknown value. */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         *
         * Use the [known] method instead if you're certain the value is always known or if you want
         * to throw for the unknown case.
         */
        fun value(): Value =
            when (this) {
                ALL -> Value.ALL
                DIRECT -> Value.DIRECT
                NESTED -> Value.NESTED
                else -> Value._UNKNOWN
            }

        /**
         * Returns an enum member corresponding to this class instance's value.
         *
         * Use the [value] method instead if you're uncertain the value is always known and don't
         * want to throw for the unknown case.
         *
         * @throws XTwitterScraperInvalidDataException if this class instance's value is a not a
         *   known member.
         */
        fun known(): Known =
            when (this) {
                ALL -> Known.ALL
                DIRECT -> Known.DIRECT
                NESTED -> Known.NESTED
                else -> throw XTwitterScraperInvalidDataException("Unknown Scope: $value")
            }

        /**
         * Returns this class instance's primitive wire representation.
         *
         * This differs from the [toString] method because that method is primarily for debugging
         * and generally doesn't throw.
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
        fun validate(): Scope = apply {
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

        /**
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Scope && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    /** Sort the selected replies before applying limit. */
    class Sort @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

        /**
         * Returns this class instance's raw value.
         *
         * This is usually only useful if this instance was deserialized from data that doesn't
         * match any known member, and you want to know that value. For example, if the SDK is on an
         * older version than the API, then the API may respond with new members that the SDK is
         * unaware of.
         */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            val RELEVANCE = of("relevance")

            val LATEST = of("latest")

            val OLDEST = of("oldest")

            val LIKES = of("likes")

            fun of(value: String) = Sort(JsonField.of(value))
        }

        /** An enum containing [Sort]'s known values. */
        enum class Known {
            RELEVANCE,
            LATEST,
            OLDEST,
            LIKES,
        }

        /**
         * An enum containing [Sort]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Sort] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            RELEVANCE,
            LATEST,
            OLDEST,
            LIKES,
            /** An enum member indicating that [Sort] was instantiated with an unknown value. */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         *
         * Use the [known] method instead if you're certain the value is always known or if you want
         * to throw for the unknown case.
         */
        fun value(): Value =
            when (this) {
                RELEVANCE -> Value.RELEVANCE
                LATEST -> Value.LATEST
                OLDEST -> Value.OLDEST
                LIKES -> Value.LIKES
                else -> Value._UNKNOWN
            }

        /**
         * Returns an enum member corresponding to this class instance's value.
         *
         * Use the [value] method instead if you're uncertain the value is always known and don't
         * want to throw for the unknown case.
         *
         * @throws XTwitterScraperInvalidDataException if this class instance's value is a not a
         *   known member.
         */
        fun known(): Known =
            when (this) {
                RELEVANCE -> Known.RELEVANCE
                LATEST -> Known.LATEST
                OLDEST -> Known.OLDEST
                LIKES -> Known.LIKES
                else -> throw XTwitterScraperInvalidDataException("Unknown Sort: $value")
            }

        /**
         * Returns this class instance's primitive wire representation.
         *
         * This differs from the [toString] method because that method is primarily for debugging
         * and generally doesn't throw.
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
        fun validate(): Sort = apply {
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

        /**
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Sort && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is TweetGetRepliesParams &&
            id == other.id &&
            anyWords == other.anyWords &&
            blueVerifiedOnly == other.blueVerifiedOnly &&
            cardName == other.cardName &&
            cashtags == other.cashtags &&
            conversationId == other.conversationId &&
            cursor == other.cursor &&
            exactPhrase == other.exactPhrase &&
            excludeOriginalAuthor == other.excludeOriginalAuthor &&
            excludeSource == other.excludeSource &&
            excludeWords == other.excludeWords &&
            fromUser == other.fromUser &&
            geocode == other.geocode &&
            hashtags == other.hashtags &&
            hasMediaOnly == other.hasMediaOnly &&
            includeOriginalPost == other.includeOriginalPost &&
            inReplyToTweetId == other.inReplyToTweetId &&
            language == other.language &&
            limit == other.limit &&
            maxDepth == other.maxDepth &&
            maxFaves == other.maxFaves &&
            maxId == other.maxId &&
            maxQuotes == other.maxQuotes &&
            maxReplies == other.maxReplies &&
            maxRetweets == other.maxRetweets &&
            mediaType == other.mediaType &&
            mentioning == other.mentioning &&
            minBookmarks == other.minBookmarks &&
            minFaves == other.minFaves &&
            minQuotes == other.minQuotes &&
            minReplies == other.minReplies &&
            minRetweets == other.minRetweets &&
            minViews == other.minViews &&
            mode == other.mode &&
            nativeRetweets == other.nativeRetweets &&
            near == other.near &&
            news == other.news &&
            pageSize == other.pageSize &&
            quotes == other.quotes &&
            quotesOfTweetId == other.quotesOfTweetId &&
            replies == other.replies &&
            retweets == other.retweets &&
            retweetsOfTweetId == other.retweetsOfTweetId &&
            safe == other.safe &&
            scope == other.scope &&
            sinceDate == other.sinceDate &&
            sinceId == other.sinceId &&
            sinceTime == other.sinceTime &&
            sort == other.sort &&
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
            anyWords,
            blueVerifiedOnly,
            cardName,
            cashtags,
            conversationId,
            cursor,
            exactPhrase,
            excludeOriginalAuthor,
            excludeSource,
            excludeWords,
            fromUser,
            geocode,
            hashtags,
            hasMediaOnly,
            includeOriginalPost,
            inReplyToTweetId,
            language,
            limit,
            maxDepth,
            maxFaves,
            maxId,
            maxQuotes,
            maxReplies,
            maxRetweets,
            mediaType,
            mentioning,
            minBookmarks,
            minFaves,
            minQuotes,
            minReplies,
            minRetweets,
            minViews,
            mode,
            nativeRetweets,
            near,
            news,
            pageSize,
            quotes,
            quotesOfTweetId,
            replies,
            retweets,
            retweetsOfTweetId,
            safe,
            scope,
            sinceDate,
            sinceId,
            sinceTime,
            sort,
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
        "TweetGetRepliesParams{id=$id, anyWords=$anyWords, blueVerifiedOnly=$blueVerifiedOnly, cardName=$cardName, cashtags=$cashtags, conversationId=$conversationId, cursor=$cursor, exactPhrase=$exactPhrase, excludeOriginalAuthor=$excludeOriginalAuthor, excludeSource=$excludeSource, excludeWords=$excludeWords, fromUser=$fromUser, geocode=$geocode, hashtags=$hashtags, hasMediaOnly=$hasMediaOnly, includeOriginalPost=$includeOriginalPost, inReplyToTweetId=$inReplyToTweetId, language=$language, limit=$limit, maxDepth=$maxDepth, maxFaves=$maxFaves, maxId=$maxId, maxQuotes=$maxQuotes, maxReplies=$maxReplies, maxRetweets=$maxRetweets, mediaType=$mediaType, mentioning=$mentioning, minBookmarks=$minBookmarks, minFaves=$minFaves, minQuotes=$minQuotes, minReplies=$minReplies, minRetweets=$minRetweets, minViews=$minViews, mode=$mode, nativeRetweets=$nativeRetweets, near=$near, news=$news, pageSize=$pageSize, quotes=$quotes, quotesOfTweetId=$quotesOfTweetId, replies=$replies, retweets=$retweets, retweetsOfTweetId=$retweetsOfTweetId, safe=$safe, scope=$scope, sinceDate=$sinceDate, sinceId=$sinceId, sinceTime=$sinceTime, sort=$sort, source=$source, toUser=$toUser, untilDate=$untilDate, untilTime=$untilTime, url=$url, verifiedOnly=$verifiedOnly, within=$within, withinTime=$withinTime, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
