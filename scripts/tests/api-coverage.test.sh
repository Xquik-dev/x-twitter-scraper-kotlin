#!/usr/bin/env bash

# SPDX-FileCopyrightText: 2026 Xquik contributors
#
# SPDX-License-Identifier: Apache-2.0

set -euo pipefail

# shellcheck source=scripts/tests/support.sh
source "$(dirname -- "${BASH_SOURCE[0]}")/support.sh"

readonly services="x-twitter-scraper-kotlin-core/src/main/kotlin/com/x_twitter_scraper/api/services"
readonly client_options="x-twitter-scraper-kotlin-core/src/main/kotlin/com/x_twitter_scraper/api/core/ClientOptions.kt"
readonly unreadable_tail="Check network access, or set XQUIK_OPENAPI_SPEC to a local copy of https://xquik.com/openapi.json."
readonly missing_next_step="Add each operation above to the SDK, or list it in excluded-operations.json with a reason."
readonly stale_next_step="Update excluded-operations.json as the lines above say, then rerun."
readonly invalid_next_step="Fix excluded-operations.json as the lines above say, then rerun."

write_spec() {
    write_file spec.json
}

fake_curl_serving_the_spec() {
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
}

write_exclusions() {
    write_file excluded-operations.json
}

write_service() {
    write_file "$services/$1"
}

prepare_coverage() {
    copy_file scripts/api-coverage
    write_file "$client_options" <<'EOF'
class ClientOptions {
    companion object {
        const val PRODUCTION_URL = "https://xquik.com/api/v1"
    }
}
EOF
    write_service blocking/x/TweetServiceImpl.kt <<'EOF'
class TweetServiceImpl {
    fun retrieve() {
        val request =
            HttpRequest.builder()
                .method(HttpMethod.GET)
                .baseUrl(clientOptions.baseUrl())
                .addPathSegments("x", "tweets", params._pathParam(0))
                .build()
    }

    fun like() {
        val request =
            HttpRequest.builder()
                .method(HttpMethod.POST)
                .baseUrl(clientOptions.baseUrl())
                .addPathSegments(
                    "x",
                    "tweets",
                    params._pathParam(0),
                    "like",
                )
                .build()
    }
}
EOF
    write_service async/x/TweetServiceAsyncImpl.kt <<'EOF'
class TweetServiceAsyncImpl {
    suspend fun retrieve() {
        val request =
            HttpRequest.builder()
                .method(HttpMethod.GET)
                .baseUrl(clientOptions.baseUrl())
                .addPathSegments("x", "tweets", params._pathParam(0))
                .build()
    }

    suspend fun like() {
        val request =
            HttpRequest.builder()
                .method(HttpMethod.POST)
                .baseUrl(clientOptions.baseUrl())
                .addPathSegments("x", "tweets", params._pathParam(0), "like")
                .build()
    }
}
EOF
    write_service async/AccountServiceAsyncImpl.kt <<'EOF'
class AccountServiceAsyncImpl {
    suspend fun retrieve() {
        val request =
            HttpRequest.builder()
                .method(HttpMethod.GET)
                .baseUrl(clientOptions.baseUrl())
                .addPathSegments("account")
                .build()
    }
}
EOF
    write_service blocking/AccountServiceImpl.kt <<'EOF'
class AccountServiceImpl {
    fun retrieve() {
        val request =
            HttpRequest.builder()
                .method(HttpMethod.GET)
                .baseUrl(clientOptions.baseUrl())
                .addPathSegments("account")
                .build()
    }
}
EOF
    write_spec <<'EOF'
{
  "openapi": "3.1.0",
  "servers": [{"url": "https://xquik.com"}],
  "paths": {
    "/api/v1/x/tweets/{id}": {
      "parameters": [{"name": "id", "in": "path", "required": true}],
      "get": {"operationId": "getTweet"}
    },
    "/api/v1/x/tweets/{id}/like": {"post": {"operationId": "likeTweet"}},
    "/api/v1/account": {"get": {"operationId": "getAccount"}},
    "/ask": {
      "servers": [{"url": "https://xquik.com"}],
      "get": {"operationId": "searchXquikDocumentationWithQuery"},
      "post": {"operationId": "searchXquikDocumentation"}
    }
  }
}
EOF
    write_exclusions <<'EOF'
[
  {
    "reason": "Searches the Xquik docs at https://xquik.com/ask, outside the SDK base URL https://xquik.com/api/v1.",
    "operations": ["GET /ask", "POST /ask"]
  }
]
EOF
    run_env=("XQUIK_OPENAPI_SPEC=$sandbox_repo/spec.json")
}

