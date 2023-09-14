package com.volkswagen.techchallenge.application.query.getrobotposition

import com.volkswagen.techchallenge.domain.value.`object`.Heading

data class GetRobotPositionResponse(
    val positionX: Int,
    val positionY: Int,
    val heading: Heading
)