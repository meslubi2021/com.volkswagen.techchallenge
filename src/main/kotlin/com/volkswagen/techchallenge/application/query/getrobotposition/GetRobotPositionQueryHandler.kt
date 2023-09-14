package com.volkswagen.techchallenge.application.query.getrobotposition

import com.volkswagen.common.cqrs.query.QueryHandler
import com.volkswagen.techchallenge.domain.entity.Vector
import com.volkswagen.techchallenge.domain.value.`object`.Direction
import org.springframework.stereotype.Service

@Service
class GetRobotPositionQueryHandler(
    //private val rewardRepository: RewardRepository,
) : QueryHandler<GetRobotPositionQuery, GetRobotPositionResponse> {
    override fun handle(query: GetRobotPositionQuery): GetRobotPositionResponse {
        return GetRobotPositionResponse(
            Vector(0,0),
            Direction.NORTH
        )
    }
}