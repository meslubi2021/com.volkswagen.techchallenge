package com.volkswagen.techchallenge.infrastructure.database.mapper

import com.volkswagen.techchallenge.domain.entity.Robot
import com.volkswagen.techchallenge.domain.entity.Vector
import com.volkswagen.techchallenge.domain.value.`object`.Direction
import com.volkswagen.techchallenge.infrastructure.database.entity.RobotJpaEntity

class RobotMapper {

    companion object {
        fun toEntity(jpaEntity: RobotJpaEntity): Robot {
            return Robot(
                id = jpaEntity.id,
                logicalId = jpaEntity.logicalId,
                position = Vector(jpaEntity.positionX, jpaEntity.positionY),
                direction = Direction.valueOf(jpaEntity.direction)
            )
        }

        fun toJpaEntity(entity: Robot): RobotJpaEntity {
            return RobotJpaEntity(
                id = entity.id,
                logicalId = entity.logicalId,
                positionX = entity.position.x,
                positionY = entity.position.y,
                direction = entity.direction.value
            )
        }
    }

}