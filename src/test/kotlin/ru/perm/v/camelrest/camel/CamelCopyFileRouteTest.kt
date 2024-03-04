package ru.perm.v.camelrest.camel

import org.apache.camel.impl.DefaultCamelContext
import org.junit.jupiter.api.Test
import ru.perm.v.camelrest.config.CamelContainer
import ru.perm.v.camelrest.config.JobParamCopyFile
import ru.perm.v.camelrest.config.MyConfig
import java.util.concurrent.TimeUnit

class CamelCopyFileRouteTest {
    @Test
    fun runRoute() {
        val camelContainer: CamelContainer = CamelContainer(
            JobParamCopyFile("file:temp/testarea/srcDir/","file:temp/testarea/dstDir/"))
        val myConfig = MyConfig(camelContainer )

        val route = CamelCopyFileRoute(myConfig)

        val context = DefaultCamelContext()
        context.addRoutes(route)

        context.start()
        TimeUnit.SECONDS.sleep(2) // with 1 second timeout not working. Bad idea.
        context.stop()

    }
}