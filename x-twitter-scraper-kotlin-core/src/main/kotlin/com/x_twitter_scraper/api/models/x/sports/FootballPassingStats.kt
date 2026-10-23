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

/** Passing stats. */
class FootballPassingStats
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val attempts: JsonField<Long>,
    private val avgYards: JsonField<Double>,
    private val completions: JsonField<Long>,
    private val interceptions: JsonField<Long>,
    private val netYards: JsonField<Long>,
    private val rating: JsonField<Double>,
    private val sackYards: JsonField<Long>,
    private val sacks: JsonField<Long>,
    private val touchdowns: JsonField<Long>,
    private val yards: JsonField<Long>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("attempts") @ExcludeMissing attempts: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("avgYards") @ExcludeMissing avgYards: JsonField<Double> = JsonMissing.of(),
        @JsonProperty("completions") @ExcludeMissing completions: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("interceptions") @ExcludeMissing interceptions: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("netYards") @ExcludeMissing netYards: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("rating") @ExcludeMissing rating: JsonField<Double> = JsonMissing.of(),
        @JsonProperty("sackYards") @ExcludeMissing sackYards: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("sacks") @ExcludeMissing sacks: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("touchdowns") @ExcludeMissing touchdowns: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("yards") @ExcludeMissing yards: JsonField<Long> = JsonMissing.of(),
    ) : this(attempts, avgYards, completions, interceptions, netYards, rating, sackYards, sacks, touchdowns, yards, mutableMapOf())

    /** Passes thrown. */
    fun attempts(): Long? = attempts.getNullable("attempts")

    /** Yards per pass. */
    fun avgYards(): Double? = avgYards.getNullable("avgYards")

    /** Passes caught. */
    fun completions(): Long? = completions.getNullable("completions")

    /** Passes intercepted. */
    fun interceptions(): Long? = interceptions.getNullable("interceptions")

    /** Passing yards minus sack yards. */
    fun netYards(): Long? = netYards.getNullable("netYards")

    /** The passer rating. */
    fun rating(): Double? = rating.getNullable("rating")

    /** Yards lost to sacks. */
    fun sackYards(): Long? = sackYards.getNullable("sackYards")

    /** Times sacked. */
    fun sacks(): Long? = sacks.getNullable("sacks")

    /** Passing touchdowns. */
    fun touchdowns(): Long? = touchdowns.getNullable("touchdowns")

    /** Passing yards. */
    fun yards(): Long? = yards.getNullable("yards")

    /** Returns the raw JSON value of [attempts]. */
    @JsonProperty("attempts") @ExcludeMissing fun _attempts(): JsonField<Long> = attempts

    /** Returns the raw JSON value of [avgYards]. */
    @JsonProperty("avgYards") @ExcludeMissing fun _avgYards(): JsonField<Double> = avgYards

    /** Returns the raw JSON value of [completions]. */
    @JsonProperty("completions") @ExcludeMissing fun _completions(): JsonField<Long> = completions

    /** Returns the raw JSON value of [interceptions]. */
    @JsonProperty("interceptions") @ExcludeMissing fun _interceptions(): JsonField<Long> = interceptions

    /** Returns the raw JSON value of [netYards]. */
    @JsonProperty("netYards") @ExcludeMissing fun _netYards(): JsonField<Long> = netYards

    /** Returns the raw JSON value of [rating]. */
    @JsonProperty("rating") @ExcludeMissing fun _rating(): JsonField<Double> = rating

    /** Returns the raw JSON value of [sackYards]. */
    @JsonProperty("sackYards") @ExcludeMissing fun _sackYards(): JsonField<Long> = sackYards

    /** Returns the raw JSON value of [sacks]. */
    @JsonProperty("sacks") @ExcludeMissing fun _sacks(): JsonField<Long> = sacks

    /** Returns the raw JSON value of [touchdowns]. */
    @JsonProperty("touchdowns") @ExcludeMissing fun _touchdowns(): JsonField<Long> = touchdowns

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

        /** Returns a mutable builder for constructing an instance of [FootballPassingStats]. */
        fun builder() = Builder()
    }

    /** A builder for [FootballPassingStats]. */
    class Builder internal constructor() {

        private var attempts: JsonField<Long>? = null
        private var avgYards: JsonField<Double>? = null
        private var completions: JsonField<Long>? = null
        private var interceptions: JsonField<Long>? = null
        private var netYards: JsonField<Long>? = null
        private var rating: JsonField<Double>? = null
        private var sackYards: JsonField<Long>? = null
        private var sacks: JsonField<Long>? = null
        private var touchdowns: JsonField<Long>? = null
        private var yards: JsonField<Long>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        internal fun from(footballPassingStats: FootballPassingStats) = apply {
            attempts = footballPassingStats.attempts
            avgYards = footballPassingStats.avgYards
            completions = footballPassingStats.completions
            interceptions = footballPassingStats.interceptions
            netYards = footballPassingStats.netYards
            rating = footballPassingStats.rating
            sackYards = footballPassingStats.sackYards
            sacks = footballPassingStats.sacks
            touchdowns = footballPassingStats.touchdowns
            yards = footballPassingStats.yards
            additionalProperties = footballPassingStats.additionalProperties.toMutableMap()
        }

        /** Passes thrown. */
        fun attempts(attempts: Long?) = attempts(JsonField.ofNullable(attempts))

        /** Sets [Builder.attempts] to an arbitrary JSON value. */
        fun attempts(attempts: JsonField<Long>) = apply { this.attempts = attempts }

        /** Yards per pass. */
        fun avgYards(avgYards: Double?) = avgYards(JsonField.ofNullable(avgYards))

        /** Sets [Builder.avgYards] to an arbitrary JSON value. */
        fun avgYards(avgYards: JsonField<Double>) = apply { this.avgYards = avgYards }

        /** Passes caught. */
        fun completions(completions: Long?) = completions(JsonField.ofNullable(completions))

        /** Sets [Builder.completions] to an arbitrary JSON value. */
        fun completions(completions: JsonField<Long>) = apply { this.completions = completions }

        /** Passes intercepted. */
        fun interceptions(interceptions: Long?) = interceptions(JsonField.ofNullable(interceptions))

        /** Sets [Builder.interceptions] to an arbitrary JSON value. */
        fun interceptions(interceptions: JsonField<Long>) = apply { this.interceptions = interceptions }

        /** Passing yards minus sack yards. */
        fun netYards(netYards: Long?) = netYards(JsonField.ofNullable(netYards))

        /** Sets [Builder.netYards] to an arbitrary JSON value. */
        fun netYards(netYards: JsonField<Long>) = apply { this.netYards = netYards }

        /** The passer rating. */
        fun rating(rating: Double?) = rating(JsonField.ofNullable(rating))

        /** Sets [Builder.rating] to an arbitrary JSON value. */
        fun rating(rating: JsonField<Double>) = apply { this.rating = rating }

        /** Yards lost to sacks. */
        fun sackYards(sackYards: Long?) = sackYards(JsonField.ofNullable(sackYards))

        /** Sets [Builder.sackYards] to an arbitrary JSON value. */
        fun sackYards(sackYards: JsonField<Long>) = apply { this.sackYards = sackYards }

        /** Times sacked. */
        fun sacks(sacks: Long?) = sacks(JsonField.ofNullable(sacks))

        /** Sets [Builder.sacks] to an arbitrary JSON value. */
        fun sacks(sacks: JsonField<Long>) = apply { this.sacks = sacks }

        /** Passing touchdowns. */
        fun touchdowns(touchdowns: Long?) = touchdowns(JsonField.ofNullable(touchdowns))

        /** Sets [Builder.touchdowns] to an arbitrary JSON value. */
        fun touchdowns(touchdowns: JsonField<Long>) = apply { this.touchdowns = touchdowns }

        /** Passing yards. */
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
         * Returns an immutable instance of [FootballPassingStats].
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): FootballPassingStats =
            FootballPassingStats(
                checkRequired("attempts", attempts),
                checkRequired("avgYards", avgYards),
                checkRequired("completions", completions),
                checkRequired("interceptions", interceptions),
                checkRequired("netYards", netYards),
                checkRequired("rating", rating),
                checkRequired("sackYards", sackYards),
                checkRequired("sacks", sacks),
                checkRequired("touchdowns", touchdowns),
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
    fun validate(): FootballPassingStats = apply {
        if (validated) {
            return@apply
        }

        attempts()
        avgYards()
        completions()
        interceptions()
        netYards()
        rating()
        sackYards()
        sacks()
        touchdowns()
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
            (if (avgYards.asKnown() == null) 0 else 1) +
            (if (completions.asKnown() == null) 0 else 1) +
            (if (interceptions.asKnown() == null) 0 else 1) +
            (if (netYards.asKnown() == null) 0 else 1) +
            (if (rating.asKnown() == null) 0 else 1) +
            (if (sackYards.asKnown() == null) 0 else 1) +
            (if (sacks.asKnown() == null) 0 else 1) +
            (if (touchdowns.asKnown() == null) 0 else 1) +
            (if (yards.asKnown() == null) 0 else 1)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is FootballPassingStats &&
            attempts == other.attempts &&
            avgYards == other.avgYards &&
            completions == other.completions &&
            interceptions == other.interceptions &&
            netYards == other.netYards &&
            rating == other.rating &&
            sackYards == other.sackYards &&
            sacks == other.sacks &&
            touchdowns == other.touchdowns &&
            yards == other.yards &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(attempts, avgYards, completions, interceptions, netYards, rating, sackYards, sacks, touchdowns, yards, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "FootballPassingStats{attempts=$attempts, avgYards=$avgYards, completions=$completions, interceptions=$interceptions, netYards=$netYards, rating=$rating, sackYards=$sackYards, sacks=$sacks, touchdowns=$touchdowns, yards=$yards, additionalProperties=$additionalProperties}"
}
