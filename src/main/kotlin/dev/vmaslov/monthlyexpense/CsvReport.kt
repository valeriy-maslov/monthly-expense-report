package dev.vmaslov.monthlyexpense

import java.math.BigDecimal

class CsvReport(private val reportProperties: ReportProperties) : Report {

    override fun transactions(): List<Transaction> {
        val csvFileContent = CsvFile(reportProperties.csvFilePath()).content()
        val subList = csvFileContent.subList(reportProperties.headerLines(), csvFileContent.size)

        // type p
        if (reportProperties.reportType() == "p") {
            val allTransactions = subList.map {
                val split = it.split(reportProperties.delimiter())
                Transaction(BigDecimal(unquoted(split[3])), split[5], split[6], split[7])
            }
            return IgnoringTransactionFilter(reportProperties, allTransactions).items()
        }
        val IallTransactions = subList.filter {
            val split = it.split(reportProperties.delimiter())
            !split[8].isEmpty() && !split[8].isBlank()
        }.map {
            val split = it.split(reportProperties.delimiter())
            Transaction(BigDecimal(unquoted(split[8].replace(",", "."))), split[3], split[2], split[14])
        }
        return IgnoringTransactionFilter(reportProperties, IallTransactions).items()
    }

    private fun unquoted(s: String) = s.replace("\"", "")
}
