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

    fun reportType(): String {
        return source.properties()["rtype"]!!
    }

    fun delimiter(): String {
        return source.properties()["delimiter"]!!
    }

    fun headerLines(): Int {
        return source.properties()["header-lines"]!!.toInt()
    }

}
