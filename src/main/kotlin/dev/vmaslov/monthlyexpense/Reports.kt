package dev.vmaslov.monthlyexpense

class Reports(val properties: Properties) {

    fun report(): Report = CsvReport(properties)

}
