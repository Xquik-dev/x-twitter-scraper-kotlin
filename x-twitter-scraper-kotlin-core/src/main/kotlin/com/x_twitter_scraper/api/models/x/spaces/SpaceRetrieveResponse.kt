// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.spaces

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

class SpaceRetrieveResponse
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val space: JsonField<Space>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("space") @ExcludeMissing space: JsonField<Space> = JsonMissing.of()
    ) : this(space, mutableMapOf())

    /**
     * X Space
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun space(): Space = space.getRequired("space")

    /**
     * Returns the raw JSON value of [space].
     *
     * Unlike [space], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("space") @ExcludeMissing fun _space(): JsonField<Space> = space

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
         * Returns a mutable builder for constructing an instance of [SpaceRetrieveResponse].
         *
         * The following fields are required:
         * ```kotlin
         * .space()
         * ```
         */
        fun builder() = Builder()
    }

    /** A builder for [SpaceRetrieveResponse]. */
    class Builder internal constructor() {

        private var space: JsonField<Space>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        internal fun from(spaceRetrieveResponse: SpaceRetrieveResponse) = apply {
            space = spaceRetrieveResponse.space
            additionalProperties = spaceRetrieveResponse.additionalProperties.toMutableMap()
        }

        /** X Space */
        fun space(space: Space) = space(JsonField.of(space))

        /** Sets [Builder.space] to an arbitrary JSON value. */
        fun space(space: JsonField<Space>) = apply { this.space = space }

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
         * Returns an immutable instance of [SpaceRetrieveResponse].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```kotlin
         * .space()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): SpaceRetrieveResponse =
            SpaceRetrieveResponse(
                checkRequired("space", space),
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
    fun validate(): SpaceRetrieveResponse = apply {
        if (validated) {
            return@apply
        }

        space().validate()
        validated = true
    }

    fun isValid(): Boolean =
        try {
            validate()
            true
        } catch (e: XTwitterScraperInvalidDataException) {
            false
        }

    internal fun validity(): Int = (space.asKnown()?.validity() ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is SpaceRetrieveResponse &&
            space == other.space &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(space, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "SpaceRetrieveResponse{space=$space, additionalProperties=$additionalProperties}"
}
