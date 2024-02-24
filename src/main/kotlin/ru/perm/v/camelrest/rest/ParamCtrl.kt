package ru.perm.v.camelrest.rest

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.perm.v.camelrest.config.MyConfig

@RestController
@RequestMapping("/params")
class ParamCtrl {
    private val logger = LoggerFactory.getLogger(this.javaClass.name)

    @Autowired
    lateinit var myConfig: MyConfig

    /**
     * Get config param from application.yaml
     */
    @GetMapping("/test_directory")
    fun getTestDirectory(): String? {
        logger.info("GET param \"myConfig.testDirectory\" = ${myConfig.testDirectory}")
        return myConfig.testDirectory
    }
}