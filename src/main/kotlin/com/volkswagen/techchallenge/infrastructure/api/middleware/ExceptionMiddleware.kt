package com.volkswagen.techchallenge.infrastructure.api.middleware

import com.volkswagen.techchallenge.infrastructure.exception.BadRequestException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionMiddleware {
    @ExceptionHandler(BadRequestException::class)
    fun validationException(ex: BadRequestException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(ErrorResponse(ex.message), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(Exception::class)
    fun genericException(ex: Exception): ResponseEntity<ErrorResponse> {
        LoggerFactory.getLogger(ExceptionMiddleware::class.java).error(ex.message, ex)
        return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
    }
}