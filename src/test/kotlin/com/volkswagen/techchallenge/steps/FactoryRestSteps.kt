package com.volkswagen.techchallenge.steps

import com.volkswagen.techchallenge.BaseIntegrationTest
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.assertj.core.api.Assertions
import org.springframework.http.*

class FactoryRestSteps : BaseIntegrationTest() {

    private var response: ResponseEntity<String>? = null

    @When("processing the following input sequence by the factory")
    fun `processing the following input sequence by the factory`(inputSequence: List<String>) {
        val inputSequenceConcatenated = inputSequence.reduce { acc, s -> "$acc\n" + s }
        val headers = HttpHeaders()
        val request = HttpEntity(inputSequenceConcatenated, headers)
        response = executeCall(request, HttpMethod.POST, "/factory/cleaning-robots/process-input-sequence")
    }

    @Then("response has code {int} and body is")
    fun `response body is`(responseCode: Int, expectedResponse: List<String>) {
        val expectedConcatenated = expectedResponse.reduce { acc, s -> "$acc\n" + s }
        Assertions.assertThat(response!!.statusCode.value()).isEqualTo(responseCode)
        Assertions.assertThat(response!!.body).isEqualTo(expectedConcatenated)
    }
}