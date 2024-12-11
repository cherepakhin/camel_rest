package ru.perm.v.camelrest.gpt

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class SimpleTest {
    @Test
    fun messageNotEmpty() {
        val str ="STRING"

        assertNotEquals("", str)
        assertEquals("STRING", str)
    }
}