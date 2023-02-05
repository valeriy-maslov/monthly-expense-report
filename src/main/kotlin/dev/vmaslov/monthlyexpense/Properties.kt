package dev.vmaslov.monthlyexpense

class Properties(val path: String) {

    private val properties = hashMapOf<String, String>()

    fun property(name: String): String {
        if (properties.isEmpty()) {
            parse()
        }
        return properties[name]!!
    }

    private fun parse() {
        val lines = Properties::class.java.getResource("/$path")!!.readText().lines()
        lines.filter { it.isNotBlank() }.forEach {
            val split = it.split("=")
            properties[split[0]] = split[1]
        }
    }

    fun groupsDefinitions(): Map<String, String> {
        return properties.filter { it.key.startsWith("group") }
            .mapKeys { it.key.split(".")[1] }
    }


}
