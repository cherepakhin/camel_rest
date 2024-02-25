package ru.perm.v.camelrest.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties("myconfig")
@ConstructorBinding
data class MyConfig(val camelContainer: CamelContainer)
