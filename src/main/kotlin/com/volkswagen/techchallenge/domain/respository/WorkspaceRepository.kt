package com.volkswagen.techchallenge.domain.respository

import com.volkswagen.techchallenge.domain.entity.Workspace
import java.util.*

interface WorkspaceRepository {
    fun findById(workspaceId: Long): Workspace
    fun findByLogicalId(workspaceLogicalId: UUID): Workspace
    fun save(workspace: Workspace): Workspace
}