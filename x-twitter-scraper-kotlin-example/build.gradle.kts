plugins {
    id("x-twitter-scraper.kotlin")
    application
}

dependencies {
    implementation(project(":x-twitter-scraper-kotlin-core"))
    implementation(project(":x-twitter-scraper-kotlin-client-okhttp"))
}

application {
    // Use `./gradlew :x-twitter-scraper-kotlin-example:run` to run `Main`
    // Use `./gradlew :x-twitter-scraper-kotlin-example:run -Pexample=Something` to run `SomethingExample`
    mainClass = "com.x_twitter_scraper.api.example.${
        if (project.hasProperty("example"))
            "${project.property("example")}ExampleKt"
        else
            "MainKt"
    }"
}
