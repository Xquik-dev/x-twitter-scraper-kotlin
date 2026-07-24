package com.x_twitter_scraper.api.core

import java.time.Duration
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DefaultSleeperTest {

    @Test
    fun sleepsSynchronouslyAndAsynchronously() {
        val sleeper = DefaultSleeper()

        try {
            sleeper.sleep(Duration.ZERO)
            val result = runBlocking { sleeper.sleepAsync(Duration.ZERO) }

            assertThat(result).isEqualTo(Unit)
        } finally {
            sleeper.close()
        }
    }
}