test_a_covered_spec_passes_with_the_counts() {
    prepare_coverage

    run_in_repo scripts/api-coverage

    assert_equal "" "$run_stderr" "stderr"
    assert_equal 0 "$run_status" "the exit code"
    assert_equal "The SDK covers 3 of 5 API operations, and excluded-operations.json lists the other 2." \
        "$run_stdout" "stdout"
    assert_no_temporary_entries
}

test_a_spec_without_exclusions_passes_without_the_exclusion_count() {
    prepare_coverage
    write_spec <<'EOF'
{"servers": [{"url": "https://xquik.com/api/v1"}], "paths": {"/account": {"get": {}}}}
EOF
    write_exclusions <<<'[]'

    run_in_repo scripts/api-coverage

    assert_equal 0 "$run_status" "the exit code"
    assert_equal "The SDK covers 1 of 1 API operations." "$run_stdout" "stdout"
}

test_a_missing_operation_fails_with_its_line_and_the_next_step() {
    prepare_coverage
    write_spec <<'EOF'
{
  "servers": [{"url": "https://xquik.com"}],
  "paths": {
    "/api/v1/x/tweets/{id}": {"get": {}},
    "/api/v1/x/tweets/{id}/like": {"post": {}},
    "/api/v1/account": {"get": {}},
    "/api/v1/x/trends/history": {"get": {"operationId": "getXTrendsHistory"}},
    "/api/v1/x/trends/locations": {"get": {}},
    "/ask": {"get": {}, "post": {}}
  }
}
EOF

    run_in_repo scripts/api-coverage

    assert_equal 1 "$run_status" "the exit code"
    assert_equal "" "$run_stdout" "stdout"
    assert_equal "GET /api/v1/x/trends/history (getXTrendsHistory) is in the API but not in the SDK.
GET /api/v1/x/trends/locations is in the API but not in the SDK.
$missing_next_step" "$run_stderr" "stderr"
}

test_an_exclusion_the_api_no_longer_has_fails() {
    prepare_coverage
    write_exclusions <<'EOF'
[
  {"reason": "Docs search.", "operations": ["GET /ask", "POST /ask", "GET /api/v1/old"]}
]
EOF

    run_in_repo scripts/api-coverage

    assert_equal 1 "$run_status" "the exit code"
    assert_equal "excluded-operations.json lists GET /api/v1/old, which the API no longer has. Remove it.
$stale_next_step" "$run_stderr" "stderr"
}

test_an_exclusion_the_sdk_implements_fails() {
    prepare_coverage
    write_exclusions <<'EOF'
[
  {"reason": "Docs search.", "operations": ["GET /ask", "POST /ask"]},
  {"reason": "Not ported yet.", "operations": ["GET /api/v1/account"]}
]
EOF

    run_in_repo scripts/api-coverage

    assert_equal 1 "$run_status" "the exit code"
    assert_equal "excluded-operations.json lists GET /api/v1/account, which the SDK already implements. Remove it.
$stale_next_step" "$run_stderr" "stderr"
}

