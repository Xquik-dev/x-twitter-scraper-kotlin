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

/** A team's totals for 1 game. */
class FootballTeamTotals
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val firstDowns: JsonField<Long>,
    private val thirdDowns: JsonField<FootballTeamTotalsThirdDowns>,
    private val fumbles: JsonField<FootballFumbleStats>,
    private val passing: JsonField<FootballPassingStats>,
    private val penalties: JsonField<FootballTeamTotalsPenalties>,
    private val rushing: JsonField<FootballRushingStats>,
    private val timeOfPossessionSeconds: JsonField<Long>,
    private val totalYards: JsonField<Long>,
    private val turnovers: JsonField<Long>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("firstDowns") @ExcludeMissing firstDowns: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("thirdDowns") @ExcludeMissing thirdDowns: JsonField<FootballTeamTotalsThirdDowns> = JsonMissing.of(),
        @JsonProperty("fumbles") @ExcludeMissing fumbles: JsonField<FootballFumbleStats> = JsonMissing.of(),
        @JsonProperty("passing") @ExcludeMissing passing: JsonField<FootballPassingStats> = JsonMissing.of(),
        @JsonProperty("penalties") @ExcludeMissing penalties: JsonField<FootballTeamTotalsPenalties> = JsonMissing.of(),
        @JsonProperty("rushing") @ExcludeMissing rushing: JsonField<FootballRushingStats> = JsonMissing.of(),
        @JsonProperty("timeOfPossessionSeconds") @ExcludeMissing timeOfPossessionSeconds: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("totalYards") @ExcludeMissing totalYards: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("turnovers") @ExcludeMissing turnovers: JsonField<Long> = JsonMissing.of(),
    ) : this(firstDowns, thirdDowns, fumbles, passing, penalties, rushing, timeOfPossessionSeconds, totalYards, turnovers, mutableMapOf())

    /** First downs. */
    fun firstDowns(): Long? = firstDowns.getNullable("firstDowns")

    /** Third downs tried & converted. */
    fun thirdDowns(): FootballTeamTotalsThirdDowns? = thirdDowns.getNullable("thirdDowns")

    /** The team's fumbles, or null. */
    fun fumbles(): FootballFumbleStats? = fumbles.getNullable("fumbles")

    /** The team's passing, or null. */
    fun passing(): FootballPassingStats? = passing.getNullable("passing")

    /** Penalties against the team. */
    fun penalties(): FootballTeamTotalsPenalties? = penalties.getNullable("penalties")

    /** The team's rushing, or null. */
    fun rushing(): FootballRushingStats? = rushing.getNullable("rushing")

    /** Time with the ball, in seconds. */
    fun timeOfPossessionSeconds(): Long? = timeOfPossessionSeconds.getNullable("timeOfPossessionSeconds")

    /** Total yards. */
    fun totalYards(): Long? = totalYards.getNullable("totalYards")

    /** Turnovers. */
    fun turnovers(): Long? = turnovers.getNullable("turnovers")

    /** Returns the raw JSON value of [firstDowns]. */
    @JsonProperty("firstDowns") @ExcludeMissing fun _firstDowns(): JsonField<Long> = firstDowns

    /** Returns the raw JSON value of [thirdDowns]. */
    @JsonProperty("thirdDowns") @ExcludeMissing fun _thirdDowns(): JsonField<FootballTeamTotalsThirdDowns> = thirdDowns

    /** Returns the raw JSON value of [fumbles]. */
    @JsonProperty("fumbles") @ExcludeMissing fun _fumbles(): JsonField<FootballFumbleStats> = fumbles

    /** Returns the raw JSON value of [passing]. */
    @JsonProperty("passing") @ExcludeMissing fun _passing(): JsonField<FootballPassingStats> = passing

    /** Returns the raw JSON value of [penalties]. */
    @JsonProperty("penalties") @ExcludeMissing fun _penalties(): JsonField<FootballTeamTotalsPenalties> = penalties

    /** Returns the raw JSON value of [rushing]. */
    @JsonProperty("rushing") @ExcludeMissing fun _rushing(): JsonField<FootballRushingStats> = rushing

    /** Returns the raw JSON value of [timeOfPossessionSeconds]. */
    @JsonProperty("timeOfPossessionSeconds") @ExcludeMissing fun _timeOfPossessionSeconds(): JsonField<Long> = timeOfPossessionSeconds

    /** Returns the raw JSON value of [totalYards]. */
    @JsonProperty("totalYards") @ExcludeMissing fun _totalYards(): JsonField<Long> = totalYards

    /** Returns the raw JSON value of [turnovers]. */
    @JsonProperty("turnovers") @ExcludeMissing fun _turnovers(): JsonField<Long> = turnovers

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

        /** Returns a mutable builder for constructing an instance of [FootballTeamTotals]. */
        fun builder() = Builder()
    }

    /** A builder for [FootballTeamTotals]. */
    class Builder internal constructor() {

        private var firstDowns: JsonField<Long>? = null
        private var thirdDowns: JsonField<FootballTeamTotalsThirdDowns>? = null
        private var fumbles: JsonField<FootballFumbleStats>? = null
        private var passing: JsonField<FootballPassingStats>? = null
        private var penalties: JsonField<FootballTeamTotalsPenalties>? = null
        private var rushing: JsonField<FootballRushingStats>? = null
        private var timeOfPossessionSeconds: JsonField<Long>? = null
        private var totalYards: JsonField<Long>? = null
        private var turnovers: JsonField<Long>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        internal fun from(footballTeamTotals: FootballTeamTotals) = apply {
            firstDowns = footballTeamTotals.firstDowns
            thirdDowns = footballTeamTotals.thirdDowns
            fumbles = footballTeamTotals.fumbles
            passing = footballTeamTotals.passing
            penalties = footballTeamTotals.penalties
            rushing = footballTeamTotals.rushing
            timeOfPossessionSeconds = footballTeamTotals.timeOfPossessionSeconds
            totalYards = footballTeamTotals.totalYards
            turnovers = footballTeamTotals.turnovers
            additionalProperties = footballTeamTotals.additionalProperties.toMutableMap()
        }

        /** First downs. */
        fun firstDowns(firstDowns: Long?) = firstDowns(JsonField.ofNullable(firstDowns))

        /** Sets [Builder.firstDowns] to an arbitrary JSON value. */
        fun firstDowns(firstDowns: JsonField<Long>) = apply { this.firstDowns = firstDowns }

        /** Third downs tried & converted. */
        fun thirdDowns(thirdDowns: FootballTeamTotalsThirdDowns?) = thirdDowns(JsonField.ofNullable(thirdDowns))

        /** Sets [Builder.thirdDowns] to an arbitrary JSON value. */
        fun thirdDowns(thirdDowns: JsonField<FootballTeamTotalsThirdDowns>) = apply { this.thirdDowns = thirdDowns }

        /** The team's fumbles, or null. */
        fun fumbles(fumbles: FootballFumbleStats?) = fumbles(JsonField.ofNullable(fumbles))

        /** Sets [Builder.fumbles] to an arbitrary JSON value. */
        fun fumbles(fumbles: JsonField<FootballFumbleStats>) = apply { this.fumbles = fumbles }

        /** The team's passing, or null. */
        fun passing(passing: FootballPassingStats?) = passing(JsonField.ofNullable(passing))

        /** Sets [Builder.passing] to an arbitrary JSON value. */
        fun passing(passing: JsonField<FootballPassingStats>) = apply { this.passing = passing }

        /** Penalties against the team. */
        fun penalties(penalties: FootballTeamTotalsPenalties?) = penalties(JsonField.ofNullable(penalties))

        /** Sets [Builder.penalties] to an arbitrary JSON value. */
        fun penalties(penalties: JsonField<FootballTeamTotalsPenalties>) = apply { this.penalties = penalties }

        /** The team's rushing, or null. */
        fun rushing(rushing: FootballRushingStats?) = rushing(JsonField.ofNullable(rushing))

        /** Sets [Builder.rushing] to an arbitrary JSON value. */
        fun rushing(rushing: JsonField<FootballRushingStats>) = apply { this.rushing = rushing }

        /** Time with the ball, in seconds. */
        fun timeOfPossessionSeconds(timeOfPossessionSeconds: Long?) = timeOfPossessionSeconds(JsonField.ofNullable(timeOfPossessionSeconds))

        /** Sets [Builder.timeOfPossessionSeconds] to an arbitrary JSON value. */
        fun timeOfPossessionSeconds(timeOfPossessionSeconds: JsonField<Long>) = apply { this.timeOfPossessionSeconds = timeOfPossessionSeconds }

        /** Total yards. */
        fun totalYards(totalYards: Long?) = totalYards(JsonField.ofNullable(totalYards))

        /** Sets [Builder.totalYards] to an arbitrary JSON value. */
        fun totalYards(totalYards: JsonField<Long>) = apply { this.totalYards = totalYards }

        /** Turnovers. */
        fun turnovers(turnovers: Long?) = turnovers(JsonField.ofNullable(turnovers))

        /** Sets [Builder.turnovers] to an arbitrary JSON value. */
        fun turnovers(turnovers: JsonField<Long>) = apply { this.turnovers = turnovers }

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
         * Returns an immutable instance of [FootballTeamTotals].
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): FootballTeamTotals =
            FootballTeamTotals(
                checkRequired("firstDowns", firstDowns),
                checkRequired("thirdDowns", thirdDowns),
                checkRequired("fumbles", fumbles),
                checkRequired("passing", passing),
                checkRequired("penalties", penalties),
                checkRequired("rushing", rushing),
                checkRequired("timeOfPossessionSeconds", timeOfPossessionSeconds),
                checkRequired("totalYards", totalYards),
                checkRequired("turnovers", turnovers),
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
    fun validate(): FootballTeamTotals = apply {
        if (validated) {
            return@apply
        }

        firstDowns()
        thirdDowns()?.validate()
        fumbles()?.validate()
        passing()?.validate()
        penalties()?.validate()
        rushing()?.validate()
        timeOfPossessionSeconds()
        totalYards()
        turnovers()
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
        (if (firstDowns.asKnown() == null) 0 else 1) +
            (thirdDowns.asKnown()?.validity() ?: 0) +
            (fumbles.asKnown()?.validity() ?: 0) +
            (passing.asKnown()?.validity() ?: 0) +
            (penalties.asKnown()?.validity() ?: 0) +
            (rushing.asKnown()?.validity() ?: 0) +
            (if (timeOfPossessionSeconds.asKnown() == null) 0 else 1) +
            (if (totalYards.asKnown() == null) 0 else 1) +
            (if (turnovers.asKnown() == null) 0 else 1)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is FootballTeamTotals &&
            firstDowns == other.firstDowns &&
            thirdDowns == other.thirdDowns &&
            fumbles == other.fumbles &&
            passing == other.passing &&
            penalties == other.penalties &&
            rushing == other.rushing &&
            timeOfPossessionSeconds == other.timeOfPossessionSeconds &&
            totalYards == other.totalYards &&
            turnovers == other.turnovers &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(firstDowns, thirdDowns, fumbles, passing, penalties, rushing, timeOfPossessionSeconds, totalYards, turnovers, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "FootballTeamTotals{firstDowns=$firstDowns, thirdDowns=$thirdDowns, fumbles=$fumbles, passing=$passing, penalties=$penalties, rushing=$rushing, timeOfPossessionSeconds=$timeOfPossessionSeconds, totalYards=$totalYards, turnovers=$turnovers, additionalProperties=$additionalProperties}"
}
