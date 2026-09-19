package com.varsel.expensetracker.parser

import javax.inject.Inject
import javax.inject.Singleton

data class RegisteredBankParser(
    val bankId: String,
    val displayName: String,
    val parser: StatementParser
)

@Singleton
class BankParserRegistry @Inject constructor(
    private val indianBankParser: IndianBankParser,
    private val iciciBankParser: IciciBankParser,
    private val hdfcBankParser: HdfcBankParser
) {

    fun all(): List<RegisteredBankParser> = listOf(
        RegisteredBankParser(
            bankId = "indian_bank",
            displayName = "Indian Bank",
            parser = indianBankParser
        ),
        RegisteredBankParser(
            bankId = "icici_bank",
            displayName = "ICICI Bank",
            parser = iciciBankParser
        ),
        RegisteredBankParser(
            bankId = "hdfc_bank",
            displayName = "HDFC Bank",
            parser = hdfcBankParser
        )
    )
}
