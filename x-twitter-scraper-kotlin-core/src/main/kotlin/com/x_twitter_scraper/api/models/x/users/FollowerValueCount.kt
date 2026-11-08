// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.users

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

/** How many followers share 1 value, such as a language or place. */
class FollowerValueCount
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val count: JsonField<Long>,
    private val percent: JsonField<Double>,
    private val value: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("count") @ExcludeMissing count: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("percent") @ExcludeMissing percent: JsonField<Double> = JsonMissing.of(),
        @JsonProperty("value") @ExcludeMissing value: JsonField<String> = JsonMissing.of(),
    ) : this(count, percent, value, mutableMapOf())

    fun count(): Long = count.getRequired("count")

    fun percent(): Double = percent.getRequired("percent")

    fun value(): String = value.getRequired("value")

    /** Returns the raw JSON value of [count]. */
    @JsonProperty("count") @ExcludeMissing fun _count(): JsonField<Long> = count

    /** Returns the raw JSON value of [percent]. */
    @JsonProperty("percent") @ExcludeMissing fun _percent(): JsonField<Double> = percent

    /** Returns the raw JSON value of [value]. */
    @JsonProperty("value") @ExcludeMissing fun _value(): JsonField<String> = value

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
         * Returns a mutable builder for constructing an instance of [FollowerValueCount].
         *
         * The following fields are required:
         * ```kotlin
         * .count()
         * .percent()
         * .value()
         * ```
         */
        fun builder() = Builder()
    }

    /** A builder for [FollowerValueCount]. */
    class Builder internal constructor() {

        private var count: JsonField<Long>? = null
        private var percent: JsonField<Double>? = null
        private var value: JsonField<String>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        internal fun from(followerValueCount: FollowerValueCount) = apply {
            count = followerValueCount.count
            percent = followerValueCount.percent
            value = followerValueCount.value
            additionalProperties = followerValueCount.additionalProperties.toMutableMap()
        }

        fun count(count: Long) = count(JsonField.of(count))

        /** Sets [Builder.count] to an arbitrary JSON value. */
        fun count(count: JsonField<Long>) = apply { this.count = count }

        fun percent(percent: Double) = percent(JsonField.of(percent))

        /** Sets [Builder.percent] to an arbitrary JSON value. */
        fun percent(percent: JsonField<Double>) = apply { this.percent = percent }

        fun value(value: String) = value(JsonField.of(value))

        /** Sets [Builder.value] to an arbitrary JSON value. */
        fun value(value: JsonField<String>) = apply { this.value = value }

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
         * Returns an immutable instance of [FollowerValueCount].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```kotlin
         * .count()
         * .percent()
         * .value()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): FollowerValueCount =
            FollowerValueCount(
                checkRequired("count", count),
                checkRequired("percent", percent),
                checkRequired("value", value),
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    /**
     * Validates that the types of all values in this object match their expected types recursively.
     *
     * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't match
     *   its expected type.
     */
    fun validate(): FollowerValueCount = apply {
        if (validated) {
            return@apply
        }

        count()
        percent()
        value()
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
        (if (count.asKnown() == null) 0 else 1) +
            (if (percent.asKnown() == null) 0 else 1) +
            (if (value.asKnown() == null) 0 else 1)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is FollowerValueCount &&
            count == other.count &&
            percent == other.percent &&
            value == other.value &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(count, percent, value, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "FollowerValueCount{count=$count, percent=$percent, value=$value, additionalProperties=$additionalProperties}"
}
