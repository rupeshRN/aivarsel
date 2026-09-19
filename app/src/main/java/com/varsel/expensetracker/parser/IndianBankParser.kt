package com.varsel.expensetracker.parser

import com.varsel.expensetracker.category.CategoryRuleEngine
import com.varsel.expensetracker.domain.model.Transaction
import com.varsel.expensetracker.domain.model.TransactionType
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class IndianBankParser @Inject constructor(
    private val blockBuilder: TransactionBlockBuilder,
    private val merchantExtractor: MerchantExtractor,
    private val descriptionCleaner: DescriptionCleaner,
    private val slashTokenizer: SlashTokenizer,
    private val tokenNormalizer: TokenNormalizer,
    private val fieldInterpreter: FieldInterpreter,
    private val amountInterpreter: AmountInterpreter,
    private val parserConfidenceEngine: ParserConfidenceEngine,
    private val displayDescriptionBuilder: DisplayDescriptionBuilder,
    private val categoryRuleEngine: CategoryRuleEngine,
) : StatementParser {

    override fun canParse(rawText: String): Boolean {

        val upper = rawText.uppercase()

        /*
         * ------------------------------------------------------------
         * 1. Examine the statement header separately.
         *
         * Bank names appearing inside transaction narrations should
         * not be sufficient to identify the statement.
         * ------------------------------------------------------------
         */
        val header = rawText
            .lines()
            .take(40)
            .joinToString("\n")
            .uppercase()

        /*
         * ------------------------------------------------------------
         * 2. Strong Indian Bank identity evidence
         * ------------------------------------------------------------
         */
        val hasStrongIndianBankBrand =
            header.contains("INDIAN BANK") ||
            header.contains("INDIANBANK") ||
            header.contains("IND BL") ||
            header.contains("IDIB")

        /*
         * ------------------------------------------------------------
         * 3. Indian Bank statement-layout evidence
         *
         * These are deliberately based on combinations of fields,
         * rather than a single generic word.
         * ------------------------------------------------------------
         */
        val hasAccountActivity =
            upper.contains("ACCOUNT ACTIVITY")

        val hasTransactionDetails =
            upper.contains("TRANSACTION DETAILS")

        val hasDebits =
            upper.contains("DEBITS")

        val hasCredits =
            upper.contains("CREDITS")

        val hasBalance =
            upper.contains("BALANCE")

        val hasDateColumn =
            Regex(
                """\bDATE\b"""
            ).containsMatchIn(header)

        /*
         * Full layout characteristic of the supported Indian Bank
         * statement format.
         */
        val hasFullIndianBankTable =
            hasAccountActivity &&
            hasTransactionDetails &&
            hasDebits &&
            hasCredits &&
            hasBalance

        /*
         * Alternate table evidence. This allows OCR variations where
         * "ACCOUNT ACTIVITY" or one of the generic column labels is
         * damaged/missing, while still requiring several coordinated
         * statement characteristics.
         */
        val hasIndianBankTransactionTable =
            hasTransactionDetails &&
            hasDebits &&
            hasCredits &&
            hasBalance &&
            hasDateColumn

        /*
         * ------------------------------------------------------------
         * 4. Date evidence
         * ------------------------------------------------------------
         *
         * Indian Bank statements supported by this parser normally use
         * dates such as:
         *
         *   28 Jul 2026
         *
         * Keep the date test independent from bank-name detection so
         * arbitrary documents containing the words "Indian Bank" are
         * not accepted unless they also look like a statement.
         */
        val hasSupportedIndianBankDate =
            Regex(
                """\b\d{1,2}\s+(?:JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)\s+\d{4}\b"""
            ).containsMatchIn(rawText)

        /*
         * ------------------------------------------------------------
         * 5. Strong identity + statement evidence
         * ------------------------------------------------------------
         *
         * A real Indian Bank statement should normally have both:
         *
         *   bank identity
         *   +
         *   transaction/date evidence
         *
         * We intentionally do NOT accept the bank name alone.
         */
        if (hasStrongIndianBankBrand) {
            return hasSupportedIndianBankDate ||
                    hasFullIndianBankTable ||
                    hasIndianBankTransactionTable
        }

        /*
         * ------------------------------------------------------------
         * 6. Layout-only detection
         * ------------------------------------------------------------
         *
         * This supports OCR where the bank logo/name is lost but the
         * distinctive Indian Bank transaction table remains.
         *
         * Requiring several coordinated fields prevents generic PDFs
         * from being classified as Indian Bank.
         */
        if (hasFullIndianBankTable && hasSupportedIndianBankDate) {
            return true
        }

        if (hasIndianBankTransactionTable && hasSupportedIndianBankDate) {
            return true
        }

        return false
    }

    override fun parse(rawText: String): List<Transaction> {

        val blocks = blockBuilder.build(rawText)

        val transactions = mutableListOf<Transaction>()

        val dateRegex =
            Regex("^\\d{1,2}\\s*[A-Za-z]{3}\\s+\\d{4}")

        val dateFormatter =
            SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH)

        for (block in blocks) {

            if (block.lines.isEmpty())
                continue

            val firstLine = block.lines.first()

            val dateMatch =
                dateRegex.find(firstLine) ?: continue

            val date = try {
                dateFormatter.parse(dateMatch.value)
            } catch (e: Exception) {
                null
            } ?: continue

            val parsedAmount =
                amountInterpreter.parse(firstLine)
                    ?: continue

            val allText =
                block.lines.joinToString(" ")

            var rawDescription = allText

            rawDescription =
                rawDescription.replace(dateMatch.value, "")

            rawDescription =
                rawDescription.replaceFirst(
                    Regex("INR\\s*[\\d,]+\\.\\d{2}"),
                    ""
                )

            rawDescription =
                rawDescription.replaceFirst(
                    Regex("INR\\s*[\\d,]+\\.\\d{2}"),
                    ""
                )

            rawDescription = rawDescription.trim()

            val tokens =
                slashTokenizer.tokenize(rawDescription)

            val normalizedTokens =
                tokenNormalizer.normalize(tokens)

            val fields =
                fieldInterpreter.interpret(normalizedTokens)

            val confidence =
                parserConfidenceEngine.evaluate(fields)

            val description =
                displayDescriptionBuilder.build(
                    fields = fields,
                    fallback =
                        descriptionCleaner.clean(rawDescription)
                )

            val isIncome =
                parsedAmount.type == TransactionType.INCOME ||
                        parsedAmount.type == TransactionType.CREDIT

            val category =
                categoryRuleEngine.categorize(
                    description,
                    isIncome
                )

            transactions.add(
                Transaction(
                    amount = parsedAmount.amount,
                    type = parsedAmount.type,
                    description = description,
                    category = category.category,
                    dateTimestamp = date.time,
                    referenceNumber = fields.reference,
                    bankName = "Indian Bank",
                    rawDescription = rawDescription
                )
            )
        }

        return transactions
    }
}
