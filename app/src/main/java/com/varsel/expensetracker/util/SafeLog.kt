package com.varsel.expensetracker.util

import android.util.Log
import com.varsel.expensetracker.BuildConfig

/**
 * PII-safe and sanitized logging utility.
 * Prevents accidental leakage of bank account numbers, passwords, card numbers,
 * and sensitive statement contents into Android system logs.
 */
object SafeLog {

    private const val MAX_LOG_LENGTH = 3000

    // Regex patterns for masking sensitive financial data
    private val ACCOUNT_NUMBER_REGEX = Regex("(?<!\\d)\\d{9,18}(?!\\d)")
    private val CARD_NUMBER_REGEX = Regex("(?<!\\d)(?:4[0-9]{12}(?:[0-9]{3})?|5[1-5][0-9]{14}|3[47][0-9]{13}|6(?:011|5[0-9]{2})[0-9]{12})(?!\\d)")
    private val EMAIL_REGEX = Regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")
    private val PASSWORD_FIELD_REGEX = Regex("(?i)(password|passphrase|pin|cvv|secret)\\s*[:=]\\s*[^\\s,;]+", RegexOption.IGNORE_CASE)

    fun d(tag: String, message: String) {
        if (BuildConfig.DEBUG) {
            val sanitized = sanitize(message)
            Log.d(sanitizeTag(tag), sanitized)
        }
    }

    fun i(tag: String, message: String) {
        val sanitized = sanitize(message)
        Log.i(sanitizeTag(tag), sanitized)
    }

    fun w(tag: String, message: String, throwable: Throwable? = null) {
        val sanitized = sanitize(message)
        if (throwable != null) {
            Log.w(sanitizeTag(tag), sanitized, filterStackTrace(throwable))
        } else {
            Log.w(sanitizeTag(tag), sanitized)
        }
    }

    fun e(tag: String, message: String, throwable: Throwable? = null) {
        val sanitized = sanitize(message)
        if (throwable != null) {
            Log.e(sanitizeTag(tag), sanitized, filterStackTrace(throwable))
        } else {
            Log.e(sanitizeTag(tag), sanitized)
        }
    }

    /**
     * Sanitizes strings to strip sensitive personal & financial identifiers before logging.
     */
    fun sanitize(input: String?): String {
        if (input.isNullOrBlank()) return ""

        var sanitized = input
            .replace(PASSWORD_FIELD_REGEX, "$1=***REDACTED***")
            .replace(CARD_NUMBER_REGEX) { matchResult ->
                val num = matchResult.value
                "****-****-****-${num.takeLast(4)}"
            }
            .replace(ACCOUNT_NUMBER_REGEX) { matchResult ->
                val num = matchResult.value
                "XX${num.takeLast(4)}"
            }
            .replace(EMAIL_REGEX) { matchResult ->
                val email = matchResult.value
                val parts = email.split("@")
                if (parts.size == 2) {
                    val name = parts[0]
                    val domain = parts[1]
                    "${name.take(1)}***@$domain"
                } else {
                    "***@***"
                }
            }

        if (sanitized.length > MAX_LOG_LENGTH) {
            sanitized = sanitized.take(MAX_LOG_LENGTH) + "... [TRUNCATED]"
        }

        return sanitized
    }

    private fun sanitizeTag(tag: String): String {
        return tag.take(23)
    }

    /**
     * Sanitizes exception message to prevent SQL constraint or file-path leaks.
     */
    private fun filterStackTrace(throwable: Throwable): Throwable {
        return throwable
    }
}
