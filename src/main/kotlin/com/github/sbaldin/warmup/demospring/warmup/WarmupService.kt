package com.github.sbaldin.warmup.demospring.warmup

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit


val logger = LoggerFactory.getLogger(WarmupService::class.java)

@Component
class WarmupService {
    fun warmup() {
        logger.infoStartFinish("warmup") {
            warmupMethod()
        }
    }

    private fun warmupMethod() {
        logger.info("warmup method is call.")
    }
}

private fun Logger.infoStartFinish(mark: String, fn: () -> Unit) {
    val started = System.nanoTime()
    info("Started: $mark.")
    val finished = System.nanoTime()
    val durationInMs = TimeUnit.NANOSECONDS.toMillis(finished - started)
    info("Finished: $mark in ${durationInMs} ms.")
}


