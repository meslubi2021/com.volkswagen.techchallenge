package com.volkswagen.techchallenge

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment

@SpringBootTest(
    classes = [MsApplication::class],
    webEnvironment = WebEnvironment.NONE
)
@AutoConfigureDataJpa
@AutoConfigureTestDatabase
class BaseIntegrationTest