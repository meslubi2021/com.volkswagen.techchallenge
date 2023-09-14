package com.volkswagen.techchallenge.application.query.getrobotposition

import com.volkswagen.common.cqrs.query.QueryHandler
import com.volkswagen.techchallenge.domain.respository.RobotRepository
import org.springframework.stereotype.Service

@Service
class GetRobotPositionQueryHandler(
    val robotRepository: RobotRepository
) : QueryHandler<GetRobotPositionQuery, GetRobotPositionResponse> {
    override fun handle(query: GetRobotPositionQuery): GetRobotPositionResponse {
        val robot = robotRepository.findByLogicalId(query.robotLogicalId)
        return GetRobotPositionResponse(
            positionX = robot.positionX,
            positionY = robot.positionY,
            heading = robot.heading
        )
    }
}