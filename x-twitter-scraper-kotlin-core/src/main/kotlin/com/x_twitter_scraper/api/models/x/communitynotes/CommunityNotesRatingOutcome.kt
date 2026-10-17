package com.x_twitter_scraper.api.models.x.communitynotes

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

/** A group of a contributor's ratings, by what each one said. */
class CommunityNotesRatingOutcome
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val helpfulCount: JsonField<Long>,
    private val notHelpfulCount: JsonField<Long>,
    private val total: JsonField<Long>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("helpfulCount")
        @ExcludeMissing
        helpfulCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("notHelpfulCount")
        @ExcludeMissing
        notHelpfulCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("total") @ExcludeMissing total: JsonField<Long> = JsonMissing.of(),
    ) : this(helpfulCount, notHelpfulCount, total, mutableMapOf())

    /**
     * Ratings that called a note helpful.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun helpfulCount(): Long? = helpfulCount.getNullable("helpfulCount")

    /**
     * Ratings that called a note not helpful.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun notHelpfulCount(): Long? = notHelpfulCount.getNullable("notHelpfulCount")

    /**
     * All ratings in the group, when X sends the sum.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun total(): Long? = total.getNullable("total")

    /**
     * Returns the raw JSON value of [helpfulCount].
     *
     * Unlike [helpfulCount], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("helpfulCount")
    @ExcludeMissing
    fun _helpfulCount(): JsonField<Long> = helpfulCount

    /**
     * Returns the raw JSON value of [notHelpfulCount].
     *
     * Unlike [notHelpfulCount], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("notHelpfulCount")
    @ExcludeMissing
    fun _notHelpfulCount(): JsonField<Long> = notHelpfulCount

    /**
     * Returns the raw JSON value of [total].
     *
     * Unlike [total], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("total") @ExcludeMissing fun _total(): JsonField<Long> = total

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
         * Returns a mutable builder for constructing an instance of [CommunityNotesRatingOutcome].
         */
        fun builder() = Builder()
    }

    /** A builder for [CommunityNotesRatingOutcome]. */
    class Builder internal constructor() {

        private var helpfulCount: JsonField<Long> = JsonMissing.of()
        private var notHelpfulCount: JsonField<Long> = JsonMissing.of()
        private var total: JsonField<Long> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        internal fun from(communityNotesRatingOutcome: CommunityNotesRatingOutcome) = apply {
            helpfulCount = communityNotesRatingOutcome.helpfulCount
            notHelpfulCount = communityNotesRatingOutcome.notHelpfulCount
            total = communityNotesRatingOutcome.total
            additionalProperties = communityNotesRatingOutcome.additionalProperties.toMutableMap()
        }

        /** Ratings that called a note helpful. */
        fun helpfulCount(helpfulCount: Long) = helpfulCount(JsonField.of(helpfulCount))

        /** Sets [Builder.helpfulCount] to an arbitrary JSON value. */
        fun helpfulCount(helpfulCount: JsonField<Long>) = apply {
            this.helpfulCount = helpfulCount
        }

        /** Ratings that called a note not helpful. */
        fun notHelpfulCount(notHelpfulCount: Long) = notHelpfulCount(JsonField.of(notHelpfulCount))

        /** Sets [Builder.notHelpfulCount] to an arbitrary JSON value. */
        fun notHelpfulCount(notHelpfulCount: JsonField<Long>) = apply {
            this.notHelpfulCount = notHelpfulCount
        }

        /** All ratings in the group, when X sends the sum. */
        fun total(total: Long) = total(JsonField.of(total))

        /** Sets [Builder.total] to an arbitrary JSON value. */
        fun total(total: JsonField<Long>) = apply { this.total = total }

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

        fun removeAdditionalProperty(key: String) = apply {
            additionalProperties.remove(key)
        }

        fun removeAllAdditionalProperties(keys: Set<String>) = apply {
            keys.forEach(::removeAdditionalProperty)
        }

        /**
         * Returns an immutable instance of [CommunityNotesRatingOutcome].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): CommunityNotesRatingOutcome =
            CommunityNotesRatingOutcome(
                helpfulCount,
                notHelpfulCount,
                total,
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
    fun validate(): CommunityNotesRatingOutcome = apply {
        if (validated) {
            return@apply
        }

        helpfulCount()
        notHelpfulCount()
        total()
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
        (if (helpfulCount.asKnown() == null) 0 else 1) +
            (if (notHelpfulCount.asKnown() == null) 0 else 1) +
            (if (total.asKnown() == null) 0 else 1)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is CommunityNotesRatingOutcome &&
            helpfulCount == other.helpfulCount &&
            notHelpfulCount == other.notHelpfulCount &&
            total == other.total &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(helpfulCount, notHelpfulCount, total, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "CommunityNotesRatingOutcome{helpfulCount=$helpfulCount, notHelpfulCount=$notHelpfulCount, total=$total, additionalProperties=$additionalProperties}"
}
