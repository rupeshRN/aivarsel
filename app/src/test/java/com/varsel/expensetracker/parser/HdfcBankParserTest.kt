package com.varsel.expensetracker.parser

import com.varsel.expensetracker.category.Category
import com.varsel.expensetracker.category.CategoryRuleEngine
import com.varsel.expensetracker.category.CustomRuleEngine
import com.varsel.expensetracker.category.DescriptionNormalizer
import com.varsel.expensetracker.domain.model.TransactionType
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class HdfcBankParserTest {

    private lateinit var hdfcBankParser: HdfcBankParser

    @Before
    fun setup() {
        val descriptionNormalizer = DescriptionNormalizer()
        val customRuleEngine = CustomRuleEngine(descriptionNormalizer)
        val categoryRuleEngine = CategoryRuleEngine(customRuleEngine)
        val descriptionCleaner = DescriptionCleaner()

        hdfcBankParser = HdfcBankParser(
            categoryRuleEngine = categoryRuleEngine,
            descriptionCleaner = descriptionCleaner,
            descriptionNormalizer = descriptionNormalizer
        )
    }

    @Test
    fun testUpiNarrationWithGenericReasonFallsBackToPayee() {
        val sampleStatement = """
            HDFC BANK LIMITED
            ACCOUNT STATEMENT
            Date Narration Chq./Ref.No. Value Dt Withdrawal Amt. Deposit Amt. Closing Balance
            01/08/2026 UPI-423512345678-SWIGGY-HDFC-xxxxxx-NA 000000000000000 01/08/2026 450.00 9,550.00
        """.trimIndent()

        val transactions = hdfcBankParser.parse(sampleStatement)
        assertEquals(1, transactions.size)
        val tx = transactions.first()
        assertEquals(450.00, tx.amount, 0.001)
        assertEquals(TransactionType.EXPENSE, tx.type)
        assertEquals("UPI: Swiggy", tx.description)
        assertEquals("423512345678", tx.referenceNumber)
        assertEquals(Category.FOOD, tx.category)
    }

    @Test
    fun testUpiNarrationWithMeaningfulReasonPreserved() {
        val sampleStatement = """
            HDFC BANK LIMITED
            ACCOUNT STATEMENT
            Date Narration Chq./Ref.No. Value Dt Withdrawal Amt. Deposit Amt. Closing Balance
            02/08/2026 UPI-423599998888-RAVI SHARMA-SBIN-xxxxxx-ROOM RENT 000000000000000 02/08/2026 12,000.00 2,550.00
        """.trimIndent()

        val transactions = hdfcBankParser.parse(sampleStatement)
        assertEquals(1, transactions.size)
        val tx = transactions.first()
        assertEquals(12000.00, tx.amount, 0.001)
        assertEquals(TransactionType.EXPENSE, tx.type)
        assertEquals("Room Rent", tx.description)
        assertEquals("423599998888", tx.referenceNumber)
    }

    @Test
    fun testPosMerchantNormalization() {
        val sampleStatement = """
            HDFC BANK LIMITED
            ACCOUNT STATEMENT
            Date Narration Chq./Ref.No. Value Dt Withdrawal Amt. Deposit Amt. Closing Balance
            03/08/2026 POS 416021XXXXXX1234 STARBUCKS BANGALORE KA IN 000000000000000 03/08/2026 350.00 2,200.00
        """.trimIndent()

        val transactions = hdfcBankParser.parse(sampleStatement)
        assertEquals(1, transactions.size)
        val tx = transactions.first()
        assertEquals(350.00, tx.amount, 0.001)
        assertEquals(TransactionType.EXPENSE, tx.type)
        assertEquals("POS: Starbucks Bangalore", tx.description)
    }

    @Test
    fun testInterestPaidExtraction() {
        val sampleStatement = """
            HDFC BANK LIMITED
            ACCOUNT STATEMENT
            Date Narration Chq./Ref.No. Value Dt Withdrawal Amt. Deposit Amt. Closing Balance
            04/08/2026 INTEREST PAID TILL 31-JUL-2026 000000000000000 04/08/2026 125.50 2,325.50
        """.trimIndent()

        val transactions = hdfcBankParser.parse(sampleStatement)
        assertEquals(1, transactions.size)
        val tx = transactions.first()
        assertEquals(125.50, tx.amount, 0.001)
        assertEquals(TransactionType.INCOME, tx.type)
        assertTrue(tx.description.contains("Interest Paid till"))
        assertEquals(Category.OTHER_INCOME, tx.category)
    }

    @Test
    fun testLastTransactionDoesNotContainAppendedBranchAddressOrThisStatement() {
        val sampleStatement = """
            HDFC BANK LIMITED
            ACCOUNT STATEMENT
            Date Narration Chq./Ref.No. Value Dt Withdrawal Amt. Deposit Amt. Closing Balance
            04/08/2026 UPI-423512345678-SWIGGY-HDFC-xxxxxx-NA 000000000000000 04/08/2026 450.00 9,550.00
            05/08/2026 UPI-423588889999-AMAZON-HDFC-xxxxxx-SHOPPING 000000000000000 05/08/2026 1,200.00 8,350.00
            This Statement. Kodambakkam, Chennai - 600024
            Requesting Branch : Kodambakkam
            Contents of this statement will be considered correct if no error is reported within 30 days.
        """.trimIndent()

        val transactions = hdfcBankParser.parse(sampleStatement)
        assertEquals(2, transactions.size)
        val lastTx = transactions.last()
        assertEquals(1200.00, lastTx.amount, 0.001)
        assertEquals(TransactionType.EXPENSE, lastTx.type)
        assertEquals("Shopping", lastTx.description)
        org.junit.Assert.assertFalse(lastTx.description.contains("This Statement"))
        org.junit.Assert.assertFalse(lastTx.description.contains("Kodambakkam"))
        org.junit.Assert.assertFalse(lastTx.description.contains("Chennai"))
        org.junit.Assert.assertFalse(lastTx.rawDescription.orEmpty().contains("This Statement"))
        org.junit.Assert.assertFalse(lastTx.rawDescription.orEmpty().contains("Kodambakkam"))
    }

    @Test
    fun testMultiPageBranchFootersAreSkippedWithoutStoppingNextPages() {
        val multiPageStatement = """
            HDFC BANK LIMITED
            ACCOUNT STATEMENT
            Date Narration Chq./Ref.No. Value Dt Withdrawal Amt. Deposit Amt. Closing Balance
            01/08/2026 UPI-423512345678-SWIGGY-HDFC-xxxxxx-NA 000000000000000 01/08/2026 300.00 10,000.00
            Requesting Branch: Kodambakkam
            Kodambakkam, Chennai - 600024
            Page No .: 1
            STATEMENT OF ACCOUNT
            Date Narration Chq./Ref.No. Value Dt Withdrawal Amt. Deposit Amt. Closing Balance
            02/08/2026 UPI-423588889999-ZOMATO-HDFC-xxxxxx-FOOD 000000000000000 02/08/2026 500.00 9,500.00
            Requesting Branch: Kodambakkam
            Kodambakkam, Chennai - 600024
            Page No .: 2
        """.trimIndent()

        val transactions = hdfcBankParser.parse(multiPageStatement)
        assertEquals(2, transactions.size)
        assertEquals(300.00, transactions[0].amount, 0.001)
        assertEquals(500.00, transactions[1].amount, 0.001)
        org.junit.Assert.assertFalse(transactions[0].description.contains("Kodambakkam"))
        org.junit.Assert.assertFalse(transactions[1].description.contains("Kodambakkam"))
    }
}
