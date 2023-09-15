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
import com.volkswagen.techchallenge.infrastructure.exception.ValidationException
import org.springframework.stereotype.Service
import java.util.*

@Service
class FactoryService(
    val createWorkspaceCommandHandler: CreateWorkspaceCommandHandler,
    val createRobotCommandHandler: CreateRobotCommandHandler,
    val moveRobotCommandHandler: MoveRobotCommandHandler,
    val getRobotPositionQueryHandler: GetRobotPositionQueryHandler
) {
    companion object {
        val WORKSPACE_VALIDATOR_REGEX = "^[123456789] [123456789]$".toRegex()
        val ROBOT_POSITION_AND_HEADING_VALIDATOR_REGEX = "^\\d \\d [NSEW]$".toRegex()
        val MOVE_SEQUENCE_VALIDATOR_REGEX = "^[LRM]+$".toRegex()
    }

    fun processInputSequence(inputSequence: String): String {

        val lines = inputSequence.split("\n")

        if(!WORKSPACE_VALIDATOR_REGEX.matches(lines[0])) {
            throw ValidationException("Workspace validation error")
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

            if(!ROBOT_POSITION_AND_HEADING_VALIDATOR_REGEX.matches(positionAndHeading)) {
                throw ValidationException("Robot position and heading validation error")
            }

            if(!MOVE_SEQUENCE_VALIDATOR_REGEX.matches(moveSequence)) {
                throw ValidationException("Robot move sequence validation error")
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

        return result.trim()
    }
}