test_missing_and_stale_operations_fail_together_with_the_missing_next_step() {
    prepare_coverage
    write_spec <<'EOF'
{
  "servers": [{"url": "https://xquik.com"}],
  "paths": {
    "/api/v1/x/tweets/{id}": {"get": {}},
    "/api/v1/x/tweets/{id}/like": {"post": {}},
    "/api/v1/account": {"get": {}},
    "/api/v1/x/hashflags": {"get": {"operationId": "getHashflags"}}
  }
}
EOF

    run_in_repo scripts/api-coverage

    assert_equal 1 "$run_status" "the exit code"
    assert_equal "GET /api/v1/x/hashflags (getHashflags) is in the API but not in the SDK.
excluded-operations.json lists GET /ask, which the API no longer has. Remove it.
excluded-operations.json lists POST /ask, which the API no longer has. Remove it.
$missing_next_step" "$run_stderr" "stderr"
}

test_path_parameter_names_do_not_matter() {
    prepare_coverage
    write_spec <<'EOF'
{
  "servers": [{"url": "https://xquik.com"}],
  "paths": {
    "/api/v1/x/tweets/{tweetId}": {"get": {}},
    "/api/v1/x/tweets/{tweetId}/like": {"post": {}},
    "/api/v1/account": {"get": {}},
    "/api/v1/x/dm/{userId}/messages/{messageId}": {"delete": {}}
  }
}
EOF
    write_exclusions <<'EOF'
[{"reason": "Not ported yet.", "operations": ["DELETE /api/v1/x/dm/{id}/messages/{message}"]}]
EOF

    run_in_repo scripts/api-coverage

    assert_equal "" "$run_stderr" "stderr"
    assert_equal 0 "$run_status" "the exit code"
    assert_equal "The SDK covers 3 of 4 API operations, and excluded-operations.json lists the other 1." \
        "$run_stdout" "stdout"
}

test_operation_and_path_servers_win_over_the_root_server_and_lose_their_trailing_slash() {
    prepare_coverage
    write_spec <<'EOF'
{
  "servers": [{"url": "https://xquik.com/api/v1/"}],
  "paths": {
    "/x/tweets/{id}": {"get": {}},
    "/x/tweets/{id}/like": {"post": {}},
    "/v1/account": {
      "servers": [{"url": "https://xquik.com/api"}],
      "get": {}
    },
    "/x/trends/history": {
      "servers": [{"url": "https://xquik.com/api/v2"}],
      "get": {"servers": [{"url": "https://xquik.com/"}], "operationId": "getXTrendsHistory"}
    }
  }
}
EOF
    write_exclusions <<'EOF'
[{"reason": "Not ported yet.", "operations": ["GET /x/trends/history"]}]
EOF

    run_in_repo scripts/api-coverage

    assert_equal "" "$run_stderr" "stderr"
    assert_equal 0 "$run_status" "the exit code"
    assert_equal "The SDK covers 3 of 4 API operations, and excluded-operations.json lists the other 1." \
        "$run_stdout" "stdout"
}

test_the_sdk_paths_take_the_path_of_the_production_url() {
    prepare_coverage
    write_file "$client_options" <<'EOF'
class ClientOptions {
    companion object {
        const val PRODUCTION_URL = "https://api.example.com/v2/"
    }
}
EOF
    write_spec <<'EOF'
{"servers": [{"url": "https://api.example.com"}], "paths": {"/v2/account": {"get": {}}, "/api/v1/account": {"get": {"operationId": "getAccount"}}}}
EOF
    write_exclusions <<<'[]'

    run_in_repo scripts/api-coverage

    assert_equal 1 "$run_status" "the exit code"
    assert_equal "GET /api/v1/account (getAccount) is in the API but not in the SDK.
$missing_next_step" "$run_stderr" "stderr"
}

test_sdk_operations_that_the_spec_lacks_pass() {
    prepare_coverage
    write_spec <<'EOF'
{"servers": [{"url": "https://xquik.com"}], "paths": {"/api/v1/account": {"get": {}}}}
EOF
    write_exclusions <<<'[]'

    run_in_repo scripts/api-coverage

    assert_equal 0 "$run_status" "the exit code"
    assert_equal "The SDK covers 1 of 1 API operations." "$run_stdout" "stdout"
}

