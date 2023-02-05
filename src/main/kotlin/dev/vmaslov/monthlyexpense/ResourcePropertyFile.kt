package dev.vmaslov.monthlyexpense

class ResourcePropertyFile(
    private val path: String
) : PropertySource {

    override fun properties(): Map<String, String> {
        val lines = ReportProperties::class.java.getResource("/$path")!!.readText().lines()
        return lines.filter { it.isNotBlank() }.associate {
            val split = it.split("=")
            Pair(split[0], split[1])
        }
    }

}
