// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.extractions

import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.x_twitter_scraper.api.core.Enum
import com.x_twitter_scraper.api.core.ExcludeMissing
import com.x_twitter_scraper.api.core.JsonField
import com.x_twitter_scraper.api.core.JsonMissing
import com.x_twitter_scraper.api.core.JsonValue
import com.x_twitter_scraper.api.core.checkRequired
import com.x_twitter_scraper.api.errors.XTwitterScraperInvalidDataException
import java.util.Collections
import java.util.Objects

class ExtractionRunResponse
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val allowed: JsonField<Boolean>,
    private val creditsAvailable: JsonField<String>,
    private val creditsRequired: JsonField<String>,
    private val estimatedResults: JsonField<Long>,
    private val source: JsonField<String>,
    private val id: JsonField<String>,
    private val pollAfterMs: JsonField<Long>,
    private val resolvedXUserId: JsonField<String>,
    private val status: JsonField<Status>,
    private val statusUrl: JsonField<String>,
    private val toolType: JsonField<ToolType>,
    private val waitUrl: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("allowed") @ExcludeMissing allowed: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("creditsAvailable")
        @ExcludeMissing
        creditsAvailable: JsonField<String> = JsonMissing.of(),
        @JsonProperty("creditsRequired")
        @ExcludeMissing
        creditsRequired: JsonField<String> = JsonMissing.of(),
        @JsonProperty("estimatedResults")
        @ExcludeMissing
        estimatedResults: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("source") @ExcludeMissing source: JsonField<String> = JsonMissing.of(),
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("pollAfterMs")
        @ExcludeMissing
        pollAfterMs: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("resolvedXUserId")
        @ExcludeMissing
        resolvedXUserId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("status") @ExcludeMissing status: JsonField<Status> = JsonMissing.of(),
        @JsonProperty("statusUrl") @ExcludeMissing statusUrl: JsonField<String> = JsonMissing.of(),
        @JsonProperty("toolType") @ExcludeMissing toolType: JsonField<ToolType> = JsonMissing.of(),
        @JsonProperty("waitUrl") @ExcludeMissing waitUrl: JsonField<String> = JsonMissing.of(),
    ) : this(
        allowed,
        creditsAvailable,
        creditsRequired,
        estimatedResults,
        source,
        id,
        pollAfterMs,
        resolvedXUserId,
        status,
        statusUrl,
        toolType,
        waitUrl,
        mutableMapOf(),
    )

    /**
     * Whether the balance covers the full estimate.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun allowed(): Boolean = allowed.getRequired("allowed")

    /**
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun creditsAvailable(): String = creditsAvailable.getRequired("creditsAvailable")

    /**
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun creditsRequired(): String = creditsRequired.getRequired("creditsRequired")

    /**
     * The row count the credit estimate uses. It does not say how many rows exist.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun estimatedResults(): Long = estimatedResults.getRequired("estimatedResults")

    /**
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun source(): String = source.getRequired("source")

    /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
    fun id(): String? = id.getNullable("id")

    /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
    fun pollAfterMs(): Long? = pollAfterMs.getNullable("pollAfterMs")

    /**
     * Resolved X user ID from count-based profile estimates.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun resolvedXUserId(): String? = resolvedXUserId.getNullable("resolvedXUserId")

    /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
    fun status(): Status? = status.getNullable("status")

    /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
    fun statusUrl(): String? = statusUrl.getNullable("statusUrl")

    /**
     * Identifier for the extraction tool used to run a job.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun toolType(): ToolType? = toolType.getNullable("toolType")

    /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
    fun waitUrl(): String? = waitUrl.getNullable("waitUrl")

    /**
     * Returns the raw JSON value of [allowed].
     *
     * Unlike [allowed], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("allowed") @ExcludeMissing fun _allowed(): JsonField<Boolean> = allowed

    /**
     * Returns the raw JSON value of [creditsAvailable].
     *
     * Unlike [creditsAvailable], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("creditsAvailable")
    @ExcludeMissing
    fun _creditsAvailable(): JsonField<String> = creditsAvailable

    /**
     * Returns the raw JSON value of [creditsRequired].
     *
     * Unlike [creditsRequired], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("creditsRequired")
    @ExcludeMissing
    fun _creditsRequired(): JsonField<String> = creditsRequired

    /**
     * Returns the raw JSON value of [estimatedResults].
     *
     * Unlike [estimatedResults], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("estimatedResults")
    @ExcludeMissing
    fun _estimatedResults(): JsonField<Long> = estimatedResults

    /**
     * Returns the raw JSON value of [source].
     *
     * Unlike [source], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("source") @ExcludeMissing fun _source(): JsonField<String> = source

    /**
     * Returns the raw JSON value of [id].
     *
     * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    /**
     * Returns the raw JSON value of [pollAfterMs].
     *
     * Unlike [pollAfterMs], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("pollAfterMs") @ExcludeMissing fun _pollAfterMs(): JsonField<Long> = pollAfterMs

    /**
     * Returns the raw JSON value of [resolvedXUserId].
     *
     * Unlike [resolvedXUserId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("resolvedXUserId")
    @ExcludeMissing
    fun _resolvedXUserId(): JsonField<String> = resolvedXUserId

    /**
     * Returns the raw JSON value of [status].
     *
     * Unlike [status], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("status") @ExcludeMissing fun _status(): JsonField<Status> = status

    /**
     * Returns the raw JSON value of [statusUrl].
     *
     * Unlike [statusUrl], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("statusUrl") @ExcludeMissing fun _statusUrl(): JsonField<String> = statusUrl

    /**
     * Returns the raw JSON value of [toolType].
     *
     * Unlike [toolType], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("toolType") @ExcludeMissing fun _toolType(): JsonField<ToolType> = toolType

    /**
     * Returns the raw JSON value of [waitUrl].
     *
     * Unlike [waitUrl], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("waitUrl") @ExcludeMissing fun _waitUrl(): JsonField<String> = waitUrl

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
         * Returns a mutable builder for constructing an instance of [ExtractionRunResponse].
         *
         * The following fields are required:
         * ```kotlin
         * .allowed()
         * .creditsAvailable()
         * .creditsRequired()
         * .estimatedResults()
         * .source()
         * ```
         */
        fun builder() = Builder()
    }

    /** A builder for [ExtractionRunResponse]. */
    class Builder internal constructor() {

        private var allowed: JsonField<Boolean>? = null
        private var creditsAvailable: JsonField<String>? = null
        private var creditsRequired: JsonField<String>? = null
        private var estimatedResults: JsonField<Long>? = null
        private var source: JsonField<String>? = null
        private var id: JsonField<String> = JsonMissing.of()
        private var pollAfterMs: JsonField<Long> = JsonMissing.of()
        private var resolvedXUserId: JsonField<String> = JsonMissing.of()
        private var status: JsonField<Status> = JsonMissing.of()
        private var statusUrl: JsonField<String> = JsonMissing.of()
        private var toolType: JsonField<ToolType> = JsonMissing.of()
        private var waitUrl: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        internal fun from(extractionRunResponse: ExtractionRunResponse) = apply {
            allowed = extractionRunResponse.allowed
            creditsAvailable = extractionRunResponse.creditsAvailable
            creditsRequired = extractionRunResponse.creditsRequired
            estimatedResults = extractionRunResponse.estimatedResults
            source = extractionRunResponse.source
            id = extractionRunResponse.id
            pollAfterMs = extractionRunResponse.pollAfterMs
            resolvedXUserId = extractionRunResponse.resolvedXUserId
            status = extractionRunResponse.status
            statusUrl = extractionRunResponse.statusUrl
            toolType = extractionRunResponse.toolType
            waitUrl = extractionRunResponse.waitUrl
            additionalProperties = extractionRunResponse.additionalProperties.toMutableMap()
        }

        /** Whether the balance covers the full estimate. */
        fun allowed(allowed: Boolean) = allowed(JsonField.of(allowed))

        /** Sets [Builder.allowed] to an arbitrary JSON value. */
        fun allowed(allowed: JsonField<Boolean>) = apply { this.allowed = allowed }

        fun creditsAvailable(creditsAvailable: String) =
            creditsAvailable(JsonField.of(creditsAvailable))

        /** Sets [Builder.creditsAvailable] to an arbitrary JSON value. */
        fun creditsAvailable(creditsAvailable: JsonField<String>) = apply {
            this.creditsAvailable = creditsAvailable
        }

        fun creditsRequired(creditsRequired: String) =
            creditsRequired(JsonField.of(creditsRequired))

        /** Sets [Builder.creditsRequired] to an arbitrary JSON value. */
        fun creditsRequired(creditsRequired: JsonField<String>) = apply {
            this.creditsRequired = creditsRequired
        }

        /** The row count the credit estimate uses. It does not say how many rows exist. */
        fun estimatedResults(estimatedResults: Long) =
            estimatedResults(JsonField.of(estimatedResults))

        /** Sets [Builder.estimatedResults] to an arbitrary JSON value. */
        fun estimatedResults(estimatedResults: JsonField<Long>) = apply {
            this.estimatedResults = estimatedResults
        }

        fun source(source: String) = source(JsonField.of(source))

        /** Sets [Builder.source] to an arbitrary JSON value. */
        fun source(source: JsonField<String>) = apply { this.source = source }

        fun id(id: String) = id(JsonField.of(id))

        /** Sets [Builder.id] to an arbitrary JSON value. */
        fun id(id: JsonField<String>) = apply { this.id = id }

        fun pollAfterMs(pollAfterMs: Long) = pollAfterMs(JsonField.of(pollAfterMs))

        /** Sets [Builder.pollAfterMs] to an arbitrary JSON value. */
        fun pollAfterMs(pollAfterMs: JsonField<Long>) = apply { this.pollAfterMs = pollAfterMs }

        /** Resolved X user ID from count-based profile estimates. */
        fun resolvedXUserId(resolvedXUserId: String) =
            resolvedXUserId(JsonField.of(resolvedXUserId))

        /** Sets [Builder.resolvedXUserId] to an arbitrary JSON value. */
        fun resolvedXUserId(resolvedXUserId: JsonField<String>) = apply {
            this.resolvedXUserId = resolvedXUserId
        }

        fun status(status: Status) = status(JsonField.of(status))

        /** Sets [Builder.status] to an arbitrary JSON value. */
        fun status(status: JsonField<Status>) = apply { this.status = status }

        fun statusUrl(statusUrl: String) = statusUrl(JsonField.of(statusUrl))

        /** Sets [Builder.statusUrl] to an arbitrary JSON value. */
        fun statusUrl(statusUrl: JsonField<String>) = apply { this.statusUrl = statusUrl }

        /** Identifier for the extraction tool used to run a job. */
        fun toolType(toolType: ToolType) = toolType(JsonField.of(toolType))

        /** Sets [Builder.toolType] to an arbitrary JSON value. */
        fun toolType(toolType: JsonField<ToolType>) = apply { this.toolType = toolType }

        fun waitUrl(waitUrl: String) = waitUrl(JsonField.of(waitUrl))

        /** Sets [Builder.waitUrl] to an arbitrary JSON value. */
        fun waitUrl(waitUrl: JsonField<String>) = apply { this.waitUrl = waitUrl }

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
         * Returns an immutable instance of [ExtractionRunResponse].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```kotlin
         * .allowed()
         * .creditsAvailable()
         * .creditsRequired()
         * .estimatedResults()
         * .source()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): ExtractionRunResponse =
            ExtractionRunResponse(
                checkRequired("allowed", allowed),
                checkRequired("creditsAvailable", creditsAvailable),
                checkRequired("creditsRequired", creditsRequired),
                checkRequired("estimatedResults", estimatedResults),
                checkRequired("source", source),
                id,
                pollAfterMs,
                resolvedXUserId,
                status,
                statusUrl,
                toolType,
                waitUrl,
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
    fun validate(): ExtractionRunResponse = apply {
        if (validated) {
            return@apply
        }

        allowed()
        creditsAvailable()
        creditsRequired()
        estimatedResults()
        source()
        id()
        pollAfterMs()
        resolvedXUserId()
        status()?.validate()
        statusUrl()
        toolType()?.validate()
        waitUrl()
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
        (if (allowed.asKnown() == null) 0 else 1) +
            (if (creditsAvailable.asKnown() == null) 0 else 1) +
            (if (creditsRequired.asKnown() == null) 0 else 1) +
            (if (estimatedResults.asKnown() == null) 0 else 1) +
            (if (source.asKnown() == null) 0 else 1) +
            (if (id.asKnown() == null) 0 else 1) +
            (if (pollAfterMs.asKnown() == null) 0 else 1) +
            (if (resolvedXUserId.asKnown() == null) 0 else 1) +
            (status.asKnown()?.validity() ?: 0) +
            (if (statusUrl.asKnown() == null) 0 else 1) +
            (toolType.asKnown()?.validity() ?: 0) +
            (if (waitUrl.asKnown() == null) 0 else 1)

    class Status @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

        /** Returns this class instance's raw value. */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            val PENDING = of("pending")

            val RUNNING = of("running")

            val CANCELED = of("canceled")

            val COMPLETED = of("completed")

            val FAILED = of("failed")

            fun of(value: String) = Status(JsonField.of(value))
        }

        /** An enum containing [Status]'s known values. */
        enum class Known {
            PENDING,
            RUNNING,
            CANCELED,
            COMPLETED,
            FAILED,
        }

        /**
         * An enum containing [Status]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Status] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            PENDING,
            RUNNING,
            CANCELED,
            COMPLETED,
            FAILED,
            /** An enum member indicating that [Status] was instantiated with an unknown value. */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         */
        fun value(): Value =
            when (this) {
                PENDING -> Value.PENDING
                RUNNING -> Value.RUNNING
                CANCELED -> Value.CANCELED
                COMPLETED -> Value.COMPLETED
                FAILED -> Value.FAILED
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
                PENDING -> Known.PENDING
                RUNNING -> Known.RUNNING
                CANCELED -> Known.CANCELED
                COMPLETED -> Known.COMPLETED
                FAILED -> Known.FAILED
                else -> throw XTwitterScraperInvalidDataException("Unknown Status: $value")
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
        fun validate(): Status = apply {
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

            return other is Status && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    /** Identifier for the extraction tool used to run a job. */
    class ToolType @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

        /** Returns this class instance's raw value. */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            val ARTICLE_EXTRACTOR = of("article_extractor")

            val COMMUNITY_EXTRACTOR = of("community_extractor")

            val COMMUNITY_MODERATOR_EXPLORER = of("community_moderator_explorer")

            val COMMUNITY_POST_EXTRACTOR = of("community_post_extractor")

            val COMMUNITY_SEARCH = of("community_search")

            val FAVORITERS = of("favoriters")

            val FOLLOWER_EXPLORER = of("follower_explorer")

            val FOLLOWING_EXPLORER = of("following_explorer")

            val LIST_FOLLOWER_EXPLORER = of("list_follower_explorer")

            val LIST_MEMBER_EXTRACTOR = of("list_member_extractor")

            val LIST_POST_EXTRACTOR = of("list_post_extractor")

            val MENTION_EXTRACTOR = of("mention_extractor")

            val PEOPLE_SEARCH = of("people_search")

            val POST_EXTRACTOR = of("post_extractor")

            val QUOTE_EXTRACTOR = of("quote_extractor")

            val REPLY_EXTRACTOR = of("reply_extractor")

            val REPOST_EXTRACTOR = of("repost_extractor")

            val SPACE_EXPLORER = of("space_explorer")

            val THREAD_EXTRACTOR = of("thread_extractor")

            val TWEET_SEARCH_EXTRACTOR = of("tweet_search_extractor")

            val USER_LIKES = of("user_likes")

            val USER_MEDIA = of("user_media")

            val VERIFIED_FOLLOWER_EXPLORER = of("verified_follower_explorer")

            fun of(value: String) = ToolType(JsonField.of(value))
        }

        /** An enum containing [ToolType]'s known values. */
        enum class Known {
            ARTICLE_EXTRACTOR,
            COMMUNITY_EXTRACTOR,
            COMMUNITY_MODERATOR_EXPLORER,
            COMMUNITY_POST_EXTRACTOR,
            COMMUNITY_SEARCH,
            FAVORITERS,
            FOLLOWER_EXPLORER,
            FOLLOWING_EXPLORER,
            LIST_FOLLOWER_EXPLORER,
            LIST_MEMBER_EXTRACTOR,
            LIST_POST_EXTRACTOR,
            MENTION_EXTRACTOR,
            PEOPLE_SEARCH,
            POST_EXTRACTOR,
            QUOTE_EXTRACTOR,
            REPLY_EXTRACTOR,
            REPOST_EXTRACTOR,
            SPACE_EXPLORER,
            THREAD_EXTRACTOR,
            TWEET_SEARCH_EXTRACTOR,
            USER_LIKES,
            USER_MEDIA,
            VERIFIED_FOLLOWER_EXPLORER,
        }

        /**
         * An enum containing [ToolType]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [ToolType] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            ARTICLE_EXTRACTOR,
            COMMUNITY_EXTRACTOR,
            COMMUNITY_MODERATOR_EXPLORER,
            COMMUNITY_POST_EXTRACTOR,
            COMMUNITY_SEARCH,
            FAVORITERS,
            FOLLOWER_EXPLORER,
            FOLLOWING_EXPLORER,
            LIST_FOLLOWER_EXPLORER,
            LIST_MEMBER_EXTRACTOR,
            LIST_POST_EXTRACTOR,
            MENTION_EXTRACTOR,
            PEOPLE_SEARCH,
            POST_EXTRACTOR,
            QUOTE_EXTRACTOR,
            REPLY_EXTRACTOR,
            REPOST_EXTRACTOR,
            SPACE_EXPLORER,
            THREAD_EXTRACTOR,
            TWEET_SEARCH_EXTRACTOR,
            USER_LIKES,
            USER_MEDIA,
            VERIFIED_FOLLOWER_EXPLORER,
            /** An enum member indicating that [ToolType] was instantiated with an unknown value. */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         */
        fun value(): Value =
            when (this) {
                ARTICLE_EXTRACTOR -> Value.ARTICLE_EXTRACTOR
                COMMUNITY_EXTRACTOR -> Value.COMMUNITY_EXTRACTOR
                COMMUNITY_MODERATOR_EXPLORER -> Value.COMMUNITY_MODERATOR_EXPLORER
                COMMUNITY_POST_EXTRACTOR -> Value.COMMUNITY_POST_EXTRACTOR
                COMMUNITY_SEARCH -> Value.COMMUNITY_SEARCH
                FAVORITERS -> Value.FAVORITERS
                FOLLOWER_EXPLORER -> Value.FOLLOWER_EXPLORER
                FOLLOWING_EXPLORER -> Value.FOLLOWING_EXPLORER
                LIST_FOLLOWER_EXPLORER -> Value.LIST_FOLLOWER_EXPLORER
                LIST_MEMBER_EXTRACTOR -> Value.LIST_MEMBER_EXTRACTOR
                LIST_POST_EXTRACTOR -> Value.LIST_POST_EXTRACTOR
                MENTION_EXTRACTOR -> Value.MENTION_EXTRACTOR
                PEOPLE_SEARCH -> Value.PEOPLE_SEARCH
                POST_EXTRACTOR -> Value.POST_EXTRACTOR
                QUOTE_EXTRACTOR -> Value.QUOTE_EXTRACTOR
                REPLY_EXTRACTOR -> Value.REPLY_EXTRACTOR
                REPOST_EXTRACTOR -> Value.REPOST_EXTRACTOR
                SPACE_EXPLORER -> Value.SPACE_EXPLORER
                THREAD_EXTRACTOR -> Value.THREAD_EXTRACTOR
                TWEET_SEARCH_EXTRACTOR -> Value.TWEET_SEARCH_EXTRACTOR
                USER_LIKES -> Value.USER_LIKES
                USER_MEDIA -> Value.USER_MEDIA
                VERIFIED_FOLLOWER_EXPLORER -> Value.VERIFIED_FOLLOWER_EXPLORER
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
                ARTICLE_EXTRACTOR -> Known.ARTICLE_EXTRACTOR
                COMMUNITY_EXTRACTOR -> Known.COMMUNITY_EXTRACTOR
                COMMUNITY_MODERATOR_EXPLORER -> Known.COMMUNITY_MODERATOR_EXPLORER
                COMMUNITY_POST_EXTRACTOR -> Known.COMMUNITY_POST_EXTRACTOR
                COMMUNITY_SEARCH -> Known.COMMUNITY_SEARCH
                FAVORITERS -> Known.FAVORITERS
                FOLLOWER_EXPLORER -> Known.FOLLOWER_EXPLORER
                FOLLOWING_EXPLORER -> Known.FOLLOWING_EXPLORER
                LIST_FOLLOWER_EXPLORER -> Known.LIST_FOLLOWER_EXPLORER
                LIST_MEMBER_EXTRACTOR -> Known.LIST_MEMBER_EXTRACTOR
                LIST_POST_EXTRACTOR -> Known.LIST_POST_EXTRACTOR
                MENTION_EXTRACTOR -> Known.MENTION_EXTRACTOR
                PEOPLE_SEARCH -> Known.PEOPLE_SEARCH
                POST_EXTRACTOR -> Known.POST_EXTRACTOR
                QUOTE_EXTRACTOR -> Known.QUOTE_EXTRACTOR
                REPLY_EXTRACTOR -> Known.REPLY_EXTRACTOR
                REPOST_EXTRACTOR -> Known.REPOST_EXTRACTOR
                SPACE_EXPLORER -> Known.SPACE_EXPLORER
                THREAD_EXTRACTOR -> Known.THREAD_EXTRACTOR
                TWEET_SEARCH_EXTRACTOR -> Known.TWEET_SEARCH_EXTRACTOR
                USER_LIKES -> Known.USER_LIKES
                USER_MEDIA -> Known.USER_MEDIA
                VERIFIED_FOLLOWER_EXPLORER -> Known.VERIFIED_FOLLOWER_EXPLORER
                else -> throw XTwitterScraperInvalidDataException("Unknown ToolType: $value")
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
        fun validate(): ToolType = apply {
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

            return other is ToolType && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ExtractionRunResponse &&
            allowed == other.allowed &&
            creditsAvailable == other.creditsAvailable &&
            creditsRequired == other.creditsRequired &&
            estimatedResults == other.estimatedResults &&
            source == other.source &&
            id == other.id &&
            pollAfterMs == other.pollAfterMs &&
            resolvedXUserId == other.resolvedXUserId &&
            status == other.status &&
            statusUrl == other.statusUrl &&
            toolType == other.toolType &&
            waitUrl == other.waitUrl &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            allowed,
            creditsAvailable,
            creditsRequired,
            estimatedResults,
            source,
            id,
            pollAfterMs,
            resolvedXUserId,
            status,
            statusUrl,
            toolType,
            waitUrl,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "ExtractionRunResponse{allowed=$allowed, creditsAvailable=$creditsAvailable, creditsRequired=$creditsRequired, estimatedResults=$estimatedResults, source=$source, id=$id, pollAfterMs=$pollAfterMs, resolvedXUserId=$resolvedXUserId, status=$status, statusUrl=$statusUrl, toolType=$toolType, waitUrl=$waitUrl, additionalProperties=$additionalProperties}"
}
