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
import com.x_twitter_scraper.api.core.checkRequired
import com.x_twitter_scraper.api.errors.XTwitterScraperInvalidDataException
import java.util.Collections
import java.util.Objects

/** Read a community metric and its percentage change. */
class CommunityAnalyticsMetric
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val changePercent: JsonField<Double>,
    private val count: JsonField<Long>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("changePercent")
        @ExcludeMissing
        changePercent: JsonField<Double> = JsonMissing.of(),
        @JsonProperty("count") @ExcludeMissing count: JsonField<Long> = JsonMissing.of(),
    ) : this(changePercent, count, mutableMapOf())

    /**
     * Compare this figure with one day earlier. Keep the percentage unrounded.
     *
     * @throws XTwitterScraperInvalidDataException if this field is missing, null, or invalid.
     */
    fun changePercent(): Double = changePercent.getRequired("changePercent")

    /**
     * Count this metric during X's latest period.
     *
     * @throws XTwitterScraperInvalidDataException if this field is missing, null, or invalid.
     */
    fun count(): Long = count.getRequired("count")

    /** Return the raw JSON value of [changePercent]. */
    @JsonProperty("changePercent")
    @ExcludeMissing
    fun _changePercent(): JsonField<Double> = changePercent

    /** Return the raw JSON value of [count]. */
    @JsonProperty("count") @ExcludeMissing fun _count(): JsonField<Long> = count

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
         * Create a mutable builder for [CommunityAnalyticsMetric].
         *
         * Set every required field before building.
         *
         * ```kotlin
         * .changePercent()
         * .count()
         * ```
         */
        fun builder() = Builder()
    }

    /** A builder for [CommunityAnalyticsMetric]. */
    class Builder internal constructor() {

        private var changePercent: JsonField<Double>? = null
        private var count: JsonField<Long>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        internal fun from(communityAnalyticsMetric: CommunityAnalyticsMetric) = apply {
            changePercent = communityAnalyticsMetric.changePercent
            count = communityAnalyticsMetric.count
            additionalProperties = communityAnalyticsMetric.additionalProperties.toMutableMap()
        }

        /** Compare this figure with one day earlier. Keep the percentage unrounded. */
        fun changePercent(changePercent: Double) = changePercent(JsonField.of(changePercent))

        /** Sets [Builder.changePercent] to an arbitrary JSON value. */
        fun changePercent(changePercent: JsonField<Double>) = apply {
            this.changePercent = changePercent
        }

        /** Count this metric during X's latest period. */
        fun count(count: Long) = count(JsonField.of(count))

        /** Sets [Builder.count] to an arbitrary JSON value. */
        fun count(count: JsonField<Long>) = apply { this.count = count }

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
         * Create an immutable instance of [CommunityAnalyticsMetric].
         *
         * Further builder updates leave this instance unchanged.
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): CommunityAnalyticsMetric =
            CommunityAnalyticsMetric(
                checkRequired("changePercent", changePercent),
                checkRequired("count", count),
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    /**
     * Validate every field recursively. New API types can fail validation.
     *
     * @throws XTwitterScraperInvalidDataException if any field is missing, null, or invalid.
     */
    fun validate(): CommunityAnalyticsMetric = apply {
        if (validated) {
            return@apply
        }

        changePercent()
        count()
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
        (if (changePercent.asKnown() == null) 0 else 1) + (if (count.asKnown() == null) 0 else 1)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is CommunityAnalyticsMetric &&
            changePercent == other.changePercent &&
            count == other.count &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(changePercent, count, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "CommunityAnalyticsMetric{changePercent=$changePercent, count=$count, additionalProperties=$additionalProperties}"
}
