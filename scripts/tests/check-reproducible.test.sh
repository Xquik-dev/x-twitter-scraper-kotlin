#!/usr/bin/env bash

# SPDX-FileCopyrightText: 2026 Xquik contributors
#
# SPDX-License-Identifier: Apache-2.0

set -euo pipefail

# shellcheck source=scripts/tests/support.sh
source "$(dirname -- "${BASH_SOURCE[0]}")/support.sh"

prepare_reproducibility() {
    copy_file scripts/check-reproducible
    write_file LICENSE <<'EOF'
Example license.
EOF
    stub gradlew <<'EOF'
#!/bin/sh
mkdir -p build/local-maven-repo META-INF
cp -p LICENSE META-INF/LICENSE
echo "artifact" >build/local-maven-repo/sdk.pom
zip -X -q build/local-maven-repo/sdk.jar META-INF/LICENSE
echo "built" >>"$FAKE_LOGS/builds"
EOF
    sandbox_git init --quiet
    sandbox_git add --all
}

test_reproducible_artifacts_pass_and_remove_the_temp_paths() {
    prepare_reproducibility

    run_in_repo scripts/check-reproducible

    assert_equal 0 "$run_status" "the exit code"
    assert_equal "" "$run_stderr" "stderr"
    assert_ends_with "$run_stdout" "Verified 2 byte-identical Maven artifacts with license notices." "stdout"
    assert_equal "built
built" "$(log_of builds)" "the isolated builds"
    assert_no_temporary_entries
}

test_reproducibility_removes_read_only_temp_paths_after_a_failing_step() {
    prepare_reproducibility
    replace_step "$sandbox_repo/gradlew"

    run_in_repo scripts/check-reproducible

    assert_equal 37 "$run_status" "the exit code"
    assert_equal "ready" "$(log_of step-ready)" "the failing step"
    assert_no_temporary_entries
}

test_reproducibility_removes_read_only_temp_paths_after_sigterm() {
    prepare_reproducibility
    replace_step "$sandbox_repo/gradlew"
    run_env+=("FAKE_STEP_STATUS=wait")

    run_in_repo_and_terminate_at step-ready scripts/check-reproducible

    assert_equal 143 "$run_status" "the exit code"
    assert_no_temporary_entries
}

test_reproducibility_removes_read_only_temp_paths_after_sighup() {
    prepare_reproducibility
    replace_step "$sandbox_repo/gradlew"
    run_env+=("FAKE_STEP_STATUS=wait")

    run_in_repo_and_signal_at HUP step-ready scripts/check-reproducible

    assert_equal 129 "$run_status" "the exit code"
    assert_no_temporary_entries
}

test_reproducibility_removes_read_only_temp_paths_after_sigint() {
    prepare_reproducibility
    replace_step "$sandbox_repo/gradlew"
    run_env+=("FAKE_STEP_STATUS=wait")

    run_in_repo_and_signal_at INT step-ready scripts/check-reproducible

    assert_equal 130 "$run_status" "the exit code"
    assert_no_temporary_entries
}

run_tests
