package com.volkswagen.techchallenge.application.command.handler

import com.volkswagen.common.cqrs.command.CommandHandler
import com.volkswagen.techchallenge.application.command.MoveRobotCommand
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class MoveRobotCommandHandler(
    //private val customerRepository: CustomerRepository,
) : CommandHandler<MoveRobotCommand> {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    override fun handle(command: MoveRobotCommand) {
        logger.info("[MoveRobotCommandHandler] - Done!")
    }
}