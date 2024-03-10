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
import ru.perm.v.camelrest.camel.CamelCopyFileRoute
import ru.perm.v.camelrest.config.MyConfig
import ru.perm.v.camelrest.rest.camel.CamelConvertorCtrl

@ExtendWith(SpringExtension::class)
@WebMvcTest(CamelConvertorCtrl::class, CamelCopyFileRoute::class, MyConfig::class)
class CamelContainerConvertorCtrlMockMvcTest {

    @Autowired
    lateinit private var mockMvc: MockMvc

    @Test
    fun checkEchoMessage() {
        val mes = mockMvc.perform(MockMvcRequestBuilders.get("/camel/echo/ECHO_MESSAGE"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()

        Assertions.assertEquals("ECHO_MESSAGE", mes.response.contentAsString)
    }
}