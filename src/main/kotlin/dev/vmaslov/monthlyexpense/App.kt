package dev.vmaslov.monthlyexpense

import com.fasterxml.jackson.databind.ObjectMapper
import java.util.logging.Logger

class App {

    private val log = Logger.getLogger(this.javaClass.name)
    private val objectMapper = ObjectMapper().writerWithDefaultPrettyPrinter()

    fun run(args: Array<String>) {
        log.info("Running Monthly Expense Report...")

        val transactions = Reports(
            ReportProperties(
                ResourcePropertyFile(args[0])
            )
        ).report().transactions()
        log.info("Read ${transactions.size} transactions")

        val categories = Categories(ReportProperties(ResourcePropertyFile(args[0])), transactions)
        val json = objectMapper.writeValueAsString(categories.expenses())
        log.info(json)
    }
}