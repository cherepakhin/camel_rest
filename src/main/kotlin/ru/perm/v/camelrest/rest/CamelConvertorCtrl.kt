package ru.perm.v.camelrest

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/camel")
class CamelConvertorCtrl {
    private val logger = LoggerFactory.getLogger(this.javaClass.name)
    private var counter = 0L

    @GetMapping("/echo/{mes}")
    fun echoStr(
        @PathVariable("mes")
        mes: String
    ): String {
        logger.info("$counter GET $mes")
        return mes
    }
}