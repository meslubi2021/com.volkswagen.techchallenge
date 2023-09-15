package com.volkswagen.techchallenge.domain.exception

class ValidationException(
    message: String
): DomainException(CODE, String.format(MESSAGE, message)) {

    companion object {
        const val CODE = "VALIDATION"
        const val MESSAGE = "[ValidationException] - %s"
    }
}