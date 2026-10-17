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
import com.x_twitter_scraper.api.models.Broadcast
import java.util.Collections
import java.util.Objects

/**
 * Says whether a user broadcasts live video on X now. `broadcast` is their latest broadcast, live
 * or ended, and null when X shows none.
 */
class UserRetrieveLiveResponse
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val broadcast: JsonField<Broadcast>,
    private val live: JsonField<Boolean>,
    private val userId: JsonField<String>,
    private val userName: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("broadcast")
        @ExcludeMissing
        broadcast: JsonField<Broadcast> = JsonMissing.of(),
        @JsonProperty("live") @ExcludeMissing live: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("userId") @ExcludeMissing userId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("userName") @ExcludeMissing userName: JsonField<String> = JsonMissing.of(),
    ) : this(broadcast, live, userId, userName, mutableMapOf())

    /**
     * Latest broadcast, live or ended. Null when X shows none.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun broadcast(): Broadcast? = broadcast.getNullable("broadcast")

    /**
     * True while the user's latest broadcast is running.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun live(): Boolean = live.getRequired("live")

    /**
     * ID of the user.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun userId(): String = userId.getRequired("userId")

    /**
     * Username of the user.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun userName(): String = userName.getRequired("userName")

    /**
     * Returns the raw JSON value of [broadcast].
     *
     * Unlike [broadcast], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("broadcast") @ExcludeMissing fun _broadcast(): JsonField<Broadcast> = broadcast

    /**
     * Returns the raw JSON value of [live].
     *
     * Unlike [live], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("live") @ExcludeMissing fun _live(): JsonField<Boolean> = live

    /**
     * Returns the raw JSON value of [userId].
     *
     * Unlike [userId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("userId") @ExcludeMissing fun _userId(): JsonField<String> = userId

    /**
     * Returns the raw JSON value of [userName].
     *
     * Unlike [userName], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("userName") @ExcludeMissing fun _userName(): JsonField<String> = userName

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
         * Returns a mutable builder for constructing an instance of [UserRetrieveLiveResponse].
         *
         * The following fields are required:
         * ```kotlin
         * .broadcast()
         * .live()
         * .userId()
         * .userName()
         * ```
         */
        fun builder() = Builder()
    }

    /** A builder for [UserRetrieveLiveResponse]. */
    class Builder internal constructor() {

        private var broadcast: JsonField<Broadcast>? = null
        private var live: JsonField<Boolean>? = null
        private var userId: JsonField<String>? = null
        private var userName: JsonField<String>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        internal fun from(userRetrieveLiveResponse: UserRetrieveLiveResponse) = apply {
            broadcast = userRetrieveLiveResponse.broadcast
            live = userRetrieveLiveResponse.live
            userId = userRetrieveLiveResponse.userId
            userName = userRetrieveLiveResponse.userName
            additionalProperties = userRetrieveLiveResponse.additionalProperties.toMutableMap()
        }

        /** Latest broadcast, live or ended. Null when X shows none. */
        fun broadcast(broadcast: Broadcast?) = broadcast(JsonField.ofNullable(broadcast))

        /** Sets [Builder.broadcast] to an arbitrary JSON value. */
        fun broadcast(broadcast: JsonField<Broadcast>) = apply { this.broadcast = broadcast }

        /** True while the user's latest broadcast is running. */
        fun live(live: Boolean) = live(JsonField.of(live))

        /** Sets [Builder.live] to an arbitrary JSON value. */
        fun live(live: JsonField<Boolean>) = apply { this.live = live }

        /** ID of the user. */
        fun userId(userId: String) = userId(JsonField.of(userId))

        /** Sets [Builder.userId] to an arbitrary JSON value. */
        fun userId(userId: JsonField<String>) = apply { this.userId = userId }

        /** Username of the user. */
        fun userName(userName: String) = userName(JsonField.of(userName))

        /** Sets [Builder.userName] to an arbitrary JSON value. */
        fun userName(userName: JsonField<String>) = apply { this.userName = userName }

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
         * Returns an immutable instance of [UserRetrieveLiveResponse].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```kotlin
         * .broadcast()
         * .live()
         * .userId()
         * .userName()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): UserRetrieveLiveResponse =
            UserRetrieveLiveResponse(
                checkRequired("broadcast", broadcast),
                checkRequired("live", live),
                checkRequired("userId", userId),
                checkRequired("userName", userName),
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
    fun validate(): UserRetrieveLiveResponse = apply {
        if (validated) {
            return@apply
        }

        broadcast()?.validate()
        live()
        userId()
        userName()
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
        (broadcast.asKnown()?.validity() ?: 0) +
            (if (live.asKnown() == null) 0 else 1) +
            (if (userId.asKnown() == null) 0 else 1) +
            (if (userName.asKnown() == null) 0 else 1)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is UserRetrieveLiveResponse &&
            broadcast == other.broadcast &&
            live == other.live &&
            userId == other.userId &&
            userName == other.userName &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(broadcast, live, userId, userName, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "UserRetrieveLiveResponse{broadcast=$broadcast, live=$live, userId=$userId, userName=$userName, additionalProperties=$additionalProperties}"
}
