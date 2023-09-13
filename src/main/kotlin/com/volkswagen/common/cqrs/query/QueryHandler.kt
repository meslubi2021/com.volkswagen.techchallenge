package com.volkswagen.common.cqrs.query

interface QueryHandler<Request: Query, Response> {
    fun handle(query: Request): Response
}