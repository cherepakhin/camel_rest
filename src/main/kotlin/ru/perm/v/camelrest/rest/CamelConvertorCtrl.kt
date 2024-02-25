package ru.perm.v.camelrest

import org.apache.camel.impl.DefaultCamelContext
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.perm.v.camelrest.camel.CamelCopyFileRoute

@RestController
@RequestMapping("/camel")
class CamelConvertorCtrl {
    private val logger = LoggerFactory.getLogger(this.javaClass.name)

    @Autowired
    var camelCopyFileRoute: CamelCopyFileRoute? = null

    private var counter = 0L

    @GetMapping("/echo/{mes}")
    fun echo(
        @PathVariable("mes")
        mes: String
    ): String {
        logger.info("$counter GET $mes")
        return mes
    }

    @GetMapping("/copy_file")
    fun copyFile() {
        val context = DefaultCamelContext()
        context.addRoutes(camelCopyFileRoute)
        context.start()
        // добавил метод sleep, чтобы дать приложению Camel время на копирование файлов https://habr.com/ru/companies/redhatrussia/articles/352188/
        Thread.sleep(2000);
        context.stop()
    }
}