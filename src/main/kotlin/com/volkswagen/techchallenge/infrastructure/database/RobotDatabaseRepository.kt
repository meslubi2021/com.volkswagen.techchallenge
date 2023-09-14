package com.volkswagen.techchallenge.infrastructure.database

import com.volkswagen.techchallenge.domain.entity.Robot
import com.volkswagen.techchallenge.domain.respository.RobotRepository
import com.volkswagen.techchallenge.infrastructure.database.jpa.repository.RobotJpaRepository
import com.volkswagen.techchallenge.infrastructure.database.mapper.RobotMapper
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class RobotDatabaseRepository(
    var robotJpaRepository: RobotJpaRepository
) : RobotRepository {

    override fun findByLogicalId(robotLogicalId: UUID): Robot {
        return RobotMapper.toEntity(robotJpaRepository.findByLogicalId(robotLogicalId))
    }

    override fun save(robot: Robot): Robot {
        return RobotMapper.toEntity(robotJpaRepository.save(RobotMapper.toJpaEntity(robot)))
    }
}