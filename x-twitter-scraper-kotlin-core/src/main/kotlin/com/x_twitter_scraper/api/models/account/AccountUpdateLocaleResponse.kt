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
import com.x_twitter_scraper.api.core.checkKnown
import com.x_twitter_scraper.api.core.toImmutable
import com.x_twitter_scraper.api.errors.XTwitterScraperInvalidDataException
import java.util.Collections
import java.util.Objects

class AccountUpdateLocaleResponse
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val ignoredFields: JsonField<List<String>>,
    private val success: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("ignoredFields")
        @ExcludeMissing
        ignoredFields: JsonField<List<String>> = JsonMissing.of(),
        @JsonProperty("success") @ExcludeMissing success: JsonValue = JsonMissing.of(),
    ) : this(ignoredFields, success, mutableMapOf())

    /**
     * Fields the request sent that this route does not read, in the order sent. They changed
     * nothing. The answer leaves this out when the route read every field.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun ignoredFields(): List<String>? = ignoredFields.getNullable("ignoredFields")

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
     * Returns the raw JSON value of [ignoredFields].
     *
     * Unlike [ignoredFields], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("ignoredFields")
    @ExcludeMissing
    fun _ignoredFields(): JsonField<List<String>> = ignoredFields

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
         * Returns a mutable builder for constructing an instance of [AccountUpdateLocaleResponse].
         */
        fun builder() = Builder()
    }

    /** A builder for [AccountUpdateLocaleResponse]. */
    class Builder internal constructor() {

        private var ignoredFields: JsonField<MutableList<String>>? = null
        private var success: JsonValue = JsonValue.from(true)
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        internal fun from(accountUpdateLocaleResponse: AccountUpdateLocaleResponse) = apply {
            ignoredFields = accountUpdateLocaleResponse.ignoredFields.map { it.toMutableList() }
            success = accountUpdateLocaleResponse.success
            additionalProperties = accountUpdateLocaleResponse.additionalProperties.toMutableMap()
        }

        /**
         * Fields the request sent that this route does not read, in the order sent. They changed
         * nothing. The answer leaves this out when the route read every field.
         */
        fun ignoredFields(ignoredFields: List<String>) = ignoredFields(JsonField.of(ignoredFields))

        /** Sets [Builder.ignoredFields] to an arbitrary JSON value. */
        fun ignoredFields(ignoredFields: JsonField<List<String>>) = apply {
            this.ignoredFields = ignoredFields.map { it.toMutableList() }
        }

        /**
         * Adds a single [String] to [ignoredFields].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addIgnoredField(ignoredField: String) = apply {
            ignoredFields =
                (ignoredFields ?: JsonField.of(mutableListOf())).also {
                    checkKnown("ignoredFields", it).add(ignoredField)
                }
        }

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
         * Returns an immutable instance of [AccountUpdateLocaleResponse].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): AccountUpdateLocaleResponse =
            AccountUpdateLocaleResponse(
                (ignoredFields ?: JsonMissing.of()).map { it.toImmutable() },
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
    fun validate(): AccountUpdateLocaleResponse = apply {
        if (validated) {
            return@apply
        }

        ignoredFields()
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
        (ignoredFields.asKnown()?.size ?: 0) +
            success.let { if (it == JsonValue.from(true)) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is AccountUpdateLocaleResponse &&
            ignoredFields == other.ignoredFields &&
            success == other.success &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(ignoredFields, success, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "AccountUpdateLocaleResponse{ignoredFields=$ignoredFields, success=$success, additionalProperties=$additionalProperties}"
}
