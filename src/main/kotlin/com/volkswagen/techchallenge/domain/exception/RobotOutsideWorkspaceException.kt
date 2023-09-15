package com.volkswagen.techchallenge.domain.exception

class RobotOutsideWorkspaceException(
    message: String
): DomainException(CODE, String.format(MESSAGE, message)) {

    companion object {
        const val CODE = "ROBOT_OUTSIDE_WORKSPACE"
        const val MESSAGE = "[RobotOutsideWorkspaceException] - %s"
    }
}