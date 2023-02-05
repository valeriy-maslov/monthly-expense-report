package dev.vmaslov.monthlyexpense

import java.math.BigDecimal

class Categories(
    private val reportProperties: ReportProperties,
    private val transactions: List<Transaction>
) {
    fun expenses(): Map<String, BigDecimal> {
        return transactions.filter { it.quote < BigDecimal.ZERO }
            .groupBy { CategorizedTransaction(reportProperties, it).category() }
            .mapValues { it.value.sumOf { t -> t.quote } }
    }

}