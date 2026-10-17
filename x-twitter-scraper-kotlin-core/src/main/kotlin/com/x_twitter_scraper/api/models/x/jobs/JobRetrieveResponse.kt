// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.jobs

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
import java.util.Collections
import java.util.Objects

class JobRetrieveResponse
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val job: JsonField<Job>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("job") @ExcludeMissing job: JsonField<Job> = JsonMissing.of()
    ) : this(job, mutableMapOf())

    /**
     * Public X job listing. A search returns the fields down to the salary, plus company & account.
     * A lookup adds work type, seniority, function, team & descriptions. A field X leaves empty is
     * left out.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun job(): Job = job.getRequired("job")

    /**
     * Returns the raw JSON value of [job].
     *
     * Unlike [job], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("job") @ExcludeMissing fun _job(): JsonField<Job> = job

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
         * Returns a mutable builder for constructing an instance of [JobRetrieveResponse].
         *
         * The following fields are required:
         * ```kotlin
         * .job()
         * ```
         */
        fun builder() = Builder()
    }

    /** A builder for [JobRetrieveResponse]. */
    class Builder internal constructor() {

        private var job: JsonField<Job>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        internal fun from(jobRetrieveResponse: JobRetrieveResponse) = apply {
            job = jobRetrieveResponse.job
            additionalProperties = jobRetrieveResponse.additionalProperties.toMutableMap()
        }

        /**
         * Public X job listing. A search returns the fields down to the salary, plus company &
         * account. A lookup adds work type, seniority, function, team & descriptions. A field X
         * leaves empty is left out.
         */
        fun job(job: Job) = job(JsonField.of(job))

        /** Sets [Builder.job] to an arbitrary JSON value. */
        fun job(job: JsonField<Job>) = apply { this.job = job }

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
         * Returns an immutable instance of [JobRetrieveResponse].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```kotlin
         * .job()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): JobRetrieveResponse =
            JobRetrieveResponse(checkRequired("job", job), additionalProperties.toMutableMap())
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
    fun validate(): JobRetrieveResponse = apply {
        if (validated) {
            return@apply
        }

        job().validate()
        validated = true
    }

    fun isValid(): Boolean =
        try {
            validate()
            true
        } catch (e: XTwitterScraperInvalidDataException) {
            false
        }

    internal fun validity(): Int = (job.asKnown()?.validity() ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is JobRetrieveResponse &&
            job == other.job &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(job, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "JobRetrieveResponse{job=$job, additionalProperties=$additionalProperties}"
}
