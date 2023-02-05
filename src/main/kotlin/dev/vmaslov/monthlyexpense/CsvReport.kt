package dev.vmaslov.monthlyexpense

import java.math.BigDecimal

class CsvReport(private val reportProperties: ReportProperties) : Report {

    override fun transactions(): List<Transaction> {
        val file = CsvFile(reportProperties.csvFilePath())

        val ignoringPatterns = reportProperties.transactionIgnoringPatterns()
        val csvFileContent = file.content()

        // type p
        if (reportProperties.reportType() == "p") {
            return csvFileContent.subList(1, csvFileContent.size).map {
                val split = it.split(reportProperties.delimiter())
                Transaction(BigDecimal(unquoted(split[3])), split[5], split[6], split[7])
            }.filter {
                for (pattern in ignoringPatterns) {
                    if (it.agent.contains(pattern) || it.description.contains(pattern) || it.details.contains(pattern))
                        return@filter false
                }
                true
            }
        }
        return csvFileContent.subList(20, csvFileContent.size).filter {
            val split = it.split(reportProperties.delimiter())
            !split[8].isEmpty() && !split[8].isBlank()
        }.map {
            val split = it.split(reportProperties.delimiter())
            Transaction(BigDecimal(unquoted(split[8].replace(",", "."))), split[3], split[2], split[14])
        }.filter {
            for (pattern in ignoringPatterns) {
                if (it.agent.contains(pattern) || it.description.contains(pattern) || it.details.contains(pattern))
                    return@filter false
            }
            true
        }
    }

    private fun unquoted(s: String) = s.replace("\"", "")
}
