package ru.perm.v.camelrest.camel

import org.apache.camel.CamelContext
import org.apache.camel.LoggingLevel
import org.apache.camel.builder.RouteBuilder
import org.apache.camel.impl.DefaultCamelContext
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import java.io.File
import java.io.PrintWriter


class CamelCopyFileTest {
    private val logger = LoggerFactory.getLogger(this.javaClass.name)

    @Test
    fun copyDirectory() {
        logger.info("START copyDirectory")
        val testDirectory = "/home/vasi/temp/testarea/copyfile"
        val srcDir = "/srcDir"
        val dstDir = "/dstDir"

        // generate test files
        val countFIles = 3
        for (i in 1..countFIles) {
            val f = File("$testDirectory$srcDir/file$i.txt")
            PrintWriter(f).use { out -> out.println("===================== content file$i") }
        }

        val ctx: CamelContext = DefaultCamelContext()
        val route: RouteBuilder = object : RouteBuilder() {
            @Throws(Exception::class)
            override fun configure() {
                // Опция `noop=true` означает, что файлы не будут удалены после обработки.
                from("file://$testDirectory$srcDir?noop=true")
                    .log(LoggingLevel.INFO, logger, "BODY: ${body().toString().substring(7)}") // trim prefix "simple{"
                    .to("file://$testDirectory$dstDir")
            }
        }
        ctx.addRoutes(route)
        ctx.start()

        // Only for test. Time for copy files. 1 second may not be not enough.
        // In real application not needed.
        // Context starting on boot application and not stopped until shutdown.
        // Bad idea.
        Thread.sleep(3000);
        ctx.stop()
    }
}