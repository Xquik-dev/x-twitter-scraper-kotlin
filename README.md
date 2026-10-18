# X API & Twitter API Kotlin SDK by Xquik

[![OpenSSF Best Practices](https://www.bestpractices.dev/projects/13736/badge)](https://www.bestpractices.dev/projects/13736)

[![Maven Central](https://img.shields.io/maven-central/v/com.xquik.api/x-twitter-scraper-kotlin)](https://central.sonatype.com/artifact/com.xquik.api/x-twitter-scraper-kotlin/0.9.4)
[![KDocs](https://javadoc.io/badge2/com.xquik.api/x-twitter-scraper-kotlin-core/0.9.4/javadoc.svg)](https://javadoc.io/doc/com.xquik.api/x-twitter-scraper-kotlin-core/0.9.4)

Use this X API & Twitter API SDK to work with X from Kotlin.
It works as a Twitter scraper & tweet scraper for search, profiles & followers.
Scrape Twitter timelines, lists, communities & trends into typed Kotlin models.
Manage media, webhooks, monitors & X automation with blocking & coroutine clients.
It provides a Twitter API alternative through documented Xquik REST routes.

Xquik is the world's fastest and cheapest X Twitter scraper service with the most complete X data.
Xquik charges only for delivered, unique, filter-matching results and never pads rows.

> Xquik is an independent third-party service. Not affiliated with X Corp.
> "Twitter" and "X" are trademarks of X Corp.

[API reference](https://docs.xquik.com/api-reference/overview) | [Authentication](https://xquik.com/auth.md) | [KDocs](https://javadoc.io/doc/com.xquik.api/x-twitter-scraper-kotlin-core)

<!-- trust:start -->

## Trust & usage

[![Twitter API package downloads](https://img.shields.io/endpoint?url=https%3A%2F%2Fxquik.com%2Fbadges%2Fdownloads.json)](https://xquik.com/trust#downloads)
[![Twitter scraper users on Apify](https://img.shields.io/endpoint?url=https%3A%2F%2Fxquik.com%2Fbadges%2Fapify-users.json)](https://apify.com/xquik)
[![Twitter scraper runs on Apify](https://img.shields.io/endpoint?url=https%3A%2F%2Fxquik.com%2Fbadges%2Fapify-runs.json)](https://apify.com/xquik)
[![Tweet scraper rating on Apify](https://img.shields.io/endpoint?url=https%3A%2F%2Fxquik.com%2Fbadges%2Fapify-rating.json)](https://apify.com/xquik/x-tweet-scraper/reviews)
[![Xquik Twitter API accounts](https://img.shields.io/endpoint?url=https%3A%2F%2Fxquik.com%2Fbadges%2Faccounts.json)](https://xquik.com/trust#accounts)
[![Twitter Skill installs](https://img.shields.io/endpoint?url=https%3A%2F%2Fxquik.com%2Fbadges%2Fskill-installs.json)](https://xquik.com/trust#skill-installs)
[![Xquik verified revenue on TrustMRR](https://img.shields.io/endpoint?url=https%3A%2F%2Fxquik.com%2Fbadges%2Frevenue.json)](https://trustmrr.com/startup/xquik)
[![Twitter API median response time](https://img.shields.io/endpoint?url=https%3A%2F%2Fxquik.com%2Fbadges%2Frest-p50.json)](https://xquik.com/trust#speed)
[![Xquik GitHub stars](https://img.shields.io/endpoint?url=https%3A%2F%2Fxquik.com%2Fbadges%2Fgithub-stars.json)](https://github.com/Xquik-dev)
[![OpenSSF Best Practices](https://www.bestpractices.dev/projects/13736/badge)](https://www.bestpractices.dev/projects/13736)

- 51,000+ downloads of Xquik's Twitter API packages on npm, PyPI, RubyGems, NuGet, Packagist & Terraform ([source](https://xquik.com/trust#downloads)) <!-- trust:downloads 51020 -->
- 7,800+ users of Xquik's Twitter scraper Actors on Apify ([source](https://apify.com/xquik)) <!-- trust:apify-users 7800 -->
- 5,700,000+ runs of Xquik's Twitter scraper Actors on Apify ([source](https://apify.com/xquik)) <!-- trust:apify-runs 5704701 -->
- 4.66 of 5 for the X Tweet Scraper from 16 Apify reviews ([source](https://apify.com/xquik/x-tweet-scraper/reviews)) <!-- trust:apify-rating 4.66 16 -->
- 3,900+ accounts on xquik.com ([source](https://xquik.com/trust#accounts)) <!-- trust:accounts 3924 -->
- 1,600+ installs of Xquik's Twitter Skills on skills.sh ([source](https://xquik.com/trust#skill-installs)) <!-- trust:skill-installs 1612 -->
- $38,000+ in revenue verified by TrustMRR ([source](https://trustmrr.com/startup/xquik)) <!-- trust:revenue 38115 -->
- 501 ms median REST API read time over the last 7 days ([source](https://xquik.com/trust#speed)) <!-- trust:rest-p50 501 -->
- 370+ GitHub stars across Xquik's repos ([source](https://github.com/Xquik-dev)) <!-- trust:github-stars 374 -->
- This repo meets the OpenSSF Best Practices passing criteria ([source](https://www.bestpractices.dev/projects/13736)) <!-- trust:openssf-x-twitter-scraper-kotlin 1 -->

Checked on 2026-10-15. See each number with its date & source on [xquik.com/trust](https://xquik.com/trust) or as [JSON](https://xquik.com/trust.json).

<!-- trust:end -->

## Kotlin client or REST

The typed Maven Central package calls the documented Xquik REST API.
It does not call or emulate the official X API.
Use this client for Kotlin coroutines, structured concurrency & typed builders.
Use the Java SDK for Java-first codebases.
Call REST directly when a Maven dependency does not fit.

## Common Twitter & X tasks

| Task | REST route | Kotlin service |
| --- | --- | --- |
| Run an advanced Twitter search | `GET /x/tweets/search` | `client.x().tweets()` |
| Retrieve tweets, threads, replies, or quotes | `GET /x/tweets/{id}`, `GET /x/tweets/{id}/thread` | `client.x().tweets()` |
| Search X or Twitter users | `GET /x/users/search` | `client.x().users()` |
| Scrape an X profile timeline | `GET /x/users/{id}/tweets` | `client.x().users()` |
| Export X followers | `GET /x/users/{id}/followers` | `client.x().users()` |
| Export X following accounts | `GET /x/users/{id}/following` | `client.x().users()` |
| Collect verified followers | `GET /x/users/{id}/verified-followers` | `client.x().users()` |
| Collect mentions, likes, replies, or media | `GET /x/users/{id}/mentions`, `GET /x/users/{id}/likes` | `client.x().users()` |
| Extract an X home timeline | `GET /x/timeline` | `client.x()` |
| Extract list tweets, members, or followers | `GET /x/lists/{id}/tweets`, `GET /x/lists/{id}/members` | `client.x().lists()` |
| Search communities and collect their tweets | `GET /x/communities/search`, `GET /x/communities/{id}/tweets` | `client.x().communities()` |
| Read community activity and growth | `GET /x/communities/{id}/analytics` | `client.x().communities().retrieveAnalytics()` |
| Read bookmarks and bookmark folders | `GET /x/bookmarks`, `GET /x/bookmarks/folders` | `client.x().bookmarks()` |
| Read notifications or direct messages | `GET /x/notifications`, `GET /x/dm/{userId}/history` | `client.x()`, `client.x().dm()` |
| Discover X trends | `GET /x/trends` | `client.x().getTrends()` |
| Export large X datasets | `POST /extractions` | `client.extractions()` |
| Monitor tweet keywords or accounts | `POST /monitors/keywords`, `POST /monitors` | `client.monitors()` |
| Receive signed event deliveries | `POST /webhooks` | `client.webhooks()` |
| Post, like, retweet, or follow | `POST /x/tweets`, `POST /x/users/{id}/follow` | `client.x().tweets()`, `client.x().users()` |
| Send media or update a profile | `POST /x/media`, `PATCH /x/profile` | `client.x().media()`, `client.x().profile()` |
| Manage connected X accounts | `GET /x/accounts`, `POST /x/accounts` | `client.x().accounts()` |

## Install

Add the Gradle dependency.

```kotlin
implementation("com.xquik.api:x-twitter-scraper-kotlin:0.9.4")
```

Or add the Maven dependency.

```xml
<dependency>
  <groupId>com.xquik.api</groupId>
  <artifactId>x-twitter-scraper-kotlin</artifactId>
  <version>0.9.4</version>
</dependency>
```

## Verify Maven signatures

Every Maven Central file has a detached OpenPGP signature. Set `VERSION`, then verify the artifact.

```sh
version=VERSION
artifact="x-twitter-scraper-kotlin-$version.jar"
base="https://repo.maven.apache.org/maven2/com/xquik/api/x-twitter-scraper-kotlin/$version"
curl --fail --location --remote-name "$base/$artifact"
curl --fail --location --remote-name "$base/$artifact.asc"
gpg --keyserver hkps://keyserver.ubuntu.com --recv-keys 0xD2037E4157E62A59
gpg --verify "$artifact.asc" "$artifact"
```

Confirm the fingerprint `6965 E561 C0AC EE32 060A B961 D203 7E41 57E6 2A59`.

## Authenticate

Create an API key in the Xquik dashboard. Then set it in your shell.

```bash
export X_TWITTER_SCRAPER_API_KEY="your-api-key"
```

OAuth users can set `X_TWITTER_SCRAPER_BEARER_TOKEN` instead.

Create 1 client and reuse it.

```kotlin
import com.x_twitter_scraper.api.client.XTwitterScraperClient
import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClient

val client: XTwitterScraperClient = XTwitterScraperOkHttpClient.fromEnv()
```

Pass credentials directly when environment variables do not fit.

```kotlin
val client: XTwitterScraperClient = XTwitterScraperOkHttpClient.builder()
    .apiKey("your-api-key")
    .build()
```

The client reads these settings.

| Setting | System property | Environment variable | Default |
| --- | --- | --- | --- |
| API key | `xtwitterscraper.apiKey` | `X_TWITTER_SCRAPER_API_KEY` | None |
| OAuth token | `xtwitterscraper.bearerToken` | `X_TWITTER_SCRAPER_BEARER_TOKEN` | None |
| Base URL | `xtwitterscraper.baseUrl` | `X_TWITTER_SCRAPER_BASE_URL` | `https://xquik.com/api/v1` |

System properties override environment variables.

## Search tweets

```kotlin
import com.x_twitter_scraper.api.models.x.tweets.TweetSearchParams
import com.x_twitter_scraper.api.models.x.tweets.TweetSearchResponse

val params = TweetSearchParams.builder()
    .q("from:elonmusk")
    .limit(10L)
    .build()

val result: TweetSearchResponse = client.x().tweets().search(params)
```

Close the client when your application stops.

```kotlin
client.close()
```

## Use coroutines

Call `async()` to use suspending methods.

```kotlin
val result: TweetSearchResponse = client.async().x().tweets().search(params)
```

## Configure retries

The client retries eligible failures 2 times by default.

```kotlin
val client: XTwitterScraperClient = XTwitterScraperOkHttpClient.builder()
    .fromEnv()
    .maxRetries(4)
    .build()
```

The API may return a successful HTTP response with a structured error.
Handle the documented error code from the response body.

## Read raw responses

Use `withRawResponse()` when you need headers or status codes.

```kotlin
import com.x_twitter_scraper.api.core.http.HttpResponseFor
import com.x_twitter_scraper.api.models.account.AccountRetrieveResponse

val response: HttpResponseFor<AccountRetrieveResponse> =
    client.account().withRawResponse().retrieve()

val status: Int = response.statusCode()
val account: AccountRetrieveResponse = response.parse()
```

Binary endpoints return `HttpResponse`. Close each response after reading it.

## Support

- [API questions at docs.xquik.com](https://docs.xquik.com)
- [Security reports in SECURITY.md](SECURITY.md)
- [SDK defects in GitHub Issues](https://github.com/Xquik-dev/x-twitter-scraper-kotlin/issues)
- [Contributions in CONTRIBUTING.md](CONTRIBUTING.md)
- [Governance in GOVERNANCE.md](GOVERNANCE.md)
- [OpenSSF evidence and current limitations in OPENSSF.md](OPENSSF.md)

## More Xquik tools

- [X (Twitter) scraper API & Twitter MCP server](https://github.com/Xquik-dev/x-twitter-scraper) with REST, MCP & Skill setup.
- Twitter API SDKs for [TypeScript](https://github.com/Xquik-dev/x-twitter-scraper-typescript), [Python](https://github.com/Xquik-dev/x-twitter-scraper-python), [Go](https://github.com/Xquik-dev/x-twitter-scraper-go), [Ruby](https://github.com/Xquik-dev/x-twitter-scraper-ruby), [Java](https://github.com/Xquik-dev/x-twitter-scraper-java), [C#](https://github.com/Xquik-dev/x-twitter-scraper-csharp) & [PHP](https://github.com/Xquik-dev/x-twitter-scraper-php).
- [Twitter CLI & X CLI](https://github.com/Xquik-dev/x-twitter-scraper-cli) for terminal workflows.
- [Twitter advanced search operators](https://github.com/Xquik-dev/x-twitter-advanced-search) with tested examples.
- [Xquik X API & Twitter scraper](https://xquik.com) and the [Twitter API documentation](https://docs.xquik.com/api-reference/overview).

## License

[Apache License 2.0](LICENSE)

Xquik is an independent third-party service. Not affiliated with X Corp. "Twitter" and "X" are trademarks of X Corp.
