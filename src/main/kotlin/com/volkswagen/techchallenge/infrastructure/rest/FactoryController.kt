package com.volkswagen.techchallenge.infrastructure.rest

import com.volkswagen.techchallenge.infrastructure.service.FactoryService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/factory")
class FactoryController(
    val factoryService: FactoryService
) {
    @PostMapping("/cleaning-robots/process-input-sequence",
        consumes = [MediaType.TEXT_PLAIN_VALUE],
        produces = [MediaType.TEXT_PLAIN_VALUE])
    @ResponseStatus(HttpStatus.OK)
    fun processInputSequence(@RequestBody inputSequence: String): String {
        return factoryService.processInputSequence(inputSequence)
    }

}