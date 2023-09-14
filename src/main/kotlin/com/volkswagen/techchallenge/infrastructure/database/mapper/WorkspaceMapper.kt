package com.volkswagen.techchallenge.infrastructure.database.mapper

import com.volkswagen.techchallenge.domain.entity.Workspace
import com.volkswagen.techchallenge.infrastructure.database.jpa.entity.WorkspaceJpaEntity
import java.util.stream.Collectors

class WorkspaceMapper {

    companion object {
        fun toEntity(jpaEntity: WorkspaceJpaEntity): Workspace {
            return Workspace(
                id = jpaEntity.id,
                logicalId = jpaEntity.logicalId,
                upperRightCornerX = jpaEntity.upperRightCornerX,
                upperRightCornerY = jpaEntity.upperRightCornerY,
                robots = jpaEntity.robots
                    .stream()
                    .map { RobotMapper.toEntity(it) }
                    .collect(Collectors.toList())
            )
        }

        fun toJpaEntity(entity: Workspace): WorkspaceJpaEntity {
            return WorkspaceJpaEntity(
                id = entity.id,
                logicalId = entity.logicalId,
                upperRightCornerX = entity.upperRightCornerX,
                upperRightCornerY = entity.upperRightCornerY,
                robots = entity.robots
                    .stream()
                    .map { RobotMapper.toJpaEntity(it) }
                    .collect(Collectors.toList())
            )
        }
    }

}