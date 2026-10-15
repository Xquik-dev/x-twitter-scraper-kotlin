# shellcheck shell=bash

# SPDX-FileCopyrightText: 2026 Xquik contributors
#
# SPDX-License-Identifier: Apache-2.0

readonly public_spec="https://xquik.com/openapi.json"
spec_source="${XQUIK_OPENAPI_SPEC:-$public_spec}"
case "$spec_source" in
    http://* | https://* | /*) ;;
    *) spec_source="$PWD/$spec_source" ;;
esac
readonly spec_source
readonly unreadable_spec="Could not read the API spec from $spec_source. Check network access, or set XQUIK_OPENAPI_SPEC to a local copy of $public_spec."

load_api_spec() {
    case "$spec_source" in
        http://* | https://*)
            curl --fail --silent --show-error --location --retry 3 --max-time 60 \
                --output "$1" "$spec_source" || fail "$unreadable_spec"
            ;;
        *) cp -- "$spec_source" "$1" 2>/dev/null || fail "$unreadable_spec" ;;
    esac
    jq -e '.paths | type == "object"' "$1" >/dev/null 2>&1 || fail "$unreadable_spec"
}
