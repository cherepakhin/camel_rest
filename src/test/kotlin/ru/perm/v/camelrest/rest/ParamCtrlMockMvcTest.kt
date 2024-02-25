package ru.perm.v.camelrest.rest

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import ru.perm.v.camelrest.config.CamelContainer
import ru.perm.v.camelrest.config.JobParamCopyFile
import ru.perm.v.camelrest.config.MyConfig
import kotlin.test.assertNotNull

@ExtendWith(SpringExtension::class)
@WebMvcTest(ParamCtrl::class)
class ParamCtrlMockMvcTest {

    var camelContainer = CamelContainer(JobParamCopyFile("", ""))

    @MockBean
    lateinit var myConfig: MyConfig

    lateinit var mockMvc: MockMvc

    @BeforeEach
    fun setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(ParamCtrl()).build()
    }

    @Test
    fun checkEchoMessage() {
        val mes = mockMvc.perform(MockMvcRequestBuilders.get("/params/echo/ECHO_MESSAGE"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()

        assertEquals("ECHO_MESSAGE", mes.response.contentAsString)
    }

    @Test
    fun getParamMyConfigTestDirectory() {
        val mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/params/myconfig"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()
        val myConfig = jacksonObjectMapper().readValue(mvcResult.response.contentAsString, MyConfig::class.java)
        assertNotNull(myConfig)
    }

}