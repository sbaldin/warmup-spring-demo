package com.github.sbaldin.warmup.demospring.configuration

import com.github.sbaldin.warmup.demospring.warmup.WarmupService
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class WarmupConfiguration {

    @Bean
    fun warmUpRunner(
        warmupService: WarmupService
    ): ApplicationRunner = ApplicationRunner {
        warmupService.warmup()
    }
}
