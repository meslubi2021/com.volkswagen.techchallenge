package com.volkswagen.techchallenge.infrastructure.api.rest

import com.volkswagen.techchallenge.application.command.createrobot.CreateRobotCommand
import com.volkswagen.techchallenge.application.command.createrobot.CreateRobotCommandHandler
import com.volkswagen.techchallenge.application.command.createworkspace.CreateWorkspaceCommand
import com.volkswagen.techchallenge.application.command.createworkspace.CreateWorkspaceCommandHandler
import com.volkswagen.techchallenge.application.command.moverobot.MoveRobotCommand
import com.volkswagen.techchallenge.application.command.moverobot.MoveRobotCommandHandler
import com.volkswagen.techchallenge.application.query.getrobotposition.GetRobotPositionQuery
import com.volkswagen.techchallenge.application.query.getrobotposition.GetRobotPositionQueryHandler
import com.volkswagen.techchallenge.domain.value.`object`.Heading
import com.volkswagen.techchallenge.infrastructure.exception.BadRequestException
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/factory")
class FactoryController(
    val createWorkspaceCommandHandler: CreateWorkspaceCommandHandler,
    val createRobotCommandHandler: CreateRobotCommandHandler,
    val moveRobotCommandHandler: MoveRobotCommandHandler,
    val getRobotPositionQueryHandler: GetRobotPositionQueryHandler
) {
    companion object {
        // https://ihateregex.io/playground/
        val REQUEST_VALIDATOR_REGEX = "^(\\d \\d\\n)(\\d \\d [NSEW]\\n[LRM]+(\\n)*)+\$".toRegex()
    }

    @PostMapping("/cleaning-robots/process-input-sequence",
        consumes = [MediaType.TEXT_PLAIN_VALUE],
        produces = [MediaType.TEXT_PLAIN_VALUE])
    @ResponseStatus(HttpStatus.OK)
    fun processInputSequence(@RequestBody inputSequence: String): String {

        if(!REQUEST_VALIDATOR_REGEX.matches(inputSequence)) {
            throw BadRequestException("Invalid request format")
        }

        val lines = inputSequence.split("\n")

        val workspaceUuid = UUID.randomUUID()
        val workspaceUpperRight = lines[0].split(" ").map { it.toInt() }
        createWorkspaceCommandHandler.handle(
            CreateWorkspaceCommand(
                workspaceUuid,
                workspaceUpperRight[0],
                workspaceUpperRight[1]
            )
        )

        var result = ""
        lines.subList(1, lines.count()).chunked(2).forEach {

            val robotUuid = UUID.randomUUID()
            val positionAndHeadings = it[0].split(" ")
            createRobotCommandHandler.handle(
                CreateRobotCommand(
                    robotLogicalId = robotUuid,
                    workspaceLogicalId = workspaceUuid,
                    positionX = positionAndHeadings[0].toInt(),
                    positionY = positionAndHeadings[1].toInt(),
                    heading = Heading.from(positionAndHeadings[2])
                )
            )

            moveRobotCommandHandler.handle(
                MoveRobotCommand(
                    robotLogicalId = robotUuid,
                    moveSequence = it[1]
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