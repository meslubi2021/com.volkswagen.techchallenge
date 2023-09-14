package com.volkswagen.techchallenge.infrastructure.database

import com.volkswagen.techchallenge.domain.entity.Workspace
import com.volkswagen.techchallenge.domain.respository.WorkspaceRepository
import com.volkswagen.techchallenge.infrastructure.database.jpa.repository.WorkspaceJpaRepository
import com.volkswagen.techchallenge.infrastructure.database.mapper.WorkspaceMapper
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class WorkspaceDatabaseRepository(
    var workspaceJpaRepository: WorkspaceJpaRepository
) : WorkspaceRepository {

    override fun findByLogicalId(workspaceLogicalId: UUID): Workspace {
        return WorkspaceMapper.toEntity(workspaceJpaRepository.findByLogicalId(workspaceLogicalId))
    }

    override fun create(workspace: Workspace): Workspace {
        return WorkspaceMapper.toEntity(workspaceJpaRepository.save(WorkspaceMapper.toJpaEntity(workspace)))
    }
}