package com.varsel.expensetracker.parser

import javax.inject.Inject

class AccountDetailsExtractor @Inject constructor() {

    private val accountNumberPatterns = listOf(
        // Matches Account/Acct/A/c No: 1234... or masked XXXXXXXXX1234
        Regex(
            """(?:Saving[s]?\s+|Current\s+|SB\s+)?(?:Account|Acct|A/c\.?|A/C|Acc)\s*(?:Number|No\.?|#)?\s*[:\-]?\s*([A-Za-z0-9Xx\s\-]{4,30})""",
            RegexOption.IGNORE_CASE
        ),
        // Multiline: Account No. \n 123456...
        Regex(
            """(?:Account|Acct|A/c\.?|A/C|Acc)\s*(?:Number|No\.?|#)?\s*[:\-]?\s*\n\s*([A-Za-z0-9Xx\s\-]{4,30})""",
            RegexOption.IGNORE_CASE
        ),
        // Fallback: Statement of Account ...
        Regex(
            """(?:Statement\s+of\s+)?Account\s*[:\-]?\s*([A-Za-z0-9Xx\s\-]{6,30})""",
            RegexOption.IGNORE_CASE
        )
    )

    private val ifscRegex =
        Regex(
            """(?:IFSC|RTGS/NEFT\s+IFSC)(?:\s+Code)?\s*[:\-]?\s*([A-Za-z]{4}0[A-Za-z0-9]{6})""",
            RegexOption.IGNORE_CASE
        )

    fun extractAccountNumber(
        rawText: String
    ): String? {
        for (pattern in accountNumberPatterns) {
            val match = pattern.find(rawText)
            val candidate = match?.groupValues?.getOrNull(1)?.trim()
            if (!candidate.isNullOrBlank()) {
                val cleaned = candidate.lines().firstOrNull()?.trim() ?: candidate
                val digitsOnly = cleaned.filter { it.isLetterOrDigit() }
                if (digitsOnly.length in 4..30 && !isIgnoredWord(digitsOnly)) {
                    return digitsOnly
                }
            }
        }
        return null
    }

    private fun isIgnoredWord(token: String): Boolean {
        val upper = token.uppercase()
        return upper in setOf(
            "NUMBER", "DETAILS", "STATEMENT", "TRANSACTIONS",
            "ACTIVITY", "BRANCH", "PERIOD", "OPENING", "CLOSING", "BALANCE"
        )
    }

    fun extractIfscCode(
        rawText: String
    ): String? {
        return ifscRegex
            .find(rawText)
            ?.groupValues
            ?.getOrNull(1)
            ?.trim()
            ?.uppercase()
    }
}
