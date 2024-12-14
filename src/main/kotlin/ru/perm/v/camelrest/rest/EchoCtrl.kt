package ru.perm.v.camelrest.rest

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.perm.v.camelrest.rest.error.BadRequestException

@RestController
@RequestMapping("/echo")
class EchoCtrl {
    private val logger = LoggerFactory.getLogger(this.javaClass.name)
    private var counter = 0L

    // for demo: http http://127.0.0.1:8980/camel_rest/api/echo
    // for demo: http http://127.0.0.1:8980/camel_rest/api/echo/
    @GetMapping("")
    fun badRequest() {
        throwEmptyMessage()
    }

    // for demo: http http://127.0.0.1:8980/camel_rest/api/echo/MESSAGE
    @GetMapping("/{mes}")
    fun echoStr(
        @PathVariable("mes")
        mes: String
    ): String {
        if (mes.isEmpty()) {
            throwEmptyMessage()
        }
        logger.info("$counter GET $mes")
        return mes
    }

    fun throwEmptyMessage() {
        val errorMessage = "Message empty."
        logger.error(errorMessage)
        throw BadRequestException(errorMessage)
    }
}