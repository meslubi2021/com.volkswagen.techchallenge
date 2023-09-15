package com.volkswagen.techchallenge.domain.repository

import com.volkswagen.techchallenge.domain.entity.Robot
import java.util.*

interface RobotRepository {
    fun findByLogicalId(robotLogicalId: UUID): Robot
    fun save(robot: Robot): Robot
}