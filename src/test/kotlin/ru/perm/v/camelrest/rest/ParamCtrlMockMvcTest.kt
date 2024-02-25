package ru.perm.v.camelrest.rest

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import ru.perm.v.camelrest.config.CamelContainer
import ru.perm.v.camelrest.config.JobParamCopyFile
import ru.perm.v.camelrest.config.MyConfig
import kotlin.test.assertNotNull

@ExtendWith(SpringExtension::class)
@WebMvcTest(ParamCtrl::class)
class ParamCtrlMockMvcTest {

    var camelContainer = CamelContainer(JobParamCopyFile("", ""))

    lateinit var myConfig: MyConfig
//    var myConfig = MyConfig("testDirectory", camelContainer)
//    lateinit var myConfig: MyConfig

    lateinit var mockMvc: MockMvc

    @BeforeEach
    fun setup() {
        MockitoAnnotations.openMocks(this)
        val paramCtrl = ParamCtrl()
        myConfig = MyConfig("testDirectory", camelContainer)
        paramCtrl.myConfig = myConfig
        mockMvc = MockMvcBuilders.standaloneSetup(paramCtrl).build()
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
//        myConfig = MyConfig("testDirectory", CamelContainer(JobParamCopyFile("testDirectory", "")))
        val mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/params/myconfig"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()
        println("--------------")
        val myConfigResult = jacksonObjectMapper().readValue(mvcResult.response.contentAsString, MyConfig::class.java)
        println("--------------${myConfigResult}")
//        println(myConfigResult)
        assertNotNull(myConfigResult)
    }

}