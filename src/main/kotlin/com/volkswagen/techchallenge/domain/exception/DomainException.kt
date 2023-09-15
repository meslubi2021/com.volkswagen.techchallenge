package com.volkswagen.techchallenge.domain.exception

abstract class DomainException(
    var code: String,
    override var message: String
) : RuntimeException()