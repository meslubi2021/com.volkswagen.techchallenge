package com.volkswagen.techchallenge.metrics

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class MetricsPublisher {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    fun increment(metricName: String) {
        logger.info("[${this.javaClass.simpleName}] - Incrementing $metricName")
    }
}