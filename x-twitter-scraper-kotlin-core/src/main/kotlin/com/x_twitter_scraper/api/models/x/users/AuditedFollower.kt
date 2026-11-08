// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.users

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
import java.util.Collections
import java.util.Objects

/** 1 follower the audit read, with its class, score & signals. */
class AuditedFollower
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val class_: JsonField<FollowerClass>,
    private val followers: JsonField<Long>,
    private val following: JsonField<Long>,
    private val id: JsonField<String>,
    private val joinedAtMs: JsonField<Long>,
    private val likes: JsonField<Long>,
    private val name: JsonField<String>,
    private val owner: JsonField<String>,
    private val pictureUrl: JsonField<String>,
    private val position: JsonField<Long>,
    private val posts: JsonField<Long>,
    private val score: JsonField<Long>,
    private val signals: JsonField<List<String>>,
    private val userName: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("class") @ExcludeMissing class_: JsonField<FollowerClass> = JsonMissing.of(),
        @JsonProperty("followers") @ExcludeMissing followers: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("following") @ExcludeMissing following: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("joinedAtMs") @ExcludeMissing joinedAtMs: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("likes") @ExcludeMissing likes: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
        @JsonProperty("owner") @ExcludeMissing owner: JsonField<String> = JsonMissing.of(),
        @JsonProperty("pictureUrl")
        @ExcludeMissing
        pictureUrl: JsonField<String> = JsonMissing.of(),
        @JsonProperty("position") @ExcludeMissing position: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("posts") @ExcludeMissing posts: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("score") @ExcludeMissing score: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("signals")
        @ExcludeMissing
        signals: JsonField<List<String>> = JsonMissing.of(),
        @JsonProperty("userName") @ExcludeMissing userName: JsonField<String> = JsonMissing.of(),
    ) : this(
        class_,
        followers,
        following,
        id,
        joinedAtMs,
        likes,
        name,
        owner,
        pictureUrl,
        position,
        posts,
        score,
        signals,
        userName,
        mutableMapOf(),
    )

    fun class_(): FollowerClass = class_.getRequired("class")

    fun followers(): Long = followers.getRequired("followers")

    fun following(): Long = following.getRequired("following")

    fun id(): String = id.getRequired("id")

    fun joinedAtMs(): Long? = joinedAtMs.getNullable("joinedAtMs")

    fun likes(): Long = likes.getRequired("likes")

    fun name(): String = name.getRequired("name")

    /** Account that runs an automated follower, when X names it. */
    fun owner(): String? = owner.getNullable("owner")

    fun pictureUrl(): String? = pictureUrl.getNullable("pictureUrl")

    fun position(): Long = position.getRequired("position")

    fun posts(): Long = posts.getRequired("posts")

    fun score(): Long = score.getRequired("score")

    fun signals(): List<String> = signals.getRequired("signals")

    fun userName(): String = userName.getRequired("userName")

    /** Returns the raw JSON value of [class_]. */
    @JsonProperty("class") @ExcludeMissing fun _class_(): JsonField<FollowerClass> = class_

    /** Returns the raw JSON value of [followers]. */
    @JsonProperty("followers") @ExcludeMissing fun _followers(): JsonField<Long> = followers

    /** Returns the raw JSON value of [following]. */
    @JsonProperty("following") @ExcludeMissing fun _following(): JsonField<Long> = following

    /** Returns the raw JSON value of [id]. */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    /** Returns the raw JSON value of [joinedAtMs]. */
    @JsonProperty("joinedAtMs") @ExcludeMissing fun _joinedAtMs(): JsonField<Long> = joinedAtMs

    /** Returns the raw JSON value of [likes]. */
    @JsonProperty("likes") @ExcludeMissing fun _likes(): JsonField<Long> = likes

    /** Returns the raw JSON value of [name]. */
    @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<String> = name

    /** Returns the raw JSON value of [owner]. */
    @JsonProperty("owner") @ExcludeMissing fun _owner(): JsonField<String> = owner

    /** Returns the raw JSON value of [pictureUrl]. */
    @JsonProperty("pictureUrl") @ExcludeMissing fun _pictureUrl(): JsonField<String> = pictureUrl

    /** Returns the raw JSON value of [position]. */
    @JsonProperty("position") @ExcludeMissing fun _position(): JsonField<Long> = position

    /** Returns the raw JSON value of [posts]. */
    @JsonProperty("posts") @ExcludeMissing fun _posts(): JsonField<Long> = posts

    /** Returns the raw JSON value of [score]. */
    @JsonProperty("score") @ExcludeMissing fun _score(): JsonField<Long> = score

    /** Returns the raw JSON value of [signals]. */
    @JsonProperty("signals") @ExcludeMissing fun _signals(): JsonField<List<String>> = signals

    /** Returns the raw JSON value of [userName]. */
    @JsonProperty("userName") @ExcludeMissing fun _userName(): JsonField<String> = userName

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
         * Returns a mutable builder for constructing an instance of [AuditedFollower].
         *
         * The following fields are required:
         * ```kotlin
         * .class_()
         * .followers()
         * .following()
         * .id()
         * .likes()
         * .name()
         * .position()
         * .posts()
         * .score()
         * .signals()
         * .userName()
         * ```
         */
        fun builder() = Builder()
    }

    /** A builder for [AuditedFollower]. */
    class Builder internal constructor() {

        private var class_: JsonField<FollowerClass>? = null
        private var followers: JsonField<Long>? = null
        private var following: JsonField<Long>? = null
        private var id: JsonField<String>? = null
        private var joinedAtMs: JsonField<Long> = JsonMissing.of()
        private var likes: JsonField<Long>? = null
        private var name: JsonField<String>? = null
        private var owner: JsonField<String> = JsonMissing.of()
        private var pictureUrl: JsonField<String> = JsonMissing.of()
        private var position: JsonField<Long>? = null
        private var posts: JsonField<Long>? = null
        private var score: JsonField<Long>? = null
        private var signals: JsonField<MutableList<String>>? = null
        private var userName: JsonField<String>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        internal fun from(auditedFollower: AuditedFollower) = apply {
            class_ = auditedFollower.class_
            followers = auditedFollower.followers
            following = auditedFollower.following
            id = auditedFollower.id
            joinedAtMs = auditedFollower.joinedAtMs
            likes = auditedFollower.likes
            name = auditedFollower.name
            owner = auditedFollower.owner
            pictureUrl = auditedFollower.pictureUrl
            position = auditedFollower.position
            posts = auditedFollower.posts
            score = auditedFollower.score
            signals = auditedFollower.signals.map { it.toMutableList() }
            userName = auditedFollower.userName
            additionalProperties = auditedFollower.additionalProperties.toMutableMap()
        }

        fun class_(class_: FollowerClass) = class_(JsonField.of(class_))

        /** Sets [Builder.class_] to an arbitrary JSON value. */
        fun class_(class_: JsonField<FollowerClass>) = apply { this.class_ = class_ }

        fun followers(followers: Long) = followers(JsonField.of(followers))

        /** Sets [Builder.followers] to an arbitrary JSON value. */
        fun followers(followers: JsonField<Long>) = apply { this.followers = followers }

        fun following(following: Long) = following(JsonField.of(following))

        /** Sets [Builder.following] to an arbitrary JSON value. */
        fun following(following: JsonField<Long>) = apply { this.following = following }

        fun id(id: String) = id(JsonField.of(id))

        /** Sets [Builder.id] to an arbitrary JSON value. */
        fun id(id: JsonField<String>) = apply { this.id = id }

        fun joinedAtMs(joinedAtMs: Long) = joinedAtMs(JsonField.of(joinedAtMs))

        /** Sets [Builder.joinedAtMs] to an arbitrary JSON value. */
        fun joinedAtMs(joinedAtMs: JsonField<Long>) = apply { this.joinedAtMs = joinedAtMs }

        fun likes(likes: Long) = likes(JsonField.of(likes))

        /** Sets [Builder.likes] to an arbitrary JSON value. */
        fun likes(likes: JsonField<Long>) = apply { this.likes = likes }

        fun name(name: String) = name(JsonField.of(name))

        /** Sets [Builder.name] to an arbitrary JSON value. */
        fun name(name: JsonField<String>) = apply { this.name = name }

        /** Account that runs an automated follower, when X names it. */
        fun owner(owner: String) = owner(JsonField.of(owner))

        /** Sets [Builder.owner] to an arbitrary JSON value. */
        fun owner(owner: JsonField<String>) = apply { this.owner = owner }

        fun pictureUrl(pictureUrl: String) = pictureUrl(JsonField.of(pictureUrl))

        /** Sets [Builder.pictureUrl] to an arbitrary JSON value. */
        fun pictureUrl(pictureUrl: JsonField<String>) = apply { this.pictureUrl = pictureUrl }

        fun position(position: Long) = position(JsonField.of(position))

        /** Sets [Builder.position] to an arbitrary JSON value. */
        fun position(position: JsonField<Long>) = apply { this.position = position }

        fun posts(posts: Long) = posts(JsonField.of(posts))

        /** Sets [Builder.posts] to an arbitrary JSON value. */
        fun posts(posts: JsonField<Long>) = apply { this.posts = posts }

        fun score(score: Long) = score(JsonField.of(score))

        /** Sets [Builder.score] to an arbitrary JSON value. */
        fun score(score: JsonField<Long>) = apply { this.score = score }

        fun signals(signals: List<String>) = signals(JsonField.of(signals))

        /** Sets [Builder.signals] to an arbitrary JSON value. */
        fun signals(signals: JsonField<List<String>>) = apply {
            this.signals = signals.map { it.toMutableList() }
        }

        /** Adds a single [String] to [signals]. */
        fun addSignal(signal: String) = apply {
            signals =
                (signals ?: JsonField.of(mutableListOf())).also {
                    checkKnown("signals", it).add(signal)
                }
        }

        fun userName(userName: String) = userName(JsonField.of(userName))

        /** Sets [Builder.userName] to an arbitrary JSON value. */
        fun userName(userName: JsonField<String>) = apply { this.userName = userName }

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
         * Returns an immutable instance of [AuditedFollower].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```kotlin
         * .class_()
         * .followers()
         * .following()
         * .id()
         * .likes()
         * .name()
         * .position()
         * .posts()
         * .score()
         * .signals()
         * .userName()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): AuditedFollower =
            AuditedFollower(
                checkRequired("class_", class_),
                checkRequired("followers", followers),
                checkRequired("following", following),
                checkRequired("id", id),
                joinedAtMs,
                checkRequired("likes", likes),
                checkRequired("name", name),
                owner,
                pictureUrl,
                checkRequired("position", position),
                checkRequired("posts", posts),
                checkRequired("score", score),
                checkRequired("signals", signals).map { it.toImmutable() },
                checkRequired("userName", userName),
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    /**
     * Validates that the types of all values in this object match their expected types recursively.
     *
     * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't match
     *   its expected type.
     */
    fun validate(): AuditedFollower = apply {
        if (validated) {
            return@apply
        }

        class_().validate()
        followers()
        following()
        id()
        joinedAtMs()
        likes()
        name()
        owner()
        pictureUrl()
        position()
        posts()
        score()
        signals()
        userName()
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
        (class_.asKnown()?.validity() ?: 0) +
            (if (followers.asKnown() == null) 0 else 1) +
            (if (following.asKnown() == null) 0 else 1) +
            (if (id.asKnown() == null) 0 else 1) +
            (if (joinedAtMs.asKnown() == null) 0 else 1) +
            (if (likes.asKnown() == null) 0 else 1) +
            (if (name.asKnown() == null) 0 else 1) +
            (if (owner.asKnown() == null) 0 else 1) +
            (if (pictureUrl.asKnown() == null) 0 else 1) +
            (if (position.asKnown() == null) 0 else 1) +
            (if (posts.asKnown() == null) 0 else 1) +
            (if (score.asKnown() == null) 0 else 1) +
            (signals.asKnown()?.size ?: 0) +
            (if (userName.asKnown() == null) 0 else 1)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is AuditedFollower &&
            class_ == other.class_ &&
            followers == other.followers &&
            following == other.following &&
            id == other.id &&
            joinedAtMs == other.joinedAtMs &&
            likes == other.likes &&
            name == other.name &&
            owner == other.owner &&
            pictureUrl == other.pictureUrl &&
            position == other.position &&
            posts == other.posts &&
            score == other.score &&
            signals == other.signals &&
            userName == other.userName &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            class_,
            followers,
            following,
            id,
            joinedAtMs,
            likes,
            name,
            owner,
            pictureUrl,
            position,
            posts,
            score,
            signals,
            userName,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "AuditedFollower{class_=$class_, followers=$followers, following=$following, id=$id, joinedAtMs=$joinedAtMs, likes=$likes, name=$name, owner=$owner, pictureUrl=$pictureUrl, position=$position, posts=$posts, score=$score, signals=$signals, userName=$userName, additionalProperties=$additionalProperties}"
}
