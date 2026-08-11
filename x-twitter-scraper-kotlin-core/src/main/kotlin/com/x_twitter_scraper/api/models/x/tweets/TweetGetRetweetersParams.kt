// File generated from our OpenAPI spec by Stainless.

package com.x_twitter_scraper.api.models.x.tweets

import com.x_twitter_scraper.api.core.Params
import com.x_twitter_scraper.api.core.http.Headers
import com.x_twitter_scraper.api.core.http.QueryParams
import java.util.Objects

/** List users who retweeted a tweet */
class TweetGetRetweetersParams
private constructor(
    private val id: String?,
    private val bioContains: String?,
    private val cursor: String?,
    private val hasLocation: Boolean?,
    private val hasWebsite: Boolean?,
    private val locationContains: String?,
    private val maxFollowers: Long?,
    private val maxFollowing: Long?,
    private val maxStatuses: Long?,
    private val minAccountAgeDays: Long?,
    private val minFollowers: Long?,
    private val minFollowing: Long?,
    private val minStatuses: Long?,
    private val pageSize: Long?,
    private val usernameContains: String?,
    private val verifiedOnly: Boolean?,
    private val verifiedType: String?,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    fun id(): String? = id

    /** Match any comma-separated or line-separated bio term, ignoring case. */
    fun bioContains(): String? = bioContains

    /** Pagination cursor for retweeters */
    fun cursor(): String? = cursor

    /** Only return profiles with a location. */
    fun hasLocation(): Boolean? = hasLocation

    /** Only return profiles with a website. */
    fun hasWebsite(): Boolean? = hasWebsite

    /** Match a location substring, ignoring case. */
    fun locationContains(): String? = locationContains

    /** Maximum follower count. Missing counts pass this maximum. */
    fun maxFollowers(): Long? = maxFollowers

    /** Maximum following count. */
    fun maxFollowing(): Long? = maxFollowing

    /** Maximum post count. maxPosts is also accepted. */
    fun maxStatuses(): Long? = maxStatuses

    /** Minimum account age in whole days. */
    fun minAccountAgeDays(): Long? = minAccountAgeDays

    /** Minimum follower count. Filtering happens before billing. */
    fun minFollowers(): Long? = minFollowers

    /** Minimum following count. */
    fun minFollowing(): Long? = minFollowing

    /** Minimum post count. minPosts is also accepted. */
    fun minStatuses(): Long? = minStatuses

    /**
     * Maximum user profiles requested from this page (20-200, default 200). Source, filters, or
     * credits can return fewer profiles. Keep requesting next_cursor while has_next_page is true.
     * Deprecated aliases remain accepted.
     */
    fun pageSize(): Long? = pageSize

    /** Match a username substring, ignoring case. */
    fun usernameContains(): String? = usernameContains

    /** Only return verified profiles. */
    fun verifiedOnly(): Boolean? = verifiedOnly

    /** Match the verification type exactly, ignoring case. */
    fun verifiedType(): String? = verifiedType

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        fun none(): TweetGetRetweetersParams = builder().build()

        /** Returns a mutable builder for constructing an instance of [TweetGetRetweetersParams]. */
        fun builder() = Builder()
    }

    /** A builder for [TweetGetRetweetersParams]. */
    class Builder internal constructor() {

        private var id: String? = null
        private var bioContains: String? = null
        private var cursor: String? = null
        private var hasLocation: Boolean? = null
        private var hasWebsite: Boolean? = null
        private var locationContains: String? = null
        private var maxFollowers: Long? = null
        private var maxFollowing: Long? = null
        private var maxStatuses: Long? = null
        private var minAccountAgeDays: Long? = null
        private var minFollowers: Long? = null
        private var minFollowing: Long? = null
        private var minStatuses: Long? = null
        private var pageSize: Long? = null
        private var usernameContains: String? = null
        private var verifiedOnly: Boolean? = null
        private var verifiedType: String? = null
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        internal fun from(tweetGetRetweetersParams: TweetGetRetweetersParams) = apply {
            id = tweetGetRetweetersParams.id
            bioContains = tweetGetRetweetersParams.bioContains
            cursor = tweetGetRetweetersParams.cursor
            hasLocation = tweetGetRetweetersParams.hasLocation
            hasWebsite = tweetGetRetweetersParams.hasWebsite
            locationContains = tweetGetRetweetersParams.locationContains
            maxFollowers = tweetGetRetweetersParams.maxFollowers
            maxFollowing = tweetGetRetweetersParams.maxFollowing
            maxStatuses = tweetGetRetweetersParams.maxStatuses
            minAccountAgeDays = tweetGetRetweetersParams.minAccountAgeDays
            minFollowers = tweetGetRetweetersParams.minFollowers
            minFollowing = tweetGetRetweetersParams.minFollowing
            minStatuses = tweetGetRetweetersParams.minStatuses
            pageSize = tweetGetRetweetersParams.pageSize
            usernameContains = tweetGetRetweetersParams.usernameContains
            verifiedOnly = tweetGetRetweetersParams.verifiedOnly
            verifiedType = tweetGetRetweetersParams.verifiedType
            additionalHeaders = tweetGetRetweetersParams.additionalHeaders.toBuilder()
            additionalQueryParams = tweetGetRetweetersParams.additionalQueryParams.toBuilder()
        }

        fun id(id: String?) = apply { this.id = id }

        /** Match any comma-separated or line-separated bio term, ignoring case. */
        fun bioContains(bioContains: String?) = apply { this.bioContains = bioContains }

        /** Pagination cursor for retweeters */
        fun cursor(cursor: String?) = apply { this.cursor = cursor }

        /** Only return profiles with a location. */
        fun hasLocation(hasLocation: Boolean?) = apply { this.hasLocation = hasLocation }

        /**
         * Alias for [Builder.hasLocation].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun hasLocation(hasLocation: Boolean) = hasLocation(hasLocation as Boolean?)

        /** Only return profiles with a website. */
        fun hasWebsite(hasWebsite: Boolean?) = apply { this.hasWebsite = hasWebsite }

        /**
         * Alias for [Builder.hasWebsite].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun hasWebsite(hasWebsite: Boolean) = hasWebsite(hasWebsite as Boolean?)

        /** Match a location substring, ignoring case. */
        fun locationContains(locationContains: String?) = apply {
            this.locationContains = locationContains
        }

        /** Maximum follower count. Missing counts pass this maximum. */
        fun maxFollowers(maxFollowers: Long?) = apply { this.maxFollowers = maxFollowers }

        /**
         * Alias for [Builder.maxFollowers].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun maxFollowers(maxFollowers: Long) = maxFollowers(maxFollowers as Long?)

        /** Maximum following count. */
        fun maxFollowing(maxFollowing: Long?) = apply { this.maxFollowing = maxFollowing }

        /**
         * Alias for [Builder.maxFollowing].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun maxFollowing(maxFollowing: Long) = maxFollowing(maxFollowing as Long?)

        /** Maximum post count. maxPosts is also accepted. */
        fun maxStatuses(maxStatuses: Long?) = apply { this.maxStatuses = maxStatuses }

        /**
         * Alias for [Builder.maxStatuses].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun maxStatuses(maxStatuses: Long) = maxStatuses(maxStatuses as Long?)

        /** Minimum account age in whole days. */
        fun minAccountAgeDays(minAccountAgeDays: Long?) = apply {
            this.minAccountAgeDays = minAccountAgeDays
        }

        /**
         * Alias for [Builder.minAccountAgeDays].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun minAccountAgeDays(minAccountAgeDays: Long) =
            minAccountAgeDays(minAccountAgeDays as Long?)

        /** Minimum follower count. Filtering happens before billing. */
        fun minFollowers(minFollowers: Long?) = apply { this.minFollowers = minFollowers }

        /**
         * Alias for [Builder.minFollowers].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun minFollowers(minFollowers: Long) = minFollowers(minFollowers as Long?)

        /** Minimum following count. */
        fun minFollowing(minFollowing: Long?) = apply { this.minFollowing = minFollowing }

        /**
         * Alias for [Builder.minFollowing].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun minFollowing(minFollowing: Long) = minFollowing(minFollowing as Long?)

        /** Minimum post count. minPosts is also accepted. */
        fun minStatuses(minStatuses: Long?) = apply { this.minStatuses = minStatuses }

        /**
         * Alias for [Builder.minStatuses].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun minStatuses(minStatuses: Long) = minStatuses(minStatuses as Long?)

        /**
         * Maximum user profiles requested from this page (20-200, default 200). Source, filters, or
         * credits can return fewer profiles. Keep requesting next_cursor while has_next_page is
         * true. Deprecated aliases remain accepted.
         */
        fun pageSize(pageSize: Long?) = apply { this.pageSize = pageSize }

        /**
         * Alias for [Builder.pageSize].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun pageSize(pageSize: Long) = pageSize(pageSize as Long?)

        /** Match a username substring, ignoring case. */
        fun usernameContains(usernameContains: String?) = apply {
            this.usernameContains = usernameContains
        }

        /** Only return verified profiles. */
        fun verifiedOnly(verifiedOnly: Boolean?) = apply { this.verifiedOnly = verifiedOnly }

        /**
         * Alias for [Builder.verifiedOnly].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun verifiedOnly(verifiedOnly: Boolean) = verifiedOnly(verifiedOnly as Boolean?)

        /** Match the verification type exactly, ignoring case. */
        fun verifiedType(verifiedType: String?) = apply { this.verifiedType = verifiedType }

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
         * Returns an immutable instance of [TweetGetRetweetersParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): TweetGetRetweetersParams =
            TweetGetRetweetersParams(
                id,
                bioContains,
                cursor,
                hasLocation,
                hasWebsite,
                locationContains,
                maxFollowers,
                maxFollowing,
                maxStatuses,
                minAccountAgeDays,
                minFollowers,
                minFollowing,
                minStatuses,
                pageSize,
                usernameContains,
                verifiedOnly,
                verifiedType,
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
                bioContains?.let { put("bioContains", it) }
                cursor?.let { put("cursor", it) }
                hasLocation?.let { put("hasLocation", it.toString()) }
                hasWebsite?.let { put("hasWebsite", it.toString()) }
                locationContains?.let { put("locationContains", it) }
                maxFollowers?.let { put("maxFollowers", it.toString()) }
                maxFollowing?.let { put("maxFollowing", it.toString()) }
                maxStatuses?.let { put("maxStatuses", it.toString()) }
                minAccountAgeDays?.let { put("minAccountAgeDays", it.toString()) }
                minFollowers?.let { put("minFollowers", it.toString()) }
                minFollowing?.let { put("minFollowing", it.toString()) }
                minStatuses?.let { put("minStatuses", it.toString()) }
                pageSize?.let { put("pageSize", it.toString()) }
                usernameContains?.let { put("usernameContains", it) }
                verifiedOnly?.let { put("verifiedOnly", it.toString()) }
                verifiedType?.let { put("verifiedType", it) }
                putAll(additionalQueryParams)
            }
            .build()

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is TweetGetRetweetersParams &&
            id == other.id &&
            bioContains == other.bioContains &&
            cursor == other.cursor &&
            hasLocation == other.hasLocation &&
            hasWebsite == other.hasWebsite &&
            locationContains == other.locationContains &&
            maxFollowers == other.maxFollowers &&
            maxFollowing == other.maxFollowing &&
            maxStatuses == other.maxStatuses &&
            minAccountAgeDays == other.minAccountAgeDays &&
            minFollowers == other.minFollowers &&
            minFollowing == other.minFollowing &&
            minStatuses == other.minStatuses &&
            pageSize == other.pageSize &&
            usernameContains == other.usernameContains &&
            verifiedOnly == other.verifiedOnly &&
            verifiedType == other.verifiedType &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(
            id,
            bioContains,
            cursor,
            hasLocation,
            hasWebsite,
            locationContains,
            maxFollowers,
            maxFollowing,
            maxStatuses,
            minAccountAgeDays,
            minFollowers,
            minFollowing,
            minStatuses,
            pageSize,
            usernameContains,
            verifiedOnly,
            verifiedType,
            additionalHeaders,
            additionalQueryParams,
        )

    override fun toString() =
        "TweetGetRetweetersParams{id=$id, bioContains=$bioContains, cursor=$cursor, hasLocation=$hasLocation, hasWebsite=$hasWebsite, locationContains=$locationContains, maxFollowers=$maxFollowers, maxFollowing=$maxFollowing, maxStatuses=$maxStatuses, minAccountAgeDays=$minAccountAgeDays, minFollowers=$minFollowers, minFollowing=$minFollowing, minStatuses=$minStatuses, pageSize=$pageSize, usernameContains=$usernameContains, verifiedOnly=$verifiedOnly, verifiedType=$verifiedType, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
