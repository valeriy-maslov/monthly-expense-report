package dev.vmaslov.monthlyexpense

class IgnoringTransactionFilter(
    private val properties: ReportProperties,
    private val transactions: List<Transaction>
) : Filter<Transaction> {

    override fun items(): List<Transaction> {
        return transactions.filter {
            !properties.transactionIgnoringPatterns().any { pattern -> it.hasPattern(pattern) }
        }
    }
}