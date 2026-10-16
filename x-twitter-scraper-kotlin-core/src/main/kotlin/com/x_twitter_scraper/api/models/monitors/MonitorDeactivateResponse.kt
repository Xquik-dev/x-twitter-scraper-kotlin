// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.monitors

import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.x_twitter_scraper.api.core.ExcludeMissing
import com.x_twitter_scraper.api.core.JsonField
import com.x_twitter_scraper.api.core.JsonMissing
import com.x_twitter_scraper.api.core.JsonValue
import com.x_twitter_scraper.api.errors.XTwitterScraperInvalidDataException
import java.util.Collections
import java.util.Objects

class MonitorDeactivateResponse
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val deletionStatus: JsonField<String>,
    private val statusUrl: JsonField<String>,
    private val success: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("deletionStatus")
        @ExcludeMissing
        deletionStatus: JsonField<String> = JsonMissing.of(),
        @JsonProperty("statusUrl") @ExcludeMissing statusUrl: JsonField<String> = JsonMissing.of(),
        @JsonProperty("success") @ExcludeMissing success: JsonValue = JsonMissing.of(),
    ) : this(deletionStatus, statusUrl, success, mutableMapOf())

    /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
    fun deletionStatus(): String? = deletionStatus.getNullable("deletionStatus")

    /**
     * Poll this monitor URL until it returns 404.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun statusUrl(): String? = statusUrl.getNullable("statusUrl")

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
     * Returns the raw JSON value of [deletionStatus].
     *
     * Unlike [deletionStatus], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("deletionStatus")
    @ExcludeMissing
    fun _deletionStatus(): JsonField<String> = deletionStatus

    /**
     * Returns the raw JSON value of [statusUrl].
     *
     * Unlike [statusUrl], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("statusUrl") @ExcludeMissing fun _statusUrl(): JsonField<String> = statusUrl

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
         * Returns a mutable builder for constructing an instance of [MonitorDeactivateResponse].
         */
        fun builder() = Builder()
    }

    /** A builder for [MonitorDeactivateResponse]. */
    class Builder internal constructor() {

        private var deletionStatus: JsonField<String> = JsonMissing.of()
        private var statusUrl: JsonField<String> = JsonMissing.of()
        private var success: JsonValue = JsonValue.from(true)
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        internal fun from(monitorDeactivateResponse: MonitorDeactivateResponse) = apply {
            deletionStatus = monitorDeactivateResponse.deletionStatus
            statusUrl = monitorDeactivateResponse.statusUrl
            success = monitorDeactivateResponse.success
            additionalProperties = monitorDeactivateResponse.additionalProperties.toMutableMap()
        }

        fun deletionStatus(deletionStatus: String) = deletionStatus(JsonField.of(deletionStatus))

        /** Sets [Builder.deletionStatus] to an arbitrary JSON value. */
        fun deletionStatus(deletionStatus: JsonField<String>) = apply {
            this.deletionStatus = deletionStatus
        }

        /** Poll this monitor URL until it returns 404. */
        fun statusUrl(statusUrl: String) = statusUrl(JsonField.of(statusUrl))

        /** Sets [Builder.statusUrl] to an arbitrary JSON value. */
        fun statusUrl(statusUrl: JsonField<String>) = apply { this.statusUrl = statusUrl }

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
         * Returns an immutable instance of [MonitorDeactivateResponse].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): MonitorDeactivateResponse =
            MonitorDeactivateResponse(
                deletionStatus,
                statusUrl,
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
    fun validate(): MonitorDeactivateResponse = apply {
        if (validated) {
            return@apply
        }

        deletionStatus()
        statusUrl()
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
        (if (deletionStatus.asKnown() == null) 0 else 1) +
            (if (statusUrl.asKnown() == null) 0 else 1) +
            success.let { if (it == JsonValue.from(true)) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is MonitorDeactivateResponse &&
            deletionStatus == other.deletionStatus &&
            statusUrl == other.statusUrl &&
            success == other.success &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(deletionStatus, statusUrl, success, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "MonitorDeactivateResponse{deletionStatus=$deletionStatus, statusUrl=$statusUrl, success=$success, additionalProperties=$additionalProperties}"
}
