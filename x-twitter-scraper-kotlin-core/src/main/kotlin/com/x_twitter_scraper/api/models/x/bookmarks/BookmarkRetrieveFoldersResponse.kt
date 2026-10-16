// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.bookmarks

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

class BookmarkRetrieveFoldersResponse
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val folders: JsonField<List<Folder>>,
    private val hasNextPage: JsonField<Boolean>,
    private val nextCursor: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("folders")
        @ExcludeMissing
        folders: JsonField<List<Folder>> = JsonMissing.of(),
        @JsonProperty("has_next_page")
        @ExcludeMissing
        hasNextPage: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("next_cursor")
        @ExcludeMissing
        nextCursor: JsonField<String> = JsonMissing.of(),
    ) : this(folders, hasNextPage, nextCursor, mutableMapOf())

    /**
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun folders(): List<Folder> = folders.getRequired("folders")

    /**
     * Whether another folder page is available
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun hasNextPage(): Boolean = hasNextPage.getRequired("has_next_page")

    /**
     * Cursor for the next folder page
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun nextCursor(): String = nextCursor.getRequired("next_cursor")

    /**
     * Returns the raw JSON value of [folders].
     *
     * Unlike [folders], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("folders") @ExcludeMissing fun _folders(): JsonField<List<Folder>> = folders

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
         * [BookmarkRetrieveFoldersResponse].
         *
         * The following fields are required:
         * ```kotlin
         * .folders()
         * .hasNextPage()
         * .nextCursor()
         * ```
         */
        fun builder() = Builder()
    }

    /** A builder for [BookmarkRetrieveFoldersResponse]. */
    class Builder internal constructor() {

        private var folders: JsonField<MutableList<Folder>>? = null
        private var hasNextPage: JsonField<Boolean>? = null
        private var nextCursor: JsonField<String>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        internal fun from(bookmarkRetrieveFoldersResponse: BookmarkRetrieveFoldersResponse) =
            apply {
                folders = bookmarkRetrieveFoldersResponse.folders.map { it.toMutableList() }
                hasNextPage = bookmarkRetrieveFoldersResponse.hasNextPage
                nextCursor = bookmarkRetrieveFoldersResponse.nextCursor
                additionalProperties =
                    bookmarkRetrieveFoldersResponse.additionalProperties.toMutableMap()
            }

        fun folders(folders: List<Folder>) = folders(JsonField.of(folders))

        /** Sets [Builder.folders] to an arbitrary JSON value. */
        fun folders(folders: JsonField<List<Folder>>) = apply {
            this.folders = folders.map { it.toMutableList() }
        }

        /**
         * Adds a single [Folder] to [folders].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addFolder(folder: Folder) = apply {
            folders =
                (folders ?: JsonField.of(mutableListOf())).also {
                    checkKnown("folders", it).add(folder)
                }
        }

        /** Whether another folder page is available */
        fun hasNextPage(hasNextPage: Boolean) = hasNextPage(JsonField.of(hasNextPage))

        /** Sets [Builder.hasNextPage] to an arbitrary JSON value. */
        fun hasNextPage(hasNextPage: JsonField<Boolean>) = apply { this.hasNextPage = hasNextPage }

        /** Cursor for the next folder page */
        fun nextCursor(nextCursor: String) = nextCursor(JsonField.of(nextCursor))

        /** Sets [Builder.nextCursor] to an arbitrary JSON value. */
        fun nextCursor(nextCursor: JsonField<String>) = apply { this.nextCursor = nextCursor }

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
         * Returns an immutable instance of [BookmarkRetrieveFoldersResponse].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```kotlin
         * .folders()
         * .hasNextPage()
         * .nextCursor()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BookmarkRetrieveFoldersResponse =
            BookmarkRetrieveFoldersResponse(
                checkRequired("folders", folders).map { it.toImmutable() },
                checkRequired("hasNextPage", hasNextPage),
                checkRequired("nextCursor", nextCursor),
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
    fun validate(): BookmarkRetrieveFoldersResponse = apply {
        if (validated) {
            return@apply
        }

        folders().forEach { it.validate() }
        hasNextPage()
        nextCursor()
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
        (folders.asKnown()?.sumOf { it.validity() } ?: 0) +
            (if (hasNextPage.asKnown() == null) 0 else 1) +
            (if (nextCursor.asKnown() == null) 0 else 1)

    /** Bookmark folder and its optional public cover image. */
    class Folder
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val id: JsonField<String>,
        private val name: JsonField<String>,
        private val media: JsonField<Media>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
            @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
            @JsonProperty("media") @ExcludeMissing media: JsonField<Media> = JsonMissing.of(),
        ) : this(id, name, media, mutableMapOf())

        /**
         * Folder ID.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or
         *   is unexpectedly missing or null.
         */
        fun id(): String = id.getRequired("id")

        /**
         * Folder name.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or
         *   is unexpectedly missing or null.
         */
        fun name(): String = name.getRequired("name")

        /**
         * Public folder cover image metadata.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun media(): Media? = media.getNullable("media")

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
         * Returns the raw JSON value of [media].
         *
         * Unlike [media], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("media") @ExcludeMissing fun _media(): JsonField<Media> = media

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
             * Returns a mutable builder for constructing an instance of [Folder].
             *
             * The following fields are required:
             * ```kotlin
             * .id()
             * .name()
             * ```
             */
            fun builder() = Builder()
        }

        /** A builder for [Folder]. */
        class Builder internal constructor() {

            private var id: JsonField<String>? = null
            private var name: JsonField<String>? = null
            private var media: JsonField<Media> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(folder: Folder) = apply {
                id = folder.id
                name = folder.name
                media = folder.media
                additionalProperties = folder.additionalProperties.toMutableMap()
            }

            /** Folder ID. */
            fun id(id: String) = id(JsonField.of(id))

            /** Sets [Builder.id] to an arbitrary JSON value. */
            fun id(id: JsonField<String>) = apply { this.id = id }

            /** Folder name. */
            fun name(name: String) = name(JsonField.of(name))

            /** Sets [Builder.name] to an arbitrary JSON value. */
            fun name(name: JsonField<String>) = apply { this.name = name }

            /** Public folder cover image metadata. */
            fun media(media: Media) = media(JsonField.of(media))

            /** Sets [Builder.media] to an arbitrary JSON value. */
            fun media(media: JsonField<Media>) = apply { this.media = media }

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
             * Returns an immutable instance of [Folder].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             *
             * The following fields are required:
             * ```kotlin
             * .id()
             * .name()
             * ```
             *
             * @throws IllegalStateException if any required field is unset.
             */
            fun build(): Folder =
                Folder(
                    checkRequired("id", id),
                    checkRequired("name", name),
                    media,
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
        fun validate(): Folder = apply {
            if (validated) {
                return@apply
            }

            id()
            name()
            media()?.validate()
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
                (media.asKnown()?.validity() ?: 0)

        /** Public folder cover image metadata. */
        class Media
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val id: JsonField<String>,
            private val mediaId: JsonField<String>,
            private val mediaKey: JsonField<String>,
            private val originalImageHeight: JsonField<Long>,
            private val originalImageUrl: JsonField<String>,
            private val originalImageWidth: JsonField<Long>,
            private val palette: JsonField<List<Palette>>,
            private val type: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
                @JsonProperty("mediaId")
                @ExcludeMissing
                mediaId: JsonField<String> = JsonMissing.of(),
                @JsonProperty("mediaKey")
                @ExcludeMissing
                mediaKey: JsonField<String> = JsonMissing.of(),
                @JsonProperty("originalImageHeight")
                @ExcludeMissing
                originalImageHeight: JsonField<Long> = JsonMissing.of(),
                @JsonProperty("originalImageUrl")
                @ExcludeMissing
                originalImageUrl: JsonField<String> = JsonMissing.of(),
                @JsonProperty("originalImageWidth")
                @ExcludeMissing
                originalImageWidth: JsonField<Long> = JsonMissing.of(),
                @JsonProperty("palette")
                @ExcludeMissing
                palette: JsonField<List<Palette>> = JsonMissing.of(),
                @JsonProperty("type") @ExcludeMissing type: JsonField<String> = JsonMissing.of(),
            ) : this(
                id,
                mediaId,
                mediaKey,
                originalImageHeight,
                originalImageUrl,
                originalImageWidth,
                palette,
                type,
                mutableMapOf(),
            )

            /**
             * Media object ID.
             *
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun id(): String? = id.getNullable("id")

            /**
             * Media ID.
             *
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun mediaId(): String? = mediaId.getNullable("mediaId")

            /**
             * Stable media key.
             *
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun mediaKey(): String? = mediaKey.getNullable("mediaKey")

            /**
             * Original image height.
             *
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun originalImageHeight(): Long? =
                originalImageHeight.getNullable("originalImageHeight")

            /**
             * Original image URL.
             *
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun originalImageUrl(): String? = originalImageUrl.getNullable("originalImageUrl")

            /**
             * Original image width.
             *
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun originalImageWidth(): Long? = originalImageWidth.getNullable("originalImageWidth")

            /**
             * Dominant image colors and their proportions.
             *
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun palette(): List<Palette>? = palette.getNullable("palette")

            /**
             * Media object type.
             *
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun type(): String? = type.getNullable("type")

            /**
             * Returns the raw JSON value of [id].
             *
             * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

            /**
             * Returns the raw JSON value of [mediaId].
             *
             * Unlike [mediaId], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("mediaId") @ExcludeMissing fun _mediaId(): JsonField<String> = mediaId

            /**
             * Returns the raw JSON value of [mediaKey].
             *
             * Unlike [mediaKey], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("mediaKey") @ExcludeMissing fun _mediaKey(): JsonField<String> = mediaKey

            /**
             * Returns the raw JSON value of [originalImageHeight].
             *
             * Unlike [originalImageHeight], this method doesn't throw if the JSON field has an
             * unexpected type.
             */
            @JsonProperty("originalImageHeight")
            @ExcludeMissing
            fun _originalImageHeight(): JsonField<Long> = originalImageHeight

            /**
             * Returns the raw JSON value of [originalImageUrl].
             *
             * Unlike [originalImageUrl], this method doesn't throw if the JSON field has an
             * unexpected type.
             */
            @JsonProperty("originalImageUrl")
            @ExcludeMissing
            fun _originalImageUrl(): JsonField<String> = originalImageUrl

            /**
             * Returns the raw JSON value of [originalImageWidth].
             *
             * Unlike [originalImageWidth], this method doesn't throw if the JSON field has an
             * unexpected type.
             */
            @JsonProperty("originalImageWidth")
            @ExcludeMissing
            fun _originalImageWidth(): JsonField<Long> = originalImageWidth

            /**
             * Returns the raw JSON value of [palette].
             *
             * Unlike [palette], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("palette")
            @ExcludeMissing
            fun _palette(): JsonField<List<Palette>> = palette

            /**
             * Returns the raw JSON value of [type].
             *
             * Unlike [type], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<String> = type

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

                /** Returns a mutable builder for constructing an instance of [Media]. */
                fun builder() = Builder()
            }

            /** A builder for [Media]. */
            class Builder internal constructor() {

                private var id: JsonField<String> = JsonMissing.of()
                private var mediaId: JsonField<String> = JsonMissing.of()
                private var mediaKey: JsonField<String> = JsonMissing.of()
                private var originalImageHeight: JsonField<Long> = JsonMissing.of()
                private var originalImageUrl: JsonField<String> = JsonMissing.of()
                private var originalImageWidth: JsonField<Long> = JsonMissing.of()
                private var palette: JsonField<MutableList<Palette>>? = null
                private var type: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                internal fun from(media: Media) = apply {
                    id = media.id
                    mediaId = media.mediaId
                    mediaKey = media.mediaKey
                    originalImageHeight = media.originalImageHeight
                    originalImageUrl = media.originalImageUrl
                    originalImageWidth = media.originalImageWidth
                    palette = media.palette.map { it.toMutableList() }
                    type = media.type
                    additionalProperties = media.additionalProperties.toMutableMap()
                }

                /** Media object ID. */
                fun id(id: String) = id(JsonField.of(id))

                /** Sets [Builder.id] to an arbitrary JSON value. */
                fun id(id: JsonField<String>) = apply { this.id = id }

                /** Media ID. */
                fun mediaId(mediaId: String) = mediaId(JsonField.of(mediaId))

                /** Sets [Builder.mediaId] to an arbitrary JSON value. */
                fun mediaId(mediaId: JsonField<String>) = apply { this.mediaId = mediaId }

                /** Stable media key. */
                fun mediaKey(mediaKey: String) = mediaKey(JsonField.of(mediaKey))

                /** Sets [Builder.mediaKey] to an arbitrary JSON value. */
                fun mediaKey(mediaKey: JsonField<String>) = apply { this.mediaKey = mediaKey }

                /** Original image height. */
                fun originalImageHeight(originalImageHeight: Long) =
                    originalImageHeight(JsonField.of(originalImageHeight))

                /** Sets [Builder.originalImageHeight] to an arbitrary JSON value. */
                fun originalImageHeight(originalImageHeight: JsonField<Long>) = apply {
                    this.originalImageHeight = originalImageHeight
                }

                /** Original image URL. */
                fun originalImageUrl(originalImageUrl: String) =
                    originalImageUrl(JsonField.of(originalImageUrl))

                /** Sets [Builder.originalImageUrl] to an arbitrary JSON value. */
                fun originalImageUrl(originalImageUrl: JsonField<String>) = apply {
                    this.originalImageUrl = originalImageUrl
                }

                /** Original image width. */
                fun originalImageWidth(originalImageWidth: Long) =
                    originalImageWidth(JsonField.of(originalImageWidth))

                /** Sets [Builder.originalImageWidth] to an arbitrary JSON value. */
                fun originalImageWidth(originalImageWidth: JsonField<Long>) = apply {
                    this.originalImageWidth = originalImageWidth
                }

                /** Dominant image colors and their proportions. */
                fun palette(palette: List<Palette>) = palette(JsonField.of(palette))

                /** Sets [Builder.palette] to an arbitrary JSON value. */
                fun palette(palette: JsonField<List<Palette>>) = apply {
                    this.palette = palette.map { it.toMutableList() }
                }

                /**
                 * Adds a single [Palette] to [palette].
                 *
                 * @throws IllegalStateException if the field was previously set to a non-list.
                 */
                fun addPalette(palette: Palette) = apply {
                    this.palette =
                        (this.palette ?: JsonField.of(mutableListOf())).also {
                            checkKnown("palette", it).add(palette)
                        }
                }

                /** Media object type. */
                fun type(type: String) = type(JsonField.of(type))

                /** Sets [Builder.type] to an arbitrary JSON value. */
                fun type(type: JsonField<String>) = apply { this.type = type }

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
                 * Returns an immutable instance of [Media].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 */
                fun build(): Media =
                    Media(
                        id,
                        mediaId,
                        mediaKey,
                        originalImageHeight,
                        originalImageUrl,
                        originalImageWidth,
                        (palette ?: JsonMissing.of()).map { it.toImmutable() },
                        type,
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
            fun validate(): Media = apply {
                if (validated) {
                    return@apply
                }

                id()
                mediaId()
                mediaKey()
                originalImageHeight()
                originalImageUrl()
                originalImageWidth()
                palette()?.forEach { it.validate() }
                type()
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
                    (if (mediaId.asKnown() == null) 0 else 1) +
                    (if (mediaKey.asKnown() == null) 0 else 1) +
                    (if (originalImageHeight.asKnown() == null) 0 else 1) +
                    (if (originalImageUrl.asKnown() == null) 0 else 1) +
                    (if (originalImageWidth.asKnown() == null) 0 else 1) +
                    (palette.asKnown()?.sumOf { it.validity() } ?: 0) +
                    (if (type.asKnown() == null) 0 else 1)

            class Palette
            @JsonCreator(mode = JsonCreator.Mode.DISABLED)
            private constructor(
                private val percentage: JsonField<Double>,
                private val rgb: JsonField<Rgb>,
                private val additionalProperties: MutableMap<String, JsonValue>,
            ) {

                @JsonCreator
                private constructor(
                    @JsonProperty("percentage")
                    @ExcludeMissing
                    percentage: JsonField<Double> = JsonMissing.of(),
                    @JsonProperty("rgb") @ExcludeMissing rgb: JsonField<Rgb> = JsonMissing.of(),
                ) : this(percentage, rgb, mutableMapOf())

                /**
                 * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected
                 *   type.
                 */
                fun percentage(): Double? = percentage.getNullable("percentage")

                /**
                 * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected
                 *   type.
                 */
                fun rgb(): Rgb? = rgb.getNullable("rgb")

                /**
                 * Returns the raw JSON value of [percentage].
                 *
                 * Unlike [percentage], this method doesn't throw if the JSON field has an
                 * unexpected type.
                 */
                @JsonProperty("percentage")
                @ExcludeMissing
                fun _percentage(): JsonField<Double> = percentage

                /**
                 * Returns the raw JSON value of [rgb].
                 *
                 * Unlike [rgb], this method doesn't throw if the JSON field has an unexpected type.
                 */
                @JsonProperty("rgb") @ExcludeMissing fun _rgb(): JsonField<Rgb> = rgb

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

                    /** Returns a mutable builder for constructing an instance of [Palette]. */
                    fun builder() = Builder()
                }

                /** A builder for [Palette]. */
                class Builder internal constructor() {

                    private var percentage: JsonField<Double> = JsonMissing.of()
                    private var rgb: JsonField<Rgb> = JsonMissing.of()
                    private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                    internal fun from(palette: Palette) = apply {
                        percentage = palette.percentage
                        rgb = palette.rgb
                        additionalProperties = palette.additionalProperties.toMutableMap()
                    }

                    fun percentage(percentage: Double) = percentage(JsonField.of(percentage))

                    /** Sets [Builder.percentage] to an arbitrary JSON value. */
                    fun percentage(percentage: JsonField<Double>) = apply {
                        this.percentage = percentage
                    }

                    fun rgb(rgb: Rgb) = rgb(JsonField.of(rgb))

                    /** Sets [Builder.rgb] to an arbitrary JSON value. */
                    fun rgb(rgb: JsonField<Rgb>) = apply { this.rgb = rgb }

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
                     * Returns an immutable instance of [Palette].
                     *
                     * Further updates to this [Builder] will not mutate the returned instance.
                     */
                    fun build(): Palette =
                        Palette(percentage, rgb, additionalProperties.toMutableMap())
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
                fun validate(): Palette = apply {
                    if (validated) {
                        return@apply
                    }

                    percentage()
                    rgb()?.validate()
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
                    (if (percentage.asKnown() == null) 0 else 1) + (rgb.asKnown()?.validity() ?: 0)

                class Rgb
                @JsonCreator(mode = JsonCreator.Mode.DISABLED)
                private constructor(
                    private val blue: JsonField<Double>,
                    private val green: JsonField<Double>,
                    private val red: JsonField<Double>,
                    private val additionalProperties: MutableMap<String, JsonValue>,
                ) {

                    @JsonCreator
                    private constructor(
                        @JsonProperty("blue")
                        @ExcludeMissing
                        blue: JsonField<Double> = JsonMissing.of(),
                        @JsonProperty("green")
                        @ExcludeMissing
                        green: JsonField<Double> = JsonMissing.of(),
                        @JsonProperty("red")
                        @ExcludeMissing
                        red: JsonField<Double> = JsonMissing.of(),
                    ) : this(blue, green, red, mutableMapOf())

                    /**
                     * @throws XTwitterScraperInvalidDataException if the JSON field has an
                     *   unexpected type.
                     */
                    fun blue(): Double? = blue.getNullable("blue")

                    /**
                     * @throws XTwitterScraperInvalidDataException if the JSON field has an
                     *   unexpected type.
                     */
                    fun green(): Double? = green.getNullable("green")

                    /**
                     * @throws XTwitterScraperInvalidDataException if the JSON field has an
                     *   unexpected type.
                     */
                    fun red(): Double? = red.getNullable("red")

                    /**
                     * Returns the raw JSON value of [blue].
                     *
                     * Unlike [blue], this method doesn't throw if the JSON field has an unexpected
                     * type.
                     */
                    @JsonProperty("blue") @ExcludeMissing fun _blue(): JsonField<Double> = blue

                    /**
                     * Returns the raw JSON value of [green].
                     *
                     * Unlike [green], this method doesn't throw if the JSON field has an unexpected
                     * type.
                     */
                    @JsonProperty("green") @ExcludeMissing fun _green(): JsonField<Double> = green

                    /**
                     * Returns the raw JSON value of [red].
                     *
                     * Unlike [red], this method doesn't throw if the JSON field has an unexpected
                     * type.
                     */
                    @JsonProperty("red") @ExcludeMissing fun _red(): JsonField<Double> = red

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

                        /** Returns a mutable builder for constructing an instance of [Rgb]. */
                        fun builder() = Builder()
                    }

                    /** A builder for [Rgb]. */
                    class Builder internal constructor() {

                        private var blue: JsonField<Double> = JsonMissing.of()
                        private var green: JsonField<Double> = JsonMissing.of()
                        private var red: JsonField<Double> = JsonMissing.of()
                        private var additionalProperties: MutableMap<String, JsonValue> =
                            mutableMapOf()

                        internal fun from(rgb: Rgb) = apply {
                            blue = rgb.blue
                            green = rgb.green
                            red = rgb.red
                            additionalProperties = rgb.additionalProperties.toMutableMap()
                        }

                        fun blue(blue: Double) = blue(JsonField.of(blue))

                        /** Sets [Builder.blue] to an arbitrary JSON value. */
                        fun blue(blue: JsonField<Double>) = apply { this.blue = blue }

                        fun green(green: Double) = green(JsonField.of(green))

                        /** Sets [Builder.green] to an arbitrary JSON value. */
                        fun green(green: JsonField<Double>) = apply { this.green = green }

                        fun red(red: Double) = red(JsonField.of(red))

                        /** Sets [Builder.red] to an arbitrary JSON value. */
                        fun red(red: JsonField<Double>) = apply { this.red = red }

                        fun additionalProperties(additionalProperties: Map<String, JsonValue>) =
                            apply {
                                this.additionalProperties.clear()
                                putAllAdditionalProperties(additionalProperties)
                            }

                        fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                            additionalProperties.put(key, value)
                        }

                        fun putAllAdditionalProperties(
                            additionalProperties: Map<String, JsonValue>
                        ) = apply {
                            this.additionalProperties.putAll(additionalProperties)
                        }

                        fun removeAdditionalProperty(key: String) = apply {
                            additionalProperties.remove(key)
                        }

                        fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                            keys.forEach(::removeAdditionalProperty)
                        }

                        /**
                         * Returns an immutable instance of [Rgb].
                         *
                         * Further updates to this [Builder] will not mutate the returned instance.
                         */
                        fun build(): Rgb =
                            Rgb(blue, green, red, additionalProperties.toMutableMap())
                    }

                    private var validated: Boolean = false

                    /**
                     * Validates that the types of all values in this object match their expected
                     * types recursively.
                     *
                     * This method is _not_ forwards compatible with new types from the API for
                     * existing fields.
                     *
                     * @throws XTwitterScraperInvalidDataException if any value type in this object
                     *   doesn't match its expected type.
                     */
                    fun validate(): Rgb = apply {
                        if (validated) {
                            return@apply
                        }

                        blue()
                        green()
                        red()
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
                        (if (blue.asKnown() == null) 0 else 1) +
                            (if (green.asKnown() == null) 0 else 1) +
                            (if (red.asKnown() == null) 0 else 1)

                    override fun equals(other: Any?): Boolean {
                        if (this === other) {
                            return true
                        }

                        return other is Rgb &&
                            blue == other.blue &&
                            green == other.green &&
                            red == other.red &&
                            additionalProperties == other.additionalProperties
                    }

                    private val hashCode: Int by lazy {
                        Objects.hash(blue, green, red, additionalProperties)
                    }

                    override fun hashCode(): Int = hashCode

                    override fun toString() =
                        "Rgb{blue=$blue, green=$green, red=$red, additionalProperties=$additionalProperties}"
                }

                override fun equals(other: Any?): Boolean {
                    if (this === other) {
                        return true
                    }

                    return other is Palette &&
                        percentage == other.percentage &&
                        rgb == other.rgb &&
                        additionalProperties == other.additionalProperties
                }

                private val hashCode: Int by lazy {
                    Objects.hash(percentage, rgb, additionalProperties)
                }

                override fun hashCode(): Int = hashCode

                override fun toString() =
                    "Palette{percentage=$percentage, rgb=$rgb, additionalProperties=$additionalProperties}"
            }

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is Media &&
                    id == other.id &&
                    mediaId == other.mediaId &&
                    mediaKey == other.mediaKey &&
                    originalImageHeight == other.originalImageHeight &&
                    originalImageUrl == other.originalImageUrl &&
                    originalImageWidth == other.originalImageWidth &&
                    palette == other.palette &&
                    type == other.type &&
                    additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(
                    id,
                    mediaId,
                    mediaKey,
                    originalImageHeight,
                    originalImageUrl,
                    originalImageWidth,
                    palette,
                    type,
                    additionalProperties,
                )
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "Media{id=$id, mediaId=$mediaId, mediaKey=$mediaKey, originalImageHeight=$originalImageHeight, originalImageUrl=$originalImageUrl, originalImageWidth=$originalImageWidth, palette=$palette, type=$type, additionalProperties=$additionalProperties}"
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Folder &&
                id == other.id &&
                name == other.name &&
                media == other.media &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(id, name, media, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Folder{id=$id, name=$name, media=$media, additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BookmarkRetrieveFoldersResponse &&
            folders == other.folders &&
            hasNextPage == other.hasNextPage &&
            nextCursor == other.nextCursor &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(folders, hasNextPage, nextCursor, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BookmarkRetrieveFoldersResponse{folders=$folders, hasNextPage=$hasNextPage, nextCursor=$nextCursor, additionalProperties=$additionalProperties}"
}
