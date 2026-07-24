// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.core

import com.x_twitter_scraper.api.core.http.HttpRequest

internal fun HttpRequest.prepare(
    clientOptions: ClientOptions,
    params: Params,
    security: SecurityOptions = SecurityOptions.all(),
): HttpRequest =
    toBuilder()
        .putAllQueryParams(clientOptions.queryParams)
        .replaceAllQueryParams(params._queryParams())
        .putAllHeaders(clientOptions.securityHeaders(security))
        .putAllHeaders(clientOptions.headers)
        .replaceAllHeaders(params._headers())
        .build()

internal suspend fun HttpRequest.prepareAsync(
    clientOptions: ClientOptions,
    params: Params,
    security: SecurityOptions = SecurityOptions.all(),
) =
    // This async version exists to make it easier to add async specific preparation logic in the
    // future.
    prepare(clientOptions, params, security)
