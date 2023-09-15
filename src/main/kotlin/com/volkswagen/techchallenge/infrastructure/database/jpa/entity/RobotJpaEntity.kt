package com.volkswagen.techchallenge.infrastructure.database.jpa.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "robots")
open class RobotJpaEntity(
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    open val id: Long? = null,

    @Column(unique=true)
    open val logicalId: UUID,

    @Column
    open val workspaceId: Long,

    @Column
    open val positionX: Int,

    @Column
    open val positionY: Int,

    @Column
    open val heading: String
)