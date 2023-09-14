package com.volkswagen.techchallenge.application.command

import com.volkswagen.common.cqrs.command.Command
import com.volkswagen.techchallenge.domain.value.`object`.Heading
import java.util.*

class CreateRobotCommand(
    val robotLogicalId: UUID,
    val workspaceLogicalId: UUID,
    val positionX: Int,
    val positionY: Int,
    val heading: Heading
) : Command