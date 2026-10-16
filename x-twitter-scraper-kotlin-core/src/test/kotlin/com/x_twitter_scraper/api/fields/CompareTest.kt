// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.fields

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CompareTest {

    private val leaf = Shape.Leaf()

    private fun fields(vararg props: Pair<String, Prop>, open: Boolean = false) =
        Shape.Fields(props.toMap(), open = open)

    private fun optional(shape: Shape = leaf) = Prop(shape, false, null)

    @Test
    fun countsAMissingObjectOnceAtItsTopPosition() {
        val spec =
            fields(
                "id" to optional(),
                "data" to optional(fields("followedAt" to optional(), "user" to optional())),
            )

        assertThat(compare(spec, fields("id" to optional()), Side.RESPONSE))
            .containsExactly(Difference(Side.RESPONSE, Kind.MISSING, "data"))
    }

    @Test
    fun walksArraysAndNamesNestedFieldsWithTheirWirePath() {
        val spec =
            fields(
                "events" to
                    optional(
                        Shape.ListOf(fields("data" to optional(fields("followedAt" to optional()))))
                    )
            )
        val sdk = fields("events" to optional(Shape.ListOf(fields("data" to optional(fields())))))

        assertThat(compare(spec, sdk, Side.RESPONSE))
            .containsExactly(Difference(Side.RESPONSE, Kind.MISSING, "events[].data.followedAt"))
    }

    @Test
    fun reportsExtraFieldsUnlessTheSpecObjectIsOpen() {
        val sdk = fields("id" to optional(), "cardName" to optional())

        assertThat(compare(fields("id" to optional()), sdk, Side.REQUEST))
            .containsExactly(Difference(Side.REQUEST, Kind.EXTRA, "cardName"))
        assertThat(compare(fields("id" to optional(), open = true), sdk, Side.REQUEST)).isEmpty()
    }

    @Test
    fun reportsEnumValuesThatDifferOnlyWhenBothSidesDefineThem() {
        val spec = fields("status" to optional(Shape.Leaf(setOf("active", "paused"))))

        assertThat(
                compare(
                    spec,
                    fields("status" to optional(Shape.Leaf(setOf("active")))),
                    Side.RESPONSE,
                )
            )
            .containsExactly(Difference(Side.RESPONSE, Kind.ENUM, "status"))
        assertThat(compare(spec, fields("status" to optional()), Side.RESPONSE)).isEmpty()
    }

    @Test
    fun reportsRequiredAndNullableFlagsOnlyWhereTheSdkEncodesThem() {
        val spec = fields("name" to Prop(leaf, true, true), "label" to Prop(leaf, true, false))
        val sdk = fields("name" to Prop(leaf, false, false), "label" to Prop(leaf, null, null))

        assertThat(compare(spec, sdk, Side.RESPONSE))
            .containsExactly(
                Difference(Side.RESPONSE, Kind.REQUIRED, "name"),
                Difference(Side.RESPONSE, Kind.NULLABLE, "name"),
            )
    }

    @Test
    fun countsAFieldOfAnotherKindAsMissing() {
        val spec = fields("geo" to optional(fields("lat" to optional())))

        assertThat(compare(spec, fields("geo" to optional()), Side.RESPONSE))
            .containsExactly(Difference(Side.RESPONSE, Kind.MISSING, "geo"))
    }

    @Test
    fun stopsAtFreeFormCyclesAndUntypedValues() {
        val spec =
            fields("meta" to optional(fields("a" to optional())), "parent" to optional(Shape.Cycle))
        val sdk =
            fields(
                "meta" to optional(Shape.Unknown),
                "parent" to optional(fields("b" to optional())),
            )

        assertThat(compare(spec, sdk, Side.RESPONSE)).isEmpty()
        assertThat(
                compare(
                    fields("meta" to optional(Shape.Free)),
                    fields("meta" to optional(fields("x" to optional()))),
                    Side.RESPONSE,
                )
            )
            .isEmpty()
    }

    @Test
    fun mergesUnionVariantsAndRequiresOnlyWhatEveryVariantRequires() {
        val spec =
            Shape.AnyOf(
                listOf(
                    fields("id" to Prop(leaf, true, false), "text" to Prop(leaf, true, false)),
                    fields("id" to Prop(leaf, true, false)),
                )
            )
        val sdk = fields("id" to Prop(leaf, true, false), "text" to Prop(leaf, false, null))

        assertThat(compare(spec, sdk, Side.RESPONSE)).isEmpty()
    }
}
