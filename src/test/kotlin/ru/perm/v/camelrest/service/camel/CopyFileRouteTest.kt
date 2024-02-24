package ru.perm.v.camelrest.service.camel

import org.apache.camel.builder.RouteBuilder
import org.apache.camel.impl.DefaultCamelContext
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.util.concurrent.TimeUnit

class CopyFileRouteTest {
    /**
     * Copy all files from directory "~/temp/testarea/srcDir"
     * to directory "~/temp/testarea/dstDir".
     * if "~/temp/testarea/srcDir" does not exists, create it.
     * if "~/temp/testarea/dstDir" does not exists, create it.
     * subdirectories will be ignored and don't copied.
     */
    class CopyAllFileFromSrcDirToDstDirRoute(
        private var srcDirectory: String,
        private var dstDirectory: String
    ) : RouteBuilder() {
        init {
            this
        }

        override fun configure() {
            from("file:" + srcDirectory + "?noop=true")
                .to("file:" + dstDirectory +"?flatten=true")
        }
    }

    @Test
    fun runRoute() {
        val srcDirectory = "/home/vasi/temp/testarea/srcDir"
        val dstDirectory = "/home/vasi/temp/testarea/dstDir"

        val dstFilePath1 = "$dstDirectory/file1.txt"
        val dstFilePath2 = "$dstDirectory/file2.txt"
        Files.deleteIfExists(Path.of(dstFilePath1))
        Files.deleteIfExists(Path.of(dstFilePath2))

        val context = DefaultCamelContext()
        context.addRoutes(CopyAllFileFromSrcDirToDstDirRoute(srcDirectory, dstDirectory))
        context.start()
        TimeUnit.SECONDS.sleep(2) // with 1 second timeout not working
        context.stop()

        Assertions.assertTrue(File(dstFilePath1).exists())
        Assertions.assertTrue(File(dstFilePath2).exists())
    }
}
