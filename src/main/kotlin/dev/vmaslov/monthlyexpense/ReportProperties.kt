package dev.vmaslov.monthlyexpense

class ReportProperties(private val source: PropertySource) {

    fun property(name: String): String {
        return source.properties()[name]!!
    }

    fun groupsDefinitions(): Map<String, String> {
        return source.properties().filter { it.key.startsWith("group") }
            .mapKeys { it.key.split(".")[1] }
    }


}
