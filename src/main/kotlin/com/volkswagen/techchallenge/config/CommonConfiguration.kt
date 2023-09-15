package com.volkswagen.techchallenge.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackages = [
    "com.volkswagen.techchallenge.application",
    "com.volkswagen.techchallenge.domain",
    "com.volkswagen.techchallenge.infrastructure",
    "com.volkswagen.common.metrics" //TODO
])
class CommonConfiguration