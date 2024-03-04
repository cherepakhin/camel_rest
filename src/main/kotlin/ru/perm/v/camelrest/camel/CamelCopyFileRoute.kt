package ru.perm.v.camelrest.camel

import org.apache.camel.builder.RouteBuilder
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.perm.v.camelrest.config.MyConfig

@Component
class CamelCopyFileRoute(@Autowired val myConfig: MyConfig) : RouteBuilder() {

    private val logger = LoggerFactory.getLogger(this.javaClass.name)

    /**
     * Copy file from src to dst
     * for run see:
     * @see ru.perm.v.camelrest.CamelConvertorCtrl.copyFile()
     */
    override fun configure() {
        logger.info("onCompletion")
        from(myConfig.camelContainer.jobParamCopyFile.srcDirectory)
            .onCompletion().to("log:onCompletion")
            .to(myConfig.camelContainer.jobParamCopyFile.dstDirectory)
        logger.info("Copy files from ${myConfig.camelContainer.jobParamCopyFile.srcDirectory} to ${myConfig.camelContainer.jobParamCopyFile.dstDirectory}")
    }
}