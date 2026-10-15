#!/usr/bin/env bash

# SPDX-FileCopyrightText: 2026 Xquik contributors
#
# SPDX-License-Identifier: Apache-2.0

set -euo pipefail

# shellcheck source=scripts/tests/support.sh
source "$(dirname -- "${BASH_SOURCE[0]}")/support.sh"

readonly unreadable_tail="Check network access, or set XQUIK_OPENAPI_SPEC to a local copy of https://xquik.com/openapi.json."

prepare_fields() {
    copy_file scripts/api-fields
    copy_file scripts/lib/api-spec.sh
    write_file spec.json <<'EOF'
{"paths": {"/api/v1/account": {"get": {}}}}
EOF
    write_file accepted-field-differences.json <<'EOF'
[]
EOF
}

fake_gradle_writing() {
    printf '%b' "$1" >"$sandbox/result.txt"
    stub gradlew <<EOF
#!/bin/sh
echo "\$*" >>"\$FAKE_LOGS/gradlew"
for argument in "\$@"; do
    case "\$argument" in
        --args=*)
            set -- \$(printf '%s' "\${argument#--args=}" | tr -d "'")
            cp "\$1" "\$FAKE_LOGS/spec.json"
            printf '%s\n' "\$2" >"\$FAKE_LOGS/accepted-path"
            cp "$sandbox/result.txt" "\$3"
            exit 0
            ;;
    esac
done
exit 2
EOF
}

test_a_passing_check_prints_its_summary_on_stdout() {
    prepare_fields
    fake_gradle_writing "pass\nThe SDK's fields match the API for 1 operation.\n"
    run_env=(XQUIK_OPENAPI_SPEC=spec.json)

    run_in_repo scripts/api-fields

    assert_equal 0 "$run_status" "the exit code"
    assert_equal "The SDK's fields match the API for 1 operation." "$run_stdout" "stdout"
    assert_equal "" "$run_stderr" "stderr"
    assert_equal "$(cat "$sandbox_repo/spec.json")" "$(log_of spec.json)" "the spec the check read"
    assert_equal "$sandbox_repo/accepted-field-differences.json" "$(log_of accepted-path)" "the accepted list path"
    assert_contains "$(log_of gradlew)" "--quiet --console=plain :x-twitter-scraper-kotlin-core:apiFields" "the Gradle task"
}

test_a_failing_check_prints_each_difference_on_stderr_and_fails() {
    prepare_fields
    fake_gradle_writing "fail\nGET /api/v1/account response missing plan\nFix each field above in the SDK, or list it in accepted-field-differences.json with a reason.\n"
    run_env=(XQUIK_OPENAPI_SPEC=spec.json)

    run_in_repo scripts/api-fields

    assert_equal 1 "$run_status" "the exit code"
    assert_equal "" "$run_stdout" "stdout"
    assert_equal "GET /api/v1/account response missing plan
Fix each field above in the SDK, or list it in accepted-field-differences.json with a reason." \
        "$run_stderr" "stderr"
}

test_a_build_error_shows_the_gradle_output_and_fails() {
    prepare_fields
    stub gradlew <<'EOF'
#!/bin/sh
echo "e: SdkShapes.kt:1:1 Unresolved reference."
exit 1
EOF
    run_env=(XQUIK_OPENAPI_SPEC=spec.json)

    run_in_repo scripts/api-fields

    assert_equal 1 "$run_status" "the exit code"
    assert_equal "e: SdkShapes.kt:1:1 Unresolved reference.
Could not run the API fields check. Fix the build error above, then rerun." \
        "$run_stderr" "stderr"
}

test_an_unreadable_spec_fails_before_gradle_runs() {
    prepare_fields
    fake_gradle_writing "pass\n"
    run_env=(XQUIK_OPENAPI_SPEC=missing.json)

    run_in_repo scripts/api-fields

    assert_equal 1 "$run_status" "the exit code"
    assert_equal "Could not read the API spec from $sandbox_repo/missing.json. $unreadable_tail" \
        "$run_stderr" "stderr"
    assert_equal "" "$(log_of gradlew)" "the Gradle calls"
}

test_the_public_spec_is_the_default_source() {
    prepare_fields
    fake_gradle_writing "pass\nThe SDK's fields match the API for 1 operation.\n"
    fake curl <<EOF
#!/bin/sh
echo "\$*" >>"\$FAKE_LOGS/curl"
while [ "\$#" -gt 0 ]; do
    if [ "\$1" = "--output" ]; then
        cp "$sandbox_repo/spec.json" "\$2"
    fi
    shift
done
EOF

    run_in_repo scripts/api-fields

    assert_equal 0 "$run_status" "the exit code"
    assert_ends_with "$(log_of curl)" "https://xquik.com/openapi.json" "the curl call"
}

run_tests
