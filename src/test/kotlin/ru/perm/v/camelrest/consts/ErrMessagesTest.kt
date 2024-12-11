package ru.perm.v.camelrest.consts

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ErrMessagesTest {
    @Test
    fun constructorTest() {
        assertEquals("not found", ErrMessages.NOT_FOUND)
    }
}