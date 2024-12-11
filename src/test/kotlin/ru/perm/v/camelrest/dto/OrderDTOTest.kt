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
}