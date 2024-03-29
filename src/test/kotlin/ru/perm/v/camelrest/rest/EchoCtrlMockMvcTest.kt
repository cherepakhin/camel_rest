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

@ExtendWith(SpringExtension::class)
@WebMvcTest(EchoCtrl::class)
class EchoCtrlMockMvcTest {

    @Autowired
    lateinit private var mockMvc: MockMvc

    @Test
    fun checkEchoMessage() {
        val mes = mockMvc.perform(MockMvcRequestBuilders.get("/echo/ECHO_MESSAGE"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()

        Assertions.assertEquals("ECHO_MESSAGE", mes.response.contentAsString)
    }
}