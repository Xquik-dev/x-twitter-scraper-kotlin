package com.x_twitter_scraper.api.models

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.JsonMissing
import com.x_twitter_scraper.api.core.jsonMapper
import com.x_twitter_scraper.api.models.x.broadcasts.BroadcastRetrieveResponse
import com.x_twitter_scraper.api.models.x.communities.CommunityRetrieveInfoResponse
import com.x_twitter_scraper.api.models.x.communitynotes.CommunityNoteRetrieveContributorResponse
import com.x_twitter_scraper.api.models.x.communitynotes.CommunityNotesContributor
import com.x_twitter_scraper.api.models.x.communitynotes.CommunityNotesRatingOutcome
import com.x_twitter_scraper.api.models.x.communitynotes.PaginatedCommunityNotes
import com.x_twitter_scraper.api.models.x.jobs.Job
import com.x_twitter_scraper.api.models.x.jobs.JobRetrieveResponse
import com.x_twitter_scraper.api.models.x.jobs.JobSearchResponse
import com.x_twitter_scraper.api.models.x.spaces.Space
import com.x_twitter_scraper.api.models.x.spaces.SpaceParticipant
import com.x_twitter_scraper.api.models.x.spaces.SpaceRetrieveResponse
import com.x_twitter_scraper.api.models.x.spaces.SpaceSearchResponse
import com.x_twitter_scraper.api.models.x.users.UserRetrieveLiveResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class NamedResourceModelsTest {

    @Test
    fun jobOperationsReturnNamedModel() {
        val jsonMapper = jsonMapper()
        val retrieved =
            jsonMapper
                .readValue(
                    """
                    {
                        "job": {
                            "id": "1899202395210649861",
                            "title": "Engineer",
                            "url": "https://x.com/i/jobs/1899202395210649861",
                            "applyUrl": "https://xquik.com/example",
                            "location": "United States",
                            "locationType": "remote",
                            "employmentType": "full_time",
                            "seniorityLevel": "lead",
                            "jobFunction": "software_engineering",
                            "formattedSalary": "Starting at ${'$'}225K",
                            "salaryMin": 225000,
                            "salaryCurrencyCode": "USD",
                            "salaryInterval": "annually",
                            "featured": true,
                            "shortDescription": "Commonware is hiring founding engineers.",
                            "description": "What is Commonware? We build open primitives.",
                            "descriptionMarkdown": "# What is Commonware?",
                            "account": {
                                "id": "1812126202804711425",
                                "username": "commonwarexyz",
                                "name": "commonware",
                                "verified": true,
                                "verifiedType": "Business"
                            }
                        }
                    }
                    """
                        .trimIndent(),
                    jacksonTypeRef<JobRetrieveResponse>(),
                )
                .validate()
        val searched =
            jsonMapper
                .readValue(
                    """
                    {
                        "jobs": [
                            {
                                "id": "1899202395210649861",
                                "title": "Engineer",
                                "url": "https://x.com/i/jobs/1899202395210649861",
                                "applyUrl": "https://xquik.com/example",
                                "location": "United States",
                                "formattedSalary": "Starting at ${'$'}225K",
                                "salaryMin": 225000,
                                "salaryCurrencyCode": "USD",
                                "salaryInterval": "annually",
                                "account": {
                                    "id": "1812126202804711425",
                                    "username": "commonwarexyz",
                                    "name": "commonware",
                                    "verified": true,
                                    "verifiedType": "Business"
                                }
                            }
                        ],
                        "has_next_page": true,
                        "next_cursor": "DAACCgACGRElMJcAAA"
                    }
                    """
                        .trimIndent(),
                    jacksonTypeRef<JobSearchResponse>(),
                )
                .validate()

        val jobs: List<Job> = listOf(retrieved.job(), searched.jobs().single())
        jobs.forEach {
            assertThat(it).isExactlyInstanceOf(Job::class.java)
            assertThat(it.id()).isEqualTo("1899202395210649861")
            assertThat(it.title()).isEqualTo("Engineer")
            assertThat(it._additionalProperties()).isEmpty()
        }
        assertThat(jobs[0].account()).isEqualTo(jobs[1].account())
        assertThat(jobs[0].salaryMin()).isEqualTo(jobs[1].salaryMin())
        assertThat(jobs[0].description()).isEqualTo("What is Commonware? We build open primitives.")
        assertThat(jobs[1].description()).isNull()
        assertThat(
                jobs[0]
                    .toBuilder()
                    .description(JsonMissing.of())
                    .descriptionMarkdown(JsonMissing.of())
                    .employmentType(JsonMissing.of())
                    .featured(JsonMissing.of())
                    .jobFunction(JsonMissing.of())
                    .locationType(JsonMissing.of())
                    .seniorityLevel(JsonMissing.of())
                    .shortDescription(JsonMissing.of())
                    .build()
            )
            .isEqualTo(jobs[1])
    }

    @Test
    fun spaceOperationsReturnNamedModel() {
        val jsonMapper = jsonMapper()
        val retrieved =
            jsonMapper
                .readValue(
                    """
                    {
                        "space": {
                            "id": "1OGwblLjnQMKB",
                            "title": "Extending the Network to the Edge",
                            "state": "Ended",
                            "startedAt": "2026-03-11T17:00:26.227Z",
                            "endedAt": "2026-03-11T17:49:29.190Z",
                            "totalLiveListeners": 841,
                            "totalReplayWatched": 609,
                            "creator": {
                                "id": "951329744804392960",
                                "username": "solana",
                                "name": "Solana"
                            },
                            "admins": [
                                {
                                    "id": "951329744804392960",
                                    "username": "solana",
                                    "name": "Solana"
                                }
                            ],
                            "speakers": [],
                            "listeners": []
                        }
                    }
                    """
                        .trimIndent(),
                    jacksonTypeRef<SpaceRetrieveResponse>(),
                )
                .validate()
        val searched =
            jsonMapper
                .readValue(
                    """
                    {
                        "spaces": [
                            {
                                "id": "1OGwblLjnQMKB",
                                "title": "Extending the Network to the Edge",
                                "state": "Ended",
                                "startedAt": "2026-03-11T17:00:26.227Z",
                                "endedAt": "2026-03-11T17:49:29.190Z",
                                "totalLiveListeners": 841,
                                "totalReplayWatched": 609,
                                "admins": [
                                    {
                                        "id": "951329744804392960",
                                        "username": "solana",
                                        "name": "Solana"
                                    }
                                ],
                                "speakers": [],
                                "listeners": []
                            }
                        ],
                        "has_next_page": true,
                        "next_cursor": "DAACCgACGRElMJcAAA"
                    }
                    """
                        .trimIndent(),
                    jacksonTypeRef<SpaceSearchResponse>(),
                )
                .validate()

        val spaces: List<Space> = listOf(retrieved.space(), searched.spaces().single())
        spaces.forEach {
            assertThat(it).isExactlyInstanceOf(Space::class.java)
            assertThat(it.id()).isEqualTo("1OGwblLjnQMKB")
            assertThat(it.state()).isEqualTo("Ended")
            assertThat(it._additionalProperties()).isEmpty()
        }
        val participants: List<SpaceParticipant> = spaces.flatMap {
            it.admins() + it.speakers() + it.listeners()
        }
        participants.forEach {
            assertThat(it).isExactlyInstanceOf(SpaceParticipant::class.java)
            assertThat(it.username()).isEqualTo("solana")
        }
        assertThat(spaces[0].admins()).isEqualTo(spaces[1].admins())
        assertThat(spaces[0].toBuilder().creator(JsonMissing.of()).build()).isEqualTo(spaces[1])
    }

    @Test
    fun communityNoteOperationsReturnNamedModels() {
        val jsonMapper = jsonMapper()
        val notes =
            jsonMapper
                .readValue(
                    """
                    {
                        "notes": [
                            {
                                "id": "2026375778254868976",
                                "createdAt": "2026-02-24T19:16:48.435Z",
                                "language": "en",
                                "classification": "MisinformedOrPotentiallyMisleading",
                                "contributor": {
                                    "alias": "ardent-beach-friarbird",
                                    "notesCount": {
                                        "currentlyRatedHelpful": 1,
                                        "currentlyRatedNotHelpful": 2
                                    },
                                    "ratingsCount": {
                                        "successful": {
                                            "total": 49
                                        },
                                        "unsuccessful": {
                                            "helpfulCount": 15,
                                            "notHelpfulCount": 4
                                        }
                                    }
                                },
                                "text": "Since this is a wall of text article and the headline doesn't say, this quick-to-read context is necessary:\n\nDiscord is STILL rolling out Age Verification methods, just delayed to the second half of 2026 per the very own article in this post.\n\nhttps://discord.com/blog/getting-global-age-assurance-right-what-we-got-wrong-and-whats-changing",
                                "entities": [
                                    {
                                        "fromIndex": 244,
                                        "toIndex": 340,
                                        "ref": {
                                            "type": "TimelineUrl",
                                            "url": "https://t.co/7fJbcLrO1k"
                                        }
                                    }
                                ],
                                "ratingStatus": "CurrentlyRatedHelpful",
                                "helpfulTags": [
                                    "ImportantContext",
                                    "Clear"
                                ],
                                "decidedBy": "CoreModel (v1.1)",
                                "fullyVisibleModel": true,
                                "abovePcrhThreshold": false,
                                "appealStatus": "NotAppealed",
                                "deleted": false,
                                "isApiAuthor": false,
                                "isMediaNote": false,
                                "mediaNoteMatchCount": 0,
                                "isUrlNote": false,
                                "isTranslatable": false,
                                "timeToHelpfulFromNoteMs": 132890587,
                                "timeToHelpfulFromPostMs": 136815576,
                                "postId": "2026359315657805939",
                                "post": {
                                    "id": "2026359315657805939",
                                    "text": "Getting Global Age Assurance Right: What We Got Wrong and What’s Changing. \n\nRead the update: https://t.co/h7aRGmN7xW",
                                    "createdAt": "Tue Feb 24 18:11:23 +0000 2026",
                                    "retweetCount": 542,
                                    "replyCount": 4197,
                                    "likeCount": 3990,
                                    "quoteCount": 1122,
                                    "viewCount": 7038435,
                                    "bookmarkCount": 1472,
                                    "lang": "en",
                                    "url": "https://x.com/discord/status/2026359315657805939",
                                    "author": {
                                        "id": "3065618342",
                                        "username": "discord",
                                        "name": "Discord",
                                        "verifiedType": "Business"
                                    }
                                }
                            }
                        ],
                        "has_next_page": true,
                        "next_cursor": "-1767098234260"
                    }
                    """
                        .trimIndent(),
                    jacksonTypeRef<PaginatedCommunityNotes>(),
                )
                .validate()
        val deleted =
            jsonMapper
                .readValue(
                    """
                    {
                        "notes": [
                            {
                                "id": "2078950499982696756",
                                "createdAt": "2026-07-19T21:09:58.885Z",
                                "ratingStatus": "CurrentlyRatedNotHelpful",
                                "notHelpfulTags": [
                                    "MissingKeyPoints",
                                    "Incorrect"
                                ],
                                "decidedBy": "CoreModel (v1.1)",
                                "fullyVisibleModel": true,
                                "abovePcrhThreshold": false,
                                "appealStatus": "NotAppealed",
                                "deleted": true,
                                "tombstone": "This note has been deleted",
                                "isApiAuthor": false,
                                "isMediaNote": false,
                                "mediaNoteMatchCount": 0,
                                "isUrlNote": false
                            }
                        ],
                        "has_next_page": false,
                        "next_cursor": ""
                    }
                    """
                        .trimIndent(),
                    jacksonTypeRef<PaginatedCommunityNotes>(),
                )
                .validate()
        val retrieved =
            jsonMapper
                .readValue(
                    """
                    {
                        "contributor": {
                            "alias": "ardent-beach-friarbird",
                            "deletedNotesCount": 0,
                            "isApiContributor": false,
                            "isTopWriter": false,
                            "notesCount": {
                                "awaitingMoreRatings": 18,
                                "currentlyRatedHelpful": 1,
                                "currentlyRatedNotHelpful": 2,
                                "updatedAt": "2026-10-12T22:18:40.096Z"
                            },
                            "ratingsCount": {
                                "awaitingMoreRatings": 703,
                                "ratedAfterDecision": 154,
                                "successful": {
                                    "helpfulCount": 27,
                                    "notHelpfulCount": 22,
                                    "total": 49
                                },
                                "unsuccessful": {
                                    "helpfulCount": 15,
                                    "notHelpfulCount": 4
                                },
                                "updatedAt": "2026-10-12T22:50:13.228Z"
                            }
                        }
                    }
                    """
                        .trimIndent(),
                    jacksonTypeRef<CommunityNoteRetrieveContributorResponse>(),
                )
                .validate()

        val pages: List<PaginatedCommunityNotes> = listOf(notes, deleted)
        pages.forEach {
            assertThat(it).isExactlyInstanceOf(PaginatedCommunityNotes::class.java)
            assertThat(it.notes()).hasSize(1)
            assertThat(it._additionalProperties()).isEmpty()
        }
        assertThat(notes.notes().single().id()).isEqualTo("2026375778254868976")
        assertThat(notes.notes().single().deleted()).isFalse()
        assertThat(deleted.notes().single().id()).isEqualTo("2078950499982696756")
        assertThat(deleted.notes().single().deleted()).isTrue()
        val contributor: CommunityNotesContributor = retrieved.contributor()
        assertThat(contributor).isExactlyInstanceOf(CommunityNotesContributor::class.java)
        assertThat(contributor.alias()).isEqualTo("ardent-beach-friarbird")
        val outcomes: List<CommunityNotesRatingOutcome?> =
            listOf(
                contributor.ratingsCount()?.successful(),
                contributor.ratingsCount()?.unsuccessful(),
            )
        outcomes.forEach {
            assertThat(it).isExactlyInstanceOf(CommunityNotesRatingOutcome::class.java)
        }
        val note =
            jsonMapper
                .readValue(
                    """{"id":"2026375778254868976","contributor":{"alias":"ardent-beach-friarbird"}}""",
                    jacksonTypeRef<PaginatedCommunityNotes.Note>(),
                )
                .validate()
        assertThat(note.contributor()).isExactlyInstanceOf(CommunityNotesContributor::class.java)
        assertThat(note.contributor()?.alias()).isEqualTo(contributor.alias())
    }

    @Test
    fun broadcastOperationsReturnNamedModel() {
        val jsonMapper = jsonMapper()
        val retrieved =
            jsonMapper
                .readValue(
                    """
                    {
                        "broadcast": {
                            "id": "1nGeLMWbkpaKX",
                            "title": "USSF-259 Mission",
                            "state": "Ended",
                            "startedAt": "2026-09-17T00:54:56.506Z",
                            "endedAt": "2026-09-17T01:17:35.036Z",
                            "totalWatched": 161758,
                            "totalWatching": 21639,
                            "width": 3840,
                            "height": 2160,
                            "creator": {
                                "id": "34743251",
                                "username": "SpaceX",
                                "name": "SpaceX"
                            }
                        }
                    }
                    """
                        .trimIndent(),
                    jacksonTypeRef<BroadcastRetrieveResponse>(),
                )
                .validate()
        val live =
            jsonMapper
                .readValue(
                    """
                    {
                        "live": false,
                        "userId": "11348282",
                        "userName": "NASA",
                        "broadcast": {
                            "id": "1DxLdZyVPMMxm",
                            "title": "NASA's SpaceX Crew-12 Re-Entry & Splashdown",
                            "state": "Ended",
                            "startedAt": "2026-10-08T14:20:33.863Z",
                            "endedAt": "2026-10-08T16:38:16.770Z",
                            "totalWatched": 220908,
                            "totalWatching": 119617,
                            "width": 3840,
                            "height": 2160
                        }
                    }
                    """
                        .trimIndent(),
                    jacksonTypeRef<UserRetrieveLiveResponse>(),
                )
                .validate()

        val broadcasts: List<Broadcast?> = listOf(retrieved.broadcast(), live.broadcast())
        broadcasts.forEach {
            assertThat(it).isExactlyInstanceOf(Broadcast::class.java)
            assertThat(it?.isValid()).isTrue()
            assertThat(it?._additionalProperties()).isEmpty()
        }
        assertThat(retrieved.broadcast().id()).isEqualTo("1nGeLMWbkpaKX")
        assertThat(live.broadcast()?.id()).isEqualTo("1DxLdZyVPMMxm")
        val matching =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(live.broadcast()),
                jacksonTypeRef<Broadcast>(),
            )
        val response = BroadcastRetrieveResponse.builder().broadcast(matching).build().validate()
        assertThat(response.broadcast()).isEqualTo(live.broadcast())
    }

    @Test
    fun communityReadsReturnSharedModel() {
        val jsonMapper = jsonMapper()
        val retrieved =
            jsonMapper
                .readValue(
                    """
                    {
                        "community": {
                            "id": "1500000000000000000",
                            "name": "Tesla Fans",
                            "description": "A community for Tesla enthusiasts",
                            "banner_url": "https://xquik.com/example",
                            "created_at": "2025-01-15T12:00:00Z",
                            "join_policy": "open",
                            "member_count": 10000,
                            "moderator_count": 5,
                            "is_nsfw": false
                        }
                    }
                    """
                        .trimIndent(),
                    jacksonTypeRef<CommunityRetrieveInfoResponse>(),
                )
                .validate()
        val found =
            jsonMapper
                .readValue(
                    """
                    {
                        "communities": [
                            {
                                "id": "1472105760389668865",
                                "name": "Tech Twitter",
                                "member_count": 121468,
                                "is_nsfw": false,
                                "primary_topic": {
                                    "name": "Technology"
                                },
                                "banner_url": "https://pbs.twimg.com/community_banner_img/example.jpg",
                                "membersFacepile": [
                                    {
                                        "id": "2864203879",
                                        "profilePicture": "https://pbs.twimg.com/profile_images/example.jpg"
                                    }
                                ]
                            }
                        ],
                        "has_next_page": true,
                        "next_cursor": "WzM1LjUxMjQ5N10="
                    }
                    """
                        .trimIndent(),
                    jacksonTypeRef<CommunityPage>(),
                )
                .validate()
        val popular =
            jsonMapper
                .readValue(
                    """
                    {
                        "communities": [
                            {
                                "id": "1472105760389668865",
                                "name": "Tech Twitter",
                                "member_count": 121468,
                                "is_nsfw": false,
                                "primary_topic": {
                                    "name": "Technology"
                                },
                                "banner_url": "https://pbs.twimg.com/community_banner_img/example.jpg",
                                "membersFacepile": [
                                    {
                                        "id": "2864203879",
                                        "profilePicture": "https://pbs.twimg.com/profile_images/example.jpg"
                                    }
                                ]
                            }
                        ],
                        "has_next_page": true,
                        "next_cursor": "WzM1LjUxMjQ5N10="
                    }
                    """
                        .trimIndent(),
                    jacksonTypeRef<CommunityPage>(),
                )
                .validate()

        val communities: List<Community> =
            listOf(
                retrieved.community(),
                found.communities().single(),
                popular.communities().single(),
            )
        communities.forEach {
            assertThat(it).isExactlyInstanceOf(Community::class.java)
            assertThat(it._additionalProperties()).isEmpty()
        }
        assertThat(found.communities().single()).isEqualTo(popular.communities().single())
    }
}
