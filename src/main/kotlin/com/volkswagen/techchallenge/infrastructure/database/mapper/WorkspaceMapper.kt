package com.volkswagen.techchallenge.infrastructure.database.mapper

import com.volkswagen.techchallenge.domain.entity.Vector
import com.volkswagen.techchallenge.domain.entity.Workspace
import com.volkswagen.techchallenge.infrastructure.database.entity.WorkspaceJpaEntity

class WorkspaceMapper {

    companion object {
        fun toEntity(jpaEntity: WorkspaceJpaEntity): Workspace {
            return Workspace(
                id = jpaEntity.id,
                logicalId = jpaEntity.logicalId,
                size = Vector(jpaEntity.sizeX, jpaEntity.sizeY)
            )
        }

        fun toJpaEntity(entity: Workspace): WorkspaceJpaEntity {
            return WorkspaceJpaEntity(
                id = entity.id,
                logicalId = entity.logicalId,
                sizeX = entity.size.x,
                sizeY = entity.size.y
            )
        }
    }

}