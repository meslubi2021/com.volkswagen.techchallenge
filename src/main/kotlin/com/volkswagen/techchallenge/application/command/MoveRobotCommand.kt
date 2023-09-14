package com.volkswagen.techchallenge.application.command

import com.volkswagen.common.cqrs.command.Command
import java.util.*

class MoveRobotCommand(
    val robotLogicalId: UUID,
    val moveSequence: String
) : Command