package dev.vmaslov.monthlyexpense

import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class ReportPropertiesTest {

    @Test
    fun property() {
        val reportProperties = ReportProperties("test.properties")

        assertEquals("xyz", reportProperties.property("abc"))
    }
}
