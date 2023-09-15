package com.volkswagen.techchallenge.infrastructure.exception

abstract class InfrastructureException(
    var code: String,
    override var message: String
) : RuntimeException()