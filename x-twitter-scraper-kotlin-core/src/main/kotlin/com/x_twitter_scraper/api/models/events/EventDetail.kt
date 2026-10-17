// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.events

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
import com.x_twitter_scraper.api.core.toImmutable
import com.x_twitter_scraper.api.errors.XTwitterScraperInvalidDataException
import com.x_twitter_scraper.api.models.EventType
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects

/** Full monitor event including payload data and optional X event ID. */
class EventDetail
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val data: JsonField<Data>,
    private val monitorId: JsonField<String>,
    private val monitorType: JsonField<MonitorType>,
    private val occurredAt: JsonField<OffsetDateTime>,
    private val type: JsonField<EventType>,
    private val keywordMonitorId: JsonField<String>,
    private val monitorName: JsonField<String>,
    private val query: JsonField<String>,
    private val username: JsonField<String>,
    private val xEventId: JsonField<String>,
    private val xUserId: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("data") @ExcludeMissing data: JsonField<Data> = JsonMissing.of(),
        @JsonProperty("monitorId") @ExcludeMissing monitorId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("monitorType")
        @ExcludeMissing
        monitorType: JsonField<MonitorType> = JsonMissing.of(),
        @JsonProperty("occurredAt")
        @ExcludeMissing
        occurredAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonField<EventType> = JsonMissing.of(),
        @JsonProperty("keywordMonitorId")
        @ExcludeMissing
        keywordMonitorId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("monitorName")
        @ExcludeMissing
        monitorName: JsonField<String> = JsonMissing.of(),
        @JsonProperty("query") @ExcludeMissing query: JsonField<String> = JsonMissing.of(),
        @JsonProperty("username") @ExcludeMissing username: JsonField<String> = JsonMissing.of(),
        @JsonProperty("xEventId") @ExcludeMissing xEventId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("xUserId") @ExcludeMissing xUserId: JsonField<String> = JsonMissing.of(),
    ) : this(
        id,
        data,
        monitorId,
        monitorType,
        occurredAt,
        type,
        keywordMonitorId,
        monitorName,
        query,
        username,
        xEventId,
        xUserId,
        mutableMapOf(),
    )

    /**
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun id(): String = id.getRequired("id")

    /**
     * Event payload - shape varies by event type (JSON)
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun data(): Data = data.getRequired("data")

    /**
     * Monitor ID associated with this detailed event payload.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun monitorId(): String = monitorId.getRequired("monitorId")

    /**
     * Source monitor type for this detailed event.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun monitorType(): MonitorType = monitorType.getRequired("monitorType")

    /**
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun occurredAt(): OffsetDateTime = occurredAt.getRequired("occurredAt")

    /**
     * Type of monitor event fired when account activity occurs.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun type(): EventType = type.getRequired("type")

    /**
     * Keyword monitor ID included on detailed keyword events.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun keywordMonitorId(): String? = keywordMonitorId.getNullable("keywordMonitorId")

    /**
     * Your monitor label when Xquik created this event, present when set. A rename leaves the
     * event's label unchanged.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun monitorName(): String? = monitorName.getNullable("monitorName")

    /**
     * Keyword query for this detailed monitor event.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun query(): String? = query.getNullable("query")

    /**
     * Account username for this detailed monitor event.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun username(): String? = username.getNullable("username")

    /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
    fun xEventId(): String? = xEventId.getNullable("xEventId")

    /**
     * Permanent X user ID of the monitored account on account events. It stays the same after a
     * username change.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun xUserId(): String? = xUserId.getNullable("xUserId")

    /**
     * Returns the raw JSON value of [id].
     *
     * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    /**
     * Returns the raw JSON value of [data].
     *
     * Unlike [data], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("data") @ExcludeMissing fun _data(): JsonField<Data> = data

    /**
     * Returns the raw JSON value of [monitorId].
     *
     * Unlike [monitorId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("monitorId") @ExcludeMissing fun _monitorId(): JsonField<String> = monitorId

    /**
     * Returns the raw JSON value of [monitorType].
     *
     * Unlike [monitorType], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("monitorType")
    @ExcludeMissing
    fun _monitorType(): JsonField<MonitorType> = monitorType

    /**
     * Returns the raw JSON value of [occurredAt].
     *
     * Unlike [occurredAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("occurredAt")
    @ExcludeMissing
    fun _occurredAt(): JsonField<OffsetDateTime> = occurredAt

    /**
     * Returns the raw JSON value of [type].
     *
     * Unlike [type], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<EventType> = type

    /**
     * Returns the raw JSON value of [keywordMonitorId].
     *
     * Unlike [keywordMonitorId], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("keywordMonitorId")
    @ExcludeMissing
    fun _keywordMonitorId(): JsonField<String> = keywordMonitorId

    /**
     * Returns the raw JSON value of [monitorName].
     *
     * Unlike [monitorName], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("monitorName") @ExcludeMissing fun _monitorName(): JsonField<String> = monitorName

    /**
     * Returns the raw JSON value of [query].
     *
     * Unlike [query], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("query") @ExcludeMissing fun _query(): JsonField<String> = query

    /**
     * Returns the raw JSON value of [username].
     *
     * Unlike [username], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("username") @ExcludeMissing fun _username(): JsonField<String> = username

    /**
     * Returns the raw JSON value of [xEventId].
     *
     * Unlike [xEventId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("xEventId") @ExcludeMissing fun _xEventId(): JsonField<String> = xEventId

    /**
     * Returns the raw JSON value of [xUserId].
     *
     * Unlike [xUserId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("xUserId") @ExcludeMissing fun _xUserId(): JsonField<String> = xUserId

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
         * Returns a mutable builder for constructing an instance of [EventDetail].
         *
         * The following fields are required:
         * ```kotlin
         * .id()
         * .data()
         * .monitorId()
         * .monitorType()
         * .occurredAt()
         * .type()
         * ```
         */
        fun builder() = Builder()
    }

    /** A builder for [EventDetail]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var data: JsonField<Data>? = null
        private var monitorId: JsonField<String>? = null
        private var monitorType: JsonField<MonitorType>? = null
        private var occurredAt: JsonField<OffsetDateTime>? = null
        private var type: JsonField<EventType>? = null
        private var keywordMonitorId: JsonField<String> = JsonMissing.of()
        private var monitorName: JsonField<String> = JsonMissing.of()
        private var query: JsonField<String> = JsonMissing.of()
        private var username: JsonField<String> = JsonMissing.of()
        private var xEventId: JsonField<String> = JsonMissing.of()
        private var xUserId: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        internal fun from(eventDetail: EventDetail) = apply {
            id = eventDetail.id
            data = eventDetail.data
            monitorId = eventDetail.monitorId
            monitorType = eventDetail.monitorType
            occurredAt = eventDetail.occurredAt
            type = eventDetail.type
            keywordMonitorId = eventDetail.keywordMonitorId
            monitorName = eventDetail.monitorName
            query = eventDetail.query
            username = eventDetail.username
            xEventId = eventDetail.xEventId
            xUserId = eventDetail.xUserId
            additionalProperties = eventDetail.additionalProperties.toMutableMap()
        }

        fun id(id: String) = id(JsonField.of(id))

        /** Sets [Builder.id] to an arbitrary JSON value. */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /** Event payload - shape varies by event type (JSON) */
        fun data(data: Data) = data(JsonField.of(data))

        /** Sets [Builder.data] to an arbitrary JSON value. */
        fun data(data: JsonField<Data>) = apply { this.data = data }

        /** Monitor ID associated with this detailed event payload. */
        fun monitorId(monitorId: String) = monitorId(JsonField.of(monitorId))

        /** Sets [Builder.monitorId] to an arbitrary JSON value. */
        fun monitorId(monitorId: JsonField<String>) = apply { this.monitorId = monitorId }

        /** Source monitor type for this detailed event. */
        fun monitorType(monitorType: MonitorType) = monitorType(JsonField.of(monitorType))

        /** Sets [Builder.monitorType] to an arbitrary JSON value. */
        fun monitorType(monitorType: JsonField<MonitorType>) = apply {
            this.monitorType = monitorType
        }

        /**
         * When the event happened by X's clock, for a post, a live video or a Space. X gives no
         * time for a deleted post, a profile change or a follow list change, so those hold when
         * Xquik saw them.
         */
        fun occurredAt(occurredAt: OffsetDateTime) = occurredAt(JsonField.of(occurredAt))

        /** Sets [Builder.occurredAt] to an arbitrary JSON value. */
        fun occurredAt(occurredAt: JsonField<OffsetDateTime>) = apply {
            this.occurredAt = occurredAt
        }

        /** Type of monitor event fired when account activity occurs. */
        fun type(type: EventType) = type(JsonField.of(type))

        /** Sets [Builder.type] to an arbitrary JSON value. */
        fun type(type: JsonField<EventType>) = apply { this.type = type }

        /** Keyword monitor ID included on detailed keyword events. */
        fun keywordMonitorId(keywordMonitorId: String) =
            keywordMonitorId(JsonField.of(keywordMonitorId))

        /** Sets [Builder.keywordMonitorId] to an arbitrary JSON value. */
        fun keywordMonitorId(keywordMonitorId: JsonField<String>) = apply {
            this.keywordMonitorId = keywordMonitorId
        }

        /**
         * Your monitor label when Xquik created this event, present when set. A rename leaves the
         * event's label unchanged.
         */
        fun monitorName(monitorName: String) = monitorName(JsonField.of(monitorName))

        /** Sets [Builder.monitorName] to an arbitrary JSON value. */
        fun monitorName(monitorName: JsonField<String>) = apply { this.monitorName = monitorName }

        /** Keyword query for this detailed monitor event. */
        fun query(query: String) = query(JsonField.of(query))

        /** Sets [Builder.query] to an arbitrary JSON value. */
        fun query(query: JsonField<String>) = apply { this.query = query }

        /** Account username for this detailed monitor event. */
        fun username(username: String) = username(JsonField.of(username))

        /** Sets [Builder.username] to an arbitrary JSON value. */
        fun username(username: JsonField<String>) = apply { this.username = username }

        fun xEventId(xEventId: String) = xEventId(JsonField.of(xEventId))

        /** Sets [Builder.xEventId] to an arbitrary JSON value. */
        fun xEventId(xEventId: JsonField<String>) = apply { this.xEventId = xEventId }

        /**
         * Permanent X user ID of the monitored account on account events. It stays the same after a
         * username change.
         */
        fun xUserId(xUserId: String) = xUserId(JsonField.of(xUserId))

        /** Sets [Builder.xUserId] to an arbitrary JSON value. */
        fun xUserId(xUserId: JsonField<String>) = apply { this.xUserId = xUserId }

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
         * Returns an immutable instance of [EventDetail].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```kotlin
         * .id()
         * .data()
         * .monitorId()
         * .monitorType()
         * .occurredAt()
         * .type()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): EventDetail =
            EventDetail(
                checkRequired("id", id),
                checkRequired("data", data),
                checkRequired("monitorId", monitorId),
                checkRequired("monitorType", monitorType),
                checkRequired("occurredAt", occurredAt),
                checkRequired("type", type),
                keywordMonitorId,
                monitorName,
                query,
                username,
                xEventId,
                xUserId,
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
    fun validate(): EventDetail = apply {
        if (validated) {
            return@apply
        }

        id()
        data().validate()
        monitorId()
        monitorType().validate()
        occurredAt()
        type().validate()
        keywordMonitorId()
        monitorName()
        query()
        username()
        xEventId()
        xUserId()
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
            (data.asKnown()?.validity() ?: 0) +
            (if (monitorId.asKnown() == null) 0 else 1) +
            (monitorType.asKnown()?.validity() ?: 0) +
            (if (occurredAt.asKnown() == null) 0 else 1) +
            (type.asKnown()?.validity() ?: 0) +
            (if (keywordMonitorId.asKnown() == null) 0 else 1) +
            (if (monitorName.asKnown() == null) 0 else 1) +
            (if (query.asKnown() == null) 0 else 1) +
            (if (username.asKnown() == null) 0 else 1) +
            (if (xEventId.asKnown() == null) 0 else 1) +
            (if (xUserId.asKnown() == null) 0 else 1)

    /** Event payload - shape varies by event type (JSON) */
    class Data
    @JsonCreator
    private constructor(
        @com.fasterxml.jackson.annotation.JsonValue
        private val additionalProperties: Map<String, JsonValue>
    ) {

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

        fun toBuilder() = Builder().from(this)

        companion object {

            /** Returns a mutable builder for constructing an instance of [Data]. */
            fun builder() = Builder()
        }

        /** A builder for [Data]. */
        class Builder internal constructor() {

            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(data: Data) = apply {
                additionalProperties = data.additionalProperties.toMutableMap()
            }

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
             * Returns an immutable instance of [Data].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): Data = Data(additionalProperties.toImmutable())
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
        fun validate(): Data = apply {
            if (validated) {
                return@apply
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

        internal fun validity(): Int = additionalProperties.count { (_, value) ->
            !value.isNull() && !value.isMissing()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Data && additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() = "Data{additionalProperties=$additionalProperties}"
    }

    /** Source monitor type for this detailed event. */
    class MonitorType @JsonCreator private constructor(private val value: JsonField<String>) :
        Enum {

        /** Returns this class instance's raw value. */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            val ACCOUNT = of("account")

            val KEYWORD = of("keyword")

            fun of(value: String) = MonitorType(JsonField.of(value))
        }

        /** An enum containing [MonitorType]'s known values. */
        enum class Known {
            ACCOUNT,
            KEYWORD,
        }

        /**
         * An enum containing [MonitorType]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [MonitorType] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            ACCOUNT,
            KEYWORD,
            /**
             * An enum member indicating that [MonitorType] was instantiated with an unknown value.
             */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         */
        fun value(): Value =
            when (this) {
                ACCOUNT -> Value.ACCOUNT
                KEYWORD -> Value.KEYWORD
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
                ACCOUNT -> Known.ACCOUNT
                KEYWORD -> Known.KEYWORD
                else -> throw XTwitterScraperInvalidDataException("Unknown MonitorType: $value")
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
        fun validate(): MonitorType = apply {
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

            return other is MonitorType && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is EventDetail &&
            id == other.id &&
            data == other.data &&
            monitorId == other.monitorId &&
            monitorType == other.monitorType &&
            occurredAt == other.occurredAt &&
            type == other.type &&
            keywordMonitorId == other.keywordMonitorId &&
            monitorName == other.monitorName &&
            query == other.query &&
            username == other.username &&
            xEventId == other.xEventId &&
            xUserId == other.xUserId &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            id,
            data,
            monitorId,
            monitorType,
            occurredAt,
            type,
            keywordMonitorId,
            monitorName,
            query,
            username,
            xEventId,
            xUserId,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "EventDetail{id=$id, data=$data, monitorId=$monitorId, monitorType=$monitorType, occurredAt=$occurredAt, type=$type, keywordMonitorId=$keywordMonitorId, monitorName=$monitorName, query=$query, username=$username, xEventId=$xEventId, xUserId=$xUserId, additionalProperties=$additionalProperties}"
}
