package ru.perm.v.camelrest.service.impl

import org.apache.camel.CamelContext
import org.apache.camel.Processor
import org.apache.camel.builder.RouteBuilder
import org.springframework.stereotype.Service

@Service
class EmployeeServiceImpl(context: CamelContext?) : RouteBuilder(context) {
    override fun configure() {
        from("direct:insert").process(Processor() {
            exchange ->
            println("Inserting employee")
        }).to("jdbc:dataSource")
    }
}