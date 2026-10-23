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

/** Receiving stats. */
class FootballPlayerStatsReceiving
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val avgYards: JsonField<Double>,
    private val longest: JsonField<Long>,
    private val receptions: JsonField<Long>,
    private val targets: JsonField<Long>,
    private val touchdowns: JsonField<Long>,
    private val yards: JsonField<Long>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("avgYards") @ExcludeMissing avgYards: JsonField<Double> = JsonMissing.of(),
        @JsonProperty("longest") @ExcludeMissing longest: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("receptions") @ExcludeMissing receptions: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("targets") @ExcludeMissing targets: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("touchdowns") @ExcludeMissing touchdowns: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("yards") @ExcludeMissing yards: JsonField<Long> = JsonMissing.of(),
    ) : this(avgYards, longest, receptions, targets, touchdowns, yards, mutableMapOf())

    /** Yards per catch. */
    fun avgYards(): Double? = avgYards.getNullable("avgYards")

    /** The longest catch, in yards. */
    fun longest(): Long? = longest.getNullable("longest")

    /** Catches. */
    fun receptions(): Long? = receptions.getNullable("receptions")

    /** Passes thrown to the player. */
    fun targets(): Long? = targets.getNullable("targets")

    /** Receiving touchdowns. */
    fun touchdowns(): Long? = touchdowns.getNullable("touchdowns")

    /** Receiving yards. */
    fun yards(): Long? = yards.getNullable("yards")

    /** Returns the raw JSON value of [avgYards]. */
    @JsonProperty("avgYards") @ExcludeMissing fun _avgYards(): JsonField<Double> = avgYards

    /** Returns the raw JSON value of [longest]. */
    @JsonProperty("longest") @ExcludeMissing fun _longest(): JsonField<Long> = longest

    /** Returns the raw JSON value of [receptions]. */
    @JsonProperty("receptions") @ExcludeMissing fun _receptions(): JsonField<Long> = receptions

    /** Returns the raw JSON value of [targets]. */
    @JsonProperty("targets") @ExcludeMissing fun _targets(): JsonField<Long> = targets

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

        /** Returns a mutable builder for constructing an instance of [FootballPlayerStatsReceiving]. */
        fun builder() = Builder()
    }

    /** A builder for [FootballPlayerStatsReceiving]. */
    class Builder internal constructor() {

        private var avgYards: JsonField<Double>? = null
        private var longest: JsonField<Long>? = null
        private var receptions: JsonField<Long>? = null
        private var targets: JsonField<Long>? = null
        private var touchdowns: JsonField<Long>? = null
        private var yards: JsonField<Long>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        internal fun from(footballPlayerStatsReceiving: FootballPlayerStatsReceiving) = apply {
            avgYards = footballPlayerStatsReceiving.avgYards
            longest = footballPlayerStatsReceiving.longest
            receptions = footballPlayerStatsReceiving.receptions
            targets = footballPlayerStatsReceiving.targets
            touchdowns = footballPlayerStatsReceiving.touchdowns
            yards = footballPlayerStatsReceiving.yards
            additionalProperties = footballPlayerStatsReceiving.additionalProperties.toMutableMap()
        }

        /** Yards per catch. */
        fun avgYards(avgYards: Double?) = avgYards(JsonField.ofNullable(avgYards))

        /** Sets [Builder.avgYards] to an arbitrary JSON value. */
        fun avgYards(avgYards: JsonField<Double>) = apply { this.avgYards = avgYards }

        /** The longest catch, in yards. */
        fun longest(longest: Long?) = longest(JsonField.ofNullable(longest))

        /** Sets [Builder.longest] to an arbitrary JSON value. */
        fun longest(longest: JsonField<Long>) = apply { this.longest = longest }

        /** Catches. */
        fun receptions(receptions: Long?) = receptions(JsonField.ofNullable(receptions))

        /** Sets [Builder.receptions] to an arbitrary JSON value. */
        fun receptions(receptions: JsonField<Long>) = apply { this.receptions = receptions }

        /** Passes thrown to the player. */
        fun targets(targets: Long?) = targets(JsonField.ofNullable(targets))

        /** Sets [Builder.targets] to an arbitrary JSON value. */
        fun targets(targets: JsonField<Long>) = apply { this.targets = targets }

        /** Receiving touchdowns. */
        fun touchdowns(touchdowns: Long?) = touchdowns(JsonField.ofNullable(touchdowns))

        /** Sets [Builder.touchdowns] to an arbitrary JSON value. */
        fun touchdowns(touchdowns: JsonField<Long>) = apply { this.touchdowns = touchdowns }

        /** Receiving yards. */
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
         * Returns an immutable instance of [FootballPlayerStatsReceiving].
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): FootballPlayerStatsReceiving =
            FootballPlayerStatsReceiving(
                checkRequired("avgYards", avgYards),
                checkRequired("longest", longest),
                checkRequired("receptions", receptions),
                checkRequired("targets", targets),
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
    fun validate(): FootballPlayerStatsReceiving = apply {
        if (validated) {
            return@apply
        }

        avgYards()
        longest()
        receptions()
        targets()
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
        (if (avgYards.asKnown() == null) 0 else 1) +
            (if (longest.asKnown() == null) 0 else 1) +
            (if (receptions.asKnown() == null) 0 else 1) +
            (if (targets.asKnown() == null) 0 else 1) +
            (if (touchdowns.asKnown() == null) 0 else 1) +
            (if (yards.asKnown() == null) 0 else 1)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is FootballPlayerStatsReceiving &&
            avgYards == other.avgYards &&
            longest == other.longest &&
            receptions == other.receptions &&
            targets == other.targets &&
            touchdowns == other.touchdowns &&
            yards == other.yards &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(avgYards, longest, receptions, targets, touchdowns, yards, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "FootballPlayerStatsReceiving{avgYards=$avgYards, longest=$longest, receptions=$receptions, targets=$targets, touchdowns=$touchdowns, yards=$yards, additionalProperties=$additionalProperties}"
}
