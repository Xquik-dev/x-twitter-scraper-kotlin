// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.services

internal object ExampleResponses {

    fun read(path: String): String =
        checkNotNull(ExampleResponses::class.java.getResource("/$path")) {
                "The test resource $path is missing."
            }
            .readText()
}
