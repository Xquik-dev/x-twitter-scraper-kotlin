// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.sports

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

/** Punting stats. */
class FootballPlayerStatsPunts
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val attempts: JsonField<Long>,
    private val avgNetYards: JsonField<Double>,
    private val avgYards: JsonField<Double>,
    private val inside20: JsonField<Long>,
    private val longest: JsonField<Long>,
    private val yards: JsonField<Long>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("attempts") @ExcludeMissing attempts: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("avgNetYards") @ExcludeMissing avgNetYards: JsonField<Double> = JsonMissing.of(),
        @JsonProperty("avgYards") @ExcludeMissing avgYards: JsonField<Double> = JsonMissing.of(),
        @JsonProperty("inside20") @ExcludeMissing inside20: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("longest") @ExcludeMissing longest: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("yards") @ExcludeMissing yards: JsonField<Long> = JsonMissing.of(),
    ) : this(attempts, avgNetYards, avgYards, inside20, longest, yards, mutableMapOf())

    /** Punts. */
    fun attempts(): Long? = attempts.getNullable("attempts")

    /** Net yards per punt, after returns. */
    fun avgNetYards(): Double? = avgNetYards.getNullable("avgNetYards")

    /** Yards per punt. */
    fun avgYards(): Double? = avgYards.getNullable("avgYards")

    /** Punts downed inside the 20. */
    fun inside20(): Long? = inside20.getNullable("inside20")

    /** The longest punt, in yards. */
    fun longest(): Long? = longest.getNullable("longest")

    /** Punting yards. */
    fun yards(): Long? = yards.getNullable("yards")

    /** Returns the raw JSON value of [attempts]. */
    @JsonProperty("attempts") @ExcludeMissing fun _attempts(): JsonField<Long> = attempts

    /** Returns the raw JSON value of [avgNetYards]. */
    @JsonProperty("avgNetYards") @ExcludeMissing fun _avgNetYards(): JsonField<Double> = avgNetYards

    /** Returns the raw JSON value of [avgYards]. */
    @JsonProperty("avgYards") @ExcludeMissing fun _avgYards(): JsonField<Double> = avgYards

    /** Returns the raw JSON value of [inside20]. */
    @JsonProperty("inside20") @ExcludeMissing fun _inside20(): JsonField<Long> = inside20

    /** Returns the raw JSON value of [longest]. */
    @JsonProperty("longest") @ExcludeMissing fun _longest(): JsonField<Long> = longest

    /** Returns the raw JSON value of [yards]. */
    @JsonProperty("yards") @ExcludeMissing fun _yards(): JsonField<Long> = yards

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

        /** Returns a mutable builder for constructing an instance of [FootballPlayerStatsPunts]. */
        fun builder() = Builder()
    }

    /** A builder for [FootballPlayerStatsPunts]. */
    class Builder internal constructor() {

        private var attempts: JsonField<Long>? = null
        private var avgNetYards: JsonField<Double>? = null
        private var avgYards: JsonField<Double>? = null
        private var inside20: JsonField<Long>? = null
        private var longest: JsonField<Long>? = null
        private var yards: JsonField<Long>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        internal fun from(footballPlayerStatsPunts: FootballPlayerStatsPunts) = apply {
            attempts = footballPlayerStatsPunts.attempts
            avgNetYards = footballPlayerStatsPunts.avgNetYards
            avgYards = footballPlayerStatsPunts.avgYards
            inside20 = footballPlayerStatsPunts.inside20
            longest = footballPlayerStatsPunts.longest
            yards = footballPlayerStatsPunts.yards
            additionalProperties = footballPlayerStatsPunts.additionalProperties.toMutableMap()
        }

        /** Punts. */
        fun attempts(attempts: Long?) = attempts(JsonField.ofNullable(attempts))

        /** Sets [Builder.attempts] to an arbitrary JSON value. */
        fun attempts(attempts: JsonField<Long>) = apply { this.attempts = attempts }

        /** Net yards per punt, after returns. */
        fun avgNetYards(avgNetYards: Double?) = avgNetYards(JsonField.ofNullable(avgNetYards))

        /** Sets [Builder.avgNetYards] to an arbitrary JSON value. */
        fun avgNetYards(avgNetYards: JsonField<Double>) = apply { this.avgNetYards = avgNetYards }

        /** Yards per punt. */
        fun avgYards(avgYards: Double?) = avgYards(JsonField.ofNullable(avgYards))

        /** Sets [Builder.avgYards] to an arbitrary JSON value. */
        fun avgYards(avgYards: JsonField<Double>) = apply { this.avgYards = avgYards }

        /** Punts downed inside the 20. */
        fun inside20(inside20: Long?) = inside20(JsonField.ofNullable(inside20))

        /** Sets [Builder.inside20] to an arbitrary JSON value. */
        fun inside20(inside20: JsonField<Long>) = apply { this.inside20 = inside20 }

        /** The longest punt, in yards. */
        fun longest(longest: Long?) = longest(JsonField.ofNullable(longest))

        /** Sets [Builder.longest] to an arbitrary JSON value. */
        fun longest(longest: JsonField<Long>) = apply { this.longest = longest }

        /** Punting yards. */
        fun yards(yards: Long?) = yards(JsonField.ofNullable(yards))

        /** Sets [Builder.yards] to an arbitrary JSON value. */
        fun yards(yards: JsonField<Long>) = apply { this.yards = yards }

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
         * Returns an immutable instance of [FootballPlayerStatsPunts].
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): FootballPlayerStatsPunts =
            FootballPlayerStatsPunts(
                checkRequired("attempts", attempts),
                checkRequired("avgNetYards", avgNetYards),
                checkRequired("avgYards", avgYards),
                checkRequired("inside20", inside20),
                checkRequired("longest", longest),
                checkRequired("yards", yards),
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
    fun validate(): FootballPlayerStatsPunts = apply {
        if (validated) {
            return@apply
        }

        attempts()
        avgNetYards()
        avgYards()
        inside20()
        longest()
        yards()
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
        (if (attempts.asKnown() == null) 0 else 1) +
            (if (avgNetYards.asKnown() == null) 0 else 1) +
            (if (avgYards.asKnown() == null) 0 else 1) +
            (if (inside20.asKnown() == null) 0 else 1) +
            (if (longest.asKnown() == null) 0 else 1) +
            (if (yards.asKnown() == null) 0 else 1)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is FootballPlayerStatsPunts &&
            attempts == other.attempts &&
            avgNetYards == other.avgNetYards &&
            avgYards == other.avgYards &&
            inside20 == other.inside20 &&
            longest == other.longest &&
            yards == other.yards &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(attempts, avgNetYards, avgYards, inside20, longest, yards, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "FootballPlayerStatsPunts{attempts=$attempts, avgNetYards=$avgNetYards, avgYards=$avgYards, inside20=$inside20, longest=$longest, yards=$yards, additionalProperties=$additionalProperties}"
}
