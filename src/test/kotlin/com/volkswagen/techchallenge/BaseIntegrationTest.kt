package com.volkswagen.techchallenge

import org.junit.jupiter.api.fail
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity

@SpringBootTest(
    classes = [MsApplication::class],
    webEnvironment = WebEnvironment.RANDOM_PORT
)
@AutoConfigureDataJpa
@AutoConfigureTestDatabase
class BaseIntegrationTest {

    private val restTemplate = TestRestTemplate()

    @LocalServerPort
    protected var port: Int? = null

    protected fun executeCall(
        request: HttpEntity<*>?,
        method: HttpMethod?,
        path: String
    ): ResponseEntity<String> {
        return restTemplate.exchange("http://localhost:$port$path", method, request, String::class.java)
    }

    protected fun getMandatoryParameter(parameterName: String, options: MutableMap<String, String>): String {
        return getParameter(parameterName, options) ?: fail { "Parameter $parameterName is required" }
    }

    protected fun getParameter(parameterName: String, options: MutableMap<String, String>): String? {
        return options[parameterName]
    }
}