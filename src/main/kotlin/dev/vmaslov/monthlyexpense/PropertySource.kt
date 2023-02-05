package dev.vmaslov.monthlyexpense

interface PropertySource {

    fun properties(): Map<String, String>
}