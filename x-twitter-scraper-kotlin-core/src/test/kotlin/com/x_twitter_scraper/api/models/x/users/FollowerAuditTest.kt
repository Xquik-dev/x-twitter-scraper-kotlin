// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.users

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.JsonValue
import com.x_twitter_scraper.api.core.jsonMapper
import com.x_twitter_scraper.api.errors.XTwitterScraperInvalidDataException
import com.x_twitter_scraper.api.services.ExampleResponses
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

internal class FollowerAuditTest {

    private fun parse(): UserRetrieveFollowerAuditResponse =
        jsonMapper().readValue(example, jacksonTypeRef<UserRetrieveFollowerAuditResponse>())

    @Test
    fun parsesTheSpecExampleIntoTheAuditBuiltFromItsValues() {
        val response = parse()

        assertThat(response.validate().isValid()).isTrue()
        assertThat(response.audit()).isEqualTo(audit)
        assertThat(response)
            .isEqualTo(UserRetrieveFollowerAuditResponse.builder().audit(audit).build())
        assertThat(response.hashCode())
            .isEqualTo(UserRetrieveFollowerAuditResponse.builder().audit(audit).build().hashCode())
        assertThat(response.audit().hashCode()).isEqualTo(audit.hashCode())
        assertThat(response.audit().toString()).isEqualTo(audit.toString())
        assertThat(response.toString()).startsWith("UserRetrieveFollowerAuditResponse{audit=")
    }

    @Test
    fun readsOneValueOfEachNestedObject() {
        val parsed = parse().audit()

        assertThat(parsed.account().userName()).isEqualTo("NASA")
        assertThat(parsed.account().sample().posts()).isEqualTo(20.0)
        assertThat(parsed.coverage().total()).isEqualTo(92376187L)
        assertThat(parsed.quality().score()).isEqualTo(71L)
        assertThat(parsed.classes().first().percent()).isEqualTo(61.0)
        assertThat(parsed.signals().last().count()).isEqualTo(58L)
        assertThat(parsed.arrivalBands().last().read()).isEqualTo(100L)
        assertThat(parsed.cohorts().single().percent()).isEqualTo(3.5)
        assertThat(parsed.languages().single().count()).isEqualTo(141L)
        assertThat(parsed.places().single().percent()).isEqualTo(11.0)
        assertThat(parsed.verified().percent()).isEqualTo(4.5)
        assertThat(parsed.followers().single().position()).isEqualTo(1L)
        assertThat(parsed.topFollowers().single().pictureUrl())
            .isEqualTo("https://pbs.twimg.com/profile_images/1/photo.jpg")
    }

    @Test
    fun roundtripsThroughJsonAndEachBuilder() {
        val jsonMapper = jsonMapper()

        val roundtripped =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(audit),
                jacksonTypeRef<FollowerAudit>(),
            )

