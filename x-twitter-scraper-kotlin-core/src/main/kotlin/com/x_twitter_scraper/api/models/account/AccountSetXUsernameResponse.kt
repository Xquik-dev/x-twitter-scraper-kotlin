// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.account

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

class AccountSetXUsernameResponse
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val xUsername: JsonField<String>,
    private val success: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("xUsername") @ExcludeMissing xUsername: JsonField<String> = JsonMissing.of(),
        @JsonProperty("success") @ExcludeMissing success: JsonValue = JsonMissing.of(),
    ) : this(xUsername, success, mutableMapOf())

    /**
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun xUsername(): String = xUsername.getRequired("xUsername")

    /**
     * Expected to always return the following: ```kotlin JsonValue.from(true) ```
     *
     * Expected to always return the following:
     * ```kotlin
     * JsonValue.from(true)
     * ```
     */
    @JsonProperty("success") @ExcludeMissing fun _success(): JsonValue = success

    /**
     * Returns the raw JSON value of [xUsername].
     *
     * Unlike [xUsername], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("xUsername") @ExcludeMissing fun _xUsername(): JsonField<String> = xUsername

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
         * Returns a mutable builder for constructing an instance of [AccountSetXUsernameResponse].
         *
         * The following fields are required:
         * ```kotlin
         * .xUsername()
         * ```
         */
        fun builder() = Builder()
    }

    /** A builder for [AccountSetXUsernameResponse]. */
    class Builder internal constructor() {

        private var xUsername: JsonField<String>? = null
        private var success: JsonValue = JsonValue.from(true)
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        internal fun from(accountSetXUsernameResponse: AccountSetXUsernameResponse) = apply {
            xUsername = accountSetXUsernameResponse.xUsername
            success = accountSetXUsernameResponse.success
            additionalProperties = accountSetXUsernameResponse.additionalProperties.toMutableMap()
        }

        fun xUsername(xUsername: String) = xUsername(JsonField.of(xUsername))

        /** Sets [Builder.xUsername] to an arbitrary JSON value. */
        fun xUsername(xUsername: JsonField<String>) = apply { this.xUsername = xUsername }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * Defaults to the following:
         * ```kotlin
         * JsonValue.from(true)
         * ```
         */
        fun success(success: JsonValue) = apply { this.success = success }

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
         * Returns an immutable instance of [AccountSetXUsernameResponse].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```kotlin
         * .xUsername()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): AccountSetXUsernameResponse =
            AccountSetXUsernameResponse(
                checkRequired("xUsername", xUsername),
                success,
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
    fun validate(): AccountSetXUsernameResponse = apply {
        if (validated) {
            return@apply
        }

        xUsername()
        _success().let {
            if (it != JsonValue.from(true)) {
                throw XTwitterScraperInvalidDataException("'success' is invalid, received $it")
            }
        }
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
        (if (xUsername.asKnown() == null) 0 else 1) +
            success.let { if (it == JsonValue.from(true)) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is AccountSetXUsernameResponse &&
            xUsername == other.xUsername &&
            success == other.success &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(xUsername, success, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "AccountSetXUsernameResponse{xUsername=$xUsername, success=$success, additionalProperties=$additionalProperties}"
}
