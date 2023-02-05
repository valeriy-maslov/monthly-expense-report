package dev.vmaslov.monthlyexpense

class ReportProperties(private val source: PropertySource) {

    fun groupsDefinitions(): Map<String, String> {
        return source.properties().filter { it.key.startsWith("group") }
            .mapKeys { it.key.split(".")[1] }
    }

    fun transactionIgnoringPatterns(): List<String> {
        return source.properties()["ignore"]!!.split(",")
    }

    fun csvFilePath(): String {
        return source.properties()["csv-report"]!!
    }

    fun delimiter(): String {
        return source.properties()["delimiter"]!!
    }

    fun headerLines(): Int {
        return source.properties()["header-lines"]!!.toInt()
    }

    fun quoteColumn(): Int {
        return source.properties()["transaction-column-mapping"]!!.split(",")[0].toInt()
    }

    fun descriptionColumn(): Int {
        return source.properties()["transaction-column-mapping"]!!.split(",")[1].toInt()
    }

    fun agentColumn(): Int {
        return source.properties()["transaction-column-mapping"]!!.split(",")[2].toInt()
    }

    fun detailsColumn(): Int {
        return source.properties()["transaction-column-mapping"]!!.split(",")[3].toInt()
    }

}
