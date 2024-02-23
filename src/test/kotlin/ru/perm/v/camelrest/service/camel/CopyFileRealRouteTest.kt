package ru.perm.v.camelrest.service.camel

import org.apache.camel.builder.RouteBuilder
import org.apache.camel.impl.DefaultCamelContext
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File
import java.nio.file.Files
import java.nio.file.Path

class CopyFileRealRouteTest {
    companion object {
        val dstDirectory = "/home/vasi/temp/testarea/dstDir"
    }

    class CopyFileRoute : RouteBuilder() {
        /**
         * Copy all files from directory "~/temp/testarea/srcDir"
         * to directory "~/temp/testarea/dstDir".
         * if "~/temp/testarea/srcDir" does not exists, create it.
         * if "~/temp/testarea/dstDir" does not exists, create it.
         * subdirectories will be ignored and don't copied.
         */
        override fun configure() {
            from("file:/home/vasi/temp/testarea/srcDir?noop=true")
                .to("file:" + dstDirectory)
        }
    }

    @Test
    fun runRoute() {
        val dstFilePath = "$dstDirectory/file.txt"
        val dstFile = File(dstFilePath)
        Files.deleteIfExists(Path.of(dstFilePath))
        Assertions.assertFalse(dstFile.exists())

        val context = DefaultCamelContext()
        context.addRoutes(CopyFileRoute())
        context.start()
        Thread.sleep(1000);
        context.stop()

        Assertions.assertTrue(dstFile.exists())
    }
}
