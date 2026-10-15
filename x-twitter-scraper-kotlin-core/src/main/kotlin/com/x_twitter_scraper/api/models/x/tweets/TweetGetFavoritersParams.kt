// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.tweets

import com.x_twitter_scraper.api.core.Params
import com.x_twitter_scraper.api.core.http.Headers
import com.x_twitter_scraper.api.core.http.QueryParams
import java.util.Objects

/**
 * Returns liker profiles that X makes visible for the post. X can withhold liker identities even
 * when the post reports likes. In that case this endpoint returns 424 `favoriters_unavailable`
 * instead of a misleading empty success.
 */
class TweetGetFavoritersParams
private constructor(
    private val id: String?,
    private val bioContains: String?,
    private val blueVerifiedOnly: Boolean?,
    private val businessEmailOnly: Boolean?,
    private val cursor: String?,
    private val emailDomains: String?,
    private val fields: String?,
    private val hasEmail: Boolean?,
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
    private val omit: String?,
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

    /**
     * Only return profiles X marks as blue verified. X marks many business & government accounts
     * blue too. Use verifiedType for 1 badge type.
     */
    fun blueVerifiedOnly(): Boolean? = blueVerifiedOnly

    /** Only return profiles that publish an email outside free webmail, such as Gmail. */
    fun businessEmailOnly(): Boolean? = businessEmailOnly

    /**
     * Pagination cursor for collection results. Also read as next_cursor, next_token,
     * pagination_token or continuation_token.
     */
    fun cursor(): String? = cursor

    /**
     * Only return profiles with an email at one of these domains. Separate domains with commas.
     * Subdomains match.
     */
    fun emailDomains(): String? = emailDomains

    /**
     * Fields to keep in each row, comma-separated. Use dots for nested fields, such as
     * author.username. A list keeps the field in each item. Case & underscores do not matter. A row
     * that lacks a field leaves it out. Cursors, counts & diagnostics always stay. An unknown name
     * answers 400 unknown_field with the valid names, before any read. The price stays the same.
     * SDK row types then hold only the named fields.
     */
    fun fields(): String? = fields

    /** Only return profiles that publish an email. */
    fun hasEmail(): Boolean? = hasEmail

    /** Only return profiles with a location. */
    fun hasLocation(): Boolean? = hasLocation

    /** Only return profiles with a website. */
    fun hasWebsite(): Boolean? = hasWebsite

    /** Match a location substring, ignoring case. */
    fun locationContains(): String? = locationContains

    /** Maximum follower count. A profile without a count passes. */
    fun maxFollowers(): Long? = maxFollowers

    /** Profiles may follow at most this many accounts. */
    fun maxFollowing(): Long? = maxFollowing

    /** Maximum post count. maxPosts is also accepted. */
    fun maxStatuses(): Long? = maxStatuses

    /** Minimum account age in whole days. */
    fun minAccountAgeDays(): Long? = minAccountAgeDays

    /** Minimum follower count. Profiles the filter drops cost nothing. */
    fun minFollowers(): Long? = minFollowers

    /** Profiles must follow at least this many accounts. */
    fun minFollowing(): Long? = minFollowing

    /** Minimum post count. minPosts is also accepted. */
    fun minStatuses(): Long? = minStatuses

    /**
     * Fields to drop from each row, comma-separated. It takes the same names as fields. With
     * fields, it drops from the kept fields.
     */
    fun omit(): String? = omit

    /**
     * Profiles to request from this page (1-200, default 200). Source, filters or credits can
     * return fewer. Follow next_cursor for more pages. Its aliases are limit, count, max_results,
     * maxItems, max_items & per_page.
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

        fun none(): TweetGetFavoritersParams = builder().build()

        /** Returns a mutable builder for constructing an instance of [TweetGetFavoritersParams]. */
        fun builder() = Builder()
    }

    /** A builder for [TweetGetFavoritersParams]. */
    class Builder internal constructor() {

        private var id: String? = null
        private var bioContains: String? = null
        private var blueVerifiedOnly: Boolean? = null
        private var businessEmailOnly: Boolean? = null
        private var cursor: String? = null
        private var emailDomains: String? = null
        private var fields: String? = null
        private var hasEmail: Boolean? = null
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
        private var omit: String? = null
        private var pageSize: Long? = null
        private var usernameContains: String? = null
        private var verifiedOnly: Boolean? = null
        private var verifiedType: String? = null
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        internal fun from(tweetGetFavoritersParams: TweetGetFavoritersParams) = apply {
            id = tweetGetFavoritersParams.id
            bioContains = tweetGetFavoritersParams.bioContains
            blueVerifiedOnly = tweetGetFavoritersParams.blueVerifiedOnly
            businessEmailOnly = tweetGetFavoritersParams.businessEmailOnly
            cursor = tweetGetFavoritersParams.cursor
            emailDomains = tweetGetFavoritersParams.emailDomains
            fields = tweetGetFavoritersParams.fields
            hasEmail = tweetGetFavoritersParams.hasEmail
            hasLocation = tweetGetFavoritersParams.hasLocation
            hasWebsite = tweetGetFavoritersParams.hasWebsite
            locationContains = tweetGetFavoritersParams.locationContains
            maxFollowers = tweetGetFavoritersParams.maxFollowers
            maxFollowing = tweetGetFavoritersParams.maxFollowing
            maxStatuses = tweetGetFavoritersParams.maxStatuses
            minAccountAgeDays = tweetGetFavoritersParams.minAccountAgeDays
            minFollowers = tweetGetFavoritersParams.minFollowers
            minFollowing = tweetGetFavoritersParams.minFollowing
            minStatuses = tweetGetFavoritersParams.minStatuses
            omit = tweetGetFavoritersParams.omit
            pageSize = tweetGetFavoritersParams.pageSize
            usernameContains = tweetGetFavoritersParams.usernameContains
            verifiedOnly = tweetGetFavoritersParams.verifiedOnly
            verifiedType = tweetGetFavoritersParams.verifiedType
            additionalHeaders = tweetGetFavoritersParams.additionalHeaders.toBuilder()
            additionalQueryParams = tweetGetFavoritersParams.additionalQueryParams.toBuilder()
        }

        fun id(id: String?) = apply { this.id = id }

        /** Match any comma-separated or line-separated bio term, ignoring case. */
        fun bioContains(bioContains: String?) = apply { this.bioContains = bioContains }

        /**
         * Only return profiles X marks as blue verified. X marks many business & government
         * accounts blue too. Use verifiedType for 1 badge type.
         */
        fun blueVerifiedOnly(blueVerifiedOnly: Boolean?) = apply {
            this.blueVerifiedOnly = blueVerifiedOnly
        }

        /** Alias for [Builder.blueVerifiedOnly]. */
        fun blueVerifiedOnly(blueVerifiedOnly: Boolean) =
            blueVerifiedOnly(blueVerifiedOnly as Boolean?)

        /** Only return profiles that publish an email outside free webmail, such as Gmail. */
        fun businessEmailOnly(businessEmailOnly: Boolean?) = apply {
            this.businessEmailOnly = businessEmailOnly
        }

        /** Alias for [Builder.businessEmailOnly]. */
        fun businessEmailOnly(businessEmailOnly: Boolean) =
            businessEmailOnly(businessEmailOnly as Boolean?)

        /**
         * Pagination cursor for collection results. Also read as next_cursor, next_token,
         * pagination_token or continuation_token.
         */
        fun cursor(cursor: String?) = apply { this.cursor = cursor }

        /**
         * Only return profiles with an email at one of these domains. Separate domains with commas.
         * Subdomains match.
         */
        fun emailDomains(emailDomains: String?) = apply { this.emailDomains = emailDomains }

        /**
         * Fields to keep in each row, comma-separated. Use dots for nested fields, such as
         * author.username. A list keeps the field in each item. Case & underscores do not matter. A
         * row that lacks a field leaves it out. Cursors, counts & diagnostics always stay. An
         * unknown name answers 400 unknown_field with the valid names, before any read. The price
         * stays the same. SDK row types then hold only the named fields.
         */
        fun fields(fields: String?) = apply { this.fields = fields }

        /** Only return profiles that publish an email. */
        fun hasEmail(hasEmail: Boolean?) = apply { this.hasEmail = hasEmail }

        /** Alias for [Builder.hasEmail]. */
        fun hasEmail(hasEmail: Boolean) = hasEmail(hasEmail as Boolean?)

        /** Only return profiles with a location. */
        fun hasLocation(hasLocation: Boolean?) = apply { this.hasLocation = hasLocation }

        /** Alias for [Builder.hasLocation]. */
        fun hasLocation(hasLocation: Boolean) = hasLocation(hasLocation as Boolean?)

        /** Only return profiles with a website. */
        fun hasWebsite(hasWebsite: Boolean?) = apply { this.hasWebsite = hasWebsite }

        /** Alias for [Builder.hasWebsite]. */
        fun hasWebsite(hasWebsite: Boolean) = hasWebsite(hasWebsite as Boolean?)

        /** Match a location substring, ignoring case. */
        fun locationContains(locationContains: String?) = apply {
            this.locationContains = locationContains
        }

        /** Maximum follower count. A profile without a count passes. */
        fun maxFollowers(maxFollowers: Long?) = apply { this.maxFollowers = maxFollowers }

        /** Alias for [Builder.maxFollowers]. */
        fun maxFollowers(maxFollowers: Long) = maxFollowers(maxFollowers as Long?)

        /** Profiles may follow at most this many accounts. */
        fun maxFollowing(maxFollowing: Long?) = apply { this.maxFollowing = maxFollowing }

        /** Alias for [Builder.maxFollowing]. */
        fun maxFollowing(maxFollowing: Long) = maxFollowing(maxFollowing as Long?)

        /** Maximum post count. maxPosts is also accepted. */
        fun maxStatuses(maxStatuses: Long?) = apply { this.maxStatuses = maxStatuses }

        /** Alias for [Builder.maxStatuses]. */
        fun maxStatuses(maxStatuses: Long) = maxStatuses(maxStatuses as Long?)

        /** Minimum account age in whole days. */
        fun minAccountAgeDays(minAccountAgeDays: Long?) = apply {
            this.minAccountAgeDays = minAccountAgeDays
        }

        /** Alias for [Builder.minAccountAgeDays]. */
        fun minAccountAgeDays(minAccountAgeDays: Long) =
            minAccountAgeDays(minAccountAgeDays as Long?)

        /** Minimum follower count. Profiles the filter drops cost nothing. */
        fun minFollowers(minFollowers: Long?) = apply { this.minFollowers = minFollowers }

        /** Alias for [Builder.minFollowers]. */
        fun minFollowers(minFollowers: Long) = minFollowers(minFollowers as Long?)

        /** Profiles must follow at least this many accounts. */
        fun minFollowing(minFollowing: Long?) = apply { this.minFollowing = minFollowing }

        /** Alias for [Builder.minFollowing]. */
        fun minFollowing(minFollowing: Long) = minFollowing(minFollowing as Long?)

        /** Minimum post count. minPosts is also accepted. */
        fun minStatuses(minStatuses: Long?) = apply { this.minStatuses = minStatuses }

        /** Alias for [Builder.minStatuses]. */
        fun minStatuses(minStatuses: Long) = minStatuses(minStatuses as Long?)

        /**
         * Fields to drop from each row, comma-separated. It takes the same names as fields. With
         * fields, it drops from the kept fields.
         */
        fun omit(omit: String?) = apply { this.omit = omit }

        /**
         * Profiles to request from this page (1-200, default 200). Source, filters or credits can
         * return fewer. Follow next_cursor for more pages. Its aliases are limit, count,
         * max_results, maxItems, max_items & per_page.
         */
        fun pageSize(pageSize: Long?) = apply { this.pageSize = pageSize }

        /** Alias for [Builder.pageSize]. */
        fun pageSize(pageSize: Long) = pageSize(pageSize as Long?)

        /** Match a username substring, ignoring case. */
        fun usernameContains(usernameContains: String?) = apply {
            this.usernameContains = usernameContains
        }

        /** Only return verified profiles. */
        fun verifiedOnly(verifiedOnly: Boolean?) = apply { this.verifiedOnly = verifiedOnly }

        /** Alias for [Builder.verifiedOnly]. */
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
         * Returns an immutable instance of [TweetGetFavoritersParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): TweetGetFavoritersParams =
            TweetGetFavoritersParams(
                id,
                bioContains,
                blueVerifiedOnly,
                businessEmailOnly,
                cursor,
                emailDomains,
                fields,
                hasEmail,
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
                omit,
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
                blueVerifiedOnly?.let { put("blueVerifiedOnly", it.toString()) }
                businessEmailOnly?.let { put("businessEmailOnly", it.toString()) }
                cursor?.let { put("cursor", it) }
                emailDomains?.let { put("emailDomains", it) }
                fields?.let { put("fields", it) }
                hasEmail?.let { put("hasEmail", it.toString()) }
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
                omit?.let { put("omit", it) }
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

        return other is TweetGetFavoritersParams &&
            id == other.id &&
            bioContains == other.bioContains &&
            blueVerifiedOnly == other.blueVerifiedOnly &&
            businessEmailOnly == other.businessEmailOnly &&
            cursor == other.cursor &&
            emailDomains == other.emailDomains &&
            fields == other.fields &&
            hasEmail == other.hasEmail &&
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
            omit == other.omit &&
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
            blueVerifiedOnly,
            businessEmailOnly,
            cursor,
            emailDomains,
            fields,
            hasEmail,
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
            omit,
            pageSize,
            usernameContains,
            verifiedOnly,
            verifiedType,
            additionalHeaders,
            additionalQueryParams,
        )

    override fun toString() =
        "TweetGetFavoritersParams{id=$id, bioContains=$bioContains, blueVerifiedOnly=$blueVerifiedOnly, businessEmailOnly=$businessEmailOnly, cursor=$cursor, emailDomains=$emailDomains, fields=$fields, hasEmail=$hasEmail, hasLocation=$hasLocation, hasWebsite=$hasWebsite, locationContains=$locationContains, maxFollowers=$maxFollowers, maxFollowing=$maxFollowing, maxStatuses=$maxStatuses, minAccountAgeDays=$minAccountAgeDays, minFollowers=$minFollowers, minFollowing=$minFollowing, minStatuses=$minStatuses, omit=$omit, pageSize=$pageSize, usernameContains=$usernameContains, verifiedOnly=$verifiedOnly, verifiedType=$verifiedType, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
