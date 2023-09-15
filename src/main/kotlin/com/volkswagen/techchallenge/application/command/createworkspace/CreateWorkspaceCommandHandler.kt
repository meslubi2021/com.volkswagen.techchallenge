package com.volkswagen.techchallenge.application.command.createworkspace

import com.volkswagen.common.cqrs.command.CommandHandler
import com.volkswagen.techchallenge.domain.entity.Workspace
import com.volkswagen.techchallenge.domain.repository.WorkspaceRepository
import com.volkswagen.common.metrics.MetricsPublisher
import com.volkswagen.techchallenge.domain.exception.ValidationException
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class CreateWorkspaceCommandHandler(
    val metricsPublisher: MetricsPublisher,
    val workspaceRepository: WorkspaceRepository
) : CommandHandler<CreateWorkspaceCommand> {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    override fun handle(command: CreateWorkspaceCommand) {

        if(
            command.upperRightCornerX <= 0 ||
            command.upperRightCornerY <= 0
        ) {
            throw ValidationException("Workspace need a correct size")
        }

        workspaceRepository.save(
            Workspace(
                logicalId = command.workspaceLogicalId,
                upperRightCornerX = command.upperRightCornerX,
                upperRightCornerY = command.upperRightCornerY
            )
        )

        logger.info("[${this.javaClass.simpleName}] - Done!")
        metricsPublisher.increment("volkswagen.techchallenge.workspace.created")
    }
}