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
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects

/** Extraction job tracking status, tool type, and result count. */
class ExtractionJob
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val createdAt: JsonField<OffsetDateTime>,
    private val status: JsonField<Status>,
    private val toolType: JsonField<ToolType>,
    private val totalResults: JsonField<Long>,
    private val completedAt: JsonField<OffsetDateTime>,
    private val completionReason: JsonField<CompletionReason>,
    private val effectiveResultsLimit: JsonField<Long>,
    private val errorMessage: JsonField<String>,
    private val resultsLimit: JsonField<Long>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("createdAt")
        @ExcludeMissing
        createdAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("status") @ExcludeMissing status: JsonField<Status> = JsonMissing.of(),
        @JsonProperty("toolType") @ExcludeMissing toolType: JsonField<ToolType> = JsonMissing.of(),
        @JsonProperty("totalResults")
        @ExcludeMissing
        totalResults: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("completedAt")
        @ExcludeMissing
        completedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("completionReason")
        @ExcludeMissing
        completionReason: JsonField<CompletionReason> = JsonMissing.of(),
        @JsonProperty("effectiveResultsLimit")
        @ExcludeMissing
        effectiveResultsLimit: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("errorMessage")
        @ExcludeMissing
        errorMessage: JsonField<String> = JsonMissing.of(),
        @JsonProperty("resultsLimit")
        @ExcludeMissing
        resultsLimit: JsonField<Long> = JsonMissing.of(),
    ) : this(
        id,
        createdAt,
        status,
        toolType,
        totalResults,
        completedAt,
        completionReason,
        effectiveResultsLimit,
        errorMessage,
        resultsLimit,
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
    fun createdAt(): OffsetDateTime = createdAt.getRequired("createdAt")

    /**
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun status(): Status = status.getRequired("status")

    /**
     * Identifier for the extraction tool used to run a job.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun toolType(): ToolType = toolType.getRequired("toolType")

    /**
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun totalResults(): Long = totalResults.getRequired("totalResults")

    /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
    fun completedAt(): OffsetDateTime? = completedAt.getNullable("completedAt")

    /**
     * Why a terminal job stopped.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun completionReason(): CompletionReason? = completionReason.getNullable("completionReason")

    /**
     * Result limit after affordability checks.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun effectiveResultsLimit(): Long? = effectiveResultsLimit.getNullable("effectiveResultsLimit")

    /**
     * Safe error code for a failed job.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun errorMessage(): String? = errorMessage.getNullable("errorMessage")

    /**
     * Requested result limit.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun resultsLimit(): Long? = resultsLimit.getNullable("resultsLimit")

    /**
     * Returns the raw JSON value of [id].
     *
     * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    /**
     * Returns the raw JSON value of [createdAt].
     *
     * Unlike [createdAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("createdAt")
    @ExcludeMissing
    fun _createdAt(): JsonField<OffsetDateTime> = createdAt

    /**
     * Returns the raw JSON value of [status].
     *
     * Unlike [status], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("status") @ExcludeMissing fun _status(): JsonField<Status> = status

    /**
     * Returns the raw JSON value of [toolType].
     *
     * Unlike [toolType], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("toolType") @ExcludeMissing fun _toolType(): JsonField<ToolType> = toolType

    /**
     * Returns the raw JSON value of [totalResults].
     *
     * Unlike [totalResults], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("totalResults")
    @ExcludeMissing
    fun _totalResults(): JsonField<Long> = totalResults

    /**
     * Returns the raw JSON value of [completedAt].
     *
     * Unlike [completedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("completedAt")
    @ExcludeMissing
    fun _completedAt(): JsonField<OffsetDateTime> = completedAt

    /**
     * Returns the raw JSON value of [completionReason].
     *
     * Unlike [completionReason], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("completionReason")
    @ExcludeMissing
    fun _completionReason(): JsonField<CompletionReason> = completionReason

    /**
     * Returns the raw JSON value of [effectiveResultsLimit].
     *
     * Unlike [effectiveResultsLimit], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("effectiveResultsLimit")
    @ExcludeMissing
    fun _effectiveResultsLimit(): JsonField<Long> = effectiveResultsLimit

    /**
     * Returns the raw JSON value of [errorMessage].
     *
     * Unlike [errorMessage], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("errorMessage")
    @ExcludeMissing
    fun _errorMessage(): JsonField<String> = errorMessage

    /**
     * Returns the raw JSON value of [resultsLimit].
     *
     * Unlike [resultsLimit], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("resultsLimit")
    @ExcludeMissing
    fun _resultsLimit(): JsonField<Long> = resultsLimit

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
         * Returns a mutable builder for constructing an instance of [ExtractionJob].
         *
         * The following fields are required:
         * ```kotlin
         * .id()
         * .createdAt()
         * .status()
         * .toolType()
         * .totalResults()
         * ```
         */
        fun builder() = Builder()
    }

    /** A builder for [ExtractionJob]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var createdAt: JsonField<OffsetDateTime>? = null
        private var status: JsonField<Status>? = null
        private var toolType: JsonField<ToolType>? = null
        private var totalResults: JsonField<Long>? = null
        private var completedAt: JsonField<OffsetDateTime> = JsonMissing.of()
        private var completionReason: JsonField<CompletionReason> = JsonMissing.of()
        private var effectiveResultsLimit: JsonField<Long> = JsonMissing.of()
        private var errorMessage: JsonField<String> = JsonMissing.of()
        private var resultsLimit: JsonField<Long> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        internal fun from(extractionJob: ExtractionJob) = apply {
            id = extractionJob.id
            createdAt = extractionJob.createdAt
            status = extractionJob.status
            toolType = extractionJob.toolType
            totalResults = extractionJob.totalResults
            completedAt = extractionJob.completedAt
            completionReason = extractionJob.completionReason
            effectiveResultsLimit = extractionJob.effectiveResultsLimit
            errorMessage = extractionJob.errorMessage
            resultsLimit = extractionJob.resultsLimit
            additionalProperties = extractionJob.additionalProperties.toMutableMap()
        }

        fun id(id: String) = id(JsonField.of(id))

        /** Sets [Builder.id] to an arbitrary JSON value. */
        fun id(id: JsonField<String>) = apply { this.id = id }

        fun createdAt(createdAt: OffsetDateTime) = createdAt(JsonField.of(createdAt))

        /** Sets [Builder.createdAt] to an arbitrary JSON value. */
        fun createdAt(createdAt: JsonField<OffsetDateTime>) = apply { this.createdAt = createdAt }

        fun status(status: Status) = status(JsonField.of(status))

        /** Sets [Builder.status] to an arbitrary JSON value. */
        fun status(status: JsonField<Status>) = apply { this.status = status }

        /** Identifier for the extraction tool used to run a job. */
        fun toolType(toolType: ToolType) = toolType(JsonField.of(toolType))

        /** Sets [Builder.toolType] to an arbitrary JSON value. */
        fun toolType(toolType: JsonField<ToolType>) = apply { this.toolType = toolType }

        fun totalResults(totalResults: Long) = totalResults(JsonField.of(totalResults))

        /** Sets [Builder.totalResults] to an arbitrary JSON value. */
        fun totalResults(totalResults: JsonField<Long>) = apply { this.totalResults = totalResults }

        fun completedAt(completedAt: OffsetDateTime) = completedAt(JsonField.of(completedAt))

        /** Sets [Builder.completedAt] to an arbitrary JSON value. */
        fun completedAt(completedAt: JsonField<OffsetDateTime>) = apply {
            this.completedAt = completedAt
        }

        /** Why a terminal job stopped. */
        fun completionReason(completionReason: CompletionReason) =
            completionReason(JsonField.of(completionReason))

        /** Sets [Builder.completionReason] to an arbitrary JSON value. */
        fun completionReason(completionReason: JsonField<CompletionReason>) = apply {
            this.completionReason = completionReason
        }

        /** Result limit after affordability checks. */
        fun effectiveResultsLimit(effectiveResultsLimit: Long) =
            effectiveResultsLimit(JsonField.of(effectiveResultsLimit))

        /** Sets [Builder.effectiveResultsLimit] to an arbitrary JSON value. */
        fun effectiveResultsLimit(effectiveResultsLimit: JsonField<Long>) = apply {
            this.effectiveResultsLimit = effectiveResultsLimit
        }

        /** Safe error code for a failed job. */
        fun errorMessage(errorMessage: String) = errorMessage(JsonField.of(errorMessage))

        /** Sets [Builder.errorMessage] to an arbitrary JSON value. */
        fun errorMessage(errorMessage: JsonField<String>) = apply {
            this.errorMessage = errorMessage
        }

        /** Requested result limit. */
        fun resultsLimit(resultsLimit: Long) = resultsLimit(JsonField.of(resultsLimit))

        /** Sets [Builder.resultsLimit] to an arbitrary JSON value. */
        fun resultsLimit(resultsLimit: JsonField<Long>) = apply { this.resultsLimit = resultsLimit }

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
         * Returns an immutable instance of [ExtractionJob].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```kotlin
         * .id()
         * .createdAt()
         * .status()
         * .toolType()
         * .totalResults()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): ExtractionJob =
            ExtractionJob(
                checkRequired("id", id),
                checkRequired("createdAt", createdAt),
                checkRequired("status", status),
                checkRequired("toolType", toolType),
                checkRequired("totalResults", totalResults),
                completedAt,
                completionReason,
                effectiveResultsLimit,
                errorMessage,
                resultsLimit,
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
    fun validate(): ExtractionJob = apply {
        if (validated) {
            return@apply
        }

        id()
        createdAt()
        status().validate()
        toolType().validate()
        totalResults()
        completedAt()
        completionReason()?.validate()
        effectiveResultsLimit()
        errorMessage()
        resultsLimit()
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
            (if (createdAt.asKnown() == null) 0 else 1) +
            (status.asKnown()?.validity() ?: 0) +
            (toolType.asKnown()?.validity() ?: 0) +
            (if (totalResults.asKnown() == null) 0 else 1) +
            (if (completedAt.asKnown() == null) 0 else 1) +
            (completionReason.asKnown()?.validity() ?: 0) +
            (if (effectiveResultsLimit.asKnown() == null) 0 else 1) +
            (if (errorMessage.asKnown() == null) 0 else 1) +
            (if (resultsLimit.asKnown() == null) 0 else 1)

    class Status @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

        /** Returns this class instance's raw value. */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            val RUNNING = of("running")

            val COMPLETED = of("completed")

            val FAILED = of("failed")

            val PENDING = of("pending")

            val CANCELED = of("canceled")

            fun of(value: String) = Status(JsonField.of(value))
        }

        /** An enum containing [Status]'s known values. */
        enum class Known {
            RUNNING,
            COMPLETED,
            FAILED,
            PENDING,
            CANCELED,
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
            RUNNING,
            COMPLETED,
            FAILED,
            PENDING,
            CANCELED,
            /** An enum member indicating that [Status] was instantiated with an unknown value. */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         */
        fun value(): Value =
            when (this) {
                RUNNING -> Value.RUNNING
                COMPLETED -> Value.COMPLETED
                FAILED -> Value.FAILED
                PENDING -> Value.PENDING
                CANCELED -> Value.CANCELED
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
                RUNNING -> Known.RUNNING
                COMPLETED -> Known.COMPLETED
                FAILED -> Known.FAILED
                PENDING -> Known.PENDING
                CANCELED -> Known.CANCELED
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

    /** Why a terminal job stopped. */
    class CompletionReason @JsonCreator private constructor(private val value: JsonField<String>) :
        Enum {

        /** Returns this class instance's raw value. */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            val BUDGET_LIMITED = of("budget_limited")

            val CANCELED = of("canceled")

            val DEADLINE_REACHED = of("deadline_reached")

            val FAILED = of("failed")

            val PAGINATION_SAFETY_LIMIT = of("pagination_safety_limit")

            val PARTIAL_FAILURE = of("partial_failure")

            val REQUESTED_LIMIT_REACHED = of("requested_limit_reached")

            val SOURCE_EXHAUSTED = of("source_exhausted")

            fun of(value: String) = CompletionReason(JsonField.of(value))
        }

        /** An enum containing [CompletionReason]'s known values. */
        enum class Known {
            BUDGET_LIMITED,
            CANCELED,
            DEADLINE_REACHED,
            FAILED,
            PAGINATION_SAFETY_LIMIT,
            PARTIAL_FAILURE,
            REQUESTED_LIMIT_REACHED,
            SOURCE_EXHAUSTED,
        }

        /**
         * An enum containing [CompletionReason]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [CompletionReason] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            BUDGET_LIMITED,
            CANCELED,
            DEADLINE_REACHED,
            FAILED,
            PAGINATION_SAFETY_LIMIT,
            PARTIAL_FAILURE,
            REQUESTED_LIMIT_REACHED,
            SOURCE_EXHAUSTED,
            /**
             * An enum member indicating that [CompletionReason] was instantiated with an unknown
             * value.
             */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         */
        fun value(): Value =
            when (this) {
                BUDGET_LIMITED -> Value.BUDGET_LIMITED
                CANCELED -> Value.CANCELED
                DEADLINE_REACHED -> Value.DEADLINE_REACHED
                FAILED -> Value.FAILED
                PAGINATION_SAFETY_LIMIT -> Value.PAGINATION_SAFETY_LIMIT
                PARTIAL_FAILURE -> Value.PARTIAL_FAILURE
                REQUESTED_LIMIT_REACHED -> Value.REQUESTED_LIMIT_REACHED
                SOURCE_EXHAUSTED -> Value.SOURCE_EXHAUSTED
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
                BUDGET_LIMITED -> Known.BUDGET_LIMITED
                CANCELED -> Known.CANCELED
                DEADLINE_REACHED -> Known.DEADLINE_REACHED
                FAILED -> Known.FAILED
                PAGINATION_SAFETY_LIMIT -> Known.PAGINATION_SAFETY_LIMIT
                PARTIAL_FAILURE -> Known.PARTIAL_FAILURE
                REQUESTED_LIMIT_REACHED -> Known.REQUESTED_LIMIT_REACHED
                SOURCE_EXHAUSTED -> Known.SOURCE_EXHAUSTED
                else ->
                    throw XTwitterScraperInvalidDataException("Unknown CompletionReason: $value")
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
        fun validate(): CompletionReason = apply {
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

            return other is CompletionReason && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ExtractionJob &&
            id == other.id &&
            createdAt == other.createdAt &&
            status == other.status &&
            toolType == other.toolType &&
            totalResults == other.totalResults &&
            completedAt == other.completedAt &&
            completionReason == other.completionReason &&
            effectiveResultsLimit == other.effectiveResultsLimit &&
            errorMessage == other.errorMessage &&
            resultsLimit == other.resultsLimit &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            id,
            createdAt,
            status,
            toolType,
            totalResults,
            completedAt,
            completionReason,
            effectiveResultsLimit,
            errorMessage,
            resultsLimit,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "ExtractionJob{id=$id, createdAt=$createdAt, status=$status, toolType=$toolType, totalResults=$totalResults, completedAt=$completedAt, completionReason=$completionReason, effectiveResultsLimit=$effectiveResultsLimit, errorMessage=$errorMessage, resultsLimit=$resultsLimit, additionalProperties=$additionalProperties}"
}
