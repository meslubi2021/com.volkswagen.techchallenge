package com.volkswagen.techchallenge.infrastructure.service

import com.volkswagen.techchallenge.application.command.createrobot.CreateRobotCommand
import com.volkswagen.techchallenge.application.command.createrobot.CreateRobotCommandHandler
import com.volkswagen.techchallenge.application.command.createworkspace.CreateWorkspaceCommand
import com.volkswagen.techchallenge.application.command.createworkspace.CreateWorkspaceCommandHandler
import com.volkswagen.techchallenge.application.command.moverobot.MoveRobotCommand
import com.volkswagen.techchallenge.application.command.moverobot.MoveRobotCommandHandler
import com.volkswagen.techchallenge.application.query.getrobotposition.GetRobotPositionQuery
import com.volkswagen.techchallenge.application.query.getrobotposition.GetRobotPositionQueryHandler
import com.volkswagen.techchallenge.domain.value.`object`.Heading
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.util.*

@Service
class FactoryService(
    val createWorkspaceCommandHandler: CreateWorkspaceCommandHandler,
    val createRobotCommandHandler: CreateRobotCommandHandler,
    val moveRobotCommandHandler: MoveRobotCommandHandler,
    val getRobotPositionQueryHandler: GetRobotPositionQueryHandler
) {


    fun processInputSequence(inputSequence: String): String {

        val workspaceValidator = "^[123456789] [123456789]$".toRegex()
        val robotPositionAndHeadingValidator = "^\\d \\d [NSEW]$".toRegex() //TODO
        val moveSequenceValidator = "^[LRM]+$".toRegex() //TODO

        val lines = inputSequence.split("\n")

        if(!workspaceValidator.matches(lines[0])) {
            throw RuntimeException("") //TODO
        }

        val workspaceUuid = UUID.randomUUID()
        createWorkspaceCommandHandler.handle(
            CreateWorkspaceCommand(
                workspaceUuid,
                lines[0].split(" ")[0].toInt(),
                lines[0].split(" ")[1].toInt()
            )
        )

        val robotLines = lines.subList(1, lines.count())

        var result = ""
        robotLines.chunked(2).forEach {

            val positionAndHeading = it[0]
            val moveSequence = it[1]

            if(!robotPositionAndHeadingValidator.matches(positionAndHeading)) {
                throw RuntimeException("") //TODO
            }

            if(!moveSequenceValidator.matches(moveSequence)) {
                throw RuntimeException("") //TODO
            }

            val robotUuid = UUID.randomUUID()
            createRobotCommandHandler.handle(
                CreateRobotCommand(
                    robotLogicalId = robotUuid,
                    workspaceLogicalId = workspaceUuid,
                    positionX = positionAndHeading.split(" ")[0].toInt(),
                    positionY = positionAndHeading.split(" ")[1].toInt(),
                    heading = Heading.from(positionAndHeading.split(" ")[2])
                )
            )

            moveRobotCommandHandler.handle(
                MoveRobotCommand(
                    robotLogicalId = robotUuid,
                    moveSequence = moveSequence
                )
            )

            val robotFinalPositionAndHeading = getRobotPositionQueryHandler.handle(
                GetRobotPositionQuery(
                    robotLogicalId = robotUuid
                )
            )

            result += "${robotFinalPositionAndHeading.positionX} ${robotFinalPositionAndHeading.positionY} ${robotFinalPositionAndHeading.heading.value}\n"
        }

        return result
    }
}