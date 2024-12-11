package ru.perm.v.camelrest.dto

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import kotlin.test.assertNotEquals

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

    @Test
    fun equalsObj() {
        val orderDTO1 = OrderDTO(10L, "NAME", BigDecimal(10.00))
        val orderDTO2 = OrderDTO(10L, "NAME", BigDecimal(10.00))

        assertEquals(orderDTO1, orderDTO2)
    }

    @Test
    fun notEqualsById() {
        val orderDTO1 = OrderDTO(11L, "NAME", BigDecimal(10.00))
        val orderDTO2 = OrderDTO(12L, "NAME", BigDecimal(10.00))

        assertNotEquals(orderDTO1, orderDTO2)
    }

    @Test
    fun notEqualsByName() {
        val orderDTO1 = OrderDTO(10L, "NAME", BigDecimal(10.00))
        val orderDTO2 = OrderDTO(10L, "NAME2", BigDecimal(10.00))

        assertNotEquals(orderDTO1, orderDTO2)
    }

    @Test
    fun notEqualsByPrice() {
        val orderDTO1 = OrderDTO(10L, "NAME", BigDecimal(10.00))
        val orderDTO2 = OrderDTO(10L, "NAME", BigDecimal(11.00))

        assertNotEquals(orderDTO1, orderDTO2)
    }

    @Test
    fun hashCodeTest() {
        val orderDTO1 = OrderDTO(11L, "NAME", BigDecimal(10.00))
        val orderDTO2 = OrderDTO(11L, "NAME", BigDecimal(10.00))

        assertEquals(orderDTO1.hashCode(), orderDTO2.hashCode())
    }
}