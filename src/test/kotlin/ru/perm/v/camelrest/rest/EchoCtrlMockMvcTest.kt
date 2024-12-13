package ru.perm.v.camelrest.rest

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import kotlin.test.assertEquals

@ExtendWith(SpringExtension::class)
@WebMvcTest(EchoCtrl::class)
class EchoCtrlMockMvcTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    /**
     * Test REST echo controller, check message
     */
    @Test
    fun checkEchoMessage() {
        val mes = mockMvc.perform(MockMvcRequestBuilders.get("/echo/ECHO_MESSAGE"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()
        Assertions.assertEquals("ECHO_MESSAGE", mes.response.contentAsString)
    }

    @Test
    fun checkEmptyMessage() {
        val result = mockMvc.perform(MockMvcRequestBuilders.get("/echo"))
            .andExpect(MockMvcResultMatchers.status().is5xxServerError)
            .andReturn()
        assertEquals("Message empty.", result.resolvedException.message)
    }

    @Test
    fun checkEmptyMessageWithSlash() {
        val result = mockMvc.perform(MockMvcRequestBuilders.get("/echo/"))
            .andExpect(MockMvcResultMatchers.status().is5xxServerError)
            .andReturn()
        assertEquals("Message empty.", result.resolvedException.message)
    }
}