// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

plugins {
    id("x-twitter-scraper.kotlin")
    application
}

dependencies {
    implementation(project(":x-twitter-scraper-kotlin-core"))
    implementation(project(":x-twitter-scraper-kotlin-client-okhttp"))
}

application {
    mainClass = "com.x_twitter_scraper.api.example.${
        if (project.hasProperty("example"))
            "${project.property("example")}ExampleKt"
        else
            "MainKt"
    }"
}
