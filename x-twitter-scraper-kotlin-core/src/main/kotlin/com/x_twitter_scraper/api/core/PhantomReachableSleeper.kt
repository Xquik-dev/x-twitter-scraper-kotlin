// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.core

import java.time.Duration

internal class PhantomReachableSleeper(private val sleeper: Sleeper) : Sleeper {

    init {
        closeWhenPhantomReachable(this, sleeper)
    }

    override fun sleep(duration: Duration) = sleeper.sleep(duration)

    override suspend fun sleepAsync(duration: Duration) = sleeper.sleepAsync(duration)

    override fun close() = sleeper.close()
}
