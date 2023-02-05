package dev.vmaslov.monthlyexpense

import java.math.BigDecimal

data class Transaction(
    val quote: BigDecimal,
    val description: String,
    val agent: String,
    val details: String
)
