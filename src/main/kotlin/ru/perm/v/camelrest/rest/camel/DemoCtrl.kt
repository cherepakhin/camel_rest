package ru.perm.v.camelrest.rest.camel

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/demo")
class DemoCtrl {

    private val logger = LoggerFactory.getLogger(this.javaClass.name)

    @GetMapping("/{message}")
    fun echo(
        @PathVariable("message")
        message: String,
    ): String {
        logger.info("GET /demo/$message")
        return message
    }

}