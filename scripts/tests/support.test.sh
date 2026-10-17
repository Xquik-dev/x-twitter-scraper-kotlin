#!/usr/bin/env bash

# SPDX-FileCopyrightText: 2026 Xquik contributors
#
# SPDX-License-Identifier: Apache-2.0

set -euo pipefail

# shellcheck source=scripts/tests/support.sh
source "$(dirname -- "${BASH_SOURCE[0]}")/support.sh"

prepare_sandbox_cleanup() {
    copy_file scripts/tests/support.sh
    stub scripts/tests/sandbox.test.sh <<EOF
#!/usr/bin/env bash
set -euo pipefail
source "\$(dirname -- "\${BASH_SOURCE[0]}")/support.sh"
test_sandbox_cleanup() {
    mkdir "\$sandbox/cache"
    echo "cache" >"\$sandbox/cache/entry"
    chmod 400 "\$sandbox/cache/entry"
    chmod 500 "\$sandbox/cache"
    echo "ready" >"$sandbox_logs/step-ready"
    if [[ "\${FAKE_STEP_STATUS:-37}" == wait ]]; then
        /bin/sleep 30
    else
        return "\${FAKE_STEP_STATUS:-37}"
    fi
}
run_tests
EOF
}

test_the_runner_removes_read_only_temp_paths_after_a_failing_test() {
    prepare_sandbox_cleanup

    run_in_repo bash scripts/tests/sandbox.test.sh

    assert_equal 1 "$run_status" "the exit code"
    assert_equal "ready" "$(log_of step-ready)" "the failing test"
    assert_no_temporary_entries
}

test_the_runner_removes_read_only_temp_paths_after_sigterm() {
    prepare_sandbox_cleanup
    run_env+=("FAKE_STEP_STATUS=wait")

    run_in_repo_and_terminate_at step-ready bash scripts/tests/sandbox.test.sh

    assert_equal 143 "$run_status" "the exit code"
    assert_no_temporary_entries
}

test_the_runner_removes_read_only_temp_paths_after_success() {
    prepare_sandbox_cleanup
    run_env+=("FAKE_STEP_STATUS=0")

    run_in_repo bash scripts/tests/sandbox.test.sh

    assert_equal 0 "$run_status" "the exit code"
    assert_no_temporary_entries
}

test_the_script_runner_stops_after_sigint_without_running_the_next_command() {
    prepare_sandbox_cleanup
    copy_file scripts/test-scripts
    stub scripts/tests/zz-second.test.sh <<'EOF'
#!/usr/bin/env bash
echo "ran" >"$FAKE_LOGS/second-file"
EOF
    stub parent.sh <<'EOF'
#!/usr/bin/env bash
scripts/test-scripts
echo "ran" >"$FAKE_LOGS/second-command"
EOF
    run_env+=("FAKE_STEP_STATUS=wait")

    run_in_repo_and_signal_at INT step-ready bash parent.sh

    assert_equal "" "$(log_of second-file)" "the second test file"
    assert_equal "" "$(log_of second-command)" "the second command"
    assert_equal 130 "$run_status" "the SIGINT wait status"
    assert_no_temporary_entries
}

run_tests
