package ru.perm.v.camelrest.camel

import org.apache.camel.CamelContext
import org.apache.camel.builder.RouteBuilder
import org.apache.camel.impl.DefaultCamelContext
import org.junit.jupiter.api.Test
import java.io.File


class CamelCopyFileTest {
    @Test
    fun copyDirectory() {
        val testDirectory = "/home/vasi/temp/testarea/copyfile"
        val srcDir = "/srcDir"
        val dstDir = "/dstDir"
        val fileName = "/file1.txt"
        val file1 = File(testDirectory + srcDir + fileName)
        if (!file1.exists()) {
            file1.createNewFile()
        }

        val ctx: CamelContext = DefaultCamelContext()
        val route: RouteBuilder = object : RouteBuilder() {
            @Throws(Exception::class)
            override fun configure() {
                from("file://" + testDirectory + srcDir+"?noop=true")
                    .to("file://" + testDirectory + dstDir)
            }
        }
        ctx.addRoutes(route)
        ctx.start()
        Thread.sleep(10000); // time for copy files. 1 second may not be not enough.
        ctx.stop()
    }
}