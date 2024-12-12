package ru.perm.v.camelrest.camel

import org.apache.camel.CamelContext
import org.apache.camel.builder.RouteBuilder
import org.apache.camel.impl.DefaultCamelContext
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.perm.v.camelrest.config.MyConfig

@Component
class CamelCopyFileRoute(@Autowired val myConfig: MyConfig) : RouteBuilder() {

    private val logger = LoggerFactory.getLogger(this.javaClass.name)
    val camelContext: CamelContext = DefaultCamelContext()
    private var started = false

    fun start() {
        if (!started) {
            configure()
            started = true
        }
    }
    /**
     * Copy file from src to dst
     * for run see:
     * @see ru.perm.v.camelrest.rest.camel.CamelConvertorCtrl.copyFile()
     */
    override fun configure() {
//        from(myConfig.camelContainer.jobParamCopyFile.srcDirectory)
//            .log(LoggingLevel.INFO, logger, "BODY: ${body()}") // trim prefix "simple{"
//            .to(myConfig.camelContainer.jobParamCopyFile.dstDirectory)
        val routeBuilder: RouteBuilder = object : RouteBuilder() {
            @Throws(Exception::class)
            override fun configure() {
                // Опция `noop=true` означает, что файлы не будут удалены после обработки.
                from(myConfig.camelContainer.jobParamCopyFile.srcDirectory)
//                    .log(LoggingLevel.INFO, logger,
//                        "BODY: ${body().toString().substring(7)}") // trim prefix "simple{"
                    .to(myConfig.camelContainer.jobParamCopyFile.dstDirectory)
            }
        }
        camelContext.addRoutes(routeBuilder)
//        camelContext.start().also { camelContext.stop() } // не работает
//        logger.info("Camel start")
//        camelContext.start() // работает. Но, че его все время дергать (start, wait, stop)?
//        logger.info("Start sleep")
//        Thread.sleep(3000);
//        logger.info("End sleep")
//        logger.info("Camel stop")
//        camelContext.stop();
//        started = false
        logger.info("Copy files from ${myConfig.camelContainer.jobParamCopyFile.srcDirectory} to ${myConfig.camelContainer.jobParamCopyFile.dstDirectory}")
    }
}