package com.volkswagen.techchallenge.domain.entity

import com.volkswagen.techchallenge.domain.exception.RobotCollisionException
import com.volkswagen.techchallenge.domain.exception.RobotOutsideWorkspaceException
import java.util.*

class Workspace(
    var id: Long? = null,
    val logicalId: UUID,
    val upperRightCornerX: Int,
    val upperRightCornerY: Int,
    val robots: MutableList<Robot> = ArrayList()
) {
    fun isPossibleToMoveRobot(robot: Robot): Boolean {

        if (
            (robot.positionX < 0 || robot.positionX > upperRightCornerX) ||
            (robot.positionY < 0 || robot.positionY > upperRightCornerY)
        ) {
            throw RobotOutsideWorkspaceException("Not possible to move to ${robot.positionX}, ${robot.positionY}")
        }

        robots.forEach {
            if (it.id != robot.id && it.positionX == robot.positionX && it.positionY == robot.positionY) {
                throw RobotCollisionException("Collision with robot at position ${robot.positionX}, ${robot.positionY}")
            }
        }
        return true
    }
}