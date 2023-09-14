package com.volkswagen.techchallenge.application.command

import com.volkswagen.common.cqrs.command.Command
import com.volkswagen.techchallenge.domain.entity.Vector
import com.volkswagen.techchallenge.domain.value.`object`.Direction
import java.util.*

class InitRobotCommand(
    val robotLogicalId: UUID,
    val workspaceLogicalId: UUID,
    val position: Vector,
    val direction: Direction
) : Command