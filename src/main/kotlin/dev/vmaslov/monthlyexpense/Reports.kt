package dev.vmaslov.monthlyexpense

class Reports(private val reportProperties: ReportProperties) {

    fun report(): Report = CsvReport(reportProperties)

}
