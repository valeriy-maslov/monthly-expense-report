package dev.vmaslov.monthlyexpense

import com.fasterxml.jackson.databind.ObjectMapper
import java.util.logging.Logger

class App {

    private val log = Logger.getLogger(this.javaClass.name)
    private val objectMapper = ObjectMapper().writerWithDefaultPrettyPrinter()

    fun run(args: Array<String>) {
        log.info("Running Monthly Expense Report...")

        val reportPropertiesFile = PropertyFile(args[0])
        val reportProperties = ReportProperties(reportPropertiesFile)
        val monthlyReport = Reports(reportProperties).report()

        log.fine("Getting transactions...")
        val transactions = monthlyReport.transactions()
        log.info("Read ${transactions.size} transactions")

        val categories = Categories(reportProperties, transactions)
        val json = objectMapper.writeValueAsString(categories.expenses())
        log.info(json)
    }
}