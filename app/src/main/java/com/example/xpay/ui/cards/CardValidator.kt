package com.example.xpay.ui.cards

import java.util.Calendar

/**
 * Pure functions with no Compose or Android UI code, so they are easy to unit test.
 * Every function returns an error message, or null when the value is valid.
 */
object CardValidator {

    const val CARD_NUMBER_LENGTH = 16
    const val HOLDER_MAX_LENGTH = 26

    fun holderError(name: String): String? =
        if (name.trim().length < 2) "Enter the name on the card" else null

    /**
     * Luhn is OFF by default: this is a demo app, and random numbers typed by hand
     * almost always fail it. Turn it on for production. 4242 4242 4242 4242 passes.
     */
    fun cardNumberError(digits: String, enforceLuhn: Boolean = false): String? = when {
        digits.length < CARD_NUMBER_LENGTH -> "Card number must be $CARD_NUMBER_LENGTH digits"
        enforceLuhn && !passesLuhn(digits) -> "This card number is not valid"
        else -> null
    }

    /** "0529" -> (5, 2029). Returns null when the text is not a real month/year. */
    fun parseExpiry(mmYY: String): Pair<Int, Int>? {
        if (mmYY.length != 4 || !mmYY.all(Char::isDigit)) return null
        val month = mmYY.substring(0, 2).toInt()
        val year = 2000 + mmYY.substring(2).toInt()
        return if (month in 1..12) month to year else null
    }

    fun expiryError(mmYY: String, now: Calendar = Calendar.getInstance()): String? {
        if (mmYY.length < 4) return "Use the MM/YY format"
        val (month, year) = parseExpiry(mmYY) ?: return "Enter a valid month (01-12)"
        val currentYear = now.get(Calendar.YEAR)
        val currentMonth = now.get(Calendar.MONTH) + 1
        return when {
            year < currentYear || (year == currentYear && month < currentMonth) -> "This card has expired"
            year > currentYear + 20 -> "Enter a valid year"
            else -> null
        }
    }

    fun cvvError(cvv: String): String? =
        if (cvv.length in 3..4) null else "3 or 4 digits"

    internal fun passesLuhn(digits: String): Boolean {
        var sum = 0
        digits.reversed().forEachIndexed { index, char ->
            var digit = char - '0'
            if (index % 2 == 1) {
                digit *= 2
                if (digit > 9) digit -= 9
            }
            sum += digit
        }
        return sum % 10 == 0
    }
}