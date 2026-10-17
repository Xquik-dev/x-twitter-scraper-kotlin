// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models.x.accounts

import com.x_twitter_scraper.api.core.JsonValue
import com.x_twitter_scraper.api.core.jsonMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AccountBulkRetryParamsTest {

    @Test
    fun create() {
        AccountBulkRetryParams.builder().addAccountId("1").build()
    }

    @Test
    fun body() {
        val params =
            AccountBulkRetryParams.builder().accountIds(listOf("1")).addAccountId("xacct_2").build()

        val jsonMapper = jsonMapper()
        val body = jsonMapper.readTree(jsonMapper.writeValueAsString(params._body()))

        assertThat(params.accountIds()).containsExactly("1", "xacct_2")
        assertThat(body).isEqualTo(jsonMapper.readTree("""{"accountIds":["1","xacct_2"]}"""))
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params = AccountBulkRetryParams.none()

        assertThat(params.accountIds()).isNull()
        assertThat(params._accountIds().isMissing()).isTrue()
        assertThat(params._body()).isNull()
    }

    @Test
    fun bodyWithAdditionalPropertiesWithoutOptionalFields() {
        val params =
            AccountBulkRetryParams.builder()
                .putAdditionalBodyProperty("extra", JsonValue.from("value"))
                .build()

        val jsonMapper = jsonMapper()
        val body = jsonMapper.readTree(jsonMapper.writeValueAsString(params._body()))

        assertThat(body).isEqualTo(jsonMapper.readTree("""{"extra":"value"}"""))
        assertThat(body.has("accountIds")).isFalse()
    }
}
