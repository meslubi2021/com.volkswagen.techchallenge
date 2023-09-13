package com.volkswagen.techchallenge

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MsApplication

fun main(args: Array<String>) {
	runApplication<MsApplication>(*args)
}
