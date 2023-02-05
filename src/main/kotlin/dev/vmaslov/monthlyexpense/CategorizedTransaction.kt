package dev.vmaslov.monthlyexpense

class CategorizedTransaction(
    private val properties: ReportProperties,
    private val transaction: Transaction
) {

    fun category(): String {
        return properties.groupsDefinitions().entries.toList()
            .flatMap { group ->
                group.value.split(",")
                    .map {
                        Pair(group.key, it)
                    }
            }
            .find {
                transaction.hasPattern(it.second)
            }?.first ?: "Other"
    }


}