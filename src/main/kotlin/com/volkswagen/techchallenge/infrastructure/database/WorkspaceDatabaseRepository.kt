package com.volkswagen.techchallenge.infrastructure.database

import com.volkswagen.techchallenge.domain.entity.Workspace
import com.volkswagen.techchallenge.domain.repository.WorkspaceRepository
import com.volkswagen.techchallenge.infrastructure.database.jpa.repository.WorkspaceJpaRepository
import com.volkswagen.techchallenge.infrastructure.database.mapper.WorkspaceMapper
import com.volkswagen.techchallenge.infrastructure.exception.EntityNotFoundException
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class WorkspaceDatabaseRepository(
    var workspaceJpaRepository: WorkspaceJpaRepository
) : WorkspaceRepository {

    override fun findById(workspaceId: Long): Workspace {
        return WorkspaceMapper.toEntity(workspaceJpaRepository.findById(workspaceId)
            .orElseThrow {EntityNotFoundException("Workspace with id $workspaceId not found!")})
    }

    override fun findByLogicalId(workspaceLogicalId: UUID): Workspace {
        return WorkspaceMapper.toEntity(workspaceJpaRepository.findByLogicalId(workspaceLogicalId))
    }

    override fun save(workspace: Workspace): Workspace {
        return WorkspaceMapper.toEntity(workspaceJpaRepository.save(WorkspaceMapper.toJpaEntity(workspace)))
    }
}