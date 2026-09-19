package com.varsel.expensetracker.parser

sealed class BankDetectionResult {

    data class Supported(
        val parser: StatementParser
    ) : BankDetectionResult()

    data class Ambiguous(
        val parsers: List<StatementParser>
    ) : BankDetectionResult()

    data class Unsupported(
        val reason: String
    ) : BankDetectionResult()
}