test_the_public_spec_is_the_default_source() {
    prepare_coverage
    run_env=()
    fake_curl_serving_the_spec

    run_in_repo scripts/api-coverage

    assert_equal 0 "$run_status" "the exit code"
    assert_equal "The SDK covers 3 of 5 API operations, and excluded-operations.json lists the other 2." \
        "$run_stdout" "stdout"
    assert_contains "$(log_of curl)" "https://xquik.com/openapi.json" "the curl call"
}

test_a_spec_url_in_the_environment_replaces_the_default() {
    prepare_coverage
    run_env=("XQUIK_OPENAPI_SPEC=https://spec.example.com/openapi.json")
    fake_curl_serving_the_spec

    run_in_repo scripts/api-coverage

    assert_equal 0 "$run_status" "the exit code"
    assert_contains "$(log_of curl)" "https://spec.example.com/openapi.json" "the curl call"
    assert_not_contains "$(log_of curl)" "https://xquik.com/openapi.json" "the curl call"
}

test_a_spec_that_cannot_be_fetched_fails_with_the_next_step() {
    prepare_coverage
    run_env=()
    fake curl <<'EOF'
#!/bin/sh
echo "curl: (6) Could not resolve host: xquik.com" >&2
exit 6
EOF

    run_in_repo scripts/api-coverage

    assert_equal 1 "$run_status" "the exit code"
    assert_equal "curl: (6) Could not resolve host: xquik.com
Could not read the API spec from https://xquik.com/openapi.json. $unreadable_tail" \
        "$run_stderr" "stderr"
}

test_a_spec_file_that_is_missing_or_not_json_fails_with_the_next_step() {
    prepare_coverage
    run_env=("XQUIK_OPENAPI_SPEC=$sandbox_repo/absent.json")

    run_in_repo scripts/api-coverage

    assert_equal 1 "$run_status" "the exit code for a missing file"
    assert_equal "Could not read the API spec from $sandbox_repo/absent.json. $unreadable_tail" \
        "$run_stderr" "stderr for a missing file"

    write_spec <<'EOF'
openapi: 3.1.0
paths: {}
EOF
    run_env=("XQUIK_OPENAPI_SPEC=$sandbox_repo/spec.json")

    run_in_repo scripts/api-coverage

    assert_equal 1 "$run_status" "the exit code for YAML"
    assert_equal "Could not read the API spec from $sandbox_repo/spec.json. $unreadable_tail" \
        "$run_stderr" "stderr for YAML"
}

