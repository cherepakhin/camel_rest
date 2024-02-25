package ru.perm.v.camelrest.rest

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@SpringBootTest
class ParamCtrlSpringBootTest(@Autowired private val paramCtrl: ParamCtrl) {
    @Test
    fun checkEchoMessage() {
        Assertions.assertEquals("ECHO_MESSAGE", paramCtrl.echo("ECHO_MESSAGE"))
    }

    @Test
    fun getParamMyConfigTestDirectory() {
        val myConfig = paramCtrl.getParamMyConfigTestDirectory()

        assertNotNull(myConfig)
        assertEquals("file:temp/testarea", myConfig.camelContainer.jobParamCopyFile.dstDirectory)
    }

    @Test
    fun getParamMyConfigCamelContainer() {
        val myConfig = paramCtrl.getParamMyConfigTestDirectory()

        assertNotNull(myConfig)
        assertNotNull(myConfig.camelContainer)
        assertNotNull(myConfig.camelContainer.jobParamCopyFile)

        assertEquals("file:temp/testarea/srcDir",
            myConfig.camelContainer.jobParamCopyFile.srcDirectory)
        assertEquals("file:temp/testarea/dstDir",
            myConfig.camelContainer.jobParamCopyFile.dstDirectory)
    }
}
