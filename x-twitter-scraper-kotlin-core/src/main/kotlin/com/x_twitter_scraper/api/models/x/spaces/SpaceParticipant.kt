package com.x_twitter_scraper.api.models.x.spaces

import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.x_twitter_scraper.api.core.ExcludeMissing
import com.x_twitter_scraper.api.core.JsonField
import com.x_twitter_scraper.api.core.JsonMissing
import com.x_twitter_scraper.api.core.JsonValue
import com.x_twitter_scraper.api.core.checkRequired
import com.x_twitter_scraper.api.core.toImmutable
import com.x_twitter_scraper.api.errors.XTwitterScraperInvalidDataException
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects

/** An account on a Space's stage or in its audience, as X lists it there. */
class SpaceParticipant
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val name: JsonField<String>,
    private val username: JsonField<String>,
    private val affiliatesHighlightedLabel: JsonField<AffiliatesHighlightedLabel>,
    private val isBlueVerified: JsonField<Boolean>,
    private val isMutedByAdmin: JsonField<Boolean>,
    private val isMutedByGuest: JsonField<Boolean>,
    private val isVerified: JsonField<Boolean>,
    private val joinedAt: JsonField<OffsetDateTime>,
    private val periscopeUserId: JsonField<String>,
    private val profilePicture: JsonField<String>,
    private val profilePictureOriginal: JsonField<String>,
    private val verifiedType: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
        @JsonProperty("username") @ExcludeMissing username: JsonField<String> = JsonMissing.of(),
        @JsonProperty("affiliatesHighlightedLabel")
        @ExcludeMissing
        affiliatesHighlightedLabel: JsonField<AffiliatesHighlightedLabel> = JsonMissing.of(),
        @JsonProperty("isBlueVerified")
        @ExcludeMissing
        isBlueVerified: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("isMutedByAdmin")
        @ExcludeMissing
        isMutedByAdmin: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("isMutedByGuest")
        @ExcludeMissing
        isMutedByGuest: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("isVerified")
        @ExcludeMissing
        isVerified: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("joinedAt")
        @ExcludeMissing
        joinedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("periscopeUserId")
        @ExcludeMissing
        periscopeUserId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("profilePicture")
        @ExcludeMissing
        profilePicture: JsonField<String> = JsonMissing.of(),
        @JsonProperty("profilePictureOriginal")
        @ExcludeMissing
        profilePictureOriginal: JsonField<String> = JsonMissing.of(),
        @JsonProperty("verifiedType")
        @ExcludeMissing
        verifiedType: JsonField<String> = JsonMissing.of(),
    ) : this(
        id,
        name,
        username,
        affiliatesHighlightedLabel,
        isBlueVerified,
        isMutedByAdmin,
        isMutedByGuest,
        isVerified,
        joinedAt,
        periscopeUserId,
        profilePicture,
        profilePictureOriginal,
        verifiedType,
        mutableMapOf(),
    )

    /**
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun id(): String = id.getRequired("id")

    /**
     * Name the account shows in the Space
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun name(): String = name.getRequired("name")

    /**
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun username(): String = username.getRequired("username")

    /**
     * Organization affiliation label shown on an X profile.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun affiliatesHighlightedLabel(): AffiliatesHighlightedLabel? =
        affiliatesHighlightedLabel.getNullable("affiliatesHighlightedLabel")

    /**
     * Whether the account has X Premium verification
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun isBlueVerified(): Boolean? = isBlueVerified.getNullable("isBlueVerified")

    /**
     * Whether a host muted the account
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun isMutedByAdmin(): Boolean? = isMutedByAdmin.getNullable("isMutedByAdmin")

    /**
     * Whether the account muted itself
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun isMutedByGuest(): Boolean? = isMutedByGuest.getNullable("isMutedByGuest")

    /**
     * Whether X shows a verified badge in the Space
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun isVerified(): Boolean? = isVerified.getNullable("isVerified")

    /**
     * When the account joined the Space
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun joinedAt(): OffsetDateTime? = joinedAt.getNullable("joinedAt")

    /**
     * The account's ID in X's broadcast service
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun periscopeUserId(): String? = periscopeUserId.getNullable("periscopeUserId")

    /**
     * Profile picture URL
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun profilePicture(): String? = profilePicture.getNullable("profilePicture")

    /**
     * The participant's profile picture at full size
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun profilePictureOriginal(): String? =
        profilePictureOriginal.getNullable("profilePictureOriginal")

    /**
     * Business or Government badge type
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun verifiedType(): String? = verifiedType.getNullable("verifiedType")

    /**
     * Returns the raw JSON value of [id].
     *
     * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    /**
     * Returns the raw JSON value of [name].
     *
     * Unlike [name], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<String> = name

    /**
     * Returns the raw JSON value of [username].
     *
     * Unlike [username], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("username") @ExcludeMissing fun _username(): JsonField<String> = username

    /**
     * Returns the raw JSON value of [affiliatesHighlightedLabel].
     *
     * Unlike [affiliatesHighlightedLabel], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("affiliatesHighlightedLabel")
    @ExcludeMissing
    fun _affiliatesHighlightedLabel(): JsonField<AffiliatesHighlightedLabel> =
        affiliatesHighlightedLabel

    /**
     * Returns the raw JSON value of [isBlueVerified].
     *
     * Unlike [isBlueVerified], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("isBlueVerified")
    @ExcludeMissing
    fun _isBlueVerified(): JsonField<Boolean> = isBlueVerified

    /**
     * Returns the raw JSON value of [isMutedByAdmin].
     *
     * Unlike [isMutedByAdmin], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("isMutedByAdmin")
    @ExcludeMissing
    fun _isMutedByAdmin(): JsonField<Boolean> = isMutedByAdmin

    /**
     * Returns the raw JSON value of [isMutedByGuest].
     *
     * Unlike [isMutedByGuest], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("isMutedByGuest")
    @ExcludeMissing
    fun _isMutedByGuest(): JsonField<Boolean> = isMutedByGuest

    /**
     * Returns the raw JSON value of [isVerified].
     *
     * Unlike [isVerified], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("isVerified") @ExcludeMissing fun _isVerified(): JsonField<Boolean> = isVerified

    /**
     * Returns the raw JSON value of [joinedAt].
     *
     * Unlike [joinedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("joinedAt") @ExcludeMissing fun _joinedAt(): JsonField<OffsetDateTime> = joinedAt

    /**
     * Returns the raw JSON value of [periscopeUserId].
     *
     * Unlike [periscopeUserId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("periscopeUserId")
    @ExcludeMissing
    fun _periscopeUserId(): JsonField<String> = periscopeUserId

    /**
     * Returns the raw JSON value of [profilePicture].
     *
     * Unlike [profilePicture], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("profilePicture")
    @ExcludeMissing
    fun _profilePicture(): JsonField<String> = profilePicture

    /**
     * Returns the raw JSON value of [profilePictureOriginal].
     *
     * Unlike [profilePictureOriginal], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("profilePictureOriginal")
    @ExcludeMissing
    fun _profilePictureOriginal(): JsonField<String> = profilePictureOriginal

    /**
     * Returns the raw JSON value of [verifiedType].
     *
     * Unlike [verifiedType], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("verifiedType")
    @ExcludeMissing
    fun _verifiedType(): JsonField<String> = verifiedType

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
         * Returns a mutable builder for constructing an instance of [SpaceParticipant].
         *
         * The following fields are required:
         * ```kotlin
         * .id()
         * .name()
         * .username()
         * ```
         */
        fun builder() = Builder()
    }

    /** A builder for [SpaceParticipant]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var name: JsonField<String>? = null
        private var username: JsonField<String>? = null
        private var affiliatesHighlightedLabel: JsonField<AffiliatesHighlightedLabel> =
            JsonMissing.of()
        private var isBlueVerified: JsonField<Boolean> = JsonMissing.of()
        private var isMutedByAdmin: JsonField<Boolean> = JsonMissing.of()
        private var isMutedByGuest: JsonField<Boolean> = JsonMissing.of()
        private var isVerified: JsonField<Boolean> = JsonMissing.of()
        private var joinedAt: JsonField<OffsetDateTime> = JsonMissing.of()
        private var periscopeUserId: JsonField<String> = JsonMissing.of()
        private var profilePicture: JsonField<String> = JsonMissing.of()
        private var profilePictureOriginal: JsonField<String> = JsonMissing.of()
        private var verifiedType: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        internal fun from(spaceParticipant: SpaceParticipant) = apply {
            id = spaceParticipant.id
            name = spaceParticipant.name
            username = spaceParticipant.username
            affiliatesHighlightedLabel = spaceParticipant.affiliatesHighlightedLabel
            isBlueVerified = spaceParticipant.isBlueVerified
            isMutedByAdmin = spaceParticipant.isMutedByAdmin
            isMutedByGuest = spaceParticipant.isMutedByGuest
            isVerified = spaceParticipant.isVerified
            joinedAt = spaceParticipant.joinedAt
            periscopeUserId = spaceParticipant.periscopeUserId
            profilePicture = spaceParticipant.profilePicture
            profilePictureOriginal = spaceParticipant.profilePictureOriginal
            verifiedType = spaceParticipant.verifiedType
            additionalProperties = spaceParticipant.additionalProperties.toMutableMap()
        }

        fun id(id: String) = id(JsonField.of(id))

        /** Sets [Builder.id] to an arbitrary JSON value. */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /** Name the account shows in the Space */
        fun name(name: String) = name(JsonField.of(name))

        /** Sets [Builder.name] to an arbitrary JSON value. */
        fun name(name: JsonField<String>) = apply { this.name = name }

        fun username(username: String) = username(JsonField.of(username))

        /** Sets [Builder.username] to an arbitrary JSON value. */
        fun username(username: JsonField<String>) = apply { this.username = username }

        /** Organization affiliation label shown on an X profile. */
        fun affiliatesHighlightedLabel(affiliatesHighlightedLabel: AffiliatesHighlightedLabel) =
            affiliatesHighlightedLabel(JsonField.of(affiliatesHighlightedLabel))

        /** Sets [Builder.affiliatesHighlightedLabel] to an arbitrary JSON value. */
        fun affiliatesHighlightedLabel(
            affiliatesHighlightedLabel: JsonField<AffiliatesHighlightedLabel>
        ) = apply { this.affiliatesHighlightedLabel = affiliatesHighlightedLabel }

        /** Whether the account has X Premium verification */
        fun isBlueVerified(isBlueVerified: Boolean) = isBlueVerified(JsonField.of(isBlueVerified))

        /** Sets [Builder.isBlueVerified] to an arbitrary JSON value. */
        fun isBlueVerified(isBlueVerified: JsonField<Boolean>) = apply {
            this.isBlueVerified = isBlueVerified
        }

        /** Whether a host muted the account */
        fun isMutedByAdmin(isMutedByAdmin: Boolean) = isMutedByAdmin(JsonField.of(isMutedByAdmin))

        /** Sets [Builder.isMutedByAdmin] to an arbitrary JSON value. */
        fun isMutedByAdmin(isMutedByAdmin: JsonField<Boolean>) = apply {
            this.isMutedByAdmin = isMutedByAdmin
        }

        /** Whether the account muted itself */
        fun isMutedByGuest(isMutedByGuest: Boolean) = isMutedByGuest(JsonField.of(isMutedByGuest))

        /** Sets [Builder.isMutedByGuest] to an arbitrary JSON value. */
        fun isMutedByGuest(isMutedByGuest: JsonField<Boolean>) = apply {
            this.isMutedByGuest = isMutedByGuest
        }

        /** Whether X shows a verified badge in the Space */
        fun isVerified(isVerified: Boolean) = isVerified(JsonField.of(isVerified))

        /** Sets [Builder.isVerified] to an arbitrary JSON value. */
        fun isVerified(isVerified: JsonField<Boolean>) = apply {
            this.isVerified = isVerified
        }

        /** When the account joined the Space */
        fun joinedAt(joinedAt: OffsetDateTime) = joinedAt(JsonField.of(joinedAt))

        /** Sets [Builder.joinedAt] to an arbitrary JSON value. */
        fun joinedAt(joinedAt: JsonField<OffsetDateTime>) = apply {
            this.joinedAt = joinedAt
        }

        /** The account's ID in X's broadcast service */
        fun periscopeUserId(periscopeUserId: String) =
            periscopeUserId(JsonField.of(periscopeUserId))

        /** Sets [Builder.periscopeUserId] to an arbitrary JSON value. */
        fun periscopeUserId(periscopeUserId: JsonField<String>) = apply {
            this.periscopeUserId = periscopeUserId
        }

        /** Profile picture URL */
        fun profilePicture(profilePicture: String) = profilePicture(JsonField.of(profilePicture))

        /** Sets [Builder.profilePicture] to an arbitrary JSON value. */
        fun profilePicture(profilePicture: JsonField<String>) = apply {
            this.profilePicture = profilePicture
        }

        /** The participant's profile picture at full size */
        fun profilePictureOriginal(profilePictureOriginal: String) =
            profilePictureOriginal(JsonField.of(profilePictureOriginal))

        /** Sets [Builder.profilePictureOriginal] to an arbitrary JSON value. */
        fun profilePictureOriginal(profilePictureOriginal: JsonField<String>) = apply {
            this.profilePictureOriginal = profilePictureOriginal
        }

        /** Business or Government badge type */
        fun verifiedType(verifiedType: String) = verifiedType(JsonField.of(verifiedType))

        /** Sets [Builder.verifiedType] to an arbitrary JSON value. */
        fun verifiedType(verifiedType: JsonField<String>) = apply {
            this.verifiedType = verifiedType
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
         * Returns an immutable instance of [SpaceParticipant].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```kotlin
         * .id()
         * .name()
         * .username()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): SpaceParticipant =
            SpaceParticipant(
                checkRequired("id", id),
                checkRequired("name", name),
                checkRequired("username", username),
                affiliatesHighlightedLabel,
                isBlueVerified,
                isMutedByAdmin,
                isMutedByGuest,
                isVerified,
                joinedAt,
                periscopeUserId,
                profilePicture,
                profilePictureOriginal,
                verifiedType,
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
    fun validate(): SpaceParticipant = apply {
        if (validated) {
            return@apply
        }

        id()
        name()
        username()
        affiliatesHighlightedLabel()?.validate()
        isBlueVerified()
        isMutedByAdmin()
        isMutedByGuest()
        isVerified()
        joinedAt()
        periscopeUserId()
        profilePicture()
        profilePictureOriginal()
        verifiedType()
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
            (if (name.asKnown() == null) 0 else 1) +
            (if (username.asKnown() == null) 0 else 1) +
            (affiliatesHighlightedLabel.asKnown()?.validity() ?: 0) +
            (if (isBlueVerified.asKnown() == null) 0 else 1) +
            (if (isMutedByAdmin.asKnown() == null) 0 else 1) +
            (if (isMutedByGuest.asKnown() == null) 0 else 1) +
            (if (isVerified.asKnown() == null) 0 else 1) +
            (if (joinedAt.asKnown() == null) 0 else 1) +
            (if (periscopeUserId.asKnown() == null) 0 else 1) +
            (if (profilePicture.asKnown() == null) 0 else 1) +
            (if (profilePictureOriginal.asKnown() == null) 0 else 1) +
            (if (verifiedType.asKnown() == null) 0 else 1)

    /** Organization affiliation label shown on an X profile. */
    class AffiliatesHighlightedLabel
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val badgeUrl: JsonField<String>,
        private val description: JsonField<String>,
        private val longDescription: JsonField<LongDescription>,
        private val url: JsonField<String>,
        private val urlType: JsonField<String>,
        private val userLabelDisplayType: JsonField<String>,
        private val userLabelType: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("badgeUrl")
            @ExcludeMissing
            badgeUrl: JsonField<String> = JsonMissing.of(),
            @JsonProperty("description")
            @ExcludeMissing
            description: JsonField<String> = JsonMissing.of(),
            @JsonProperty("longDescription")
            @ExcludeMissing
            longDescription: JsonField<LongDescription> = JsonMissing.of(),
            @JsonProperty("url") @ExcludeMissing url: JsonField<String> = JsonMissing.of(),
            @JsonProperty("urlType") @ExcludeMissing urlType: JsonField<String> = JsonMissing.of(),
            @JsonProperty("userLabelDisplayType")
            @ExcludeMissing
            userLabelDisplayType: JsonField<String> = JsonMissing.of(),
            @JsonProperty("userLabelType")
            @ExcludeMissing
            userLabelType: JsonField<String> = JsonMissing.of(),
        ) : this(
            badgeUrl,
            description,
            longDescription,
            url,
            urlType,
            userLabelDisplayType,
            userLabelType,
            mutableMapOf(),
        )

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun badgeUrl(): String? = badgeUrl.getNullable("badgeUrl")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun description(): String? = description.getNullable("description")

        /**
         * Public text, ranges, references, and mention data.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun longDescription(): LongDescription? = longDescription.getNullable("longDescription")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun url(): String? = url.getNullable("url")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun urlType(): String? = urlType.getNullable("urlType")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun userLabelDisplayType(): String? =
            userLabelDisplayType.getNullable("userLabelDisplayType")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun userLabelType(): String? = userLabelType.getNullable("userLabelType")

        /**
         * Returns the raw JSON value of [badgeUrl].
         *
         * Unlike [badgeUrl], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("badgeUrl") @ExcludeMissing fun _badgeUrl(): JsonField<String> = badgeUrl

        /**
         * Returns the raw JSON value of [description].
         *
         * Unlike [description], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("description")
        @ExcludeMissing
        fun _description(): JsonField<String> = description

        /**
         * Returns the raw JSON value of [longDescription].
         *
         * Unlike [longDescription], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("longDescription")
        @ExcludeMissing
        fun _longDescription(): JsonField<LongDescription> = longDescription

        /**
         * Returns the raw JSON value of [url].
         *
         * Unlike [url], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("url") @ExcludeMissing fun _url(): JsonField<String> = url

        /**
         * Returns the raw JSON value of [urlType].
         *
         * Unlike [urlType], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("urlType") @ExcludeMissing fun _urlType(): JsonField<String> = urlType

        /**
         * Returns the raw JSON value of [userLabelDisplayType].
         *
         * Unlike [userLabelDisplayType], this method doesn't throw if the JSON field has an
         * unexpected type.
         */
        @JsonProperty("userLabelDisplayType")
        @ExcludeMissing
        fun _userLabelDisplayType(): JsonField<String> = userLabelDisplayType

        /**
         * Returns the raw JSON value of [userLabelType].
         *
         * Unlike [userLabelType], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("userLabelType")
        @ExcludeMissing
        fun _userLabelType(): JsonField<String> = userLabelType

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
             * [AffiliatesHighlightedLabel].
             */
            fun builder() = Builder()
        }

        /** A builder for [AffiliatesHighlightedLabel]. */
        class Builder internal constructor() {

            private var badgeUrl: JsonField<String> = JsonMissing.of()
            private var description: JsonField<String> = JsonMissing.of()
            private var longDescription: JsonField<LongDescription> = JsonMissing.of()
            private var url: JsonField<String> = JsonMissing.of()
            private var urlType: JsonField<String> = JsonMissing.of()
            private var userLabelDisplayType: JsonField<String> = JsonMissing.of()
            private var userLabelType: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(affiliatesHighlightedLabel: AffiliatesHighlightedLabel) = apply {
                badgeUrl = affiliatesHighlightedLabel.badgeUrl
                description = affiliatesHighlightedLabel.description
                longDescription = affiliatesHighlightedLabel.longDescription
                url = affiliatesHighlightedLabel.url
                urlType = affiliatesHighlightedLabel.urlType
                userLabelDisplayType = affiliatesHighlightedLabel.userLabelDisplayType
                userLabelType = affiliatesHighlightedLabel.userLabelType
                additionalProperties =
                    affiliatesHighlightedLabel.additionalProperties.toMutableMap()
            }

            fun badgeUrl(badgeUrl: String) = badgeUrl(JsonField.of(badgeUrl))

            /** Sets [Builder.badgeUrl] to an arbitrary JSON value. */
            fun badgeUrl(badgeUrl: JsonField<String>) = apply { this.badgeUrl = badgeUrl }

            fun description(description: String) = description(JsonField.of(description))

            /** Sets [Builder.description] to an arbitrary JSON value. */
            fun description(description: JsonField<String>) = apply {
                this.description = description
            }

            /** Public text, ranges, references, and mention data. */
            fun longDescription(longDescription: LongDescription) =
                longDescription(JsonField.of(longDescription))

            /** Sets [Builder.longDescription] to an arbitrary JSON value. */
            fun longDescription(longDescription: JsonField<LongDescription>) = apply {
                this.longDescription = longDescription
            }

            fun url(url: String) = url(JsonField.of(url))

            /** Sets [Builder.url] to an arbitrary JSON value. */
            fun url(url: JsonField<String>) = apply { this.url = url }

            fun urlType(urlType: String) = urlType(JsonField.of(urlType))

            /** Sets [Builder.urlType] to an arbitrary JSON value. */
            fun urlType(urlType: JsonField<String>) = apply { this.urlType = urlType }

            fun userLabelDisplayType(userLabelDisplayType: String) =
                userLabelDisplayType(JsonField.of(userLabelDisplayType))

            /** Sets [Builder.userLabelDisplayType] to an arbitrary JSON value. */
            fun userLabelDisplayType(userLabelDisplayType: JsonField<String>) = apply {
                this.userLabelDisplayType = userLabelDisplayType
            }

            fun userLabelType(userLabelType: String) = userLabelType(JsonField.of(userLabelType))

            /** Sets [Builder.userLabelType] to an arbitrary JSON value. */
            fun userLabelType(userLabelType: JsonField<String>) = apply {
                this.userLabelType = userLabelType
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
             * Returns an immutable instance of [AffiliatesHighlightedLabel].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): AffiliatesHighlightedLabel =
                AffiliatesHighlightedLabel(
                    badgeUrl,
                    description,
                    longDescription,
                    url,
                    urlType,
                    userLabelDisplayType,
                    userLabelType,
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
        fun validate(): AffiliatesHighlightedLabel = apply {
            if (validated) {
                return@apply
            }

            badgeUrl()
            description()
            longDescription()?.validate()
            url()
            urlType()
            userLabelDisplayType()
            userLabelType()
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
            (if (badgeUrl.asKnown() == null) 0 else 1) +
                (if (description.asKnown() == null) 0 else 1) +
                (longDescription.asKnown()?.validity() ?: 0) +
                (if (url.asKnown() == null) 0 else 1) +
                (if (urlType.asKnown() == null) 0 else 1) +
                (if (userLabelDisplayType.asKnown() == null) 0 else 1) +
                (if (userLabelType.asKnown() == null) 0 else 1)

        /** Public metadata whose fields X defines. */
        class LongDescription
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

                /** Returns a mutable builder for constructing an instance of [LongDescription]. */
                fun builder() = Builder()
            }

            /** A builder for [LongDescription]. */
            class Builder internal constructor() {

                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                internal fun from(longDescription: LongDescription) = apply {
                    additionalProperties = longDescription.additionalProperties.toMutableMap()
                }

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
                 * Returns an immutable instance of [LongDescription].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 */
                fun build(): LongDescription = LongDescription(additionalProperties.toImmutable())
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
            fun validate(): LongDescription = apply {
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

                return other is LongDescription &&
                    additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy { Objects.hash(additionalProperties) }

            override fun hashCode(): Int = hashCode

            override fun toString() = "LongDescription{additionalProperties=$additionalProperties}"
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is AffiliatesHighlightedLabel &&
                badgeUrl == other.badgeUrl &&
                description == other.description &&
                longDescription == other.longDescription &&
                url == other.url &&
                urlType == other.urlType &&
                userLabelDisplayType == other.userLabelDisplayType &&
                userLabelType == other.userLabelType &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(
                badgeUrl,
                description,
                longDescription,
                url,
                urlType,
                userLabelDisplayType,
                userLabelType,
                additionalProperties,
            )
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "AffiliatesHighlightedLabel{badgeUrl=$badgeUrl, description=$description, longDescription=$longDescription, url=$url, urlType=$urlType, userLabelDisplayType=$userLabelDisplayType, userLabelType=$userLabelType, additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is SpaceParticipant &&
            id == other.id &&
            name == other.name &&
            username == other.username &&
            affiliatesHighlightedLabel == other.affiliatesHighlightedLabel &&
            isBlueVerified == other.isBlueVerified &&
            isMutedByAdmin == other.isMutedByAdmin &&
            isMutedByGuest == other.isMutedByGuest &&
            isVerified == other.isVerified &&
            joinedAt == other.joinedAt &&
            periscopeUserId == other.periscopeUserId &&
            profilePicture == other.profilePicture &&
            profilePictureOriginal == other.profilePictureOriginal &&
            verifiedType == other.verifiedType &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            id,
            name,
            username,
            affiliatesHighlightedLabel,
            isBlueVerified,
            isMutedByAdmin,
            isMutedByGuest,
            isVerified,
            joinedAt,
            periscopeUserId,
            profilePicture,
            profilePictureOriginal,
            verifiedType,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "SpaceParticipant{id=$id, name=$name, username=$username, affiliatesHighlightedLabel=$affiliatesHighlightedLabel, isBlueVerified=$isBlueVerified, isMutedByAdmin=$isMutedByAdmin, isMutedByGuest=$isMutedByGuest, isVerified=$isVerified, joinedAt=$joinedAt, periscopeUserId=$periscopeUserId, profilePicture=$profilePicture, profilePictureOriginal=$profilePictureOriginal, verifiedType=$verifiedType, additionalProperties=$additionalProperties}"
}
