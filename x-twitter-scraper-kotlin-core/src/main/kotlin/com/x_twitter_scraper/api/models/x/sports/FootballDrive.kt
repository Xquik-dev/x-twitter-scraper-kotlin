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
import com.x_twitter_scraper.api.core.checkKnown
import com.x_twitter_scraper.api.core.checkRequired
import com.x_twitter_scraper.api.core.toImmutable
import com.x_twitter_scraper.api.errors.XTwitterScraperInvalidDataException
import java.util.Collections
import java.util.Objects

/** A drive, with its result & plays. */
class FootballDrive
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val sequence: JsonField<Long>,
    private val offense: JsonField<String>,
    private val defense: JsonField<String>,
    private val startClock: JsonField<String>,
    private val endClock: JsonField<String>,
    private val startReason: JsonField<String>,
    private val endReason: JsonField<String>,
    private val playCount: JsonField<Long>,
    private val firstDowns: JsonField<Long>,
    private val netYards: JsonField<Long>,
    private val scoring: JsonField<Boolean>,
    private val plays: JsonField<List<FootballPlay>>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("sequence") @ExcludeMissing sequence: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("offense") @ExcludeMissing offense: JsonField<String> = JsonMissing.of(),
        @JsonProperty("defense") @ExcludeMissing defense: JsonField<String> = JsonMissing.of(),
        @JsonProperty("startClock") @ExcludeMissing startClock: JsonField<String> = JsonMissing.of(),
        @JsonProperty("endClock") @ExcludeMissing endClock: JsonField<String> = JsonMissing.of(),
        @JsonProperty("startReason") @ExcludeMissing startReason: JsonField<String> = JsonMissing.of(),
        @JsonProperty("endReason") @ExcludeMissing endReason: JsonField<String> = JsonMissing.of(),
        @JsonProperty("playCount") @ExcludeMissing playCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("firstDowns") @ExcludeMissing firstDowns: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("netYards") @ExcludeMissing netYards: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("scoring") @ExcludeMissing scoring: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("plays") @ExcludeMissing plays: JsonField<List<FootballPlay>> = JsonMissing.of(),
    ) : this(id, sequence, offense, defense, startClock, endClock, startReason, endReason, playCount, firstDowns, netYards, scoring, plays, mutableMapOf())

    /** X's ID for the drive within the game. */
    fun id(): String? = id.getNullable("id")

    /** The drive's place in the game, from 1. */
    fun sequence(): Long? = sequence.getNullable("sequence")

    /** The team that drove. Home or Away in NFL games, the team's alternateId in college games. */
    fun offense(): String? = offense.getNullable("offense")

    /** The team on defense. Home or Away in NFL games, the team's alternateId in college games. */
    fun defense(): String? = defense.getNullable("defense")

    /** The game clock when the drive started, as HH:MM:SS. */
    fun startClock(): String? = startClock.getNullable("startClock")

    /** The game clock when the drive ended, as HH:MM:SS. */
    fun endClock(): String? = endClock.getNullable("endClock")

    /** How the drive started, such as Kickoff or Punt. */
    fun startReason(): String? = startReason.getNullable("startReason")

    /** How the drive ended, such as Touchdown or Punt. */
    fun endReason(): String? = endReason.getNullable("endReason")

    /** The drive's plays. */
    fun playCount(): Long? = playCount.getNullable("playCount")

    /** The first downs the drive gained. */
    fun firstDowns(): Long? = firstDowns.getNullable("firstDowns")

    /** The yards the drive gained. */
    fun netYards(): Long? = netYards.getNullable("netYards")

    /** Whether the drive scored. */
    fun scoring(): Boolean? = scoring.getNullable("scoring")

    /** The drive's plays, in order. */
    fun plays(): List<FootballPlay> = plays.getRequired("plays")

    /** Returns the raw JSON value of [id]. */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    /** Returns the raw JSON value of [sequence]. */
    @JsonProperty("sequence") @ExcludeMissing fun _sequence(): JsonField<Long> = sequence

    /** Returns the raw JSON value of [offense]. */
    @JsonProperty("offense") @ExcludeMissing fun _offense(): JsonField<String> = offense

    /** Returns the raw JSON value of [defense]. */
    @JsonProperty("defense") @ExcludeMissing fun _defense(): JsonField<String> = defense

    /** Returns the raw JSON value of [startClock]. */
    @JsonProperty("startClock") @ExcludeMissing fun _startClock(): JsonField<String> = startClock

    /** Returns the raw JSON value of [endClock]. */
    @JsonProperty("endClock") @ExcludeMissing fun _endClock(): JsonField<String> = endClock

    /** Returns the raw JSON value of [startReason]. */
    @JsonProperty("startReason") @ExcludeMissing fun _startReason(): JsonField<String> = startReason

    /** Returns the raw JSON value of [endReason]. */
    @JsonProperty("endReason") @ExcludeMissing fun _endReason(): JsonField<String> = endReason

    /** Returns the raw JSON value of [playCount]. */
    @JsonProperty("playCount") @ExcludeMissing fun _playCount(): JsonField<Long> = playCount

    /** Returns the raw JSON value of [firstDowns]. */
    @JsonProperty("firstDowns") @ExcludeMissing fun _firstDowns(): JsonField<Long> = firstDowns

    /** Returns the raw JSON value of [netYards]. */
    @JsonProperty("netYards") @ExcludeMissing fun _netYards(): JsonField<Long> = netYards

    /** Returns the raw JSON value of [scoring]. */
    @JsonProperty("scoring") @ExcludeMissing fun _scoring(): JsonField<Boolean> = scoring

    /** Returns the raw JSON value of [plays]. */
    @JsonProperty("plays") @ExcludeMissing fun _plays(): JsonField<List<FootballPlay>> = plays

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

        /** Returns a mutable builder for constructing an instance of [FootballDrive]. */
        fun builder() = Builder()
    }

    /** A builder for [FootballDrive]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var sequence: JsonField<Long>? = null
        private var offense: JsonField<String>? = null
        private var defense: JsonField<String>? = null
        private var startClock: JsonField<String>? = null
        private var endClock: JsonField<String>? = null
        private var startReason: JsonField<String>? = null
        private var endReason: JsonField<String>? = null
        private var playCount: JsonField<Long>? = null
        private var firstDowns: JsonField<Long>? = null
        private var netYards: JsonField<Long>? = null
        private var scoring: JsonField<Boolean>? = null
        private var plays: JsonField<MutableList<FootballPlay>>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        internal fun from(footballDrive: FootballDrive) = apply {
            id = footballDrive.id
            sequence = footballDrive.sequence
            offense = footballDrive.offense
            defense = footballDrive.defense
            startClock = footballDrive.startClock
            endClock = footballDrive.endClock
            startReason = footballDrive.startReason
            endReason = footballDrive.endReason
            playCount = footballDrive.playCount
            firstDowns = footballDrive.firstDowns
            netYards = footballDrive.netYards
            scoring = footballDrive.scoring
            plays = footballDrive.plays.map { it.toMutableList() }
            additionalProperties = footballDrive.additionalProperties.toMutableMap()
        }

        /** X's ID for the drive within the game. */
        fun id(id: String?) = id(JsonField.ofNullable(id))

        /** Sets [Builder.id] to an arbitrary JSON value. */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /** The drive's place in the game, from 1. */
        fun sequence(sequence: Long?) = sequence(JsonField.ofNullable(sequence))

        /** Sets [Builder.sequence] to an arbitrary JSON value. */
        fun sequence(sequence: JsonField<Long>) = apply { this.sequence = sequence }

        /** The team that drove. Home or Away in NFL games, the team's alternateId in college games. */
        fun offense(offense: String?) = offense(JsonField.ofNullable(offense))

        /** Sets [Builder.offense] to an arbitrary JSON value. */
        fun offense(offense: JsonField<String>) = apply { this.offense = offense }

        /** The team on defense. Home or Away in NFL games, the team's alternateId in college games. */
        fun defense(defense: String?) = defense(JsonField.ofNullable(defense))

        /** Sets [Builder.defense] to an arbitrary JSON value. */
        fun defense(defense: JsonField<String>) = apply { this.defense = defense }

        /** The game clock when the drive started, as HH:MM:SS. */
        fun startClock(startClock: String?) = startClock(JsonField.ofNullable(startClock))

        /** Sets [Builder.startClock] to an arbitrary JSON value. */
        fun startClock(startClock: JsonField<String>) = apply { this.startClock = startClock }

        /** The game clock when the drive ended, as HH:MM:SS. */
        fun endClock(endClock: String?) = endClock(JsonField.ofNullable(endClock))

        /** Sets [Builder.endClock] to an arbitrary JSON value. */
        fun endClock(endClock: JsonField<String>) = apply { this.endClock = endClock }

        /** How the drive started, such as Kickoff or Punt. */
        fun startReason(startReason: String?) = startReason(JsonField.ofNullable(startReason))

        /** Sets [Builder.startReason] to an arbitrary JSON value. */
        fun startReason(startReason: JsonField<String>) = apply { this.startReason = startReason }

        /** How the drive ended, such as Touchdown or Punt. */
        fun endReason(endReason: String?) = endReason(JsonField.ofNullable(endReason))

        /** Sets [Builder.endReason] to an arbitrary JSON value. */
        fun endReason(endReason: JsonField<String>) = apply { this.endReason = endReason }

        /** The drive's plays. */
        fun playCount(playCount: Long?) = playCount(JsonField.ofNullable(playCount))

        /** Sets [Builder.playCount] to an arbitrary JSON value. */
        fun playCount(playCount: JsonField<Long>) = apply { this.playCount = playCount }

        /** The first downs the drive gained. */
        fun firstDowns(firstDowns: Long?) = firstDowns(JsonField.ofNullable(firstDowns))

        /** Sets [Builder.firstDowns] to an arbitrary JSON value. */
        fun firstDowns(firstDowns: JsonField<Long>) = apply { this.firstDowns = firstDowns }

        /** The yards the drive gained. */
        fun netYards(netYards: Long?) = netYards(JsonField.ofNullable(netYards))

        /** Sets [Builder.netYards] to an arbitrary JSON value. */
        fun netYards(netYards: JsonField<Long>) = apply { this.netYards = netYards }

        /** Whether the drive scored. */
        fun scoring(scoring: Boolean?) = scoring(JsonField.ofNullable(scoring))

        /** Sets [Builder.scoring] to an arbitrary JSON value. */
        fun scoring(scoring: JsonField<Boolean>) = apply { this.scoring = scoring }

        /** The drive's plays, in order. */
        fun plays(plays: List<FootballPlay>) = plays(JsonField.of(plays))

        /** Sets [Builder.plays] to an arbitrary JSON value. */
        fun plays(plays: JsonField<List<FootballPlay>>) = apply { this.plays = plays.map { it.toMutableList() } }

        /** Adds a single [FootballPlay] to [plays]. */
        fun addPlay(play: FootballPlay) = apply {
            plays =
                (plays ?: JsonField.of(mutableListOf())).also {
                    checkKnown("plays", it).add(play)
                }
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
         * Returns an immutable instance of [FootballDrive].
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): FootballDrive =
            FootballDrive(
                checkRequired("id", id),
                checkRequired("sequence", sequence),
                checkRequired("offense", offense),
                checkRequired("defense", defense),
                checkRequired("startClock", startClock),
                checkRequired("endClock", endClock),
                checkRequired("startReason", startReason),
                checkRequired("endReason", endReason),
                checkRequired("playCount", playCount),
                checkRequired("firstDowns", firstDowns),
                checkRequired("netYards", netYards),
                checkRequired("scoring", scoring),
                checkRequired("plays", plays).map { it.toImmutable() },
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
    fun validate(): FootballDrive = apply {
        if (validated) {
            return@apply
        }

        id()
        sequence()
        offense()
        defense()
        startClock()
        endClock()
        startReason()
        endReason()
        playCount()
        firstDowns()
        netYards()
        scoring()
        plays().forEach { it.validate() }
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
            (if (sequence.asKnown() == null) 0 else 1) +
            (if (offense.asKnown() == null) 0 else 1) +
            (if (defense.asKnown() == null) 0 else 1) +
            (if (startClock.asKnown() == null) 0 else 1) +
            (if (endClock.asKnown() == null) 0 else 1) +
            (if (startReason.asKnown() == null) 0 else 1) +
            (if (endReason.asKnown() == null) 0 else 1) +
            (if (playCount.asKnown() == null) 0 else 1) +
            (if (firstDowns.asKnown() == null) 0 else 1) +
            (if (netYards.asKnown() == null) 0 else 1) +
            (if (scoring.asKnown() == null) 0 else 1) +
            (plays.asKnown()?.sumOf { it.validity() } ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is FootballDrive &&
            id == other.id &&
            sequence == other.sequence &&
            offense == other.offense &&
            defense == other.defense &&
            startClock == other.startClock &&
            endClock == other.endClock &&
            startReason == other.startReason &&
            endReason == other.endReason &&
            playCount == other.playCount &&
            firstDowns == other.firstDowns &&
            netYards == other.netYards &&
            scoring == other.scoring &&
            plays == other.plays &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(id, sequence, offense, defense, startClock, endClock, startReason, endReason, playCount, firstDowns, netYards, scoring, plays, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "FootballDrive{id=$id, sequence=$sequence, offense=$offense, defense=$defense, startClock=$startClock, endClock=$endClock, startReason=$startReason, endReason=$endReason, playCount=$playCount, firstDowns=$firstDowns, netYards=$netYards, scoring=$scoring, plays=$plays, additionalProperties=$additionalProperties}"
}
