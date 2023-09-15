package com.volkswagen.techchallenge.infrastructure.exception

class ValidationException(
    message: String
): InfrastructureException(CODE, String.format(MESSAGE, message)) {

    companion object {
        const val CODE = "VALIDATION"
        const val MESSAGE = "[ValidationException] - %s"
    }
}