package ru.perm.v.camelrest.camel

import org.apache.camel.builder.RouteBuilder

class CopyFileRoute : RouteBuilder() {
    companion object {
        val dstDirectory = "/home/vasi/temp/testarea/dstDir"
    }

    override fun configure() {
        from("file:/home/vasi/temp/testarea/srcDir?noop=true")
            .to("file:" + dstDirectory)
    }
}