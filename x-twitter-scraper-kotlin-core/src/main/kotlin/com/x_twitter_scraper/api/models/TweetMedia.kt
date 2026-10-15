// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models

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
import java.util.Collections
import java.util.Objects

/** Tweet media. */
class TweetMedia
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val mediaUrl: JsonField<String>,
    private val type: JsonField<Type>,
    private val url: JsonField<String>,
    private val id: JsonField<String>,
    private val adultContent: JsonField<Boolean>,
    private val allowDownload: JsonField<Boolean>,
    private val altText: JsonField<String>,
    private val aspectRatio: JsonField<List<Long>>,
    private val availabilityReason: JsonField<String>,
    private val availabilityStatus: JsonField<String>,
    private val description: JsonField<String>,
    private val displayUrl: JsonField<String>,
    private val durationMillis: JsonField<Long>,
    private val embeddable: JsonField<Boolean>,
    private val expandedUrl: JsonField<String>,
    private val faceRects: JsonField<FaceRects>,
    private val focusRects: JsonField<List<FocusRect>>,
    private val graphicViolence: JsonField<Boolean>,
    private val grokImageAnnotation: JsonField<GrokImageAnnotation>,
    private val grokPostId: JsonField<String>,
    private val height: JsonField<Long>,
    private val indices: JsonField<List<Long>>,
    private val mediaKey: JsonField<String>,
    private val monetizable: JsonField<Boolean>,
    private val otherSensitiveContent: JsonField<Boolean>,
    private val sizes: JsonField<Sizes>,
    private val sourceStatusId: JsonField<String>,
    private val sourceUser: JsonField<UserProfile>,
    private val sourceUserId: JsonField<String>,
    private val tags: JsonField<List<Tag>>,
    private val title: JsonField<String>,
    private val videoVariants: JsonField<List<VideoVariant>>,
    private val visitSiteUrl: JsonField<String>,
    private val watchNowUrl: JsonField<String>,
    private val width: JsonField<Long>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("mediaUrl") @ExcludeMissing mediaUrl: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonField<Type> = JsonMissing.of(),
        @JsonProperty("url") @ExcludeMissing url: JsonField<String> = JsonMissing.of(),
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("adultContent")
        @ExcludeMissing
        adultContent: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("allowDownload")
        @ExcludeMissing
        allowDownload: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("altText") @ExcludeMissing altText: JsonField<String> = JsonMissing.of(),
        @JsonProperty("aspectRatio")
        @ExcludeMissing
        aspectRatio: JsonField<List<Long>> = JsonMissing.of(),
        @JsonProperty("availabilityReason")
        @ExcludeMissing
        availabilityReason: JsonField<String> = JsonMissing.of(),
        @JsonProperty("availabilityStatus")
        @ExcludeMissing
        availabilityStatus: JsonField<String> = JsonMissing.of(),
        @JsonProperty("description")
        @ExcludeMissing
        description: JsonField<String> = JsonMissing.of(),
        @JsonProperty("displayUrl")
        @ExcludeMissing
        displayUrl: JsonField<String> = JsonMissing.of(),
        @JsonProperty("durationMillis")
        @ExcludeMissing
        durationMillis: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("embeddable")
        @ExcludeMissing
        embeddable: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("expandedUrl")
        @ExcludeMissing
        expandedUrl: JsonField<String> = JsonMissing.of(),
        @JsonProperty("faceRects")
        @ExcludeMissing
        faceRects: JsonField<FaceRects> = JsonMissing.of(),
        @JsonProperty("focusRects")
        @ExcludeMissing
        focusRects: JsonField<List<FocusRect>> = JsonMissing.of(),
        @JsonProperty("graphicViolence")
        @ExcludeMissing
        graphicViolence: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("grokImageAnnotation")
        @ExcludeMissing
        grokImageAnnotation: JsonField<GrokImageAnnotation> = JsonMissing.of(),
        @JsonProperty("grokPostId")
        @ExcludeMissing
        grokPostId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("height") @ExcludeMissing height: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("indices") @ExcludeMissing indices: JsonField<List<Long>> = JsonMissing.of(),
        @JsonProperty("mediaKey") @ExcludeMissing mediaKey: JsonField<String> = JsonMissing.of(),
        @JsonProperty("monetizable")
        @ExcludeMissing
        monetizable: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("otherSensitiveContent")
        @ExcludeMissing
        otherSensitiveContent: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("sizes") @ExcludeMissing sizes: JsonField<Sizes> = JsonMissing.of(),
        @JsonProperty("sourceStatusId")
        @ExcludeMissing
        sourceStatusId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("sourceUser")
        @ExcludeMissing
        sourceUser: JsonField<UserProfile> = JsonMissing.of(),
        @JsonProperty("sourceUserId")
        @ExcludeMissing
        sourceUserId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("tags") @ExcludeMissing tags: JsonField<List<Tag>> = JsonMissing.of(),
        @JsonProperty("title") @ExcludeMissing title: JsonField<String> = JsonMissing.of(),
        @JsonProperty("videoVariants")
        @ExcludeMissing
        videoVariants: JsonField<List<VideoVariant>> = JsonMissing.of(),
        @JsonProperty("visitSiteUrl")
        @ExcludeMissing
        visitSiteUrl: JsonField<String> = JsonMissing.of(),
        @JsonProperty("watchNowUrl")
        @ExcludeMissing
        watchNowUrl: JsonField<String> = JsonMissing.of(),
        @JsonProperty("width") @ExcludeMissing width: JsonField<Long> = JsonMissing.of(),
    ) : this(
        mediaUrl,
        type,
        url,
        id,
        adultContent,
        allowDownload,
        altText,
        aspectRatio,
        availabilityReason,
        availabilityStatus,
        description,
        displayUrl,
        durationMillis,
        embeddable,
        expandedUrl,
        faceRects,
        focusRects,
        graphicViolence,
        grokImageAnnotation,
        grokPostId,
        height,
        indices,
        mediaKey,
        monetizable,
        otherSensitiveContent,
        sizes,
        sourceStatusId,
        sourceUser,
        sourceUserId,
        tags,
        title,
        videoVariants,
        visitSiteUrl,
        watchNowUrl,
        width,
        mutableMapOf(),
    )

    /**
     * Preview URL.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun mediaUrl(): String = mediaUrl.getRequired("mediaUrl")

    /**
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun type(): Type = type.getRequired("type")

    /**
     * Tweet media link.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun url(): String = url.getRequired("url")

    /**
     * Media entity ID.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun id(): String? = id.getNullable("id")

    /**
     * Adult-content warning.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun adultContent(): Boolean? = adultContent.getNullable("adultContent")

    /**
     * Direct download permission.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun allowDownload(): Boolean? = allowDownload.getNullable("allowDownload")

    /**
     * Accessibility text.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun altText(): String? = altText.getNullable("altText")

    /**
     * Video width and height ratio.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun aspectRatio(): List<Long>? = aspectRatio.getNullable("aspectRatio")

    /**
     * Why X made the media unavailable, such as `Dmcaed` after a copyright report. Omitted on
     * available media.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun availabilityReason(): String? = availabilityReason.getNullable("availabilityReason")

    /**
     * Availability state, such as `Available` or `Unavailable`.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun availabilityStatus(): String? = availabilityStatus.getNullable("availabilityStatus")

    /**
     * Media description.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun description(): String? = description.getNullable("description")

    /**
     * Display URL.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun displayUrl(): String? = displayUrl.getNullable("displayUrl")

    /**
     * Video duration in milliseconds.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun durationMillis(): Long? = durationMillis.getNullable("durationMillis")

    /**
     * Embeddable status.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun embeddable(): Boolean? = embeddable.getNullable("embeddable")

    /**
     * Expanded media URL.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun expandedUrl(): String? = expandedUrl.getNullable("expandedUrl")

    /**
     * Face crop rectangles by size.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun faceRects(): FaceRects? = faceRects.getNullable("faceRects")

    /**
     * Suggested image crops.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun focusRects(): List<FocusRect>? = focusRects.getNullable("focusRects")

    /**
     * Graphic-violence warning.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun graphicViolence(): Boolean? = graphicViolence.getNullable("graphicViolence")

    /**
     * Prompt a Grok image came from. Absent on other media & when X sends no prompt.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun grokImageAnnotation(): GrokImageAnnotation? =
        grokImageAnnotation.getNullable("grokImageAnnotation")

    /**
     * Grok post ID associated with the media.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun grokPostId(): String? = grokPostId.getNullable("grokPostId")

    /**
     * Original height.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun height(): Long? = height.getNullable("height")

    /**
     * Tweet text offsets.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun indices(): List<Long>? = indices.getNullable("indices")

    /**
     * Stable X media key.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun mediaKey(): String? = mediaKey.getNullable("mediaKey")

    /**
     * Monetization status.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun monetizable(): Boolean? = monetizable.getNullable("monetizable")

    /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
    fun otherSensitiveContent(): Boolean? =
        otherSensitiveContent.getNullable("otherSensitiveContent")

    /**
     * Named media renditions and resize modes.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun sizes(): Sizes? = sizes.getNullable("sizes")

    /**
     * Source tweet ID for copied media.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun sourceStatusId(): String? = sourceStatusId.getNullable("sourceStatusId")

    /**
     * Public X profile.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun sourceUser(): UserProfile? = sourceUser.getNullable("sourceUser")

    /**
     * Source profile ID for copied media.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun sourceUserId(): String? = sourceUserId.getNullable("sourceUserId")

    /**
     * Public profiles tagged in the media.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun tags(): List<Tag>? = tags.getNullable("tags")

    /**
     * Media title.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun title(): String? = title.getNullable("title")

    /**
     * Video encodings in source order.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun videoVariants(): List<VideoVariant>? = videoVariants.getNullable("videoVariants")

    /**
     * Public destination URL.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun visitSiteUrl(): String? = visitSiteUrl.getNullable("visitSiteUrl")

    /**
     * Public media action URL.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun watchNowUrl(): String? = watchNowUrl.getNullable("watchNowUrl")

    /**
     * Original width.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun width(): Long? = width.getNullable("width")

    /**
     * Returns the raw JSON value of [mediaUrl].
     *
     * Unlike [mediaUrl], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("mediaUrl") @ExcludeMissing fun _mediaUrl(): JsonField<String> = mediaUrl

    /**
     * Returns the raw JSON value of [type].
     *
     * Unlike [type], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<Type> = type

    /**
     * Returns the raw JSON value of [url].
     *
     * Unlike [url], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("url") @ExcludeMissing fun _url(): JsonField<String> = url

    /**
     * Returns the raw JSON value of [id].
     *
     * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    /**
     * Returns the raw JSON value of [adultContent].
     *
     * Unlike [adultContent], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("adultContent")
    @ExcludeMissing
    fun _adultContent(): JsonField<Boolean> = adultContent

    /**
     * Returns the raw JSON value of [allowDownload].
     *
     * Unlike [allowDownload], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("allowDownload")
    @ExcludeMissing
    fun _allowDownload(): JsonField<Boolean> = allowDownload

    /**
     * Returns the raw JSON value of [altText].
     *
     * Unlike [altText], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("altText") @ExcludeMissing fun _altText(): JsonField<String> = altText

    /**
     * Returns the raw JSON value of [aspectRatio].
     *
     * Unlike [aspectRatio], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("aspectRatio")
    @ExcludeMissing
    fun _aspectRatio(): JsonField<List<Long>> = aspectRatio

    /**
     * Returns the raw JSON value of [availabilityReason].
     *
     * Unlike [availabilityReason], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("availabilityReason")
    @ExcludeMissing
    fun _availabilityReason(): JsonField<String> = availabilityReason

    /**
     * Returns the raw JSON value of [availabilityStatus].
     *
     * Unlike [availabilityStatus], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("availabilityStatus")
    @ExcludeMissing
    fun _availabilityStatus(): JsonField<String> = availabilityStatus

    /**
     * Returns the raw JSON value of [description].
     *
     * Unlike [description], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("description") @ExcludeMissing fun _description(): JsonField<String> = description

    /**
     * Returns the raw JSON value of [displayUrl].
     *
     * Unlike [displayUrl], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("displayUrl") @ExcludeMissing fun _displayUrl(): JsonField<String> = displayUrl

    /**
     * Returns the raw JSON value of [durationMillis].
     *
     * Unlike [durationMillis], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("durationMillis")
    @ExcludeMissing
    fun _durationMillis(): JsonField<Long> = durationMillis

    /**
     * Returns the raw JSON value of [embeddable].
     *
     * Unlike [embeddable], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("embeddable") @ExcludeMissing fun _embeddable(): JsonField<Boolean> = embeddable

    /**
     * Returns the raw JSON value of [expandedUrl].
     *
     * Unlike [expandedUrl], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("expandedUrl") @ExcludeMissing fun _expandedUrl(): JsonField<String> = expandedUrl

    /**
     * Returns the raw JSON value of [faceRects].
     *
     * Unlike [faceRects], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("faceRects") @ExcludeMissing fun _faceRects(): JsonField<FaceRects> = faceRects

    /**
     * Returns the raw JSON value of [focusRects].
     *
     * Unlike [focusRects], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("focusRects")
    @ExcludeMissing
    fun _focusRects(): JsonField<List<FocusRect>> = focusRects

    /**
     * Returns the raw JSON value of [graphicViolence].
     *
     * Unlike [graphicViolence], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("graphicViolence")
    @ExcludeMissing
    fun _graphicViolence(): JsonField<Boolean> = graphicViolence

    /**
     * Returns the raw JSON value of [grokImageAnnotation].
     *
     * Unlike [grokImageAnnotation], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("grokImageAnnotation")
    @ExcludeMissing
    fun _grokImageAnnotation(): JsonField<GrokImageAnnotation> = grokImageAnnotation

    /**
     * Returns the raw JSON value of [grokPostId].
     *
     * Unlike [grokPostId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("grokPostId") @ExcludeMissing fun _grokPostId(): JsonField<String> = grokPostId

    /**
     * Returns the raw JSON value of [height].
     *
     * Unlike [height], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("height") @ExcludeMissing fun _height(): JsonField<Long> = height

    /**
     * Returns the raw JSON value of [indices].
     *
     * Unlike [indices], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("indices") @ExcludeMissing fun _indices(): JsonField<List<Long>> = indices

    /**
     * Returns the raw JSON value of [mediaKey].
     *
     * Unlike [mediaKey], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("mediaKey") @ExcludeMissing fun _mediaKey(): JsonField<String> = mediaKey

    /**
     * Returns the raw JSON value of [monetizable].
     *
     * Unlike [monetizable], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("monetizable")
    @ExcludeMissing
    fun _monetizable(): JsonField<Boolean> = monetizable

    /**
     * Returns the raw JSON value of [otherSensitiveContent].
     *
     * Unlike [otherSensitiveContent], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("otherSensitiveContent")
    @ExcludeMissing
    fun _otherSensitiveContent(): JsonField<Boolean> = otherSensitiveContent

    /**
     * Returns the raw JSON value of [sizes].
     *
     * Unlike [sizes], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("sizes") @ExcludeMissing fun _sizes(): JsonField<Sizes> = sizes

    /**
     * Returns the raw JSON value of [sourceStatusId].
     *
     * Unlike [sourceStatusId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("sourceStatusId")
    @ExcludeMissing
    fun _sourceStatusId(): JsonField<String> = sourceStatusId

    /**
     * Returns the raw JSON value of [sourceUser].
     *
     * Unlike [sourceUser], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("sourceUser")
    @ExcludeMissing
    fun _sourceUser(): JsonField<UserProfile> = sourceUser

    /**
     * Returns the raw JSON value of [sourceUserId].
     *
     * Unlike [sourceUserId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("sourceUserId")
    @ExcludeMissing
    fun _sourceUserId(): JsonField<String> = sourceUserId

    /**
     * Returns the raw JSON value of [tags].
     *
     * Unlike [tags], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("tags") @ExcludeMissing fun _tags(): JsonField<List<Tag>> = tags

    /**
     * Returns the raw JSON value of [title].
     *
     * Unlike [title], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("title") @ExcludeMissing fun _title(): JsonField<String> = title

    /**
     * Returns the raw JSON value of [videoVariants].
     *
     * Unlike [videoVariants], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("videoVariants")
    @ExcludeMissing
    fun _videoVariants(): JsonField<List<VideoVariant>> = videoVariants

    /**
     * Returns the raw JSON value of [visitSiteUrl].
     *
     * Unlike [visitSiteUrl], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("visitSiteUrl")
    @ExcludeMissing
    fun _visitSiteUrl(): JsonField<String> = visitSiteUrl

    /**
     * Returns the raw JSON value of [watchNowUrl].
     *
     * Unlike [watchNowUrl], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("watchNowUrl") @ExcludeMissing fun _watchNowUrl(): JsonField<String> = watchNowUrl

    /**
     * Returns the raw JSON value of [width].
     *
     * Unlike [width], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("width") @ExcludeMissing fun _width(): JsonField<Long> = width

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
         * Returns a mutable builder for constructing an instance of [TweetMedia].
         *
         * The following fields are required:
         * ```kotlin
         * .mediaUrl()
         * .type()
         * .url()
         * ```
         */
        fun builder() = Builder()
    }

    /** A builder for [TweetMedia]. */
    class Builder internal constructor() {

        private var mediaUrl: JsonField<String>? = null
        private var type: JsonField<Type>? = null
        private var url: JsonField<String>? = null
        private var id: JsonField<String> = JsonMissing.of()
        private var adultContent: JsonField<Boolean> = JsonMissing.of()
        private var allowDownload: JsonField<Boolean> = JsonMissing.of()
        private var altText: JsonField<String> = JsonMissing.of()
        private var aspectRatio: JsonField<MutableList<Long>>? = null
        private var availabilityReason: JsonField<String> = JsonMissing.of()
        private var availabilityStatus: JsonField<String> = JsonMissing.of()
        private var description: JsonField<String> = JsonMissing.of()
        private var displayUrl: JsonField<String> = JsonMissing.of()
        private var durationMillis: JsonField<Long> = JsonMissing.of()
        private var embeddable: JsonField<Boolean> = JsonMissing.of()
        private var expandedUrl: JsonField<String> = JsonMissing.of()
        private var faceRects: JsonField<FaceRects> = JsonMissing.of()
        private var focusRects: JsonField<MutableList<FocusRect>>? = null
        private var graphicViolence: JsonField<Boolean> = JsonMissing.of()
        private var grokImageAnnotation: JsonField<GrokImageAnnotation> = JsonMissing.of()
        private var grokPostId: JsonField<String> = JsonMissing.of()
        private var height: JsonField<Long> = JsonMissing.of()
        private var indices: JsonField<MutableList<Long>>? = null
        private var mediaKey: JsonField<String> = JsonMissing.of()
        private var monetizable: JsonField<Boolean> = JsonMissing.of()
        private var otherSensitiveContent: JsonField<Boolean> = JsonMissing.of()
        private var sizes: JsonField<Sizes> = JsonMissing.of()
        private var sourceStatusId: JsonField<String> = JsonMissing.of()
        private var sourceUser: JsonField<UserProfile> = JsonMissing.of()
        private var sourceUserId: JsonField<String> = JsonMissing.of()
        private var tags: JsonField<MutableList<Tag>>? = null
        private var title: JsonField<String> = JsonMissing.of()
        private var videoVariants: JsonField<MutableList<VideoVariant>>? = null
        private var visitSiteUrl: JsonField<String> = JsonMissing.of()
        private var watchNowUrl: JsonField<String> = JsonMissing.of()
        private var width: JsonField<Long> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        internal fun from(tweetMedia: TweetMedia) = apply {
            mediaUrl = tweetMedia.mediaUrl
            type = tweetMedia.type
            url = tweetMedia.url
            id = tweetMedia.id
            adultContent = tweetMedia.adultContent
            allowDownload = tweetMedia.allowDownload
            altText = tweetMedia.altText
            aspectRatio = tweetMedia.aspectRatio.map { it.toMutableList() }
            availabilityReason = tweetMedia.availabilityReason
            availabilityStatus = tweetMedia.availabilityStatus
            description = tweetMedia.description
            displayUrl = tweetMedia.displayUrl
            durationMillis = tweetMedia.durationMillis
            embeddable = tweetMedia.embeddable
            expandedUrl = tweetMedia.expandedUrl
            faceRects = tweetMedia.faceRects
            focusRects = tweetMedia.focusRects.map { it.toMutableList() }
            graphicViolence = tweetMedia.graphicViolence
            grokImageAnnotation = tweetMedia.grokImageAnnotation
            grokPostId = tweetMedia.grokPostId
            height = tweetMedia.height
            indices = tweetMedia.indices.map { it.toMutableList() }
            mediaKey = tweetMedia.mediaKey
            monetizable = tweetMedia.monetizable
            otherSensitiveContent = tweetMedia.otherSensitiveContent
            sizes = tweetMedia.sizes
            sourceStatusId = tweetMedia.sourceStatusId
            sourceUser = tweetMedia.sourceUser
            sourceUserId = tweetMedia.sourceUserId
            tags = tweetMedia.tags.map { it.toMutableList() }
            title = tweetMedia.title
            videoVariants = tweetMedia.videoVariants.map { it.toMutableList() }
            visitSiteUrl = tweetMedia.visitSiteUrl
            watchNowUrl = tweetMedia.watchNowUrl
            width = tweetMedia.width
            additionalProperties = tweetMedia.additionalProperties.toMutableMap()
        }

        /** Preview URL. */
        fun mediaUrl(mediaUrl: String) = mediaUrl(JsonField.of(mediaUrl))

        /** Sets [Builder.mediaUrl] to an arbitrary JSON value. */
        fun mediaUrl(mediaUrl: JsonField<String>) = apply { this.mediaUrl = mediaUrl }

        fun type(type: Type) = type(JsonField.of(type))

        /** Sets [Builder.type] to an arbitrary JSON value. */
        fun type(type: JsonField<Type>) = apply { this.type = type }

        /** Tweet media link. */
        fun url(url: String) = url(JsonField.of(url))

        /** Sets [Builder.url] to an arbitrary JSON value. */
        fun url(url: JsonField<String>) = apply { this.url = url }

        /** Media entity ID. */
        fun id(id: String) = id(JsonField.of(id))

        /** Sets [Builder.id] to an arbitrary JSON value. */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /** Adult-content warning. */
        fun adultContent(adultContent: Boolean) = adultContent(JsonField.of(adultContent))

        /** Sets [Builder.adultContent] to an arbitrary JSON value. */
        fun adultContent(adultContent: JsonField<Boolean>) = apply {
            this.adultContent = adultContent
        }

        /** Direct download permission. */
        fun allowDownload(allowDownload: Boolean) = allowDownload(JsonField.of(allowDownload))

        /** Sets [Builder.allowDownload] to an arbitrary JSON value. */
        fun allowDownload(allowDownload: JsonField<Boolean>) = apply {
            this.allowDownload = allowDownload
        }

        /** Accessibility text. */
        fun altText(altText: String) = altText(JsonField.of(altText))

        /** Sets [Builder.altText] to an arbitrary JSON value. */
        fun altText(altText: JsonField<String>) = apply { this.altText = altText }

        /** Video width and height ratio. */
        fun aspectRatio(aspectRatio: List<Long>) = aspectRatio(JsonField.of(aspectRatio))

        /** Sets [Builder.aspectRatio] to an arbitrary JSON value. */
        fun aspectRatio(aspectRatio: JsonField<List<Long>>) = apply {
            this.aspectRatio = aspectRatio.map { it.toMutableList() }
        }

        /**
         * Adds a single [Long] to [aspectRatio].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addAspectRatio(aspectRatio: Long) = apply {
            this.aspectRatio =
                (this.aspectRatio ?: JsonField.of(mutableListOf())).also {
                    checkKnown("aspectRatio", it).add(aspectRatio)
                }
        }

        /**
         * Why X made the media unavailable, such as `Dmcaed` after a copyright report. Omitted on
         * available media.
         */
        fun availabilityReason(availabilityReason: String) =
            availabilityReason(JsonField.of(availabilityReason))

        /** Sets [Builder.availabilityReason] to an arbitrary JSON value. */
        fun availabilityReason(availabilityReason: JsonField<String>) = apply {
            this.availabilityReason = availabilityReason
        }

        /** Availability state, such as `Available` or `Unavailable`. */
        fun availabilityStatus(availabilityStatus: String) =
            availabilityStatus(JsonField.of(availabilityStatus))

        /** Sets [Builder.availabilityStatus] to an arbitrary JSON value. */
        fun availabilityStatus(availabilityStatus: JsonField<String>) = apply {
            this.availabilityStatus = availabilityStatus
        }

        /** Media description. */
        fun description(description: String) = description(JsonField.of(description))

        /** Sets [Builder.description] to an arbitrary JSON value. */
        fun description(description: JsonField<String>) = apply { this.description = description }

        /** Display URL. */
        fun displayUrl(displayUrl: String) = displayUrl(JsonField.of(displayUrl))

        /** Sets [Builder.displayUrl] to an arbitrary JSON value. */
        fun displayUrl(displayUrl: JsonField<String>) = apply { this.displayUrl = displayUrl }

        /** Video duration in milliseconds. */
        fun durationMillis(durationMillis: Long) = durationMillis(JsonField.of(durationMillis))

        /** Sets [Builder.durationMillis] to an arbitrary JSON value. */
        fun durationMillis(durationMillis: JsonField<Long>) = apply {
            this.durationMillis = durationMillis
        }

        /** Embeddable status. */
        fun embeddable(embeddable: Boolean) = embeddable(JsonField.of(embeddable))

        /** Sets [Builder.embeddable] to an arbitrary JSON value. */
        fun embeddable(embeddable: JsonField<Boolean>) = apply { this.embeddable = embeddable }

        /** Expanded media URL. */
        fun expandedUrl(expandedUrl: String) = expandedUrl(JsonField.of(expandedUrl))

        /** Sets [Builder.expandedUrl] to an arbitrary JSON value. */
        fun expandedUrl(expandedUrl: JsonField<String>) = apply { this.expandedUrl = expandedUrl }

        /** Face crop rectangles by size. */
        fun faceRects(faceRects: FaceRects) = faceRects(JsonField.of(faceRects))

        /** Sets [Builder.faceRects] to an arbitrary JSON value. */
        fun faceRects(faceRects: JsonField<FaceRects>) = apply { this.faceRects = faceRects }

        /** Suggested image crops. */
        fun focusRects(focusRects: List<FocusRect>) = focusRects(JsonField.of(focusRects))

        /** Sets [Builder.focusRects] to an arbitrary JSON value. */
        fun focusRects(focusRects: JsonField<List<FocusRect>>) = apply {
            this.focusRects = focusRects.map { it.toMutableList() }
        }

        /**
         * Adds a single [FocusRect] to [focusRects].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addFocusRect(focusRect: FocusRect) = apply {
            focusRects =
                (focusRects ?: JsonField.of(mutableListOf())).also {
                    checkKnown("focusRects", it).add(focusRect)
                }
        }

        /** Graphic-violence warning. */
        fun graphicViolence(graphicViolence: Boolean) =
            graphicViolence(JsonField.of(graphicViolence))

        /** Sets [Builder.graphicViolence] to an arbitrary JSON value. */
        fun graphicViolence(graphicViolence: JsonField<Boolean>) = apply {
            this.graphicViolence = graphicViolence
        }

        /** Prompt a Grok image came from. Absent on other media & when X sends no prompt. */
        fun grokImageAnnotation(grokImageAnnotation: GrokImageAnnotation) =
            grokImageAnnotation(JsonField.of(grokImageAnnotation))

        /** Sets [Builder.grokImageAnnotation] to an arbitrary JSON value. */
        fun grokImageAnnotation(grokImageAnnotation: JsonField<GrokImageAnnotation>) = apply {
            this.grokImageAnnotation = grokImageAnnotation
        }

        /** Grok post ID associated with the media. */
        fun grokPostId(grokPostId: String) = grokPostId(JsonField.of(grokPostId))

        /** Sets [Builder.grokPostId] to an arbitrary JSON value. */
        fun grokPostId(grokPostId: JsonField<String>) = apply { this.grokPostId = grokPostId }

        /** Original height. */
        fun height(height: Long) = height(JsonField.of(height))

        /** Sets [Builder.height] to an arbitrary JSON value. */
        fun height(height: JsonField<Long>) = apply { this.height = height }

        /** Tweet text offsets. */
        fun indices(indices: List<Long>) = indices(JsonField.of(indices))

        /** Sets [Builder.indices] to an arbitrary JSON value. */
        fun indices(indices: JsonField<List<Long>>) = apply {
            this.indices = indices.map { it.toMutableList() }
        }

        /**
         * Adds a single [Long] to [indices].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addIndex(index: Long) = apply {
            indices =
                (indices ?: JsonField.of(mutableListOf())).also {
                    checkKnown("indices", it).add(index)
                }
        }

        /** Stable X media key. */
        fun mediaKey(mediaKey: String) = mediaKey(JsonField.of(mediaKey))

        /** Sets [Builder.mediaKey] to an arbitrary JSON value. */
        fun mediaKey(mediaKey: JsonField<String>) = apply { this.mediaKey = mediaKey }

        /** Monetization status. */
        fun monetizable(monetizable: Boolean) = monetizable(JsonField.of(monetizable))

        /** Sets [Builder.monetizable] to an arbitrary JSON value. */
        fun monetizable(monetizable: JsonField<Boolean>) = apply { this.monetizable = monetizable }

        fun otherSensitiveContent(otherSensitiveContent: Boolean) =
            otherSensitiveContent(JsonField.of(otherSensitiveContent))

        /** Sets [Builder.otherSensitiveContent] to an arbitrary JSON value. */
        fun otherSensitiveContent(otherSensitiveContent: JsonField<Boolean>) = apply {
            this.otherSensitiveContent = otherSensitiveContent
        }

        /** Named media renditions and resize modes. */
        fun sizes(sizes: Sizes) = sizes(JsonField.of(sizes))

        /** Sets [Builder.sizes] to an arbitrary JSON value. */
        fun sizes(sizes: JsonField<Sizes>) = apply { this.sizes = sizes }

        /** Source tweet ID for copied media. */
        fun sourceStatusId(sourceStatusId: String) = sourceStatusId(JsonField.of(sourceStatusId))

        /** Sets [Builder.sourceStatusId] to an arbitrary JSON value. */
        fun sourceStatusId(sourceStatusId: JsonField<String>) = apply {
            this.sourceStatusId = sourceStatusId
        }

        /** Public X profile. */
        fun sourceUser(sourceUser: UserProfile) = sourceUser(JsonField.of(sourceUser))

        /** Sets [Builder.sourceUser] to an arbitrary JSON value. */
        fun sourceUser(sourceUser: JsonField<UserProfile>) = apply { this.sourceUser = sourceUser }

        /** Source profile ID for copied media. */
        fun sourceUserId(sourceUserId: String) = sourceUserId(JsonField.of(sourceUserId))

        /** Sets [Builder.sourceUserId] to an arbitrary JSON value. */
        fun sourceUserId(sourceUserId: JsonField<String>) = apply {
            this.sourceUserId = sourceUserId
        }

        /** Public profiles tagged in the media. */
        fun tags(tags: List<Tag>) = tags(JsonField.of(tags))

        /** Sets [Builder.tags] to an arbitrary JSON value. */
        fun tags(tags: JsonField<List<Tag>>) = apply {
            this.tags = tags.map { it.toMutableList() }
        }

        /**
         * Adds a single [Tag] to [tags].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addTag(tag: Tag) = apply {
            tags =
                (tags ?: JsonField.of(mutableListOf())).also {
                    checkKnown("tags", it).add(tag)
                }
        }

        /** Media title. */
        fun title(title: String) = title(JsonField.of(title))

        /** Sets [Builder.title] to an arbitrary JSON value. */
        fun title(title: JsonField<String>) = apply { this.title = title }

        /** Video encodings in source order. */
        fun videoVariants(videoVariants: List<VideoVariant>) =
            videoVariants(JsonField.of(videoVariants))

        /** Sets [Builder.videoVariants] to an arbitrary JSON value. */
        fun videoVariants(videoVariants: JsonField<List<VideoVariant>>) = apply {
            this.videoVariants = videoVariants.map { it.toMutableList() }
        }

        /**
         * Adds a single [VideoVariant] to [videoVariants].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addVideoVariant(videoVariant: VideoVariant) = apply {
            videoVariants =
                (videoVariants ?: JsonField.of(mutableListOf())).also {
                    checkKnown("videoVariants", it).add(videoVariant)
                }
        }

        /** Public destination URL. */
        fun visitSiteUrl(visitSiteUrl: String) = visitSiteUrl(JsonField.of(visitSiteUrl))

        /** Sets [Builder.visitSiteUrl] to an arbitrary JSON value. */
        fun visitSiteUrl(visitSiteUrl: JsonField<String>) = apply {
            this.visitSiteUrl = visitSiteUrl
        }

        /** Public media action URL. */
        fun watchNowUrl(watchNowUrl: String) = watchNowUrl(JsonField.of(watchNowUrl))

        /** Sets [Builder.watchNowUrl] to an arbitrary JSON value. */
        fun watchNowUrl(watchNowUrl: JsonField<String>) = apply { this.watchNowUrl = watchNowUrl }

        /** Original width. */
        fun width(width: Long) = width(JsonField.of(width))

        /** Sets [Builder.width] to an arbitrary JSON value. */
        fun width(width: JsonField<Long>) = apply { this.width = width }

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
         * Returns an immutable instance of [TweetMedia].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```kotlin
         * .mediaUrl()
         * .type()
         * .url()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): TweetMedia =
            TweetMedia(
                checkRequired("mediaUrl", mediaUrl),
                checkRequired("type", type),
                checkRequired("url", url),
                id,
                adultContent,
                allowDownload,
                altText,
                (aspectRatio ?: JsonMissing.of()).map { it.toImmutable() },
                availabilityReason,
                availabilityStatus,
                description,
                displayUrl,
                durationMillis,
                embeddable,
                expandedUrl,
                faceRects,
                (focusRects ?: JsonMissing.of()).map { it.toImmutable() },
                graphicViolence,
                grokImageAnnotation,
                grokPostId,
                height,
                (indices ?: JsonMissing.of()).map { it.toImmutable() },
                mediaKey,
                monetizable,
                otherSensitiveContent,
                sizes,
                sourceStatusId,
                sourceUser,
                sourceUserId,
                (tags ?: JsonMissing.of()).map { it.toImmutable() },
                title,
                (videoVariants ?: JsonMissing.of()).map { it.toImmutable() },
                visitSiteUrl,
                watchNowUrl,
                width,
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
    fun validate(): TweetMedia = apply {
        if (validated) {
            return@apply
        }

        mediaUrl()
        type().validate()
        url()
        id()
        adultContent()
        allowDownload()
        altText()
        aspectRatio()
        availabilityReason()
        availabilityStatus()
        description()
        displayUrl()
        durationMillis()
        embeddable()
        expandedUrl()
        faceRects()?.validate()
        focusRects()?.forEach { it.validate() }
        graphicViolence()
        grokImageAnnotation()?.validate()
        grokPostId()
        height()
        indices()
        mediaKey()
        monetizable()
        otherSensitiveContent()
        sizes()?.validate()
        sourceStatusId()
        sourceUser()?.validate()
        sourceUserId()
        tags()?.forEach { it.validate() }
        title()
        videoVariants()?.forEach { it.validate() }
        visitSiteUrl()
        watchNowUrl()
        width()
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
        (if (mediaUrl.asKnown() == null) 0 else 1) +
            (type.asKnown()?.validity() ?: 0) +
            (if (url.asKnown() == null) 0 else 1) +
            (if (id.asKnown() == null) 0 else 1) +
            (if (adultContent.asKnown() == null) 0 else 1) +
            (if (allowDownload.asKnown() == null) 0 else 1) +
            (if (altText.asKnown() == null) 0 else 1) +
            (aspectRatio.asKnown()?.size ?: 0) +
            (if (availabilityReason.asKnown() == null) 0 else 1) +
            (if (availabilityStatus.asKnown() == null) 0 else 1) +
            (if (description.asKnown() == null) 0 else 1) +
            (if (displayUrl.asKnown() == null) 0 else 1) +
            (if (durationMillis.asKnown() == null) 0 else 1) +
            (if (embeddable.asKnown() == null) 0 else 1) +
            (if (expandedUrl.asKnown() == null) 0 else 1) +
            (faceRects.asKnown()?.validity() ?: 0) +
            (focusRects.asKnown()?.sumOf { it.validity() } ?: 0) +
            (if (graphicViolence.asKnown() == null) 0 else 1) +
            (grokImageAnnotation.asKnown()?.validity() ?: 0) +
            (if (grokPostId.asKnown() == null) 0 else 1) +
            (if (height.asKnown() == null) 0 else 1) +
            (indices.asKnown()?.size ?: 0) +
            (if (mediaKey.asKnown() == null) 0 else 1) +
            (if (monetizable.asKnown() == null) 0 else 1) +
            (if (otherSensitiveContent.asKnown() == null) 0 else 1) +
            (sizes.asKnown()?.validity() ?: 0) +
            (if (sourceStatusId.asKnown() == null) 0 else 1) +
            (sourceUser.asKnown()?.validity() ?: 0) +
            (if (sourceUserId.asKnown() == null) 0 else 1) +
            (tags.asKnown()?.sumOf { it.validity() } ?: 0) +
            (if (title.asKnown() == null) 0 else 1) +
            (videoVariants.asKnown()?.sumOf { it.validity() } ?: 0) +
            (if (visitSiteUrl.asKnown() == null) 0 else 1) +
            (if (watchNowUrl.asKnown() == null) 0 else 1) +
            (if (width.asKnown() == null) 0 else 1)

    class Type @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

        /** Returns this class instance's raw value. */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            val PHOTO = of("photo")

            val VIDEO = of("video")

            val ANIMATED_GIF = of("animated_gif")

            fun of(value: String) = Type(JsonField.of(value))
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            PHOTO,
            VIDEO,
            ANIMATED_GIF,
        }

        /**
         * An enum containing [Type]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Type] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            PHOTO,
            VIDEO,
            ANIMATED_GIF,
            /** An enum member indicating that [Type] was instantiated with an unknown value. */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         */
        fun value(): Value =
            when (this) {
                PHOTO -> Value.PHOTO
                VIDEO -> Value.VIDEO
                ANIMATED_GIF -> Value.ANIMATED_GIF
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
                PHOTO -> Known.PHOTO
                VIDEO -> Known.VIDEO
                ANIMATED_GIF -> Known.ANIMATED_GIF
                else -> throw XTwitterScraperInvalidDataException("Unknown Type: $value")
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
        fun validate(): Type = apply {
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

            return other is Type && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    /** Face crop rectangles by size. */
    class FaceRects
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

            /** Returns a mutable builder for constructing an instance of [FaceRects]. */
            fun builder() = Builder()
        }

        /** A builder for [FaceRects]. */
        class Builder internal constructor() {

            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(faceRects: FaceRects) = apply {
                additionalProperties = faceRects.additionalProperties.toMutableMap()
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
             * Returns an immutable instance of [FaceRects].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): FaceRects = FaceRects(additionalProperties.toImmutable())
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
        fun validate(): FaceRects = apply {
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

            return other is FaceRects && additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() = "FaceRects{additionalProperties=$additionalProperties}"
    }

    class FocusRect
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val h: JsonField<Long>,
        private val w: JsonField<Long>,
        private val x: JsonField<Long>,
        private val y: JsonField<Long>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("h") @ExcludeMissing h: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("w") @ExcludeMissing w: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("x") @ExcludeMissing x: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("y") @ExcludeMissing y: JsonField<Long> = JsonMissing.of(),
        ) : this(h, w, x, y, mutableMapOf())

        /**
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or
         *   is unexpectedly missing or null.
         */
        fun h(): Long = h.getRequired("h")

        /**
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or
         *   is unexpectedly missing or null.
         */
        fun w(): Long = w.getRequired("w")

        /**
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or
         *   is unexpectedly missing or null.
         */
        fun x(): Long = x.getRequired("x")

        /**
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or
         *   is unexpectedly missing or null.
         */
        fun y(): Long = y.getRequired("y")

        /**
         * Returns the raw JSON value of [h].
         *
         * Unlike [h], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("h") @ExcludeMissing fun _h(): JsonField<Long> = h

        /**
         * Returns the raw JSON value of [w].
         *
         * Unlike [w], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("w") @ExcludeMissing fun _w(): JsonField<Long> = w

        /**
         * Returns the raw JSON value of [x].
         *
         * Unlike [x], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("x") @ExcludeMissing fun _x(): JsonField<Long> = x

        /**
         * Returns the raw JSON value of [y].
         *
         * Unlike [y], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("y") @ExcludeMissing fun _y(): JsonField<Long> = y

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
             * Returns a mutable builder for constructing an instance of [FocusRect].
             *
             * The following fields are required:
             * ```kotlin
             * .h()
             * .w()
             * .x()
             * .y()
             * ```
             */
            fun builder() = Builder()
        }

        /** A builder for [FocusRect]. */
        class Builder internal constructor() {

            private var h: JsonField<Long>? = null
            private var w: JsonField<Long>? = null
            private var x: JsonField<Long>? = null
            private var y: JsonField<Long>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(focusRect: FocusRect) = apply {
                h = focusRect.h
                w = focusRect.w
                x = focusRect.x
                y = focusRect.y
                additionalProperties = focusRect.additionalProperties.toMutableMap()
            }

            fun h(h: Long) = h(JsonField.of(h))

            /** Sets [Builder.h] to an arbitrary JSON value. */
            fun h(h: JsonField<Long>) = apply { this.h = h }

            fun w(w: Long) = w(JsonField.of(w))

            /** Sets [Builder.w] to an arbitrary JSON value. */
            fun w(w: JsonField<Long>) = apply { this.w = w }

            fun x(x: Long) = x(JsonField.of(x))

            /** Sets [Builder.x] to an arbitrary JSON value. */
            fun x(x: JsonField<Long>) = apply { this.x = x }

            fun y(y: Long) = y(JsonField.of(y))

            /** Sets [Builder.y] to an arbitrary JSON value. */
            fun y(y: JsonField<Long>) = apply { this.y = y }

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
             * Returns an immutable instance of [FocusRect].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             *
             * The following fields are required:
             * ```kotlin
             * .h()
             * .w()
             * .x()
             * .y()
             * ```
             *
             * @throws IllegalStateException if any required field is unset.
             */
            fun build(): FocusRect =
                FocusRect(
                    checkRequired("h", h),
                    checkRequired("w", w),
                    checkRequired("x", x),
                    checkRequired("y", y),
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
        fun validate(): FocusRect = apply {
            if (validated) {
                return@apply
            }

            h()
            w()
            x()
            y()
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
            (if (h.asKnown() == null) 0 else 1) +
                (if (w.asKnown() == null) 0 else 1) +
                (if (x.asKnown() == null) 0 else 1) +
                (if (y.asKnown() == null) 0 else 1)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is FocusRect &&
                h == other.h &&
                w == other.w &&
                x == other.x &&
                y == other.y &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(h, w, x, y, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "FocusRect{h=$h, w=$w, x=$x, y=$y, additionalProperties=$additionalProperties}"
    }

    /** Prompt a Grok image came from. Absent on other media & when X sends no prompt. */
    class GrokImageAnnotation
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val prompt: JsonField<String>,
        private val upsampledPrompt: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("prompt") @ExcludeMissing prompt: JsonField<String> = JsonMissing.of(),
            @JsonProperty("upsampledPrompt")
            @ExcludeMissing
            upsampledPrompt: JsonField<String> = JsonMissing.of(),
        ) : this(prompt, upsampledPrompt, mutableMapOf())

        /**
         * Prompt the image came from.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun prompt(): String? = prompt.getNullable("prompt")

        /**
         * Grok's longer rewrite of the prompt.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun upsampledPrompt(): String? = upsampledPrompt.getNullable("upsampledPrompt")

        /**
         * Returns the raw JSON value of [prompt].
         *
         * Unlike [prompt], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("prompt") @ExcludeMissing fun _prompt(): JsonField<String> = prompt

        /**
         * Returns the raw JSON value of [upsampledPrompt].
         *
         * Unlike [upsampledPrompt], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("upsampledPrompt")
        @ExcludeMissing
        fun _upsampledPrompt(): JsonField<String> = upsampledPrompt

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

            /** Returns a mutable builder for constructing an instance of [GrokImageAnnotation]. */
            fun builder() = Builder()
        }

        /** A builder for [GrokImageAnnotation]. */
        class Builder internal constructor() {

            private var prompt: JsonField<String> = JsonMissing.of()
            private var upsampledPrompt: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(grokImageAnnotation: GrokImageAnnotation) = apply {
                prompt = grokImageAnnotation.prompt
                upsampledPrompt = grokImageAnnotation.upsampledPrompt
                additionalProperties = grokImageAnnotation.additionalProperties.toMutableMap()
            }

            /** Prompt the image came from. */
            fun prompt(prompt: String) = prompt(JsonField.of(prompt))

            /** Sets [Builder.prompt] to an arbitrary JSON value. */
            fun prompt(prompt: JsonField<String>) = apply { this.prompt = prompt }

            /** Grok's longer rewrite of the prompt. */
            fun upsampledPrompt(upsampledPrompt: String) =
                upsampledPrompt(JsonField.of(upsampledPrompt))

            /** Sets [Builder.upsampledPrompt] to an arbitrary JSON value. */
            fun upsampledPrompt(upsampledPrompt: JsonField<String>) = apply {
                this.upsampledPrompt = upsampledPrompt
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
             * Returns an immutable instance of [GrokImageAnnotation].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): GrokImageAnnotation =
                GrokImageAnnotation(prompt, upsampledPrompt, additionalProperties.toMutableMap())
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
        fun validate(): GrokImageAnnotation = apply {
            if (validated) {
                return@apply
            }

            prompt()
            upsampledPrompt()
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
            (if (prompt.asKnown() == null) 0 else 1) +
                (if (upsampledPrompt.asKnown() == null) 0 else 1)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is GrokImageAnnotation &&
                prompt == other.prompt &&
                upsampledPrompt == other.upsampledPrompt &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(prompt, upsampledPrompt, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "GrokImageAnnotation{prompt=$prompt, upsampledPrompt=$upsampledPrompt, additionalProperties=$additionalProperties}"
    }

    /** Named media renditions and resize modes. */
    class Sizes
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

            /** Returns a mutable builder for constructing an instance of [Sizes]. */
            fun builder() = Builder()
        }

        /** A builder for [Sizes]. */
        class Builder internal constructor() {

            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(sizes: Sizes) = apply {
                additionalProperties = sizes.additionalProperties.toMutableMap()
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
             * Returns an immutable instance of [Sizes].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): Sizes = Sizes(additionalProperties.toImmutable())
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
        fun validate(): Sizes = apply {
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

            return other is Sizes && additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() = "Sizes{additionalProperties=$additionalProperties}"
    }

    class Tag
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val name: JsonField<String>,
        private val screenName: JsonField<String>,
        private val type: JsonField<String>,
        private val userId: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
            @JsonProperty("screen_name")
            @ExcludeMissing
            screenName: JsonField<String> = JsonMissing.of(),
            @JsonProperty("type") @ExcludeMissing type: JsonField<String> = JsonMissing.of(),
            @JsonProperty("user_id") @ExcludeMissing userId: JsonField<String> = JsonMissing.of(),
        ) : this(name, screenName, type, userId, mutableMapOf())

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun name(): String? = name.getNullable("name")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun screenName(): String? = screenName.getNullable("screen_name")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun type(): String? = type.getNullable("type")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun userId(): String? = userId.getNullable("user_id")

        /**
         * Returns the raw JSON value of [name].
         *
         * Unlike [name], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<String> = name

        /**
         * Returns the raw JSON value of [screenName].
         *
         * Unlike [screenName], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("screen_name")
        @ExcludeMissing
        fun _screenName(): JsonField<String> = screenName

        /**
         * Returns the raw JSON value of [type].
         *
         * Unlike [type], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<String> = type

        /**
         * Returns the raw JSON value of [userId].
         *
         * Unlike [userId], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("user_id") @ExcludeMissing fun _userId(): JsonField<String> = userId

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

            /** Returns a mutable builder for constructing an instance of [Tag]. */
            fun builder() = Builder()
        }

        /** A builder for [Tag]. */
        class Builder internal constructor() {

            private var name: JsonField<String> = JsonMissing.of()
            private var screenName: JsonField<String> = JsonMissing.of()
            private var type: JsonField<String> = JsonMissing.of()
            private var userId: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(tag: Tag) = apply {
                name = tag.name
                screenName = tag.screenName
                type = tag.type
                userId = tag.userId
                additionalProperties = tag.additionalProperties.toMutableMap()
            }

            fun name(name: String) = name(JsonField.of(name))

            /** Sets [Builder.name] to an arbitrary JSON value. */
            fun name(name: JsonField<String>) = apply { this.name = name }

            fun screenName(screenName: String) = screenName(JsonField.of(screenName))

            /** Sets [Builder.screenName] to an arbitrary JSON value. */
            fun screenName(screenName: JsonField<String>) = apply { this.screenName = screenName }

            fun type(type: String) = type(JsonField.of(type))

            /** Sets [Builder.type] to an arbitrary JSON value. */
            fun type(type: JsonField<String>) = apply { this.type = type }

            fun userId(userId: String) = userId(JsonField.of(userId))

            /** Sets [Builder.userId] to an arbitrary JSON value. */
            fun userId(userId: JsonField<String>) = apply { this.userId = userId }

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
             * Returns an immutable instance of [Tag].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): Tag =
                Tag(name, screenName, type, userId, additionalProperties.toMutableMap())
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
        fun validate(): Tag = apply {
            if (validated) {
                return@apply
            }

            name()
            screenName()
            type()
            userId()
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
            (if (name.asKnown() == null) 0 else 1) +
                (if (screenName.asKnown() == null) 0 else 1) +
                (if (type.asKnown() == null) 0 else 1) +
                (if (userId.asKnown() == null) 0 else 1)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Tag &&
                name == other.name &&
                screenName == other.screenName &&
                type == other.type &&
                userId == other.userId &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(name, screenName, type, userId, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Tag{name=$name, screenName=$screenName, type=$type, userId=$userId, additionalProperties=$additionalProperties}"
    }

    class VideoVariant
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val contentType: JsonField<String>,
        private val url: JsonField<String>,
        private val bitrate: JsonField<Long>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("contentType")
            @ExcludeMissing
            contentType: JsonField<String> = JsonMissing.of(),
            @JsonProperty("url") @ExcludeMissing url: JsonField<String> = JsonMissing.of(),
            @JsonProperty("bitrate") @ExcludeMissing bitrate: JsonField<Long> = JsonMissing.of(),
        ) : this(contentType, url, bitrate, mutableMapOf())

        /**
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or
         *   is unexpectedly missing or null.
         */
        fun contentType(): String = contentType.getRequired("contentType")

        /**
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or
         *   is unexpectedly missing or null.
         */
        fun url(): String = url.getRequired("url")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun bitrate(): Long? = bitrate.getNullable("bitrate")

        /**
         * Returns the raw JSON value of [contentType].
         *
         * Unlike [contentType], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("contentType")
        @ExcludeMissing
        fun _contentType(): JsonField<String> = contentType

        /**
         * Returns the raw JSON value of [url].
         *
         * Unlike [url], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("url") @ExcludeMissing fun _url(): JsonField<String> = url

        /**
         * Returns the raw JSON value of [bitrate].
         *
         * Unlike [bitrate], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("bitrate") @ExcludeMissing fun _bitrate(): JsonField<Long> = bitrate

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
             * Returns a mutable builder for constructing an instance of [VideoVariant].
             *
             * The following fields are required:
             * ```kotlin
             * .contentType()
             * .url()
             * ```
             */
            fun builder() = Builder()
        }

        /** A builder for [VideoVariant]. */
        class Builder internal constructor() {

            private var contentType: JsonField<String>? = null
            private var url: JsonField<String>? = null
            private var bitrate: JsonField<Long> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(videoVariant: VideoVariant) = apply {
                contentType = videoVariant.contentType
                url = videoVariant.url
                bitrate = videoVariant.bitrate
                additionalProperties = videoVariant.additionalProperties.toMutableMap()
            }

            fun contentType(contentType: String) = contentType(JsonField.of(contentType))

            /** Sets [Builder.contentType] to an arbitrary JSON value. */
            fun contentType(contentType: JsonField<String>) = apply {
                this.contentType = contentType
            }

            fun url(url: String) = url(JsonField.of(url))

            /** Sets [Builder.url] to an arbitrary JSON value. */
            fun url(url: JsonField<String>) = apply { this.url = url }

            fun bitrate(bitrate: Long) = bitrate(JsonField.of(bitrate))

            /** Sets [Builder.bitrate] to an arbitrary JSON value. */
            fun bitrate(bitrate: JsonField<Long>) = apply { this.bitrate = bitrate }

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
             * Returns an immutable instance of [VideoVariant].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             *
             * The following fields are required:
             * ```kotlin
             * .contentType()
             * .url()
             * ```
             *
             * @throws IllegalStateException if any required field is unset.
             */
            fun build(): VideoVariant =
                VideoVariant(
                    checkRequired("contentType", contentType),
                    checkRequired("url", url),
                    bitrate,
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
        fun validate(): VideoVariant = apply {
            if (validated) {
                return@apply
            }

            contentType()
            url()
            bitrate()
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
            (if (contentType.asKnown() == null) 0 else 1) +
                (if (url.asKnown() == null) 0 else 1) +
                (if (bitrate.asKnown() == null) 0 else 1)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is VideoVariant &&
                contentType == other.contentType &&
                url == other.url &&
                bitrate == other.bitrate &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(contentType, url, bitrate, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "VideoVariant{contentType=$contentType, url=$url, bitrate=$bitrate, additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is TweetMedia &&
            mediaUrl == other.mediaUrl &&
            type == other.type &&
            url == other.url &&
            id == other.id &&
            adultContent == other.adultContent &&
            allowDownload == other.allowDownload &&
            altText == other.altText &&
            aspectRatio == other.aspectRatio &&
            availabilityReason == other.availabilityReason &&
            availabilityStatus == other.availabilityStatus &&
            description == other.description &&
            displayUrl == other.displayUrl &&
            durationMillis == other.durationMillis &&
            embeddable == other.embeddable &&
            expandedUrl == other.expandedUrl &&
            faceRects == other.faceRects &&
            focusRects == other.focusRects &&
            graphicViolence == other.graphicViolence &&
            grokImageAnnotation == other.grokImageAnnotation &&
            grokPostId == other.grokPostId &&
            height == other.height &&
            indices == other.indices &&
            mediaKey == other.mediaKey &&
            monetizable == other.monetizable &&
            otherSensitiveContent == other.otherSensitiveContent &&
            sizes == other.sizes &&
            sourceStatusId == other.sourceStatusId &&
            sourceUser == other.sourceUser &&
            sourceUserId == other.sourceUserId &&
            tags == other.tags &&
            title == other.title &&
            videoVariants == other.videoVariants &&
            visitSiteUrl == other.visitSiteUrl &&
            watchNowUrl == other.watchNowUrl &&
            width == other.width &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            mediaUrl,
            type,
            url,
            id,
            adultContent,
            allowDownload,
            altText,
            aspectRatio,
            availabilityReason,
            availabilityStatus,
            description,
            displayUrl,
            durationMillis,
            embeddable,
            expandedUrl,
            faceRects,
            focusRects,
            graphicViolence,
            grokImageAnnotation,
            grokPostId,
            height,
            indices,
            mediaKey,
            monetizable,
            otherSensitiveContent,
            sizes,
            sourceStatusId,
            sourceUser,
            sourceUserId,
            tags,
            title,
            videoVariants,
            visitSiteUrl,
            watchNowUrl,
            width,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "TweetMedia{mediaUrl=$mediaUrl, type=$type, url=$url, id=$id, adultContent=$adultContent, allowDownload=$allowDownload, altText=$altText, aspectRatio=$aspectRatio, availabilityReason=$availabilityReason, availabilityStatus=$availabilityStatus, description=$description, displayUrl=$displayUrl, durationMillis=$durationMillis, embeddable=$embeddable, expandedUrl=$expandedUrl, faceRects=$faceRects, focusRects=$focusRects, graphicViolence=$graphicViolence, grokImageAnnotation=$grokImageAnnotation, grokPostId=$grokPostId, height=$height, indices=$indices, mediaKey=$mediaKey, monetizable=$monetizable, otherSensitiveContent=$otherSensitiveContent, sizes=$sizes, sourceStatusId=$sourceStatusId, sourceUser=$sourceUser, sourceUserId=$sourceUserId, tags=$tags, title=$title, videoVariants=$videoVariants, visitSiteUrl=$visitSiteUrl, watchNowUrl=$watchNowUrl, width=$width, additionalProperties=$additionalProperties}"
}
