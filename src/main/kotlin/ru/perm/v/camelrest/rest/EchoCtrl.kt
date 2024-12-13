package ru.perm.v.camelrest.rest

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.perm.v.camelrest.rest.error.BadRequestException

@RestController
@RequestMapping("/demo")
class EchoCtrl {
    private val logger = LoggerFactory.getLogger(this.javaClass.name)
    private var counter = 0L


    // http http://127.0.0.1:8980/camel_rest/api/demo/e
    @GetMapping("/e")
    fun echoStr() {
        throwEmptyMessage()
    }

    // http http://127.0.0.1:8980/camel_rest/api/demo/MESSAGE
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
        logger.error("Message empty.")
        throw BadRequestException("Message empty.")
    }

}