package com.volkswagen.techchallenge.application.query.getrobotposition

import com.volkswagen.techchallenge.domain.entity.Vector
import com.volkswagen.techchallenge.domain.value.`object`.Direction

data class GetRobotPositionResponse(
    val position: Vector,
    val direction: Direction
)