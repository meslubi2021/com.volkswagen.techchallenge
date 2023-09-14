package com.volkswagen.techchallenge.infrastructure.database.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "workspaces")
data class WorkspaceJpaEntity(
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    var id: Long? = null,

    @Column
    var logicalId: UUID,

    @Column
    val sizeX: Int,

    @Column
    val sizeY: Int
)