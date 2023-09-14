package com.volkswagen.techchallenge.application.command.moverobot

import com.volkswagen.common.cqrs.command.CommandHandler
import com.volkswagen.techchallenge.domain.respository.RobotRepository
import com.volkswagen.common.metrics.MetricsPublisher
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class MoveRobotCommandHandler(
    val metricsPublisher: MetricsPublisher,
    val robotRepository: RobotRepository
) : CommandHandler<MoveRobotCommand> {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    override fun handle(command: MoveRobotCommand) {

        var robot = robotRepository.findByLogicalId(command.robotLogicalId)

        command.moveSequence.forEach {
            logger.info("[${this.javaClass.simpleName}] - Processing movement letter => $it")

            when(it) {
                'L' -> robot.left()
                'R' -> robot.right()
                'M' -> robot.move()
                else -> { throw IllegalArgumentException()}
            }
        }

        robot = robotRepository.save(robot)

        logger.info("[${this.javaClass.simpleName}] - Done!")
    }
}