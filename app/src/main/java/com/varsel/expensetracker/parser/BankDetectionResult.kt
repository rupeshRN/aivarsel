package com.varsel.expensetracker.parser

sealed class BankDetectionResult {

    data class Supported(
        val parser: StatementParser,
        val bankId: String? = null,
        val displayName: String? = null
    ) : BankDetectionResult()

    data class Ambiguous(
        val parsers: List<StatementParser>,
        val possibleBankIds: List<String> = emptyList(),
        val possibleBankNames: List<String> = emptyList()
    ) : BankDetectionResult()

    data class Unsupported(
        val reason: String
    ) : BankDetectionResult()
}
