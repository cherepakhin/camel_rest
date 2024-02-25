package ru.perm.v.camelrest.rest

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ParamControllerSpringTest(@Autowired private val paramCtrl: ParamCtrl) {
    @Test
    fun checkEchoMessage() {
        Assertions.assertEquals("ECHO_MESSAGE", paramCtrl.echo("ECHO_MESSAGE"))
    }
}
