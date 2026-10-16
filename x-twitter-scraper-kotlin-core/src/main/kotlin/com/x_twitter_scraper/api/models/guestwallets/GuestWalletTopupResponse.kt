// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.guestwallets

import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.x_twitter_scraper.api.core.Enum
import com.x_twitter_scraper.api.core.ExcludeMissing
import com.x_twitter_scraper.api.core.JsonField
import com.x_twitter_scraper.api.core.JsonMissing
import com.x_twitter_scraper.api.core.JsonValue
import com.x_twitter_scraper.api.core.checkRequired
import com.x_twitter_scraper.api.errors.XTwitterScraperInvalidDataException
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects

/** Pending hosted checkout and guest wallet purchase details. */
class GuestWalletTopupResponse
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val amount: JsonField<GuestWalletAmount>,
    private val checkoutUrl: JsonField<String>,
    private val credits: JsonField<String>,
    private val expiresAt: JsonField<OffsetDateTime>,
    private val instructions: JsonField<String>,
    private val purchaseId: JsonField<String>,
    private val status: JsonField<Status>,
    private val walletId: JsonField<String>,
    private val accountRequired: JsonValue,
    private val apiKey: JsonField<String>,
    private val authorization: JsonField<Authorization>,
    private val credentialNotice: JsonField<CredentialNotice>,
    private val pollAfterSeconds: JsonValue,
    private val requiresUserInteraction: JsonValue,
    private val statusUrl: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("amount")
        @ExcludeMissing
        amount: JsonField<GuestWalletAmount> = JsonMissing.of(),
        @JsonProperty("checkout_url")
        @ExcludeMissing
        checkoutUrl: JsonField<String> = JsonMissing.of(),
        @JsonProperty("credits") @ExcludeMissing credits: JsonField<String> = JsonMissing.of(),
        @JsonProperty("expires_at")
        @ExcludeMissing
        expiresAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("instructions")
        @ExcludeMissing
        instructions: JsonField<String> = JsonMissing.of(),
        @JsonProperty("purchase_id")
        @ExcludeMissing
        purchaseId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("status") @ExcludeMissing status: JsonField<Status> = JsonMissing.of(),
        @JsonProperty("wallet_id") @ExcludeMissing walletId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("account_required")
        @ExcludeMissing
        accountRequired: JsonValue = JsonMissing.of(),
        @JsonProperty("api_key") @ExcludeMissing apiKey: JsonField<String> = JsonMissing.of(),
        @JsonProperty("authorization")
        @ExcludeMissing
        authorization: JsonField<Authorization> = JsonMissing.of(),
        @JsonProperty("credential_notice")
        @ExcludeMissing
        credentialNotice: JsonField<CredentialNotice> = JsonMissing.of(),
        @JsonProperty("poll_after_seconds")
        @ExcludeMissing
        pollAfterSeconds: JsonValue = JsonMissing.of(),
        @JsonProperty("requires_user_interaction")
        @ExcludeMissing
        requiresUserInteraction: JsonValue = JsonMissing.of(),
        @JsonProperty("status_url") @ExcludeMissing statusUrl: JsonValue = JsonMissing.of(),
    ) : this(
        amount,
        checkoutUrl,
        credits,
        expiresAt,
        instructions,
        purchaseId,
        status,
        walletId,
        accountRequired,
        apiKey,
        authorization,
        credentialNotice,
        pollAfterSeconds,
        requiresUserInteraction,
        statusUrl,
        mutableMapOf(),
    )

    /**
     * Confirmed USD amount for a guest wallet purchase.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun amount(): GuestWalletAmount = amount.getRequired("amount")

    /**
     * Hosted checkout URL for user interaction.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun checkoutUrl(): String = checkoutUrl.getRequired("checkout_url")

    /**
     * Credits granted after verified payment.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun credits(): String = credits.getRequired("credits")

    /**
     * Time when the pending checkout expires.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun expiresAt(): OffsetDateTime = expiresAt.getRequired("expires_at")

    /**
     * Hosted checkout and status polling instructions.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun instructions(): String = instructions.getRequired("instructions")

    /**
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun purchaseId(): String = purchaseId.getRequired("purchase_id")

    /**
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun status(): Status = status.getRequired("status")

    /**
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun walletId(): String = walletId.getRequired("wallet_id")

    /**
     * Expected to always return the following: ```kotlin JsonValue.from(false) ```
     *
     * Expected to always return the following:
     * ```kotlin
     * JsonValue.from(false)
     * ```
     */
    @JsonProperty("account_required")
    @ExcludeMissing
    fun _accountRequired(): JsonValue = accountRequired

    /**
     * Paid-read bearer credential returned only by initial creation. Store it as a secret. Never
     * place it in a URL or log.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun apiKey(): String? = apiKey.getNullable("api_key")

    /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
    fun authorization(): Authorization? = authorization.getNullable("authorization")

    /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
    fun credentialNotice(): CredentialNotice? = credentialNotice.getNullable("credential_notice")

    /**
     * Wait at least this long before polling status_url.
     *
     * Expected to always return the following:
     * ```kotlin
     * JsonValue.from(2)
     * ```
     */
    @JsonProperty("poll_after_seconds")
    @ExcludeMissing
    fun _pollAfterSeconds(): JsonValue = pollAfterSeconds

    /**
     * Expected to always return the following: ```kotlin JsonValue.from(true) ```
     *
     * Expected to always return the following:
     * ```kotlin
     * JsonValue.from(true)
     * ```
     */
    @JsonProperty("requires_user_interaction")
    @ExcludeMissing
    fun _requiresUserInteraction(): JsonValue = requiresUserInteraction

    /**
     * Expected to always return the following: ```kotlin
     * JsonValue.from("https://xquik.com/api/v1/guest-wallets/status") ```
     *
     * Expected to always return the following:
     * ```kotlin
     * JsonValue.from("https://xquik.com/api/v1/guest-wallets/status")
     * ```
     */
    @JsonProperty("status_url") @ExcludeMissing fun _statusUrl(): JsonValue = statusUrl

    /**
     * Returns the raw JSON value of [amount].
     *
     * Unlike [amount], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("amount") @ExcludeMissing fun _amount(): JsonField<GuestWalletAmount> = amount

    /**
     * Returns the raw JSON value of [checkoutUrl].
     *
     * Unlike [checkoutUrl], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("checkout_url")
    @ExcludeMissing
    fun _checkoutUrl(): JsonField<String> = checkoutUrl

    /**
     * Returns the raw JSON value of [credits].
     *
     * Unlike [credits], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("credits") @ExcludeMissing fun _credits(): JsonField<String> = credits

    /**
     * Returns the raw JSON value of [expiresAt].
     *
     * Unlike [expiresAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("expires_at")
    @ExcludeMissing
    fun _expiresAt(): JsonField<OffsetDateTime> = expiresAt

    /**
     * Returns the raw JSON value of [instructions].
     *
     * Unlike [instructions], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("instructions")
    @ExcludeMissing
    fun _instructions(): JsonField<String> = instructions

    /**
     * Returns the raw JSON value of [purchaseId].
     *
     * Unlike [purchaseId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("purchase_id") @ExcludeMissing fun _purchaseId(): JsonField<String> = purchaseId

    /**
     * Returns the raw JSON value of [status].
     *
     * Unlike [status], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("status") @ExcludeMissing fun _status(): JsonField<Status> = status

    /**
     * Returns the raw JSON value of [walletId].
     *
     * Unlike [walletId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("wallet_id") @ExcludeMissing fun _walletId(): JsonField<String> = walletId

    /**
     * Returns the raw JSON value of [apiKey].
     *
     * Unlike [apiKey], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("api_key") @ExcludeMissing fun _apiKey(): JsonField<String> = apiKey

    /**
     * Returns the raw JSON value of [authorization].
     *
     * Unlike [authorization], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("authorization")
    @ExcludeMissing
    fun _authorization(): JsonField<Authorization> = authorization

    /**
     * Returns the raw JSON value of [credentialNotice].
     *
     * Unlike [credentialNotice], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("credential_notice")
    @ExcludeMissing
    fun _credentialNotice(): JsonField<CredentialNotice> = credentialNotice

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
         * Returns a mutable builder for constructing an instance of [GuestWalletTopupResponse].
         *
         * The following fields are required:
         * ```kotlin
         * .amount()
         * .checkoutUrl()
         * .credits()
         * .expiresAt()
         * .instructions()
         * .purchaseId()
         * .status()
         * .walletId()
         * ```
         */
        fun builder() = Builder()
    }

    /** A builder for [GuestWalletTopupResponse]. */
    class Builder internal constructor() {

        private var amount: JsonField<GuestWalletAmount>? = null
        private var checkoutUrl: JsonField<String>? = null
        private var credits: JsonField<String>? = null
        private var expiresAt: JsonField<OffsetDateTime>? = null
        private var instructions: JsonField<String>? = null
        private var purchaseId: JsonField<String>? = null
        private var status: JsonField<Status>? = null
        private var walletId: JsonField<String>? = null
        private var accountRequired: JsonValue = JsonValue.from(false)
        private var apiKey: JsonField<String> = JsonMissing.of()
        private var authorization: JsonField<Authorization> = JsonMissing.of()
        private var credentialNotice: JsonField<CredentialNotice> = JsonMissing.of()
        private var pollAfterSeconds: JsonValue = JsonValue.from(2)
        private var requiresUserInteraction: JsonValue = JsonValue.from(true)
        private var statusUrl: JsonValue =
            JsonValue.from("https://xquik.com/api/v1/guest-wallets/status")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        internal fun from(guestWalletTopupResponse: GuestWalletTopupResponse) = apply {
            amount = guestWalletTopupResponse.amount
            checkoutUrl = guestWalletTopupResponse.checkoutUrl
            credits = guestWalletTopupResponse.credits
            expiresAt = guestWalletTopupResponse.expiresAt
            instructions = guestWalletTopupResponse.instructions
            purchaseId = guestWalletTopupResponse.purchaseId
            status = guestWalletTopupResponse.status
            walletId = guestWalletTopupResponse.walletId
            accountRequired = guestWalletTopupResponse.accountRequired
            apiKey = guestWalletTopupResponse.apiKey
            authorization = guestWalletTopupResponse.authorization
            credentialNotice = guestWalletTopupResponse.credentialNotice
            pollAfterSeconds = guestWalletTopupResponse.pollAfterSeconds
            requiresUserInteraction = guestWalletTopupResponse.requiresUserInteraction
            statusUrl = guestWalletTopupResponse.statusUrl
            additionalProperties = guestWalletTopupResponse.additionalProperties.toMutableMap()
        }

        /** Confirmed USD amount for a guest wallet purchase. */
        fun amount(amount: GuestWalletAmount) = amount(JsonField.of(amount))

        /** Sets [Builder.amount] to an arbitrary JSON value. */
        fun amount(amount: JsonField<GuestWalletAmount>) = apply { this.amount = amount }

        /** Hosted checkout URL for user interaction. */
        fun checkoutUrl(checkoutUrl: String) = checkoutUrl(JsonField.of(checkoutUrl))

        /** Sets [Builder.checkoutUrl] to an arbitrary JSON value. */
        fun checkoutUrl(checkoutUrl: JsonField<String>) = apply { this.checkoutUrl = checkoutUrl }

        /** Credits granted after verified payment. */
        fun credits(credits: String) = credits(JsonField.of(credits))

        /** Sets [Builder.credits] to an arbitrary JSON value. */
        fun credits(credits: JsonField<String>) = apply { this.credits = credits }

        /** Time when the pending checkout expires. */
        fun expiresAt(expiresAt: OffsetDateTime) = expiresAt(JsonField.of(expiresAt))

        /** Sets [Builder.expiresAt] to an arbitrary JSON value. */
        fun expiresAt(expiresAt: JsonField<OffsetDateTime>) = apply { this.expiresAt = expiresAt }

        /** Hosted checkout and status polling instructions. */
        fun instructions(instructions: String) = instructions(JsonField.of(instructions))

        /** Sets [Builder.instructions] to an arbitrary JSON value. */
        fun instructions(instructions: JsonField<String>) = apply {
            this.instructions = instructions
        }

        fun purchaseId(purchaseId: String) = purchaseId(JsonField.of(purchaseId))

        /** Sets [Builder.purchaseId] to an arbitrary JSON value. */
        fun purchaseId(purchaseId: JsonField<String>) = apply { this.purchaseId = purchaseId }

        fun status(status: Status) = status(JsonField.of(status))

        /** Sets [Builder.status] to an arbitrary JSON value. */
        fun status(status: JsonField<Status>) = apply { this.status = status }

        fun walletId(walletId: String) = walletId(JsonField.of(walletId))

        /** Sets [Builder.walletId] to an arbitrary JSON value. */
        fun walletId(walletId: JsonField<String>) = apply { this.walletId = walletId }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * Defaults to the following:
         * ```kotlin
         * JsonValue.from(false)
         * ```
         */
        fun accountRequired(accountRequired: JsonValue) = apply {
            this.accountRequired = accountRequired
        }

        /**
         * Paid-read bearer credential returned only by initial creation. Store it as a secret.
         * Never place it in a URL or log.
         */
        fun apiKey(apiKey: String) = apiKey(JsonField.of(apiKey))

        /** Sets [Builder.apiKey] to an arbitrary JSON value. */
        fun apiKey(apiKey: JsonField<String>) = apply { this.apiKey = apiKey }

        fun authorization(authorization: Authorization) = authorization(JsonField.of(authorization))

        /** Sets [Builder.authorization] to an arbitrary JSON value. */
        fun authorization(authorization: JsonField<Authorization>) = apply {
            this.authorization = authorization
        }

        fun credentialNotice(credentialNotice: CredentialNotice) =
            credentialNotice(JsonField.of(credentialNotice))

        /** Sets [Builder.credentialNotice] to an arbitrary JSON value. */
        fun credentialNotice(credentialNotice: JsonField<CredentialNotice>) = apply {
            this.credentialNotice = credentialNotice
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * Defaults to the following:
         * ```kotlin
         * JsonValue.from(2)
         * ```
         */
        fun pollAfterSeconds(pollAfterSeconds: JsonValue) = apply {
            this.pollAfterSeconds = pollAfterSeconds
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * Defaults to the following:
         * ```kotlin
         * JsonValue.from(true)
         * ```
         */
        fun requiresUserInteraction(requiresUserInteraction: JsonValue) = apply {
            this.requiresUserInteraction = requiresUserInteraction
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * Defaults to the following:
         * ```kotlin
         * JsonValue.from("https://xquik.com/api/v1/guest-wallets/status")
         * ```
         */
        fun statusUrl(statusUrl: JsonValue) = apply { this.statusUrl = statusUrl }

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
         * Returns an immutable instance of [GuestWalletTopupResponse].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```kotlin
         * .amount()
         * .checkoutUrl()
         * .credits()
         * .expiresAt()
         * .instructions()
         * .purchaseId()
         * .status()
         * .walletId()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): GuestWalletTopupResponse =
            GuestWalletTopupResponse(
                checkRequired("amount", amount),
                checkRequired("checkoutUrl", checkoutUrl),
                checkRequired("credits", credits),
                checkRequired("expiresAt", expiresAt),
                checkRequired("instructions", instructions),
                checkRequired("purchaseId", purchaseId),
                checkRequired("status", status),
                checkRequired("walletId", walletId),
                accountRequired,
                apiKey,
                authorization,
                credentialNotice,
                pollAfterSeconds,
                requiresUserInteraction,
                statusUrl,
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
    fun validate(): GuestWalletTopupResponse = apply {
        if (validated) {
            return@apply
        }

        amount().validate()
        checkoutUrl()
        credits()
        expiresAt()
        instructions()
        purchaseId()
        status().validate()
        walletId()
        _accountRequired().let {
            if (it != JsonValue.from(false)) {
                throw XTwitterScraperInvalidDataException(
                    "'accountRequired' is invalid, received $it"
                )
            }
        }
        apiKey()
        authorization()?.validate()
        credentialNotice()?.validate()
        _pollAfterSeconds().let {
            if (it != JsonValue.from(2)) {
                throw XTwitterScraperInvalidDataException(
                    "'pollAfterSeconds' is invalid, received $it"
                )
            }
        }
        _requiresUserInteraction().let {
            if (it != JsonValue.from(true)) {
                throw XTwitterScraperInvalidDataException(
                    "'requiresUserInteraction' is invalid, received $it"
                )
            }
        }
        _statusUrl().let {
            if (it != JsonValue.from("https://xquik.com/api/v1/guest-wallets/status")) {
                throw XTwitterScraperInvalidDataException("'statusUrl' is invalid, received $it")
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
        (amount.asKnown()?.validity() ?: 0) +
            (if (checkoutUrl.asKnown() == null) 0 else 1) +
            (if (credits.asKnown() == null) 0 else 1) +
            (if (expiresAt.asKnown() == null) 0 else 1) +
            (if (instructions.asKnown() == null) 0 else 1) +
            (if (purchaseId.asKnown() == null) 0 else 1) +
            (status.asKnown()?.validity() ?: 0) +
            (if (walletId.asKnown() == null) 0 else 1) +
            accountRequired.let { if (it == JsonValue.from(false)) 1 else 0 } +
            (if (apiKey.asKnown() == null) 0 else 1) +
            (authorization.asKnown()?.validity() ?: 0) +
            (credentialNotice.asKnown()?.validity() ?: 0) +
            pollAfterSeconds.let { if (it == JsonValue.from(2)) 1 else 0 } +
            requiresUserInteraction.let { if (it == JsonValue.from(true)) 1 else 0 } +
            statusUrl.let {
                if (it == JsonValue.from("https://xquik.com/api/v1/guest-wallets/status")) 1 else 0
            }

    class Status @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

        /** Returns this class instance's raw value. */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            val CREATING = of("creating")

            val PENDING = of("pending")

            val PAID = of("paid")

            val EXPIRED = of("expired")

            val FAILED = of("failed")

            val REFUNDED = of("refunded")

            val DISPUTED = of("disputed")

            fun of(value: String) = Status(JsonField.of(value))
        }

        /** An enum containing [Status]'s known values. */
        enum class Known {
            CREATING,
            PENDING,
            PAID,
            EXPIRED,
            FAILED,
            REFUNDED,
            DISPUTED,
        }

        /**
         * An enum containing [Status]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Status] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            CREATING,
            PENDING,
            PAID,
            EXPIRED,
            FAILED,
            REFUNDED,
            DISPUTED,
            /** An enum member indicating that [Status] was instantiated with an unknown value. */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         */
        fun value(): Value =
            when (this) {
                CREATING -> Value.CREATING
                PENDING -> Value.PENDING
                PAID -> Value.PAID
                EXPIRED -> Value.EXPIRED
                FAILED -> Value.FAILED
                REFUNDED -> Value.REFUNDED
                DISPUTED -> Value.DISPUTED
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
                CREATING -> Known.CREATING
                PENDING -> Known.PENDING
                PAID -> Known.PAID
                EXPIRED -> Known.EXPIRED
                FAILED -> Known.FAILED
                REFUNDED -> Known.REFUNDED
                DISPUTED -> Known.DISPUTED
                else -> throw XTwitterScraperInvalidDataException("Unknown Status: $value")
            }

        /**
         * Returns this class instance's primitive wire representation.
         *
         * @throws XTwitterScraperInvalidDataException if this class instance's value does not have
         *   the expected primitive type.
         */
        fun asString(): String =
            _value().asString()
                ?: throw XTwitterScraperInvalidDataException("Value is not a String")

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
        fun validate(): Status = apply {
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

            return other is Status && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    class Authorization
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val header: JsonField<Header>,
        private val scheme: JsonField<Scheme>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("header") @ExcludeMissing header: JsonField<Header> = JsonMissing.of(),
            @JsonProperty("scheme") @ExcludeMissing scheme: JsonField<Scheme> = JsonMissing.of(),
        ) : this(header, scheme, mutableMapOf())

        /**
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or
         *   is unexpectedly missing or null.
         */
        fun header(): Header = header.getRequired("header")

        /**
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or
         *   is unexpectedly missing or null.
         */
        fun scheme(): Scheme = scheme.getRequired("scheme")

        /**
         * Returns the raw JSON value of [header].
         *
         * Unlike [header], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("header") @ExcludeMissing fun _header(): JsonField<Header> = header

        /**
         * Returns the raw JSON value of [scheme].
         *
         * Unlike [scheme], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("scheme") @ExcludeMissing fun _scheme(): JsonField<Scheme> = scheme

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
             * Returns a mutable builder for constructing an instance of [Authorization].
             *
             * The following fields are required:
             * ```kotlin
             * .header()
             * .scheme()
             * ```
             */
            fun builder() = Builder()
        }

        /** A builder for [Authorization]. */
        class Builder internal constructor() {

            private var header: JsonField<Header>? = null
            private var scheme: JsonField<Scheme>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(authorization: Authorization) = apply {
                header = authorization.header
                scheme = authorization.scheme
                additionalProperties = authorization.additionalProperties.toMutableMap()
            }

            fun header(header: Header) = header(JsonField.of(header))

            /** Sets [Builder.header] to an arbitrary JSON value. */
            fun header(header: JsonField<Header>) = apply { this.header = header }

            fun scheme(scheme: Scheme) = scheme(JsonField.of(scheme))

            /** Sets [Builder.scheme] to an arbitrary JSON value. */
            fun scheme(scheme: JsonField<Scheme>) = apply { this.scheme = scheme }

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
             * Returns an immutable instance of [Authorization].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             *
             * The following fields are required:
             * ```kotlin
             * .header()
             * .scheme()
             * ```
             *
             * @throws IllegalStateException if any required field is unset.
             */
            fun build(): Authorization =
                Authorization(
                    checkRequired("header", header),
                    checkRequired("scheme", scheme),
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
        fun validate(): Authorization = apply {
            if (validated) {
                return@apply
            }

            header().validate()
            scheme().validate()
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
            (header.asKnown()?.validity() ?: 0) + (scheme.asKnown()?.validity() ?: 0)

        class Header @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

            /** Returns this class instance's raw value. */
            @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

            companion object {

                val AUTHORIZATION = of("Authorization")

                fun of(value: String) = Header(JsonField.of(value))
            }

            /** An enum containing [Header]'s known values. */
            enum class Known {
                AUTHORIZATION
            }

            /**
             * An enum containing [Header]'s known values, as well as an [_UNKNOWN] member.
             *
             * An instance of [Header] can contain an unknown value in a couple of cases:
             * - It was deserialized from data that doesn't match any known member. For example, if
             *   the SDK is on an older version than the API, then the API may respond with new
             *   members that the SDK is unaware of.
             * - It was constructed with an arbitrary value using the [of] method.
             */
            enum class Value {
                AUTHORIZATION,
                /**
                 * An enum member indicating that [Header] was instantiated with an unknown value.
                 */
                _UNKNOWN,
            }

            /**
             * Returns an enum member corresponding to this class instance's value, or
             * [Value._UNKNOWN] if the class was instantiated with an unknown value.
             */
            fun value(): Value =
                when (this) {
                    AUTHORIZATION -> Value.AUTHORIZATION
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
                    AUTHORIZATION -> Known.AUTHORIZATION
                    else -> throw XTwitterScraperInvalidDataException("Unknown Header: $value")
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
            fun validate(): Header = apply {
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

                return other is Header && value == other.value
            }

            override fun hashCode() = value.hashCode()

            override fun toString() = value.toString()
        }

        class Scheme @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

            /** Returns this class instance's raw value. */
            @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

            companion object {

                val BEARER = of("Bearer")

                fun of(value: String) = Scheme(JsonField.of(value))
            }

            /** An enum containing [Scheme]'s known values. */
            enum class Known {
                BEARER
            }

            /**
             * An enum containing [Scheme]'s known values, as well as an [_UNKNOWN] member.
             *
             * An instance of [Scheme] can contain an unknown value in a couple of cases:
             * - It was deserialized from data that doesn't match any known member. For example, if
             *   the SDK is on an older version than the API, then the API may respond with new
             *   members that the SDK is unaware of.
             * - It was constructed with an arbitrary value using the [of] method.
             */
            enum class Value {
                BEARER,
                /**
                 * An enum member indicating that [Scheme] was instantiated with an unknown value.
                 */
                _UNKNOWN,
            }

            /**
             * Returns an enum member corresponding to this class instance's value, or
             * [Value._UNKNOWN] if the class was instantiated with an unknown value.
             */
            fun value(): Value =
                when (this) {
                    BEARER -> Value.BEARER
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
                    BEARER -> Known.BEARER
                    else -> throw XTwitterScraperInvalidDataException("Unknown Scheme: $value")
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
            fun validate(): Scheme = apply {
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

                return other is Scheme && value == other.value
            }

            override fun hashCode() = value.hashCode()

            override fun toString() = value.toString()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Authorization &&
                header == other.header &&
                scheme == other.scheme &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(header, scheme, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Authorization{header=$header, scheme=$scheme, additionalProperties=$additionalProperties}"
    }

    class CredentialNotice @JsonCreator private constructor(private val value: JsonField<String>) :
        Enum {

        /** Returns this class instance's raw value. */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            val STORE_API_KEY_AND_THE_IDEMPOTENCY_KEY_SECURELY_BEFORE_SHARING_CHECKOUT_URL_NO_EMAIL_RECOVERY_IS_AVAILABLE =
                of(
                    "Store api_key and the Idempotency-Key securely before sharing checkout_url. No email recovery is available."
                )

            fun of(value: String) = CredentialNotice(JsonField.of(value))
        }

        /** An enum containing [CredentialNotice]'s known values. */
        enum class Known {
            STORE_API_KEY_AND_THE_IDEMPOTENCY_KEY_SECURELY_BEFORE_SHARING_CHECKOUT_URL_NO_EMAIL_RECOVERY_IS_AVAILABLE
        }

        /**
         * An enum containing [CredentialNotice]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [CredentialNotice] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            STORE_API_KEY_AND_THE_IDEMPOTENCY_KEY_SECURELY_BEFORE_SHARING_CHECKOUT_URL_NO_EMAIL_RECOVERY_IS_AVAILABLE,
            /**
             * An enum member indicating that [CredentialNotice] was instantiated with an unknown
             * value.
             */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         */
        fun value(): Value =
            when (this) {
                STORE_API_KEY_AND_THE_IDEMPOTENCY_KEY_SECURELY_BEFORE_SHARING_CHECKOUT_URL_NO_EMAIL_RECOVERY_IS_AVAILABLE ->
                    Value
                        .STORE_API_KEY_AND_THE_IDEMPOTENCY_KEY_SECURELY_BEFORE_SHARING_CHECKOUT_URL_NO_EMAIL_RECOVERY_IS_AVAILABLE
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
                STORE_API_KEY_AND_THE_IDEMPOTENCY_KEY_SECURELY_BEFORE_SHARING_CHECKOUT_URL_NO_EMAIL_RECOVERY_IS_AVAILABLE ->
                    Known
                        .STORE_API_KEY_AND_THE_IDEMPOTENCY_KEY_SECURELY_BEFORE_SHARING_CHECKOUT_URL_NO_EMAIL_RECOVERY_IS_AVAILABLE
                else ->
                    throw XTwitterScraperInvalidDataException("Unknown CredentialNotice: $value")
            }

        /**
         * Returns this class instance's primitive wire representation.
         *
         * @throws XTwitterScraperInvalidDataException if this class instance's value does not have
         *   the expected primitive type.
         */
        fun asString(): String =
            _value().asString()
                ?: throw XTwitterScraperInvalidDataException("Value is not a String")

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
        fun validate(): CredentialNotice = apply {
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

            return other is CredentialNotice && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is GuestWalletTopupResponse &&
            amount == other.amount &&
            checkoutUrl == other.checkoutUrl &&
            credits == other.credits &&
            expiresAt == other.expiresAt &&
            instructions == other.instructions &&
            purchaseId == other.purchaseId &&
            status == other.status &&
            walletId == other.walletId &&
            accountRequired == other.accountRequired &&
            apiKey == other.apiKey &&
            authorization == other.authorization &&
            credentialNotice == other.credentialNotice &&
            pollAfterSeconds == other.pollAfterSeconds &&
            requiresUserInteraction == other.requiresUserInteraction &&
            statusUrl == other.statusUrl &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            amount,
            checkoutUrl,
            credits,
            expiresAt,
            instructions,
            purchaseId,
            status,
            walletId,
            accountRequired,
            apiKey,
            authorization,
            credentialNotice,
            pollAfterSeconds,
            requiresUserInteraction,
            statusUrl,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "GuestWalletTopupResponse{amount=$amount, checkoutUrl=$checkoutUrl, credits=$credits, expiresAt=$expiresAt, instructions=$instructions, purchaseId=$purchaseId, status=$status, walletId=$walletId, accountRequired=$accountRequired, apiKey=$apiKey, authorization=$authorization, credentialNotice=$credentialNotice, pollAfterSeconds=$pollAfterSeconds, requiresUserInteraction=$requiresUserInteraction, statusUrl=$statusUrl, additionalProperties=$additionalProperties}"
}
