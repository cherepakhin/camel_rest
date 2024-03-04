package ru.perm.v.camelrest.service.camel

import org.apache.camel.builder.NotifyBuilder
import org.apache.camel.builder.RouteBuilder
import org.apache.camel.impl.DefaultCamelContext
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.util.concurrent.TimeUnit


class CamelContainerJobParamCopyFileRouteTest {
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
        override fun configure() {
            from("file:$srcDirectory?noop=true")
                .to("file:$dstDirectory?flatten=true")
        }
    }

    @Test
    fun runRoute() {
        val srcDirectory = "temp/testarea/srcDir"
        val dstDirectory = "temp/testarea/dstDir"

        val file1 = File("$srcDirectory/file1.txt")
        if(!file1.exists()) {
            file1.createNewFile()
        }
        val file2 = File("$srcDirectory/file2.txt")
        if(!file2.exists()) {
            file2.createNewFile()
        }

        val dstFilePath1 = "$dstDirectory/file1.txt"
        val dstFilePath2 = "$dstDirectory/file2.txt"
        Files.deleteIfExists(Path.of(dstFilePath1))
        Files.deleteIfExists(Path.of(dstFilePath2))

        val context = DefaultCamelContext()
// ждать одно задание "whenDone(1)"
        val notify = NotifyBuilder(context).whenDone(1).create()
        context.addRoutes(CopyAllFileFromSrcDirToDstDirRoute(srcDirectory, dstDirectory))
        context.start()
// ждать 2 секунды или пока не выполнится 1 задание см. выше ....whenDone(1)...
        val matches = notify.matches(2, TimeUnit.SECONDS)
        Assertions.assertTrue(matches)
// или тупо так сделать задержку, вместо
// val notify = NotifyBuilder(context).whenDone(1).create()
// val matches = notify.matches(2, TimeUnit.SECONDS)
        TimeUnit.SECONDS.sleep(2) // wait 2 sec. With 1 second timeout not working
        context.stop()

        Assertions.assertTrue(File(dstFilePath1).exists())
        Assertions.assertTrue(File(dstFilePath2).exists())

        Files.deleteIfExists(file1.toPath())
        Files.deleteIfExists(file2.toPath())
    }

    @Test
    fun createFile() {
        val srcDirectory = "temp/testarea/srcDir"
        val file = File("$srcDirectory/file1.txt")
        if(!file.exists()) {
            file.createNewFile()
        }
        Assertions.assertTrue(file.exists());
        Files.deleteIfExists(file.toPath())
    }
}
