package com.volkswagen.techchallenge.domain.entity

import java.util.*

class Workspace(
    var id: Long? = null,
    val logicalId: UUID,
    val upperRightCornerX: Int,
    val upperRightCornerY: Int,
    val robots: MutableList<Robot> = ArrayList()
)