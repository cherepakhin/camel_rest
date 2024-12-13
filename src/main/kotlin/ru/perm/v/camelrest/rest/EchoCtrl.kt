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

    @GetMapping("")
    fun echoStr(): Unit {
            logger.error("Message empty.")
            throw BadRequestException("Message empty.")
    }

    @GetMapping("/{mes}")
    fun echoStr(
        @PathVariable("mes")
        mes: String
    ): String {
        if (mes.isEmpty()) {
            logger.error("Message empty.")
            throw BadRequestException("Message empty.")
        }
        logger.info("$counter GET $mes")
        return mes
    }
}