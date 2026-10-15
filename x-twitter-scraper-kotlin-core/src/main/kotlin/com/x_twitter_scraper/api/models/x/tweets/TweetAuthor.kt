// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.tweets

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
import com.x_twitter_scraper.api.models.UserProfile
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects

/**
 * Tweet author profile. The lookup route always includes follower count and verification state.
 * Other profile fields appear when available.
 */
class TweetAuthor
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val name: JsonField<String>,
    private val username: JsonField<String>,
    private val accountBasedIn: JsonField<UserProfile.AccountBasedIn>,
    private val accountBasedInAccurate: JsonField<Boolean>,
    private val accountBasedInUnavailable: JsonField<Boolean>,
    private val advertiserAccountServiceLevels: JsonField<List<String>>,
    private val advertiserAccountType: JsonField<String>,
    private val affiliateUsername: JsonField<String>,
    private val affiliatesHighlightedLabel: JsonField<UserProfile.AffiliatesHighlightedLabel>,
    private val automatedBy: JsonField<String>,
    private val birthdate: JsonField<UserProfile.Birthdate>,
    private val businessAccountAffiliatesCount: JsonField<Long>,
    private val businessProfileState: JsonField<String>,
    private val communityRole: JsonField<String>,
    private val connectedVia: JsonField<String>,
    private val connectedViaAccurate: JsonField<Boolean>,
    private val contactInfo: JsonField<UserProfile.ContactInfo>,
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
    private val grokTranslatedBio: JsonField<UserProfile.GrokTranslatedBio>,
    private val hasCustomTimelines: JsonField<Boolean>,
    private val hasExtendedProfile: JsonField<Boolean>,
    private val hasGraduatedAccess: JsonField<Boolean>,
    private val hasHiddenSubscriptionsOnProfile: JsonField<Boolean>,
    private val highlightsInfo: JsonField<UserProfile.HighlightsInfo>,
    private val identityVerification: JsonField<UserProfile.IdentityVerification>,
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
    private val professional: JsonField<UserProfile.Professional>,
    private val profileBannerUrl: JsonField<String>,
    private val profileDescriptionLanguage: JsonField<String>,
    private val profileImageShape: JsonField<String>,
    private val profileInterstitialType: JsonField<String>,
    private val profilePicture: JsonField<String>,
    private val profilePictureOriginal: JsonField<String>,
    private val profileSortEnabled: JsonField<Boolean>,
    private val profileTranslatorType: JsonField<String>,
    private val profileBio: JsonField<UserProfile.ProfileBio>,
    private val protected: JsonField<Boolean>,
    private val redirectsToUsername: JsonField<String>,
    private val retweetedAt: JsonField<OffsetDateTime>,
    private val statusesCount: JsonField<Long>,
    private val subscribersCount: JsonField<Long>,
    private val superFollowEligible: JsonField<Boolean>,
    private val superFollowsUserProfileActive: JsonField<Boolean>,
    private val tipJar: JsonField<UserProfile.TipJar>,
    private val unavailable: JsonField<Boolean>,
    private val unavailableReason: JsonField<String>,
    private val url: JsonField<String>,
    private val usernameChanges: JsonField<UserProfile.UsernameChanges>,
    private val verificationReason: JsonField<UserProfile.VerificationReason>,
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
        accountBasedIn: JsonField<UserProfile.AccountBasedIn> = JsonMissing.of(),
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
        affiliatesHighlightedLabel: JsonField<UserProfile.AffiliatesHighlightedLabel> =
            JsonMissing.of(),
        @JsonProperty("automatedBy")
        @ExcludeMissing
        automatedBy: JsonField<String> = JsonMissing.of(),
        @JsonProperty("birthdate")
        @ExcludeMissing
        birthdate: JsonField<UserProfile.Birthdate> = JsonMissing.of(),
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
        contactInfo: JsonField<UserProfile.ContactInfo> = JsonMissing.of(),
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
        grokTranslatedBio: JsonField<UserProfile.GrokTranslatedBio> = JsonMissing.of(),
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
        highlightsInfo: JsonField<UserProfile.HighlightsInfo> = JsonMissing.of(),
        @JsonProperty("identityVerification")
        @ExcludeMissing
        identityVerification: JsonField<UserProfile.IdentityVerification> = JsonMissing.of(),
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
        professional: JsonField<UserProfile.Professional> = JsonMissing.of(),
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
        profileBio: JsonField<UserProfile.ProfileBio> = JsonMissing.of(),
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
        @JsonProperty("tipJar")
        @ExcludeMissing
        tipJar: JsonField<UserProfile.TipJar> = JsonMissing.of(),
        @JsonProperty("unavailable")
        @ExcludeMissing
        unavailable: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("unavailableReason")
        @ExcludeMissing
        unavailableReason: JsonField<String> = JsonMissing.of(),
        @JsonProperty("url") @ExcludeMissing url: JsonField<String> = JsonMissing.of(),
        @JsonProperty("usernameChanges")
        @ExcludeMissing
        usernameChanges: JsonField<UserProfile.UsernameChanges> = JsonMissing.of(),
        @JsonProperty("verificationReason")
        @ExcludeMissing
        verificationReason: JsonField<UserProfile.VerificationReason> = JsonMissing.of(),
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

    fun toUserProfile(): UserProfile =
        UserProfile.builder()
            .id(id)
            .name(name)
            .username(username)
            .accountBasedIn(accountBasedIn)
            .accountBasedInAccurate(accountBasedInAccurate)
            .accountBasedInUnavailable(accountBasedInUnavailable)
            .advertiserAccountServiceLevels(advertiserAccountServiceLevels)
            .advertiserAccountType(advertiserAccountType)
            .affiliateUsername(affiliateUsername)
            .affiliatesHighlightedLabel(affiliatesHighlightedLabel)
            .automatedBy(automatedBy)
            .birthdate(birthdate)
            .businessAccountAffiliatesCount(businessAccountAffiliatesCount)
            .businessProfileState(businessProfileState)
            .communityRole(communityRole)
            .connectedVia(connectedVia)
            .connectedViaAccurate(connectedViaAccurate)
            .contactInfo(contactInfo)
            .coverPicture(coverPicture)
            .createdAt(createdAt)
            .creatorSubscriptionsCount(creatorSubscriptionsCount)
            .defaultProfile(defaultProfile)
            .defaultProfileImage(defaultProfileImage)
            .description(description)
            .email(email)
            .fastFollowersCount(fastFollowersCount)
            .favouritesCount(favouritesCount)
            .followers(followers)
            .following(following)
            .geoEnabled(geoEnabled)
            .grokTranslatedBio(grokTranslatedBio)
            .hasCustomTimelines(hasCustomTimelines)
            .hasExtendedProfile(hasExtendedProfile)
            .hasGraduatedAccess(hasGraduatedAccess)
            .hasHiddenSubscriptionsOnProfile(hasHiddenSubscriptionsOnProfile)
            .highlightsInfo(highlightsInfo)
            .identityVerification(identityVerification)
            .isAutomated(isAutomated)
            .isBlueVerified(isBlueVerified)
            .isProfileTranslatable(isProfileTranslatable)
            .isTranslationEnabled(isTranslationEnabled)
            .isTranslator(isTranslator)
            .isVerified(isVerified)
            .listedCount(listedCount)
            .location(location)
            .mediaCount(mediaCount)
            .normalFollowersCount(normalFollowersCount)
            .parodyCommentaryFanLabel(parodyCommentaryFanLabel)
            .pinnedTweetIds(pinnedTweetIds)
            .possiblySensitive(possiblySensitive)
            .professional(professional)
            .profileBannerUrl(profileBannerUrl)
            .profileBio(profileBio)
            .profileDescriptionLanguage(profileDescriptionLanguage)
            .profileImageShape(profileImageShape)
            .profileInterstitialType(profileInterstitialType)
            .profilePicture(profilePicture)
            .profilePictureOriginal(profilePictureOriginal)
            .profileSortEnabled(profileSortEnabled)
            .profileTranslatorType(profileTranslatorType)
            .protected(protected)
            .redirectsToUsername(redirectsToUsername)
            .retweetedAt(retweetedAt)
            .statusesCount(statusesCount)
            .subscribersCount(subscribersCount)
            .superFollowEligible(superFollowEligible)
            .superFollowsUserProfileActive(superFollowsUserProfileActive)
            .tipJar(tipJar)
            .unavailable(unavailable)
            .unavailableReason(unavailableReason)
            .url(url)
            .usernameChanges(usernameChanges)
            .verificationReason(verificationReason)
            .verified(verified)
            .verifiedType(verifiedType)
            .withheldInCountries(withheldInCountries)
            .withheldScope(withheldScope)
            .build()

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
    fun accountBasedIn(): UserProfile.AccountBasedIn? = accountBasedIn.getNullable("accountBasedIn")

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
    fun affiliatesHighlightedLabel(): UserProfile.AffiliatesHighlightedLabel? =
        affiliatesHighlightedLabel.getNullable("affiliatesHighlightedLabel")

    /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
    fun automatedBy(): String? = automatedBy.getNullable("automatedBy")

    /**
     * The parts of the birthday the account made public.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun birthdate(): UserProfile.Birthdate? = birthdate.getNullable("birthdate")

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
    fun contactInfo(): UserProfile.ContactInfo? = contactInfo.getNullable("contactInfo")

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

    /** @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type. */
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
    fun grokTranslatedBio(): UserProfile.GrokTranslatedBio? =
        grokTranslatedBio.getNullable("grokTranslatedBio")

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
    fun highlightsInfo(): UserProfile.HighlightsInfo? = highlightsInfo.getNullable("highlightsInfo")

    /**
     * Identity verification metadata displayed by X.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun identityVerification(): UserProfile.IdentityVerification? =
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
    fun professional(): UserProfile.Professional? = professional.getNullable("professional")

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
    fun profileBio(): UserProfile.ProfileBio? = profileBio.getNullable("profile_bio")

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
    fun tipJar(): UserProfile.TipJar? = tipJar.getNullable("tipJar")

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
    fun usernameChanges(): UserProfile.UsernameChanges? =
        usernameChanges.getNullable("usernameChanges")

    /**
     * X's text on why the profile is verified, with its links.
     *
     * @throws XTwitterScraperInvalidDataException if the JSON field has an unexpected type.
     */
    fun verificationReason(): UserProfile.VerificationReason? =
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
    fun _accountBasedIn(): JsonField<UserProfile.AccountBasedIn> = accountBasedIn

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
    fun _affiliatesHighlightedLabel(): JsonField<UserProfile.AffiliatesHighlightedLabel> =
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
    @JsonProperty("birthdate")
    @ExcludeMissing
    fun _birthdate(): JsonField<UserProfile.Birthdate> = birthdate

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
    fun _contactInfo(): JsonField<UserProfile.ContactInfo> = contactInfo

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
    fun _grokTranslatedBio(): JsonField<UserProfile.GrokTranslatedBio> = grokTranslatedBio

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
    fun _highlightsInfo(): JsonField<UserProfile.HighlightsInfo> = highlightsInfo

    /**
     * Returns the raw JSON value of [identityVerification].
     *
     * Unlike [identityVerification], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("identityVerification")
    @ExcludeMissing
    fun _identityVerification(): JsonField<UserProfile.IdentityVerification> = identityVerification

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
    fun _professional(): JsonField<UserProfile.Professional> = professional

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
    fun _profileBio(): JsonField<UserProfile.ProfileBio> = profileBio

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
    @JsonProperty("tipJar") @ExcludeMissing fun _tipJar(): JsonField<UserProfile.TipJar> = tipJar

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
    fun _usernameChanges(): JsonField<UserProfile.UsernameChanges> = usernameChanges

    /**
     * Returns the raw JSON value of [verificationReason].
     *
     * Unlike [verificationReason], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("verificationReason")
    @ExcludeMissing
    fun _verificationReason(): JsonField<UserProfile.VerificationReason> = verificationReason

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
         * Returns a mutable builder for constructing an instance of [TweetAuthor].
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

    /** A builder for [TweetAuthor]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var name: JsonField<String>? = null
        private var username: JsonField<String>? = null
        private var accountBasedIn: JsonField<UserProfile.AccountBasedIn> = JsonMissing.of()
        private var accountBasedInAccurate: JsonField<Boolean> = JsonMissing.of()
        private var accountBasedInUnavailable: JsonField<Boolean> = JsonMissing.of()
        private var advertiserAccountServiceLevels: JsonField<MutableList<String>>? = null
        private var advertiserAccountType: JsonField<String> = JsonMissing.of()
        private var affiliateUsername: JsonField<String> = JsonMissing.of()
        private var affiliatesHighlightedLabel: JsonField<UserProfile.AffiliatesHighlightedLabel> =
            JsonMissing.of()
        private var automatedBy: JsonField<String> = JsonMissing.of()
        private var birthdate: JsonField<UserProfile.Birthdate> = JsonMissing.of()
        private var businessAccountAffiliatesCount: JsonField<Long> = JsonMissing.of()
        private var businessProfileState: JsonField<String> = JsonMissing.of()
        private var communityRole: JsonField<String> = JsonMissing.of()
        private var connectedVia: JsonField<String> = JsonMissing.of()
        private var connectedViaAccurate: JsonField<Boolean> = JsonMissing.of()
        private var contactInfo: JsonField<UserProfile.ContactInfo> = JsonMissing.of()
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
        private var grokTranslatedBio: JsonField<UserProfile.GrokTranslatedBio> = JsonMissing.of()
        private var hasCustomTimelines: JsonField<Boolean> = JsonMissing.of()
        private var hasExtendedProfile: JsonField<Boolean> = JsonMissing.of()
        private var hasGraduatedAccess: JsonField<Boolean> = JsonMissing.of()
        private var hasHiddenSubscriptionsOnProfile: JsonField<Boolean> = JsonMissing.of()
        private var highlightsInfo: JsonField<UserProfile.HighlightsInfo> = JsonMissing.of()
        private var identityVerification: JsonField<UserProfile.IdentityVerification> =
            JsonMissing.of()
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
        private var professional: JsonField<UserProfile.Professional> = JsonMissing.of()
        private var profileBannerUrl: JsonField<String> = JsonMissing.of()
        private var profileDescriptionLanguage: JsonField<String> = JsonMissing.of()
        private var profileImageShape: JsonField<String> = JsonMissing.of()
        private var profileInterstitialType: JsonField<String> = JsonMissing.of()
        private var profilePicture: JsonField<String> = JsonMissing.of()
        private var profilePictureOriginal: JsonField<String> = JsonMissing.of()
        private var profileSortEnabled: JsonField<Boolean> = JsonMissing.of()
        private var profileTranslatorType: JsonField<String> = JsonMissing.of()
        private var profileBio: JsonField<UserProfile.ProfileBio> = JsonMissing.of()
        private var protected: JsonField<Boolean> = JsonMissing.of()
        private var redirectsToUsername: JsonField<String> = JsonMissing.of()
        private var retweetedAt: JsonField<OffsetDateTime> = JsonMissing.of()
        private var statusesCount: JsonField<Long> = JsonMissing.of()
        private var subscribersCount: JsonField<Long> = JsonMissing.of()
        private var superFollowEligible: JsonField<Boolean> = JsonMissing.of()
        private var superFollowsUserProfileActive: JsonField<Boolean> = JsonMissing.of()
        private var tipJar: JsonField<UserProfile.TipJar> = JsonMissing.of()
        private var unavailable: JsonField<Boolean> = JsonMissing.of()
        private var unavailableReason: JsonField<String> = JsonMissing.of()
        private var url: JsonField<String> = JsonMissing.of()
        private var usernameChanges: JsonField<UserProfile.UsernameChanges> = JsonMissing.of()
        private var verificationReason: JsonField<UserProfile.VerificationReason> = JsonMissing.of()
        private var verified: JsonField<Boolean> = JsonMissing.of()
        private var verifiedType: JsonField<String> = JsonMissing.of()
        private var withheldInCountries: JsonField<MutableList<String>>? = null
        private var withheldScope: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        internal fun from(tweetAuthor: TweetAuthor) = apply {
            id = tweetAuthor.id
            name = tweetAuthor.name
            username = tweetAuthor.username
            accountBasedIn = tweetAuthor.accountBasedIn
            accountBasedInAccurate = tweetAuthor.accountBasedInAccurate
            accountBasedInUnavailable = tweetAuthor.accountBasedInUnavailable
            advertiserAccountServiceLevels =
                tweetAuthor.advertiserAccountServiceLevels.map { it.toMutableList() }
            advertiserAccountType = tweetAuthor.advertiserAccountType
            affiliateUsername = tweetAuthor.affiliateUsername
            affiliatesHighlightedLabel = tweetAuthor.affiliatesHighlightedLabel
            automatedBy = tweetAuthor.automatedBy
            birthdate = tweetAuthor.birthdate
            businessAccountAffiliatesCount = tweetAuthor.businessAccountAffiliatesCount
            businessProfileState = tweetAuthor.businessProfileState
            communityRole = tweetAuthor.communityRole
            connectedVia = tweetAuthor.connectedVia
            connectedViaAccurate = tweetAuthor.connectedViaAccurate
            contactInfo = tweetAuthor.contactInfo
            coverPicture = tweetAuthor.coverPicture
            createdAt = tweetAuthor.createdAt
            creatorSubscriptionsCount = tweetAuthor.creatorSubscriptionsCount
            defaultProfile = tweetAuthor.defaultProfile
            defaultProfileImage = tweetAuthor.defaultProfileImage
            description = tweetAuthor.description
            email = tweetAuthor.email
            fastFollowersCount = tweetAuthor.fastFollowersCount
            favouritesCount = tweetAuthor.favouritesCount
            followers = tweetAuthor.followers
            following = tweetAuthor.following
            geoEnabled = tweetAuthor.geoEnabled
            grokTranslatedBio = tweetAuthor.grokTranslatedBio
            hasCustomTimelines = tweetAuthor.hasCustomTimelines
            hasExtendedProfile = tweetAuthor.hasExtendedProfile
            hasGraduatedAccess = tweetAuthor.hasGraduatedAccess
            hasHiddenSubscriptionsOnProfile = tweetAuthor.hasHiddenSubscriptionsOnProfile
            highlightsInfo = tweetAuthor.highlightsInfo
            identityVerification = tweetAuthor.identityVerification
            isAutomated = tweetAuthor.isAutomated
            isBlueVerified = tweetAuthor.isBlueVerified
            isProfileTranslatable = tweetAuthor.isProfileTranslatable
            isTranslationEnabled = tweetAuthor.isTranslationEnabled
            isTranslator = tweetAuthor.isTranslator
            isVerified = tweetAuthor.isVerified
            listedCount = tweetAuthor.listedCount
            location = tweetAuthor.location
            mediaCount = tweetAuthor.mediaCount
            normalFollowersCount = tweetAuthor.normalFollowersCount
            parodyCommentaryFanLabel = tweetAuthor.parodyCommentaryFanLabel
            pinnedTweetIds = tweetAuthor.pinnedTweetIds.map { it.toMutableList() }
            possiblySensitive = tweetAuthor.possiblySensitive
            professional = tweetAuthor.professional
            profileBannerUrl = tweetAuthor.profileBannerUrl
            profileDescriptionLanguage = tweetAuthor.profileDescriptionLanguage
            profileImageShape = tweetAuthor.profileImageShape
            profileInterstitialType = tweetAuthor.profileInterstitialType
            profilePicture = tweetAuthor.profilePicture
            profilePictureOriginal = tweetAuthor.profilePictureOriginal
            profileSortEnabled = tweetAuthor.profileSortEnabled
            profileTranslatorType = tweetAuthor.profileTranslatorType
            profileBio = tweetAuthor.profileBio
            protected = tweetAuthor.protected
            redirectsToUsername = tweetAuthor.redirectsToUsername
            retweetedAt = tweetAuthor.retweetedAt
            statusesCount = tweetAuthor.statusesCount
            subscribersCount = tweetAuthor.subscribersCount
            superFollowEligible = tweetAuthor.superFollowEligible
            superFollowsUserProfileActive = tweetAuthor.superFollowsUserProfileActive
            tipJar = tweetAuthor.tipJar
            unavailable = tweetAuthor.unavailable
            unavailableReason = tweetAuthor.unavailableReason
            url = tweetAuthor.url
            usernameChanges = tweetAuthor.usernameChanges
            verificationReason = tweetAuthor.verificationReason
            verified = tweetAuthor.verified
            verifiedType = tweetAuthor.verifiedType
            withheldInCountries = tweetAuthor.withheldInCountries.map { it.toMutableList() }
            withheldScope = tweetAuthor.withheldScope
            additionalProperties = tweetAuthor.additionalProperties.toMutableMap()
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
        fun accountBasedIn(accountBasedIn: UserProfile.AccountBasedIn?) =
            accountBasedIn(JsonField.ofNullable(accountBasedIn))

        /** Sets [Builder.accountBasedIn] to an arbitrary JSON value. */
        fun accountBasedIn(accountBasedIn: JsonField<UserProfile.AccountBasedIn>) = apply {
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
        fun affiliatesHighlightedLabel(
            affiliatesHighlightedLabel: UserProfile.AffiliatesHighlightedLabel
        ) = affiliatesHighlightedLabel(JsonField.of(affiliatesHighlightedLabel))

        /** Sets [Builder.affiliatesHighlightedLabel] to an arbitrary JSON value. */
        fun affiliatesHighlightedLabel(
            affiliatesHighlightedLabel: JsonField<UserProfile.AffiliatesHighlightedLabel>
        ) = apply { this.affiliatesHighlightedLabel = affiliatesHighlightedLabel }

        fun automatedBy(automatedBy: String) = automatedBy(JsonField.of(automatedBy))

        /** Sets [Builder.automatedBy] to an arbitrary JSON value. */
        fun automatedBy(automatedBy: JsonField<String>) = apply { this.automatedBy = automatedBy }

        /** The parts of the birthday the account made public. */
        fun birthdate(birthdate: UserProfile.Birthdate) = birthdate(JsonField.of(birthdate))

        /** Sets [Builder.birthdate] to an arbitrary JSON value. */
        fun birthdate(birthdate: JsonField<UserProfile.Birthdate>) = apply {
            this.birthdate = birthdate
        }

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
        fun contactInfo(contactInfo: UserProfile.ContactInfo) =
            contactInfo(JsonField.of(contactInfo))

        /** Sets [Builder.contactInfo] to an arbitrary JSON value. */
        fun contactInfo(contactInfo: JsonField<UserProfile.ContactInfo>) = apply {
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
        fun grokTranslatedBio(grokTranslatedBio: UserProfile.GrokTranslatedBio) =
            grokTranslatedBio(JsonField.of(grokTranslatedBio))

        /** Sets [Builder.grokTranslatedBio] to an arbitrary JSON value. */
        fun grokTranslatedBio(grokTranslatedBio: JsonField<UserProfile.GrokTranslatedBio>) = apply {
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
        fun highlightsInfo(highlightsInfo: UserProfile.HighlightsInfo) =
            highlightsInfo(JsonField.of(highlightsInfo))

        /** Sets [Builder.highlightsInfo] to an arbitrary JSON value. */
        fun highlightsInfo(highlightsInfo: JsonField<UserProfile.HighlightsInfo>) = apply {
            this.highlightsInfo = highlightsInfo
        }

        /** Identity verification metadata displayed by X. */
        fun identityVerification(identityVerification: UserProfile.IdentityVerification) =
            identityVerification(JsonField.of(identityVerification))

        /** Sets [Builder.identityVerification] to an arbitrary JSON value. */
        fun identityVerification(
            identityVerification: JsonField<UserProfile.IdentityVerification>
        ) = apply { this.identityVerification = identityVerification }

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
        fun professional(professional: UserProfile.Professional) =
            professional(JsonField.of(professional))

        /** Sets [Builder.professional] to an arbitrary JSON value. */
        fun professional(professional: JsonField<UserProfile.Professional>) = apply {
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
        fun profileBio(profileBio: UserProfile.ProfileBio) = profileBio(JsonField.of(profileBio))

        /** Sets [Builder.profileBio] to an arbitrary JSON value. */
        fun profileBio(profileBio: JsonField<UserProfile.ProfileBio>) = apply {
            this.profileBio = profileBio
        }

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
        fun tipJar(tipJar: UserProfile.TipJar) = tipJar(JsonField.of(tipJar))

        /** Sets [Builder.tipJar] to an arbitrary JSON value. */
        fun tipJar(tipJar: JsonField<UserProfile.TipJar>) = apply { this.tipJar = tipJar }

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
        fun usernameChanges(usernameChanges: UserProfile.UsernameChanges) =
            usernameChanges(JsonField.of(usernameChanges))

        /** Sets [Builder.usernameChanges] to an arbitrary JSON value. */
        fun usernameChanges(usernameChanges: JsonField<UserProfile.UsernameChanges>) = apply {
            this.usernameChanges = usernameChanges
        }

        /** X's text on why the profile is verified, with its links. */
        fun verificationReason(verificationReason: UserProfile.VerificationReason) =
            verificationReason(JsonField.of(verificationReason))

        /** Sets [Builder.verificationReason] to an arbitrary JSON value. */
        fun verificationReason(verificationReason: JsonField<UserProfile.VerificationReason>) =
            apply {
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
         * Returns an immutable instance of [TweetAuthor].
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
        fun build(): TweetAuthor =
            TweetAuthor(
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
    fun validate(): TweetAuthor = apply {
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

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is TweetAuthor &&
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
        "TweetAuthor{id=$id, name=$name, username=$username, accountBasedIn=$accountBasedIn, accountBasedInAccurate=$accountBasedInAccurate, accountBasedInUnavailable=$accountBasedInUnavailable, advertiserAccountServiceLevels=$advertiserAccountServiceLevels, advertiserAccountType=$advertiserAccountType, affiliateUsername=$affiliateUsername, affiliatesHighlightedLabel=$affiliatesHighlightedLabel, automatedBy=$automatedBy, birthdate=$birthdate, businessAccountAffiliatesCount=$businessAccountAffiliatesCount, businessProfileState=$businessProfileState, communityRole=$communityRole, connectedVia=$connectedVia, connectedViaAccurate=$connectedViaAccurate, contactInfo=$contactInfo, coverPicture=$coverPicture, createdAt=$createdAt, creatorSubscriptionsCount=$creatorSubscriptionsCount, defaultProfile=$defaultProfile, defaultProfileImage=$defaultProfileImage, description=$description, email=$email, fastFollowersCount=$fastFollowersCount, favouritesCount=$favouritesCount, followers=$followers, following=$following, geoEnabled=$geoEnabled, grokTranslatedBio=$grokTranslatedBio, hasCustomTimelines=$hasCustomTimelines, hasExtendedProfile=$hasExtendedProfile, hasGraduatedAccess=$hasGraduatedAccess, hasHiddenSubscriptionsOnProfile=$hasHiddenSubscriptionsOnProfile, highlightsInfo=$highlightsInfo, identityVerification=$identityVerification, isAutomated=$isAutomated, isBlueVerified=$isBlueVerified, isProfileTranslatable=$isProfileTranslatable, isTranslationEnabled=$isTranslationEnabled, isTranslator=$isTranslator, isVerified=$isVerified, listedCount=$listedCount, location=$location, mediaCount=$mediaCount, normalFollowersCount=$normalFollowersCount, parodyCommentaryFanLabel=$parodyCommentaryFanLabel, pinnedTweetIds=$pinnedTweetIds, possiblySensitive=$possiblySensitive, professional=$professional, profileBannerUrl=$profileBannerUrl, profileDescriptionLanguage=$profileDescriptionLanguage, profileImageShape=$profileImageShape, profileInterstitialType=$profileInterstitialType, profilePicture=$profilePicture, profilePictureOriginal=$profilePictureOriginal, profileSortEnabled=$profileSortEnabled, profileTranslatorType=$profileTranslatorType, profileBio=$profileBio, protected=$protected, redirectsToUsername=$redirectsToUsername, retweetedAt=$retweetedAt, statusesCount=$statusesCount, subscribersCount=$subscribersCount, superFollowEligible=$superFollowEligible, superFollowsUserProfileActive=$superFollowsUserProfileActive, tipJar=$tipJar, unavailable=$unavailable, unavailableReason=$unavailableReason, url=$url, usernameChanges=$usernameChanges, verificationReason=$verificationReason, verified=$verified, verifiedType=$verifiedType, withheldInCountries=$withheldInCountries, withheldScope=$withheldScope, additionalProperties=$additionalProperties}"
}
