package com.x_twitter_scraper.api.models.x.spaces

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
import com.x_twitter_scraper.api.models.UserProfile
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects

/** X Space */
class Space
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val admins: JsonField<List<SpaceParticipant>>,
    private val listeners: JsonField<List<SpaceParticipant>>,
    private val speakers: JsonField<List<SpaceParticipant>>,
    private val contentType: JsonField<String>,
    private val conversationControls: JsonField<Long>,
    private val createdAt: JsonField<OffsetDateTime>,
    private val creator: JsonField<UserProfile>,
    private val disallowJoin: JsonField<Boolean>,
    private val endedAt: JsonField<OffsetDateTime>,
    private val isAvailableForClipping: JsonField<Boolean>,
    private val isAvailableForReplay: JsonField<Boolean>,
    private val isEmployeeOnly: JsonField<Boolean>,
    private val isLocked: JsonField<Boolean>,
    private val maxAdminCapacity: JsonField<Long>,
    private val mediaKey: JsonField<String>,
    private val mentionedUserIds: JsonField<List<String>>,
    private val noIncognito: JsonField<Boolean>,
    private val participantCount: JsonField<Long>,
    private val scheduledStart: JsonField<OffsetDateTime>,
    private val sharings: JsonField<List<Sharing>>,
    private val startedAt: JsonField<OffsetDateTime>,
    private val state: JsonField<String>,
    private val title: JsonField<String>,
    private val totalLiveListeners: JsonField<Long>,
    private val totalReplayWatched: JsonField<Long>,
    private val tweet: JsonField<SearchTweet>,
    private val tweetId: JsonField<String>,
    private val updatedAt: JsonField<OffsetDateTime>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("admins")
        @ExcludeMissing
        admins: JsonField<List<SpaceParticipant>> = JsonMissing.of(),
        @JsonProperty("listeners")
        @ExcludeMissing
        listeners: JsonField<List<SpaceParticipant>> = JsonMissing.of(),
        @JsonProperty("speakers")
        @ExcludeMissing
        speakers: JsonField<List<SpaceParticipant>> = JsonMissing.of(),
        @JsonProperty("contentType")
        @ExcludeMissing
        contentType: JsonField<String> = JsonMissing.of(),
        @JsonProperty("conversationControls")
        @ExcludeMissing
        conversationControls: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("createdAt")
        @ExcludeMissing
        createdAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("creator") @ExcludeMissing creator: JsonField<UserProfile> = JsonMissing.of(),
        @JsonProperty("disallowJoin")
        @ExcludeMissing
        disallowJoin: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("endedAt")
        @ExcludeMissing
        endedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("isAvailableForClipping")
        @ExcludeMissing
        isAvailableForClipping: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("isAvailableForReplay")
        @ExcludeMissing
        isAvailableForReplay: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("isEmployeeOnly")
        @ExcludeMissing
        isEmployeeOnly: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("isLocked") @ExcludeMissing isLocked: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("maxAdminCapacity")
        @ExcludeMissing
        maxAdminCapacity: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("mediaKey") @ExcludeMissing mediaKey: JsonField<String> = JsonMissing.of(),
        @JsonProperty("mentionedUserIds")
        @ExcludeMissing
        mentionedUserIds: JsonField<List<String>> = JsonMissing.of(),
        @JsonProperty("noIncognito")
        @ExcludeMissing
        noIncognito: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("participantCount")
        @ExcludeMissing
        participantCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("scheduledStart")
        @ExcludeMissing
        scheduledStart: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("sharings")
        @ExcludeMissing
        sharings: JsonField<List<Sharing>> = JsonMissing.of(),
        @JsonProperty("startedAt")
        @ExcludeMissing
        startedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("state") @ExcludeMissing state: JsonField<String> = JsonMissing.of(),
        @JsonProperty("title") @ExcludeMissing title: JsonField<String> = JsonMissing.of(),
        @JsonProperty("totalLiveListeners")
        @ExcludeMissing
        totalLiveListeners: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("totalReplayWatched")
        @ExcludeMissing
        totalReplayWatched: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("tweet") @ExcludeMissing tweet: JsonField<SearchTweet> = JsonMissing.of(),
        @JsonProperty("tweetId") @ExcludeMissing tweetId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("updatedAt")
        @ExcludeMissing
        updatedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
    ) : this(
        id,
        admins,
        listeners,
        speakers,
        contentType,
        conversationControls,
        createdAt,
        creator,
        disallowJoin,
        endedAt,
        isAvailableForClipping,
        isAvailableForReplay,
        isEmployeeOnly,
        isLocked,
        maxAdminCapacity,
        mediaKey,
        mentionedUserIds,
        noIncognito,
        participantCount,
        scheduledStart,
        sharings,
        startedAt,
        state,
        title,
        totalLiveListeners,
        totalReplayWatched,
        tweet,
        tweetId,
        updatedAt,
        mutableMapOf(),
    )

    /**
     * Space ID
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun id(): String = id.getRequired("id")

    /**
     * Hosts and co-hosts
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun admins(): List<SpaceParticipant> = admins.getRequired("admins")

    /**
     * Accounts X lists as listeners
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun listeners(): List<SpaceParticipant> = listeners.getRequired("listeners")

    /**
     * Accounts X lists as speakers
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun speakers(): List<SpaceParticipant> = speakers.getRequired("speakers")

    /**
     * Audio or video kind, as X names it
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun contentType(): String? = contentType.getNullable("contentType")

    /**
     * X's code for who may speak
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun conversationControls(): Long? = conversationControls.getNullable("conversationControls")

    /**
     * When the Space was made
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun createdAt(): OffsetDateTime? = createdAt.getNullable("createdAt")

    /**
     * Public X profile.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun creator(): UserProfile? = creator.getNullable("creator")

    /**
     * Whether X lets no more accounts join
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun disallowJoin(): Boolean? = disallowJoin.getNullable("disallowJoin")

    /**
     * When the Space ended
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun endedAt(): OffsetDateTime? = endedAt.getNullable("endedAt")

    /**
     * Whether listeners may clip the Space
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun isAvailableForClipping(): Boolean? =
        isAvailableForClipping.getNullable("isAvailableForClipping")

    /**
     * Whether X offers a recording
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun isAvailableForReplay(): Boolean? = isAvailableForReplay.getNullable("isAvailableForReplay")

    /**
     * Whether only X employees may join
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun isEmployeeOnly(): Boolean? = isEmployeeOnly.getNullable("isEmployeeOnly")

    /**
     * Whether the host locked the Space
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun isLocked(): Boolean? = isLocked.getNullable("isLocked")

    /**
     * Most hosts and co-hosts the Space allows
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun maxAdminCapacity(): Long? = maxAdminCapacity.getNullable("maxAdminCapacity")

    /**
     * X's media key for the Space's audio
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun mediaKey(): String? = mediaKey.getNullable("mediaKey")

    /**
     * IDs of the accounts the host tagged
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun mentionedUserIds(): List<String>? = mentionedUserIds.getNullable("mentionedUserIds")

    /**
     * Whether anonymous listening is off
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun noIncognito(): Boolean? = noIncognito.getNullable("noIncognito")

    /**
     * Accounts in the Space now, while it is live
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun participantCount(): Long? = participantCount.getNullable("participantCount")

    /**
     * When the Space was set to start
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun scheduledStart(): OffsetDateTime? = scheduledStart.getNullable("scheduledStart")

    /**
     * Posts the hosts and speakers shared in the Space, in X's order. Present only when X lists
     * them.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun sharings(): List<Sharing>? = sharings.getNullable("sharings")

    /**
     * When the Space went live
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun startedAt(): OffsetDateTime? = startedAt.getNullable("startedAt")

    /**
     * NotStarted (scheduled), Running (live), Ended, Canceled (scheduled, never started) or
     * TimedOut (closed by X, not by its host)
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun state(): String? = state.getNullable("state")

    /**
     * Space title
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun title(): String? = title.getNullable("title")

    /**
     * Accounts that listened live
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun totalLiveListeners(): Long? = totalLiveListeners.getNullable("totalLiveListeners")

    /**
     * Accounts that played the recording
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun totalReplayWatched(): Long? = totalReplayWatched.getNullable("totalReplayWatched")

    /**
     * Search tweet with author. Zero metrics may mean X omitted counts.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun tweet(): SearchTweet? = tweet.getNullable("tweet")

    /**
     * ID of the post that announces the Space
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun tweetId(): String? = tweetId.getNullable("tweetId")

    /**
     * When X last changed the Space
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun updatedAt(): OffsetDateTime? = updatedAt.getNullable("updatedAt")

    /**
     * Returns the raw JSON value of [id].
     *
     * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    /**
     * Returns the raw JSON value of [admins].
     *
     * Unlike [admins], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("admins")
    @ExcludeMissing
    fun _admins(): JsonField<List<SpaceParticipant>> = admins

    /**
     * Returns the raw JSON value of [listeners].
     *
     * Unlike [listeners], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("listeners")
    @ExcludeMissing
    fun _listeners(): JsonField<List<SpaceParticipant>> = listeners

    /**
     * Returns the raw JSON value of [speakers].
     *
     * Unlike [speakers], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("speakers")
    @ExcludeMissing
    fun _speakers(): JsonField<List<SpaceParticipant>> = speakers

    /**
     * Returns the raw JSON value of [contentType].
     *
     * Unlike [contentType], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("contentType") @ExcludeMissing fun _contentType(): JsonField<String> = contentType

    /**
     * Returns the raw JSON value of [conversationControls].
     *
     * Unlike [conversationControls], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("conversationControls")
    @ExcludeMissing
    fun _conversationControls(): JsonField<Long> = conversationControls

    /**
     * Returns the raw JSON value of [createdAt].
     *
     * Unlike [createdAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("createdAt")
    @ExcludeMissing
    fun _createdAt(): JsonField<OffsetDateTime> = createdAt

    /**
     * Returns the raw JSON value of [creator].
     *
     * Unlike [creator], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("creator") @ExcludeMissing fun _creator(): JsonField<UserProfile> = creator

    /**
     * Returns the raw JSON value of [disallowJoin].
     *
     * Unlike [disallowJoin], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("disallowJoin")
    @ExcludeMissing
    fun _disallowJoin(): JsonField<Boolean> = disallowJoin

    /**
     * Returns the raw JSON value of [endedAt].
     *
     * Unlike [endedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("endedAt") @ExcludeMissing fun _endedAt(): JsonField<OffsetDateTime> = endedAt

    /**
     * Returns the raw JSON value of [isAvailableForClipping].
     *
     * Unlike [isAvailableForClipping], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("isAvailableForClipping")
    @ExcludeMissing
    fun _isAvailableForClipping(): JsonField<Boolean> = isAvailableForClipping

    /**
     * Returns the raw JSON value of [isAvailableForReplay].
     *
     * Unlike [isAvailableForReplay], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("isAvailableForReplay")
    @ExcludeMissing
    fun _isAvailableForReplay(): JsonField<Boolean> = isAvailableForReplay

    /**
     * Returns the raw JSON value of [isEmployeeOnly].
     *
     * Unlike [isEmployeeOnly], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("isEmployeeOnly")
    @ExcludeMissing
    fun _isEmployeeOnly(): JsonField<Boolean> = isEmployeeOnly

    /**
     * Returns the raw JSON value of [isLocked].
     *
     * Unlike [isLocked], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("isLocked") @ExcludeMissing fun _isLocked(): JsonField<Boolean> = isLocked

    /**
     * Returns the raw JSON value of [maxAdminCapacity].
     *
     * Unlike [maxAdminCapacity], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("maxAdminCapacity")
    @ExcludeMissing
    fun _maxAdminCapacity(): JsonField<Long> = maxAdminCapacity

    /**
     * Returns the raw JSON value of [mediaKey].
     *
     * Unlike [mediaKey], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("mediaKey") @ExcludeMissing fun _mediaKey(): JsonField<String> = mediaKey

    /**
     * Returns the raw JSON value of [mentionedUserIds].
     *
     * Unlike [mentionedUserIds], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("mentionedUserIds")
    @ExcludeMissing
    fun _mentionedUserIds(): JsonField<List<String>> = mentionedUserIds

    /**
     * Returns the raw JSON value of [noIncognito].
     *
     * Unlike [noIncognito], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("noIncognito")
    @ExcludeMissing
    fun _noIncognito(): JsonField<Boolean> = noIncognito

    /**
     * Returns the raw JSON value of [participantCount].
     *
     * Unlike [participantCount], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("participantCount")
    @ExcludeMissing
    fun _participantCount(): JsonField<Long> = participantCount

    /**
     * Returns the raw JSON value of [scheduledStart].
     *
     * Unlike [scheduledStart], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("scheduledStart")
    @ExcludeMissing
    fun _scheduledStart(): JsonField<OffsetDateTime> = scheduledStart

    /**
     * Returns the raw JSON value of [sharings].
     *
     * Unlike [sharings], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("sharings") @ExcludeMissing fun _sharings(): JsonField<List<Sharing>> = sharings

    /**
     * Returns the raw JSON value of [startedAt].
     *
     * Unlike [startedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("startedAt")
    @ExcludeMissing
    fun _startedAt(): JsonField<OffsetDateTime> = startedAt

    /**
     * Returns the raw JSON value of [state].
     *
     * Unlike [state], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("state") @ExcludeMissing fun _state(): JsonField<String> = state

    /**
     * Returns the raw JSON value of [title].
     *
     * Unlike [title], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("title") @ExcludeMissing fun _title(): JsonField<String> = title

    /**
     * Returns the raw JSON value of [totalLiveListeners].
     *
     * Unlike [totalLiveListeners], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("totalLiveListeners")
    @ExcludeMissing
    fun _totalLiveListeners(): JsonField<Long> = totalLiveListeners

    /**
     * Returns the raw JSON value of [totalReplayWatched].
     *
     * Unlike [totalReplayWatched], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("totalReplayWatched")
    @ExcludeMissing
    fun _totalReplayWatched(): JsonField<Long> = totalReplayWatched

    /**
     * Returns the raw JSON value of [tweet].
     *
     * Unlike [tweet], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("tweet") @ExcludeMissing fun _tweet(): JsonField<SearchTweet> = tweet

    /**
     * Returns the raw JSON value of [tweetId].
     *
     * Unlike [tweetId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("tweetId") @ExcludeMissing fun _tweetId(): JsonField<String> = tweetId

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

        /**
         * Returns a mutable builder for constructing an instance of [Space].
         *
         * The following fields are required:
         * ```kotlin
         * .id()
         * .admins()
         * .listeners()
         * .speakers()
         * ```
         */
        fun builder() = Builder()
    }

    /** A builder for [Space]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var admins: JsonField<MutableList<SpaceParticipant>>? = null
        private var listeners: JsonField<MutableList<SpaceParticipant>>? = null
        private var speakers: JsonField<MutableList<SpaceParticipant>>? = null
        private var contentType: JsonField<String> = JsonMissing.of()
        private var conversationControls: JsonField<Long> = JsonMissing.of()
        private var createdAt: JsonField<OffsetDateTime> = JsonMissing.of()
        private var creator: JsonField<UserProfile> = JsonMissing.of()
        private var disallowJoin: JsonField<Boolean> = JsonMissing.of()
        private var endedAt: JsonField<OffsetDateTime> = JsonMissing.of()
        private var isAvailableForClipping: JsonField<Boolean> = JsonMissing.of()
        private var isAvailableForReplay: JsonField<Boolean> = JsonMissing.of()
        private var isEmployeeOnly: JsonField<Boolean> = JsonMissing.of()
        private var isLocked: JsonField<Boolean> = JsonMissing.of()
        private var maxAdminCapacity: JsonField<Long> = JsonMissing.of()
        private var mediaKey: JsonField<String> = JsonMissing.of()
        private var mentionedUserIds: JsonField<MutableList<String>>? = null
        private var noIncognito: JsonField<Boolean> = JsonMissing.of()
        private var participantCount: JsonField<Long> = JsonMissing.of()
        private var scheduledStart: JsonField<OffsetDateTime> = JsonMissing.of()
        private var sharings: JsonField<MutableList<Sharing>>? = null
        private var startedAt: JsonField<OffsetDateTime> = JsonMissing.of()
        private var state: JsonField<String> = JsonMissing.of()
        private var title: JsonField<String> = JsonMissing.of()
        private var totalLiveListeners: JsonField<Long> = JsonMissing.of()
        private var totalReplayWatched: JsonField<Long> = JsonMissing.of()
        private var tweet: JsonField<SearchTweet> = JsonMissing.of()
        private var tweetId: JsonField<String> = JsonMissing.of()
        private var updatedAt: JsonField<OffsetDateTime> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        internal fun from(space: Space) = apply {
            id = space.id
            admins = space.admins.map { it.toMutableList() }
            listeners = space.listeners.map { it.toMutableList() }
            speakers = space.speakers.map { it.toMutableList() }
            contentType = space.contentType
            conversationControls = space.conversationControls
            createdAt = space.createdAt
            creator = space.creator
            disallowJoin = space.disallowJoin
            endedAt = space.endedAt
            isAvailableForClipping = space.isAvailableForClipping
            isAvailableForReplay = space.isAvailableForReplay
            isEmployeeOnly = space.isEmployeeOnly
            isLocked = space.isLocked
            maxAdminCapacity = space.maxAdminCapacity
            mediaKey = space.mediaKey
            mentionedUserIds = space.mentionedUserIds.map { it.toMutableList() }
            noIncognito = space.noIncognito
            participantCount = space.participantCount
            scheduledStart = space.scheduledStart
            sharings = space.sharings.map { it.toMutableList() }
            startedAt = space.startedAt
            state = space.state
            title = space.title
            totalLiveListeners = space.totalLiveListeners
            totalReplayWatched = space.totalReplayWatched
            tweet = space.tweet
            tweetId = space.tweetId
            updatedAt = space.updatedAt
            additionalProperties = space.additionalProperties.toMutableMap()
        }

        /** Space ID */
        fun id(id: String) = id(JsonField.of(id))

        /** Sets [Builder.id] to an arbitrary JSON value. */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /** Hosts and co-hosts */
        fun admins(admins: List<SpaceParticipant>) = admins(JsonField.of(admins))

        /** Sets [Builder.admins] to an arbitrary JSON value. */
        fun admins(admins: JsonField<List<SpaceParticipant>>) = apply {
            this.admins = admins.map { it.toMutableList() }
        }

        /**
         * Adds a single [SpaceParticipant] to [admins].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addAdmin(admin: SpaceParticipant) = apply {
            admins =
                (admins ?: JsonField.of(mutableListOf())).also {
                    checkKnown("admins", it).add(admin)
                }
        }

        /** Accounts X lists as listeners */
        fun listeners(listeners: List<SpaceParticipant>) = listeners(JsonField.of(listeners))

        /** Sets [Builder.listeners] to an arbitrary JSON value. */
        fun listeners(listeners: JsonField<List<SpaceParticipant>>) = apply {
            this.listeners = listeners.map { it.toMutableList() }
        }

        /**
         * Adds a single [SpaceParticipant] to [listeners].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addListener(listener: SpaceParticipant) = apply {
            listeners =
                (listeners ?: JsonField.of(mutableListOf())).also {
                    checkKnown("listeners", it).add(listener)
                }
        }

        /** Accounts X lists as speakers */
        fun speakers(speakers: List<SpaceParticipant>) = speakers(JsonField.of(speakers))

        /** Sets [Builder.speakers] to an arbitrary JSON value. */
        fun speakers(speakers: JsonField<List<SpaceParticipant>>) = apply {
            this.speakers = speakers.map { it.toMutableList() }
        }

        /**
         * Adds a single [SpaceParticipant] to [speakers].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addSpeaker(speaker: SpaceParticipant) = apply {
            speakers =
                (speakers ?: JsonField.of(mutableListOf())).also {
                    checkKnown("speakers", it).add(speaker)
                }
        }

        /** Audio or video kind, as X names it */
        fun contentType(contentType: String) = contentType(JsonField.of(contentType))

        /** Sets [Builder.contentType] to an arbitrary JSON value. */
        fun contentType(contentType: JsonField<String>) = apply {
            this.contentType = contentType
        }

        /** X's code for who may speak */
        fun conversationControls(conversationControls: Long) =
            conversationControls(JsonField.of(conversationControls))

        /** Sets [Builder.conversationControls] to an arbitrary JSON value. */
        fun conversationControls(conversationControls: JsonField<Long>) = apply {
            this.conversationControls = conversationControls
        }

        /** When the Space was made */
        fun createdAt(createdAt: OffsetDateTime) = createdAt(JsonField.of(createdAt))

        /** Sets [Builder.createdAt] to an arbitrary JSON value. */
        fun createdAt(createdAt: JsonField<OffsetDateTime>) = apply {
            this.createdAt = createdAt
        }

        /** Public X profile. */
        fun creator(creator: UserProfile) = creator(JsonField.of(creator))

        /** Sets [Builder.creator] to an arbitrary JSON value. */
        fun creator(creator: JsonField<UserProfile>) = apply { this.creator = creator }

        /** Whether X lets no more accounts join */
        fun disallowJoin(disallowJoin: Boolean) = disallowJoin(JsonField.of(disallowJoin))

        /** Sets [Builder.disallowJoin] to an arbitrary JSON value. */
        fun disallowJoin(disallowJoin: JsonField<Boolean>) = apply {
            this.disallowJoin = disallowJoin
        }

        /** When the Space ended */
        fun endedAt(endedAt: OffsetDateTime) = endedAt(JsonField.of(endedAt))

        /** Sets [Builder.endedAt] to an arbitrary JSON value. */
        fun endedAt(endedAt: JsonField<OffsetDateTime>) = apply { this.endedAt = endedAt }

        /** Whether listeners may clip the Space */
        fun isAvailableForClipping(isAvailableForClipping: Boolean) =
            isAvailableForClipping(JsonField.of(isAvailableForClipping))

        /** Sets [Builder.isAvailableForClipping] to an arbitrary JSON value. */
        fun isAvailableForClipping(isAvailableForClipping: JsonField<Boolean>) = apply {
            this.isAvailableForClipping = isAvailableForClipping
        }

        /** Whether X offers a recording */
        fun isAvailableForReplay(isAvailableForReplay: Boolean) =
            isAvailableForReplay(JsonField.of(isAvailableForReplay))

        /** Sets [Builder.isAvailableForReplay] to an arbitrary JSON value. */
        fun isAvailableForReplay(isAvailableForReplay: JsonField<Boolean>) = apply {
            this.isAvailableForReplay = isAvailableForReplay
        }

        /** Whether only X employees may join */
        fun isEmployeeOnly(isEmployeeOnly: Boolean) = isEmployeeOnly(JsonField.of(isEmployeeOnly))

        /** Sets [Builder.isEmployeeOnly] to an arbitrary JSON value. */
        fun isEmployeeOnly(isEmployeeOnly: JsonField<Boolean>) = apply {
            this.isEmployeeOnly = isEmployeeOnly
        }

        /** Whether the host locked the Space */
        fun isLocked(isLocked: Boolean) = isLocked(JsonField.of(isLocked))

        /** Sets [Builder.isLocked] to an arbitrary JSON value. */
        fun isLocked(isLocked: JsonField<Boolean>) = apply { this.isLocked = isLocked }

        /** Most hosts and co-hosts the Space allows */
        fun maxAdminCapacity(maxAdminCapacity: Long) =
            maxAdminCapacity(JsonField.of(maxAdminCapacity))

        /** Sets [Builder.maxAdminCapacity] to an arbitrary JSON value. */
        fun maxAdminCapacity(maxAdminCapacity: JsonField<Long>) = apply {
            this.maxAdminCapacity = maxAdminCapacity
        }

        /** X's media key for the Space's audio */
        fun mediaKey(mediaKey: String) = mediaKey(JsonField.of(mediaKey))

        /** Sets [Builder.mediaKey] to an arbitrary JSON value. */
        fun mediaKey(mediaKey: JsonField<String>) = apply { this.mediaKey = mediaKey }

        /** IDs of the accounts the host tagged */
        fun mentionedUserIds(mentionedUserIds: List<String>) =
            mentionedUserIds(JsonField.of(mentionedUserIds))

        /** Sets [Builder.mentionedUserIds] to an arbitrary JSON value. */
        fun mentionedUserIds(mentionedUserIds: JsonField<List<String>>) = apply {
            this.mentionedUserIds = mentionedUserIds.map { it.toMutableList() }
        }

        /**
         * Adds a single [String] to [mentionedUserIds].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addMentionedUserId(mentionedUserId: String) = apply {
            mentionedUserIds =
                (mentionedUserIds ?: JsonField.of(mutableListOf())).also {
                    checkKnown("mentionedUserIds", it).add(mentionedUserId)
                }
        }

        /** Whether anonymous listening is off */
        fun noIncognito(noIncognito: Boolean) = noIncognito(JsonField.of(noIncognito))

        /** Sets [Builder.noIncognito] to an arbitrary JSON value. */
        fun noIncognito(noIncognito: JsonField<Boolean>) = apply {
            this.noIncognito = noIncognito
        }

        /** Accounts in the Space now, while it is live */
        fun participantCount(participantCount: Long) =
            participantCount(JsonField.of(participantCount))

        /** Sets [Builder.participantCount] to an arbitrary JSON value. */
        fun participantCount(participantCount: JsonField<Long>) = apply {
            this.participantCount = participantCount
        }

        /** When the Space was set to start */
        fun scheduledStart(scheduledStart: OffsetDateTime) =
            scheduledStart(JsonField.of(scheduledStart))

        /** Sets [Builder.scheduledStart] to an arbitrary JSON value. */
        fun scheduledStart(scheduledStart: JsonField<OffsetDateTime>) = apply {
            this.scheduledStart = scheduledStart
        }

        /**
         * Posts the hosts and speakers shared in the Space, in X's order. Present only when X lists
         * them.
         */
        fun sharings(sharings: List<Sharing>) = sharings(JsonField.of(sharings))

        /** Sets [Builder.sharings] to an arbitrary JSON value. */
        fun sharings(sharings: JsonField<List<Sharing>>) = apply {
            this.sharings = sharings.map { it.toMutableList() }
        }

        /**
         * Adds a single [Sharing] to [sharings].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addSharing(sharing: Sharing) = apply {
            sharings =
                (sharings ?: JsonField.of(mutableListOf())).also {
                    checkKnown("sharings", it).add(sharing)
                }
        }

        /** When the Space went live */
        fun startedAt(startedAt: OffsetDateTime) = startedAt(JsonField.of(startedAt))

        /** Sets [Builder.startedAt] to an arbitrary JSON value. */
        fun startedAt(startedAt: JsonField<OffsetDateTime>) = apply {
            this.startedAt = startedAt
        }

        /**
         * NotStarted (scheduled), Running (live), Ended, Canceled (scheduled, never started) or
         * TimedOut (closed by X, not by its host)
         */
        fun state(state: String) = state(JsonField.of(state))

        /** Sets [Builder.state] to an arbitrary JSON value. */
        fun state(state: JsonField<String>) = apply { this.state = state }

        /** Space title */
        fun title(title: String) = title(JsonField.of(title))

        /** Sets [Builder.title] to an arbitrary JSON value. */
        fun title(title: JsonField<String>) = apply { this.title = title }

        /** Accounts that listened live */
        fun totalLiveListeners(totalLiveListeners: Long) =
            totalLiveListeners(JsonField.of(totalLiveListeners))

        /** Sets [Builder.totalLiveListeners] to an arbitrary JSON value. */
        fun totalLiveListeners(totalLiveListeners: JsonField<Long>) = apply {
            this.totalLiveListeners = totalLiveListeners
        }

        /** Accounts that played the recording */
        fun totalReplayWatched(totalReplayWatched: Long) =
            totalReplayWatched(JsonField.of(totalReplayWatched))

        /** Sets [Builder.totalReplayWatched] to an arbitrary JSON value. */
        fun totalReplayWatched(totalReplayWatched: JsonField<Long>) = apply {
            this.totalReplayWatched = totalReplayWatched
        }

        /** Search tweet with author. Zero metrics may mean X omitted counts. */
        fun tweet(tweet: SearchTweet) = tweet(JsonField.of(tweet))

        /** Sets [Builder.tweet] to an arbitrary JSON value. */
        fun tweet(tweet: JsonField<SearchTweet>) = apply { this.tweet = tweet }

        /** ID of the post that announces the Space */
        fun tweetId(tweetId: String) = tweetId(JsonField.of(tweetId))

        /** Sets [Builder.tweetId] to an arbitrary JSON value. */
        fun tweetId(tweetId: JsonField<String>) = apply { this.tweetId = tweetId }

        /** When X last changed the Space */
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

        fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

        fun removeAllAdditionalProperties(keys: Set<String>) = apply {
            keys.forEach(::removeAdditionalProperty)
        }

        /**
         * Returns an immutable instance of [Space].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```kotlin
         * .id()
         * .admins()
         * .listeners()
         * .speakers()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): Space =
            Space(
                checkRequired("id", id),
                checkRequired("admins", admins).map { it.toImmutable() },
                checkRequired("listeners", listeners).map { it.toImmutable() },
                checkRequired("speakers", speakers).map { it.toImmutable() },
                contentType,
                conversationControls,
                createdAt,
                creator,
                disallowJoin,
                endedAt,
                isAvailableForClipping,
                isAvailableForReplay,
                isEmployeeOnly,
                isLocked,
                maxAdminCapacity,
                mediaKey,
                (mentionedUserIds ?: JsonMissing.of()).map { it.toImmutable() },
                noIncognito,
                participantCount,
                scheduledStart,
                (sharings ?: JsonMissing.of()).map { it.toImmutable() },
                startedAt,
                state,
                title,
                totalLiveListeners,
                totalReplayWatched,
                tweet,
                tweetId,
                updatedAt,
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
    fun validate(): Space = apply {
        if (validated) {
            return@apply
        }

        id()
        admins().forEach { it.validate() }
        listeners().forEach { it.validate() }
        speakers().forEach { it.validate() }
        contentType()
        conversationControls()
        createdAt()
        creator()?.validate()
        disallowJoin()
        endedAt()
        isAvailableForClipping()
        isAvailableForReplay()
        isEmployeeOnly()
        isLocked()
        maxAdminCapacity()
        mediaKey()
        mentionedUserIds()
        noIncognito()
        participantCount()
        scheduledStart()
        sharings()?.forEach { it.validate() }
        startedAt()
        state()
        title()
        totalLiveListeners()
        totalReplayWatched()
        tweet()?.validate()
        tweetId()
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
        (if (id.asKnown() == null) 0 else 1) +
            (admins.asKnown()?.sumOf { it.validity() } ?: 0) +
            (listeners.asKnown()?.sumOf { it.validity() } ?: 0) +
            (speakers.asKnown()?.sumOf { it.validity() } ?: 0) +
            (if (contentType.asKnown() == null) 0 else 1) +
            (if (conversationControls.asKnown() == null) 0 else 1) +
            (if (createdAt.asKnown() == null) 0 else 1) +
            (creator.asKnown()?.validity() ?: 0) +
            (if (disallowJoin.asKnown() == null) 0 else 1) +
            (if (endedAt.asKnown() == null) 0 else 1) +
            (if (isAvailableForClipping.asKnown() == null) 0 else 1) +
            (if (isAvailableForReplay.asKnown() == null) 0 else 1) +
            (if (isEmployeeOnly.asKnown() == null) 0 else 1) +
            (if (isLocked.asKnown() == null) 0 else 1) +
            (if (maxAdminCapacity.asKnown() == null) 0 else 1) +
            (if (mediaKey.asKnown() == null) 0 else 1) +
            (mentionedUserIds.asKnown()?.size ?: 0) +
            (if (noIncognito.asKnown() == null) 0 else 1) +
            (if (participantCount.asKnown() == null) 0 else 1) +
            (if (scheduledStart.asKnown() == null) 0 else 1) +
            (sharings.asKnown()?.sumOf { it.validity() } ?: 0) +
            (if (startedAt.asKnown() == null) 0 else 1) +
            (if (state.asKnown() == null) 0 else 1) +
            (if (title.asKnown() == null) 0 else 1) +
            (if (totalLiveListeners.asKnown() == null) 0 else 1) +
            (if (totalReplayWatched.asKnown() == null) 0 else 1) +
            (tweet.asKnown()?.validity() ?: 0) +
            (if (tweetId.asKnown() == null) 0 else 1) +
            (if (updatedAt.asKnown() == null) 0 else 1)

    /** A post shared in a Space, & the account that shared it. */
    class Sharing
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val id: JsonField<String>,
        private val sharedAt: JsonField<OffsetDateTime>,
        private val sharedBy: JsonField<UserProfile>,
        private val tweet: JsonField<SearchTweet>,
        private val updatedAt: JsonField<OffsetDateTime>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
            @JsonProperty("sharedAt")
            @ExcludeMissing
            sharedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
            @JsonProperty("sharedBy")
            @ExcludeMissing
            sharedBy: JsonField<UserProfile> = JsonMissing.of(),
            @JsonProperty("tweet") @ExcludeMissing tweet: JsonField<SearchTweet> = JsonMissing.of(),
            @JsonProperty("updatedAt")
            @ExcludeMissing
            updatedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        ) : this(id, sharedAt, sharedBy, tweet, updatedAt, mutableMapOf())

        /**
         * X's ID of the sharing, not of the post
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun id(): String? = id.getNullable("id")

        /**
         * When the post was shared
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun sharedAt(): OffsetDateTime? = sharedAt.getNullable("sharedAt")

        /**
         * Public X profile.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun sharedBy(): UserProfile? = sharedBy.getNullable("sharedBy")

        /**
         * Search tweet with author. Zero metrics may mean X omitted counts.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun tweet(): SearchTweet? = tweet.getNullable("tweet")

        /**
         * When X last changed the sharing
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun updatedAt(): OffsetDateTime? = updatedAt.getNullable("updatedAt")

        /**
         * Returns the raw JSON value of [id].
         *
         * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

        /**
         * Returns the raw JSON value of [sharedAt].
         *
         * Unlike [sharedAt], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("sharedAt")
        @ExcludeMissing
        fun _sharedAt(): JsonField<OffsetDateTime> = sharedAt

        /**
         * Returns the raw JSON value of [sharedBy].
         *
         * Unlike [sharedBy], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("sharedBy") @ExcludeMissing fun _sharedBy(): JsonField<UserProfile> = sharedBy

        /**
         * Returns the raw JSON value of [tweet].
         *
         * Unlike [tweet], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("tweet") @ExcludeMissing fun _tweet(): JsonField<SearchTweet> = tweet

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

            /** Returns a mutable builder for constructing an instance of [Sharing]. */
            fun builder() = Builder()
        }

        /** A builder for [Sharing]. */
        class Builder internal constructor() {

            private var id: JsonField<String> = JsonMissing.of()
            private var sharedAt: JsonField<OffsetDateTime> = JsonMissing.of()
            private var sharedBy: JsonField<UserProfile> = JsonMissing.of()
            private var tweet: JsonField<SearchTweet> = JsonMissing.of()
            private var updatedAt: JsonField<OffsetDateTime> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(sharing: Sharing) = apply {
                id = sharing.id
                sharedAt = sharing.sharedAt
                sharedBy = sharing.sharedBy
                tweet = sharing.tweet
                updatedAt = sharing.updatedAt
                additionalProperties = sharing.additionalProperties.toMutableMap()
            }

            /** X's ID of the sharing, not of the post */
            fun id(id: String) = id(JsonField.of(id))

            /** Sets [Builder.id] to an arbitrary JSON value. */
            fun id(id: JsonField<String>) = apply { this.id = id }

            /** When the post was shared */
            fun sharedAt(sharedAt: OffsetDateTime) = sharedAt(JsonField.of(sharedAt))

            /** Sets [Builder.sharedAt] to an arbitrary JSON value. */
            fun sharedAt(sharedAt: JsonField<OffsetDateTime>) = apply {
                this.sharedAt = sharedAt
            }

            /** Public X profile. */
            fun sharedBy(sharedBy: UserProfile) = sharedBy(JsonField.of(sharedBy))

            /** Sets [Builder.sharedBy] to an arbitrary JSON value. */
            fun sharedBy(sharedBy: JsonField<UserProfile>) = apply { this.sharedBy = sharedBy }

            /** Search tweet with author. Zero metrics may mean X omitted counts. */
            fun tweet(tweet: SearchTweet) = tweet(JsonField.of(tweet))

            /** Sets [Builder.tweet] to an arbitrary JSON value. */
            fun tweet(tweet: JsonField<SearchTweet>) = apply { this.tweet = tweet }

            /** When X last changed the sharing */
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
             * Returns an immutable instance of [Sharing].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): Sharing =
                Sharing(
                    id,
                    sharedAt,
                    sharedBy,
                    tweet,
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
        fun validate(): Sharing = apply {
            if (validated) {
                return@apply
            }

            id()
            sharedAt()
            sharedBy()?.validate()
            tweet()?.validate()
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
            (if (id.asKnown() == null) 0 else 1) +
                (if (sharedAt.asKnown() == null) 0 else 1) +
                (sharedBy.asKnown()?.validity() ?: 0) +
                (tweet.asKnown()?.validity() ?: 0) +
                (if (updatedAt.asKnown() == null) 0 else 1)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Sharing &&
                id == other.id &&
                sharedAt == other.sharedAt &&
                sharedBy == other.sharedBy &&
                tweet == other.tweet &&
                updatedAt == other.updatedAt &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(id, sharedAt, sharedBy, tweet, updatedAt, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Sharing{id=$id, sharedAt=$sharedAt, sharedBy=$sharedBy, tweet=$tweet, updatedAt=$updatedAt, additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is Space &&
            id == other.id &&
            admins == other.admins &&
            listeners == other.listeners &&
            speakers == other.speakers &&
            contentType == other.contentType &&
            conversationControls == other.conversationControls &&
            createdAt == other.createdAt &&
            creator == other.creator &&
            disallowJoin == other.disallowJoin &&
            endedAt == other.endedAt &&
            isAvailableForClipping == other.isAvailableForClipping &&
            isAvailableForReplay == other.isAvailableForReplay &&
            isEmployeeOnly == other.isEmployeeOnly &&
            isLocked == other.isLocked &&
            maxAdminCapacity == other.maxAdminCapacity &&
            mediaKey == other.mediaKey &&
            mentionedUserIds == other.mentionedUserIds &&
            noIncognito == other.noIncognito &&
            participantCount == other.participantCount &&
            scheduledStart == other.scheduledStart &&
            sharings == other.sharings &&
            startedAt == other.startedAt &&
            state == other.state &&
            title == other.title &&
            totalLiveListeners == other.totalLiveListeners &&
            totalReplayWatched == other.totalReplayWatched &&
            tweet == other.tweet &&
            tweetId == other.tweetId &&
            updatedAt == other.updatedAt &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            id,
            admins,
            listeners,
            speakers,
            contentType,
            conversationControls,
            createdAt,
            creator,
            disallowJoin,
            endedAt,
            isAvailableForClipping,
            isAvailableForReplay,
            isEmployeeOnly,
            isLocked,
            maxAdminCapacity,
            mediaKey,
            mentionedUserIds,
            noIncognito,
            participantCount,
            scheduledStart,
            sharings,
            startedAt,
            state,
            title,
            totalLiveListeners,
            totalReplayWatched,
            tweet,
            tweetId,
            updatedAt,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "Space{id=$id, admins=$admins, listeners=$listeners, speakers=$speakers, contentType=$contentType, conversationControls=$conversationControls, createdAt=$createdAt, creator=$creator, disallowJoin=$disallowJoin, endedAt=$endedAt, isAvailableForClipping=$isAvailableForClipping, isAvailableForReplay=$isAvailableForReplay, isEmployeeOnly=$isEmployeeOnly, isLocked=$isLocked, maxAdminCapacity=$maxAdminCapacity, mediaKey=$mediaKey, mentionedUserIds=$mentionedUserIds, noIncognito=$noIncognito, participantCount=$participantCount, scheduledStart=$scheduledStart, sharings=$sharings, startedAt=$startedAt, state=$state, title=$title, totalLiveListeners=$totalLiveListeners, totalReplayWatched=$totalReplayWatched, tweet=$tweet, tweetId=$tweetId, updatedAt=$updatedAt, additionalProperties=$additionalProperties}"
}
