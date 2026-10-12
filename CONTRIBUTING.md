# Contributing

Read [GOVERNANCE.md](GOVERNANCE.md) before proposing major changes.

Follow the shared [Xquik contribution policy][contribution-policy].

## Set Up

Install JDK 26, JDK 25, `curl`, `tar`, `unzip`, and `shellcheck`.

Install `reuse` or `uv` to include the licensing check.

Use JDK 26 as `JAVA_HOME`.

Expose JDK 25 to the compatibility tests:

```sh
export SHRINKER_JAVA_HOME="/path/to/jdk-25"
```

The Gradle wrapper resolves locked, checksum-verified dependencies.

Never commit credentials or runtime environment files.

## Generated Code

Most SDK files come from the public OpenAPI contract.

Preserve generated method names and response contracts.

Avoid generated-file changes when a generator fix exists.

Protect maintained runtime changes with focused regression tests.

## First Contributions

Browse issues labeled [`good first issue`][good-first-issues].

Maintainers use that label for bounded documentation and test changes.

Comment on an issue before starting substantial work.

Ask for scope details when acceptance criteria are unclear.

## Verify Changes

Run focused tests while editing.

Run every gate before requesting review:

```sh
bin/check-local
```

It runs formatting, shellcheck, REUSE, build, tests, coverage, fuzzing, the
dependency audit, and the reproducibility check, in that order.

It stops at the first failure and says what to fix.

GitHub runs no checks for this repository, so this run is the gate.

When the tree is clean and the commit is on GitHub, it posts the result as the
`xquik-local-checks` commit status. Pass `--no-status` to skip that.

The audit script downloads a verified OSV Scanner when needed.

Set `OSV_SCANNER_BIN` to use an OSV Scanner you already have.

Maintained-source line coverage must remain at least 90%.

Maintained-source branch coverage must remain at least 80%.

The raw report keeps generated-source coverage visible.

Skipped tests are forbidden.

Add regression tests for every corrected defect.

Service tests accept literal loopback traffic only.

This guard prevents tests from mutating remote services.

## Submit changes

Use clear Conventional Commit subjects and sign commits with `git commit --signoff`.
Follow the shared [review policy][review-policy].

## Report Security Issues

Never disclose suspected vulnerabilities in public issues.

Follow [SECURITY.md](SECURITY.md) for private reporting.

## Releases

Maintainers release from their own machine with `bin/release-local`.

Land the version bump and its CHANGELOG section on `main` first.

The script stops unless `main` is clean and matches `origin/main`.

It stops if the version's `v*` tag already exists on GitHub.

It runs `bin/check-local` and the dependency audit, then builds the artifacts.

It signs and uploads them to Maven Central, unless that version is already there.

It reads the Central Portal token and the signing key passphrase from the macOS Keychain.

Then it pushes the annotated `v*` tag and creates the GitHub release.

Run `bin/verify-release-signature v<version>` once Maven Central shows the release.

[contribution-policy]: https://github.com/Xquik-dev/.github/blob/main/CONTRIBUTING.md
[good-first-issues]: https://github.com/Xquik-dev/x-twitter-scraper-kotlin/labels/good%20first%20issue
[review-policy]: https://github.com/Xquik-dev/.github/blob/main/REVIEWING.md

Xquik is an independent third-party service. Not affiliated with X Corp. "Twitter" and "X" are trademarks of X Corp.
