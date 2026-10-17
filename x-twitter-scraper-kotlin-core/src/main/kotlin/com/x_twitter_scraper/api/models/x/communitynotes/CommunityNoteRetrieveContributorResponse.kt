// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

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
import java.util.Collections
import java.util.Objects

class CommunityNoteRetrieveContributorResponse
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val contributor: JsonField<CommunityNotesContributor>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("contributor")
        @ExcludeMissing
        contributor: JsonField<CommunityNotesContributor> = JsonMissing.of()
    ) : this(contributor, mutableMapOf())

    /**
     * A Community Notes contributor. X names contributors by an alias, never by their X account. A
     * field X leaves out is left out. Beside a note, X sends its writer with only some of the
     * counts.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun contributor(): CommunityNotesContributor = contributor.getRequired("contributor")

    /**
     * Returns the raw JSON value of [contributor].
     *
     * Unlike [contributor], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("contributor")
    @ExcludeMissing
    fun _contributor(): JsonField<CommunityNotesContributor> = contributor

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
         * [CommunityNoteRetrieveContributorResponse].
         *
         * The following fields are required:
         * ```kotlin
         * .contributor()
         * ```
         */
        fun builder() = Builder()
    }

    /** A builder for [CommunityNoteRetrieveContributorResponse]. */
    class Builder internal constructor() {

        private var contributor: JsonField<CommunityNotesContributor>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        internal fun from(
            communityNoteRetrieveContributorResponse: CommunityNoteRetrieveContributorResponse
        ) = apply {
            contributor = communityNoteRetrieveContributorResponse.contributor
            additionalProperties =
                communityNoteRetrieveContributorResponse.additionalProperties.toMutableMap()
        }

        /**
         * A Community Notes contributor. X names contributors by an alias, never by their X
         * account. A field X leaves out is left out. Beside a note, X sends its writer with only
         * some of the counts.
         */
        fun contributor(contributor: CommunityNotesContributor) =
            contributor(JsonField.of(contributor))

        /** Sets [Builder.contributor] to an arbitrary JSON value. */
        fun contributor(contributor: JsonField<CommunityNotesContributor>) = apply {
            this.contributor = contributor
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
         * Returns an immutable instance of [CommunityNoteRetrieveContributorResponse].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```kotlin
         * .contributor()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): CommunityNoteRetrieveContributorResponse =
            CommunityNoteRetrieveContributorResponse(
                checkRequired("contributor", contributor),
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
    fun validate(): CommunityNoteRetrieveContributorResponse = apply {
        if (validated) {
            return@apply
        }

        contributor().validate()
        validated = true
    }

    fun isValid(): Boolean =
        try {
            validate()
            true
        } catch (e: XTwitterScraperInvalidDataException) {
            false
        }

    internal fun validity(): Int = (contributor.asKnown()?.validity() ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is CommunityNoteRetrieveContributorResponse &&
            contributor == other.contributor &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(contributor, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "CommunityNoteRetrieveContributorResponse{contributor=$contributor, additionalProperties=$additionalProperties}"
}
