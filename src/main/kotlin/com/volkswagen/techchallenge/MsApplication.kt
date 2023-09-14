package com.volkswagen.techchallenge

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("com.volkswagen.techchallenge.config")
class MsApplication

fun main(args: Array<String>) {
	runApplication<MsApplication>(*args)
}
