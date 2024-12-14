package ru.perm.v.camelrest.rest

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import ru.perm.v.camelrest.rest.camel.DemoCtrl
import kotlin.test.assertEquals

@ExtendWith(SpringExtension::class)
@WebMvcTest(DemoCtrl::class)
class DemoCtrlMockMvcTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun checkEchoRequest() {
        val message = mockMvc.perform(MockMvcRequestBuilders.get("/demo/ECHO_MESSAGE"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()

        assertEquals("ECHO_MESSAGE", message.response.contentAsString)
    }
}