package ru.perm.v.camelrest.service.impl

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class ConvertorServiceImplTest {

    @Test
    fun echo() {
        val service = ConvertorServiceImpl()
        val result = service.echo("Hello")

        assertEquals("Hello", result)
    }
}