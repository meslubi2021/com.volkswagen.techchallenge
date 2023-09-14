package com.volkswagen.techchallenge.infrastructure.database.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "robots")
data class RobotJpaEntity(
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    var id: Long? = null,

    @Column
    var logicalId: UUID,

    @Column
    var positionX: Int,

    @Column
    var positionY: Int,

    @Column
    var direction: String
)