package com.volkswagen.techchallenge.application.command.createrobot

import com.volkswagen.common.cqrs.command.CommandHandler
import com.volkswagen.techchallenge.domain.entity.Robot
import com.volkswagen.techchallenge.domain.respository.WorkspaceRepository
import com.volkswagen.common.metrics.MetricsPublisher
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