        assertThat(roundtripped).isEqualTo(audit)
        assertThat(audit.toBuilder().build()).isEqualTo(audit)
        assertThat(audit.coverage().toBuilder().build()).isEqualTo(audit.coverage())
        assertThat(audit.quality().toBuilder().build()).isEqualTo(audit.quality())
        assertThat(audit.classes().first().toBuilder().build()).isEqualTo(audit.classes().first())
        assertThat(audit.signals().first().toBuilder().build()).isEqualTo(audit.signals().first())
        assertThat(audit.arrivalBands().first().toBuilder().build())
            .isEqualTo(audit.arrivalBands().first())
        assertThat(audit.cohorts().single().toBuilder().build()).isEqualTo(audit.cohorts().single())
        assertThat(audit.languages().single().toBuilder().build())
            .isEqualTo(audit.languages().single())
        assertThat(audit.verified().toBuilder().build()).isEqualTo(audit.verified())
        assertThat(follower.toBuilder().build()).isEqualTo(follower)
        val response = UserRetrieveFollowerAuditResponse.builder().audit(audit).build()
        assertThat(response.toBuilder().build()).isEqualTo(response)
    }

    @Test
    fun readsEveryEnumValueAndRejectsAnUnknownOne() {
        val classes =
            listOf(
                FollowerClass.AUTOMATED,
                FollowerClass.INACTIVE,
                FollowerClass.LIKELY_FAKE,
                FollowerClass.LIKELY_REAL,
                FollowerClass.TOO_NEW,
            )
        val grades =
            listOf(
                FollowerAudit.Quality.Grade.A,
                FollowerAudit.Quality.Grade.B,
                FollowerAudit.Quality.Grade.C,
                FollowerAudit.Quality.Grade.D,
                FollowerAudit.Quality.Grade.F,
            )

        assertThat(classes.map { it.asString() })
            .containsExactly("automated", "inactive", "likelyFake", "likelyReal", "tooNew")
        assertThat(classes.map { it.value() })
            .containsExactly(
                FollowerClass.Value.AUTOMATED,
                FollowerClass.Value.INACTIVE,
                FollowerClass.Value.LIKELY_FAKE,
                FollowerClass.Value.LIKELY_REAL,
                FollowerClass.Value.TOO_NEW,
            )
        assertThat(classes.map { it.known() })
            .containsExactly(
                FollowerClass.Known.AUTOMATED,
                FollowerClass.Known.INACTIVE,
                FollowerClass.Known.LIKELY_FAKE,
                FollowerClass.Known.LIKELY_REAL,
                FollowerClass.Known.TOO_NEW,
            )
        assertThat(classes.map { it.validate().isValid() }).containsOnly(true)
        assertThat(grades.map { it.asString() }).containsExactly("A", "B", "C", "D", "F")
        assertThat(grades.map { it.value() })
            .containsExactly(
                FollowerAudit.Quality.Grade.Value.A,
                FollowerAudit.Quality.Grade.Value.B,
                FollowerAudit.Quality.Grade.Value.C,
                FollowerAudit.Quality.Grade.Value.D,
                FollowerAudit.Quality.Grade.Value.F,
            )
        assertThat(grades.map { it.known() })
            .containsExactly(
                FollowerAudit.Quality.Grade.Known.A,
                FollowerAudit.Quality.Grade.Known.B,
                FollowerAudit.Quality.Grade.Known.C,
                FollowerAudit.Quality.Grade.Known.D,
                FollowerAudit.Quality.Grade.Known.F,
            )
        assertThat(grades.map { it.validate().isValid() }).containsOnly(true)
        assertThat(FollowerClass.of("tooNew")).isEqualTo(FollowerClass.TOO_NEW)
        assertThat(FollowerClass.of("tooNew").hashCode())
            .isEqualTo(FollowerClass.TOO_NEW.hashCode())
        assertThat(FollowerAudit.Quality.Grade.of("B").toString()).isEqualTo("B")

        val unknownClass = FollowerClass.of("bot")
        val unknownGrade = FollowerAudit.Quality.Grade.of("E")

        assertThat(unknownClass.value()).isEqualTo(FollowerClass.Value._UNKNOWN)
        assertThat(unknownClass.isValid()).isFalse()
        assertThatThrownBy { unknownClass.known() }
            .isInstanceOf(XTwitterScraperInvalidDataException::class.java)
        assertThat(unknownGrade.value()).isEqualTo(FollowerAudit.Quality.Grade.Value._UNKNOWN)
        assertThat(unknownGrade.isValid()).isFalse()
        assertThatThrownBy { unknownGrade.known() }
            .isInstanceOf(XTwitterScraperInvalidDataException::class.java)
        assertThat(follower.toBuilder().class_(unknownClass).build().isValid()).isFalse()
        assertThat(
                audit
                    .toBuilder()
                    .quality(FollowerAudit.Quality.builder().grade(unknownGrade).score(71L).build())
                    .build()
                    .isValid()
            )
            .isFalse()
    }

    @Test
    fun requiresEveryFieldTheSpecRequires() {
        assertThatThrownBy { FollowerAudit.builder().build() }
            .isInstanceOf(IllegalStateException::class.java)
            .hasMessage("`account` is required, but was not set")
        assertThatThrownBy { AuditedFollower.builder().build() }
            .isInstanceOf(IllegalStateException::class.java)
            .hasMessage("`class_` is required, but was not set")
        assertThatThrownBy { FollowerAudit.ArrivalBand.builder().count(13L).build() }
            .isInstanceOf(IllegalStateException::class.java)
            .hasMessage("`from` is required, but was not set")
        assertThatThrownBy { UserRetrieveFollowerAuditResponse.builder().build() }
            .isInstanceOf(IllegalStateException::class.java)
            .hasMessage("`audit` is required, but was not set")
        assertThat(
                AuditedFollower.builder()
                    .class_(FollowerClass.INACTIVE)
                    .followers(0L)
                    .following(0L)
                    .id("1")
                    .likes(0L)
                    .name("Quiet")
                    .position(2L)
                    .posts(0L)
                    .score(3L)
                    .signals(listOf<String>())
                    .userName("quiet")
                    .build()
                    .joinedAtMs()
            )
            .isNull()
    }

    @Test
    fun keepsUnknownKeysAndFlagsAWrongType() {
        val jsonMapper = jsonMapper()
        val counted =
            jsonMapper.readValue(
                """{"count": "many", "percent": 1.5, "value": "en", "rank": 1}""",
                jacksonTypeRef<FollowerValueCount>(),
            )

        assertThat(counted.isValid()).isFalse()
        assertThat(counted.validity()).isEqualTo(2)
        assertThat(counted._additionalProperties()).containsOnlyKeys("rank")
        assertThatThrownBy { counted.count() }
            .isInstanceOf(XTwitterScraperInvalidDataException::class.java)

        val extra =
            audit
                .toBuilder()
                .putAdditionalProperty("sampled", JsonValue.from(true))
                .putAllAdditionalProperties(mapOf("note" to JsonValue.from("newest")))
                .build()

        assertThat(extra._additionalProperties()).containsOnlyKeys("sampled", "note")
        assertThat(extra).isNotEqualTo(audit)
        assertThat(
                extra
                    .toBuilder()
                    .removeAdditionalProperty("note")
                    .removeAllAdditionalProperties(setOf("sampled"))
                    .build()
            )
            .isEqualTo(audit)
        assertThat(extra.toBuilder().additionalProperties(mapOf<String, JsonValue>()).build())
            .isEqualTo(audit)
    }

    companion object {

        private val example = ExampleResponses.read("examples/x/users/retrieveFollowerAudit.json")

        private val follower =
            AuditedFollower.builder()
                .class_(FollowerClass.LIKELY_REAL)
                .followers(312L)
                .following(410L)
                .id("1852412278803021824")
                .joinedAtMs(1561939200000L)
                .likes(2840L)
                .name("Orbit Watcher")
                .pictureUrl("https://pbs.twimg.com/profile_images/1/photo.jpg")
                .position(1L)
                .posts(1290L)
                .score(1L)
                .addSignal("noBanner")
                .userName("orbit_watcher")
                .build()

        private val audit =
            FollowerAudit.builder()
                .account(
                    UserRetrieveInsightsResponse.Insights.builder()
                        .accountAgeDays(6449.0)
                        .averages(
                            UserRetrieveInsightsResponse.Insights.Averages.builder()
                                .bookmarks(209.7)
                                .engagements(4331.7)
                                .likes(3684.2)
                                .quotes(45.0)
                                .replies(142.7)
                                .reposts(459.8)
                                .views(785247.8)
                                .build()
                        )
                        .engagementPerView(0.55)
                        .engagementRate(0.0047)
                        .medianViews(512330.0)
                        .followers(92376187.0)
                        .following(180.0)
                        .posts(73919.0)
                        .postsPerDay(3.8)
                        .postsPrivate(false)
                        .sample(
                            UserRetrieveInsightsResponse.Insights.Sample.builder()
                                .newestAt(OffsetDateTime.parse("2026-11-02T22:12:05.000Z"))
                                .oldestAt(OffsetDateTime.parse("2026-10-28T13:15:28.000Z"))
                                .posts(20.0)
                                .build()
                        )
                        .topPostId("1852301447063109847")
                        .userId("11348282")
                        .userName("NASA")
                        .build()
                )
                .coverage(
                    FollowerAudit.Coverage.builder()
                        .percent(0.0002)
                        .read(200L)
                        .total(92376187L)
                        .build()
                )
                .quality(
                    FollowerAudit.Quality.builder()
                        .grade(FollowerAudit.Quality.Grade.B)
                        .score(71L)
                        .build()
                )
                .addClass(
                    FollowerAudit.Class.builder()
                        .class_(FollowerClass.LIKELY_REAL)
                        .count(122L)
                        .percent(61.0)
                        .build()
                )
                .addClass(
                    FollowerAudit.Class.builder()
                        .class_(FollowerClass.INACTIVE)
                        .count(41L)
                        .percent(20.5)
                        .build()
                )
                .addClass(
                    FollowerAudit.Class.builder()
                        .class_(FollowerClass.LIKELY_FAKE)
                        .count(29L)
                        .percent(14.5)
                        .build()
                )
                .addClass(
                    FollowerAudit.Class.builder()
                        .class_(FollowerClass.TOO_NEW)
                        .count(8L)
                        .percent(4.0)
                        .build()
                )
                .signals(
                    listOf(
                        FollowerAudit.Signal.builder()
                            .count(36L)
                            .id("defaultPicture")
                            .percent(18.0)
                            .points(2L)
                            .build(),
                        FollowerAudit.Signal.builder()
                            .count(58L)
                            .id("noBio")
                            .percent(29.0)
                            .points(1L)
                            .build(),
                    )
                )
                .addArrivalBand(
                    FollowerAudit.ArrivalBand.builder()
                        .count(13L)
                        .from(1L)
                        .percent(13.0)
                        .read(100L)
                        .to(100L)
                        .build()
                )
                .addArrivalBand(
                    FollowerAudit.ArrivalBand.builder()
                        .count(16L)
                        .from(101L)
                        .percent(16.0)
                        .read(100L)
                        .to(200L)
                        .build()
                )
                .addCohort(
                    FollowerAudit.Cohort.builder()
                        .count(7L)
                        .likelyFake(6L)
                        .percent(3.5)
                        .weekStartMs(1761523200000L)
                        .build()
                )
                .addLanguage(
                    FollowerValueCount.builder().count(141L).percent(70.5).value("en").build()
                )
                .places(
                    listOf(
                        FollowerValueCount.builder()
                            .count(22L)
                            .percent(11.0)
                            .value("United States")
                            .build()
                    )
                )
                .verified(FollowerAudit.Verified.builder().count(9L).percent(4.5).build())
                .addFollower(follower)
                .addTopFollower(follower)
                .build()
    }
}
