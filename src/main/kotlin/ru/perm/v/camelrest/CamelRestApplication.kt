package ru.perm.v.camelrest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CamelRestApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<CamelRestApplication>(*args)
        }
    }
}

