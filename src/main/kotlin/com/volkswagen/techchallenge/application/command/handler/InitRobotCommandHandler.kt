package com.volkswagen.techchallenge.application.command.handler

import com.volkswagen.common.cqrs.command.CommandHandler
import com.volkswagen.techchallenge.application.command.InitRobotCommand
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class InitRobotCommandHandler : CommandHandler<InitRobotCommand> {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    override fun handle(command: InitRobotCommand) {
        logger.info("[InitRobotCommandHandler] - Done!")
    }
}