test_an_invalid_exclusion_file_fails_with_what_is_wrong() {
    local invalid_file expected_line
    prepare_coverage
    while IFS='|' read -r invalid_file expected_line; do
        write_exclusions <<<"$invalid_file"

        run_in_repo scripts/api-coverage

        assert_equal 1 "$run_status" "the exit code for $invalid_file"
        assert_equal "excluded-operations.json is not valid. $expected_line
$invalid_next_step" "$run_stderr" "stderr for $invalid_file"
    done <<'EOF'
[{"reason": "Docs search.", "operations": ["GET /ask", "POST /ask"]|It does not parse as JSON.
{"reason": "Docs search.", "operations": ["GET /ask", "POST /ask"]}|It must be a JSON array of groups.
["GET /ask"]|Group 1 must be an object with a reason and operations.
[{"reason": " ", "operations": ["GET /ask", "POST /ask"]}]|Group 1 needs a non-empty reason.
[{"operations": ["GET /ask", "POST /ask"]}]|Group 1 needs a non-empty reason.
[{"reason": "Docs search.", "operations": ["GET /ask", "POST /ask"]}, {"reason": "Old.", "operations": []}]|Group 2 needs at least 1 operation.
[{"reason": "Docs search.", "operations": ["GET /ask", "POST /ask"], "note": "x"}]|Group 1 has the unknown key note.
[{"reason": "Docs search.", "operations": ["get /ask", "POST /ask"]}]|Group 1 lists get /ask, but each operation needs an upper-case method, 1 space and a path that starts with /.
[{"reason": "Docs search.", "operations": ["GET /ask", "POST /ask", 7]}]|Group 1 lists 7, but each operation needs an upper-case method, 1 space and a path that starts with /.
[{"reason": "Docs search.", "operations": ["GET /ask", "POST /ask"]}, {"reason": "Again.", "operations": ["GET /ask"]}]|It lists GET /ask 2 times. List each operation once.
[{"reason": "Docs.", "operations": ["GET /x/{id}", "GET /x/{tweetId}", "GET /ask", "POST /ask"]}]|It lists GET /x/{id} 2 times. List each operation once.
null|It must be a JSON array of groups.
[] [{"reason": "Docs search.", "operations": ["GET /ask", "POST /ask"]}]|It holds 2 JSON values, but it needs 1 array.
|It is empty.
EOF
}

test_a_missing_exclusion_file_fails_with_the_next_step() {
    prepare_coverage
    rm -- "$sandbox_repo/excluded-operations.json"

    run_in_repo scripts/api-coverage

    assert_equal 1 "$run_status" "the exit code"
    assert_equal "excluded-operations.json is not valid. The file does not exist.
$invalid_next_step" "$run_stderr" "stderr"
}

test_a_request_whose_path_cannot_be_read_fails_with_its_file_and_line() {
    prepare_coverage
    write_service blocking/DraftServiceImpl.kt <<'EOF'
class DraftServiceImpl {
    fun retrieve() {
        val request =
            HttpRequest.builder()
                .method(HttpMethod.GET)
                .baseUrl(clientOptions.baseUrl())
                .addPathSegments("drafts", draftPath)
                .build()
    }

    fun list() {
        val request =
            HttpRequest.builder()
                .method(method)
                .baseUrl(clientOptions.baseUrl())
                .addPathSegments("drafts")
                .build()
    }
}
EOF

    run_in_repo scripts/api-coverage

    assert_equal 1 "$run_status" "the exit code"
    assert_equal "$services/blocking/DraftServiceImpl.kt:5 sends a request whose method or path this check cannot read.
$services/blocking/DraftServiceImpl.kt:14 sends a request whose method or path this check cannot read.
Build each request with .method(HttpMethod.<METHOD>), then .baseUrl(clientOptions.baseUrl()), then .addPathSegments with string literals and params._pathParam(<index>)." \
        "$run_stderr" "stderr"
}

test_a_production_url_that_cannot_be_read_fails_with_its_file() {
    prepare_coverage
    write_file "$client_options" <<'EOF'
class ClientOptions {
    companion object {
        val PRODUCTION_URL = System.getenv("URL")
    }
}
EOF

    run_in_repo scripts/api-coverage

    assert_equal 1 "$run_status" "the exit code"
    assert_equal "Could not read the default base URL from $client_options. Keep it in 1 line that reads const val PRODUCTION_URL = \"<url>\"." \
        "$run_stderr" "stderr"
}

test_an_operation_that_only_the_blocking_client_has_fails() {
    prepare_coverage
    write_service async/x/TweetServiceAsyncImpl.kt <<'EOF'
class TweetServiceAsyncImpl {
    suspend fun retrieve() {
        val request =
            HttpRequest.builder()
                .method(HttpMethod.GET)
                .baseUrl(clientOptions.baseUrl())
                .addPathSegments("x", "tweets", params._pathParam(0))
                .build()
    }
}
EOF

    run_in_repo scripts/api-coverage

    assert_equal 1 "$run_status" "the exit code"
    assert_equal "POST /api/v1/x/tweets/{}/like is in the blocking client but not in the async client.
POST /api/v1/x/tweets/{id}/like (likeTweet) is in the API but not in the SDK.
$missing_next_step" "$run_stderr" "stderr"
}

test_an_operation_that_only_the_async_client_has_fails() {
    prepare_coverage
    write_service async/x/DraftServiceAsyncImpl.kt <<'EOF'
class DraftServiceAsyncImpl {
    suspend fun list() {
        val request =
            HttpRequest.builder()
                .method(HttpMethod.GET)
                .baseUrl(clientOptions.baseUrl())
                .addPathSegments("drafts")
                .build()
    }
}
EOF

    run_in_repo scripts/api-coverage

    assert_equal 1 "$run_status" "the exit code"
    assert_equal "GET /api/v1/drafts is in the async client but not in the blocking client.
Add each operation above to the client that lacks it, then rerun." "$run_stderr" "stderr"
}

test_an_async_request_whose_path_cannot_be_read_fails_with_its_file_and_line() {
    prepare_coverage
    write_service async/DraftServiceAsyncImpl.kt <<'EOF'
class DraftServiceAsyncImpl {
    suspend fun list() {
        val request =
            HttpRequest.builder()
                .method(HttpMethod.GET)
                .baseUrl(clientOptions.baseUrl())
                .addPathSegments(*segments)
                .build()
    }
}
EOF

    run_in_repo scripts/api-coverage

    assert_equal 1 "$run_status" "the exit code"
    assert_contains "$run_stderr" "$services/async/DraftServiceAsyncImpl.kt:5 sends a request whose method or path this check cannot read." \
        "stderr"
}

test_a_relative_spec_path_reads_from_the_callers_folder() {
    prepare_coverage
    mkdir -p "$sandbox_repo/specs"
    mv -- "$sandbox_repo/spec.json" "$sandbox_repo/specs/openapi.json"
    run_env=("XQUIK_OPENAPI_SPEC=openapi.json")

    run_in_repo sh -c 'cd specs && ../scripts/api-coverage'

    assert_equal "" "$run_stderr" "stderr"
    assert_equal 0 "$run_status" "the exit code"
    assert_equal "The SDK covers 3 of 5 API operations, and excluded-operations.json lists the other 2." \
        "$run_stdout" "stdout"
}

test_a_path_item_that_uses_ref_fails_instead_of_counting_no_operations() {
    prepare_coverage
    write_spec <<'EOF'
{
  "servers": [{"url": "https://xquik.com"}],
  "paths": {
    "/api/v1/x/tweets/{id}": {"get": {}},
    "/api/v1/x/tweets/{id}/like": {"post": {}},
    "/api/v1/account": {"$ref": "#/components/pathItems/Account"},
    "/ask": {"get": {}, "post": {}}
  }
}
EOF

    run_in_repo scripts/api-coverage

    assert_equal 1 "$run_status" "the exit code"
    assert_equal "The API spec path /api/v1/account uses \$ref, which this check cannot read.
Inline each path item that uses \$ref in the API spec, or set XQUIK_OPENAPI_SPEC to a spec that does." \
        "$run_stderr" "stderr"
}

test_api_coverage_removes_read_only_temp_paths_after_a_failing_step() {
    prepare_coverage
    replace_step "$sandbox_bin/jq"

    run_in_repo scripts/api-coverage

    assert_equal 1 "$run_status" "the exit code"
    assert_equal "ready" "$(log_of step-ready)" "the failing step"
    assert_no_temporary_entries
}

test_api_coverage_removes_read_only_temp_paths_after_sigterm() {
    prepare_coverage
    replace_step "$sandbox_bin/jq"
    run_env+=("FAKE_STEP_STATUS=wait")

    run_in_repo_and_terminate_at step-ready scripts/api-coverage

    assert_equal 143 "$run_status" "the exit code"
    assert_no_temporary_entries
}

run_tests
