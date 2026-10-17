package com.x_twitter_scraper.api.models.x.communitynotes

import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.x_twitter_scraper.api.core.ExcludeMissing
import com.x_twitter_scraper.api.core.JsonField
import com.x_twitter_scraper.api.core.JsonMissing
import com.x_twitter_scraper.api.core.JsonValue
import com.x_twitter_scraper.api.core.checkKnown
import com.x_twitter_scraper.api.core.checkRequired
import com.x_twitter_scraper.api.core.toImmutable
import com.x_twitter_scraper.api.errors.XTwitterScraperInvalidDataException
import com.x_twitter_scraper.api.models.SearchTweet
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects

/** 1 page of Community Notes. Follow next_cursor while the response reports more pages. */
class PaginatedCommunityNotes
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val hasNextPage: JsonField<Boolean>,
    private val nextCursor: JsonField<String>,
    private val notes: JsonField<List<Note>>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("has_next_page")
        @ExcludeMissing
        hasNextPage: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("next_cursor")
        @ExcludeMissing
        nextCursor: JsonField<String> = JsonMissing.of(),
        @JsonProperty("notes") @ExcludeMissing notes: JsonField<List<Note>> = JsonMissing.of(),
    ) : this(hasNextPage, nextCursor, notes, mutableMapOf())

    /**
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun hasNextPage(): Boolean = hasNextPage.getRequired("has_next_page")

    /**
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun nextCursor(): String = nextCursor.getRequired("next_cursor")

    /**
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun notes(): List<Note> = notes.getRequired("notes")

    /**
     * Returns the raw JSON value of [hasNextPage].
     *
     * Unlike [hasNextPage], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("has_next_page")
    @ExcludeMissing
    fun _hasNextPage(): JsonField<Boolean> = hasNextPage

    /**
     * Returns the raw JSON value of [nextCursor].
     *
     * Unlike [nextCursor], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("next_cursor") @ExcludeMissing fun _nextCursor(): JsonField<String> = nextCursor

    /**
     * Returns the raw JSON value of [notes].
     *
     * Unlike [notes], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("notes") @ExcludeMissing fun _notes(): JsonField<List<Note>> = notes

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
         * Returns a mutable builder for constructing an instance of [PaginatedCommunityNotes].
         *
         * The following fields are required:
         * ```kotlin
         * .hasNextPage()
         * .nextCursor()
         * .notes()
         * ```
         */
        fun builder() = Builder()
    }

    /** A builder for [PaginatedCommunityNotes]. */
    class Builder internal constructor() {

        private var hasNextPage: JsonField<Boolean>? = null
        private var nextCursor: JsonField<String>? = null
        private var notes: JsonField<MutableList<Note>>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        internal fun from(paginatedCommunityNotes: PaginatedCommunityNotes) = apply {
            hasNextPage = paginatedCommunityNotes.hasNextPage
            nextCursor = paginatedCommunityNotes.nextCursor
            notes = paginatedCommunityNotes.notes.map { it.toMutableList() }
            additionalProperties = paginatedCommunityNotes.additionalProperties.toMutableMap()
        }

        fun hasNextPage(hasNextPage: Boolean) = hasNextPage(JsonField.of(hasNextPage))

        /** Sets [Builder.hasNextPage] to an arbitrary JSON value. */
        fun hasNextPage(hasNextPage: JsonField<Boolean>) = apply { this.hasNextPage = hasNextPage }

        fun nextCursor(nextCursor: String) = nextCursor(JsonField.of(nextCursor))

        /** Sets [Builder.nextCursor] to an arbitrary JSON value. */
        fun nextCursor(nextCursor: JsonField<String>) = apply { this.nextCursor = nextCursor }

        fun notes(notes: List<Note>) = notes(JsonField.of(notes))

        /** Sets [Builder.notes] to an arbitrary JSON value. */
        fun notes(notes: JsonField<List<Note>>) = apply {
            this.notes = notes.map { it.toMutableList() }
        }

        /**
         * Adds a single [Note] to [notes].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addNote(note: Note) = apply {
            notes =
                (notes ?: JsonField.of(mutableListOf())).also {
                    checkKnown("notes", it).add(note)
                }
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
         * Returns an immutable instance of [PaginatedCommunityNotes].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```kotlin
         * .hasNextPage()
         * .nextCursor()
         * .notes()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): PaginatedCommunityNotes =
            PaginatedCommunityNotes(
                checkRequired("hasNextPage", hasNextPage),
                checkRequired("nextCursor", nextCursor),
                checkRequired("notes", notes).map { it.toImmutable() },
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
    fun validate(): PaginatedCommunityNotes = apply {
        if (validated) {
            return@apply
        }

        hasNextPage()
        nextCursor()
        notes().forEach { it.validate() }
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
        (if (hasNextPage.asKnown() == null) 0 else 1) +
            (if (nextCursor.asKnown() == null) 0 else 1) +
            (notes.asKnown()?.sumOf { it.validity() } ?: 0)

    /**
     * A Community Note as its writer's page lists it, with the post it is on. A deleted note has no
     * text, classification or post. A field X leaves out is left out.
     */
    class Note
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val id: JsonField<String>,
        private val abovePcrhThreshold: JsonField<Boolean>,
        private val appealStatus: JsonField<String>,
        private val classification: JsonField<String>,
        private val contributor: JsonField<CommunityNotesContributor>,
        private val createdAt: JsonField<OffsetDateTime>,
        private val decidedBy: JsonField<String>,
        private val deleted: JsonField<Boolean>,
        private val displayUrl: JsonField<String>,
        private val entities: JsonField<List<Entity>>,
        private val fullyVisibleModel: JsonField<Boolean>,
        private val helpfulTags: JsonField<List<String>>,
        private val impressionCount: JsonField<Long>,
        private val isApiAuthor: JsonField<Boolean>,
        private val isMediaNote: JsonField<Boolean>,
        private val isTranslatable: JsonField<Boolean>,
        private val isUrlNote: JsonField<Boolean>,
        private val language: JsonField<String>,
        private val mediaNoteMatchCount: JsonField<Long>,
        private val notHelpfulTags: JsonField<List<String>>,
        private val post: JsonField<SearchTweet>,
        private val postId: JsonField<String>,
        private val postUnavailable: JsonField<PostUnavailable>,
        private val ratingStatus: JsonField<String>,
        private val text: JsonField<String>,
        private val timeToHelpfulFromNoteMs: JsonField<Long>,
        private val timeToHelpfulFromPostMs: JsonField<Long>,
        private val tombstone: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
            @JsonProperty("abovePcrhThreshold")
            @ExcludeMissing
            abovePcrhThreshold: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("appealStatus")
            @ExcludeMissing
            appealStatus: JsonField<String> = JsonMissing.of(),
            @JsonProperty("classification")
            @ExcludeMissing
            classification: JsonField<String> = JsonMissing.of(),
            @JsonProperty("contributor")
            @ExcludeMissing
            contributor: JsonField<CommunityNotesContributor> = JsonMissing.of(),
            @JsonProperty("createdAt")
            @ExcludeMissing
            createdAt: JsonField<OffsetDateTime> = JsonMissing.of(),
            @JsonProperty("decidedBy")
            @ExcludeMissing
            decidedBy: JsonField<String> = JsonMissing.of(),
            @JsonProperty("deleted") @ExcludeMissing deleted: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("displayUrl")
            @ExcludeMissing
            displayUrl: JsonField<String> = JsonMissing.of(),
            @JsonProperty("entities")
            @ExcludeMissing
            entities: JsonField<List<Entity>> = JsonMissing.of(),
            @JsonProperty("fullyVisibleModel")
            @ExcludeMissing
            fullyVisibleModel: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("helpfulTags")
            @ExcludeMissing
            helpfulTags: JsonField<List<String>> = JsonMissing.of(),
            @JsonProperty("impressionCount")
            @ExcludeMissing
            impressionCount: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("isApiAuthor")
            @ExcludeMissing
            isApiAuthor: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("isMediaNote")
            @ExcludeMissing
            isMediaNote: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("isTranslatable")
            @ExcludeMissing
            isTranslatable: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("isUrlNote")
            @ExcludeMissing
            isUrlNote: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("language")
            @ExcludeMissing
            language: JsonField<String> = JsonMissing.of(),
            @JsonProperty("mediaNoteMatchCount")
            @ExcludeMissing
            mediaNoteMatchCount: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("notHelpfulTags")
            @ExcludeMissing
            notHelpfulTags: JsonField<List<String>> = JsonMissing.of(),
            @JsonProperty("post") @ExcludeMissing post: JsonField<SearchTweet> = JsonMissing.of(),
            @JsonProperty("postId") @ExcludeMissing postId: JsonField<String> = JsonMissing.of(),
            @JsonProperty("postUnavailable")
            @ExcludeMissing
            postUnavailable: JsonField<PostUnavailable> = JsonMissing.of(),
            @JsonProperty("ratingStatus")
            @ExcludeMissing
            ratingStatus: JsonField<String> = JsonMissing.of(),
            @JsonProperty("text") @ExcludeMissing text: JsonField<String> = JsonMissing.of(),
            @JsonProperty("timeToHelpfulFromNoteMs")
            @ExcludeMissing
            timeToHelpfulFromNoteMs: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("timeToHelpfulFromPostMs")
            @ExcludeMissing
            timeToHelpfulFromPostMs: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("tombstone")
            @ExcludeMissing
            tombstone: JsonField<String> = JsonMissing.of(),
        ) : this(
            id,
            abovePcrhThreshold,
            appealStatus,
            classification,
            contributor,
            createdAt,
            decidedBy,
            deleted,
            displayUrl,
            entities,
            fullyVisibleModel,
            helpfulTags,
            impressionCount,
            isApiAuthor,
            isMediaNote,
            isTranslatable,
            isUrlNote,
            language,
            mediaNoteMatchCount,
            notHelpfulTags,
            post,
            postId,
            postUnavailable,
            ratingStatus,
            text,
            timeToHelpfulFromNoteMs,
            timeToHelpfulFromPostMs,
            tombstone,
            mutableMapOf(),
        )

        /**
         * Note ID.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or
         *   is unexpectedly missing or null.
         */
        fun id(): String = id.getRequired("id")

        /**
         * Whether the note scores above X's bar for a possible helpful rating.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun abovePcrhThreshold(): Boolean? = abovePcrhThreshold.getNullable("abovePcrhThreshold")

        /**
         * Whether the rating is under appeal.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun appealStatus(): String? = appealStatus.getNullable("appealStatus")

        /**
         * What the writer says of the post, such as MisinformedOrPotentiallyMisleading or
         * NotMisleading.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun classification(): String? = classification.getNullable("classification")

        /**
         * The note's writer, with the counts X sends beside a note. GET
         * /x/community-notes/contributors/{alias} serves every count.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun contributor(): CommunityNotesContributor? = contributor.getNullable("contributor")

        /**
         * When the contributor wrote the note.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun createdAt(): OffsetDateTime? = createdAt.getNullable("createdAt")

        /**
         * The X scoring model that set the rating.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun decidedBy(): String? = decidedBy.getNullable("decidedBy")

        /**
         * Whether the writer deleted the note.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun deleted(): Boolean? = deleted.getNullable("deleted")

        /**
         * The link a link note is on, as X shows it.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun displayUrl(): String? = displayUrl.getNullable("displayUrl")

        /**
         * Links, tags & accounts inside the text, by character range.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun entities(): List<Entity>? = entities.getNullable("entities")

        /**
         * Whether X marks that scoring model as fully visible.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun fullyVisibleModel(): Boolean? = fullyVisibleModel.getNullable("fullyVisibleModel")

        /**
         * Why raters called the note helpful.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun helpfulTags(): List<String>? = helpfulTags.getNullable("helpfulTags")

        /**
         * Views of the note, when X shares them.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun impressionCount(): Long? = impressionCount.getNullable("impressionCount")

        /**
         * Whether the note came through X's API.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun isApiAuthor(): Boolean? = isApiAuthor.getNullable("isApiAuthor")

        /**
         * Whether the note is about the post's media. X can show such a note on other posts.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun isMediaNote(): Boolean? = isMediaNote.getNullable("isMediaNote")

        /**
         * Whether X offers a translation of the note.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun isTranslatable(): Boolean? = isTranslatable.getNullable("isTranslatable")

        /**
         * Whether the note is on a link.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun isUrlNote(): Boolean? = isUrlNote.getNullable("isUrlNote")

        /**
         * Language code of the note.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun language(): String? = language.getNullable("language")

        /**
         * Other posts X matched a media note to.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun mediaNoteMatchCount(): Long? = mediaNoteMatchCount.getNullable("mediaNoteMatchCount")

        /**
         * Why raters called the note not helpful.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun notHelpfulTags(): List<String>? = notHelpfulTags.getNullable("notHelpfulTags")

        /**
         * The post the note is on. Null when X no longer shows it.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun post(): SearchTweet? = post.getNullable("post")

        /**
         * ID of the post the note is on.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun postId(): String? = postId.getNullable("postId")

        /**
         * Why X shows no post, when X says.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun postUnavailable(): PostUnavailable? = postUnavailable.getNullable("postUnavailable")

        /**
         * X's current rating, such as NeedsMoreRatings, CurrentlyRatedHelpful or
         * CurrentlyRatedNotHelpful. x.com shows only helpful notes under posts.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun ratingStatus(): String? = ratingStatus.getNullable("ratingStatus")

        /**
         * What the note says.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun text(): String? = text.getNullable("text")

        /**
         * Milliseconds from the note's creation to its helpful rating.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun timeToHelpfulFromNoteMs(): Long? =
            timeToHelpfulFromNoteMs.getNullable("timeToHelpfulFromNoteMs")

        /**
         * Milliseconds from the post's creation to the note's helpful rating.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun timeToHelpfulFromPostMs(): Long? =
            timeToHelpfulFromPostMs.getNullable("timeToHelpfulFromPostMs")

        /**
         * X's notice in place of a deleted note.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun tombstone(): String? = tombstone.getNullable("tombstone")

        /**
         * Returns the raw JSON value of [id].
         *
         * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

        /**
         * Returns the raw JSON value of [abovePcrhThreshold].
         *
         * Unlike [abovePcrhThreshold], this method doesn't throw if the JSON field has an
         * unexpected type.
         */
        @JsonProperty("abovePcrhThreshold")
        @ExcludeMissing
        fun _abovePcrhThreshold(): JsonField<Boolean> = abovePcrhThreshold

        /**
         * Returns the raw JSON value of [appealStatus].
         *
         * Unlike [appealStatus], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("appealStatus")
        @ExcludeMissing
        fun _appealStatus(): JsonField<String> = appealStatus

        /**
         * Returns the raw JSON value of [classification].
         *
         * Unlike [classification], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("classification")
        @ExcludeMissing
        fun _classification(): JsonField<String> = classification

        /**
         * Returns the raw JSON value of [contributor].
         *
         * Unlike [contributor], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("contributor")
        @ExcludeMissing
        fun _contributor(): JsonField<CommunityNotesContributor> = contributor

        /**
         * Returns the raw JSON value of [createdAt].
         *
         * Unlike [createdAt], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("createdAt")
        @ExcludeMissing
        fun _createdAt(): JsonField<OffsetDateTime> = createdAt

        /**
         * Returns the raw JSON value of [decidedBy].
         *
         * Unlike [decidedBy], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("decidedBy") @ExcludeMissing fun _decidedBy(): JsonField<String> = decidedBy

        /**
         * Returns the raw JSON value of [deleted].
         *
         * Unlike [deleted], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("deleted") @ExcludeMissing fun _deleted(): JsonField<Boolean> = deleted

        /**
         * Returns the raw JSON value of [displayUrl].
         *
         * Unlike [displayUrl], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("displayUrl")
        @ExcludeMissing
        fun _displayUrl(): JsonField<String> = displayUrl

        /**
         * Returns the raw JSON value of [entities].
         *
         * Unlike [entities], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("entities")
        @ExcludeMissing
        fun _entities(): JsonField<List<Entity>> = entities

        /**
         * Returns the raw JSON value of [fullyVisibleModel].
         *
         * Unlike [fullyVisibleModel], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("fullyVisibleModel")
        @ExcludeMissing
        fun _fullyVisibleModel(): JsonField<Boolean> = fullyVisibleModel

        /**
         * Returns the raw JSON value of [helpfulTags].
         *
         * Unlike [helpfulTags], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("helpfulTags")
        @ExcludeMissing
        fun _helpfulTags(): JsonField<List<String>> = helpfulTags

        /**
         * Returns the raw JSON value of [impressionCount].
         *
         * Unlike [impressionCount], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("impressionCount")
        @ExcludeMissing
        fun _impressionCount(): JsonField<Long> = impressionCount

        /**
         * Returns the raw JSON value of [isApiAuthor].
         *
         * Unlike [isApiAuthor], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("isApiAuthor")
        @ExcludeMissing
        fun _isApiAuthor(): JsonField<Boolean> = isApiAuthor

        /**
         * Returns the raw JSON value of [isMediaNote].
         *
         * Unlike [isMediaNote], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("isMediaNote")
        @ExcludeMissing
        fun _isMediaNote(): JsonField<Boolean> = isMediaNote

        /**
         * Returns the raw JSON value of [isTranslatable].
         *
         * Unlike [isTranslatable], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("isTranslatable")
        @ExcludeMissing
        fun _isTranslatable(): JsonField<Boolean> = isTranslatable

        /**
         * Returns the raw JSON value of [isUrlNote].
         *
         * Unlike [isUrlNote], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("isUrlNote") @ExcludeMissing fun _isUrlNote(): JsonField<Boolean> = isUrlNote

        /**
         * Returns the raw JSON value of [language].
         *
         * Unlike [language], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("language") @ExcludeMissing fun _language(): JsonField<String> = language

        /**
         * Returns the raw JSON value of [mediaNoteMatchCount].
         *
         * Unlike [mediaNoteMatchCount], this method doesn't throw if the JSON field has an
         * unexpected type.
         */
        @JsonProperty("mediaNoteMatchCount")
        @ExcludeMissing
        fun _mediaNoteMatchCount(): JsonField<Long> = mediaNoteMatchCount

        /**
         * Returns the raw JSON value of [notHelpfulTags].
         *
         * Unlike [notHelpfulTags], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("notHelpfulTags")
        @ExcludeMissing
        fun _notHelpfulTags(): JsonField<List<String>> = notHelpfulTags

        /**
         * Returns the raw JSON value of [post].
         *
         * Unlike [post], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("post") @ExcludeMissing fun _post(): JsonField<SearchTweet> = post

        /**
         * Returns the raw JSON value of [postId].
         *
         * Unlike [postId], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("postId") @ExcludeMissing fun _postId(): JsonField<String> = postId

        /**
         * Returns the raw JSON value of [postUnavailable].
         *
         * Unlike [postUnavailable], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("postUnavailable")
        @ExcludeMissing
        fun _postUnavailable(): JsonField<PostUnavailable> = postUnavailable

        /**
         * Returns the raw JSON value of [ratingStatus].
         *
         * Unlike [ratingStatus], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("ratingStatus")
        @ExcludeMissing
        fun _ratingStatus(): JsonField<String> = ratingStatus

        /**
         * Returns the raw JSON value of [text].
         *
         * Unlike [text], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("text") @ExcludeMissing fun _text(): JsonField<String> = text

        /**
         * Returns the raw JSON value of [timeToHelpfulFromNoteMs].
         *
         * Unlike [timeToHelpfulFromNoteMs], this method doesn't throw if the JSON field has an
         * unexpected type.
         */
        @JsonProperty("timeToHelpfulFromNoteMs")
        @ExcludeMissing
        fun _timeToHelpfulFromNoteMs(): JsonField<Long> = timeToHelpfulFromNoteMs

        /**
         * Returns the raw JSON value of [timeToHelpfulFromPostMs].
         *
         * Unlike [timeToHelpfulFromPostMs], this method doesn't throw if the JSON field has an
         * unexpected type.
         */
        @JsonProperty("timeToHelpfulFromPostMs")
        @ExcludeMissing
        fun _timeToHelpfulFromPostMs(): JsonField<Long> = timeToHelpfulFromPostMs

        /**
         * Returns the raw JSON value of [tombstone].
         *
         * Unlike [tombstone], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("tombstone") @ExcludeMissing fun _tombstone(): JsonField<String> = tombstone

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
             * Returns a mutable builder for constructing an instance of [Note].
             *
             * The following fields are required:
             * ```kotlin
             * .id()
             * ```
             */
            fun builder() = Builder()
        }

        /** A builder for [Note]. */
        class Builder internal constructor() {

            private var id: JsonField<String>? = null
            private var abovePcrhThreshold: JsonField<Boolean> = JsonMissing.of()
            private var appealStatus: JsonField<String> = JsonMissing.of()
            private var classification: JsonField<String> = JsonMissing.of()
            private var contributor: JsonField<CommunityNotesContributor> = JsonMissing.of()
            private var createdAt: JsonField<OffsetDateTime> = JsonMissing.of()
            private var decidedBy: JsonField<String> = JsonMissing.of()
            private var deleted: JsonField<Boolean> = JsonMissing.of()
            private var displayUrl: JsonField<String> = JsonMissing.of()
            private var entities: JsonField<MutableList<Entity>>? = null
            private var fullyVisibleModel: JsonField<Boolean> = JsonMissing.of()
            private var helpfulTags: JsonField<MutableList<String>>? = null
            private var impressionCount: JsonField<Long> = JsonMissing.of()
            private var isApiAuthor: JsonField<Boolean> = JsonMissing.of()
            private var isMediaNote: JsonField<Boolean> = JsonMissing.of()
            private var isTranslatable: JsonField<Boolean> = JsonMissing.of()
            private var isUrlNote: JsonField<Boolean> = JsonMissing.of()
            private var language: JsonField<String> = JsonMissing.of()
            private var mediaNoteMatchCount: JsonField<Long> = JsonMissing.of()
            private var notHelpfulTags: JsonField<MutableList<String>>? = null
            private var post: JsonField<SearchTweet> = JsonMissing.of()
            private var postId: JsonField<String> = JsonMissing.of()
            private var postUnavailable: JsonField<PostUnavailable> = JsonMissing.of()
            private var ratingStatus: JsonField<String> = JsonMissing.of()
            private var text: JsonField<String> = JsonMissing.of()
            private var timeToHelpfulFromNoteMs: JsonField<Long> = JsonMissing.of()
            private var timeToHelpfulFromPostMs: JsonField<Long> = JsonMissing.of()
            private var tombstone: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(note: Note) = apply {
                id = note.id
                abovePcrhThreshold = note.abovePcrhThreshold
                appealStatus = note.appealStatus
                classification = note.classification
                contributor = note.contributor
                createdAt = note.createdAt
                decidedBy = note.decidedBy
                deleted = note.deleted
                displayUrl = note.displayUrl
                entities = note.entities.map { it.toMutableList() }
                fullyVisibleModel = note.fullyVisibleModel
                helpfulTags = note.helpfulTags.map { it.toMutableList() }
                impressionCount = note.impressionCount
                isApiAuthor = note.isApiAuthor
                isMediaNote = note.isMediaNote
                isTranslatable = note.isTranslatable
                isUrlNote = note.isUrlNote
                language = note.language
                mediaNoteMatchCount = note.mediaNoteMatchCount
                notHelpfulTags = note.notHelpfulTags.map { it.toMutableList() }
                post = note.post
                postId = note.postId
                postUnavailable = note.postUnavailable
                ratingStatus = note.ratingStatus
                text = note.text
                timeToHelpfulFromNoteMs = note.timeToHelpfulFromNoteMs
                timeToHelpfulFromPostMs = note.timeToHelpfulFromPostMs
                tombstone = note.tombstone
                additionalProperties = note.additionalProperties.toMutableMap()
            }

            /** Note ID. */
            fun id(id: String) = id(JsonField.of(id))

            /** Sets [Builder.id] to an arbitrary JSON value. */
            fun id(id: JsonField<String>) = apply { this.id = id }

            /** Whether the note scores above X's bar for a possible helpful rating. */
            fun abovePcrhThreshold(abovePcrhThreshold: Boolean) =
                abovePcrhThreshold(JsonField.of(abovePcrhThreshold))

            /** Sets [Builder.abovePcrhThreshold] to an arbitrary JSON value. */
            fun abovePcrhThreshold(abovePcrhThreshold: JsonField<Boolean>) = apply {
                this.abovePcrhThreshold = abovePcrhThreshold
            }

            /** Whether the rating is under appeal. */
            fun appealStatus(appealStatus: String) = appealStatus(JsonField.of(appealStatus))

            /** Sets [Builder.appealStatus] to an arbitrary JSON value. */
            fun appealStatus(appealStatus: JsonField<String>) = apply {
                this.appealStatus = appealStatus
            }

            /**
             * What the writer says of the post, such as MisinformedOrPotentiallyMisleading or
             * NotMisleading.
             */
            fun classification(classification: String) =
                classification(JsonField.of(classification))

            /** Sets [Builder.classification] to an arbitrary JSON value. */
            fun classification(classification: JsonField<String>) = apply {
                this.classification = classification
            }

            /**
             * The note's writer, with the counts X sends beside a note. GET
             * /x/community-notes/contributors/{alias} serves every count.
             */
            fun contributor(contributor: CommunityNotesContributor) =
                contributor(JsonField.of(contributor))

            /** Sets [Builder.contributor] to an arbitrary JSON value. */
            fun contributor(contributor: JsonField<CommunityNotesContributor>) = apply {
                this.contributor = contributor
            }

            /** When the contributor wrote the note. */
            fun createdAt(createdAt: OffsetDateTime) = createdAt(JsonField.of(createdAt))

            /** Sets [Builder.createdAt] to an arbitrary JSON value. */
            fun createdAt(createdAt: JsonField<OffsetDateTime>) = apply {
                this.createdAt = createdAt
            }

            /** The X scoring model that set the rating. */
            fun decidedBy(decidedBy: String) = decidedBy(JsonField.of(decidedBy))

            /** Sets [Builder.decidedBy] to an arbitrary JSON value. */
            fun decidedBy(decidedBy: JsonField<String>) = apply { this.decidedBy = decidedBy }

            /** Whether the writer deleted the note. */
            fun deleted(deleted: Boolean) = deleted(JsonField.of(deleted))

            /** Sets [Builder.deleted] to an arbitrary JSON value. */
            fun deleted(deleted: JsonField<Boolean>) = apply { this.deleted = deleted }

            /** The link a link note is on, as X shows it. */
            fun displayUrl(displayUrl: String) = displayUrl(JsonField.of(displayUrl))

            /** Sets [Builder.displayUrl] to an arbitrary JSON value. */
            fun displayUrl(displayUrl: JsonField<String>) = apply { this.displayUrl = displayUrl }

            /** Links, tags & accounts inside the text, by character range. */
            fun entities(entities: List<Entity>) = entities(JsonField.of(entities))

            /** Sets [Builder.entities] to an arbitrary JSON value. */
            fun entities(entities: JsonField<List<Entity>>) = apply {
                this.entities = entities.map { it.toMutableList() }
            }

            /**
             * Adds a single [Entity] to [entities].
             *
             * @throws IllegalStateException if the field was previously set to a non-list.
             */
            fun addEntity(entity: Entity) = apply {
                entities =
                    (entities ?: JsonField.of(mutableListOf())).also {
                        checkKnown("entities", it).add(entity)
                    }
            }

            /** Whether X marks that scoring model as fully visible. */
            fun fullyVisibleModel(fullyVisibleModel: Boolean) =
                fullyVisibleModel(JsonField.of(fullyVisibleModel))

            /** Sets [Builder.fullyVisibleModel] to an arbitrary JSON value. */
            fun fullyVisibleModel(fullyVisibleModel: JsonField<Boolean>) = apply {
                this.fullyVisibleModel = fullyVisibleModel
            }

            /** Why raters called the note helpful. */
            fun helpfulTags(helpfulTags: List<String>) = helpfulTags(JsonField.of(helpfulTags))

            /** Sets [Builder.helpfulTags] to an arbitrary JSON value. */
            fun helpfulTags(helpfulTags: JsonField<List<String>>) = apply {
                this.helpfulTags = helpfulTags.map { it.toMutableList() }
            }

            /**
             * Adds a single [String] to [helpfulTags].
             *
             * @throws IllegalStateException if the field was previously set to a non-list.
             */
            fun addHelpfulTag(helpfulTag: String) = apply {
                helpfulTags =
                    (helpfulTags ?: JsonField.of(mutableListOf())).also {
                        checkKnown("helpfulTags", it).add(helpfulTag)
                    }
            }

            /** Views of the note, when X shares them. */
            fun impressionCount(impressionCount: Long) =
                impressionCount(JsonField.of(impressionCount))

            /** Sets [Builder.impressionCount] to an arbitrary JSON value. */
            fun impressionCount(impressionCount: JsonField<Long>) = apply {
                this.impressionCount = impressionCount
            }

            /** Whether the note came through X's API. */
            fun isApiAuthor(isApiAuthor: Boolean) = isApiAuthor(JsonField.of(isApiAuthor))

            /** Sets [Builder.isApiAuthor] to an arbitrary JSON value. */
            fun isApiAuthor(isApiAuthor: JsonField<Boolean>) = apply {
                this.isApiAuthor = isApiAuthor
            }

            /**
             * Whether the note is about the post's media. X can show such a note on other posts.
             */
            fun isMediaNote(isMediaNote: Boolean) = isMediaNote(JsonField.of(isMediaNote))

            /** Sets [Builder.isMediaNote] to an arbitrary JSON value. */
            fun isMediaNote(isMediaNote: JsonField<Boolean>) = apply {
                this.isMediaNote = isMediaNote
            }

            /** Whether X offers a translation of the note. */
            fun isTranslatable(isTranslatable: Boolean) =
                isTranslatable(JsonField.of(isTranslatable))

            /** Sets [Builder.isTranslatable] to an arbitrary JSON value. */
            fun isTranslatable(isTranslatable: JsonField<Boolean>) = apply {
                this.isTranslatable = isTranslatable
            }

            /** Whether the note is on a link. */
            fun isUrlNote(isUrlNote: Boolean) = isUrlNote(JsonField.of(isUrlNote))

            /** Sets [Builder.isUrlNote] to an arbitrary JSON value. */
            fun isUrlNote(isUrlNote: JsonField<Boolean>) = apply { this.isUrlNote = isUrlNote }

            /** Language code of the note. */
            fun language(language: String) = language(JsonField.of(language))

            /** Sets [Builder.language] to an arbitrary JSON value. */
            fun language(language: JsonField<String>) = apply { this.language = language }

            /** Other posts X matched a media note to. */
            fun mediaNoteMatchCount(mediaNoteMatchCount: Long) =
                mediaNoteMatchCount(JsonField.of(mediaNoteMatchCount))

            /** Sets [Builder.mediaNoteMatchCount] to an arbitrary JSON value. */
            fun mediaNoteMatchCount(mediaNoteMatchCount: JsonField<Long>) = apply {
                this.mediaNoteMatchCount = mediaNoteMatchCount
            }

            /** Why raters called the note not helpful. */
            fun notHelpfulTags(notHelpfulTags: List<String>) =
                notHelpfulTags(JsonField.of(notHelpfulTags))

            /** Sets [Builder.notHelpfulTags] to an arbitrary JSON value. */
            fun notHelpfulTags(notHelpfulTags: JsonField<List<String>>) = apply {
                this.notHelpfulTags = notHelpfulTags.map { it.toMutableList() }
            }

            /**
             * Adds a single [String] to [notHelpfulTags].
             *
             * @throws IllegalStateException if the field was previously set to a non-list.
             */
            fun addNotHelpfulTag(notHelpfulTag: String) = apply {
                notHelpfulTags =
                    (notHelpfulTags ?: JsonField.of(mutableListOf())).also {
                        checkKnown("notHelpfulTags", it).add(notHelpfulTag)
                    }
            }

            /** The post the note is on. Null when X no longer shows it. */
            fun post(post: SearchTweet?) = post(JsonField.ofNullable(post))

            /** Sets [Builder.post] to an arbitrary JSON value. */
            fun post(post: JsonField<SearchTweet>) = apply { this.post = post }

            /** ID of the post the note is on. */
            fun postId(postId: String) = postId(JsonField.of(postId))

            /** Sets [Builder.postId] to an arbitrary JSON value. */
            fun postId(postId: JsonField<String>) = apply { this.postId = postId }

            /** Why X shows no post, when X says. */
            fun postUnavailable(postUnavailable: PostUnavailable) =
                postUnavailable(JsonField.of(postUnavailable))

            /** Sets [Builder.postUnavailable] to an arbitrary JSON value. */
            fun postUnavailable(postUnavailable: JsonField<PostUnavailable>) = apply {
                this.postUnavailable = postUnavailable
            }

            /**
             * X's current rating, such as NeedsMoreRatings, CurrentlyRatedHelpful or
             * CurrentlyRatedNotHelpful. x.com shows only helpful notes under posts.
             */
            fun ratingStatus(ratingStatus: String) = ratingStatus(JsonField.of(ratingStatus))

            /** Sets [Builder.ratingStatus] to an arbitrary JSON value. */
            fun ratingStatus(ratingStatus: JsonField<String>) = apply {
                this.ratingStatus = ratingStatus
            }

            /** What the note says. */
            fun text(text: String) = text(JsonField.of(text))

            /** Sets [Builder.text] to an arbitrary JSON value. */
            fun text(text: JsonField<String>) = apply { this.text = text }

            /** Milliseconds from the note's creation to its helpful rating. */
            fun timeToHelpfulFromNoteMs(timeToHelpfulFromNoteMs: Long) =
                timeToHelpfulFromNoteMs(JsonField.of(timeToHelpfulFromNoteMs))

            /** Sets [Builder.timeToHelpfulFromNoteMs] to an arbitrary JSON value. */
            fun timeToHelpfulFromNoteMs(timeToHelpfulFromNoteMs: JsonField<Long>) = apply {
                this.timeToHelpfulFromNoteMs = timeToHelpfulFromNoteMs
            }

            /** Milliseconds from the post's creation to the note's helpful rating. */
            fun timeToHelpfulFromPostMs(timeToHelpfulFromPostMs: Long) =
                timeToHelpfulFromPostMs(JsonField.of(timeToHelpfulFromPostMs))

            /** Sets [Builder.timeToHelpfulFromPostMs] to an arbitrary JSON value. */
            fun timeToHelpfulFromPostMs(timeToHelpfulFromPostMs: JsonField<Long>) = apply {
                this.timeToHelpfulFromPostMs = timeToHelpfulFromPostMs
            }

            /** X's notice in place of a deleted note. */
            fun tombstone(tombstone: String) = tombstone(JsonField.of(tombstone))

            /** Sets [Builder.tombstone] to an arbitrary JSON value. */
            fun tombstone(tombstone: JsonField<String>) = apply { this.tombstone = tombstone }

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
             * Returns an immutable instance of [Note].
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
            fun build(): Note =
                Note(
                    checkRequired("id", id),
                    abovePcrhThreshold,
                    appealStatus,
                    classification,
                    contributor,
                    createdAt,
                    decidedBy,
                    deleted,
                    displayUrl,
                    (entities ?: JsonMissing.of()).map { it.toImmutable() },
                    fullyVisibleModel,
                    (helpfulTags ?: JsonMissing.of()).map { it.toImmutable() },
                    impressionCount,
                    isApiAuthor,
                    isMediaNote,
                    isTranslatable,
                    isUrlNote,
                    language,
                    mediaNoteMatchCount,
                    (notHelpfulTags ?: JsonMissing.of()).map { it.toImmutable() },
                    post,
                    postId,
                    postUnavailable,
                    ratingStatus,
                    text,
                    timeToHelpfulFromNoteMs,
                    timeToHelpfulFromPostMs,
                    tombstone,
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
        fun validate(): Note = apply {
            if (validated) {
                return@apply
            }

            id()
            abovePcrhThreshold()
            appealStatus()
            classification()
            contributor()?.validate()
            createdAt()
            decidedBy()
            deleted()
            displayUrl()
            entities()?.forEach { it.validate() }
            fullyVisibleModel()
            helpfulTags()
            impressionCount()
            isApiAuthor()
            isMediaNote()
            isTranslatable()
            isUrlNote()
            language()
            mediaNoteMatchCount()
            notHelpfulTags()
            post()?.validate()
            postId()
            postUnavailable()?.validate()
            ratingStatus()
            text()
            timeToHelpfulFromNoteMs()
            timeToHelpfulFromPostMs()
            tombstone()
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
                (if (abovePcrhThreshold.asKnown() == null) 0 else 1) +
                (if (appealStatus.asKnown() == null) 0 else 1) +
                (if (classification.asKnown() == null) 0 else 1) +
                (contributor.asKnown()?.validity() ?: 0) +
                (if (createdAt.asKnown() == null) 0 else 1) +
                (if (decidedBy.asKnown() == null) 0 else 1) +
                (if (deleted.asKnown() == null) 0 else 1) +
                (if (displayUrl.asKnown() == null) 0 else 1) +
                (entities.asKnown()?.sumOf { it.validity() } ?: 0) +
                (if (fullyVisibleModel.asKnown() == null) 0 else 1) +
                (helpfulTags.asKnown()?.size ?: 0) +
                (if (impressionCount.asKnown() == null) 0 else 1) +
                (if (isApiAuthor.asKnown() == null) 0 else 1) +
                (if (isMediaNote.asKnown() == null) 0 else 1) +
                (if (isTranslatable.asKnown() == null) 0 else 1) +
                (if (isUrlNote.asKnown() == null) 0 else 1) +
                (if (language.asKnown() == null) 0 else 1) +
                (if (mediaNoteMatchCount.asKnown() == null) 0 else 1) +
                (notHelpfulTags.asKnown()?.size ?: 0) +
                (post.asKnown()?.validity() ?: 0) +
                (if (postId.asKnown() == null) 0 else 1) +
                (postUnavailable.asKnown()?.validity() ?: 0) +
                (if (ratingStatus.asKnown() == null) 0 else 1) +
                (if (text.asKnown() == null) 0 else 1) +
                (if (timeToHelpfulFromNoteMs.asKnown() == null) 0 else 1) +
                (if (timeToHelpfulFromPostMs.asKnown() == null) 0 else 1) +
                (if (tombstone.asKnown() == null) 0 else 1)

        /** A link, tag or account inside a note's text. */
        class Entity
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val fromIndex: JsonField<Long>,
            private val ref: JsonField<Ref>,
            private val toIndex: JsonField<Long>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("fromIndex")
                @ExcludeMissing
                fromIndex: JsonField<Long> = JsonMissing.of(),
                @JsonProperty("ref") @ExcludeMissing ref: JsonField<Ref> = JsonMissing.of(),
                @JsonProperty("toIndex")
                @ExcludeMissing
                toIndex: JsonField<Long> = JsonMissing.of(),
            ) : this(fromIndex, ref, toIndex, mutableMapOf())

            /**
             * Where it starts in the text.
             *
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun fromIndex(): Long? = fromIndex.getNullable("fromIndex")

            /**
             * What it points at.
             *
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun ref(): Ref? = ref.getNullable("ref")

            /**
             * Where it ends in the text.
             *
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun toIndex(): Long? = toIndex.getNullable("toIndex")

            /**
             * Returns the raw JSON value of [fromIndex].
             *
             * Unlike [fromIndex], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("fromIndex") @ExcludeMissing fun _fromIndex(): JsonField<Long> = fromIndex

            /**
             * Returns the raw JSON value of [ref].
             *
             * Unlike [ref], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("ref") @ExcludeMissing fun _ref(): JsonField<Ref> = ref

            /**
             * Returns the raw JSON value of [toIndex].
             *
             * Unlike [toIndex], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("toIndex") @ExcludeMissing fun _toIndex(): JsonField<Long> = toIndex

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

                /** Returns a mutable builder for constructing an instance of [Entity]. */
                fun builder() = Builder()
            }

            /** A builder for [Entity]. */
            class Builder internal constructor() {

                private var fromIndex: JsonField<Long> = JsonMissing.of()
                private var ref: JsonField<Ref> = JsonMissing.of()
                private var toIndex: JsonField<Long> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                internal fun from(entity: Entity) = apply {
                    fromIndex = entity.fromIndex
                    ref = entity.ref
                    toIndex = entity.toIndex
                    additionalProperties = entity.additionalProperties.toMutableMap()
                }

                /** Where it starts in the text. */
                fun fromIndex(fromIndex: Long) = fromIndex(JsonField.of(fromIndex))

                /** Sets [Builder.fromIndex] to an arbitrary JSON value. */
                fun fromIndex(fromIndex: JsonField<Long>) = apply { this.fromIndex = fromIndex }

                /** What it points at. */
                fun ref(ref: Ref) = ref(JsonField.of(ref))

                /** Sets [Builder.ref] to an arbitrary JSON value. */
                fun ref(ref: JsonField<Ref>) = apply { this.ref = ref }

                /** Where it ends in the text. */
                fun toIndex(toIndex: Long) = toIndex(JsonField.of(toIndex))

                /** Sets [Builder.toIndex] to an arbitrary JSON value. */
                fun toIndex(toIndex: JsonField<Long>) = apply { this.toIndex = toIndex }

                fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                    this.additionalProperties.clear()
                    putAllAdditionalProperties(additionalProperties)
                }

                fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                    additionalProperties.put(key, value)
                }

                fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) =
                    apply {
                        this.additionalProperties.putAll(additionalProperties)
                    }

                fun removeAdditionalProperty(key: String) = apply {
                    additionalProperties.remove(key)
                }

                fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                    keys.forEach(::removeAdditionalProperty)
                }

                /**
                 * Returns an immutable instance of [Entity].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 */
                fun build(): Entity =
                    Entity(fromIndex, ref, toIndex, additionalProperties.toMutableMap())
            }

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
            fun validate(): Entity = apply {
                if (validated) {
                    return@apply
                }

                fromIndex()
                ref()?.validate()
                toIndex()
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
                (if (fromIndex.asKnown() == null) 0 else 1) +
                    (ref.asKnown()?.validity() ?: 0) +
                    (if (toIndex.asKnown() == null) 0 else 1)

            /** What it points at. */
            class Ref
            @JsonCreator(mode = JsonCreator.Mode.DISABLED)
            private constructor(
                private val screenName: JsonField<String>,
                private val text: JsonField<String>,
                private val type: JsonField<String>,
                private val url: JsonField<String>,
                private val urlType: JsonField<String>,
                private val additionalProperties: MutableMap<String, JsonValue>,
            ) {

                @JsonCreator
                private constructor(
                    @JsonProperty("screenName")
                    @ExcludeMissing
                    screenName: JsonField<String> = JsonMissing.of(),
                    @JsonProperty("text")
                    @ExcludeMissing
                    text: JsonField<String> = JsonMissing.of(),
                    @JsonProperty("type")
                    @ExcludeMissing
                    type: JsonField<String> = JsonMissing.of(),
                    @JsonProperty("url") @ExcludeMissing url: JsonField<String> = JsonMissing.of(),
                    @JsonProperty("urlType")
                    @ExcludeMissing
                    urlType: JsonField<String> = JsonMissing.of(),
                ) : this(screenName, text, type, url, urlType, mutableMapOf())

                /**
                 * A mentioned account's username.
                 *
                 * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected
                 *   type.
                 */
                fun screenName(): String? = screenName.getNullable("screenName")

                /**
                 * A hashtag's or cashtag's text.
                 *
                 * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected
                 *   type.
                 */
                fun text(): String? = text.getNullable("text")

                /**
                 * X's kind, such as TimelineUrl.
                 *
                 * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected
                 *   type.
                 */
                fun type(): String? = type.getNullable("type")

                /**
                 * The link.
                 *
                 * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected
                 *   type.
                 */
                fun url(): String? = url.getNullable("url")

                /**
                 * How X opens the link, such as ExternalUrl.
                 *
                 * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected
                 *   type.
                 */
                fun urlType(): String? = urlType.getNullable("urlType")

                /**
                 * Returns the raw JSON value of [screenName].
                 *
                 * Unlike [screenName], this method doesn't throw if the JSON field has an
                 * unexpected type.
                 */
                @JsonProperty("screenName")
                @ExcludeMissing
                fun _screenName(): JsonField<String> = screenName

                /**
                 * Returns the raw JSON value of [text].
                 *
                 * Unlike [text], this method doesn't throw if the JSON field has an unexpected
                 * type.
                 */
                @JsonProperty("text") @ExcludeMissing fun _text(): JsonField<String> = text

                /**
                 * Returns the raw JSON value of [type].
                 *
                 * Unlike [type], this method doesn't throw if the JSON field has an unexpected
                 * type.
                 */
                @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<String> = type

                /**
                 * Returns the raw JSON value of [url].
                 *
                 * Unlike [url], this method doesn't throw if the JSON field has an unexpected type.
                 */
                @JsonProperty("url") @ExcludeMissing fun _url(): JsonField<String> = url

                /**
                 * Returns the raw JSON value of [urlType].
                 *
                 * Unlike [urlType], this method doesn't throw if the JSON field has an unexpected
                 * type.
                 */
                @JsonProperty("urlType") @ExcludeMissing fun _urlType(): JsonField<String> = urlType

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

                    /** Returns a mutable builder for constructing an instance of [Ref]. */
                    fun builder() = Builder()
                }

                /** A builder for [Ref]. */
                class Builder internal constructor() {

                    private var screenName: JsonField<String> = JsonMissing.of()
                    private var text: JsonField<String> = JsonMissing.of()
                    private var type: JsonField<String> = JsonMissing.of()
                    private var url: JsonField<String> = JsonMissing.of()
                    private var urlType: JsonField<String> = JsonMissing.of()
                    private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                    internal fun from(ref: Ref) = apply {
                        screenName = ref.screenName
                        text = ref.text
                        type = ref.type
                        url = ref.url
                        urlType = ref.urlType
                        additionalProperties = ref.additionalProperties.toMutableMap()
                    }

                    /** A mentioned account's username. */
                    fun screenName(screenName: String) = screenName(JsonField.of(screenName))

                    /** Sets [Builder.screenName] to an arbitrary JSON value. */
                    fun screenName(screenName: JsonField<String>) = apply {
                        this.screenName = screenName
                    }

                    /** A hashtag's or cashtag's text. */
                    fun text(text: String) = text(JsonField.of(text))

                    /** Sets [Builder.text] to an arbitrary JSON value. */
                    fun text(text: JsonField<String>) = apply { this.text = text }

                    /** X's kind, such as TimelineUrl. */
                    fun type(type: String) = type(JsonField.of(type))

                    /** Sets [Builder.type] to an arbitrary JSON value. */
                    fun type(type: JsonField<String>) = apply { this.type = type }

                    /** The link. */
                    fun url(url: String) = url(JsonField.of(url))

                    /** Sets [Builder.url] to an arbitrary JSON value. */
                    fun url(url: JsonField<String>) = apply { this.url = url }

                    /** How X opens the link, such as ExternalUrl. */
                    fun urlType(urlType: String) = urlType(JsonField.of(urlType))

                    /** Sets [Builder.urlType] to an arbitrary JSON value. */
                    fun urlType(urlType: JsonField<String>) = apply { this.urlType = urlType }

                    fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                        this.additionalProperties.clear()
                        putAllAdditionalProperties(additionalProperties)
                    }

                    fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                        additionalProperties.put(key, value)
                    }

                    fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) =
                        apply {
                            this.additionalProperties.putAll(additionalProperties)
                        }

                    fun removeAdditionalProperty(key: String) = apply {
                        additionalProperties.remove(key)
                    }

                    fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                        keys.forEach(::removeAdditionalProperty)
                    }

                    /**
                     * Returns an immutable instance of [Ref].
                     *
                     * Further updates to this [Builder] will not mutate the returned instance.
                     */
                    fun build(): Ref =
                        Ref(
                            screenName,
                            text,
                            type,
                            url,
                            urlType,
                            additionalProperties.toMutableMap(),
                        )
                }

                private var validated: Boolean = false

                /**
                 * Validates that the types of all values in this object match their expected types
                 * recursively.
                 *
                 * This method is _not_ forwards compatible with new types from the API for existing
                 * fields.
                 *
                 * @throws XTwitterScraperInvalidDataException if any value type in this object
                 *   doesn't match its expected type.
                 */
                fun validate(): Ref = apply {
                    if (validated) {
                        return@apply
                    }

                    screenName()
                    text()
                    type()
                    url()
                    urlType()
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
                    (if (screenName.asKnown() == null) 0 else 1) +
                        (if (text.asKnown() == null) 0 else 1) +
                        (if (type.asKnown() == null) 0 else 1) +
                        (if (url.asKnown() == null) 0 else 1) +
                        (if (urlType.asKnown() == null) 0 else 1)

                override fun equals(other: Any?): Boolean {
                    if (this === other) {
                        return true
                    }

                    return other is Ref &&
                        screenName == other.screenName &&
                        text == other.text &&
                        type == other.type &&
                        url == other.url &&
                        urlType == other.urlType &&
                        additionalProperties == other.additionalProperties
                }

                private val hashCode: Int by lazy {
                    Objects.hash(screenName, text, type, url, urlType, additionalProperties)
                }

                override fun hashCode(): Int = hashCode

                override fun toString() =
                    "Ref{screenName=$screenName, text=$text, type=$type, url=$url, urlType=$urlType, additionalProperties=$additionalProperties}"
            }

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is Entity &&
                    fromIndex == other.fromIndex &&
                    ref == other.ref &&
                    toIndex == other.toIndex &&
                    additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(fromIndex, ref, toIndex, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "Entity{fromIndex=$fromIndex, ref=$ref, toIndex=$toIndex, additionalProperties=$additionalProperties}"
        }

        /** Why X shows no post, when X says. */
        class PostUnavailable
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val reason: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("reason") @ExcludeMissing reason: JsonField<String> = JsonMissing.of()
            ) : this(reason, mutableMapOf())

            /**
             * Why X shows no post, as X names it.
             *
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type
             *   or is unexpectedly missing or null.
             */
            fun reason(): String = reason.getRequired("reason")

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
                 * Returns a mutable builder for constructing an instance of [PostUnavailable].
                 *
                 * The following fields are required:
                 * ```kotlin
                 * .reason()
                 * ```
                 */
                fun builder() = Builder()
            }

            /** A builder for [PostUnavailable]. */
            class Builder internal constructor() {

                private var reason: JsonField<String>? = null
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                internal fun from(postUnavailable: PostUnavailable) = apply {
                    reason = postUnavailable.reason
                    additionalProperties = postUnavailable.additionalProperties.toMutableMap()
                }

                /** Why X shows no post, as X names it. */
                fun reason(reason: String) = reason(JsonField.of(reason))

                /** Sets [Builder.reason] to an arbitrary JSON value. */
                fun reason(reason: JsonField<String>) = apply { this.reason = reason }

                fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                    this.additionalProperties.clear()
                    putAllAdditionalProperties(additionalProperties)
                }

                fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                    additionalProperties.put(key, value)
                }

                fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) =
                    apply {
                        this.additionalProperties.putAll(additionalProperties)
                    }

                fun removeAdditionalProperty(key: String) = apply {
                    additionalProperties.remove(key)
                }

                fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                    keys.forEach(::removeAdditionalProperty)
                }

                /**
                 * Returns an immutable instance of [PostUnavailable].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 *
                 * The following fields are required:
                 * ```kotlin
                 * .reason()
                 * ```
                 *
                 * @throws IllegalStateException if any required field is unset.
                 */
                fun build(): PostUnavailable =
                    PostUnavailable(
                        checkRequired("reason", reason),
                        additionalProperties.toMutableMap(),
                    )
            }

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
            fun validate(): PostUnavailable = apply {
                if (validated) {
                    return@apply
                }

                reason()
                validated = true
            }

            fun isValid(): Boolean =
                try {
                    validate()
                    true
                } catch (e: XTwitterScraperInvalidDataException) {
                    false
                }

            internal fun validity(): Int = (if (reason.asKnown() == null) 0 else 1)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is PostUnavailable &&
                    reason == other.reason &&
                    additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(reason, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "PostUnavailable{reason=$reason, additionalProperties=$additionalProperties}"
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Note &&
                id == other.id &&
                abovePcrhThreshold == other.abovePcrhThreshold &&
                appealStatus == other.appealStatus &&
                classification == other.classification &&
                contributor == other.contributor &&
                createdAt == other.createdAt &&
                decidedBy == other.decidedBy &&
                deleted == other.deleted &&
                displayUrl == other.displayUrl &&
                entities == other.entities &&
                fullyVisibleModel == other.fullyVisibleModel &&
                helpfulTags == other.helpfulTags &&
                impressionCount == other.impressionCount &&
                isApiAuthor == other.isApiAuthor &&
                isMediaNote == other.isMediaNote &&
                isTranslatable == other.isTranslatable &&
                isUrlNote == other.isUrlNote &&
                language == other.language &&
                mediaNoteMatchCount == other.mediaNoteMatchCount &&
                notHelpfulTags == other.notHelpfulTags &&
                post == other.post &&
                postId == other.postId &&
                postUnavailable == other.postUnavailable &&
                ratingStatus == other.ratingStatus &&
                text == other.text &&
                timeToHelpfulFromNoteMs == other.timeToHelpfulFromNoteMs &&
                timeToHelpfulFromPostMs == other.timeToHelpfulFromPostMs &&
                tombstone == other.tombstone &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(
                id,
                abovePcrhThreshold,
                appealStatus,
                classification,
                contributor,
                createdAt,
                decidedBy,
                deleted,
                displayUrl,
                entities,
                fullyVisibleModel,
                helpfulTags,
                impressionCount,
                isApiAuthor,
                isMediaNote,
                isTranslatable,
                isUrlNote,
                language,
                mediaNoteMatchCount,
                notHelpfulTags,
                post,
                postId,
                postUnavailable,
                ratingStatus,
                text,
                timeToHelpfulFromNoteMs,
                timeToHelpfulFromPostMs,
                tombstone,
                additionalProperties,
            )
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Note{id=$id, abovePcrhThreshold=$abovePcrhThreshold, appealStatus=$appealStatus, classification=$classification, contributor=$contributor, createdAt=$createdAt, decidedBy=$decidedBy, deleted=$deleted, displayUrl=$displayUrl, entities=$entities, fullyVisibleModel=$fullyVisibleModel, helpfulTags=$helpfulTags, impressionCount=$impressionCount, isApiAuthor=$isApiAuthor, isMediaNote=$isMediaNote, isTranslatable=$isTranslatable, isUrlNote=$isUrlNote, language=$language, mediaNoteMatchCount=$mediaNoteMatchCount, notHelpfulTags=$notHelpfulTags, post=$post, postId=$postId, postUnavailable=$postUnavailable, ratingStatus=$ratingStatus, text=$text, timeToHelpfulFromNoteMs=$timeToHelpfulFromNoteMs, timeToHelpfulFromPostMs=$timeToHelpfulFromPostMs, tombstone=$tombstone, additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is PaginatedCommunityNotes &&
            hasNextPage == other.hasNextPage &&
            nextCursor == other.nextCursor &&
            notes == other.notes &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(hasNextPage, nextCursor, notes, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "PaginatedCommunityNotes{hasNextPage=$hasNextPage, nextCursor=$nextCursor, notes=$notes, additionalProperties=$additionalProperties}"
}
