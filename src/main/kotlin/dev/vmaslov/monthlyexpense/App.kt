package dev.vmaslov.monthlyexpense

import com.fasterxml.jackson.databind.ObjectMapper
import java.util.logging.Logger

class App {

    val log = Logger.getLogger(this.javaClass.name)
    val objectMapper = ObjectMapper().writerWithDefaultPrettyPrinter()

    fun run(args: Array<String>) {
        log.info("Running Monthly Expense Report...")

        val properties = Properties(args.get(0))
        val monthlyReport = Reports(properties).report()

        log.info("Getting transactions...")
        val transactions = monthlyReport.transactions()
        log.info("Read ${transactions.size} transactions")

        val categories = Categories(properties, transactions)
        val json = objectMapper.writeValueAsString(categories.expenses())


        log.info(json)

    }
}