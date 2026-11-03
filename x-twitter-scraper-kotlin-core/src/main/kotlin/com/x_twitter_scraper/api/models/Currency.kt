// SPDX-FileCopyrightText: 2026 Xquik contributors
//
// SPDX-License-Identifier: Apache-2.0

package com.x_twitter_scraper.api.models

import com.fasterxml.jackson.annotation.JsonCreator
import com.x_twitter_scraper.api.core.Enum
import com.x_twitter_scraper.api.core.JsonField
import com.x_twitter_scraper.api.core.JsonValue
import com.x_twitter_scraper.api.errors.XTwitterScraperInvalidDataException

/** ISO 4217 code of a currency Xquik charges in. */
class Currency @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

    /** Returns this class instance's raw value. */
    @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

    companion object {

        val USD = of("USD")

        val EUR = of("EUR")

        val GBP = of("GBP")

        val TRY = of("TRY")

        val JPY = of("JPY")

        val CNY = of("CNY")

        val KRW = of("KRW")

        val CAD = of("CAD")

        val AUD = of("AUD")

        val MXN = of("MXN")

        val AED = of("AED")

        val BRL = of("BRL")

        val CHF = of("CHF")

        val CZK = of("CZK")

        val DKK = of("DKK")

        val HKD = of("HKD")

        val HUF = of("HUF")

        val IDR = of("IDR")

        val ILS = of("ILS")

        val INR = of("INR")

        val MYR = of("MYR")

        val NOK = of("NOK")

        val NZD = of("NZD")

        val PHP = of("PHP")

        val PLN = of("PLN")

        val QAR = of("QAR")

        val RON = of("RON")

        val SAR = of("SAR")

        val SEK = of("SEK")

        val SGD = of("SGD")

        val THB = of("THB")

        val ZAR = of("ZAR")

        fun of(value: String) = Currency(JsonField.of(value))
    }

    /** An enum containing [Currency]'s known values. */
    enum class Known {
        USD,
        EUR,
        GBP,
        TRY,
        JPY,
        CNY,
        KRW,
        CAD,
        AUD,
        MXN,
        AED,
        BRL,
        CHF,
        CZK,
        DKK,
        HKD,
        HUF,
        IDR,
        ILS,
        INR,
        MYR,
        NOK,
        NZD,
        PHP,
        PLN,
        QAR,
        RON,
        SAR,
        SEK,
        SGD,
        THB,
        ZAR,
    }

    /** An enum containing [Currency]'s known values, as well as an [_UNKNOWN] member. */
    enum class Value {
        USD,
        EUR,
        GBP,
        TRY,
        JPY,
        CNY,
        KRW,
        CAD,
        AUD,
        MXN,
        AED,
        BRL,
        CHF,
        CZK,
        DKK,
        HKD,
        HUF,
        IDR,
        ILS,
        INR,
        MYR,
        NOK,
        NZD,
        PHP,
        PLN,
        QAR,
        RON,
        SAR,
        SEK,
        SGD,
        THB,
        ZAR,
        /** An enum member indicating that [Currency] was instantiated with an unknown value. */
        _UNKNOWN,
    }

    /**
     * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN] if
     * unknown.
     */
    fun value(): Value =
        when (this) {
            USD -> Value.USD
            EUR -> Value.EUR
            GBP -> Value.GBP
            TRY -> Value.TRY
            JPY -> Value.JPY
            CNY -> Value.CNY
            KRW -> Value.KRW
            CAD -> Value.CAD
            AUD -> Value.AUD
            MXN -> Value.MXN
            AED -> Value.AED
            BRL -> Value.BRL
            CHF -> Value.CHF
            CZK -> Value.CZK
            DKK -> Value.DKK
            HKD -> Value.HKD
            HUF -> Value.HUF
            IDR -> Value.IDR
            ILS -> Value.ILS
            INR -> Value.INR
            MYR -> Value.MYR
            NOK -> Value.NOK
            NZD -> Value.NZD
            PHP -> Value.PHP
            PLN -> Value.PLN
            QAR -> Value.QAR
            RON -> Value.RON
            SAR -> Value.SAR
            SEK -> Value.SEK
            SGD -> Value.SGD
            THB -> Value.THB
            ZAR -> Value.ZAR
            else -> Value._UNKNOWN
        }

    /**
     * Returns an enum member corresponding to this class instance's value.
     *
     * @throws XTwitterScraperInvalidDataException if this class instance's value is a not a known
     *   member.
     */
    fun known(): Known =
        when (this) {
            USD -> Known.USD
            EUR -> Known.EUR
            GBP -> Known.GBP
            TRY -> Known.TRY
            JPY -> Known.JPY
            CNY -> Known.CNY
            KRW -> Known.KRW
            CAD -> Known.CAD
            AUD -> Known.AUD
            MXN -> Known.MXN
            AED -> Known.AED
            BRL -> Known.BRL
            CHF -> Known.CHF
            CZK -> Known.CZK
            DKK -> Known.DKK
            HKD -> Known.HKD
            HUF -> Known.HUF
            IDR -> Known.IDR
            ILS -> Known.ILS
            INR -> Known.INR
            MYR -> Known.MYR
            NOK -> Known.NOK
            NZD -> Known.NZD
            PHP -> Known.PHP
            PLN -> Known.PLN
            QAR -> Known.QAR
            RON -> Known.RON
            SAR -> Known.SAR
            SEK -> Known.SEK
            SGD -> Known.SGD
            THB -> Known.THB
            ZAR -> Known.ZAR
            else -> throw XTwitterScraperInvalidDataException("Unknown Currency: $value")
        }

    /**
     * Returns this class instance's primitive wire representation.
     *
     * @throws XTwitterScraperInvalidDataException if this class instance's value does not have the
     *   expected primitive type.
     */
    fun asString(): String =
        _value().asString() ?: throw XTwitterScraperInvalidDataException("Value is not a String")

    private var validated: Boolean = false

    /** Validates that this value is a known member. */
    fun validate(): Currency = apply {
        if (validated) {
            return@apply
        }

        known()
        validated = true
    }

    fun isValid(): Boolean =
        try {
            validate()
            true
        } catch (e: XTwitterScraperInvalidDataException) {
            false
        }

    internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is Currency && value == other.value
    }

    override fun hashCode() = value.hashCode()

    override fun toString() = value.toString()
}
