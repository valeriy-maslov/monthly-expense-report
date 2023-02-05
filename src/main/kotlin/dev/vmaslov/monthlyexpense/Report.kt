package dev.vmaslov.monthlyexpense

interface Report {

    fun transactions(): List<Transaction>

}
