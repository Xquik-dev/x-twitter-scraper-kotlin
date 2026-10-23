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

/** Down & distance now, or null before kickoff. */
class FootballScoreboardSituation
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val down: JsonField<Long>,
    private val yardsToGo: JsonField<Long>,
    private val yardline: JsonField<Long>,
    private val yardlineTeamId: JsonField<String>,
    private val possessionTeamId: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("down") @ExcludeMissing down: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("yardsToGo") @ExcludeMissing yardsToGo: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("yardline") @ExcludeMissing yardline: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("yardlineTeamId") @ExcludeMissing yardlineTeamId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("possessionTeamId") @ExcludeMissing possessionTeamId: JsonField<String> = JsonMissing.of(),
    ) : this(down, yardsToGo, yardline, yardlineTeamId, possessionTeamId, mutableMapOf())

    /** The current down, from 1 to 4. */
    fun down(): Long? = down.getNullable("down")

    /** The yards the offense needs now for a first down. */
    fun yardsToGo(): Long? = yardsToGo.getNullable("yardsToGo")

    /** The ball's current yard line. */
    fun yardline(): Long? = yardline.getNullable("yardline")

    /** The team whose half the ball is in, a key of teams. */
    fun yardlineTeamId(): String? = yardlineTeamId.getNullable("yardlineTeamId")

    /** The team with the ball, a key of teams. */
    fun possessionTeamId(): String? = possessionTeamId.getNullable("possessionTeamId")

    /** Returns the raw JSON value of [down]. */
    @JsonProperty("down") @ExcludeMissing fun _down(): JsonField<Long> = down

    /** Returns the raw JSON value of [yardsToGo]. */
    @JsonProperty("yardsToGo") @ExcludeMissing fun _yardsToGo(): JsonField<Long> = yardsToGo

    /** Returns the raw JSON value of [yardline]. */
    @JsonProperty("yardline") @ExcludeMissing fun _yardline(): JsonField<Long> = yardline

    /** Returns the raw JSON value of [yardlineTeamId]. */
    @JsonProperty("yardlineTeamId") @ExcludeMissing fun _yardlineTeamId(): JsonField<String> = yardlineTeamId

    /** Returns the raw JSON value of [possessionTeamId]. */
    @JsonProperty("possessionTeamId") @ExcludeMissing fun _possessionTeamId(): JsonField<String> = possessionTeamId

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

        /** Returns a mutable builder for constructing an instance of [FootballScoreboardSituation]. */
        fun builder() = Builder()
    }

    /** A builder for [FootballScoreboardSituation]. */
    class Builder internal constructor() {

        private var down: JsonField<Long>? = null
        private var yardsToGo: JsonField<Long>? = null
        private var yardline: JsonField<Long>? = null
        private var yardlineTeamId: JsonField<String>? = null
        private var possessionTeamId: JsonField<String>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        internal fun from(footballScoreboardSituation: FootballScoreboardSituation) = apply {
            down = footballScoreboardSituation.down
            yardsToGo = footballScoreboardSituation.yardsToGo
            yardline = footballScoreboardSituation.yardline
            yardlineTeamId = footballScoreboardSituation.yardlineTeamId
            possessionTeamId = footballScoreboardSituation.possessionTeamId
            additionalProperties = footballScoreboardSituation.additionalProperties.toMutableMap()
        }

        /** The current down, from 1 to 4. */
        fun down(down: Long?) = down(JsonField.ofNullable(down))

        /** Sets [Builder.down] to an arbitrary JSON value. */
        fun down(down: JsonField<Long>) = apply { this.down = down }

        /** The yards the offense needs now for a first down. */
        fun yardsToGo(yardsToGo: Long?) = yardsToGo(JsonField.ofNullable(yardsToGo))

        /** Sets [Builder.yardsToGo] to an arbitrary JSON value. */
        fun yardsToGo(yardsToGo: JsonField<Long>) = apply { this.yardsToGo = yardsToGo }

        /** The ball's current yard line. */
        fun yardline(yardline: Long?) = yardline(JsonField.ofNullable(yardline))

        /** Sets [Builder.yardline] to an arbitrary JSON value. */
        fun yardline(yardline: JsonField<Long>) = apply { this.yardline = yardline }

        /** The team whose half the ball is in, a key of teams. */
        fun yardlineTeamId(yardlineTeamId: String?) = yardlineTeamId(JsonField.ofNullable(yardlineTeamId))

        /** Sets [Builder.yardlineTeamId] to an arbitrary JSON value. */
        fun yardlineTeamId(yardlineTeamId: JsonField<String>) = apply { this.yardlineTeamId = yardlineTeamId }

        /** The team with the ball, a key of teams. */
        fun possessionTeamId(possessionTeamId: String?) = possessionTeamId(JsonField.ofNullable(possessionTeamId))

        /** Sets [Builder.possessionTeamId] to an arbitrary JSON value. */
        fun possessionTeamId(possessionTeamId: JsonField<String>) = apply { this.possessionTeamId = possessionTeamId }

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
         * Returns an immutable instance of [FootballScoreboardSituation].
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): FootballScoreboardSituation =
            FootballScoreboardSituation(
                checkRequired("down", down),
                checkRequired("yardsToGo", yardsToGo),
                checkRequired("yardline", yardline),
                checkRequired("yardlineTeamId", yardlineTeamId),
                checkRequired("possessionTeamId", possessionTeamId),
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
    fun validate(): FootballScoreboardSituation = apply {
        if (validated) {
            return@apply
        }

        down()
        yardsToGo()
        yardline()
        yardlineTeamId()
        possessionTeamId()
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
        (if (down.asKnown() == null) 0 else 1) +
            (if (yardsToGo.asKnown() == null) 0 else 1) +
            (if (yardline.asKnown() == null) 0 else 1) +
            (if (yardlineTeamId.asKnown() == null) 0 else 1) +
            (if (possessionTeamId.asKnown() == null) 0 else 1)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is FootballScoreboardSituation &&
            down == other.down &&
            yardsToGo == other.yardsToGo &&
            yardline == other.yardline &&
            yardlineTeamId == other.yardlineTeamId &&
            possessionTeamId == other.possessionTeamId &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(down, yardsToGo, yardline, yardlineTeamId, possessionTeamId, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "FootballScoreboardSituation{down=$down, yardsToGo=$yardsToGo, yardline=$yardline, yardlineTeamId=$yardlineTeamId, possessionTeamId=$possessionTeamId, additionalProperties=$additionalProperties}"
}
