package com.volkswagen.techchallenge.infrastructure.exception

class EntityNotFoundException(
    message: String
): InfrastructureException(CODE, String.format(MESSAGE, message)) {

    companion object {
        const val CODE = "ENTITY_NOT_FOUND"
        const val MESSAGE = "[EntityNotFoundException] - %s"
    }
}