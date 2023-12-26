package com.github.sbaldin.warmup.demospring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@EnableConfigurationProperties
@SpringBootApplication
class DemoSpringApplication

fun main(args: Array<String>) {
    runApplication<DemoSpringApplication>(*args)
}
