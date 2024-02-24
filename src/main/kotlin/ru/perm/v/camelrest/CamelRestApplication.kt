package ru.perm.v.camelrest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class CamelRestApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<CamelRestApplication>(*args)
        }
    }
}

