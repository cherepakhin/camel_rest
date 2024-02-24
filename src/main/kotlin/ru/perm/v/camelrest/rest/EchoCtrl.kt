package ru.perm.v.camelrest.rest

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.perm.v.camelrest.config.MyConfig

@RestController
@RequestMapping("/echo")
class EchoCtrl {
    private val logger = LoggerFactory.getLogger(this.javaClass.name)
    private var counter = 0L

    @Autowired
    lateinit var myConfig: MyConfig

    @GetMapping("/{mes}")
    fun echoStr(
        @PathVariable("mes")
        mes: String
    ): String {
        logger.info("$counter GET $mes")
        return mes
    }

    /**
     * Get config param from application.yaml
     */
    @GetMapping("/test_directory")
    fun testDirectory(): String? {
        return myConfig.testDirectory
    }

}