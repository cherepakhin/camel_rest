package ru.perm.v.camelrest.rest

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import ru.perm.v.camelrest.rest.error.BadRequestException
import kotlin.test.assertEquals

class EchoCtrlTest {
    @Test
    fun forEmptyMessage() {
        val ctrl = EchoCtrl()

        val excpt = assertThrows<BadRequestException> {
            ctrl.echoStr()
        }

        assertEquals("Message empty.", excpt.message)
    }

    @Test
    fun forMessage() {
        val ctrl = EchoCtrl()

        assertEquals("MESSAGE", ctrl.echoStr("MESSAGE"))
    }
}