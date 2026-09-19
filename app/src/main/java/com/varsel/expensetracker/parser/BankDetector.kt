package com.varsel.expensetracker.parser

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BankDetector @Inject constructor(
    private val indianBankParser: IndianBankParser,
    private val iciciBankParser: IciciBankParser,
    private val hdfcBankParser: HdfcBankParser
) {

    fun detect(rawText: String): StatementParser {
        val upper = rawText.uppercase()
        val header = rawText.lines().take(30).joinToString("\n").uppercase()

        // 1. Primary Header Branding Check
        val hasHdfcInHeader = header.contains("HDFC") ||
                header.contains("HDFCBANK") ||
                header.contains("HDFC BANK") ||
                header.contains("WWW.HDFCBANK.COM")

        val hasIndianBankInHeader = header.contains("INDIAN BANK") ||
                header.contains("INDIANBANK") ||
                header.contains("IND BL") ||
                header.contains("IDIB")

        val hasIciciInHeader = header.contains("ICICI") ||
                header.contains("ICIC0")

        if (hasHdfcInHeader && !hasIndianBankInHeader && !hasIciciInHeader) {
            return hdfcBankParser
        }

        if (hasIndianBankInHeader && !hasIciciInHeader && !hasHdfcInHeader) {
            return indianBankParser
        }

        if (hasIciciInHeader && !hasIndianBankInHeader && !hasHdfcInHeader) {
            return iciciBankParser
        }
// 2. Validate parser structure before selecting a bank.
// Never select a bank using scores or transaction narration alone.

if (hdfcBankParser.canParse(rawText)) {
    return hdfcBankParser
}

if (indianBankParser.canParse(rawText)) {
    return indianBankParser
}

if (iciciBankParser.canParse(rawText)) {
    return iciciBankParser
}

        throw IllegalArgumentException("Unsupported bank statement format. Supported banks: HDFC, ICICI, and Indian Bank.")
    }
}


