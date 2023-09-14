package com.volkswagen.techchallenge.domain.respository

import com.volkswagen.techchallenge.domain.entity.Workspace
import java.util.*

interface WorkspaceRepository {
    fun findByLogicalId(workspaceLogicalId: UUID): Workspace
    fun create(workspace: Workspace): Workspace
}