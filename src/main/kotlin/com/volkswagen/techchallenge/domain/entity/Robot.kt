package com.volkswagen.techchallenge.domain.entity

import com.volkswagen.techchallenge.domain.exception.ValidationException
import com.volkswagen.techchallenge.domain.value.`object`.Heading
import java.util.*

class Robot(
    val id: Long? = null,
    val logicalId: UUID,
    val workspaceId: Long,
    var positionX: Int,
    var positionY: Int,
    var heading: Heading
) {

    constructor(
        id: Long? = null,
        logicalId: UUID,
        workspace: Workspace,
        positionX: Int,
        positionY: Int,
        heading: Heading
    ) : this(
        id,
        logicalId,
        workspace.id!!,
        positionX,
        positionY,
        heading
    ) {
        if(
            positionX < 0 ||
            positionY < 0 ||
            positionX > workspace.upperRightCornerX ||
            positionY > workspace.upperRightCornerY
        ) {
            throw ValidationException("Robot has to be inside workspace")
        }
    }

    fun move() {
        when(heading) {
            Heading.NORTH -> positionY += 1
            Heading.EAST -> positionX += 1
            Heading.SOUTH -> positionY += -1
            Heading.WEST -> positionX += -1
        }
    }

    fun left() {
        heading = when(heading) {
            Heading.NORTH -> Heading.WEST
            Heading.EAST -> Heading.NORTH
            Heading.SOUTH -> Heading.EAST
            Heading.WEST -> Heading.SOUTH
        }
    }

    fun right() {
        heading = when(heading) {
            Heading.NORTH -> Heading.EAST
            Heading.EAST -> Heading.SOUTH
            Heading.SOUTH -> Heading.WEST
            Heading.WEST -> Heading.NORTH
        }
    }
}