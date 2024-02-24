package ru.perm.v.camelrest.rest

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import ru.perm.v.camelrest.config.MyConfig

@ExtendWith(SpringExtension::class)
@WebMvcTest(ParamCtrl::class, MyConfig::class)
class ParamCtrlMockMvcTest {

    @Autowired
    lateinit private var mockMvc: MockMvc

    @Test
    fun getTestDirectory() {
        val mes = mockMvc.perform(MockMvcRequestBuilders.get("/params/test_directory"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()

        assertEquals("file:~/temp/testarea", mes.response.contentAsString)
    }
}