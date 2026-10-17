// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.broadcasts

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
import com.x_twitter_scraper.api.models.Broadcast
import java.util.Collections
import java.util.Objects

class BroadcastRetrieveResponse
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val broadcast: JsonField<Broadcast>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("broadcast")
        @ExcludeMissing
        broadcast: JsonField<Broadcast> = JsonMissing.of()
    ) : this(broadcast, mutableMapOf())

    /**
     * X broadcast, a live video
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun broadcast(): Broadcast = broadcast.getRequired("broadcast")

    /**
     * Returns the raw JSON value of [broadcast].
     *
     * Unlike [broadcast], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("broadcast") @ExcludeMissing fun _broadcast(): JsonField<Broadcast> = broadcast

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
         * Returns a mutable builder for constructing an instance of [BroadcastRetrieveResponse].
         *
         * The following fields are required:
         * ```kotlin
         * .broadcast()
         * ```
         */
        fun builder() = Builder()
    }

    /** A builder for [BroadcastRetrieveResponse]. */
    class Builder internal constructor() {

        private var broadcast: JsonField<Broadcast>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        internal fun from(broadcastRetrieveResponse: BroadcastRetrieveResponse) = apply {
            broadcast = broadcastRetrieveResponse.broadcast
            additionalProperties = broadcastRetrieveResponse.additionalProperties.toMutableMap()
        }

        /** X broadcast, a live video */
        fun broadcast(broadcast: Broadcast) = broadcast(JsonField.of(broadcast))

        /** Sets [Builder.broadcast] to an arbitrary JSON value. */
        fun broadcast(broadcast: JsonField<Broadcast>) = apply { this.broadcast = broadcast }

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
         * Returns an immutable instance of [BroadcastRetrieveResponse].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```kotlin
         * .broadcast()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BroadcastRetrieveResponse =
            BroadcastRetrieveResponse(
                checkRequired("broadcast", broadcast),
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
    fun validate(): BroadcastRetrieveResponse = apply {
        if (validated) {
            return@apply
        }

        broadcast().validate()
        validated = true
    }

    fun isValid(): Boolean =
        try {
            validate()
            true
        } catch (e: XTwitterScraperInvalidDataException) {
            false
        }

    internal fun validity(): Int = (broadcast.asKnown()?.validity() ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BroadcastRetrieveResponse &&
            broadcast == other.broadcast &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(broadcast, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BroadcastRetrieveResponse{broadcast=$broadcast, additionalProperties=$additionalProperties}"
}
