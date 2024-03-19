package ru.perm.v.camelrest.camel

import org.apache.camel.CamelContext
import org.apache.camel.LoggingLevel
import org.apache.camel.ProducerTemplate
import org.apache.camel.builder.RouteBuilder
import org.apache.camel.impl.DefaultCamelContext
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory


class CamelDirectTest {
    private val logger = LoggerFactory.getLogger(this.javaClass.name)

    @Test
    fun simpleTest() {
        logger.info("START CamelDirectTest.simpleTest")
        val context: CamelContext = DefaultCamelContext()
        val template: ProducerTemplate = context.createProducerTemplate()
        val route: RouteBuilder = object : RouteBuilder() {
            @Throws(Exception::class)
            override fun configure() {
                from("direct:start")
//               .log(LoggingLevel.INFO, logger, "BODY: ${bodyAs(String::class.java)}") // "BODY: simple{This is test message}"
//               .log(LoggingLevel.INFO, logger,"BODY: ${SimpleExpression("\${body}")}") // BODY: simple{******************This is test message******************}
                    .log(
                        LoggingLevel.INFO,
                        logger,
                        "BODY: \${body}"
                    ) // OK! ******************This is test message******************
                    .end()
            }
        }
        context.addRoutes(route)
        context.start()
        template.sendBody("direct:start", "******************This is test message******************")
        context.stop()
//        val route: RouteBuilder = object : RouteBuilder() {
//            @Throws(Exception::class)
//            override fun configure() {
//                // Опция `noop=true` означает, что файлы не будут удалены после обработки.
//                from("direct:start")
//                    .log(LoggingLevel.INFO, logger, "BODY: ${body().toString().substring(7)}") // trim prefix "simple{"
//                    .to("file://$testDirectory$dstDir")
//            }
//        }
//        ctx.addRoutes(route)
//        ctx.start()
//
//        // Only for test. Time for copy files. 1 second may not be not enough.
//        // In real application not needed.
//        // Context starting on boot application and not stopped until shutdown.
//        // Bad idea.
//        Thread.sleep(3000);
//        ctx.stop()

    }
}