package ru.perm.v.camelrest.camel

import org.apache.camel.builder.RouteBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.perm.v.camelrest.config.MyConfig

@Component
class CamelCopyFileRoute : RouteBuilder() {

    var testDirectory = "file:/home/vasi/temp/testarea"

    @Autowired
    lateinit var myConfig: MyConfig

    val srcDirectory = testDirectory + "/srcDir"
    val dstDirectory = testDirectory + "/dstDir"

//    companion object {
//        val srcDirectory = testDirectory + "/srcDir"
//        val dstDirectory = testDirectory + "/dstDir"
//    }


    /**
     * Copy file from src to dst
     * for run see:
     * @see ru.perm.v.camelrest.CamelConvertorCtrl.copyFile()
     */
    override fun configure() {
//      Параметр noop означает, что исходные файлы надо оставить на месте, иначе Camel перенесет их
//      https://habr.com/ru/companies/redhatrussia/articles/352188/
        from(srcDirectory + "?noop=true")
            .to(dstDirectory)
    }
}