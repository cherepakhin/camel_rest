package ru.perm.v.camelrest.rest.camel

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
        logger.info("$counter GET copy_file")
        camelCopyFileRoute!!.start()
//        val context = DefaultCamelContext()
//        context.addRoutes(camelCopyFileRoute)
//        //TODO: Все сдужебные мероприятия по поднятию контекста, route и т.д. перенести в Spring
//        context.start()
//        camelCopyFileRoute.let { context.stop() }
//        // добавил метод sleep, чтобы дать приложению Camel время на копирование файлов https://habr.com/ru/companies/redhatrussia/articles/352188/
//        Thread.sleep(2000); // 1 секунда мало!!!
//        context.stop()
    }
}