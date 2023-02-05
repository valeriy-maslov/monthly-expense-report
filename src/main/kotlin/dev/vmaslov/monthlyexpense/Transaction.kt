package dev.vmaslov.monthlyexpense

import java.math.BigDecimal

data class Transaction(
    val quote: BigDecimal,
    val description: String,
    val agent: String,
    val details: String
) {

    fun hasPattern(pattern: String): Boolean {
        return this.agent.contains(pattern) || this.description.contains(pattern) || this.details.contains(pattern)
    }
}

