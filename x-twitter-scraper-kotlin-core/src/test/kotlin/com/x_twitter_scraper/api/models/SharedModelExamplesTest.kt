// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import com.x_twitter_scraper.api.core.JsonField
import com.x_twitter_scraper.api.core.jsonMapper
import com.x_twitter_scraper.api.models.x.tweets.TweetAuthor
import com.x_twitter_scraper.api.models.x.tweets.TweetDetail
import com.x_twitter_scraper.api.services.ExampleResponses
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class SharedModelExamplesTest {

    @ParameterizedTest
    @MethodSource("examples")
    fun readsEveryKeyOfTheSpecExampleIntoATypedField(name: String, type: Class<*>) {
        val model = jsonMapper().readValue(ExampleResponses.read("examples/$name.json"), type)

        type.getMethod("validate").invoke(model)
        assertThat(untypedKeys(model, name)).isEmpty()
    }

    private fun untypedKeys(value: Any?, path: String): List<String> =
        when (value) {
            null -> emptyList()
            is List<*> -> value.flatMapIndexed { index, item -> untypedKeys(item, "$path[$index]") }
            else -> {
                val methods = value.javaClass.methods
                val additional = methods.firstOrNull {
                    it.name == "_additionalProperties" && it.parameterCount == 0
                }
                val variant = methods.firstOrNull {
                    it.name.startsWith("is") &&
                        it.parameterCount == 0 &&
                        it.returnType == Boolean::class.javaPrimitiveType &&
                        methods.any { other -> other.name == "as" + it.name.removePrefix("is") } &&
                        it.invoke(value) == true
                }
                val typed = methods.filter {
                    it.parameterCount == 0 &&
                        it.isAnnotationPresent(JsonProperty::class.java) &&
                        JsonField::class.java.isAssignableFrom(it.returnType)
                }
                when {
                    additional != null && typed.isNotEmpty() ->
                        (additional.invoke(value) as Map<*, *>).keys.map { "$path.$it" } +
                            typed.flatMap {
                                untypedKeys(
                                    (it.invoke(value) as JsonField<*>).asKnown(),
                                    "$path.${it.getAnnotation(JsonProperty::class.java).value}",
                                )
                            }
                    variant != null ->
                        untypedKeys(
                            value.javaClass
                                .getMethod("as" + variant.name.removePrefix("is"))
                                .invoke(value),
                            path,
                        )
                    else -> emptyList()
                }
            }
        }

    companion object {

        @JvmStatic
        fun examples(): List<Arguments> =
            listOf(
                Arguments.of("UserProfile", UserProfile::class.java),
                Arguments.of("TweetAuthor", TweetAuthor::class.java),
                Arguments.of("TweetDetail", TweetDetail::class.java),
                Arguments.of("PaginatedUsers", PaginatedUsers::class.java),
                Arguments.of("PaginatedTweets", PaginatedTweets::class.java),
            )
    }
}
