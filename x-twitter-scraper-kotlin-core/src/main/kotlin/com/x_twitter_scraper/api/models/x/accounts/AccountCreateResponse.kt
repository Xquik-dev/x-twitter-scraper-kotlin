// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.accounts

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
import com.x_twitter_scraper.api.core.Enum
import com.x_twitter_scraper.api.core.ExcludeMissing
import com.x_twitter_scraper.api.core.JsonField
import com.x_twitter_scraper.api.core.JsonMissing
import com.x_twitter_scraper.api.core.JsonValue
import com.x_twitter_scraper.api.core.allMaxBy
import com.x_twitter_scraper.api.core.checkRequired
import com.x_twitter_scraper.api.core.getOrThrow
import com.x_twitter_scraper.api.errors.XTwitterScraperInvalidDataException
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects

/** Sanitized X account summary returned by connect and reauth. */
@JsonDeserialize(using = AccountCreateResponse.Deserializer::class)
@JsonSerialize(using = AccountCreateResponse.Serializer::class)
class AccountCreateResponse
private constructor(
    private val sanitizedXAccount: SanitizedXAccount? = null,
    private val xAccountConnectionAttemptPending: XAccountConnectionAttemptPending? = null,
    private val xAccountConnectionChallenge: XAccountConnectionChallenge? = null,
    private val _json: JsonValue? = null,
) {

    /** Sanitized X account summary returned by connect and reauth. */
    fun sanitizedXAccount(): SanitizedXAccount? = sanitizedXAccount

    /** The connection is still in progress. */
    fun xAccountConnectionAttemptPending(): XAccountConnectionAttemptPending? =
        xAccountConnectionAttemptPending

    /**
     * Resumable account connection challenge. Submit the email code to finish the same connection
     * attempt.
     */
    fun xAccountConnectionChallenge(): XAccountConnectionChallenge? = xAccountConnectionChallenge

    fun isSanitizedXAccount(): Boolean = sanitizedXAccount != null

    fun isXAccountConnectionAttemptPending(): Boolean = xAccountConnectionAttemptPending != null

    fun isXAccountConnectionChallenge(): Boolean = xAccountConnectionChallenge != null

    /** Sanitized X account summary returned by connect and reauth. */
    fun asSanitizedXAccount(): SanitizedXAccount = sanitizedXAccount.getOrThrow("sanitizedXAccount")

    /** The connection is still in progress. */
    fun asXAccountConnectionAttemptPending(): XAccountConnectionAttemptPending =
        xAccountConnectionAttemptPending.getOrThrow("xAccountConnectionAttemptPending")

    /**
     * Resumable account connection challenge. Submit the email code to finish the same connection
     * attempt.
     */
    fun asXAccountConnectionChallenge(): XAccountConnectionChallenge =
        xAccountConnectionChallenge.getOrThrow("xAccountConnectionChallenge")

    fun _json(): JsonValue? = _json

    /**
     * Maps this instance's current variant to a value of type [T] using the given [visitor].
     *
     * @throws XTwitterScraperInvalidDataException if [Visitor.unknown] is not overridden in
     *   [visitor] and the current variant is unknown.
     */
    fun <T> accept(visitor: Visitor<T>): T =
        when {
            sanitizedXAccount != null -> visitor.visitSanitizedXAccount(sanitizedXAccount)
            xAccountConnectionAttemptPending != null ->
                visitor.visitXAccountConnectionAttemptPending(xAccountConnectionAttemptPending)
            xAccountConnectionChallenge != null ->
                visitor.visitXAccountConnectionChallenge(xAccountConnectionChallenge)
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
    fun validate(): AccountCreateResponse = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitSanitizedXAccount(sanitizedXAccount: SanitizedXAccount) {
                    sanitizedXAccount.validate()
                }

                override fun visitXAccountConnectionAttemptPending(
                    xAccountConnectionAttemptPending: XAccountConnectionAttemptPending
                ) {
                    xAccountConnectionAttemptPending.validate()
                }

                override fun visitXAccountConnectionChallenge(
                    xAccountConnectionChallenge: XAccountConnectionChallenge
                ) {
                    xAccountConnectionChallenge.validate()
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
                override fun visitSanitizedXAccount(sanitizedXAccount: SanitizedXAccount) =
                    sanitizedXAccount.validity()

                override fun visitXAccountConnectionAttemptPending(
                    xAccountConnectionAttemptPending: XAccountConnectionAttemptPending
                ) = xAccountConnectionAttemptPending.validity()

                override fun visitXAccountConnectionChallenge(
                    xAccountConnectionChallenge: XAccountConnectionChallenge
                ) = xAccountConnectionChallenge.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is AccountCreateResponse &&
            sanitizedXAccount == other.sanitizedXAccount &&
            xAccountConnectionAttemptPending == other.xAccountConnectionAttemptPending &&
            xAccountConnectionChallenge == other.xAccountConnectionChallenge
    }

    override fun hashCode(): Int =
        Objects.hash(
            sanitizedXAccount,
            xAccountConnectionAttemptPending,
            xAccountConnectionChallenge,
        )

    override fun toString(): String =
        when {
            sanitizedXAccount != null ->
                "AccountCreateResponse{sanitizedXAccount=$sanitizedXAccount}"
            xAccountConnectionAttemptPending != null ->
                "AccountCreateResponse{xAccountConnectionAttemptPending=$xAccountConnectionAttemptPending}"
            xAccountConnectionChallenge != null ->
                "AccountCreateResponse{xAccountConnectionChallenge=$xAccountConnectionChallenge}"
            _json != null -> "AccountCreateResponse{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid AccountCreateResponse")
        }

    companion object {

        /** Sanitized X account summary returned by connect and reauth. */
        fun ofSanitizedXAccount(sanitizedXAccount: SanitizedXAccount) =
            AccountCreateResponse(sanitizedXAccount = sanitizedXAccount)

        /** The connection is still in progress. */
        fun ofXAccountConnectionAttemptPending(
            xAccountConnectionAttemptPending: XAccountConnectionAttemptPending
        ) =
            AccountCreateResponse(
                xAccountConnectionAttemptPending = xAccountConnectionAttemptPending
            )

        /**
         * Resumable account connection challenge. Submit the email code to finish the same
         * connection attempt.
         */
        fun ofXAccountConnectionChallenge(
            xAccountConnectionChallenge: XAccountConnectionChallenge
        ) = AccountCreateResponse(xAccountConnectionChallenge = xAccountConnectionChallenge)
    }

    /**
     * An interface that defines how to map each variant of [AccountCreateResponse] to a value of
     * type [T].
     */
    interface Visitor<out T> {

        /** Sanitized X account summary returned by connect and reauth. */
        fun visitSanitizedXAccount(sanitizedXAccount: SanitizedXAccount): T

        /** The connection is still in progress. */
        fun visitXAccountConnectionAttemptPending(
            xAccountConnectionAttemptPending: XAccountConnectionAttemptPending
        ): T

        /**
         * Resumable account connection challenge. Submit the email code to finish the same
         * connection attempt.
         */
        fun visitXAccountConnectionChallenge(
            xAccountConnectionChallenge: XAccountConnectionChallenge
        ): T

        /**
         * Maps an unknown variant of [AccountCreateResponse] to a value of type [T].
         *
         * An instance of [AccountCreateResponse] can contain an unknown variant if it was
         * deserialized from data that doesn't match any known variant.
         *
         * @throws XTwitterScraperInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw XTwitterScraperInvalidDataException("Unknown AccountCreateResponse: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<AccountCreateResponse>(AccountCreateResponse::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): AccountCreateResponse {
            val json = JsonValue.fromJsonNode(node)

            val bestMatches =
                sequenceOf(
                        tryDeserialize(node, jacksonTypeRef<SanitizedXAccount>())?.let {
                            AccountCreateResponse(sanitizedXAccount = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<XAccountConnectionAttemptPending>())
                            ?.let {
                                AccountCreateResponse(
                                    xAccountConnectionAttemptPending = it,
                                    _json = json,
                                )
                            },
                        tryDeserialize(node, jacksonTypeRef<XAccountConnectionChallenge>())?.let {
                            AccountCreateResponse(xAccountConnectionChallenge = it, _json = json)
                        },
                    )
                    .filterNotNull()
                    .allMaxBy { it.validity() }
                    .toList()
            return when (bestMatches.size) {
                0 -> AccountCreateResponse(_json = json)
                1 -> bestMatches.single()
                else -> bestMatches.firstOrNull { it.isValid() } ?: bestMatches.first()
            }
        }
    }

    internal class Serializer :
        BaseSerializer<AccountCreateResponse>(AccountCreateResponse::class) {

        override fun serialize(
            value: AccountCreateResponse,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.sanitizedXAccount != null -> generator.writeObject(value.sanitizedXAccount)
                value.xAccountConnectionAttemptPending != null ->
                    generator.writeObject(value.xAccountConnectionAttemptPending)
                value.xAccountConnectionChallenge != null ->
                    generator.writeObject(value.xAccountConnectionChallenge)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid AccountCreateResponse")
            }
        }
    }

    /** Sanitized X account summary returned by connect and reauth. */
    class SanitizedXAccount
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val id: JsonField<String>,
        private val createdAt: JsonField<OffsetDateTime>,
        private val health: JsonField<Health>,
        private val xUserId: JsonField<String>,
        private val xUsername: JsonField<String>,
        private val expiresAt: JsonField<OffsetDateTime>,
        private val message: JsonField<String>,
        private val object_: JsonField<String>,
        private val pollAfterMs: JsonField<Long>,
        private val status: JsonValue,
        private val username: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
            @JsonProperty("createdAt")
            @ExcludeMissing
            createdAt: JsonField<OffsetDateTime> = JsonMissing.of(),
            @JsonProperty("health") @ExcludeMissing health: JsonField<Health> = JsonMissing.of(),
            @JsonProperty("xUserId") @ExcludeMissing xUserId: JsonField<String> = JsonMissing.of(),
            @JsonProperty("xUsername")
            @ExcludeMissing
            xUsername: JsonField<String> = JsonMissing.of(),
            @JsonProperty("expiresAt")
            @ExcludeMissing
            expiresAt: JsonField<OffsetDateTime> = JsonMissing.of(),
            @JsonProperty("message") @ExcludeMissing message: JsonField<String> = JsonMissing.of(),
            @JsonProperty("object") @ExcludeMissing object_: JsonField<String> = JsonMissing.of(),
            @JsonProperty("pollAfterMs")
            @ExcludeMissing
            pollAfterMs: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("status") @ExcludeMissing status: JsonValue = JsonMissing.of(),
            @JsonProperty("username")
            @ExcludeMissing
            username: JsonField<String> = JsonMissing.of(),
        ) : this(
            id,
            createdAt,
            health,
            xUserId,
            xUsername,
            expiresAt,
            message,
            object_,
            pollAfterMs,
            status,
            username,
            mutableMapOf(),
        )

        /**
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or
         *   is unexpectedly missing or null.
         */
        fun id(): String = id.getRequired("id")

        /**
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or
         *   is unexpectedly missing or null.
         */
        fun createdAt(): OffsetDateTime = createdAt.getRequired("createdAt")

        /**
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or
         *   is unexpectedly missing or null.
         */
        fun health(): Health = health.getRequired("health")

        /**
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or
         *   is unexpectedly missing or null.
         */
        fun xUserId(): String = xUserId.getRequired("xUserId")

        /**
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or
         *   is unexpectedly missing or null.
         */
        fun xUsername(): String = xUsername.getRequired("xUsername")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun expiresAt(): OffsetDateTime? = expiresAt.getNullable("expiresAt")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun message(): String? = message.getNullable("message")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun object_(): String? = object_.getNullable("object")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun pollAfterMs(): Long? = pollAfterMs.getNullable("pollAfterMs")

        /**
         * Expected to always return the following:
         * ```kotlin
         * JsonValue.from("active")
         * ```
         */
        @JsonProperty("status") @ExcludeMissing fun _status(): JsonValue = status

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun username(): String? = username.getNullable("username")

        /**
         * Returns the raw JSON value of [id].
         *
         * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

        /**
         * Returns the raw JSON value of [createdAt].
         *
         * Unlike [createdAt], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("createdAt")
        @ExcludeMissing
        fun _createdAt(): JsonField<OffsetDateTime> = createdAt

        /**
         * Returns the raw JSON value of [health].
         *
         * Unlike [health], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("health") @ExcludeMissing fun _health(): JsonField<Health> = health

        /**
         * Returns the raw JSON value of [xUserId].
         *
         * Unlike [xUserId], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("xUserId") @ExcludeMissing fun _xUserId(): JsonField<String> = xUserId

        /**
         * Returns the raw JSON value of [xUsername].
         *
         * Unlike [xUsername], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("xUsername") @ExcludeMissing fun _xUsername(): JsonField<String> = xUsername

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
         * Returns the raw JSON value of [object_].
         *
         * Unlike [object_], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("object") @ExcludeMissing fun _object_(): JsonField<String> = object_

        /**
         * Returns the raw JSON value of [pollAfterMs].
         *
         * Unlike [pollAfterMs], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("pollAfterMs")
        @ExcludeMissing
        fun _pollAfterMs(): JsonField<Long> = pollAfterMs

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
             * Returns a mutable builder for constructing an instance of [SanitizedXAccount].
             *
             * The following fields are required:
             * ```kotlin
             * .id()
             * .createdAt()
             * .health()
             * .xUserId()
             * .xUsername()
             * ```
             */
            fun builder() = Builder()
        }

        /** A builder for [SanitizedXAccount]. */
        class Builder internal constructor() {

            private var id: JsonField<String>? = null
            private var createdAt: JsonField<OffsetDateTime>? = null
            private var health: JsonField<Health>? = null
            private var xUserId: JsonField<String>? = null
            private var xUsername: JsonField<String>? = null
            private var expiresAt: JsonField<OffsetDateTime> = JsonMissing.of()
            private var message: JsonField<String> = JsonMissing.of()
            private var object_: JsonField<String> = JsonMissing.of()
            private var pollAfterMs: JsonField<Long> = JsonMissing.of()
            private var status: JsonValue = JsonValue.from("active")
            private var username: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(sanitizedXAccount: SanitizedXAccount) = apply {
                id = sanitizedXAccount.id
                createdAt = sanitizedXAccount.createdAt
                health = sanitizedXAccount.health
                xUserId = sanitizedXAccount.xUserId
                xUsername = sanitizedXAccount.xUsername
                expiresAt = sanitizedXAccount.expiresAt
                message = sanitizedXAccount.message
                object_ = sanitizedXAccount.object_
                pollAfterMs = sanitizedXAccount.pollAfterMs
                status = sanitizedXAccount.status
                username = sanitizedXAccount.username
                additionalProperties = sanitizedXAccount.additionalProperties.toMutableMap()
            }

            fun id(id: String) = id(JsonField.of(id))

            /** Sets [Builder.id] to an arbitrary JSON value. */
            fun id(id: JsonField<String>) = apply { this.id = id }

            fun createdAt(createdAt: OffsetDateTime) = createdAt(JsonField.of(createdAt))

            /** Sets [Builder.createdAt] to an arbitrary JSON value. */
            fun createdAt(createdAt: JsonField<OffsetDateTime>) = apply {
                this.createdAt = createdAt
            }

            fun health(health: Health) = health(JsonField.of(health))

            /** Sets [Builder.health] to an arbitrary JSON value. */
            fun health(health: JsonField<Health>) = apply { this.health = health }

            fun xUserId(xUserId: String) = xUserId(JsonField.of(xUserId))

            /** Sets [Builder.xUserId] to an arbitrary JSON value. */
            fun xUserId(xUserId: JsonField<String>) = apply { this.xUserId = xUserId }

            fun xUsername(xUsername: String) = xUsername(JsonField.of(xUsername))

            /** Sets [Builder.xUsername] to an arbitrary JSON value. */
            fun xUsername(xUsername: JsonField<String>) = apply { this.xUsername = xUsername }

            fun expiresAt(expiresAt: OffsetDateTime) = expiresAt(JsonField.of(expiresAt))

            /** Sets [Builder.expiresAt] to an arbitrary JSON value. */
            fun expiresAt(expiresAt: JsonField<OffsetDateTime>) = apply {
                this.expiresAt = expiresAt
            }

            fun message(message: String) = message(JsonField.of(message))

            /** Sets [Builder.message] to an arbitrary JSON value. */
            fun message(message: JsonField<String>) = apply { this.message = message }

            fun object_(object_: String) = object_(JsonField.of(object_))

            /** Sets [Builder.object_] to an arbitrary JSON value. */
            fun object_(object_: JsonField<String>) = apply { this.object_ = object_ }

            fun pollAfterMs(pollAfterMs: Long) = pollAfterMs(JsonField.of(pollAfterMs))

            /** Sets [Builder.pollAfterMs] to an arbitrary JSON value. */
            fun pollAfterMs(pollAfterMs: JsonField<Long>) = apply { this.pollAfterMs = pollAfterMs }

            /**
             * Sets the field to an arbitrary JSON value.
             *
             * Defaults to the following:
             * ```kotlin
             * JsonValue.from("active")
             * ```
             */
            fun status(status: JsonValue) = apply { this.status = status }

            fun username(username: String) = username(JsonField.of(username))

            /** Sets [Builder.username] to an arbitrary JSON value. */
            fun username(username: JsonField<String>) = apply { this.username = username }

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
             * Returns an immutable instance of [SanitizedXAccount].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             *
             * The following fields are required:
             * ```kotlin
             * .id()
             * .createdAt()
             * .health()
             * .xUserId()
             * .xUsername()
             * ```
             *
             * @throws IllegalStateException if any required field is unset.
             */
            fun build(): SanitizedXAccount =
                SanitizedXAccount(
                    checkRequired("id", id),
                    checkRequired("createdAt", createdAt),
                    checkRequired("health", health),
                    checkRequired("xUserId", xUserId),
                    checkRequired("xUsername", xUsername),
                    expiresAt,
                    message,
                    object_,
                    pollAfterMs,
                    status,
                    username,
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
        fun validate(): SanitizedXAccount = apply {
            if (validated) {
                return@apply
            }

            id()
            createdAt()
            health().validate()
            xUserId()
            xUsername()
            expiresAt()
            message()
            object_()
            pollAfterMs()
            _status().let {
                if (it != JsonValue.from("active")) {
                    throw XTwitterScraperInvalidDataException("'status' is invalid, received $it")
                }
            }
            username()
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
                (if (createdAt.asKnown() == null) 0 else 1) +
                (health.asKnown()?.validity() ?: 0) +
                (if (xUserId.asKnown() == null) 0 else 1) +
                (if (xUsername.asKnown() == null) 0 else 1) +
                (if (expiresAt.asKnown() == null) 0 else 1) +
                (if (message.asKnown() == null) 0 else 1) +
                (if (object_.asKnown() == null) 0 else 1) +
                (if (pollAfterMs.asKnown() == null) 0 else 1) +
                status.let { if (it == JsonValue.from("active")) 1 else 0 } +
                (if (username.asKnown() == null) 0 else 1)

        class Health @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

            /** Returns this class instance's raw value. */
            @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

            companion object {

                val HEALTHY = of("healthy")

                val LOCKED = of("locked")

                val NEEDS_REAUTH = of("needsReauth")

                val RECOVERING = of("recovering")

                val SUSPENDED = of("suspended")

                val TEMPORARY_ISSUE = of("temporaryIssue")

                fun of(value: String) = Health(JsonField.of(value))
            }

            /** An enum containing [Health]'s known values. */
            enum class Known {
                HEALTHY,
                LOCKED,
                NEEDS_REAUTH,
                RECOVERING,
                SUSPENDED,
                TEMPORARY_ISSUE,
            }

            /**
             * An enum containing [Health]'s known values, as well as an [_UNKNOWN] member.
             *
             * An instance of [Health] can contain an unknown value in a couple of cases:
             * - It was deserialized from data that doesn't match any known member. For example, if
             *   the SDK is on an older version than the API, then the API may respond with new
             *   members that the SDK is unaware of.
             * - It was constructed with an arbitrary value using the [of] method.
             */
            enum class Value {
                HEALTHY,
                LOCKED,
                NEEDS_REAUTH,
                RECOVERING,
                SUSPENDED,
                TEMPORARY_ISSUE,
                /**
                 * An enum member indicating that [Health] was instantiated with an unknown value.
                 */
                _UNKNOWN,
            }

            /**
             * Returns an enum member corresponding to this class instance's value, or
             * [Value._UNKNOWN] if the class was instantiated with an unknown value.
             */
            fun value(): Value =
                when (this) {
                    HEALTHY -> Value.HEALTHY
                    LOCKED -> Value.LOCKED
                    NEEDS_REAUTH -> Value.NEEDS_REAUTH
                    RECOVERING -> Value.RECOVERING
                    SUSPENDED -> Value.SUSPENDED
                    TEMPORARY_ISSUE -> Value.TEMPORARY_ISSUE
                    else -> Value._UNKNOWN
                }

            /**
             * Returns an enum member corresponding to this class instance's value.
             *
             * @throws XTwitterScraperInvalidDataException if this class instance's value is a not a
             *   known member.
             */
            fun known(): Known =
                when (this) {
                    HEALTHY -> Known.HEALTHY
                    LOCKED -> Known.LOCKED
                    NEEDS_REAUTH -> Known.NEEDS_REAUTH
                    RECOVERING -> Known.RECOVERING
                    SUSPENDED -> Known.SUSPENDED
                    TEMPORARY_ISSUE -> Known.TEMPORARY_ISSUE
                    else -> throw XTwitterScraperInvalidDataException("Unknown Health: $value")
                }

            /**
             * Returns this class instance's primitive wire representation.
             *
             * @throws XTwitterScraperInvalidDataException if this class instance's value does not
             *   have the expected primitive type.
             */
            fun asString(): String =
                _value().asString()
                    ?: throw XTwitterScraperInvalidDataException("Value is not a String")

            private var validated: Boolean = false

            /**
             * Validates that the types of all values in this object match their expected types
             * recursively.
             *
             * This method is _not_ forwards compatible with new types from the API for existing
             * fields.
             *
             * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
             *   match its expected type.
             */
            fun validate(): Health = apply {
                if (validated) {
                    return@apply
                }

                known()
                validated = true
            }

            fun isValid(): Boolean =
                try {
                    validate()
                    true
                } catch (e: XTwitterScraperInvalidDataException) {
                    false
                }

            internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is Health && value == other.value
            }

            override fun hashCode() = value.hashCode()

            override fun toString() = value.toString()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is SanitizedXAccount &&
                id == other.id &&
                createdAt == other.createdAt &&
                health == other.health &&
                xUserId == other.xUserId &&
                xUsername == other.xUsername &&
                expiresAt == other.expiresAt &&
                message == other.message &&
                object_ == other.object_ &&
                pollAfterMs == other.pollAfterMs &&
                status == other.status &&
                username == other.username &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(
                id,
                createdAt,
                health,
                xUserId,
                xUsername,
                expiresAt,
                message,
                object_,
                pollAfterMs,
                status,
                username,
                additionalProperties,
            )
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "SanitizedXAccount{id=$id, createdAt=$createdAt, health=$health, xUserId=$xUserId, xUsername=$xUsername, expiresAt=$expiresAt, message=$message, object_=$object_, pollAfterMs=$pollAfterMs, status=$status, username=$username, additionalProperties=$additionalProperties}"
    }

    /** The connection is still in progress. */
    class XAccountConnectionAttemptPending
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val id: JsonField<String>,
        private val pollAfterMs: JsonField<Long>,
        private val createdAt: JsonField<OffsetDateTime>,
        private val expiresAt: JsonField<OffsetDateTime>,
        private val health: JsonField<Health>,
        private val message: JsonField<String>,
        private val object_: JsonValue,
        private val status: JsonValue,
        private val username: JsonField<String>,
        private val xUserId: JsonField<String>,
        private val xUsername: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
            @JsonProperty("pollAfterMs")
            @ExcludeMissing
            pollAfterMs: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("createdAt")
            @ExcludeMissing
            createdAt: JsonField<OffsetDateTime> = JsonMissing.of(),
            @JsonProperty("expiresAt")
            @ExcludeMissing
            expiresAt: JsonField<OffsetDateTime> = JsonMissing.of(),
            @JsonProperty("health") @ExcludeMissing health: JsonField<Health> = JsonMissing.of(),
            @JsonProperty("message") @ExcludeMissing message: JsonField<String> = JsonMissing.of(),
            @JsonProperty("object") @ExcludeMissing object_: JsonValue = JsonMissing.of(),
            @JsonProperty("status") @ExcludeMissing status: JsonValue = JsonMissing.of(),
            @JsonProperty("username")
            @ExcludeMissing
            username: JsonField<String> = JsonMissing.of(),
            @JsonProperty("xUserId") @ExcludeMissing xUserId: JsonField<String> = JsonMissing.of(),
            @JsonProperty("xUsername")
            @ExcludeMissing
            xUsername: JsonField<String> = JsonMissing.of(),
        ) : this(
            id,
            pollAfterMs,
            createdAt,
            expiresAt,
            health,
            message,
            object_,
            status,
            username,
            xUserId,
            xUsername,
            mutableMapOf(),
        )

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

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun createdAt(): OffsetDateTime? = createdAt.getNullable("createdAt")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun expiresAt(): OffsetDateTime? = expiresAt.getNullable("expiresAt")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun health(): Health? = health.getNullable("health")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun message(): String? = message.getNullable("message")

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

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun username(): String? = username.getNullable("username")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun xUserId(): String? = xUserId.getNullable("xUserId")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun xUsername(): String? = xUsername.getNullable("xUsername")

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

        /**
         * Returns the raw JSON value of [createdAt].
         *
         * Unlike [createdAt], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("createdAt")
        @ExcludeMissing
        fun _createdAt(): JsonField<OffsetDateTime> = createdAt

        /**
         * Returns the raw JSON value of [expiresAt].
         *
         * Unlike [expiresAt], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("expiresAt")
        @ExcludeMissing
        fun _expiresAt(): JsonField<OffsetDateTime> = expiresAt

        /**
         * Returns the raw JSON value of [health].
         *
         * Unlike [health], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("health") @ExcludeMissing fun _health(): JsonField<Health> = health

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

        /**
         * Returns the raw JSON value of [xUserId].
         *
         * Unlike [xUserId], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("xUserId") @ExcludeMissing fun _xUserId(): JsonField<String> = xUserId

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
             * Returns a mutable builder for constructing an instance of
             * [XAccountConnectionAttemptPending].
             *
             * The following fields are required:
             * ```kotlin
             * .id()
             * .pollAfterMs()
             * ```
             */
            fun builder() = Builder()
        }

        /** A builder for [XAccountConnectionAttemptPending]. */
        class Builder internal constructor() {

            private var id: JsonField<String>? = null
            private var pollAfterMs: JsonField<Long>? = null
            private var createdAt: JsonField<OffsetDateTime> = JsonMissing.of()
            private var expiresAt: JsonField<OffsetDateTime> = JsonMissing.of()
            private var health: JsonField<Health> = JsonMissing.of()
            private var message: JsonField<String> = JsonMissing.of()
            private var object_: JsonValue = JsonValue.from("x_account_connection_attempt")
            private var status: JsonValue = JsonValue.from("pending")
            private var username: JsonField<String> = JsonMissing.of()
            private var xUserId: JsonField<String> = JsonMissing.of()
            private var xUsername: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(xAccountConnectionAttemptPending: XAccountConnectionAttemptPending) =
                apply {
                    id = xAccountConnectionAttemptPending.id
                    pollAfterMs = xAccountConnectionAttemptPending.pollAfterMs
                    createdAt = xAccountConnectionAttemptPending.createdAt
                    expiresAt = xAccountConnectionAttemptPending.expiresAt
                    health = xAccountConnectionAttemptPending.health
                    message = xAccountConnectionAttemptPending.message
                    object_ = xAccountConnectionAttemptPending.object_
                    status = xAccountConnectionAttemptPending.status
                    username = xAccountConnectionAttemptPending.username
                    xUserId = xAccountConnectionAttemptPending.xUserId
                    xUsername = xAccountConnectionAttemptPending.xUsername
                    additionalProperties =
                        xAccountConnectionAttemptPending.additionalProperties.toMutableMap()
                }

            fun id(id: String) = id(JsonField.of(id))

            /** Sets [Builder.id] to an arbitrary JSON value. */
            fun id(id: JsonField<String>) = apply { this.id = id }

            fun pollAfterMs(pollAfterMs: Long) = pollAfterMs(JsonField.of(pollAfterMs))

            /** Sets [Builder.pollAfterMs] to an arbitrary JSON value. */
            fun pollAfterMs(pollAfterMs: JsonField<Long>) = apply { this.pollAfterMs = pollAfterMs }

            fun createdAt(createdAt: OffsetDateTime) = createdAt(JsonField.of(createdAt))

            /** Sets [Builder.createdAt] to an arbitrary JSON value. */
            fun createdAt(createdAt: JsonField<OffsetDateTime>) = apply {
                this.createdAt = createdAt
            }

            fun expiresAt(expiresAt: OffsetDateTime) = expiresAt(JsonField.of(expiresAt))

            /** Sets [Builder.expiresAt] to an arbitrary JSON value. */
            fun expiresAt(expiresAt: JsonField<OffsetDateTime>) = apply {
                this.expiresAt = expiresAt
            }

            fun health(health: Health) = health(JsonField.of(health))

            /** Sets [Builder.health] to an arbitrary JSON value. */
            fun health(health: JsonField<Health>) = apply { this.health = health }

            fun message(message: String) = message(JsonField.of(message))

            /** Sets [Builder.message] to an arbitrary JSON value. */
            fun message(message: JsonField<String>) = apply { this.message = message }

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

            fun username(username: String) = username(JsonField.of(username))

            /** Sets [Builder.username] to an arbitrary JSON value. */
            fun username(username: JsonField<String>) = apply { this.username = username }

            fun xUserId(xUserId: String) = xUserId(JsonField.of(xUserId))

            /** Sets [Builder.xUserId] to an arbitrary JSON value. */
            fun xUserId(xUserId: JsonField<String>) = apply { this.xUserId = xUserId }

            fun xUsername(xUsername: String) = xUsername(JsonField.of(xUsername))

            /** Sets [Builder.xUsername] to an arbitrary JSON value. */
            fun xUsername(xUsername: JsonField<String>) = apply { this.xUsername = xUsername }

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
             * Returns an immutable instance of [XAccountConnectionAttemptPending].
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
            fun build(): XAccountConnectionAttemptPending =
                XAccountConnectionAttemptPending(
                    checkRequired("id", id),
                    checkRequired("pollAfterMs", pollAfterMs),
                    createdAt,
                    expiresAt,
                    health,
                    message,
                    object_,
                    status,
                    username,
                    xUserId,
                    xUsername,
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
        fun validate(): XAccountConnectionAttemptPending = apply {
            if (validated) {
                return@apply
            }

            id()
            pollAfterMs()
            createdAt()
            expiresAt()
            health()?.validate()
            message()
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
            username()
            xUserId()
            xUsername()
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
                (if (createdAt.asKnown() == null) 0 else 1) +
                (if (expiresAt.asKnown() == null) 0 else 1) +
                (health.asKnown()?.validity() ?: 0) +
                (if (message.asKnown() == null) 0 else 1) +
                object_.let { if (it == JsonValue.from("x_account_connection_attempt")) 1 else 0 } +
                status.let { if (it == JsonValue.from("pending")) 1 else 0 } +
                (if (username.asKnown() == null) 0 else 1) +
                (if (xUserId.asKnown() == null) 0 else 1) +
                (if (xUsername.asKnown() == null) 0 else 1)

        class Health @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

            /** Returns this class instance's raw value. */
            @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

            companion object {

                val HEALTHY = of("healthy")

                val LOCKED = of("locked")

                val NEEDS_REAUTH = of("needsReauth")

                val RECOVERING = of("recovering")

                val SUSPENDED = of("suspended")

                val TEMPORARY_ISSUE = of("temporaryIssue")

                fun of(value: String) = Health(JsonField.of(value))
            }

            /** An enum containing [Health]'s known values. */
            enum class Known {
                HEALTHY,
                LOCKED,
                NEEDS_REAUTH,
                RECOVERING,
                SUSPENDED,
                TEMPORARY_ISSUE,
            }

            /**
             * An enum containing [Health]'s known values, as well as an [_UNKNOWN] member.
             *
             * An instance of [Health] can contain an unknown value in a couple of cases:
             * - It was deserialized from data that doesn't match any known member. For example, if
             *   the SDK is on an older version than the API, then the API may respond with new
             *   members that the SDK is unaware of.
             * - It was constructed with an arbitrary value using the [of] method.
             */
            enum class Value {
                HEALTHY,
                LOCKED,
                NEEDS_REAUTH,
                RECOVERING,
                SUSPENDED,
                TEMPORARY_ISSUE,
                /**
                 * An enum member indicating that [Health] was instantiated with an unknown value.
                 */
                _UNKNOWN,
            }

            /**
             * Returns an enum member corresponding to this class instance's value, or
             * [Value._UNKNOWN] if the class was instantiated with an unknown value.
             */
            fun value(): Value =
                when (this) {
                    HEALTHY -> Value.HEALTHY
                    LOCKED -> Value.LOCKED
                    NEEDS_REAUTH -> Value.NEEDS_REAUTH
                    RECOVERING -> Value.RECOVERING
                    SUSPENDED -> Value.SUSPENDED
                    TEMPORARY_ISSUE -> Value.TEMPORARY_ISSUE
                    else -> Value._UNKNOWN
                }

            /**
             * Returns an enum member corresponding to this class instance's value.
             *
             * @throws XTwitterScraperInvalidDataException if this class instance's value is a not a
             *   known member.
             */
            fun known(): Known =
                when (this) {
                    HEALTHY -> Known.HEALTHY
                    LOCKED -> Known.LOCKED
                    NEEDS_REAUTH -> Known.NEEDS_REAUTH
                    RECOVERING -> Known.RECOVERING
                    SUSPENDED -> Known.SUSPENDED
                    TEMPORARY_ISSUE -> Known.TEMPORARY_ISSUE
                    else -> throw XTwitterScraperInvalidDataException("Unknown Health: $value")
                }

            /**
             * Returns this class instance's primitive wire representation.
             *
             * @throws XTwitterScraperInvalidDataException if this class instance's value does not
             *   have the expected primitive type.
             */
            fun asString(): String =
                _value().asString()
                    ?: throw XTwitterScraperInvalidDataException("Value is not a String")

            private var validated: Boolean = false

            /**
             * Validates that the types of all values in this object match their expected types
             * recursively.
             *
             * This method is _not_ forwards compatible with new types from the API for existing
             * fields.
             *
             * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
             *   match its expected type.
             */
            fun validate(): Health = apply {
                if (validated) {
                    return@apply
                }

                known()
                validated = true
            }

            fun isValid(): Boolean =
                try {
                    validate()
                    true
                } catch (e: XTwitterScraperInvalidDataException) {
                    false
                }

            internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is Health && value == other.value
            }

            override fun hashCode() = value.hashCode()

            override fun toString() = value.toString()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is XAccountConnectionAttemptPending &&
                id == other.id &&
                pollAfterMs == other.pollAfterMs &&
                createdAt == other.createdAt &&
                expiresAt == other.expiresAt &&
                health == other.health &&
                message == other.message &&
                object_ == other.object_ &&
                status == other.status &&
                username == other.username &&
                xUserId == other.xUserId &&
                xUsername == other.xUsername &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(
                id,
                pollAfterMs,
                createdAt,
                expiresAt,
                health,
                message,
                object_,
                status,
                username,
                xUserId,
                xUsername,
                additionalProperties,
            )
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "XAccountConnectionAttemptPending{id=$id, pollAfterMs=$pollAfterMs, createdAt=$createdAt, expiresAt=$expiresAt, health=$health, message=$message, object_=$object_, status=$status, username=$username, xUserId=$xUserId, xUsername=$xUsername, additionalProperties=$additionalProperties}"
    }

    /**
     * Resumable account connection challenge. Submit the email code to finish the same connection
     * attempt.
     */
    class XAccountConnectionChallenge
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val id: JsonField<String>,
        private val expiresAt: JsonField<OffsetDateTime>,
        private val message: JsonField<String>,
        private val username: JsonField<String>,
        private val createdAt: JsonField<OffsetDateTime>,
        private val health: JsonField<Health>,
        private val object_: JsonValue,
        private val pollAfterMs: JsonField<Long>,
        private val status: JsonValue,
        private val xUserId: JsonField<String>,
        private val xUsername: JsonField<String>,
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
            @JsonProperty("createdAt")
            @ExcludeMissing
            createdAt: JsonField<OffsetDateTime> = JsonMissing.of(),
            @JsonProperty("health") @ExcludeMissing health: JsonField<Health> = JsonMissing.of(),
            @JsonProperty("object") @ExcludeMissing object_: JsonValue = JsonMissing.of(),
            @JsonProperty("pollAfterMs")
            @ExcludeMissing
            pollAfterMs: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("status") @ExcludeMissing status: JsonValue = JsonMissing.of(),
            @JsonProperty("xUserId") @ExcludeMissing xUserId: JsonField<String> = JsonMissing.of(),
            @JsonProperty("xUsername")
            @ExcludeMissing
            xUsername: JsonField<String> = JsonMissing.of(),
        ) : this(
            id,
            expiresAt,
            message,
            username,
            createdAt,
            health,
            object_,
            pollAfterMs,
            status,
            xUserId,
            xUsername,
            mutableMapOf(),
        )

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

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun createdAt(): OffsetDateTime? = createdAt.getNullable("createdAt")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun health(): Health? = health.getNullable("health")

        /**
         * Expected to always return the following:
         * ```kotlin
         * JsonValue.from("x_account_connection_challenge")
         * ```
         */
        @JsonProperty("object") @ExcludeMissing fun _object_(): JsonValue = object_

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun pollAfterMs(): Long? = pollAfterMs.getNullable("pollAfterMs")

        /**
         * Expected to always return the following:
         * ```kotlin
         * JsonValue.from("requires_email_code")
         * ```
         */
        @JsonProperty("status") @ExcludeMissing fun _status(): JsonValue = status

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun xUserId(): String? = xUserId.getNullable("xUserId")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun xUsername(): String? = xUsername.getNullable("xUsername")

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

        /**
         * Returns the raw JSON value of [createdAt].
         *
         * Unlike [createdAt], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("createdAt")
        @ExcludeMissing
        fun _createdAt(): JsonField<OffsetDateTime> = createdAt

        /**
         * Returns the raw JSON value of [health].
         *
         * Unlike [health], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("health") @ExcludeMissing fun _health(): JsonField<Health> = health

        /**
         * Returns the raw JSON value of [pollAfterMs].
         *
         * Unlike [pollAfterMs], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("pollAfterMs")
        @ExcludeMissing
        fun _pollAfterMs(): JsonField<Long> = pollAfterMs

        /**
         * Returns the raw JSON value of [xUserId].
         *
         * Unlike [xUserId], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("xUserId") @ExcludeMissing fun _xUserId(): JsonField<String> = xUserId

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
             * Returns a mutable builder for constructing an instance of
             * [XAccountConnectionChallenge].
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

        /** A builder for [XAccountConnectionChallenge]. */
        class Builder internal constructor() {

            private var id: JsonField<String>? = null
            private var expiresAt: JsonField<OffsetDateTime>? = null
            private var message: JsonField<String>? = null
            private var username: JsonField<String>? = null
            private var createdAt: JsonField<OffsetDateTime> = JsonMissing.of()
            private var health: JsonField<Health> = JsonMissing.of()
            private var object_: JsonValue = JsonValue.from("x_account_connection_challenge")
            private var pollAfterMs: JsonField<Long> = JsonMissing.of()
            private var status: JsonValue = JsonValue.from("requires_email_code")
            private var xUserId: JsonField<String> = JsonMissing.of()
            private var xUsername: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(xAccountConnectionChallenge: XAccountConnectionChallenge) = apply {
                id = xAccountConnectionChallenge.id
                expiresAt = xAccountConnectionChallenge.expiresAt
                message = xAccountConnectionChallenge.message
                username = xAccountConnectionChallenge.username
                createdAt = xAccountConnectionChallenge.createdAt
                health = xAccountConnectionChallenge.health
                object_ = xAccountConnectionChallenge.object_
                pollAfterMs = xAccountConnectionChallenge.pollAfterMs
                status = xAccountConnectionChallenge.status
                xUserId = xAccountConnectionChallenge.xUserId
                xUsername = xAccountConnectionChallenge.xUsername
                additionalProperties =
                    xAccountConnectionChallenge.additionalProperties.toMutableMap()
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

            fun createdAt(createdAt: OffsetDateTime) = createdAt(JsonField.of(createdAt))

            /** Sets [Builder.createdAt] to an arbitrary JSON value. */
            fun createdAt(createdAt: JsonField<OffsetDateTime>) = apply {
                this.createdAt = createdAt
            }

            fun health(health: Health) = health(JsonField.of(health))

            /** Sets [Builder.health] to an arbitrary JSON value. */
            fun health(health: JsonField<Health>) = apply { this.health = health }

            /**
             * Sets the field to an arbitrary JSON value.
             *
             * Defaults to the following:
             * ```kotlin
             * JsonValue.from("x_account_connection_challenge")
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
             * JsonValue.from("requires_email_code")
             * ```
             */
            fun status(status: JsonValue) = apply { this.status = status }

            fun xUserId(xUserId: String) = xUserId(JsonField.of(xUserId))

            /** Sets [Builder.xUserId] to an arbitrary JSON value. */
            fun xUserId(xUserId: JsonField<String>) = apply { this.xUserId = xUserId }

            fun xUsername(xUsername: String) = xUsername(JsonField.of(xUsername))

            /** Sets [Builder.xUsername] to an arbitrary JSON value. */
            fun xUsername(xUsername: JsonField<String>) = apply { this.xUsername = xUsername }

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
             * Returns an immutable instance of [XAccountConnectionChallenge].
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
            fun build(): XAccountConnectionChallenge =
                XAccountConnectionChallenge(
                    checkRequired("id", id),
                    checkRequired("expiresAt", expiresAt),
                    checkRequired("message", message),
                    checkRequired("username", username),
                    createdAt,
                    health,
                    object_,
                    pollAfterMs,
                    status,
                    xUserId,
                    xUsername,
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
        fun validate(): XAccountConnectionChallenge = apply {
            if (validated) {
                return@apply
            }

            id()
            expiresAt()
            message()
            username()
            createdAt()
            health()?.validate()
            _object_().let {
                if (it != JsonValue.from("x_account_connection_challenge")) {
                    throw XTwitterScraperInvalidDataException("'object_' is invalid, received $it")
                }
            }
            pollAfterMs()
            _status().let {
                if (it != JsonValue.from("requires_email_code")) {
                    throw XTwitterScraperInvalidDataException("'status' is invalid, received $it")
                }
            }
            xUserId()
            xUsername()
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
                (if (createdAt.asKnown() == null) 0 else 1) +
                (health.asKnown()?.validity() ?: 0) +
                object_.let {
                    if (it == JsonValue.from("x_account_connection_challenge")) 1 else 0
                } +
                (if (pollAfterMs.asKnown() == null) 0 else 1) +
                status.let { if (it == JsonValue.from("requires_email_code")) 1 else 0 } +
                (if (xUserId.asKnown() == null) 0 else 1) +
                (if (xUsername.asKnown() == null) 0 else 1)

        class Health @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

            /** Returns this class instance's raw value. */
            @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

            companion object {

                val HEALTHY = of("healthy")

                val LOCKED = of("locked")

                val NEEDS_REAUTH = of("needsReauth")

                val RECOVERING = of("recovering")

                val SUSPENDED = of("suspended")

                val TEMPORARY_ISSUE = of("temporaryIssue")

                fun of(value: String) = Health(JsonField.of(value))
            }

            /** An enum containing [Health]'s known values. */
            enum class Known {
                HEALTHY,
                LOCKED,
                NEEDS_REAUTH,
                RECOVERING,
                SUSPENDED,
                TEMPORARY_ISSUE,
            }

            /**
             * An enum containing [Health]'s known values, as well as an [_UNKNOWN] member.
             *
             * An instance of [Health] can contain an unknown value in a couple of cases:
             * - It was deserialized from data that doesn't match any known member. For example, if
             *   the SDK is on an older version than the API, then the API may respond with new
             *   members that the SDK is unaware of.
             * - It was constructed with an arbitrary value using the [of] method.
             */
            enum class Value {
                HEALTHY,
                LOCKED,
                NEEDS_REAUTH,
                RECOVERING,
                SUSPENDED,
                TEMPORARY_ISSUE,
                /**
                 * An enum member indicating that [Health] was instantiated with an unknown value.
                 */
                _UNKNOWN,
            }

            /**
             * Returns an enum member corresponding to this class instance's value, or
             * [Value._UNKNOWN] if the class was instantiated with an unknown value.
             */
            fun value(): Value =
                when (this) {
                    HEALTHY -> Value.HEALTHY
                    LOCKED -> Value.LOCKED
                    NEEDS_REAUTH -> Value.NEEDS_REAUTH
                    RECOVERING -> Value.RECOVERING
                    SUSPENDED -> Value.SUSPENDED
                    TEMPORARY_ISSUE -> Value.TEMPORARY_ISSUE
                    else -> Value._UNKNOWN
                }

            /**
             * Returns an enum member corresponding to this class instance's value.
             *
             * @throws XTwitterScraperInvalidDataException if this class instance's value is a not a
             *   known member.
             */
            fun known(): Known =
                when (this) {
                    HEALTHY -> Known.HEALTHY
                    LOCKED -> Known.LOCKED
                    NEEDS_REAUTH -> Known.NEEDS_REAUTH
                    RECOVERING -> Known.RECOVERING
                    SUSPENDED -> Known.SUSPENDED
                    TEMPORARY_ISSUE -> Known.TEMPORARY_ISSUE
                    else -> throw XTwitterScraperInvalidDataException("Unknown Health: $value")
                }

            /**
             * Returns this class instance's primitive wire representation.
             *
             * @throws XTwitterScraperInvalidDataException if this class instance's value does not
             *   have the expected primitive type.
             */
            fun asString(): String =
                _value().asString()
                    ?: throw XTwitterScraperInvalidDataException("Value is not a String")

            private var validated: Boolean = false

            /**
             * Validates that the types of all values in this object match their expected types
             * recursively.
             *
             * This method is _not_ forwards compatible with new types from the API for existing
             * fields.
             *
             * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
             *   match its expected type.
             */
            fun validate(): Health = apply {
                if (validated) {
                    return@apply
                }

                known()
                validated = true
            }

            fun isValid(): Boolean =
                try {
                    validate()
                    true
                } catch (e: XTwitterScraperInvalidDataException) {
                    false
                }

            internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is Health && value == other.value
            }

            override fun hashCode() = value.hashCode()

            override fun toString() = value.toString()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is XAccountConnectionChallenge &&
                id == other.id &&
                expiresAt == other.expiresAt &&
                message == other.message &&
                username == other.username &&
                createdAt == other.createdAt &&
                health == other.health &&
                object_ == other.object_ &&
                pollAfterMs == other.pollAfterMs &&
                status == other.status &&
                xUserId == other.xUserId &&
                xUsername == other.xUsername &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(
                id,
                expiresAt,
                message,
                username,
                createdAt,
                health,
                object_,
                pollAfterMs,
                status,
                xUserId,
                xUsername,
                additionalProperties,
            )
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "XAccountConnectionChallenge{id=$id, expiresAt=$expiresAt, message=$message, username=$username, createdAt=$createdAt, health=$health, object_=$object_, pollAfterMs=$pollAfterMs, status=$status, xUserId=$xUserId, xUsername=$xUsername, additionalProperties=$additionalProperties}"
    }
}
