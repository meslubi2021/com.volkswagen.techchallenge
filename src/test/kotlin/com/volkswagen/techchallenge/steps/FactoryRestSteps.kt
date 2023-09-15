package com.volkswagen.techchallenge.steps

import com.volkswagen.techchallenge.BaseIntegrationTest
import io.cucumber.java.en.When
import org.springframework.http.*

class FactoryRestSteps : BaseIntegrationTest() {

    @When("process input valid sequence by the factory")
    fun `factory call to clean with input sequence`() {
        val inputSequence = """
            |5 5
            |1 2 N
            |LMLMLMLMM
            |3 3 E
            |MMRMMRMRRM""".trimMargin()

        val headers = HttpHeaders()
        val request = HttpEntity(inputSequence, headers)
        executeCall(request, HttpMethod.POST, "/factory/cleaning-robots/process-input-sequence")
    }
}