package com.volkswagen.techchallenge.application.command.createrobot

import com.volkswagen.common.cqrs.command.CommandHandler
import com.volkswagen.techchallenge.domain.entity.Robot
import com.volkswagen.techchallenge.domain.repository.WorkspaceRepository
import com.volkswagen.common.metrics.MetricsPublisher
import com.volkswagen.techchallenge.domain.exception.ValidationException
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class CreateRobotCommandHandler(
    val metricsPublisher: MetricsPublisher,
    val workspaceRepository: WorkspaceRepository
) : CommandHandler<CreateRobotCommand> {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    override fun handle(command: CreateRobotCommand) {

        val workspace = workspaceRepository.findByLogicalId(command.workspaceLogicalId)

        if(
            command.positionX < 0 ||
            command.positionY < 0 ||
            command.positionX > workspace.upperRightCornerX ||
            command.positionY > workspace.upperRightCornerY
            ) {
            throw ValidationException("Robot has to be inside workspace")
        }

        val newRobot = Robot(
            logicalId = command.robotLogicalId,
            workspaceId = workspace.id!!,
            positionX = command.positionX,
            positionY = command.positionY,
            heading = command.heading
        )
        workspace.robots.add(newRobot)
        workspaceRepository.save(workspace)

        logger.info("[${this.javaClass.simpleName}] - Done!")
        metricsPublisher.increment("volkswagen.techchallenge.robot.created")
    }
}