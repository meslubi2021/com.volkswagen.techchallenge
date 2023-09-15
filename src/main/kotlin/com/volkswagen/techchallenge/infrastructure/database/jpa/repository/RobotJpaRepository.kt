package com.volkswagen.techchallenge.infrastructure.database.jpa.repository

import com.volkswagen.techchallenge.infrastructure.database.jpa.entity.RobotJpaEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RobotJpaRepository : CrudRepository<RobotJpaEntity, Long> {
    fun findByLogicalId(logicalId: UUID): RobotJpaEntity
}