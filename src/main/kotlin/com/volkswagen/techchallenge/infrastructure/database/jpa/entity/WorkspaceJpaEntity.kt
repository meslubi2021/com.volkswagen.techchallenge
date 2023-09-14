package com.volkswagen.techchallenge.infrastructure.database.jpa.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "workspaces")
open class WorkspaceJpaEntity(
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    open val id: Long? = null,

    @Column
    open val logicalId: UUID,

    @Column
    open val upperRightCornerX: Int,

    @Column
    open val upperRightCornerY: Int,

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(name = "workspaceId")
    open val robots: List<RobotJpaEntity>
)
