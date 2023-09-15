package com.volkswagen.techchallenge.infrastructure.api.middleware

import java.io.Serializable

class ErrorResponse(
    val message: String
) : Serializable