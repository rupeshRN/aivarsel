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

class IciciBankParserTest {

    private lateinit var iciciBankParser: IciciBankParser

    @Before
    fun setup() {
        val descriptionNormalizer = DescriptionNormalizer()
        val customRuleEngine = CustomRuleEngine(descriptionNormalizer)
        val categoryRuleEngine = CategoryRuleEngine(customRuleEngine)
        val descriptionCleaner = DescriptionCleaner()

        iciciBankParser = IciciBankParser(
            categoryRuleEngine = categoryRuleEngine,
            descriptionCleaner = descriptionCleaner,
            descriptionNormalizer = descriptionNormalizer
        )
    }

    @Test
    fun `test canParse detects ICICI Bank statements`() {
        val sampleStatement = """
            ICICI Bank
            Statement of Transactions in Saving Account no. 123456789012 in INR for the period August 25, 2025 - August 25, 2026
            Your Base Branch: ICICI BANK LIMITED
        """.trimIndent()

        assertTrue(iciciBankParser.canParse(sampleStatement))
    }

    @Test
    fun `test parse extracts sample ICICI transactions correctly`() {
        val rawStatement = """
            ICICI Bank
            Statement of Transactions in Saving Account no. 123456789012 in INR for the period August 25, 2025 - August 25, 2026
            Your Base Branch: ICICI BANK LIMITED

            S No.  Transaction Date  Cheque Number  Transaction Remarks  Withdrawal Amount (INR)  Deposit Amount (INR)  Balance (INR)
            1  25.08.2025  Navaneetha Krishnan
            MMT/IMPS/523700123471/For ticket/Krishnan/IDIB0001234  2000.00  34521.01

            2  27.08.2025  Home Expenses
            MMT/IMPS/523922456789/For home expens/Krishnan/IDIB0001234  5000.00  29521.01

            3  28.08.2025  NEFT trxn
            NEFT-SBIN000123456789-ATTN//INB-0000003...-SBIN0001234  4220.00  33741.01

            4  29.08.2025  CAPGEMINI TECHNOLOGY SERVICES INDIA
            NEFT-SCBL0012345-CAPGEMINI TECHNOLOGY SERVICES INDIA-SALARY CR AUG-25-44605040462-SCBL00  74872.00  108613.01

            5  01.09.2025  Gym Payment
            MMT/IMPS/524400120254/B gym/Trainer/HDFC0001234  1400.00  107213.01

            16  01.10.2025  CC EMI
            MMT/IMPS/527416441723/cc emi/Bank/HDFC0001234  1365.00  118912.01

            Sincerely,
            Team ICICI Bank
            This is a system generated statement. Hence, it does not require any signature.
            Legends for transactions in your Account Statement
        """.trimIndent()

        val transactions = iciciBankParser.parse(rawStatement)

        assertEquals(6, transactions.size)

        // Tx 1: 2000.00 Expense
        assertEquals(2000.00, transactions[0].amount, 0.01)
        assertEquals(TransactionType.EXPENSE, transactions[0].type)
        assertEquals("523700123471", transactions[0].referenceNumber)

        // Tx 3: 4220.00 Income (Balance went from 29521.01 to 33741.01)
        assertEquals(4220.00, transactions[2].amount, 0.01)
        assertEquals(TransactionType.INCOME, transactions[2].type)

        // Tx 4: 74872.00 Salary Income (Balance went from 33741.01 to 108613.01)
        assertEquals(74872.00, transactions[3].amount, 0.01)
        assertEquals(TransactionType.INCOME, transactions[3].type)
        assertEquals(Category.SALARY, transactions[3].category)
        assertTrue(transactions[3].description.contains("Capgemini", ignoreCase = true))

        // Tx 5: 1400.00 Expense for Gym
        assertEquals(1400.00, transactions[4].amount, 0.01)
        assertEquals(TransactionType.EXPENSE, transactions[4].type)
        assertEquals("524400120254", transactions[4].referenceNumber)
    }
}
