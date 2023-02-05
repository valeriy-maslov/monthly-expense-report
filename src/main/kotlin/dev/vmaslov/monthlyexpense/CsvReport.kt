package dev.vmaslov.monthlyexpense

import java.math.BigDecimal

class CsvReport(private val reportProperties: ReportProperties) : Report {

    override fun transactions(): List<Transaction> {

        // Properties aliases
        val quoteIdx = reportProperties.quoteColumn()
        val descriptionIdx = reportProperties.descriptionColumn()
        val agentIdx = reportProperties.agentColumn()
        val detailsIdx = reportProperties.detailsColumn()
        val delimiter = reportProperties.delimiter()

        val csvFileContent = CsvFile(reportProperties.csvFilePath()).content()
        val allTransactions =
            csvFileContent.subList(reportProperties.headerLines(), csvFileContent.size)
                // Some reports may have currency conversion records represented by row with an empty quota
                .filter { it.split(delimiter)[quoteIdx].isNotEmpty() }
                .map {
                    val reportLine = it.split(delimiter)
                    Transaction(
                        BigDecimal(normalise(reportLine[quoteIdx])),
                        reportLine[descriptionIdx],
                        reportLine[agentIdx],
                        reportLine[detailsIdx]
                    )
                }
        return IgnoringTransactionFilter(reportProperties, allTransactions).items()
    }

    // Some reports have double quotes and/or commas in numbers
    private fun normalise(s: String) = s.replace("\"", "").replace(",", ".")
}
