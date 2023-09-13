package com.volkswagen.common.cqrs.command

interface CommandHandler<Request: Command> {
    fun handle(command: Request)
}