// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.jobs

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.jsonMapper
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class JobSearchResponseTest {

    @Test
    fun create() {
        val jobSearchResponse =
            JobSearchResponse.builder()
                .hasNextPage(true)
                .addJob(
                    JobSearchResponse.Job.builder()
                        .id("id")
                        .url("url")
                        .account(
                            JobSearchResponse.Job.Account.builder()
                                .id("id")
                                .verified(true)
                                .description("description")
                                .name("name")
                                .profileImageShape("profileImageShape")
                                .profilePicture("profilePicture")
                                .profilePictureOriginal("profilePictureOriginal")
                                .username("username")
                                .verifiedType("verifiedType")
                                .build()
                        )
                        .applyUrl("applyUrl")
                        .company(
                            JobSearchResponse.Job.Company.builder()
                                .id("id")
                                .logoUrl("logoUrl")
                                .name("name")
                                .build()
                        )
                        .description("description")
                        .descriptionMarkdown("descriptionMarkdown")
                        .employmentType("employmentType")
                        .featured(true)
                        .formattedSalary("formattedSalary")
                        .jobFunction("jobFunction")
                        .location("location")
                        .locationType("locationType")
                        .postedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .salaryCurrencyCode("salaryCurrencyCode")
                        .salaryInterval("salaryInterval")
                        .salaryMax(0.0)
                        .salaryMin(0.0)
                        .seniorityLevel("seniorityLevel")
                        .shortDescription("shortDescription")
                        .team("team")
                        .title("title")
                        .build()
                )
                .nextCursor("next_cursor")
                .build()

        assertThat(jobSearchResponse.hasNextPage()).isEqualTo(true)
        assertThat(jobSearchResponse.jobs())
            .containsExactly(
                JobSearchResponse.Job.builder()
                    .id("id")
                    .url("url")
                    .account(
                        JobSearchResponse.Job.Account.builder()
                            .id("id")
                            .verified(true)
                            .description("description")
                            .name("name")
                            .profileImageShape("profileImageShape")
                            .profilePicture("profilePicture")
                            .profilePictureOriginal("profilePictureOriginal")
                            .username("username")
                            .verifiedType("verifiedType")
                            .build()
                    )
                    .applyUrl("applyUrl")
                    .company(
                        JobSearchResponse.Job.Company.builder()
                            .id("id")
                            .logoUrl("logoUrl")
                            .name("name")
                            .build()
                    )
                    .description("description")
                    .descriptionMarkdown("descriptionMarkdown")
                    .employmentType("employmentType")
                    .featured(true)
                    .formattedSalary("formattedSalary")
                    .jobFunction("jobFunction")
                    .location("location")
                    .locationType("locationType")
                    .postedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .salaryCurrencyCode("salaryCurrencyCode")
                    .salaryInterval("salaryInterval")
                    .salaryMax(0.0)
                    .salaryMin(0.0)
                    .seniorityLevel("seniorityLevel")
                    .shortDescription("shortDescription")
                    .team("team")
                    .title("title")
                    .build()
            )
        assertThat(jobSearchResponse.nextCursor()).isEqualTo("next_cursor")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val jobSearchResponse =
            JobSearchResponse.builder()
                .hasNextPage(true)
                .addJob(
                    JobSearchResponse.Job.builder()
                        .id("id")
                        .url("url")
                        .account(
                            JobSearchResponse.Job.Account.builder()
                                .id("id")
                                .verified(true)
                                .description("description")
                                .name("name")
                                .profileImageShape("profileImageShape")
                                .profilePicture("profilePicture")
                                .profilePictureOriginal("profilePictureOriginal")
                                .username("username")
                                .verifiedType("verifiedType")
                                .build()
                        )
                        .applyUrl("applyUrl")
                        .company(
                            JobSearchResponse.Job.Company.builder()
                                .id("id")
                                .logoUrl("logoUrl")
                                .name("name")
                                .build()
                        )
                        .description("description")
                        .descriptionMarkdown("descriptionMarkdown")
                        .employmentType("employmentType")
                        .featured(true)
                        .formattedSalary("formattedSalary")
                        .jobFunction("jobFunction")
                        .location("location")
                        .locationType("locationType")
                        .postedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .salaryCurrencyCode("salaryCurrencyCode")
                        .salaryInterval("salaryInterval")
                        .salaryMax(0.0)
                        .salaryMin(0.0)
                        .seniorityLevel("seniorityLevel")
                        .shortDescription("shortDescription")
                        .team("team")
                        .title("title")
                        .build()
                )
                .nextCursor("next_cursor")
                .build()

        val roundtrippedJobSearchResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(jobSearchResponse),
                jacksonTypeRef<JobSearchResponse>(),
            )

        assertThat(roundtrippedJobSearchResponse).isEqualTo(jobSearchResponse)
    }
}
