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
// TODO: Remove comments when I fully understand
// WORKED. Sample test:
//      Параметр "noop" означает, что исходные файлы надо оставить на месте, иначе Camel перенесет их
//      https://habr.com/ru/companies/redhatrussia/articles/352188/
//        val srcDirectory = "file:temp/testarea/srcDir"
//        val dstDirectory = "file:temp/testarea/dstDir"
//        from(srcDirectory + "?noop=true")
//            .to(dstDirectory)

// WORKED. Real variant
//      Параметр "noop" означает, что исходные файлы надо оставить на месте, иначе Camel перенесет их
//        from(myConfig.camelContainer.jobParamCopyFile.srcDirectory + "?noop=true")
        from(myConfig.camelContainer.jobParamCopyFile.srcDirectory)
            .to(myConfig.camelContainer.jobParamCopyFile.dstDirectory)
        logger.info("Copy files from ${myConfig.camelContainer.jobParamCopyFile.srcDirectory} to ${myConfig.camelContainer.jobParamCopyFile.dstDirectory}")

// TODO: Remove comments when I fully understand
//        println("------------------------------")
//        println(myConfig.camelContainer.jobParamCopyFile.srcDirectory) // file:temp/testarea/srcDir/
//        println(myConfig.camelContainer.jobParamCopyFile.dstDirectory) // file:temp/testarea/dstDir/
//        println("------------------------------")
    }
}