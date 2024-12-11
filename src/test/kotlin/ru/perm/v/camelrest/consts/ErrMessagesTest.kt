package ru.perm.v.camelrest.consts

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ErrMessagesTest {
    @Test
    fun constValNOT_FOUNDTest() {
        assertEquals("not found", ErrMessages.NOT_FOUND)
    }
}