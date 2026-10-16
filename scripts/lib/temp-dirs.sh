# shellcheck shell=bash

# SPDX-FileCopyrightText: 2026 Xquik contributors
#
# SPDX-License-Identifier: Apache-2.0

temp_dirs=()

remove_temp_dirs() {
    local dir
    for dir in ${temp_dirs[@]+"${temp_dirs[@]}"}; do
        chmod -R u+w -- "$dir" 2>/dev/null || true
        rm -rf -- "$dir"
    done
    temp_dirs=()
}

trap remove_temp_dirs EXIT
trap 'exit 129' HUP
trap 'exit 130' INT
trap 'exit 143' TERM
