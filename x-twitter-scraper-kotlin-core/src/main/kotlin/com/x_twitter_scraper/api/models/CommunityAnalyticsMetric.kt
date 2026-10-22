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

/** A community metric for X's latest period & its percent change. */
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
     * Change in percent from the same figure 1 day earlier. Values stay unrounded.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun changePercent(): Double = changePercent.getRequired("changePercent")

    /**
     * Total for this metric during X's latest period.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun count(): Long = count.getRequired("count")

    /**
     * Returns the raw JSON value of [changePercent].
     *
     * Unlike [changePercent], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("changePercent")
    @ExcludeMissing
    fun _changePercent(): JsonField<Double> = changePercent

    /**
     * Returns the raw JSON value of [count].
     *
     * Unlike [count], this method doesn't throw if the JSON field has an unexpected type.
     */
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
         * Returns a mutable builder for constructing an instance of [CommunityAnalyticsMetric].
         *
         * The following fields are required:
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

        /** Change in percent from the same figure 1 day earlier. Values stay unrounded. */
        fun changePercent(changePercent: Double) = changePercent(JsonField.of(changePercent))

        /** Sets [Builder.changePercent] to an arbitrary JSON value. */
        fun changePercent(changePercent: JsonField<Double>) = apply {
            this.changePercent = changePercent
        }

        /** Total for this metric during X's latest period. */
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
         * Returns an immutable instance of [CommunityAnalyticsMetric].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```kotlin
         * .changePercent()
         * .count()
         * ```
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
     * Validates that the types of all values in this object match their expected types recursively.
     *
     * This method is _not_ forwards compatible with new types from the API for existing fields.
     *
     * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't match
     *   its expected type.
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
