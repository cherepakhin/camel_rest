package ru.perm.v.camelrest.dto

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class OrderDTOTest {
    @Test
    fun createWithDefaultValues() {
        val orderDTO = OrderDTO()

        assertEquals(-1L, orderDTO.id)
        assertEquals("", orderDTO.name)
        assertEquals(BigDecimal.ZERO, orderDTO.price)
    }

    @Test
    fun constructorWithVals() {
        val orderDTO = OrderDTO(10L, "NAME", BigDecimal(10.00))

        assertEquals(10, orderDTO.id)
        assertEquals("NAME", orderDTO.name)
        assertEquals(BigDecimal(10.00), orderDTO.price)
    }
}