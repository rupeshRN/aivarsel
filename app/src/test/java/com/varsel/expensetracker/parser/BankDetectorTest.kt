package com.varsel.expensetracker.parser

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class BankDetectorTest {

    private lateinit var bankDetector: BankDetector
    private lateinit var dummyIndianBankParser: StatementParser
    private lateinit var dummyIciciBankParser: StatementParser
    private lateinit var dummyHdfcBankParser: StatementParser

    @Before
    fun setup() {
        dummyIndianBankParser = object : StatementParser {
            override fun canParse(rawText: String) = rawText.contains("INDIAN BANK") && rawText.contains("STATEMENT OF ACCOUNT") && !rawText.contains("AMBIGUOUS")
            override fun parse(rawText: String) = emptyList<com.varsel.expensetracker.domain.model.Transaction>()
        }
        dummyIciciBankParser = object : StatementParser {
            override fun canParse(rawText: String) = rawText.contains("ICICI BANK") && rawText.contains("STATEMENT OF ACCOUNT") && !rawText.contains("AMBIGUOUS")
            override fun parse(rawText: String) = emptyList<com.varsel.expensetracker.domain.model.Transaction>()
        }
        dummyHdfcBankParser = object : StatementParser {
            override fun canParse(rawText: String) = rawText.contains("HDFC BANK") && rawText.contains("STATEMENT OF ACCOUNT") && !rawText.contains("AMBIGUOUS")
            override fun parse(rawText: String) = emptyList<com.varsel.expensetracker.domain.model.Transaction>()
        }

        val registry = BankParserRegistry {
            listOf(
                RegisteredBankParser("indian_bank", "Indian Bank", dummyIndianBankParser),
                RegisteredBankParser("icici_bank", "ICICI Bank", dummyIciciBankParser),
                RegisteredBankParser("hdfc_bank", "HDFC Bank", dummyHdfcBankParser)
            )
        }

        bankDetector = BankDetector(bankParserRegistry = registry)
    }

    @Test
    fun `test detectResult returns UnsupportedBank for State Bank of India`() {
        val sbiText = """
            STATE BANK OF INDIA
            Branch: Connaught Place, New Delhi
            IFSC: SBIN0001234
            Account Statement for 01/01/2024 to 31/01/2024
        """.trimIndent()

        val result = bankDetector.detectResult(sbiText)
        assertTrue(result is BankDetectionResult.UnsupportedBank)
        assertEquals("State Bank of India (SBI)", (result as BankDetectionResult.UnsupportedBank).detectedBankName)
    }

    @Test(expected = UnsupportedBankException::class)
    fun `test detect throws UnsupportedBankException for State Bank of India`() {
        val sbiText = """
            STATE BANK OF INDIA
            Branch: Connaught Place, New Delhi
            IFSC: SBIN0001234
            Account Statement for 01/01/2024 to 31/01/2024
        """.trimIndent()

        bankDetector.detect(sbiText)
    }

    @Test
    fun `test detectResult returns UnsupportedBank for Axis Bank`() {
        val axisText = """
            AXIS BANK LIMITED
            Statement of Account
            IFSC: UTIB0000001
        """.trimIndent()

        val result = bankDetector.detectResult(axisText)
        assertTrue(result is BankDetectionResult.UnsupportedBank)
        assertEquals("Axis Bank", (result as BankDetectionResult.UnsupportedBank).detectedBankName)
    }

    @Test
    fun `test detectResult returns UnsupportedFormat for supported bank with unrecognized layout`() {
        val brokenIndianBankText = """
            INDIAN BANK
            Random receipt without tabular format or statement dates
        """.trimIndent()

        val result = bankDetector.detectResult(brokenIndianBankText)
        assertTrue(result is BankDetectionResult.UnsupportedFormat)
        assertEquals("Indian Bank", (result as BankDetectionResult.UnsupportedFormat).bankName)
    }

    @Test(expected = UnsupportedStatementFormatException::class)
    fun `test detect throws UnsupportedStatementFormatException for unsupported layout`() {
        val brokenIndianBankText = """
            INDIAN BANK
            Random receipt without tabular format or statement dates
        """.trimIndent()

        bankDetector.detect(brokenIndianBankText)
    }

    @Test
    fun `test detectResult returns Supported for valid Indian Bank statement`() {
        val validIndianBankText = """
            INDIAN BANK
            STATEMENT OF ACCOUNT
        """.trimIndent()

        val result = bankDetector.detectResult(validIndianBankText)
        assertTrue(result is BankDetectionResult.Supported)
        assertEquals("Indian Bank", (result as BankDetectionResult.Supported).displayName)
    }
}
