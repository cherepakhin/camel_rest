package ru.perm.v.camelrest.rest

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.perm.v.camelrest.config.CamelContainer
import ru.perm.v.camelrest.config.MyConfig


/**
 * Rest controller for reading params from application.yaml
 */
@RestController
@RequestMapping("/params")
class ParamCtrl {
    private val logger = LoggerFactory.getLogger(this.javaClass.name)

    @Autowired
    lateinit var myConfig: MyConfig

    @GetMapping("/echo/{message}")
    fun echo(@PathVariable("message") message: String): String {
        return message
    }

    /**
     * Get config param from application.yaml
     */
    @GetMapping("/myconfig")
    fun getParamMyConfigTestDirectory(): MyConfig {
        logger.info("GET param \"myConfig.testDirectory\" = ${myConfig.testDirectory}")
        return myConfig
    }


}