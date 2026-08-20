# OpenSSF Best Practices Evidence

This register tracks the Gold assessment for this repository.

The official entry is [bestpractices.dev project 13736][badge].

Assessment date: 2026-07-24.

## Eligibility

This active, released Kotlin SDK meets the OpenSSF eligibility rules.

## Verified Technical Controls

| Area | Evidence |
| --- | --- |
| License | Apache-2.0 and REUSE 3.3 metadata |
| Contribution process | DCO sign-off and independent review rules |
| Governance | Public roles, decisions, releases, and continuity policy |
| Security reporting | Private reporting, response targets, boundaries, and threat model |
| Runtime compatibility | Java 8 bytecode with JDK 26 tests |
| Functional tests | `./scripts/test` rejects failures, errors, and skips |
| Line coverage | `./scripts/coverage` enforces 90% |
| Branch coverage | `./scripts/coverage` enforces 80% |
| Static analysis | Compiler checks, formatting checks, and CodeQL |
| Dynamic analysis | Jazzer fuzzes untrusted retry headers for 30 seconds |
| Dependency integrity | Lockfiles and SHA-256 verification metadata |
| Dependency review | Dependabot, OSV Scanner, and an explicit license policy |
| Licensing gate | Pinned REUSE action checks every repository file |
| Reproducibility | 2 isolated builds compare every public Maven artifact |
| Release integrity | Maven Central provides OpenPGP signatures for all 15 artifacts |
| CI | Pull requests and pushes run pinned, least-privilege workflows |
| Two-factor authentication | The Xquik-dev organization requires 2FA |

The raw report keeps untouched generated-source coverage visible.

Exclusions use the repository-owned, provider-neutral generated-source header prefix.

Maintained runtime exceptions remain inside the enforced coverage gate.

Generated service tests use a loopback transport.

They exercise route construction, request serialization, and response handling.

The generated-model contract suite discovers every model on the test classpath.

It verifies parsing, builders, validation, copying, equality, and unknown values.

The default transport rejects redirects that could forward credentials.

Retry delays reject invalid values and cap server-provided delays.

The latest verified evidence produced these results:

| Evidence | Result |
| --- | --- |
| Functional tests | 1,402 tests across 329 suites; 0 failures, errors, or skips |
| Maintained coverage | 1,128/1,230 lines (91.71%); 410/504 branches (81.35%) |
| Raw coverage | 103,368/107,366 lines (96.28%); 28,397/31,076 branches (91.38%) |
| Dynamic analysis | Jazzer completed 3,574,610 runs without findings |
| Repository licensing | REUSE covered 839/839 files |
| Dependency licensing | 106 components; 0 unknown or disallowed licenses |
| Vulnerability audit | OSV found 0 issues in 7 lockfiles and checksum metadata |
| Reproducibility | 15 Maven artifacts matched byte-for-byte |

Maven Central published `v0.9.1` from commit `0a2ceaa43f30f200734c4584ff854a058c69af62` and its exact tag.

All 15 public files verify with this OpenPGP fingerprint:

`6965 E561 C0AC EE32 060A B961 D203 7E41 57E6 2A59`

The root JAR SHA-256 is:

`6681a1529ef1e95fc28bbc6ef28fdfdfb3fd29e72ba3acb07e7e495876f0530c`

## Outstanding Gold Blockers

Human and organizational evidence remains incomplete.

Do not claim Gold while any mandatory criterion remains unmet.

| Gold Requirement | Current Evidence | Required Action |
| --- | --- | --- |
| Access continuity | Public evidence does not prove 2 release-capable maintainers | Grant and verify another maintainer's access |
| Bus factor | Git history shows one significant contributor | Add another significant contributor |
| Unassociated contributors | Fewer than 2 qualifying contributors are independent | Accept qualifying external contributions |
| Independent review | History does not prove 50% qualifying review coverage | Require and record independent reviews |
| Human security review | No completed review exists within 5 years | Commission and publish a scoped review |

Gold eligibility still requires review by a different human.

## Maintenance

Run these evidence commands before releases:

```sh
./scripts/lint
./scripts/test
./scripts/coverage
./scripts/audit
uvx --from reuse==5.1.1 reuse lint
./scripts/check-reproducible
```

Reassess the register before every major release.

Update bestpractices.dev only with public evidence.

[badge]: https://www.bestpractices.dev/projects/13736

Xquik is an independent third-party service. Not affiliated with X Corp. "Twitter" and "X" are trademarks of X Corp.
