// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.users

import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.x_twitter_scraper.api.core.Enum
import com.x_twitter_scraper.api.core.ExcludeMissing
import com.x_twitter_scraper.api.core.JsonField
import com.x_twitter_scraper.api.core.JsonMissing
import com.x_twitter_scraper.api.core.JsonValue
import com.x_twitter_scraper.api.core.checkKnown
import com.x_twitter_scraper.api.core.checkRequired
import com.x_twitter_scraper.api.core.toImmutable
import com.x_twitter_scraper.api.errors.XTwitterScraperInvalidDataException
import com.x_twitter_scraper.api.models.x.users.UserRetrieveInsightsResponse.Insights
import java.util.Collections
import java.util.Objects

/**
 * Report on the followers read. Classes rest on public profile signals, so they say what a profile
 * looks like, never who runs it.
 */
class FollowerAudit
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val account: JsonField<Insights>,
    private val coverage: JsonField<Coverage>,
    private val quality: JsonField<Quality>,
    private val classes: JsonField<List<Class>>,
    private val signals: JsonField<List<Signal>>,
    private val arrivalBands: JsonField<List<ArrivalBand>>,
    private val cohorts: JsonField<List<Cohort>>,
    private val languages: JsonField<List<FollowerValueCount>>,
    private val places: JsonField<List<FollowerValueCount>>,
    private val verified: JsonField<Verified>,
    private val followers: JsonField<List<AuditedFollower>>,
    private val topFollowers: JsonField<List<AuditedFollower>>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("account") @ExcludeMissing account: JsonField<Insights> = JsonMissing.of(),
        @JsonProperty("coverage") @ExcludeMissing coverage: JsonField<Coverage> = JsonMissing.of(),
        @JsonProperty("quality") @ExcludeMissing quality: JsonField<Quality> = JsonMissing.of(),
        @JsonProperty("classes") @ExcludeMissing classes: JsonField<List<Class>> = JsonMissing.of(),
        @JsonProperty("signals")
        @ExcludeMissing
        signals: JsonField<List<Signal>> = JsonMissing.of(),
        @JsonProperty("arrivalBands")
        @ExcludeMissing
        arrivalBands: JsonField<List<ArrivalBand>> = JsonMissing.of(),
        @JsonProperty("cohorts")
        @ExcludeMissing
        cohorts: JsonField<List<Cohort>> = JsonMissing.of(),
        @JsonProperty("languages")
        @ExcludeMissing
        languages: JsonField<List<FollowerValueCount>> = JsonMissing.of(),
        @JsonProperty("places")
        @ExcludeMissing
        places: JsonField<List<FollowerValueCount>> = JsonMissing.of(),
        @JsonProperty("verified") @ExcludeMissing verified: JsonField<Verified> = JsonMissing.of(),
        @JsonProperty("followers")
        @ExcludeMissing
        followers: JsonField<List<AuditedFollower>> = JsonMissing.of(),
        @JsonProperty("topFollowers")
        @ExcludeMissing
        topFollowers: JsonField<List<AuditedFollower>> = JsonMissing.of(),
    ) : this(
        account,
        coverage,
        quality,
        classes,
        signals,
        arrivalBands,
        cohorts,
        languages,
        places,
        verified,
        followers,
        topFollowers,
        mutableMapOf(),
    )

    /** Engagement insights of the audited account. */
    fun account(): Insights = account.getRequired("account")

    fun coverage(): Coverage = coverage.getRequired("coverage")

    fun quality(): Quality = quality.getRequired("quality")

    fun classes(): List<Class> = classes.getRequired("classes")

    fun signals(): List<Signal> = signals.getRequired("signals")

    /** Likely fake share by position in the newest-first list. */
    fun arrivalBands(): List<ArrivalBand> = arrivalBands.getRequired("arrivalBands")

    /** Followers who joined X in the same UTC week. */
    fun cohorts(): List<Cohort> = cohorts.getRequired("cohorts")

    fun languages(): List<FollowerValueCount> = languages.getRequired("languages")

    fun places(): List<FollowerValueCount> = places.getRequired("places")

    fun verified(): Verified = verified.getRequired("verified")

    fun followers(): List<AuditedFollower> = followers.getRequired("followers")

    fun topFollowers(): List<AuditedFollower> = topFollowers.getRequired("topFollowers")

    /** Returns the raw JSON value of [account]. */
    @JsonProperty("account") @ExcludeMissing fun _account(): JsonField<Insights> = account

    /** Returns the raw JSON value of [coverage]. */
    @JsonProperty("coverage") @ExcludeMissing fun _coverage(): JsonField<Coverage> = coverage

    /** Returns the raw JSON value of [quality]. */
    @JsonProperty("quality") @ExcludeMissing fun _quality(): JsonField<Quality> = quality

    /** Returns the raw JSON value of [classes]. */
    @JsonProperty("classes") @ExcludeMissing fun _classes(): JsonField<List<Class>> = classes

    /** Returns the raw JSON value of [signals]. */
    @JsonProperty("signals") @ExcludeMissing fun _signals(): JsonField<List<Signal>> = signals

    /** Returns the raw JSON value of [arrivalBands]. */
    @JsonProperty("arrivalBands")
    @ExcludeMissing
    fun _arrivalBands(): JsonField<List<ArrivalBand>> = arrivalBands

    /** Returns the raw JSON value of [cohorts]. */
    @JsonProperty("cohorts") @ExcludeMissing fun _cohorts(): JsonField<List<Cohort>> = cohorts

    /** Returns the raw JSON value of [languages]. */
    @JsonProperty("languages")
    @ExcludeMissing
    fun _languages(): JsonField<List<FollowerValueCount>> = languages

    /** Returns the raw JSON value of [places]. */
    @JsonProperty("places")
    @ExcludeMissing
    fun _places(): JsonField<List<FollowerValueCount>> = places

    /** Returns the raw JSON value of [verified]. */
    @JsonProperty("verified") @ExcludeMissing fun _verified(): JsonField<Verified> = verified

    /** Returns the raw JSON value of [followers]. */
    @JsonProperty("followers")
    @ExcludeMissing
    fun _followers(): JsonField<List<AuditedFollower>> = followers

    /** Returns the raw JSON value of [topFollowers]. */
    @JsonProperty("topFollowers")
    @ExcludeMissing
    fun _topFollowers(): JsonField<List<AuditedFollower>> = topFollowers

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
         * Returns a mutable builder for constructing an instance of [FollowerAudit].
         *
         * The following fields are required:
         * ```kotlin
         * .account()
         * .coverage()
         * .quality()
         * .classes()
         * .signals()
         * .arrivalBands()
         * .cohorts()
         * .languages()
         * .places()
         * .verified()
         * .followers()
         * .topFollowers()
         * ```
         */
        fun builder() = Builder()
    }

    /** A builder for [FollowerAudit]. */
    class Builder internal constructor() {

        private var account: JsonField<Insights>? = null
        private var coverage: JsonField<Coverage>? = null
        private var quality: JsonField<Quality>? = null
        private var classes: JsonField<MutableList<Class>>? = null
        private var signals: JsonField<MutableList<Signal>>? = null
        private var arrivalBands: JsonField<MutableList<ArrivalBand>>? = null
        private var cohorts: JsonField<MutableList<Cohort>>? = null
        private var languages: JsonField<MutableList<FollowerValueCount>>? = null
        private var places: JsonField<MutableList<FollowerValueCount>>? = null
        private var verified: JsonField<Verified>? = null
        private var followers: JsonField<MutableList<AuditedFollower>>? = null
        private var topFollowers: JsonField<MutableList<AuditedFollower>>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        internal fun from(followerAudit: FollowerAudit) = apply {
            account = followerAudit.account
            coverage = followerAudit.coverage
            quality = followerAudit.quality
            classes = followerAudit.classes.map { it.toMutableList() }
            signals = followerAudit.signals.map { it.toMutableList() }
            arrivalBands = followerAudit.arrivalBands.map { it.toMutableList() }
            cohorts = followerAudit.cohorts.map { it.toMutableList() }
            languages = followerAudit.languages.map { it.toMutableList() }
            places = followerAudit.places.map { it.toMutableList() }
            verified = followerAudit.verified
            followers = followerAudit.followers.map { it.toMutableList() }
            topFollowers = followerAudit.topFollowers.map { it.toMutableList() }
            additionalProperties = followerAudit.additionalProperties.toMutableMap()
        }

        /** Engagement insights of the audited account. */
        fun account(account: Insights) = account(JsonField.of(account))

        /** Sets [Builder.account] to an arbitrary JSON value. */
        fun account(account: JsonField<Insights>) = apply { this.account = account }

        fun coverage(coverage: Coverage) = coverage(JsonField.of(coverage))

        /** Sets [Builder.coverage] to an arbitrary JSON value. */
        fun coverage(coverage: JsonField<Coverage>) = apply { this.coverage = coverage }

        fun quality(quality: Quality) = quality(JsonField.of(quality))

        /** Sets [Builder.quality] to an arbitrary JSON value. */
        fun quality(quality: JsonField<Quality>) = apply { this.quality = quality }

        fun classes(classes: List<Class>) = classes(JsonField.of(classes))

        /** Sets [Builder.classes] to an arbitrary JSON value. */
        fun classes(classes: JsonField<List<Class>>) = apply {
            this.classes = classes.map { it.toMutableList() }
        }

        /** Adds a single [Class] to [classes]. */
        fun addClass(class_: Class) = apply {
            classes =
                (classes ?: JsonField.of(mutableListOf())).also {
                    checkKnown("classes", it).add(class_)
                }
        }

        fun signals(signals: List<Signal>) = signals(JsonField.of(signals))

        /** Sets [Builder.signals] to an arbitrary JSON value. */
        fun signals(signals: JsonField<List<Signal>>) = apply {
            this.signals = signals.map { it.toMutableList() }
        }

        /** Adds a single [Signal] to [signals]. */
        fun addSignal(signal: Signal) = apply {
            signals =
                (signals ?: JsonField.of(mutableListOf())).also {
                    checkKnown("signals", it).add(signal)
                }
        }

        /** Likely fake share by position in the newest-first list. */
        fun arrivalBands(arrivalBands: List<ArrivalBand>) = arrivalBands(JsonField.of(arrivalBands))

        /** Sets [Builder.arrivalBands] to an arbitrary JSON value. */
        fun arrivalBands(arrivalBands: JsonField<List<ArrivalBand>>) = apply {
            this.arrivalBands = arrivalBands.map { it.toMutableList() }
        }

        /** Adds a single [ArrivalBand] to [arrivalBands]. */
        fun addArrivalBand(arrivalBand: ArrivalBand) = apply {
            arrivalBands =
                (arrivalBands ?: JsonField.of(mutableListOf())).also {
                    checkKnown("arrivalBands", it).add(arrivalBand)
                }
        }

        /** Followers who joined X in the same UTC week. */
        fun cohorts(cohorts: List<Cohort>) = cohorts(JsonField.of(cohorts))

        /** Sets [Builder.cohorts] to an arbitrary JSON value. */
        fun cohorts(cohorts: JsonField<List<Cohort>>) = apply {
            this.cohorts = cohorts.map { it.toMutableList() }
        }

        /** Adds a single [Cohort] to [cohorts]. */
        fun addCohort(cohort: Cohort) = apply {
            cohorts =
                (cohorts ?: JsonField.of(mutableListOf())).also {
                    checkKnown("cohorts", it).add(cohort)
                }
        }

        fun languages(languages: List<FollowerValueCount>) = languages(JsonField.of(languages))

        /** Sets [Builder.languages] to an arbitrary JSON value. */
        fun languages(languages: JsonField<List<FollowerValueCount>>) = apply {
            this.languages = languages.map { it.toMutableList() }
        }

        /** Adds a single [FollowerValueCount] to [languages]. */
        fun addLanguage(language: FollowerValueCount) = apply {
            languages =
                (languages ?: JsonField.of(mutableListOf())).also {
                    checkKnown("languages", it).add(language)
                }
        }

        fun places(places: List<FollowerValueCount>) = places(JsonField.of(places))

        /** Sets [Builder.places] to an arbitrary JSON value. */
        fun places(places: JsonField<List<FollowerValueCount>>) = apply {
            this.places = places.map { it.toMutableList() }
        }

        /** Adds a single [FollowerValueCount] to [places]. */
        fun addPlace(place: FollowerValueCount) = apply {
            places =
                (places ?: JsonField.of(mutableListOf())).also {
                    checkKnown("places", it).add(place)
                }
        }

        fun verified(verified: Verified) = verified(JsonField.of(verified))

        /** Sets [Builder.verified] to an arbitrary JSON value. */
        fun verified(verified: JsonField<Verified>) = apply { this.verified = verified }

        fun followers(followers: List<AuditedFollower>) = followers(JsonField.of(followers))

        /** Sets [Builder.followers] to an arbitrary JSON value. */
        fun followers(followers: JsonField<List<AuditedFollower>>) = apply {
            this.followers = followers.map { it.toMutableList() }
        }

        /** Adds a single [AuditedFollower] to [followers]. */
        fun addFollower(follower: AuditedFollower) = apply {
            followers =
                (followers ?: JsonField.of(mutableListOf())).also {
                    checkKnown("followers", it).add(follower)
                }
        }

        fun topFollowers(topFollowers: List<AuditedFollower>) =
            topFollowers(JsonField.of(topFollowers))

        /** Sets [Builder.topFollowers] to an arbitrary JSON value. */
        fun topFollowers(topFollowers: JsonField<List<AuditedFollower>>) = apply {
            this.topFollowers = topFollowers.map { it.toMutableList() }
        }

        /** Adds a single [AuditedFollower] to [topFollowers]. */
        fun addTopFollower(topFollower: AuditedFollower) = apply {
            topFollowers =
                (topFollowers ?: JsonField.of(mutableListOf())).also {
                    checkKnown("topFollowers", it).add(topFollower)
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
         * Returns an immutable instance of [FollowerAudit].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```kotlin
         * .account()
         * .coverage()
         * .quality()
         * .classes()
         * .signals()
         * .arrivalBands()
         * .cohorts()
         * .languages()
         * .places()
         * .verified()
         * .followers()
         * .topFollowers()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): FollowerAudit =
            FollowerAudit(
                checkRequired("account", account),
                checkRequired("coverage", coverage),
                checkRequired("quality", quality),
                checkRequired("classes", classes).map { it.toImmutable() },
                checkRequired("signals", signals).map { it.toImmutable() },
                checkRequired("arrivalBands", arrivalBands).map { it.toImmutable() },
                checkRequired("cohorts", cohorts).map { it.toImmutable() },
                checkRequired("languages", languages).map { it.toImmutable() },
                checkRequired("places", places).map { it.toImmutable() },
                checkRequired("verified", verified),
                checkRequired("followers", followers).map { it.toImmutable() },
                checkRequired("topFollowers", topFollowers).map { it.toImmutable() },
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
    fun validate(): FollowerAudit = apply {
        if (validated) {
            return@apply
        }

        account().validate()
        coverage().validate()
        quality().validate()
        classes().forEach { it.validate() }
        signals().forEach { it.validate() }
        arrivalBands().forEach { it.validate() }
        cohorts().forEach { it.validate() }
        languages().forEach { it.validate() }
        places().forEach { it.validate() }
        verified().validate()
        followers().forEach { it.validate() }
        topFollowers().forEach { it.validate() }
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
        (account.asKnown()?.validity() ?: 0) +
            (coverage.asKnown()?.validity() ?: 0) +
            (quality.asKnown()?.validity() ?: 0) +
            (classes.asKnown()?.sumOf { it.validity() } ?: 0) +
            (signals.asKnown()?.sumOf { it.validity() } ?: 0) +
            (arrivalBands.asKnown()?.sumOf { it.validity() } ?: 0) +
            (cohorts.asKnown()?.sumOf { it.validity() } ?: 0) +
            (languages.asKnown()?.sumOf { it.validity() } ?: 0) +
            (places.asKnown()?.sumOf { it.validity() } ?: 0) +
            (verified.asKnown()?.validity() ?: 0) +
            (followers.asKnown()?.sumOf { it.validity() } ?: 0) +
            (topFollowers.asKnown()?.sumOf { it.validity() } ?: 0)

    /** How many followers the audit read, out of the account's total. */
    class Coverage
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val percent: JsonField<Double>,
        private val read: JsonField<Long>,
        private val total: JsonField<Long>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("percent") @ExcludeMissing percent: JsonField<Double> = JsonMissing.of(),
            @JsonProperty("read") @ExcludeMissing read: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("total") @ExcludeMissing total: JsonField<Long> = JsonMissing.of(),
        ) : this(percent, read, total, mutableMapOf())

        fun percent(): Double = percent.getRequired("percent")

        fun read(): Long = read.getRequired("read")

        fun total(): Long = total.getRequired("total")

        /** Returns the raw JSON value of [percent]. */
        @JsonProperty("percent") @ExcludeMissing fun _percent(): JsonField<Double> = percent

        /** Returns the raw JSON value of [read]. */
        @JsonProperty("read") @ExcludeMissing fun _read(): JsonField<Long> = read

        /** Returns the raw JSON value of [total]. */
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
             * Returns a mutable builder for constructing an instance of [Coverage].
             *
             * The following fields are required:
             * ```kotlin
             * .percent()
             * .read()
             * .total()
             * ```
             */
            fun builder() = Builder()
        }

        /** A builder for [Coverage]. */
        class Builder internal constructor() {

            private var percent: JsonField<Double>? = null
            private var read: JsonField<Long>? = null
            private var total: JsonField<Long>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(coverage: Coverage) = apply {
                percent = coverage.percent
                read = coverage.read
                total = coverage.total
                additionalProperties = coverage.additionalProperties.toMutableMap()
            }

            fun percent(percent: Double) = percent(JsonField.of(percent))

            /** Sets [Builder.percent] to an arbitrary JSON value. */
            fun percent(percent: JsonField<Double>) = apply { this.percent = percent }

            fun read(read: Long) = read(JsonField.of(read))

            /** Sets [Builder.read] to an arbitrary JSON value. */
            fun read(read: JsonField<Long>) = apply { this.read = read }

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

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            /**
             * Returns an immutable instance of [Coverage].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             *
             * The following fields are required:
             * ```kotlin
             * .percent()
             * .read()
             * .total()
             * ```
             *
             * @throws IllegalStateException if any required field is unset.
             */
            fun build(): Coverage =
                Coverage(
                    checkRequired("percent", percent),
                    checkRequired("read", read),
                    checkRequired("total", total),
                    additionalProperties.toMutableMap(),
                )
        }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
         *   match its expected type.
         */
        fun validate(): Coverage = apply {
            if (validated) {
                return@apply
            }

            percent()
            read()
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
            (if (percent.asKnown() == null) 0 else 1) +
                (if (read.asKnown() == null) 0 else 1) +
                (if (total.asKnown() == null) 0 else 1)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Coverage &&
                percent == other.percent &&
                read == other.read &&
                total == other.total &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(percent, read, total, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Coverage{percent=$percent, read=$read, total=$total, additionalProperties=$additionalProperties}"
    }

    /** Quality grade & score of the followers read. */
    class Quality
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val grade: JsonField<Grade>,
        private val score: JsonField<Long>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("grade") @ExcludeMissing grade: JsonField<Grade> = JsonMissing.of(),
            @JsonProperty("score") @ExcludeMissing score: JsonField<Long> = JsonMissing.of(),
        ) : this(grade, score, mutableMapOf())

        fun grade(): Grade = grade.getRequired("grade")

        fun score(): Long = score.getRequired("score")

        /** Returns the raw JSON value of [grade]. */
        @JsonProperty("grade") @ExcludeMissing fun _grade(): JsonField<Grade> = grade

        /** Returns the raw JSON value of [score]. */
        @JsonProperty("score") @ExcludeMissing fun _score(): JsonField<Long> = score

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
             * Returns a mutable builder for constructing an instance of [Quality].
             *
             * The following fields are required:
             * ```kotlin
             * .grade()
             * .score()
             * ```
             */
            fun builder() = Builder()
        }

        /** A builder for [Quality]. */
        class Builder internal constructor() {

            private var grade: JsonField<Grade>? = null
            private var score: JsonField<Long>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(quality: Quality) = apply {
                grade = quality.grade
                score = quality.score
                additionalProperties = quality.additionalProperties.toMutableMap()
            }

            fun grade(grade: Grade) = grade(JsonField.of(grade))

            /** Sets [Builder.grade] to an arbitrary JSON value. */
            fun grade(grade: JsonField<Grade>) = apply { this.grade = grade }

            fun score(score: Long) = score(JsonField.of(score))

            /** Sets [Builder.score] to an arbitrary JSON value. */
            fun score(score: JsonField<Long>) = apply { this.score = score }

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
             * Returns an immutable instance of [Quality].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             *
             * The following fields are required:
             * ```kotlin
             * .grade()
             * .score()
             * ```
             *
             * @throws IllegalStateException if any required field is unset.
             */
            fun build(): Quality =
                Quality(
                    checkRequired("grade", grade),
                    checkRequired("score", score),
                    additionalProperties.toMutableMap(),
                )
        }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
         *   match its expected type.
         */
        fun validate(): Quality = apply {
            if (validated) {
                return@apply
            }

            grade().validate()
            score()
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
            (grade.asKnown()?.validity() ?: 0) + (if (score.asKnown() == null) 0 else 1)

        class Grade @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

            /** Returns this class instance's raw value. */
            @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

            companion object {

                val A = of("A")

                val B = of("B")

                val C = of("C")

                val D = of("D")

                val F = of("F")

                fun of(value: String) = Grade(JsonField.of(value))
            }

            /** An enum containing [Grade]'s known values. */
            enum class Known {
                A,
                B,
                C,
                D,
                F,
            }

            /** An enum containing [Grade]'s known values, as well as an [_UNKNOWN] member. */
            enum class Value {
                A,
                B,
                C,
                D,
                F,
                /**
                 * An enum member indicating that [Grade] was instantiated with an unknown value.
                 */
                _UNKNOWN,
            }

            /**
             * Returns an enum member corresponding to this class instance's value, or
             * [Value._UNKNOWN] if unknown.
             */
            fun value(): Value =
                when (this) {
                    A -> Value.A
                    B -> Value.B
                    C -> Value.C
                    D -> Value.D
                    F -> Value.F
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
                    A -> Known.A
                    B -> Known.B
                    C -> Known.C
                    D -> Known.D
                    F -> Known.F
                    else -> throw XTwitterScraperInvalidDataException("Unknown Grade: $value")
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

            /** Validates that this value is a known member. */
            fun validate(): Grade = apply {
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

                return other is Grade && value == other.value
            }

            override fun hashCode() = value.hashCode()

            override fun toString() = value.toString()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Quality &&
                grade == other.grade &&
                score == other.score &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(grade, score, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Quality{grade=$grade, score=$score, additionalProperties=$additionalProperties}"
    }

    /** How many followers the audit put in 1 class. */
    class Class
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val class_: JsonField<FollowerClass>,
        private val count: JsonField<Long>,
        private val percent: JsonField<Double>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("class")
            @ExcludeMissing
            class_: JsonField<FollowerClass> = JsonMissing.of(),
            @JsonProperty("count") @ExcludeMissing count: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("percent") @ExcludeMissing percent: JsonField<Double> = JsonMissing.of(),
        ) : this(class_, count, percent, mutableMapOf())

        fun class_(): FollowerClass = class_.getRequired("class")

        fun count(): Long = count.getRequired("count")

        fun percent(): Double = percent.getRequired("percent")

        /** Returns the raw JSON value of [class_]. */
        @JsonProperty("class") @ExcludeMissing fun _class_(): JsonField<FollowerClass> = class_

        /** Returns the raw JSON value of [count]. */
        @JsonProperty("count") @ExcludeMissing fun _count(): JsonField<Long> = count

        /** Returns the raw JSON value of [percent]. */
        @JsonProperty("percent") @ExcludeMissing fun _percent(): JsonField<Double> = percent

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
             * Returns a mutable builder for constructing an instance of [Class].
             *
             * The following fields are required:
             * ```kotlin
             * .class_()
             * .count()
             * .percent()
             * ```
             */
            fun builder() = Builder()
        }

        /** A builder for [Class]. */
        class Builder internal constructor() {

            private var class_: JsonField<FollowerClass>? = null
            private var count: JsonField<Long>? = null
            private var percent: JsonField<Double>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(classEntry: Class) = apply {
                class_ = classEntry.class_
                count = classEntry.count
                percent = classEntry.percent
                additionalProperties = classEntry.additionalProperties.toMutableMap()
            }

            fun class_(class_: FollowerClass) = class_(JsonField.of(class_))

            /** Sets [Builder.class_] to an arbitrary JSON value. */
            fun class_(class_: JsonField<FollowerClass>) = apply { this.class_ = class_ }

            fun count(count: Long) = count(JsonField.of(count))

            /** Sets [Builder.count] to an arbitrary JSON value. */
            fun count(count: JsonField<Long>) = apply { this.count = count }

            fun percent(percent: Double) = percent(JsonField.of(percent))

            /** Sets [Builder.percent] to an arbitrary JSON value. */
            fun percent(percent: JsonField<Double>) = apply { this.percent = percent }

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
             * Returns an immutable instance of [Class].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             *
             * The following fields are required:
             * ```kotlin
             * .class_()
             * .count()
             * .percent()
             * ```
             *
             * @throws IllegalStateException if any required field is unset.
             */
            fun build(): Class =
                Class(
                    checkRequired("class_", class_),
                    checkRequired("count", count),
                    checkRequired("percent", percent),
                    additionalProperties.toMutableMap(),
                )
        }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
         *   match its expected type.
         */
        fun validate(): Class = apply {
            if (validated) {
                return@apply
            }

            class_().validate()
            count()
            percent()
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
                (if (count.asKnown() == null) 0 else 1) +
                (if (percent.asKnown() == null) 0 else 1)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Class &&
                class_ == other.class_ &&
                count == other.count &&
                percent == other.percent &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(class_, count, percent, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Class{class_=$class_, count=$count, percent=$percent, additionalProperties=$additionalProperties}"
    }

    /** How many followers match 1 signal, & the points it adds to a score. */
    class Signal
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val count: JsonField<Long>,
        private val id: JsonField<String>,
        private val percent: JsonField<Double>,
        private val points: JsonField<Long>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("count") @ExcludeMissing count: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
            @JsonProperty("percent") @ExcludeMissing percent: JsonField<Double> = JsonMissing.of(),
            @JsonProperty("points") @ExcludeMissing points: JsonField<Long> = JsonMissing.of(),
        ) : this(count, id, percent, points, mutableMapOf())

        fun count(): Long = count.getRequired("count")

        fun id(): String = id.getRequired("id")

        fun percent(): Double = percent.getRequired("percent")

        fun points(): Long = points.getRequired("points")

        /** Returns the raw JSON value of [count]. */
        @JsonProperty("count") @ExcludeMissing fun _count(): JsonField<Long> = count

        /** Returns the raw JSON value of [id]. */
        @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

        /** Returns the raw JSON value of [percent]. */
        @JsonProperty("percent") @ExcludeMissing fun _percent(): JsonField<Double> = percent

        /** Returns the raw JSON value of [points]. */
        @JsonProperty("points") @ExcludeMissing fun _points(): JsonField<Long> = points

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
             * Returns a mutable builder for constructing an instance of [Signal].
             *
             * The following fields are required:
             * ```kotlin
             * .count()
             * .id()
             * .percent()
             * .points()
             * ```
             */
            fun builder() = Builder()
        }

        /** A builder for [Signal]. */
        class Builder internal constructor() {

            private var count: JsonField<Long>? = null
            private var id: JsonField<String>? = null
            private var percent: JsonField<Double>? = null
            private var points: JsonField<Long>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(signal: Signal) = apply {
                count = signal.count
                id = signal.id
                percent = signal.percent
                points = signal.points
                additionalProperties = signal.additionalProperties.toMutableMap()
            }

            fun count(count: Long) = count(JsonField.of(count))

            /** Sets [Builder.count] to an arbitrary JSON value. */
            fun count(count: JsonField<Long>) = apply { this.count = count }

            fun id(id: String) = id(JsonField.of(id))

            /** Sets [Builder.id] to an arbitrary JSON value. */
            fun id(id: JsonField<String>) = apply { this.id = id }

            fun percent(percent: Double) = percent(JsonField.of(percent))

            /** Sets [Builder.percent] to an arbitrary JSON value. */
            fun percent(percent: JsonField<Double>) = apply { this.percent = percent }

            fun points(points: Long) = points(JsonField.of(points))

            /** Sets [Builder.points] to an arbitrary JSON value. */
            fun points(points: JsonField<Long>) = apply { this.points = points }

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
             * Returns an immutable instance of [Signal].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             *
             * The following fields are required:
             * ```kotlin
             * .count()
             * .id()
             * .percent()
             * .points()
             * ```
             *
             * @throws IllegalStateException if any required field is unset.
             */
            fun build(): Signal =
                Signal(
                    checkRequired("count", count),
                    checkRequired("id", id),
                    checkRequired("percent", percent),
                    checkRequired("points", points),
                    additionalProperties.toMutableMap(),
                )
        }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
         *   match its expected type.
         */
        fun validate(): Signal = apply {
            if (validated) {
                return@apply
            }

            count()
            id()
            percent()
            points()
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
            (if (count.asKnown() == null) 0 else 1) +
                (if (id.asKnown() == null) 0 else 1) +
                (if (percent.asKnown() == null) 0 else 1) +
                (if (points.asKnown() == null) 0 else 1)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Signal &&
                count == other.count &&
                id == other.id &&
                percent == other.percent &&
                points == other.points &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(count, id, percent, points, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Signal{count=$count, id=$id, percent=$percent, points=$points, additionalProperties=$additionalProperties}"
    }

    /** Likely fake share in 1 band of positions in the newest-first list. */
    class ArrivalBand
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val count: JsonField<Long>,
        private val from: JsonField<Long>,
        private val percent: JsonField<Double>,
        private val read: JsonField<Long>,
        private val to: JsonField<Long>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("count") @ExcludeMissing count: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("from") @ExcludeMissing from: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("percent") @ExcludeMissing percent: JsonField<Double> = JsonMissing.of(),
            @JsonProperty("read") @ExcludeMissing read: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("to") @ExcludeMissing to: JsonField<Long> = JsonMissing.of(),
        ) : this(count, from, percent, read, to, mutableMapOf())

        fun count(): Long = count.getRequired("count")

        fun from(): Long = from.getRequired("from")

        fun percent(): Double = percent.getRequired("percent")

        fun read(): Long = read.getRequired("read")

        fun to(): Long = to.getRequired("to")

        /** Returns the raw JSON value of [count]. */
        @JsonProperty("count") @ExcludeMissing fun _count(): JsonField<Long> = count

        /** Returns the raw JSON value of [from]. */
        @JsonProperty("from") @ExcludeMissing fun _from(): JsonField<Long> = from

        /** Returns the raw JSON value of [percent]. */
        @JsonProperty("percent") @ExcludeMissing fun _percent(): JsonField<Double> = percent

        /** Returns the raw JSON value of [read]. */
        @JsonProperty("read") @ExcludeMissing fun _read(): JsonField<Long> = read

        /** Returns the raw JSON value of [to]. */
        @JsonProperty("to") @ExcludeMissing fun _to(): JsonField<Long> = to

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
             * Returns a mutable builder for constructing an instance of [ArrivalBand].
             *
             * The following fields are required:
             * ```kotlin
             * .count()
             * .from()
             * .percent()
             * .read()
             * .to()
             * ```
             */
            fun builder() = Builder()
        }

        /** A builder for [ArrivalBand]. */
        class Builder internal constructor() {

            private var count: JsonField<Long>? = null
            private var from: JsonField<Long>? = null
            private var percent: JsonField<Double>? = null
            private var read: JsonField<Long>? = null
            private var to: JsonField<Long>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(arrivalBand: ArrivalBand) = apply {
                count = arrivalBand.count
                from = arrivalBand.from
                percent = arrivalBand.percent
                read = arrivalBand.read
                to = arrivalBand.to
                additionalProperties = arrivalBand.additionalProperties.toMutableMap()
            }

            fun count(count: Long) = count(JsonField.of(count))

            /** Sets [Builder.count] to an arbitrary JSON value. */
            fun count(count: JsonField<Long>) = apply { this.count = count }

            fun from(from: Long): Builder = from(JsonField.of(from))

            /** Sets [Builder.from] to an arbitrary JSON value. */
            fun from(from: JsonField<Long>): Builder = apply { this.from = from }

            fun percent(percent: Double) = percent(JsonField.of(percent))

            /** Sets [Builder.percent] to an arbitrary JSON value. */
            fun percent(percent: JsonField<Double>) = apply { this.percent = percent }

            fun read(read: Long) = read(JsonField.of(read))

            /** Sets [Builder.read] to an arbitrary JSON value. */
            fun read(read: JsonField<Long>) = apply { this.read = read }

            fun to(to: Long): Builder = to(JsonField.of(to))

            /** Sets [Builder.to] to an arbitrary JSON value. */
            fun to(to: JsonField<Long>): Builder = apply { this.to = to }

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
             * Returns an immutable instance of [ArrivalBand].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             *
             * The following fields are required:
             * ```kotlin
             * .count()
             * .from()
             * .percent()
             * .read()
             * .to()
             * ```
             *
             * @throws IllegalStateException if any required field is unset.
             */
            fun build(): ArrivalBand =
                ArrivalBand(
                    checkRequired("count", count),
                    checkRequired("from", from),
                    checkRequired("percent", percent),
                    checkRequired("read", read),
                    checkRequired("to", to),
                    additionalProperties.toMutableMap(),
                )
        }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
         *   match its expected type.
         */
        fun validate(): ArrivalBand = apply {
            if (validated) {
                return@apply
            }

            count()
            from()
            percent()
            read()
            to()
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
            (if (count.asKnown() == null) 0 else 1) +
                (if (from.asKnown() == null) 0 else 1) +
                (if (percent.asKnown() == null) 0 else 1) +
                (if (read.asKnown() == null) 0 else 1) +
                (if (to.asKnown() == null) 0 else 1)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is ArrivalBand &&
                count == other.count &&
                from == other.from &&
                percent == other.percent &&
                read == other.read &&
                to == other.to &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(count, from, percent, read, to, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "ArrivalBand{count=$count, from=$from, percent=$percent, read=$read, to=$to, additionalProperties=$additionalProperties}"
    }

    /** Followers who joined X in the same UTC week. */
    class Cohort
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val count: JsonField<Long>,
        private val likelyFake: JsonField<Long>,
        private val percent: JsonField<Double>,
        private val weekStartMs: JsonField<Long>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("count") @ExcludeMissing count: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("likelyFake")
            @ExcludeMissing
            likelyFake: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("percent") @ExcludeMissing percent: JsonField<Double> = JsonMissing.of(),
            @JsonProperty("weekStartMs")
            @ExcludeMissing
            weekStartMs: JsonField<Long> = JsonMissing.of(),
        ) : this(count, likelyFake, percent, weekStartMs, mutableMapOf())

        fun count(): Long = count.getRequired("count")

        fun likelyFake(): Long = likelyFake.getRequired("likelyFake")

        fun percent(): Double = percent.getRequired("percent")

        fun weekStartMs(): Long = weekStartMs.getRequired("weekStartMs")

        /** Returns the raw JSON value of [count]. */
        @JsonProperty("count") @ExcludeMissing fun _count(): JsonField<Long> = count

        /** Returns the raw JSON value of [likelyFake]. */
        @JsonProperty("likelyFake") @ExcludeMissing fun _likelyFake(): JsonField<Long> = likelyFake

        /** Returns the raw JSON value of [percent]. */
        @JsonProperty("percent") @ExcludeMissing fun _percent(): JsonField<Double> = percent

        /** Returns the raw JSON value of [weekStartMs]. */
        @JsonProperty("weekStartMs")
        @ExcludeMissing
        fun _weekStartMs(): JsonField<Long> = weekStartMs

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
             * Returns a mutable builder for constructing an instance of [Cohort].
             *
             * The following fields are required:
             * ```kotlin
             * .count()
             * .likelyFake()
             * .percent()
             * .weekStartMs()
             * ```
             */
            fun builder() = Builder()
        }

        /** A builder for [Cohort]. */
        class Builder internal constructor() {

            private var count: JsonField<Long>? = null
            private var likelyFake: JsonField<Long>? = null
            private var percent: JsonField<Double>? = null
            private var weekStartMs: JsonField<Long>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(cohort: Cohort) = apply {
                count = cohort.count
                likelyFake = cohort.likelyFake
                percent = cohort.percent
                weekStartMs = cohort.weekStartMs
                additionalProperties = cohort.additionalProperties.toMutableMap()
            }

            fun count(count: Long) = count(JsonField.of(count))

            /** Sets [Builder.count] to an arbitrary JSON value. */
            fun count(count: JsonField<Long>) = apply { this.count = count }

            fun likelyFake(likelyFake: Long) = likelyFake(JsonField.of(likelyFake))

            /** Sets [Builder.likelyFake] to an arbitrary JSON value. */
            fun likelyFake(likelyFake: JsonField<Long>) = apply { this.likelyFake = likelyFake }

            fun percent(percent: Double) = percent(JsonField.of(percent))

            /** Sets [Builder.percent] to an arbitrary JSON value. */
            fun percent(percent: JsonField<Double>) = apply { this.percent = percent }

            fun weekStartMs(weekStartMs: Long) = weekStartMs(JsonField.of(weekStartMs))

            /** Sets [Builder.weekStartMs] to an arbitrary JSON value. */
            fun weekStartMs(weekStartMs: JsonField<Long>) = apply { this.weekStartMs = weekStartMs }

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
             * Returns an immutable instance of [Cohort].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             *
             * The following fields are required:
             * ```kotlin
             * .count()
             * .likelyFake()
             * .percent()
             * .weekStartMs()
             * ```
             *
             * @throws IllegalStateException if any required field is unset.
             */
            fun build(): Cohort =
                Cohort(
                    checkRequired("count", count),
                    checkRequired("likelyFake", likelyFake),
                    checkRequired("percent", percent),
                    checkRequired("weekStartMs", weekStartMs),
                    additionalProperties.toMutableMap(),
                )
        }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
         *   match its expected type.
         */
        fun validate(): Cohort = apply {
            if (validated) {
                return@apply
            }

            count()
            likelyFake()
            percent()
            weekStartMs()
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
            (if (count.asKnown() == null) 0 else 1) +
                (if (likelyFake.asKnown() == null) 0 else 1) +
                (if (percent.asKnown() == null) 0 else 1) +
                (if (weekStartMs.asKnown() == null) 0 else 1)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Cohort &&
                count == other.count &&
                likelyFake == other.likelyFake &&
                percent == other.percent &&
                weekStartMs == other.weekStartMs &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(count, likelyFake, percent, weekStartMs, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Cohort{count=$count, likelyFake=$likelyFake, percent=$percent, weekStartMs=$weekStartMs, additionalProperties=$additionalProperties}"
    }

    /** How many followers are verified. */
    class Verified
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val count: JsonField<Long>,
        private val percent: JsonField<Double>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("count") @ExcludeMissing count: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("percent") @ExcludeMissing percent: JsonField<Double> = JsonMissing.of(),
        ) : this(count, percent, mutableMapOf())

        fun count(): Long = count.getRequired("count")

        fun percent(): Double = percent.getRequired("percent")

        /** Returns the raw JSON value of [count]. */
        @JsonProperty("count") @ExcludeMissing fun _count(): JsonField<Long> = count

        /** Returns the raw JSON value of [percent]. */
        @JsonProperty("percent") @ExcludeMissing fun _percent(): JsonField<Double> = percent

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
             * Returns a mutable builder for constructing an instance of [Verified].
             *
             * The following fields are required:
             * ```kotlin
             * .count()
             * .percent()
             * ```
             */
            fun builder() = Builder()
        }

        /** A builder for [Verified]. */
        class Builder internal constructor() {

            private var count: JsonField<Long>? = null
            private var percent: JsonField<Double>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(verified: Verified) = apply {
                count = verified.count
                percent = verified.percent
                additionalProperties = verified.additionalProperties.toMutableMap()
            }

            fun count(count: Long) = count(JsonField.of(count))

            /** Sets [Builder.count] to an arbitrary JSON value. */
            fun count(count: JsonField<Long>) = apply { this.count = count }

            fun percent(percent: Double) = percent(JsonField.of(percent))

            /** Sets [Builder.percent] to an arbitrary JSON value. */
            fun percent(percent: JsonField<Double>) = apply { this.percent = percent }

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
             * Returns an immutable instance of [Verified].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             *
             * The following fields are required:
             * ```kotlin
             * .count()
             * .percent()
             * ```
             *
             * @throws IllegalStateException if any required field is unset.
             */
            fun build(): Verified =
                Verified(
                    checkRequired("count", count),
                    checkRequired("percent", percent),
                    additionalProperties.toMutableMap(),
                )
        }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * @throws XTwitterScraperInvalidDataException if any value type in this object doesn't
         *   match its expected type.
         */
        fun validate(): Verified = apply {
            if (validated) {
                return@apply
            }

            count()
            percent()
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
            (if (count.asKnown() == null) 0 else 1) + (if (percent.asKnown() == null) 0 else 1)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Verified &&
                count == other.count &&
                percent == other.percent &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(count, percent, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Verified{count=$count, percent=$percent, additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is FollowerAudit &&
            account == other.account &&
            coverage == other.coverage &&
            quality == other.quality &&
            classes == other.classes &&
            signals == other.signals &&
            arrivalBands == other.arrivalBands &&
            cohorts == other.cohorts &&
            languages == other.languages &&
            places == other.places &&
            verified == other.verified &&
            followers == other.followers &&
            topFollowers == other.topFollowers &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            account,
            coverage,
            quality,
            classes,
            signals,
            arrivalBands,
            cohorts,
            languages,
            places,
            verified,
            followers,
            topFollowers,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "FollowerAudit{account=$account, coverage=$coverage, quality=$quality, classes=$classes, signals=$signals, arrivalBands=$arrivalBands, cohorts=$cohorts, languages=$languages, places=$places, verified=$verified, followers=$followers, topFollowers=$topFollowers, additionalProperties=$additionalProperties}"
}
