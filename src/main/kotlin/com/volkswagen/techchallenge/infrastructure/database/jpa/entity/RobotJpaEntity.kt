package com.volkswagen.techchallenge.infrastructure.database.jpa.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "robots")
open class RobotJpaEntity(
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    open var id: Long? = null,

    @Column
    open var logicalId: UUID,

    @Column
    open var workspaceId: Long,

    @Column
    open var positionX: Int,

    @Column
    open var positionY: Int,

    @Column
    open var heading: String
)