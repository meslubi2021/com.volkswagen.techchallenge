package com.volkswagen.techchallenge.infrastructure.api.rest

import com.volkswagen.common.cqrs.command.CommandHandler
import com.volkswagen.common.cqrs.query.QueryHandler
import com.volkswagen.techchallenge.application.command.createrobot.CreateRobotCommand
import com.volkswagen.techchallenge.application.command.createworkspace.CreateWorkspaceCommand
import com.volkswagen.techchallenge.application.command.moverobot.MoveRobotCommand
import com.volkswagen.techchallenge.application.query.getrobotposition.GetRobotPositionQuery
import com.volkswagen.techchallenge.application.query.getrobotposition.GetRobotPositionResponse
import com.volkswagen.techchallenge.domain.value.`object`.Heading
import com.volkswagen.techchallenge.infrastructure.exception.BadRequestException
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/factory")
class FactoryController(
    val createWorkspaceCommandHandler: CommandHandler<CreateWorkspaceCommand>,
    val createRobotCommandHandler: CommandHandler<CreateRobotCommand>,
    val moveRobotCommandHandler: CommandHandler<MoveRobotCommand>,
    val getRobotPositionQueryHandler: QueryHandler<GetRobotPositionQuery, GetRobotPositionResponse>
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
        val (workspaceUpperRightX, workspaceUpperRightY) = workspaceUpperRightSplitter(lines[0])
        createWorkspaceCommandHandler.handle(
            CreateWorkspaceCommand(
                workspaceUuid,
                workspaceUpperRightX,
                workspaceUpperRightY
            )
        )

        var result = ""
        lines
            .subList(1, lines.count())
            .chunked(2) { (positionAndHeadings, moveSequence) ->

                val robotUuid = UUID.randomUUID()
                val (posX, posY, heading) = positionsAndHeadingSplitter(positionAndHeadings)
                createRobotCommandHandler.handle(
                    CreateRobotCommand(
                        robotLogicalId = robotUuid,
                        workspaceLogicalId = workspaceUuid,
                        positionX = posX,
                        positionY = posY,
                        heading = Heading.from(heading)
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

    private fun workspaceUpperRightSplitter(input: String): Pair<Int, Int> {
        val split = input.split(" ").map { it.toInt() }
        return Pair(split[0],split[1])
    }

    private fun positionsAndHeadingSplitter(input: String): Triple<Int, Int, String> {
        val split = input.split(" ")
        return Triple(split[0].toInt(),split[1].toInt(),split[2])
    }

}