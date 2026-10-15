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
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects

/** Public X profile. */
class UserProfile
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val name: JsonField<String>,
    private val username: JsonField<String>,
    private val accountBasedIn: JsonField<AccountBasedIn>,
    private val accountBasedInAccurate: JsonField<Boolean>,
    private val accountBasedInUnavailable: JsonField<Boolean>,
    private val advertiserAccountServiceLevels: JsonField<List<String>>,
    private val advertiserAccountType: JsonField<String>,
    private val affiliateUsername: JsonField<String>,
    private val affiliatesHighlightedLabel: JsonField<AffiliatesHighlightedLabel>,
    private val automatedBy: JsonField<String>,
    private val birthdate: JsonField<Birthdate>,
    private val businessAccountAffiliatesCount: JsonField<Long>,
    private val businessProfileState: JsonField<String>,
    private val communityRole: JsonField<String>,
    private val connectedVia: JsonField<String>,
    private val connectedViaAccurate: JsonField<Boolean>,
    private val contactInfo: JsonField<ContactInfo>,
    private val coverPicture: JsonField<String>,
    private val createdAt: JsonField<String>,
    private val creatorSubscriptionsCount: JsonField<Long>,
    private val defaultProfile: JsonField<Boolean>,
    private val defaultProfileImage: JsonField<Boolean>,
    private val description: JsonField<String>,
    private val email: JsonField<String>,
    private val fastFollowersCount: JsonField<Long>,
    private val favouritesCount: JsonField<Long>,
    private val followers: JsonField<Long>,
    private val following: JsonField<Long>,
    private val geoEnabled: JsonField<Boolean>,
    private val grokTranslatedBio: JsonField<GrokTranslatedBio>,
    private val hasCustomTimelines: JsonField<Boolean>,
    private val hasExtendedProfile: JsonField<Boolean>,
    private val hasGraduatedAccess: JsonField<Boolean>,
    private val hasHiddenSubscriptionsOnProfile: JsonField<Boolean>,
    private val highlightsInfo: JsonField<HighlightsInfo>,
    private val identityVerification: JsonField<IdentityVerification>,
    private val isAutomated: JsonField<Boolean>,
    private val isBlueVerified: JsonField<Boolean>,
    private val isProfileTranslatable: JsonField<Boolean>,
    private val isTranslationEnabled: JsonField<Boolean>,
    private val isTranslator: JsonField<Boolean>,
    private val isVerified: JsonField<Boolean>,
    private val listedCount: JsonField<Long>,
    private val location: JsonField<String>,
    private val mediaCount: JsonField<Long>,
    private val normalFollowersCount: JsonField<Long>,
    private val parodyCommentaryFanLabel: JsonField<String>,
    private val pinnedTweetIds: JsonField<List<String>>,
    private val possiblySensitive: JsonField<Boolean>,
    private val professional: JsonField<Professional>,
    private val profileBannerUrl: JsonField<String>,
    private val profileDescriptionLanguage: JsonField<String>,
    private val profileImageShape: JsonField<String>,
    private val profileInterstitialType: JsonField<String>,
    private val profilePicture: JsonField<String>,
    private val profilePictureOriginal: JsonField<String>,
    private val profileSortEnabled: JsonField<Boolean>,
    private val profileTranslatorType: JsonField<String>,
    private val profileBio: JsonField<ProfileBio>,
    private val protected: JsonField<Boolean>,
    private val redirectsToUsername: JsonField<String>,
    private val retweetedAt: JsonField<OffsetDateTime>,
    private val statusesCount: JsonField<Long>,
    private val subscribersCount: JsonField<Long>,
    private val superFollowEligible: JsonField<Boolean>,
    private val superFollowsUserProfileActive: JsonField<Boolean>,
    private val tipJar: JsonField<TipJar>,
    private val unavailable: JsonField<Boolean>,
    private val unavailableReason: JsonField<String>,
    private val url: JsonField<String>,
    private val usernameChanges: JsonField<UsernameChanges>,
    private val verificationReason: JsonField<VerificationReason>,
    private val verified: JsonField<Boolean>,
    private val verifiedType: JsonField<String>,
    private val withheldInCountries: JsonField<List<String>>,
    private val withheldScope: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
        @JsonProperty("username") @ExcludeMissing username: JsonField<String> = JsonMissing.of(),
        @JsonProperty("accountBasedIn")
        @ExcludeMissing
        accountBasedIn: JsonField<AccountBasedIn> = JsonMissing.of(),
        @JsonProperty("accountBasedInAccurate")
        @ExcludeMissing
        accountBasedInAccurate: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("accountBasedInUnavailable")
        @ExcludeMissing
        accountBasedInUnavailable: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("advertiserAccountServiceLevels")
        @ExcludeMissing
        advertiserAccountServiceLevels: JsonField<List<String>> = JsonMissing.of(),
        @JsonProperty("advertiserAccountType")
        @ExcludeMissing
        advertiserAccountType: JsonField<String> = JsonMissing.of(),
        @JsonProperty("affiliateUsername")
        @ExcludeMissing
        affiliateUsername: JsonField<String> = JsonMissing.of(),
        @JsonProperty("affiliatesHighlightedLabel")
        @ExcludeMissing
        affiliatesHighlightedLabel: JsonField<AffiliatesHighlightedLabel> = JsonMissing.of(),
        @JsonProperty("automatedBy")
        @ExcludeMissing
        automatedBy: JsonField<String> = JsonMissing.of(),
        @JsonProperty("birthdate")
        @ExcludeMissing
        birthdate: JsonField<Birthdate> = JsonMissing.of(),
        @JsonProperty("businessAccountAffiliatesCount")
        @ExcludeMissing
        businessAccountAffiliatesCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("businessProfileState")
        @ExcludeMissing
        businessProfileState: JsonField<String> = JsonMissing.of(),
        @JsonProperty("communityRole")
        @ExcludeMissing
        communityRole: JsonField<String> = JsonMissing.of(),
        @JsonProperty("connectedVia")
        @ExcludeMissing
        connectedVia: JsonField<String> = JsonMissing.of(),
        @JsonProperty("connectedViaAccurate")
        @ExcludeMissing
        connectedViaAccurate: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("contactInfo")
        @ExcludeMissing
        contactInfo: JsonField<ContactInfo> = JsonMissing.of(),
        @JsonProperty("coverPicture")
        @ExcludeMissing
        coverPicture: JsonField<String> = JsonMissing.of(),
        @JsonProperty("createdAt") @ExcludeMissing createdAt: JsonField<String> = JsonMissing.of(),
        @JsonProperty("creatorSubscriptionsCount")
        @ExcludeMissing
        creatorSubscriptionsCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("defaultProfile")
        @ExcludeMissing
        defaultProfile: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("defaultProfileImage")
        @ExcludeMissing
        defaultProfileImage: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("description")
        @ExcludeMissing
        description: JsonField<String> = JsonMissing.of(),
        @JsonProperty("email") @ExcludeMissing email: JsonField<String> = JsonMissing.of(),
        @JsonProperty("fastFollowersCount")
        @ExcludeMissing
        fastFollowersCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("favouritesCount")
        @ExcludeMissing
        favouritesCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("followers") @ExcludeMissing followers: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("following") @ExcludeMissing following: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("geoEnabled")
        @ExcludeMissing
        geoEnabled: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("grokTranslatedBio")
        @ExcludeMissing
        grokTranslatedBio: JsonField<GrokTranslatedBio> = JsonMissing.of(),
        @JsonProperty("hasCustomTimelines")
        @ExcludeMissing
        hasCustomTimelines: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("hasExtendedProfile")
        @ExcludeMissing
        hasExtendedProfile: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("hasGraduatedAccess")
        @ExcludeMissing
        hasGraduatedAccess: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("hasHiddenSubscriptionsOnProfile")
        @ExcludeMissing
        hasHiddenSubscriptionsOnProfile: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("highlightsInfo")
        @ExcludeMissing
        highlightsInfo: JsonField<HighlightsInfo> = JsonMissing.of(),
        @JsonProperty("identityVerification")
        @ExcludeMissing
        identityVerification: JsonField<IdentityVerification> = JsonMissing.of(),
        @JsonProperty("isAutomated")
        @ExcludeMissing
        isAutomated: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("isBlueVerified")
        @ExcludeMissing
        isBlueVerified: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("isProfileTranslatable")
        @ExcludeMissing
        isProfileTranslatable: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("isTranslationEnabled")
        @ExcludeMissing
        isTranslationEnabled: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("isTranslator")
        @ExcludeMissing
        isTranslator: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("isVerified")
        @ExcludeMissing
        isVerified: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("listedCount")
        @ExcludeMissing
        listedCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("location") @ExcludeMissing location: JsonField<String> = JsonMissing.of(),
        @JsonProperty("mediaCount") @ExcludeMissing mediaCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("normalFollowersCount")
        @ExcludeMissing
        normalFollowersCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("parodyCommentaryFanLabel")
        @ExcludeMissing
        parodyCommentaryFanLabel: JsonField<String> = JsonMissing.of(),
        @JsonProperty("pinnedTweetIds")
        @ExcludeMissing
        pinnedTweetIds: JsonField<List<String>> = JsonMissing.of(),
        @JsonProperty("possiblySensitive")
        @ExcludeMissing
        possiblySensitive: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("professional")
        @ExcludeMissing
        professional: JsonField<Professional> = JsonMissing.of(),
        @JsonProperty("profileBannerUrl")
        @ExcludeMissing
        profileBannerUrl: JsonField<String> = JsonMissing.of(),
        @JsonProperty("profileDescriptionLanguage")
        @ExcludeMissing
        profileDescriptionLanguage: JsonField<String> = JsonMissing.of(),
        @JsonProperty("profileImageShape")
        @ExcludeMissing
        profileImageShape: JsonField<String> = JsonMissing.of(),
        @JsonProperty("profileInterstitialType")
        @ExcludeMissing
        profileInterstitialType: JsonField<String> = JsonMissing.of(),
        @JsonProperty("profilePicture")
        @ExcludeMissing
        profilePicture: JsonField<String> = JsonMissing.of(),
        @JsonProperty("profilePictureOriginal")
        @ExcludeMissing
        profilePictureOriginal: JsonField<String> = JsonMissing.of(),
        @JsonProperty("profileSortEnabled")
        @ExcludeMissing
        profileSortEnabled: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("profileTranslatorType")
        @ExcludeMissing
        profileTranslatorType: JsonField<String> = JsonMissing.of(),
        @JsonProperty("profile_bio")
        @ExcludeMissing
        profileBio: JsonField<ProfileBio> = JsonMissing.of(),
        @JsonProperty("protected") @ExcludeMissing protected: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("redirectsToUsername")
        @ExcludeMissing
        redirectsToUsername: JsonField<String> = JsonMissing.of(),
        @JsonProperty("retweetedAt")
        @ExcludeMissing
        retweetedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("statusesCount")
        @ExcludeMissing
        statusesCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("subscribersCount")
        @ExcludeMissing
        subscribersCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("superFollowEligible")
        @ExcludeMissing
        superFollowEligible: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("superFollowsUserProfileActive")
        @ExcludeMissing
        superFollowsUserProfileActive: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("tipJar") @ExcludeMissing tipJar: JsonField<TipJar> = JsonMissing.of(),
        @JsonProperty("unavailable")
        @ExcludeMissing
        unavailable: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("unavailableReason")
        @ExcludeMissing
        unavailableReason: JsonField<String> = JsonMissing.of(),
        @JsonProperty("url") @ExcludeMissing url: JsonField<String> = JsonMissing.of(),
        @JsonProperty("usernameChanges")
        @ExcludeMissing
        usernameChanges: JsonField<UsernameChanges> = JsonMissing.of(),
        @JsonProperty("verificationReason")
        @ExcludeMissing
        verificationReason: JsonField<VerificationReason> = JsonMissing.of(),
        @JsonProperty("verified") @ExcludeMissing verified: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("verifiedType")
        @ExcludeMissing
        verifiedType: JsonField<String> = JsonMissing.of(),
        @JsonProperty("withheldInCountries")
        @ExcludeMissing
        withheldInCountries: JsonField<List<String>> = JsonMissing.of(),
        @JsonProperty("withheldScope")
        @ExcludeMissing
        withheldScope: JsonField<String> = JsonMissing.of(),
    ) : this(
        id,
        name,
        username,
        accountBasedIn,
        accountBasedInAccurate,
        accountBasedInUnavailable,
        advertiserAccountServiceLevels,
        advertiserAccountType,
        affiliateUsername,
        affiliatesHighlightedLabel,
        automatedBy,
        birthdate,
        businessAccountAffiliatesCount,
        businessProfileState,
        communityRole,
        connectedVia,
        connectedViaAccurate,
        contactInfo,
        coverPicture,
        createdAt,
        creatorSubscriptionsCount,
        defaultProfile,
        defaultProfileImage,
        description,
        email,
        fastFollowersCount,
        favouritesCount,
        followers,
        following,
        geoEnabled,
        grokTranslatedBio,
        hasCustomTimelines,
        hasExtendedProfile,
        hasGraduatedAccess,
        hasHiddenSubscriptionsOnProfile,
        highlightsInfo,
        identityVerification,
        isAutomated,
        isBlueVerified,
        isProfileTranslatable,
        isTranslationEnabled,
        isTranslator,
        isVerified,
        listedCount,
        location,
        mediaCount,
        normalFollowersCount,
        parodyCommentaryFanLabel,
        pinnedTweetIds,
        possiblySensitive,
        professional,
        profileBannerUrl,
        profileDescriptionLanguage,
        profileImageShape,
        profileInterstitialType,
        profilePicture,
        profilePictureOriginal,
        profileSortEnabled,
        profileTranslatorType,
        profileBio,
        protected,
        redirectsToUsername,
        retweetedAt,
        statusesCount,
        subscribersCount,
        superFollowEligible,
        superFollowsUserProfileActive,
        tipJar,
        unavailable,
        unavailableReason,
        url,
        usernameChanges,
        verificationReason,
        verified,
        verifiedType,
        withheldInCountries,
        withheldScope,
        mutableMapOf(),
    )

    /**
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null.
     */
    fun id(): String = id.getRequired("id")

    /**
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
     * Null when X shows no label.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun accountBasedIn(): AccountBasedIn? = accountBasedIn.getNullable("accountBasedIn")

    /**
     * False when X notes accountBasedIn may be inaccurate.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun accountBasedInAccurate(): Boolean? =
        accountBasedInAccurate.getNullable("accountBasedInAccurate")

    /**
     * True when X withheld accountBasedIn from this read. Retry later.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun accountBasedInUnavailable(): Boolean? =
        accountBasedInUnavailable.getNullable("accountBasedInUnavailable")

    /**
     * X Ads services the account uses, such as `analytics`, `media_studio` or `smb`. Empty for an
     * account without ads.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun advertiserAccountServiceLevels(): List<String>? =
        advertiserAccountServiceLevels.getNullable("advertiserAccountServiceLevels")

    /**
     * The account's X Ads type. `none` for an account without ads, or `promotable_user` for one
     * that may run them.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun advertiserAccountType(): String? =
        advertiserAccountType.getNullable("advertiserAccountType")

    /**
     * Handle of the organization X shows the account is affiliated with, from its About page.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun affiliateUsername(): String? = affiliateUsername.getNullable("affiliateUsername")

    /**
     * Organization affiliation label shown on an X profile.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun affiliatesHighlightedLabel(): AffiliatesHighlightedLabel? =
        affiliatesHighlightedLabel.getNullable("affiliatesHighlightedLabel")

    /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
    fun automatedBy(): String? = automatedBy.getNullable("automatedBy")

    /**
     * The parts of the birthday the account made public.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun birthdate(): Birthdate? = birthdate.getNullable("birthdate")

    /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
    fun businessAccountAffiliatesCount(): Long? =
        businessAccountAffiliatesCount.getNullable("businessAccountAffiliatesCount")

    /**
     * Whether X treats the profile as a business profile. `none` or `enabled`.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun businessProfileState(): String? = businessProfileState.getNullable("businessProfileState")

    /**
     * Community role when returned by community member reads
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun communityRole(): String? = communityRole.getNullable("communityRole")

    /**
     * How the account first connected to X.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun connectedVia(): String? = connectedVia.getNullable("connectedVia")

    /**
     * False when X notes that country may be inaccurate.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun connectedViaAccurate(): Boolean? = connectedViaAccurate.getNullable("connectedViaAccurate")

    /**
     * Emails, phone numbers & links the account publishes on its own profile. Omitted when it
     * publishes none.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun contactInfo(): ContactInfo? = contactInfo.getNullable("contactInfo")

    /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
    fun coverPicture(): String? = coverPicture.getNullable("coverPicture")

    /**
     * Account creation time.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun createdAt(): String? = createdAt.getNullable("createdAt")

    /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
    fun creatorSubscriptionsCount(): Long? =
        creatorSubscriptionsCount.getNullable("creatorSubscriptionsCount")

    /**
     * Whether the profile keeps X's default theme.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun defaultProfile(): Boolean? = defaultProfile.getNullable("defaultProfile")

    /**
     * Whether the profile keeps X's default avatar.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun defaultProfileImage(): Boolean? = defaultProfileImage.getNullable("defaultProfileImage")

    /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
    fun description(): String? = description.getNullable("description")

    /**
     * First email the account publishes on its profile, lowercased. Omitted when it publishes none.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun email(): String? = email.getNullable("email")

    /**
     * Followers X counts as fast followers.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun fastFollowersCount(): Long? = fastFollowersCount.getNullable("fastFollowersCount")

    /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
    fun favouritesCount(): Long? = favouritesCount.getNullable("favouritesCount")

    /**
     * Omitted when X sends no count, as for the account that first posted reused media.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun followers(): Long? = followers.getNullable("followers")

    /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
    fun following(): Long? = following.getNullable("following")

    /**
     * Whether the profile may add a location to posts.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun geoEnabled(): Boolean? = geoEnabled.getNullable("geoEnabled")

    /**
     * Public metadata whose fields X defines.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun grokTranslatedBio(): GrokTranslatedBio? = grokTranslatedBio.getNullable("grokTranslatedBio")

    /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
    fun hasCustomTimelines(): Boolean? = hasCustomTimelines.getNullable("hasCustomTimelines")

    /**
     * Whether the profile filled X's extended fields, such as a birth date. True or false.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun hasExtendedProfile(): Boolean? = hasExtendedProfile.getNullable("hasExtendedProfile")

    /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
    fun hasGraduatedAccess(): Boolean? = hasGraduatedAccess.getNullable("hasGraduatedAccess")

    /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
    fun hasHiddenSubscriptionsOnProfile(): Boolean? =
        hasHiddenSubscriptionsOnProfile.getNullable("hasHiddenSubscriptionsOnProfile")

    /**
     * Profile highlight availability and count metadata.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun highlightsInfo(): HighlightsInfo? = highlightsInfo.getNullable("highlightsInfo")

    /**
     * Identity verification metadata displayed by X.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun identityVerification(): IdentityVerification? =
        identityVerification.getNullable("identityVerification")

    /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
    fun isAutomated(): Boolean? = isAutomated.getNullable("isAutomated")

    /**
     * Whether X shows a blue verification badge
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun isBlueVerified(): Boolean? = isBlueVerified.getNullable("isBlueVerified")

    /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
    fun isProfileTranslatable(): Boolean? =
        isProfileTranslatable.getNullable("isProfileTranslatable")

    /**
     * Whether the account turned on X's translation of its posts. True or false.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun isTranslationEnabled(): Boolean? = isTranslationEnabled.getNullable("isTranslationEnabled")

    /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
    fun isTranslator(): Boolean? = isTranslator.getNullable("isTranslator")

    /**
     * Whether X marks the profile as verified
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun isVerified(): Boolean? = isVerified.getNullable("isVerified")

    /**
     * Public lists that include the profile.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun listedCount(): Long? = listedCount.getNullable("listedCount")

    /**
     * Account owner's public profile location text
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun location(): String? = location.getNullable("location")

    /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
    fun mediaCount(): Long? = mediaCount.getNullable("mediaCount")

    /**
     * Followers minus fast followers.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun normalFollowersCount(): Long? = normalFollowersCount.getNullable("normalFollowersCount")

    /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
    fun parodyCommentaryFanLabel(): String? =
        parodyCommentaryFanLabel.getNullable("parodyCommentaryFanLabel")

    /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
    fun pinnedTweetIds(): List<String>? = pinnedTweetIds.getNullable("pinnedTweetIds")

    /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
    fun possiblySensitive(): Boolean? = possiblySensitive.getNullable("possiblySensitive")

    /**
     * Public metadata whose fields X defines.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun professional(): Professional? = professional.getNullable("professional")

    /**
     * Original X profile banner field when available
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun profileBannerUrl(): String? = profileBannerUrl.getNullable("profileBannerUrl")

    /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
    fun profileDescriptionLanguage(): String? =
        profileDescriptionLanguage.getNullable("profileDescriptionLanguage")

    /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
    fun profileImageShape(): String? = profileImageShape.getNullable("profileImageShape")

    /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
    fun profileInterstitialType(): String? =
        profileInterstitialType.getNullable("profileInterstitialType")

    /**
     * Profile picture at 48 px, as X sends it.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun profilePicture(): String? = profilePicture.getNullable("profilePicture")

    /**
     * The same profile picture at full size, as the account uploaded it. Download this one for the
     * image.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun profilePictureOriginal(): String? =
        profilePictureOriginal.getNullable("profilePictureOriginal")

    /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
    fun profileSortEnabled(): Boolean? = profileSortEnabled.getNullable("profileSortEnabled")

    /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
    fun profileTranslatorType(): String? =
        profileTranslatorType.getNullable("profileTranslatorType")

    /**
     * Structured profile bio with entity annotations
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun profileBio(): ProfileBio? = profileBio.getNullable("profile_bio")

    /**
     * Whether the profile protects its posts
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun protected(): Boolean? = protected.getNullable("protected")

    /**
     * Handle x.com sends this profile's visitors to, such as the new handle of a renamed account
     * whose old handle now holds a placeholder. Missing when x.com shows the profile itself.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun redirectsToUsername(): String? = redirectsToUsername.getNullable("redirectsToUsername")

    /**
     * UTC repost time with includeRetweetTimestamp. Null if the newest profile page has no match or
     * lookup failed. Otherwise omitted.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun retweetedAt(): OffsetDateTime? = retweetedAt.getNullable("retweetedAt")

    /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
    fun statusesCount(): Long? = statusesCount.getNullable("statusesCount")

    /**
     * Accounts subscribed to the profile's paid Subscriptions. 0 when it has none.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun subscribersCount(): Long? = subscribersCount.getNullable("subscribersCount")

    /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
    fun superFollowEligible(): Boolean? = superFollowEligible.getNullable("superFollowEligible")

    /**
     * Whether X marks the subscription profile as active.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun superFollowsUserProfileActive(): Boolean? =
        superFollowsUserProfileActive.getNullable("superFollowsUserProfileActive")

    /**
     * Public payment and creator-support handles shown on X.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun tipJar(): TipJar? = tipJar.getNullable("tipJar")

    /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
    fun unavailable(): Boolean? = unavailable.getNullable("unavailable")

    /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
    fun unavailableReason(): String? = unavailableReason.getNullable("unavailableReason")

    /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
    fun url(): String? = url.getNullable("url")

    /**
     * How often the account changed its username.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun usernameChanges(): UsernameChanges? = usernameChanges.getNullable("usernameChanges")

    /**
     * X's text on why the profile is verified, with its links.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun verificationReason(): VerificationReason? =
        verificationReason.getNullable("verificationReason")

    /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
    fun verified(): Boolean? = verified.getNullable("verified")

    /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
    fun verifiedType(): String? = verifiedType.getNullable("verifiedType")

    /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
    fun withheldInCountries(): List<String>? =
        withheldInCountries.getNullable("withheldInCountries")

    /**
     * Whether X withholds a post or user
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun withheldScope(): String? = withheldScope.getNullable("withheldScope")

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
     * Returns the raw JSON value of [accountBasedIn].
     *
     * Unlike [accountBasedIn], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("accountBasedIn")
    @ExcludeMissing
    fun _accountBasedIn(): JsonField<AccountBasedIn> = accountBasedIn

    /**
     * Returns the raw JSON value of [accountBasedInAccurate].
     *
     * Unlike [accountBasedInAccurate], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("accountBasedInAccurate")
    @ExcludeMissing
    fun _accountBasedInAccurate(): JsonField<Boolean> = accountBasedInAccurate

    /**
     * Returns the raw JSON value of [accountBasedInUnavailable].
     *
     * Unlike [accountBasedInUnavailable], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("accountBasedInUnavailable")
    @ExcludeMissing
    fun _accountBasedInUnavailable(): JsonField<Boolean> = accountBasedInUnavailable

    /**
     * Returns the raw JSON value of [advertiserAccountServiceLevels].
     *
     * Unlike [advertiserAccountServiceLevels], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("advertiserAccountServiceLevels")
    @ExcludeMissing
    fun _advertiserAccountServiceLevels(): JsonField<List<String>> = advertiserAccountServiceLevels

    /**
     * Returns the raw JSON value of [advertiserAccountType].
     *
     * Unlike [advertiserAccountType], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("advertiserAccountType")
    @ExcludeMissing
    fun _advertiserAccountType(): JsonField<String> = advertiserAccountType

    /**
     * Returns the raw JSON value of [affiliateUsername].
     *
     * Unlike [affiliateUsername], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("affiliateUsername")
    @ExcludeMissing
    fun _affiliateUsername(): JsonField<String> = affiliateUsername

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
     * Returns the raw JSON value of [automatedBy].
     *
     * Unlike [automatedBy], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("automatedBy") @ExcludeMissing fun _automatedBy(): JsonField<String> = automatedBy

    /**
     * Returns the raw JSON value of [birthdate].
     *
     * Unlike [birthdate], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("birthdate") @ExcludeMissing fun _birthdate(): JsonField<Birthdate> = birthdate

    /**
     * Returns the raw JSON value of [businessAccountAffiliatesCount].
     *
     * Unlike [businessAccountAffiliatesCount], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("businessAccountAffiliatesCount")
    @ExcludeMissing
    fun _businessAccountAffiliatesCount(): JsonField<Long> = businessAccountAffiliatesCount

    /**
     * Returns the raw JSON value of [businessProfileState].
     *
     * Unlike [businessProfileState], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("businessProfileState")
    @ExcludeMissing
    fun _businessProfileState(): JsonField<String> = businessProfileState

    /**
     * Returns the raw JSON value of [communityRole].
     *
     * Unlike [communityRole], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("communityRole")
    @ExcludeMissing
    fun _communityRole(): JsonField<String> = communityRole

    /**
     * Returns the raw JSON value of [connectedVia].
     *
     * Unlike [connectedVia], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("connectedVia")
    @ExcludeMissing
    fun _connectedVia(): JsonField<String> = connectedVia

    /**
     * Returns the raw JSON value of [connectedViaAccurate].
     *
     * Unlike [connectedViaAccurate], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("connectedViaAccurate")
    @ExcludeMissing
    fun _connectedViaAccurate(): JsonField<Boolean> = connectedViaAccurate

    /**
     * Returns the raw JSON value of [contactInfo].
     *
     * Unlike [contactInfo], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("contactInfo")
    @ExcludeMissing
    fun _contactInfo(): JsonField<ContactInfo> = contactInfo

    /**
     * Returns the raw JSON value of [coverPicture].
     *
     * Unlike [coverPicture], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("coverPicture")
    @ExcludeMissing
    fun _coverPicture(): JsonField<String> = coverPicture

    /**
     * Returns the raw JSON value of [createdAt].
     *
     * Unlike [createdAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("createdAt") @ExcludeMissing fun _createdAt(): JsonField<String> = createdAt

    /**
     * Returns the raw JSON value of [creatorSubscriptionsCount].
     *
     * Unlike [creatorSubscriptionsCount], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("creatorSubscriptionsCount")
    @ExcludeMissing
    fun _creatorSubscriptionsCount(): JsonField<Long> = creatorSubscriptionsCount

    /**
     * Returns the raw JSON value of [defaultProfile].
     *
     * Unlike [defaultProfile], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("defaultProfile")
    @ExcludeMissing
    fun _defaultProfile(): JsonField<Boolean> = defaultProfile

    /**
     * Returns the raw JSON value of [defaultProfileImage].
     *
     * Unlike [defaultProfileImage], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("defaultProfileImage")
    @ExcludeMissing
    fun _defaultProfileImage(): JsonField<Boolean> = defaultProfileImage

    /**
     * Returns the raw JSON value of [description].
     *
     * Unlike [description], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("description") @ExcludeMissing fun _description(): JsonField<String> = description

    /**
     * Returns the raw JSON value of [email].
     *
     * Unlike [email], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("email") @ExcludeMissing fun _email(): JsonField<String> = email

    /**
     * Returns the raw JSON value of [fastFollowersCount].
     *
     * Unlike [fastFollowersCount], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("fastFollowersCount")
    @ExcludeMissing
    fun _fastFollowersCount(): JsonField<Long> = fastFollowersCount

    /**
     * Returns the raw JSON value of [favouritesCount].
     *
     * Unlike [favouritesCount], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("favouritesCount")
    @ExcludeMissing
    fun _favouritesCount(): JsonField<Long> = favouritesCount

    /**
     * Returns the raw JSON value of [followers].
     *
     * Unlike [followers], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("followers") @ExcludeMissing fun _followers(): JsonField<Long> = followers

    /**
     * Returns the raw JSON value of [following].
     *
     * Unlike [following], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("following") @ExcludeMissing fun _following(): JsonField<Long> = following

    /**
     * Returns the raw JSON value of [geoEnabled].
     *
     * Unlike [geoEnabled], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("geoEnabled") @ExcludeMissing fun _geoEnabled(): JsonField<Boolean> = geoEnabled

    /**
     * Returns the raw JSON value of [grokTranslatedBio].
     *
     * Unlike [grokTranslatedBio], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("grokTranslatedBio")
    @ExcludeMissing
    fun _grokTranslatedBio(): JsonField<GrokTranslatedBio> = grokTranslatedBio

    /**
     * Returns the raw JSON value of [hasCustomTimelines].
     *
     * Unlike [hasCustomTimelines], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("hasCustomTimelines")
    @ExcludeMissing
    fun _hasCustomTimelines(): JsonField<Boolean> = hasCustomTimelines

    /**
     * Returns the raw JSON value of [hasExtendedProfile].
     *
     * Unlike [hasExtendedProfile], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("hasExtendedProfile")
    @ExcludeMissing
    fun _hasExtendedProfile(): JsonField<Boolean> = hasExtendedProfile

    /**
     * Returns the raw JSON value of [hasGraduatedAccess].
     *
     * Unlike [hasGraduatedAccess], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("hasGraduatedAccess")
    @ExcludeMissing
    fun _hasGraduatedAccess(): JsonField<Boolean> = hasGraduatedAccess

    /**
     * Returns the raw JSON value of [hasHiddenSubscriptionsOnProfile].
     *
     * Unlike [hasHiddenSubscriptionsOnProfile], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("hasHiddenSubscriptionsOnProfile")
    @ExcludeMissing
    fun _hasHiddenSubscriptionsOnProfile(): JsonField<Boolean> = hasHiddenSubscriptionsOnProfile

    /**
     * Returns the raw JSON value of [highlightsInfo].
     *
     * Unlike [highlightsInfo], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("highlightsInfo")
    @ExcludeMissing
    fun _highlightsInfo(): JsonField<HighlightsInfo> = highlightsInfo

    /**
     * Returns the raw JSON value of [identityVerification].
     *
     * Unlike [identityVerification], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("identityVerification")
    @ExcludeMissing
    fun _identityVerification(): JsonField<IdentityVerification> = identityVerification

    /**
     * Returns the raw JSON value of [isAutomated].
     *
     * Unlike [isAutomated], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("isAutomated")
    @ExcludeMissing
    fun _isAutomated(): JsonField<Boolean> = isAutomated

    /**
     * Returns the raw JSON value of [isBlueVerified].
     *
     * Unlike [isBlueVerified], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("isBlueVerified")
    @ExcludeMissing
    fun _isBlueVerified(): JsonField<Boolean> = isBlueVerified

    /**
     * Returns the raw JSON value of [isProfileTranslatable].
     *
     * Unlike [isProfileTranslatable], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("isProfileTranslatable")
    @ExcludeMissing
    fun _isProfileTranslatable(): JsonField<Boolean> = isProfileTranslatable

    /**
     * Returns the raw JSON value of [isTranslationEnabled].
     *
     * Unlike [isTranslationEnabled], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("isTranslationEnabled")
    @ExcludeMissing
    fun _isTranslationEnabled(): JsonField<Boolean> = isTranslationEnabled

    /**
     * Returns the raw JSON value of [isTranslator].
     *
     * Unlike [isTranslator], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("isTranslator")
    @ExcludeMissing
    fun _isTranslator(): JsonField<Boolean> = isTranslator

    /**
     * Returns the raw JSON value of [isVerified].
     *
     * Unlike [isVerified], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("isVerified") @ExcludeMissing fun _isVerified(): JsonField<Boolean> = isVerified

    /**
     * Returns the raw JSON value of [listedCount].
     *
     * Unlike [listedCount], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("listedCount") @ExcludeMissing fun _listedCount(): JsonField<Long> = listedCount

    /**
     * Returns the raw JSON value of [location].
     *
     * Unlike [location], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("location") @ExcludeMissing fun _location(): JsonField<String> = location

    /**
     * Returns the raw JSON value of [mediaCount].
     *
     * Unlike [mediaCount], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("mediaCount") @ExcludeMissing fun _mediaCount(): JsonField<Long> = mediaCount

    /**
     * Returns the raw JSON value of [normalFollowersCount].
     *
     * Unlike [normalFollowersCount], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("normalFollowersCount")
    @ExcludeMissing
    fun _normalFollowersCount(): JsonField<Long> = normalFollowersCount

    /**
     * Returns the raw JSON value of [parodyCommentaryFanLabel].
     *
     * Unlike [parodyCommentaryFanLabel], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("parodyCommentaryFanLabel")
    @ExcludeMissing
    fun _parodyCommentaryFanLabel(): JsonField<String> = parodyCommentaryFanLabel

    /**
     * Returns the raw JSON value of [pinnedTweetIds].
     *
     * Unlike [pinnedTweetIds], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("pinnedTweetIds")
    @ExcludeMissing
    fun _pinnedTweetIds(): JsonField<List<String>> = pinnedTweetIds

    /**
     * Returns the raw JSON value of [possiblySensitive].
     *
     * Unlike [possiblySensitive], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("possiblySensitive")
    @ExcludeMissing
    fun _possiblySensitive(): JsonField<Boolean> = possiblySensitive

    /**
     * Returns the raw JSON value of [professional].
     *
     * Unlike [professional], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("professional")
    @ExcludeMissing
    fun _professional(): JsonField<Professional> = professional

    /**
     * Returns the raw JSON value of [profileBannerUrl].
     *
     * Unlike [profileBannerUrl], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("profileBannerUrl")
    @ExcludeMissing
    fun _profileBannerUrl(): JsonField<String> = profileBannerUrl

    /**
     * Returns the raw JSON value of [profileDescriptionLanguage].
     *
     * Unlike [profileDescriptionLanguage], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("profileDescriptionLanguage")
    @ExcludeMissing
    fun _profileDescriptionLanguage(): JsonField<String> = profileDescriptionLanguage

    /**
     * Returns the raw JSON value of [profileImageShape].
     *
     * Unlike [profileImageShape], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("profileImageShape")
    @ExcludeMissing
    fun _profileImageShape(): JsonField<String> = profileImageShape

    /**
     * Returns the raw JSON value of [profileInterstitialType].
     *
     * Unlike [profileInterstitialType], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("profileInterstitialType")
    @ExcludeMissing
    fun _profileInterstitialType(): JsonField<String> = profileInterstitialType

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
     * Returns the raw JSON value of [profileSortEnabled].
     *
     * Unlike [profileSortEnabled], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("profileSortEnabled")
    @ExcludeMissing
    fun _profileSortEnabled(): JsonField<Boolean> = profileSortEnabled

    /**
     * Returns the raw JSON value of [profileTranslatorType].
     *
     * Unlike [profileTranslatorType], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("profileTranslatorType")
    @ExcludeMissing
    fun _profileTranslatorType(): JsonField<String> = profileTranslatorType

    /**
     * Returns the raw JSON value of [profileBio].
     *
     * Unlike [profileBio], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("profile_bio")
    @ExcludeMissing
    fun _profileBio(): JsonField<ProfileBio> = profileBio

    /**
     * Returns the raw JSON value of [protected].
     *
     * Unlike [protected], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("protected") @ExcludeMissing fun _protected(): JsonField<Boolean> = protected

    /**
     * Returns the raw JSON value of [redirectsToUsername].
     *
     * Unlike [redirectsToUsername], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("redirectsToUsername")
    @ExcludeMissing
    fun _redirectsToUsername(): JsonField<String> = redirectsToUsername

    /**
     * Returns the raw JSON value of [retweetedAt].
     *
     * Unlike [retweetedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("retweetedAt")
    @ExcludeMissing
    fun _retweetedAt(): JsonField<OffsetDateTime> = retweetedAt

    /**
     * Returns the raw JSON value of [statusesCount].
     *
     * Unlike [statusesCount], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("statusesCount")
    @ExcludeMissing
    fun _statusesCount(): JsonField<Long> = statusesCount

    /**
     * Returns the raw JSON value of [subscribersCount].
     *
     * Unlike [subscribersCount], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("subscribersCount")
    @ExcludeMissing
    fun _subscribersCount(): JsonField<Long> = subscribersCount

    /**
     * Returns the raw JSON value of [superFollowEligible].
     *
     * Unlike [superFollowEligible], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("superFollowEligible")
    @ExcludeMissing
    fun _superFollowEligible(): JsonField<Boolean> = superFollowEligible

    /**
     * Returns the raw JSON value of [superFollowsUserProfileActive].
     *
     * Unlike [superFollowsUserProfileActive], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("superFollowsUserProfileActive")
    @ExcludeMissing
    fun _superFollowsUserProfileActive(): JsonField<Boolean> = superFollowsUserProfileActive

    /**
     * Returns the raw JSON value of [tipJar].
     *
     * Unlike [tipJar], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("tipJar") @ExcludeMissing fun _tipJar(): JsonField<TipJar> = tipJar

    /**
     * Returns the raw JSON value of [unavailable].
     *
     * Unlike [unavailable], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("unavailable")
    @ExcludeMissing
    fun _unavailable(): JsonField<Boolean> = unavailable

    /**
     * Returns the raw JSON value of [unavailableReason].
     *
     * Unlike [unavailableReason], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("unavailableReason")
    @ExcludeMissing
    fun _unavailableReason(): JsonField<String> = unavailableReason

    /**
     * Returns the raw JSON value of [url].
     *
     * Unlike [url], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("url") @ExcludeMissing fun _url(): JsonField<String> = url

    /**
     * Returns the raw JSON value of [usernameChanges].
     *
     * Unlike [usernameChanges], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("usernameChanges")
    @ExcludeMissing
    fun _usernameChanges(): JsonField<UsernameChanges> = usernameChanges

    /**
     * Returns the raw JSON value of [verificationReason].
     *
     * Unlike [verificationReason], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("verificationReason")
    @ExcludeMissing
    fun _verificationReason(): JsonField<VerificationReason> = verificationReason

    /**
     * Returns the raw JSON value of [verified].
     *
     * Unlike [verified], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("verified") @ExcludeMissing fun _verified(): JsonField<Boolean> = verified

    /**
     * Returns the raw JSON value of [verifiedType].
     *
     * Unlike [verifiedType], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("verifiedType")
    @ExcludeMissing
    fun _verifiedType(): JsonField<String> = verifiedType

    /**
     * Returns the raw JSON value of [withheldInCountries].
     *
     * Unlike [withheldInCountries], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("withheldInCountries")
    @ExcludeMissing
    fun _withheldInCountries(): JsonField<List<String>> = withheldInCountries

    /**
     * Returns the raw JSON value of [withheldScope].
     *
     * Unlike [withheldScope], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("withheldScope")
    @ExcludeMissing
    fun _withheldScope(): JsonField<String> = withheldScope

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
         * Returns a mutable builder for constructing an instance of [UserProfile].
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

    /** A builder for [UserProfile]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var name: JsonField<String>? = null
        private var username: JsonField<String>? = null
        private var accountBasedIn: JsonField<AccountBasedIn> = JsonMissing.of()
        private var accountBasedInAccurate: JsonField<Boolean> = JsonMissing.of()
        private var accountBasedInUnavailable: JsonField<Boolean> = JsonMissing.of()
        private var advertiserAccountServiceLevels: JsonField<MutableList<String>>? = null
        private var advertiserAccountType: JsonField<String> = JsonMissing.of()
        private var affiliateUsername: JsonField<String> = JsonMissing.of()
        private var affiliatesHighlightedLabel: JsonField<AffiliatesHighlightedLabel> =
            JsonMissing.of()
        private var automatedBy: JsonField<String> = JsonMissing.of()
        private var birthdate: JsonField<Birthdate> = JsonMissing.of()
        private var businessAccountAffiliatesCount: JsonField<Long> = JsonMissing.of()
        private var businessProfileState: JsonField<String> = JsonMissing.of()
        private var communityRole: JsonField<String> = JsonMissing.of()
        private var connectedVia: JsonField<String> = JsonMissing.of()
        private var connectedViaAccurate: JsonField<Boolean> = JsonMissing.of()
        private var contactInfo: JsonField<ContactInfo> = JsonMissing.of()
        private var coverPicture: JsonField<String> = JsonMissing.of()
        private var createdAt: JsonField<String> = JsonMissing.of()
        private var creatorSubscriptionsCount: JsonField<Long> = JsonMissing.of()
        private var defaultProfile: JsonField<Boolean> = JsonMissing.of()
        private var defaultProfileImage: JsonField<Boolean> = JsonMissing.of()
        private var description: JsonField<String> = JsonMissing.of()
        private var email: JsonField<String> = JsonMissing.of()
        private var fastFollowersCount: JsonField<Long> = JsonMissing.of()
        private var favouritesCount: JsonField<Long> = JsonMissing.of()
        private var followers: JsonField<Long> = JsonMissing.of()
        private var following: JsonField<Long> = JsonMissing.of()
        private var geoEnabled: JsonField<Boolean> = JsonMissing.of()
        private var grokTranslatedBio: JsonField<GrokTranslatedBio> = JsonMissing.of()
        private var hasCustomTimelines: JsonField<Boolean> = JsonMissing.of()
        private var hasExtendedProfile: JsonField<Boolean> = JsonMissing.of()
        private var hasGraduatedAccess: JsonField<Boolean> = JsonMissing.of()
        private var hasHiddenSubscriptionsOnProfile: JsonField<Boolean> = JsonMissing.of()
        private var highlightsInfo: JsonField<HighlightsInfo> = JsonMissing.of()
        private var identityVerification: JsonField<IdentityVerification> = JsonMissing.of()
        private var isAutomated: JsonField<Boolean> = JsonMissing.of()
        private var isBlueVerified: JsonField<Boolean> = JsonMissing.of()
        private var isProfileTranslatable: JsonField<Boolean> = JsonMissing.of()
        private var isTranslationEnabled: JsonField<Boolean> = JsonMissing.of()
        private var isTranslator: JsonField<Boolean> = JsonMissing.of()
        private var isVerified: JsonField<Boolean> = JsonMissing.of()
        private var listedCount: JsonField<Long> = JsonMissing.of()
        private var location: JsonField<String> = JsonMissing.of()
        private var mediaCount: JsonField<Long> = JsonMissing.of()
        private var normalFollowersCount: JsonField<Long> = JsonMissing.of()
        private var parodyCommentaryFanLabel: JsonField<String> = JsonMissing.of()
        private var pinnedTweetIds: JsonField<MutableList<String>>? = null
        private var possiblySensitive: JsonField<Boolean> = JsonMissing.of()
        private var professional: JsonField<Professional> = JsonMissing.of()
        private var profileBannerUrl: JsonField<String> = JsonMissing.of()
        private var profileDescriptionLanguage: JsonField<String> = JsonMissing.of()
        private var profileImageShape: JsonField<String> = JsonMissing.of()
        private var profileInterstitialType: JsonField<String> = JsonMissing.of()
        private var profilePicture: JsonField<String> = JsonMissing.of()
        private var profilePictureOriginal: JsonField<String> = JsonMissing.of()
        private var profileSortEnabled: JsonField<Boolean> = JsonMissing.of()
        private var profileTranslatorType: JsonField<String> = JsonMissing.of()
        private var profileBio: JsonField<ProfileBio> = JsonMissing.of()
        private var protected: JsonField<Boolean> = JsonMissing.of()
        private var redirectsToUsername: JsonField<String> = JsonMissing.of()
        private var retweetedAt: JsonField<OffsetDateTime> = JsonMissing.of()
        private var statusesCount: JsonField<Long> = JsonMissing.of()
        private var subscribersCount: JsonField<Long> = JsonMissing.of()
        private var superFollowEligible: JsonField<Boolean> = JsonMissing.of()
        private var superFollowsUserProfileActive: JsonField<Boolean> = JsonMissing.of()
        private var tipJar: JsonField<TipJar> = JsonMissing.of()
        private var unavailable: JsonField<Boolean> = JsonMissing.of()
        private var unavailableReason: JsonField<String> = JsonMissing.of()
        private var url: JsonField<String> = JsonMissing.of()
        private var usernameChanges: JsonField<UsernameChanges> = JsonMissing.of()
        private var verificationReason: JsonField<VerificationReason> = JsonMissing.of()
        private var verified: JsonField<Boolean> = JsonMissing.of()
        private var verifiedType: JsonField<String> = JsonMissing.of()
        private var withheldInCountries: JsonField<MutableList<String>>? = null
        private var withheldScope: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        internal fun from(userProfile: UserProfile) = apply {
            id = userProfile.id
            name = userProfile.name
            username = userProfile.username
            accountBasedIn = userProfile.accountBasedIn
            accountBasedInAccurate = userProfile.accountBasedInAccurate
            accountBasedInUnavailable = userProfile.accountBasedInUnavailable
            advertiserAccountServiceLevels =
                userProfile.advertiserAccountServiceLevels.map { it.toMutableList() }
            advertiserAccountType = userProfile.advertiserAccountType
            affiliateUsername = userProfile.affiliateUsername
            affiliatesHighlightedLabel = userProfile.affiliatesHighlightedLabel
            automatedBy = userProfile.automatedBy
            birthdate = userProfile.birthdate
            businessAccountAffiliatesCount = userProfile.businessAccountAffiliatesCount
            businessProfileState = userProfile.businessProfileState
            communityRole = userProfile.communityRole
            connectedVia = userProfile.connectedVia
            connectedViaAccurate = userProfile.connectedViaAccurate
            contactInfo = userProfile.contactInfo
            coverPicture = userProfile.coverPicture
            createdAt = userProfile.createdAt
            creatorSubscriptionsCount = userProfile.creatorSubscriptionsCount
            defaultProfile = userProfile.defaultProfile
            defaultProfileImage = userProfile.defaultProfileImage
            description = userProfile.description
            email = userProfile.email
            fastFollowersCount = userProfile.fastFollowersCount
            favouritesCount = userProfile.favouritesCount
            followers = userProfile.followers
            following = userProfile.following
            geoEnabled = userProfile.geoEnabled
            grokTranslatedBio = userProfile.grokTranslatedBio
            hasCustomTimelines = userProfile.hasCustomTimelines
            hasExtendedProfile = userProfile.hasExtendedProfile
            hasGraduatedAccess = userProfile.hasGraduatedAccess
            hasHiddenSubscriptionsOnProfile = userProfile.hasHiddenSubscriptionsOnProfile
            highlightsInfo = userProfile.highlightsInfo
            identityVerification = userProfile.identityVerification
            isAutomated = userProfile.isAutomated
            isBlueVerified = userProfile.isBlueVerified
            isProfileTranslatable = userProfile.isProfileTranslatable
            isTranslationEnabled = userProfile.isTranslationEnabled
            isTranslator = userProfile.isTranslator
            isVerified = userProfile.isVerified
            listedCount = userProfile.listedCount
            location = userProfile.location
            mediaCount = userProfile.mediaCount
            normalFollowersCount = userProfile.normalFollowersCount
            parodyCommentaryFanLabel = userProfile.parodyCommentaryFanLabel
            pinnedTweetIds = userProfile.pinnedTweetIds.map { it.toMutableList() }
            possiblySensitive = userProfile.possiblySensitive
            professional = userProfile.professional
            profileBannerUrl = userProfile.profileBannerUrl
            profileDescriptionLanguage = userProfile.profileDescriptionLanguage
            profileImageShape = userProfile.profileImageShape
            profileInterstitialType = userProfile.profileInterstitialType
            profilePicture = userProfile.profilePicture
            profilePictureOriginal = userProfile.profilePictureOriginal
            profileSortEnabled = userProfile.profileSortEnabled
            profileTranslatorType = userProfile.profileTranslatorType
            profileBio = userProfile.profileBio
            protected = userProfile.protected
            redirectsToUsername = userProfile.redirectsToUsername
            retweetedAt = userProfile.retweetedAt
            statusesCount = userProfile.statusesCount
            subscribersCount = userProfile.subscribersCount
            superFollowEligible = userProfile.superFollowEligible
            superFollowsUserProfileActive = userProfile.superFollowsUserProfileActive
            tipJar = userProfile.tipJar
            unavailable = userProfile.unavailable
            unavailableReason = userProfile.unavailableReason
            url = userProfile.url
            usernameChanges = userProfile.usernameChanges
            verificationReason = userProfile.verificationReason
            verified = userProfile.verified
            verifiedType = userProfile.verifiedType
            withheldInCountries = userProfile.withheldInCountries.map { it.toMutableList() }
            withheldScope = userProfile.withheldScope
            additionalProperties = userProfile.additionalProperties.toMutableMap()
        }

        fun id(id: String) = id(JsonField.of(id))

        /** Sets [Builder.id] to an arbitrary JSON value. */
        fun id(id: JsonField<String>) = apply { this.id = id }

        fun name(name: String) = name(JsonField.of(name))

        /** Sets [Builder.name] to an arbitrary JSON value. */
        fun name(name: JsonField<String>) = apply { this.name = name }

        fun username(username: String) = username(JsonField.of(username))

        /** Sets [Builder.username] to an arbitrary JSON value. */
        fun username(username: JsonField<String>) = apply { this.username = username }

        /** Null when X shows no label. */
        fun accountBasedIn(accountBasedIn: AccountBasedIn?) =
            accountBasedIn(JsonField.ofNullable(accountBasedIn))

        /** Sets [Builder.accountBasedIn] to an arbitrary JSON value. */
        fun accountBasedIn(accountBasedIn: JsonField<AccountBasedIn>) = apply {
            this.accountBasedIn = accountBasedIn
        }

        /** False when X notes accountBasedIn may be inaccurate. */
        fun accountBasedInAccurate(accountBasedInAccurate: Boolean) =
            accountBasedInAccurate(JsonField.of(accountBasedInAccurate))

        /** Sets [Builder.accountBasedInAccurate] to an arbitrary JSON value. */
        fun accountBasedInAccurate(accountBasedInAccurate: JsonField<Boolean>) = apply {
            this.accountBasedInAccurate = accountBasedInAccurate
        }

        /** True when X withheld accountBasedIn from this read. Retry later. */
        fun accountBasedInUnavailable(accountBasedInUnavailable: Boolean) =
            accountBasedInUnavailable(JsonField.of(accountBasedInUnavailable))

        /** Sets [Builder.accountBasedInUnavailable] to an arbitrary JSON value. */
        fun accountBasedInUnavailable(accountBasedInUnavailable: JsonField<Boolean>) = apply {
            this.accountBasedInUnavailable = accountBasedInUnavailable
        }

        /**
         * X Ads services the account uses, such as `analytics`, `media_studio` or `smb`. Empty for
         * an account without ads.
         */
        fun advertiserAccountServiceLevels(advertiserAccountServiceLevels: List<String>) =
            advertiserAccountServiceLevels(JsonField.of(advertiserAccountServiceLevels))

        /** Sets [Builder.advertiserAccountServiceLevels] to an arbitrary JSON value. */
        fun advertiserAccountServiceLevels(
            advertiserAccountServiceLevels: JsonField<List<String>>
        ) = apply {
            this.advertiserAccountServiceLevels = advertiserAccountServiceLevels.map {
                it.toMutableList()
            }
        }

        /**
         * Adds a single [String] to [advertiserAccountServiceLevels].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addAdvertiserAccountServiceLevel(advertiserAccountServiceLevel: String) = apply {
            advertiserAccountServiceLevels =
                (advertiserAccountServiceLevels ?: JsonField.of(mutableListOf())).also {
                    checkKnown("advertiserAccountServiceLevels", it)
                        .add(advertiserAccountServiceLevel)
                }
        }

        /**
         * The account's X Ads type. `none` for an account without ads, or `promotable_user` for one
         * that may run them.
         */
        fun advertiserAccountType(advertiserAccountType: String) =
            advertiserAccountType(JsonField.of(advertiserAccountType))

        /** Sets [Builder.advertiserAccountType] to an arbitrary JSON value. */
        fun advertiserAccountType(advertiserAccountType: JsonField<String>) = apply {
            this.advertiserAccountType = advertiserAccountType
        }

        /**
         * Handle of the organization X shows the account is affiliated with, from its About page.
         */
        fun affiliateUsername(affiliateUsername: String) =
            affiliateUsername(JsonField.of(affiliateUsername))

        /** Sets [Builder.affiliateUsername] to an arbitrary JSON value. */
        fun affiliateUsername(affiliateUsername: JsonField<String>) = apply {
            this.affiliateUsername = affiliateUsername
        }

        /** Organization affiliation label shown on an X profile. */
        fun affiliatesHighlightedLabel(affiliatesHighlightedLabel: AffiliatesHighlightedLabel) =
            affiliatesHighlightedLabel(JsonField.of(affiliatesHighlightedLabel))

        /** Sets [Builder.affiliatesHighlightedLabel] to an arbitrary JSON value. */
        fun affiliatesHighlightedLabel(
            affiliatesHighlightedLabel: JsonField<AffiliatesHighlightedLabel>
        ) = apply { this.affiliatesHighlightedLabel = affiliatesHighlightedLabel }

        fun automatedBy(automatedBy: String) = automatedBy(JsonField.of(automatedBy))

        /** Sets [Builder.automatedBy] to an arbitrary JSON value. */
        fun automatedBy(automatedBy: JsonField<String>) = apply { this.automatedBy = automatedBy }

        /** The parts of the birthday the account made public. */
        fun birthdate(birthdate: Birthdate) = birthdate(JsonField.of(birthdate))

        /** Sets [Builder.birthdate] to an arbitrary JSON value. */
        fun birthdate(birthdate: JsonField<Birthdate>) = apply { this.birthdate = birthdate }

        fun businessAccountAffiliatesCount(businessAccountAffiliatesCount: Long) =
            businessAccountAffiliatesCount(JsonField.of(businessAccountAffiliatesCount))

        /** Sets [Builder.businessAccountAffiliatesCount] to an arbitrary JSON value. */
        fun businessAccountAffiliatesCount(businessAccountAffiliatesCount: JsonField<Long>) =
            apply {
                this.businessAccountAffiliatesCount = businessAccountAffiliatesCount
            }

        /** Whether X treats the profile as a business profile. `none` or `enabled`. */
        fun businessProfileState(businessProfileState: String) =
            businessProfileState(JsonField.of(businessProfileState))

        /** Sets [Builder.businessProfileState] to an arbitrary JSON value. */
        fun businessProfileState(businessProfileState: JsonField<String>) = apply {
            this.businessProfileState = businessProfileState
        }

        /** Community role when returned by community member reads */
        fun communityRole(communityRole: String) = communityRole(JsonField.of(communityRole))

        /** Sets [Builder.communityRole] to an arbitrary JSON value. */
        fun communityRole(communityRole: JsonField<String>) = apply {
            this.communityRole = communityRole
        }

        /** How the account first connected to X. */
        fun connectedVia(connectedVia: String) = connectedVia(JsonField.of(connectedVia))

        /** Sets [Builder.connectedVia] to an arbitrary JSON value. */
        fun connectedVia(connectedVia: JsonField<String>) = apply {
            this.connectedVia = connectedVia
        }

        /** False when X notes that country may be inaccurate. */
        fun connectedViaAccurate(connectedViaAccurate: Boolean) =
            connectedViaAccurate(JsonField.of(connectedViaAccurate))

        /** Sets [Builder.connectedViaAccurate] to an arbitrary JSON value. */
        fun connectedViaAccurate(connectedViaAccurate: JsonField<Boolean>) = apply {
            this.connectedViaAccurate = connectedViaAccurate
        }

        /**
         * Emails, phone numbers & links the account publishes on its own profile. Omitted when it
         * publishes none.
         */
        fun contactInfo(contactInfo: ContactInfo) = contactInfo(JsonField.of(contactInfo))

        /** Sets [Builder.contactInfo] to an arbitrary JSON value. */
        fun contactInfo(contactInfo: JsonField<ContactInfo>) = apply {
            this.contactInfo = contactInfo
        }

        fun coverPicture(coverPicture: String) = coverPicture(JsonField.of(coverPicture))

        /** Sets [Builder.coverPicture] to an arbitrary JSON value. */
        fun coverPicture(coverPicture: JsonField<String>) = apply {
            this.coverPicture = coverPicture
        }

        /** Account creation time. */
        fun createdAt(createdAt: String) = createdAt(JsonField.of(createdAt))

        /** Sets [Builder.createdAt] to an arbitrary JSON value. */
        fun createdAt(createdAt: JsonField<String>) = apply { this.createdAt = createdAt }

        fun creatorSubscriptionsCount(creatorSubscriptionsCount: Long) =
            creatorSubscriptionsCount(JsonField.of(creatorSubscriptionsCount))

        /** Sets [Builder.creatorSubscriptionsCount] to an arbitrary JSON value. */
        fun creatorSubscriptionsCount(creatorSubscriptionsCount: JsonField<Long>) = apply {
            this.creatorSubscriptionsCount = creatorSubscriptionsCount
        }

        /** Whether the profile keeps X's default theme. */
        fun defaultProfile(defaultProfile: Boolean) = defaultProfile(JsonField.of(defaultProfile))

        /** Sets [Builder.defaultProfile] to an arbitrary JSON value. */
        fun defaultProfile(defaultProfile: JsonField<Boolean>) = apply {
            this.defaultProfile = defaultProfile
        }

        /** Whether the profile keeps X's default avatar. */
        fun defaultProfileImage(defaultProfileImage: Boolean) =
            defaultProfileImage(JsonField.of(defaultProfileImage))

        /** Sets [Builder.defaultProfileImage] to an arbitrary JSON value. */
        fun defaultProfileImage(defaultProfileImage: JsonField<Boolean>) = apply {
            this.defaultProfileImage = defaultProfileImage
        }

        fun description(description: String) = description(JsonField.of(description))

        /** Sets [Builder.description] to an arbitrary JSON value. */
        fun description(description: JsonField<String>) = apply { this.description = description }

        /**
         * First email the account publishes on its profile, lowercased. Omitted when it publishes
         * none.
         */
        fun email(email: String) = email(JsonField.of(email))

        /** Sets [Builder.email] to an arbitrary JSON value. */
        fun email(email: JsonField<String>) = apply { this.email = email }

        /** Followers X counts as fast followers. */
        fun fastFollowersCount(fastFollowersCount: Long) =
            fastFollowersCount(JsonField.of(fastFollowersCount))

        /** Sets [Builder.fastFollowersCount] to an arbitrary JSON value. */
        fun fastFollowersCount(fastFollowersCount: JsonField<Long>) = apply {
            this.fastFollowersCount = fastFollowersCount
        }

        fun favouritesCount(favouritesCount: Long) = favouritesCount(JsonField.of(favouritesCount))

        /** Sets [Builder.favouritesCount] to an arbitrary JSON value. */
        fun favouritesCount(favouritesCount: JsonField<Long>) = apply {
            this.favouritesCount = favouritesCount
        }

        /** Omitted when X sends no count, as for the account that first posted reused media. */
        fun followers(followers: Long) = followers(JsonField.of(followers))

        /** Sets [Builder.followers] to an arbitrary JSON value. */
        fun followers(followers: JsonField<Long>) = apply { this.followers = followers }

        fun following(following: Long) = following(JsonField.of(following))

        /** Sets [Builder.following] to an arbitrary JSON value. */
        fun following(following: JsonField<Long>) = apply { this.following = following }

        /** Whether the profile may add a location to posts. */
        fun geoEnabled(geoEnabled: Boolean) = geoEnabled(JsonField.of(geoEnabled))

        /** Sets [Builder.geoEnabled] to an arbitrary JSON value. */
        fun geoEnabled(geoEnabled: JsonField<Boolean>) = apply { this.geoEnabled = geoEnabled }

        /** Public metadata whose fields X defines. */
        fun grokTranslatedBio(grokTranslatedBio: GrokTranslatedBio) =
            grokTranslatedBio(JsonField.of(grokTranslatedBio))

        /** Sets [Builder.grokTranslatedBio] to an arbitrary JSON value. */
        fun grokTranslatedBio(grokTranslatedBio: JsonField<GrokTranslatedBio>) = apply {
            this.grokTranslatedBio = grokTranslatedBio
        }

        fun hasCustomTimelines(hasCustomTimelines: Boolean) =
            hasCustomTimelines(JsonField.of(hasCustomTimelines))

        /** Sets [Builder.hasCustomTimelines] to an arbitrary JSON value. */
        fun hasCustomTimelines(hasCustomTimelines: JsonField<Boolean>) = apply {
            this.hasCustomTimelines = hasCustomTimelines
        }

        /** Whether the profile filled X's extended fields, such as a birth date. True or false. */
        fun hasExtendedProfile(hasExtendedProfile: Boolean) =
            hasExtendedProfile(JsonField.of(hasExtendedProfile))

        /** Sets [Builder.hasExtendedProfile] to an arbitrary JSON value. */
        fun hasExtendedProfile(hasExtendedProfile: JsonField<Boolean>) = apply {
            this.hasExtendedProfile = hasExtendedProfile
        }

        fun hasGraduatedAccess(hasGraduatedAccess: Boolean) =
            hasGraduatedAccess(JsonField.of(hasGraduatedAccess))

        /** Sets [Builder.hasGraduatedAccess] to an arbitrary JSON value. */
        fun hasGraduatedAccess(hasGraduatedAccess: JsonField<Boolean>) = apply {
            this.hasGraduatedAccess = hasGraduatedAccess
        }

        fun hasHiddenSubscriptionsOnProfile(hasHiddenSubscriptionsOnProfile: Boolean) =
            hasHiddenSubscriptionsOnProfile(JsonField.of(hasHiddenSubscriptionsOnProfile))

        /** Sets [Builder.hasHiddenSubscriptionsOnProfile] to an arbitrary JSON value. */
        fun hasHiddenSubscriptionsOnProfile(hasHiddenSubscriptionsOnProfile: JsonField<Boolean>) =
            apply {
                this.hasHiddenSubscriptionsOnProfile = hasHiddenSubscriptionsOnProfile
            }

        /** Profile highlight availability and count metadata. */
        fun highlightsInfo(highlightsInfo: HighlightsInfo) =
            highlightsInfo(JsonField.of(highlightsInfo))

        /** Sets [Builder.highlightsInfo] to an arbitrary JSON value. */
        fun highlightsInfo(highlightsInfo: JsonField<HighlightsInfo>) = apply {
            this.highlightsInfo = highlightsInfo
        }

        /** Identity verification metadata displayed by X. */
        fun identityVerification(identityVerification: IdentityVerification) =
            identityVerification(JsonField.of(identityVerification))

        /** Sets [Builder.identityVerification] to an arbitrary JSON value. */
        fun identityVerification(identityVerification: JsonField<IdentityVerification>) = apply {
            this.identityVerification = identityVerification
        }

        fun isAutomated(isAutomated: Boolean) = isAutomated(JsonField.of(isAutomated))

        /** Sets [Builder.isAutomated] to an arbitrary JSON value. */
        fun isAutomated(isAutomated: JsonField<Boolean>) = apply { this.isAutomated = isAutomated }

        /** Whether X shows a blue verification badge */
        fun isBlueVerified(isBlueVerified: Boolean) = isBlueVerified(JsonField.of(isBlueVerified))

        /** Sets [Builder.isBlueVerified] to an arbitrary JSON value. */
        fun isBlueVerified(isBlueVerified: JsonField<Boolean>) = apply {
            this.isBlueVerified = isBlueVerified
        }

        fun isProfileTranslatable(isProfileTranslatable: Boolean) =
            isProfileTranslatable(JsonField.of(isProfileTranslatable))

        /** Sets [Builder.isProfileTranslatable] to an arbitrary JSON value. */
        fun isProfileTranslatable(isProfileTranslatable: JsonField<Boolean>) = apply {
            this.isProfileTranslatable = isProfileTranslatable
        }

        /** Whether the account turned on X's translation of its posts. True or false. */
        fun isTranslationEnabled(isTranslationEnabled: Boolean) =
            isTranslationEnabled(JsonField.of(isTranslationEnabled))

        /** Sets [Builder.isTranslationEnabled] to an arbitrary JSON value. */
        fun isTranslationEnabled(isTranslationEnabled: JsonField<Boolean>) = apply {
            this.isTranslationEnabled = isTranslationEnabled
        }

        fun isTranslator(isTranslator: Boolean) = isTranslator(JsonField.of(isTranslator))

        /** Sets [Builder.isTranslator] to an arbitrary JSON value. */
        fun isTranslator(isTranslator: JsonField<Boolean>) = apply {
            this.isTranslator = isTranslator
        }

        /** Whether X marks the profile as verified */
        fun isVerified(isVerified: Boolean) = isVerified(JsonField.of(isVerified))

        /** Sets [Builder.isVerified] to an arbitrary JSON value. */
        fun isVerified(isVerified: JsonField<Boolean>) = apply { this.isVerified = isVerified }

        /** Public lists that include the profile. */
        fun listedCount(listedCount: Long) = listedCount(JsonField.of(listedCount))

        /** Sets [Builder.listedCount] to an arbitrary JSON value. */
        fun listedCount(listedCount: JsonField<Long>) = apply { this.listedCount = listedCount }

        /** Account owner's public profile location text */
        fun location(location: String) = location(JsonField.of(location))

        /** Sets [Builder.location] to an arbitrary JSON value. */
        fun location(location: JsonField<String>) = apply { this.location = location }

        fun mediaCount(mediaCount: Long) = mediaCount(JsonField.of(mediaCount))

        /** Sets [Builder.mediaCount] to an arbitrary JSON value. */
        fun mediaCount(mediaCount: JsonField<Long>) = apply { this.mediaCount = mediaCount }

        /** Followers minus fast followers. */
        fun normalFollowersCount(normalFollowersCount: Long) =
            normalFollowersCount(JsonField.of(normalFollowersCount))

        /** Sets [Builder.normalFollowersCount] to an arbitrary JSON value. */
        fun normalFollowersCount(normalFollowersCount: JsonField<Long>) = apply {
            this.normalFollowersCount = normalFollowersCount
        }

        fun parodyCommentaryFanLabel(parodyCommentaryFanLabel: String) =
            parodyCommentaryFanLabel(JsonField.of(parodyCommentaryFanLabel))

        /** Sets [Builder.parodyCommentaryFanLabel] to an arbitrary JSON value. */
        fun parodyCommentaryFanLabel(parodyCommentaryFanLabel: JsonField<String>) = apply {
            this.parodyCommentaryFanLabel = parodyCommentaryFanLabel
        }

        fun pinnedTweetIds(pinnedTweetIds: List<String>) =
            pinnedTweetIds(JsonField.of(pinnedTweetIds))

        /** Sets [Builder.pinnedTweetIds] to an arbitrary JSON value. */
        fun pinnedTweetIds(pinnedTweetIds: JsonField<List<String>>) = apply {
            this.pinnedTweetIds = pinnedTweetIds.map { it.toMutableList() }
        }

        /**
         * Adds a single [String] to [pinnedTweetIds].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addPinnedTweetId(pinnedTweetId: String) = apply {
            pinnedTweetIds =
                (pinnedTweetIds ?: JsonField.of(mutableListOf())).also {
                    checkKnown("pinnedTweetIds", it).add(pinnedTweetId)
                }
        }

        fun possiblySensitive(possiblySensitive: Boolean) =
            possiblySensitive(JsonField.of(possiblySensitive))

        /** Sets [Builder.possiblySensitive] to an arbitrary JSON value. */
        fun possiblySensitive(possiblySensitive: JsonField<Boolean>) = apply {
            this.possiblySensitive = possiblySensitive
        }

        /** Public metadata whose fields X defines. */
        fun professional(professional: Professional) = professional(JsonField.of(professional))

        /** Sets [Builder.professional] to an arbitrary JSON value. */
        fun professional(professional: JsonField<Professional>) = apply {
            this.professional = professional
        }

        /** Original X profile banner field when available */
        fun profileBannerUrl(profileBannerUrl: String) =
            profileBannerUrl(JsonField.of(profileBannerUrl))

        /** Sets [Builder.profileBannerUrl] to an arbitrary JSON value. */
        fun profileBannerUrl(profileBannerUrl: JsonField<String>) = apply {
            this.profileBannerUrl = profileBannerUrl
        }

        fun profileDescriptionLanguage(profileDescriptionLanguage: String) =
            profileDescriptionLanguage(JsonField.of(profileDescriptionLanguage))

        /** Sets [Builder.profileDescriptionLanguage] to an arbitrary JSON value. */
        fun profileDescriptionLanguage(profileDescriptionLanguage: JsonField<String>) = apply {
            this.profileDescriptionLanguage = profileDescriptionLanguage
        }

        fun profileImageShape(profileImageShape: String) =
            profileImageShape(JsonField.of(profileImageShape))

        /** Sets [Builder.profileImageShape] to an arbitrary JSON value. */
        fun profileImageShape(profileImageShape: JsonField<String>) = apply {
            this.profileImageShape = profileImageShape
        }

        fun profileInterstitialType(profileInterstitialType: String) =
            profileInterstitialType(JsonField.of(profileInterstitialType))

        /** Sets [Builder.profileInterstitialType] to an arbitrary JSON value. */
        fun profileInterstitialType(profileInterstitialType: JsonField<String>) = apply {
            this.profileInterstitialType = profileInterstitialType
        }

        /** Profile picture at 48 px, as X sends it. */
        fun profilePicture(profilePicture: String) = profilePicture(JsonField.of(profilePicture))

        /** Sets [Builder.profilePicture] to an arbitrary JSON value. */
        fun profilePicture(profilePicture: JsonField<String>) = apply {
            this.profilePicture = profilePicture
        }

        /**
         * The same profile picture at full size, as the account uploaded it. Download this one for
         * the image.
         */
        fun profilePictureOriginal(profilePictureOriginal: String) =
            profilePictureOriginal(JsonField.of(profilePictureOriginal))

        /** Sets [Builder.profilePictureOriginal] to an arbitrary JSON value. */
        fun profilePictureOriginal(profilePictureOriginal: JsonField<String>) = apply {
            this.profilePictureOriginal = profilePictureOriginal
        }

        fun profileSortEnabled(profileSortEnabled: Boolean) =
            profileSortEnabled(JsonField.of(profileSortEnabled))

        /** Sets [Builder.profileSortEnabled] to an arbitrary JSON value. */
        fun profileSortEnabled(profileSortEnabled: JsonField<Boolean>) = apply {
            this.profileSortEnabled = profileSortEnabled
        }

        fun profileTranslatorType(profileTranslatorType: String) =
            profileTranslatorType(JsonField.of(profileTranslatorType))

        /** Sets [Builder.profileTranslatorType] to an arbitrary JSON value. */
        fun profileTranslatorType(profileTranslatorType: JsonField<String>) = apply {
            this.profileTranslatorType = profileTranslatorType
        }

        /** Structured profile bio with entity annotations */
        fun profileBio(profileBio: ProfileBio) = profileBio(JsonField.of(profileBio))

        /** Sets [Builder.profileBio] to an arbitrary JSON value. */
        fun profileBio(profileBio: JsonField<ProfileBio>) = apply { this.profileBio = profileBio }

        /** Whether the profile protects its posts */
        fun protected(protected: Boolean) = protected(JsonField.of(protected))

        /** Sets [Builder.protected] to an arbitrary JSON value. */
        fun protected(protected: JsonField<Boolean>) = apply { this.protected = protected }

        /**
         * Handle x.com sends this profile's visitors to, such as the new handle of a renamed
         * account whose old handle now holds a placeholder. Missing when x.com shows the profile
         * itself.
         */
        fun redirectsToUsername(redirectsToUsername: String) =
            redirectsToUsername(JsonField.of(redirectsToUsername))

        /** Sets [Builder.redirectsToUsername] to an arbitrary JSON value. */
        fun redirectsToUsername(redirectsToUsername: JsonField<String>) = apply {
            this.redirectsToUsername = redirectsToUsername
        }

        /**
         * UTC repost time with includeRetweetTimestamp. Null if the newest profile page has no
         * match or lookup failed. Otherwise omitted.
         */
        fun retweetedAt(retweetedAt: OffsetDateTime?) =
            retweetedAt(JsonField.ofNullable(retweetedAt))

        /** Sets [Builder.retweetedAt] to an arbitrary JSON value. */
        fun retweetedAt(retweetedAt: JsonField<OffsetDateTime>) = apply {
            this.retweetedAt = retweetedAt
        }

        fun statusesCount(statusesCount: Long) = statusesCount(JsonField.of(statusesCount))

        /** Sets [Builder.statusesCount] to an arbitrary JSON value. */
        fun statusesCount(statusesCount: JsonField<Long>) = apply {
            this.statusesCount = statusesCount
        }

        /** Accounts subscribed to the profile's paid Subscriptions. 0 when it has none. */
        fun subscribersCount(subscribersCount: Long) =
            subscribersCount(JsonField.of(subscribersCount))

        /** Sets [Builder.subscribersCount] to an arbitrary JSON value. */
        fun subscribersCount(subscribersCount: JsonField<Long>) = apply {
            this.subscribersCount = subscribersCount
        }

        fun superFollowEligible(superFollowEligible: Boolean) =
            superFollowEligible(JsonField.of(superFollowEligible))

        /** Sets [Builder.superFollowEligible] to an arbitrary JSON value. */
        fun superFollowEligible(superFollowEligible: JsonField<Boolean>) = apply {
            this.superFollowEligible = superFollowEligible
        }

        /** Whether X marks the subscription profile as active. */
        fun superFollowsUserProfileActive(superFollowsUserProfileActive: Boolean) =
            superFollowsUserProfileActive(JsonField.of(superFollowsUserProfileActive))

        /** Sets [Builder.superFollowsUserProfileActive] to an arbitrary JSON value. */
        fun superFollowsUserProfileActive(superFollowsUserProfileActive: JsonField<Boolean>) =
            apply {
                this.superFollowsUserProfileActive = superFollowsUserProfileActive
            }

        /** Public payment and creator-support handles shown on X. */
        fun tipJar(tipJar: TipJar) = tipJar(JsonField.of(tipJar))

        /** Sets [Builder.tipJar] to an arbitrary JSON value. */
        fun tipJar(tipJar: JsonField<TipJar>) = apply { this.tipJar = tipJar }

        fun unavailable(unavailable: Boolean) = unavailable(JsonField.of(unavailable))

        /** Sets [Builder.unavailable] to an arbitrary JSON value. */
        fun unavailable(unavailable: JsonField<Boolean>) = apply { this.unavailable = unavailable }

        fun unavailableReason(unavailableReason: String) =
            unavailableReason(JsonField.of(unavailableReason))

        /** Sets [Builder.unavailableReason] to an arbitrary JSON value. */
        fun unavailableReason(unavailableReason: JsonField<String>) = apply {
            this.unavailableReason = unavailableReason
        }

        fun url(url: String) = url(JsonField.of(url))

        /** Sets [Builder.url] to an arbitrary JSON value. */
        fun url(url: JsonField<String>) = apply { this.url = url }

        /** How often the account changed its username. */
        fun usernameChanges(usernameChanges: UsernameChanges) =
            usernameChanges(JsonField.of(usernameChanges))

        /** Sets [Builder.usernameChanges] to an arbitrary JSON value. */
        fun usernameChanges(usernameChanges: JsonField<UsernameChanges>) = apply {
            this.usernameChanges = usernameChanges
        }

        /** X's text on why the profile is verified, with its links. */
        fun verificationReason(verificationReason: VerificationReason) =
            verificationReason(JsonField.of(verificationReason))

        /** Sets [Builder.verificationReason] to an arbitrary JSON value. */
        fun verificationReason(verificationReason: JsonField<VerificationReason>) = apply {
            this.verificationReason = verificationReason
        }

        fun verified(verified: Boolean) = verified(JsonField.of(verified))

        /** Sets [Builder.verified] to an arbitrary JSON value. */
        fun verified(verified: JsonField<Boolean>) = apply { this.verified = verified }

        fun verifiedType(verifiedType: String) = verifiedType(JsonField.of(verifiedType))

        /** Sets [Builder.verifiedType] to an arbitrary JSON value. */
        fun verifiedType(verifiedType: JsonField<String>) = apply {
            this.verifiedType = verifiedType
        }

        fun withheldInCountries(withheldInCountries: List<String>) =
            withheldInCountries(JsonField.of(withheldInCountries))

        /** Sets [Builder.withheldInCountries] to an arbitrary JSON value. */
        fun withheldInCountries(withheldInCountries: JsonField<List<String>>) = apply {
            this.withheldInCountries = withheldInCountries.map { it.toMutableList() }
        }

        /**
         * Adds a single [String] to [withheldInCountries].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addWithheldInCountry(withheldInCountry: String) = apply {
            withheldInCountries =
                (withheldInCountries ?: JsonField.of(mutableListOf())).also {
                    checkKnown("withheldInCountries", it).add(withheldInCountry)
                }
        }

        /** Whether X withholds a post or user */
        fun withheldScope(withheldScope: String) = withheldScope(JsonField.of(withheldScope))

        /** Sets [Builder.withheldScope] to an arbitrary JSON value. */
        fun withheldScope(withheldScope: JsonField<String>) = apply {
            this.withheldScope = withheldScope
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
         * Returns an immutable instance of [UserProfile].
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
        fun build(): UserProfile =
            UserProfile(
                checkRequired("id", id),
                checkRequired("name", name),
                checkRequired("username", username),
                accountBasedIn,
                accountBasedInAccurate,
                accountBasedInUnavailable,
                (advertiserAccountServiceLevels ?: JsonMissing.of()).map { it.toImmutable() },
                advertiserAccountType,
                affiliateUsername,
                affiliatesHighlightedLabel,
                automatedBy,
                birthdate,
                businessAccountAffiliatesCount,
                businessProfileState,
                communityRole,
                connectedVia,
                connectedViaAccurate,
                contactInfo,
                coverPicture,
                createdAt,
                creatorSubscriptionsCount,
                defaultProfile,
                defaultProfileImage,
                description,
                email,
                fastFollowersCount,
                favouritesCount,
                followers,
                following,
                geoEnabled,
                grokTranslatedBio,
                hasCustomTimelines,
                hasExtendedProfile,
                hasGraduatedAccess,
                hasHiddenSubscriptionsOnProfile,
                highlightsInfo,
                identityVerification,
                isAutomated,
                isBlueVerified,
                isProfileTranslatable,
                isTranslationEnabled,
                isTranslator,
                isVerified,
                listedCount,
                location,
                mediaCount,
                normalFollowersCount,
                parodyCommentaryFanLabel,
                (pinnedTweetIds ?: JsonMissing.of()).map { it.toImmutable() },
                possiblySensitive,
                professional,
                profileBannerUrl,
                profileDescriptionLanguage,
                profileImageShape,
                profileInterstitialType,
                profilePicture,
                profilePictureOriginal,
                profileSortEnabled,
                profileTranslatorType,
                profileBio,
                protected,
                redirectsToUsername,
                retweetedAt,
                statusesCount,
                subscribersCount,
                superFollowEligible,
                superFollowsUserProfileActive,
                tipJar,
                unavailable,
                unavailableReason,
                url,
                usernameChanges,
                verificationReason,
                verified,
                verifiedType,
                (withheldInCountries ?: JsonMissing.of()).map { it.toImmutable() },
                withheldScope,
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
    fun validate(): UserProfile = apply {
        if (validated) {
            return@apply
        }

        id()
        name()
        username()
        accountBasedIn()?.validate()
        accountBasedInAccurate()
        accountBasedInUnavailable()
        advertiserAccountServiceLevels()
        advertiserAccountType()
        affiliateUsername()
        affiliatesHighlightedLabel()?.validate()
        automatedBy()
        birthdate()?.validate()
        businessAccountAffiliatesCount()
        businessProfileState()
        communityRole()
        connectedVia()
        connectedViaAccurate()
        contactInfo()?.validate()
        coverPicture()
        createdAt()
        creatorSubscriptionsCount()
        defaultProfile()
        defaultProfileImage()
        description()
        email()
        fastFollowersCount()
        favouritesCount()
        followers()
        following()
        geoEnabled()
        grokTranslatedBio()?.validate()
        hasCustomTimelines()
        hasExtendedProfile()
        hasGraduatedAccess()
        hasHiddenSubscriptionsOnProfile()
        highlightsInfo()?.validate()
        identityVerification()?.validate()
        isAutomated()
        isBlueVerified()
        isProfileTranslatable()
        isTranslationEnabled()
        isTranslator()
        isVerified()
        listedCount()
        location()
        mediaCount()
        normalFollowersCount()
        parodyCommentaryFanLabel()
        pinnedTweetIds()
        possiblySensitive()
        professional()?.validate()
        profileBannerUrl()
        profileDescriptionLanguage()
        profileImageShape()
        profileInterstitialType()
        profilePicture()
        profilePictureOriginal()
        profileSortEnabled()
        profileTranslatorType()
        profileBio()?.validate()
        protected()
        redirectsToUsername()
        retweetedAt()
        statusesCount()
        subscribersCount()
        superFollowEligible()
        superFollowsUserProfileActive()
        tipJar()?.validate()
        unavailable()
        unavailableReason()
        url()
        usernameChanges()?.validate()
        verificationReason()?.validate()
        verified()
        verifiedType()
        withheldInCountries()
        withheldScope()
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
            (accountBasedIn.asKnown()?.validity() ?: 0) +
            (if (accountBasedInAccurate.asKnown() == null) 0 else 1) +
            (if (accountBasedInUnavailable.asKnown() == null) 0 else 1) +
            (advertiserAccountServiceLevels.asKnown()?.size ?: 0) +
            (if (advertiserAccountType.asKnown() == null) 0 else 1) +
            (if (affiliateUsername.asKnown() == null) 0 else 1) +
            (affiliatesHighlightedLabel.asKnown()?.validity() ?: 0) +
            (if (automatedBy.asKnown() == null) 0 else 1) +
            (birthdate.asKnown()?.validity() ?: 0) +
            (if (businessAccountAffiliatesCount.asKnown() == null) 0 else 1) +
            (if (businessProfileState.asKnown() == null) 0 else 1) +
            (if (communityRole.asKnown() == null) 0 else 1) +
            (if (connectedVia.asKnown() == null) 0 else 1) +
            (if (connectedViaAccurate.asKnown() == null) 0 else 1) +
            (contactInfo.asKnown()?.validity() ?: 0) +
            (if (coverPicture.asKnown() == null) 0 else 1) +
            (if (createdAt.asKnown() == null) 0 else 1) +
            (if (creatorSubscriptionsCount.asKnown() == null) 0 else 1) +
            (if (defaultProfile.asKnown() == null) 0 else 1) +
            (if (defaultProfileImage.asKnown() == null) 0 else 1) +
            (if (description.asKnown() == null) 0 else 1) +
            (if (email.asKnown() == null) 0 else 1) +
            (if (fastFollowersCount.asKnown() == null) 0 else 1) +
            (if (favouritesCount.asKnown() == null) 0 else 1) +
            (if (followers.asKnown() == null) 0 else 1) +
            (if (following.asKnown() == null) 0 else 1) +
            (if (geoEnabled.asKnown() == null) 0 else 1) +
            (grokTranslatedBio.asKnown()?.validity() ?: 0) +
            (if (hasCustomTimelines.asKnown() == null) 0 else 1) +
            (if (hasExtendedProfile.asKnown() == null) 0 else 1) +
            (if (hasGraduatedAccess.asKnown() == null) 0 else 1) +
            (if (hasHiddenSubscriptionsOnProfile.asKnown() == null) 0 else 1) +
            (highlightsInfo.asKnown()?.validity() ?: 0) +
            (identityVerification.asKnown()?.validity() ?: 0) +
            (if (isAutomated.asKnown() == null) 0 else 1) +
            (if (isBlueVerified.asKnown() == null) 0 else 1) +
            (if (isProfileTranslatable.asKnown() == null) 0 else 1) +
            (if (isTranslationEnabled.asKnown() == null) 0 else 1) +
            (if (isTranslator.asKnown() == null) 0 else 1) +
            (if (isVerified.asKnown() == null) 0 else 1) +
            (if (listedCount.asKnown() == null) 0 else 1) +
            (if (location.asKnown() == null) 0 else 1) +
            (if (mediaCount.asKnown() == null) 0 else 1) +
            (if (normalFollowersCount.asKnown() == null) 0 else 1) +
            (if (parodyCommentaryFanLabel.asKnown() == null) 0 else 1) +
            (pinnedTweetIds.asKnown()?.size ?: 0) +
            (if (possiblySensitive.asKnown() == null) 0 else 1) +
            (professional.asKnown()?.validity() ?: 0) +
            (if (profileBannerUrl.asKnown() == null) 0 else 1) +
            (if (profileDescriptionLanguage.asKnown() == null) 0 else 1) +
            (if (profileImageShape.asKnown() == null) 0 else 1) +
            (if (profileInterstitialType.asKnown() == null) 0 else 1) +
            (if (profilePicture.asKnown() == null) 0 else 1) +
            (if (profilePictureOriginal.asKnown() == null) 0 else 1) +
            (if (profileSortEnabled.asKnown() == null) 0 else 1) +
            (if (profileTranslatorType.asKnown() == null) 0 else 1) +
            (profileBio.asKnown()?.validity() ?: 0) +
            (if (protected.asKnown() == null) 0 else 1) +
            (if (redirectsToUsername.asKnown() == null) 0 else 1) +
            (if (retweetedAt.asKnown() == null) 0 else 1) +
            (if (statusesCount.asKnown() == null) 0 else 1) +
            (if (subscribersCount.asKnown() == null) 0 else 1) +
            (if (superFollowEligible.asKnown() == null) 0 else 1) +
            (if (superFollowsUserProfileActive.asKnown() == null) 0 else 1) +
            (tipJar.asKnown()?.validity() ?: 0) +
            (if (unavailable.asKnown() == null) 0 else 1) +
            (if (unavailableReason.asKnown() == null) 0 else 1) +
            (if (url.asKnown() == null) 0 else 1) +
            (usernameChanges.asKnown()?.validity() ?: 0) +
            (verificationReason.asKnown()?.validity() ?: 0) +
            (if (verified.asKnown() == null) 0 else 1) +
            (if (verifiedType.asKnown() == null) 0 else 1) +
            (withheldInCountries.asKnown()?.size ?: 0) +
            (if (withheldScope.asKnown() == null) 0 else 1)

    /**
     * X's best-effort public label inferred from aggregated account-access IP addresses. It does
     * not state nationality, residence, identity, registration, post location, or exact location.
     */
    class AccountBasedIn
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val level: JsonField<Level>,
        private val observedAt: JsonField<OffsetDateTime>,
        private val value: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("level") @ExcludeMissing level: JsonField<Level> = JsonMissing.of(),
            @JsonProperty("observedAt")
            @ExcludeMissing
            observedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
            @JsonProperty("value") @ExcludeMissing value: JsonField<String> = JsonMissing.of(),
        ) : this(level, observedAt, value, mutableMapOf())

        /**
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or
         *   is unexpectedly missing or null.
         */
        fun level(): Level = level.getRequired("level")

        /**
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or
         *   is unexpectedly missing or null.
         */
        fun observedAt(): OffsetDateTime = observedAt.getRequired("observedAt")

        /**
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or
         *   is unexpectedly missing or null.
         */
        fun value(): String = value.getRequired("value")

        /**
         * Returns the raw JSON value of [level].
         *
         * Unlike [level], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("level") @ExcludeMissing fun _level(): JsonField<Level> = level

        /**
         * Returns the raw JSON value of [observedAt].
         *
         * Unlike [observedAt], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("observedAt")
        @ExcludeMissing
        fun _observedAt(): JsonField<OffsetDateTime> = observedAt

        /**
         * Returns the raw JSON value of [value].
         *
         * Unlike [value], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("value") @ExcludeMissing fun _value(): JsonField<String> = value

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
             * Returns a mutable builder for constructing an instance of [AccountBasedIn].
             *
             * The following fields are required:
             * ```kotlin
             * .level()
             * .observedAt()
             * .value()
             * ```
             */
            fun builder() = Builder()
        }

        /** A builder for [AccountBasedIn]. */
        class Builder internal constructor() {

            private var level: JsonField<Level>? = null
            private var observedAt: JsonField<OffsetDateTime>? = null
            private var value: JsonField<String>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(accountBasedIn: AccountBasedIn) = apply {
                level = accountBasedIn.level
                observedAt = accountBasedIn.observedAt
                value = accountBasedIn.value
                additionalProperties = accountBasedIn.additionalProperties.toMutableMap()
            }

            fun level(level: Level) = level(JsonField.of(level))

            /** Sets [Builder.level] to an arbitrary JSON value. */
            fun level(level: JsonField<Level>) = apply { this.level = level }

            fun observedAt(observedAt: OffsetDateTime) = observedAt(JsonField.of(observedAt))

            /** Sets [Builder.observedAt] to an arbitrary JSON value. */
            fun observedAt(observedAt: JsonField<OffsetDateTime>) = apply {
                this.observedAt = observedAt
            }

            fun value(value: String) = value(JsonField.of(value))

            /** Sets [Builder.value] to an arbitrary JSON value. */
            fun value(value: JsonField<String>) = apply { this.value = value }

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
             * Returns an immutable instance of [AccountBasedIn].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             *
             * The following fields are required:
             * ```kotlin
             * .level()
             * .observedAt()
             * .value()
             * ```
             *
             * @throws IllegalStateException if any required field is unset.
             */
            fun build(): AccountBasedIn =
                AccountBasedIn(
                    checkRequired("level", level),
                    checkRequired("observedAt", observedAt),
                    checkRequired("value", value),
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
        fun validate(): AccountBasedIn = apply {
            if (validated) {
                return@apply
            }

            level().validate()
            observedAt()
            value()
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
            (level.asKnown()?.validity() ?: 0) +
                (if (observedAt.asKnown() == null) 0 else 1) +
                (if (value.asKnown() == null) 0 else 1)

        class Level @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

            /** Returns this class instance's raw value. */
            @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

            companion object {

                val COUNTRY = of("country")

                val REGION = of("region")

                fun of(value: String) = Level(JsonField.of(value))
            }

            /** An enum containing [Level]'s known values. */
            enum class Known {
                COUNTRY,
                REGION,
            }

            /**
             * An enum containing [Level]'s known values, as well as an [_UNKNOWN] member.
             *
             * An instance of [Level] can contain an unknown value in a couple of cases:
             * - It was deserialized from data that doesn't match any known member. For example, if
             *   the SDK is on an older version than the API, then the API may respond with new
             *   members that the SDK is unaware of.
             * - It was constructed with an arbitrary value using the [of] method.
             */
            enum class Value {
                COUNTRY,
                REGION,
                /**
                 * An enum member indicating that [Level] was instantiated with an unknown value.
                 */
                _UNKNOWN,
            }

            /**
             * Returns an enum member corresponding to this class instance's value, or
             * [Value._UNKNOWN] if the class was instantiated with an unknown value.
             */
            fun value(): Value =
                when (this) {
                    COUNTRY -> Value.COUNTRY
                    REGION -> Value.REGION
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
                    COUNTRY -> Known.COUNTRY
                    REGION -> Known.REGION
                    else -> throw XTwitterScraperInvalidDataException("Unknown Level: $value")
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
            fun validate(): Level = apply {
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

                return other is Level && value == other.value
            }

            override fun hashCode() = value.hashCode()

            override fun toString() = value.toString()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is AccountBasedIn &&
                level == other.level &&
                observedAt == other.observedAt &&
                value == other.value &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(level, observedAt, value, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "AccountBasedIn{level=$level, observedAt=$observedAt, value=$value, additionalProperties=$additionalProperties}"
    }

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
         * Public metadata whose fields X defines.
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

            /** Public metadata whose fields X defines. */
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

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

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

    /** The parts of the birthday the account made public. */
    class Birthdate
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val day: JsonField<Long>,
        private val month: JsonField<Long>,
        private val year: JsonField<Long>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("day") @ExcludeMissing day: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("month") @ExcludeMissing month: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("year") @ExcludeMissing year: JsonField<Long> = JsonMissing.of(),
        ) : this(day, month, year, mutableMapOf())

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun day(): Long? = day.getNullable("day")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun month(): Long? = month.getNullable("month")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun year(): Long? = year.getNullable("year")

        /**
         * Returns the raw JSON value of [day].
         *
         * Unlike [day], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("day") @ExcludeMissing fun _day(): JsonField<Long> = day

        /**
         * Returns the raw JSON value of [month].
         *
         * Unlike [month], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("month") @ExcludeMissing fun _month(): JsonField<Long> = month

        /**
         * Returns the raw JSON value of [year].
         *
         * Unlike [year], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("year") @ExcludeMissing fun _year(): JsonField<Long> = year

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

            /** Returns a mutable builder for constructing an instance of [Birthdate]. */
            fun builder() = Builder()
        }

        /** A builder for [Birthdate]. */
        class Builder internal constructor() {

            private var day: JsonField<Long> = JsonMissing.of()
            private var month: JsonField<Long> = JsonMissing.of()
            private var year: JsonField<Long> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(birthdate: Birthdate) = apply {
                day = birthdate.day
                month = birthdate.month
                year = birthdate.year
                additionalProperties = birthdate.additionalProperties.toMutableMap()
            }

            fun day(day: Long) = day(JsonField.of(day))

            /** Sets [Builder.day] to an arbitrary JSON value. */
            fun day(day: JsonField<Long>) = apply { this.day = day }

            fun month(month: Long) = month(JsonField.of(month))

            /** Sets [Builder.month] to an arbitrary JSON value. */
            fun month(month: JsonField<Long>) = apply { this.month = month }

            fun year(year: Long) = year(JsonField.of(year))

            /** Sets [Builder.year] to an arbitrary JSON value. */
            fun year(year: JsonField<Long>) = apply { this.year = year }

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
             * Returns an immutable instance of [Birthdate].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): Birthdate =
                Birthdate(day, month, year, additionalProperties.toMutableMap())
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
        fun validate(): Birthdate = apply {
            if (validated) {
                return@apply
            }

            day()
            month()
            year()
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
            (if (day.asKnown() == null) 0 else 1) +
                (if (month.asKnown() == null) 0 else 1) +
                (if (year.asKnown() == null) 0 else 1)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Birthdate &&
                day == other.day &&
                month == other.month &&
                year == other.year &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(day, month, year, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Birthdate{day=$day, month=$month, year=$year, additionalProperties=$additionalProperties}"
    }

    /**
     * Emails, phone numbers & links the account publishes on its own profile. Omitted when it
     * publishes none.
     */
    class ContactInfo
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val emails: JsonField<List<Email>>,
        private val links: JsonField<List<Link>>,
        private val phones: JsonField<List<Phone>>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("emails")
            @ExcludeMissing
            emails: JsonField<List<Email>> = JsonMissing.of(),
            @JsonProperty("links") @ExcludeMissing links: JsonField<List<Link>> = JsonMissing.of(),
            @JsonProperty("phones")
            @ExcludeMissing
            phones: JsonField<List<Phone>> = JsonMissing.of(),
        ) : this(emails, links, phones, mutableMapOf())

        /**
         * Emails in the bio, name or location, lowercased.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun emails(): List<Email>? = emails.getNullable("emails")

        /**
         * The profile's website & the links in its bio.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun links(): List<Link>? = links.getNullable("links")

        /**
         * Phone numbers on the profile. Only a spelling that can be nothing else counts.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun phones(): List<Phone>? = phones.getNullable("phones")

        /**
         * Returns the raw JSON value of [emails].
         *
         * Unlike [emails], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("emails") @ExcludeMissing fun _emails(): JsonField<List<Email>> = emails

        /**
         * Returns the raw JSON value of [links].
         *
         * Unlike [links], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("links") @ExcludeMissing fun _links(): JsonField<List<Link>> = links

        /**
         * Returns the raw JSON value of [phones].
         *
         * Unlike [phones], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("phones") @ExcludeMissing fun _phones(): JsonField<List<Phone>> = phones

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

            /** Returns a mutable builder for constructing an instance of [ContactInfo]. */
            fun builder() = Builder()
        }

        /** A builder for [ContactInfo]. */
        class Builder internal constructor() {

            private var emails: JsonField<MutableList<Email>>? = null
            private var links: JsonField<MutableList<Link>>? = null
            private var phones: JsonField<MutableList<Phone>>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(contactInfo: ContactInfo) = apply {
                emails = contactInfo.emails.map { it.toMutableList() }
                links = contactInfo.links.map { it.toMutableList() }
                phones = contactInfo.phones.map { it.toMutableList() }
                additionalProperties = contactInfo.additionalProperties.toMutableMap()
            }

            /** Emails in the bio, name or location, lowercased. */
            fun emails(emails: List<Email>) = emails(JsonField.of(emails))

            /** Sets [Builder.emails] to an arbitrary JSON value. */
            fun emails(emails: JsonField<List<Email>>) = apply {
                this.emails = emails.map { it.toMutableList() }
            }

            /**
             * Adds a single [Email] to [emails].
             *
             * @throws IllegalStateException if the field was previously set to a non-list.
             */
            fun addEmail(email: Email) = apply {
                emails =
                    (emails ?: JsonField.of(mutableListOf())).also {
                        checkKnown("emails", it).add(email)
                    }
            }

            /** The profile's website & the links in its bio. */
            fun links(links: List<Link>) = links(JsonField.of(links))

            /** Sets [Builder.links] to an arbitrary JSON value. */
            fun links(links: JsonField<List<Link>>) = apply {
                this.links = links.map { it.toMutableList() }
            }

            /**
             * Adds a single [Link] to [links].
             *
             * @throws IllegalStateException if the field was previously set to a non-list.
             */
            fun addLink(link: Link) = apply {
                links =
                    (links ?: JsonField.of(mutableListOf())).also {
                        checkKnown("links", it).add(link)
                    }
            }

            /** Phone numbers on the profile. Only a spelling that can be nothing else counts. */
            fun phones(phones: List<Phone>) = phones(JsonField.of(phones))

            /** Sets [Builder.phones] to an arbitrary JSON value. */
            fun phones(phones: JsonField<List<Phone>>) = apply {
                this.phones = phones.map { it.toMutableList() }
            }

            /**
             * Adds a single [Phone] to [phones].
             *
             * @throws IllegalStateException if the field was previously set to a non-list.
             */
            fun addPhone(phone: Phone) = apply {
                phones =
                    (phones ?: JsonField.of(mutableListOf())).also {
                        checkKnown("phones", it).add(phone)
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
             * Returns an immutable instance of [ContactInfo].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): ContactInfo =
                ContactInfo(
                    (emails ?: JsonMissing.of()).map { it.toImmutable() },
                    (links ?: JsonMissing.of()).map { it.toImmutable() },
                    (phones ?: JsonMissing.of()).map { it.toImmutable() },
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
        fun validate(): ContactInfo = apply {
            if (validated) {
                return@apply
            }

            emails()?.forEach { it.validate() }
            links()?.forEach { it.validate() }
            phones()?.forEach { it.validate() }
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
            (emails.asKnown()?.sumOf { it.validity() } ?: 0) +
                (links.asKnown()?.sumOf { it.validity() } ?: 0) +
                (phones.asKnown()?.sumOf { it.validity() } ?: 0)

        class Email
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val domain: JsonField<String>,
            private val source: JsonField<Source>,
            private val value: JsonField<String>,
            private val writtenAs: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("domain")
                @ExcludeMissing
                domain: JsonField<String> = JsonMissing.of(),
                @JsonProperty("source")
                @ExcludeMissing
                source: JsonField<Source> = JsonMissing.of(),
                @JsonProperty("value") @ExcludeMissing value: JsonField<String> = JsonMissing.of(),
                @JsonProperty("writtenAs")
                @ExcludeMissing
                writtenAs: JsonField<String> = JsonMissing.of(),
            ) : this(domain, source, value, writtenAs, mutableMapOf())

            /**
             * The part after the @ sign.
             *
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type
             *   or is unexpectedly missing or null.
             */
            fun domain(): String = domain.getRequired("domain")

            /**
             * Profile field that holds the email.
             *
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type
             *   or is unexpectedly missing or null.
             */
            fun source(): Source = source.getRequired("source")

            /**
             * The email address.
             *
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type
             *   or is unexpectedly missing or null.
             */
            fun value(): String = value.getRequired("value")

            /**
             * The account's own spelling, sent when it spelled the email out.
             *
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun writtenAs(): String? = writtenAs.getNullable("writtenAs")

            /**
             * Returns the raw JSON value of [domain].
             *
             * Unlike [domain], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("domain") @ExcludeMissing fun _domain(): JsonField<String> = domain

            /**
             * Returns the raw JSON value of [source].
             *
             * Unlike [source], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("source") @ExcludeMissing fun _source(): JsonField<Source> = source

            /**
             * Returns the raw JSON value of [value].
             *
             * Unlike [value], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("value") @ExcludeMissing fun _value(): JsonField<String> = value

            /**
             * Returns the raw JSON value of [writtenAs].
             *
             * Unlike [writtenAs], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("writtenAs")
            @ExcludeMissing
            fun _writtenAs(): JsonField<String> = writtenAs

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
                 * Returns a mutable builder for constructing an instance of [Email].
                 *
                 * The following fields are required:
                 * ```kotlin
                 * .domain()
                 * .source()
                 * .value()
                 * ```
                 */
                fun builder() = Builder()
            }

            /** A builder for [Email]. */
            class Builder internal constructor() {

                private var domain: JsonField<String>? = null
                private var source: JsonField<Source>? = null
                private var value: JsonField<String>? = null
                private var writtenAs: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                internal fun from(email: Email) = apply {
                    domain = email.domain
                    source = email.source
                    value = email.value
                    writtenAs = email.writtenAs
                    additionalProperties = email.additionalProperties.toMutableMap()
                }

                /** The part after the @ sign. */
                fun domain(domain: String) = domain(JsonField.of(domain))

                /** Sets [Builder.domain] to an arbitrary JSON value. */
                fun domain(domain: JsonField<String>) = apply { this.domain = domain }

                /** Profile field that holds the email. */
                fun source(source: Source) = source(JsonField.of(source))

                /** Sets [Builder.source] to an arbitrary JSON value. */
                fun source(source: JsonField<Source>) = apply { this.source = source }

                /** The email address. */
                fun value(value: String) = value(JsonField.of(value))

                /** Sets [Builder.value] to an arbitrary JSON value. */
                fun value(value: JsonField<String>) = apply { this.value = value }

                /** The account's own spelling, sent when it spelled the email out. */
                fun writtenAs(writtenAs: String) = writtenAs(JsonField.of(writtenAs))

                /** Sets [Builder.writtenAs] to an arbitrary JSON value. */
                fun writtenAs(writtenAs: JsonField<String>) = apply { this.writtenAs = writtenAs }

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
                 * Returns an immutable instance of [Email].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 *
                 * The following fields are required:
                 * ```kotlin
                 * .domain()
                 * .source()
                 * .value()
                 * ```
                 *
                 * @throws IllegalStateException if any required field is unset.
                 */
                fun build(): Email =
                    Email(
                        checkRequired("domain", domain),
                        checkRequired("source", source),
                        checkRequired("value", value),
                        writtenAs,
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
            fun validate(): Email = apply {
                if (validated) {
                    return@apply
                }

                domain()
                source().validate()
                value()
                writtenAs()
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
                (if (domain.asKnown() == null) 0 else 1) +
                    (source.asKnown()?.validity() ?: 0) +
                    (if (value.asKnown() == null) 0 else 1) +
                    (if (writtenAs.asKnown() == null) 0 else 1)

            /** Profile field that holds the email. */
            class Source @JsonCreator private constructor(private val value: JsonField<String>) :
                Enum {

                /** Returns this class instance's raw value. */
                @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

                companion object {

                    val BIO = of("bio")

                    val NAME = of("name")

                    val LOCATION = of("location")

                    fun of(value: String) = Source(JsonField.of(value))
                }

                /** An enum containing [Source]'s known values. */
                enum class Known {
                    BIO,
                    NAME,
                    LOCATION,
                }

                /**
                 * An enum containing [Source]'s known values, as well as an [_UNKNOWN] member.
                 *
                 * An instance of [Source] can contain an unknown value in a couple of cases:
                 * - It was deserialized from data that doesn't match any known member. For example,
                 *   if the SDK is on an older version than the API, then the API may respond with
                 *   new members that the SDK is unaware of.
                 * - It was constructed with an arbitrary value using the [of] method.
                 */
                enum class Value {
                    BIO,
                    NAME,
                    LOCATION,
                    /**
                     * An enum member indicating that [Source] was instantiated with an unknown
                     * value.
                     */
                    _UNKNOWN,
                }

                /**
                 * Returns an enum member corresponding to this class instance's value, or
                 * [Value._UNKNOWN] if the class was instantiated with an unknown value.
                 */
                fun value(): Value =
                    when (this) {
                        BIO -> Value.BIO
                        NAME -> Value.NAME
                        LOCATION -> Value.LOCATION
                        else -> Value._UNKNOWN
                    }

                /**
                 * Returns an enum member corresponding to this class instance's value.
                 *
                 * @throws XTwitterScraperInvalidDataException if this class instance's value is a
                 *   not a known member.
                 */
                fun known(): Known =
                    when (this) {
                        BIO -> Known.BIO
                        NAME -> Known.NAME
                        LOCATION -> Known.LOCATION
                        else -> throw XTwitterScraperInvalidDataException("Unknown Source: $value")
                    }

                /**
                 * Returns this class instance's primitive wire representation.
                 *
                 * @throws XTwitterScraperInvalidDataException if this class instance's value does
                 *   not have the expected primitive type.
                 */
                fun asString(): String =
                    _value().asString()
                        ?: throw XTwitterScraperInvalidDataException("Value is not a String")

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
                fun validate(): Source = apply {
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

                    return other is Source && value == other.value
                }

                override fun hashCode() = value.hashCode()

                override fun toString() = value.toString()
            }

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is Email &&
                    domain == other.domain &&
                    source == other.source &&
                    value == other.value &&
                    writtenAs == other.writtenAs &&
                    additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(domain, source, value, writtenAs, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "Email{domain=$domain, source=$source, value=$value, writtenAs=$writtenAs, additionalProperties=$additionalProperties}"
        }

        class Link
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val network: JsonField<String>,
            private val source: JsonField<Source>,
            private val url: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("network")
                @ExcludeMissing
                network: JsonField<String> = JsonMissing.of(),
                @JsonProperty("source")
                @ExcludeMissing
                source: JsonField<Source> = JsonMissing.of(),
                @JsonProperty("url") @ExcludeMissing url: JsonField<String> = JsonMissing.of(),
            ) : this(network, source, url, mutableMapOf())

            /**
             * Site the link belongs to, such as `linkedin`, `github` or `telegram`. Any other site
             * is `website`.
             *
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type
             *   or is unexpectedly missing or null.
             */
            fun network(): String = network.getRequired("network")

            /**
             * `url` for the website field, `bio` for a bio link.
             *
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type
             *   or is unexpectedly missing or null.
             */
            fun source(): Source = source.getRequired("source")

            /**
             * The link, as X expands it.
             *
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type
             *   or is unexpectedly missing or null.
             */
            fun url(): String = url.getRequired("url")

            /**
             * Returns the raw JSON value of [network].
             *
             * Unlike [network], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("network") @ExcludeMissing fun _network(): JsonField<String> = network

            /**
             * Returns the raw JSON value of [source].
             *
             * Unlike [source], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("source") @ExcludeMissing fun _source(): JsonField<Source> = source

            /**
             * Returns the raw JSON value of [url].
             *
             * Unlike [url], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("url") @ExcludeMissing fun _url(): JsonField<String> = url

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
                 * Returns a mutable builder for constructing an instance of [Link].
                 *
                 * The following fields are required:
                 * ```kotlin
                 * .network()
                 * .source()
                 * .url()
                 * ```
                 */
                fun builder() = Builder()
            }

            /** A builder for [Link]. */
            class Builder internal constructor() {

                private var network: JsonField<String>? = null
                private var source: JsonField<Source>? = null
                private var url: JsonField<String>? = null
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                internal fun from(link: Link) = apply {
                    network = link.network
                    source = link.source
                    url = link.url
                    additionalProperties = link.additionalProperties.toMutableMap()
                }

                /**
                 * Site the link belongs to, such as `linkedin`, `github` or `telegram`. Any other
                 * site is `website`.
                 */
                fun network(network: String) = network(JsonField.of(network))

                /** Sets [Builder.network] to an arbitrary JSON value. */
                fun network(network: JsonField<String>) = apply { this.network = network }

                /** `url` for the website field, `bio` for a bio link. */
                fun source(source: Source) = source(JsonField.of(source))

                /** Sets [Builder.source] to an arbitrary JSON value. */
                fun source(source: JsonField<Source>) = apply { this.source = source }

                /** The link, as X expands it. */
                fun url(url: String) = url(JsonField.of(url))

                /** Sets [Builder.url] to an arbitrary JSON value. */
                fun url(url: JsonField<String>) = apply { this.url = url }

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
                 * Returns an immutable instance of [Link].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 *
                 * The following fields are required:
                 * ```kotlin
                 * .network()
                 * .source()
                 * .url()
                 * ```
                 *
                 * @throws IllegalStateException if any required field is unset.
                 */
                fun build(): Link =
                    Link(
                        checkRequired("network", network),
                        checkRequired("source", source),
                        checkRequired("url", url),
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
            fun validate(): Link = apply {
                if (validated) {
                    return@apply
                }

                network()
                source().validate()
                url()
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
                (if (network.asKnown() == null) 0 else 1) +
                    (source.asKnown()?.validity() ?: 0) +
                    (if (url.asKnown() == null) 0 else 1)

            /** `url` for the website field, `bio` for a bio link. */
            class Source @JsonCreator private constructor(private val value: JsonField<String>) :
                Enum {

                /** Returns this class instance's raw value. */
                @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

                companion object {

                    val URL = of("url")

                    val BIO = of("bio")

                    fun of(value: String) = Source(JsonField.of(value))
                }

                /** An enum containing [Source]'s known values. */
                enum class Known {
                    URL,
                    BIO,
                }

                /**
                 * An enum containing [Source]'s known values, as well as an [_UNKNOWN] member.
                 *
                 * An instance of [Source] can contain an unknown value in a couple of cases:
                 * - It was deserialized from data that doesn't match any known member. For example,
                 *   if the SDK is on an older version than the API, then the API may respond with
                 *   new members that the SDK is unaware of.
                 * - It was constructed with an arbitrary value using the [of] method.
                 */
                enum class Value {
                    URL,
                    BIO,
                    /**
                     * An enum member indicating that [Source] was instantiated with an unknown
                     * value.
                     */
                    _UNKNOWN,
                }

                /**
                 * Returns an enum member corresponding to this class instance's value, or
                 * [Value._UNKNOWN] if the class was instantiated with an unknown value.
                 */
                fun value(): Value =
                    when (this) {
                        URL -> Value.URL
                        BIO -> Value.BIO
                        else -> Value._UNKNOWN
                    }

                /**
                 * Returns an enum member corresponding to this class instance's value.
                 *
                 * @throws XTwitterScraperInvalidDataException if this class instance's value is a
                 *   not a known member.
                 */
                fun known(): Known =
                    when (this) {
                        URL -> Known.URL
                        BIO -> Known.BIO
                        else -> throw XTwitterScraperInvalidDataException("Unknown Source: $value")
                    }

                /**
                 * Returns this class instance's primitive wire representation.
                 *
                 * @throws XTwitterScraperInvalidDataException if this class instance's value does
                 *   not have the expected primitive type.
                 */
                fun asString(): String =
                    _value().asString()
                        ?: throw XTwitterScraperInvalidDataException("Value is not a String")

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
                fun validate(): Source = apply {
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

                    return other is Source && value == other.value
                }

                override fun hashCode() = value.hashCode()

                override fun toString() = value.toString()
            }

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is Link &&
                    network == other.network &&
                    source == other.source &&
                    url == other.url &&
                    additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(network, source, url, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "Link{network=$network, source=$source, url=$url, additionalProperties=$additionalProperties}"
        }

        class Phone
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val source: JsonField<Source>,
            private val value: JsonField<String>,
            private val writtenAs: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("source")
                @ExcludeMissing
                source: JsonField<Source> = JsonMissing.of(),
                @JsonProperty("value") @ExcludeMissing value: JsonField<String> = JsonMissing.of(),
                @JsonProperty("writtenAs")
                @ExcludeMissing
                writtenAs: JsonField<String> = JsonMissing.of(),
            ) : this(source, value, writtenAs, mutableMapOf())

            /**
             * Profile field that holds the number.
             *
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type
             *   or is unexpectedly missing or null.
             */
            fun source(): Source = source.getRequired("source")

            /**
             * The digits, with a leading + when the account wrote a country code.
             *
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type
             *   or is unexpectedly missing or null.
             */
            fun value(): String = value.getRequired("value")

            /**
             * The account's own spelling of the number.
             *
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun writtenAs(): String? = writtenAs.getNullable("writtenAs")

            /**
             * Returns the raw JSON value of [source].
             *
             * Unlike [source], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("source") @ExcludeMissing fun _source(): JsonField<Source> = source

            /**
             * Returns the raw JSON value of [value].
             *
             * Unlike [value], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("value") @ExcludeMissing fun _value(): JsonField<String> = value

            /**
             * Returns the raw JSON value of [writtenAs].
             *
             * Unlike [writtenAs], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("writtenAs")
            @ExcludeMissing
            fun _writtenAs(): JsonField<String> = writtenAs

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
                 * Returns a mutable builder for constructing an instance of [Phone].
                 *
                 * The following fields are required:
                 * ```kotlin
                 * .source()
                 * .value()
                 * ```
                 */
                fun builder() = Builder()
            }

            /** A builder for [Phone]. */
            class Builder internal constructor() {

                private var source: JsonField<Source>? = null
                private var value: JsonField<String>? = null
                private var writtenAs: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                internal fun from(phone: Phone) = apply {
                    source = phone.source
                    value = phone.value
                    writtenAs = phone.writtenAs
                    additionalProperties = phone.additionalProperties.toMutableMap()
                }

                /** Profile field that holds the number. */
                fun source(source: Source) = source(JsonField.of(source))

                /** Sets [Builder.source] to an arbitrary JSON value. */
                fun source(source: JsonField<Source>) = apply { this.source = source }

                /** The digits, with a leading + when the account wrote a country code. */
                fun value(value: String) = value(JsonField.of(value))

                /** Sets [Builder.value] to an arbitrary JSON value. */
                fun value(value: JsonField<String>) = apply { this.value = value }

                /** The account's own spelling of the number. */
                fun writtenAs(writtenAs: String) = writtenAs(JsonField.of(writtenAs))

                /** Sets [Builder.writtenAs] to an arbitrary JSON value. */
                fun writtenAs(writtenAs: JsonField<String>) = apply { this.writtenAs = writtenAs }

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
                 * Returns an immutable instance of [Phone].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 *
                 * The following fields are required:
                 * ```kotlin
                 * .source()
                 * .value()
                 * ```
                 *
                 * @throws IllegalStateException if any required field is unset.
                 */
                fun build(): Phone =
                    Phone(
                        checkRequired("source", source),
                        checkRequired("value", value),
                        writtenAs,
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
            fun validate(): Phone = apply {
                if (validated) {
                    return@apply
                }

                source().validate()
                value()
                writtenAs()
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
                (source.asKnown()?.validity() ?: 0) +
                    (if (value.asKnown() == null) 0 else 1) +
                    (if (writtenAs.asKnown() == null) 0 else 1)

            /** Profile field that holds the number. */
            class Source @JsonCreator private constructor(private val value: JsonField<String>) :
                Enum {

                /** Returns this class instance's raw value. */
                @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

                companion object {

                    val BIO = of("bio")

                    val NAME = of("name")

                    val LOCATION = of("location")

                    val URL = of("url")

                    fun of(value: String) = Source(JsonField.of(value))
                }

                /** An enum containing [Source]'s known values. */
                enum class Known {
                    BIO,
                    NAME,
                    LOCATION,
                    URL,
                }

                /**
                 * An enum containing [Source]'s known values, as well as an [_UNKNOWN] member.
                 *
                 * An instance of [Source] can contain an unknown value in a couple of cases:
                 * - It was deserialized from data that doesn't match any known member. For example,
                 *   if the SDK is on an older version than the API, then the API may respond with
                 *   new members that the SDK is unaware of.
                 * - It was constructed with an arbitrary value using the [of] method.
                 */
                enum class Value {
                    BIO,
                    NAME,
                    LOCATION,
                    URL,
                    /**
                     * An enum member indicating that [Source] was instantiated with an unknown
                     * value.
                     */
                    _UNKNOWN,
                }

                /**
                 * Returns an enum member corresponding to this class instance's value, or
                 * [Value._UNKNOWN] if the class was instantiated with an unknown value.
                 */
                fun value(): Value =
                    when (this) {
                        BIO -> Value.BIO
                        NAME -> Value.NAME
                        LOCATION -> Value.LOCATION
                        URL -> Value.URL
                        else -> Value._UNKNOWN
                    }

                /**
                 * Returns an enum member corresponding to this class instance's value.
                 *
                 * @throws XTwitterScraperInvalidDataException if this class instance's value is a
                 *   not a known member.
                 */
                fun known(): Known =
                    when (this) {
                        BIO -> Known.BIO
                        NAME -> Known.NAME
                        LOCATION -> Known.LOCATION
                        URL -> Known.URL
                        else -> throw XTwitterScraperInvalidDataException("Unknown Source: $value")
                    }

                /**
                 * Returns this class instance's primitive wire representation.
                 *
                 * @throws XTwitterScraperInvalidDataException if this class instance's value does
                 *   not have the expected primitive type.
                 */
                fun asString(): String =
                    _value().asString()
                        ?: throw XTwitterScraperInvalidDataException("Value is not a String")

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
                fun validate(): Source = apply {
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

                    return other is Source && value == other.value
                }

                override fun hashCode() = value.hashCode()

                override fun toString() = value.toString()
            }

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is Phone &&
                    source == other.source &&
                    value == other.value &&
                    writtenAs == other.writtenAs &&
                    additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(source, value, writtenAs, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "Phone{source=$source, value=$value, writtenAs=$writtenAs, additionalProperties=$additionalProperties}"
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is ContactInfo &&
                emails == other.emails &&
                links == other.links &&
                phones == other.phones &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(emails, links, phones, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "ContactInfo{emails=$emails, links=$links, phones=$phones, additionalProperties=$additionalProperties}"
    }

    /** Public metadata whose fields X defines. */
    class GrokTranslatedBio
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

            /** Returns a mutable builder for constructing an instance of [GrokTranslatedBio]. */
            fun builder() = Builder()
        }

        /** A builder for [GrokTranslatedBio]. */
        class Builder internal constructor() {

            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(grokTranslatedBio: GrokTranslatedBio) = apply {
                additionalProperties = grokTranslatedBio.additionalProperties.toMutableMap()
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
             * Returns an immutable instance of [GrokTranslatedBio].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): GrokTranslatedBio = GrokTranslatedBio(additionalProperties.toImmutable())
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
        fun validate(): GrokTranslatedBio = apply {
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

            return other is GrokTranslatedBio && additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() = "GrokTranslatedBio{additionalProperties=$additionalProperties}"
    }

    /** Profile highlight availability and count metadata. */
    class HighlightsInfo
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val canHighlightTweets: JsonField<Boolean>,
        private val highlightedTweets: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("canHighlightTweets")
            @ExcludeMissing
            canHighlightTweets: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("highlightedTweets")
            @ExcludeMissing
            highlightedTweets: JsonField<String> = JsonMissing.of(),
        ) : this(canHighlightTweets, highlightedTweets, mutableMapOf())

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun canHighlightTweets(): Boolean? = canHighlightTweets.getNullable("canHighlightTweets")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun highlightedTweets(): String? = highlightedTweets.getNullable("highlightedTweets")

        /**
         * Returns the raw JSON value of [canHighlightTweets].
         *
         * Unlike [canHighlightTweets], this method doesn't throw if the JSON field has an
         * unexpected type.
         */
        @JsonProperty("canHighlightTweets")
        @ExcludeMissing
        fun _canHighlightTweets(): JsonField<Boolean> = canHighlightTweets

        /**
         * Returns the raw JSON value of [highlightedTweets].
         *
         * Unlike [highlightedTweets], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("highlightedTweets")
        @ExcludeMissing
        fun _highlightedTweets(): JsonField<String> = highlightedTweets

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

            /** Returns a mutable builder for constructing an instance of [HighlightsInfo]. */
            fun builder() = Builder()
        }

        /** A builder for [HighlightsInfo]. */
        class Builder internal constructor() {

            private var canHighlightTweets: JsonField<Boolean> = JsonMissing.of()
            private var highlightedTweets: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(highlightsInfo: HighlightsInfo) = apply {
                canHighlightTweets = highlightsInfo.canHighlightTweets
                highlightedTweets = highlightsInfo.highlightedTweets
                additionalProperties = highlightsInfo.additionalProperties.toMutableMap()
            }

            fun canHighlightTweets(canHighlightTweets: Boolean) =
                canHighlightTweets(JsonField.of(canHighlightTweets))

            /** Sets [Builder.canHighlightTweets] to an arbitrary JSON value. */
            fun canHighlightTweets(canHighlightTweets: JsonField<Boolean>) = apply {
                this.canHighlightTweets = canHighlightTweets
            }

            fun highlightedTweets(highlightedTweets: String) =
                highlightedTweets(JsonField.of(highlightedTweets))

            /** Sets [Builder.highlightedTweets] to an arbitrary JSON value. */
            fun highlightedTweets(highlightedTweets: JsonField<String>) = apply {
                this.highlightedTweets = highlightedTweets
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
             * Returns an immutable instance of [HighlightsInfo].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): HighlightsInfo =
                HighlightsInfo(
                    canHighlightTweets,
                    highlightedTweets,
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
        fun validate(): HighlightsInfo = apply {
            if (validated) {
                return@apply
            }

            canHighlightTweets()
            highlightedTweets()
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
            (if (canHighlightTweets.asKnown() == null) 0 else 1) +
                (if (highlightedTweets.asKnown() == null) 0 else 1)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is HighlightsInfo &&
                canHighlightTweets == other.canHighlightTweets &&
                highlightedTweets == other.highlightedTweets &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(canHighlightTweets, highlightedTweets, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "HighlightsInfo{canHighlightTweets=$canHighlightTweets, highlightedTweets=$highlightedTweets, additionalProperties=$additionalProperties}"
    }

    /** Identity verification X shows on the profile & its About page. */
    class IdentityVerification
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val description: JsonField<String>,
        private val isIdentityVerified: JsonField<Boolean>,
        private val verifiedSinceMsec: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("description")
            @ExcludeMissing
            description: JsonField<String> = JsonMissing.of(),
            @JsonProperty("isIdentityVerified")
            @ExcludeMissing
            isIdentityVerified: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("verifiedSinceMsec")
            @ExcludeMissing
            verifiedSinceMsec: JsonField<String> = JsonMissing.of(),
        ) : this(description, isIdentityVerified, verifiedSinceMsec, mutableMapOf())

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun description(): String? = description.getNullable("description")

        /**
         * Whether the owner verified their ID with X.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun isIdentityVerified(): Boolean? = isIdentityVerified.getNullable("isIdentityVerified")

        /**
         * When X verified the account, in milliseconds since 1970, as text.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun verifiedSinceMsec(): String? = verifiedSinceMsec.getNullable("verifiedSinceMsec")

        /**
         * Returns the raw JSON value of [description].
         *
         * Unlike [description], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("description")
        @ExcludeMissing
        fun _description(): JsonField<String> = description

        /**
         * Returns the raw JSON value of [isIdentityVerified].
         *
         * Unlike [isIdentityVerified], this method doesn't throw if the JSON field has an
         * unexpected type.
         */
        @JsonProperty("isIdentityVerified")
        @ExcludeMissing
        fun _isIdentityVerified(): JsonField<Boolean> = isIdentityVerified

        /**
         * Returns the raw JSON value of [verifiedSinceMsec].
         *
         * Unlike [verifiedSinceMsec], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("verifiedSinceMsec")
        @ExcludeMissing
        fun _verifiedSinceMsec(): JsonField<String> = verifiedSinceMsec

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

            /** Returns a mutable builder for constructing an instance of [IdentityVerification]. */
            fun builder() = Builder()
        }

        /** A builder for [IdentityVerification]. */
        class Builder internal constructor() {

            private var description: JsonField<String> = JsonMissing.of()
            private var isIdentityVerified: JsonField<Boolean> = JsonMissing.of()
            private var verifiedSinceMsec: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(identityVerification: IdentityVerification) = apply {
                description = identityVerification.description
                isIdentityVerified = identityVerification.isIdentityVerified
                verifiedSinceMsec = identityVerification.verifiedSinceMsec
                additionalProperties = identityVerification.additionalProperties.toMutableMap()
            }

            fun description(description: String) = description(JsonField.of(description))

            /** Sets [Builder.description] to an arbitrary JSON value. */
            fun description(description: JsonField<String>) = apply {
                this.description = description
            }

            /** Whether the owner verified their ID with X. */
            fun isIdentityVerified(isIdentityVerified: Boolean) =
                isIdentityVerified(JsonField.of(isIdentityVerified))

            /** Sets [Builder.isIdentityVerified] to an arbitrary JSON value. */
            fun isIdentityVerified(isIdentityVerified: JsonField<Boolean>) = apply {
                this.isIdentityVerified = isIdentityVerified
            }

            /** When X verified the account, in milliseconds since 1970, as text. */
            fun verifiedSinceMsec(verifiedSinceMsec: String) =
                verifiedSinceMsec(JsonField.of(verifiedSinceMsec))

            /** Sets [Builder.verifiedSinceMsec] to an arbitrary JSON value. */
            fun verifiedSinceMsec(verifiedSinceMsec: JsonField<String>) = apply {
                this.verifiedSinceMsec = verifiedSinceMsec
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
             * Returns an immutable instance of [IdentityVerification].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): IdentityVerification =
                IdentityVerification(
                    description,
                    isIdentityVerified,
                    verifiedSinceMsec,
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
        fun validate(): IdentityVerification = apply {
            if (validated) {
                return@apply
            }

            description()
            isIdentityVerified()
            verifiedSinceMsec()
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
            (if (description.asKnown() == null) 0 else 1) +
                (if (isIdentityVerified.asKnown() == null) 0 else 1) +
                (if (verifiedSinceMsec.asKnown() == null) 0 else 1)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is IdentityVerification &&
                description == other.description &&
                isIdentityVerified == other.isIdentityVerified &&
                verifiedSinceMsec == other.verifiedSinceMsec &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(description, isIdentityVerified, verifiedSinceMsec, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "IdentityVerification{description=$description, isIdentityVerified=$isIdentityVerified, verifiedSinceMsec=$verifiedSinceMsec, additionalProperties=$additionalProperties}"
    }

    /** Public metadata whose fields X defines. */
    class Professional
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

            /** Returns a mutable builder for constructing an instance of [Professional]. */
            fun builder() = Builder()
        }

        /** A builder for [Professional]. */
        class Builder internal constructor() {

            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(professional: Professional) = apply {
                additionalProperties = professional.additionalProperties.toMutableMap()
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
             * Returns an immutable instance of [Professional].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): Professional = Professional(additionalProperties.toImmutable())
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
        fun validate(): Professional = apply {
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

            return other is Professional && additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() = "Professional{additionalProperties=$additionalProperties}"
    }

    /** Public metadata whose fields X defines. */
    class ProfileBio
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

            /** Returns a mutable builder for constructing an instance of [ProfileBio]. */
            fun builder() = Builder()
        }

        /** A builder for [ProfileBio]. */
        class Builder internal constructor() {

            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(profileBio: ProfileBio) = apply {
                additionalProperties = profileBio.additionalProperties.toMutableMap()
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
             * Returns an immutable instance of [ProfileBio].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): ProfileBio = ProfileBio(additionalProperties.toImmutable())
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
        fun validate(): ProfileBio = apply {
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

            return other is ProfileBio && additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() = "ProfileBio{additionalProperties=$additionalProperties}"
    }

    /** Public payment and creator-support handles shown on X. */
    class TipJar
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val bandcampHandle: JsonField<String>,
        private val bitcoinHandle: JsonField<String>,
        private val cashAppHandle: JsonField<String>,
        private val ethereumHandle: JsonField<String>,
        private val gofundmeHandle: JsonField<String>,
        private val isEnabled: JsonField<Boolean>,
        private val patreonHandle: JsonField<String>,
        private val payPalHandle: JsonField<String>,
        private val venmoHandle: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("bandcampHandle")
            @ExcludeMissing
            bandcampHandle: JsonField<String> = JsonMissing.of(),
            @JsonProperty("bitcoinHandle")
            @ExcludeMissing
            bitcoinHandle: JsonField<String> = JsonMissing.of(),
            @JsonProperty("cashAppHandle")
            @ExcludeMissing
            cashAppHandle: JsonField<String> = JsonMissing.of(),
            @JsonProperty("ethereumHandle")
            @ExcludeMissing
            ethereumHandle: JsonField<String> = JsonMissing.of(),
            @JsonProperty("gofundmeHandle")
            @ExcludeMissing
            gofundmeHandle: JsonField<String> = JsonMissing.of(),
            @JsonProperty("isEnabled")
            @ExcludeMissing
            isEnabled: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("patreonHandle")
            @ExcludeMissing
            patreonHandle: JsonField<String> = JsonMissing.of(),
            @JsonProperty("payPalHandle")
            @ExcludeMissing
            payPalHandle: JsonField<String> = JsonMissing.of(),
            @JsonProperty("venmoHandle")
            @ExcludeMissing
            venmoHandle: JsonField<String> = JsonMissing.of(),
        ) : this(
            bandcampHandle,
            bitcoinHandle,
            cashAppHandle,
            ethereumHandle,
            gofundmeHandle,
            isEnabled,
            patreonHandle,
            payPalHandle,
            venmoHandle,
            mutableMapOf(),
        )

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun bandcampHandle(): String? = bandcampHandle.getNullable("bandcampHandle")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun bitcoinHandle(): String? = bitcoinHandle.getNullable("bitcoinHandle")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun cashAppHandle(): String? = cashAppHandle.getNullable("cashAppHandle")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun ethereumHandle(): String? = ethereumHandle.getNullable("ethereumHandle")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun gofundmeHandle(): String? = gofundmeHandle.getNullable("gofundmeHandle")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun isEnabled(): Boolean? = isEnabled.getNullable("isEnabled")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun patreonHandle(): String? = patreonHandle.getNullable("patreonHandle")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun payPalHandle(): String? = payPalHandle.getNullable("payPalHandle")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun venmoHandle(): String? = venmoHandle.getNullable("venmoHandle")

        /**
         * Returns the raw JSON value of [bandcampHandle].
         *
         * Unlike [bandcampHandle], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("bandcampHandle")
        @ExcludeMissing
        fun _bandcampHandle(): JsonField<String> = bandcampHandle

        /**
         * Returns the raw JSON value of [bitcoinHandle].
         *
         * Unlike [bitcoinHandle], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("bitcoinHandle")
        @ExcludeMissing
        fun _bitcoinHandle(): JsonField<String> = bitcoinHandle

        /**
         * Returns the raw JSON value of [cashAppHandle].
         *
         * Unlike [cashAppHandle], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("cashAppHandle")
        @ExcludeMissing
        fun _cashAppHandle(): JsonField<String> = cashAppHandle

        /**
         * Returns the raw JSON value of [ethereumHandle].
         *
         * Unlike [ethereumHandle], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("ethereumHandle")
        @ExcludeMissing
        fun _ethereumHandle(): JsonField<String> = ethereumHandle

        /**
         * Returns the raw JSON value of [gofundmeHandle].
         *
         * Unlike [gofundmeHandle], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("gofundmeHandle")
        @ExcludeMissing
        fun _gofundmeHandle(): JsonField<String> = gofundmeHandle

        /**
         * Returns the raw JSON value of [isEnabled].
         *
         * Unlike [isEnabled], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("isEnabled") @ExcludeMissing fun _isEnabled(): JsonField<Boolean> = isEnabled

        /**
         * Returns the raw JSON value of [patreonHandle].
         *
         * Unlike [patreonHandle], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("patreonHandle")
        @ExcludeMissing
        fun _patreonHandle(): JsonField<String> = patreonHandle

        /**
         * Returns the raw JSON value of [payPalHandle].
         *
         * Unlike [payPalHandle], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("payPalHandle")
        @ExcludeMissing
        fun _payPalHandle(): JsonField<String> = payPalHandle

        /**
         * Returns the raw JSON value of [venmoHandle].
         *
         * Unlike [venmoHandle], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("venmoHandle")
        @ExcludeMissing
        fun _venmoHandle(): JsonField<String> = venmoHandle

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

            /** Returns a mutable builder for constructing an instance of [TipJar]. */
            fun builder() = Builder()
        }

        /** A builder for [TipJar]. */
        class Builder internal constructor() {

            private var bandcampHandle: JsonField<String> = JsonMissing.of()
            private var bitcoinHandle: JsonField<String> = JsonMissing.of()
            private var cashAppHandle: JsonField<String> = JsonMissing.of()
            private var ethereumHandle: JsonField<String> = JsonMissing.of()
            private var gofundmeHandle: JsonField<String> = JsonMissing.of()
            private var isEnabled: JsonField<Boolean> = JsonMissing.of()
            private var patreonHandle: JsonField<String> = JsonMissing.of()
            private var payPalHandle: JsonField<String> = JsonMissing.of()
            private var venmoHandle: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(tipJar: TipJar) = apply {
                bandcampHandle = tipJar.bandcampHandle
                bitcoinHandle = tipJar.bitcoinHandle
                cashAppHandle = tipJar.cashAppHandle
                ethereumHandle = tipJar.ethereumHandle
                gofundmeHandle = tipJar.gofundmeHandle
                isEnabled = tipJar.isEnabled
                patreonHandle = tipJar.patreonHandle
                payPalHandle = tipJar.payPalHandle
                venmoHandle = tipJar.venmoHandle
                additionalProperties = tipJar.additionalProperties.toMutableMap()
            }

            fun bandcampHandle(bandcampHandle: String) =
                bandcampHandle(JsonField.of(bandcampHandle))

            /** Sets [Builder.bandcampHandle] to an arbitrary JSON value. */
            fun bandcampHandle(bandcampHandle: JsonField<String>) = apply {
                this.bandcampHandle = bandcampHandle
            }

            fun bitcoinHandle(bitcoinHandle: String) = bitcoinHandle(JsonField.of(bitcoinHandle))

            /** Sets [Builder.bitcoinHandle] to an arbitrary JSON value. */
            fun bitcoinHandle(bitcoinHandle: JsonField<String>) = apply {
                this.bitcoinHandle = bitcoinHandle
            }

            fun cashAppHandle(cashAppHandle: String) = cashAppHandle(JsonField.of(cashAppHandle))

            /** Sets [Builder.cashAppHandle] to an arbitrary JSON value. */
            fun cashAppHandle(cashAppHandle: JsonField<String>) = apply {
                this.cashAppHandle = cashAppHandle
            }

            fun ethereumHandle(ethereumHandle: String) =
                ethereumHandle(JsonField.of(ethereumHandle))

            /** Sets [Builder.ethereumHandle] to an arbitrary JSON value. */
            fun ethereumHandle(ethereumHandle: JsonField<String>) = apply {
                this.ethereumHandle = ethereumHandle
            }

            fun gofundmeHandle(gofundmeHandle: String) =
                gofundmeHandle(JsonField.of(gofundmeHandle))

            /** Sets [Builder.gofundmeHandle] to an arbitrary JSON value. */
            fun gofundmeHandle(gofundmeHandle: JsonField<String>) = apply {
                this.gofundmeHandle = gofundmeHandle
            }

            fun isEnabled(isEnabled: Boolean) = isEnabled(JsonField.of(isEnabled))

            /** Sets [Builder.isEnabled] to an arbitrary JSON value. */
            fun isEnabled(isEnabled: JsonField<Boolean>) = apply { this.isEnabled = isEnabled }

            fun patreonHandle(patreonHandle: String) = patreonHandle(JsonField.of(patreonHandle))

            /** Sets [Builder.patreonHandle] to an arbitrary JSON value. */
            fun patreonHandle(patreonHandle: JsonField<String>) = apply {
                this.patreonHandle = patreonHandle
            }

            fun payPalHandle(payPalHandle: String) = payPalHandle(JsonField.of(payPalHandle))

            /** Sets [Builder.payPalHandle] to an arbitrary JSON value. */
            fun payPalHandle(payPalHandle: JsonField<String>) = apply {
                this.payPalHandle = payPalHandle
            }

            fun venmoHandle(venmoHandle: String) = venmoHandle(JsonField.of(venmoHandle))

            /** Sets [Builder.venmoHandle] to an arbitrary JSON value. */
            fun venmoHandle(venmoHandle: JsonField<String>) = apply {
                this.venmoHandle = venmoHandle
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
             * Returns an immutable instance of [TipJar].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): TipJar =
                TipJar(
                    bandcampHandle,
                    bitcoinHandle,
                    cashAppHandle,
                    ethereumHandle,
                    gofundmeHandle,
                    isEnabled,
                    patreonHandle,
                    payPalHandle,
                    venmoHandle,
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
        fun validate(): TipJar = apply {
            if (validated) {
                return@apply
            }

            bandcampHandle()
            bitcoinHandle()
            cashAppHandle()
            ethereumHandle()
            gofundmeHandle()
            isEnabled()
            patreonHandle()
            payPalHandle()
            venmoHandle()
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
            (if (bandcampHandle.asKnown() == null) 0 else 1) +
                (if (bitcoinHandle.asKnown() == null) 0 else 1) +
                (if (cashAppHandle.asKnown() == null) 0 else 1) +
                (if (ethereumHandle.asKnown() == null) 0 else 1) +
                (if (gofundmeHandle.asKnown() == null) 0 else 1) +
                (if (isEnabled.asKnown() == null) 0 else 1) +
                (if (patreonHandle.asKnown() == null) 0 else 1) +
                (if (payPalHandle.asKnown() == null) 0 else 1) +
                (if (venmoHandle.asKnown() == null) 0 else 1)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is TipJar &&
                bandcampHandle == other.bandcampHandle &&
                bitcoinHandle == other.bitcoinHandle &&
                cashAppHandle == other.cashAppHandle &&
                ethereumHandle == other.ethereumHandle &&
                gofundmeHandle == other.gofundmeHandle &&
                isEnabled == other.isEnabled &&
                patreonHandle == other.patreonHandle &&
                payPalHandle == other.payPalHandle &&
                venmoHandle == other.venmoHandle &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(
                bandcampHandle,
                bitcoinHandle,
                cashAppHandle,
                ethereumHandle,
                gofundmeHandle,
                isEnabled,
                patreonHandle,
                payPalHandle,
                venmoHandle,
                additionalProperties,
            )
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "TipJar{bandcampHandle=$bandcampHandle, bitcoinHandle=$bitcoinHandle, cashAppHandle=$cashAppHandle, ethereumHandle=$ethereumHandle, gofundmeHandle=$gofundmeHandle, isEnabled=$isEnabled, patreonHandle=$patreonHandle, payPalHandle=$payPalHandle, venmoHandle=$venmoHandle, additionalProperties=$additionalProperties}"
    }

    /** How often the account changed its username. */
    class UsernameChanges
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val count: JsonField<Long>,
        private val lastChangedAt: JsonField<OffsetDateTime>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("count") @ExcludeMissing count: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("lastChangedAt")
            @ExcludeMissing
            lastChangedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        ) : this(count, lastChangedAt, mutableMapOf())

        /**
         * Whole number from 0.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type or
         *   is unexpectedly missing or null.
         */
        fun count(): Long = count.getRequired("count")

        /**
         * Time of the latest change, omitted when there is none.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun lastChangedAt(): OffsetDateTime? = lastChangedAt.getNullable("lastChangedAt")

        /**
         * Returns the raw JSON value of [count].
         *
         * Unlike [count], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("count") @ExcludeMissing fun _count(): JsonField<Long> = count

        /**
         * Returns the raw JSON value of [lastChangedAt].
         *
         * Unlike [lastChangedAt], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("lastChangedAt")
        @ExcludeMissing
        fun _lastChangedAt(): JsonField<OffsetDateTime> = lastChangedAt

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
             * Returns a mutable builder for constructing an instance of [UsernameChanges].
             *
             * The following fields are required:
             * ```kotlin
             * .count()
             * ```
             */
            fun builder() = Builder()
        }

        /** A builder for [UsernameChanges]. */
        class Builder internal constructor() {

            private var count: JsonField<Long>? = null
            private var lastChangedAt: JsonField<OffsetDateTime> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(usernameChanges: UsernameChanges) = apply {
                count = usernameChanges.count
                lastChangedAt = usernameChanges.lastChangedAt
                additionalProperties = usernameChanges.additionalProperties.toMutableMap()
            }

            /** Whole number from 0. */
            fun count(count: Long) = count(JsonField.of(count))

            /** Sets [Builder.count] to an arbitrary JSON value. */
            fun count(count: JsonField<Long>) = apply { this.count = count }

            /** Time of the latest change, omitted when there is none. */
            fun lastChangedAt(lastChangedAt: OffsetDateTime) =
                lastChangedAt(JsonField.of(lastChangedAt))

            /** Sets [Builder.lastChangedAt] to an arbitrary JSON value. */
            fun lastChangedAt(lastChangedAt: JsonField<OffsetDateTime>) = apply {
                this.lastChangedAt = lastChangedAt
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
             * Returns an immutable instance of [UsernameChanges].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             *
             * The following fields are required:
             * ```kotlin
             * .count()
             * ```
             *
             * @throws IllegalStateException if any required field is unset.
             */
            fun build(): UsernameChanges =
                UsernameChanges(
                    checkRequired("count", count),
                    lastChangedAt,
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
        fun validate(): UsernameChanges = apply {
            if (validated) {
                return@apply
            }

            count()
            lastChangedAt()
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
                (if (lastChangedAt.asKnown() == null) 0 else 1)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is UsernameChanges &&
                count == other.count &&
                lastChangedAt == other.lastChangedAt &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(count, lastChangedAt, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "UsernameChanges{count=$count, lastChangedAt=$lastChangedAt, additionalProperties=$additionalProperties}"
    }

    /** X's text on why the profile is verified, with its links. */
    class VerificationReason
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val entities: JsonField<List<Entity>>,
        private val text: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("entities")
            @ExcludeMissing
            entities: JsonField<List<Entity>> = JsonMissing.of(),
            @JsonProperty("text") @ExcludeMissing text: JsonField<String> = JsonMissing.of(),
        ) : this(entities, text, mutableMapOf())

        /**
         * Links inside the text, by character range.
         *
         * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
         */
        fun entities(): List<Entity>? = entities.getNullable("entities")

        /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
        fun text(): String? = text.getNullable("text")

        /**
         * Returns the raw JSON value of [entities].
         *
         * Unlike [entities], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("entities")
        @ExcludeMissing
        fun _entities(): JsonField<List<Entity>> = entities

        /**
         * Returns the raw JSON value of [text].
         *
         * Unlike [text], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("text") @ExcludeMissing fun _text(): JsonField<String> = text

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

            /** Returns a mutable builder for constructing an instance of [VerificationReason]. */
            fun builder() = Builder()
        }

        /** A builder for [VerificationReason]. */
        class Builder internal constructor() {

            private var entities: JsonField<MutableList<Entity>>? = null
            private var text: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            internal fun from(verificationReason: VerificationReason) = apply {
                entities = verificationReason.entities.map { it.toMutableList() }
                text = verificationReason.text
                additionalProperties = verificationReason.additionalProperties.toMutableMap()
            }

            /** Links inside the text, by character range. */
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

            fun text(text: String) = text(JsonField.of(text))

            /** Sets [Builder.text] to an arbitrary JSON value. */
            fun text(text: JsonField<String>) = apply { this.text = text }

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
             * Returns an immutable instance of [VerificationReason].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): VerificationReason =
                VerificationReason(
                    (entities ?: JsonMissing.of()).map { it.toImmutable() },
                    text,
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
        fun validate(): VerificationReason = apply {
            if (validated) {
                return@apply
            }

            entities()?.forEach { it.validate() }
            text()
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
            (entities.asKnown()?.sumOf { it.validity() } ?: 0) +
                (if (text.asKnown() == null) 0 else 1)

        class Entity
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val fromIndex: JsonField<Long>,
            private val toIndex: JsonField<Long>,
            private val url: JsonField<String>,
            private val urlType: JsonField<String>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("fromIndex")
                @ExcludeMissing
                fromIndex: JsonField<Long> = JsonMissing.of(),
                @JsonProperty("toIndex")
                @ExcludeMissing
                toIndex: JsonField<Long> = JsonMissing.of(),
                @JsonProperty("url") @ExcludeMissing url: JsonField<String> = JsonMissing.of(),
                @JsonProperty("urlType")
                @ExcludeMissing
                urlType: JsonField<String> = JsonMissing.of(),
            ) : this(fromIndex, toIndex, url, urlType, mutableMapOf())

            /**
             * Whole number from 0.
             *
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun fromIndex(): Long? = fromIndex.getNullable("fromIndex")

            /**
             * Whole number from 0.
             *
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun toIndex(): Long? = toIndex.getNullable("toIndex")

            /**
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun url(): String? = url.getNullable("url")

            /**
             * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
             */
            fun urlType(): String? = urlType.getNullable("urlType")

            /**
             * Returns the raw JSON value of [fromIndex].
             *
             * Unlike [fromIndex], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("fromIndex") @ExcludeMissing fun _fromIndex(): JsonField<Long> = fromIndex

            /**
             * Returns the raw JSON value of [toIndex].
             *
             * Unlike [toIndex], this method doesn't throw if the JSON field has an unexpected type.
             */
            @JsonProperty("toIndex") @ExcludeMissing fun _toIndex(): JsonField<Long> = toIndex

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
                private var toIndex: JsonField<Long> = JsonMissing.of()
                private var url: JsonField<String> = JsonMissing.of()
                private var urlType: JsonField<String> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                internal fun from(entity: Entity) = apply {
                    fromIndex = entity.fromIndex
                    toIndex = entity.toIndex
                    url = entity.url
                    urlType = entity.urlType
                    additionalProperties = entity.additionalProperties.toMutableMap()
                }

                /** Whole number from 0. */
                fun fromIndex(fromIndex: Long) = fromIndex(JsonField.of(fromIndex))

                /** Sets [Builder.fromIndex] to an arbitrary JSON value. */
                fun fromIndex(fromIndex: JsonField<Long>) = apply { this.fromIndex = fromIndex }

                /** Whole number from 0. */
                fun toIndex(toIndex: Long) = toIndex(JsonField.of(toIndex))

                /** Sets [Builder.toIndex] to an arbitrary JSON value. */
                fun toIndex(toIndex: JsonField<Long>) = apply { this.toIndex = toIndex }

                fun url(url: String) = url(JsonField.of(url))

                /** Sets [Builder.url] to an arbitrary JSON value. */
                fun url(url: JsonField<String>) = apply { this.url = url }

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
                 * Returns an immutable instance of [Entity].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 */
                fun build(): Entity =
                    Entity(fromIndex, toIndex, url, urlType, additionalProperties.toMutableMap())
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
                toIndex()
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
                (if (fromIndex.asKnown() == null) 0 else 1) +
                    (if (toIndex.asKnown() == null) 0 else 1) +
                    (if (url.asKnown() == null) 0 else 1) +
                    (if (urlType.asKnown() == null) 0 else 1)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is Entity &&
                    fromIndex == other.fromIndex &&
                    toIndex == other.toIndex &&
                    url == other.url &&
                    urlType == other.urlType &&
                    additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(fromIndex, toIndex, url, urlType, additionalProperties)
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "Entity{fromIndex=$fromIndex, toIndex=$toIndex, url=$url, urlType=$urlType, additionalProperties=$additionalProperties}"
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is VerificationReason &&
                entities == other.entities &&
                text == other.text &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(entities, text, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "VerificationReason{entities=$entities, text=$text, additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is UserProfile &&
            id == other.id &&
            name == other.name &&
            username == other.username &&
            accountBasedIn == other.accountBasedIn &&
            accountBasedInAccurate == other.accountBasedInAccurate &&
            accountBasedInUnavailable == other.accountBasedInUnavailable &&
            advertiserAccountServiceLevels == other.advertiserAccountServiceLevels &&
            advertiserAccountType == other.advertiserAccountType &&
            affiliateUsername == other.affiliateUsername &&
            affiliatesHighlightedLabel == other.affiliatesHighlightedLabel &&
            automatedBy == other.automatedBy &&
            birthdate == other.birthdate &&
            businessAccountAffiliatesCount == other.businessAccountAffiliatesCount &&
            businessProfileState == other.businessProfileState &&
            communityRole == other.communityRole &&
            connectedVia == other.connectedVia &&
            connectedViaAccurate == other.connectedViaAccurate &&
            contactInfo == other.contactInfo &&
            coverPicture == other.coverPicture &&
            createdAt == other.createdAt &&
            creatorSubscriptionsCount == other.creatorSubscriptionsCount &&
            defaultProfile == other.defaultProfile &&
            defaultProfileImage == other.defaultProfileImage &&
            description == other.description &&
            email == other.email &&
            fastFollowersCount == other.fastFollowersCount &&
            favouritesCount == other.favouritesCount &&
            followers == other.followers &&
            following == other.following &&
            geoEnabled == other.geoEnabled &&
            grokTranslatedBio == other.grokTranslatedBio &&
            hasCustomTimelines == other.hasCustomTimelines &&
            hasExtendedProfile == other.hasExtendedProfile &&
            hasGraduatedAccess == other.hasGraduatedAccess &&
            hasHiddenSubscriptionsOnProfile == other.hasHiddenSubscriptionsOnProfile &&
            highlightsInfo == other.highlightsInfo &&
            identityVerification == other.identityVerification &&
            isAutomated == other.isAutomated &&
            isBlueVerified == other.isBlueVerified &&
            isProfileTranslatable == other.isProfileTranslatable &&
            isTranslationEnabled == other.isTranslationEnabled &&
            isTranslator == other.isTranslator &&
            isVerified == other.isVerified &&
            listedCount == other.listedCount &&
            location == other.location &&
            mediaCount == other.mediaCount &&
            normalFollowersCount == other.normalFollowersCount &&
            parodyCommentaryFanLabel == other.parodyCommentaryFanLabel &&
            pinnedTweetIds == other.pinnedTweetIds &&
            possiblySensitive == other.possiblySensitive &&
            professional == other.professional &&
            profileBannerUrl == other.profileBannerUrl &&
            profileDescriptionLanguage == other.profileDescriptionLanguage &&
            profileImageShape == other.profileImageShape &&
            profileInterstitialType == other.profileInterstitialType &&
            profilePicture == other.profilePicture &&
            profilePictureOriginal == other.profilePictureOriginal &&
            profileSortEnabled == other.profileSortEnabled &&
            profileTranslatorType == other.profileTranslatorType &&
            profileBio == other.profileBio &&
            protected == other.protected &&
            redirectsToUsername == other.redirectsToUsername &&
            retweetedAt == other.retweetedAt &&
            statusesCount == other.statusesCount &&
            subscribersCount == other.subscribersCount &&
            superFollowEligible == other.superFollowEligible &&
            superFollowsUserProfileActive == other.superFollowsUserProfileActive &&
            tipJar == other.tipJar &&
            unavailable == other.unavailable &&
            unavailableReason == other.unavailableReason &&
            url == other.url &&
            usernameChanges == other.usernameChanges &&
            verificationReason == other.verificationReason &&
            verified == other.verified &&
            verifiedType == other.verifiedType &&
            withheldInCountries == other.withheldInCountries &&
            withheldScope == other.withheldScope &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            id,
            name,
            username,
            accountBasedIn,
            accountBasedInAccurate,
            accountBasedInUnavailable,
            advertiserAccountServiceLevels,
            advertiserAccountType,
            affiliateUsername,
            affiliatesHighlightedLabel,
            automatedBy,
            birthdate,
            businessAccountAffiliatesCount,
            businessProfileState,
            communityRole,
            connectedVia,
            connectedViaAccurate,
            contactInfo,
            coverPicture,
            createdAt,
            creatorSubscriptionsCount,
            defaultProfile,
            defaultProfileImage,
            description,
            email,
            fastFollowersCount,
            favouritesCount,
            followers,
            following,
            geoEnabled,
            grokTranslatedBio,
            hasCustomTimelines,
            hasExtendedProfile,
            hasGraduatedAccess,
            hasHiddenSubscriptionsOnProfile,
            highlightsInfo,
            identityVerification,
            isAutomated,
            isBlueVerified,
            isProfileTranslatable,
            isTranslationEnabled,
            isTranslator,
            isVerified,
            listedCount,
            location,
            mediaCount,
            normalFollowersCount,
            parodyCommentaryFanLabel,
            pinnedTweetIds,
            possiblySensitive,
            professional,
            profileBannerUrl,
            profileDescriptionLanguage,
            profileImageShape,
            profileInterstitialType,
            profilePicture,
            profilePictureOriginal,
            profileSortEnabled,
            profileTranslatorType,
            profileBio,
            protected,
            redirectsToUsername,
            retweetedAt,
            statusesCount,
            subscribersCount,
            superFollowEligible,
            superFollowsUserProfileActive,
            tipJar,
            unavailable,
            unavailableReason,
            url,
            usernameChanges,
            verificationReason,
            verified,
            verifiedType,
            withheldInCountries,
            withheldScope,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "UserProfile{id=$id, name=$name, username=$username, accountBasedIn=$accountBasedIn, accountBasedInAccurate=$accountBasedInAccurate, accountBasedInUnavailable=$accountBasedInUnavailable, advertiserAccountServiceLevels=$advertiserAccountServiceLevels, advertiserAccountType=$advertiserAccountType, affiliateUsername=$affiliateUsername, affiliatesHighlightedLabel=$affiliatesHighlightedLabel, automatedBy=$automatedBy, birthdate=$birthdate, businessAccountAffiliatesCount=$businessAccountAffiliatesCount, businessProfileState=$businessProfileState, communityRole=$communityRole, connectedVia=$connectedVia, connectedViaAccurate=$connectedViaAccurate, contactInfo=$contactInfo, coverPicture=$coverPicture, createdAt=$createdAt, creatorSubscriptionsCount=$creatorSubscriptionsCount, defaultProfile=$defaultProfile, defaultProfileImage=$defaultProfileImage, description=$description, email=$email, fastFollowersCount=$fastFollowersCount, favouritesCount=$favouritesCount, followers=$followers, following=$following, geoEnabled=$geoEnabled, grokTranslatedBio=$grokTranslatedBio, hasCustomTimelines=$hasCustomTimelines, hasExtendedProfile=$hasExtendedProfile, hasGraduatedAccess=$hasGraduatedAccess, hasHiddenSubscriptionsOnProfile=$hasHiddenSubscriptionsOnProfile, highlightsInfo=$highlightsInfo, identityVerification=$identityVerification, isAutomated=$isAutomated, isBlueVerified=$isBlueVerified, isProfileTranslatable=$isProfileTranslatable, isTranslationEnabled=$isTranslationEnabled, isTranslator=$isTranslator, isVerified=$isVerified, listedCount=$listedCount, location=$location, mediaCount=$mediaCount, normalFollowersCount=$normalFollowersCount, parodyCommentaryFanLabel=$parodyCommentaryFanLabel, pinnedTweetIds=$pinnedTweetIds, possiblySensitive=$possiblySensitive, professional=$professional, profileBannerUrl=$profileBannerUrl, profileDescriptionLanguage=$profileDescriptionLanguage, profileImageShape=$profileImageShape, profileInterstitialType=$profileInterstitialType, profilePicture=$profilePicture, profilePictureOriginal=$profilePictureOriginal, profileSortEnabled=$profileSortEnabled, profileTranslatorType=$profileTranslatorType, profileBio=$profileBio, protected=$protected, redirectsToUsername=$redirectsToUsername, retweetedAt=$retweetedAt, statusesCount=$statusesCount, subscribersCount=$subscribersCount, superFollowEligible=$superFollowEligible, superFollowsUserProfileActive=$superFollowsUserProfileActive, tipJar=$tipJar, unavailable=$unavailable, unavailableReason=$unavailableReason, url=$url, usernameChanges=$usernameChanges, verificationReason=$verificationReason, verified=$verified, verifiedType=$verifiedType, withheldInCountries=$withheldInCountries, withheldScope=$withheldScope, additionalProperties=$additionalProperties}"
}
