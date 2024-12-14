package ru.perm.v.camelrest.rest.camel

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class DemoCtrlTest {

    @Test
    fun echo() {
        val ctrl = DemoCtrl()

        assertEquals("MESSAGE", ctrl.echo("MESSAGE"))
    }
}