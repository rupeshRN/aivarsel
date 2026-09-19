
package com.varsel.expensetracker.parser

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BankDetector @Inject constructor(
    private val bankParserRegistry: BankParserRegistry
) {

    /**
     * Detects a statement using all registered bank parsers.
     *
     * A bank is selected only when exactly one parser matches.
     * Multiple matches are treated as ambiguous rather than guessing.
     */
    fun detectResult(rawText: String): BankDetectionResult {
        val matchingParsers = bankParserRegistry
            .all()
            .filter { registeredParser ->
                registeredParser.parser.canParse(rawText)
            }
            .map { registeredParser ->
                registeredParser.parser
            }

        return when {
            matchingParsers.isEmpty() -> {
                BankDetectionResult.Unsupported(
                    reason = "No supported bank statement format was detected."
                )
            }

            matchingParsers.size == 1 -> {
                BankDetectionResult.Supported(
                    parser = matchingParsers.first()
                )
            }

            else -> {
                BankDetectionResult.Ambiguous(
                    parsers = matchingParsers
                )
            }
        }
    }

    /**
     * Compatibility method used by the existing StatementParserEngine.
     */
    fun detect(rawText: String): StatementParser {
        return when (val result = detectResult(rawText)) {

            is BankDetectionResult.Supported -> {
                result.parser
            }

            is BankDetectionResult.Ambiguous -> {
                throw IllegalArgumentException(
                    "The statement matches multiple bank formats. " +
                            "Please provide a clearer statement PDF."
                )
            }

            is BankDetectionResult.Unsupported -> {
                throw IllegalArgumentException(result.reason)
            }
        }
    }
}
