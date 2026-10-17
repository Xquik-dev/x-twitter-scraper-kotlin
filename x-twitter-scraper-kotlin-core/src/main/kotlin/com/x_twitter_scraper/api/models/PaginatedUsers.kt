// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models

import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.x_twitter_scraper.api.core.ExcludeMissing
import com.x_twitter_scraper.api.core.JsonField
import com.x_twitter_scraper.api.core.JsonMissing
import com.x_twitter_scraper.api.core.JsonValue
import com.x_twitter_scraper.api.core.checkKnown
import com.x_twitter_scraper.api.core.checkRequired
import com.x_twitter_scraper.api.core.toImmutable
import com.x_twitter_scraper.api.errors.XTwitterScraperInvalidDataException
import com.x_twitter_scraper.api.models.x.users.UserRetrieveFollowersResponse
import java.util.Collections
import java.util.Objects

/**
 * Profile coverage preserves shape, billing, aliases, and filters. Follow next_cursor while the
 * response reports more pages. Unprefixed cursors remain legacy.
 */
class PaginatedUsers
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val hasNextPage: JsonField<Boolean>,
    private val nextCursor: JsonField<String>,
    private val users: JsonField<List<UserProfile>>,
    private val filteredCount: JsonField<Long>,
    private val scanLimit: JsonField<ScanLimit>,
    private val diagnostic:
        JsonField<UserRetrieveFollowersResponse.UserListCoverageResponse.Diagnostic>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("has_next_page")
        @ExcludeMissing
        hasNextPage: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("next_cursor")
        @ExcludeMissing
        nextCursor: JsonField<String> = JsonMissing.of(),
        @JsonProperty("users")
        @ExcludeMissing
        users: JsonField<List<UserProfile>> = JsonMissing.of(),
        @JsonProperty("filtered_count")
        @ExcludeMissing
        filteredCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("scanLimit")
        @ExcludeMissing
        scanLimit: JsonField<ScanLimit> = JsonMissing.of(),
        @JsonProperty("diagnostic")
        @ExcludeMissing
        diagnostic: JsonField<UserRetrieveFollowersResponse.UserListCoverageResponse.Diagnostic> =
            JsonMissing.of(),
    ) : this(hasNextPage, nextCursor, users, filteredCount, scanLimit, diagnostic, mutableMapOf())

    /**
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun hasNextPage(): Boolean = hasNextPage.getRequired("has_next_page")

    /**
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun nextCursor(): String = nextCursor.getRequired("next_cursor")

    /**
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun users(): List<UserProfile> = users.getRequired("users")

    /**
     * Whole number from 0.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun filteredCount(): Long? = filteredCount.getNullable("filtered_count")

    /**
     * Returns the raw JSON value of [hasNextPage].
     *
     * Unlike [hasNextPage], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("has_next_page")
    @ExcludeMissing
    fun _hasNextPage(): JsonField<Boolean> = hasNextPage

    /**
     * Returns the raw JSON value of [nextCursor].
     *
     * Unlike [nextCursor], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("next_cursor") @ExcludeMissing fun _nextCursor(): JsonField<String> = nextCursor

    /**
     * Returns the raw JSON value of [users].
     *
     * Unlike [users], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("users") @ExcludeMissing fun _users(): JsonField<List<UserProfile>> = users

    /**
     * Returns the raw JSON value of [filteredCount].
     *
     * Unlike [filteredCount], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("filtered_count")
    @ExcludeMissing
    fun _filteredCount(): JsonField<Long> = filteredCount

    /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
    fun scanLimit(): ScanLimit? = scanLimit.getNullable("scanLimit")

    /**
     * Returns the raw JSON value of [scanLimit].
     *
     * Unlike [scanLimit], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("scanLimit") @ExcludeMissing fun _scanLimit(): JsonField<ScanLimit> = scanLimit

    /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
    fun diagnostic(): UserRetrieveFollowersResponse.UserListCoverageResponse.Diagnostic? =
        diagnostic.getNullable("diagnostic")

    /**
     * Returns the raw JSON value of [diagnostic].
     *
     * Unlike [diagnostic], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("diagnostic")
    @ExcludeMissing
    fun _diagnostic():
        JsonField<UserRetrieveFollowersResponse.UserListCoverageResponse.Diagnostic> = diagnostic

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
         * Returns a mutable builder for constructing an instance of [PaginatedUsers].
         *
         * The following fields are required:
         * ```kotlin
         * .hasNextPage()
         * .nextCursor()
         * .users()
         * ```
         */
        fun builder() = Builder()
    }

    /** A builder for [PaginatedUsers]. */
    class Builder internal constructor() {

        private var hasNextPage: JsonField<Boolean>? = null
        private var nextCursor: JsonField<String>? = null
        private var users: JsonField<MutableList<UserProfile>>? = null
        private var filteredCount: JsonField<Long> = JsonMissing.of()
        private var scanLimit: JsonField<ScanLimit> = JsonMissing.of()
        private var diagnostic:
            JsonField<UserRetrieveFollowersResponse.UserListCoverageResponse.Diagnostic> =
            JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        internal fun from(paginatedUsers: PaginatedUsers) = apply {
            hasNextPage = paginatedUsers.hasNextPage
            nextCursor = paginatedUsers.nextCursor
            users = paginatedUsers.users.map { it.toMutableList() }
            filteredCount = paginatedUsers.filteredCount
            scanLimit = paginatedUsers.scanLimit
            diagnostic = paginatedUsers.diagnostic
            additionalProperties = paginatedUsers.additionalProperties.toMutableMap()
        }

        fun hasNextPage(hasNextPage: Boolean) = hasNextPage(JsonField.of(hasNextPage))

        /** Sets [Builder.hasNextPage] to an arbitrary JSON value. */
        fun hasNextPage(hasNextPage: JsonField<Boolean>) = apply { this.hasNextPage = hasNextPage }

        fun nextCursor(nextCursor: String) = nextCursor(JsonField.of(nextCursor))

        /** Sets [Builder.nextCursor] to an arbitrary JSON value. */
        fun nextCursor(nextCursor: JsonField<String>) = apply { this.nextCursor = nextCursor }

        fun users(users: List<UserProfile>) = users(JsonField.of(users))

        /** Sets [Builder.users] to an arbitrary JSON value. */
        fun users(users: JsonField<List<UserProfile>>) = apply {
            this.users = users.map { it.toMutableList() }
        }

        /**
         * Adds a single [UserProfile] to [users].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addUser(user: UserProfile) = apply {
            users =
                (users ?: JsonField.of(mutableListOf())).also {
                    checkKnown("users", it).add(user)
                }
        }

        /** Whole number from 0. */
        fun filteredCount(filteredCount: Long) = filteredCount(JsonField.of(filteredCount))

        /** Sets [Builder.filteredCount] to an arbitrary JSON value. */
        fun filteredCount(filteredCount: JsonField<Long>) = apply {
            this.filteredCount = filteredCount
        }

        fun scanLimit(scanLimit: ScanLimit) = scanLimit(JsonField.of(scanLimit))

        /** Sets [Builder.scanLimit] to an arbitrary JSON value. */
        fun scanLimit(scanLimit: JsonField<ScanLimit>) = apply { this.scanLimit = scanLimit }

        fun diagnostic(
            diagnostic: UserRetrieveFollowersResponse.UserListCoverageResponse.Diagnostic
        ) = diagnostic(JsonField.of(diagnostic))

        /** Sets [Builder.diagnostic] to an arbitrary JSON value. */
        fun diagnostic(
            diagnostic: JsonField<UserRetrieveFollowersResponse.UserListCoverageResponse.Diagnostic>
        ) = apply { this.diagnostic = diagnostic }

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
         * Returns an immutable instance of [PaginatedUsers].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```kotlin
         * .hasNextPage()
         * .nextCursor()
         * .users()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): PaginatedUsers =
            PaginatedUsers(
                checkRequired("hasNextPage", hasNextPage),
                checkRequired("nextCursor", nextCursor),
                checkRequired("users", users).map { it.toImmutable() },
                filteredCount,
                scanLimit,
                diagnostic,
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
    fun validate(): PaginatedUsers = apply {
        if (validated) {
            return@apply
        }

        hasNextPage()
        nextCursor()
        users().forEach { it.validate() }
        filteredCount()
        scanLimit()?.validate()
        diagnostic()?.validate()
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
        (diagnostic.asKnown()?.validity() ?: 0) +
            (scanLimit.asKnown()?.validity() ?: 0) +
            (if (hasNextPage.asKnown() == null) 0 else 1) +
            (if (nextCursor.asKnown() == null) 0 else 1) +
            (users.asKnown()?.sumOf { it.validity() } ?: 0) +
            (if (filteredCount.asKnown() == null) 0 else 1)

    class ScanLimit
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val message: JsonField<String>,
        private val rowsRead: JsonField<Long>,
        private val limit: JsonField<Long>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("message") @ExcludeMissing message: JsonField<String> = JsonMissing.of(),
            @JsonProperty("rowsRead") @ExcludeMissing rowsRead: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("limit") @ExcludeMissing limit: JsonField<Long> = JsonMissing.of(),
        ) : this(message, rowsRead, limit, mutableMapOf())

        fun message(): String = message.getRequired("message")

        fun rowsRead(): Long = rowsRead.getRequired("rowsRead")

        @JsonProperty("message") @ExcludeMissing fun _message(): JsonField<String> = message

        @JsonProperty("rowsRead") @ExcludeMissing fun _rowsRead(): JsonField<Long> = rowsRead

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun limit(): Long = limit.getRequired("limit")

        /**
         * Returns the raw JSON value of [limit].
         *
         * Unlike [limit], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("limit") @ExcludeMissing fun _limit(): JsonField<Long> = limit

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

            fun builder() = Builder()
        }

        class Builder internal constructor() {

            private var message: JsonField<String>? = null
            private var rowsRead: JsonField<Long>? = null
            private var limit: JsonField<Long>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(scanLimit: ScanLimit) = apply {
                message = scanLimit.message
                rowsRead = scanLimit.rowsRead
                limit = scanLimit.limit
                additionalProperties = scanLimit.additionalProperties.toMutableMap()
            }

            fun message(message: String) = message(JsonField.of(message))

            fun message(message: JsonField<String>) = apply { this.message = message }

            fun rowsRead(rowsRead: Long) = rowsRead(JsonField.of(rowsRead))

            fun rowsRead(rowsRead: JsonField<Long>) = apply { this.rowsRead = rowsRead }

            fun limit(limit: Long) = limit(JsonField.of(limit))

            /** Sets [Builder.limit] to an arbitrary JSON value. */
            fun limit(limit: JsonField<Long>) = apply { this.limit = limit }

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

            fun build(): ScanLimit =
                ScanLimit(
                    checkRequired("message", message),
                    checkRequired("rowsRead", rowsRead),
                    checkRequired("limit", limit),
                    additionalProperties.toMutableMap(),
                )
        }

        private var validated: Boolean = false

        fun validate(): ScanLimit = apply {
            if (validated) {
                return@apply
            }

            message()
            rowsRead()
            limit()
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
            (if (limit.asKnown() == null) 0 else 1) +
                (if (message.asKnown() == null) 0 else 1) +
                (if (rowsRead.asKnown() == null) 0 else 1)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is ScanLimit &&
                message == other.message &&
                rowsRead == other.rowsRead &&
                limit == other.limit &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(message, rowsRead, limit, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "ScanLimit{message=$message, rowsRead=$rowsRead, limit=$limit, additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is PaginatedUsers &&
            hasNextPage == other.hasNextPage &&
            nextCursor == other.nextCursor &&
            users == other.users &&
            filteredCount == other.filteredCount &&
            scanLimit == other.scanLimit &&
            diagnostic == other.diagnostic &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            hasNextPage,
            nextCursor,
            users,
            filteredCount,
            scanLimit,
            diagnostic,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "PaginatedUsers{hasNextPage=$hasNextPage, nextCursor=$nextCursor, users=$users, filteredCount=$filteredCount, scanLimit=$scanLimit, diagnostic=$diagnostic, additionalProperties=$additionalProperties}"
}
