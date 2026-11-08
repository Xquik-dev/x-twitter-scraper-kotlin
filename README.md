# Kotlin X (Twitter) API SDK & Scraper by Xquik

Use this X (Twitter) scraper Kotlin SDK by Xquik to search tweets & posts.
Blocking & coroutine clients return typed models.
Public reads need no X (Twitter) developer account.

Xquik is the world's fastest and cheapest X (Twitter) scraper service with the most complete X (Twitter) data.
Most other Apify Actors charge before filtering or deduplication.
Xquik charges only for delivered, unique, filter-matching results and never pads rows.
Xquik is independent and not affiliated with X Corp.

<!-- trust:win:start -->
The Xquik REST API read 45 tweets per second at $0.15 per 1,000 on 2026-10-15. The fastest of 4 rival Actors read 12.5 per second. The cheapest cost $0.173 per 1,000. [Compare each run](https://xquik.com/benchmarks#benchmark-x-tweet-scraper).
<!-- trust:win:end -->

## Install the X (Twitter) API Kotlin SDK

Add the Gradle dependency from [Maven Central](https://central.sonatype.com/artifact/com.xquik.api/x-twitter-scraper-kotlin).

```kotlin
implementation("com.xquik.api:x-twitter-scraper-kotlin:0.9.3")
```

Or add the Maven dependency.

```xml
<dependency>
  <groupId>com.xquik.api</groupId>
  <artifactId>x-twitter-scraper-kotlin</artifactId>
  <version>0.9.3</version>
</dependency>
```

## Scrape tweets with the Twitter Kotlin client

Create an API key in the [Xquik dashboard](https://dashboard.xquik.com) and export it.

```bash
export X_TWITTER_SCRAPER_API_KEY="your-api-key"
```

This program prints 10 recent X (Twitter) posts from 1 account.

```kotlin
import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClient
import com.x_twitter_scraper.api.models.x.tweets.TweetSearchParams

fun main() {
    val client = XTwitterScraperOkHttpClient.fromEnv()

    val params = TweetSearchParams.builder()
        .q("from:elonmusk")
        .limit(10L)
        .build()

    println(client.x().tweets().search(params))

    client.close()
}
```

Next, read the [Kotlin SDK guide](https://docs.xquik.com/sdks/kotlin), [REST API reference](https://docs.xquik.com/api-reference/overview) or [KDocs](https://javadoc.io/doc/com.xquik.api/x-twitter-scraper-kotlin-core).

<!-- trust:start -->

## Benchmarks & usage

[![X (Twitter) API package downloads](https://img.shields.io/endpoint?url=https%3A%2F%2Fxquik.com%2Fbadges%2Fdownloads.json)](https://xquik.com/benchmarks#downloads)
[![X (Twitter) API p99 server read time](https://img.shields.io/endpoint?url=https%3A%2F%2Fxquik.com%2Fbadges%2Frest-p99.json)](https://xquik.com/benchmarks#speed)
[![OpenSSF Best Practices](https://www.bestpractices.dev/projects/13736/badge)](https://www.bestpractices.dev/projects/13736)

- 63,000+ downloads of Xquik's X (Twitter) API packages across 6 registries ([source](https://xquik.com/benchmarks#downloads)) <!-- trust:downloads 63212 -->
- 6.1 s p99 REST API server read time over 7 days, failed reads included ([source](https://xquik.com/benchmarks#speed)) <!-- trust:rest-p99 6050 -->
- This repo meets the OpenSSF Best Practices passing criteria ([source](https://www.bestpractices.dev/projects/13736)) <!-- trust:openssf-x-twitter-scraper-kotlin 1 -->

Checked on 2026-11-07. See each number with its date & source on [xquik.com/benchmarks](https://xquik.com/benchmarks) or as [JSON](https://xquik.com/trust.json).

<!-- trust:end -->

## Common X (Twitter) tasks

| Task | REST route | Kotlin service |
| --- | --- | --- |
| Run an advanced Twitter search | `GET /x/tweets/search` | `client.x().tweets()` |
| Retrieve tweets or threads | `GET /x/tweets/{id}`, `GET /x/tweets/{id}/thread` | `client.x().tweets()` |
| Search X (Twitter) users | `GET /x/users/search` | `client.x().users()` |
| Scrape an X (Twitter) profile timeline | `GET /x/users/{id}/tweets` | `client.x().users()` |
| Export X (Twitter) followers | `GET /x/users/{id}/followers` | `client.x().users()` |
| Export X (Twitter) following accounts | `GET /x/users/{id}/following` | `client.x().users()` |
| Extract an X (Twitter) home timeline | `GET /x/timeline` | `client.x()` |
| Extract list tweets or members | `GET /x/lists/{id}/tweets`, `GET /x/lists/{id}/members` | `client.x().lists()` |
| Search communities | `GET /x/communities/search` | `client.x().communities()` |
| Read bookmarks | `GET /x/bookmarks` | `client.x().bookmarks()` |
| Export large X (Twitter) datasets | `POST /extractions` | `client.extractions()` |
| Monitor tweet keywords or accounts | `POST /monitors/keywords`, `POST /monitors` | `client.monitors()` |
| Receive signed event deliveries | `POST /webhooks` | `client.webhooks()` |
| Post, like, retweet or follow | `POST /x/tweets`, `POST /x/users/{id}/follow` | `client.x().tweets()`, `client.x().users()` |

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

`fromEnv()` reads `X_TWITTER_SCRAPER_API_KEY`.
OAuth users can set `X_TWITTER_SCRAPER_BEARER_TOKEN` instead.

Pass credentials directly when environment variables do not fit.

```kotlin
import com.x_twitter_scraper.api.client.XTwitterScraperClient
import com.x_twitter_scraper.api.client.okhttp.XTwitterScraperOkHttpClient

val client: XTwitterScraperClient = XTwitterScraperOkHttpClient.builder()
    .apiKey("your-api-key")
    .build()
```

Create 1 client and reuse it.

The client reads these settings.

| Setting | System property | Environment variable | Default |
| --- | --- | --- | --- |
| API key | `xtwitterscraper.apiKey` | `X_TWITTER_SCRAPER_API_KEY` | None |
| OAuth token | `xtwitterscraper.bearerToken` | `X_TWITTER_SCRAPER_BEARER_TOKEN` | None |
| Base URL | `xtwitterscraper.baseUrl` | `X_TWITTER_SCRAPER_BASE_URL` | `https://xquik.com/api/v1` |

System properties override environment variables.

## Use coroutines

Call `async()` to use suspending methods.

```kotlin
val result = client.async().x().tweets().search(params)
```

## Configure retries

The client retries eligible failures 2 times by default.

```kotlin
val client: XTwitterScraperClient = XTwitterScraperOkHttpClient.builder()
    .fromEnv()
    .maxRetries(4)
    .build()
```

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

## Help & contributing

- [API questions at docs.xquik.com](https://docs.xquik.com)
- [Security reports in SECURITY.md](SECURITY.md)
- [SDK defects in GitHub Issues](https://github.com/Xquik-dev/x-twitter-scraper-kotlin/issues)
- [Contributions in CONTRIBUTING.md](CONTRIBUTING.md)
- [Governance in GOVERNANCE.md](GOVERNANCE.md)
- [OpenSSF evidence and current limitations in OPENSSF.md](OPENSSF.md)

## Questions

### Do I need an X (Twitter) developer account?

No. Public reads need only an Xquik API key.
Private reads & account actions need a connected X (Twitter) account.

### Is this the official X API?

No. Xquik runs its own REST API. This SDK calls that API, not the official X API.

## More Xquik tools

- [X (Twitter) scraper API & X (Twitter) MCP server](https://github.com/Xquik-dev/x-twitter-scraper) with REST, MCP & Skill setup.
- X (Twitter) API SDKs for [TypeScript](https://github.com/Xquik-dev/x-twitter-scraper-typescript), [Python](https://github.com/Xquik-dev/x-twitter-scraper-python), [Go](https://github.com/Xquik-dev/x-twitter-scraper-go) & [Java](https://github.com/Xquik-dev/x-twitter-scraper-java).
- X (Twitter) API SDKs for [Ruby](https://github.com/Xquik-dev/x-twitter-scraper-ruby), [C#](https://github.com/Xquik-dev/x-twitter-scraper-csharp) & [PHP](https://github.com/Xquik-dev/x-twitter-scraper-php).
- [Twitter CLI & X CLI](https://github.com/Xquik-dev/x-twitter-scraper-cli) for terminal workflows.
- [Twitter advanced search operators](https://github.com/Xquik-dev/x-twitter-advanced-search) with tested examples.
- X (Twitter) agent plugins for [OpenClaw](https://github.com/Xquik-dev/tweetclaw) & [Hermes Agent](https://github.com/Xquik-dev/hermes-tweet).
- X (Twitter) integrations for [n8n](https://github.com/Xquik-dev/n8n-nodes-xquik), [Prefect](https://github.com/Xquik-dev/prefect-xquik), [Haystack](https://github.com/Xquik-dev/xquik-haystack) & [Paperclip](https://github.com/Xquik-dev/paperclip-plugin-xquik).
- X (Twitter) integrations for [Terraform](https://github.com/Xquik-dev/terraform-provider-x-twitter-scraper), [Convex](https://github.com/Xquik-dev/xquik-convex), [Supabase](https://github.com/Xquik-dev/xquik-supabase) & [Strapi](https://github.com/Xquik-dev/strapi-plugin-xquik).
- X (Twitter) MCP for [AI agents](https://github.com/Xquik-dev/xquik-mcp), [VS Code](https://github.com/Xquik-dev/xquik-vscode) & [Raycast](https://github.com/Xquik-dev/xquik-raycast).
- [Xquik X API & Twitter scraper](https://xquik.com).
- [X (Twitter) API documentation](https://docs.xquik.com/api-reference/overview) and its [source](https://github.com/Xquik-dev/xquik-docs).

## License

[Apache License 2.0](LICENSE)

Xquik is an independent third-party service. Not affiliated with X Corp. "Twitter" and "X" are trademarks of X Corp.
