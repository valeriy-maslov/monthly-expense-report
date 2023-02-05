package dev.vmaslov.monthlyexpense

import java.io.File

class CsvFile(private val path: String) {

    fun content(): List<String> = File(path).readLines()

}
