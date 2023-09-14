package com.volkswagen.techchallenge.application.query.getrobotposition

import com.volkswagen.common.cqrs.query.Query
import java.util.UUID

data class GetRobotPositionQuery(
    val robotLogicalId: UUID
) : Query