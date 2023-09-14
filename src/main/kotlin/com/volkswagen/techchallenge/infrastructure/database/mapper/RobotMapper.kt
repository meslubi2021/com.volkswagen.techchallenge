package com.volkswagen.techchallenge.infrastructure.database.mapper

import com.volkswagen.techchallenge.domain.entity.Robot
import com.volkswagen.techchallenge.domain.value.`object`.Heading
import com.volkswagen.techchallenge.infrastructure.database.jpa.entity.RobotJpaEntity

class RobotMapper {

    companion object {
        fun toEntity(jpaEntity: RobotJpaEntity): Robot {
            return Robot(
                id = jpaEntity.id,
                logicalId = jpaEntity.logicalId,
                workspaceId = jpaEntity.workspaceId,
                positionX = jpaEntity.positionX,
                positionY = jpaEntity.positionY,
                heading = Heading.from(jpaEntity.heading)
            )
        }

        fun toJpaEntity(entity: Robot): RobotJpaEntity {
            return RobotJpaEntity(
                id = entity.id,
                logicalId = entity.logicalId,
                workspaceId = entity.workspaceId,
                positionX = entity.positionX,
                positionY = entity.positionY,
                heading = entity.heading.value
            )
        }
    }

}