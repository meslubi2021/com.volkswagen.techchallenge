package com.volkswagen.techchallenge.infrastructure.database.jpa.repository

import com.volkswagen.techchallenge.infrastructure.database.jpa.entity.WorkspaceJpaEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface WorkspaceJpaRepository : CrudRepository<WorkspaceJpaEntity, Int> {
    fun findByLogicalId(logicalId: UUID): WorkspaceJpaEntity
}