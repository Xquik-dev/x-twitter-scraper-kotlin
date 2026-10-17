package com.x_twitter_scraper.api.models.x.communitynotes

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
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects

/**
 * A Community Notes contributor. X names contributors by an alias, never by their X account. A
 * field X leaves out is left out. Beside a note, X sends its writer with only some of the counts.
 */
class CommunityNotesContributor
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val alias: JsonField<String>,
    private val deletedNotesCount: JsonField<Long>,
    private val isApiContributor: JsonField<Boolean>,
    private val isTopWriter: JsonField<Boolean>,
    private val notesCount: JsonField<NotesCount>,
    private val ratingsCount: JsonField<RatingsCount>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("alias") @ExcludeMissing alias: JsonField<String> = JsonMissing.of(),
        @JsonProperty("deletedNotesCount")
        @ExcludeMissing
        deletedNotesCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("isApiContributor")
        @ExcludeMissing
        isApiContributor: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("isTopWriter")
        @ExcludeMissing
        isTopWriter: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("notesCount")
        @ExcludeMissing
        notesCount: JsonField<NotesCount> = JsonMissing.of(),
        @JsonProperty("ratingsCount")
        @ExcludeMissing
        ratingsCount: JsonField<RatingsCount> = JsonMissing.of(),
    ) : this(
        alias,
        deletedNotesCount,
        isApiContributor,
        isTopWriter,
        notesCount,
        ratingsCount,
        mutableMapOf(),
    )

    /**
     * The alias X gives the contributor.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun alias(): String = alias.getRequired("alias")

    /**
     * Notes the contributor deleted.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun deletedNotesCount(): Long? = deletedNotesCount.getNullable("deletedNotesCount")

    /**
     * Whether the contributor writes notes through X's API.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun isApiContributor(): Boolean? = isApiContributor.getNullable("isApiContributor")

    /**
     * Whether X marks the contributor as a top writer.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun isTopWriter(): Boolean? = isTopWriter.getNullable("isTopWriter")

    /**
     * How many notes a contributor wrote, by X's current rating.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun notesCount(): NotesCount? = notesCount.getNullable("notesCount")

    /**
     * How a contributor's ratings of other notes went.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun ratingsCount(): RatingsCount? = ratingsCount.getNullable("ratingsCount")

    /**
     * Returns the raw JSON value of [alias].
     *
     * Unlike [alias], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("alias") @ExcludeMissing fun _alias(): JsonField<String> = alias

    /**
     * Returns the raw JSON value of [deletedNotesCount].
     *
     * Unlike [deletedNotesCount], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("deletedNotesCount")
    @ExcludeMissing
    fun _deletedNotesCount(): JsonField<Long> = deletedNotesCount

    /**
     * Returns the raw JSON value of [isApiContributor].
     *
     * Unlike [isApiContributor], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("isApiContributor")
    @ExcludeMissing
    fun _isApiContributor(): JsonField<Boolean> = isApiContributor

    /**
     * Returns the raw JSON value of [isTopWriter].
     *
     * Unlike [isTopWriter], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("isTopWriter")
    @ExcludeMissing
    fun _isTopWriter(): JsonField<Boolean> = isTopWriter

    /**
     * Returns the raw JSON value of [notesCount].
     *
     * Unlike [notesCount], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("notesCount")
    @ExcludeMissing
    fun _notesCount(): JsonField<NotesCount> = notesCount

    /**
     * Returns the raw JSON value of [ratingsCount].
     *
     * Unlike [ratingsCount], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("ratingsCount")
    @ExcludeMissing
    fun _ratingsCount(): JsonField<RatingsCount> = ratingsCount

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
         * Returns a mutable builder for constructing an instance of [CommunityNotesContributor].
         *
         * The following fields are required:
         * ```kotlin
         * .alias()
         * ```
         */
        fun builder() = Builder()
    }

    /** A builder for [CommunityNotesContributor]. */
    class Builder internal constructor() {

        private var alias: JsonField<String>? = null
        private var deletedNotesCount: JsonField<Long> = JsonMissing.of()
        private var isApiContributor: JsonField<Boolean> = JsonMissing.of()
        private var isTopWriter: JsonField<Boolean> = JsonMissing.of()
        private var notesCount: JsonField<NotesCount> = JsonMissing.of()
        private var ratingsCount: JsonField<RatingsCount> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        internal fun from(communityNotesContributor: CommunityNotesContributor) = apply {
            alias = communityNotesContributor.alias
            deletedNotesCount = communityNotesContributor.deletedNotesCount
            isApiContributor = communityNotesContributor.isApiContributor
            isTopWriter = communityNotesContributor.isTopWriter
            notesCount = communityNotesContributor.notesCount
            ratingsCount = communityNotesContributor.ratingsCount
            additionalProperties = communityNotesContributor.additionalProperties.toMutableMap()
        }

        /** The alias X gives the contributor. */
        fun alias(alias: String) = alias(JsonField.of(alias))

        /** Sets [Builder.alias] to an arbitrary JSON value. */
        fun alias(alias: JsonField<String>) = apply { this.alias = alias }

        /** Notes the contributor deleted. */
        fun deletedNotesCount(deletedNotesCount: Long) =
            deletedNotesCount(JsonField.of(deletedNotesCount))

        /** Sets [Builder.deletedNotesCount] to an arbitrary JSON value. */
        fun deletedNotesCount(deletedNotesCount: JsonField<Long>) = apply {
            this.deletedNotesCount = deletedNotesCount
        }

        /** Whether the contributor writes notes through X's API. */
        fun isApiContributor(isApiContributor: Boolean) =
            isApiContributor(JsonField.of(isApiContributor))

        /** Sets [Builder.isApiContributor] to an arbitrary JSON value. */
        fun isApiContributor(isApiContributor: JsonField<Boolean>) = apply {
            this.isApiContributor = isApiContributor
        }

        /** Whether X marks the contributor as a top writer. */
        fun isTopWriter(isTopWriter: Boolean) = isTopWriter(JsonField.of(isTopWriter))

        /** Sets [Builder.isTopWriter] to an arbitrary JSON value. */
        fun isTopWriter(isTopWriter: JsonField<Boolean>) = apply {
            this.isTopWriter = isTopWriter
        }

        /** How many notes a contributor wrote, by X's current rating. */
        fun notesCount(notesCount: NotesCount) = notesCount(JsonField.of(notesCount))

        /** Sets [Builder.notesCount] to an arbitrary JSON value. */
        fun notesCount(notesCount: JsonField<NotesCount>) = apply {
            this.notesCount = notesCount
        }

        /** How a contributor's ratings of other notes went. */
        fun ratingsCount(ratingsCount: RatingsCount) = ratingsCount(JsonField.of(ratingsCount))

        /** Sets [Builder.ratingsCount] to an arbitrary JSON value. */
        fun ratingsCount(ratingsCount: JsonField<RatingsCount>) = apply {
            this.ratingsCount = ratingsCount
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
         * Returns an immutable instance of [CommunityNotesContributor].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```kotlin
         * .alias()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): CommunityNotesContributor =
            CommunityNotesContributor(
                checkRequired("alias", alias),
                deletedNotesCount,
                isApiContributor,
                isTopWriter,
                notesCount,
                ratingsCount,
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
    fun validate(): CommunityNotesContributor = apply {
        if (validated) {
            return@apply
        }

        alias()
        deletedNotesCount()
        isApiContributor()
        isTopWriter()
        notesCount()?.validate()
        ratingsCount()?.validate()
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
        (if (alias.asKnown() == null) 0 else 1) +
            (if (deletedNotesCount.asKnown() == null) 0 else 1) +
            (if (isApiContributor.asKnown() == null) 0 else 1) +
            (if (isTopWriter.asKnown() == null) 0 else 1) +
            (notesCount.asKnown()?.validity() ?: 0) +
            (ratingsCount.asKnown()?.validity() ?: 0)

    /** How many notes a contributor wrote, by X's current rating. */
    class NotesCount
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val awaitingMoreRatings: JsonField<Long>,
        private val currentlyRatedHelpful: JsonField<Long>,
        private val currentlyRatedNotHelpful: JsonField<Long>,
        private val updatedAt: JsonField<OffsetDateTime>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("awaitingMoreRatings")
            @ExcludeMissing
            awaitingMoreRatings: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("currentlyRatedHelpful")
            @ExcludeMissing
            currentlyRatedHelpful: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("currentlyRatedNotHelpful")
            @ExcludeMissing
            currentlyRatedNotHelpful: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("updatedAt")
            @ExcludeMissing
            updatedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        ) : this(
            awaitingMoreRatings,
            currentlyRatedHelpful,
            currentlyRatedNotHelpful,
            updatedAt,
            mutableMapOf(),
        )

        /**
         * Notes that need more ratings.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun awaitingMoreRatings(): Long? = awaitingMoreRatings.getNullable("awaitingMoreRatings")

        /**
         * Notes X rates helpful now.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun currentlyRatedHelpful(): Long? =
            currentlyRatedHelpful.getNullable("currentlyRatedHelpful")

        /**
         * Notes X rates not helpful now.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun currentlyRatedNotHelpful(): Long? =
            currentlyRatedNotHelpful.getNullable("currentlyRatedNotHelpful")

        /**
         * When X last counted the notes.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun updatedAt(): OffsetDateTime? = updatedAt.getNullable("updatedAt")

        /**
         * Returns the raw JSON value of [awaitingMoreRatings].
         *
         * Unlike [awaitingMoreRatings], this method doesn't throw if the JSON field has an
         * unexpected type.
         */
        @JsonProperty("awaitingMoreRatings")
        @ExcludeMissing
        fun _awaitingMoreRatings(): JsonField<Long> = awaitingMoreRatings

        /**
         * Returns the raw JSON value of [currentlyRatedHelpful].
         *
         * Unlike [currentlyRatedHelpful], this method doesn't throw if the JSON field has an
         * unexpected type.
         */
        @JsonProperty("currentlyRatedHelpful")
        @ExcludeMissing
        fun _currentlyRatedHelpful(): JsonField<Long> = currentlyRatedHelpful

        /**
         * Returns the raw JSON value of [currentlyRatedNotHelpful].
         *
         * Unlike [currentlyRatedNotHelpful], this method doesn't throw if the JSON field has an
         * unexpected type.
         */
        @JsonProperty("currentlyRatedNotHelpful")
        @ExcludeMissing
        fun _currentlyRatedNotHelpful(): JsonField<Long> = currentlyRatedNotHelpful

        /**
         * Returns the raw JSON value of [updatedAt].
         *
         * Unlike [updatedAt], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("updatedAt")
        @ExcludeMissing
        fun _updatedAt(): JsonField<OffsetDateTime> = updatedAt

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

            /** Returns a mutable builder for constructing an instance of [NotesCount]. */
            fun builder() = Builder()
        }

        /** A builder for [NotesCount]. */
        class Builder internal constructor() {

            private var awaitingMoreRatings: JsonField<Long> = JsonMissing.of()
            private var currentlyRatedHelpful: JsonField<Long> = JsonMissing.of()
            private var currentlyRatedNotHelpful: JsonField<Long> = JsonMissing.of()
            private var updatedAt: JsonField<OffsetDateTime> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(notesCount: NotesCount) = apply {
                awaitingMoreRatings = notesCount.awaitingMoreRatings
                currentlyRatedHelpful = notesCount.currentlyRatedHelpful
                currentlyRatedNotHelpful = notesCount.currentlyRatedNotHelpful
                updatedAt = notesCount.updatedAt
                additionalProperties = notesCount.additionalProperties.toMutableMap()
            }

            /** Notes that need more ratings. */
            fun awaitingMoreRatings(awaitingMoreRatings: Long) =
                awaitingMoreRatings(JsonField.of(awaitingMoreRatings))

            /** Sets [Builder.awaitingMoreRatings] to an arbitrary JSON value. */
            fun awaitingMoreRatings(awaitingMoreRatings: JsonField<Long>) = apply {
                this.awaitingMoreRatings = awaitingMoreRatings
            }

            /** Notes X rates helpful now. */
            fun currentlyRatedHelpful(currentlyRatedHelpful: Long) =
                currentlyRatedHelpful(JsonField.of(currentlyRatedHelpful))

            /** Sets [Builder.currentlyRatedHelpful] to an arbitrary JSON value. */
            fun currentlyRatedHelpful(currentlyRatedHelpful: JsonField<Long>) = apply {
                this.currentlyRatedHelpful = currentlyRatedHelpful
            }

            /** Notes X rates not helpful now. */
            fun currentlyRatedNotHelpful(currentlyRatedNotHelpful: Long) =
                currentlyRatedNotHelpful(JsonField.of(currentlyRatedNotHelpful))

            /** Sets [Builder.currentlyRatedNotHelpful] to an arbitrary JSON value. */
            fun currentlyRatedNotHelpful(currentlyRatedNotHelpful: JsonField<Long>) = apply {
                this.currentlyRatedNotHelpful = currentlyRatedNotHelpful
            }

            /** When X last counted the notes. */
            fun updatedAt(updatedAt: OffsetDateTime) = updatedAt(JsonField.of(updatedAt))

            /** Sets [Builder.updatedAt] to an arbitrary JSON value. */
            fun updatedAt(updatedAt: JsonField<OffsetDateTime>) = apply {
                this.updatedAt = updatedAt
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

            fun removeAdditionalProperty(key: String) = apply {
                additionalProperties.remove(key)
            }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            /**
             * Returns an immutable instance of [NotesCount].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): NotesCount =
                NotesCount(
                    awaitingMoreRatings,
                    currentlyRatedHelpful,
                    currentlyRatedNotHelpful,
                    updatedAt,
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
        fun validate(): NotesCount = apply {
            if (validated) {
                return@apply
            }

            awaitingMoreRatings()
            currentlyRatedHelpful()
            currentlyRatedNotHelpful()
            updatedAt()
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
            (if (awaitingMoreRatings.asKnown() == null) 0 else 1) +
                (if (currentlyRatedHelpful.asKnown() == null) 0 else 1) +
                (if (currentlyRatedNotHelpful.asKnown() == null) 0 else 1) +
                (if (updatedAt.asKnown() == null) 0 else 1)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is NotesCount &&
                awaitingMoreRatings == other.awaitingMoreRatings &&
                currentlyRatedHelpful == other.currentlyRatedHelpful &&
                currentlyRatedNotHelpful == other.currentlyRatedNotHelpful &&
                updatedAt == other.updatedAt &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(
                awaitingMoreRatings,
                currentlyRatedHelpful,
                currentlyRatedNotHelpful,
                updatedAt,
                additionalProperties,
            )
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "NotesCount{awaitingMoreRatings=$awaitingMoreRatings, currentlyRatedHelpful=$currentlyRatedHelpful, currentlyRatedNotHelpful=$currentlyRatedNotHelpful, updatedAt=$updatedAt, additionalProperties=$additionalProperties}"
    }

    /** How a contributor's ratings of other notes went. */
    class RatingsCount
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val awaitingMoreRatings: JsonField<Long>,
        private val ratedAfterDecision: JsonField<Long>,
        private val successful: JsonField<CommunityNotesRatingOutcome>,
        private val unsuccessful: JsonField<CommunityNotesRatingOutcome>,
        private val updatedAt: JsonField<OffsetDateTime>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("awaitingMoreRatings")
            @ExcludeMissing
            awaitingMoreRatings: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("ratedAfterDecision")
            @ExcludeMissing
            ratedAfterDecision: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("successful")
            @ExcludeMissing
            successful: JsonField<CommunityNotesRatingOutcome> = JsonMissing.of(),
            @JsonProperty("unsuccessful")
            @ExcludeMissing
            unsuccessful: JsonField<CommunityNotesRatingOutcome> = JsonMissing.of(),
            @JsonProperty("updatedAt")
            @ExcludeMissing
            updatedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        ) : this(
            awaitingMoreRatings,
            ratedAfterDecision,
            successful,
            unsuccessful,
            updatedAt,
            mutableMapOf(),
        )

        /**
         * Ratings on notes that need more ratings.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun awaitingMoreRatings(): Long? = awaitingMoreRatings.getNullable("awaitingMoreRatings")

        /**
         * Ratings made after X rated the note.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun ratedAfterDecision(): Long? = ratedAfterDecision.getNullable("ratedAfterDecision")

        /**
         * Ratings that matched the rating X gave the note.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun successful(): CommunityNotesRatingOutcome? = successful.getNullable("successful")

        /**
         * Ratings that went against the rating X gave the note.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun unsuccessful(): CommunityNotesRatingOutcome? = unsuccessful.getNullable("unsuccessful")

        /**
         * When X last counted the ratings.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun updatedAt(): OffsetDateTime? = updatedAt.getNullable("updatedAt")

        /**
         * Returns the raw JSON value of [awaitingMoreRatings].
         *
         * Unlike [awaitingMoreRatings], this method doesn't throw if the JSON field has an
         * unexpected type.
         */
        @JsonProperty("awaitingMoreRatings")
        @ExcludeMissing
        fun _awaitingMoreRatings(): JsonField<Long> = awaitingMoreRatings

        /**
         * Returns the raw JSON value of [ratedAfterDecision].
         *
         * Unlike [ratedAfterDecision], this method doesn't throw if the JSON field has an
         * unexpected type.
         */
        @JsonProperty("ratedAfterDecision")
        @ExcludeMissing
        fun _ratedAfterDecision(): JsonField<Long> = ratedAfterDecision

        /**
         * Returns the raw JSON value of [successful].
         *
         * Unlike [successful], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("successful")
        @ExcludeMissing
        fun _successful(): JsonField<CommunityNotesRatingOutcome> = successful

        /**
         * Returns the raw JSON value of [unsuccessful].
         *
         * Unlike [unsuccessful], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("unsuccessful")
        @ExcludeMissing
        fun _unsuccessful(): JsonField<CommunityNotesRatingOutcome> = unsuccessful

        /**
         * Returns the raw JSON value of [updatedAt].
         *
         * Unlike [updatedAt], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("updatedAt")
        @ExcludeMissing
        fun _updatedAt(): JsonField<OffsetDateTime> = updatedAt

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

            /** Returns a mutable builder for constructing an instance of [RatingsCount]. */
            fun builder() = Builder()
        }

        /** A builder for [RatingsCount]. */
        class Builder internal constructor() {

            private var awaitingMoreRatings: JsonField<Long> = JsonMissing.of()
            private var ratedAfterDecision: JsonField<Long> = JsonMissing.of()
            private var successful: JsonField<CommunityNotesRatingOutcome> = JsonMissing.of()
            private var unsuccessful: JsonField<CommunityNotesRatingOutcome> = JsonMissing.of()
            private var updatedAt: JsonField<OffsetDateTime> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(ratingsCount: RatingsCount) = apply {
                awaitingMoreRatings = ratingsCount.awaitingMoreRatings
                ratedAfterDecision = ratingsCount.ratedAfterDecision
                successful = ratingsCount.successful
                unsuccessful = ratingsCount.unsuccessful
                updatedAt = ratingsCount.updatedAt
                additionalProperties = ratingsCount.additionalProperties.toMutableMap()
            }

            /** Ratings on notes that need more ratings. */
            fun awaitingMoreRatings(awaitingMoreRatings: Long) =
                awaitingMoreRatings(JsonField.of(awaitingMoreRatings))

            /** Sets [Builder.awaitingMoreRatings] to an arbitrary JSON value. */
            fun awaitingMoreRatings(awaitingMoreRatings: JsonField<Long>) = apply {
                this.awaitingMoreRatings = awaitingMoreRatings
            }

            /** Ratings made after X rated the note. */
            fun ratedAfterDecision(ratedAfterDecision: Long) =
                ratedAfterDecision(JsonField.of(ratedAfterDecision))

            /** Sets [Builder.ratedAfterDecision] to an arbitrary JSON value. */
            fun ratedAfterDecision(ratedAfterDecision: JsonField<Long>) = apply {
                this.ratedAfterDecision = ratedAfterDecision
            }

            /** Ratings that matched the rating X gave the note. */
            fun successful(successful: CommunityNotesRatingOutcome) =
                successful(JsonField.of(successful))

            /** Sets [Builder.successful] to an arbitrary JSON value. */
            fun successful(successful: JsonField<CommunityNotesRatingOutcome>) = apply {
                this.successful = successful
            }

            /** Ratings that went against the rating X gave the note. */
            fun unsuccessful(unsuccessful: CommunityNotesRatingOutcome) =
                unsuccessful(JsonField.of(unsuccessful))

            /** Sets [Builder.unsuccessful] to an arbitrary JSON value. */
            fun unsuccessful(unsuccessful: JsonField<CommunityNotesRatingOutcome>) = apply {
                this.unsuccessful = unsuccessful
            }

            /** When X last counted the ratings. */
            fun updatedAt(updatedAt: OffsetDateTime) = updatedAt(JsonField.of(updatedAt))

            /** Sets [Builder.updatedAt] to an arbitrary JSON value. */
            fun updatedAt(updatedAt: JsonField<OffsetDateTime>) = apply {
                this.updatedAt = updatedAt
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

            fun removeAdditionalProperty(key: String) = apply {
                additionalProperties.remove(key)
            }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            /**
             * Returns an immutable instance of [RatingsCount].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): RatingsCount =
                RatingsCount(
                    awaitingMoreRatings,
                    ratedAfterDecision,
                    successful,
                    unsuccessful,
                    updatedAt,
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
        fun validate(): RatingsCount = apply {
            if (validated) {
                return@apply
            }

            awaitingMoreRatings()
            ratedAfterDecision()
            successful()?.validate()
            unsuccessful()?.validate()
            updatedAt()
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
            (if (awaitingMoreRatings.asKnown() == null) 0 else 1) +
                (if (ratedAfterDecision.asKnown() == null) 0 else 1) +
                (successful.asKnown()?.validity() ?: 0) +
                (unsuccessful.asKnown()?.validity() ?: 0) +
                (if (updatedAt.asKnown() == null) 0 else 1)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is RatingsCount &&
                awaitingMoreRatings == other.awaitingMoreRatings &&
                ratedAfterDecision == other.ratedAfterDecision &&
                successful == other.successful &&
                unsuccessful == other.unsuccessful &&
                updatedAt == other.updatedAt &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(
                awaitingMoreRatings,
                ratedAfterDecision,
                successful,
                unsuccessful,
                updatedAt,
                additionalProperties,
            )
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "RatingsCount{awaitingMoreRatings=$awaitingMoreRatings, ratedAfterDecision=$ratedAfterDecision, successful=$successful, unsuccessful=$unsuccessful, updatedAt=$updatedAt, additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is CommunityNotesContributor &&
            alias == other.alias &&
            deletedNotesCount == other.deletedNotesCount &&
            isApiContributor == other.isApiContributor &&
            isTopWriter == other.isTopWriter &&
            notesCount == other.notesCount &&
            ratingsCount == other.ratingsCount &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            alias,
            deletedNotesCount,
            isApiContributor,
            isTopWriter,
            notesCount,
            ratingsCount,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "CommunityNotesContributor{alias=$alias, deletedNotesCount=$deletedNotesCount, isApiContributor=$isApiContributor, isTopWriter=$isTopWriter, notesCount=$notesCount, ratingsCount=$ratingsCount, additionalProperties=$additionalProperties}"
}
