package dev.vmaslov.monthlyexpense

import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class PropertiesTest {

    @Test
    fun property() {
        val properties = Properties("test.properties")

        assertEquals("xyz", properties.property("abc"))
    }
}
