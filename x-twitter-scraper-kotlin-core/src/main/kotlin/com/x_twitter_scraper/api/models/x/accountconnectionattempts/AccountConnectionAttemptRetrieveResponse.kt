// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.accountconnectionattempts

import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.BaseDeserializer
import com.x_twitter_scraper.api.core.BaseSerializer
import com.x_twitter_scraper.api.core.ExcludeMissing
import com.x_twitter_scraper.api.core.JsonField
import com.x_twitter_scraper.api.core.JsonMissing
import com.x_twitter_scraper.api.core.JsonValue
import com.x_twitter_scraper.api.core.checkRequired
import com.x_twitter_scraper.api.core.getOrThrow
import com.x_twitter_scraper.api.errors.XTwitterScraperInvalidDataException
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects

/** The connection is still in progress. */
@JsonDeserialize(using = AccountConnectionAttemptRetrieveResponse.Deserializer::class)
@JsonSerialize(using = AccountConnectionAttemptRetrieveResponse.Serializer::class)
class AccountConnectionAttemptRetrieveResponse
private constructor(
    private val pending: Pending? = null,
    private val success: Success? = null,
    private val failed: Failed? = null,
    private val requiresEmailCode: RequiresEmailCode? = null,
    private val _json: JsonValue? = null,
) {

    /** The connection is still in progress. */
    fun pending(): Pending? = pending

    /** The account connected successfully. */
    fun success(): Success? = success

    /** The connection reached a final failure. */
    fun failed(): Failed? = failed

    /**
     * Resumable account connection challenge. Submit the email code to finish the same connection
     * attempt.
     */
    fun requiresEmailCode(): RequiresEmailCode? = requiresEmailCode

    fun isPending(): Boolean = pending != null

    fun isSuccess(): Boolean = success != null

    fun isFailed(): Boolean = failed != null

    fun isRequiresEmailCode(): Boolean = requiresEmailCode != null

    /** The connection is still in progress. */
    fun asPending(): Pending = pending.getOrThrow("pending")

    /** The account connected successfully. */
    fun asSuccess(): Success = success.getOrThrow("success")

    /** The connection reached a final failure. */
    fun asFailed(): Failed = failed.getOrThrow("failed")

    /**
     * Resumable account connection challenge. Submit the email code to finish the same connection
     * attempt.
     */
    fun asRequiresEmailCode(): RequiresEmailCode = requiresEmailCode.getOrThrow("requiresEmailCode")

    fun _json(): JsonValue? = _json

    /**
     * Maps this instance's current variant to a value of type [T] using the given [visitor].
     *
     * @throws XTwitterScraperInvalidDataException if [Visitor.unknown] is not overridden in
     *   [visitor] and the current variant is unknown.
     */
    fun <T> accept(visitor: Visitor<T>): T =
        when {
            pending != null -> visitor.visitPending(pending)
            success != null -> visitor.visitSuccess(success)
            failed != null -> visitor.visitFailed(failed)
            requiresEmailCode != null -> visitor.visitRequiresEmailCode(requiresEmailCode)
            else -> visitor.unknown(_json)
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
    fun validate(): AccountConnectionAttemptRetrieveResponse = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitPending(pending: Pending) {
                    pending.validate()
                }

                override fun visitSuccess(success: Success) {
                    success.validate()
                }

                override fun visitFailed(failed: Failed) {
                    failed.validate()
                }

                override fun visitRequiresEmailCode(requiresEmailCode: RequiresEmailCode) {
                    requiresEmailCode.validate()
                }
            }
        )
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
        accept(
            object : Visitor<Int> {
                override fun visitPending(pending: Pending) = pending.validity()

                override fun visitSuccess(success: Success) = success.validity()

                override fun visitFailed(failed: Failed) = failed.validity()

                override fun visitRequiresEmailCode(requiresEmailCode: RequiresEmailCode) =
                    requiresEmailCode.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is AccountConnectionAttemptRetrieveResponse &&
            pending == other.pending &&
            success == other.success &&
            failed == other.failed &&
            requiresEmailCode == other.requiresEmailCode
    }

    override fun hashCode(): Int = Objects.hash(pending, success, failed, requiresEmailCode)

    override fun toString(): String =
        when {
            pending != null -> "AccountConnectionAttemptRetrieveResponse{pending=$pending}"
            success != null -> "AccountConnectionAttemptRetrieveResponse{success=$success}"
            failed != null -> "AccountConnectionAttemptRetrieveResponse{failed=$failed}"
            requiresEmailCode != null ->
                "AccountConnectionAttemptRetrieveResponse{requiresEmailCode=$requiresEmailCode}"
            _json != null -> "AccountConnectionAttemptRetrieveResponse{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid AccountConnectionAttemptRetrieveResponse")
        }

    companion object {

        /** The connection is still in progress. */
        fun ofPending(pending: Pending) =
            AccountConnectionAttemptRetrieveResponse(pending = pending)

        /** The account connected successfully. */
        fun ofSuccess(success: Success) =
            AccountConnectionAttemptRetrieveResponse(success = success)

        /** The connection reached a final failure. */
        fun ofFailed(failed: Failed) = AccountConnectionAttemptRetrieveResponse(failed = failed)

        /**
         * Resumable account connection challenge. Submit the email code to finish the same
         * connection attempt.
         */
        fun ofRequiresEmailCode(requiresEmailCode: RequiresEmailCode) =
            AccountConnectionAttemptRetrieveResponse(requiresEmailCode = requiresEmailCode)
    }

    /**
     * An interface that defines how to map each variant of
     * [AccountConnectionAttemptRetrieveResponse] to a value of type [T].
     */
    interface Visitor<out T> {

        /** The connection is still in progress. */
        fun visitPending(pending: Pending): T

        /** The account connected successfully. */
        fun visitSuccess(success: Success): T

        /** The connection reached a final failure. */
        fun visitFailed(failed: Failed): T

        /**
         * Resumable account connection challenge. Submit the email code to finish the same
         * connection attempt.
         */
        fun visitRequiresEmailCode(requiresEmailCode: RequiresEmailCode): T

        /**
         * Maps an unknown variant of [AccountConnectionAttemptRetrieveResponse] to a value of type
         * [T].
         *
         * An instance of [AccountConnectionAttemptRetrieveResponse] can contain an unknown variant
         * if it was deserialized from data that doesn't match any known variant.
         *
         * @throws XTwitterScraperInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw XTwitterScraperInvalidDataException(
                "Unknown AccountConnectionAttemptRetrieveResponse: $json"
            )
        }
    }

    internal class Deserializer :
        BaseDeserializer<AccountConnectionAttemptRetrieveResponse>(
            AccountConnectionAttemptRetrieveResponse::class
        ) {

        override fun ObjectCodec.deserialize(
            node: JsonNode
        ): AccountConnectionAttemptRetrieveResponse {
            val json = JsonValue.fromJsonNode(node)
            val status = json.asObject()?.get("status")?.asString()

            when (status) {
                "pending" -> {
                    return tryDeserialize(node, jacksonTypeRef<Pending>())?.let {
                        AccountConnectionAttemptRetrieveResponse(pending = it, _json = json)
                    } ?: AccountConnectionAttemptRetrieveResponse(_json = json)
                }
                "success" -> {
                    return tryDeserialize(node, jacksonTypeRef<Success>())?.let {
                        AccountConnectionAttemptRetrieveResponse(success = it, _json = json)
                    } ?: AccountConnectionAttemptRetrieveResponse(_json = json)
                }
                "failed" -> {
                    return tryDeserialize(node, jacksonTypeRef<Failed>())?.let {
                        AccountConnectionAttemptRetrieveResponse(failed = it, _json = json)
                    } ?: AccountConnectionAttemptRetrieveResponse(_json = json)
                }
                "requires_email_code" -> {
                    return tryDeserialize(node, jacksonTypeRef<RequiresEmailCode>())?.let {
                        AccountConnectionAttemptRetrieveResponse(
                            requiresEmailCode = it,
                            _json = json,
                        )
                    } ?: AccountConnectionAttemptRetrieveResponse(_json = json)
                }
            }

            return AccountConnectionAttemptRetrieveResponse(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<AccountConnectionAttemptRetrieveResponse>(
            AccountConnectionAttemptRetrieveResponse::class
        ) {

        override fun serialize(
            value: AccountConnectionAttemptRetrieveResponse,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.pending != null -> generator.writeObject(value.pending)
                value.success != null -> generator.writeObject(value.success)
                value.failed != null -> generator.writeObject(value.failed)
                value.requiresEmailCode != null -> generator.writeObject(value.requiresEmailCode)
                value._json != null -> generator.writeObject(value._json)
                else ->
                    throw IllegalStateException("Invalid AccountConnectionAttemptRetrieveResponse")
            }
        }
    }

    /** The connection is still in progress. */
    class Pending
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val id: JsonField<String>,
        private val pollAfterMs: JsonField<Long>,
        private val object_: JsonValue,
        private val status: JsonValue,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
            @JsonProperty("pollAfterMs")
            @ExcludeMissing
            pollAfterMs: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("object") @ExcludeMissing object_: JsonValue = JsonMissing.of(),
            @JsonProperty("status") @ExcludeMissing status: JsonValue = JsonMissing.of(),
        ) : this(id, pollAfterMs, object_, status, mutableMapOf())

        /**
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or
         *   is unexpectedly missing or null.
         */
        fun id(): String = id.getRequired("id")

        /**
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or
         *   is unexpectedly missing or null.
         */
        fun pollAfterMs(): Long = pollAfterMs.getRequired("pollAfterMs")

        /**
         * Expected to always return the following:
         * ```kotlin
         * JsonValue.from("x_account_connection_attempt")
         * ```
         */
        @JsonProperty("object") @ExcludeMissing fun _object_(): JsonValue = object_

        /**
         * Expected to always return the following:
         * ```kotlin
         * JsonValue.from("pending")
         * ```
         */
        @JsonProperty("status") @ExcludeMissing fun _status(): JsonValue = status

        /**
         * Returns the raw JSON value of [id].
         *
         * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

        /**
         * Returns the raw JSON value of [pollAfterMs].
         *
         * Unlike [pollAfterMs], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("pollAfterMs")
        @ExcludeMissing
        fun _pollAfterMs(): JsonField<Long> = pollAfterMs

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
             * Returns a mutable builder for constructing an instance of [Pending].
             *
             * The following fields are required:
             * ```kotlin
             * .id()
             * .pollAfterMs()
             * ```
             */
            fun builder() = Builder()
        }

        /** A builder for [Pending]. */
        class Builder internal constructor() {

            private var id: JsonField<String>? = null
            private var pollAfterMs: JsonField<Long>? = null
            private var object_: JsonValue = JsonValue.from("x_account_connection_attempt")
            private var status: JsonValue = JsonValue.from("pending")
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(pending: Pending) = apply {
                id = pending.id
                pollAfterMs = pending.pollAfterMs
                object_ = pending.object_
                status = pending.status
                additionalProperties = pending.additionalProperties.toMutableMap()
            }

            fun id(id: String) = id(JsonField.of(id))

            /** Sets [Builder.id] to an arbitrary JSON value. */
            fun id(id: JsonField<String>) = apply { this.id = id }

            fun pollAfterMs(pollAfterMs: Long) = pollAfterMs(JsonField.of(pollAfterMs))

            /** Sets [Builder.pollAfterMs] to an arbitrary JSON value. */
            fun pollAfterMs(pollAfterMs: JsonField<Long>) = apply { this.pollAfterMs = pollAfterMs }

            /**
             * Sets the field to an arbitrary JSON value.
             *
             * Defaults to the following:
             * ```kotlin
             * JsonValue.from("x_account_connection_attempt")
             * ```
             */
            fun object_(object_: JsonValue) = apply { this.object_ = object_ }

            /**
             * Sets the field to an arbitrary JSON value.
             *
             * Defaults to the following:
             * ```kotlin
             * JsonValue.from("pending")
             * ```
             */
            fun status(status: JsonValue) = apply { this.status = status }

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
             * Returns an immutable instance of [Pending].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             *
             * The following fields are required:
             * ```kotlin
             * .id()
             * .pollAfterMs()
             * ```
             *
             * @throws IllegalStateException if any required field is unset.
             */
            fun build(): Pending =
                Pending(
                    checkRequired("id", id),
                    checkRequired("pollAfterMs", pollAfterMs),
                    object_,
                    status,
                    additionalProperties.toMutableMap(),
                )
        }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
         *   match its expected type.
         */
        fun validate(): Pending = apply {
            if (validated) {
                return@apply
            }

            id()
            pollAfterMs()
            _object_().let {
                if (it != JsonValue.from("x_account_connection_attempt")) {
                    throw XTwitterScraperInvalidDataException("'object_' is invalid, received $it")
                }
            }
            _status().let {
                if (it != JsonValue.from("pending")) {
                    throw XTwitterScraperInvalidDataException("'status' is invalid, received $it")
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
            (if (id.asKnown() == null) 0 else 1) +
                (if (pollAfterMs.asKnown() == null) 0 else 1) +
                object_.let { if (it == JsonValue.from("x_account_connection_attempt")) 1 else 0 } +
                status.let { if (it == JsonValue.from("pending")) 1 else 0 }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Pending &&
                id == other.id &&
                pollAfterMs == other.pollAfterMs &&
                object_ == other.object_ &&
                status == other.status &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(id, pollAfterMs, object_, status, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Pending{id=$id, pollAfterMs=$pollAfterMs, object_=$object_, status=$status, additionalProperties=$additionalProperties}"
    }

    /** The account connected successfully. */
    class Success
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val id: JsonField<String>,
        private val object_: JsonValue,
        private val pollAfterMs: JsonField<Long>,
        private val status: JsonValue,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
            @JsonProperty("object") @ExcludeMissing object_: JsonValue = JsonMissing.of(),
            @JsonProperty("pollAfterMs")
            @ExcludeMissing
            pollAfterMs: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("status") @ExcludeMissing status: JsonValue = JsonMissing.of(),
        ) : this(id, object_, pollAfterMs, status, mutableMapOf())

        /**
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or
         *   is unexpectedly missing or null.
         */
        fun id(): String = id.getRequired("id")

        /**
         * Expected to always return the following:
         * ```kotlin
         * JsonValue.from("x_account_connection_attempt")
         * ```
         */
        @JsonProperty("object") @ExcludeMissing fun _object_(): JsonValue = object_

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun pollAfterMs(): Long? = pollAfterMs.getNullable("pollAfterMs")

        /**
         * Expected to always return the following:
         * ```kotlin
         * JsonValue.from("success")
         * ```
         */
        @JsonProperty("status") @ExcludeMissing fun _status(): JsonValue = status

        /**
         * Returns the raw JSON value of [id].
         *
         * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

        /**
         * Returns the raw JSON value of [pollAfterMs].
         *
         * Unlike [pollAfterMs], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("pollAfterMs")
        @ExcludeMissing
        fun _pollAfterMs(): JsonField<Long> = pollAfterMs

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
             * Returns a mutable builder for constructing an instance of [Success].
             *
             * The following fields are required:
             * ```kotlin
             * .id()
             * ```
             */
            fun builder() = Builder()
        }

        /** A builder for [Success]. */
        class Builder internal constructor() {

            private var id: JsonField<String>? = null
            private var object_: JsonValue = JsonValue.from("x_account_connection_attempt")
            private var pollAfterMs: JsonField<Long> = JsonMissing.of()
            private var status: JsonValue = JsonValue.from("success")
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(success: Success) = apply {
                id = success.id
                object_ = success.object_
                pollAfterMs = success.pollAfterMs
                status = success.status
                additionalProperties = success.additionalProperties.toMutableMap()
            }

            fun id(id: String) = id(JsonField.of(id))

            /** Sets [Builder.id] to an arbitrary JSON value. */
            fun id(id: JsonField<String>) = apply { this.id = id }

            /**
             * Sets the field to an arbitrary JSON value.
             *
             * Defaults to the following:
             * ```kotlin
             * JsonValue.from("x_account_connection_attempt")
             * ```
             */
            fun object_(object_: JsonValue) = apply { this.object_ = object_ }

            fun pollAfterMs(pollAfterMs: Long) = pollAfterMs(JsonField.of(pollAfterMs))

            /** Sets [Builder.pollAfterMs] to an arbitrary JSON value. */
            fun pollAfterMs(pollAfterMs: JsonField<Long>) = apply { this.pollAfterMs = pollAfterMs }

            /**
             * Sets the field to an arbitrary JSON value.
             *
             * Defaults to the following:
             * ```kotlin
             * JsonValue.from("success")
             * ```
             */
            fun status(status: JsonValue) = apply { this.status = status }

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
             * Returns an immutable instance of [Success].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             *
             * The following fields are required:
             * ```kotlin
             * .id()
             * ```
             *
             * @throws IllegalStateException if any required field is unset.
             */
            fun build(): Success =
                Success(
                    checkRequired("id", id),
                    object_,
                    pollAfterMs,
                    status,
                    additionalProperties.toMutableMap(),
                )
        }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
         *   match its expected type.
         */
        fun validate(): Success = apply {
            if (validated) {
                return@apply
            }

            id()
            _object_().let {
                if (it != JsonValue.from("x_account_connection_attempt")) {
                    throw XTwitterScraperInvalidDataException("'object_' is invalid, received $it")
                }
            }
            pollAfterMs()
            _status().let {
                if (it != JsonValue.from("success")) {
                    throw XTwitterScraperInvalidDataException("'status' is invalid, received $it")
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
            (if (id.asKnown() == null) 0 else 1) +
                object_.let { if (it == JsonValue.from("x_account_connection_attempt")) 1 else 0 } +
                (if (pollAfterMs.asKnown() == null) 0 else 1) +
                status.let { if (it == JsonValue.from("success")) 1 else 0 }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Success &&
                id == other.id &&
                object_ == other.object_ &&
                pollAfterMs == other.pollAfterMs &&
                status == other.status &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(id, object_, pollAfterMs, status, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Success{id=$id, object_=$object_, pollAfterMs=$pollAfterMs, status=$status, additionalProperties=$additionalProperties}"
    }

    /** The connection reached a final failure. */
    class Failed
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val id: JsonField<String>,
        private val error: JsonField<String>,
        private val retryable: JsonField<Boolean>,
        private val object_: JsonValue,
        private val reason: JsonField<String>,
        private val status: JsonValue,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
            @JsonProperty("error") @ExcludeMissing error: JsonField<String> = JsonMissing.of(),
            @JsonProperty("retryable")
            @ExcludeMissing
            retryable: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("object") @ExcludeMissing object_: JsonValue = JsonMissing.of(),
            @JsonProperty("reason") @ExcludeMissing reason: JsonField<String> = JsonMissing.of(),
            @JsonProperty("status") @ExcludeMissing status: JsonValue = JsonMissing.of(),
        ) : this(id, error, retryable, object_, reason, status, mutableMapOf())

        /**
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or
         *   is unexpectedly missing or null.
         */
        fun id(): String = id.getRequired("id")

        /**
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or
         *   is unexpectedly missing or null.
         */
        fun error(): String = error.getRequired("error")

        /**
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or
         *   is unexpectedly missing or null.
         */
        fun retryable(): Boolean = retryable.getRequired("retryable")

        /**
         * Expected to always return the following:
         * ```kotlin
         * JsonValue.from("x_account_connection_attempt")
         * ```
         */
        @JsonProperty("object") @ExcludeMissing fun _object_(): JsonValue = object_

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun reason(): String? = reason.getNullable("reason")

        /**
         * Expected to always return the following:
         * ```kotlin
         * JsonValue.from("failed")
         * ```
         */
        @JsonProperty("status") @ExcludeMissing fun _status(): JsonValue = status

        /**
         * Returns the raw JSON value of [id].
         *
         * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

        /**
         * Returns the raw JSON value of [error].
         *
         * Unlike [error], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("error") @ExcludeMissing fun _error(): JsonField<String> = error

        /**
         * Returns the raw JSON value of [retryable].
         *
         * Unlike [retryable], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("retryable") @ExcludeMissing fun _retryable(): JsonField<Boolean> = retryable

        /**
         * Returns the raw JSON value of [reason].
         *
         * Unlike [reason], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("reason") @ExcludeMissing fun _reason(): JsonField<String> = reason

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
             * Returns a mutable builder for constructing an instance of [Failed].
             *
             * The following fields are required:
             * ```kotlin
             * .id()
             * .error()
             * .retryable()
             * ```
             */
            fun builder() = Builder()
        }

        /** A builder for [Failed]. */
        class Builder internal constructor() {

            private var id: JsonField<String>? = null
            private var error: JsonField<String>? = null
            private var retryable: JsonField<Boolean>? = null
            private var object_: JsonValue = JsonValue.from("x_account_connection_attempt")
            private var reason: JsonField<String> = JsonMissing.of()
            private var status: JsonValue = JsonValue.from("failed")
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(failed: Failed) = apply {
                id = failed.id
                error = failed.error
                retryable = failed.retryable
                object_ = failed.object_
                reason = failed.reason
                status = failed.status
                additionalProperties = failed.additionalProperties.toMutableMap()
            }

            fun id(id: String) = id(JsonField.of(id))

            /** Sets [Builder.id] to an arbitrary JSON value. */
            fun id(id: JsonField<String>) = apply { this.id = id }

            fun error(error: String) = error(JsonField.of(error))

            /** Sets [Builder.error] to an arbitrary JSON value. */
            fun error(error: JsonField<String>) = apply { this.error = error }

            fun retryable(retryable: Boolean) = retryable(JsonField.of(retryable))

            /** Sets [Builder.retryable] to an arbitrary JSON value. */
            fun retryable(retryable: JsonField<Boolean>) = apply { this.retryable = retryable }

            /**
             * Sets the field to an arbitrary JSON value.
             *
             * Defaults to the following:
             * ```kotlin
             * JsonValue.from("x_account_connection_attempt")
             * ```
             */
            fun object_(object_: JsonValue) = apply { this.object_ = object_ }

            fun reason(reason: String) = reason(JsonField.of(reason))

            /** Sets [Builder.reason] to an arbitrary JSON value. */
            fun reason(reason: JsonField<String>) = apply { this.reason = reason }

            /**
             * Sets the field to an arbitrary JSON value.
             *
             * Defaults to the following:
             * ```kotlin
             * JsonValue.from("failed")
             * ```
             */
            fun status(status: JsonValue) = apply { this.status = status }

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
             * Returns an immutable instance of [Failed].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             *
             * The following fields are required:
             * ```kotlin
             * .id()
             * .error()
             * .retryable()
             * ```
             *
             * @throws IllegalStateException if any required field is unset.
             */
            fun build(): Failed =
                Failed(
                    checkRequired("id", id),
                    checkRequired("error", error),
                    checkRequired("retryable", retryable),
                    object_,
                    reason,
                    status,
                    additionalProperties.toMutableMap(),
                )
        }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
         *   match its expected type.
         */
        fun validate(): Failed = apply {
            if (validated) {
                return@apply
            }

            id()
            error()
            retryable()
            _object_().let {
                if (it != JsonValue.from("x_account_connection_attempt")) {
                    throw XTwitterScraperInvalidDataException("'object_' is invalid, received $it")
                }
            }
            reason()
            _status().let {
                if (it != JsonValue.from("failed")) {
                    throw XTwitterScraperInvalidDataException("'status' is invalid, received $it")
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
            (if (id.asKnown() == null) 0 else 1) +
                (if (error.asKnown() == null) 0 else 1) +
                (if (retryable.asKnown() == null) 0 else 1) +
                object_.let { if (it == JsonValue.from("x_account_connection_attempt")) 1 else 0 } +
                (if (reason.asKnown() == null) 0 else 1) +
                status.let { if (it == JsonValue.from("failed")) 1 else 0 }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Failed &&
                id == other.id &&
                error == other.error &&
                retryable == other.retryable &&
                object_ == other.object_ &&
                reason == other.reason &&
                status == other.status &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(id, error, retryable, object_, reason, status, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Failed{id=$id, error=$error, retryable=$retryable, object_=$object_, reason=$reason, status=$status, additionalProperties=$additionalProperties}"
    }

    /**
     * Resumable account connection challenge. Submit the email code to finish the same connection
     * attempt.
     */
    class RequiresEmailCode
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val id: JsonField<String>,
        private val expiresAt: JsonField<OffsetDateTime>,
        private val message: JsonField<String>,
        private val username: JsonField<String>,
        private val object_: JsonValue,
        private val status: JsonValue,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
            @JsonProperty("expiresAt")
            @ExcludeMissing
            expiresAt: JsonField<OffsetDateTime> = JsonMissing.of(),
            @JsonProperty("message") @ExcludeMissing message: JsonField<String> = JsonMissing.of(),
            @JsonProperty("username")
            @ExcludeMissing
            username: JsonField<String> = JsonMissing.of(),
            @JsonProperty("object") @ExcludeMissing object_: JsonValue = JsonMissing.of(),
            @JsonProperty("status") @ExcludeMissing status: JsonValue = JsonMissing.of(),
        ) : this(id, expiresAt, message, username, object_, status, mutableMapOf())

        /**
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or
         *   is unexpectedly missing or null.
         */
        fun id(): String = id.getRequired("id")

        /**
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or
         *   is unexpectedly missing or null.
         */
        fun expiresAt(): OffsetDateTime = expiresAt.getRequired("expiresAt")

        /**
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or
         *   is unexpectedly missing or null.
         */
        fun message(): String = message.getRequired("message")

        /**
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or
         *   is unexpectedly missing or null.
         */
        fun username(): String = username.getRequired("username")

        /**
         * Expected to always return the following:
         * ```kotlin
         * JsonValue.from("x_account_connection_challenge")
         * ```
         */
        @JsonProperty("object") @ExcludeMissing fun _object_(): JsonValue = object_

        /**
         * Expected to always return the following:
         * ```kotlin
         * JsonValue.from("requires_email_code")
         * ```
         */
        @JsonProperty("status") @ExcludeMissing fun _status(): JsonValue = status

        /**
         * Returns the raw JSON value of [id].
         *
         * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

        /**
         * Returns the raw JSON value of [expiresAt].
         *
         * Unlike [expiresAt], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("expiresAt")
        @ExcludeMissing
        fun _expiresAt(): JsonField<OffsetDateTime> = expiresAt

        /**
         * Returns the raw JSON value of [message].
         *
         * Unlike [message], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("message") @ExcludeMissing fun _message(): JsonField<String> = message

        /**
         * Returns the raw JSON value of [username].
         *
         * Unlike [username], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("username") @ExcludeMissing fun _username(): JsonField<String> = username

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
             * Returns a mutable builder for constructing an instance of [RequiresEmailCode].
             *
             * The following fields are required:
             * ```kotlin
             * .id()
             * .expiresAt()
             * .message()
             * .username()
             * ```
             */
            fun builder() = Builder()
        }

        /** A builder for [RequiresEmailCode]. */
        class Builder internal constructor() {

            private var id: JsonField<String>? = null
            private var expiresAt: JsonField<OffsetDateTime>? = null
            private var message: JsonField<String>? = null
            private var username: JsonField<String>? = null
            private var object_: JsonValue = JsonValue.from("x_account_connection_challenge")
            private var status: JsonValue = JsonValue.from("requires_email_code")
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(requiresEmailCode: RequiresEmailCode) = apply {
                id = requiresEmailCode.id
                expiresAt = requiresEmailCode.expiresAt
                message = requiresEmailCode.message
                username = requiresEmailCode.username
                object_ = requiresEmailCode.object_
                status = requiresEmailCode.status
                additionalProperties = requiresEmailCode.additionalProperties.toMutableMap()
            }

            fun id(id: String) = id(JsonField.of(id))

            /** Sets [Builder.id] to an arbitrary JSON value. */
            fun id(id: JsonField<String>) = apply { this.id = id }

            fun expiresAt(expiresAt: OffsetDateTime) = expiresAt(JsonField.of(expiresAt))

            /** Sets [Builder.expiresAt] to an arbitrary JSON value. */
            fun expiresAt(expiresAt: JsonField<OffsetDateTime>) = apply {
                this.expiresAt = expiresAt
            }

            fun message(message: String) = message(JsonField.of(message))

            /** Sets [Builder.message] to an arbitrary JSON value. */
            fun message(message: JsonField<String>) = apply { this.message = message }

            fun username(username: String) = username(JsonField.of(username))

            /** Sets [Builder.username] to an arbitrary JSON value. */
            fun username(username: JsonField<String>) = apply { this.username = username }

            /**
             * Sets the field to an arbitrary JSON value.
             *
             * Defaults to the following:
             * ```kotlin
             * JsonValue.from("x_account_connection_challenge")
             * ```
             */
            fun object_(object_: JsonValue) = apply { this.object_ = object_ }

            /**
             * Sets the field to an arbitrary JSON value.
             *
             * Defaults to the following:
             * ```kotlin
             * JsonValue.from("requires_email_code")
             * ```
             */
            fun status(status: JsonValue) = apply { this.status = status }

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
             * Returns an immutable instance of [RequiresEmailCode].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             *
             * The following fields are required:
             * ```kotlin
             * .id()
             * .expiresAt()
             * .message()
             * .username()
             * ```
             *
             * @throws IllegalStateException if any required field is unset.
             */
            fun build(): RequiresEmailCode =
                RequiresEmailCode(
                    checkRequired("id", id),
                    checkRequired("expiresAt", expiresAt),
                    checkRequired("message", message),
                    checkRequired("username", username),
                    object_,
                    status,
                    additionalProperties.toMutableMap(),
                )
        }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
         *   match its expected type.
         */
        fun validate(): RequiresEmailCode = apply {
            if (validated) {
                return@apply
            }

            id()
            expiresAt()
            message()
            username()
            _object_().let {
                if (it != JsonValue.from("x_account_connection_challenge")) {
                    throw XTwitterScraperInvalidDataException("'object_' is invalid, received $it")
                }
            }
            _status().let {
                if (it != JsonValue.from("requires_email_code")) {
                    throw XTwitterScraperInvalidDataException("'status' is invalid, received $it")
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
            (if (id.asKnown() == null) 0 else 1) +
                (if (expiresAt.asKnown() == null) 0 else 1) +
                (if (message.asKnown() == null) 0 else 1) +
                (if (username.asKnown() == null) 0 else 1) +
                object_.let {
                    if (it == JsonValue.from("x_account_connection_challenge")) 1 else 0
                } +
                status.let { if (it == JsonValue.from("requires_email_code")) 1 else 0 }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is RequiresEmailCode &&
                id == other.id &&
                expiresAt == other.expiresAt &&
                message == other.message &&
                username == other.username &&
                object_ == other.object_ &&
                status == other.status &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(id, expiresAt, message, username, object_, status, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "RequiresEmailCode{id=$id, expiresAt=$expiresAt, message=$message, username=$username, object_=$object_, status=$status, additionalProperties=$additionalProperties}"
    }
}
