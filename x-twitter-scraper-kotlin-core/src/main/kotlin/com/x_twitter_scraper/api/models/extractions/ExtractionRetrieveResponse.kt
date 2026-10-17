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
import com.x_twitter_scraper.api.core.checkKnown
import com.x_twitter_scraper.api.core.checkRequired
import com.x_twitter_scraper.api.core.toImmutable
import com.x_twitter_scraper.api.errors.XTwitterScraperInvalidDataException
import com.x_twitter_scraper.api.models.TweetMedia
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects

class ExtractionRetrieveResponse
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val hasMore: JsonField<Boolean>,
    private val job: JsonField<Job>,
    private val results: JsonField<List<Result>>,
    private val nextCursor: JsonField<String>,
    private val pollAfterMs: JsonField<Long>,
    private val waitUrl: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("hasMore") @ExcludeMissing hasMore: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("job") @ExcludeMissing job: JsonField<Job> = JsonMissing.of(),
        @JsonProperty("results")
        @ExcludeMissing
        results: JsonField<List<Result>> = JsonMissing.of(),
        @JsonProperty("nextCursor")
        @ExcludeMissing
        nextCursor: JsonField<String> = JsonMissing.of(),
        @JsonProperty("pollAfterMs")
        @ExcludeMissing
        pollAfterMs: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("waitUrl") @ExcludeMissing waitUrl: JsonField<String> = JsonMissing.of(),
    ) : this(hasMore, job, results, nextCursor, pollAfterMs, waitUrl, mutableMapOf())

    /**
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun hasMore(): Boolean = hasMore.getRequired("hasMore")

    /**
     * Extraction job metadata - shape varies by tool type (JSON)
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun job(): Job = job.getRequired("job")

    /**
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun results(): List<Result> = results.getRequired("results")

    /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
    fun nextCursor(): String? = nextCursor.getNullable("nextCursor")

    /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
    fun pollAfterMs(): Long? = pollAfterMs.getNullable("pollAfterMs")

    /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
    fun waitUrl(): String? = waitUrl.getNullable("waitUrl")

    /**
     * Returns the raw JSON value of [hasMore].
     *
     * Unlike [hasMore], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("hasMore") @ExcludeMissing fun _hasMore(): JsonField<Boolean> = hasMore

    /**
     * Returns the raw JSON value of [job].
     *
     * Unlike [job], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("job") @ExcludeMissing fun _job(): JsonField<Job> = job

    /**
     * Returns the raw JSON value of [results].
     *
     * Unlike [results], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("results") @ExcludeMissing fun _results(): JsonField<List<Result>> = results

    /**
     * Returns the raw JSON value of [nextCursor].
     *
     * Unlike [nextCursor], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("nextCursor") @ExcludeMissing fun _nextCursor(): JsonField<String> = nextCursor

    /**
     * Returns the raw JSON value of [pollAfterMs].
     *
     * Unlike [pollAfterMs], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("pollAfterMs") @ExcludeMissing fun _pollAfterMs(): JsonField<Long> = pollAfterMs

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
         * Returns a mutable builder for constructing an instance of [ExtractionRetrieveResponse].
         *
         * The following fields are required:
         * ```kotlin
         * .hasMore()
         * .job()
         * .results()
         * ```
         */
        fun builder() = Builder()
    }

    /** A builder for [ExtractionRetrieveResponse]. */
    class Builder internal constructor() {

        private var hasMore: JsonField<Boolean>? = null
        private var job: JsonField<Job>? = null
        private var results: JsonField<MutableList<Result>>? = null
        private var nextCursor: JsonField<String> = JsonMissing.of()
        private var pollAfterMs: JsonField<Long> = JsonMissing.of()
        private var waitUrl: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        internal fun from(extractionRetrieveResponse: ExtractionRetrieveResponse) = apply {
            hasMore = extractionRetrieveResponse.hasMore
            job = extractionRetrieveResponse.job
            results = extractionRetrieveResponse.results.map { it.toMutableList() }
            nextCursor = extractionRetrieveResponse.nextCursor
            pollAfterMs = extractionRetrieveResponse.pollAfterMs
            waitUrl = extractionRetrieveResponse.waitUrl
            additionalProperties = extractionRetrieveResponse.additionalProperties.toMutableMap()
        }

        fun hasMore(hasMore: Boolean) = hasMore(JsonField.of(hasMore))

        /** Sets [Builder.hasMore] to an arbitrary JSON value. */
        fun hasMore(hasMore: JsonField<Boolean>) = apply { this.hasMore = hasMore }

        /** Extraction job metadata - shape varies by tool type (JSON) */
        fun job(job: Job) = job(JsonField.of(job))

        /** Sets [Builder.job] to an arbitrary JSON value. */
        fun job(job: JsonField<Job>) = apply { this.job = job }

        fun results(results: List<Result>) = results(JsonField.of(results))

        /** Sets [Builder.results] to an arbitrary JSON value. */
        fun results(results: JsonField<List<Result>>) = apply {
            this.results = results.map { it.toMutableList() }
        }

        /**
         * Adds a single [Result] to [results].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addResult(result: Result) = apply {
            results =
                (results ?: JsonField.of(mutableListOf())).also {
                    checkKnown("results", it).add(result)
                }
        }

        fun nextCursor(nextCursor: String) = nextCursor(JsonField.of(nextCursor))

        /** Sets [Builder.nextCursor] to an arbitrary JSON value. */
        fun nextCursor(nextCursor: JsonField<String>) = apply { this.nextCursor = nextCursor }

        fun pollAfterMs(pollAfterMs: Long) = pollAfterMs(JsonField.of(pollAfterMs))

        /** Sets [Builder.pollAfterMs] to an arbitrary JSON value. */
        fun pollAfterMs(pollAfterMs: JsonField<Long>) = apply { this.pollAfterMs = pollAfterMs }

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
         * Returns an immutable instance of [ExtractionRetrieveResponse].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```kotlin
         * .hasMore()
         * .job()
         * .results()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): ExtractionRetrieveResponse =
            ExtractionRetrieveResponse(
                checkRequired("hasMore", hasMore),
                checkRequired("job", job),
                checkRequired("results", results).map { it.toImmutable() },
                nextCursor,
                pollAfterMs,
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
    fun validate(): ExtractionRetrieveResponse = apply {
        if (validated) {
            return@apply
        }

        hasMore()
        job().validate()
        results().forEach { it.validate() }
        nextCursor()
        pollAfterMs()
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
        (if (hasMore.asKnown() == null) 0 else 1) +
            (job.asKnown()?.validity() ?: 0) +
            (results.asKnown()?.sumOf { it.validity() } ?: 0) +
            (if (nextCursor.asKnown() == null) 0 else 1) +
            (if (pollAfterMs.asKnown() == null) 0 else 1) +
            (if (waitUrl.asKnown() == null) 0 else 1)

    /** Extraction job metadata - shape varies by tool type (JSON) */
    class Job
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val id: JsonField<String>,
        private val completedAt: JsonField<OffsetDateTime>,
        private val completionReason: JsonField<CompletionReason>,
        private val createdAt: JsonField<OffsetDateTime>,
        private val effectiveResultsLimit: JsonField<Long>,
        private val errorMessage: JsonField<String>,
        private val resultsLimit: JsonField<Long>,
        private val status: JsonField<Status>,
        private val toolType: JsonField<ToolType>,
        private val totalResults: JsonField<Long>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
            @JsonProperty("completedAt")
            @ExcludeMissing
            completedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
            @JsonProperty("completionReason")
            @ExcludeMissing
            completionReason: JsonField<CompletionReason> = JsonMissing.of(),
            @JsonProperty("createdAt")
            @ExcludeMissing
            createdAt: JsonField<OffsetDateTime> = JsonMissing.of(),
            @JsonProperty("effectiveResultsLimit")
            @ExcludeMissing
            effectiveResultsLimit: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("errorMessage")
            @ExcludeMissing
            errorMessage: JsonField<String> = JsonMissing.of(),
            @JsonProperty("resultsLimit")
            @ExcludeMissing
            resultsLimit: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("status") @ExcludeMissing status: JsonField<Status> = JsonMissing.of(),
            @JsonProperty("toolType")
            @ExcludeMissing
            toolType: JsonField<ToolType> = JsonMissing.of(),
            @JsonProperty("totalResults")
            @ExcludeMissing
            totalResults: JsonField<Long> = JsonMissing.of(),
        ) : this(
            id,
            completedAt,
            completionReason,
            createdAt,
            effectiveResultsLimit,
            errorMessage,
            resultsLimit,
            status,
            toolType,
            totalResults,
            mutableMapOf(),
        )

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun id(): String? = id.getNullable("id")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun completedAt(): OffsetDateTime? = completedAt.getNullable("completedAt")

        /**
         * Why a terminal job stopped.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun completionReason(): CompletionReason? = completionReason.getNullable("completionReason")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun createdAt(): OffsetDateTime? = createdAt.getNullable("createdAt")

        /**
         * Result limit after affordability checks.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun effectiveResultsLimit(): Long? =
            effectiveResultsLimit.getNullable("effectiveResultsLimit")

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

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun status(): Status? = status.getNullable("status")

        /**
         * Identifier for the extraction tool used to run a job.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun toolType(): ToolType? = toolType.getNullable("toolType")

        /**
         * Whole number from 0.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun totalResults(): Long? = totalResults.getNullable("totalResults")

        /**
         * Returns the raw JSON value of [id].
         *
         * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

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
         * Returns the raw JSON value of [createdAt].
         *
         * Unlike [createdAt], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("createdAt")
        @ExcludeMissing
        fun _createdAt(): JsonField<OffsetDateTime> = createdAt

        /**
         * Returns the raw JSON value of [effectiveResultsLimit].
         *
         * Unlike [effectiveResultsLimit], this method doesn't throw if the JSON field has an
         * unexpected type.
         */
        @JsonProperty("effectiveResultsLimit")
        @ExcludeMissing
        fun _effectiveResultsLimit(): JsonField<Long> = effectiveResultsLimit

        /**
         * Returns the raw JSON value of [errorMessage].
         *
         * Unlike [errorMessage], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("errorMessage")
        @ExcludeMissing
        fun _errorMessage(): JsonField<String> = errorMessage

        /**
         * Returns the raw JSON value of [resultsLimit].
         *
         * Unlike [resultsLimit], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("resultsLimit")
        @ExcludeMissing
        fun _resultsLimit(): JsonField<Long> = resultsLimit

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
         * Unlike [totalResults], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("totalResults")
        @ExcludeMissing
        fun _totalResults(): JsonField<Long> = totalResults

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

            /** Returns a mutable builder for constructing an instance of [Job]. */
            fun builder() = Builder()
        }

        /** A builder for [Job]. */
        class Builder internal constructor() {

            private var id: JsonField<String> = JsonMissing.of()
            private var completedAt: JsonField<OffsetDateTime> = JsonMissing.of()
            private var completionReason: JsonField<CompletionReason> = JsonMissing.of()
            private var createdAt: JsonField<OffsetDateTime> = JsonMissing.of()
            private var effectiveResultsLimit: JsonField<Long> = JsonMissing.of()
            private var errorMessage: JsonField<String> = JsonMissing.of()
            private var resultsLimit: JsonField<Long> = JsonMissing.of()
            private var status: JsonField<Status> = JsonMissing.of()
            private var toolType: JsonField<ToolType> = JsonMissing.of()
            private var totalResults: JsonField<Long> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(job: Job) = apply {
                id = job.id
                completedAt = job.completedAt
                completionReason = job.completionReason
                createdAt = job.createdAt
                effectiveResultsLimit = job.effectiveResultsLimit
                errorMessage = job.errorMessage
                resultsLimit = job.resultsLimit
                status = job.status
                toolType = job.toolType
                totalResults = job.totalResults
                additionalProperties = job.additionalProperties.toMutableMap()
            }

            fun id(id: String) = id(JsonField.of(id))

            /** Sets [Builder.id] to an arbitrary JSON value. */
            fun id(id: JsonField<String>) = apply { this.id = id }

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

            fun createdAt(createdAt: OffsetDateTime) = createdAt(JsonField.of(createdAt))

            /** Sets [Builder.createdAt] to an arbitrary JSON value. */
            fun createdAt(createdAt: JsonField<OffsetDateTime>) = apply {
                this.createdAt = createdAt
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
            fun resultsLimit(resultsLimit: JsonField<Long>) = apply {
                this.resultsLimit = resultsLimit
            }

            fun status(status: Status) = status(JsonField.of(status))

            /** Sets [Builder.status] to an arbitrary JSON value. */
            fun status(status: JsonField<Status>) = apply { this.status = status }

            /** Identifier for the extraction tool used to run a job. */
            fun toolType(toolType: ToolType) = toolType(JsonField.of(toolType))

            /** Sets [Builder.toolType] to an arbitrary JSON value. */
            fun toolType(toolType: JsonField<ToolType>) = apply { this.toolType = toolType }

            /** Whole number from 0. */
            fun totalResults(totalResults: Long) = totalResults(JsonField.of(totalResults))

            /** Sets [Builder.totalResults] to an arbitrary JSON value. */
            fun totalResults(totalResults: JsonField<Long>) = apply {
                this.totalResults = totalResults
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
             * Returns an immutable instance of [Job].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): Job =
                Job(
                    id,
                    completedAt,
                    completionReason,
                    createdAt,
                    effectiveResultsLimit,
                    errorMessage,
                    resultsLimit,
                    status,
                    toolType,
                    totalResults,
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
        fun validate(): Job = apply {
            if (validated) {
                return@apply
            }

            id()
            completedAt()
            completionReason()?.validate()
            createdAt()
            effectiveResultsLimit()
            errorMessage()
            resultsLimit()
            status()?.validate()
            toolType()?.validate()
            totalResults()
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
                (if (completedAt.asKnown() == null) 0 else 1) +
                (completionReason.asKnown()?.validity() ?: 0) +
                (if (createdAt.asKnown() == null) 0 else 1) +
                (if (effectiveResultsLimit.asKnown() == null) 0 else 1) +
                (if (errorMessage.asKnown() == null) 0 else 1) +
                (if (resultsLimit.asKnown() == null) 0 else 1) +
                (status.asKnown()?.validity() ?: 0) +
                (toolType.asKnown()?.validity() ?: 0) +
                (if (totalResults.asKnown() == null) 0 else 1)

        /** Why a terminal job stopped. */
        class CompletionReason
        @JsonCreator
        private constructor(private val value: JsonField<String>) : Enum {

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
             * An enum containing [CompletionReason]'s known values, as well as an [_UNKNOWN]
             * member.
             *
             * An instance of [CompletionReason] can contain an unknown value in a couple of cases:
             * - It was deserialized from data that doesn't match any known member. For example, if
             *   the SDK is on an older version than the API, then the API may respond with new
             *   members that the SDK is unaware of.
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
                 * An enum member indicating that [CompletionReason] was instantiated with an
                 * unknown value.
                 */
                _UNKNOWN,
            }

            /**
             * Returns an enum member corresponding to this class instance's value, or
             * [Value._UNKNOWN] if the class was instantiated with an unknown value.
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
                        throw XTwitterScraperInvalidDataException(
                            "Unknown CompletionReason: $value"
                        )
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
             * - It was deserialized from data that doesn't match any known member. For example, if
             *   the SDK is on an older version than the API, then the API may respond with new
             *   members that the SDK is unaware of.
             * - It was constructed with an arbitrary value using the [of] method.
             */
            enum class Value {
                PENDING,
                RUNNING,
                CANCELED,
                COMPLETED,
                FAILED,
                /**
                 * An enum member indicating that [Status] was instantiated with an unknown value.
                 */
                _UNKNOWN,
            }

            /**
             * Returns an enum member corresponding to this class instance's value, or
             * [Value._UNKNOWN] if the class was instantiated with an unknown value.
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
        class ToolType @JsonCreator private constructor(private val value: JsonField<String>) :
            Enum {

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
             * - It was deserialized from data that doesn't match any known member. For example, if
             *   the SDK is on an older version than the API, then the API may respond with new
             *   members that the SDK is unaware of.
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
                /**
                 * An enum member indicating that [ToolType] was instantiated with an unknown value.
                 */
                _UNKNOWN,
            }

            /**
             * Returns an enum member corresponding to this class instance's value, or
             * [Value._UNKNOWN] if the class was instantiated with an unknown value.
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

            return other is Job &&
                id == other.id &&
                completedAt == other.completedAt &&
                completionReason == other.completionReason &&
                createdAt == other.createdAt &&
                effectiveResultsLimit == other.effectiveResultsLimit &&
                errorMessage == other.errorMessage &&
                resultsLimit == other.resultsLimit &&
                status == other.status &&
                toolType == other.toolType &&
                totalResults == other.totalResults &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(
                id,
                completedAt,
                completionReason,
                createdAt,
                effectiveResultsLimit,
                errorMessage,
                resultsLimit,
                status,
                toolType,
                totalResults,
                additionalProperties,
            )
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Job{id=$id, completedAt=$completedAt, completionReason=$completionReason, createdAt=$createdAt, effectiveResultsLimit=$effectiveResultsLimit, errorMessage=$errorMessage, resultsLimit=$resultsLimit, status=$status, toolType=$toolType, totalResults=$totalResults, additionalProperties=$additionalProperties}"
    }

    /** A public row from any supported extraction mode. */
    class Result
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val id: JsonField<String>,
        private val bookmarkCount: JsonField<Long>,
        private val createdAt: JsonField<OffsetDateTime>,
        private val email: JsonField<String>,
        private val enrichmentData: JsonField<EnrichmentData>,
        private val likeCount: JsonField<Long>,
        private val media: JsonField<List<TweetMedia>>,
        private val quoteCount: JsonField<Long>,
        private val replyCount: JsonField<Long>,
        private val retweetCount: JsonField<Long>,
        private val tweetCreatedAt: JsonField<OffsetDateTime>,
        private val tweetId: JsonField<String>,
        private val tweetText: JsonField<String>,
        private val tweetUrl: JsonField<String>,
        private val viewCount: JsonField<Long>,
        private val xDisplayName: JsonField<String>,
        private val xFollowersCount: JsonField<Long>,
        private val xProfileImageUrl: JsonField<String>,
        private val xUserId: JsonField<String>,
        private val xUsername: JsonField<String>,
        private val xVerified: JsonField<Boolean>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
            @JsonProperty("bookmarkCount")
            @ExcludeMissing
            bookmarkCount: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("createdAt")
            @ExcludeMissing
            createdAt: JsonField<OffsetDateTime> = JsonMissing.of(),
            @JsonProperty("email") @ExcludeMissing email: JsonField<String> = JsonMissing.of(),
            @JsonProperty("enrichmentData")
            @ExcludeMissing
            enrichmentData: JsonField<EnrichmentData> = JsonMissing.of(),
            @JsonProperty("likeCount")
            @ExcludeMissing
            likeCount: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("media")
            @ExcludeMissing
            media: JsonField<List<TweetMedia>> = JsonMissing.of(),
            @JsonProperty("quoteCount")
            @ExcludeMissing
            quoteCount: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("replyCount")
            @ExcludeMissing
            replyCount: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("retweetCount")
            @ExcludeMissing
            retweetCount: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("tweetCreatedAt")
            @ExcludeMissing
            tweetCreatedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
            @JsonProperty("tweetId") @ExcludeMissing tweetId: JsonField<String> = JsonMissing.of(),
            @JsonProperty("tweetText")
            @ExcludeMissing
            tweetText: JsonField<String> = JsonMissing.of(),
            @JsonProperty("tweetUrl")
            @ExcludeMissing
            tweetUrl: JsonField<String> = JsonMissing.of(),
            @JsonProperty("viewCount")
            @ExcludeMissing
            viewCount: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("xDisplayName")
            @ExcludeMissing
            xDisplayName: JsonField<String> = JsonMissing.of(),
            @JsonProperty("xFollowersCount")
            @ExcludeMissing
            xFollowersCount: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("xProfileImageUrl")
            @ExcludeMissing
            xProfileImageUrl: JsonField<String> = JsonMissing.of(),
            @JsonProperty("xUserId") @ExcludeMissing xUserId: JsonField<String> = JsonMissing.of(),
            @JsonProperty("xUsername")
            @ExcludeMissing
            xUsername: JsonField<String> = JsonMissing.of(),
            @JsonProperty("xVerified")
            @ExcludeMissing
            xVerified: JsonField<Boolean> = JsonMissing.of(),
        ) : this(
            id,
            bookmarkCount,
            createdAt,
            email,
            enrichmentData,
            likeCount,
            media,
            quoteCount,
            replyCount,
            retweetCount,
            tweetCreatedAt,
            tweetId,
            tweetText,
            tweetUrl,
            viewCount,
            xDisplayName,
            xFollowersCount,
            xProfileImageUrl,
            xUserId,
            xUsername,
            xVerified,
            mutableMapOf(),
        )

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun id(): String? = id.getNullable("id")

        /**
         * Whole number from 0.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun bookmarkCount(): Long? = bookmarkCount.getNullable("bookmarkCount")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun createdAt(): OffsetDateTime? = createdAt.getNullable("createdAt")

        /**
         * First email the account publishes on its profile, with outputPreset=flat. Default nested
         * output uses enrichmentData.user.email, beside enrichmentData.user.contactInfo. Absent
         * when the profile publishes none.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun email(): String? = email.getNullable("email")

        /**
         * Public metadata whose fields X defines.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun enrichmentData(): EnrichmentData? = enrichmentData.getNullable("enrichmentData")

        /**
         * Whole number from 0.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun likeCount(): Long? = likeCount.getNullable("likeCount")

        /**
         * Attached media with outputPreset=flat. Default nested output uses
         * enrichmentData.tweet.media.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun media(): List<TweetMedia>? = media.getNullable("media")

        /**
         * Whole number from 0.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun quoteCount(): Long? = quoteCount.getNullable("quoteCount")

        /**
         * Whole number from 0.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun replyCount(): Long? = replyCount.getNullable("replyCount")

        /**
         * Whole number from 0.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun retweetCount(): Long? = retweetCount.getNullable("retweetCount")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun tweetCreatedAt(): OffsetDateTime? = tweetCreatedAt.getNullable("tweetCreatedAt")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun tweetId(): String? = tweetId.getNullable("tweetId")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun tweetText(): String? = tweetText.getNullable("tweetText")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun tweetUrl(): String? = tweetUrl.getNullable("tweetUrl")

        /**
         * Whole number from 0.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun viewCount(): Long? = viewCount.getNullable("viewCount")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun xDisplayName(): String? = xDisplayName.getNullable("xDisplayName")

        /**
         * Followers of the account. Absent when X sends no count, as for the hosts & speakers of a
         * Space, and never 0 in its place.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun xFollowersCount(): Long? = xFollowersCount.getNullable("xFollowersCount")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun xProfileImageUrl(): String? = xProfileImageUrl.getNullable("xProfileImageUrl")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun xUserId(): String? = xUserId.getNullable("xUserId")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun xUsername(): String? = xUsername.getNullable("xUsername")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun xVerified(): Boolean? = xVerified.getNullable("xVerified")

        /**
         * Returns the raw JSON value of [id].
         *
         * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

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
         * Returns the raw JSON value of [createdAt].
         *
         * Unlike [createdAt], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("createdAt")
        @ExcludeMissing
        fun _createdAt(): JsonField<OffsetDateTime> = createdAt

        /**
         * Returns the raw JSON value of [email].
         *
         * Unlike [email], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("email") @ExcludeMissing fun _email(): JsonField<String> = email

        /**
         * Returns the raw JSON value of [enrichmentData].
         *
         * Unlike [enrichmentData], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("enrichmentData")
        @ExcludeMissing
        fun _enrichmentData(): JsonField<EnrichmentData> = enrichmentData

        /**
         * Returns the raw JSON value of [likeCount].
         *
         * Unlike [likeCount], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("likeCount") @ExcludeMissing fun _likeCount(): JsonField<Long> = likeCount

        /**
         * Returns the raw JSON value of [media].
         *
         * Unlike [media], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("media") @ExcludeMissing fun _media(): JsonField<List<TweetMedia>> = media

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

        /**
         * Returns the raw JSON value of [tweetCreatedAt].
         *
         * Unlike [tweetCreatedAt], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("tweetCreatedAt")
        @ExcludeMissing
        fun _tweetCreatedAt(): JsonField<OffsetDateTime> = tweetCreatedAt

        /**
         * Returns the raw JSON value of [tweetId].
         *
         * Unlike [tweetId], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("tweetId") @ExcludeMissing fun _tweetId(): JsonField<String> = tweetId

        /**
         * Returns the raw JSON value of [tweetText].
         *
         * Unlike [tweetText], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("tweetText") @ExcludeMissing fun _tweetText(): JsonField<String> = tweetText

        /**
         * Returns the raw JSON value of [tweetUrl].
         *
         * Unlike [tweetUrl], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("tweetUrl") @ExcludeMissing fun _tweetUrl(): JsonField<String> = tweetUrl

        /**
         * Returns the raw JSON value of [viewCount].
         *
         * Unlike [viewCount], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("viewCount") @ExcludeMissing fun _viewCount(): JsonField<Long> = viewCount

        /**
         * Returns the raw JSON value of [xDisplayName].
         *
         * Unlike [xDisplayName], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("xDisplayName")
        @ExcludeMissing
        fun _xDisplayName(): JsonField<String> = xDisplayName

        /**
         * Returns the raw JSON value of [xFollowersCount].
         *
         * Unlike [xFollowersCount], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("xFollowersCount")
        @ExcludeMissing
        fun _xFollowersCount(): JsonField<Long> = xFollowersCount

        /**
         * Returns the raw JSON value of [xProfileImageUrl].
         *
         * Unlike [xProfileImageUrl], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("xProfileImageUrl")
        @ExcludeMissing
        fun _xProfileImageUrl(): JsonField<String> = xProfileImageUrl

        /**
         * Returns the raw JSON value of [xUserId].
         *
         * Unlike [xUserId], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("xUserId") @ExcludeMissing fun _xUserId(): JsonField<String> = xUserId

        /**
         * Returns the raw JSON value of [xUsername].
         *
         * Unlike [xUsername], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("xUsername") @ExcludeMissing fun _xUsername(): JsonField<String> = xUsername

        /**
         * Returns the raw JSON value of [xVerified].
         *
         * Unlike [xVerified], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("xVerified") @ExcludeMissing fun _xVerified(): JsonField<Boolean> = xVerified

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

            /** Returns a mutable builder for constructing an instance of [Result]. */
            fun builder() = Builder()
        }

        /** A builder for [Result]. */
        class Builder internal constructor() {

            private var id: JsonField<String> = JsonMissing.of()
            private var bookmarkCount: JsonField<Long> = JsonMissing.of()
            private var createdAt: JsonField<OffsetDateTime> = JsonMissing.of()
            private var email: JsonField<String> = JsonMissing.of()
            private var enrichmentData: JsonField<EnrichmentData> = JsonMissing.of()
            private var likeCount: JsonField<Long> = JsonMissing.of()
            private var media: JsonField<MutableList<TweetMedia>>? = null
            private var quoteCount: JsonField<Long> = JsonMissing.of()
            private var replyCount: JsonField<Long> = JsonMissing.of()
            private var retweetCount: JsonField<Long> = JsonMissing.of()
            private var tweetCreatedAt: JsonField<OffsetDateTime> = JsonMissing.of()
            private var tweetId: JsonField<String> = JsonMissing.of()
            private var tweetText: JsonField<String> = JsonMissing.of()
            private var tweetUrl: JsonField<String> = JsonMissing.of()
            private var viewCount: JsonField<Long> = JsonMissing.of()
            private var xDisplayName: JsonField<String> = JsonMissing.of()
            private var xFollowersCount: JsonField<Long> = JsonMissing.of()
            private var xProfileImageUrl: JsonField<String> = JsonMissing.of()
            private var xUserId: JsonField<String> = JsonMissing.of()
            private var xUsername: JsonField<String> = JsonMissing.of()
            private var xVerified: JsonField<Boolean> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(result: Result) = apply {
                id = result.id
                bookmarkCount = result.bookmarkCount
                createdAt = result.createdAt
                email = result.email
                enrichmentData = result.enrichmentData
                likeCount = result.likeCount
                media = result.media.map { it.toMutableList() }
                quoteCount = result.quoteCount
                replyCount = result.replyCount
                retweetCount = result.retweetCount
                tweetCreatedAt = result.tweetCreatedAt
                tweetId = result.tweetId
                tweetText = result.tweetText
                tweetUrl = result.tweetUrl
                viewCount = result.viewCount
                xDisplayName = result.xDisplayName
                xFollowersCount = result.xFollowersCount
                xProfileImageUrl = result.xProfileImageUrl
                xUserId = result.xUserId
                xUsername = result.xUsername
                xVerified = result.xVerified
                additionalProperties = result.additionalProperties.toMutableMap()
            }

            fun id(id: String) = id(JsonField.of(id))

            /** Sets [Builder.id] to an arbitrary JSON value. */
            fun id(id: JsonField<String>) = apply { this.id = id }

            /** Whole number from 0. */
            fun bookmarkCount(bookmarkCount: Long) = bookmarkCount(JsonField.of(bookmarkCount))

            /** Sets [Builder.bookmarkCount] to an arbitrary JSON value. */
            fun bookmarkCount(bookmarkCount: JsonField<Long>) = apply {
                this.bookmarkCount = bookmarkCount
            }

            fun createdAt(createdAt: OffsetDateTime) = createdAt(JsonField.of(createdAt))

            /** Sets [Builder.createdAt] to an arbitrary JSON value. */
            fun createdAt(createdAt: JsonField<OffsetDateTime>) = apply {
                this.createdAt = createdAt
            }

            /**
             * First email the account publishes on its profile, with outputPreset=flat. Default
             * nested output uses enrichmentData.user.email, beside enrichmentData.user.contactInfo.
             * Absent when the profile publishes none.
             */
            fun email(email: String) = email(JsonField.of(email))

            /** Sets [Builder.email] to an arbitrary JSON value. */
            fun email(email: JsonField<String>) = apply { this.email = email }

            /** Public metadata whose fields X defines. */
            fun enrichmentData(enrichmentData: EnrichmentData) =
                enrichmentData(JsonField.of(enrichmentData))

            /** Sets [Builder.enrichmentData] to an arbitrary JSON value. */
            fun enrichmentData(enrichmentData: JsonField<EnrichmentData>) = apply {
                this.enrichmentData = enrichmentData
            }

            /** Whole number from 0. */
            fun likeCount(likeCount: Long) = likeCount(JsonField.of(likeCount))

            /** Sets [Builder.likeCount] to an arbitrary JSON value. */
            fun likeCount(likeCount: JsonField<Long>) = apply { this.likeCount = likeCount }

            /**
             * Attached media with outputPreset=flat. Default nested output uses
             * enrichmentData.tweet.media.
             */
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

            /** Whole number from 0. */
            fun quoteCount(quoteCount: Long) = quoteCount(JsonField.of(quoteCount))

            /** Sets [Builder.quoteCount] to an arbitrary JSON value. */
            fun quoteCount(quoteCount: JsonField<Long>) = apply { this.quoteCount = quoteCount }

            /** Whole number from 0. */
            fun replyCount(replyCount: Long) = replyCount(JsonField.of(replyCount))

            /** Sets [Builder.replyCount] to an arbitrary JSON value. */
            fun replyCount(replyCount: JsonField<Long>) = apply { this.replyCount = replyCount }

            /** Whole number from 0. */
            fun retweetCount(retweetCount: Long) = retweetCount(JsonField.of(retweetCount))

            /** Sets [Builder.retweetCount] to an arbitrary JSON value. */
            fun retweetCount(retweetCount: JsonField<Long>) = apply {
                this.retweetCount = retweetCount
            }

            fun tweetCreatedAt(tweetCreatedAt: OffsetDateTime) =
                tweetCreatedAt(JsonField.of(tweetCreatedAt))

            /** Sets [Builder.tweetCreatedAt] to an arbitrary JSON value. */
            fun tweetCreatedAt(tweetCreatedAt: JsonField<OffsetDateTime>) = apply {
                this.tweetCreatedAt = tweetCreatedAt
            }

            fun tweetId(tweetId: String) = tweetId(JsonField.of(tweetId))

            /** Sets [Builder.tweetId] to an arbitrary JSON value. */
            fun tweetId(tweetId: JsonField<String>) = apply { this.tweetId = tweetId }

            fun tweetText(tweetText: String) = tweetText(JsonField.of(tweetText))

            /** Sets [Builder.tweetText] to an arbitrary JSON value. */
            fun tweetText(tweetText: JsonField<String>) = apply { this.tweetText = tweetText }

            fun tweetUrl(tweetUrl: String) = tweetUrl(JsonField.of(tweetUrl))

            /** Sets [Builder.tweetUrl] to an arbitrary JSON value. */
            fun tweetUrl(tweetUrl: JsonField<String>) = apply { this.tweetUrl = tweetUrl }

            /** Whole number from 0. */
            fun viewCount(viewCount: Long) = viewCount(JsonField.of(viewCount))

            /** Sets [Builder.viewCount] to an arbitrary JSON value. */
            fun viewCount(viewCount: JsonField<Long>) = apply { this.viewCount = viewCount }

            fun xDisplayName(xDisplayName: String) = xDisplayName(JsonField.of(xDisplayName))

            /** Sets [Builder.xDisplayName] to an arbitrary JSON value. */
            fun xDisplayName(xDisplayName: JsonField<String>) = apply {
                this.xDisplayName = xDisplayName
            }

            /**
             * Followers of the account. Absent when X sends no count, as for the hosts & speakers
             * of a Space, and never 0 in its place.
             */
            fun xFollowersCount(xFollowersCount: Long) =
                xFollowersCount(JsonField.of(xFollowersCount))

            /** Sets [Builder.xFollowersCount] to an arbitrary JSON value. */
            fun xFollowersCount(xFollowersCount: JsonField<Long>) = apply {
                this.xFollowersCount = xFollowersCount
            }

            fun xProfileImageUrl(xProfileImageUrl: String) =
                xProfileImageUrl(JsonField.of(xProfileImageUrl))

            /** Sets [Builder.xProfileImageUrl] to an arbitrary JSON value. */
            fun xProfileImageUrl(xProfileImageUrl: JsonField<String>) = apply {
                this.xProfileImageUrl = xProfileImageUrl
            }

            fun xUserId(xUserId: String) = xUserId(JsonField.of(xUserId))

            /** Sets [Builder.xUserId] to an arbitrary JSON value. */
            fun xUserId(xUserId: JsonField<String>) = apply { this.xUserId = xUserId }

            fun xUsername(xUsername: String) = xUsername(JsonField.of(xUsername))

            /** Sets [Builder.xUsername] to an arbitrary JSON value. */
            fun xUsername(xUsername: JsonField<String>) = apply { this.xUsername = xUsername }

            fun xVerified(xVerified: Boolean) = xVerified(JsonField.of(xVerified))

            /** Sets [Builder.xVerified] to an arbitrary JSON value. */
            fun xVerified(xVerified: JsonField<Boolean>) = apply { this.xVerified = xVerified }

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
             * Returns an immutable instance of [Result].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): Result =
                Result(
                    id,
                    bookmarkCount,
                    createdAt,
                    email,
                    enrichmentData,
                    likeCount,
                    (media ?: JsonMissing.of()).map { it.toImmutable() },
                    quoteCount,
                    replyCount,
                    retweetCount,
                    tweetCreatedAt,
                    tweetId,
                    tweetText,
                    tweetUrl,
                    viewCount,
                    xDisplayName,
                    xFollowersCount,
                    xProfileImageUrl,
                    xUserId,
                    xUsername,
                    xVerified,
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
        fun validate(): Result = apply {
            if (validated) {
                return@apply
            }

            id()
            bookmarkCount()
            createdAt()
            email()
            enrichmentData()?.validate()
            likeCount()
            media()?.forEach { it.validate() }
            quoteCount()
            replyCount()
            retweetCount()
            tweetCreatedAt()
            tweetId()
            tweetText()
            tweetUrl()
            viewCount()
            xDisplayName()
            xFollowersCount()
            xProfileImageUrl()
            xUserId()
            xUsername()
            xVerified()
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
                (if (bookmarkCount.asKnown() == null) 0 else 1) +
                (if (createdAt.asKnown() == null) 0 else 1) +
                (if (email.asKnown() == null) 0 else 1) +
                (enrichmentData.asKnown()?.validity() ?: 0) +
                (if (likeCount.asKnown() == null) 0 else 1) +
                (media.asKnown()?.sumOf { it.validity() } ?: 0) +
                (if (quoteCount.asKnown() == null) 0 else 1) +
                (if (replyCount.asKnown() == null) 0 else 1) +
                (if (retweetCount.asKnown() == null) 0 else 1) +
                (if (tweetCreatedAt.asKnown() == null) 0 else 1) +
                (if (tweetId.asKnown() == null) 0 else 1) +
                (if (tweetText.asKnown() == null) 0 else 1) +
                (if (tweetUrl.asKnown() == null) 0 else 1) +
                (if (viewCount.asKnown() == null) 0 else 1) +
                (if (xDisplayName.asKnown() == null) 0 else 1) +
                (if (xFollowersCount.asKnown() == null) 0 else 1) +
                (if (xProfileImageUrl.asKnown() == null) 0 else 1) +
                (if (xUserId.asKnown() == null) 0 else 1) +
                (if (xUsername.asKnown() == null) 0 else 1) +
                (if (xVerified.asKnown() == null) 0 else 1)

        /** Public metadata whose fields X defines. */
        class EnrichmentData
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

                /** Returns a mutable builder for constructing an instance of [EnrichmentData]. */
                fun builder() = Builder()
            }

            /** A builder for [EnrichmentData]. */
            class Builder internal constructor() {

                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                internal fun from(enrichmentData: EnrichmentData) = apply {
                    additionalProperties = enrichmentData.additionalProperties.toMutableMap()
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
                 * Returns an immutable instance of [EnrichmentData].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 */
                fun build(): EnrichmentData = EnrichmentData(additionalProperties.toImmutable())
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
            fun validate(): EnrichmentData = apply {
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

                return other is EnrichmentData && additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy { Objects.hash(additionalProperties) }

            override fun hashCode(): Int = hashCode

            override fun toString() = "EnrichmentData{additionalProperties=$additionalProperties}"
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Result &&
                id == other.id &&
                bookmarkCount == other.bookmarkCount &&
                createdAt == other.createdAt &&
                email == other.email &&
                enrichmentData == other.enrichmentData &&
                likeCount == other.likeCount &&
                media == other.media &&
                quoteCount == other.quoteCount &&
                replyCount == other.replyCount &&
                retweetCount == other.retweetCount &&
                tweetCreatedAt == other.tweetCreatedAt &&
                tweetId == other.tweetId &&
                tweetText == other.tweetText &&
                tweetUrl == other.tweetUrl &&
                viewCount == other.viewCount &&
                xDisplayName == other.xDisplayName &&
                xFollowersCount == other.xFollowersCount &&
                xProfileImageUrl == other.xProfileImageUrl &&
                xUserId == other.xUserId &&
                xUsername == other.xUsername &&
                xVerified == other.xVerified &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(
                id,
                bookmarkCount,
                createdAt,
                email,
                enrichmentData,
                likeCount,
                media,
                quoteCount,
                replyCount,
                retweetCount,
                tweetCreatedAt,
                tweetId,
                tweetText,
                tweetUrl,
                viewCount,
                xDisplayName,
                xFollowersCount,
                xProfileImageUrl,
                xUserId,
                xUsername,
                xVerified,
                additionalProperties,
            )
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Result{id=$id, bookmarkCount=$bookmarkCount, createdAt=$createdAt, email=$email, enrichmentData=$enrichmentData, likeCount=$likeCount, media=$media, quoteCount=$quoteCount, replyCount=$replyCount, retweetCount=$retweetCount, tweetCreatedAt=$tweetCreatedAt, tweetId=$tweetId, tweetText=$tweetText, tweetUrl=$tweetUrl, viewCount=$viewCount, xDisplayName=$xDisplayName, xFollowersCount=$xFollowersCount, xProfileImageUrl=$xProfileImageUrl, xUserId=$xUserId, xUsername=$xUsername, xVerified=$xVerified, additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ExtractionRetrieveResponse &&
            hasMore == other.hasMore &&
            job == other.job &&
            results == other.results &&
            nextCursor == other.nextCursor &&
            pollAfterMs == other.pollAfterMs &&
            waitUrl == other.waitUrl &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(hasMore, job, results, nextCursor, pollAfterMs, waitUrl, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "ExtractionRetrieveResponse{hasMore=$hasMore, job=$job, results=$results, nextCursor=$nextCursor, pollAfterMs=$pollAfterMs, waitUrl=$waitUrl, additionalProperties=$additionalProperties}"
}
