package dev.vmaslov.monthlyexpense

import java.math.BigDecimal

class Categories(
    val properties: Properties,
    val transactions: List<Transaction>
) {
    fun expenses(): Map<String, BigDecimal> {
        return transactions.filter { it.quote < BigDecimal.ZERO }.groupBy { categorize(it) }
            .mapValues { it.value.sumOf { t -> t.quote } }
            .toMap()
    }

    private fun categorize(transaction: Transaction): String {
        val groupsDefinitions = properties.groupsDefinitions()
        val element = groupsDefinitions.entries.toList()
            .find {
                val patterns = it.value.split(",")
                for (pattern in patterns) {
                    if (transaction.description.contains(pattern) || transaction.agent.contains(pattern)
                        || transaction.details.contains(pattern)
                    ) {
                        return@find true
                    }
                }
                return@find false
            }
        return element?.key ?: "Other"
    }
}