package com.volkswagen.techchallenge.application.command.handler

import com.volkswagen.common.cqrs.command.CommandHandler
import com.volkswagen.techchallenge.application.command.InitWorkspaceCommand
import com.volkswagen.techchallenge.metrics.MetricNames
import com.volkswagen.techchallenge.metrics.MetricsPublisher
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class InitWorkspaceCommandHandler(
    val metricsPublisher: MetricsPublisher
) : CommandHandler<InitWorkspaceCommand> {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    override fun handle(command: InitWorkspaceCommand) {

        logger.info("[InitWorkspaceCommandHandler] - Done!")
        metricsPublisher.increment(MetricNames.WORKSPACE_INITIALIZED)
    }
}