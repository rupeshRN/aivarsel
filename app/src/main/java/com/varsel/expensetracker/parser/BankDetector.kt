
package com.varsel.expensetracker.parser

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BankDetector @Inject constructor(
    private val bankParserRegistry: BankParserRegistry
) {

    /**
     * Detects the bank using every registered parser.
     *
     * A bank is selected only when exactly one parser matches.
     * Multiple matches are treated as ambiguous.
     */
    fun detectResult(rawText: String): BankDetectionResult {

        val matchingParsers = bankParserRegistry
            .all()
            .filter { registeredParser ->
                registeredParser.parser.canParse(rawText)
            }

        return when {

            matchingParsers.isEmpty() -> {
                BankDetectionResult.Unsupported(
                    reason = "No supported bank statement format was detected."
                )
            }

            matchingParsers.size == 1 -> {

                val match = matchingParsers.first()

                BankDetectionResult.Supported(
                    parser = match.parser,
                    bankId = match.bankId,
                    displayName = match.displayName
                )
            }

            else -> {

                BankDetectionResult.Ambiguous(
                    parsers = matchingParsers.map { it.parser },
                    possibleBankIds = matchingParsers.map { it.bankId },
                    possibleBankNames = matchingParsers.map { it.displayName }
                )
            }
        }
    }

    /**
     * Compatibility method used by StatementParserEngine.
     */
    fun detect(rawText: String): StatementParser {

        return when (val result = detectResult(rawText)) {

            is BankDetectionResult.Supported -> {
                result.parser
            }

            is BankDetectionResult.Ambiguous -> {

                val bankNames = result.possibleBankNames
                    .joinToString(", ")

                throw IllegalArgumentException(
                    "The statement matches multiple bank formats: $bankNames"
                )
            }

            is BankDetectionResult.Unsupported -> {
                throw IllegalArgumentException(
                    result.reason
                )
            }
            
        }
    }
}
