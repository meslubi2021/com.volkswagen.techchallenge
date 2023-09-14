package com.volkswagen.techchallenge.domain.respository

import com.volkswagen.techchallenge.domain.entity.Robot
import java.util.*

interface RobotRepository {
    fun findByLogicalId(robotLogicalId: UUID): Robot
    fun save(robot: Robot): Robot
}