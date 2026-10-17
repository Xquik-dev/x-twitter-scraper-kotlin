package com.x_twitter_scraper.api.models.x.jobs

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.x_twitter_scraper.api.core.JsonValue
import com.x_twitter_scraper.api.core.jsonMapper
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class JobTest {

    @Test
    fun create() {
        val job =
            Job.builder()
                .id("1899202395210649861")
                .url("https://x.com/i/jobs/1899202395210649861")
                .account(
                    Job.Account.builder()
                        .id("1812126202804711425")
                        .verified(true)
                        .description("description")
                        .name("commonware")
                        .profileImageShape("Square")
                        .profilePicture("profilePicture")
                        .profilePictureOriginal("profilePictureOriginal")
                        .username("commonwarexyz")
                        .verifiedType("Business")
                        .build()
                )
                .applyUrl("https://xquik.com/example")
                .company(
                    Job.Company.builder()
                        .id("1714017006498824192")
                        .logoUrl("logoUrl")
                        .name("TotalMed")
                        .build()
                )
                .description("description")
                .descriptionMarkdown("descriptionMarkdown")
                .employmentType("full_time")
                .featured(true)
                .formattedSalary("Starting at \$225K")
                .jobFunction("software_engineering")
                .location("United States")
                .locationType("remote")
                .postedAt(OffsetDateTime.parse("2025-10-04T08:30:00.000Z"))
                .salaryCurrencyCode("USD")
                .salaryInterval("annually")
                .salaryMax(300000.0)
                .salaryMin(225000.0)
                .seniorityLevel("lead")
                .shortDescription("shortDescription")
                .team("Protocol")
                .title("Engineer")
                .build()

        assertThat(job.id()).isEqualTo("1899202395210649861")
        assertThat(job.title()).isEqualTo("Engineer")
        assertThat(job.salaryMin()).isEqualTo(225000.0)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val job =
            Job.builder()
                .id("1899202395210649861")
                .url("https://x.com/i/jobs/1899202395210649861")
                .account(
                    Job.Account.builder()
                        .id("1812126202804711425")
                        .verified(true)
                        .description("description")
                        .name("commonware")
                        .profileImageShape("Square")
                        .profilePicture("profilePicture")
                        .profilePictureOriginal("profilePictureOriginal")
                        .username("commonwarexyz")
                        .verifiedType("Business")
                        .build()
                )
                .applyUrl("https://xquik.com/example")
                .company(
                    Job.Company.builder()
                        .id("1714017006498824192")
                        .logoUrl("logoUrl")
                        .name("TotalMed")
                        .build()
                )
                .description("description")
                .descriptionMarkdown("descriptionMarkdown")
                .employmentType("full_time")
                .featured(true)
                .formattedSalary("Starting at \$225K")
                .jobFunction("software_engineering")
                .location("United States")
                .locationType("remote")
                .postedAt(OffsetDateTime.parse("2025-10-04T08:30:00.000Z"))
                .salaryCurrencyCode("USD")
                .salaryInterval("annually")
                .salaryMax(300000.0)
                .salaryMin(225000.0)
                .seniorityLevel("lead")
                .shortDescription("shortDescription")
                .team("Protocol")
                .title("Engineer")
                .build()
                .toBuilder()
                .putAdditionalProperty("futureField", JsonValue.from("value"))
                .build()

        val roundtrippedJob =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(job),
                jacksonTypeRef<Job>(),
            )

        assertThat(roundtrippedJob).isEqualTo(job)
        assertThat(roundtrippedJob._additionalProperties())
            .containsEntry("futureField", JsonValue.from("value"))
    }

    @Test
    fun validate() {
        val job =
            Job.builder()
                .id("1899202395210649861")
                .url("https://x.com/i/jobs/1899202395210649861")
                .account(
                    Job.Account.builder()
                        .id("1812126202804711425")
                        .verified(true)
                        .description("description")
                        .name("commonware")
                        .profileImageShape("Square")
                        .profilePicture("profilePicture")
                        .profilePictureOriginal("profilePictureOriginal")
                        .username("commonwarexyz")
                        .verifiedType("Business")
                        .build()
                )
                .applyUrl("https://xquik.com/example")
                .company(
                    Job.Company.builder()
                        .id("1714017006498824192")
                        .logoUrl("logoUrl")
                        .name("TotalMed")
                        .build()
                )
                .description("description")
                .descriptionMarkdown("descriptionMarkdown")
                .employmentType("full_time")
                .featured(true)
                .formattedSalary("Starting at \$225K")
                .jobFunction("software_engineering")
                .location("United States")
                .locationType("remote")
                .postedAt(OffsetDateTime.parse("2025-10-04T08:30:00.000Z"))
                .salaryCurrencyCode("USD")
                .salaryInterval("annually")
                .salaryMax(300000.0)
                .salaryMin(225000.0)
                .seniorityLevel("lead")
                .shortDescription("shortDescription")
                .team("Protocol")
                .title("Engineer")
                .build()

        assertThat(job.validate()).isSameAs(job)
        assertThat(job.isValid()).isTrue()
    }
}
