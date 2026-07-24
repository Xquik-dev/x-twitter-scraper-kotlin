# OpenSSF Best Practices Evidence

This register tracks the Gold assessment for this repository.

The official entry is [bestpractices.dev project 13736][badge].

Assessment date: 2026-07-23.

## Eligibility

This public Kotlin SDK is active and released.

It is eligible for the OpenSSF Best Practices badge.

No OpenSSF-defined ineligibility applies.

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
| CI | Pull requests and pushes run pinned, least-privilege workflows |
| Two-factor authentication | The Xquik-dev organization requires 2FA |

The raw report keeps untouched generated-source coverage visible.

Exclusions require the exact Stainless source marker.

Maintained runtime exceptions remain inside the enforced coverage gate.

Generated service tests use a loopback transport.

They exercise route construction, request serialization, and response handling.

Model tests validate generated parsing and builder behavior.

The default transport rejects redirects that could forward credentials.

Retry delays reject invalid values and cap server-provided delays.

The 2026-07-23 evidence run produced these results:

| Evidence | Result |
| --- | --- |
| Functional tests | 1,207 tests across 282 suites; 0 failures, errors, or skips |
| Maintained coverage | 1,120/1,230 lines (91.06%); 406/504 branches (80.56%) |
| Raw coverage | 34,671/88,473 lines (39.19%); 5,094/24,796 branches (20.54%) |
| Dynamic analysis | Jazzer completed 3,651,892 runs without findings |
| Repository licensing | REUSE covered 807/807 files |
| Dependency licensing | 105 components; 0 unknown or disallowed licenses |
| Vulnerability audit | OSV found 0 issues in 7 lockfiles and checksum metadata |
| Reproducibility | 15 Maven artifacts matched byte-for-byte |

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
| Generated code coverage | Gold does not explicitly exempt generated code | Confirm applicability or raise generated-inclusive coverage |

This remediation pull request needs a different human reviewer.

## Maintenance

Run these evidence commands before releases:

```sh
./scripts/lint
./scripts/test
./scripts/coverage
./scripts/audit
reuse lint
./scripts/check-reproducible
```

Reassess the register before every major release.

Update bestpractices.dev only with public evidence.

[badge]: https://www.bestpractices.dev/projects/13736

Xquik is an independent third-party service. Not affiliated with X Corp. "Twitter" and "X" are trademarks of X Corp.
