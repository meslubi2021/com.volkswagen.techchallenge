package com.volkswagen.techchallenge.domain.exception

class RobotCollisionException(
    message: String
): DomainException(CODE, String.format(MESSAGE, message)) {

    companion object {
        const val CODE = "ROBOT_COLLISION"
        const val MESSAGE = "[RobotCollisionException] - %s"
    }
}